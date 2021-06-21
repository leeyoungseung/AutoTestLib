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
		
		boolean result = (currentUrl.equals("https://shop.hikaritv.net/")) ? true : false;
		
		if (result) {
			tool.screenShot("test_001_success");
		} else {
			tool.screenShot("test_001_failure");
		}
		
		assertTrue("Failure Test 001", result);
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
		boolean result = (loadedNicname.equals(prop.getProperty("login.webid.nickname"))) ? true : false;
		
		if (result) {
			tool.screenShot("test_002_success");
		} else {
			tool.screenShot("test_002_failure");
		}
		
		assertTrue("Failure Test 002", result);
	}
	
}
