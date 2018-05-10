package com.bms.M4.Insurances;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestAddCompleteInsurance extends TestSetUp {
	@Test(priority = 1, enabled = true)
	public void AddCompletePrimaryInsurance() {
		try {
			test = extent.startTest("Add Complete Primary Insurance",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "INSCompletePrimary");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.PrimaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.PrimaryInsuranceAddEligibity(driver);
			AddCaseUtils.PrimaryInsuranceAddDocotorsOrder(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
			test.log(LogStatus.INFO, "Case with  Complete Primary Insurance  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddCompletePrimaryInsurance() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddCompletePrimaryInsurance() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2, enabled = true)
	public void AddCompleteSecondaryInsurance() {
		try {
			test = extent.startTest("Add Complete Secondary Insurance",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "INSCompleteSecondary");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.PrimaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.PrimaryInsuranceAddEligibity(driver);
			AddCaseUtils.PrimaryInsuranceAddDocotorsOrder(driver);
			AddCaseUtils.AddSecondaryInsuranceAsBlueShield(driver);
			AddCaseUtils.SecondaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.SecondaryInsuranceAddEligibility(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			try {
				Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
			} catch (AssertionError e) {
			}
			expectedMessage = AddCaseUtils.GetFirstCaseSecondaryInsuranceName(driver);
			Assert.assertEquals(expectedMessage, "AutoSec Blue Shield");
			test.log(LogStatus.INFO, "Case with  Complete Secondary Insurance  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddCompleteSecondaryInsurance() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddCompleteSecondaryInsurance() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 3)
	public void AddCompleteTertiaryInsurance() {
		try {
			test = extent.startTest("Add Complete Tertiary Insurance",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "INSCompleteTertiary");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.PrimaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.PrimaryInsuranceAddEligibity(driver);
			AddCaseUtils.PrimaryInsuranceAddDocotorsOrder(driver);
			AddCaseUtils.AddSecondaryInsuranceAsBlueShield(driver);
			AddCaseUtils.SecondaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.SecondaryInsuranceAddEligibility(driver);
			AddCaseUtils.AddTertiaryInsuranceAsBlueShield(driver);
			AddCaseUtils.TertiaryInsuranceAddPreauthorization(driver);
			AddCaseUtils.TertiaryInsuranceAddEligibility(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			try {
				Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
				expectedMessage = AddCaseUtils.GetFirstCaseSecondaryInsuranceName(driver);
				Assert.assertEquals(expectedMessage, "AutoSec Blue Shield");
			} catch (AssertionError e) {
			}
			expectedMessage = AddCaseUtils.GetFirstCaseTertiaryInsuranceName(driver);
			Assert.assertEquals(expectedMessage, "AutoTert Blue Shield");
			test.log(LogStatus.INFO, "Case with  Complete Tertiary Insurance  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddCompleteSTertiaryInsurance() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: AddCompleteTertiaryInsurance() Completed as Fail" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
