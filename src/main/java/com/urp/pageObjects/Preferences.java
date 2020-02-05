package com.urp.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.urp.commonClasses.Helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Preferences extends Helper  {
	public Preferences(AppiumDriver<MobileElement> driver)
	{
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
	public MobileElement dependencies;
	
	
	@AndroidFindBy(className="android.widget.Button")
	public MobileElement buttons;
	
	@AndroidFindBy(id="android:id/checkbox")
	public MobileElement selectWificheckbox;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='WiFi settings']")
	public MobileElement wifisettings;
	

	@AndroidFindBy(className="android.widget.EditText")
	public MobileElement wifiName;
	
	@AndroidFindBy(className="android.widget.Button")
	public MobileElement okButton;
	
	public void clickonDependencies() {
		clickOn(dependencies);	
	}
	
	public void selectWifichkbox() {
		clickOn(selectWificheckbox);	
	}
	
	public void clickOnWifisetttigs() {
		clickOn(wifisettings);	
	}
	
	public void enterWifiName(String Name) {
		sendText(wifiName, Name);
	}
	
	public void clickOnOkbutton() {
		clickOn(okButton);	
	}
	
	
	
	
	
   
  
    
	
	
	
    
   
	
	
}
