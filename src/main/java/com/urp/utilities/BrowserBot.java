package com.urp.utilities;


import static com.olo.util.PropertyReader.configProp;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.olo.util.Commons;
import com.olo.util.OloExpectedConditions;
import com.olo.util.VerificationError;
import com.olo.util.VerificationErrorsInTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class BrowserBot{
	
	protected static AndroidDriver<MobileElement> driver;
	
	protected static long pageWaitAndWaitTimeOut=30;
	
	private static final Logger logger = LogManager.getLogger(BrowserBot.class.getName());
	

	public BrowserBot(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		if(configProp.containsKey("pageWaitAndWaitTimeOut")){
			pageWaitAndWaitTimeOut=Integer.parseInt(configProp.getProperty("pageWaitAndWaitTimeOut"));
		}
	}
	
	public static void Wait(int timeOutSec) throws Exception {
			Thread.sleep(timeOutSec*1000);
	}
	
	public void waitForFrameToBeAvailableAndSwitchToIt(String frameLocator,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void waitForFrameToBeAvailableAndSwitchToIt(String frameLocator){
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void waitForElementPresent(By by) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForElementPresent(By by,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitForElementNotPresent(MobileElement element){
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(OloExpectedConditions.elementNotPresent(element));
	}
	
	public void waitForElementNotPresent(MobileElement element,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(OloExpectedConditions.elementNotPresent(element));
	}
	
	public void waitForElementNotPresent(By by){
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)));
	}
	
	public void waitForElementNotPresent(By by,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)));
	}
	
	public static void waitForVisible(MobileElement element){
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForNotVisible(MobileElement element) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
	}
	
	public void waitForVisible(MobileElement element,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForNotVisible(final MobileElement element,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
	}
	
	public void waitForValue(final MobileElement element, final String value) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return getValue(element).equals(value);
    		}
    	});
	}
	
	public void waitForNotValue(final MobileElement element, final String value) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return !getValue(element).equals(value);
    		}
    	});
	}
	
	public void waitForValue(final MobileElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return getValue(element).equals(value);
    		}
    	});
	}
	
	public void waitForNotValue(final MobileElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return !getValue(element).equals(value);
    		}
    	});
	}
	
	public void waitForEditable(final MobileElement element) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return isElementPresent(element) && isVisible(element) && isEnabled(element);
    		}
    	});
	}
	
	public void waitForNotEditable(final MobileElement element) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return isElementPresent(element) && isVisible(element) && !isEnabled(element);
    		}
    	});
	}
	
	public void waitForEditable(final MobileElement element,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return isElementPresent(element) && isVisible(element) && isEnabled(element);
    		}
    	});
	}
	
	public void waitForNotEditable(final MobileElement element,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return isElementPresent(element) && isVisible(element) && !isEnabled(element);
    		}
    	});
	}
	
	public void waitForText(final MobileElement element, final String value) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return getText(element).equals(value);
    		}
    	});
	}
	
	public void waitForNotText(final MobileElement element, final String value) throws Exception{
		new WebDriverWait(driver, pageWaitAndWaitTimeOut) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return !getText(element).equals(value);
    		}
    	});
	}
	
	public void waitForText(final MobileElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return getText(element).equals(value);
    		}
    	});
	}
	
	public void waitForNotText(final MobileElement element, final String value,long timeOutInSeconds) throws Exception{
		new WebDriverWait(driver, timeOutInSeconds) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return !getText(element).equals(value);
    		}
    	});
	}
	
	public void waitForAlert(final String pattern){
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.switchTo().alert().getText().equals(pattern);
            }
        });
	}
	
	public void waitForAlert(final String pattern,long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.switchTo().alert().getText().equals(pattern);
            }
        });
	}
	
	public void waitForAlertPresent(){
		new WebDriverWait(driver, pageWaitAndWaitTimeOut).until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForAlertPresent(long timeOutInSeconds){
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForTitle(String pageTitle){
		new WebDriverWait(driver,pageWaitAndWaitTimeOut).until(ExpectedConditions.titleIs(pageTitle));
	}
	
	public void waitForTitle(String pageTitle,long timeOutInSeconds){
		new WebDriverWait(driver,timeOutInSeconds).until(ExpectedConditions.titleIs(pageTitle));
	}
	
	public void assertFail(String errorMessage){
		Assert.fail(errorMessage);
	}
	
	public void verifyFail(String errorMessage) throws Exception{
		try {
			assertFail(errorMessage);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertTrue(boolean condition,String message){
		Assert.assertTrue(condition, message);
	}
	
	public void verifyTrue(boolean condition,String message) throws Exception{
		try {
			assertTrue(condition,message);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertTrue(boolean condition){
		Assert.assertTrue(condition);
	}
	
	public void verifyTrue(boolean condition) throws Exception{
		try {
			assertTrue(condition);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertFalse(boolean condition,String message){
		Assert.assertFalse(condition, message);
	}
	
	public void verifyFalse(boolean condition,String message) throws Exception{
		try {
			assertFalse(condition,message);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertFalse(boolean condition){
		Assert.assertTrue(condition);
	}
	
	public void verifyFalse(boolean condition) throws Exception{
		try {
			assertTrue(condition);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public static void assertEquals(Object actual, Object expected){
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyEquals(Object actual, Object expected) throws Exception{
		try {
			assertEquals(actual, expected);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotEquals(Object actual, Object expected){
		Assert.assertNotEquals(actual, expected);
	}
	
	public void verifyNotEquals(Object actual, Object expected) throws Exception{
		try {
			assertNotEquals(actual, expected);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertTitle(String expectedTitle) throws Exception{
		Assert.assertEquals(getTitle(),expectedTitle);
	}
	
	public void verifyTitle(String expectedTitle) throws Exception{
		try {
			assertTitle(expectedTitle);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotTitle(String unexpectedTitle) throws Exception{
		Assert.assertNotEquals(getTitle(),unexpectedTitle);
	}
	
	public void verifyNotTitle(String unexpectedTitle) throws Exception{
		try {
			assertNotTitle(unexpectedTitle);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertValue(MobileElement element, String expectedValue){
		String actualValue = getAttribute(element,"value");
		logger.info("Comparing elementValue : '"+actualValue +"' equals to expectedValue : '"+expectedValue+"' in element "+element);
		Assert.assertEquals( actualValue, expectedValue);
	}
	
	public void verifyValue(MobileElement element, String expectedValue) throws Exception{
		try {
			assertValue(element, expectedValue);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotValue(MobileElement element,String unexpectedValue) throws Exception {
		String actualValue = getAttribute(element,"value");
		logger.info("Comparing elementValue : '"+actualValue +"' not equals to unexpectedValue : '"+unexpectedValue+"' in element "+element);
		Assert.assertNotEquals(actualValue, unexpectedValue);
	}
	
	public void verifyNotValue(MobileElement element,String unexpectedValue) throws Exception {
		try {
			assertNotValue(element, unexpectedValue);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertText(MobileElement element, String expectedText){
		String actualText = getText(element).toString();
		logger.info("Comparing elementText : '"+actualText +"' equals to expectedText : '"+expectedText+"' in element "+element);
		Assert.assertEquals(actualText, expectedText);
	}
	
	public void verifyText(MobileElement element, String expectedText) throws Exception{
		try {
			assertText(element, expectedText);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotText(MobileElement element,String unexpectedText) throws Exception{
		String actualText = getText(element);
		logger.info("Comparing elementText : '"+actualText +"' not equals to unexpectedText : '"+unexpectedText+"' in element "+element);
		Assert.assertNotEquals(actualText, unexpectedText);
	}
	
	public void verifyNotText(MobileElement element,String unexpectedText) throws Exception{
		try {
			assertNotText(element, unexpectedText);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertSelectedText(MobileElement element, String expectedSelectedText){
		Assert.assertEquals(element.getAttribute("value"), expectedSelectedText);
	}
	
	public void verifySelectedText(MobileElement element, String expectedSelectedText) throws Exception{
		try {
			assertSelectedText(element, expectedSelectedText);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotSelectedText(MobileElement element, String unexpectedSelectedText){
		Assert.assertNotEquals(element.getAttribute("value"), unexpectedSelectedText);
	}
	
	public void verifyNotSelectedText(MobileElement element, String unexpectedSelectedText) throws Exception{
		try {
			assertSelectedText(element, unexpectedSelectedText);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertElementPresent(MobileElement element){
		Assert.assertTrue(isElementPresent(element));
		
	}
	
	public void verifyElementPresent(MobileElement element) throws Exception{
		try {
			assertElementPresent(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertElementNotPresent(MobileElement element) throws Exception{
		Assert.assertFalse(isElementPresent(element));
	}
	
	public void verifyElementNotPresent(MobileElement element) throws Exception{
		try {
			assertElementNotPresent(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertChecked(MobileElement element){
		Assert.assertTrue(element.isSelected());
	}
	
	public void verifyChecked(MobileElement element) throws Exception{
		try {
			assertChecked(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotChecked(MobileElement element){
		Assert.assertFalse(element.isSelected());
	}
	
	public void verifyNotChecked(MobileElement element) throws Exception{
		try {
			assertNotChecked(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertSelectOptionsSize(MobileElement element,String expectedSize) throws Exception {
		Assert.assertEquals(select(element).getOptions().size(),expectedSize);
	}
	
	public void verifySelectOptionsSize(MobileElement element,String expectedSize) throws Exception {
		try {
			assertSelectOptionsSize(element, expectedSize);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotSelectOptionsSize(MobileElement element,String expectedNotSize) throws Exception {
		Assert.assertNotEquals(select(element).getOptions().size(),expectedNotSize);
	}
	
	public void verifyNotSelectOptionsSize(MobileElement element,String expectedNotSize) throws Exception {
		try {
			assertNotSelectOptionsSize(element, expectedNotSize);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertVisible(MobileElement element) throws Exception {
		Assert.assertTrue(isVisible(element));
	}
	
	public void verifyVisible(MobileElement element) throws Exception {
		try {
			assertVisible(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotVisible(MobileElement element) throws Exception{
		Assert.assertFalse(isVisible(element));
	}
	
	public void verifyNotVisible(MobileElement element) throws Exception{
		try {
			assertNotVisible(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertEditable(MobileElement element) throws Exception {
		Assert.assertTrue(isEnabled(element));
	}
	
	public void verifyEditable(MobileElement element) throws Exception {
		try {
			assertEditable(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotEditable(MobileElement element) throws Exception{
		Assert.assertFalse(isEnabled(element));
	}
	
	public void verifyNotEditable(MobileElement element) throws Exception{
		try {
			assertNotEditable(element);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertAlert(String expectedAlertText) throws Exception{
		Assert.assertEquals(driver.switchTo().alert().getText(), expectedAlertText);
	}
	
	public void verifyAlert(String expectedAlertText) throws Exception{
		try {
			assertAlert(expectedAlertText);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotAlert(String unexpectedAlertText) throws Exception{
		Assert.assertNotEquals(driver.switchTo().alert().getText(), unexpectedAlertText);
	}
	
	public void verifyNotAlert(String unexpectedAlertText) throws Exception{
		try {
			assertNotAlert(unexpectedAlertText);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertAttribute(MobileElement element,String attributeName,String expectedAttributeValue) throws Exception{
		Assert.assertEquals(getAttribute(element, attributeName), expectedAttributeValue);
	}
	
	public void verifyAttribute(MobileElement element,String attributeName,String expectedAttributeValue) throws Exception{
		try {
			assertAttribute(element, attributeName, expectedAttributeValue);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public void assertNotAttribute(MobileElement element,String attributeName,String unexpectedAttributeValue) throws Exception{
		Assert.assertNotEquals(getAttribute(element, attributeName), unexpectedAttributeValue);
	}
	
	public void verifyNotAttribute(MobileElement element,String attributeName,String unexpectedAttributeValue) throws Exception{
		try {
			assertNotAttribute(element, attributeName, unexpectedAttributeValue);
		} catch (AssertionError e) {
			addVerificationError(e);
		}
	}
	
	public Select select(MobileElement element){
		return new Select(element);
	}
	
	public Keyboard getKeyBoard(){
		return ((HasInputDevices) driver).getKeyboard();
	}
	
	public Mouse getMouse(){
		return ((HasInputDevices) driver).getMouse();
	}
	
	public Actions actions(){
		return new Actions(driver);
	}
	
	public String getBrowserName(){
		return ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
	}
	
	public String getBrowserVersion(){
		return ((RemoteWebDriver) driver).getCapabilities().getVersion();
	}
	
	public void get(String url){
		driver.get(url);
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	public boolean isElementPresent(MobileElement element){
		try {
			element.getTagName();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
	}
	
	public boolean isVisible(MobileElement element){
		return element.isDisplayed();
	}
	
	public boolean isEnabled(MobileElement element){
		return element.isEnabled();
	}
	
	public String getText(MobileElement element){
		return element.getText();
	}
	
	public String getTextFromHiddenElement(MobileElement element){
		return executeJavascript(element, "return arguments[0].innerHTML");
	}
	
	public String getValue(MobileElement element){
		return element.getAttribute("value");
	}
	
	public String getAttribute(MobileElement element,String attributeName){
		return element.getAttribute(attributeName);
	}
	
	public String getTagName(MobileElement element){
		return element.getTagName();
	}
	
	public static MobileElement findElement(String locator){
		return driver.findElement(byLocator(locator));
	}
	
	public MobileElement findElement(By by){
		return driver.findElement(by);
	}
	
	public List<MobileElement> findElements(String locator){
		return driver.findElements(byLocator(locator));
	}
	
	public List<MobileElement> findElements(By by){
		return driver.findElements(by);
	}
	
	public void clear(MobileElement element){
		element.clear();
	}
	
	public static void type(MobileElement element,String value){
		element.sendKeys(value);	
	}
  
	public void clearAndType(MobileElement element,String value){
		element.clear();
		element.sendKeys(value);
	}
	
	public void typeRandomAlphabets(MobileElement element,String value){
		element.sendKeys(value+RandomStringUtils.randomAlphabetic(8));
	}
	
	public void typeRandomAlphabets(MobileElement element, String value, int numberOfRandomAlphabets){
		element.sendKeys(value+RandomStringUtils.randomAlphabetic(numberOfRandomAlphabets));
	}
	
	public String typeRandomAlphabets(MobileElement element){
		
		String formatted = "CaseName" + RandomStringUtils.randomAlphabetic(8);
		element.sendKeys(formatted);
		return formatted;
	}
	
	
	
	public void typeRandomAlphabets(MobileElement element, int numberOfRandomAlphabets){
		element.sendKeys(RandomStringUtils.randomAlphabetic(numberOfRandomAlphabets));
	}
	
	public void clearAndTypeRandomAlphabets(MobileElement element, String value){
		element.clear();
		element.sendKeys(value+RandomStringUtils.randomAlphabetic(8));
	}
	
	public void clearAndTypeRandomAlphabets(MobileElement element, String value, int numberOfRandomAlphabets){
		element.clear();
		element.sendKeys(value+RandomStringUtils.randomAlphabetic(numberOfRandomAlphabets));
	}
	
	public void clearAndTypeRandomAlphabets(MobileElement element){
		element.clear();
		element.sendKeys(RandomStringUtils.randomAlphabetic(8));
	}
	
	public void clearAndTypeRandomAlphabets(MobileElement element, int numberOfRandomAlphabets){
		element.clear();
		element.sendKeys(RandomStringUtils.randomAlphabetic(numberOfRandomAlphabets));
	}
	
	public void typeRandomNumbers(MobileElement element, String value){
		element.sendKeys(value+RandomStringUtils.randomNumeric(8));
	}
	
	public void typeRandomNumbers(MobileElement element, String value, int numberOfRandomNumbers){
		element.sendKeys(value+RandomStringUtils.randomNumeric(numberOfRandomNumbers));
	}
	
	public void typeRandomNumbers(MobileElement element){
		element.sendKeys(RandomStringUtils.randomNumeric(8));
	}
	
	public void typeRandomNumbers(MobileElement element, int numberOfRandomNumbers){
		element.sendKeys(RandomStringUtils.randomNumeric(numberOfRandomNumbers));
	}
	
	public void clearAndTypeRandomNumbers(MobileElement element,String value){
		element.clear();
		element.sendKeys(value+RandomStringUtils.randomNumeric(8));
	}
	
	public void clearAndTypeRandomNumbers(MobileElement element,String value, int numberOfRandomNumbers){
		element.clear();
		element.sendKeys(value+RandomStringUtils.randomNumeric(numberOfRandomNumbers));
	}
	
	public void clearAndTypeRandomNumbers(MobileElement element){
		element.clear();
		element.sendKeys(RandomStringUtils.randomNumeric(8));
	}
	
	public void clearAndTypeRandomNumbers(MobileElement element, int numberOfRandomNumbers){
		element.clear();
		element.sendKeys(RandomStringUtils.randomNumeric(numberOfRandomNumbers));
	}
	
	public void typeRandomAlphaNumeric(MobileElement element, String value){
		element.sendKeys(value+RandomStringUtils.randomAlphanumeric(8));
	}
	
	public void typeRandomAlphaNumeric(MobileElement element, String value, int numberOfRandomAlphanumeric){
		element.sendKeys(value+RandomStringUtils.randomAlphanumeric(numberOfRandomAlphanumeric));
	}
	
	public void typeRandomAlphaNumeric(MobileElement element){
		element.sendKeys(RandomStringUtils.randomAlphanumeric(8));
	}
	
	public void typeRandomAlphaNumeric(MobileElement element, int numberOfRandomAlphanumeric){
		element.sendKeys(RandomStringUtils.randomAlphanumeric(numberOfRandomAlphanumeric));
	}
	
	public void clearAndTypeRandomAlphaNumeric(MobileElement element,String value){
		element.clear();
		element.sendKeys(value+RandomStringUtils.randomAlphanumeric(8));
	}
	
	public void clearAndTypeRandomAlphaNumeric(MobileElement element,String value, int numberOfRandomAlphanumeric){
		element.clear();
		element.sendKeys(value+RandomStringUtils.randomAlphanumeric(numberOfRandomAlphanumeric));
	}
	
	public void clearAndTypeRandomAlphaNumeric(MobileElement element){
		element.clear();
		element.sendKeys(RandomStringUtils.randomAlphanumeric(8));
	}
	
	public void clearAndTypeRandomAlphaNumeric(MobileElement element, int numberOfRandomAlphanumeric){
		element.clear();
		element.sendKeys(RandomStringUtils.randomAlphanumeric(numberOfRandomAlphanumeric));
	}
	
	public static void click(MobileElement element) throws Exception{
		
		 element.click();	 
		
	}
	public void clickByJS(MobileElement element) throws Exception {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()",element);
	}
	
	public void clickAt(MobileElement element,String value){
		String[] v = value.split(",");
		new Actions(driver).moveByOffset(Integer.parseInt(v[0]), Integer.parseInt(v[1])).click(element).perform();
	}
	
	public void doubleClick(MobileElement element){
		new Actions(driver).doubleClick(element).perform();
	}
	
	public void selectByText(MobileElement element, String visibleText){
		select(element).selectByVisibleText(visibleText);
	}
	
	public void dragAndDrop(MobileElement element,String value) throws Exception {
		String[] v = value.split(",");
		new Actions(driver).dragAndDropBy(element, Integer.parseInt(v[0]), Integer.parseInt(v[1])).perform();
	}
	
	public void contextMenu(MobileElement element){
		new Actions(driver).contextClick(element).perform();
	}
	public static String getCurrentDate(String Dateformat) {
		String DATE_FORMAT = Dateformat;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		String strCurrentDate = sdf.format(c1.getTime());
		return strCurrentDate;
	}
	
	public void contextMenuAt(MobileElement element,String value){
		String[] v = value.split(",");
		new Actions(driver).moveByOffset(Integer.parseInt(v[0]), Integer.parseInt(v[1])).contextClick(element).perform();
	}
	
	public void mouseDown(MobileElement element){
		getMouse().mouseDown((Coordinates) element.getLocation());
	}
	
	public void mouseDownAt(MobileElement element,String coordString) throws Exception {
		String[] v = coordString.split(",");
		getMouse().mouseDown((Coordinates) element.getLocation().moveBy(Integer.parseInt(v[0]), Integer.parseInt(v[1])));
	}
	
	public void mouseUp(MobileElement element){
		getMouse().mouseUp((Coordinates) element.getLocation());
	}
	
	public void mouseUpAt(MobileElement element,String coordString){
		String[] v = coordString.split(",");
		getMouse().mouseUp((Coordinates) element.getLocation().moveBy(Integer.parseInt(v[0]), Integer.parseInt(v[1])));
	}
	
	public void mouseOver(MobileElement element) throws Exception{
		new Actions(driver).moveToElement(element).build().perform();
	}
	
	public void focus(MobileElement element){
		element.sendKeys(Keys.TAB);
	}
	public void Enterv(MobileElement element){
		element.sendKeys(Keys.ARROW_DOWN);
		element.sendKeys(Keys.ENTER);
	}
	
	public void keyDown(MobileElement element, String value){
		new Actions(driver).keyDown(element, Keys.valueOf(value)).perform();
	}
	
	public void keyDown(MobileElement element, Keys key){
		new Actions(driver).keyDown(element, key).perform();
	}
	
	public void keyUp(MobileElement element, String value){
		new Actions(driver).keyUp(element, Keys.valueOf(value)).perform();
	}
	
	public void keyUp(MobileElement element, Keys key){
		new Actions(driver).keyUp(element, key).perform();
	}
	
	public void controlKeyUp(){
		new Actions(driver).keyUp(Keys.CONTROL).perform();
	}
	
	public void controlKeyDown(){
		new Actions(driver).keyDown(Keys.CONTROL).perform();
	}
	
	public void chooseOk(){
		driver.switchTo().alert().accept();
	}
	
	public void chooseCancel(){
		driver.switchTo().alert().dismiss();
	}
	
	public void check(MobileElement checkBoxElement){
		if(!checkBoxElement.isSelected()){
			checkBoxElement.click();
		}
	}
	
	public void uncheck(MobileElement checkBoxElement){
		if(checkBoxElement.isSelected()){
			checkBoxElement.click();
		}
	}
	
	public void captureScreenshot(String screenShotPath) throws Exception{
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "//screenshots//PassScreenshot//" + screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catcsh block
			e.printStackTrace();
		}
		//put screenshot file in reports
	}
	
	public void deleteAllVisibleCookies(){
		driver.manage().deleteAllCookies();
	}
	
	public Object executeJavascript(String executeJavascript){
		return ((JavascriptExecutor)driver).executeScript(executeJavascript);
	}
	
	public String executeJavascript(MobileElement MobileElement,String executeJavascript){
		return (String) ((JavascriptExecutor)driver).executeScript(executeJavascript, MobileElement);
	}
	
	public void switchToDefault(){
		driver.switchTo().defaultContent();
	}
	
	public void windowMaximize(){
		driver.manage().window().maximize();
	}
	
	public void windowFocus(){
		driver.switchTo().window(driver.getWindowHandle());
	}
	
	public String getWindowHandle(){
		return driver.getWindowHandle();
	}
	
	public Set<String> getWindowHandles(){
		return driver.getWindowHandles();
	}
	
	public void switchToWindow(String nameOrHandle){
		driver.switchTo().window(nameOrHandle);
	}
	
	public long getTimeOut(){
		return pageWaitAndWaitTimeOut;
	}
	
	public static By byLocator(String locator) {
		if(locator.startsWith("css=")){
			locator=locator.replaceFirst("css=", "");
			return By.cssSelector(locator);
		}else if(locator.startsWith("xpath=")){
			locator=locator.replaceFirst("xpath=", "");
			return By.xpath(locator);
		}else if(locator.startsWith("class=")){
			locator=locator.replaceFirst("class=", "");
			return By.className(locator);
		}else if(locator.startsWith("id=")){
			locator=locator.replaceFirst("id=", "");
			return By.id(locator);
		}else if(locator.startsWith("linkText=")){
			locator=locator.replaceFirst("linkText=", "");
			return By.linkText(locator);
		}else if(locator.startsWith("partialLinkText=")){
			locator=locator.replaceFirst("partialLinkText=", "");
			return By.partialLinkText(locator);
		}else if(locator.startsWith("name=")){
			locator=locator.replaceFirst("name=", "");
			return By.partialLinkText(locator);
		}else{
			return By.id(locator);
		}
	}
	
	
	protected void addVerificationError(AssertionError e) throws Exception{
		ITestResult testResult = Reporter.getCurrentTestResult();
		
		String screenShotFileName = System.currentTimeMillis()+".png";
		String screenShotPath = testResult.getTestContext().getOutputDirectory()+File.separator+"screenshots"+File.separator+screenShotFileName;
		captureScreenshot(screenShotPath);
		VerificationError ve = new VerificationError();
		if(e.getStackTrace() != null) {
			logger.error("Verification Error : "+Commons.getStackTraceAsString(e));
		}else{
			logger.error("Verification Error : Null");
		}
		ve.setScreenShotFileName(screenShotFileName);
		ve.setAssertionError(e);
		VerificationErrorsInTest.addError(testResult, ve);
	}
	
}

