package com.urp.commonClasses;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.TouchAction;


public class Helper extends base {
 

  WebDriverWait wait;
  String data;
  TouchAction t ;
  

	public static Logger logger = Logger.getLogger(Helper.class.getName());
	
  
  
	public Helper(AppiumDriver<MobileElement> driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 15);
		 t =new TouchAction(driver);
		
	}
	
	
	
	/**
	 * this function will help to click on the mobile element
	 * */
	
	public void clickOn(MobileElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		logger.info("clicked on the element found by "+element);
	}
	
	
	
	
	/**
	 * This function will help to send data to the mobile element
	 * */
	
	public void sendText(MobileElement element ,String text){
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
		logger.info("send the text "+text+"to the element found by "+element);
	}
	
	/**
	 * Important Note 1: Value of @content-desc property can be obtained at runtime
	 by using getAttribute() method and passing the attribute name as “contentDescription” .
	Instead of “contentDescription”, you can also use “name” as well. 
	But “name” doesn’t work with UIAutomator2. 
	Hence it’s a good approach to use “contentDescription”.
	
	Important Note 2: content-desc would be the ideal property that you can use in 
	your test automation scripts. This is due to the fact that app developers can 
	set value for this property. 
	They might not be able to set the property for resource-id always, 
	because a lot of times, the resource-id value is set by the underlying 
	Android or iOS framework.
	 * */
	
	public String getText(MobileElement element,String attribute){
		
		if(attribute.equals("text")){
			data=element.getAttribute("text");
		}else if(attribute.equals("content-desc")){
			data=element.getAttribute("contentDescription");
		}else{
			System.out.println("The attribute you have eneterd is wrong.Please enter correct one ...");
		}
		return data;
	}
	
	public void clickBackButton(){
		driver.navigate().back();
		}
	public static void Wait(int timeOutSec) throws Exception {
		Thread.sleep(timeOutSec*1000);
}
	
	public void tap(AppiumDriver<MobileElement> driver,MobileElement element){
		t.tap(tapOptions().withElement(element(element))).perform();
	}
	
	public void longPressOnElement(AppiumDriver<MobileElement> driver,MobileElement element){
		t.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(2))).release().perform();
	}
	//drag and drop
	public void longPressOnElementFirstToSecondElement(AppiumDriver<MobileElement> driver,MobileElement element,MobileElement element1){
		t.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(2))).moveTo(element(element1)).release().perform();			
	}
	

	public static void scrollToElemntBytext(AppiumDriver<MobileElement> driver,String text){
		String s1="new UiScrollable(new UiSelector()).scrollIntoView(text(\"";
		String s2="\"));";
		System.out.println(s1+text+s2);
		((FindsByAndroidUIAutomator<MobileElement>) driver).findElementByAndroidUIAutomator(s1+text+s2);
		System.out.println(s1+text+s2);
	}
	
	
	
	
}
