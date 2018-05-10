package com.bms.M6.Scheduler;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestScheduleFutureAppointment extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();

	@Test(enabled = true, priority = 1)
	public void TestCreatefutureAppointment() {
		test = extent.startTest("To Verify User can Schedule an Appointment to future date",
				"Test Schedule Appointment By Location to a future date" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.AppointmentToFutureDate(driver);
		System.out.println("Current Date is" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Current Date is" + "  " + strActual_text);
		String Text_Expected = ActionUtils
				.getText(driver.findElement(By.xpath("//div[@id='scheduler']//a//span[@class='k-lg-date-format']"))); // Expected
																														// date
																														// from
																														// app
																														// after
																														// scheduling
																														// appointment
		test.log(LogStatus.INFO, "Appointment Date is" + "  " + Text_Expected);
		try {
			Assert.assertNotEquals(strActual_text, Text_Expected, "Test Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: TestCreatefutureAppointment() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestCreatePastAppointment() {
		test = extent.startTest("To Verify User can Schedule an Appointment to Past date",
				"Test Schedule Appointment By Location to a Past date" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.AppointmentToPast(driver);
		System.out.println("Current Date is" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Current Date is" + "  " + strActual_text);
		String Text_Expected = ActionUtils
				.getText(driver.findElement(By.xpath("//div[@id='scheduler']//a//span[@class='k-lg-date-format']"))); // Expected
																														// date
																														// from
																														// app
																														// after
																														// scheduling
																														// appointment
		test.log(LogStatus.INFO, "Appointment Date is" + "  " + Text_Expected);
		try {
			Assert.assertNotEquals(strActual_text, Text_Expected, "Test Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: TestCreatePastAppointment() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}