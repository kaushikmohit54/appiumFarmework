package com.urp.TestCases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.urp.commonClasses.InvokeApplication;
import com.urp.commonClasses.Server;
import com.urp.commonClasses.base;
import com.urp.pageObjects.FormPage;

public class Ecomerce extends base {
	@BeforeSuite
	public void setup() throws IOException, InterruptedException {
		   //Server.stopServer();
		  service=startServer();
		  driver=InvokeApplication.startAppWithAPK("GeneralStoreApp");
		  
	}

	@Test
	public void shopping() {
		logger.info("=========started test case execution of genrel store========");
		FormPage formPage=new FormPage(driver);
		formPage.enterNameField();
		formPage.getcountrySelection("India");
		formPage.selectGender();
		formPage.clickOnShopButton();
		logger.info("=========sucessfully fininished execution of test case genrel store========	");
		
	}
	
	@AfterTest
	public void endAndroid() {
		try {
			Server.stopServer();
			Server.closingEmulator();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
