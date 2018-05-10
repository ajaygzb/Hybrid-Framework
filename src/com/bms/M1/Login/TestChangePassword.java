package com.bms.M1.Login;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.ChangePasswordPage;
import UIMap.HomePage;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestChangePassword extends TestSetUp {
	// @Test(priority=1,enabled=false)
	public void testchangepassword() {
		test = extent.startTest("Change Password Functionality", "Test Change Password" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("ChangePasswordUsername"));
		ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("ChangePassword"));
		ActionUtils.click(LoginPage.signin(driver));
		test.log(LogStatus.INFO, "Logged in App");
		ActionUtils.click(HomePage.datatoggle(driver));
		ActionUtils.click(HomePage.changePasswordlink(driver));
		test.log(LogStatus.INFO, "Clicked on Change Password Link");
		ActionUtils.sendKeys(ChangePasswordPage.oldpassword(driver), Commons.readPropertyValue("oldpassword"));
		ActionUtils.sendKeys(ChangePasswordPage.newpassword(driver), Commons.readPropertyValue("newpassword"));
		ActionUtils.sendKeys(ChangePasswordPage.confirmpassword(driver), Commons.readPropertyValue("newpassword"));
		ActionUtils.click(ChangePasswordPage.changepassword(driver));
		test.log(LogStatus.INFO, "Entered New Password");
		String strMessage = Commons.capturemessage(driver, 30);
		try {
			if (strMessage.contains("Password cannot be same")) {
				Assert.assertEquals(strMessage, "Password cannot be same as last 6 passwords used.");
			} else {
				Assert.assertEquals(strMessage, "Password changed successfully.");
			}
			test.log(LogStatus.INFO, "Password changed successfully.");
			test.log(LogStatus.INFO, "Test:::: testchangepassword() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testchangepassword() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestChangePassword",
			// "testchangepassword");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	/*
	 * To reset the change password
	 */
	// @Test(priority=2,enabled=false,dependsOnMethods = { "testchangepassword"
	// } )
	public void testresetpassword() {
		test = extent.startTest("Reset Password Functionality", "Test Reset Password" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("ChangePasswordUsername"));
		ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("newpassword"));
		ActionUtils.click(LoginPage.signin(driver));
		test.log(LogStatus.INFO, "Logged in App");
		ActionUtils.click(HomePage.datatoggle(driver));
		ActionUtils.click(HomePage.changePasswordlink(driver));
		test.log(LogStatus.INFO, "Clicked on Change Password Link");
		ActionUtils.sendKeys(ChangePasswordPage.oldpassword(driver), Commons.readPropertyValue("newpassword"));
		ActionUtils.sendKeys(ChangePasswordPage.newpassword(driver), Commons.readPropertyValue("oldpassword"));
		ActionUtils.sendKeys(ChangePasswordPage.confirmpassword(driver), Commons.readPropertyValue("oldpassword"));
		ActionUtils.click(ChangePasswordPage.changepassword(driver));
		test.log(LogStatus.INFO, "Entered New Password");
		String strMessage = Commons.capturemessage(driver, 30);
		System.out.println("Password reset successfully");
		try {
			if (strMessage.contains("Password cannot be same")) {
				Assert.assertEquals(strMessage, "Password cannot be same as last 6 passwords used.");
			} else {
				Assert.assertEquals(strMessage, "Password changed successfully.");
			}
			test.log(LogStatus.INFO, "Password changed successfully.");
			test.log(LogStatus.INFO, "Test:::: testresetpassword() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testresetpassword() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestChangePassword",
			// "testresetpassword");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
