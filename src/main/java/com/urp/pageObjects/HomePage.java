package com.urp.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.urp.commonClasses.Helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All th objects belonging to one page will be defined in java class
public class HomePage extends Helper{
// 1. Is to call the driver object from testcase to Pageobject file
	
	//Concatenate driver
	public HomePage(AppiumDriver<MobileElement> driver)
	{
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public MobileElement Preferences;
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Views']")
	public MobileElement Views;
	
	
	public void clickonPrefrence() {
		logger.info("******** clicking on prefrences*********");
		clickOn(Preferences);
	}
	
	public void clickonViews() {
		logger.info("******** clicking on prefrences*********");
		clickOn(Views);
		
	}
	
	
	
}
