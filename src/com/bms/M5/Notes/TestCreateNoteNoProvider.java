package com.bms.M5.Notes;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCreateNoteNoProvider extends TestSetUp {
	public String strActualtext;
	CreateNoteUtils callto = new CreateNoteUtils();
	AddScheduleUtils call = new AddScheduleUtils();

	@Test(enabled = true, priority = 1)
	public void UserdontHaveRightToCreateNote() throws InterruptedException {
		test = extent.startTest(
				"To Validate create note button unavailable for users NOT linked to a provider - subsequent note on case - where previous note created in R5",
				"create note button unavailable for users NOT linked to a provider" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider101"),
				Commons.readPropertyValue("password"));
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																									// Start
																									// Of
																									// CARE
																									// DATE
		HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
		ActionUtils.click(CreateNotePage.AddAll(driver, "bodyparts"));
		Commons.waitForLoad(driver);
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			String msg = Commons.capturemessage(driver, 20);
			Assert.assertTrue(msg.contains("You are not allowed to create new notes"), "Toast messages not matched");
			test.log(LogStatus.INFO, "Message Verified:=>" + "      " + msg);
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: CreateNote With user not linked to provider Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void create_note_from_provider_home_page() throws InterruptedException {
		test = extent.startTest("To Validate user can create a note from provider Home page",
				"Create Note from Provider Home page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		/*
		 * Commons.logout(driver); Commons.logintoRevflow(driver,
		 * "AutomationBMSProvider", "Qwerty@123");
		 */
		Boolean present;
		try {
			Commons.waitforElement(driver, By.xpath("//span[@class='clinicalNoteStatus note-red']"), 20);
			present = true;
			System.out.println("Found Red button on Home Page");
		} catch (Exception e) {
			System.out.println("did not get  Red button on Home Page");
			present = false;
		}
		if (present == false) {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, "AutomationBMSProvider", Commons.readPropertyValue("password"));
			String strActualtext = null;
			strActualtext = call.ScheduleAppointment(driver);
			System.out.println("Quick Scheduled appointment" + strActualtext);
			Commons.waitforElement(driver, By.xpath("//a[contains(.,'Home')]"), 40);
			driver.findElement(By.xpath("//a[contains(.,'Home')]")).click();
			System.out.println("Clicked on Provider Home");
			Commons.waitForLoad(driver);
		}
		Commons.waitForElementToBeClickable(driver, CreateNotePage.RedButton(driver), 30);
		Commons.waitForLoad(driver);
		ActionUtils.click(CreateNotePage.RedButton(driver));
		System.out.println("Clicked on Red button on Provider Home page");
		Commons.waitforElement(driver, By.xpath("//select[@name='ddlVisitType']"), 40);
		driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
		Commons.Explicitwait();
		if (Commons.existsElement(driver,
				By.xpath("//select[@name='ddlVisitType']//option[@label='Evaluation/Transfer']"))) {
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
			Commons.Explicitwait();
		} else {
			System.out.println("User already completed Initial Visit");
			System.out.println("Selecting Followup visit from dropdown");
			Commons.Explicitwait();
			driver.findElement(By.xpath("//select[@name='ddlVisitType']")).click();
			CreateNotePage.VisitTypeddl(driver, "Followup Visit");
		}
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
		// ActionUtils.click(CreateNotePage.AddAll(driver, "PublicTemplates"));
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		System.out.println("Initial-Visit Note crated Successfully");
	}

	@Test(enabled = true, priority = 3)
	public void ValidateBodyPartsList() throws InterruptedException {
		test = extent.startTest("To Validate available Available body parts = global constants",
				"Test Validate Available body parts = global constants" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		try {
			Commons.Explicitwait();
			System.out.println(CreateNotePage.AvailableBodyParts(driver).size());
			test.log(LogStatus.INFO,
					"Available Body Parts in Application==>> " + CreateNotePage.AvailableBodyParts(driver).size());
			String[] expected = { "Ankle Sprain", "Ankle/Foot", "Cardiopulmonary", "Cervical Spine", "Elbow/Forearm",
					"Frozen Shoulder", "Hip Pain Non-OA", "Hip Pain OA", "Jaw", "Knee", "Knee Pain (Cart)",
					"Knee Sprain", "Low Back Pain", "Lumbar Spine", "Lymphedema", "Neck Pain", "Neuro", "Pediatric",
					"Pelvis/Hip", "Shoulder", "Speech", "Thoracic Spine", "Urogenital", "Wound", "Wrist/Hand" };
			List<WebElement> allOptions = CreateNotePage.AvailableBodyParts(driver);
			// make sure you found the right number of elements
			if (expected.length != allOptions.size()) {
				System.out.println("fail, wrong number of body parts found");
				Assert.assertFalse(true, "test failed, wrong number of body parts found");
			}
			// make sure that the value of every <option> element equals the
			// expected value
			test.log(LogStatus.INFO, "Validating available Body parts in Application are as Expected");
			for (int i = 0; i < expected.length; i++) {
				String optionValue = allOptions.get(i).getText().trim();
				if (optionValue.equals(expected[i])) {
					System.out.println("passed on: " + optionValue);
					test.log(LogStatus.INFO, "passed on: " + optionValue);
				} else {
					System.out.println("failed on: " + optionValue);
					test.log(LogStatus.INFO, "failed on: " + optionValue);
					Assert.assertFalse(true, "failed on: " + optionValue);
				}
			}
			test.log(LogStatus.INFO, "Total available Body parts are:>>" + allOptions.size());
			test.log(LogStatus.INFO, "Test::::Validate Available body Parts Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate Available body Parts Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
