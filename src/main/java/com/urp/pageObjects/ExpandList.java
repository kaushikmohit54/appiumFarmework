package com.urp.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.urp.commonClasses.Helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ExpandList extends Helper  {
	public ExpandList(AppiumDriver<MobileElement> driver)
	{
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='3. Preference dependencies']")
	public MobileElement dependencies;
	
	
	@AndroidFindBy(className="android.widget.Button")
	public MobileElement buttons;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Expandable Lists']")
	public MobileElement expandList;
	

	public void clickonexpandList() {
		clickOn(expandList);	
	}
	
	
	
	
	
	
	
   
  
    
	
	
	
    
   
	
	
}
