package com.SeleniumDemoProject.Test;

import org.testng.annotations.Test;
import com.SeleniumDemoProject.Frameworks.BrowserFactory;
import com.SeleniumDemoProject.Frameworks.CaptureScreenshot;
import com.SeleniumDemoProject.Pages.AuthenticationPage;
import com.SeleniumDemoProject.Pages.HomePage;
import com.SeleniumDemoProject.utility.Constants;
import com.SeleniumDemoProject.utility.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestClass {
	
	public static WebDriver driver;
	double loadTime;
	HomePage homepg;
	AuthenticationPage authenticationPg;
	static ExtentTest extentTest;
	static ExtentReports extentReport;
	boolean result;
	
	public TestClass() 
	{
		// TODO Auto-generated constructor stub
		DOMConfigurator.configure("log4j.xml");
	}
	
	@Test
	public void launchApp() 
	{
		homepg = new HomePage(driver);
		
		Log.startTestCase("Launch application or web site");
		
		
		result = homepg.homePageLoaded();
		if(result) 
		{
			CaptureScreenshot.takeScreenShot(driver, Constants.SCREENSHOTS_PATH_PASSED + "LaunchHomePage" + Constants.SCREENSHOTS_FORMAT);
			extentTest.log(LogStatus.PASS, "LaunchApp(): The e-commerce application 'My Store' website should be launched successfully", "The e-commerce application "
					+ "'My Store' website is launched successfully");
			Reporter.log("'My Store' e-commerce site application URL is opened/launched");
			Log.info("'My Store' e-commerce site application URL is opened/launched");
		}
		else 
		{
			CaptureScreenshot.errorScreenShot(driver, Constants.SCREENSHOTS_PATH_FAILED + "LaunchHomePage" + Constants.SCREENSHOTS_FORMAT);
			extentTest.log(LogStatus.FAIL, "LaunchApp(): The e-commerce application 'My Store' website should be launched successfully", "Test Failed: The e-commerce application "
					+ "'My Store' website is not launched successfully" + extentTest.addScreenCapture(Constants.SCREENSHOTS_PATH_FAILED + "FailedLaunchHomePage" 
					+ Constants.SCREENSHOTS_FORMAT));
			Reporter.log("'My Store' e-commerce site application URL is not opened/launched");
			Log.info("'My Store' e-commerce site application URL is not opened/launched");
		}
	}
	
	@Test(dependsOnMethods = {"launchApp"})
	public void newUserRegistrationInApp() throws InterruptedException 
	{
		homepg = new HomePage(driver);
		authenticationPg = new AuthenticationPage(driver);
		
		Log.startTestCase("New User Registration Process");
		
		Reporter.log("New user needs to be registered on the site - Click on the Sign in link");
		Log.info("New user needs to be registered on the site - Click on the Sign in link");
		
		homepg.clickOnSignInLink();
		Thread.sleep(2000);
		
		result = authenticationPg.authenticationPageLoaded();
		if(result) 
		{
			CaptureScreenshot.takeScreenShot(driver, Constants.SCREENSHOTS_PATH_PASSED + "LauchAuthenticationPage" + Constants.SCREENSHOTS_FORMAT);
			extentTest.log(LogStatus.PASS, "newUserRegistrationInApp(): The authentication page should appear after clicking on Sign In link",
					"The authentication page appears after clicking on Sign In link");
			Reporter.log("The authentication page appears after clicking on Sign In link");
			Log.info("The authentication page appears after clicking on Sign In link");
		}
		else 
		{
			CaptureScreenshot.errorScreenShot(driver, Constants.SCREENSHOTS_PATH_FAILED + "LauchAuthenticationPage" + Constants.SCREENSHOTS_FORMAT);
			extentTest.log(LogStatus.FAIL, "newUserRegistrationInApp(): The authentication page should appear after clicking on Sign In link", "Test Failed: The "
					+ "authentication page does not appear after clicking on Sign In link" + extentTest.addScreenCapture(Constants.SCREENSHOTS_PATH_FAILED + 
							"LauchAuthenticationPage" + Constants.SCREENSHOTS_FORMAT));
			Reporter.log("The authentication page does not appear after clicking on Sign In link");
			Log.info("The authentication page does not appear after clicking on Sign In link");
		}
		
		authenticationPg.enterEmailAddressForCreateAnAccountForNewUser();
		Thread.sleep(5000);
		
		
		result = authenticationPg.checkForCreateAnAccountFormForNewUserIsDisplayed();
		if(result) 
		{
			CaptureScreenshot.takeScreenShot(driver, Constants.SCREENSHOTS_PATH_PASSED + "LauchAuthenticationCreateAnAccountPage" + Constants.SCREENSHOTS_FORMAT);
			extentTest.log(LogStatus.PASS, "newUserRegistrationInApp(): The authentication create an account page for new user should appear after clicking on 'Create "
					+ "An Account' button",  "The authentication create an account page for new user appears after clicking on 'Create An Account' button");
			Reporter.log("The authentication create an account page for new user appears after clicking on 'Create An Account' button");
			Log.info("The authentication create an account page for new user appears after clicking on 'Create An Account' button");
		}
		else 
		{
			CaptureScreenshot.errorScreenShot(driver, Constants.SCREENSHOTS_PATH_FAILED + "LauchAuthenticationCreateAnAccountPage" + Constants.SCREENSHOTS_FORMAT);
			extentTest.log(LogStatus.FAIL, "newUserRegistrationInApp(): The authentication create an account page for new user should appear after "
					+ "clicking on 'Create An Account' button", "Test Failed: The authentication create an account page for new user does not appear "
							+ "after clicking on 'Create An Account' button" + extentTest.addScreenCapture(Constants.SCREENSHOTS_PATH_FAILED + "LauchAuthenticationPage"
									+ Constants.SCREENSHOTS_FORMAT));
			Reporter.log("The authentication create an account page for new user does not appear after clicking on 'Create An Account' button");
			Log.info("The authentication create an account page for new user does not appear after clicking on 'Create An Account' button");
		}
	}
	
	
	@Test(enabled = false)
	public void timeToLoadHomePage() throws InterruptedException 
	{
	  Log.startTestCase("Time Taken To Load Home Page Test Case");
	  Reporter.log("The site gets opened");
	  
	  homepg = new HomePage(driver);
	  loadTime = homepg.timeTakenToLoadHomepage();
	  Reporter.log("The home page load time is: " + String.valueOf(loadTime));
	  
	  Log.endTestCase("Time Taken To Load Home Page Test Case");
	  
	  
	}
  
	@Test(enabled = false)
	public void searchAProduct() 
	{
		Log.startTestCase("Search a Product Test Case");
		Reporter.log("A product is searched using Search box present in the Home page");
		Log.info("Searching for a Product - Printed Chiffon Dress - through Search input box on Home Page");
		homepg.searchAProductOnHomePage();
		Log.endTestCase("Search a Product Test Case");
	}
	
	
	@BeforeMethod
	public void beforeMethod() 
	{
	  
	}
  
	@AfterMethod
	public void afterMethod() 
	{
		
	}

	@BeforeClass
	public void beforeClass()
	{
		Log.info("Execution started........");
		
		driver = BrowserFactory.getDriver();
		Log.info("New ChromeDriver is instantiated");
		
		driver.get(Constants.APPLICATION_URL);
	}

	@AfterClass
	public void afterClass() 
	{
		driver.close();
		Log.info("Browser is the closed");
		
		driver.quit();
		Log.info("Quiting the driver instance");
	}

	@BeforeTest
	public void beforeTest() 
	{
		extentReport = new ExtentReports(Constants.EXTENTREPORT_PATH + "\\ExtentReportResults.html");
		extentTest = extentReport.startTest("TestClass");
	}

	@AfterTest
	public void afterTest() 
	{
		extentReport.endTest(extentTest);
		extentReport.flush();
	}

}
