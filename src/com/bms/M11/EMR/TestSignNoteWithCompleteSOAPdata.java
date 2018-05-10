package com.bms.M11.EMR;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.EMRUtils;

public class TestSignNoteWithCompleteSOAPdata extends TestSetUp {
	public String strActualtext = null;
	public List<WebElement> actual = null;

	// TC-2 Sign and Finalize - Initial Visit
	@Test(enabled = true, priority = 1)
	public void InitialVisitWithSOAP() throws InterruptedException {
		test = extent.startTest(
				"Sign and Fianlize Note with Complete SOAP data and validate all Hard stop messages for SOAP",
				"Sign and Fianlize Note with Complete SOAP data and validate all Hard stop messages for SOAP"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			APageUtils.GotoFLR(driver); // Added a Patient with all fields and
										// Complete case with insurance Medicare
										// FLR
			test.log(LogStatus.INFO, "Added a Patient with all fields and Complete case with insurance Medicare FLR");
			test.log(LogStatus.INFO,
					"To Validate Alert Message when Do NOT enter any minutes and click Charge Capture");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Validate Charges')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			test.log(LogStatus.INFO, "Clicked on Validate Charge button");
			Commons.waitforElement(driver, By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//form"), 30);
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
				strActualtext = driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//form"))
						.getText().trim();
				test.log(LogStatus.INFO, "Warning message displayed for no charge");
				Assert.assertEquals(strActualtext,
						"This note includes no charges. This will complete the note. Are you sure you want to complete the note at this time?");
				Commons.screenshot();
			}
			System.out.println("Closing warning pop up");
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
			test.log(LogStatus.INFO, "Clicked on Validate Charge button");
			strActualtext = Commons.capturemessage(driver, 20);
			Assert.assertEquals(strActualtext, "You must enter treatment minutes.");
			System.out.println("Entering Treatment minute and navigate to sign and finalize page");
			test.log(LogStatus.INFO, "Enter Treatment minute and navigate to sing and finalize page");
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "1");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//form"), 30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
			Commons.waitForLoad(driver);
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 20)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"), 40);
			test.log(LogStatus.INFO, "Triggred  6 Hard Stop message");
			Commons.screenshot();
			System.out.println("Triggred 6 Hard Stop message");
			actual = driver.findElements(By.xpath("//*[@id='errorGridId']//tbody/tr"));
			Assert.assertEquals(actual.size(), 6, "Incorrect no. of Hard stop mesages occured");
			try {
				System.out.println("No of Error messages found:" + actual.size());
				test.log(LogStatus.INFO, "No of Error messages found:" + actual.size());
				test.log(LogStatus.INFO, "Validating hard Stop messages text");
				for (WebElement we : actual) {
					System.out.println(we.getText());
					test.log(LogStatus.INFO, we.getText());
				}
				String[] expected1 = {
						"Red Flags - You must select \"Yes\" or \"No.\" If you select \"Yes,\" you must select at least one red flag from the list that displays.",
						"Allergies - You must select \"Yes\" or \"No.\" If you select \"Yes,\" you must select at least one allergy from the list that displays.",
						"Precautions/Contraindications - You must select \"Yes\" or \"No.\" If you select \"Yes,\" you must select at least one precaution/contraindication from the list that displays.",
						"Assessment - You must select if the patient is a candidate for therapy.",
						"Functional Reporting Codes - You must enter functional reporting codes.",
						"Severity Modifier Rationale - You must enter a severity modifier rationale." };
				for (int i = 0; i < 5; i++) {
					Assert.assertTrue(actual.get(i).getText().equals(expected1[i]));
				}
				System.out.println("************Assertion Pass*********");
				// closing error pop up
				driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")).click();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Hard Stop Messages on Sign and Finalize page");
				Assert.assertFalse(true, "Could not Validate Hard Stop Messages on Sign and Finalize page"
						+ Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Goto A page and Add A page Data");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.waitForLoad(driver);
			// closing error pop up
			System.out.println("Waiting for Hard stop messages to trigger....");
			/*Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver, By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"),
					80)) {
				driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")).click();
			}*/
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			System.out.println("Setting Candidate for therapy as YES");
			ActionUtils.click(driver.findElement(By.xpath("//input[@id='therapyradio']")));
			Commons.screenshot();
			Commons.waitForLoad(driver);
			APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation");
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"), 80);
			System.out.println("Triggred Hard Stop message");
			test.log(LogStatus.INFO, "Triggred  11 Hard Stop message");
			Commons.screenshot();
			System.out.println("Asserting Hard stop messages");
			// Asserting 11 Error messages on Sing and Finalize screen
			actual = driver.findElements(By.xpath("//*[@id='errorGridId']//tbody/tr"));
			Assert.assertEquals(actual.size(), 11);
			System.out.println("No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "Validating hard Stop messages text");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = {
					"Current Problems - You must enter at least one problem, the date of onset and mechanism of onset.",
					"Red Flags - You must select \"Yes\" or \"No.\" If you select \"Yes,\" you must select at least one red flag from the list that displays.",
					"Allergies - You must select \"Yes\" or \"No.\" If you select \"Yes,\" you must select at least one allergy from the list that displays.",
					"Precautions/Contraindications - You must select \"Yes\" or \"No.\" If you select \"Yes,\" you must select at least one precaution/contraindication from the list that displays.",
					"Medications - You must select if there are any medications. If you select \"Table,\" you must enter available details in at least one row.",
					"Assessment - You must enter an assessment.", "Goals - You must enter at least one goal.",
					"Rehab Potential - You must select a rehab potential.",
					"Plan of Care Expiration Date - You must Enter a Plan of Care Expiration Date.",
					"Schedule - You must select frequency and duration of visits using all four drop down menus in the first row of the schedule table.",
					"Planned Interventions - You must enter information indicating the interventions you plan to use during the episode of care." };
			for (int i = 0; i < 10; i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected1[i]));
			}
			System.out.println("************Assertion Pass*********");
			// closing error pop up
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
			Commons.screenshot();
			test.log(LogStatus.INFO, "Adding Spage Data");
			System.out.println("Adding Spage Data");
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToSubjective()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToSubjective()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(@href,'#PatientReport')]"), 40);
			System.out.println("Navigated to Spage");
			EMRUtils.AddDataSpage(driver);
			// Asserting Hard stop after S page Data Added
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 40);
			try {
				driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")).click();
			} catch (Exception e) {
			}
			Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"), 260);
			System.out.println("Triggred Hard Stop message");
			test.log(LogStatus.INFO, "Triggred  6 Hard Stop message");
			Commons.screenshot();
			System.out.println("Asserting Hard stop messages");
			actual = driver.findElements(By.xpath("//*[@id='errorGridId']//tbody/tr"));
			Assert.assertEquals(actual.size(), 6);
			System.out.println("No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "Validating hard Stop messages text");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			String[] expected2 = { "Assessment - You must enter an assessment.",
					"Goals - You must enter at least one goal.", "Rehab Potential - You must select a rehab potential.",
					"Plan of Care Expiration Date - You must Enter a Plan of Care Expiration Date.",
					"Schedule - You must select frequency and duration of visits using all four drop down menus in the first row of the schedule table.",
					"Planned Interventions - You must enter information indicating the interventions you plan to use during the episode of care." };
			for (int i = 0; i < 6; i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected2[i]));
			}
			System.out.println("************Assertion Pass*********");
			// closing error pop up
			driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")).click();
			System.out.println("Adding Apage Data");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.waitForLoad(driver);
			EMRUtils.AddDataApage(driver);
			// Asserting Hard stop after A page Data Added
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 40);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"), 90);
			test.log(LogStatus.INFO, "Triggred  3 Hard Stop message");
			System.out.println("Triggred Hard Stop message");
			Commons.screenshot();
			System.out.println("Asserting Hard stop messages");
			actual = driver.findElements(By.xpath("//*[@id='errorGridId']//tbody/tr"));
			Assert.assertEquals(actual.size(), 3);
			System.out.println("No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "Validating hard Stop messages text");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			String[] expected3 = { "Plan of Care Expiration Date - You must Enter a Plan of Care Expiration Date.",
					"Schedule - You must select frequency and duration of visits using all four drop down menus in the first row of the schedule table.",
					"Planned Interventions - You must enter information indicating the interventions you plan to use during the episode of care." };
			for (int i = 0; i < 3; i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected3[i]));
			}
			System.out.println("************Assertion Pass*********");
			// closing error pop up
			driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")).click();
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			EMRUtils.AddDataPpage(driver);
			EMRUtils.AddDataOpage(driver);
			// Charge Capture and Finalize Note with Procedure code 97140
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 30);
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).click();
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).clear();
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).sendKeys("97140");
			Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(text(),'MANUAL THERAPY')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'MANUAL THERAPY')]")));
			Commons.waitForLoad(driver);
			// Assertion-1
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "5");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			strActualtext = Commons.capturemessage(driver, 50);
			Assert.assertTrue(strActualtext.contains("You have selected more units than the time entered allows"));
			System.out.println("Clicked on Validate Charge button");
			// Assertion-2
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "60");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='ValidateWarmUnderBilledMessagePopUp']//div[contains(text(),'You have not charged to the maximum number of units based on the number')]"),
					240);
			Assert.assertTrue(driver
					.findElement(By
							.xpath("//div[@id='ValidateWarmUnderBilledMessagePopUp']//div[contains(text(),'You have not charged to the maximum number of units based on the number')]"))
					.getText().contains("You have not charged to the maximum number of units based on the number"));
			Commons.screenshot();
			driver.findElement(By.xpath("//div[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
					.click();
			Commons.waitForLoad(driver);
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 20)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					80);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
			System.out.println("Verifying Output report Gnenerated and status completed");
			Commons.waitforElement(driver,
					By.xpath(".//*[@id='outputReportGrid']/table/tbody//span[contains(text(),'Completed')]"), 60);
			Assert.assertEquals(driver
					.findElement(
							By.xpath(".//*[@id='outputReportGrid']/table/tbody//span[contains(text(),'Completed')]"))
					.getText().trim(), "Completed");
			System.out.println("Opening output report");
			test.log(LogStatus.INFO, "Opening output report");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='outputReportGrid']/table/tbody/tr[1]")));
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//*[@id='outputReportGrid']/table/tbody/tr[1]")));
			Commons.scrollElementinotView(driver.findElement(By.xpath("//button[contains(.,'Re-Generate')]")), driver);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
