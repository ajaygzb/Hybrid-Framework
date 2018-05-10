package com.bms.M6.Scheduler;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CreateNotePage;
import UIMap.SchedulerPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import Utils.SearchPatientUtils;

public class TestProviderHomePageNoteStatusColor extends TestSetUp {
	public String strText_Actual;
	public String  PID;

	@Test(enabled = true, priority = 1)
	public void testProviderHomeScheduleAppointmnet() {
		test = extent.startTest("To Verify Appointment note indicator color Red when no note is created ",
				"To Verify Appointment note indicator color Red when no note is created " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			
			Commons.logout(driver);
			Commons.logintoRevflow(driver, "AutomationBmsprovider", Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "adding patient with insurance as medicare ");
			AddPatientUtils.addpatient_withallfields(driver, "TestNoteStatusColor");
			String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
			System.out.println("name is                     ::::::::::::::::::::::" + patientname);
			String namesplit[] = patientname.split(" ");
			String newName = namesplit[0] + " " + namesplit[2];
			System.out.println("new name on home page is :" + newName);
			System.out.println("add case");
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			test.log(LogStatus.INFO, "Case Added with All required field");
			Commons.waitForLoad(driver);
			AddCaseUtils.AddDXCode(driver);
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Added DX Code");
			AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
			Commons.waitForLoad(driver);
			AddCaseUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//span[@id='patientID']"), 180);
			PID = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientID']")));
			test.log(LogStatus.INFO, " patient is added with insurance as  medicare ");
			test.log(LogStatus.INFO, "creating appointmnet for above added patient  ");
			System.out.println("Adding appointmnet ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Thread.sleep(5000);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"), 30);
			driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
					.clear();
			driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
					.sendKeys(PID);
			Commons.waitforElement(driver, By.xpath("//li[1]//span[contains(.,'" + PID + "')]"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//li[1]//span[contains(.,'" + PID + "')]")));
			Thread.sleep(2000);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			// Select Case name
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='caseDropDown']//span[contains(@class,'k-input')]")));
			Thread.sleep(5000);
			List<WebElement> els = driver
					.findElements(By.xpath("//*[@id='cases_listbox']//li[contains(.,'Case With All Fields')]"));
			System.out.println(els.size());
			ActionUtils.JavaScriptclick(els.get(els.size() - 1));
			/*
			 * for ( WebElement el : els ) { ActionUtils.JavaScriptclick(el); }
			 */
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			test.log(LogStatus.INFO, "appointment is created for above added patient  ");
			test.log(LogStatus.INFO, "checking status of above created appoinmnet Display ");
			System.out.println("navigated to home page");
			test.log(LogStatus.INFO, "navigated to home page");
			// *********************************
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Commons.waitForLoad(driver);
			String xpath = "//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-red')]";
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-red')]"), 5);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'"
					+ newName + "')]//preceding::td[1]//span[contains(@class,'note-red')]")), driver);
			Assert.assertTrue(Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'"
					+ newName + "')]//preceding::td[1]//span[contains(@class,'note-red')]"), 5));
			System.out.println("*************Assertion-1 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			// test-2
			test = extent.startTest("To Verify Appointment note indicator color Yellow when  note is created ",
					"To Verify Appointment note indicator color Yellow when  note is created "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			System.out.println("Adding a new case to patient");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//a[contains(.,'Home')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-red')]"), 30);
			System.out.println("Click on Red dot on Provider Home screen.");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-red')]")));
			// APageUtils.createNewNote(driver);
			// create New note
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'New Note')]"), 90);
			System.out.println("Setting Visit Type as Initial Visit");
			Commons.waitForLoad(driver);
			CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
			if (Commons.existsElement(driver,
					By.xpath("//span[contains(text(),'Ankle Sprain')]//preceding::input[1]"))) {
				ActionUtils.click(
						driver.findElement(By.xpath("//span[contains(text(),'Ankle Sprain')]//preceding::input[1]")));
				ActionUtils.click(driver.findElement(By.xpath(
						"//div[@title-details='vmNote.globalTitle']/div/div[1]//input[contains(@value,'Add Selected')]")));
			} else {
				ActionUtils.click(driver.findElement(By.xpath(
						"//div[@title-details='vmNote.globalTitle']/div/div[1]//input[contains(@value,'Add All')]")));
			}
			Commons.waitForElementToBeClickable(driver, CreateNotePage.CreateNoteButton(driver), 10);
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToSubjective()']"), 190);
			Commons.waitForLoad(driver);
			// Navigate to home screen to validate color of note status button
			// is changed to yellow
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//a[contains(.,'Home')]")));
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'"
					+ newName + "')]//preceding::td[1]//span[contains(@class,'note-yellow')]"), 190));
			System.out.println("*************Assertion-2 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
			// Using same patient schedule another appointment on different date
			System.out.println("Schedule appointment to past date with same patient");
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName + "')]")));
			Commons.waitforElement(driver, By.xpath("//span[@id='patientID']"), 180);
			System.out.println("Adding appointmnet ");
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			System.out.println("Open scheduler window in past date");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@title,'Previous')]")));
			Commons.waitForLoad(driver);
			Commons.screenshot();
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			Thread.sleep(5000);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"), 30);
			driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
					.clear();
			driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
					.sendKeys(PID);
			Commons.waitforElement(driver, By.xpath("//li[1]//span[contains(.,'" + PID + "')]"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//li[1]//span[contains(.,'" + PID + "')]")));
			Thread.sleep(2000);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			// Select Case name
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='caseDropDown']//span[contains(@class,'k-input')]")));
			Thread.sleep(5000);
			List<WebElement> els1 = driver
					.findElements(By.xpath("//*[@id='cases_listbox']//li[contains(.,'Case With All Fields')]"));
			ActionUtils.JavaScriptclick(els1.get(1));
			/*
			 * System.out.println(els1.size()); for ( WebElement el : els1 ) {
			 * ActionUtils.JavaScriptclick(el); }
			 */
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			test.log(LogStatus.INFO, "Another appointment is created for above added patient  ");
			// Navigate to Home screen and change Note DOS same as current
			// appointment date.
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//a[contains(.,'Home')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//b[contains(.,'Appointment')]"), 190);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@title,'Previous')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-red')]"), 180);
			Commons.screenshot();
			System.out.println("**********Assertion-3 Pass**************");
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@title,'Next')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-yellow')]"),30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-yellow')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(.,'Note Details')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Note Details')]")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='DateOfService']//button[contains(@ng-click,'DateOfService')]"), 60);
			Calendar cal = Calendar.getInstance();
			int currentDay = cal.get(Calendar.DAY_OF_MONTH);
			String previousDay = Integer.toString(currentDay - 1);
			String currentMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
			String currentYear = Integer.toString(cal.get(Calendar.YEAR));
			HandlingCalendars.datepick(driver,
					By.xpath("//*[@id='DateOfService']//button[contains(@ng-click,'DateOfService')]"),
					previousDay + "/" + currentMonth + "/" + currentYear);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='saveEMRData']")));
			Commons.capturemessage(driver, 30);
			Commons.waitForLoad(driver);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//a[contains(.,'Home')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-red')]"), 180);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@title,'Previous')]")));
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-yellow')]"),30);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-yellow')]")), driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-yellow')]"), 180);
			Commons.screenshot();
			// test-3
			test = extent.startTest("To Verify Appointment note indicator color Green when  note is Finalized ",
					"To Verify Appointment note indicator color Green when  note is Finalized "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-yellow')]"), 90);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-yellow')]")));
			Commons.waitforElement(driver, By.xpath("//span[@id='patientID']"), 160);
			APageUtils.GotoNoteList(driver, 1);
			System.out.println("Finalize note.");
			Commons.waitforElement(driver, By.xpath("//a[contains(@data-ng-click,'redirectToAssesment()')]"), 90);
			APageUtils.click(driver, "clickApage");
			APageUtils.FinalizeNote(driver);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//a[contains(.,'Home')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@title,'Previous')]")));
			Commons.waitForLoad(driver);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'"
					+ newName + "')]//preceding::td[1]//span[contains(@class,'note-green')]")), driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName
					+ "')]//preceding::td[1]//span[contains(@class,'note-green')]"), 180);
			Commons.screenshot();
			Assert.assertTrue(Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'"
					+ newName + "')]//preceding::td[1]//span[contains(@class,'note-green')]"), 90));
			System.out.println("*************Assertion-3 Pass*********");
			Commons.screenshot();
			extent.endTest(test);
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

   
	
	@Test(enabled = false,dependsOnMethods = { "testProviderHomeScheduleAppointmnet" })
	public void TestNotestatuscolorRecuringappointment() throws InterruptedException {
		test = extent.startTest("To Verify Recurring appointments for future dates should not carry the same status as that of single appointment ",
				"To Verify Recurring appointments for future dates should not carry the same status as that of single appointment" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, "AutomationBmsprovider", Commons.readPropertyValue("password"));
		
		if(PID!=null){
        SearchPatientUtils.QuickpatientSearch(driver,PID);
		}else{
			
			Assert.assertFalse(true);
		}
		String patientname = driver.findElement(By.xpath("//span[@id='patientFirstName']")).getText();
		System.out.println("name is                     ::::::::::::::::::::::" + patientname);
		String namesplit[] = patientname.split(" ");
		String newName = namesplit[0] + " " + namesplit[2];
		System.out.println("new name on home page is :" + newName);
		//Navigate to Provider Home
         ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//a[contains(.,'Home')]")));
         ActionUtils.click(driver.findElement(By.xpath("//a[contains(@title,'Previous')]")));
         Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName + "')]//preceding::td[2]//span[contains(@class,'agendaTime')]"), 30);
         ActionUtils.click(driver.findElement(By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName + "')]//preceding::td[2]//span[contains(@class,'agendaTime')]")));
	     //
 		try {
 			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.SelectType(driver, "Initial Evaluation"));
			ActionUtils.JavaScriptclick(SchedulerPage.SelectStatus(driver, "Scheduled"));
			//ActionUtils.click(SchedulerPage.ResourceField(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ActionUtils.click(SchedulerPage.Recurrencefield(driver, "Daily"));
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='end'][@value='count']")));
			driver.findElement(By.xpath("(//span[contains(@class,'k-icon k-i-arrow-60-up')])[2]")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("(//span[contains(@class,'k-icon k-i-arrow-60-up')])[2]")).click();
			Thread.sleep(500);
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='scheduler']//a[contains(.,'Today')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName + "')]//preceding::td[1]//span[contains(@class,'clinicalNoteStatus note-red')]"), 30);
			Commons.screenshot();
			// Next day
			
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='scheduler']//a[contains(@title,'Next')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(.,'" + newName + "')]//preceding::td[1]//span[contains(@class,'clinicalNoteStatus note-red')]"),130);
			 Commons.screenshot();
		} catch (Exception e) {
			System.out.println("Unable to Save Appointment" + e);
			Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
		}
       
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}




























}
