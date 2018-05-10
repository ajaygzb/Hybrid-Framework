package com.bms.M6.Scheduler;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestScheduleMeeting extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();

	// keep it false as this test is covered in test schedule meetings by view
	@Test(enabled = true, priority = 6)
	public void QuickScheduleMeeting() {
		test = extent.startTest("To Verify User can Create new MEETING for a provider",
				"Test Create Meeting for provider" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		Commons.waitforElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 30);
		AddScheduleUtils.SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		int a = driver.findElements(By.xpath("//*[@id='eventTemplate'][contains(.,'MEETING')]")).size();
		callto.CreateMeeting(driver);
		// boolean Text_Expected =
		// driver.getPageSource().contains(strActual_text);
		int b = driver.findElements(By.xpath("//*[@id='eventTemplate'][contains(.,'MEETING')]")).size();
		test.log(LogStatus.INFO, "Expected Count" + "  " + b);
		try {
			Assert.assertTrue(b > a);
			test.log(LogStatus.INFO, "Scheduled Meeting Successfully");
			try {
				System.out.println("Cancel meeting");
				WebElement meeting = driver.findElements(By.xpath("//*[@id='eventTemplate'][contains(.,'MEETING')]"))
						.get(0);
				Commons.scrollElementinotView(meeting, driver);
				ActionUtils.doubleClick(driver, meeting);
				Commons.ExistandDisplayElement(driver,
						By.xpath("//label[contains(.,'Cancel all meetings in the series')]//input[@type='checkbox']"), 20);
				ActionUtils.click(driver
						.findElement(By.xpath("//label[contains(.,'Cancel all meetings in the series')]//input[@type='checkbox']")));
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Save')]")));
				Commons.waitForLoad(driver);
				if (driver.findElements(By.xpath("//*[@id='eventTemplate'][contains(.,'MEETING')]")).size() == a) {
					test.log(LogStatus.INFO, "Canceled Meeting Successfully");
				}
			} catch (Exception e) {
				System.out.println("Could not cancel meeting" + Throwables.getStackTraceAsString(e));
			}
			// *[@id='eventTemplate'][contains(.,'MEETING')] //cancel meeting
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!=====");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: QuickScheduleMeeting() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void QuickrReScheduleAppointment() {
		test = extent.startTest("To Verify User can Reschedule an Existing Appointment", "Test Reschedule Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text_Toast = null;
		strActual_text_Toast = AddScheduleUtils.ReScheduleAppointment(driver);
		System.out.println("Toast text in app" + "  " + strActual_text_Toast);
		String Text_Expected = "You must complete all required fields.";
		test.log(LogStatus.INFO, Text_Expected);
		try {
			Assert.assertTrue(strActual_text_Toast.contains(Text_Expected), "Toast messages not matched");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, strActual_text_Toast + "   " + Text_Expected);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = false, priority = 3)
	public void AppointmentOverBooking() {
		test = extent.startTest("To Verify Appointment Overbooking pop up", "Test Appointment OverBookig"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = callto.ScheduleAppointmentOverBooking(driver);
		boolean Text_Expected = "Maximum booking reached for Automation Overbooking".contains(strActual_text);
		test.log(LogStatus.INFO, "Expected View" + "  " + Text_Expected);
		try {
			Assert.assertTrue(Text_Expected);
			test.log(LogStatus.INFO, "Appointment Overbooking Reached MAX");
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!=====");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: AppointmentOverBookig() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void CancelAppointment() {
		test = extent.startTest("To Verify User can Cancel an Existing Appointment", "Test Cancel Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddScheduleUtils ASU = new AddScheduleUtils();
		int strActual_text = ASU.CancelAppointment(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='ListViewli']/a"), 30);
		ActionUtils.click(SchedulerPage.ListViewButton(driver));
		Commons.Explicitwait();
		if (Commons.existsElement(driver,
				By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"))) {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"), 30);
			ASU.AfterCount = driver
					.findElements(By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr")).size();
		} else {
			System.out.println("No Appointment is available after canceling the current Appointment");
			ASU.AfterCount = 0; // Appointment list is empty after canceling
								// last existing appointment
		}
		System.out.println("Apoointment counts after Cancel" + "  " + ASU.AfterCount);
		ActionUtils.click(SchedulerPage.CloseButtonListView(driver));
		test.log(LogStatus.INFO, "Expected View" + "  " + ASU.AfterCount);
		try {
			test.log(LogStatus.INFO,
					"Appointments Count  in List View Before Cancel Appointment" + "     " + strActual_text);
			test.log(LogStatus.INFO,
					"Appointments Count in List View After Cancel Appointment" + "     " + ASU.AfterCount);
			Assert.assertTrue(strActual_text > ASU.AfterCount);
			test.log(LogStatus.INFO, "Appointment Canceled Successfully");
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!=====");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: CancelAppointment() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 1)
	public void NoShowAppointment() {
		test = extent.startTest("To Verify User can set an Existing Appointment to No SHow", "Test No Show Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddScheduleUtils ASU = new AddScheduleUtils();
		int strActual_text = ASU.AppointmentNoShow(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='ListViewli']"), 30);
		ActionUtils.click(SchedulerPage.ListViewButton(driver));
		Commons.Explicitwait();
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"), 50);
			Commons.Explicitwait();
			ASU.AfterCount = driver
					.findElements(By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr")).size();
		} catch (Exception e) {
			System.out.println("List View is EMpty there is no Appointment");
			ASU.AfterCount = 0;
		}
		ActionUtils.click(SchedulerPage.CloseButtonListView(driver));
		test.log(LogStatus.INFO, "Expected View" + "  " + ASU.AfterCount);
		try {
			test.log(LogStatus.INFO,
					"Appointments Count  in List View Before status No Show" + "     " + strActual_text);
			test.log(LogStatus.INFO,
					"Appointments Count in List View After status No Show  " + "     " + ASU.AfterCount);
			Assert.assertTrue(strActual_text > ASU.AfterCount);
			test.log(LogStatus.INFO, "Appointment Changed to No Show Successfully");
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!=====");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: NoShowAppointment() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void ArrivedAppointment() {
		test = extent.startTest("To Verify User can set an Existing Appointment to Arrived", "Test Arrived Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Assert.assertTrue(AddScheduleUtils.AppointmentArrived(driver));
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.logout(driver);
			Commons.logintoRevflow(driver, "AutomationBMSProvider", Commons.readPropertyValue("password"));
			Commons.waitforElement(driver, By.xpath("//a[@ng-click='setproviderHomeDate()']//span"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")));
			Commons.waitForLoad(driver);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("ARRIVED"));
			test.log(LogStatus.INFO, "Arrived appointment is visible on home page ");
			test.log(LogStatus.INFO, "Test:::: testProviderHomeArrivedAppointmnet Completed as Pass");
			test.log(LogStatus.INFO, "Appointment Changed to Arrived Successfully");
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!=====");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: NoShowAppointment() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}