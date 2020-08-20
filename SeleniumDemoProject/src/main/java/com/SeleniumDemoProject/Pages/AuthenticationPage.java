package com.SeleniumDemoProject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.SeleniumDemoProject.utility.Constants;
import com.SeleniumDemoProject.utility.Log;

public class AuthenticationPage {

	WebDriver driver;
	WebDriverWait waitApp;
	
	@FindBy(xpath = "//body[@id='authentication']")
	WebElement authenticationPage;
	
	@FindBy(how = How.XPATH,using = "//div[@class='breadcrumb clearfix']//span[@class='navigation_page']")
	WebElement authenticationTab;
	
	@FindBy(linkText = "Authentication")
	WebElement authenticationHeader;
	
	//div[@id='center_column']//h1[@class='page-heading']
	
	@FindBy(how = How.XPATH,using ="//form[@id='create-account_form']")
	WebElement createAnAccount;
			
	@FindBy(xpath = "//form[@id='create-account_form']//input[@id='email_create']")
	WebElement createNewEmailAddress;
	
	@FindBy(how = How.XPATH,using = "//form[@id='create-account_form']//button[@id='SubmitCreate']")
	WebElement createAnAccountSubmitBtn;
	
	@FindBy(xpath = "//form[@id='account-creation_form']")
	WebElement createAnAccountForm;
	
	public AuthenticationPage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitApp = new WebDriverWait(driver, 30);
	}
	
	public void checkLaunchOfAuthenticationPage() 
	{
		waitApp.until(ExpectedConditions.visibilityOf(authenticationPage));
	}
	
	public void checkForAuthenticationTab() 
	{
		waitApp.until(ExpectedConditions.visibilityOf(authenticationTab));
		//waitApp.until(ExpectedConditions.visibilityOf(authenticationHeader));
	}
	
	public boolean authenticationPageLoaded() 
	{
		boolean result = false;
		try 
		{
			checkLaunchOfAuthenticationPage();
			checkForAuthenticationTab();
			result = true;
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public void enterEmailAddressForCreateAnAccountForNewUser() 
	{
		try 
		{
			waitApp.until(ExpectedConditions.visibilityOf(createAnAccount));
			waitApp.until(ExpectedConditions.elementToBeClickable(createNewEmailAddress));
			waitApp.until(ExpectedConditions.visibilityOf(createAnAccountSubmitBtn));
			
			createNewEmailAddress.sendKeys(Constants.EMAIL_ADDRESS_FOR_CREATING_AN_ACCOUNT_FOR_NEW_USER);
			Thread.sleep(2000);
			Reporter.log("Entered email address for creating an account for new user");
			Log.info("Entered email address for creating an account for new user");
			
			if(createAnAccountSubmitBtn.isEnabled()) 
			{
				createAnAccountSubmitBtn.click();
				Reporter.log("Create an Account button is clicked");
				Log.info("Create an Account button is clicked");
			}
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkForCreateAnAccountFormForNewUserIsDisplayed() 
	{
		boolean result = false;
		try 
		{
			checkLaunchOfAuthenticationPage();
			checkForAuthenticationTab();
			waitApp.until(ExpectedConditions.visibilityOf(createAnAccountForm));
			result = true;
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
}
