package com.bms.M11.EMR;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import UIMap.SchedulerPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.EMRUtils;
import Utils.FinancialUtils;
import Utils.HandlingCalendars;
public class TestEndtoEndVisit extends TestSetUp {
	public String strActualtext = null;
	public String strText_Actual = null;
	public List<WebElement> actual = null;
	// TC-2 Sign and Finalize - Initial Visit
	@Test(enabled = true, priority = 1)
	public void CompleteFlowInitialVisitWithSOAP() throws InterruptedException {
		test = extent.startTest(
				"Sign and Fianlize Note with Complete SOAP data and validate all Hard stop messages for SOAP",
				"Sign and Fianlize Note with Complete SOAP data and validate all Hard stop messages for SOAP"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login with Valid Provider");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user AutomationBMSProvider");
		test.log(LogStatus.INFO, "Login as user AutomationBMSProvider");
		Commons.logintoRevflow(driver, "AutomationBMSProvider", Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Add Patient With All fields");
		AddPatientUtils.addpatient_withallfields(driver, "TestENDTOEND");
		strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
		AddCaseUtils.GoToAddNewCase(driver);
		AddCaseUtils.AddCaseWithAllRequiredFields(driver,"Case With Required Fields","01/01/2018","01/01/2018","Automation BMS","Automation BMS Provider");
		AddCaseUtils.AddDXCode(driver);
		AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
		AddCaseUtils.ClickCaseSave(driver);
		Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"),200);
		System.out.println("***********************Assertion-1 Pass***************");
		// Schedule An Appointment
		test.log(LogStatus.INFO, "Schedule Appointment for same patient");
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"),160);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"),200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		AddScheduleUtils.GotoAppointmentWindow(driver);
        // use same patient to schedule Appointment
		Thread.sleep(2000);
		Commons.waitforElement(driver,
				By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"), 30);
		System.out.println(driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']")).getAttribute("value"));
		if(!driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
		.getAttribute("value").contains(strText_Actual)){
		driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
				.clear();
		driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
				.sendKeys(strText_Actual);
		Commons.waitforElement(driver, By.xpath("//li[1]//span[contains(.,'" + strText_Actual + "')]"), 40);
		ActionUtils.click(driver.findElement(By.xpath("//li[1]//span[contains(.,'" + strText_Actual + "')]")));
		Thread.sleep(2000);
		// Select Case name
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseDropDown']//span[contains(@class,'k-input')]")));
		Thread.sleep(5000);
		List<WebElement> els1 = driver
				.findElements(By.xpath("//*[@id='cases_listbox']//li[contains(.,'Case With Required Fields')]"));
		System.out.println(els1.size());
		ActionUtils.JavaScriptclick(els1.get(els1.size() - 1));
		Commons.screenshot();
		}
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
		AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
		Commons.strText_Actual = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
				.replaceAll("\\s", "");
		System.out.println("***********************Assertion-2 Pass***************");
		// Make Appointment arrived
		String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
		System.out.println("name is                     ::::::::::::::::::::::" + patientname);
		String namesplit[] = patientname.split(" ");
		String newName = namesplit[0] + " " + namesplit[2];
		Commons.waitforElement(driver, By.xpath("//a[@ng-click='setproviderHomeDate()']//span"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//u[contains(.,'" + newName + "')]"), 30);
		Commons.screenshot();
		test.log(LogStatus.INFO, "Make Appointment as Arrived from Home page");
		Commons.scrollElementinotView(driver.findElement(By.xpath("//u[contains(.,'" + newName + "')]//preceding::td[2]//div//div//span")), driver);
		Thread.sleep(1000);
		ActionUtils.click(driver.findElement(By.xpath("//u[contains(.,'" + newName + "')]//preceding::td[2]//div//div//span")));
		Commons.waitforElement(driver, By.xpath("//span[contains(@class,'window-title')][contains(.,'Appointment ')]"),
				160);
		ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithArrived(driver));
		Thread.sleep(1000);
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Arrived Notes");
		// Select Case name
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseDropDown']//span[contains(@class,'k-input')]")));
		Thread.sleep(2000);
		List<WebElement> els = driver
				.findElements(By.xpath("//*[@id='cases_listbox']//li[contains(.,'Case With Required Fields')]"));
		System.out.println(els.size());
		ActionUtils.JavaScriptclick(els.get(els.size() - 1));
		Commons.screenshot();
		AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
		Commons.waitforElement(driver,
				By.xpath("//u[contains(.,'" + newName + "')]//following::td[contains(.,'ARRIVED')][1]"), 60);
		Commons.screenshot();
		System.out.println("***********************Assertion-3 Pass***************");
		// Create Note Initial Visit
		CreateNoteUtils.NavigatetoCreateNote(driver);
	       System.out.println("Navigated to Create Notes");
		try {
		System.out.println("Setting Visit Type as Initial Visit");
		Commons.waitForLoad(driver);
		CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
		if (Commons.existsElement(driver,By.xpath("//span[contains(text(),'Ankle Sprain')]//preceding::input[1]"))) {
	ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'Ankle Sprain')]//preceding::input[1]")));
	ActionUtils.click(driver.findElement(By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[contains(@value,'Add Selected')]")));
				} else {
	ActionUtils.click(driver.findElement(By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[contains(@value,'Add All')]")));
				}
		
		
		HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2017");// Enter
		// Start
		// Of
		// CARE
		// DATE
HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2017");
				Commons.waitForElementToBeClickable(driver, CreateNotePage.CreateNoteButton(driver),30);
				ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
				System.out.println("Initial-Visit Note crated Successfully");
			} catch (AssertionError e) {
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 30);
			System.out.println("Confirmation pop up appeared");
			Commons.screenshot();
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			
		}
		// driver.navigate().refresh();
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 250);
		System.out.println("Initial-Visit Note crated Successfully");
		System.out.println("Created a New Note for Initial Visit");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");		
		Commons.waitForLoad(driver);
		System.out.println("Validating that FLR Link Appear on A Page..");
		Commons.waitforElement(driver, By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"), 60);
		APageUtils.click(driver, "ClickMedicareFLRLink");
		Commons.waitForLoad(driver);
		Commons.screenshot();
		// Validate Hard stops on SOAP pages
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
		ActionUtils
				.click(driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		Commons.waitForLoad(driver);
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"),140);
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
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Could not Validate Hard Stop Messages on Sign and Finalize page");
			Assert.assertFalse(true, "Could not Validate Hard Stop Messages on Sign and Finalize page"
					+ Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "Goto A page and Add A page Data");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAssesmentAndPrognosislink");
		Commons.waitForLoad(driver);
		System.out.println("Setting Candidate for therapy as YES");
		ActionUtils.click(driver.findElement(By.xpath("//input[@id='therapyradio']")));
		Commons.screenshot();
		Commons.waitForLoad(driver);
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation");
		APageUtils.click(driver, "clickAssesmentAndPrognosislink");
		Commons.screenshot();
		//ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
		//Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"),140);
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"),180);
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
		Commons.waitforElement(driver, By.xpath("//a[contains(.,'Patient Report')]"), 40);
		System.out.println("Navigated to Spage");
		Commons.waitForLoad(driver);
		EMRUtils.AddDataSpage(driver);
		// Asserting Hard stop after S page Data Added
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
		Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Sign and Finalize')]"), 40);
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
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
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
		System.out.println("Adding Apage Data");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		System.out.println("Successfully redirected to APage");
		Commons.waitForLoad(driver);
		EMRUtils.AddDataApage(driver);
		// Asserting Hard stop after A page Data Added
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitforElement(driver, By.xpath("//*[@id='errorGridId']"),190);
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
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")));
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
		APageUtils.addProcedureCode(driver,"97140");
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
		ActionUtils.click(driver.findElement(By.xpath("//div[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']")));
		Commons.waitForLoad(driver);
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='finalizeKxModifierMessagePopUp']//input[@value='No']"),90);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='finalizeKxModifierMessagePopUp']//input[@value='No']")));
		Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),180);
		System.out.println("Finalizing Note...");
		test.log(LogStatus.INFO, "Finalized Note.");
		Commons.screenshot();
		System.out.println("Verifying Output report Gnenerated and status completed");
		Commons.waitforElement(driver,By.xpath(".//*[@id='outputReportGrid']/table/tbody//span[contains(text(),'Completed')]"),260);
		Assert.assertEquals(driver
				.findElement(By.xpath(".//*[@id='outputReportGrid']/table/tbody//span[contains(text(),'Completed')]"))
				.getText().trim(), "Completed");
		System.out.println("Opening output report");
		test.log(LogStatus.INFO, "Opening output report");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='outputReportGrid']/table/tbody/tr[1]")));
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//*[@id='outputReportGrid']/table/tbody/tr[1]")));
		Commons.scrollElementinotView(driver.findElement(By.xpath("//button[contains(.,'Re-Generate')]")), driver);
		Commons.screenshot();
		// Add $100 charges to patient
		AddChargeUtils.NavigateToAddChargePage(driver);
		test.log(LogStatus.INFO, "Navigate to Add charges page");
		Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
		Commons.waitForLoad(driver);
		test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "01/01/2015");
		HandlingCalendars.datepick(driver, By.xpath("//*[@id='dosCalendarBtn']"), "01/01/2015");
		// AddChargeUtils.AddCPTCode(driver,"97140",1);
		AddChargeUtils.AddCPTCode(driver, "97110", 1);
		test.log(LogStatus.INFO, "Enter Date in Plan of care" + "01/01/2015");
		HandlingCalendars.datepick(driver, By.xpath("//*[@id='planofcaredate']/span/button"), "01/01/2015");
		test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
		ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"), 30)) {
			driver.findElement(By.xpath("//div[@id='KxModifierPopup']//button[@id='btnCloseKxModifierWarning']"))
					.click();
			Commons.capturemessage(driver, 90);
		}
		FinancialUtils.NavigatetoAccountSummary(driver);
		test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
		String BAL_AccountSummary = ActionUtils
				.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
		Assert.assertTrue(BAL_AccountSummary.contains("$100.02"));
		FinancialUtils.NavigatetoLedgerFull(driver);
		test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
		String BAL_Ledgerfull = ActionUtils.getText(
				driver.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
		Assert.assertTrue(BAL_Ledgerfull.contains("$100.02"));
		test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
		FinancialUtils.NavigatetoLedgerVisit(driver);
		String BAL_LedgerVisit = ActionUtils
				.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
		Assert.assertTrue(BAL_LedgerVisit.contains("$100.02"));
		test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary);
		test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull);
		test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit);
		// Make a Payment of $15 and validate Correct amount updated
		AddChargeUtils.NavigateToPaymentPage(driver);
		test.log(LogStatus.INFO, "Navigate to Payments page");
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
		HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
		ActionUtils.click(driver.findElement(By.xpath("//option[@label='PATIENT PD IN OFFICE']")));
		ActionUtils.click(driver.findElement(By.xpath("//option[@label='Cash']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
		Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
		Commons.waitforElement(driver, By.xpath("//div[@id='patientCredits']//table/tbody/tr[1]"), 50);
		System.out.println("Added credits to Patient");
		FinancialUtils.NavigatetoAccountSummary(driver);
		test.log(LogStatus.INFO, "Go to the patient account and select Finanacial and review the Account Summary");
		String BAL_AccountSummary1 = ActionUtils
				.getText(driver.findElement(By.xpath("//tr[contains(.,'Total')]//td[5]")));
		Assert.assertTrue(BAL_AccountSummary1.contains("$85.02"));
		FinancialUtils.NavigatetoLedgerFull(driver);
		test.log(LogStatus.INFO, "Go to the patient's Ledger Full and review the Totals");
		String BAL_Ledgerfull1 = ActionUtils.getText(
				driver.findElement(By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]")));
		Assert.assertTrue(BAL_Ledgerfull1.contains("$85.02"));
		test.log(LogStatus.INFO, "Go to the patients Ledger Visit and review the totals");
		FinancialUtils.NavigatetoLedgerVisit(driver);
		String BAL_LedgerVisit1 = ActionUtils
				.getText(driver.findElement(By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]")));
		Assert.assertTrue(BAL_LedgerVisit1.contains("$85.02"));
		test.log(LogStatus.INFO, "Balance Account Summary=" + BAL_AccountSummary1);
		test.log(LogStatus.INFO, "Balance Ledger Full=" + BAL_Ledgerfull1);
		test.log(LogStatus.INFO, "Balance Ledger Visit=" + BAL_LedgerVisit1);
	     
		
		
		
		// Add Addendum
		
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
		//APageUtils.addProcedureCode(driver, "97140");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "100");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver,
				By.xpath(
						"//div[@id='ValidateWarmUnderBilledMessagePopUp']//div[contains(text(),'You have not charged to the maximum number of units based on the number')]"),
				340);
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//div[@id='ValidateWarmUnderBilledMessagePopUp']//div[contains(text(),'You have not charged to the maximum number of units based on the number')]"))
				.getText().contains("You have not charged to the maximum number of units based on the number"));
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//div[@id='ValidateWarmUnderBilledMessagePopUp']//input[@value='Yes']")));
		Commons.waitForLoad(driver);
		// finalize Addendum
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		Commons.waitForLoad(driver);
		Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
				80);
		System.out.println("Finalizing Note...");
		test.log(LogStatus.INFO, "Finalized Addendum.");
		Commons.screenshot();

	}


}
