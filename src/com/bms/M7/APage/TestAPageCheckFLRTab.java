package com.bms.M7.APage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.relevantcodes.extentreports.LogStatus;

public class TestAPageCheckFLRTab extends TestSetUp {
	public String strText_Actual;

	@Test(enabled = true, priority = 1)
	public void FLRTabDisplay() throws InterruptedException {
		try {
			test = extent.startTest("FLR tab available based on insurance flags - presence of FLR tab",
					"Test FLR Tab Display" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			test.log(LogStatus.INFO, "Case with Insurance As Medicare Saves  successfully.");
			test.log(LogStatus.INFO, "Insurance Name is ==>AutoPri Medicare");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Fail");
			Assert.assertFalse(true, "Assertion Error!!!" + e);
		}
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		test.log(LogStatus.INFO, "Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 50);
		System.out.println("Successfully redirected to APage");
		test.log(LogStatus.INFO, "Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		test.log(LogStatus.INFO, "Validating that FLR Link Appear on A Page..");
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']"))
					.isDisplayed(), "FLR TAB Not Appeared");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Fail");
			Assert.assertTrue(false, "FLR TAB Not Appeared");
		}
		APageUtils.click(driver, "ClickMedicareFLRLink");
		Commons.waitForLoad(driver);
		// a[@href='#Medicare-Functional-Limitation-Reporting']
	}

	@Test(enabled = true, priority = 2)
	public void FLRTabNotDisplay() throws InterruptedException {
		try {
			test = extent.startTest("FLR tab Not available based on insurance flags - absence of FLR tab",
					"Test FLR Tab Not Display" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			try {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
			}
			AddCaseUtils.GoToCaseList(driver);
			expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
			test.log(LogStatus.INFO, "Case with Insurance As AutoPri Blue Shield Saves  successfully.");
			test.log(LogStatus.INFO, "Insurance Name is ==>AutoPri Blue Shield");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsBlueShield Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsBlueShield() Completed as Fail");
			Assert.assertFalse(true, "Assertion Error!!!" + e);
		}
		// Create Notes and Navigating to A page
		Commons.waitForLoad(driver);
		APageUtils.createNewNote(driver);
		test.log(LogStatus.INFO, "Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		test.log(LogStatus.INFO, "Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		test.log(LogStatus.INFO, "Validating that FLR Link Appear on A Page..");
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']"))
					.isDisplayed(), "FLR TAB  Appeared");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Fail" + e);
			Assert.assertTrue(false, "FLR TAB  Appeared");
		}
		Commons.waitForLoad(driver);
		// a[@href='#Medicare-Functional-Limitation-Reporting']
	}
}
