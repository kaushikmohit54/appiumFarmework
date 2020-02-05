package com.urp.commonClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {

	public static AppiumDriverLocalService service;
	public static AppiumDriver<MobileElement> driver;
	public static Logger logger ;
	public static Properties prop;
	public AppiumDriverLocalService startServer() {
		//
		loadPropertiesFile();
		boolean flag = Server.checkIfServerIsRunnning(4273);
		if (!flag) {
			logger.info("=========Starting Appium Server========");
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
			logger.info("=========Started Appium Server sucessfully========");
		}
		return service;
	}

	static{
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hhmmss");
      System.setProperty("current.date", dateFormat.format(new Date()));
  }
    
	public static void loadPropertiesFile() {
		try {
			prop = new Properties();
			FileInputStream inputStream = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/Properties/global.properties");
			prop.load(inputStream);

			PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/log4j.properties");
			logger = Logger.getLogger(base.class.getName());

		} catch (FileNotFoundException Ex) {
			logger.info("File not found: " + Ex.getMessage());

		} catch (IOException Ex) {
			logger.info("Exception occurred: " + Ex.getMessage());
		}
		
	}
	
	
	public static String getScreenshot(String s) throws IOException {
        Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\ScreenShots\\" + screenshotFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return screenshotFile;
		
		}

	
		
	
	

}
