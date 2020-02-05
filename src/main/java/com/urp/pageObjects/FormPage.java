package com.urp.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.urp.commonClasses.Helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends Helper {
	public FormPage(AppiumDriver<MobileElement> driver)
	{
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	

	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private MobileElement nameField;
	
	
	@AndroidFindBy(xpath="//*[@text='Female']")
	public MobileElement femaleOption;
	
	@AndroidFindBy(id="android:id/text1")
	private MobileElement countrySelection;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='India']")
	private MobileElement selectIndia;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")
	private MobileElement Btnshop;

	public MobileElement enterNameField(){
		sendText(nameField, "mohit");
		return nameField;
	}
	
	public MobileElement clickOnShopButton(){
	clickOn(Btnshop);
	return Btnshop;
	}
	
	public MobileElement selectGender(){
		clickOn(femaleOption);
		return femaleOption;
		}
	
	public void getcountrySelection(String text){  
		clickOn(countrySelection);
		scrollToElemntBytext(driver, text);
    	clickOn(selectIndia);	
	}
	
	//driver.findElementByXPath("//android.widget.TextView[@resource-id='android:id/text1']").click();
			//scrollToElemntBytext(driver, "India");
	
	
	
}
