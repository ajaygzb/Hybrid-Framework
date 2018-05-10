package com.bms.M6.Scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.APage;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import UIMap.SchedulerPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestProviderHomePagetopMenu extends TestSetUp {
	/*
	 * Test-01 note status changes when note dos is NOT the same as appointment
	 * date Test-02 Provider and POS pulled from appt when creating the note
	 * from the Provider Home page Test-03 POC exp date display FOR insurance
	 * flag DX POC change req as Unchecked Test-04 POC exp date display FOR
	 * insurance flag DX POC change req as CHECKED Test-05 Provide Home Page
	 * Plan of Care Expiration validation Test-06 NotesSinceMedProg display if
	 * insurance is Medicare Test-07 NotesSinceMedProg display if insurance is
	 * not Medicare Test-08 Provide Home Page Notes Since Medical Progress Note
	 * validation Test-09 To validate Non Presence of Alert button when login
	 * from Non-provider
	 */
	@Test(enabled = true, priority = 1)
	public void TestnoteStatusChangesWhenNotedosIsNOTtheSameAsAppointmentdate() {
		test = extent.startTest("To Verify Note status does not change if dos is not same as appoointmnet date",
				"Test Provier Home Page Top menu " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider2"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addScheduleAppointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("SCHEDULED"));
			xpath = "//u[contains(.,'" + newName + "')]/preceding::span[@class='clinicalNoteStatus note-red'][1]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			ActionUtils.click(driver.findElement(By.xpath(xpath)));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.name("ddlVisitType"), 120);
			String date = driver.findElement(By.xpath("//*[@id='DateOfService']/div/input")).getAttribute("value");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate date2 = LocalDate.now();
			Assert.assertTrue(date.contains(date2.format(dtf)));
			System.out.println("dos is " + date);
			System.out.println("changing dos from current date to another date ");
			test.log(LogStatus.INFO, "changing dos from current date to another date ");
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
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "Note status does not change if dos is not same as appoointmnet date");
			test.log(LogStatus.INFO,
					"Test:::: TestnoteStatusChangesWhenNotedosIsNOTtheSameAsAppointmentdate Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestnoteStatusChangesWhenNotedosIsNOTtheSameAsAppointmentdate Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestnoteStatusChangesWhenNotedosIsNOTtheSameAsAppointmentdate Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestPOCexpDateDisplayFORInsuranceFlagDXPOCChangeReqAsUnchecked() {
		test = extent.startTest(
				"to verify poc exp behavior on provider home page and poc expiration red dot with insurance with dx required flag un checked ",
				"Test Provier Home Page Top menu " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date2 = LocalDate.now();
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider2"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient with insurance : dx required uncheck ");
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			String patient_id = ActionUtils.getText(AddPatientPage.patientID(driver));
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[2];
			System.out.println("new name on home page is :" + newName);
			System.out.println("add case");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCode(driver);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, " patient is added with insurance : dx required uncheck ");
			test.log(LogStatus.INFO, "creating appointmnet for above added patient  ");
			System.out.println("Adding appointmnet ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Commons.waitforElement(driver, By.id("clearPatient"), 120);
			driver.findElement(By.id("clearPatient")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='patientName']//input[@placeholder='Patient Search']"))
					.sendKeys(patient_id);
			Commons.waitforElement(driver,
					By.xpath(
							"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span")));
			Commons.waitforElement(driver, By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span"), 120);
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]")));
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "appointment is created for above added patient  ");
			test.log(LogStatus.INFO, "checking status of above created appoinmnet and POC expiration date ");
			System.out.println("navigated to home page");
			test.log(LogStatus.INFO, "navigated to home page");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(10000);
			Commons.waitForLoad(driver);
			System.out.println("checking plan of care expiration value");
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[3]";
			Commons.ExistandDisplayElement(driver, By.xpath(xpath), 5);
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			String planofcareexp = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Plan of care expiration is " + planofcareexp);
			test.log(LogStatus.INFO,
					"status of above created appointment is scheduled and POC exp is " + planofcareexp);
			System.out.println("creating note and adding plan of expiration ");
			test.log(LogStatus.INFO, "creating note and adding plan of expiration to todays date ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//u[contains(.,'" + newName + "')]/preceding::span[@class='clinicalNoteStatus note-red'][1]";
			AddScheduleUtils.CreateInitialNoteFromProviderHomePage(driver, xpath, "10/10/2015");
			test.log(LogStatus.INFO, "Initial not is created and poC exp added is " + "10/10/2015");
			test.log(LogStatus.INFO, "checking poc exp is :  " + "10/10/2015"
					+ " and red dot is added indicating poc expiration is today ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[3]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			planofcareexp = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Plan of care expiration is " + planofcareexp);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/dd/yy");
			System.out.println("date need to be check " + "10/10/15");
			// Assert.assertTrue(date2.format(dtf2).contains(planofcareexp));
			Assert.assertTrue(planofcareexp.equals("10/10/15"));
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/following-sibling::td[3]/span[@class='warning-sign']";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "poc exp on provider home page is :  " + planofcareexp
					+ " and red dot is added indicating poc expiration is today ");
			test.log(LogStatus.INFO,
					"creating addendum for above created note and changing poc of exp to " + "10/10/2020");
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-green']";
			AddScheduleUtils.CreateAddendumNoteFromProviderHomePage(driver, xpath, "10/10/2020");
			test.log(LogStatus.INFO,
					"addendum is created for above created note and poc of exp is changed to " + "10/10/2020");
			test.log(LogStatus.INFO, "checking poc of exp is changed to " + "10/10/2020"
					+ " and red dot is removed as poc is not today or in past");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[3]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			planofcareexp = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Plan of care expiration 2 is " + planofcareexp);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("date need to be check 2 " + "10/10/20");
			// Assert.assertTrue(date2.plusDays(2).format(dtf2).contains(planofcareexp));
			Assert.assertTrue(planofcareexp.equals("10/10/20"));
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/following-sibling::td[3]/span[@class='warning-sign']";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "poc of exp is changed to " + date2.plusDays(2).format(dtf)
					+ " and red dot is removed as poc is not today or in past");
			test.log(LogStatus.INFO,
					"Test:::: TestnoteStatusChangesWhenNotedosIsNOTtheSameAsAppointmentdate Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestPOCexpDateDisplayFORInsuranceFlagDXPOCChangeReqAsUnchecked Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestPOCexpDateDisplayFORInsuranceFlagDXPOCChangeReqAsUnchecked Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestPOCexpDateDisplayFORInsuranceFlagDXPOCChangeReqAsChecked() {
		test = extent.startTest(
				"to verify poc exp behavior on provider home page and poc expiration red dot with insurance with dx required flag checked ",
				"Test Provier Home Page Top menu " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date2 = LocalDate.now();
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, "AutomationProvider2", Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient with insurance : dx required uncheck ");
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			String patient_id = ActionUtils.getText(AddPatientPage.patientID(driver));
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[2];
			System.out.println("new name on home page is :" + newName);
			System.out.println("add case");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCode(driver);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsRailRoad(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, " patient is added with insurance : dx required check ");
			test.log(LogStatus.INFO, "creating appointmnet for above added patient  ");
			System.out.println("Adding appointmnet ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Commons.waitforElement(driver, By.id("clearPatient"), 120);
			driver.findElement(By.id("clearPatient")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='patientName']//input[@placeholder='Patient Search']"))
					.sendKeys(patient_id);
			Commons.waitforElement(driver,
					By.xpath(
							"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span")));
			Commons.waitforElement(driver, By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span"), 120);
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]")));
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "appointment is created for above added patient  ");
			test.log(LogStatus.INFO, "checking status of above created appoinmnet and POC expiration date ");
			System.out.println("navigated to home page");
			test.log(LogStatus.INFO, "navigated to home page");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(10000);
			Commons.waitForLoad(driver);
			System.out.println("checking plan of care expiration value");
			Commons.waitforElement(driver,
					By.xpath("//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[3]"),
					80);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[3]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			String planofcareexp = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Plan of care expiration is " + planofcareexp);
			test.log(LogStatus.INFO,
					"status of above created appointment is scheduled and POC exp is " + planofcareexp);
			System.out.println("creating note and adding plan of expiration ");
			test.log(LogStatus.INFO, "creating note and adding plan of expiration ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-red']";
			AddScheduleUtils.CreateInitialNoteFromProviderHomePage(driver, xpath, "10/10/2015");
			test.log(LogStatus.INFO, "Initial not is created and poC exp added is " + "10/10/2015");
			test.log(LogStatus.INFO, "checking poc exp is :  " + "10/10/2015"
					+ " and red dot should be added indicating poc expiration is today or in past ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[3]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			planofcareexp = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Plan of care expiration is " + planofcareexp);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/dd/yy");
			System.out.println("date need to be check " + "10/10/15");
			// Assert.assertTrue(date2.format(dtf2).contains(planofcareexp));
			Assert.assertTrue(planofcareexp.equals("10/10/15"));
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/following-sibling::td[3]/span[@class='warning-sign']";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "poc exp on provider home page is :  " + planofcareexp
					+ " and red dot is added indicating poc expiration is today or in past ");
			test.log(LogStatus.INFO,
					"creating addendum for above created note and changing poc of exp to " + "10/10/2020");
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-green']";
			AddScheduleUtils.CreateAddendumNoteFromProviderHomePage(driver, xpath, "10/10/2020");
			test.log(LogStatus.INFO,
					"addendum is created for above created note and poc of exp is changed to " + "10/10/2020");
			test.log(LogStatus.INFO, "checking poc of exp is changed to " + "10/10/2020"
					+ " and red dot should be removed as poc is not today or in past");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[3]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			planofcareexp = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Plan of care expiration 2 is " + planofcareexp);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("date need to be check 2 " + "10/10/20");
			// Assert.assertTrue(date2.plusDays(2).format(dtf2).contains(planofcareexp));
			Assert.assertTrue(planofcareexp.equals("10/10/20"));
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/following-sibling::td[3]/span[@class='warning-sign']";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "poc of exp is changed to " + "10/10/20"
					+ " and red dot is removed as poc is not today or in past");
			test.log(LogStatus.INFO,
					"Test:::: TestPOCexpDateDisplayFORInsuranceFlagDXPOCChangeReqAsChecked Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestPOCexpDateDisplayFORInsuranceFlagDXPOCChangeReqAsChecked Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestPOCexpDateDisplayFORInsuranceFlagDXPOCChangeReqAsChecked Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void TestNotesSinceMedProgdisplayifInsuranceIsNotMedicare() {
		test = extent.startTest(
				"to verify Note since medProg Display  behavior on provider home page if insurance is not medicare ",
				"Test Provier Home Page Top menu " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date2 = LocalDate.now();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider2"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient with insurance as not medicare ");
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			String patient_id = ActionUtils.getText(AddPatientPage.patientID(driver));
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[2];
			System.out.println("new name on home page is :" + newName);
			System.out.println("add case");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCode(driver);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, " patient is added with insurance as non medicare ");
			test.log(LogStatus.INFO, "creating appointmnet for above added patient  ");
			System.out.println("Adding appointmnet ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Commons.waitforElement(driver, By.id("clearPatient"), 120);
			driver.findElement(By.id("clearPatient")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='patientName']//input[@placeholder='Patient Search']"))
					.sendKeys(patient_id);
			Commons.waitforElement(driver,
					By.xpath(
							"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span")));
			Commons.waitforElement(driver, By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span"), 120);
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]")));
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "appointment is created for above added patient  ");
			test.log(LogStatus.INFO, "checking status of above created appoinmnet and medProg Display ");
			System.out.println("navigated to home page");
			test.log(LogStatus.INFO, "navigated to home page");
			String medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("N/A"));
			test.log(LogStatus.INFO, "status of above created appointment is scheduled and medProg is" + medProg);
			System.out.println("creating Initial note ");
			test.log(LogStatus.INFO, "creating Initial note ");
			String xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-red']";
			AddScheduleUtils.CreateInitialNoteFromProviderHomePage(driver, xpath, date2.plusDays(5).format(dtf));
			test.log(LogStatus.INFO, "Initial note is created and checking medprog should be : N/A");
			medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("N/A"));
			test.log(LogStatus.INFO, "status of medProg after Initail note creation  is" + medProg);
			System.out.println("creating Follow up note ");
			test.log(LogStatus.INFO, "creating Follow up note and adding Medicare Progress Note report ");
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-green']";
			AddScheduleUtils.CreateFollowUpNoteFromProviderHomePage(driver, xpath, date2.plusDays(5).format(dtf));
			Select dropdown = new Select(
					driver.findElement(By.xpath("//select[@ng-model='vmNote.currentTemplateList']")));
			dropdown.selectByVisibleText("Medicare Progress Note");
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")), 90);
			ActionUtils.click(driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeVisible(driver,
					driver.findElement(By.xpath("//button[@ng-click='vmNote.finalizeReportClick()']")), 300);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmNote.finalizeReportClick()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@ng-show='vmNote.viewReportButtonsOnFinal']"), 300);
			test.log(LogStatus.INFO, "Follow up note is created and checking medprog should be : N/A");
			medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("N/A"));
			test.log(LogStatus.INFO,
					"status of medProg after followup note creation and Medicare Progress Note report finalize  is"
							+ medProg);
			test.log(LogStatus.INFO,
					"creating followup note creation and not finalizing Medicare Progress Note report ");
			AddScheduleUtils.CreateFollowUpNoteFromProviderHomePage(driver, xpath, date2.plusDays(2).format(dtf));
			test.log(LogStatus.INFO, "Follow up note is created and checking medprog should be : N/A");
			medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("N/A"));
			test.log(LogStatus.INFO,
					"status of medProg after followup note creation and Medicare Progress Note report not finalize  is"
							+ medProg);
			test.log(LogStatus.INFO, "TestNotesSinceMedProgdisplayifInsuranceIsNotMedicare is success");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNotesSinceMedProgdisplayifInsuranceIsNotMedicare Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNotesSinceMedProgdisplayifInsuranceIsNotMedicare Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void TestNotesSinceMedProgdisplayifInsuranceIsMedicare() {
		test = extent.startTest(
				"to verify Note since medProg Display  behavior on provider home page if insurance is  medicare ",
				"Test Provier Home Page Top menu " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date2 = LocalDate.now();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider2"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient with insurance as not medicare ");
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			String patient_id = ActionUtils.getText(AddPatientPage.patientID(driver));
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[2];
			System.out.println("new name on home page is :" + newName);
			System.out.println("add case");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCode(driver);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, " patient is added with insurance as  medicare ");
			test.log(LogStatus.INFO, "creating appointmnet for above added patient  ");
			System.out.println("Adding appointmnet ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Commons.waitforElement(driver, By.id("clearPatient"), 120);
			driver.findElement(By.id("clearPatient")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='patientName']//input[@placeholder='Patient Search']"))
					.sendKeys(patient_id);
			Commons.waitforElement(driver,
					By.xpath(
							"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span")));
			Commons.waitforElement(driver, By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span"), 120);
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]")));
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "appointment is created for above added patient  ");
			test.log(LogStatus.INFO, "checking status of above created appoinmnet and medProg Display ");
			System.out.println("navigated to home page");
			test.log(LogStatus.INFO, "navigated to home page");
			String medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("Not Reported"));
			test.log(LogStatus.INFO, "status of above created appointment is scheduled and medProg is" + medProg);
			System.out.println("creating Initial note ");
			test.log(LogStatus.INFO, "creating Initial note ");
			String xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-red']";
			AddScheduleUtils.CreateInitialNoteFromProviderHomePage(driver, xpath, date2.plusDays(5).format(dtf));
			test.log(LogStatus.INFO, "Initial note is created and checking medprog should be : not reported");
			medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("Not Reported"));
			test.log(LogStatus.INFO, "status of medProg after Initail note creation  is" + medProg);
			System.out.println("creating Follow up note ");
			test.log(LogStatus.INFO, "creating Follow up note and adding Medicare Progress Note report ");
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-green']";
			AddScheduleUtils.CreateFollowUpNoteFromProviderHomePage(driver, xpath, date2.plusDays(5).format(dtf));
			Select dropdown = new Select(
					driver.findElement(By.xpath("//select[@ng-model='vmNote.currentTemplateList']")));
			dropdown.selectByVisibleText("Medicare Progress Note");
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")), 90);
			ActionUtils.click(driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@ng-click='vmNote.finalizeReportClick()']")), 90);
			test.log(LogStatus.INFO, "Follow up note is created and checking medprog should be : 0");
			medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("0"));
			test.log(LogStatus.INFO,
					"status of medProg after followup note creation and Medicare Progress Note report finalize  is"
							+ medProg);
			test.log(LogStatus.INFO,
					"creating followup note creation and not generating Medicare Progress Note report ");
			AddScheduleUtils.CreateFollowUpNoteFromProviderHomePage(driver, xpath, date2.plusDays(2).format(dtf));
			test.log(LogStatus.INFO, "Follow up note is created and checking medprog should be : 1");
			medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("1"));
			test.log(LogStatus.INFO,
					"status of medProg after followup note creation and Medicare Progress Note report not generated"
							+ medProg);
			test.log(LogStatus.INFO, "creating addendum of above created note and finalizing medicare Proress note ");
			AddScheduleUtils.CreateAddendumNoteFromProviderHomePage(driver, xpath, date2.plusDays(2).format(dtf));
			dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vmNote.currentTemplateList']")));
			dropdown.selectByVisibleText("Medicare Progress Note");
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")), 90);
			ActionUtils.click(driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@ng-click='vmNote.finalizeReportClick()']")), 90);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmNote.finalizeReportClick()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@ng-show='vmNote.viewReportButtonsOnFinal']"), 300);
			medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("0"));
			test.log(LogStatus.INFO,
					"status of medProg after followup note is addendum and Medicare Progress Note report finalize  is"
							+ medProg);
			test.log(LogStatus.INFO, "TestNotesSinceMedProgdisplayifInsuranceIsMedicare is success");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNotesSinceMedProgdisplayifInsuranceIsMedicare Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNotesSinceMedProgdisplayifInsuranceIstMedicare Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 6)
	public void FLR10thVisitValidateMedicalProgressReportvalidation() throws InterruptedException {
		test = extent.startTest("To verify on 10 visit medical report progress shot red dot ",
				"Test FLR 10th Visit Promt with all finalized notes" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date2 = LocalDate.now();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider2"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient with insurance as not medicare ");
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			String patient_id = ActionUtils.getText(AddPatientPage.patientID(driver));
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[2];
			System.out.println("new name on home page is :" + newName);
			System.out.println("add case");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCode(driver);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, " patient is added with insurance as  medicare ");
			test.log(LogStatus.INFO, "creating appointmnet for above added patient  ");
			System.out.println("Adding appointmnet ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Commons.waitforElement(driver, By.id("clearPatient"), 120);
			driver.findElement(By.id("clearPatient")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='patientName']//input[@placeholder='Patient Search']"))
					.sendKeys(patient_id);
			Commons.waitforElement(driver,
					By.xpath(
							"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span")));
			Commons.waitforElement(driver, By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span"), 120);
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]")));
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(100);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//u[contains(.,'" + newName
							+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-red']"),
					60);
			String xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/parent::tr/td/span[@class='clinicalNoteStatus note-red']";
			AddScheduleUtils.CreateInitialNoteFromProviderHomePage(driver, xpath, date2.plusDays(5).format(dtf));
			AddScheduleUtils.CreateFollowupWithMedicalProgressReport(driver, date2.plusDays(5).format(dtf));
			Select dropdown = new Select(
					driver.findElement(By.xpath("//select[@ng-model='vmNote.currentTemplateList']")));
			dropdown.selectByVisibleText("Medicare Progress Note");
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")), 90);
			ActionUtils.click(driver.findElement(By.xpath("//button[@data-ng-click='vmNote.addNewOutputReport()']")));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeClickable(driver,
					driver.findElement(By.xpath("//button[@ng-click='vmNote.finalizeReportClick()']")), 90);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmNote.finalizeReportClick()']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@ng-show='vmNote.viewReportButtonsOnFinal']"), 300);
			for (int i = 1; i < 10; i++) {
				ActionUtils.click(CreateNotePage.EMROnMenu(driver));
				ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
				System.out.println("Opened Create Note Screen");
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver,
						By.xpath("//select[@id='ddlCases']//option[contains(text(),'Case With All Fields')]"), 120);
				ActionUtils.click(driver.findElement(
						By.xpath("//select[@id='ddlCases']//option[contains(text(),'Case With All Fields')]")));
				Commons.waitForLoad(driver);
				Thread.sleep(5000);
				CreateNotePage.VisitTypeddl(driver, "Followup Visit");
				Commons.Explicitwait();
				Thread.sleep(5000);
				Commons.waitForLoad(driver);
				ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
				try {
					Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
					System.out.println("Confirmation pop up appeared");
					driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
				} catch (Exception e) {
					System.out.println("Confirmation pop up Not appeared");
				}
				Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 200);
			}
			String medProg = AddScheduleUtils.checkingMedProdStatus(driver, newName);
			Assert.assertTrue(medProg.equalsIgnoreCase("9"));
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/following-sibling::td[5]/span[@class='warning-sign']";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "FLR10thVisitValidateMedicalProgressReportvalidation is success");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: FLR10thVisitValidateMedicalProgressReportvalidation Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: FLR10thVisitValidateMedicalProgressReportvalidation Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
