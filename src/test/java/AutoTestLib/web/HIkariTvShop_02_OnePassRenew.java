package AutoTestLib.web;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import AutoTestLib.selenium.ToolsSelenium;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HIkariTvShop_02_OnePassRenew {

	private static ToolsSelenium tool;
	private static WebDriver driver;
	private static Properties prop;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		if (tool == null) {
			tool = new ToolsSelenium("chrome","hikari_tv_shop");
			driver = tool.getWebDriver();
			prop = tool.getProperties();
		}
	}

	
	/**
	 * 001 : Top Page check
	 * @throws Exception
	 */
	@Test
	public void test_001() throws Exception {
		driver.get("https://shop.hikaritv.net/");
		
		String currentUrl = driver.getCurrentUrl();
		
		assertTrue("Failure Test 001", tool.judgeText("test_001", "https://shop.hikaritv.net/", currentUrl));
	}
	

	/**
	 * 002 : Login
	 * @throws Exception
	 */
	@Test
	public void test_002() throws Exception {
		tool.waitElementLinkText("ログイン", 5);
		driver.findElement(By.linkText("ログイン")).click();
		tool.screenShot("test_002_01_LoginPage");
		
		driver.findElement(By.id("webid")).click();
		driver.findElement(By.id("webid")).clear();
		driver.findElement(By.id("webid")).sendKeys(prop.getProperty("login.webid.id"));
		
		driver.findElement(By.id("aikotoba")).clear();
		driver.findElement(By.id("aikotoba")).sendKeys(prop.getProperty("login.webid.pw"));
		
		tool.screenShot("test_002_02_LoginForm");
		driver.findElement(By.xpath("//input[@value='ログイン']")).click();
		
		String xpathNicname = "//*[@id=\"header\"]/div[1]/div[2]/ul[1]/li[1]/span";
		tool.waitElementXpath(xpathNicname, 5);
		
		String loadedNicname = driver.findElement(By.xpath(xpathNicname)).getText();
		
		assertTrue("Failure Test 002", tool.judgeText("test_002", prop.getProperty("login.webid.nickname"), loadedNicname));
	}
	
	
	/**
	 * 003 : search
	 * @throws Exception
	 */
	@Test
	public void test_003() throws Exception {
		driver.findElement(By.id("searchText1")).click();
		driver.findElement(By.id("searchText1")).clear();
		
		String searchText = new String(prop.getProperty("search.commodity.no01").getBytes("UTF-8"), "UTF-8");
		driver.findElement(By.id("searchText1")).sendKeys(searchText);
		String inputedText = driver.findElement(By.id("searchText1")).getAttribute("value");

		assertTrue("Failure Test 003", tool.judgeText("test_003", searchText, inputedText));
	}
	
	
	/**
	 * 004 : search result
	 * @throws Exception
	 */
	@Test
	public void test_004() throws Exception {
		driver.findElement(By.xpath("//div[@id='suggest0']/span")).click();
		tool.screenShot("test_004_01_commoditypage");
		
		String searchText = new String(prop.getProperty("search.commodity.no01").getBytes("UTF-8"), "UTF-8");
		String loadedText = driver.findElement(By.xpath("//*[@id=\"cart\"]/div/section/div[2]/div/h1")).getText();
		
		assertTrue("Failure Test 004", tool.judgeText("test_004", searchText, loadedText));
	}
	
}
