package com.SeleniumDemoProject.Pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.SeleniumDemoProject.Frameworks.CaptureScreenshot;
import com.SeleniumDemoProject.utility.Log;
import com.google.common.base.VerifyException;

public class HomePage {
		
	WebDriver driver;
	WebDriverWait waitApp;
	String filepath = "E:\\Selenium_MavenDemo\\SeleniumDemoProject\\src\\main\\resources\\screenshots\\";
	double loadHomePageTime;
	
	@FindBy(how = How.XPATH,using = "//body[@id='index']//div[@id='page']")
	WebElement homePage;
		
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	WebElement homePageSiteLogo;
	
	@FindBy(xpath = "//form[@id='searchbox'][@action='http://automationpractice.com/index.php?controller=search']//input[@id='search_query_top'][@name='search_query']")
	WebElement searchProductInputBox;
	
	@FindBy(name = "submit_search")
	WebElement searchBtn;
	
	@FindBy(how = How.XPATH,using = "//div[@class='ac_results']")
	WebElement searchProductResultsList;
	
	@FindBy(how = How.XPATH,using = "//div[@id='contact-link']//a[@href='http://automationpractice.com/index.php?controller=contact'][@title='Contact Us']")
	WebElement contactUsLink;
	
	@FindBy(xpath = "//div//a[@title='Log in to your customer account'][@class='login'][@href='http://automationpractice.com/index.php?controller=my-account']")
	WebElement signInLink;
	
	@FindBy(xpath = "//li//a[@title='Women']")
	WebElement womenTabOnHomePage;
	
	@FindBy(how = How.XPATH,using = "//li[@class='sfHover']//ul[@class='submenu-container clearfix first-in-line-xs']")
	WebElement womenTabDropDown;
	
	public HomePage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitApp = new WebDriverWait(driver, 30);
	}
	
	public void checkHomePage() 
	{
		waitApp.until(ExpectedConditions.visibilityOf(homePage));
	}
	
	public void checkForSearchBoxOnHomePage() 
	{
		waitApp.until(ExpectedConditions.visibilityOf(searchProductInputBox));
	}
	
	public void checkForHomePgSiteLogo() 
	{
		waitApp.until(ExpectedConditions.visibilityOf(homePageSiteLogo));
	}
	
	public void checkForContactUsLink() 
	{
		waitApp.until(ExpectedConditions.elementToBeClickable(contactUsLink));
	}
	
	public void checkForSignInLinkOnHomePg() 
	{
		waitApp.until(ExpectedConditions.elementToBeClickable(signInLink));
	}
	
	public void checkForWomenTabOnHomePage() 
	{
		waitApp.until(ExpectedConditions.visibilityOf(womenTabOnHomePage));
	}
	
	public void hoverWomenTabOnHomePg() 
	{
		checkForWomenTabOnHomePage();
		Actions action = new Actions(driver);
		action.moveToElement(womenTabOnHomePage).build().perform();
		waitApp.until(ExpectedConditions.visibilityOf(womenTabDropDown));
	}
	
	public boolean homePageLoaded() 
	{
		boolean result = false;
		try 
		{	
			checkHomePage();
			checkForHomePgSiteLogo();
			checkForContactUsLink();
			checkForSearchBoxOnHomePage();
			hoverWomenTabOnHomePg();
			Thread.sleep(4000);
			result = true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	return result;
	}
	
	public void clickOnSignInLink() 
	{
		try 
		{
			
			homePageLoaded();
			checkForSignInLinkOnHomePg();
			Thread.sleep(2000);
			
			signInLink.click();
			Thread.sleep(2000);
			Reporter.log("Clicked on the Sign In link present on the Home Page");
			Log.info("Clicked on the Sign In link present on the Home Page");
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public double timeTakenToLoadHomepage()
	{
		try 
		{
			waitApp.until(ExpectedConditions.visibilityOf(homePage));
			Log.info("The Home Page gets loaded");
			Reporter.log("The home page is loaded.");
			
			if(homePageSiteLogo.isDisplayed()) 
			{
				// get the  page load time
				Actions hover = new Actions(driver);
				hover.moveToElement(homePageSiteLogo).build().perform();
				loadHomePageTime = ((Double)((JavascriptExecutor)driver).executeScript("return Date.now() - performance.timeOrigin;"))/1000;
				Log.info("Time taken to Load Home Page is: "+ String.valueOf(loadHomePageTime));
				Thread.sleep(5000);
				CaptureScreenshot.takeScreenShot(driver, filepath+"\\LoadHomePage"+CaptureScreenshot.getDateTimeStamp());
			}
				
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	
	return loadHomePageTime;
	}
	
	public void searchAProductOnHomePage() 
	{
		try 
		{
			waitApp.until(ExpectedConditions.visibilityOf(searchProductInputBox));
			
			searchProductInputBox.sendKeys("Dresses");
			Reporter.log("Seacrhed for a product type 'Dresses' and selected 'Printed Chiffon Dress'");
			Log.info("Searched for 'Printed Chiffon Dress'");
			
			waitApp.until(ExpectedConditions.visibilityOf(searchProductResultsList));
			Log.info("Wait for the search results dropdown to appear");
			
			List<WebElement> listOfProductsInProductSearxhResults = new ArrayList<WebElement>();
			listOfProductsInProductSearxhResults.addAll(driver.findElements(By.xpath("//div[@class='ac_results']//ul//li[contains(@class,'ac_')]")));
			
			Reporter.log("The size/number of search results is: "+listOfProductsInProductSearxhResults.size());
			Log.info("Number of search results for 'Printed Chiffon Dress' is: "+listOfProductsInProductSearxhResults.size());
			
			for(int i = 0; i<listOfProductsInProductSearxhResults.size(); i++) 
			{
				if(listOfProductsInProductSearxhResults.get(i).getText().contains("Printed Chiffon Dress")) 
				{
					listOfProductsInProductSearxhResults.get(i).click();
					break;
				}
			}
			Thread.sleep(5000);
			Log.info("Clicked on 'Printed Chiffon Dress' option listed in search results list");
			Thread.sleep(5000);
			
			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			Log.error("Test Case Failed");
		}
		
	}
	
}
