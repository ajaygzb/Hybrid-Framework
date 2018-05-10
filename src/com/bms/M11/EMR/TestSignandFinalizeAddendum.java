package com.bms.M11.EMR;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import Utils.SPageUtils;

public class TestSignandFinalizeAddendum extends TestSetUp {
	public String strActualtext = null;
	public List<WebElement> actual = null;

	// TC-14 Sign and Finalize - Non Visit Discharge
	// TC-16 Sign and Finalize Addendum
	// TC-11 Sign and Finalize - Non Visit (incomplete case)
	// TC-17 Sign and Finalize note with Assessment is "Not a candidate for
	// therapy" required fields override
	// TC-18 Previous note not finalized
	@Test(enabled = true, priority = 2)
	public void SignandFinalizeNoteandAddAddendum() throws InterruptedException {
		test = extent.startTest("To verify Add addendum with new data and charges will show updated charges and DOS",
				"To verify Add addendum with new data and charges will show updated charges and DOS "
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Navigated to A PAge");
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation FLRDataSubsequentNote");
		APageUtils.FinalizeNote(driver);
		AddChargeUtils.NavigateToViewChargePage(driver);
		Commons.waitforElement(driver, By.xpath("//div[@id='viewChargeGrid']/table/tfoot/tr/td[21]"), 30);
		// charges before adding addendum
		String chargebefore = driver.findElement(By.xpath("//div[@id='viewChargeGrid']/table/tfoot/tr/td[21]"))
				.getText().trim();
		test.log(LogStatus.INFO, "Charges Before Adding Addendum" + chargebefore);
		String DOSbefore = driver.findElement(By.xpath("//div[@id='viewChargeGrid']/table/tbody/tr[1]/td[4]")).getText()
				.trim();
		test.log(LogStatus.INFO, "Date of service Before Adding Addendum" + DOSbefore);
		// Add addendum and Update the Date of Service on the Note Details page.
		test.log(LogStatus.INFO, "Add addendum and Update the Date of Service on the Note Details page.");
		APageUtils.GotoNoteList(driver, 1);
		// Add Addendum
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//input[@value='Add Addendum']")));
		System.out.println("Clicked on Add addendum button");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@value='Create Note']"), 50);
		// update DOS
		HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "16/06/2015");
		Commons.scrollElementinotView(driver.findElement(By.xpath("//textarea[@name='addendumStatement']")), driver);
		ActionUtils.sendKeys(driver.findElement(By.xpath("//textarea[@name='addendumStatement']")),
				"Test Addendum statement Initial Note");
		Commons.waitforElement(driver, By.xpath("//input[@value='Create Note']"), 30);
		Commons.waitForLoad(driver);
		Commons.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//input[@value='Create Note']")), 30);
		ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//input[@value='Create Note']")));
		System.out.println("Clicked on Create Note button for addendum");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[contains(.,'Charge Capture')]"), 80);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		APageUtils.addProcedureCode(driver, "97140");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		// finalize Addendum
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		// To Check Note had linked Library Items auto loaded pop up
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 15)) {
			System.out.println(
					driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
		}
		Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
				80);
		System.out.println("Finalizing Note...");
		test.log(LogStatus.INFO, "Finalized Addendum.");
		Commons.screenshot();
		try {
			// To verify The charges from the original note are no longer
			// active, and the new charges with the new date of service display.
			test.log(LogStatus.INFO,
					"To verify The charges from the original note are no longer active, and the new charges with the new date of service display. ");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='viewChargeGrid']/table/tfoot/tr/td[21]"), 30);
			// charges AFTER adding addendum
			String chargeafter = driver.findElement(By.xpath("//div[@id='viewChargeGrid']/table/tfoot/tr/td[21]"))
					.getText().trim();
			test.log(LogStatus.INFO, "Charges Before Adding Addendum" + chargeafter);
			String DOSafter = driver.findElement(By.xpath("//div[@id='viewChargeGrid']/table/tbody/tr[3]/td[4]"))
					.getText().trim();
			test.log(LogStatus.INFO, "Date of service Before Adding Addendum" + DOSafter);
			Assert.assertNotEquals(chargeafter, chargebefore);
			Assert.assertNotEquals(DOSafter, DOSbefore);
			// Assertion -2
			test.log(LogStatus.INFO,
					"To Verify Patient header does not count the addendum as a new visit, so the numbers should be the same before and after the note is finalized");
			System.out.println("Verifying Adding Addendum is not considered as a Visit");
			System.out.println("Reading Patient data information from Header");
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//i[@id='headerCaseInfoIcon']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//i[@id='headerCaseInfoIcon']")));
			strActualtext = ActionUtils
					.getText(driver.findElement(By.xpath("//*[@id='headerCaseInfoIcon_tt_active']/div[1]")));
			Assert.assertTrue(strActualtext.contains("Total Visit Notes : 1"));
			// TC-14
			SPageUtils.creatingNonVisitDischargeNote(driver);
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
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
			// TC-11
			SPageUtils.creatingNonVisitNote(driver);
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					80);
			System.out.println("Finalized Discharge visit Note");
			test.log(LogStatus.INFO, "Finalized Discharge visit Note");
			Commons.screenshot();
			// To verify The charges from the original note are not changed
			// after non visit note finalize.
			test.log(LogStatus.INFO,
					"To verify The charges from the original note are not changed after non visit note finalize.");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='viewChargeGrid']/table/tfoot/tr/td[21]"), 30);
			// charges AFTER Non Visit note
			String chargeafterNonvisit = driver
					.findElement(By.xpath("//div[@id='viewChargeGrid']/table/tfoot/tr/td[21]")).getText().trim();
			test.log(LogStatus.INFO, "Charges Before Adding Addendum" + chargeafterNonvisit);
			Assert.assertEquals(chargeafter, chargeafterNonvisit);
			System.out.println("*********Assertion Pass************");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Test Sign and Finalize Addendum failed");
			Assert.assertFalse(true, "Test Sign and Finalize Addendum failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 1)
	public void SignandFinalizeOverriderequiredfiels() throws InterruptedException {
		test = extent.startTest(
				"To verify Sign and Finalize note with Assessment is Not a candidate for therapy required fields override",
				"Test Sign and Finalize note with Assessment is Not a candidate for therapy required fields override"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "To Validate Alert Message when Do NOT enter any minutes and click Charge Capture");
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//button[contains(.,'Validate Charges')]"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		test.log(LogStatus.INFO, "Clicked on Validate Charge button");
		Commons.waitforElement(driver, By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//form"), 30);
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
			strActualtext = driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//form")).getText()
					.trim();
			test.log(LogStatus.INFO, "Warning message displayed for no charge");
			Assert.assertEquals(strActualtext,
					"This note includes no charges. This will complete the note. Are you sure you want to complete the note at this time?");
			Commons.screenshot();
		}
		System.out.println("Closing warning pop up");
		ActionUtils
				.click(driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		strActualtext = Commons.capturemessage(driver, 20);
		Assert.assertEquals(strActualtext, "You must enter treatment minutes.");
		System.out.println("Entering Treatment minute and navigate to sign and finalize page");
		test.log(LogStatus.INFO, "Enter Treatment minute and navigate to sing and finalize page");
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "1");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		Commons.waitforElement(driver, By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//form"), 30);
		ActionUtils
				.click(driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		Commons.waitForLoad(driver);
		// Clicking on Sign and Finalize to trigger Hard stop messages
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		// To Check Note had linked Library Items auto loaded pop up
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 20)) {
			System.out.println(
					driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
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
		/*
		 * System.out.println("Waiting for Hard stop messages to trigger....");
		 * Commons.waitForLoad(driver); if(Commons.waitforElement(driver,
		 * By.xpath("//button[contains(@ng-click,'closeErrorPopup')]"),120)){
		 * driver.findElement(By.xpath(
		 * "//button[contains(@ng-click,'closeErrorPopup')]")).click(); }
		 */
		Commons.waitForLoad(driver);
		System.out.println("Setting Candidate for therapy as NO");
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='CandidateTherapy'][@value='2']")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation");
		// set candidate for therapy as no
		APageUtils.createfollowUpNewNote(driver);
		// TC-18
		// Try to Finalize Note when previous note is not finalized
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		APageUtils.addProcedureCode(driver, "97140");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		// finalize Addendum
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		// To Check Note had linked Library Items auto loaded pop up
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 15)) {
			System.out.println(
					driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
		}
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		String hardstopmsg = Commons.capturemessage(driver, 150);
		System.out.println(hardstopmsg);
		try {
			Assert.assertTrue(hardstopmsg.contains("There are previous notes that have not been finalized."),
					"Could not validate hardstop for previous note not finalized");
		} catch (AssertionError e) {
			Commons.screenshot();
		test.log(LogStatus.INFO, "Retry Click on Sign and finalize button");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			String hardstopmsg1 = Commons.capturemessage(driver, 150);
			System.out.println(hardstopmsg1);
			Assert.assertTrue(hardstopmsg1.contains("There are previous notes that have not been finalized."),
					"Could not validate hardstop for previous note not finalized");
		}
	test.log(LogStatus.INFO, "Finalized Addendum.");
		Commons.screenshot();
	}
}
