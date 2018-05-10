package com.bms.M8.FLR;

import java.util.Random;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;

public class TestCreateCompleteNote extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void CreateCompleteNote() throws InterruptedException {
		test = extent.startTest("Charge Captured and Finalize Note with Procedure code 97140",
				"Charge Captured and Finalize Note with Procedure code 97140" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// APageUtils.GotoFLR(driver);
		try {
			AddPatientUtils.addpatient_withallfields(driver, "Note Complete" + new Random().nextInt(10000000));// Add
																												// Patient
																												// all
																												// Details
			String strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 500);
			// Assert.assertTrue(Commons.existsElement(driver,
			// By.xpath("//button[contains(.,'Edit Case')]")),"Could not save
			// case..");
			// AddCaseUtils.GoToCaseList(driver);
			// String
			// expectedMessage=AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			// System.out.println("Expected=>"+expectedMessage );
			// Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
			test.log(LogStatus.INFO, "Added Patient with all fields and completed Case:" + strText_Actual);
			Commons.screenshot();
			// Create Notes and Navigating to A page
			Commons.waitForLoad(driver);
			APageUtils.createNewNote(driver);
			System.out.println("Created a New Note for Initial Visit");
			APageUtils.click(driver, "clickAPAge");
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
			System.out.println("Successfully redirected to APage");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			// System.out.println("Validating that FLR Link Appear on A
			// Page..");
			// Commons.waitforElement(driver,
			// By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"),
			// 60);
			// APageUtils.click(driver, "ClickMedicareFLRLink");
			Commons.waitForLoad(driver);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Added Initial Visit Note");
			// APageUtils.AddFLRDATA(driver, "G8993","CN","CH","Test
			// Automation");
			// test.log(LogStatus.INFO, "Added FLR Data to Initial Note");
			// APageUtils.FinalizeNote(driver); //Initial Visit note finalized
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAssesmentAndPrognosislink");
			Commons.waitForLoad(driver);
			System.out.println("Setting Candidate for therapy as NO");
			Commons.waitforElement(driver, By.xpath("//input[@name='CandidateTherapy'][@value='2']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
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
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Sign and Finalize')]"), 290);
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Charge Captured and Finalize Note with Procedure code 97140");
			Commons.screenshot();
			// To Check if Note had linked Library Items auto loaded pop up
			/*
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),10)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 */
			System.out.println("Waiting for Note Finalize...");
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					90);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
			Commons.ExistandDisplayElement(driver, By.xpath("//input[@value='Add Addendum']"), 60);
			System.out.println("Initial Visit note finalized");
			test.log(LogStatus.INFO, "Initial Visit note finalized");
		} catch (Exception e) {
			System.out.println("Got exception");
		}
	}
}
