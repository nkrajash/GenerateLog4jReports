package com.freecrm.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
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
	Logger log = LogManager.getLogger(LoginTest.class.getName());

	File file = new File("E:\\Data\\Selenium-workspace\\GenerateLog4jReports\\src\\main\\resources\\log4j.component.properties");
	// this will force a reconfiguration

	@BeforeClass
	public void setlog4j2Configuration() {
		System.setProperty("log4j.configurationFile", "E:\\Data\\Selenium-workspace\\GenerateLog4jReports\\src\\main\\resources\\log4j2.xml");
	}
	
	@BeforeMethod
	public void setup(){	
		log.info("****************************** Starting test cases execution  *****************************************");
		System.setProperty("webdriver.chrome.driver", "C:\\Data\\Selenium\\Softwares\\chromedriver_win32\\chromedriver.exe");	
		driver = new ChromeDriver(); 
		log.info("launching chrome browser");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://classic.crmpro.com");
		log.info("entering application URL");
		log.warn("Hey this just a warning message");
		log.fatal("hey this is just fatal error message");
		log.debug("this is debug message");
		
	}
	
	
	@Test(priority=1)
	public void CRMPROTitleTest(){
		
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** CRMPROTitleTest *****************************************");
		String title = driver.getTitle();
		System.out.println(title);
		log.info("login page title is--->"+title);
		Assert.assertEquals(title,"CRMPRO - CRM software for customer relationship management, sales, and support.");
		
		log.info("****************************** ending test case *****************************************");
		log.info("****************************** CRMPROTitleTest *****************************************");

	}
	
	@Test(priority=2)
	public void CRMPROLogoTest(){
		
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** CRMPROLogoTest *****************************************");

		boolean b = driver.findElement(By.xpath("//img[@class='img-responsive']")).isDisplayed();
		Assert.assertTrue(b);
		
		log.info("****************************** ending test case *****************************************");
		log.info("****************************** CRMPROLogoTest *****************************************");

	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
		log.info("****************************** Browser is closed *****************************************");

		
	}

}
