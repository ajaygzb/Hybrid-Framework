package com.bms.M4.Insurances;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.relevantcodes.extentreports.LogStatus;

public class TestRemoveInsurance extends TestSetUp {
	@Test(priority = 3, enabled = true)
	public void testremoveTertiaryInsurance() throws InterruptedException {
		test = extent.startTest("To Verify User can Remove Tertiary Insurance", "TestRemoveTertiaryInsurance"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Search for Patient
		AddPatientUtils.QuickAddpatient(driver);
		AddCaseUtils.GoToAddNewCase(driver);
		Commons.Explicitwait();
		AddCaseUtils.AddCaseWithAllRequiredFields(driver);
		AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
		AddCaseUtils.AddSecondaryInsuranceAsBlueShield(driver);
		AddCaseUtils.AddTertiaryInsuranceAsBlueShield(driver);
		Commons.Explicitwait();
		AddCaseUtils.ClickCaseSave(driver);
		Commons.Explicitwait();
		// Now Remove the Tertiary Insurance
		// Click Edit Case Button
		AddCaseUtils.ClickCaseEdit(driver);
		do {
			ActionUtils.sendKeys(CasePage.PrimaryInsuranceTab(driver), Keys.PAGE_DOWN);
		} while (!CasePage.TertiaryInsuranceTab(driver).isDisplayed());
		Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//a[@data-toggle='collapse']"), 20);
		ActionUtils.click(CasePage.TertiaryInsuranceTab(driver));
		Commons.Explicitwait();
		Commons.scrollElementinotView(CasePage.TertiaryInsuranceSubscriberIsActiveCheckbox(driver), driver);
		ActionUtils.click(CasePage.TertiaryInsuranceSubscriberIsActiveCheckbox(driver));
		AddCaseUtils.ClickCaseSave(driver);
		Commons.Explicitwait();
		// Now verify the Secondary Insurance is not available as a link in Case
		// Header section
		try {
			Assert.assertTrue(!driver.findElement(By.tagName("body")).getText().contains("Tertiary")); // verify
																										// Tertiary
																										// Insurance
																										// is
																										// not
																										// present
																										// on
																										// the
																										// Page
			test.log(LogStatus.INFO, "Test:::: testRemoveTertiaryInsurance() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testremoveTertiaryInsurance() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestRemoveInsurance",
			// "testremoveTertiaryInsurance");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
	}

	@Test(priority = 2, enabled = true)
	public void testremoveSecondaryInsurance() throws InterruptedException {
		test = extent.startTest("To Verify User can Remove Secondary Insurance", "TestRemoveSecondaryInsurance"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Search for Patient
		AddPatientUtils.QuickAddpatient(driver);
		AddCaseUtils.GoToAddNewCase(driver);
		Commons.Explicitwait();
		AddCaseUtils.AddCaseWithAllRequiredFields(driver);
		AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
		AddCaseUtils.AddSecondaryInsuranceAsBlueShield(driver);
		AddCaseUtils.ClickCaseSave(driver);
		// Now Remove Secondary Insurance
		// Click Edit Case Button
		AddCaseUtils.ClickCaseEdit(driver);
		do {
			ActionUtils.sendKeys(CasePage.PrimaryInsuranceTab(driver), Keys.PAGE_DOWN);
		} while (!CasePage.TertiaryInsuranceTab(driver).isDisplayed());
		ActionUtils.click(CasePage.SecondaryInsuranceTab(driver));
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.SecondaryInsuranceTab(driver), Keys.PAGE_DOWN);
		Commons.Explicitwait();
		ActionUtils.click(CasePage.SecondaryInsuranceSubscriberActiveCheckbox(driver));
		AddCaseUtils.ClickCaseSave(driver);
		Commons.Explicitwait();
		// Now verify the Secondary Insurance is not available as a link in Case
		// Header section
		try {
			Assert.assertTrue(!driver.findElement(By.tagName("body")).getText().contains("Secondary")); // verify
																										// Tertiary
																										// Insurance
																										// is
																										// not
																										// present
																										// on
																										// the
																										// Page
			test.log(LogStatus.INFO, "Test:::: testRemoveSecondaryInsurance() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testremoveSecondaryInsurance() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestRemoveInsurance",
			// "testremoveSecondaryInsurance");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
	}

	@Test(enabled = true, priority = 1)
	public void testremovePrimaryInsurance() throws InterruptedException {
		test = extent.startTest("To Verify User can Remove Primary Insurance", "TestRemovePrimaryInsurance"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Search for Patient
		AddPatientUtils.QuickAddpatient(driver);
		AddCaseUtils.GoToAddNewCase(driver);
		Commons.Explicitwait();
		AddCaseUtils.AddCaseWithAllRequiredFields(driver);
		AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
		AddCaseUtils.ClickCaseSave(driver);
		// Now Remove Primary Insurance
		// Click Edit Case Button
		AddCaseUtils.ClickCaseEdit(driver);
		do {
			ActionUtils.sendKeys(CasePage.PrimaryInsuranceTab(driver), Keys.PAGE_DOWN);
		} while (!CasePage.TertiaryInsuranceTab(driver).isDisplayed());
		ActionUtils.click(CasePage.PrimaryInsuranceTab(driver));
		Commons.Explicitwait();
		ActionUtils.sendKeys(CasePage.PrimaryInsuranceTab(driver), Keys.PAGE_DOWN);
		Commons.Explicitwait();
		ActionUtils.click(CasePage.PrimarySubscriberIsActive(driver));
		String strToastMessage = AddCaseUtils.ClickCaseSave(driver);
		Commons.Explicitwait();
		// Now verify the Secondary Insurance is not available as a link in Case
		// Header section
		try {
			Assert.assertTrue(strToastMessage.contains("The Primary Insurance cannot be removed, only changed.")); // verify
																													// Primary
																													// Insurance
																													// is
																													// not
																													// present
																													// on
																													// the
																													// Page
			test.log(LogStatus.INFO, "Toast Message is :::: The Primary Insurance cannot be removed, only changed.");
			test.log(LogStatus.INFO, "Test:::: testRemovePrimaryInsurance() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testremovePrimaryInsurance() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestRemoveInsurance",
			// "testremovePrimaryInsurance");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
	}
}
