package PerformanceTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestLogin extends TestSetUp {
	public static String strActual_text_username = null;
	public static String Text_Expected = null;

	@Test(enabled = true, priority = 1)
	public void testvalidlogin() throws InterruptedException {
		// 1. Valid Login
		test = extent.startTest("Valid Login Functionality with Diffrent Roles/Users/Permissions",
				"Test Valid Login with Diffrent Roles/Users/Permissions" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Login");
		test.log(LogStatus.INFO, "Browser started");
		strActual_text_username = ActionUtils.getText(LoginPage.usernameOnHomeScreen(driver));
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("username");
		System.out.println(strActual_text_username.contains(Text_Expected.trim()));
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected.trim()), "Expected username not matched");
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestValidLogin", "testvalidlogin");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 2. Login customer
		test = extent.startTest("Login as Customer", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Login");
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Customer");
		test.log(LogStatus.INFO, "Login as user Customer");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userCustomer"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userCustomer");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 3. login partner
		test = extent.startTest("Login as Partner", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Login");
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Partner");
		test.log(LogStatus.INFO, "Login as user Partner");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPartner"), Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userPartner");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// . Login provider 100
		test = extent.startTest("Login as Provider100", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Login");
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Provider100");
		test.log(LogStatus.INFO, "Login as user Provider100");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider100"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Assert.assertTrue(driver.getCurrentUrl().toString().contains("provider"), "Not logged in as provider");
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userProvider100");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// Front office staff
		test = extent.startTest("Login as Frontofficestaff User", "Test Login as Frontofficestaff User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Login");
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as Frontofficestaff User");
		test.log(LogStatus.INFO, "Login as Frontofficestaff User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userFrontofficestaff"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userFrontofficestaff");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as Frontofficestaff User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as Frontofficestaff User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
	}
}
