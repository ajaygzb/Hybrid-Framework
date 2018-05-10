package UIMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;

public class LoginPage {
	private static WebElement webelement = null;

	public static WebElement username(WebDriver driver) {
		webelement = null;
		System.out.println("***********************************************");
		try {
			Commons.waitforElement(driver, By.name("username"), 80);
			webelement = driver.findElement(By.name("username"));
			System.out.println("Username found");
		} catch (NoSuchElementException e) {
			System.out.println("Username not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement password(WebDriver driver) {
		webelement = null;
		try {
			webelement = driver.findElement(By.name("password"));
			System.out.println("Password found");
		} catch (NoSuchElementException e) {
			System.out.println("password not found");
			// Commons.ScreenPrint(driver, "LoginPage", "password");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement signin(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign in')]"), 30);
			webelement = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
			System.out.println("Signin button found");
		} catch (NoSuchElementException e) {
			System.out.println("Signin button not found");
			// Commons.ScreenPrint(driver, "LoginPage", "signin");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement usernameOnHomeScreen(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//span[@class='ng-binding'][2]"), 30);
			webelement = driver.findElement(By.xpath("//span[@class='ng-binding'][2]"));
			System.out.println("username found");
		} catch (NoSuchElementException e) {
			System.out.println("username not found");
			// Commons.ScreenPrint(driver, "LoginPage", "usernameOnHomeScreen");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	// POM 
	WebDriver driver;
    public LoginPage(WebDriver driver){ 
        this.driver=driver; 
}

//Using FindBy for locating elements
@FindBy(how=How.XPATH, using="//a[contains(@href,'http://www.bmspracticesolutions.com/')]") WebElement bmspracticesolutions;
@FindBy(xpath="//a[contains(@href,'#')]") WebElement Help;
@FindBy(xpath="//a[contains(.,'Forgot password ?')]") WebElement Forgotpassword;
@FindBy(xpath="//a[contains(.,'here')]") WebElement here;

// This method is to return current URL
public String getcurrentURL(){
	
	 String currentWindowHandle = driver.getWindowHandle();
	 String URL = null;
ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());

for (String window:windowHandles){

    //if it contains the current window we want to eliminate that from switchTo();
    if (!window.contains(currentWindowHandle)){
        //Now switchTo new Tab.
        driver.switchTo().window(window);
        driver.get(driver.getCurrentUrl());
        System.out.println(driver.getCurrentUrl());
        URL = driver.getCurrentUrl();

        //Close the newly opened tab
        //driver.close();
    }

}
return URL;
}
// This method is to click on Help
public void clickOnHelp(){
	ActionUtils.click(Help);
	Commons.waitforElement(driver, By.xpath("//label[contains(.,'RevFlow Support')]"),10);
	Commons.waitforElement(driver, By.xpath("//label[contains(.,'After-hours Support ')]"),10);
	Commons.waitforElement(driver, By.xpath("//a[contains(.,' RevFlowSupport@bmsemail.com')]"),10);
	Commons.waitforElement(driver, By.xpath("//label[contains(.,' (844) 358 - 5817')]"),10);
	Commons.waitforElement(driver, By.xpath("//label[contains(.,' (626) 201 - 5957')]"),10);
	Commons.waitforElement(driver, By.xpath("//label[contains(.,'Call this number if you are unable to access Revflow outside of business hours.')]"),10);
	Commons.screenshot();
}	

public void clickOnForgotPassword(){
	ActionUtils.click(Forgotpassword);
	Commons.waitforElement(driver, By.xpath("//h2[contains(.,'Forgot Password')]"),10);
	Commons.waitforElement(driver, By.xpath("//lable[contains(.,'Please enter your username to retrieve your password')]"),10);
	Commons.waitforElement(driver, By.xpath("//input[@placeholder='Username']"),10);
	Commons.waitforElement(driver, By.xpath("//button[contains(.,'Submit')]"),10);
	Commons.waitforElement(driver, By.xpath("//div[@class='loginbox'][contains(.,'Click here to go to Login Page.')]"),10);
	Commons.waitforElement(driver, By.xpath("//span[contains(.,'© 2018 BMS Practice Solutions. All rights reserved.')]"),10);
	Commons.screenshot();
	
}	

public void clickOnHere(){
	ActionUtils.click(here);
	
}	
// This method is to click on Login Button
public void clickOnBmspracticesolutions(){
bmspracticesolutions.click();
}	


public void AssertSideMenuItems(WebDriver driver){
	Commons.waitforElement(driver, By.xpath("//*[@id='leftMenuList']/li"),30);
	List<WebElement> Menuitems = driver.findElements(By.xpath("//*[@id='leftMenuList']/li"));
	List<String> MenuitemsActual = new ArrayList<String>();
	for(WebElement w : Menuitems){
		System.out.println(w.getText().trim());
		MenuitemsActual.add(w.getText().trim());
	}
	System.out.println(MenuitemsActual.size());
	List<String> MenuitemsExpected = new ArrayList<String>(Arrays.asList
	("Patient","Schedule","EMR","Transactions","Reports","Practice Management","Communication","Admin","Help"));
	System.out.println(MenuitemsActual.size());
	System.out.println(MenuitemsExpected.size());
	System.out.println(MenuitemsActual);
	System.out.println(MenuitemsExpected);
	
	
	
	boolean b  = MenuitemsActual.equals(MenuitemsExpected);  
    System.out.println(b);
    
    Assert.assertTrue(b);
    Assert.assertTrue(MenuitemsActual.size()==MenuitemsExpected.size());
}
	  
	  
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
