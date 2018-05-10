package com.bms.M15.ArrivalAlert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.TestSetUp;
import UIMap.SchedulerPage;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestReoccurringAppointments extends TestSetUp{
	
	@Test(enabled = true, priority = 1)
	public void TestCopyAndPasteOfSingleAppointmentMultipleTimes() {
		test = extent.startTest("To Test Copy And Paste Of Single Appointment Multiple Times", "Test Copy And Paste Of Single Appointment Multiple Times"
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
			System.out.println("creating appointmnet on provider home page");
			test.log(LogStatus.INFO, "creating appointmnet on provider home page");
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
			System.out.println("appointmnet is visible on Provider home page");
			test.log(LogStatus.INFO, "appointmnet is visible on Provider home page");
			Assert.assertTrue(Status.contains("SCHEDULED"));
			System.out.println("Navigating to scheduler page");
			test.log(LogStatus.INFO, "Navigating to scheduler page");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			Thread.sleep(4000);
			Commons.waitForLoad(driver);
			try{
				List<WebElement> appointments= driver.findElements(By.id("eventTemplate"));
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
				}
				catch (Exception e) {
					System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
					Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				}
			ActionUtils.click(driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible; opacity: 1;']//div[2]//ul//li[1]//a")));
			Thread.sleep(2000);
			System.out.println("Appointmnet is copied ");
			test.log(LogStatus.INFO, "Appointmnet is copied ");
			for(int i=1 ; i<=3 ; i++)
			{
			WebElement timeSlot=SchedulerPage.TimeSlot(driver);
			Commons.scrollElementinotView(timeSlot, driver);
			ActionUtils.click(timeSlot);
			Thread.sleep(2000);
			ActionUtils.click(timeSlot);
			System.out.println("Paste the appointmnet on selected time slot");
			test.log(LogStatus.INFO, "Paste the appointmnet on selected time slot");
			try
			{
			ActionUtils.click(driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible;']//ul//li[1]//i")));
			}
			  catch(Exception e)
			{
				  ActionUtils.click(timeSlot);
					ActionUtils.click(driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible;']//ul//li[1]//i")));
			}
		    try
		    {
		    	Commons.waitforElement(driver, By.id("saveWarningAppoinment"), 40);
		    	ActionUtils.click(driver.findElement(By.id("saveWarningAppoinment")));
		    }
		    catch(Exception e)
		    {
		    	System.out.println("save waring appointmnet message dont appears ");
		    }
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			}
			System.out.println("Navigating to Provider home page");
			test.log(LogStatus.INFO, "Navigating to Provider home page");
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(5000);
			Commons.waitForLoad(driver);
			xpath = "//u[contains(.,'" + newName + "')]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			List<WebElement> appointmnets= driver.findElements(By.xpath(xpath));
			int size= appointmnets.size();
			System.out.println(" size is "+ size)  ;
			Assert.assertTrue(size==4);
			System.out.println("Pasted appointmnet is visible on Provider home page");
			test.log(LogStatus.INFO, "Pasted appointmnet is visible on Provider home page , hence test case is pass");
			
			
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Test Copy And Paste Of Single Appointment Multiple Times Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			test.log(LogStatus.INFO, " Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Test Copy And Paste Of Single Appointment Multiple Times Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = false, priority = 2)
	public void TestUserShouldAbleToEditRecurringMeeting() {
		test = extent.startTest("To Test User Should Able To Edit Recurring Meeting",
				"To Test User Should Able To Edit Recurring Meeting" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try
		{
			
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with recurring  provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),Commons.readPropertyValue("password"));
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		    ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		    Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		    Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		    System.out.println("Opened View Schedule page");
		    test.log(LogStatus.INFO, "Adding recurring meeting");
		    System.out.println("Adding recurring meeting");
		    String meeting =AddScheduleUtils.addRecurringMeeting(driver);
		    test.log(LogStatus.INFO, "recurring meeting is created");
		    System.out.println("recurring meeting is created");
		    WebElement meetingAppointment = driver.findElement(By.xpath("//div[@id='eventTemplate']/div/span[contains(text(),'"+meeting+"')]"));
			ActionUtils.doubleClick(driver, meetingAppointment);
			test.log(LogStatus.INFO, "selecting edit occurrence");
		    System.out.println("selecting edit occurrence");
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditOccurrence']")));
			Thread.sleep(2000);
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-cancel']")));
			Commons.waitForLoad(driver);
			Thread.sleep(2000);
			try{
			ActionUtils.doubleClick(driver, meetingAppointment);
			Commons.waitForElementToBeVisible(driver,  driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditSeries']")),40);
			}
			catch(Exception e)
			{
				ActionUtils.doubleClick(driver, meetingAppointment);
				Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditSeries']")),40);
				
			}
			
			test.log(LogStatus.INFO, "selecting edit Series");
		    System.out.println("selecting edit Series");
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditSeries']")));
			Commons.waitForLoad(driver);
			Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath("//a[@class='k-button k-scheduler-cancel']")), 50);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d, YYYY");
			LocalDate date2 = LocalDate.now();
			LocalDate date3=date2.plusDays(1);
			String date4=date3.format(dtf);
			System.out.println("date is :::::::::::::::::::"+date4);
			test.log(LogStatus.INFO, "ReScheduling recurring meeting to date " + date4);
		    System.out.println("ReScheduling recurring meeting to date " + date4);
			HandlingCalendars.Schedulerdatepick(driver, By.xpath("//span[@aria-controls='meetingStartTime_dateview']"),date4);
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			test.log(LogStatus.INFO, "Navigating  to date " + date4 +"to verify meeting is rescheduled ");
		    System.out.println("Navigating  to date " + date4 +"to verify meeting is rescheduled ");
		    Commons.waitForElementToBeClickable(driver, driver.findElement(By.xpath("//a[@title='Next']/span")), 120);
		    ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//a[@title='Next']/span")));
		    Commons.waitForLoad(driver);
		    Assert.assertTrue(Commons.existsElement(driver, By.xpath("//div[@id='eventTemplate']/div/span[contains(text(),'"+meeting+"')]")));
		    test.log(LogStatus.INFO, "meeting is found on date " + date4 +"hence test case is pass , user is able to reschedule recurring meeting");
		    System.out.println("meeting is found on date " + date4 +"hence test case is pass , user is able to reschedule recurring meeting");
		
				
			} catch (Exception e) {
				System.out.println("Test User Should Able To Edit Recurring Meeting  is fail" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			// *[@id='eventTemplate'][contains(.,'MEETING')] //cancel meeting
			
		catch (AssertionError e) {
			System.out.println("Test User Should Able To Edit Recurring Meeting  is fail" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			
		}
	}
	
	
	@Test(enabled = false, priority = 3)
	public void TestingNewCaseOnRecurringAppointment() {
		test = extent.startTest("To Verify new case is visible on all recucurring apointments ",
				"To Verify new case is visible on all recucurring apointments " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try
		{
			
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),Commons.readPropertyValue("password"));
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		    ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		    Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		    Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		    System.out.println("Opened View Schedule page");
		    System.out.println("navigating to yesterday ");
		    test.log(LogStatus.INFO, "navigating to yesterday ");
		    Thread.sleep(3000);
		    ActionUtils.JavaScriptclick(driver.findElement(By.xpath("//a[@title='Previous']/span")));
		    Commons.waitForLoad(driver);
		    Thread.sleep(2000);
		    System.out.println("Opened View Schedule page : yesterday");
		    test.log(LogStatus.INFO, "Opened View Schedule page : yesterday");
		    test.log(LogStatus.INFO, "Adding Recurring appointment with new case");
		    System.out.println("Adding Recurring appointment with new case");
		    String casename=AddScheduleUtils.addRecurringAppointmentwithNewCase(driver);
		    test.log(LogStatus.INFO, "Recurring appointment with new case :"+ casename);
		    System.out.println("Recurring appointment with new case :"+ casename);
		    String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("Appointment is created for patient :" + newName);
			Commons.waitForLoad(driver);
			try{
				List<WebElement> appointments= driver.findElements(By.id("eventTemplate"));
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
					System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
					Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				}
			
			try{
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditSeries']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//span[@aria-owns='cases_listbox']/span/span"), 40);
			}
			catch(Exception e)
			{
				ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditSeries']")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//span[@aria-owns='cases_listbox']/span/span"), 40);
			}
			String casename1=driver.findElement(By.xpath("//span[@aria-owns='cases_listbox']/span/span")).getText();
			Assert.assertTrue(casename1.equalsIgnoreCase(casename));
			System.out.println("Appointment is having same case");
			test.log(LogStatus.INFO, "Appointment is having same case");
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-cancel']")));
			System.out.println("navigating to today ");
		    test.log(LogStatus.INFO, "navigating to today ");
		    Thread.sleep(3000);
			ActionUtils.click(driver.findElement(By.xpath("//a[@title='Next']/span")));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page : today");
			test.log(LogStatus.INFO, "Opened View Schedule page : today");
			try{
				List<WebElement> appointments= driver.findElements(By.id("eventTemplate"));
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
					System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
					Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				}
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditSeries']")));
			Commons.waitforElement(driver, By.xpath("//span[@aria-owns='cases_listbox']/span/span"), 120);
			casename1=driver.findElement(By.xpath("//span[@aria-owns='cases_listbox']/span/span")).getText();
			Assert.assertTrue(casename1.equalsIgnoreCase(casename));
			System.out.println("Appointment is having same case");
			test.log(LogStatus.INFO, "Appointment is having same case");
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-cancel']")));
			System.out.println("navigating to Tomorrow ");
		    test.log(LogStatus.INFO, "navigating to Tomorrow ");
		    Thread.sleep(3000);
			ActionUtils.click(driver.findElement(By.xpath("//a[@title='Next']/span")));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page : Tomorrow");
			test.log(LogStatus.INFO, "Opened View Schedule page : Tomorrow");
			try{
				List<WebElement> appointments= driver.findElements(By.id("eventTemplate"));
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
					System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
					Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				}
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-EditSeries']")));
			Commons.waitforElement(driver, By.xpath("//span[@aria-owns='cases_listbox']/span/span"), 120);
			casename1=driver.findElement(By.xpath("//span[@aria-owns='cases_listbox']/span/span")).getText();
			Assert.assertTrue(casename1.equalsIgnoreCase(casename));
			System.out.println("Appointment is having same case");
			test.log(LogStatus.INFO, "Appointment is having same case");
			ActionUtils.click(driver.findElement(By.xpath("//a[@class='k-button k-scheduler-cancel']")));
			test.log(LogStatus.INFO, "To Verify new case is visible on all recucurring apointments : is Pass");
			} catch (Exception e) {
				System.out.println("To Verify new case is visible on all recucurring apointments : is fail" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			// *[@id='eventTemplate'][contains(.,'MEETING')] //cancel meeting
			
		catch (AssertionError e) {
			System.out.println("To Verify new case is visible on all recucurring apointments : is fail" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			
		}
	}
	
	
	@Test(enabled = true, priority = 1)
	public void TestUserIsAbleToRescheduleRecurringAppointmentFromToolTip() {
		test = extent.startTest("Testing User Is Able To Reschedule Recurring Appointment From Tool Tip",
				"To Test User Is Able To Reschedule Recurring Appointment From Tool Tip" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try
		{
			
			test.log(LogStatus.INFO, "logging out");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "log out and login with different provider");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),Commons.readPropertyValue("password"));
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		    ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		    Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		    Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		    System.out.println("Opened View Schedule page");
		    test.log(LogStatus.INFO, "Adding Recurring appointment ");
		    System.out.println("Adding Recurring appointment");
		    AddScheduleUtils.addRecurringAppointment(driver , 2);
		    String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[1];
			System.out.println("Appointment is created for patient :" + newName);
			Commons.waitForLoad(driver);
			try{
			List<WebElement> appointments= driver.findElements(By.id("eventTemplate"));
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
			}
			catch (Exception e) {
				System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			ActionUtils.click(driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible; opacity: 1;']//div[2]//ul//li[2]//a")));
			System.out.println("Rescheduled Appointment is selected ");
			test.log(LogStatus.INFO, "Rescheduled Appointment is selected ");
			System.out.println("navigating to next day");
			test.log(LogStatus.INFO, "navigating to next day");
			ActionUtils.click(driver.findElement(By.xpath("//a[@title='Next']/span")));
			Commons.waitForLoad(driver);
			System.out.println("Opened View Schedule page : Tomorrow");
			test.log(LogStatus.INFO, "Opened View Schedule page : Tomorrow");
			WebElement timeSlot=SchedulerPage.TimeSlot(driver);
			Commons.scrollElementinotView(timeSlot, driver);
			ActionUtils.click(timeSlot);
			Thread.sleep(2000);
			ActionUtils.click(timeSlot);
			System.out.println("Paste the appointmnet on selected time slot");
			test.log(LogStatus.INFO, "Paste the appointmnet on selected time slot");
			try
			{
			ActionUtils.click(driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible;']//ul//li[2]//i")));
			}
			  catch(Exception e)
			{
				  ActionUtils.click(timeSlot);
					ActionUtils.click(driver.findElement(By.xpath("//div[@role='tooltip'][@style='display: block; position: absolute; width: 240px; visibility: visible;']//ul//li[2]//i")));
			}
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "selecting edit Series");
		    System.out.println("selecting edit Series");
		    try{
			ActionUtils.click(driver.findElement(By.id("EditSeries")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//h4[contains(text(),'Reschedule Reason')]"), 50);
		    }
		    catch(Exception e)
		    {
		    	ActionUtils.click(driver.findElement(By.id("EditSeries")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//h4[contains(text(),'Reschedule Reason')]"), 50);
		    }
		
			ActionUtils.click( driver.findElement(By.xpath("//div[@id='rescheduleReasonCodeRecurringDiv']//span[@class='k-select']/span")));
			ActionUtils.click(driver.findElement(By.xpath("//ul[@id='rescheduleReasonCodeForRecurring_listbox']/li")));
			ActionUtils.click(driver.findElement(By.id("rescheduleReasonSave")));
		    try
		    {
		    	Commons.waitforElement(driver, By.id("saveWarningAppoinment"), 40);
		    	ActionUtils.click(driver.findElement(By.id("saveWarningAppoinment")));
		    }
		    catch(Exception e)
		    {
		    	System.out.println("save waring appointmnet message dont appears ");
		    }
			Commons.waitForLoad(driver);
			Thread.sleep(5000);
			try{
				List<WebElement> appointments= driver.findElements(By.id("eventTemplate"));
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
				}
				catch (Exception e) {
					System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
					Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				}
		
			test.log(LogStatus.INFO, "ToTest User Is Able To Reschedule Recurring Appointment From Tool Tip : is Pass");
			} catch (Exception e) {
				System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			// *[@id='eventTemplate'][contains(.,'MEETING')] //cancel meeting
			
		catch (AssertionError e) {
			System.out.println("To Test User Is Able To Reschedule Recurring Appointment From Tool Tip : is fail" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			
		}
	}
	
}
