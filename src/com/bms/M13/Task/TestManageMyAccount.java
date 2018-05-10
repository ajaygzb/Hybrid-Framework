package com.bms.M13.Task;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.ManageMyAccount;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.ManageMyAccountUtils;

public class TestManageMyAccount extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void TestMandatoryFields() {
		test = extent.startTest("To Verify mandatory fields message ", "Test mandatory fields"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		ManageMyAccountUtils.NavigateToManageMyAccount(driver);
		test.log(LogStatus.INFO, "navigated to manage my account page");
		System.out.println("clicking on edit button");
		test.log(LogStatus.INFO, "clicking on edit button");
		ManageMyAccount.EditButton(driver).click();
		Commons.waitForLoad(driver);
		ManageMyAccount.firstNameTextBox(driver).clear();
		ManageMyAccount.SaveButton(driver).click();
		try {
			String toastermessage = Commons.capturemessage(driver, 100);
			Assert.assertEquals(toastermessage,
					"Please fill all the mandatory fields or check highlighted field values.");
			// System.out.println("toaster message is : " + toastermessage);
			test.log(LogStatus.INFO, "got toster message " + toastermessage + "hence test case is pass");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, "did not get toster hence test case fails");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, "did not get correct toster message hence test case fails ");
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestIvalidOldPassword() {
		test = extent.startTest("To Verify invalid old password", "Test invalid old password"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		ManageMyAccountUtils.NavigateToManageMyAccount(driver);
		test.log(LogStatus.INFO, "navigated to manage my account page");
		System.out.println("clicking on edit button");
		test.log(LogStatus.INFO, "clicking on edit button");
		ManageMyAccount.EditButton(driver).click();
		Commons.waitForLoad(driver);
		ManageMyAccount.oldPassword(driver).sendKeys("testpassword");
		ManageMyAccount.newPassword(driver).sendKeys("Bmspass1234");
		ManageMyAccount.confirmPassword(driver).sendKeys("Bmspass1234");
		ManageMyAccount.SaveButton(driver).click();
		try {
			String toastermessage = Commons.capturemessage(driver, 100);
			Assert.assertEquals(toastermessage, "Old password does not match.");
			System.out.println("toaster message is : " + toastermessage);
			test.log(LogStatus.INFO, "got toster message " + toastermessage + "hence test case is pass");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, "did not get toster hence test case fails");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, "did not get correct toster message hence test case fails ");
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestIvalidnewAndConfirmPasswordIssame() {
		test = extent.startTest("To Verify confirm and new password are same", "Test confirm and new password are same"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		ManageMyAccountUtils.NavigateToManageMyAccount(driver);
		test.log(LogStatus.INFO, "navigated to manage my account page");
		System.out.println("clicking on edit button");
		test.log(LogStatus.INFO, "clicking on edit button");
		ManageMyAccount.EditButton(driver).click();
		Commons.waitForLoad(driver);
		ManageMyAccount.oldPassword(driver).sendKeys(Commons.readPropertyValue("password"));
		ManageMyAccount.newPassword(driver).sendKeys("Bmspass1234");
		ManageMyAccount.confirmPassword(driver).sendKeys("Bmspass12345");
		ManageMyAccount.SaveButton(driver).click();
		try {
			String toastermessage = Commons.capturemessage(driver, 100);
			Assert.assertEquals(toastermessage, "New password and confirm password does not match.");
			System.out.println("toaster message is : " + toastermessage);
			test.log(LogStatus.INFO, "got toster message " + toastermessage + "hence test case is pass");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, "did not get toster hence test case fails");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, "did not get correct toster message hence test case fails ");
		}
	}

	@Test(enabled = true, priority = 4)
	public void TestDefaultView() {
		test = extent.startTest("To Verify default scheduler view ", "Test default view" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "selecting default view to provider's view :  " + Commons.readPropertyValue("userPT"));
		try {
			ManageMyAccountUtils.changedefaultViewProvider(driver, Commons.readPropertyValue("userPT"));
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode-value']//option[@selected]"),
					200);
			Select dropdown = new Select(driver.findElement(By.id("dropdown-view-mode-value")));
			String selectedprovider = dropdown.getFirstSelectedOption().getText();
			System.out.println("selected provider is :" + selectedprovider);
			Assert.assertEquals(selectedprovider.toLowerCase(), Commons.readPropertyValue("userPT").toLowerCase());
			System.out.println("change default view is successfull ");
			test.log(LogStatus.INFO, "change default view is successfull ");
			System.out.println("changing to default view ");
			ManageMyAccountUtils.NavigateToManageMyAccount(driver);
			System.out.println("clicking on edit button");
			ManageMyAccount.EditButton(driver).click();
			Commons.waitForLoad(driver);
			dropdown = new Select(
					driver.findElement(By.xpath("//select[@ng-change='vmManageMyAccount.defaultViewchange()']")));
			dropdown.selectByVisibleText("Select");
			ManageMyAccount.SaveButton(driver).click();
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode-value']//option[@selected]"),
					200);
			dropdown = new Select(driver.findElement(By.id("dropdown-view-mode-value")));
			selectedprovider = ActionUtils.getText(dropdown.getFirstSelectedOption());
			System.out.println("selected provider is :" + selectedprovider);
			Assert.assertEquals(selectedprovider.toLowerCase().trim(),
					Commons.readPropertyValue("username").toLowerCase().trim());
			System.out.println("changing back to default view is successfull hence test case pass");
			test.log(LogStatus.INFO, "changing back to default view is successfull hence test case pass");
		} catch (AssertionError e) {
			System.out.println("unable to change default view hence test case fail");
			test.log(LogStatus.INFO, "unable to change default view hence test case fail");
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			System.out.println("unable to change default view hence test case fail");
			test.log(LogStatus.INFO, "unable to change default view hence test case fail");
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
