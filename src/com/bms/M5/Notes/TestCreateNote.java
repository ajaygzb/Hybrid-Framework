package com.bms.M5.Notes;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.HandlingCalendars;
import Utils.SearchPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCreateNote extends TestSetUp {
	public String strActualtext;
	CreateNoteUtils callto = new CreateNoteUtils();

	@Test(enabled = true, priority = 1)
	public void ValidateVisitTypeDDL() throws InterruptedException {
		test = extent.startTest("To Validate available visit type - first note as visit note on case", "Test Visit Type"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		try {
			System.out.println("Validating Values in Visit Type DDL"); // open
																		// DDL
			test.log(LogStatus.INFO, "Validating Values in Visit Type DDL");
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			Commons.Explicitwait();
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Evaluation/Transfer')]")).isDisplayed(),
					"Did not get Initial Visit in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Plan of Care Update')]")).isDisplayed(),
					"Did not get Followup Re-Evaluation in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Non Visit')]")).isDisplayed(),
					"Did not get Non Visit in Visit Type DDL");
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click(); // Closed
																					// DDL
			test.log(LogStatus.INFO, "Test:::: CreateNote Visit Type Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: CreateNote Visit Type Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void ValidateNonVisitTypeDDL() throws InterruptedException {
		test = extent.startTest("To Validate available visit type - first note as Non visit note on case",
				"Test Non Visit Type" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		System.out.println("ID from Previous test case" + strActualtext);
		if (!strActualtext.isEmpty()) {
			try {
				SearchPatientUtils.QuickpatientSearch(driver, strActualtext);
			} catch (Exception e) {
				System.out.println("Could not perform quick search by id");
			}
		} else {
			AddPatientUtils.QuickAddpatient(driver);
			strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Quick Add a Patient with ID==>" + strActualtext);
			test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		}
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		try {
			System.out.println("Setting Visit Type as Non Visit");
			test.log(LogStatus.INFO, "Setting Visit Type as Non Visit");
			CreateNotePage.VisitTypeddl(driver, "Non Visit");
			Commons.Explicitwait();
			HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																										// Start
																										// Of
																										// CARE
																										// DATE
			HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
			ActionUtils.sendKeys(CreateNotePage.NonVisitStatement(driver), "Non-Visit Statement test");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Sign and Finalize')]"), 130);
			System.out.println("Non-Visit Note crated Successfully");
			CreateNoteUtils.NavigatetoCreateNote(driver);
			System.out.println("Navigated to Create Notes");
			test.log(LogStatus.INFO, "Navigated to Create Notes");
			System.out.println("Validating Values in Visit Type DDL"); // open
																		// DDL
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			test.log(LogStatus.INFO, "Validating Values in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Evaluation/Transfer')]")).isDisplayed(),
					"Did not get Initial Visit in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Plan of Care Update')]")).isDisplayed(),
					"Did not get Followup Re-Evaluation in Visit Type DDL");
			Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Non Visit')]")).isDisplayed(),
					"Did not get Non Visit in Visit Type DDL");
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click(); // Closed
																					// DDL
			test.log(LogStatus.INFO, "Test:::: CreateNote Visit Type For Non-Visit Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: CreateNote Visit Type For Non-visit Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void SubsequentNoteVisitTypeDDL() throws InterruptedException {
		test = extent.startTest(
				"To Validate available visit type - subsequent note on case - where previous note created in R5",
				"Visit Type for subsequent note" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		System.out.println("ID from Previous test case" + strActualtext);
		if (strActualtext != null && !strActualtext.isEmpty()) {
			try {
				SearchPatientUtils.QuickpatientSearch(driver, strActualtext);
			} catch (Exception e) {
				System.out.println("Could not perform quick search by id");
			}
		} else {
			AddPatientUtils.QuickAddpatient(driver);
			strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Quick Add a Patient with ID==>" + strActualtext);
			test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		}
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		try {
			System.out.println("Setting Visit Type as Initial Visit");
			test.log(LogStatus.INFO, "Setting Visit Type as Initial Visit");
			if (Commons.existsElement(driver,
					By.xpath("//select[@name='ddlVisitType']//option[@label='Evaluation/Transfer']"))) {
				CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
				Commons.Explicitwait();
				HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");
				HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
				ActionUtils.click(CreateNotePage.AddAll(driver, "bodyparts"));
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