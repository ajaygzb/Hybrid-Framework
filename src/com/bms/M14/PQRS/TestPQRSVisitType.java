package com.bms.M14.PQRS;

import java.util.List;
import java.util.Random;
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
import Utils.SearchPatientUtils;

public class TestPQRSVisitType extends TestSetUp {
	// TC Initial Visit and PQRS tab
	// TC Follow Up visit and PQRS tab
	// TC Patient AGE between 12 -17 years old
	// TC Patient AGE over 64 years old
	public String strActualtext = null;
	public List<WebElement> actual = null;

	@Test(enabled = true, priority = 1)
	public void PQRSMeasuresBasedOnVisitType() throws InterruptedException {
		test = extent.startTest(
				"Validate PQRS Measures for Initial Visit with OT provider while the patient age is over 65 ",
				"Validate PQRS Measures for Initial Visit with OT provider while the patient age is over 65 "
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userOT"), Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Login as OT provider");
		try {
			strActualtext = EMRUtils.AddPatientPQRS(driver, "PQRS65" + new Random().nextInt(9999999), "14/05/1952", ""); 
			System.out.println("Added a Patient with all fields and Complete case with insurance flagged for PQRS");
			test.log(LogStatus.INFO,
					"Added a Patient with all fields and Complete case with insurance flagged for PQRS");
			test.log(LogStatus.INFO,
					"To Validate Alert Message when Do NOT enter any minutes and click Charge Capture");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			// ********************CASE-1: Validate Initial Visit PQRS tab
			// available with ONLY the following measures
			// displayed**************
			// 128,130,131,134,154,155,181,182,226,431
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 10);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected4 = { "128", "130", "131", "134", "154", "155", "181", "182", "226", "431" };
			for (int i = 0; i < 10; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected4[i]));
			}
			System.out.println("************Assertion-1 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			System.out.println("Test Initial Visit and PQRS tab is Completed as Pass");
			// *********Case-2 Follow up visit
			test = extent.startTest(
					"Validate PQRS Measures for Follow up Visit with OT provider while the patient age is over 65 ",
					"Validate PQRS Measures for Follow up Visit with OT provider while the patient age is over 65 "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			APageUtils.createfollowUpNewNote(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 10);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected2 = { "128", "130", "131", "134", "154", "155", "181", "182", "226", "431" };
			for (int i = 0; i < 10; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected2[i]));
			}
			System.out.println("************Assertion-2 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			System.out.println("Test Follow Up Visit and PQRS tab is Completed as Pass");
			// *********Case-3 Re-evaluation Visit Note
			test = extent.startTest(
					"Validate PQRS Measures for Re-evaluation Visit Note with OT provider while the patient age is over 65 ",
					"Validate PQRS Measures for Re-evaluation Visit Note with OT provider while the patient age is over 65 "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			SPageUtils.creatingfollowupReEvaluationNote(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 10);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected3 = { "128", "130", "131", "134", "154", "155", "181", "182", "226", "431" };
			for (int i = 0; i < 10; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected3[i]));
			}
			System.out.println("************Assertion-3 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			System.out.println("Test Re-Eval Notes visit and PQRS tab is Completed as Pass");
			// *********Case-4 Discharge Visit Note
			test = extent.startTest(
					"Validate PQRS Measures for Discharge Visit Note with OT provider while the patient age is over 65 ",
					"Validate PQRS Measures for Re-evaluation Visit Note with OT provider while the patient age is over 65 "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			SPageUtils.creatingfollowupDischargeNote(driver);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 10);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected = { "128", "130", "131", "134", "154", "155", "181", "182", "226", "431" };
			for (int i = 0; i < 10; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected[i]));
			}
			System.out.println("************Assertion-4 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			System.out.println("Test Discharge Notes visit and PQRS tab is Completed as Pass");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e) + "Test PQRS Visit type and PQRS tab Failed");
		}
	}

	// *******************************************AGE
	// RESTRICTION**********Patient AGE between 12 -17 years
	// old****************** *
	@Test(enabled = true, priority = 2)
	public void PQRSMeasuresBasedOnAge_17() throws InterruptedException {
		test = extent.startTest("Validate PQRS Measures with OT provider while the patient age is 12 ",
				"Validate PQRS Measures with OT provider while the patient age is 12 " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userOT"), Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Login as OT provider");
		try {
			strActualtext = EMRUtils.AddPatientPQRS(driver, "PQRS65" + new Random().nextInt(9999999), "14/05/2005", ""); // Added
																															// a
																															// Patient
																															// with
																															// all
																															// fields
																															// and
																															// Complete
																															// case
																															// with
																															// insurance
																															// Medicare
																															// FLR
			System.out.println("Added a Patient with all fields and Complete case with insurance flagged for PQRS");
			test.log(LogStatus.INFO,
					"Added a Patient with all fields and Complete case with insurance flagged for PQRS");
			Commons.waitForLoad(driver);
			EMRUtils.AddDataSpage(driver);
			test.log(LogStatus.INFO, "Added Data on S Page");
			EMRUtils.AddDataApage(driver);
			test.log(LogStatus.INFO, "Added Data on A Page");
			EMRUtils.AddDataPpage(driver);
			test.log(LogStatus.INFO, "Added Data on P Page");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			System.out.println("Navigated to P page");
			test.log(LogStatus.INFO, "Navigated to P page");
			Commons.screenshot();
			// ********************CASE-1: Validate PQRS tab available with ONLY
			// the following measures displayed**************
			// 134
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 1);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected = { "134" };
			for (int i = 0; i < 1; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected[i]));
			}
			System.out.println("************Assertion-1 Pass*********");
			Commons.screenshot();
			// extent.endTest(test);
			System.out.println("Test Validate PQRS measures when patient age is 12, Completed as Pass");
			// Charge Capture and Finalize Note with Procedure code 97001
			test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code 97001");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 160);
			// *[@id='autosearchProcedureCodesChargeCaptureChargeCapture']//input
			APageUtils.addProcedureCode(driver, "97001");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "100");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"), 70)) {
				System.out.println(
						"Warning Pop up appeared: You have not charged to the maximum number of units based on the number of one-on-one minutes documented.  Are you sure you want to complete the charges? ");
				Commons.screenshot();
				driver.findElement(By.xpath("//*[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']"))
						.click();
			}
			ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(driver));
			Commons.waitForLoad(driver);
			// To Check Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 10)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			if (Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//div[contains(@class,'modal-body')]"),
					60)) {
				System.out.println("No hard stop requiring PQRS measure if 97001 is entered");
				test.log(LogStatus.INFO, "No hard stop requiring PQRS measure if 97001 is entered");
				Commons.screenshot();
				ActionUtils.click(driver.findElement(
						By.xpath("//*[@id='SignAttachScannedDocuments']//button[contains(text(),'Cancel')]")));
			}
			// Case-2***********************
			/*
			 * //goto Charge capture and change proc. code to 97004
			 * test.log(LogStatus.INFO,
			 * "Charge Capture and Finalize Note with Procedure code 97004");
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * "//a[contains(text(),'Charge Capture')]")));
			 * Commons.waitForLoad(driver); Commons.waitforElement(driver,
			 * By.xpath(
			 * "//*[@id='autosearchProcedureCodesChargeCapture']//input"),60);
			 * //closing error pop up //Commons.ExistandDisplayElement(driver,
			 * By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"),50);
			 * // ActionUtils.click(driver.findElement(By.xpath(
			 * "//button[contains(@ng-click,'closeErrorPopup')]"))); //Remove
			 * Existing Proc code Thread.sleep(5000);
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * "(//input[contains(@ng-click,'selectAllProcedureCodes(list,isCheck)')])[1]"
			 * ))); Commons.waitforElement(driver, By.xpath(
			 * "//button[contains(text(),'Delete Procedure Code')]"),30);
			 * driver.findElement(By.xpath(
			 * "//button[contains(text(),'Delete Procedure Code')]")).click();
			 * Commons.waitForLoad(driver); //Add Proc code 97004 //
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='autosearchProcedureCodesChargeCapture']//input")));
			 * APageUtils.addProcedureCode(driver,"97004");
			 * Commons.waitForLoad(driver); Commons.waitforElement(driver,
			 * By.xpath("//input[@name='handOnTreatmentMinutes']"),30);
			 * ActionUtils.clear(driver.findElement(By.xpath(
			 * "//input[@name='handOnTreatmentMinutes']")));
			 * ActionUtils.sendKeys(driver.findElement(By.xpath(
			 * "//input[@name='handOnTreatmentMinutes']")), "15");
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * "//button[contains(.,'Validate Charges')]")));
			 * System.out.println("Clicked on Validate Charge button");
			 * Commons.waitforElement(driver, By.xpath(
			 * "//button[contains(text(),'Sign and Finalize')]"),80);
			 */
			/*
			 * // Verify No Hard stop with proc ocde 97004
			 * ActionUtils.click(CreateNotePage.SignandAttachScannedDocButton(
			 * driver)); Commons.waitForLoad(driver); //To Check Note had linked
			 * Library Items auto loaded pop up
			 * if(Commons.ExistandDisplayElement(driver, By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"
			 * ),5)){ System.out.println(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).
			 * getText()); ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='examTemplateMessagePopUp']//input[@value='OK']"))); }
			 * if(Commons.waitforElement(driver,By.xpath(
			 * "//div[@id='SignAttachScannedDocuments']//div[@class='modal-content']//div[contains(@class,'modal-body')]"
			 * ),60)){ System.out.println(
			 * "No hard stop requiring PQRS measure if 97004 is entered");
			 * TestSetUp.test.log(LogStatus.INFO,
			 * "No hard stop requiring PQRS measure if 97004 is entered");
			 * Commons.screenshot();
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='SignAttachScannedDocuments']//button[contains(text(),'Cancel')]"
			 * ))); }
			 */
			// Case-3***********************
			// goto Charge capture and change proc. code to 97003
			test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code 97003");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			// closing error pop up
			// Commons.ExistandDisplayElement(driver,
			// By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"),50);
			// ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
			// Remove Existing Proc code
			ActionUtils.click(driver.findElement(
					By.xpath("(//input[contains(@ng-click,'selectAllProcedureCodes(list,isCheck)')])[1]")));
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Delete Procedure Code')]"), 30);
			driver.findElement(By.xpath("//button[contains(text(),'Delete Procedure Code')]")).click();
			Commons.waitForLoad(driver);
			// Add Proc code 97004
			// ActionUtils.click(driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")));
			APageUtils.addProcedureCode(driver, "97003");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 80);
			// Verify Hard stop with proc ocde 97003
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"), 60);
			System.out.println("Triggred Hard Stop message");
			test.log(LogStatus.INFO, "Triggred Hard Stop message");
			Commons.screenshot();
			System.out.println("Asserting Hard stop messages");
			// Asserting Error messages on Sing and Finalize screen
			actual = driver.findElements(By.xpath("//*[@id='errorGridId']//tbody/tr"));
			// actual = actual.subList(0,6);
			Assert.assertEquals(actual.size(), 1);
			System.out.println("No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "Validating hard Stop messages text");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected3 = {
					"When billing 97003, 97165, 97166, or 97167 PQRS measure 134 - Clinical Depression must be reported." };
			for (int i = 0; i < 1; i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected3[i]));
			}
			System.out.println("************Assertion-3 Pass*********");
			Commons.screenshot();
			// closing error pop up
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e)
					+ "Test PQRS Measures when Patient AGE between 12 -17 years old");
		}
	}

	// *******************************************AGE
	// RESTRICTION**********Patient AGE over 64 years old****************** *
	@Test(enabled = true, priority = 3)
	public void PQRSpromptHardStop_Age_Over_64() throws InterruptedException {
		test = extent.startTest(
				"Validate PQRS Measures and Hard Stop Messages while Patient AGE over 64 years old with OT provider",
				"Validate PQRS Measures and Hard Stop Messages Patient AGE over 64 years old "
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			strActualtext = EMRUtils.AddPatientPQRS(driver, "PQRS65" + new Random().nextInt(9999999), "14/05/1952", "");
			// Added a Patient with all fields and Complete case with insurance
			// Medicare FLR
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
			// ******************TC-20*********************************
			// ********************CASE-1: Validate PQRS tab available with ONLY
			// the following measures displayed**************
			// 128,130,131,134,182,226,431
			// 2114594
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"),
					60);
			actual = driver.findElements(By.xpath(
					"//div[@id='PQRS']//div[contains(@class,'relative PQRS')]//div[contains(@class,'panel-heading')]"));
			Assert.assertEquals(actual.size(), 10);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = { "128", "130", "131", "134", "154", "155", "181", "182", "226", "431" };
			for (int i = 0; i < 10; i++) {
				Assert.assertTrue(actual.get(i).getText().trim().contains(expected1[i]));
			}
			Commons.screenshot();
			System.out.println("************Assertion-1 Pass*********");
			// Charge Capture and Finalize Note with Procedure code 97003
			test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code 97003");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			APageUtils.addProcedureCode(driver, "97003");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 80);
			// CASE-2
			// go to charge capture > procedure code entry, enter 97003,
			// completed charge capture and click "Sign and finalize" on sign
			// note tab
			try {
				driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")).click();
			} catch (Exception e) {
			}
			Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"), 40);
			System.out.println("Triggred Hard Stop message");
			test.log(LogStatus.INFO, "Triggred 9 Hard Stop message");
			Commons.screenshot();
			System.out.println("Asserting Hard stop messages");
			// Asserting 7 Error messages on Sing and Finalize screen
			actual = driver.findElements(By.xpath("//*[@id='errorGridId']//tbody/tr"));
			// actual = actual.subList(0,6);
			Assert.assertEquals(actual.size(), 9);
			System.out.println("No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "Validating hard Stop messages text");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected2 = {
					"When billing 97001, 97003, 97161, 97162, 97163, 97165, 97166, or 97167 PQRS measure 128 - BMI must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, 97168, 97532, 92507, 92508, 92526, or 92626 PQRS measure 130 - Medications must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, 97168, 97532, 92507, 92508, or 92526 PQRS measure 131 - Pain must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, or 97168 PQRS measure 154 - Fall Risk Assessment must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, or 97168 PQRS measure 182 - Functional Outcome Assessment must be reported",
					"When billing 97003, 97165, 97166, or 97167 PQRS measure 181 - Elder Maltreatment Screen must be reported.",
					"When billing 97003, 97004, 97165, 97166, 97167, 97168, 92521, 92522, 92523, or 92524 PQRS measure 226 - Tobacco Use must be reported.",
					"When billing 97003, 97004, 97165, 97166, 97167, or 97168 PQRS measure 431 - Unhealthy Alcohol Use: Screening & Brief Counseling must be reported for this patient.",
					"When billing 97003, 97165, 97166, or 97167 PQRS measure 134 - Clinical Depression must be reported.", };
			for (int i = 0; i < 9; i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected2[i]));
			}
			System.out.println("************Assertion-2 Pass*********");
			Commons.screenshot();
			// closing error pop up
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
			// CASE-3
			// goto Charge capture and change proc. code to 97001
			test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code 97001");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			// closing error pop up
			// Commons.ExistandDisplayElement(driver,
			// By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"),50);
			// ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
			// Remove Existing Proc code
			ActionUtils.click(driver.findElement(
					By.xpath("(//input[contains(@ng-click,'selectAllProcedureCodes(list,isCheck)')])[1]")));
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Delete Procedure Code')]"), 30);
			driver.findElement(By.xpath("//button[contains(text(),'Delete Procedure Code')]")).click();
			Commons.waitForLoad(driver);
			// Add Proc code 97001
			// ActionUtils.click(driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")));
			APageUtils.addProcedureCode(driver, "97001");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 80);
			try {
				driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")).click();
			} catch (Exception e) {
			}
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"), 60);
			System.out.println("Triggred Hard Stop message");
			test.log(LogStatus.INFO, "Triggred 5 Hard Stop message");
			Commons.screenshot();
			System.out.println("Asserting Hard stop messages");
			// Asserting 4 Error messages on Sing and Finalize screen
			actual = driver.findElements(By.xpath("//*[@id='errorGridId']//tbody/tr"));
			// actual = actual.subList(0,6);
			Assert.assertEquals(actual.size(), 5);
			System.out.println("No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "No of Error messages found:" + actual.size());
			test.log(LogStatus.INFO, "Validating hard Stop messages text");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected3 = {
					"When billing 97001, 97003, 97161, 97162, 97163, 97165, 97166, or 97167 PQRS measure 128 - BMI must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, 97168, 97532, 92507, 92508, 92526, or 92626 PQRS measure 130 - Medications must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, 97168, 97532, 92507, 92508, or 92526 PQRS measure 131 - Pain must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, or 97168 PQRS measure 154 - Fall Risk Assessment must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, or 97168 PQRS measure 182 - Functional Outcome Assessment must be reported", };
			for (int i = 0; i < 5; i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected3[i]));
			}
			System.out.println("************Assertion-3 Pass*********");
			Commons.screenshot();
			// closing error pop up
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e) + "Test Sign and Finalize failed");
		}
	}
}
