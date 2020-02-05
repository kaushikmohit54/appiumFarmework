package com.urp.commonClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class InvokeApplication extends base {

	public static AppiumDriver<MobileElement> driver;

	
	public static AppiumDriver<MobileElement> startAppWithAPK(String appName) throws IOException, InterruptedException {
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String device=(String) prop.get("device");
		//String device = System.getProperty("deviceName");
		System.out.println(device);
		if (device.contains("emulator")) {
			Server.startEmulator();
		}
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static AppiumDriver<MobileElement> startWithPreInstalledApp() throws IOException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String device=(String) prop.get("device");
		String activityname=(String) prop.get("activity");
		String packagename=(String) prop.get("package");
		//String device = System.getProperty("deviceName");
		System.out.println(device);
//		if (device.contains("emulator")) {
//			startEmulator();
//		}
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		capabilities.setCapability("appPackage",packagename);
		capabilities.setCapability("appActivity",activityname );
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static AppiumDriver<MobileElement> runappiumBrowserStack(String userName,String accessKey) throws MalformedURLException{
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setCapability("device", "Google Pixel");
		
		caps.setCapability("app", "bs://91259592108e0c91bff672a189ec134cd6614be4");

		driver = new AndroidDriver<MobileElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
		return driver;

	}


}
