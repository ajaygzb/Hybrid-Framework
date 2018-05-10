package com.bms.M4.Insurances;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseInsurances extends TestSetUp {
	@Test(priority = 1, enabled = true)
	public void AddInsuranceAsBlueShield() {
		try {
			test = extent.startTest("Add Insurance As Blue Shield",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "INSBlueShield");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.AddSecondaryInsuranceAsBlueShield(driver);
			AddCaseUtils.AddTertiaryInsuranceAsBlueShield(driver);
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
			expectedMessage = AddCaseUtils.GetFirstCaseSecondaryInsuranceName(driver);
			Assert.assertEquals(expectedMessage, "AutoSec Blue Shield");
			expectedMessage = AddCaseUtils.GetFirstCaseTertiaryInsuranceName(driver);
			Assert.assertEquals(expectedMessage, "AutoTert Blue Shield");
			test.log(LogStatus.INFO, "Case with Insurance As Blue Shield Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsBlueShield() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsBlueShield() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2, enabled = true)
	public void AddInsuranceAsWorkerCompensation() {
		try {
			test = extent.startTest("Add Insurance As Workers Compensation",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "INSWorkerCompensation");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsWorkerCompensation(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri WorkersCompensation"));
			expectedMessage = AddCaseUtils.GetFirstCaseSecondaryInsuranceName(driver);
			Assert.assertEquals(expectedMessage, "");
			expectedMessage = AddCaseUtils.GetFirstCaseTertiaryInsuranceName(driver);
			Assert.assertEquals(expectedMessage, "");
			test.log(LogStatus.INFO, "Case with Insurance As WorkersCompensation Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsWorkerCompensation() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsWorkerCompensation() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 3, enabled = true)
	public void AddInsuranceAsMedicare() {
		try {
			test = extent.startTest("Add Insurance As Medicare", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "INSMedicare");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.AddSecondaryInsuranceAsMedicare(driver);
			AddCaseUtils.AddTertiaryInsuranceAsMedicare(driver);
			;
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			expectedMessage = AddCaseUtils.GetFirstCaseSecondaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoSec Medicare"));
			expectedMessage = AddCaseUtils.GetFirstCaseTertiaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoTert Medicare"));
			test.log(LogStatus.INFO, "Case with Insurance As Medicare Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Fail");
			Assert.assertFalse(true, "Assertion Error!!!" + e);
		}
	}

	@Test(priority = 4, enabled = true)
	public void AddInsuranceAsMedicareRailRoad() {
		try {
			test = extent.startTest("Add Insurance As Medicare RailRoad",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.addpatient_withallfields(driver, "INSMedicareRailRoad");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsRailRoad(driver);
			AddCaseUtils.AddSecondaryInsuranceAsRailRoad(driver);
			AddCaseUtils.AddTertiaryInsuranceAsRailRoad(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare RailRoad"));
			expectedMessage = AddCaseUtils.GetFirstCaseSecondaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoSec Medicare RailRoad"));
			expectedMessage = AddCaseUtils.GetFirstCaseTertiaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoTert Medicare RailRoad"));
			test.log(LogStatus.INFO, "Case with Insurance As Medicare RailRoad Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicareRailRoad() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicareRailRoad() Completed as Fail");
			Assert.assertFalse(true, "Assertion Error!!!" + e);
		}
	}

	@Test(priority = 5)
	public void AddInsuranceAsTricareChampus() {
		try {
			test = extent.startTest("Add Insurance As TricareChampus",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			// .
			AddPatientUtils.addpatient_withallfields(driver, "INSTricareChampus");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsTriCare(driver);
			AddCaseUtils.AddSecondaryInsuranceAsTricare(driver);
			AddCaseUtils.AddTertiaryInsuranceAsTricare(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoPri Tricare(Champus)"));
			expectedMessage = AddCaseUtils.GetFirstCaseSecondaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoSec Tricare(Champus)"));
			expectedMessage = AddCaseUtils.GetFirstCaseTertiaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoTert Tricare(Champus)"));
			test.log(LogStatus.INFO, "Case with Insurance As TricareChampus Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsTricareChampus() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsTricareChampus() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
