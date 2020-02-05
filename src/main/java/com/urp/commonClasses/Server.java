package com.urp.commonClasses;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Server extends base {
	
	
	
	/*
	 * this function is used to run 
	 * the appium server 1.7.0/1.6.5 and also for all the version
	 * only thing u have to give the proper path of the node.exe and main.js
	 * String nodepath="C:/Program Files/nodejs/node.exe";
	   String apppath="C:/Users/User/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
	 *  */
	
	 public static void startServeronConsol(String nodePath,String appPath){
		 CommandLine cmd = new CommandLine(nodePath);
			cmd.addArgument(appPath);
//			cmd.addArgument("--address");
//			cmd.addArgument("127.0.0.1");
//			cmd.addArgument("--port");
//			cmd.addArgument("4723");
			
			DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			try {
				executor.execute(cmd, handler);
				Thread.sleep(5000);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
		 	}
	  }
	 /*************************************************************/
	 /*
	  * here how to run server from command prompt
	  * here you have to give the only your .bat file name
	  * */
	 public static void startServerOnCommandPrompt(String urBatFileNAme) throws IOException{
		 		 System.out.println(System.getProperty("user.dir") 
		 		 + "\\src\\test\\resources\\BatFolder\\"+urBatFileNAme+".bat");
		 Runtime.getRuntime().exec(System.getProperty("user.dir") 
				 + "\\src\\test\\resources\\BatFolder\\"+urBatFileNAme+".bat");
		 
	 }
	 
	
	 /***********************************************************/
	 
	 /*
	  *This is the function how to stop the server
	  *it will help to stop the server where ever u started
	  *like command prompt or console 
	  * 
	  * */
	 public static boolean StartEmulator1(){
		 Runtime runtime = Runtime.getRuntime();
			try {
				System.out.println("Starting Emulator Device.....");
				runtime.exec("emulator -avd emulator-5554");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			
		}
	 
	 public static boolean stopServer(){
		 Runtime runtime = Runtime.getRuntime();
			try {
				System.out.println("killing the server.....");
				runtime.exec("taskkill /F /IM node.exe");
				runtime.exec("taskkill /F /IM cmd.exe");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			
		}
	 /****************************************************/
	 
	 /*
	  * this function is to check whether the server is running or not
	  * 
	  * */
	 public static boolean checkIfServerIsRunnning(int port) {
			
			boolean isServerRunning = false;
			ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(port);
				serverSocket.close();
			} catch (IOException e) {
				//If control comes here, then it means that the port is in use
				isServerRunning = true;
			} finally {
				serverSocket = null;
			}
			return isServerRunning;
		}
	 
	 
		//for starting android device
		public static void startEmulator() throws IOException, InterruptedException {
			logger.info("=========Starting Android Device========");
			//startServerOnCommandPrompt("startEmulator");
			StartEmulator1();
			Thread.sleep(9000);
			logger.info("=========Started sucessfully Android Device========");
		}
		
		
		public static void closingEmulator() throws IOException, InterruptedException
		{
		    //adb -s emulator-5554 emu kill
			logger.info("=========Closing Emulator Android device========");
			Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
			Thread.sleep(3000);
		}

}
