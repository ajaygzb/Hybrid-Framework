package com.bms.M15.ArrivalAlert;

import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestArrivalAlert extends TestSetUp {
	/*
	 * Test-10 To validate red dot over alert button Test-11 To validate
	 * Additional Arrival Alerts settings Test-12 To validate popup displays
	 * Arival alert Test-13 To validate dimissed alert are not coming in Alert
	 * Popup list Test-14 To validate The functioning of Dismiss All
	 */
	@Test(enabled = true, priority = 1)
	public void TestArrivalAlertIconNotPresentOnNonProviderPage() {
		test = extent.startTest("To Verify Arrival alert con is not present for non provider", "Test arrival alert "
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userNonProvider"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "waiting for Arrival alert icon");
			Assert.assertFalse(Commons.existsElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]")));
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestArrivalAlertIconNotPresentOnNonProviderPage Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestArrivalAlertIconNotPresentOnNonProviderPage Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDDismissFunctionality() {
		test = extent.startTest(
				"To Verify arrival Alert pop up pops on Screen when Additional Arrival alerts are checked and on clicking on dismiss button alert dismiss",
				"Test arrival alert " + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String newName = null;
		String revname = null;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userArrivalAlert1"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "waiting for Arrival alert icon");
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90));
			/* String currentWindow = driver.getWindowHandle(); */
			try {
				test.log(LogStatus.INFO, "log with different user in different tab");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				WebDriver driver = new ChromeDriver(options);
				driver.get(Commons.readPropertyValue("URL"));
				Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
						Commons.readPropertyValue("password"));
				Commons.waitForLoad(driver);
				ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
				ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				System.out.println("Opened View Schedule page");
				Thread.sleep(10000);
				Select dropdown = new Select(driver.findElement(By.id("dropdown-view-mode-value")));
				dropdown.selectByVisibleText(Commons.readPropertyValue("userArrivalAlert1"));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				// Appointment
				test.log(LogStatus.INFO, "creating arrival appointment");
				AddScheduleUtils.GotoAppointmentWindow(driver);
				ActionUtils.click(SchedulerPage.QuickAddPatientbutton(driver));
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
				ActionUtils.sendKeys(SchedulerPage.firstname(driver),
						AddPatientUtils.strFirstNametemp + new Random().nextInt(99999));
				ActionUtils.sendKeys(SchedulerPage.lastname(driver),
						AddPatientUtils.strLastNametemp + new Random().nextInt(99999));
				ActionUtils.sendKeys(SchedulerPage.homenumber(driver), AddPatientUtils.strPhonetemp);
				ActionUtils.click(SchedulerPage.gender(driver));
				Commons.Explicitwait();
				driver.findElement(By.name("dateofBirth")).sendKeys("10/10/2016");
				ActionUtils.sendKeys(SchedulerPage.casename(driver), "Case" + new Random().nextInt(1000));
				ActionUtils.click(SchedulerPage.PatientSave(driver));
				driver.switchTo().defaultContent();
				Commons.waitForElementToBeNotVisible(driver,
						By.xpath(
								"//div[contains(@class,'quickAddWindow')]//span[@id='quickAddPatientWindow_wnd_title']"),
						150);
				System.out.println("Patient Add steps completed");
				System.out.println("Navigate back to appointment window...");
				Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 15);
				AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
				ActionUtils.click(SchedulerPage.Typefield(driver));
				ActionUtils.click(SchedulerPage.StatusfieldWithArrived(driver));
				Thread.sleep(3000);
				ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Arrived Notes");
				AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
				String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
				System.out.println("name is                     ::::::::::::::::::::::" + patientname);
				String namesplit[] = patientname.split(" ");
				newName = namesplit[0] + " " + namesplit[1];
				revname = namesplit[1] + " , " + namesplit[0];
				System.out.println("new name on home page is :" + newName);
				driver.quit();
				test.log(LogStatus.INFO, "Arrived appointment is created for patient" + newName);
			} catch (Exception e) {
				test.log(LogStatus.INFO, " Error!!!" + e);
				test.log(LogStatus.INFO,
						"Test:::: TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDDismissFunctionality Completed as Fail");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "checking arrival alert pop ");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//div[@id='patientArrivedPopUp']//div[contains(text(),'Arrived')]"), 90);
			String message = driver
					.findElement(By.xpath("//div[@id='patientArrivedPopUp']//div[contains(text(),'Arrived')]"))
					.getText();
			System.out.println(" Arrived alert message is " + message);
			test.log(LogStatus.INFO, "arrived alert message is " + message);
			Assert.assertTrue(message.contains(newName));
			test.log(LogStatus.INFO, "got arrival alert pop up ");
			test.log(LogStatus.INFO,
					"Clicking on dismiss button and checking no data should be in avviral alert notification window ");
			ActionUtils.click(driver.findElement(By.id("dismissArrivedAppointment")));
			test.log(LogStatus.INFO,
					"Clicking on arrival alert icon and checking no data should be in avviral alert notification window ");
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'Arrival Alerts')]")));
			Thread.sleep(2000);
			String xpath = "//div[@id='arrivalAlertsGrid']//div[2]//tbody/tr/td[3]/span[contains(text(),'" + revname
					+ "')]";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO,
					"on creating appointment arrival alert pop pops on screen and get dismiss on pressing dismiss ");
			Commons.waitforElement(driver, By.id("arrivalAlertOkButton"), 120);
			ActionUtils.click(driver.findElement(By.id("arrivalAlertOkButton")));
			test.log(LogStatus.INFO, "test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDDismissFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDDismissFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDCancelFunctionality() {
		test = extent.startTest(
				"To Verify arrival Alert pop up pops on Screen when Additional Arrival alerts are checked and on clicking on calncel button alert dont dismiss",
				"Test arrival alert " + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String newName = null;
		String revname = null;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userArrivalAlert1"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "waiting for Arrival alert icon");
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90));
			/* String currentWindow = driver.getWindowHandle(); */
			try {
				test.log(LogStatus.INFO, "log with different user in different tab");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				WebDriver driver = new ChromeDriver(options);
				driver.get(Commons.readPropertyValue("URL"));
				Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
						Commons.readPropertyValue("password"));
				Commons.waitForLoad(driver);
				ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
				ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				System.out.println("Opened View Schedule page");
				Thread.sleep(10000);
				Select dropdown = new Select(driver.findElement(By.id("dropdown-view-mode-value")));
				dropdown.selectByVisibleText(Commons.readPropertyValue("userArrivalAlert1"));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				// Appointment
				test.log(LogStatus.INFO, "creating arrival appointment");
				AddScheduleUtils.GotoAppointmentWindow(driver);
				ActionUtils.click(SchedulerPage.QuickAddPatientbutton(driver));
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
				ActionUtils.sendKeys(SchedulerPage.firstname(driver),
						AddPatientUtils.strFirstNametemp + new Random().nextInt(99999));
				ActionUtils.sendKeys(SchedulerPage.lastname(driver),
						AddPatientUtils.strLastNametemp + new Random().nextInt(99999));
				ActionUtils.sendKeys(SchedulerPage.homenumber(driver), AddPatientUtils.strPhonetemp);
				ActionUtils.click(SchedulerPage.gender(driver));
				Commons.Explicitwait();
				driver.findElement(By.name("dateofBirth")).sendKeys("10/10/2016");
				ActionUtils.sendKeys(SchedulerPage.casename(driver), "Case" + new Random().nextInt(1000));
				ActionUtils.click(SchedulerPage.PatientSave(driver));
				driver.switchTo().defaultContent();
				Commons.waitForElementToBeNotVisible(driver,
						By.xpath(
								"//div[contains(@class,'quickAddWindow')]//span[@id='quickAddPatientWindow_wnd_title']"),
						150);
				System.out.println("Patient Add steps completed");
				System.out.println("Navigate back to appointment window...");
				Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 15);
				AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
				ActionUtils.click(SchedulerPage.Typefield(driver));
				ActionUtils.click(SchedulerPage.StatusfieldWithArrived(driver));
				Thread.sleep(3000);
				ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Arrived Notes");
				AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
				String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
				System.out.println("name is                     ::::::::::::::::::::::" + patientname);
				String namesplit[] = patientname.split(" ");
				newName = namesplit[0] + " " + namesplit[1];
				revname = namesplit[1] + " , " + namesplit[0];
				System.out.println("new name on home page is :" + newName);
				driver.quit();
				test.log(LogStatus.INFO, "Arrived appointment is created for patient" + newName);
			} catch (Exception e) {
				test.log(LogStatus.INFO, " Error!!!" + e);
				test.log(LogStatus.INFO,
						"Test:::: TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDCancelFunctionality Completed as Fail");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "checking arrival alert pop ");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//div[@id='patientArrivedPopUp']//div[contains(text(),'Arrived')]"), 90);
			String message = driver
					.findElement(By.xpath("//div[@id='patientArrivedPopUp']//div[contains(text(),'Arrived')]"))
					.getText();
			System.out.println(" Arrived alert message is " + message);
			test.log(LogStatus.INFO, "arrived alert message is " + message);
			Assert.assertTrue(message.contains(newName));
			test.log(LogStatus.INFO, "got arrival alert pop up ");
			test.log(LogStatus.INFO,
					"Clicking on cancel button and checking data should be in avviral alert notification window ");
			ActionUtils.click(driver.findElement(
					By.xpath("//button[@ng-click='vmIndex.closeArrivedPopup(vmIndex.arrivedPatientDetails)']")));
			test.log(LogStatus.INFO,
					"Clicking on arrival alert icon and checking data should be in avviral alert notification window ");
			test.log(LogStatus.INFO, "checking red dot and getting number of alerts");
			Commons.waitforElement(driver, By.xpath("//span[@class='count ng-binding ng-scope']"), 90);
			System.out.println("number of arrived appoinments are:"
					+ driver.findElement(By.xpath("//span[@class='count ng-binding ng-scope']")).getText());
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'Arrival Alerts')]")));
			Thread.sleep(2000);
			String xpath = "//div[@id='arrivalAlertsGrid']//div[2]//tbody/tr/td[3]/span[contains(text(),'" + revname
					+ "')]";
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath(xpath), 90));
			test.log(LogStatus.INFO,
					"on creating appointment arrival alert pop  pops on screen and  pressing cancel it stays in window  ");
			Commons.waitforElement(driver, By.xpath(" //div[@id='arrivalAlertPopup']//button[contains(.,'×')]"), 120);
			ActionUtils.click(driver.findElement(By.xpath(" //div[@id='arrivalAlertPopup']//button[contains(.,'×')]")));
			test.log(LogStatus.INFO, "test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDCancelFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestArrivalAlertPopupComeWhenAdditionalAlertsareCheckedAnDCancelFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void TestArrivalAlertPopupDontComeWhenAdditionalAlertsareUnCheckedAndDismissFunctionality() {
		test = extent.startTest(
				"To Verify arrival Alert pop up dont pops on Screen when Additional Arrival alerts are Unchecked and on clicking on dismiss button alert  dismiss",
				"Test arrival alert " + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String newName = null;
		String revname = null;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userArrivalAlert2"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "waiting for Arrival alert icon");
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90));
			/* String currentWindow = driver.getWindowHandle(); */
			try {
				test.log(LogStatus.INFO, "log with different user in different tab");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				WebDriver driver = new ChromeDriver(options);
				driver.get(Commons.readPropertyValue("URL"));
				Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
						Commons.readPropertyValue("password"));
				Commons.waitForLoad(driver);
				ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
				ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				System.out.println("Opened View Schedule page");
				Thread.sleep(10000);
				Select dropdown = new Select(driver.findElement(By.id("dropdown-view-mode-value")));
				dropdown.selectByVisibleText(Commons.readPropertyValue("userArrivalAlert2"));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				// Appointment
				test.log(LogStatus.INFO, "creating arrival appointment");
				AddScheduleUtils.GotoAppointmentWindow(driver);
				ActionUtils.click(SchedulerPage.QuickAddPatientbutton(driver));
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
				ActionUtils.sendKeys(SchedulerPage.firstname(driver),
						AddPatientUtils.strFirstNametemp + new Random().nextInt(99999));
				ActionUtils.sendKeys(SchedulerPage.lastname(driver),
						AddPatientUtils.strLastNametemp + new Random().nextInt(99999));
				ActionUtils.sendKeys(SchedulerPage.homenumber(driver), AddPatientUtils.strPhonetemp);
				ActionUtils.click(SchedulerPage.gender(driver));
				Commons.Explicitwait();
				driver.findElement(By.name("dateofBirth")).sendKeys("10/10/2016");
				ActionUtils.sendKeys(SchedulerPage.casename(driver), "Case" + new Random().nextInt(1000));
				ActionUtils.click(SchedulerPage.PatientSave(driver));
				driver.switchTo().defaultContent();
				Commons.waitForElementToBeNotVisible(driver,
						By.xpath(
								"//div[contains(@class,'quickAddWindow')]//span[@id='quickAddPatientWindow_wnd_title']"),
						150);
				System.out.println("Patient Add steps completed");
				System.out.println("Navigate back to appointment window...");
				Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 15);
				AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
				ActionUtils.click(SchedulerPage.Typefield(driver));
				ActionUtils.click(SchedulerPage.StatusfieldWithArrived(driver));
				Thread.sleep(3000);
				ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Arrived Notes");
				AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
				String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
				System.out.println("name is                     ::::::::::::::::::::::" + patientname);
				String namesplit[] = patientname.split(" ");
				newName = namesplit[0] + " " + namesplit[1];
				revname = namesplit[1] + " , " + namesplit[0];
				System.out.println("new name on home page is :" + newName);
				driver.quit();
				test.log(LogStatus.INFO, "Arrived appointment is created for patient" + newName);
			} catch (Exception e) {
				test.log(LogStatus.INFO, " Error!!!" + e);
				test.log(LogStatus.INFO,
						"Test:::: TestArrivalAlertPopupDontComeWhenAdditionalAlertsareUnCheckedAndDismissFunctionality Completed as Fail");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "checking arrival alert pop ");
			Commons.waitForLoad(driver);
			Assert.assertFalse(Commons.existsElement(driver,
					By.xpath("//div[@id='patientArrivedPopUp']//div[contains(text(),'Arrived')]")));
			test.log(LogStatus.INFO, "arrival alert dont pop up ");
			test.log(LogStatus.INFO,
					"Clicking on arrival alert icon and checking data should be in arrival alert notification window ");
			test.log(LogStatus.INFO, "checking red dot and getting number of alerts");
			Commons.waitforElement(driver, By.xpath("//span[@class='count ng-binding ng-scope']"), 90);
			System.out.println("number of arrived appoinments are:"
					+ driver.findElement(By.xpath("//span[@class='count ng-binding ng-scope']")).getText());
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'Arrival Alerts')]")));
			Thread.sleep(2000);
			String xpath = "//div[@id='arrivalAlertsGrid']//div[2]//tbody/tr/td[3]/span[contains(text(),'" + revname
					+ "')]";
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath(xpath), 90));
			xpath = "//div[@id='arrivalAlertsGrid']//div[2]//tbody/tr/td[3]/span[contains(text(),'" + revname
					+ "')]/ancestor::tr/td/input";
			ActionUtils.click(driver.findElement(By.xpath(xpath)));
			Commons.waitForLoad(driver);
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO,
					"on creating appointment arrival alert pop dont pops on screen and  get dismiss on pressing dismiss ");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestArrivalAlertPopupDontComeWhenAdditionalAlertsareUnCheckedAndDismissFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: TestArrivalAlertPopupDontComeWhenAdditionalAlertsareUnCheckedAndDismissFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void TestArrivalAlertDismissAllFunctionality() {
		test = extent.startTest("To Verify arrival Alert Dismiss All Functionality ", "Test arrival alert "
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		LinkedList<String> newName = new LinkedList<String>();
		LinkedList<String> revname = new LinkedList<String>();
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userArrivalAlert2"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "waiting for Arrival alert icon");
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90));
			/* String currentWindow = driver.getWindowHandle(); */
			try {
				test.log(LogStatus.INFO, "log with different user in different tab");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				WebDriver driver = new ChromeDriver(options);
				driver.get(Commons.readPropertyValue("URL"));
				Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
						Commons.readPropertyValue("password"));
				Commons.waitForLoad(driver);
				ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
				ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				System.out.println("Opened View Schedule page");
				Thread.sleep(10000);
				Select dropdown = new Select(driver.findElement(By.id("dropdown-view-mode-value")));
				dropdown.selectByVisibleText(Commons.readPropertyValue("userArrivalAlert2"));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
				// Appointment
				for (int i = 0; i < 3; i++) {
					test.log(LogStatus.INFO, "creating arrival appointment");
					AddScheduleUtils.GotoAppointmentWindow(driver);
					ActionUtils.click(SchedulerPage.QuickAddPatientbutton(driver));
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
					ActionUtils.sendKeys(SchedulerPage.firstname(driver),
							AddPatientUtils.strFirstNametemp + new Random().nextInt(99999));
					ActionUtils.sendKeys(SchedulerPage.lastname(driver),
							AddPatientUtils.strLastNametemp + new Random().nextInt(99999));
					ActionUtils.sendKeys(SchedulerPage.homenumber(driver), AddPatientUtils.strPhonetemp);
					ActionUtils.click(SchedulerPage.gender(driver));
					Commons.Explicitwait();
					driver.findElement(By.name("dateofBirth")).sendKeys("10/10/2016");
					ActionUtils.sendKeys(SchedulerPage.casename(driver), "Case" + new Random().nextInt(1000));
					ActionUtils.click(SchedulerPage.PatientSave(driver));
					driver.switchTo().defaultContent();
					Commons.waitForElementToBeNotVisible(driver,
							By.xpath(
									"//div[contains(@class,'quickAddWindow')]//span[@id='quickAddPatientWindow_wnd_title']"),
							150);
					System.out.println("Patient Add steps completed");
					System.out.println("Navigate back to appointment window...");
					Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 15);
					AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
					ActionUtils.click(SchedulerPage.Typefield(driver));
					ActionUtils.click(SchedulerPage.StatusfieldWithArrived(driver));
					Thread.sleep(3000);
					ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver),
							"This Is a Test String For Arrived Notes");
					AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
					String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
					System.out.println("name is                     ::::::::::::::::::::::" + patientname);
					String namesplit[] = patientname.split(" ");
					newName.add(namesplit[0] + " " + namesplit[1]);
					revname.add(namesplit[1] + " , " + namesplit[0]);
					System.out.println("new name on home page is :" + newName.get(i));
				}
				driver.quit();
				test.log(LogStatus.INFO, "Arrived appointment is created for patient" + newName);
			} catch (Exception e) {
				test.log(LogStatus.INFO, " Error!!!" + e);
				test.log(LogStatus.INFO, "Test:::: TestArrivalAlertDismissAllFunctionality Completed as Fail");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "checking arrival alert pop ");
			Commons.waitForLoad(driver);
			Assert.assertFalse(Commons.existsElement(driver,
					By.xpath("//div[@id='patientArrivedPopUp']//div[contains(text(),'Arrived')]")));
			test.log(LogStatus.INFO, "arrival alert dont pop up ");
			test.log(LogStatus.INFO,
					"Clicking on arrival alert icon and checking data should be in arrival alert notification window ");
			test.log(LogStatus.INFO, "checking red dot and getting number of alerts");
			Commons.waitforElement(driver, By.xpath("//span[@class='count ng-binding ng-scope']"), 90);
			System.out.println("number of arrived appoinments are:"
					+ driver.findElement(By.xpath("//span[@class='count ng-binding ng-scope']")).getText());
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Arrival Alerts')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'Arrival Alerts')]")));
			Thread.sleep(2000);
			for (int i = 0; i < 3; i++) {
				String xpath = "//div[@id='arrivalAlertsGrid']//div[2]//tbody/tr/td[3]/span[contains(text(),'"
						+ revname.get(i) + "')]";
				Assert.assertTrue(Commons.waitforElement(driver, By.xpath(xpath), 90));
			}
			Commons.waitforElement(driver, By.xpath("//input[@value='Dismiss All']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Dismiss All']")));
			Commons.waitForLoad(driver);
			for (int i = 0; i < 3; i++) {
				String xpath = "//div[@id='arrivalAlertsGrid']//div[2]//tbody/tr/td[3]/span[contains(text(),'"
						+ revname.get(i) + "')]";
				Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			}
			test.log(LogStatus.INFO, "On pressing Dismiss All , All records flush out off window ");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestArrivalAlertDismissAllFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestArrivalAlertDismissAllFunctionality Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
