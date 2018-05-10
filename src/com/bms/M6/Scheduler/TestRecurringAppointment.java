package com.bms.M6.Scheduler;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestRecurringAppointment extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();
	public int AppCountBefore = 0;
	public int AppCountAfter = 0;
	public String PatientID =null;

	@Test(enabled = true, priority = 1)
	public void TestAddRecurringAppointment() throws InterruptedException {
		test = extent.startTest("Test Add Recurring Appointment", "Test Add Recurring Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),
				Commons.readPropertyValue("password"));
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 5)) {
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			AppCountBefore = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			test.log(LogStatus.INFO, "Count Before" + AppCountBefore);
			Commons.screenshot();
		}
		strActualtext = callto.ScheduleRecurringAppointment(driver, "Initial Evaluation", "Scheduled");
		test.log(LogStatus.INFO, "Schedule Reccuring Appointment as" + "Initial Evaluation" + "Scheduled");
		String strExpectedText = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
				.replaceAll("\\s", "");
		PatientID = AddPatientPage.patientID(driver).getText();
		Commons.screenshot();
		System.out.println(
				"Patient Got Appointment  " + "  " + strActualtext + "\n" + AddPatientPage.patientID(driver).getText());
		test.log(LogStatus.INFO, "Patient Got Appointment Name:  " + "  " + strActualtext);
		test.log(LogStatus.INFO, "ID:=> " + AddPatientPage.patientID(driver).getText());
		try {
			test.log(LogStatus.INFO, "Actual Name" + "  " + strActualtext + "  ",
					"Expected Name" + "  " + strExpectedText);
			Assert.assertEquals(strExpectedText, strActualtext);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 90);
			AppCountAfter = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			Commons.screenshot();
			test.log(LogStatus.INFO, "Count After" + AppCountAfter);
			Assert.assertTrue(AppCountAfter > AppCountBefore, "Could Not Add Recurring Appointment");
			test.log(LogStatus.INFO, "Test Add Recurring Appointment Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test Add recurring Appointment is Failed");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	
	}

	@Test(enabled = true, dependsOnMethods = { "TestAddRecurringAppointment" })
	public void TestEditOccurrenceRecurringAppointment() throws InterruptedException {
		test = extent.startTest("Test Edit Recurring Appointment", "Test Edit Recurring Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),
				Commons.readPropertyValue("password"));
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 5)) {
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			AppCountBefore = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			Commons.screenshot();
		}
		strActualtext = callto.EditRecurringAppointment(driver, "Edit Occurrence");
		Commons.screenshot();
		test.log(LogStatus.INFO, "Edit Occurance");
		String strExpectedText = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
				.replaceAll("\\s", "");
		System.out.println(AddPatientPage.patientID(driver).getText());
		Commons.screenshot();
		System.out.println(
				"Patient Got Appointment  " + "  " + strActualtext + "\n" + AddPatientPage.patientID(driver).getText());
		test.log(LogStatus.INFO, "Patient Got Appointment Name:  " + "  " + strActualtext);
		test.log(LogStatus.INFO, "ID:=> " + AddPatientPage.patientID(driver).getText());
		try {
			test.log(LogStatus.INFO, "Actual Name" + "  " + strActualtext + "  ",
					"Expected Name" + "  " + strExpectedText);
			Assert.assertEquals(strExpectedText, strActualtext);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 90);
			AppCountAfter = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			Commons.screenshot();
			Assert.assertTrue(AppCountAfter == AppCountBefore, "Could Not Edit Recurring Appointment");
			test.log(LogStatus.INFO, "Test Edit Recurring Appointment Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test Edit Recurring Appointment is failed");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, dependsOnMethods = { "TestAddRecurringAppointment" })
	public void TestEditSeriesRecurringAppointment() throws InterruptedException {
		test = extent.startTest("Test Edit Series Recurring Appointment", "Test Edit Series Recurring Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),
				Commons.readPropertyValue("password"));
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 5)) {
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			AppCountBefore = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			Commons.screenshot();
		}
		strActualtext = callto.EditRecurringAppointment(driver, "Edit Series");
		String strExpectedText = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
				.replaceAll("\\s", "");
		System.out.println(AddPatientPage.patientID(driver).getText());
		Commons.screenshot();
		System.out.println(
				"Patient Got Appointment  " + "  " + strActualtext + "\n" + AddPatientPage.patientID(driver).getText());
		test.log(LogStatus.INFO, "Patient Got Appointment Name:  " + "  " + strActualtext);
		test.log(LogStatus.INFO, "ID:=> " + AddPatientPage.patientID(driver).getText());
		try {
			test.log(LogStatus.INFO, "Actual Name" + "  " + strActualtext + "  ",
					"Expected Name" + "  " + strExpectedText);
			Assert.assertEquals(strExpectedText, strActualtext);
			SearchPatientUtils.QuickpatientSearch(driver, AddPatientPage.patientID(driver).getText());
			Commons.waitforElement(driver, By.xpath("//*[@id='headerAppointmentIcon']"),90);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerAppointmentIcon']")));
			Commons.waitforElement(driver, By.xpath(".//*[@id='headerAppointment']//h4[contains(.,'Appointments for')]"),90);
			Commons.screenshot();
			Assert.assertTrue(driver.findElements(By.xpath("//*[@id='HeaderAppopintmentId']//tbody//tr")).size()>=5);
			ActionUtils.click(driver.findElement(By.xpath(".//*[@id='headerAppointment']//button[contains(.,'Close')]")));
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 90);
			AppCountAfter = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			Commons.screenshot();
			Assert.assertTrue(AppCountAfter == AppCountBefore, "Could Not Edit Series Recurring Appointment");
			test.log(LogStatus.INFO, "Test Edit Series Recurring Appointment Completed as Pass");	
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test Edit  Series Recurring Appointment is failed");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4, dependsOnMethods = { "TestAddRecurringAppointment" })
	public void TestCancelRecurringAppointment() throws InterruptedException {
		test = extent.startTest("Test Cancel Series Recurring Appointment", "Test Cancel Series Recurring Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),
				Commons.readPropertyValue("password"));
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 5)) {
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			AppCountBefore = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			Commons.screenshot();
		}
		strActualtext = callto.CancelRecurringAppointment(driver, "Edit Series");
		String strExpectedText = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
				.replaceAll("\\s", "");
		System.out.println(AddPatientPage.patientID(driver).getText());
		Commons.screenshot();
		System.out.println(
				"Patient Got Appointment  " + "  " + strActualtext + "\n" + AddPatientPage.patientID(driver).getText());
		test.log(LogStatus.INFO, "Patient Got Appointment Name:  " + "  " + strActualtext);
		test.log(LogStatus.INFO, "ID:=> " + AddPatientPage.patientID(driver).getText());
		try {
			test.log(LogStatus.INFO, "Actual Name" + "  " + strActualtext + "  ",
					"Expected Name" + "  " + strExpectedText);
			Assert.assertEquals(strExpectedText, strActualtext);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"),30)) {
				AppCountAfter = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
			} else {
				AppCountAfter = 0; // No appointment left on Home page
				System.out.println("No appointment left on Home page");
			}
			Commons.screenshot();
			Assert.assertTrue(AppCountAfter < AppCountBefore, "Could Not Cancel Series Recurring Appointment");
			test.log(LogStatus.INFO, "Test Cancel Series Recurring Appointment Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test Cancel  Series Recurring Appointment is failed");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
