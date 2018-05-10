package com.bms.M1.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestValidLogin extends TestSetUp {
	public static String strActual_text_username = null;
	public static String Text_Expected = null;
	LoginPage loginpage = new LoginPage(driver);

	@Test(enabled = true, priority = 1)
	public void testvalidlogin() throws InterruptedException {
		// 1. Valid Login
		test = extent.startTest("Valid Login Functionality with Diffrent Roles/Users/Permissions",
				"Test Valid Login with Diffrent Roles/Users/Permissions" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
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
		// 2. Login BMS
		test = extent.startTest("Login as QA_BMS", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user QA_BMS");
		test.log(LogStatus.INFO, "Login as user QA_BMS");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userBMS"), Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userBMS");
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
		// 3. Login customer
		test = extent.startTest("Login as Customer", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
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
		// 4. login sysadmin
		test = extent.startTest("Login as Sysadmin", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Sysadmin");
		test.log(LogStatus.INFO, "Login as user Sysadmin");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userSysadmin"),
				Commons.readPropertyValue("password"));
		strActual_text_username = null;
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userSysadmin");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO,"Verify Menu items that displays for system admins: Patient, Schedule,  EMR, Transactions, Reports, Practice Management, Admin, Help");
			loginpage.AssertSideMenuItems(driver);
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Pass");
		} catch (AssertionError f) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(f));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 5. login partner
		test = extent.startTest("Login as Partner", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
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
			//test.log(LogStatus.INFO,"Verify Menu items that displays for Partner: Patient, Schedule,  EMR, Transactions, Reports, Practice Management, Admin, Help");
			//loginpage.AssertSideMenuItems(driver);
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testvalidlogin() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 6. Login provider 100
		test = extent.startTest("Login as Provider100", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
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
			loginpage.AssertSideMenuItems(driver);
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
		// 7. provider101
		test = extent.startTest("Login as Provider101", "Test Valid Login" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Provider101");
		test.log(LogStatus.INFO, "Login as user Provider101");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider101"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Assert.assertTrue(driver.getCurrentUrl().toString().contains("non-provider"), "Not logged in as non-provider");
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userProvider101");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			loginpage.AssertSideMenuItems(driver);
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
		// 8. User PT
		test = extent.startTest("Login as PT User", "Test Login as PT User" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as PT User");
		test.log(LogStatus.INFO, "Login as PT User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userPT");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as PT User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as PT User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 9. User OT
		test = extent.startTest("Login as OT User", "Test Login as OT User" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as OT User");
		test.log(LogStatus.INFO, "Login as OT User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userOT"), Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userOT");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as OT User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as OT User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 10. User SLP
		test = extent.startTest("Login as SLP User", "Test Login as SLP User" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as SLP User");
		test.log(LogStatus.INFO, "Login as SLP User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userSLP"), Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userSLP");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as SLP User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as SLP User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 11 User RT
		test = extent.startTest("Login as RT User", "Test Login as RT User" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as RT User");
		test.log(LogStatus.INFO, "Login as RT User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userRT"), Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userRT");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as RT User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as RT User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 12 Front office staff
		test = extent.startTest("Login as Frontofficestaff User", "Test Login as Frontofficestaff User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
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
			test.log(LogStatus.INFO,"Verify Menu items that displays for Frontoffice staff : Patient, Schedule,  EMR, Transactions, Reports, Practice Management, Admin, Help");
		//	loginpage.AssertSideMenuItems(driver);
			test.log(LogStatus.INFO, "Test Login as Frontofficestaff User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as Frontofficestaff User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 13 user Practice administrator
		test = extent.startTest("Login as PracticeAdminstrator User", "Test Login as PracticeAdminstrator User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as PracticeAdminstrator User");
		test.log(LogStatus.INFO, "Login as PracticeAdminstrator User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPracticeAdminstrator"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userPracticeAdminstrator");
		loginpage.AssertSideMenuItems(driver);
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO,"Verify Menu items that displays for Practice Administrator: Patient, Schedule,  EMR, Transactions, Reports, Practice Management, Admin, Help");
			//loginpage.AssertSideMenuItems(driver);
			test.log(LogStatus.INFO, "Test Login as PracticeAdminstrator User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as PracticeAdminstrator User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 14 userstudentprovider
		test = extent.startTest("Login as Studentprovider User", "Test Login as Studentprovider User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as userstudentprovider User");
		test.log(LogStatus.INFO, "Login as userstudentprovider User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userstudentprovider"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("userstudentprovider");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as userstudentprovider User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as userstudentprovider User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 15 usertreatingprovider
		test = extent.startTest("Login as Treating provider User", "Test Login as Treating provider User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as usertreatingprovider User");
		test.log(LogStatus.INFO, "Login as userstudentprovider User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("usertreatingprovider"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("usertreatingprovider");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as usertreatingprovider User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as usertreatingprovider User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
		// 16 usernonclinicalstaff
		test = extent.startTest("Login as Nonclinicalstaff User", "Test Login as Nonclinicalstaff User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as usernonclinicalstaff User");
		test.log(LogStatus.INFO, "Login as usernonclinicalstaff User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff"),
				Commons.readPropertyValue("password"));
		strActual_text_username = LoginPage.usernameOnHomeScreen(driver).getText();
		Commons.fnHighlightMe(driver, LoginPage.usernameOnHomeScreen(driver));
		System.out.println("username text in app" + "  " + strActual_text_username);
		test.log(LogStatus.INFO, "Got username in app" + strActual_text_username);
		Text_Expected = Commons.readPropertyValue("usernonclinicalstaff");
		try {
			Assert.assertTrue(strActual_text_username.contains(Text_Expected), "Expected username not matched");
			test.log(LogStatus.INFO, "Actual username" + "  " + strActual_text_username);
			test.log(LogStatus.INFO, "Expected username" + "  " + Text_Expected);
			test.log(LogStatus.INFO, "Test Login as usernonclinicalstaff User Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test Login as usernonclinicalstaff User Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.flush();
		extent.endTest(test);
	}



@Test(priority = 2)
public void testHyperlinks(){
	
	
	test = extent.startTest("To Verify Corporate Website button and Help button",
			"Test Verify Corporate Website button and Help button" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
	test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
			this.getClass().getPackage().getName().toString());
	test.log(LogStatus.INFO, "Browser started");
	LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
    String defaultwindow = driver.getWindowHandle();
	Commons.logout(driver);
	loginpage.clickOnBmspracticesolutions();
	System.out.println(loginpage.getcurrentURL().contains("bmspracticesolutions"));
	Commons.screenshot();
	driver.navigate().to(Commons.readPropertyValue("URL"));
	loginpage.clickOnHelp();
	//driver.close();
	driver.switchTo().window(defaultwindow);
	loginpage.clickOnForgotPassword();
	Commons.screenshot();
	loginpage.clickOnHere();
	Commons.screenshot();
	Commons.logintoRevflow(driver, Commons.readPropertyValue("username"), Commons.readPropertyValue("password"));
	
	
}

















}
