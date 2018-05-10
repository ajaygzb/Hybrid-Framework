package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Utils.Commons;
import com.google.common.base.Throwables;

public class ChangePasswordPage {
	public static WebElement webelement;

	public static WebElement oldpassword(WebDriver driver) {
		try {
			webelement = driver.findElement(By.name("oldPassword"));
			System.out.println("Got old password webelement");
		} catch (Exception e) {
			System.out.println("Did not get old password webelement");
			// Commons.ScreenPrint(driver, "ChangePasswordPage", "oldpassword");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement newpassword(WebDriver driver) {
		try {
			webelement = driver.findElement(By.name("newPassword"));
			System.out.println("Got new password webelement");
		} catch (Exception e) {
			System.out.println("Did not get new password webelement");
			// Commons.ScreenPrint(driver, "ChangePasswordPage", "newpassword");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement confirmpassword(WebDriver driver) {
		try {
			webelement = driver.findElement(By.name("confirmPassword"));
			System.out.println("Got confirm password webelement");
		} catch (Exception e) {
			System.out.println("Did not get confirm password webelement");
			// Commons.ScreenPrint(driver, "ChangePasswordPage",
			// "confirmpassword");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement changepassword(WebDriver driver) {
		try {
			webelement = driver.findElement(By.xpath("//button[text()='Change Password']"));
			System.out.println("Got change password webelement");
		} catch (Exception e) {
			System.out.println("Did not get change password webelement");
			// Commons.ScreenPrint(driver, "ChangePasswordPage",
			// "changepassword");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement cancel(WebDriver driver) {
		try {
			webelement = driver.findElement(By.xpath("//button[text()='Cancel']"));
			System.out.println("Got cancel webelement");
		} catch (Exception e) {
			// Commons.ScreenPrint(driver, "ChangePasswordPage", "cencel");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			e.printStackTrace();
		}
		return webelement;
	}
}
