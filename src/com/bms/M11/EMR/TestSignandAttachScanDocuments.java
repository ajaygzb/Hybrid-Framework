package com.bms.M11.EMR;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.EMRUtils;
import Utils.SPageUtils;
import Utils.SearchPatientUtils;

public class TestSignandAttachScanDocuments extends TestSetUp {
	// TC-05 Sign and Attach Scanned Documents - Initial Visit
	// TC-07 Sign and Attach Scanned Documents - Followup Visit
	// TC-08 Sign and Attach Scanned Documents - Followup Re-evaluation Visit
	// TC-10 Sign and Attach Scanned Documents - Followup Discharge Visit
	// TC-13 Sign and Attach Scanned Documents - Non Visit
	// TC-15 Sign and Attach Scanned Documents - Non Visit Discharge
	public String strActualtext = null;
	public List<WebElement> actual = null;

	@Test(enabled = true, priority = 1)
	public void SignandAttachScanDocuments() throws InterruptedException {
		test = extent.startTest("Sign and Attach Scanned Documents - Initial Visit",
				"Sign and Attach Scanned Documents - Initial Visit" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "Add Patient with all fields and complete case"); 
			AddPatientUtils.addpatient_withallfields(driver, "Demoscan");
			AddCaseUtils.GoToAddNewCase(driver);
			Commons.waitforElement(driver, By.id("saveCaseDetailsButton"), 30);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			String PID = ActionUtils.getText(AddPatientPage.patientID(driver));
			
			try {
				// Assign a image from image queue
				test.log(LogStatus.INFO, "Go to Scanne queue and assign image to current patient" + PID);
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Communication')]")));
				ActionUtils.click(driver.findElement(By.xpath("(//span[contains(.,'Scan')])[1]")));
				ActionUtils.click(driver.findElement(By.xpath("(//span[contains(.,'Scanned Queue')])[1]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//*[@id='sel_POS']"), 60);
				Commons.screenshot();
				if (Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='scannedQueueImageList']//td[contains(.,' No Record Found')]"), 5)) {
					System.out.println("No Image in Scanned Queue");
					test.log(LogStatus.INFO, "No Image in Scanned Queue");
					System.out.println("trying to add image to sccaned queue");
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Communication')]")));
					ActionUtils.click(driver.findElement(By.xpath("(//span[contains(.,'Scan')])[1]")));
					ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Image Indexes')]")));
					Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Image Indexes')]"), 30);
					ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSearchImageIndexes']")));
					Thread.sleep(2000);
					ActionUtils.click(driver.findElement(By.xpath("//*[@id='imageList']/div/a[4]/span")));
					Thread.sleep(2000);
					Commons.waitForLoad(driver);
					Commons.waitforElement(driver, By.xpath("//*[@id='imageList']//tbody//tr[last()]"), 70);
					ActionUtils.doubleClick(driver,
							driver.findElement(By.xpath("//*[@id='imageList']//tbody//tr[last()]")));
					Commons.screenshot();
					Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Edit Image Indexes')]"), 60);
					ActionUtils.click(driver.findElement(By.xpath("//button[@id='btnEditImageIndex']")));
					Thread.sleep(2000);
					ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkIsIndexed']")));
					System.out.println("Un Indexed image from image index");
					driver.findElement(By.xpath("//input[@id='txtImageName']")).clear();
					driver.findElement(By.xpath("//input[@id='txtImageName']")).click();
					ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txtImageName']")), "Auto" + PID);
					Commons.screenshot();
					ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSave']")));
					Commons.waitForLoad(driver);
					Commons.capturemessage(driver, 60);
					System.out.println("Navigate Back to Image Queue to check image added");
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Communication')]")));
					ActionUtils.click(driver.findElement(By.xpath("(//span[contains(.,'Scan')])[1]")));
					ActionUtils.click(driver.findElement(By.xpath("(//span[contains(.,'Scanned Queue')])[1]")));
					Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='sel_POS']"), 60);
					Commons.screenshot();
					Assert.assertFalse(
							(Commons.ExistandDisplayElement(driver,
									By.xpath("//*[@id='scannedQueueImageList']//td[contains(.,' No Record Found')]"),
									5)),
							"*********No image found in Image Queue hence cannot proceed test*************");
				}
				// Assert.assertFalse(true,"No Image in Scanned Queue, Cannot
				// Proceed to test.");
				Commons.scrollElementinotView(
						driver.findElement(By.xpath("//select[@name='POSList']//following::div[2]//tbody//tr[last()]")),
						driver);
				ActionUtils.doubleClick(driver, driver
						.findElement(By.xpath("//select[@name='POSList']//following::div[2]//tbody//tr[last()]")));
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='patientInput']"), 30);
				System.out.println("Assigning Image to Patient..");
				Thread.sleep(2000);
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='patientInput']")));
				Thread.sleep(2000);
				ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='divPatientAutoSearch']//input")), PID);
				Thread.sleep(2000);
				Commons.ExistandDisplayElement(driver, By.xpath("//li[contains(@class,'selected')]"), 20);
				Thread.sleep(5000);
				ActionUtils.click(driver.findElement(By.xpath("//li[contains(@class,'selected')]")));
				/*driver.findElement(By.xpath("//*[@id='txtImageIndexName']")).clear();
				driver.findElement(By.xpath("//*[@id='txtImageIndexName']")).click();
				ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='txtImageIndexName']")), "Auto" + PID);*/
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btnAssign']")));
				Commons.capturemessage(driver, 60);
			} catch (Exception e) {
				System.out.println("**********************Unable to assign image to patient....*********"+Throwables.getStackTraceAsString(e));
			}
			// CReate NOte
			test.log(LogStatus.INFO, "Create Initial Visit Note");
			CreateNoteUtils.NavigatetoCreateNote(driver);
			// select one body part
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(), 'Ankle/Foot')]")));
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Selected']")));
			// hit Create Note
			ActionUtils.click(driver.findElement(By.id("btnCreateNote")));
			Commons.waitforElement(driver, By.xpath("//button[@value='Create New Note']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//button[@value='Create New Note']")));
			Commons.waitforElement(driver, By.linkText("Charge Capture"), 40);
			CreateNoteUtils.clikOnAPageOnSOAP(driver);
			APageUtils.ClickCandidateTherapyNo(driver, "no");
			Commons.waitForLoad(driver);
			// Charge Capture and Finalize Note with Procedure code 97140
			test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code 97140");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			APageUtils.addProcedureCode(driver, "97140");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			try {
				test.log(LogStatus.INFO, "Sign and attached sccaned docs");
				ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//div[contains(@class,'modal-body')]"),
						60);
				System.out.println(driver
						.findElement(By
								.xpath("//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//div[contains(@class,'modal-body')]"))
						.getText());
				String strtext = driver
						.findElement(By
								.xpath("//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//div[contains(@class,'modal-body')]"))
						.getText();
				// Assert.assertTrue(strtext.contains("You are about to finalize
				// the note and the charges (if any) associated with it. Once
				// the note is finalized, you will not be able to edit it or the
				// charges. If you want to finalize the note, select the scanned
				// documents to attach and then click Finalize. Otherwise click
				// Cancel to return to the note."));
				Assert.assertTrue(strtext.contains("Scanned Documents"));
				Assert.assertTrue(strtext.contains("Document Name"));
				Assert.assertTrue(strtext.contains("Image Type"));
				Assert.assertTrue(strtext.contains("Category Type"));
				Assert.assertTrue(strtext.contains("Case"));
				Assert.assertTrue(driver
						.findElement(By
								.xpath("//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//button[text()='Finalize']"))
						.isDisplayed());
				Assert.assertTrue(driver
						.findElement(By
								.xpath("//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//button[text()='Cancel']"))
						.isDisplayed());
				ActionUtils.click(driver.findElement(
						By.xpath("//*[@id='SignAttachScannedDocuments']//button[contains(text(),'Cancel')]")));
				Commons.waitForElementToBeNotVisible(driver,
						By.xpath("//*[@id='SignAttachScannedDocuments']//button[contains(text(),'Cancel')]"), 40);
				ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
				Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']"),
						90);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[text()='Finalize']")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Output Reports')]"), 60);
				test.log(LogStatus.INFO, "Initial Visit note finalized attached sccaned document");
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("//i[@id='headerCaseInfoIcon']"), 30);
				ActionUtils.click(driver.findElement(By.xpath("//i[@id='headerCaseInfoIcon']")));
				strActualtext = ActionUtils
						.getText(driver.findElement(By.xpath("//*[@id='headerCaseInfoIcon_tt_active']/div[1]")));
				Assert.assertTrue(strActualtext.contains("Total Visit Notes : 1"));
				test.log(LogStatus.INFO, "Verify Visit count is 1 after initial visit completed");
			} catch (AssertionError e) {
				Assert.assertFalse(true,
						"Assertion error after sign anf finalized scanned doc" + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// case-1 TC-5
			test = extent.startTest(
					"CASE-1:==>>Create Followup Note and verify  Sign and Attch Scanned Documents again with same patient",
					"CASE-1:==>>Create Followup Note and verify  Sign and Attch Scanned Documents again with same patient"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.log(LogStatus.INFO,
					"CASE-1:==>>Create Followup Note and verify  Sign and Attch Scanned Documents again with same patient");
			test.log(LogStatus.INFO, "Create Followup Note");
			test.log(LogStatus.INFO, "To verify charges ");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]"), 30);
			// charges AFTER finalize
			String chargebefore = driver.findElement(By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]")).getText()
					.trim();
			test.log(LogStatus.INFO,
					"Charges after finalized initial note and sccaned documant attached" + chargebefore);
			APageUtils.createfollowUpNewNote(driver);
			try {
				// Charge Capture and Finalize Note with Procedure code 97140
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
				Commons.waitForLoad(driver);
				APageUtils.addProcedureCode(driver, "97113");
				Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
				ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "100");
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
				System.out.println("Clicked on Validate Charge button");
				if (Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 20)) {
					System.out.println(
							"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
					Commons.screenshot();
					driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
							.click();
				}
				// ******************
				
				ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
				Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']"),
						90);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[text()='Finalize']")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Output Reports')]"), 60);
				test.log(LogStatus.INFO, "Initial Visit note finalized attached sccaned document");
				test.log(LogStatus.INFO, "To verify charges updated ");
				AddChargeUtils.NavigateToViewChargePage(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]"), 30);
				// charges AFTER finalize
				String chargeafter = ActionUtils
						.getText(driver.findElement(By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]")));
				test.log(LogStatus.INFO, "Charges after finalized and sccaned" + chargeafter);
				// Assert.assertNotEquals(chargeafter,chargebefore);
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("//i[@id='headerCaseInfoIcon']"), 30);
				ActionUtils.click(driver.findElement(By.xpath("//i[@id='headerCaseInfoIcon']")));
				strActualtext = ActionUtils
						.getText(driver.findElement(By.xpath("//*[@id='headerCaseInfoIcon_tt_active']/div[1]")));
				Assert.assertTrue(strActualtext.contains("Total Visit Notes : 2"));
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Follow Up Note");
				Assert.assertFalse(true,
						"Could not Could not Validate Data In Follow Up Note" + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// case-2 TC-8
			test = extent.startTest(
					"CASE-2:==>>Create Followup ReEvaluation note and verify  Sign and Attch Scanned Documents again with same patient",
					"Followup ReEvaluation note and verify  Sign and Attch Scanned Documents again with same patient"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Create Followup ReEvaluation Note");
			SPageUtils.creatingfollowupReEvaluationNote(driver);
			try {
				// Charge Capture and Finalize Note with Procedure code 97140
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
				ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "100");
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
				System.out.println("Clicked on Validate Charge button");
				if (Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 10)) {
					System.out.println(
							"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
					Commons.screenshot();
					driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
							.click();
				}
				// ******************
				ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
				Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']"),
						90);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[text()='Finalize']")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Output Reports')]"), 60);
				test.log(LogStatus.INFO, "Initial Visit note finalized attached sccaned document");
				test.log(LogStatus.INFO, "To verify charges updated ");
				AddChargeUtils.NavigateToViewChargePage(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]"), 30);
				// charges AFTER finalize
				String chargeafter = driver.findElement(By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]"))
						.getText().trim();
				test.log(LogStatus.INFO, "Charges after finalized and sccaned" + chargeafter);
				// Assert.assertNotEquals(chargeafter,chargebefore);
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("//i[@id='headerCaseInfoIcon']"), 30);
				ActionUtils.click(driver.findElement(By.xpath("//i[@id='headerCaseInfoIcon']")));
				strActualtext = ActionUtils
						.getText(driver.findElement(By.xpath("//*[@id='headerCaseInfoIcon_tt_active']/div[1]")));
				Assert.assertTrue(strActualtext.contains("Total Visit Notes : 3"));
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Follow Up ReEvaluation Note");
				Assert.assertFalse(true, "Could not Could not Validate Data In Follow Up ReEvaluation Note"
						+ Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// case-3 TC-13
			test = extent.startTest(
					"CASE-3:==>>Create Non visit note and verify  Sign and Attch Scanned Documents again with same patient ",
					"CASE-3:==>>Create Non visit note and verify  Sign and Attch Scanned Documents again with same patient "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO,
					"CASE-4:Create a Non visit note for the complete case and verify  note finalizes with no charges produced ");
			test.log(LogStatus.INFO, "Create a Non visit Note");
			SPageUtils.creatingNonVisitNote(driver);
			try {
				ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
				Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']"),
						90);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[text()='Finalize']")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Output Reports')]"), 60);
				test.log(LogStatus.INFO, "Initial Visit note finalized attached sccaned document");
				test.log(LogStatus.INFO, "To verify charges updated ");
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("//i[@id='headerCaseInfoIcon']"), 30);
				ActionUtils.click(driver.findElement(By.xpath("//i[@id='headerCaseInfoIcon']")));
				strActualtext = ActionUtils
						.getText(driver.findElement(By.xpath("//*[@id='headerCaseInfoIcon_tt_active']/div[1]")));
				Assert.assertTrue(strActualtext.contains("Total Visit Notes : 3"));
				test.log(LogStatus.INFO, "*************Assertion Pass*************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Non Visit Note");
				Assert.assertFalse(true,
						"Could not Could not Validate Data In Non Visit Note" + Throwables.getStackTraceAsString(e));
			}
			// case-4 TC-10
			test = extent.startTest(
					"CASE-4:==>>Create Followup Discharge note and verify  Sign and Attch Scanned Documents again with same patient",
					"Create Followup Discharge note and verify  Sign and Attch Scanned Documents again with same patient"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO,
					"CASE-4:Create Followup Discharge Note and verify data copied from previous Note to FollowUp Discharge Note");
			test.log(LogStatus.INFO, "Create Followup Discharge Note");
			SPageUtils.creatingfollowupDischargeNote(driver);
			try {
				// Charge Capture and Finalize Note with Procedure code 97140
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
				ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "100");
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
				System.out.println("Clicked on Validate Charge button");
				if (Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 30)) {
					System.out.println(
							"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
					Commons.screenshot();
					driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
							.click();
				}
				// ******************
				ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
				Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']"),
						90);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[text()='Finalize']")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Output Reports')]"), 60);
				test.log(LogStatus.INFO, "Initial Visit note finalized attached sccaned document");
				test.log(LogStatus.INFO, "To verify charges updated ");
				AddChargeUtils.NavigateToViewChargePage(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]"), 30);
				// charges AFTER finalize
				String chargeafter = ActionUtils
						.getText(driver.findElement(By.xpath("(//*[@id='viewChargeGrid']//td[21])[last()]")));
				test.log(LogStatus.INFO, "Charges after finalized and sccaned" + chargeafter);
				// Assert.assertNotEquals(chargeafter,chargebefore);
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver, By.xpath("//i[@id='headerCaseInfoIcon']"), 30);
				ActionUtils.click(driver.findElement(By.xpath("//i[@id='headerCaseInfoIcon']")));
				strActualtext = ActionUtils
						.getText(driver.findElement(By.xpath("//*[@id='headerCaseInfoIcon_tt_active']/div[1]")));
				Assert.assertTrue(strActualtext.contains("Total Visit Notes : 4"));
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Follow Up Discharge Note");
				Assert.assertFalse(true, "Could not Could not Validate Data In Follow Up Discharge Note"
						+ Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-5 TC-15
			// Add new case with all required field
			test = extent.startTest(
					"CASE-5:==>>Create Non Visit Discharge note and verify  Sign and Attch Scanned Documents again with same patient",
					"Create Non Visit Discharge note and verify  Sign and Attch Scanned Documents again with same patient"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			ActionUtils.click(AddPatientPage.PatientNameInHeader(driver));
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			Commons.waitforElement(driver, By.id("saveCaseDetailsButton"), 30);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			AddCaseUtils.ClickCaseSave(driver);
			// Create Initial note and finalize
			// CReate NOte
			CreateNoteUtils.NavigatetoCreateNote(driver);
			// select one body part
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(), 'Ankle/Foot')]")));
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Selected']")));
			// hit Create Note
			ActionUtils.click(driver.findElement(By.id("btnCreateNote")));
			Commons.waitforElement(driver, By.xpath("//button[@value='Create New Note']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//button[@value='Create New Note']")));
			Commons.waitforElement(driver, By.linkText("Charge Capture"), 40);
			CreateNoteUtils.clikOnAPageOnSOAP(driver);
			APageUtils.ClickCandidateTherapyNo(driver, "no");
			Commons.waitForLoad(driver);
			// Charge Capture and Finalize Note with Procedure code 97140
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			APageUtils.addProcedureCode(driver, "97140");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
			Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']")));
			ActionUtils.click(driver.findElement(By.xpath("//button[text()='Finalize']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Output Reports')]"), 60);
			test.log(LogStatus.INFO, "Initial Visit note finalized attached sccaned document");
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//i[@id='headerCaseInfoIcon']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//i[@id='headerCaseInfoIcon']")));
			strActualtext = ActionUtils
					.getText(driver.findElement(By.xpath("//*[@id='headerCaseInfoIcon_tt_active']/div[1]")));
			Assert.assertTrue(strActualtext.contains("Total Visit Notes : 1"));
			// ******************
			SPageUtils.creatingNonVisitDischargeNote(driver);
			// *********************case-5
			
			ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//div[contains(@class,'modal-body')]"),
					60);
			Commons.waitforElement(driver, By.xpath("//div[@id='scannedDocumentTable']"), 90);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='scannedDocumentTable']//input[@id='chkSelecAll']")));
			ActionUtils.click(driver.findElement(By.xpath("//button[text()='Finalize']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Output Reports')]"), 60);
			// **************
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					80);
			System.out.println("Finalized Discharge visit Note");
			test.log(LogStatus.INFO, "Finalized Discharge visit Note");
			Commons.screenshot();
			test.log(LogStatus.INFO,
					"To Verify Note finalizes and discharge date entered in note displays in case information.  ");
			AddCaseUtils.GoToCaseList(driver);
			strActualtext = ActionUtils
					.getText(driver.findElement(By.xpath("//div[@id='gridCaseDetails']/table/tbody/tr[1]/td[9]")));
			test.log(LogStatus.INFO, "Discharge date on Case details page is" + strActualtext);
			Assert.assertTrue(strActualtext.contains("06/14/2015"),
					"Could not validate Discharge date on case information Page");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e) + "Test Sign and Finalize failed");
		}
	}
}
