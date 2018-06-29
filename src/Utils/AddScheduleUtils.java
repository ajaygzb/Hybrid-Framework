package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import UIMap.APage;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import UIMap.SchedulerPage;

import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

import TestBase.TestSetUp;

public class AddScheduleUtils {
	public static boolean present = false;
	public String Randomstr = RandomStringUtils.randomAlphabetic(20);
	public int BeforeCount;
	public int AfterCount;
	String PID = null;

	/*
	 * This Function is use to 'Quick Add' patient and schedule an appointment
	 */
	public String ScheduleAppointment(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		GotoAppointmentWindow(driver);
		QuickAddPatient(driver);
		setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
		ClickSaveAppointmentwindow(driver, false);
		Commons.strText_Actual = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
				.replaceAll("\\s", "");
				
		return Commons.strText_Actual;
	}

	/* This Method is used to navigate to Appointment window in scheduler */
	public static void GotoAppointmentWindow(WebDriver driver) {
		try {
			Commons.waitForLoad(driver);
			if (!Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 10)) {
				Commons.waitForElementToBeClickable(driver, SchedulerPage.ScheduleOnMenu(driver), 20);
				ActionUtils.JavaScriptclick(SchedulerPage.ScheduleOnMenu(driver));
				ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 90);
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 100);
				System.out.println("Opened View Schedule page");
			}
			OpenAppointmentWindow(driver);
		} catch (Exception e) {
			System.out.println("Unable to load Appointment window" + e);
		}
	}

	/*
	 * This Method is used to Add quick patient with mandatory fields only in
	 * scheduler window
	 */
	public static void QuickAddPatient(WebDriver driver) {
		try {
			Commons.waitForLoad(driver);
			ActionUtils.click(SchedulerPage.QuickAddPatientbutton(driver));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			ActionUtils.sendKeys(SchedulerPage.firstname(driver),
					"Auto"+ new Random().nextInt(999999999));
			ActionUtils.sendKeys(SchedulerPage.lastname(driver),
					"Last"+ new Random().nextInt(999999999));
			ActionUtils.sendKeys(SchedulerPage.homenumber(driver), AddPatientUtils.strPhonetemp);
			ActionUtils.click(SchedulerPage.gender(driver));
			HandlingCalendars.datepick(driver, By.xpath("//div[@id='dob-patient']//span//button"), "12/05/1993");
			ActionUtils.sendKeys(SchedulerPage.casename(driver), "Case" + new Random().nextInt(99999));
			ActionUtils.click(SchedulerPage.PatientSave(driver));
			System.out.println("Clicked On Patient Save Button");
			driver.switchTo().defaultContent();
			Commons.waitForElementToBeNotVisible(driver,
					By.xpath("//div[contains(@class,'quickAddWindow')]//span[@id='quickAddPatientWindow_wnd_title']"),
					150);
			System.out.println("Patient Add steps completed");
			System.out.println("Navigate back to appointment window...");
			Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 15);
		} catch (AssertionError a) {
			Commons.screenshot();
			Assert.assertFalse(true,
					"Unable to Quick add patient in Scheduler Window" + Throwables.getStackTraceAsString(a));
		}
	}

	public String ScheduleMeeting(WebDriver driver) {
		try {
			Commons.Explicitwait();
			ActionUtils.click(SchedulerPage.ScheduleMeetingbutton(driver));
			Commons.waitForLoad(driver);
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@ng-show='vmScheduler.showLocationView']//div[@data-container-for='resource']//span[contains(@class,'k-select')]"),
						20);
				ActionUtils.click(driver.findElement(By.xpath(
						"//div[@ng-show='vmScheduler.showLocationView']//div[@data-container-for='resource']//span[contains(@class,'k-select')]"))); // select
				// LocationCommons.Explicitwait();
				Commons.ExistandDisplayElement(driver,
						By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Automation BMS')]"), 20);
				ActionUtils.JavaScriptclick(driver.findElement(
						By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Automation BMS')]")));
				;
				System.out.println("Got Location Field");
			} catch (Exception e) {
				System.out.println("Did not get Location Field");
				// Assert.assertFalse(true,Throwables.getStackTraceAsString
				// (e));
			}
			ActionUtils.click(SchedulerPage.MeetingAppintmentNote(driver));
			ActionUtils.sendKeys(SchedulerPage.MeetingAppintmentNote(driver),
					"This is a Random String of Characters entered as appointment Notes==>>" + Randomstr);
			Commons.strText_Actual = Randomstr;
			System.out.println(Commons.strText_Actual);
			Commons.Explicitwait();
			ActionUtils.click(
					driver.findElement(By.xpath("(//ul[@id='availableAttendees']//input[@type='checkbox'])[1]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Add All')]")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Save')]")));
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 10)) {
				Commons.waitforElement(driver,
						By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 10);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]")));
				System.out.println("Warning closed successfully");
				Commons.waitForLoad(driver);
			}
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@id='editorButtons']/a[contains(.,'Save')]"),
					30);
			System.out.println("Meeting Saved ");
		} catch (Exception e) {
			System.out.println(Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Meeting not Saved " + Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	public String ScheduleReccurringMeeting(WebDriver driver) {
		try {
			Commons.Explicitwait();
			ActionUtils.click(SchedulerPage.ScheduleMeetingbutton(driver));
			Commons.waitForLoad(driver);
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@ng-show='vmScheduler.showLocationView']//div[@data-container-for='resource']//span[contains(@class,'k-select')]"),
						20);
				ActionUtils.click(driver.findElement(By.xpath(
						"//div[@ng-show='vmScheduler.showLocationView']//div[@data-container-for='resource']//span[contains(@class,'k-select')]"))); // select
				// LocationCommons.Explicitwait();
				Commons.ExistandDisplayElement(driver,
						By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Automation')]"), 20);
				ActionUtils.JavaScriptclick(driver.findElement(
						By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Automation')]")));
				;
				System.out.println("Got Location Field");
			} catch (Exception e) {
				System.out.println("Did not get Location Field");
				// Assert.assertFalse(true,Throwables.getStackTraceAsString
				// (e));
			}
			ActionUtils.click(SchedulerPage.MeetingAppintmentNote(driver));
			ActionUtils.sendKeys(SchedulerPage.MeetingAppintmentNote(driver),
					"This is a Random String of Characters entered as appointment Notes==>>" + Randomstr);
			Commons.strText_Actual = Randomstr;
			System.out.println(Commons.strText_Actual);
			Commons.Explicitwait();
			ActionUtils.click(
					driver.findElement(By.xpath("(//ul[@id='availableAttendees']//input[@type='checkbox'])[1]")));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Add All')]")));
			// Add Reccurance type
			Commons.waitforElement(driver,
					By.xpath(
							"(//div[@name='recurrencePattern']//span[contains(.,'Never')]//span[contains(@class,'k-input')])[2]"),
					30);
			ActionUtils.click(driver.findElement(By.xpath(
					"(//div[@name='recurrencePattern']//span[contains(.,'Never')]//span[contains(@class,'k-input')])[2]")));
			Thread.sleep(2000);
			Commons.waitforElement(driver, By.xpath("//div[@*='k-animation-container']//li[contains(.,'Daily')]"), 40);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@*='k-animation-container']//li[contains(.,'Daily')]")));
			System.out.println("Got Recurrance  Field");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Add All')]")));
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='end'][@value='count']")));
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Save')]")));
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 10)) {
				Commons.screenshot();
				Commons.waitforElement(driver,
						By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 10);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]")));
				System.out.println("Warning closed successfully");
				Commons.waitForLoad(driver);
			}
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@id='editorButtons']/a[contains(.,'Save')]"),
					30);
			System.out.println("Meeting Saved ");
		} catch (Exception e) {
			System.out.println(Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Meeting not Saved " + Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	/*
	 * This function is to capture Mandatory fields Error pop up in scheduler
	 * page
	 */
	public String AddPatientWithMandatoyfields(WebDriver driver) {
		try {
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			SelectViewBy(driver, "Provider View", "Automation BMS Provider");
			GotoAppointmentWindow(driver);
			Commons.Explicitwait();
			ActionUtils.click(SchedulerPage.QuickAddPatientbutton(driver));
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Thread.sleep(2000);
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='quickAddPatientTemplate']//button[contains(text(),'Save')]"), 30);
			Commons.waitForElementToBeClickable(driver, driver.findElement(
					By.xpath("//div[@id='quickAddPatientTemplate']//button[contains(text(),'Save')]")), 30);
			ActionUtils.click(SchedulerPage.PatientSave(driver));
			Commons.strText_Actual = Commons.capturemessage(driver, 30);
			/*
			 * ActionUtils.sendKeys(SchedulerPage.firstname(driver),
			 * AddPatientUtils.strFirstNametemp+new Random().nextInt(1000));
			 * ActionUtils.sendKeys(SchedulerPage.lastname(driver),
			 * AddPatientUtils.strLastNametemp + new Random().nextInt(1000));
			 * ActionUtils.sendKeys(SchedulerPage.homenumber(driver),
			 * AddPatientUtils.strPhonetemp);
			 * ActionUtils.click(SchedulerPage.gender(driver));
			 * HandlingCalendars.datepick(driver,By.xpath(
			 * "//div[@id='dob-patient']//span"),"12/05/1993"); String Casename=
			 * "Test"+new Random().nextInt(1000);
			 * ActionUtils.sendKeys(SchedulerPage.casename(driver),Casename);
			 * ActionUtils.click(SchedulerPage.PatientSave(driver));
			 * Commons.Explicitwait(); boolean x =
			 * AddPatientPage.dup_save_btn(driver).isDisplayed();
			 * System.out.println("duplicate patient pop up status is=" + x +
			 * AddPatientPage.dup_save_btn(driver).getText()); if (x == true) {
			 * ActionUtils.click(AddPatientPage.duplicateSelectFirstRecord(
			 * driver));
			 * 
			 * }
			 */
		} catch (Exception e) {
			System.out.println("Unable to Quick Add patient");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.Explicitwait();
		driver.switchTo().defaultContent();
		System.out.println("Patient Add steps completed");
		return Commons.strText_Actual;
	}

	/* Newely created case in scheduler displays as incomplete */
	public String CasecreationScheduler(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		GotoAppointmentWindow(driver);
		QuickAddPatient(driver);
		Commons.strText_Actual = ActionUtils.getText(SchedulerPage.Casefield(driver));
		System.out.println(Commons.strText_Actual);
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
		setAppointmenttime(driver, String.valueOf(new Random().nextInt(10) + 1));
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
		ClickSaveAppointmentwindow(driver, false);
		return Commons.strText_Actual;
	}

	/* Search for patient and create new case from Appointment window */
	public String NewCasefromAppointmentwindow(WebDriver driver) throws InterruptedException {
		
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		System.out.println("Waiting for Patient Header to load.....");
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[@id='patientID']"), 160)) {
			PID = driver.findElement(By.xpath("//span[@id='patientID']")).getText();
		}
		GotoAppointmentWindow(driver);
		
		
		try {
			Thread.sleep(2000);
			Commons.waitforElement(driver,By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"), 30);
			if(!driver.findElement(By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']")).getAttribute("value").contains(PID)){
			
			System.out.println("Searching patient from Scheduler Quick search");
			do {
				Commons.waitforElement(driver,
						By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"), 20);
				ActionUtils.click(driver.findElement(
						By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']")));
				driver.findElement(
						By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
						.clear();
				Commons.Explicitwait();
				if (PID != null) {
					driver.findElement(
							By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
							.sendKeys(PID);
					Thread.sleep(2000);
				} else {
					driver.findElement(
							By.xpath("//*[@id='autosearchPatientScheduler']//input[@placeholder='Patient Search']"))
							.sendKeys("Automate");
					Thread.sleep(2000);
				}
			} while (!driver.findElement(By.xpath("//span[contains(@md-highlight-text,'searchText')]")).isDisplayed());
			Commons.waitforElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"), 30);
			// span[contains(.,'Automate')]
			ActionUtils
					.click(driver.findElements(By.xpath("//span[contains(@md-highlight-text,'searchText')]")).get(0));
			System.out.println("Quick search Done!!");
			}
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='caseDropDown']//span[@class='k-select']"), 80);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseDropDown']//span[@class='k-select']")));
			Thread.sleep(3000);
			try {
				driver.findElement(By.xpath("(//*[@id='cases_listbox']//li[contains(.,'Create A New Case')])[last()]")).click();
				Commons.screenshot();
			} catch (Exception e) {
				int list = driver.findElements(By.xpath("//*[@id='cases_listbox']//li[@role='option']")).size();
				ActionUtils.click(
						driver.findElements(By.xpath("//*[@id='cases_listbox']//li[@role='option']")).get(list - 1));
			}
			System.out.println("Setting New case in  Appointment");
			Commons.Explicitwait();
			String Casename = "Test" + new Random().nextInt(1000);
			ActionUtils.sendKeys(SchedulerPage.newcasename(driver), Casename);
			Commons.strText_Actual = Casename;
			System.out.println(Commons.strText_Actual);
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ClickSaveAppointmentwindow(driver, false);
		} catch (Exception e) {
			System.out.println("Unable to Set New case in  Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			Commons.Explicitwait();
		}
		/*
		 * if(Commons.ExistandDisplayElement(driver,
		 * By.xpath("//a[contains(text(),'Cancel')]"), 10)){
		 * Commons.screenshot(); Commons.waitForLoad(driver);
		 * ActionUtils.click(driver.findElement(By.xpath(
		 * "//a[contains(text(),'Cancel')]"))); }
		 */
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 100);
		Commons.waitForLoad(driver);
		Commons.screenshot();
		return Commons.strText_Actual;
	}

	/* Open Appointment by Location Page */
	public String AppointmentByLocation(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 100);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Location View", "Automation BMS");
		Commons.Explicitwait();
		Commons.strText_Actual = ActionUtils
				.getText(driver.findElement(By.xpath("//*[@id='dropdown-view-mode']//option[1]")));
		OpenAppointmentWindow(driver);
		System.out.println("Opened Appointmnet window");
		QuickAddPatient(driver);
		ActionUtils.click(SchedulerPage.Providerfield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
		// ActionUtils.click(SchedulerPage.ResourceField(driver));
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
		ClickSaveAppointmentwindow(driver, false);
		return Commons.strText_Actual;
	}

	/* Open Appointment by Provider Page */
	public String AppointmentByProvider(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		Commons.Explicitwait();
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		try {
			GotoAppointmentWindow(driver);
			QuickAddPatient(driver);
			Commons.Explicitwait();
			ActionUtils.click(SchedulerPage.locationfield(driver));
			Commons.Explicitwait();
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			// ActionUtils.click(SchedulerPage.ResourceField(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ClickSaveAppointmentwindow(driver, false);
			Commons.strText_Actual = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='dropdown-view-mode']//option[contains(text(),'Provider View')]")));
		} catch (Exception e) {
			System.out.println("Unable to Set New case in  Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	// BY Practice
	public String AppointmentByPractice(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Practice View", "Automation BMS");
		Commons.Explicitwait();
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		Commons.waitForLoad(driver);
		try {
			GotoAppointmentWindow(driver);
			QuickAddPatient(driver);
			Commons.waitForLoad(driver);
			// Setting Location from dropdown
			Commons.Explicitwait();
			ActionUtils.click(SchedulerPage.locationfield(driver));
			System.out.println("Location is selected");
			Commons.Explicitwait();
			// ActionUtils.click(SchedulerPage.Providerfield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			Commons.existsElement(driver, By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"));
			try {
				if (driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"))
						.isDisplayed()) {
					Commons.waitforElement(driver,
							By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 10);
					ActionUtils.click(driver
							.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]")));
					System.out.println("Warning closed successfully");
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Warning Pop up not appeared");
			}
			if (Commons.existsElement(driver, By.xpath("//a[contains(text(),'Cancel')]"))) {
				Commons.waitForLoad(driver);
				driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
			}
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 100);
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			Commons.strText_Actual = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='dropdown-view-mode']//option[contains(text(),'Practice View')]")));
		} catch (Exception e) {
			System.out.println("Unable to Set New case in  Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	public String AppointmentByNonProvider(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Non-Provider View", "Automation NonProvider");
		Commons.Explicitwait();
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		ActionUtils.click(SchedulerPage.FutureDayNavigatetionArrow(driver));
		Commons.waitForLoad(driver);
		try {
			GotoAppointmentWindow(driver);
			QuickAddPatient(driver);
			Commons.waitForLoad(driver);
			ActionUtils.click(SchedulerPage.locationfield(driver));
			Commons.Explicitwait();
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ClickSaveAppointmentwindow(driver, false);
			Commons.strText_Actual = ActionUtils.getText(driver.findElement(
					By.xpath("//*[@id='dropdown-view-mode']//option[contains(text(),'Non-Provider View')]")));
		} catch (Exception e) {
			System.out.println("Unable to Set New case in  Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	public String AppointmentByCompany(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 30);
		Select dropdownView = new Select(driver.findElement(By.xpath("//*[@id='dropdown-view-mode']")));
		Commons.Explicitwait();
		dropdownView.selectByVisibleText("Company View");
		Commons.Explicitwait();
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		try {
			GotoAppointmentWindow(driver);
			QuickAddPatient(driver);
			ActionUtils.click(SchedulerPage.locationfield(driver));
			Commons.Explicitwait();
			// ActionUtils.click(SchedulerPage.Providerfield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ClickSaveAppointmentwindow(driver, false);
			Commons.strText_Actual = ActionUtils.getText(driver
					.findElement(By.xpath("//*[@id='dropdown-view-mode']//option[contains(text(),'Company View')]")));
		} catch (Exception e) {
			System.out.println("Unable to Set New case in  Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	// This method to create a meeting
	public  void CreateMeeting(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		Commons.waitforElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 30);
		// SelectViewBy(driver, "Provider View","Automation Meeting");
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		try {
			GotoAppointmentWindow(driver);
			ScheduleMeeting(driver);
			Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver, By.xpath("//a[contains(text(),'Cancel')]"), 8)) {
				Commons.waitForLoad(driver);
				driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
			}
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 60);
		} catch (Exception e) {
			System.out.println("Could not schedule meeting Test Failed");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		
	}

	public String CreateReccurringMeeting(WebDriver driver) {
		if (!Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 10)) {
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
		}
		Commons.waitforElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 30);
		// SelectViewBy(driver, "Provider View","Automation Meeting");
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		try {
			GotoAppointmentWindow(driver);
			ScheduleReccurringMeeting(driver);
			Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver, By.xpath("//a[contains(text(),'Cancel')]"), 8)) {
				Commons.waitForLoad(driver);
				driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
			}
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 60);
		} catch (Exception e) {
			System.out.println("Could not schedule meeting Test Failed");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	/* Set Appointment by provider to a future date */
	public String AppointmentToFutureDate(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 100);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		System.out.println(driver.findElement(By.xpath("//div[@id='scheduler']//a//span[@class='k-lg-date-format']"))
				.getText().toString());
		Commons.strText_Actual = ActionUtils
				.getText(driver.findElement(By.xpath("//div[@id='scheduler']//a//span[@class='k-lg-date-format']"))); // Current
																														// date
																														// from
																														// app
																														// before
																														// scheduling
																														// appointment
		Commons.waitforElement(driver, By.xpath("//div[@id='scheduler']//a[contains(text(),'Week')]"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//div[@id='scheduler']//a[contains(text(),'Week')]"))); // changed
																												// view
																												// to
																												// week
		ActionUtils.click(SchedulerPage.FutureDayNavigatetionArrow(driver)); // navigated
																				// to
																				// one
																				// week
																				// ahead
																				// of
																				// current
																				// date
		OpenAppointmentWindow(driver);
		System.out.println("Opened Appointmnet window");
		QuickAddPatient(driver);
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
		// ActionUtils.click(SchedulerPage.ResourceField(driver));
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
		ClickSaveAppointmentwindow(driver, false);
		System.out.println("Waiting for day view button");
		Commons.waitforElement(driver,
				By.xpath("//div[@id='scheduler']//li[contains(@class,'default')]//a[contains(text(),'Day')]/.."), 90);
		Commons.Explicitwait();
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		Commons.waitForElementToBeClickable(driver,
				driver.findElement(By
						.xpath("//div[@id='scheduler']//li[contains(@class,'default')]//a[contains(text(),'Day')]/..")),
				20);
		ActionUtils.JavaScriptclick(driver
				.findElements(By
						.xpath("//div[@id='scheduler']//li[contains(@class,'default')]//a[contains(text(),'Day')]/.."))
				.get(0)); // changed view to Day
		return Commons.strText_Actual;
	}

	/* Set Appointment by provider to a Past date */
	public String AppointmentToPast(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 100);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		System.out.println(driver.findElement(By.xpath("//div[@id='scheduler']//a//span[@class='k-lg-date-format']"))
				.getText().toString());
		Commons.strText_Actual = ActionUtils
				.getText(driver.findElement(By.xpath("//div[@id='scheduler']//a//span[@class='k-lg-date-format']"))); // Current
																														// date
																														// from
																														// app
																														// before
																														// scheduling
																														// appointment
		Commons.waitforElement(driver, By.xpath("//div[@id='scheduler']//a[contains(text(),'Week')]"), 30);
		driver.findElement(By.xpath("//div[@id='scheduler']//a[contains(text(),'Week')]")).click(); // changed
																									// view
																									// to
																									// week
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		ActionUtils.click(SchedulerPage.PastDayNavigatetionArrow(driver)); // navigated
																			// to
																			// one
																			// week
																			// back
																			// of
																			// current
																			// date
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		OpenAppointmentWindow(driver);
		System.out.println("Opened Appointmnet window");
		QuickAddPatient(driver);
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
		setAppointmenttime(driver, "9");
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
		ClickSaveAppointmentwindow(driver, false);
		Commons.waitForElementToBeClickable(driver,
				driver.findElement(By
						.xpath("//div[@id='scheduler']//li[contains(@class,'default')]//a[contains(text(),'Day')]/..")),
				20);
		ActionUtils.JavaScriptclick(driver
				.findElements(By
						.xpath("//div[@id='scheduler']//li[contains(@class,'default')]//a[contains(text(),'Day')]/.."))
				.get(0)); // changed view to Day
		return Commons.strText_Actual;
	}

	/* Select view type from dropdown */
	public static void SelectViewBy(WebDriver driver, String View, String Value) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//select[@id='dropdown-view-mode']/option[text()= '" + View + "']"), 300);
			System.out.println("Found view");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			// ActionUtils.click(driver.findElement(By.xpath("//select[@id='dropdown-view-mode']")));
			Commons.waitForLoad(driver);
			System.out.println(
					driver.findElement(By.xpath("//select[@id='dropdown-view-mode']/option[text()= '" + View + "']"))
							.getText());
			System.out.println(
					driver.findElement(By.xpath("//select[@id='dropdown-view-mode']/option[text()= '" + View + "']"))
							.getSize());
			// ActionUtils.click(driver.findElement(By.xpath("//select[@id='dropdown-view-mode']/option[text()=
			// '"+View+"']")));
			Commons.SelectElement(driver, By.xpath("//select[@id='dropdown-view-mode']"), View);
			Commons.waitForLoad(driver);
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='dropdown-view-mode-value']/option[text()= '" + Value + "']"), 150)) {
				// ActionUtils.click(driver.findElement(By.xpath("//*[@id='dropdown-view-mode-value']/option[text()=
				// '"+Value+"']")));
				Commons.SelectElement(driver, By.xpath("//*[@id='dropdown-view-mode-value']"), Value);
				Commons.Explicitwait();
				Commons.waitForLoad(driver);
				do {
					Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 300);
					System.out.println("Loading View Page..");
				} while (driver.findElement(By.xpath("//*[@id='mySpinner']")).isDisplayed());
				Commons.waitForLoad(driver);
				Commons.ExistandDisplayElement(driver,
						By.xpath("//*[@id='dropdown-view-mode-value']/option[text()= '" + Value + "']"), 400);
			} else {
				// select value by index
				System.out.println("select value by index..");
				WebElement element = driver.findElement(By.xpath("//*[@id='dropdown-view-mode-value']"));
				Select se = new Select(element);
				se.selectByIndex(2);
			}
			Commons.waitforElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 400);
		} catch (Exception e) {
			System.out.println("Could not Select View" + Throwables.getStackTraceAsString(e));
			
			TestSetUp.test.log(LogStatus.INFO, "Could not Select View");
			Commons.screenshot();
			Assert.assertFalse(true, "Could not Select View" + Throwables.getStackTraceAsString(e));
		}
		Commons.waitForLoad(driver);
	}

	/* Open Appointment window by clicking on time slot from schedluer page */
	public static void OpenAppointmentWindow(WebDriver driver) {
		int i = 0;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='dropdown-view-mode']"), 100);
			do {
				ActionUtils.click(SchedulerPage.TimeSlot(driver));
				Thread.sleep(2000);
				ActionUtils.doubleClick(driver, SchedulerPage.TimeSlot(driver));
				try {
					Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 15);
					if (driver.findElement(By.xpath("//button[@id='quickAddPatient']")).isDisplayed()
							&& (driver.findElement(By.xpath("//button[@id='meetingForm']")).isDisplayed())) {
						present = true;
						System.out.println("Create Appointment window appeared");
					} else {
						present = false;
						System.out.println("Create Appointment window not appeared");
						System.out.println("Did not double clicked on Coreect Timeslot Window");
						try {
							Commons.waitForLoad(driver);
							driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
						} catch (Exception e) {
							System.out.println("Not performed double click on time slot");
						}
						// ActionUtils.click(SchedulerPage.FutureDayNavigatetionArrow(driver));
					}
				} catch (Exception e) {
					present = false;
					if (Commons.ExistandDisplayElement(driver,
							By.xpath(
									"//span[contains(.,'Edit Recurring Appointment')]/following::span[contains(@class,'k-i-close')]"),
							5)) {
						ActionUtils.click(driver.findElement(By.xpath(
								"//span[contains(.,'Edit Recurring Appointment')]/following::span[contains(@class,'k-i-close')]")));
						System.out.println("Closed Edit Recurrance appointment pop up");
					}
					try {
						Commons.waitForLoad(driver);
						driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
					} catch (Exception e1) {
						System.out.println("Not performed double click on time slot");
					}
					Commons.Explicitwait();
					// ActionUtils.click(SchedulerPage.FutureDayNavigatetionArrow(driver));
					Commons.Explicitwait();
				}
				i++;
			} while (!(present) && i < 20);
		} catch (Exception e) {
			System.out.println("Could not Open Appointment Window");
			Assert.assertTrue(false);
		}
	}

	public static String StartTime(WebDriver driver) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime())); // 2014/08/06
																// 16:00
		System.out.println("Appointment Start Time is" + "  " + dateFormat.format(cal.getTime()));
		String StartTime = dateFormat.format(cal.getTime()).toString();
		return StartTime;
	}

	public static String EndTime(WebDriver driver) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.HOUR, 1);
		System.out.println("Appointment End Time is" + "  " + dateFormat.format(cal1.getTime()));
		String EndTime = dateFormat.format(cal1.getTime()).toString();
		return EndTime;
	}

	// *************************************************************
	public static String ReScheduleAppointment(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		try {
			// Clicking on Existing Appointment
			try {
				if (driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"))
						.size() >= 2) {
					System.out.println("Found existing appointments on scheduler page...");
				} else {
					System.out.println("Did not get more than 1 existing appointments on scheduler page...");
					AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
					AddScheduleUtils.ScheduleAppointment(driver);
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Did not get existing appointments on scheduler page...");
				AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
				AddScheduleUtils.ScheduleAppointment(driver);
				Commons.waitForLoad(driver);
			}
			// AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
			// AddScheduleUtils.ScheduleAppointment(driver);
			// Commons.waitForLoad(driver);
			ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
			Commons.Explicitwait();
			// ActionUtils.Javascriptclick(SchedulerPage.Typefield(driver));
			try {
				System.out.println("Waiting to open Existing appointment window");
				Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 15);
				System.out.println("opened Existing appointment window");
			} catch (Exception e) {
				ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
			}
			// Setting Status as Rescheduled
			Commons.Explicitwait();
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Rescheduled')]"),
					40);
			Commons.Explicitwait();
			ActionUtils.JavaScriptclick(
					driver.findElement(By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Rescheduled')]")));
			System.out.println("Got Status field Field");
			Commons.Explicitwait();
			// will reschedule appoint to a random time between 1 to 10
			setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ActionUtils.click(SchedulerPage.Savebutton(driver));
			Commons.strText_Actual = Commons.capturemessage(driver, 60);
			Commons.waitforElement(driver,
					By.xpath("//div[contains(@ng-show,'RESCHED')]//label[contains(text(),'Reason')]"), 10);
			Commons.Explicitwait();
			driver.findElement(By.xpath("//div[@id='rescheduleReasonCodeDiv']//span[@class='k-select']")).click();
			Commons.waitforElement(driver, By.xpath("//ul[@id='rescheduleReasonCode_listbox']/li[1]"), 20);
			ActionUtils.click(driver.findElement(By.xpath("//ul[@id='rescheduleReasonCode_listbox']/li[1]")));
			ClickSaveAppointmentwindow(driver, true);
		} catch (Exception e) {
			System.out.println("Unable to Save Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	// To Cancel an Existing appointment
	public int CancelAppointment(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		try {
			if (driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"))
					.size() >= 2) {
				System.out.println("Found existing appointments on scheduler page...");
			} else {
				System.out.println("Did not get more than 1 existing appointments on scheduler page...");
				AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
				AddScheduleUtils.ScheduleAppointment(driver);
				Commons.waitForLoad(driver);
			}
		} catch (Exception e) {
			System.out.println("Did not get existing appointments on scheduler page...");
			AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
			AddScheduleUtils.ScheduleAppointment(driver);
			Commons.waitForLoad(driver);
		}
		Commons.waitForLoad(driver);
		ActionUtils.click(SchedulerPage.ListViewButton(driver));
		if (!Commons.ExistandDisplayElement(driver,
				By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"), 30)) {
			ActionUtils.click(SchedulerPage.ListViewButton(driver));
		}
		Commons.waitforElement(driver, By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"),
				30);
		BeforeCount = driver.findElements(By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"))
				.size();
		System.out.println("No. Of appointment Before Cancel" + "  " + BeforeCount);
		ActionUtils.click(SchedulerPage.CloseButtonListView(driver));
		int i = 0;
		// Clicking on Existing Appointment
		do {
			try {
				ActionUtils.click(SchedulerPage.eventTemplate(driver));
				ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
				Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 10);
				i++;
			} catch (Exception e) {
				System.out.println("Did not open Existing appointment retrying....");
				if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'Edit Recurring Appointment')]"), 8)) {
					System.out.println("Opened Existing Recurring Appointment");
					Commons.screenshot();
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Edit Occurrence')]")));
					Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 15);
				}else{
					
					ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
					
					
				}
			}
		} while (!Commons.existsElement(driver, By.xpath("//button[@id='quickAddPatient']")) && i > 5);
		ActionUtils.click(SchedulerPage.Typefield(driver));
		// Setting Status as Cancel
		ActionUtils
				.click(driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
		Commons.waitforElement(driver, By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Cancel')]"), 40);
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Cancel')]")));
		System.out.println("Set Status to Cancel");
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
		Commons.waitforElement(driver, By.xpath("//div[contains(@ng-show,'CANCEL')]//label[contains(text(),'Reason')]"),
				10);
		ActionUtils.click(driver.findElement(By.xpath("//div[@id='cancelReasonCodeDiv']//span[@class='k-select']")));
		Commons.waitforElement(driver, By.xpath("//ul[@id='cancelReasonCode_listbox']/li[1]"), 20);
		ActionUtils.click(driver.findElement(By.xpath("//ul[@id='cancelReasonCode_listbox']/li[1]")));
		ClickSaveAppointmentwindow(driver, true);
		return BeforeCount;
	}

	// No Show Appointment
	public int AppointmentNoShow(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		try {
			try {
				if (driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"))
						.size() >= 2) {
					System.out.println("Found existing appointments on scheduler page...");
				} else {
					System.out.println("Did not get more than 1 existing appointments on scheduler page...");
					AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
					AddScheduleUtils.ScheduleAppointment(driver);
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Did not get any existing appointments on scheduler page...");
				AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
				AddScheduleUtils.ScheduleAppointment(driver);
				Commons.waitForLoad(driver);
			}
			Commons.waitForLoad(driver);
			Commons.waitForLoad(driver);
			ActionUtils.click(SchedulerPage.ListViewButton(driver));
			Commons.waitForLoad(driver);
			if (!Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"), 30)) {
				ActionUtils.click(SchedulerPage.ListViewButton(driver));
			}
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"), 50);
			Commons.Explicitwait();
			BeforeCount = driver
					.findElements(By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr")).size();
			ActionUtils.click(SchedulerPage.CloseButtonListView(driver));
			// Clicking on Existing Appointment
			Commons.waitForLoad(driver);
			Thread.sleep(2000);
			ActionUtils.click(SchedulerPage.eventTemplate(driver));
			Thread.sleep(1000);
			ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
			Commons.Explicitwait();
			try {
				System.out.println("Waiting to open Existing appointment window");
				Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 15);
				System.out.println("opened Existing appointment window");
			} catch (Exception e) {
				
				if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'Edit Recurring Appointment')]"), 8)) {
					System.out.println("Opened Existing Recurring Appointment");
					Commons.screenshot();
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Edit Occurrence')]")));
					Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 15);
				}else{
				
				
				ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
				}
			}
			Thread.sleep(2000);
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			// Setting Status as No Show
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					40);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Thread.sleep(1000);
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='statusCode-list']//ul[@id='statusCode_listbox']/li[contains(text(),'No Show')]"),
					40);
			ActionUtils.click(driver.findElement(By.xpath(
					"//div[@id='statusCode-list']//ul[@id='statusCode_listbox']/li[contains(text(),'No Show')]")));
			System.out.println("Set Status to No Show");
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			Commons.existsElement(driver, By.xpath("//div[contains(@ng-show,'NOSHOW')]//label[contains(.,'Reason')]"));
			Commons.Explicitwait();
			Commons.existsElement(driver, By.xpath("//div[@id='noShowReasonCodeDiv']//span[@class='k-select']"));
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='noShowReasonCodeDiv']//span[@class='k-select']")));
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//ul[@id='noShowReasonCode_listbox']//li[1]"), 20);
			Thread.sleep(1000);
			ActionUtils.click(driver.findElement(By.xpath("//ul[@id='noShowReasonCode_listbox']//li[1]")));
			ClickSaveAppointmentwindow(driver, true);
		} catch (Exception e) {
			System.out.println("Unable to Set  Appointment to No Show" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		if (Commons.ExistandDisplayElement(driver, By.xpath("//a[contains(text(),'Cancel')]"), 5)) {
			Commons.waitForLoad(driver);
			driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
		}
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 100);
		return BeforeCount;
	}

	// Arrived Appointment
	public static boolean AppointmentArrived(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		try {
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"), 2);
				if (driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"))
						.size() >= 2) {
					System.out.println("Found existing appointments on scheduler page...");
				} else {
					System.out.println("Did not get morethan 1 existing appointments on scheduler page...");
					AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
					AddScheduleUtils.ScheduleAppointment(driver);
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Did not get any existing appointments on scheduler page...");
				AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
				AddScheduleUtils.ScheduleAppointment(driver);
				Commons.waitForLoad(driver);
			}
			// AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
			// AddScheduleUtils.ScheduleAppointment(driver);
			Commons.waitForLoad(driver);
			// Clicking on Existing Appointment
			ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
			Commons.Explicitwait();
			try {
				System.out.println("Waiting to open Existing appointment window");
				Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 15);
				System.out.println("opened Existing appointment window");
			} catch (Exception e) {
				ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
			}
			ActionUtils.click(SchedulerPage.Typefield(driver));
			// Setting Status as Arrived
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")).click();
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='statusCode-list']//ul[@id='statusCode_listbox']/li[contains(text(),'Arrived')]"),
					40);
			Thread.sleep(1000);
			driver.findElement(By
					.xpath("//div[@id='statusCode-list']//ul[@id='statusCode_listbox']/li[contains(text(),'Arrived')]"))
					.click();
			Commons.waitforElement(driver, By.xpath("//label[contains(.,'  Patient Payment Collected')]"),30);
			System.out.println("Set Status to Arrived");
			Commons.screenshot();
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ClickSaveAppointmentwindow(driver, false);
		} catch (Exception e) {
			System.out.println("Unable to Set Appointment to Arrived " + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		present = Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 10);
		return present;
	}

	public String ScheduleAppointmentOverBooking(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		try {
			int i = 0;
			while (i <= 1) {
				GotoAppointmentWindow(driver);
				QuickAddPatient(driver);
				setAppointmenttime(driver, "6");
				Commons.Explicitwait();
				// ActionUtils.click(SchedulerPage.ResourceField(driver));
				ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
				ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
				ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver),
						"This Is a Test String For Scheduler Notes");
				ActionUtils.click(SchedulerPage.Savebutton(driver));
				Commons.Explicitwait();
				i++;
				try {
					if (driver.findElement(By.xpath("//div[contains(@class,'toast-message')]")).isDisplayed()) {
						break;
					}
				} catch (Exception e) {
					System.out.println("Not reached Maximum bookings");
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to Save Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.strText_Actual = Commons.capturemessage(driver, 30);
		ActionUtils.click(SchedulerPage.Cancelbutton(driver));
		Commons.Explicitwait();
		return Commons.strText_Actual;
	}

	public static String setAppointmenttime(WebDriver driver, String time) {
		int i = 0;
		do {
			System.out.println("Seeting Appointment time as " + time);
			Commons.waitforElement(driver,By.xpath("//*[@id='startTimeAppt']//span[@aria-label='Open the time view']//span[contains(@class,'clock')]"),50);
			Commons.Explicitwait();
			WebElement clock = driver.findElement(By.xpath("//*[@id='startTimeAppt']//span[@aria-label='Open the time view']//span[contains(@class,'clock')]"));
			ActionUtils.click(clock);
			Commons.Explicitwait();
			WebElement Timestamp = driver.findElements(By.xpath("//div[@class='k-animation-container']//li[contains(text(), '" + time + ":00 AM')]")).get(0);
			Commons.scrollElementinotView(Timestamp, driver);
			present = Timestamp.isDisplayed();
			System.out.println(Timestamp.isDisplayed());
			System.out.println(Timestamp.getText());
			i++;
			try {
				System.out.println("Finding Appointment for time:      " +Timestamp.getText());
				
				ActionUtils.click(Timestamp);
			} catch (Exception e) {
				System.out.println("Searching for  Appointment time in list" + time);
				time=time+1;
			}
		} while (!present && i < 10);
		return time;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String ScheduleRecurringAppointment(WebDriver driver, String Type, String Status) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		// SelectViewBy(driver, "Provider View","Automation BMS Provider");
		GotoAppointmentWindow(driver);
		QuickAddPatient(driver);
		try {
			setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.SelectType(driver, Type));
			ActionUtils.JavaScriptclick(SchedulerPage.SelectStatus(driver, Status));
			// ActionUtils.click(SchedulerPage.ResourceField(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ActionUtils.click(SchedulerPage.Recurrencefield(driver, "Daily"));
			ActionUtils.click(driver.findElement(By.xpath("//input[@name='end'][@value='count']")));
			ClickSaveAppointmentwindow(driver, false);
			Commons.strText_Actual = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
					.replaceAll("\\s", "");
			// *[@id='eventTemplate']//span[contains(@class,'history')]
		} catch (Exception e) {
			System.out.println("Unable to Save Appointment" + e);
			Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		return Commons.strText_Actual;
	}

	public String EditRecurringAppointment(WebDriver driver, String EditType) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		// SelectViewBy(driver, "Provider View","Automation BMS Provider");
		Commons.waitForLoad(driver);
		Thread.sleep(5000);
		ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'Edit Recurring Appointment')]"), 8)) {
			System.out.println("Opened Existing Recurring Appointment");
			Commons.screenshot();
		} else {
			ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
		}
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Edit Recurring Appointment')]"), 20);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[contains(.,'Edit Recurring Appointment')]")).getText().trim(),
				"Edit Recurring Appointment");
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,'Edit Occurrence')]/preceding::div[1]")).getText()
				.trim().contains("You are editing a recurring appointment."));
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'" + EditType + "')]")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		try {
			SchedulerPage.AppointmentNote(driver).clear();
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver),
					"Automation Test for Editing Recurring Appointment");
			
			if(EditType.contains("Edit Series")){
				driver.findElement(By.xpath("(//span[contains(@class,'k-icon k-i-arrow-60-up')])[2]")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("(//span[contains(@class,'k-icon k-i-arrow-60-up')])[2]")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("(//span[contains(@class,'k-icon k-i-arrow-60-up')])[2]")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("(//span[contains(@class,'k-icon k-i-arrow-60-up')])[2]")).click();
				Thread.sleep(500);
			}
			ClickSaveAppointmentwindow(driver, false);
			Commons.strText_Actual = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
					.replaceAll("\\s", "");
			// *[@id='eventTemplate']//span[contains(@class,'history')]
		} catch (Exception e) {
			System.out.println("Unable to Save Appointment" + Throwables.getStackTraceAsString(e));
			Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		return Commons.strText_Actual;
	}

	public String CancelRecurringAppointment(WebDriver driver, String EditType) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		// SelectViewBy(driver, "Provider View","Automation BMS Provider");
		Commons.waitForLoad(driver);
		Thread.sleep(5000);
		ActionUtils.click(SchedulerPage.eventTemplate(driver));
		ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'Edit Recurring Appointment')]"), 8)) {
			System.out.println("Opened Existing Recurring Appointment");
			Commons.screenshot();
		} else {
			ActionUtils.doubleClick(driver, SchedulerPage.eventTemplate(driver));
		}
		Commons.waitforElement(driver, By.xpath("//span[contains(.,'Edit Recurring Appointment')]"), 20);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[contains(.,'Edit Recurring Appointment')]")).getText().trim(),
				"Edit Recurring Appointment");
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(.,'Edit Occurrence')]/preceding::div[1]")).getText()
				.trim().contains("You are editing a recurring appointment."));
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'" + EditType + "')]")));
		Commons.waitForLoad(driver);
		Commons.screenshot();
		try {
			// setAppointmenttime(driver,String.valueOf(new
			// Random().nextInt(10)));
			// ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			// ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			// ActionUtils.click(SchedulerPage.ResourceField(driver));
			SchedulerPage.AppointmentNote(driver).clear();
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver),
					"Automation Test for Editing Recurring Appointment");
			// ActionUtils.click(SchedulerPage.Recurrencefield(driver));
			ActionUtils.click(SchedulerPage.Recurrencefield(driver, "Never"));
			// Setting Status as Cancel
			Thread.sleep(1000);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Cancel')]"), 40);
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Cancel')]")));
			System.out.println("Set Status to Cancel");
			ActionUtils.click(SchedulerPage.Savebutton(driver));
			Commons.waitforElement(driver,
					By.xpath("//div[contains(@ng-show,'CANCEL')]//label[contains(text(),'Reason')]"), 10);
			Thread.sleep(1000);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='cancelReasonCodeDiv']//span[@class='k-select']")));
			Commons.waitforElement(driver, By.xpath("//ul[@id='cancelReasonCode_listbox']/li[1]"), 20);
			ActionUtils.click(driver.findElement(By.xpath("//ul[@id='cancelReasonCode_listbox']/li[1]")));
			ActionUtils.click(SchedulerPage.Savebutton(driver));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='saveWithNeverRecurrence']"), 15)) {
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='saveWithNeverRecurrence']")));
			}
			Assert.assertTrue(
					Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 40),
					"Could not close appintment window");
			Commons.screenshot();
			Commons.strText_Actual = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
					.replaceAll("\\s", "");
			// *[@id='eventTemplate']//span[contains(@class,'history')]
		} catch (Exception e) {
			System.out.println("Unable to Save Appointment" + e);
			Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		return Commons.strText_Actual;
	}

	// To Cancel an Existing appointment and return Patient Name to check
	// Charges
	public String ScheduleAppointmentForCancel(WebDriver driver) {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		GotoAppointmentWindow(driver);
		String str_Patient_Name = QuickAddPatientCancel(driver);
		try {
			setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			ActionUtils.click(SchedulerPage.Savebutton(driver));
			Commons.Explicitwait();
			try {
				System.out.println("Waiting for any warning window to appear....");
				Commons.waitforElement(driver,
						By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 6);
				if (driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"))
						.isDisplayed()) {
					ActionUtils.click(driver
							.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]")));
					System.out.println("Warning closed successfully");
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Warning Pop up not appeared");
				if (Commons.existsElement(driver, By.xpath("//a[contains(text(),'Cancel')]"))) {
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")));
					System.out.println("Cannot schedule appointment as time conflicts");
				}
			}
			Assert.assertTrue(
					Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 40),
					"Could not close appintment window");
			Commons.Explicitwait();
			Commons.strText_Actual = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
					.replaceAll("\\s", "");
		} catch (Exception e) {
			System.out.println("Unable to Save Appointment" + e);
			Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
		}
		return str_Patient_Name;
	}

	public String QuickAddPatientCancel(WebDriver driver) {
		String str_Patient_Name = new String();
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			ActionUtils.click(SchedulerPage.QuickAddPatientbutton(driver));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
			str_Patient_Name = AddPatientUtils.strFirstNameCancel + new Random().nextInt(1000);
			ActionUtils.sendKeys(SchedulerPage.firstname(driver), str_Patient_Name);
			ActionUtils.sendKeys(SchedulerPage.lastname(driver),
					AddPatientUtils.strLastNametemp + new Random().nextInt(1000));
			ActionUtils.sendKeys(SchedulerPage.homenumber(driver), AddPatientUtils.strPhonetemp);
			ActionUtils.click(SchedulerPage.gender(driver));
			Commons.Explicitwait();
			HandlingCalendars.datepick(driver, By.xpath("//div[@id='dob-patient']//span//button"), "12/05/1993");
			ActionUtils.sendKeys(SchedulerPage.casename(driver), "Case" + new Random().nextInt(1000));
			ActionUtils.JavaScriptclick(SchedulerPage.PatientSave(driver));
			Commons.Explicitwait();
			if (driver
					.findElement(
							By.xpath("//div[@id='duplicatePatientPopup']//button[@class='btn btn-primary btnGreen']"))
					.isDisplayed()) {
				System.out.println(
						"duplicate patient pop up Found" + "    " + AddPatientPage.dup_save_btn(driver).getText());
				ActionUtils.click(AddPatientPage.dup_save_btn(driver));
			}
		} catch (Exception e) {
			System.out.println("Unable to Quick Add patient" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Unable to Quick Add patient" + Throwables.getStackTraceAsString(e));
		}
		Commons.Explicitwait();
		driver.switchTo().defaultContent();
		System.out.println("Navigate back to appointment window...");
		// Commons.waitForElementToBeNotVisible(driver,
		// By.xpath("//button[@id='quickAddPatient']"),15);
		Commons.waitForElementToBeNotVisible(driver,
				By.xpath("//div[contains(@class,'quickAddWindow')]//span[@id='quickAddPatientWindow_wnd_title']"), 35);
		System.out.println("Patient Add steps completed");
		Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 15);
		return str_Patient_Name;
	}

	// To Cancel an Existing appointment and return Patient Name to check
	// Charges
	public String CancelAppointmentGetPatientName(WebDriver driver) {
		String str_Patient_Name = new String();
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		SelectViewBy(driver, "Provider View", "Automation BMS Provider");
		try {
			try {
				if (driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'CancelQA')]"))
						.size() >= 2) {
					System.out.println("Found existing appointments on scheduler page...");
				} else {
					// Add Appoitment for Cancel Patient
					System.out.println("Did not get more than 1 existing appointments on scheduler page...");
					AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
					str_Patient_Name = AddScheduleUtils.ScheduleAppointmentForCancel(driver);
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Did not get existing appointments on scheduler page...");
				AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
				AddScheduleUtils.ScheduleAppointmentForCancel(driver);
				Commons.waitForLoad(driver);
			}
			// AddScheduleUtils AddScheduleUtils = new AddScheduleUtils();
			// AddScheduleUtils.ScheduleAppointment(driver);
			Commons.waitForLoad(driver);
			ActionUtils.click(SchedulerPage.ListViewButton(driver));
			Commons.Explicitwait();
			if (!Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"), 30)) {
				ActionUtils.click(SchedulerPage.ListViewButton(driver));
			}
			Commons.waitforElement(driver,
					By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr"), 30);
			BeforeCount = driver
					.findElements(By.xpath("//div[@id='gridSearchModel']//div[contains(@class,'content')]//tr")).size();
			System.out.println("No. Of appointment Before Cancel" + "  " + BeforeCount);
			ActionUtils.click(SchedulerPage.CloseButtonListView(driver));
			int i = 0;
			// Clicking on Existing Appointment
			do {
				try {
					ActionUtils.click(SchedulerPage.eventTemplateCancel(driver));
					ActionUtils.doubleClick(driver, SchedulerPage.eventTemplateCancel(driver));
					Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 10);
					i++;
				} catch (Exception e) {
					System.out.println("Did not open Existing appointment retrying....");
				}
			} while (!Commons.existsElement(driver, By.xpath("//button[@id='quickAddPatient']")) && i < 5);
			Thread.sleep(1000);
			Thread.sleep(1000);
			ActionUtils.click(SchedulerPage.Typefield(driver));
			// Setting Status as Cancel
			Thread.sleep(1000);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Cancel')]"), 40);
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='statusCode_listbox']/li[contains(text(),'Cancel')]")));
			System.out.println("Set Status to Cancel");
			// setAppointmenttime(driver,"9");
			// ActionUtils.click(SchedulerPage.Statusfield(driver));
			// ActionUtils.click(SchedulerPage.ResourceField(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			// ActionUtils.click(SchedulerPage.Savebutton(driver));
			Commons.waitforElement(driver,
					By.xpath("//div[contains(@ng-show,'CANCEL')]//label[contains(text(),'Reason')]"), 10);
			Thread.sleep(1000);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='cancelReasonCodeDiv']//span[@class='k-select']")));
			Commons.waitforElement(driver, By.xpath("//ul[@id='cancelReasonCode_listbox']/li[1]"), 20);
			ActionUtils.click(driver.findElement(By.xpath("//ul[@id='cancelReasonCode_listbox']/li[1]")));
			ActionUtils.click(SchedulerPage.Savebutton(driver));
			Commons.Explicitwait();
			Commons.existsElement(driver, By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"));
			try {
				System.out.println("Checking for Warning Pop up to appeared");
				Commons.waitforElement(driver,
						By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 10);
				if (driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"))
						.isDisplayed()) {
					System.out.println("Warning Pop up  appeared");
					Commons.waitforElement(driver,
							By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 10);
					driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"))
							.click();
					System.out.println("Warning closed successfully");
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Warning Pop up not appeared");
			}
			try {
				if (driver.findElement(By.xpath("//*[@id='saveWithpaymentCancelCharge']")).isDisplayed()) {
					Commons.waitforElement(driver, By.xpath("//*[@id='saveWithpaymentCancelCharge']"), 10);
					driver.findElement(By.xpath("//*[@id='saveWithpaymentCancelCharge']")).click();
					System.out.println("Charges Added to Patient for cancel");
					System.out.println("Warning closed successfully");
					Commons.waitForLoad(driver);
				}
			} catch (Exception e) {
				System.out.println("Warning Pop up not appeared");
			}
		} catch (Exception e) {
			System.out.println("Unable to Cancel Appointment" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		if (Commons.existsElement(driver, By.xpath("//a[contains(text(),'Cancel')]"))) {
			Commons.waitForLoad(driver);
			driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
		}
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 100);
		return str_Patient_Name;
	}

	public static void addnoShowappointmnet(WebDriver driver) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithNoShow(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.NoShowReasonForgot(driver));
		Thread.sleep(3000);
		Commons.screenshot();
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For No show Notes");
		ActionUtils.click(SchedulerPage.Savebutton(driver));
		Commons.Explicitwait();
		try {
			System.out.println("Waiting for any warning window to appear....");
			Commons.waitforElement(driver, By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"),
					6);
			if (driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"))
					.isDisplayed()) {
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]")));
				System.out.println("Warning closed successfully");
				Commons.waitForLoad(driver);
			}
		} catch (Exception e) {
			System.out.println("Warning Pop up not appeared");
			if (Commons.existsElement(driver, By.xpath("//a[contains(text(),'Cancel')]"))) {
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")));
				System.out.println("Cannot schedule appointment as time conflicts");
			}
			Assert.assertTrue(
					Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 40),
					"Could not close appintment window");
			Commons.Explicitwait();
		}
	}

	public static void addCancelappointmnet(WebDriver driver) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithcancel(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.cancelReason(driver));
		Thread.sleep(3000);
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For cancel Notes");
		ClickSaveAppointmentwindow(driver, false);
	}

	public static void addrescheduleappointmnet(WebDriver driver) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithReschedule(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.RescheduleReason(driver));
		Thread.sleep(3000);
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For reschedule Notes");
		ClickSaveAppointmentwindow(driver, false);
	}

	public static void addArrivedAppointmnets(WebDriver driver) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithArrived(driver));
		Thread.sleep(3000);
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Arrived Notes");
		ClickSaveAppointmentwindow(driver, false);
	}

	public static void addVoidAppointmnet(WebDriver driver) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithVoid(driver));
		Thread.sleep(3000);
		
	

		ActionUtils.click(SchedulerPage.ResourceField(driver, "Massage"));

		System.out.println("Verify Resource clear button functionality");
		ActionUtils.JavaScriptclick(SchedulerPage.ResourceField(driver, "Massage"));
		Commons.screenshot();

		Commons.waitforElement(driver, By.xpath("//span[contains(@unselectable,'on')][(text()='Massage')]"),30);
		Commons.waitforElement(driver, By.xpath("//*[@data-container-for='facResourceCode']//span[@class='k-select']//span"), 40);
		driver.findElement(By.xpath("//*[@data-container-for='facResourceCode']//span[@class='k-select']//span")).click();
		Thread.sleep(1000);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='clear']")));
		Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(@unselectable,'on')][(text()='Massage')]"),5));
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For void Notes");
		ClickSaveAppointmentwindow(driver, false);
	}

	public static void addScheduleAppointmnet(WebDriver driver) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
		Thread.sleep(3000);
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Schedule Notes");
		ClickSaveAppointmentwindow(driver, false);
	}

	public static  List<String> addGroupAppointmnet(WebDriver driver) throws InterruptedException {
		List<String> patients = new LinkedList<String>();
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
		Thread.sleep(3000);
		String fieldset = driver
				.findElement(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']")).getText();
		System.out.println("added patient is " + fieldset);
		String splits[] = fieldset.split(" ");
		String name = splits[3] + " " + splits[4];
		String patientId = splits[1];
		System.out.println("split is " + name);
		patients.add(name);
		AddScheduleUtils.QuickAddPatient(driver);
		Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
		ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
		Thread.sleep(3000);
		fieldset = driver.findElement(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"))
				.getText();
		System.out.println("added patient is " + fieldset);
		String splits2[] = fieldset.split(" ");
		String name2 = splits2[3] + " " + splits2[4];
		String patientId2 = splits[1];
		patients.add(name2);
		patients.add(patientId);
		patients.add(patientId2);
		Commons.waitForLoad(driver);
		ClickSaveAppointmentwindow(driver, false);
		return patients;
	}

	public static  List<String> addGroupAppointmnetAsCancel(WebDriver driver) throws InterruptedException {
		List<String> patients = new LinkedList<String>();
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
		AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
		ActionUtils.JavaScriptclick(SchedulerPage.GroupTypefield(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.StatusfieldWithcancel(driver));
		ActionUtils.JavaScriptclick(SchedulerPage.cancelReason(driver));
		Thread.sleep(3000);
		ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For cancel Notes");
		Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
		ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
		Commons.waitForLoad(driver);
		Thread.sleep(3000);
		String fieldset = driver
				.findElement(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']")).getText();
		System.out.println("added patient is " + fieldset);
		String splits[] = fieldset.split(" ");
		String name = splits[3] + " " + splits[4];
		String patientId = splits[1];
		System.out.println("split is " + name);
		patients.add(name);
		AddScheduleUtils.QuickAddPatient(driver);
		Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmScheduler.addGroup()']"), 120);
		ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmScheduler.addGroup()']")));
		Thread.sleep(3000);
		fieldset = driver.findElement(By.xpath("//fieldset[@ng-repeat='groupDetail in vmScheduler.groupDetails']"))
				.getText();
		System.out.println("added patient is " + fieldset);
		String splits2[] = fieldset.split(" ");
		String name2 = splits2[3] + " " + splits2[4];
		String patientId2 = splits[1];
		patients.add(name2);
		patients.add(patientId);
		patients.add(patientId2);
		Commons.waitForLoad(driver);
		ClickSaveAppointmentwindow(driver, false);
		return patients;
	}

	public static void ClickSaveAppointmentwindow(WebDriver driver, Boolean policychargequeue) {
		Commons.screenshot();
		ActionUtils.click(SchedulerPage.Savebutton(driver));
		// Handled Check for Time conflict
		System.out.println("Waiting to check if there any conflict.");
		System.out.println("Saving Appointment...");
		/*
		 * String checktoast = Commons.capturemessage(driver,3); if (checktoast
		 * != null) { if(checktoast.contains("conflicts")){ System.out.println(
		 * "Time conflict error found.... rescheduling time"); int i = 0; do {
		 * AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new
		 * Random().nextInt(10)));
		 * ActionUtils.click(SchedulerPage.Savebutton(driver)); i++; } while
		 * (Commons.capturemessage(driver,3) != null&& i<3); } }
		 */
		// Handled Out side provider hours warning
		System.out.println("Checking for Warning Pop up for Outside provider Hours");
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"), 5)) {
			System.out.println("Warning Pop up  appeared");
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]"),
					10);
			driver.findElement(By.xpath("//div[@id='warningPopup']//button[contains(@class,'btnGreen')]")).click();
			System.out.println("Warning closed successfully");
			Commons.waitForLoad(driver);
		}
		// Handled Policy Charge Queue Warning
		if (policychargequeue == true) {
			System.out.println("Checking for Warning Pop up for Policy Charges for Cancel/No Show.....");
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='saveWithpaymentCancelCharge']"), 5)) {
				Commons.screenshot();
				Commons.waitforElement(driver, By.xpath("//*[@id='saveWithpaymentCancelCharge']"), 10);
				driver.findElement(By.xpath("//*[@id='saveWithpaymentCancelCharge']")).click();
				System.out.println("Charges Added to Patient for No Show");
				System.out.println("Warning closed successfully");
				Commons.waitForLoad(driver);
			}
		}
		try {
			Assert.assertTrue(
					Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 100));
		} catch (AssertionError a) {
			System.out.println("*************Error while saving appointment***************");
			ActionUtils.click(SchedulerPage.Savebutton(driver));
			Commons.screenshot();
			if (Commons.ExistandDisplayElement(driver, By.xpath("//a[contains(text(),'Cancel')]"), 5)) {
				System.out.println("Could not create Appointment");
				Commons.screenshot();
				Commons.waitForLoad(driver);
				driver.findElement(By.xpath("//a[contains(text(),'Cancel')]")).click();
			}
			Commons.screenshot();
			Assert.assertTrue(false, "Error in saving appointment" + Throwables.getStackTraceAsString(a));
		}
		System.out.println("Appointment Saved Successfully..");
	}

	public static void CreateInitialNoteFromProviderHomePage(WebDriver driver, String redDotonProviderHomepage,
			String POCEnddate) throws InterruptedException {
		Commons.scrollElementinotView(driver.findElement(By.xpath(redDotonProviderHomepage)), driver);
		Assert.assertTrue(
				Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(redDotonProviderHomepage)), 45));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'",
				driver.findElement(By.xpath(redDotonProviderHomepage)));
		ActionUtils.click(driver.findElement(By.xpath(redDotonProviderHomepage)));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.name("ddlVisitType"), 120);
		String date = driver.findElement(By.xpath("//*[@id='DateOfService']/div/input")).getAttribute("value");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date2 = LocalDate.now();
		Assert.assertTrue(date.contains(date2.format(dtf)));
		System.out.println("dos is " + date);
		ActionUtils.click(CreateNotePage.AddAll(driver, "bodyparts"));
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 200);
		System.out.println("Initial-Visit Note crated Successfully");
		ActionUtils.click(APage.APageLink(driver));
		Commons.waitForLoad(driver);
		APageUtils.ClickCandidateTherapy(driver, "no");
		if (Commons.ExistandDisplayElement(driver, By.id("Apage_Medicare-Functional-Limitation-Reporting"), 20)) {
			APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation");
		}
		ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
		Commons.waitForLoad(driver);
		ActionUtils.sendKeys(driver.findElement(By.name("POCEndDate")), POCEnddate);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
			System.out.println("Warning Pop up appeared for No charges");
			Commons.screenshot();
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		}
		if (Commons.ExistandDisplayElement(driver,
				By.xpath(
						"//div[contains(text(),'For the unaccounted for 10 minutes, were these minutes associated with one of the codes already entered?')]"),
				10)) {
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Yes'][@type='button']")));
		}
		Commons.waitForLoad(driver);
		System.out.println("clicking on sign and finalize button ");
		ActionUtils.click(driver.findElement(By.id("signFinalNoAttachButton")));
		System.out.println("Waiting for Note Finalize...");
		Commons.waitForLoad(driver);
		System.out.println("Finalizing Note...");
		TestSetUp.test.log(LogStatus.INFO, "Finalized Note.");
		Commons.screenshot();
		Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 80);
		System.out.println("note finalized");
		Commons.waitForLoad(driver);
	}

	public static void CreateAddendumNoteFromProviderHomePage(WebDriver driver, String redDotonProviderHomepage,
			String POCEnddate) throws InterruptedException {
		Commons.scrollElementinotView(driver.findElement(By.xpath(redDotonProviderHomepage)), driver);
		Assert.assertTrue(
				Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(redDotonProviderHomepage)), 120));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'",
				driver.findElement(By.xpath(redDotonProviderHomepage)));
		ActionUtils.click(driver.findElement(By.xpath(redDotonProviderHomepage)));
		Commons.waitForLoad(driver);
		Thread.sleep(5000);
		Commons.waitforElement(driver, By.id("addAddendumNoteBtn"), 90);
		ActionUtils.click(driver.findElement(By.id("addAddendumNoteBtn")));
		Commons.waitForLoad(driver);
		Thread.sleep(5000);
		Commons.waitforElement(driver, By.id("addendumStatement"), 90);
		Commons.scrollElementinotView(driver.findElement(By.id("addendumStatement")), driver);
		ActionUtils.sendKeys(driver.findElement(By.id("addendumStatement")), "test add");
		Thread.sleep(5000);
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 200);
		System.out.println("Initial-Visit Note crated Successfully");
		ActionUtils.click(APage.APageLink(driver));
		Commons.waitForLoad(driver);
		APageUtils.ClickCandidateTherapy(driver, "no");
		ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
		Commons.waitForLoad(driver);
		ActionUtils.clear(driver.findElement(By.name("POCEndDate")));
		ActionUtils.sendKeys(driver.findElement(By.name("POCEndDate")), POCEnddate);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
			System.out.println("Warning Pop up appeared for No charges");
			Commons.screenshot();
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		}
		if (Commons.ExistandDisplayElement(driver,
				By.xpath(
						"//div[contains(text(),'For the unaccounted for 10 minutes, were these minutes associated with one of the codes already entered?')]"),
				10)) {
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Yes'][@type='button']")));
		}
		Commons.waitForLoad(driver);
		System.out.println("clicking on sign and finalize button ");
		ActionUtils.click(driver.findElement(By.id("signFinalNoAttachButton")));
		System.out.println("Waiting for Note Finalize...");
		Commons.waitForLoad(driver);
		System.out.println("Finalizing Note...");
		TestSetUp.test.log(LogStatus.INFO, "Finalized Note.");
		Commons.screenshot();
		Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 80);
		System.out.println("note finalized");
		Commons.waitForLoad(driver);
	}

	public static String checkingMedProdStatus(WebDriver driver, String newName) {
		String medProg = null;
		try {
			driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span")).click();
			Thread.sleep(100);
			Commons.waitForLoad(driver);
			System.out.println("checking medProg value");
			String xpath = "//u[contains(.,'" + newName + "')]/ancestor::span/parent::td/following-sibling::td[5]";
			Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
			Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
			medProg = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("MedProg display is " + medProg);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
		} catch (Exception e) {
			System.out.println("unable to get sttaus of medProg");
		}
		return medProg;
	}

	public static void CreateFollowUpNoteFromProviderHomePage(WebDriver driver, String redDotonProviderHomepage,
			String POCEnddate) throws InterruptedException {
		Commons.scrollElementinotView(driver.findElement(By.xpath(redDotonProviderHomepage)), driver);
		Assert.assertTrue(
				Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(redDotonProviderHomepage)), 120));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'",
				driver.findElement(By.xpath(redDotonProviderHomepage)));
		ActionUtils.click(driver.findElement(By.xpath(redDotonProviderHomepage)));
		Commons.waitForLoad(driver);
		Thread.sleep(5000);
		// Commons.waitforElement(driver, By.id("addAddendumNoteBtn"), 120);
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
		System.out.println("Opened Create Note Screen");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver,
				By.xpath("//select[@id='ddlCases']//option[contains(text(),'Case With All Fields')]"), 20);
		ActionUtils.click(driver
				.findElement(By.xpath("//select[@id='ddlCases']//option[contains(text(),'Case With All Fields')]")));
		Commons.waitForLoad(driver);
		CreateNotePage.VisitTypeddl(driver, "Followup Visit");
		Commons.Explicitwait();
		Thread.sleep(5000);
		ActionUtils.click(CasePage.LocationMagnifier(driver));
		Commons.waitforElement(driver, By.id("Location_Name"), 30);
		ActionUtils.sendKeys(driver.findElement(By.id("Location_Name")), "automation BMS");
		ActionUtils.click(CasePage.LocationSearchButton(driver));
		Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='LocationId']//table//tbody/tr[1]/td[1]"), 60);
		ActionUtils.doubleClick(driver,
				driver.findElement(By.xpath("//div[@id='LocationId']//table//tbody/tr[1]/td[1]")));
		Commons.waitForLoad(driver);
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		try {
			Commons.waitforElement(driver, By.xpath("//div[@data-ng-bind-html='vmNote.planOfCareMsg |to_trusted']"),
					30);
			ActionUtils.click(driver.findElement(By.xpath("//input[@data-ng-click='vmNote.cancelPlanOfCareAlert()']")));
		} catch (Exception e) {
			System.out.println("plan of care alert not present");
		}
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 200);
		System.out.println("Follow up Note crated Successfully");
		ActionUtils.click(APage.APageLink(driver));
		Commons.waitForLoad(driver);
		APageUtils.ClickCandidateTherapy(driver, "no");
		ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToPlan()']")));
		Commons.waitForLoad(driver);
		ActionUtils.clear(driver.findElement(By.name("POCEndDate")));
		ActionUtils.sendKeys(driver.findElement(By.name("POCEndDate")), POCEnddate);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
			System.out.println("Warning Pop up appeared for No charges");
			Commons.screenshot();
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		}
		if (Commons.ExistandDisplayElement(driver,
				By.xpath(
						"//div[contains(text(),'For the unaccounted for 10 minutes, were these minutes associated with one of the codes already entered?')]"),
				10)) {
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Yes'][@type='button']")));
		}
		Commons.waitForLoad(driver);
		System.out.println("clicking on sign and finalize button ");
		ActionUtils.click(driver.findElement(By.id("signFinalNoAttachButton")));
		System.out.println("Waiting for Note Finalize...");
		Commons.waitForLoad(driver);
		System.out.println("Finalizing Note...");
		TestSetUp.test.log(LogStatus.INFO, "Finalized Note.");
		Commons.screenshot();
		Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 80);
		System.out.println("note finalized");
		Commons.waitForLoad(driver);
	}

	public static void CreateFollowupWithMedicalProgressReport(WebDriver driver, String POCEnddate)
			throws InterruptedException {
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
		System.out.println("Opened Create Note Screen");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver,
				By.xpath("//select[@id='ddlCases']//option[contains(text(),'Case With All Fields')]"), 20);
		ActionUtils.click(driver
				.findElement(By.xpath("//select[@id='ddlCases']//option[contains(text(),'Case With All Fields')]")));
		Commons.waitForLoad(driver);
		CreateNotePage.VisitTypeddl(driver, "Followup Visit");
		Commons.Explicitwait();
		Thread.sleep(5000);
		ActionUtils.click(CasePage.LocationMagnifier(driver));
		Commons.waitforElement(driver, By.id("Location_Name"), 30);
		ActionUtils.sendKeys(driver.findElement(By.id("Location_Name")), "automation BMS");
		ActionUtils.click(CasePage.LocationSearchButton(driver));
		Commons.waitforElement(driver, By.xpath("//div[@id='LocationId']//table//tbody/tr[1]/td[1]"), 30);
		ActionUtils.doubleClick(driver,
				driver.findElement(By.xpath("//div[@id='LocationId']//table//tbody/tr[1]/td[1]")));
		Commons.waitForLoad(driver);
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 200);
		System.out.println("Initial-Visit Note crated Successfully");
		/*
		 * ActionUtils.click(APage.APageLink(driver));
		 * Commons.waitForLoad(driver); APageUtils.ClickCandidateTherapy(driver,
		 * "no"); ActionUtils.click(driver.findElement(By.xpath(
		 * "//a[@data-ng-click='redirectToPlan()']")));
		 * Commons.waitForLoad(driver);
		 * ActionUtils.clear(driver.findElement(By.name("POCEndDate")));
		 * ActionUtils.sendKeys(driver.findElement(By.name("POCEndDate")),
		 * POCEnddate);
		 */
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
		ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
		ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
		System.out.println("Clicked on Validate Charge button");
		Commons.waitForLoad(driver);
		if (Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']"), 10)) {
			System.out.println("Warning Pop up appeared for No charges");
			Commons.screenshot();
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@id='NoChargesZeroChargesMessagePopUp']//input[@value='OK']")));
		}
		if (Commons.ExistandDisplayElement(driver,
				By.xpath(
						"//div[contains(text(),'For the unaccounted for 10 minutes, were these minutes associated with one of the codes already entered?')]"),
				10)) {
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//input[@value='Yes'][@type='button']")));
		}
		Commons.waitForLoad(driver);
		System.out.println("clicking on sign and finalize button ");
		ActionUtils.click(driver.findElement(By.id("signFinalNoAttachButton")));
		System.out.println("Waiting for Note Finalize...");
		Commons.waitForLoad(driver);
		System.out.println("Finalizing Note...");
		TestSetUp.test.log(LogStatus.INFO, "Finalized Note.");
		Commons.screenshot();
		Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 80);
		System.out.println("note finalized");
		Commons.waitForLoad(driver);
	}
	
	public static String addRecurringMeeting(WebDriver driver) throws InterruptedException {
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
		Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
		System.out.println("Opened View Schedule page");
		AddScheduleUtils.GotoAppointmentWindow(driver);
		ActionUtils.click(driver.findElement(By.id("meetingForm")));
		String message="Meeting recurring "+new Random().nextInt(1000)+new Random().nextInt(1000);
		Thread.sleep(2000);
		ActionUtils.sendKeys(driver.findElement(By.id("meetingApptNote")), message);
		Thread.sleep(2000);
		ActionUtils.click(driver.findElement(By.id("AddAllProviders")));
		ActionUtils.click(driver.findElement(By.xpath("(//span[@title='Recurrence editor']/span/span/span)[2]")));
		Thread.sleep(2000);
	    ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Daily')]")));
	    try
	    {
		ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
	    }
	    catch (Exception e)
	    {
	    	ActionUtils.click(driver.findElement(By.xpath("(//span[@title='Recurrence editor']/span/span/span)[2]")));
	    	Thread.sleep(2000);
		    ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Daily')]")));
		    Thread.sleep(2000);
		    ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
	    }
	    Thread.sleep(2000);
		ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
		ClickSaveAppointmentwindow(driver, false);
		return message;
	}
	
	public static String addRecurringAppointmentwithNewCase(WebDriver driver) throws InterruptedException {
		String casename = null;
		try
		{
		
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
	    Commons.waitForLoad(driver);
	    Commons.waitforElement(driver, By.xpath("//*[@id='caseDropDown']//span[@class='k-select']"), 80);
	    ActionUtils.click(driver.findElement(By.xpath("//*[@id='caseDropDown']//span[@class='k-select']")));
	   Thread.sleep(2000);
	    try {
		Commons.scrollElementinotView(driver.findElement(By.xpath("//li[contains(.,'Create New Case')]")),
				driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[contains(.,'Create New Case')]")).click();
		Commons.screenshot();
	} catch (Exception e) {
		int list = driver.findElements(By.xpath("//*[@id='cases_listbox']//li[@role='option']")).size();
		ActionUtils.click(
				driver.findElements(By.xpath("//*[@id='cases_listbox']//li[@role='option']")).get(list - 1));
	}
	System.out.println("Setting New case in  Appointment");
	Commons.Explicitwait();
	casename = "Test" + new Random().nextInt(1000);
	ActionUtils.sendKeys(SchedulerPage.newcasename(driver), casename);
	ActionUtils.click(driver.findElement(By.xpath("(//span[@title='Recurrence editor']/span/span/span)[1]")));
	Thread.sleep(1000);
    ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Daily')]")));
    try
    {
	ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
    }
    catch (Exception e)
    {
    	ActionUtils.click(driver.findElement(By.xpath("(//span[@title='Recurrence editor']/span/span/span)[1]")));
    	Thread.sleep(1000);
	    ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Daily')]")));
	    Thread.sleep(1000);
	    ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
    }
    ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
	ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
	ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
	ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
	
	//ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
	ClickSaveAppointmentwindow(driver, false);
} catch (Exception e) {
	System.out.println("Unable to Set New case in  Appointment" + e);
	Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
	Commons.Explicitwait();
}

Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 100);
Commons.waitForLoad(driver);
Commons.screenshot();
return casename;
}

	
	public static void addRecurringAppointment(WebDriver driver , int days) throws InterruptedException {
		
		try
		{
		
		AddScheduleUtils.GotoAppointmentWindow(driver);
		AddScheduleUtils.QuickAddPatient(driver);
	    Commons.waitForLoad(driver);
	 
	ActionUtils.click(driver.findElement(By.xpath("(//span[@title='Recurrence editor']/span/span/span)[1]")));
	Thread.sleep(1000);
    ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Daily')]")));
    try
    {
	ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
	
    }
    catch (Exception e)
    {
    	ActionUtils.click(driver.findElement(By.xpath("(//span[@title='Recurrence editor']/span/span/span)[1]")));
    	Thread.sleep(1000);
	    ActionUtils.click(driver.findElement(By.xpath("//div[@class='k-animation-container']//li[contains(text(),'Daily')]")));
	    ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']")));
    }
    if(days>2)
    {
     for(int i=3;i<=days ; i++)
     {
    	  ActionUtils.click(driver.findElement(By.xpath("//span[@class='k-widget k-numerictextbox k-recur-count']//span[2]/span[@aria-label='Increase value']"))); 
     }
    }
  
	ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
	ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
	ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
	ClickSaveAppointmentwindow(driver, false);
} catch (Exception e) {
	System.out.println("Unable to Set New case in  Appointment" + e);
	Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
	Commons.Explicitwait();
}

Commons.waitForElementToBeNotVisible(driver, By.xpath("//a[contains(text(),'Save')]"), 100);
Commons.waitForLoad(driver);
Commons.screenshot();

}

}


