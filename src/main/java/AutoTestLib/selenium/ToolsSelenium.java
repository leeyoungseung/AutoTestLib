package AutoTestLib.selenium;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ToolsSelenium {

	
	private WebDriver driver = null;
	private WebDriverWait wait = null;
	private Properties prop = null;
	private String screenShotPath = null;
	private JavascriptExecutor jse = null;
	private String testCaseName = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public ToolsSelenium(String browserName, String testCaseName) {
		setProperties(testCaseName);
		startBrowser(browserName);
		this.screenShotPath = prop.getProperty("screenshot.path");
	}
	
	public void setProperties(String testCaseName) {
		this.prop = new Properties();
		InputStream is = null;
		try {
			is = ToolsSelenium.class.getClassLoader().getResourceAsStream("config."+testCaseName+".properties");
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Properties getProperties() {
		return prop;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public void startBrowser(String browserName){
		switch(browserName){
		case "chrome":
			System.setProperty("webdriver.chrome.driver", prop.getProperty("path.chrome"));
			driver = new ChromeDriver();
		default: break;
		}
	}
	
	
	// ----- (1) Funciont of Waiting element load on Web Browser
	
	/**
	 * 
	 * @param locator
	 * @param waitTime
	 * @return
	 */
	public WebElement waitWhenElementVisible(By locator, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * 
	 * @param xpath
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementXpath(String xpath, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	/**
	 * 
	 * @param name
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementName(String name, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
	}
	
	/**
	 * 
	 * @param className
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementClassName(String className, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
	}
	
	/**
	 * 
	 * @param cssSelector
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementCssSelector(String cssSelector, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
	}
	
	/**
	 * 
	 * @param cssSelector
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementID(String id, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	/**
	 * 
	 * @param linkText
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementLinkText(String linkText, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
	}
	
	/**
	 * 
	 * @param partialLinkText
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementPartialLinkText(String partialLinkText, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(partialLinkText)));
	}
	
	/**
	 * 
	 * @param tagName
	 * @param waitTime
	 * @return
	 */
	public WebElement waitElementTagName(String tagName, int waitTime){
		wait = new WebDriverWait(driver, waitTime);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(tagName)));
	}
	
	// ----- (2) Funcion of Using Java Script Action
	
	/**
	 * 
	 * @return
	 */
	public JavascriptExecutor controlToJS(){
		return jse = (JavascriptExecutor)this.driver;
	}
	
	/**
	 * 
	 * @param driver
	 * @return
	 */
	public JavascriptExecutor controlToJS(WebDriver driver){
		return jse = (JavascriptExecutor)driver;
	}
	
	/**
	 * 
	 * @param script :
	 * @Ex : js.executeScript("document.getElementById('some id').value='someValue';");
	 * @param value : 
	 */
	public void sendKeysJSE(String script, String value){
		controlToJS().executeScript(script+".value='"+value+"';");
	}
	
	/**
	 * 
	 * @param script
	 * @Ex : js.executeScript("document.getElementById('enter your element id').click();");
	 */
	public void clickJSE(String script){
		controlToJS().executeScript(script+".click();");
	}
	
	/**
	 * 
	 * @param script
	 * @param value
	 * @Ex : js.executeScript("document.getElementById('enter element id').checked=false;");
	 */
	public void checkBoxHandleJSE(String script, boolean value){
		String checked = "false";
		if(value){
			checked = "true";
		}
		controlToJS().executeScript(script+".checked="+checked+";");
	}
	
	/**
	 * 
	 */
	public void refreshJSE(){
		controlToJS().executeScript("history.go(0)");
	}
	
	/**
	 * 
	 * @param url
	 */
	public void moveToUrlJSE(String url){
		controlToJS().executeScript("window.location = '"+url+"'");
	}
	
	/**
	 * 
	 * @param lange
	 */
	public void scrollPage(String lange){
		controlToJS().executeScript("window.scrollBy(0,"+lange+")");
	}
	
	/**
	 * 
	 * reference : https://www.guru99.com/scroll-up-down-selenium-webdriver.html
	 * @param element : Find WebElement
	 */
	public void scrollToElementAuto(WebElement element){
		controlToJS().executeScript("arguments[0].scrollIntoView();", element);
	}
	
	
	// ----- (3) Function of Utils
	
	public boolean screenShot(String fileName){
		boolean res = true;
		Date date = new Date();
		
		try{
			File capturedScreen = ((TakesScreenshot)this.driver)
					.getScreenshotAs(OutputType.FILE);
			
			String absoluteScreenShotPath = this.screenShotPath + sdf.format(date) +"_"+fileName+".png";
			
			Files.copy(capturedScreen.toPath(), new File(absoluteScreenShotPath).toPath());
		}catch (IOException e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	
	public boolean judgeText(String testNo, String expactedValue, String actualValue) {
		
		boolean result = (expactedValue.equals(actualValue)) ? true : false;
		
		if (result) {
			System.out.println("Success : Expected Value : ("+expactedValue+"), Actual Value : ("+actualValue+")");
			screenShot(testNo+"_success");
		} else { 
			System.out.println("Error : Expected Value : ("+expactedValue+"), Actual Value : ("+actualValue+")");
			screenShot(testNo+"_failure");
		} 
		
		return result;
	}
	
}
