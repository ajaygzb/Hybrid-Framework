package com.bms.M15.ArrivalAlert;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.PolicyChargeUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestDifferentTypeOfAppointmentsOnPracticeView extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void TestCancelationOnPracticeView() {
		test = extent.startTest(
				"To Verify a self pay case is created if user approve charge added in policy charge queue , Practice view (cancel)",
				"Test Cancelation On Practice View" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient ");
			AddPatientUtils.QuickAddpatient(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new patient added is :" + newName);
			test.log(LogStatus.INFO, "new patient added is :" + newName);
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(3000);
			// selecting Practice view
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode']"), 120);
			Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode']")));
			dropdown.selectByVisibleText("Practice View");
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode-value']"), 120);
			Thread.sleep(3000);
			dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode-value']")));
			dropdown.selectByVisibleText("BMS_Automation_Practice");
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			System.out.println("open Practice view of patient :" + newName);
			test.log(LogStatus.INFO, "open Practice view of patient :" + newName);
			System.out.println("creating cancel appointmnet of patient :" + newName);
			test.log(LogStatus.INFO, "creating cancel appointmnet of patient :" + newName);
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.click(SchedulerPage.LocationSelection(driver, "BMS_Automation_2"));
			ActionUtils.click(SchedulerPage.ProviderSelection(driver, "AutomationPolicyCharge"));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithcancel(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.cancelReason(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For cancel Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			System.out.println("approving charges form policy charge queue ");
			test.log(LogStatus.INFO, "approving charges form policy charge queue ");
			PolicyChargeUtils.ApproveCharges(driver, newName);
			System.out.println("charges are approved form policy charge queue ");
			test.log(LogStatus.INFO, "charges are approved  form policy charge queue ");
			System.out.println("checking self pay case is created and charges are added ");
			test.log(LogStatus.INFO, "checking self pay case is created and charges are added ");
			PolicyChargeUtils.ViewcancellationCharges(driver, patientid);
			System.out.println("self pay case is created and charges are added successfully , hence test case is pass");
			test.log(LogStatus.INFO,
					"self pay case is created and charges are added successfully , hence test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCancelationOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCancelationOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestNoShowOnPracticeView() {
		test = extent.startTest(
				"To Verify a self pay case is created if user approve charge added in policy charge queue , on patient view (no show )",
				"Test Cancelation On Practice View" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient ");
			AddPatientUtils.QuickAddpatient(driver);
			/*
			 * // adding case AddCaseUtils.GoToAddNewCase(driver);
			 * ActionUtils.sendKeys(CasePage.CaseName(driver),
			 * "Case With All Fields");
			 * ActionUtils.click(CasePage.InjuryTypeAsNone(driver));
			 * HandlingCalendars.datepick(driver,CasePage.OnsetDate(driver),
			 * "15/03/2016");// Enter injury onset date
			 * HandlingCalendars.datepick(driver,
			 * CasePage.StartofCareDate(driver), "15/04/2016");//Enter Start
			 * care of date HandlingCalendars.datepick(driver,
			 * CasePage.AssignmentDate(driver), "15/05/2016");//Enter assignment
			 * date HandlingCalendars.datepick(driver,
			 * CasePage.CaseEffectiveDate(driver), "15/06/2016");//enter Case
			 * effective date
			 * ActionUtils.click(CasePage.ReferringPhysicianMagnifier(driver));
			 * ActionUtils.click(CasePage.ReferringPhysicianSearchButton(driver)
			 * ); ActionUtils.doubleClick(driver,
			 * CasePage.ReferringPhysicianList(driver)); //adding location
			 * ActionUtils.click(CasePage.LocationMagnifier(driver));
			 * ActionUtils.sendKeys(driver.findElement(By.id("Location_Name")),
			 * "BMS_Automation_2");
			 * ActionUtils.click(CasePage.LocationSearchButton(driver));
			 * ActionUtils.doubleClick(driver, driver.findElement(By.xpath(
			 * "//div[@id='LocationId']//tbody//tr/td"))); //adding billing
			 * provider
			 * ActionUtils.click(CasePage.BillingProviderMagnifier(driver));
			 * ActionUtils.sendKeys(driver.findElement(By.id("Provider_Name")),
			 * "AutomationPolicyCharge");
			 * ActionUtils.click(CasePage.BillingProviderSearchButton(driver));
			 * ActionUtils.doubleClick(driver,driver.findElement(By.xpath(
			 * "//div[@id='BillingId']//tbody//tr/td"))); //saving case
			 * Thread.sleep(5000); Commons.waitForLoad(driver);
			 * AddCaseUtils.ClickCaseSave(driver);
			 */
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			String patientid = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[1] + ", " + namesplit[0];
			System.out.println("new patient added is :" + newName);
			test.log(LogStatus.INFO, "new patient added is :" + newName);
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(3000);
			// selecting Practice view
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode']"), 120);
			Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode']")));
			dropdown.selectByVisibleText("Practice View");
			Commons.waitForLoad(driver);
			Thread.sleep(2000);
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode-value']"), 120);
			Thread.sleep(3000);
			dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode-value']")));
			dropdown.selectByVisibleText("BMS_Automation_Practice");
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			System.out.println("open Practice view of patient :" + newName);
			test.log(LogStatus.INFO, "open Practice view of patient :" + newName);
			System.out.println("creating no show appointmnet of patient :" + newName);
			test.log(LogStatus.INFO, "creating no show appointmnet of patient :" + newName);
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.click(SchedulerPage.LocationSelection(driver, "BMS_Automation_2"));
			ActionUtils.click(SchedulerPage.ProviderSelection(driver, "AutomationPolicyCharge"));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithNoShow(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.NoShowReasonForgot(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For no show Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			System.out.println("approving charges form policy charge queue ");
			test.log(LogStatus.INFO, "approving charges form policy charge queue ");
			PolicyChargeUtils.ApproveCharges(driver, newName);
			System.out.println("charges are approved form policy charge queue ");
			test.log(LogStatus.INFO, "charges are approved  form policy charge queue ");
			System.out.println("checking self pay case is created and charges are added ");
			test.log(LogStatus.INFO, "checking self pay case is created and charges are added ");
			PolicyChargeUtils.ViewNoShowCharges(driver, patientid);
			System.out.println("self pay case is created and charges are added successfully , hence test case is pass");
			test.log(LogStatus.INFO,
					"self pay case is created and charges are added successfully , hence test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNoShowOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestNoShowOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestGroupAppointmnetOnPracticeView() {
		test = extent.startTest("group appointmnet is created on Practice view ", "Test Cancelation On Practice View"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "naviagting to Practice view ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(3000);
			// selecting Practice view
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode']"), 120);
			Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode']")));
			dropdown.selectByVisibleText("Practice View");
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode-value']"), 120);
			Thread.sleep(3000);
			dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode-value']")));
			dropdown.selectByVisibleText("BMS_Automation_Practice");
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			System.out.println("open Practice view ");
			test.log(LogStatus.INFO, "open Practice view ");
			System.out.println("creating group appointmnet on Practice view ");
			test.log(LogStatus.INFO, "creating group appointment  on Practice view ");
			// adding appointment
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.QuickAddPatient(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.click(SchedulerPage.LocationSelection(driver, "BMS_Automation_2"));
			ActionUtils.click(SchedulerPage.ProviderSelection(driver, "AutomationPolicyCharge"));
			ActionUtils.JavaScriptclick(SchedulerPage.GroupTypefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For group Notes");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			AddScheduleUtils.QuickAddPatient(driver);
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
			Thread.sleep(3000);
			Commons.waitForLoad(driver);
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			// checking appointment at home page
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "group appintment is created and now navigating to home page ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "checking group appointment on providers home page");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//div[@id='scheduler']//tbody//tr//td[contains(text(),'Group')]"), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'",
					driver.findElement(By.xpath("//div[@id='scheduler']//tbody//tr//td[contains(text(),'Group')]")));
			test.log(LogStatus.INFO, "group appointment is created successfully , test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestGroupAppointmnetOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestGroupAppointmnetOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void TestArrivalAppointmnetOnPracticeView() {
		test = extent.startTest(
				"To Verify arrival Alert pop up pops on Screen when Additional Arrival alerts are checked and on clicking on dismiss button alert dismiss on Practice view",
				"Test Arrival Appointmnet On Practice View" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
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
				Thread.sleep(3000);
				// selecting Practice view
				Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode']"), 120);
				Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode']")));
				dropdown.selectByVisibleText("Practice View");
				Commons.waitForLoad(driver);
				Thread.sleep(3000);
				Commons.waitforElement(driver, By.xpath("//select[@id='dropdown-view-mode-value']"), 120);
				Thread.sleep(3000);
				dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-view-mode-value']")));
				dropdown.selectByVisibleText("BMS_Automation_Practice");
				Commons.waitForLoad(driver);
				Thread.sleep(3000);
				System.out.println("open Practice view ");
				test.log(LogStatus.INFO, "open Practice view ");
				System.out.println("creating group appointmnet on Practice view ");
				test.log(LogStatus.INFO, "creating group appointment  on Practice view ");
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
				ActionUtils.click(SchedulerPage.LocationSelection(driver, "Automation BMS"));
				ActionUtils.click(SchedulerPage.ProviderSelection(driver, "AutomationArivalAlert1"));
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
				test.log(LogStatus.INFO, "Test:::: TestArrivalAppointmnetOnPracticeView Completed as Fail");
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
			test.log(LogStatus.INFO, "Test:::: TestArrivalAppointmnetOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestArrivalAppointmnetOnPracticeView Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
