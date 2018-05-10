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
import Utils.HandlingCalendars;
import Utils.SPageUtils;

public class TestPQRSHardstop_2 extends TestSetUp {
	// TC-Patient AGE over 64 years old
	public String strActualtext = null;
	public List<WebElement> actual = null;

	// **************************************Verify Hard stop,PQRS measures,View
	// Past data when patient age over 64 years old
	@Test(enabled = true, priority = 1)
	public void PQRS_HardStop_ViewPastData_64() throws InterruptedException {
		test = extent.startTest(
				"Validate PQRS Measures and Hard Stop Messages and View Past data while creaing initial Note with PT provider",
				"Validate PQRS Measures and Hard Stop Messages and View Past data  " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
			strActualtext = EMRUtils.AddPatientPQRS(driver, "PQRS65" + new Random().nextInt(9999999), "14/05/1952", ""); // Added
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
			Assert.assertEquals(actual.size(), 8);
			System.out.println("PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "PQRS Measures found:" + actual.size());
			test.log(LogStatus.INFO, "Validating PQRS Measures Codes");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = { "126", "127", "128", "130", "131", "154", "155", "182" };
			for (int i = 0; i < 8; i++) {
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
			String[] expected2 = {
					"When billing 97001, 97003, 97161, 97162, 97163, 97165, 97166, or 97167 PQRS measure 128 - BMI must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, 97168, 97532, 92507, 92508, 92526, or 92626 PQRS measure 130 - Medications must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, 97168, 97532, 92507, 92508, or 92526 PQRS measure 131 - Pain must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, or 97168 PQRS measure 154 - Fall Risk Assessment must be reported.",
					"When billing 97001, 97002, 97161, 97162, 97163, 97164, 97003, 97004, 97165, 97166, 97167, or 97168 PQRS measure 182 - Functional Outcome Assessment must be reported", };
			for (int i = 0; i < 5; i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected2[i]));
			}
			System.out.println("************Assertion-2 Pass*********");
			Commons.screenshot();
			// closing error pop up
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			test.log(LogStatus.INFO, "Delete Existing proc code then Sign and finalize Note");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			// Remove Existing Proc code
			ActionUtils.click(driver.findElement(
					By.xpath("(//input[contains(@ng-click,'selectAllProcedureCodes(list,isCheck)')])[1]")));
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Delete Procedure Code')]"), 30);
			driver.findElement(By.xpath("//button[contains(text(),'Delete Procedure Code')]")).click();
			Commons.waitForLoad(driver);
			// APageUtils.addProcedureCode(driver,"97003");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 380);
			// Add Addendum and go to PQRS and add G-codes
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Addendum']")));
			System.out.println("Clicked on Add addendum button");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@value='Create Note']"), 50);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//textarea[@name='addendumStatement']")),
					driver);
			ActionUtils.sendKeys(driver.findElement(By.xpath("//textarea[@name='addendumStatement']")),
					"Test PQRS G codes");
			Commons.waitforElement(driver, By.xpath("//input[@value='Create Note']"), 30);
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//input[@value='Create Note']")),
					30);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Create Note']")));
			System.out.println("Clicked on Create Note button for addendum");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(.,'Charge Capture')]"), 80);
			// Navigate to PQRS
			// 2130104
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 140);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			System.out.println("Navigated to P page");
			// Add G codes to PQRS measures.
			// 2129659
			Commons.waitforElement(driver,
					By.xpath("(//div[@id='PQRS']//span[contains(@class,'glyphicon glyphicon-plus')])[1]"), 30);
			// Add measures 128
			ActionUtils.click(driver.findElement(
					By.xpath("(//div[@id='PQRS']//span[contains(@class,'glyphicon glyphicon-plus')])[3]")));
			Commons.ExistandDisplayElement(driver, By.xpath("//option[@label='G8421 - BMI NOT CALCULATED']"), 10);
			ActionUtils.click(driver.findElement(By.xpath("//option[@label='G8421 - BMI NOT CALCULATED']")));
			// Verify Drop down values in Measures 128
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//option[@label='G8420 - CALCULATED BMI WITHIN NORMAL PARAMETERS AND DOCUMENTED']"), 10));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//option[@label='G8417 - Calculated BMI above normal parameters and a follow-up plan was documented']"),
					10));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//option[@label='G8418 - Calculated BMI below normal parameters and a follow-up plan was documented']"),
					10));
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//option[@label='G8422 - NOT ELIGIBLE FOR BMI']"), 10));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//option[@label='G8938 - BMI IS CALCULATED, BUT PATIENT NOT ELIGIBLE FOR FOLLOW-UP PLAN']"),
					10));
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//option[@label='G8421 - BMI NOT CALCULATED']"), 10));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//option[@label='G8419 - Calculated BMI outside normal parameters, no follow up plan documented']"),
					10));
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='PQRS']//div[contains(text(),'128 - Body Mass Index')]//span[contains(@class,'glyphicon-minus')]")));
			// Add measures 130
			ActionUtils.click(driver.findElement(
					By.xpath("(//div[@id='PQRS']//span[contains(@class,'glyphicon glyphicon-plus')])[4]")));
			Commons.ExistandDisplayElement(driver,
					By.xpath("//option[@label='G8427 - VERIFIED AND DOCUMENTED MEDICATIONS']"), 10);
			ActionUtils.click(
					driver.findElement(By.xpath("//option[@label='G8427 - VERIFIED AND DOCUMENTED MEDICATIONS']")));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//option[@label='G8427 - VERIFIED AND DOCUMENTED MEDICATIONS']"), 10));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//option[@label='G8430 - DOC NOT ELIGIBLE FOR MEDS ASSESSMENT']"), 10));
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//option[@label='G8428 - MEDICATIONS NOT DOCUMENTED (REASON UNSPECIFIED)']"), 10));
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='PQRS']//div[contains(text(),'130')]//span[contains(@class,'glyphicon-minus')]")));
			// Add measures 131
			ActionUtils.click(driver.findElement(
					By.xpath("(//div[@id='PQRS']//span[contains(@class,'glyphicon glyphicon-plus')])[5]")));
			Commons.ExistandDisplayElement(driver,
					By.xpath("//option[@label='G8732 - NO DOCUMENTATION OF PAIN ASSESSMENT, REASON NOT GIVEN']"), 10);
			ActionUtils.click(driver.findElement(
					By.xpath("//option[@label='G8732 - NO DOCUMENTATION OF PAIN ASSESSMENT, REASON NOT GIVEN']")));
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='PQRS']//div[contains(text(),'131')]//span[contains(@class,'glyphicon glyphicon-minus')]")));
			// Add measures 154
			ActionUtils.click(driver.findElement(
					By.xpath("(//div[@id='PQRS']//span[contains(@class,'glyphicon glyphicon-plus')])[6]")));
			Commons.ExistandDisplayElement(driver,
					By.xpath("//option[@label='1101F-8P - FALLS STATUS NOT DOCUMENTED']"), 10);
			ActionUtils
					.click(driver.findElement(By.xpath("//option[@label='1101F-8P - FALLS STATUS NOT DOCUMENTED']")));
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='PQRS']//div[contains(text(),'154')]//span[contains(@class,'glyphicon glyphicon-minus')]")));
			// Add measures 182
			ActionUtils.click(driver.findElement(
					By.xpath("(//div[@id='PQRS']//span[contains(@class,'glyphicon glyphicon-plus')])[8]")));
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//option[@label='G8541 - FUNCTIONAL OUTCOME ASSESSMENT USING A STANDARDIZED TOOL NOT DOCUMENTED, REASON NOT GIVEN']"),
					10);
			ActionUtils.click(driver.findElement(By.xpath(
					"//option[@label='G8541 - FUNCTIONAL OUTCOME ASSESSMENT USING A STANDARDIZED TOOL NOT DOCUMENTED, REASON NOT GIVEN']")));
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='PQRS']//div[contains(text(),'182')]//span[contains(@class,'glyphicon glyphicon-minus')]")));
			// Charge Capture and finalize note
			test.log(LogStatus.INFO, "Charge Capture and Finalize Note with Procedure code 97161");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			APageUtils.addProcedureCode(driver, "97161");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			Commons.waitforElement(driver, By.xpath("//*[@id='EvaluationProcCodeMessagePopUp']//input[@value='Yes']"),
					60);
			Commons.screenshot();
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='EvaluationProcCodeMessagePopUp']//input[@value='Yes']")));
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Sign and Finalize')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"), 380);
			APageUtils.createfollowUpNewNote(driver);
			// go to the pqrs tab, click "display past data"
			// Navigate to PQRS
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToPlan()']"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[contains(text(),'Treatment Schedule')]"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#PQRS']")));
			System.out.println("Navigated to P page");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='PQRS']//button[contains(.,'View Past Data')]")));
			Commons.waitforElement(driver, By.xpath("//th[contains(.,'Test of Measure')]"), 80);
			try {
				// Assert Past data Values
				Assert.assertTrue(
						driver.findElement(By.xpath("//td[2]//label[contains(.,'G8421 - BMI NOT CALCULATED')]"))
								.getText().contains("G8421"));
				Assert.assertTrue(driver
						.findElement(
								By.xpath("//td[2]//label[contains(.,'G8427 - VERIFIED AND DOCUMENTED MEDICATIONS')]"))
						.getText().contains("G8427"));
				Assert.assertTrue(driver
						.findElement(By
								.xpath("//td[2]//label[contains(.,'G8732 - NO DOCUMENTATION OF PAIN ASSESSMENT, REASON NOT GIVEN')]"))
						.getText().contains("G8732"));
			} catch (AssertionError e) {
				Assert.assertFalse(true, "Unable to Verify View past tab data" + Throwables.getStackTraceAsString(e));
			}
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e) + "Test Sign and Finalize failed");
		}
	}
}
