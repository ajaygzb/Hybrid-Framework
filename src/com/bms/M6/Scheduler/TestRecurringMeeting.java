package com.bms.M6.Scheduler;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import com.google.common.base.CharMatcher;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestRecurringMeeting extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();
	public int ScheduledAppointmentonProviderHome = 0;
	public List<WebElement> actual = null;

	@Test(enabled = true, priority = 1)
	public void AddCancelRecurringMeeting() {
		test = extent.startTest("To Verify User can Create new reccurring MEETING for a provider",
				"Test Create reccurring Meeting for provider" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),
				Commons.readPropertyValue("password"));
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		Commons.waitforElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 30);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		int a = driver.findElements(By.xpath("//*[@id='eventTemplate'][contains(.,'MEETING')]")).size();
		String strActual_text = callto.CreateReccurringMeeting(driver);
		// boolean Text_Expected =
		// driver.getPageSource().contains(strActual_text);
		int b = driver.findElements(By.xpath("//*[@id='eventTemplate'][contains(.,'MEETING')]")).size();
		test.log(LogStatus.INFO, "Expected Count" + "  " + b);
		try {
			Assert.assertTrue(b > a);
			test.log(LogStatus.INFO, "Scheduled Reccurring Meeting Successfully");
			extent.endTest(test);
			Commons.screenshot();
			// Case-2
			test = extent.startTest("To Verify User can Cancel an existing reccurring MEETING for a provider",
					"To Verify User can Cancel an existing reccurring MEETING for a provider"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			try {
				System.out.println("Cancel meeting");
				WebElement meeting = driver.findElements(By.xpath("//*[@id='eventTemplate'][contains(.,'MEETING')]"))
						.get(0);
				Commons.scrollElementinotView(meeting, driver);
				ActionUtils.doubleClick(driver, meeting);
				test.log(LogStatus.INFO, "Go to View schedule page and Double click on Existing Reccuring Meeting");
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Edit Series')]")));
				Commons.waitForLoad(driver);
				Commons.screenshot();
				Commons.ExistandDisplayElement(driver,
						By.xpath("//label[contains(.,'Cancel all meetings in the series')]//input[@type='checkbox']"), 20);
				ActionUtils.click(driver
						.findElement(By.xpath("//label[contains(.,'Cancel all meetings in the series')]//input[@type='checkbox']")));
				test.log(LogStatus.INFO, "Clicked on Cancel this meeting checkbox");
				Commons.screenshot();
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
			test.log(LogStatus.INFO, "Test Schedule reccurring meeting Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}