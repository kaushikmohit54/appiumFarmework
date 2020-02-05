package com.urp.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.urp.commonClasses.Helper;
import com.urp.commonClasses.InvokeApplication;
import com.urp.commonClasses.Server;
import com.urp.commonClasses.base;
import com.urp.pageObjects.Api;


public class ApiDemo extends base {
	
	@BeforeSuite
	public void setup() throws IOException, InterruptedException {
		   //Server.stopServer();
		  service=startServer();
		  driver=InvokeApplication.startAppWithAPK("apiDemo");
	}
	
	
	@Test
	public void shopping() throws Exception {
		logger.info("=========started test case execution of api Demo========");
	    Api api=new Api(driver);
		api.clickOnViews();
		
		logger.info("=========sucessfully fininished execution of test case api Demo========	");
		
	}
	
	@AfterTest
	public void endAndroid() {
		try {
			Server.closingEmulator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
