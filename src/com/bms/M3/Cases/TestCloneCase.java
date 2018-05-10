package com.bms.M3.Cases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCloneCase extends TestSetUp {
	@Test(priority = 3)
	public void testCloneIncompleteActiveCase() throws InterruptedException {
		test = extent.startTest("Test Clone Incomplete Active Case", "Test Able to Clone Active Incomplete Case"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			// Add new Case with Just case Name
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			Commons.Explicitwait();
			for (int i = 0; i < 3; i++) {
				ActionUtils.sendKeys(CasePage.CaseName(driver), "Case Clone Base");// Enter
																					// Case
																					// Name
			}
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver); // Go to Case List
			Commons.Explicitwait();
			ActionUtils.click(CasePage.CloneCaseRadioButtonFirstCase(driver));// click
																				// on
																				// Clone
																				// Case
																				// Radio
																				// Button
																				// for
																				// the
																				// recently
																				// added
																				// Case
			Commons.Explicitwait();
			ActionUtils.click(CasePage.CloneCaseGreenButton(driver)); // Click
																		// on
																		// Clone
																		// Case
																		// Green
																		// Button
			ActionUtils.click(CasePage.CloneCasePopUpYesButton(driver)); // Click
																			// on
																			// Clone
																			// Case
																			// Green
																			// Button
			Commons.Explicitwait();
			for (int i = 0; i < 3; i++) {
				Commons.waitForLoad(driver);
				CasePage.CaseName(driver).clear();
				ActionUtils.click(CasePage.CaseName(driver));
				ActionUtils.sendKeys(CasePage.CaseName(driver), "Case Clone New");
			} // Enter Case Name
			Commons.Explicitwait();
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Case Clone Base"));
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Case Clone New"));
			test.log(LogStatus.INFO, "Case Updated Successfully");
			Assert.assertTrue(AddCaseUtils.GetFirstCaseStatus(driver).contains("Incomplete"));
			test.log(LogStatus.INFO, "Test:::: testCloneIncompleteActiveCase() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error !!!" + e);
			test.log(LogStatus.INFO, "Test:::: testCloneIncompleteActiveCase() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestCloneCase",
			// "testCloneIncompleteActiveCase");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 1)
	public void testCloneCompleteActiveCase() throws InterruptedException {
		test = extent.startTest("Test Clone Complete Active Case", "Test Able to Clone Active Complete Case"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			// Add new Case with Just case Name
			AddPatientUtils.addpatient_withallfields(driver, "");// Add Patient
																	// all
																	// Details
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);// Enter Case Name
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver); // Go to Case List
			Assert.assertTrue(AddCaseUtils.GetFirstCaseStatus(driver).contains("Complete"));
			ActionUtils.click(CasePage.CloneCaseRadioButtonFirstCase(driver)); // click
																				// on
																				// Clone
																				// Case
																				// Radio
																				// Button
																				// for
																				// the
																				// recently
																				// added
																				// Case
			Commons.Explicitwait();
			ActionUtils.click(CasePage.CloneCaseGreenButton(driver)); // Click
																		// on
																		// Clone
																		// Case
																		// Green
																		// Button
			ActionUtils.click(CasePage.CloneCasePopUpYesButton(driver)); // Click
																			// on
																			// Clone
																			// Case
																			// Green
																			// Button
			Commons.Explicitwait();
			for (int i = 0; i < 3; i++) {
				Commons.waitForLoad(driver);
				CasePage.CaseName(driver).clear();
				ActionUtils.click(CasePage.CaseName(driver));
				ActionUtils.sendKeys(CasePage.CaseName(driver), "Case Clone New");// Enter
																					// Case
																					// Name
			}
			Commons.scrollElementinotView(CasePage.AssignmentDate(driver), driver);
			Commons.Explicitwait();
			HandlingCalendars.datepick(driver, CasePage.AssignmentDate(driver), "14/05/2015");// Enter
																								// Assignment
																								// date
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");
			Commons.Explicitwait();
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Case Clone New"));
			Assert.assertTrue(AddCaseUtils.GetFirstCaseStatus(driver).contains("Incomplete"));
			test.log(LogStatus.INFO, "Case Updated Successfully");
			test.log(LogStatus.INFO, "Test:::: testCloneCompleteActiveCase() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error !!!" + e);
			System.out.println("Assertion Error !!!" + e);
			test.log(LogStatus.INFO, "Test:::: testCloneCompleteActiveCase() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestCloneCase",
			// "testCloneCompleteActiveCase");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2)
	public void testCloneExistingCase() throws InterruptedException {
		test = extent.startTest("Test Clone Existing Case", "Test Able to Clone Existing Case "
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			// Add new Case with Just case Name
			// AddCaseUtils.GetRandomPatientFomPatientListOnAdvanceSearchPage(driver);
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitforElement(driver, By.xpath("//input[@type='radio' and @name='cloneCase']"), 20);
			ActionUtils.click(CasePage.CloneCaseRadioButtonFirstCase(driver));
			// click on Clone Case Radio Button for the recently added Case
			Commons.Explicitwait();
			ActionUtils.click(CasePage.CloneCaseGreenButton(driver)); // Click
																		// on
																		// Clone
																		// Case
																		// Green
																		// Button
			ActionUtils.click(CasePage.CloneCasePopUpYesButton(driver)); // Click
																			// on
																			// Clone
																			// Case
																			// Green
																			// Button
			Commons.Explicitwait();
			for (int i = 0; i < 3; i++) {
				Commons.waitForLoad(driver);
				CasePage.CaseName(driver).clear();
				ActionUtils.click(CasePage.CaseName(driver));
				ActionUtils.sendKeys(CasePage.CaseName(driver), "Cloned Case With Required Fields ");// Enter
																										// Case
																										// Name
			}
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsWorkerCompensation(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver); // Go to Case List
			Assert.assertTrue(
					driver.findElement(By.tagName("body")).getText().contains("Cloned Case With Required Fields"));
			Assert.assertTrue(AddCaseUtils.GetFirstCaseStatus(driver).contains("Incomplete"));
			test.log(LogStatus.INFO, "Case Updated Successfully");
			test.log(LogStatus.INFO, "Test:::: testCloneAndExistingCase() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error !!!");
			test.log(LogStatus.INFO, "Test:::: testCloneAndExistingCase() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestCloneCase",
			// "testCloneAndExistingCase");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
