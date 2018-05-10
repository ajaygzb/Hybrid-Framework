package com.bms.M1.Login;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.Commons;

public class TestLoginUnsuccessfull extends TestSetUp {
	// @Test( priority = 4 )
	public void testbadusername() {
		test = extent.startTest("Login With Invalid Username", "Test Bad Username" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Commons.logout(driver);
		ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("invalidusername"));
		test.log(LogStatus.INFO, "Entered Invalid Username");
		ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Entered  Password");
		ActionUtils.click(LoginPage.signin(driver));
		test.log(LogStatus.INFO, "Clicked on Sign in Button");
		String strMessage = Commons.capturemessage(driver, 30);
		try {
			Assert.assertEquals(strMessage, "Either user name or password is incorrect");
			test.log(LogStatus.PASS, "Test:::: testbadusername() Completed as Pass");
			driver.navigate().refresh();
			Commons.Explicitwait();
			// Commons.logintoRevflow(driver,
			// Commons.readPropertyValue("username"),
			// Commons.readPropertyValue("password"));
			Commons.Explicitwait();
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			// //Commons.ScreenPrint(driver, "TestLoginUnsuccessfull",
			// "testbadusername");
			test.log(LogStatus.INFO, "Test:::: testbadusername() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	// @Test( priority = 2 )
	public void testbadpassword() {
		test = extent.startTest("Login With Invalid Password", "Test Bad Password" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Commons.logout(driver);
		ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("username"));
		test.log(LogStatus.INFO, "Entered Valid Username");
		ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("invalidpassword"));
		test.log(LogStatus.INFO, "Entered InValid Password");
		ActionUtils.click(LoginPage.signin(driver));
		test.log(LogStatus.INFO, "Clicked on Sign in Button");
		String strMessage = Commons.capturemessage(driver, 30);
		try {
			Assert.assertEquals(strMessage, "Either user name or password is incorrect");
			test.log(LogStatus.INFO,
					"!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!===============================================================================");
			// driver.navigate().refresh();
			// Commons.logintoRevflow(driver);
		} catch (AssertionError e) {
			test.log(LogStatus.INFO,
					"=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=============================================================================== ");
			// //Commons.ScreenPrint(driver, "TestLoginUnsuccessfull",
			// "testbadpassword");
			test.log(LogStatus.INFO, "Test:::: testbadpassword() Failed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		/*
		 * driver.navigate().refresh(); Commons.logintoRevflow(driver);
		 */ // ???????? why is this needed
	}

	// @Test( priority = 3 )
	public void testoutsideaccessnotallowed() {
		test = extent.startTest("To Verify Outside Access Not Allowed Functionality", "Test Outside Access not allowed"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Commons.logout(driver);
		ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("usernameNoOutSideAccess"));
		ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
		ActionUtils.click(LoginPage.signin(driver));
		test.log(LogStatus.INFO, "Trying to Login to App from Outside IP");
		String strMessage = Commons.capturemessage(driver, 30);
		try {
			Assert.assertEquals(strMessage,
					"You are not able to access RevFlow at this IP address. Contact your admin for assistance.");
			test.log(LogStatus.INFO, "!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!");
			// driver.navigate().refresh();
			// Commons.logintoRevflow(driver);
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!! ");
			// //Commons.ScreenPrint(driver, "TestLoginUnsuccessfull",
			// "testoutsideaccessnotallowed");
			test.log(LogStatus.INFO, "Test:::: testoutsideaccessnotallowed() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 1, enabled = true)
	public void TestInvalidLogin() {
		test = extent.startTest("To Verify ip not active Functionality", "Test Outside Access not allowed"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		LoginPage.username(driver).clear();
		LoginPage.password(driver).clear();
		ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("usernameNoIpActive"));
		ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
		ActionUtils.click(LoginPage.signin(driver));
		test.log(LogStatus.INFO, "Logged in to app With Valid credentials");
		String strMessage = Commons.capturemessage(driver, 30);
		try {
			if (strMessage
					.contains("An email has been sent to the email on file to grant access to your IP address.")) {
				Assert.assertEquals(strMessage,
						"An email has been sent to the email on file to grant access to your IP address.");
				test.log(LogStatus.INFO, strMessage);
			} else {
				Assert.assertEquals(strMessage,
						"You are not able to access RevFlow at this IP address, as it is disabled. Please enable from the link sent to you over the e-mail. Click on 'Resend' link, in case you didn't receive the e-mail.");
				test.log(LogStatus.INFO, strMessage);
			}
			System.out.println("!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!");
			test.log(LogStatus.INFO, "!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!");
			// driver.navigate().refresh();
			extent.flush();
			extent.endTest(test);
			LoginPage.username(driver).clear();
			LoginPage.password(driver).clear();
			testoutsideaccessnotallowed();
			extent.flush();
			extent.endTest(test);
			LoginPage.username(driver).clear();
			LoginPage.password(driver).clear();
			testbadpassword();
			extent.flush();
			extent.endTest(test);
			LoginPage.username(driver).clear();
			LoginPage.password(driver).clear();
			testbadusername();
			LoginPage.username(driver).clear();
			LoginPage.password(driver).clear();
			// Commons.logintoRevflow(driver);
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!");
			// //Commons.ScreenPrint(driver, "TestLoginUnsuccessfull",
			// "testipnotactive");
			test.log(LogStatus.INFO, "Test:::: TestLoginUnsuccessfull() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.logintoRevflow(driver, Commons.readPropertyValue("username"), Commons.readPropertyValue("password"));
	}
}
