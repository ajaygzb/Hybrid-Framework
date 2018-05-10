package com.bms.M6.Scheduler;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import Utils.AddScheduleUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestScheduleAppointmentByView extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();

	@Test(enabled = true, priority = 1)
	public void TestAppointmentByLocation() {
		test = extent.startTest("To Verify User can Schedule an Appointment by Location",
				"Test Schedule Appointment By Location" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.AppointmentByLocation(driver);
		System.out.println("Current Selected View is" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Current Selected View is" + "  " + strActual_text);
		String Text_Expected = ("Location View").trim();
		test.log(LogStatus.INFO, "Expected View" + "  " + Text_Expected);
		try {
			Assert.assertEquals(strActual_text, Text_Expected, "Test Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: TestAppointmentByLocation() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2) // This test case covered in quick
										// schedule test
	public void TestAppointmentByProvider() {
		test = extent.startTest("To Verify User can Schedule an Appointment by Provider",
				"Test Schedule Appointment By Provider" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.AppointmentByProvider(driver);
		System.out.println("Current Selected View is" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Current Actusl Selected View is" + "  " + strActual_text);
		String Text_Expected = ("Provider View").trim();
		test.log(LogStatus.INFO, "Expected View" + "  " + Text_Expected);
		try {
			Assert.assertEquals(strActual_text, Text_Expected, "Test Pass");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: TestAppointmentByProvider() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestAppointmentByCompany() {
		test = extent.startTest("To Verify User can Schedule an Appointment by Company",
				"Test Schedule Appointment By Company" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.AppointmentByCompany(driver);
		System.out.println("Current Selected View is" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Current Actual Selected View is" + "  " + strActual_text);
		String Text_Expected = ("Company View").trim();
		test.log(LogStatus.INFO, "Expected View" + "  " + Text_Expected);
		try {
			Assert.assertEquals(strActual_text, Text_Expected, "Test Pass");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: TestAppointmentByCompany() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = false, priority = 4)
	public void TestAppointmentByPractice() {
		test = extent.startTest("To Verify User can Schedule an Appointment by Practice",
				"Test Schedule Appointment By Practice" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.AppointmentByPractice(driver);
		System.out.println("Current Selected View is" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Current Actusl Selected View is" + "  " + strActual_text);
		String Text_Expected = ("Practice View").trim();
		test.log(LogStatus.INFO, "Expected View" + "  " + Text_Expected);
		try {
			Assert.assertEquals(strActual_text, Text_Expected, "Test Pass");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: TestAppointmentByPractice() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void TestAppointmentByNonProvider() {
		test = extent.startTest("To Verify User can Schedule an Appointment by Non-Provider",
				"Test Schedule Appointment By Non-Provider" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.AppointmentByNonProvider(driver);
		System.out.println("Current Selected View is" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Current Actusl Selected View is" + "  " + strActual_text);
		String Text_Expected = ("Non-Provider View").trim();
		test.log(LogStatus.INFO, "Expected View" + "  " + Text_Expected);
		try {
			Assert.assertEquals(strActual_text, Text_Expected, "Test Pass");
			test.log(LogStatus.INFO, "Assertion Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: TestAppointmentByNonProvider() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
