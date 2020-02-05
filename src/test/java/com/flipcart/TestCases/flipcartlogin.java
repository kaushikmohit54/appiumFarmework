package com.flipcart.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.urp.commonClasses.Helper;
import com.urp.commonClasses.InvokeApplication;
import com.urp.commonClasses.base;

public class flipcartlogin extends base {
	
	
	@Test
	public void test1() throws IOException, InterruptedException {
		service=startServer();
		driver=InvokeApplication.startWithPreInstalledApp();
		driver.findElement(By.id("com.google.android.gms:id/cancel")).click();
		driver.findElement(By.id("com.flipkart.android:id/mobileNo")).sendKeys("7204395877");
		driver.findElement(By.id("com.flipkart.android:id/btn_mlogin")).click();
		
		driver.findElement(By.id("com.flipkart.android:id/et_password")).sendKeys("1208");
		driver.findElement(By.id("com.flipkart.android:id/btn_msignup")).click();
		
		
		
		driver.findElement(By.id("com.flipkart.android:id/search_widget_textbox")).click();
		driver.findElement(By.id("com.flipkart.android:id/search_autoCompleteTextView")).sendKeys("Realme C2");
		driver.findElement(By.id("com.flipkart.android:id/txt_subtitle")).click();
		driver.findElement(By.id("com.flipkart.android:id/allow_button")).click();
		driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
		//Thread.sleep(10000);
		boolean val=driver.findElement(By.xpath("Realme C2 (Diamond Black, 16 GB)']")).isDisplayed();
		Assert.assertTrue(val);
		System.out.println(val);
		Helper.scrollToElemntBytext(driver, "Realme C2 (Diamond Black, 16 GB)']");
		driver.findElementByXPath("//android.widget.TextView[@text()='Realme C2 (Diamond Black, 16 GB)']").click();
		//driver.findElementByXPath("//android.widget.TextView[@text()='BUY NOW").click();
		driver.findElementByXPath("//android.widget.TextView[@text()='SKIP & CONTINUE").click();
		
		
		service.stop();
	}
	
	

}
