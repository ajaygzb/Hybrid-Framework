package com.bms.M8.FLR;

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
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestFLRTabNotDisplayRTProvider extends TestSetUp {
	public String strText_Actual;

	@Test(enabled = true, priority = 1)
	public void FLR_ProviderRT() throws InterruptedException {
		try {
			test = extent.startTest("FLR tab Not available based on Providers flags - presence of FLR tab",
					"Test FLR Tab Not Display for RT provider" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Login With RT Provider ");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userRT"), Commons.readPropertyValue("password"));
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
		test.log(LogStatus.INFO, "Validating that FLR Link does not Appear on A Page..");
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']"))
					.isDisplayed(), "FLR TAB Appeared for RT Provider");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Fail");
			Assert.assertTrue(false, "FLR TAB Appeared for RT Provider");
		}
		Commons.waitForLoad(driver);
		APageUtils.FinalizeNote(driver);
		test.log(LogStatus.INFO, "Finalized Note for Provider RT");
		APageUtils.GotoNoteList(driver, 1);
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']"))
					.isDisplayed(), "FLR TAB Appeared for RT Provider");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Fail");
			Assert.assertTrue(false, "FLR TAB Not Appeared");
		}
		test.log(LogStatus.INFO, "Adding Addendum to Finalized Note");
		APageUtils.AddAddendum(driver);
		APageUtils.FinalizeNote(driver);
		test.log(LogStatus.INFO, "Finalized Addendum Note");
		// a[@href='#Medicare-Functional-Limitation-Reporting']
		// 2108922
	}

	@Test(enabled = true, priority = 2)
	public void FLR_ProviderPT() throws InterruptedException {
		try {
			test = extent.startTest("FLR tab Not available based on Insurance flags - presence of FLR tab",
					"Test FLR Tab Not Display for non-FLR insurance" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Login With PT Provider ");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
			AddPatientUtils.addpatient_withallfields(driver, "EMRMedicare");// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsWorkerCompensation(driver);
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
			Assert.assertTrue(expectedMessage.contains("AutoPri WorkersCompensation"));
			test.log(LogStatus.INFO, "Case with Insurance As WorkersCompensation Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsWorkerCompensation() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: AddInsuranceAsWorkerCompensation() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
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
		test.log(LogStatus.INFO, "Validating that FLR Link does not Appear on A Page..");
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//a[@id='Apage_Medicare-Functional-Limitation-Reporting']"))
					.isDisplayed(), "FLR TAB Appeared for RT Provider");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Fail");
			Assert.assertTrue(false, "FLR TAB Appeared for RT Provider");
		}
	}
}
