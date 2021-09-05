package com.amazon.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonLoginTest {
	//What is log? : capturing info/activities at the time of program execution. 
	// types of logs:
		//1. info
		//2. warn
		//3. debug
		//4. fatal
		
	//how to generate the logs? : use Apache log4j API (log4j jar)
	//How it works? : it reads log 4j configuration from log4j.properties file
	//where to create: create inside resources folder
	
	WebDriver driver;
	Properties props;
	File file = new File("E:\\Data\\Selenium-workspace\\GenerateLog4jReports\\src\\main\\resources\\log4j.component.properties");
	// this will force a reconfiguration
	
	Logger log = LogManager.getLogger(AmazonLoginTest.class.getName());

	
	@BeforeClass
	public void setlog4j2Configuration() {
		try {
			props = new Properties();
			FileInputStream ip = new FileInputStream(file);
			props.load(ip);
			System.out.println(props.getProperty("log4j.configurationFile"));
		}

		catch (FileNotFoundException ip) {
			ip.printStackTrace();
		}

		catch (IOException io) {
			io.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void setup(){	
		log.info("****************************** Starting test cases execution  *****************************************");
		log.debug("Setting chrome driver property");
		System.setProperty("webdriver.chrome.driver", "C:\\Data\\Selenium\\Softwares\\chromedriver_win32\\chromedriver.exe");	
		driver = new ChromeDriver(); 
		log.info("launching chrome broswer");
		driver.manage().window().maximize();
		log.info("Window Maximized");
		
		driver.manage().deleteAllCookies();
		log.info("Delete all cookies");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        log.info("pageLoadTimeout  applied on the driver for 30 seconds");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        log.info("Implicit wait applied on the driver for 10 seconds");
		log.debug("Now hitting Amazon server");
		driver.get("https://www.amazon.com/");
		log.info("entering application URL");
		log.info("Landed on amazon home page");
		
	}
	
	
	@Test(priority=1)
	public void AmazonTitleTest(){
		
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** AmazonTitleTest *****************************************");
		String title = driver.getTitle();
		System.out.println(title);
		log.info("login page title is--->"+title);
		Assert.assertEquals(title,"Amazon.com. Spend less. Smile more.");
		
		log.info("****************************** ending test case *****************************************");
		log.info("****************************** AmazonTitleTest *****************************************");

	}
		
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
		log.info("****************************** Browser is closed *****************************************");

		
	}

}
