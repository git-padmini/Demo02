package com.SeleniumDemoProject.Frameworks;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class CaptureScreenshot {

	public CaptureScreenshot() 
	{
		
	}
	
	public static void takeScreenShot(WebDriver driver, String filepath) 
	{
		try 
		{
			TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
			File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(filepath));
		} 
		catch (WebDriverException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	public static String getDateTimeStamp()
	{
		Date currentDate;
		String[] datePart;
		String dateStamp;
		currentDate = new Date();
		datePart = currentDate.toString().split(" ");
		dateStamp = datePart[5] + "_" + datePart[1] + "_" + datePart[2] + "_" + datePart[3] ;
		dateStamp = dateStamp.replace(":", "_");
		System.out.println(dateStamp);
		return dateStamp;
	} 
	
	public static void errorScreenShot(WebDriver driver, String filepath) 
	{
		try 
		{
			TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
			File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(filepath));
		} 
		catch (WebDriverException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
