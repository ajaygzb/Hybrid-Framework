package PerformanceTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.HandlingCalendars;
import Utils.SearchPatientUtils;

public class TestCase extends TestSetUp {
	public String strActualtext;

	@Test(priority = 1, enabled = true)
	public void CaseSaveWithNoInfo() {
		try {
			test = extent.startTest("Add case with No fields", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Case");
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			System.out.println(expectedMessage);
			Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(CasePage.GetFirstCaseNameFromCaseList(driver).getText(), "");
			test.log(LogStatus.INFO, "Blank Case Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: CaseSaveWithNoInfo() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: CaseSaveWithNoInfo() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2, enabled = true)
	public void CaseEditWithAllFields() {
		try {
			test = extent.startTest("Edit Case with All fields", "Test Case Information" + "*****Current Browser******"
					+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Case");
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			HandlingCalendars.datepick(driver, CasePage.CaseEffectiveDate(driver), "14/06/2015");// Enter
																									// Case
																									// effective
																									// date
			AddCaseUtils.ClickCaseSave(driver);
			ActionUtils.click(CasePage.CaseEditButton(driver));
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 40);
			test.log(LogStatus.INFO, "Edited Case Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: CaseEditWithAllFields() as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: CaseEditWithAllFields() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 3, enabled = true)
	public void CaseAsCompleted() throws InterruptedException {
		test = extent.startTest("Add complete case with All fields", "Test Case Information"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Case");
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			AddCaseUtils.AddDXCode(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
			test.log(LogStatus.INFO, "Verified Primary Insurance as AutoPri Blue Shield");
			test.log(LogStatus.INFO, "Case Completed successfully.");
			test.log(LogStatus.INFO, "Test:::: CaseAsCompleted() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: CaseAsCompleted() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void SubsequentNoteVisitTypeDDL() throws InterruptedException {
		test = extent.startTest(
				"To Validate available visit type - subsequent note on case - where previous note created in R5",
				"Visit Type for subsequent note" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Case");
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		try {
			System.out.println("Setting Visit Type as Initial Visit");
			test.log(LogStatus.INFO, "Setting Visit Type as Initial Visit");
			if (Commons.existsElement(driver,
					By.xpath("//select[@name='ddlVisitType']//option[@label='Evaluation/Transfer']"))) {
				CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
				HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																											// Start
																											// Of
																											// CARE
																											// DATE
				HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
				// ActionUtils.click(CreateNotePage.AddAll(driver));
				ActionUtils.click(CreateNotePage.AddAll(driver, "bodyparts"));
				// ActionUtils.click(CreateNotePage.AddAll(driver,
				// "PersonalTemplates"));
				// ActionUtils.click(CreateNotePage.AddAll(driver,
				// "PublicTemplates"));
				ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
				try {
					Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
					System.out.println("Confirmation pop up appeared");
					driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
				} catch (Exception e) {
					System.out.println("Confirmation pop up Not appeared");
				}
				Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 200);
				System.out.println("Initial-Visit Note crated Successfully");
				test.log(LogStatus.INFO, "Initial-Visit Note crated Successfully");
				Commons.Explicitwait();
				ActionUtils.click(CreateNotePage.HomeButton(driver));
				Commons.Explicitwait();
				CreateNoteUtils.NavigatetoCreateNote(driver);
			} else {
				System.out.println("Current Patient Already has Initial Visit Done");
				test.log(LogStatus.INFO, "Current Patient Already has Initial Visit Done");
			}
			System.out.println("Navigated to Create Notes");
			test.log(LogStatus.INFO, "Navigated to Create Notes");
			System.out.println("Validating Values in Visit Type DDL"); // open
																		// DDL
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			test.log(LogStatus.INFO, "Validating Values in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Followup Visit')]")).isDisplayed(),
					"Did not get Followup Visit in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Plan of Care Update')]")).isDisplayed(),
					"Did not get Followup Re-Evaluation in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Followup Discharge')]")).isDisplayed(),
					"Did not get Followup Discharge in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Non Visit')]")).isDisplayed(),
					"Did not get Non Visit in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Non Visit Discharge')]")).isDisplayed(),
					"Did not get Non Visit Discharge in Visit Type DDL");
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click(); // Closed
																					// DDL
			test.log(LogStatus.INFO, "Test:::: CreateNote Visit Type For Subsequent-Visit Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: CreateNote Visit Type For Subsequent-visit Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
