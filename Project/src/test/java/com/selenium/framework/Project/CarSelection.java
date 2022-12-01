package com.selenium.framework.Project;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class CarSelection {

    public static String title;
    public static WebDriver driver;
	public static Properties prop;
    
    @BeforeSuite
	public static void driverSetup() 
	{
		System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
    
	@Test
	public void carSelection() throws InterruptedException
	{
		driver.get("http://cookbook.seleniumacademy.com/Config.html");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Diesel']")).click();
		driver.findElement(By.name("parksensor")).click();
		Select color = new Select(driver.findElement(By.name("color")));
		color.selectByVisibleText("White");
		System.out.println("The car is configured to White BMW Diesel car with Parking Sensor");
		driver.findElement(By.id("helpbutton")).click();
		driver.findElement(By.id("chatbutton")).click();
	    driver.findElement(By.id("visitbutton")).click();
	    Thread.sleep(1000);
	    ArrayList<String> windowList = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Number of browser windows opened : " + windowList.size());
	    for(int i=0;i<windowList.size();i++)
	    {
	    	driver.switchTo().window(windowList.get(i));
	    	title=driver.getTitle();
	    	if(title.equals("Visit Us"))
	    	{
	    		System.out.println(title + " Is Found");
	    		driver.close();
	    		break;
	    	}
	  
	    }
	    driver.switchTo().window(windowList.get(0));
	    Thread.sleep(1000);
		
	}
	
	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}

}