package com.bms.M6.Scheduler;

import org.openqa.selenium.By;
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

public class TestScheduleMeetingByView extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();

	@Test(enabled = true, priority = 1)
	public void ScheduleMeetingbyProvider() {
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
			test.log(LogStatus.INFO, "Test:::: Schedule Meeting by provider Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void ScheduleMeetingByLocation() {
		test = extent.startTest("To Verify User can Create new MEETING for a Location",
				"Test Create Meeting for Location" + "*****Current Browser******" + CurrentBrowserName
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
		AddScheduleUtils.SelectViewBy(driver, "Location View", "Automation BMS");
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
			// *[@id='eventTemplate'][contains(.,'MEETING')] //cancel meeting
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION PASS !!!!!!!!!!!!=====");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: Schedule Meeting By Location Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}