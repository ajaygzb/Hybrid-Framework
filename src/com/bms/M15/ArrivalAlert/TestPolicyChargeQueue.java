package com.bms.M15.ArrivalAlert;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestPolicyChargeQueue extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void TestCancelPolicyChargeQueueApproval() {
		test = extent.startTest(
				"To Verify a self pay case is created if user approve charge added in policy charge queue",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new name on home page is :" + newName);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			String patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField[] = patientField.split("BAL");
			Assert.assertTrue(subField[1].contains("$0"));
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from thr queue");
			SearchPatientUtils.QuickpatientSearch(driver, patientid);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitForLoad(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertTrue(Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]"), 30));
			test.log(LogStatus.INFO, "Self pay case is added");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 1);
			test.log(LogStatus.INFO, "There is only one self pay case");
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("0"));
			test.log(LogStatus.INFO,
					"Number of charges added on self pay case is " + driver.findElement(By.xpath(xpath)).getText());
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "self pay case is visible in view charges");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().contains("40"));
			test.log(LogStatus.INFO,
					"charge added on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
			test.log(LogStatus.INFO, "Test:::: TestCancelPolicyChargeQueueApproval Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCancelPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCancelPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestCancelPolicyChargeQueueDeny() {
		test = extent.startTest(
				"To Verify a self pay case is not created if user Deny the charge added in policy charge queue",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new name on home page is :" + newName);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			String patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField[] = patientField.split("BAL");
			Assert.assertTrue(subField[1].contains("$0"));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policydenyBtn")));
			test.log(LogStatus.INFO, "Charge is denied");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from thr queue");
			SearchPatientUtils.QuickpatientSearch(driver, patientid);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is not added");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			// Thread.sleep(3000);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "self pay case is not visible in view charges");
			test.log(LogStatus.INFO, "Test:::: TestCancelPolicyChargeQueueDeny Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCancelPolicyChargeQueueDeny Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCancelPolicyChargeQueueDeny Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestMutipleCancelPolicyChargeQueueApproval() {
		test = extent.startTest(
				"To Verify only one self pay case is created if user approve mutiple charges added in policy charge queue for one patient",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new name on home page is :" + newName);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			String patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField[] = patientField.split("BAL");
			Assert.assertTrue(subField[1].contains("$0"));
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			// creating another cancel appointment
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithcancel(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.cancelReason(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For cancel Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField2[] = patientField.split("BAL");
			Assert.assertTrue(subField2[1].contains("$40"));
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			// driver.findElement(By.xpath(xpath)).click();
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			// searching patient
			SearchPatientUtils.QuickpatientSearch(driver, patientid);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitForLoad(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is added");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 1);
			test.log(LogStatus.INFO, "There is only one self pay case");
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
			Assert.assertTrue(Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span"),
					30));
			Assert.assertTrue(driver
					.findElement(By
							.xpath("//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span"))
					.getText().equals("0"));
			test.log(LogStatus.INFO,
					"Number of charges added on self pay case is " + driver.findElement(By.xpath(xpath)).getText());
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "self pay case is visible in view charges");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 2);
			for (WebElement element : elements) {
				Assert.assertTrue(element.getText().contains("40"));
			}
			test.log(LogStatus.INFO,
					"charge added on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
			test.log(LogStatus.INFO, "Test:::: TestMutipleCancelPolicyChargeQueueApproval Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMutipleCancelPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMutipleCancelPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void TestNoShowPolicyChargeQueueApproval() {
		test = extent.startTest(
				"To Verify a self pay case is created if user approve charge added in policy charge queue",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addnoShowappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new name on home page is :" + newName);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			String patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField[] = patientField.split("BAL");
			System.out.println("old charges are " + subField[1]);
			test.log(LogStatus.INFO, "old charges are " + subField[1]);
			Assert.assertTrue(subField[1].contains("$0"));
			test.log(LogStatus.INFO, "there is no old charges ");
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			// driver.findElement(By.xpath(xpath)).click();
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from thr queue");
			SearchPatientUtils.QuickpatientSearch(driver, patientid);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitForLoad(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is added");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 1);
			test.log(LogStatus.INFO, "There is only one self pay case");
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			// Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("1"));
			test.log(LogStatus.INFO,
					"Number of charges added on self pay case is " + driver.findElement(By.xpath(xpath)).getText());
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "self pay case is visible in view charges");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().contains("27"));
			test.log(LogStatus.INFO,
					"charge added on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
			test.log(LogStatus.INFO, "Test:::: TestNoShowPolicyChargeQueueApproval Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNoShowPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNoShowPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void TestNoShowPolicyChargeQueueDeny() {
		test = extent.startTest(
				"To Verify a self pay case is not created if user Deny the charge added in policy charge queue",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addnoShowappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new name on home page is :" + newName);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			String patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField[] = patientField.split("BAL");
			System.out.println("old charges are " + subField[1]);
			test.log(LogStatus.INFO, "old charges are " + subField[1]);
			Assert.assertTrue(subField[1].contains("$0"));
			test.log(LogStatus.INFO, "there is no old charges ");
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			// driver.findElement(By.xpath(xpath)).click();
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policydenyBtn")));
			test.log(LogStatus.INFO, "Charge is denied");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from thr queue");
			SearchPatientUtils.QuickpatientSearch(driver, patientid);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is not added");
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			// Thread.sleep(3000);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "self pay case is not visible in view charges");
			test.log(LogStatus.INFO, "Test:::: TestNoShowPolicyChargeQueueDeny Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNoShowPolicyChargeQueueDeny Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNoShowPolicyChargeQueueDeny Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 6)
	public void TestMutipleNoShowPolicyChargeQueueApproval() {
		test = extent.startTest(
				"To Verify only one self pay case is created if user approve mutiple charges added in policy charge queue for one patient",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addnoShowappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new name on home page is :" + newName);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			String patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField[] = patientField.split("BAL");
			System.out.println("old charges are " + subField[1]);
			test.log(LogStatus.INFO, "old charges are " + subField[1]);
			Assert.assertTrue(subField[1].contains("$0"));
			test.log(LogStatus.INFO, "there is no old charges ");
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			// driver.findElement(By.xpath(xpath)).click();
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			// creating another cancel appointment
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithNoShow(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For cancel Notes");
			ActionUtils.JavaScriptclick(SchedulerPage.NoShowReasonForgot(driver));
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField2[] = patientField.split("BAL");
			System.out.println("old charges are " + subField2[1]);
			test.log(LogStatus.INFO, "old charges are " + subField2[1]);
			Assert.assertTrue(subField2[1].contains("$27"));
			test.log(LogStatus.INFO, "there is  old charges ");
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			// driver.findElement(By.xpath(xpath)).click();
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			// searching patient
			SearchPatientUtils.QuickpatientSearch(driver, patientid);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitForLoad(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is added");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 1);
			test.log(LogStatus.INFO, "There is only one self pay case");
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			// Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("2"));
			test.log(LogStatus.INFO,
					"Number of charges added on self pay case is " + driver.findElement(By.xpath(xpath)).getText());
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "self pay case is visible in view charges");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 2);
			for (WebElement element : elements) {
				Assert.assertTrue(element.getText().contains("27"));
			}
			test.log(LogStatus.INFO,
					"charge added on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
			test.log(LogStatus.INFO, "Test:::: TestMutipleNoShowPolicyChargeQueueApproval Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMutipleNoShowPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMutipleNoShowPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 8)
	public void TestMutipleCancelAndNowPolicyChargeQueueApprovalForMultiplePatient() {
		test = extent.startTest(
				"To Verify only one self pay case is created if user approve mutiple charges added in policy charge queue for multiple patient",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname1 = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid1 = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname1);
			String namesplit1[] = patientname1.split(" ");
			String newName1 = namesplit1[1] + ", " + namesplit1[0];
			System.out.println("new name on home page is :" + newName1);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName1);
			Commons.waitForLoad(driver);
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithNoShow(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.NoShowReasonForgot(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For no show Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			// creating appointments for 2 patient
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname2 = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid2 = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname2);
			String namesplit2[] = patientname2.split(" ");
			String newName2 = namesplit2[1] + ", " + namesplit2[0];
			System.out.println("new name on home page is :" + newName2);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName2);
			Commons.waitForLoad(driver);
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithNoShow(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.NoShowReasonForgot(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For no show Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			// navigating to policy charge queue
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName1);
			System.out.println("looking for patient" + newName1);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName1
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			List<WebElement> patientelement = driver.findElements(By.xpath(xpath));
			for (WebElement element : patientelement) {
				Commons.scrollElementinotView(element, driver);
				Assert.assertTrue(Commons.waitForElementToBeVisible(driver, element, 120));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.border='3px solid red'", element);
				System.out.println("found");
			}
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName1
					+ "')]/ancestor::tr/td/input";
			List<WebElement> patientelementcheckbox = driver.findElements(By.xpath(xpath));
			for (WebElement element : patientelementcheckbox) {
				Assert.assertTrue(Commons.waitForElementToBeVisible(driver, element, 120));
				Thread.sleep(2000);
				ActionUtils.JavaScriptclick(element);
				test.log(LogStatus.INFO, "selecting the charge");
				System.out.println("selecting the charge");
			}
			// selecting 2sd patient from policy charge queue
			System.out.println("looking for patient" + newName2);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName2
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			List<WebElement> patientelement2 = driver.findElements(By.xpath(xpath));
			for (WebElement element : patientelement2) {
				Commons.scrollElementinotView(element, driver);
				Assert.assertTrue(Commons.waitForElementToBeVisible(driver, element, 120));
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.border='3px solid red'", element);
				System.out.println("found");
			}
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName2
					+ "')]/ancestor::tr/td/input";
			List<WebElement> patientelementcheckbox2 = driver.findElements(By.xpath(xpath));
			for (WebElement element : patientelementcheckbox2) {
				Assert.assertTrue(Commons.waitForElementToBeVisible(driver, element, 120));
				Thread.sleep(2000);
				ActionUtils.JavaScriptclick(element);
				test.log(LogStatus.INFO, "selecting the charge");
				System.out.println("selecting the charge");
			}
			Thread.sleep(1000);
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName1
					+ "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName2
					+ "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			// checking self pay case of 1 patient
			SearchPatientUtils.QuickpatientSearch(driver, patientid1);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitForLoad(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is added");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 1);
			test.log(LogStatus.INFO, "There is only one self pay case");
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			// Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("2"));
			test.log(LogStatus.INFO,
					"Number of charges added on self pay case is " + driver.findElement(By.xpath(xpath)).getText());
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "self pay case is visible in view charges");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			elements = driver.findElements(By.xpath(xpath));
			// Assert.assertEquals(elements.size(), 2);
			int flag1 = 2, flag2 = 3;
			for (WebElement element : elements) {
				if (element.getText().contains("40")) {
					flag1 = 1;
				}
				if (element.getText().contains("27")) {
					flag2 = 1;
				}
			}
			Assert.assertTrue(flag1 == flag2);
			// checking self pay case of 2 patient
			SearchPatientUtils.QuickpatientSearch(driver, patientid2);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitForLoad(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is added");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			List<WebElement> elements2 = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements2.size(), 1);
			test.log(LogStatus.INFO, "There is only one self pay case");
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			// Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("2"));
			test.log(LogStatus.INFO,
					"Number of charges added on self pay case is " + driver.findElement(By.xpath(xpath)).getText());
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "self pay case is visible in view charges");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			elements = driver.findElements(By.xpath(xpath));
			// Assert.assertEquals(elements.size(), 2);
			int flag3 = 2, flag4 = 3;
			for (WebElement element : elements) {
				if (element.getText().contains("40")) {
					flag3 = 1;
				}
				if (element.getText().contains("27")) {
					flag4 = 1;
				}
			}
			Assert.assertTrue(flag3 == flag4);
			test.log(LogStatus.INFO,
					"Test:::: TestMutipleCancelAndNowPolicyChargeQueueApprovalForMultiplePatient Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestMutipleCancelAndNowPolicyChargeQueueApprovalForMultiplePatient Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestMutipleCancelAndNowPolicyChargeQueueApprovalForMultiplePatient Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 7)
	public void TestMutipleCancelAndNowPolicyChargeQueueApprovalForOnePatient() {
		test = extent.startTest(
				"To Verify only one self pay case is created if user approve mutiple charges added in policy charge queue for one patient",
				"Test Policy Charge Queue" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "creating cancel appointmnet");
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new name on home page is :" + newName);
			test.log(LogStatus.INFO, "cancel appointmnet created for patient" + newName);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "navigating to policy charge queue");
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			String patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField[] = patientField.split("BAL");
			System.out.println("old charges are " + subField[1]);
			test.log(LogStatus.INFO, "old charges are " + subField[1]);
			Assert.assertTrue(subField[1].contains("$0"));
			test.log(LogStatus.INFO, "there is no old charges ");
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			// driver.findElement(By.xpath(xpath)).click();
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			// creating no show appointment for same patient
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithNoShow(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.NoShowReasonForgot(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For no show Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			System.out.println("navigating to ploicy charge queue");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "looking for patient" + newName);
			System.out.println("looking for patient" + newName);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("found");
			patientField = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Balance is" + patientField);
			String subField2[] = patientField.split("BAL");
			System.out.println("old charges are " + subField2[1]);
			test.log(LogStatus.INFO, "old charges are " + subField2[1]);
			Assert.assertTrue(subField2[1].contains("$40"));
			test.log(LogStatus.INFO, "there are old charges ");
			Thread.sleep(3000);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
					+ "')]/ancestor::tr/td/input";
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			Thread.sleep(4000);
			// driver.findElement(By.xpath(xpath)).click();
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "selecting the charge");
			System.out.println("selecting the charge");
			ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
			test.log(LogStatus.INFO, "Charge is approved");
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Charge is removed from the queue");
			// searching patient
			SearchPatientUtils.QuickpatientSearch(driver, patientid);
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitForLoad(driver);
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "Self pay case is added");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			List<WebElement> elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 1);
			test.log(LogStatus.INFO, "There is only one self pay case");
			xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
			// Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("2"));
			test.log(LogStatus.INFO,
					"Number of charges added on self pay case is " + driver.findElement(By.xpath(xpath)).getText());
			AddChargeUtils.NavigateToViewChargePage(driver);
			Commons.waitForLoad(driver);
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			test.log(LogStatus.INFO, "self pay case is visible in view charges");
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			elements = driver.findElements(By.xpath(xpath));
			Assert.assertEquals(elements.size(), 2);
			int flag1 = 2, flag2 = 3;
			for (WebElement element : elements) {
				if (element.getText().contains("40")) {
					flag1 = 1;
				}
				if (element.getText().contains("27")) {
					flag2 = 1;
				}
			}
			Assert.assertTrue(flag1 == flag2);
			test.log(LogStatus.INFO,
					"charge added on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
			test.log(LogStatus.INFO, "Test:::: TestMutipleCancelPolicyChargeQueueApproval Completed as pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMutipleCancelPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMutipleCancelPolicyChargeQueueApproval Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
