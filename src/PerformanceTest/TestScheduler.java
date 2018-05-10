package PerformanceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestScheduler extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();

	@Test(enabled = true, priority = 1)
	public void testQuickSchedule() throws InterruptedException {
		test = extent.startTest("To Verify Create 'Quick Add' patient and schedule an appointment",
				"Test Quick Schedule" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Scheduler");
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		strActualtext = callto.ScheduleAppointment(driver);
		String strExpectedText = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
				.replaceAll("\\s", "");
		System.out.println("Patient Got Appointment  " + "  " + strActualtext);
		test.log(LogStatus.INFO, "Patient Got Appointment Name:  " + "  " + strActualtext);
		try {
			test.log(LogStatus.INFO, "Actual Name" + "  " + strActualtext + "  ",
					"Expected Name" + "  " + strExpectedText);
			Assert.assertEquals(strExpectedText, strActualtext);
			test.log(LogStatus.INFO, "Test:::: testQuickSchedule() Completed as Pass");
			/*
			 * NOT IN SCOPE test.log(LogStatus.INFO,
			 * "******************Verify Diffrent types of views are loaded in scheduler****************"
			 * ); ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			 * ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			 * Commons.waitForElementToBeNotVisible(driver,By.xpath(
			 * "//span[contains(text(),'Loading')]"), 60);
			 * Commons.waitForElementToBeNotVisible(driver,
			 * By.xpath("//*[@id='mySpinner']"),200); System.out.println(
			 * "Opened View Schedule page"); test.log(LogStatus.INFO,
			 * "Verify Provider View loaded");
			 * AddScheduleUtils.SelectViewBy(driver, "Provider View",
			 * "Automation BMS Provider"); Commons.waitforElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),80); String
			 * viewvalprovider=driver.findElement(By.xpath(
			 * "//*[@id='dropdown-view-mode']")).getAttribute("value").toString(
			 * ); Assert.assertTrue(viewvalprovider.contains("2"));
			 * System.out.println("************Assertion -1 Pass***********");
			 * test.log(LogStatus.INFO,
			 * "************Assertion -1 Pass***********");
			 * Commons.screenshot(); test.log(LogStatus.INFO,
			 * "************Provider View Pass***********");
			 * test.log(LogStatus.INFO,"Verify location View loaded");
			 * AddScheduleUtils.SelectViewBy(driver, "Location View",
			 * "Automation BMS"); Commons.waitforElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),80); String
			 * viewvallocation=driver.findElement(By.xpath(
			 * "//*[@id='dropdown-view-mode']")).getAttribute("value").toString(
			 * ); Assert.assertTrue(viewvallocation.contains("1"));
			 * System.out.println("************Assertion -2 Pass***********");
			 * test.log(LogStatus.INFO,
			 * "************Assertion -2 Pass***********");
			 * Commons.screenshot(); test.log(LogStatus.INFO,
			 * "************Location View Pass***********");
			 * test.log(LogStatus.INFO,"Verify Non Provider View loaded");
			 * AddScheduleUtils.SelectViewBy(driver, "Non-Provider View",
			 * "Automation NonProvider"); Commons.waitforElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),80); String
			 * viewvalNonprovider=driver.findElement(By.xpath(
			 * "//*[@id='dropdown-view-mode']")).getAttribute("value").toString(
			 * ); Assert.assertTrue(viewvalNonprovider.contains("3"));
			 * System.out.println("************Assertion -3 Pass***********");
			 * test.log(LogStatus.INFO,
			 * "************Assertion -3 Pass***********");
			 * Commons.screenshot(); test.log(LogStatus.INFO,
			 * "************Non-Provider View Pass***********");
			 * test.log(LogStatus.INFO,"Verify Patient View loaded");
			 * Commons.SelectElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),"Patient View");
			 * Thread.sleep(1000); Commons.waitForLoad(driver);
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * ".//*[@id='dropdown-view-mode-value']/option[2]")));
			 * Thread.sleep(5000); Commons.waitforElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),80); String
			 * viewvalpatient=driver.findElement(By.xpath(
			 * "//*[@id='dropdown-view-mode']")).getAttribute("value").toString(
			 * ); Assert.assertTrue(viewvalpatient.contains("6"));
			 * System.out.println("************Assertion -4 Pass***********");
			 * test.log(LogStatus.INFO,
			 * "************Assertion -4 Pass***********");
			 * Commons.screenshot(); test.log(LogStatus.INFO,
			 * "************Patient View Pass***********");
			 * test.log(LogStatus.INFO,"Verify Practice View loaded");
			 * Commons.SelectElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),"Practice View");
			 * Thread.sleep(1000); Commons.waitForLoad(driver);
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * ".//*[@id='dropdown-view-mode-value']/option[2]")));
			 * Thread.sleep(5000); Commons.waitforElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),80); String
			 * viewvalPractice=driver.findElement(By.xpath(
			 * "//*[@id='dropdown-view-mode']")).getAttribute("value").toString(
			 * ); Assert.assertTrue(viewvalPractice.contains("4"));
			 * System.out.println("************Assertion -5 Pass***********");
			 * test.log(LogStatus.INFO,
			 * "************Assertion -5 Pass***********");
			 * Commons.screenshot(); test.log(LogStatus.INFO,
			 * "************Practice View Pass***********");
			 * test.log(LogStatus.INFO,"Verify Company View loaded");
			 * Commons.SelectElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),"Company View");
			 * Thread.sleep(1000); Commons.waitForLoad(driver);
			 * //ActionUtils.click(driver.findElement(By.xpath(
			 * ".//*[@id='dropdown-view-mode-value']/option[2]")));
			 * Thread.sleep(5000); Commons.waitforElement(driver,
			 * By.xpath("//*[@id='dropdown-view-mode']"),80); String
			 * viewvalCompany=driver.findElement(By.xpath(
			 * "//*[@id='dropdown-view-mode']")).getAttribute("value").toString(
			 * ); Assert.assertTrue(viewvalCompany.contains("5"));
			 * System.out.println("************Assertion -6 Pass***********");
			 * test.log(LogStatus.INFO,
			 * "************Assertion -6 Pass***********");
			 * Commons.screenshot(); test.log(LogStatus.INFO,
			 * "************Company View Pass***********");
			 */
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testQuickSchedule() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void testValidateCaseName() throws InterruptedException {
		test = extent.startTest(
				"To Validate Newely created case in scheduler from Quick add window displays as incomplete",
				"Test Appointment quick add patient case creation" + "***Current Browser****" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Scheduler");
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = callto.CasecreationScheduler(driver);
		System.out.println("Case Name In Scheduler" + "  " + strActual_text);
		AddCaseUtils.GoToCaseList(driver);
		String Text_Expected = AddCaseUtils.GetFirstCaseName(driver).trim();
		test.log(LogStatus.INFO, Text_Expected);
		try {
			Assert.assertTrue(strActual_text.contains(Text_Expected), "Case Name not matched");
			Assert.assertTrue(AddCaseUtils.GetFirstCaseStatus(driver).trim().contains("Incomplete"));
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: testValidateCaseName() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void testValidateNewCaseAppointment() throws InterruptedException {
		test = extent.startTest(
				"To Validate Newely created case in scheduler from Appointment window displays as incomplete",
				"Test Appointment quick add patient case creation" + "***Current Browser****" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Scheduler");
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		// Commons.logout(driver);
		// Commons.logintoRevflow(driver,
		// Commons.readPropertyValue("username"),Commons.readPropertyValue("password"));
		strActual_text = callto.NewCasefromAppointmentwindow(driver);
		System.out.println("Case Name In Scheduler" + "  " + strActual_text);
		test.log(LogStatus.INFO, "Case Name In Scheduler" + "  " + strActual_text);
		AddCaseUtils.GoToCaseList(driver);
		String Text_Expected = AddCaseUtils.GetFirstCaseName(driver).trim();
		test.log(LogStatus.INFO, "Case Name In Case List" + "  " + Text_Expected);
		try {
			Assert.assertTrue(strActual_text.contains(Text_Expected), "Case Name not matched");
			Assert.assertTrue(AddCaseUtils.GetFirstCaseStatus(driver).trim().contains("Incomplete"));
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=====");
			test.log(LogStatus.INFO, "Test:::: testValidateNewCaseAppointment() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
