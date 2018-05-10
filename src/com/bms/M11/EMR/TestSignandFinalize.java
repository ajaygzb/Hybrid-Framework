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
import Utils.SPageUtils;

public class TestSignandFinalize extends TestSetUp {
	// TC-06 Sign and Finalize - Followup Visit
	// TC-08 Sign and Finalize - Followup Re-evaluation Visit
	// TC-09 Sign and Finalize - Followup Discharge Visit
	// TC-12 Sign and Finalize - Non Visit (complete case)
	// TC Sign Note: able to finalize note for PT user
	public String strActualtext = null;
	public List<WebElement> actual = null;

	@Test(enabled = true, priority = 1)
	public void SignandFinalizeWithFollowupVisit() throws InterruptedException {
		test = extent.startTest(
				"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Followup note",
				"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Followup note"
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
			Commons.waitForLoad(driver);
			EMRUtils.AddDataSpage(driver);
			test.log(LogStatus.INFO, "Added Data on S Page");
			EMRUtils.AddDataApage(driver);
			test.log(LogStatus.INFO, "Added Data on A Page");
			EMRUtils.AddDataPpage(driver);
			test.log(LogStatus.INFO, "Added Data on P Page");
			EMRUtils.AddDataOpage(driver);
			test.log(LogStatus.INFO, "Added Data on O Page");
			// Charge Capture and Finalize Note with Procedure code 97140
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).click();
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).clear();
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).sendKeys("97140");
			Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(text(),'MANUAL THERAPY')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'MANUAL THERAPY')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Charge Captured and Finalize Note with Procedure code 97140");
			Commons.screenshot();
			// To Check if Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 20)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			System.out.println("Waiting for Note Finalize...");
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					90);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 60);
			System.out.println("Initial Visit note finalized");
			test.log(LogStatus.INFO, "Initial Visit note finalized");
			// case-1
			test.log(LogStatus.INFO,
					"CASE-1:Create Followup Note and verify data copied from initial Note to Follow Up Note");
			test.log(LogStatus.INFO, "Create Followup Note");
			APageUtils.createfollowUpNewNote(driver);
			try {
				APageUtils.click(driver, "clickAPAge");
				Commons.waitForLoad(driver);
				test.log(LogStatus.INFO, "Verifying data in FLR Tab is modified/Copied from Initial Note");
				APageUtils.AssertFLRTABDATA(driver, "G8993", "CN", "G8994", "CH");
				Commons.Explicitwait();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='Apage_Goals-Rehab-Potential']")));
				Assert.assertTrue(Commons.ExistandDisplayElement(driver,
						By.xpath("//select//Option[contains(@selected,'selected')][contains(text(),'Very Good')]"), 20),
						"Data for Goals and Rehab not copied to Follow up Note");
				test.log(LogStatus.INFO, "*************Assertion Pass*************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Follow Up Note");
				Assert.assertFalse(true,
						"Could not Could not Validate Data In Follow Up Note" + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// case-2
			test = extent.startTest(
					"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Followup ReEvaluation note",
					"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Followup ReEvaluation note"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO,
					"CASE-2:Create Followup ReEvaluation Note and verify data copied from previous Note to Follow Up ReEvaluation Note");
			test.log(LogStatus.INFO, "Create Followup ReEvaluation Note");
			SPageUtils.creatingfollowupReEvaluationNote(driver);
			try {
				APageUtils.click(driver, "clickAPAge");
				Commons.waitForLoad(driver);
				test.log(LogStatus.INFO, "Verifying data in FLR Tab is modified/Copied from previous Note");
				APageUtils.AssertFLRTABDATA(driver, "G8993", "CN", "G8994", "CH");
				Commons.Explicitwait();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='Apage_Goals-Rehab-Potential']")));
				Assert.assertTrue(Commons.ExistandDisplayElement(driver,
						By.xpath("//select//Option[contains(@selected,'selected')][contains(text(),'Very Good')]"), 20),
						"Data for Goals and Rehab not copied to Follow up Note");
				test.log(LogStatus.INFO, "*************Assertion Pass*************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Follow Up ReEvaluation Note");
				Assert.assertFalse(true, "Could not Could not Validate Data In Follow Up ReEvaluation Note"
						+ Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// case-3
			test = extent.startTest(
					"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Followup Discharge note",
					"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Followup Discharge note"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO,
					"CASE-3:Create Followup Discharge Note and verify data copied from previous Note to FollowUp Discharge Note");
			test.log(LogStatus.INFO, "Create Followup Discharge Note");
			SPageUtils.creatingfollowupDischargeNote(driver);
			try {
				APageUtils.click(driver, "clickAPAge");
				Commons.waitForLoad(driver);
				test.log(LogStatus.INFO, "Verifying data in FLR Tab is modified/Copied from previous Note");
				APageUtils.AssertFLRTABDATA(driver, "G8993", "CN", "G8994", "CH");
				Commons.Explicitwait();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='Apage_Goals-Rehab-Potential']")));
				Assert.assertTrue(Commons.ExistandDisplayElement(driver,
						By.xpath("//select//Option[contains(@selected,'selected')][contains(text(),'Very Good')]"), 20),
						"Data for Goals and Rehab not copied to Follow up Note");
				test.log(LogStatus.INFO, "*************Assertion Pass*************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Follow Up Discharge Note");
				Assert.assertFalse(true, "Could not Could not Validate Data In Follow Up Discharge Note"
						+ Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// case-4
			test = extent.startTest(
					"Create a Non visit note for the complete case and verify  note finalizes with no charges produced ",
					"Create a Non visit note for the complete case and verify  note finalizes with no charges produced "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO,
					"CASE-4:Create a Non visit note for the complete case and verify  note finalizes with no charges produced ");
			test.log(LogStatus.INFO, "Create a Non visit Note");
			SPageUtils.creatingNonVisitNote(driver);
			try {
				ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
				System.out.println("Waiting for Note Finalize...");
				Commons.waitForLoad(driver);
				System.out.println("Finalizing Note...");
				test.log(LogStatus.INFO, "Finalized Note.");
				Commons.screenshot();
				Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 280);
				System.out.println("Non Visit note finalized");
				test.log(LogStatus.INFO, "Non Visit note finalized");
				Commons.waitForLoad(driver);
				test.log(LogStatus.INFO, "*************Assertion Pass*************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Non Visit Note");
				Assert.assertFalse(true,
						"Could not Could not Validate Data In Non Visit Note" + Throwables.getStackTraceAsString(e));
			}
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e) + "Test Sign and Finalize failed");
		}
	}

	@Test(enabled = true, priority = 2)
	public void SignandFinalizeWithPTuser() throws InterruptedException {
		test = extent.startTest("Sign and Fianlize Note with Complete SOAP data with PT user",
				"Sign and Fianlize Note with Complete SOAP data with PT user" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			System.out.println("Logged out app");
			System.out.println("Login as PT User");
			test.log(LogStatus.INFO, "Login as PT User");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
			APageUtils.GotoFLR(driver); // Added a Patient with all fields and
										// Complete case with insurance Medicare
										// FLR
			test.log(LogStatus.INFO, "Added a Patient with all fields and Complete case with insurance Medicare FLR");
			Commons.waitForLoad(driver);
			Commons.screenshot();
			APageUtils.AddFLRDATA(driver, "G8978", "CM", "CI", "Test Automation Sign and Finalize with PT user");
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			System.out.println("Setting Candidate for therapy as NO");
			Commons.waitforElement(driver, By.xpath("//input[@name='CandidateTherapy'][@value='2']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			/*
			 * Commons.waitForLoad(driver); EMRUtils.AddDataSpage(driver);
			 * test.log(LogStatus.INFO, "Added Data on S Page");
			 * EMRUtils.AddDataApage(driver); test.log(LogStatus.INFO,
			 * "Added Data on A Page"); EMRUtils.AddDataPpage(driver);
			 * test.log(LogStatus.INFO, "Added Data on P Page");
			 */
			/*
			 * EMRUtils.AddDataOpage(driver); test.log(LogStatus.INFO,
			 * "Added Data on O Page");
			 */
			// Charge Capture and Finalize Note with Procedure code 97140
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			APageUtils.addProcedureCode(driver, "97140");
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Charge Captured and Finalize Note with Procedure code 97140");
			Commons.screenshot();
			// To Check if Note had linked Library Items auto loaded pop up
			if (!Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 60)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			System.out.println("Waiting for Note Finalize...");
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					90);
			System.out.println("Note Finalized.");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 160);
			System.out.println("Initial Visit note finalized");
			test.log(LogStatus.INFO, "Initial Visit note finalized with PT user");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e) + "Test Sign and Finalize failed");
		}
	}
}
