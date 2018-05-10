package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Utils.Commons;
import com.google.common.base.Throwables;

public class HomePage {
	private static WebElement webelement = null;

	/*
	 * public String getURL() { return strURL; }
	 */
	public static WebElement username(WebDriver driver) {
		webelement = null;
		try {
			webelement = driver.findElement(By.xpath("//input[@ng-model='vmPatientSearch.FirstName']"));
		} catch (NoSuchElementException e) {
			System.out.println("First Name in Search not found");
			// Commons.ScreenPrint(driver, "HomePage", "username");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement datatoggle(WebDriver driver) {
		webelement = null;
		try {
			if (!Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='dropDownMenu']"), 40)) {
				Commons.launchRevFlow(driver);
				Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
						Commons.readPropertyValue("password"));
			}
			Commons.waitforElement(driver, By.xpath("//div[@id='dropDownMenu']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='dropDownMenu']"));
		} catch (Exception e) {
			System.out.println("Data toggle dropdown not found");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		return webelement;
	}

	public static WebElement logoutlink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(.,'Log Out')]"), 30);
			webelement = driver.findElement(By.xpath("//a[contains(.,'Log Out')]"));
			System.out.println("Clicked on Logout link");
		} catch (Exception e) {
			System.out.println("Logout link not found" + e);
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		return webelement;
	}

	public static WebElement changePasswordlink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[text()='Change Password']"), 10);
			webelement = driver.findElement(By.xpath("//a[text()='Change Password']"));
			System.out.println("Got change password link in drop down menu");
		} catch (NoSuchElementException e) {
			System.out.println("Change password link in drop down menu not found");
			// Commons.ScreenPrint(driver, "HomePage", "changePasswordlink");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement changecompany(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[text()='Change Company']"), 10);
			webelement = driver.findElement(By.xpath("//a[text()='Change Company']"));
			System.out.println("Got change company link in drop down menu");
		} catch (Exception e) {
			System.out.println("Change company link in drop down menu not found");
			// Commons.ScreenPrint(driver, "HomePage", "changePasswordlink");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
}