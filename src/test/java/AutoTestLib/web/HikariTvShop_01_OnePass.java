package AutoTestLib.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HikariTvShop_01_OnePass {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties prop;
	
	@Before
	public void setUp() throws Exception {
		prop = new Properties();
		InputStream is = null;
		try {
			is = HikariTvShop_01_OnePass.class.getClassLoader().getResourceAsStream("config.hikari_tv_shop.properties");
			prop.load(is);

		} catch (Exception e) {
			System.out.println("Properties load fail!!");
		}
		
		System.setProperty("webdriver.chrome.driver", prop.getProperty("path.chrome"));

		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test01Onepass() throws Exception {
		driver.get("https://shop.hikaritv.net/");
		driver.findElement(By.xpath("//div[@id='header']/div/div/a")).click();
		driver.findElement(By.linkText("ログイン")).click();
		driver.findElement(By.id("webid")).click();
		driver.findElement(By.id("webid")).clear();
		driver.findElement(By.id("webid")).sendKeys(prop.getProperty("login.webid.id"));
		driver.findElement(By.id("aikotoba")).clear();
		driver.findElement(By.id("aikotoba")).sendKeys(prop.getProperty("login.webid.pw"));
		driver.findElement(By.xpath("//input[@value='ログイン']")).click();
		driver.findElement(By.id("searchText1")).click();
		driver.findElement(By.id("searchText1")).clear();
		
		// https://imhotk.tistory.com/1067 
		// Issue about encoding
		String searchText = new String(prop.getProperty("search.commodity.no01").getBytes("ISO-8859-1"), "UTF-8");
		driver.findElement(By.id("searchText1")).sendKeys(searchText);
		driver.findElement(By.xpath("//div[@id='suggest0']/span")).click();
		driver.findElement(By.xpath("//a[@id='addcart/plala/2010062716_blockDoubleSubmitFlg']/span")).click();
		driver.findElement(By.id("move/check_shipping/plala//_blockDoubleSubmitFlg")).click();
		driver.findElement(By.xpath("//a[@id='move/payment_blockDoubleSubmitFlg']/span")).click();
		driver.findElement(By.id(prop.getProperty("payment.id.rakuten"))).click();
		driver.findElement(By.xpath("//a[@id='register']/span")).click();
		driver.findElement(By.xpath("//a[@id='register_blockDoubleSubmitFlg']/span")).click();
		driver.findElement(By.xpath("//img[@alt='次へ']")).click();
		driver.findElement(By.id("userid")).click();
		driver.findElement(By.id("userid")).clear();
		driver.findElement(By.id("userid")).sendKeys(prop.getProperty("login.rakuten.id"));
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("login.rakuten.pw"));
		driver.findElement(By.id("loginBtn")).click();
		driver.findElement(By.id("confirm_top_button")).click();
		driver.findElement(By.name("securityCd")).click();
		driver.findElement(By.name("securityCd")).clear();
		driver.findElement(By.name("securityCd")).sendKeys(prop.getProperty("credit.key"));
		driver.findElement(By.xpath("//div[@id='CONTENTS']/form/div[4]/div/div")).click();
		driver.findElement(By.id("order1")).click();
		driver.findElement(By.name("Password")).click();
		driver.findElement(By.name("Password")).clear();
		driver.findElement(By.name("Password")).sendKeys(prop.getProperty("credit.pw"));
		driver.findElement(By.id("sendButton")).click();
		driver.findElement(By.cssSelector("#cartInfo > a")).click();
		acceptNextAlert = true;
		driver.findElement(By.xpath("//img[@alt='注文をキャンセルする']")).click();
		assertEquals("注文をキャンセルしてもよろしいですか？", closeAlertAndGetItsText());
		driver.findElement(By.xpath("//form[@id='main']/div/fieldset/div[3]/div[3]/h3")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
