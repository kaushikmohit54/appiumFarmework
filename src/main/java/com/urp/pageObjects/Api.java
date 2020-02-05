package com.urp.pageObjects;

import org.openqa.selenium.support.PageFactory;

import com.urp.commonClasses.Helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Api extends Helper {
	
	public static AppiumDriver<MobileElement> driver;
	
	
	public Api(AppiumDriver<MobileElement> driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Views']")
	 MobileElement views;
	
	public void clickOnViews() throws InterruptedException{
		clickOn(views);
		wait(2);
		
	}

}
