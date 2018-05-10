package com.bms.M15.ArrivalAlert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import Utils.PolicyChargeUtils;

public class TestGroupAppointment extends TestSetUp {
	@Test(enabled = true, priority = 6)
	public void TestCreationOfGroupAppointment() {
		test = extent.startTest("To Group Appointment is created ", "Test Group Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "log in with new provider and now navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(2000);
			test.log(LogStatus.INFO, "creating group appointmnet");
			AddScheduleUtils.addGroupAppointmnet(driver);
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "group appintment is created and now navigating to home page ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "checking group appointment on providers home page");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]"),
					120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(
					".//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]")));
			test.log(LogStatus.INFO, "group appointment is created successfully , test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCreationOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCreationOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void TestMaximumPatientAllowedInGroupAppointment() {
		test = extent.startTest("To test maximum patient allowed in Group appointment", "Test Group Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "log in with new provider and now navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(2000);
			test.log(LogStatus.INFO, "creating group appointmnet");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.QuickAddPatient(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.GroupTypefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For group Notes");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
			Commons.waitForLoad(driver);
			Thread.sleep(2000);
			ActionUtils.clear(driver.findElement(By.id("groupMaxCountTxt")));
			ActionUtils.sendKeys(driver.findElement(By.id("groupMaxCountTxt")), "2");
			Thread.sleep(2000);
			AddScheduleUtils.QuickAddPatient(driver);
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
			Thread.sleep(2000);
			AddScheduleUtils.QuickAddPatient(driver);
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
			Assert.assertTrue(
					Commons.waitForElementToBeVisible(driver, driver.findElement(By.id("toast-container")), 90));
			String message = driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-message']"))
					.getText();
			System.out.println("message on toaster is " + message);
			test.log(LogStatus.INFO, "message on toaster is " + message);
			Assert.assertEquals(message, "You may only add a maximum of 2 patients.");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "group appintment is created and now navigating to home page ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "checking group appointment on providers home page");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]"),
					120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(
					"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]")));
			test.log(LogStatus.INFO, "group appointment is created successfully , test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMaximumPatientAllowedInGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestMaximumPatientAllowedInGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestCancelationOfGroupAppointment() {
		test = extent.startTest("To test cancelation functionality of group appointment ", "Test Group Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "log in with new provider and now navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			int size = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size();
			System.out.println("number of group are :"
					+ driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size());
			test.log(LogStatus.INFO, "adding new group appointmnet");
		
			Utils.AddScheduleUtils.addGroupAppointmnet(driver);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "new group appointmnet is added ");
			List<WebElement> groups = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]"));
			System.out.println("number of Groups are " + groups.size());
			int newsize = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size();
			int i = 0;
			int finalgroup = 0;
			while (i != 100) {
				test.log(LogStatus.INFO, "editing group");
				System.out.println("Opening Existing Group with 2 patients..");
				Thread.sleep(5000);
				Commons.waitForLoad(driver);
				Commons.scrollElementinotView(
						driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).get(i),
						driver);
				ActionUtils.doubleClick(driver,
						driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).get(i));
				Thread.sleep(2000);
				Commons.waitforElement(driver,
						By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"), 120);
				if (driver.findElements(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"))
						.size() >= 2) {
					String fieldset = driver
							.findElement(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"))
							.getText();
					String splits[] = fieldset.split(" ");
					String name = splits[3] + " " + splits[4];
					String patientId = splits[1];
					System.out.println("patient added is  " + name + "patient id is :" + patientId);
					test.log(LogStatus.INFO, "marking patient as cancel :" + name);
					ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.MarkCancelledGroup($index)']")));
					Thread.sleep(2000);
					Commons.waitforElement(driver, By.id("CancelledGroupAppointmentDropDown"), 120);
					Select dropdown = new Select(driver.findElement(By.id("CancelledGroupAppointmentDropDown")));
					dropdown.selectByIndex(1);
					ActionUtils.click(driver.findElement(By.id("CancelledGroupAppointmentOk")));
					Thread.sleep(2000);
					Commons.waitForLoad(driver);
					Utils.AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
					test.log(LogStatus.INFO, "patient cancelled successfully is " + name + "patient id :" + patientId);
					test.log(LogStatus.INFO, "Approving charges");
					PolicyChargeUtils.ApproveCharges(driver, name);
					test.log(LogStatus.INFO, "checking self pay cases and charge added");
					PolicyChargeUtils.ViewcancellationCharges(driver, patientId);
					finalgroup = i;
					i = 100;
					test.log(LogStatus.INFO, "group appointment is cancelled for patient " + name + " successfully ");
				} else {
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")));
					i++;
				}
			}
			test.log(LogStatus.INFO, " now navigating to home page ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "checking group appointment on providers home page");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]/preceding::td//span[@class='agendaTime']"),
					120));
			ActionUtils.click(driver.findElement(By.xpath(
					"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]/preceding::td//span[@class='agendaTime']")));
			Thread.sleep(2000);
			ActionUtils.click(SchedulerPage.StatusfieldWithcancel(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For group Notes");
			ActionUtils.JavaScriptclick(SchedulerPage.cancelReason(driver));
			Thread.sleep(2000);
			Utils.AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			test.log(LogStatus.INFO, "added appointment is cancelled , test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCreationOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCreationOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void TestNoShowOfGroupAppointment() {
		test = extent.startTest("To test no show functionality of group appointment ", "Test Group Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			System.out.println("Checking default view");
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "log in with new provider and now navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			int size = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size();
			System.out.println("number of group are :"
					+ driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size());
			Utils.AddScheduleUtils.addGroupAppointmnet(driver);
			Commons.waitForLoad(driver);
			List<WebElement> groups = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]"));
			System.out.println("number of Groups are " + groups.size());
			int newsize = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size();
			int i = 0;
			int finalgroup = 0;
			while (i != 100) {
				Thread.sleep(5000);
				Commons.scrollElementinotView(
						driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).get(i),
						driver);
				ActionUtils.doubleClick(driver,
						driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).get(i));
				Thread.sleep(2000);
				try{
				Commons.waitforElement(driver,
						By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"),60);
				}catch(Exception e){
					
					Thread.sleep(5000);
					Commons.scrollElementinotView(
							driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).get(i),
							driver);
					ActionUtils.doubleClick(driver,
							driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).get(i));
					Thread.sleep(2000);
					
					
					
				}
				if (driver.findElements(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"))
						.size() >= 2) {
					String fieldset = driver
							.findElement(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"))
							.getText();
					String splits[] = fieldset.split(" ");
					String name = splits[3] + " " + splits[4];
					String patientId = splits[1];
					System.out.println("patient added is  " + name + "patient id is :" + patientId);
					ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.MarkNoShowGroup($index)']")));
					Thread.sleep(2000);
					Commons.waitforElement(driver, By.id("NoShowGroupAppointmentDropDown"), 120);
					Select dropdown = new Select(driver.findElement(By.id("NoShowGroupAppointmentDropDown")));
					dropdown.selectByIndex(1);
					ActionUtils.click(driver.findElement(By.id("NoShowGroupAppointmentOk")));
					Thread.sleep(2000);
					Commons.waitForLoad(driver);
					Utils.AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
					test.log(LogStatus.INFO, "patient cancelled successfully is " + name + "patient id :" + patientId);
					test.log(LogStatus.INFO, "Approving charges");
					PolicyChargeUtils.ApproveCharges(driver, name);
					test.log(LogStatus.INFO, "checking self pay cases and charge added");
					PolicyChargeUtils.ViewNoShowCharges(driver, patientId);
					finalgroup = i;
					i = 100;
					test.log(LogStatus.INFO, "group appointment is no show for patient " + name + " successfully ");
				} else {
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")));
					i++;
				}
			}
			test.log(LogStatus.INFO, " now navigating to home page ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "checking group appointment on providers home page");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]/preceding::td//span[@class='agendaTime']"),
					120));
			ActionUtils.click(driver.findElement(By.xpath(
					"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]/preceding::td//span[@class='agendaTime']")));
			Thread.sleep(2000);
			ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithNoShow(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.NoShowReasonForgot(driver));
			Thread.sleep(2000);
			Utils.AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			test.log(LogStatus.INFO, "added appointment is no show , test case is pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCreationOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestCreationOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void TestReoccurrenceOfGroupAppointment() {
		test = extent.startTest("To test reoccurrence of group appointment ", "Test Group Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "log in with new provider and now navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(2000);
			test.log(LogStatus.INFO, "creating group appointmnet");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.QuickAddPatient(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			Thread.sleep(2000);
			// reoccurring appointment
			ActionUtils.click(driver.findElement(By.xpath("//span[@title='Recurrence editor']/span/span[2]/span")));
			Commons.waitforElement(driver, By.xpath("//div[@*='k-animation-container']//li[contains(.,'Daily')]"), 40);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@*='k-animation-container']//li[contains(.,'Daily')]")));
			System.out.println("Got Recurrance  Field");
			List<WebElement> elements = driver.findElements(By.xpath("//span[@title='Increase value']/span"));
			ActionUtils.click(elements.get(1));
			ActionUtils.click(elements.get(1));
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
			test.log(LogStatus.INFO, "group appintment is created and now navigating to home page ");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO,
					"checking group appointment on providers home page 1 , Appointment should be there");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]"),
					120));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(
					"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[@title='Next']")));
			// second
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO,
					"checking group appointment on providers home page 2 ,  Appointment should be there");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]"),
					120));
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(
					"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[@title='Next']")));
			// third
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO,
					"checking group appointment on providers home page 3 ,  Appointment should be there");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]"),
					120));
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(
					"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[@title='Next']")));
			// forth
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			test.log(LogStatus.INFO,
					"checking group appointment on providers home page 4 ,  Appointment shouldnt be there");
			Assert.assertFalse(Commons.existsElement(driver, By.xpath(
					"//*[@id='scheduler']//table[contains(@class,'scheduler-table')]//td[contains(.,'Group')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[@title='Next']")));
			test.log(LogStatus.INFO, "reoccuring group appointment created successfully ");
			test.log(LogStatus.INFO, "Test:::: TestReoccurrenceOfGroupAppointment Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestReoccurrenceOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestReoccurrenceOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 1)
	public void TestEditGroupAppointment() {
		test = extent.startTest("To test reoccurrence of group appointment ", "Test Group Appointment"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider3"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "log in with new provider and now navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(2000);
			AddScheduleUtils.addGroupAppointmnet(driver);
			int size = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size();
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")));
			Commons.Explicitwait();
			Thread.sleep(3000);
			// Calendar
			if (Commons.ExistandDisplayElement(driver, By.xpath("//a[contains(@class,'EditSeries')]"), 20)) {
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'EditSeries')]")));
			}
			Thread.sleep(2000);
			Commons.scrollElementinotView(driver.findElement(By.id("appointmentNoteTextArea")), driver);
			
		//Commons.scrollElementinotView(driver.findElement(By.xpath("//div[@id='typeCodeDiv']/following-sibling::div[@data-container-for='start']//span[contains(@class,'calendar')]")), driver);
		ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
				"//div[@id='typeCodeDiv']/following-sibling::div[@data-container-for='start']//span[contains(@class,'calendar')]")));
		Commons.Explicitwait();
		ActionUtils.JavaScriptclick(driver.findElement(By.xpath(
				"//div[@class='k-animation-container']//div[contains(@class,'calendar')]//tbody/tr[1]/td[1]/a")));
		Commons.Explicitwait();
        	ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithReschedule(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.RescheduleReason(driver));
			Thread.sleep(3000);
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			int size2 = driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]")).size();
			Assert.assertTrue(size2 < size);
			test.log(LogStatus.INFO, "reoccuring group appointment created successfully ");
			test.log(LogStatus.INFO, "Test:::: TestReoccurrenceOfGroupAppointment Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestReoccurrenceOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: TestReoccurrenceOfGroupAppointment Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
	
	
	@Test(enabled = true, priority = 7)
	public void TestSelectedCancelationReasonShouldBeVisible() {
		test = extent.startTest("To test Cancellation reason code drop-down should show the selected reason for cancel.", "Cancellation reason code drop-down should show the selected reason for cancel."
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPolicycharge"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "log in with new provider and now navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			test.log(LogStatus.INFO, "adding new group appointmnet");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			AddScheduleUtils.QuickAddPatient(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.GroupTypefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			Thread.sleep(3000);
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For group Notes");
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
			ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
			Commons.waitForLoad(driver);
			Thread.sleep(3000);
			String fieldset = driver.findElement(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']")).getText();
			System.out.println("added patient is " + fieldset);
		    Commons.waitForLoad(driver);
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "new group appointmnet is added ");
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("Appointment is created for patient :" + newName);
			ActionUtils.JavaScriptclick(driver.findElement(By.id("showAllAppointmentsChxkBox")));
			Commons.waitForLoad(driver);
			try{
				List<WebElement> appointments= driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]"));
				int i =0;
				String nameAppointmnet;
				do
				{
					Commons.scrollElementinotView(appointments.get(i), driver);
					ActionUtils.click(appointments.get(i));
					Commons.waitforElement(driver, By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible; opacity: 1;']//div[2]"), 20);
			        nameAppointmnet = driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible; opacity: 1;']//div[2]")).getText();
				    System.out.println("name is "+ nameAppointmnet);
				    i++;
				}
				while(!nameAppointmnet.contains(newName));
				System.out.println("Got the recently created appointment");
				test.log(LogStatus.INFO, "Got the recently created appointment");
				ActionUtils.doubleClick(driver, appointments.get(i-1));
				System.out.println("Appointment is oppend ");
				test.log(LogStatus.INFO, "Appointment is oppend");
				}
				catch (Exception e) {
					System.out.println("To test Cancellation reason code drop-down should show the selected reason for cancel : is fail" + Throwables.getStackTraceAsString(e));
					Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				}
			  System.out.println("canceling the group appointment");
			  test.log(LogStatus.INFO, "canceling the group appointment");
			  Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),25);
			  ActionUtils.click(driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			  Thread.sleep(1000);
			  ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//ul[@id='statusCode_listbox']/li[contains(text(),'Cancel')]")));
			  ActionUtils.click(driver.findElement(By.xpath("//div[@id='cancelReasonCodeDiv']/span/span/span[2]/span")));
			  Thread.sleep(1000);
			  String reason = driver.findElement(By.xpath("//div[@class='k-animation-container']//ul[@id='cancelReasonCode_listbox']/li")).getText();
			  ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//ul[@id='cancelReasonCode_listbox']/li")));
			  Thread.sleep(2000);
			  Commons.waitForLoad(driver);
			  Utils.AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			  System.out.println("group appointment is cancelled with reason :" + reason);
			  test.log(LogStatus.INFO, "group appointment is cancelled with reason :" + reason);
			  System.out.println("finding the cancelled appointment ");
			  test.log(LogStatus.INFO, "finding the cancelled appointment ");
				try{
					List<WebElement> appointments= driver.findElements(By.xpath("//div[@id='eventTemplate'][contains(.,'GROUP')]"));
					int i =0;
					String nameAppointmnet;
					do
					{
						Commons.scrollElementinotView(appointments.get(i), driver);
						ActionUtils.click(appointments.get(i));
						Commons.waitforElement(driver, By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible; opacity: 1;']//div[2]"), 20);
				        nameAppointmnet = driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible; opacity: 1;']//div[2]")).getText();
					    System.out.println("name is "+ nameAppointmnet);
					    i++;
					}
					while(!nameAppointmnet.contains(newName));
					System.out.println("Got the recently created appointment");
					test.log(LogStatus.INFO, "Got the recently created appointment");
					ActionUtils.doubleClick(driver, appointments.get(i-1));
					System.out.println("cancelled Appointment is oppend ");
					
			
		}
		catch (Exception e) {
					System.out.println("To Test Cancellation reason code drop-down should show the selected reason for cancel : is fail" + Throwables.getStackTraceAsString(e));
					Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				}
				Commons.waitforElement(driver, By.xpath("//div[@id='cancelReasonCodeDiv']/span/span/span[1]"), 60);
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='cancelReasonCodeDiv']/span/span/span[1]")).getText().equalsIgnoreCase(reason));
			
				
				test.log(LogStatus.INFO, "Cancellation reason code drop-down should show the selected reason for cancel. , test case is pass");	
		}
		
		
		catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Cancellation reason code drop-down should show the selected reason for cancel. Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Cancellation reason code drop-down should show the selected reason for cancel. Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	}
		
	
}

		
	