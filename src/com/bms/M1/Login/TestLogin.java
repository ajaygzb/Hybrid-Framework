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

public class TestLogin extends TestSetUp {
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


}
	
	





















}
