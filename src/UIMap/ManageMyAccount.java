package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.google.common.base.Throwables;
import Utils.Commons;

public class ManageMyAccount {
	static WebElement element;

	public static WebElement ManageMyAccountLink(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Manage My Account')]"), 90);
			element = driver.findElement(By.xpath("//a[contains(text(),'Manage My Account')]"));
			System.out.println("got manage my account link");
		} catch (Exception e) {
			System.out.print("Unable to get Manage my account");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement EditButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmManageMyAccount.isEditMode=true']"), 90);
			element = driver.findElement(By.xpath("//button[@ng-click='vmManageMyAccount.isEditMode=true']"));
			System.out.println("got edit buuton");
		} catch (Exception e) {
			System.out.print("Unable to get edit buuton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement SaveButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmManageMyAccount.Save()']"), 90);
			element = driver.findElement(By.xpath("//button[@ng-click='vmManageMyAccount.Save()']"));
			System.out.println("got save buuton");
		} catch (Exception e) {
			System.out.print("Unable to get save buuton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement CancelButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmManageMyAccount.cancelEditMode()']"), 90);
			element = driver.findElement(By.xpath("//button[@ng-click='vmManageMyAccount.cancelEditMode()']"));
			System.out.println("got cancel buuton");
		} catch (Exception e) {
			System.out.print("Unable to get cancel buuton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement LastNameTextBox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='lastName']"), 90);
			element = driver.findElement(By.xpath("//input[@name='lastName']"));
			System.out.println("got last name text box");
		} catch (Exception e) {
			System.out.print("Unable to get last name text box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement firstNameTextBox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='firstName']"), 90);
			element = driver.findElement(By.xpath("//input[@name='firstName']"));
			System.out.println("got first name text box");
		} catch (Exception e) {
			System.out.print("Unable to get first name text box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement oldPassword(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='oldPassword']"), 90);
			element = driver.findElement(By.xpath("//input[@name='oldPassword']"));
			System.out.println("got old Password text box");
		} catch (Exception e) {
			System.out.print("Unable to get old Password text box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement newPassword(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='newPassword']"), 90);
			element = driver.findElement(By.xpath("//input[@name='newPassword']"));
			System.out.println("got new Password text box");
		} catch (Exception e) {
			System.out.print("Unable to get new Password text box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}

	public static WebElement confirmPassword(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='confirmPassword']"), 90);
			element = driver.findElement(By.xpath("//input[@name='confirmPassword']"));
			System.out.println("got confirm Password text box");
		} catch (Exception e) {
			System.out.print("Unable to get confirm Password text box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return element;
	}
}
