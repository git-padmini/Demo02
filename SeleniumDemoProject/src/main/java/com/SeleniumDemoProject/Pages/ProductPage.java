package com.SeleniumDemoProject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage 
{

	WebDriver driver;
	WebDriverWait waitApp;
	
	public ProductPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitApp = new WebDriverWait(driver, 30);
	}
	
	
	
	
}
