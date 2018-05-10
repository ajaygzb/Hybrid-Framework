package com.bms.M6.Scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.text.DateFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.ChangeCompanyUtils;
import Utils.Commons;

public class TestProvierHomePage extends TestSetUp {
	@Test(enabled = true, priority = 9)
	public void testProviderhomepagedisplayasBMSuserforcompanythatisNOTsetasdefault() {
		test = extent.startTest("To Verify Provider home page displayas BMS user for company that is NOTset as default",
				"Test Provier Home Page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, "AutomationBMSProvider", "Bmspass123");
			System.out.println("Creating appointmnet in default company ");
			test.log(LogStatus.INFO, "Creating appointmnet in default company ");
			AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
			 AddScheduleUtils.ScheduleAppointment(driver);
			 String patientdefaultcompany =ActionUtils.getText(driver.findElement(By.xpath("//*[@id='patientFirstName']")));
			System.out.println("name is                     ::::::::::::::::::::::" + patientdefaultcompany);
			String namesplit[] = patientdefaultcompany.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("added patient is :" + newName);
			System.out.println("Appointmnet is created in default company");
			test.log(LogStatus.INFO, "Appointmnet is created in default company");
			System.out.println("Changing company ");
			test.log(LogStatus.INFO, "changing company ");
			ChangeCompanyUtils.changeCompany(driver, Commons.readPropertyValue("company1"));
			System.out.println("Creating appointmnet in switched company ");
			test.log(LogStatus.INFO, "Creating appointmnet in switched company ");
			AddScheduleUtils.ScheduleAppointment(driver);
			String patientswitchedCompany =ActionUtils.getText(driver.findElement(By.xpath("//*[@id='patientFirstName']")));
			System.out.println("name is                     ::::::::::::::::::::::" + patientswitchedCompany);
			String namesplit1[] = patientswitchedCompany.split(" ");
			String newName1 = namesplit1[0] + " " + namesplit1[1];
			System.out.println("added patient is :" + newName1);
			System.out.println("Appointmnet is created in Switched company");
			test.log(LogStatus.INFO, "Appointmnet is created in Switched company");
			System.out.println("Switching to default company");
			test.log(LogStatus.INFO, "Switching to default company");
			ChangeCompanyUtils.changeCompany(driver, "zzx");
			System.out.println("testing appointmnet created on home page of default comany");
			test.log(LogStatus.INFO, "testing appointmnet created on home page of default company");
			Commons.waitForLoad(driver);
			System.out.println("checking appontmnet of patient :" + newName + "on home page ");
			String xpath = "//u[contains(.,'" + newName + "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath(xpath), 50));
			System.out.println("Patient is found on default company provider home page ");
			test.log(LogStatus.INFO, "Patient is found on default company provider home page ");
			ChangeCompanyUtils.changeCompany(driver, Commons.readPropertyValue("company1"));
			Commons.waitForLoad(driver);
			System.out.println("checking appontmnet of patient :" + newName1 + "on home page ");
			xpath = "//u[contains(.,'" + newName1 + "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath(xpath), 50));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			System.out.println("Patient is found on other company provider home page ");
			test.log(LogStatus.INFO, "Patient is found on other company provider home page ");
			test.log(LogStatus.INFO,
					"Test:::: testProviderhomepagedisplayasBMSuserforcompanythatisNOTsetasdefault Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: testProviderhomepagedisplayasBMSuserforcompanythatisNOTsetasdefault Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: testProviderhomepagedisplayasBMSuserforcompanythatisNOTsetasdefault Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 10)
	public void testProviderHomePageAuthorizedVisitsRemainingvalidation() {
		test = extent.startTest("To Verify Provider Home Page Authorized Visits Remaining validation",
				"Test Provier Home Page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddPatientUtils.addpatient_withallfields(driver, "CaseCompleted");// Add
																				// Patient
																				// all
																				// Details
			String patient_id = ActionUtils.getText(AddPatientPage.patientID(driver));
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[2];
			System.out.println("new name on home page is :" + newName);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCode(driver);
			/*
			 * AddCaseUtils.ClickCaseSave(driver);
			 * AddCaseUtils.ClickCaseEdit(driver);
			 */
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			Thread.sleep(5000);
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.sendKeys(driver.findElement(By.name("preAuthNumber")), "preauth001");
			Commons.waitForLoad(driver);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate));
			ActionUtils.sendKeys(driver.findElement(By.name("preAuthFromDate0")), dtf.format(localDate));
			ActionUtils.sendKeys(driver.findElement(By.name("preAuthThroughDate0")), dtf.format(localDate));
			ActionUtils.sendKeys(driver.findElement(By.name("preAuthVisitUsed0")), "2");
			Thread.sleep(5000);
			AddCaseUtils.ClickCaseSave(driver);
			Thread.sleep(5000);
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Commons.waitforElement(driver, By.id("clearPatient"), 120);
			driver.findElement(By.id("clearPatient")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='patientName']//input[@placeholder='Patient Search']"))
					.sendKeys(patient_id);
			Commons.waitforElement(driver,
					By.xpath(
							"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//md-virtual-repeat-container[@ng-mouseenter='$mdAutocompleteCtrl.listEnter()']//li//span")));
			Commons.waitforElement(driver, By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span"), 120);
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//div[@id='caseDropDown']//td/span/span/span[2]/span")));
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]"),
					120);
			ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='cases-list']//li[contains(text(),'Case With All Fields')]")));
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[6]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			String numberofpreauthremaining = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("number of preauth remaining " + numberofpreauthremaining);
			Assert.assertTrue(numberofpreauthremaining.equalsIgnoreCase("2"));
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			AddChargeUtils.NavigateToAddChargePage(driver);
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			Commons.waitForLoad(driver);
			ActionUtils.sendKeys(driver.findElement(By.id("txtDOS")), dtf.format(localDate));
			ActionUtils.sendKeys(driver.findElement(By.id("txtPlanOfCare")), dtf.format(localDate));
			Select dropdown = new Select(driver.findElement(By.id("ddlPreAuth")));
			dropdown.selectByIndex(1);
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			Thread.sleep(3000);
			driver.findElement(By.id("btnSaveCharge")).click();
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			Commons.waitForLoad(driver);
			xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[6]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			numberofpreauthremaining = ActionUtils.getText(driver.findElement(By.xpath(xpath)));
			System.out.println("number of preauth remaining " + numberofpreauthremaining);
			Assert.assertTrue(numberofpreauthremaining.equalsIgnoreCase("1"));
			xpath = "//u[contains(.,'" + newName
					+ "')]/ancestor::span/parent::td/following-sibling::td[6]/span[@class='warning-sign']";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			test.log(LogStatus.INFO, "pre auth count is reduced to one and red dot is present ");
			test.log(LogStatus.INFO,
					"Test:::: testProviderHomePageAuthorizedVisitsRemainingvalidation Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: testProviderHomePageAuthorizedVisitsRemainingvalidation Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO,
					"Test:::: testProviderHomePageAuthorizedVisitsRemainingvalidation Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void testProviderHomeNoShowAppointmnet() {
		test = extent.startTest("To Verify Provider Home Page show : no show appointmnet ", "Test Provier Home Page"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addnoShowappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("typeCheck")));
			test.log(LogStatus.INFO, "clicked check box :  Include Canceled, No Show, and Rescheduled");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("NOSHOW"));
			test.log(LogStatus.INFO, "Now show appointment is visible on home page ");
			test.log(LogStatus.INFO, "Test:::: testProviderHomeNoShowAppointmnet Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeNoShowAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeNoShowAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void testProviderHomecancelAppointmnet() {
		test = extent.startTest("To Verify Provider Home Page show :  cancel appointmnet ", "Test Provier Home Page"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("typeCheck")));
			test.log(LogStatus.INFO, "clicked check box :  Include Canceled, No Show, and Rescheduled");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("CANCEL"));
			test.log(LogStatus.INFO, "cancel appointment is visible on home page ");
			test.log(LogStatus.INFO, "Test:::: testProviderHomecancelAppointmnet Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomecancelAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomecancelAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void testProviderHomeRescheduleAppointmnet() {
		test = extent.startTest("To Verify Provider Home Page show :  reshedule appointmnet ", "Test Provier Home Page"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addrescheduleappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("typeCheck")));
			test.log(LogStatus.INFO, "clicked check box :  Include Canceled, No Show, and Rescheduled");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("RESCHED"));
			test.log(LogStatus.INFO, "Reshedule appointment is visible on home page ");
			test.log(LogStatus.INFO, "Test:::: testProviderHomeRescheduleAppointmnet Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeRescheduleAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeRescheduleAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 6)
	public void testProviderHomeVoidAppointmnet() {
		test = extent.startTest("To Verify Provider Home Page dont show :  void appointmnet ", "Test Provier Home Page"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addVoidAppointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("typeCheck")));
			test.log(LogStatus.INFO, "clicked check box :  Include Canceled, No Show, and Rescheduled");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
			test.log(LogStatus.INFO, "void appointment is not visible on home page ");
			test.log(LogStatus.INFO, "Test:::: testProviderHomeRescheduleAppointmnet Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeVoidAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeVoidAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 7)
	public void testProviderHomeArrivedAppointmnet() {
		test = extent.startTest("To Verify Provider Home Page show : Arrived appointmnet ", "Test Provier Home Page"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addArrivedAppointmnets(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
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
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeArrivedAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeArrivedAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 8)
	public void testProviderHomeScheduleAppointmnet() {
		test = extent.startTest("To Verify Provider Home Page show : Schedule appointmnet ", "Test Provier Home Page"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addScheduleAppointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("SCHEDULED"));
			test.log(LogStatus.INFO, "Schedule appointment is visible on home page ");
			test.log(LogStatus.INFO, "Test:::: testProviderHomeScheduleAppointmnet Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeScheduleAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testProviderHomeScheduleAppointmnet Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 1)
	public void testcancelcreatenoteoption() {
		test = extent.startTest("To Verify note created for cancel appointmnet is always of non visit type ",
				"Test Provier Home Page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addCancelappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("typeCheck")));
			test.log(LogStatus.INFO, "clicked check box :  Include Canceled, No Show, and Rescheduled");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("CANCEL"));
			xpath = "//u[contains(.,'" + newName + "')]/preceding::span[@class='clinicalNoteStatus note-red'][1]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			ActionUtils.click(driver.findElement(By.xpath(xpath)));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.name("ddlVisitType"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//select[@name='ddlVisitType']//option[@selected='selected']")).getText().contains("Non Visit"));
			String date = driver.findElement(By.xpath("//*[@id='DateOfService']/div/input")).getAttribute("value");
			System.out.println(date);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate date2 = LocalDate.now();
			System.out.println(date2.format(dtf));
			System.out.println(date);
			Assert.assertTrue(date.contains(date2.format(dtf)));
			System.out.println("dos is " + date);
			test.log(LogStatus.INFO, "note created for cancel appointmnet is always of non visit type");
			test.log(LogStatus.INFO, "Test:::: testcancelcreatenoteoption Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testcancelcreatenoteoption Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testcancelcreatenoteoption Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void testNoShowcreatenoteoption() {
		test = extent.startTest("To Verify note created for no show appointmnet is always of non visit type ",
				"Test Provier Home Page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addnoShowappointmnet(driver);
			Thread.sleep(2000);
			String patientname = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")));
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("typeCheck")));
			test.log(LogStatus.INFO, "clicked check box :  Include Canceled, No Show, and Rescheduled");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("NOSHOW"));
			xpath = "//u[contains(.,'" + newName + "')]//preceding::span[@class='clinicalNoteStatus note-red']";
			Commons.screenshot();
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			ActionUtils.click(driver.findElement(By.xpath(xpath)));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.name("ddlVisitType"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//select[@name='ddlVisitType']//option[@selected='selected']")).getText().contains("Non Visit"));
			String date = driver.findElement(By.xpath("//*[@id='DateOfService']/div/input")).getAttribute("value");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate date2 = LocalDate.now();
			Assert.assertTrue(date.contains(date2.format(dtf)));
			System.out.println("dos is " + date);
			test.log(LogStatus.INFO, "note created for no show appointmnet is always of non visit type");
			test.log(LogStatus.INFO, "Test:::: testNoShowcreatenoteoption Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testNoShowcreatenoteoption Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testNoShowcreatenoteoption Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 11)
	public void testrescheduledcreatenoteoption() {
		test = extent.startTest("To Verify note created for reschedule appointmnet is always of non visit type ",
				"Test Provier Home Page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			AddScheduleUtils.addrescheduleappointmnet(driver);
			Thread.sleep(2000);
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("new name on home page is :" + newName);
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.id("typeCheck")));
			test.log(LogStatus.INFO, "clicked check box :  Include Canceled, No Show, and Rescheduled");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
			String Status = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Satus of patient :" + newName + "is :" + Status);
			Assert.assertTrue(Status.contains("RESCHED"));
			xpath = "//u[contains(.,'" + newName + "')]/preceding::span[@class='clinicalNoteStatus note-red'][1]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			ActionUtils.click(driver.findElement(By.xpath(xpath)));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.name("ddlVisitType"), 120);
			Assert.assertTrue(driver.findElement(By.xpath("//select[@name='ddlVisitType']//option[@selected='selected']")).getText().contains("Non Visit"));
			String date = driver.findElement(By.xpath("//*[@id='DateOfService']/div/input")).getAttribute("value");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate date2 = LocalDate.now();
			Assert.assertTrue(date.contains(date2.format(dtf)));
			System.out.println("dos is " + date);
			test.log(LogStatus.INFO,
					"note created for Reschedule appointmnet is always of non visit type and dos is appointment date ");
			test.log(LogStatus.INFO, "Test:::: testrescheduledcreatenoteoption Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testrescheduledcreatenoteoption Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testrescheduledcreatenoteoption Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
