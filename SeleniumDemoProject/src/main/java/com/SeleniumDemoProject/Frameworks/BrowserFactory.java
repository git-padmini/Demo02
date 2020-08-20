package com.SeleniumDemoProject.Frameworks;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

	static WebDriver driver;
	static String driverPath = "E:\\Selenium_MavenDemo\\SeleniumDemoProject\\src\\main\\resources\\Drivers\\ChromeDriver\\chromedriver.exe";
	public BrowserFactory() 
	{
		
	}
		
	public static WebDriver getDriver()
	{
		if(driver==null)
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--disable-notifications");
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
				
			System.setProperty("webdriver.chrome.driver", driverPath);
				
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		}	
		return driver;
	}
			 
	public static WebDriver getDriver(String browserName)
	{
		if(driver==null)
		{
			if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "E:\\Selenium_MavenDemo\\SeleniumDemoProject\\src\\main\\resources\\Drivers\\ChromeDriver\\chromedriver.exe");
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
			}
//			else if(browserName.equalsIgnoreCase("IE"))
//			{
//				System.setProperty("webdriver.ie.driver", "E:\\Selenium_MavenDemo\\SeleniumMavenPracticeProject\\src\\main\\resources\\resources\\drivers\\ChromeDriver\\chromedriver.exe");
//				driver=new InternetExplorerDriver();
//				driver.manage().window().maximize();
//				driver.manage().deleteAllCookies();
//				driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
//				driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
//			}
		}
		return driver;
	}
			
}
