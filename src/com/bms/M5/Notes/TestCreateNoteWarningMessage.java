package com.bms.M5.Notes;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import UIMap.LoginPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.CreateNoteUtils;
import Utils.HandlingCalendars;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestCreateNoteWarningMessage extends TestSetUp {
	public String strActualtext;

	@Test(enabled = true, priority = 1)
	public void ValidateNoteMandtoryfields() throws InterruptedException {
		test = extent.startTest(
				"To Validate date of service not matching appointment = warning message and required field validation (Initial, Followup, Followup Re-Evaluation)",
				"required Field validation" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		try {
			System.out.println("Clearing required fields Values");
			test.log(LogStatus.INFO, "Clearing required fields Values");
			driver.findElement(By.xpath("//*[@id='DateOfService']//input")).clear();
			Commons.Explicitwait();
			driver.findElement(By.xpath("//*[@id='StartofCare']//input")).clear();
			Commons.Explicitwait();
			driver.findElement(By.xpath("//*[@id='autosearchPOS']//following::button[1]")).click();
			;
			Commons.Explicitwait();
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			String msg = Commons.capturemessage(driver, 20);
			Assert.assertTrue(msg.contains("Please fill all the mandatory fields"), "Toast messages not matched");
			test.log(LogStatus.INFO, " Warning Message 1 Verified:=>" + "      " + msg);
			CreateNoteUtils.NavigatetoCreateNote(driver);
			HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																										// Start
																										// Of
																										// CARE
																										// DATE
			HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			String msg2 = Commons.capturemessage(driver, 20);
			Assert.assertTrue(msg2.contains("Please select at least one body template"), "Toast messages not matched");
			test.log(LogStatus.INFO, "Warning Message 2 Verified:=>" + "      " + msg2);
			ActionUtils.click(CreateNotePage.AddAll(driver, "bodyparts"));
			Commons.waitForLoad(driver);
			ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
			Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='Messagepopup']//div[contains(text(),'There is no appointment for this patient and date of service')]"),
					30);
			String msg3 = driver
					.findElement(By
							.xpath("//*[@id='Messagepopup']//div[contains(text(),'There is no appointment for this patient and date of service')]"))
					.getText().toString();
			Assert.assertTrue(msg3.contains("There is no appointment for this patient and date of service"));
			test.log(LogStatus.INFO, "Warning Message 3 Verified:=>" + "      " + msg3);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='cancelCreateNoteBtn']")));
			Commons.Explicitwait();
			// *[@id='Messagepopup']//div[contains(text(),'There is no
			// appointment for this patient and date of service')]
		} catch (Exception e) {
			System.out.println("Warning pop up not appeared");
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: CreateNote Visit Type For Subsequent-visit Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void Note_Unlock() throws InterruptedException, AWTException {
		test = extent.startTest(
				"To Validate Note is lock - subsequent note on case - where previous note created in R5",
				"Note is lock" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		test.log(LogStatus.INFO, "Quick Add a Patient with ID==>" + strActualtext);
		CreateNoteUtils.NavigatetoCreateNote(driver);
		System.out.println("Navigated to Create Notes");
		test.log(LogStatus.INFO, "Navigated to Create Notes");
		HandlingCalendars.datepick(driver, CreateNotePage.StartofCareDate(driver), "14/06/2015");// Enter
																									// Start
																									// Of
																									// CARE
																									// DATE
		HandlingCalendars.datepick(driver, CreateNotePage.DateofService(driver), "14/06/2015");
		ActionUtils.click(CreateNotePage.AddAll(driver, "bodyparts"));
		// ActionUtils.click(CreateNotePage.AddAll(driver,
		// "PersonalTemplates"));
		// ActionUtils.click(CreateNotePage.AddAll(driver, "PublicTemplates"));
		ActionUtils.click(CreateNotePage.CreateNoteButton(driver));
		try {
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Create New Note')]"), 20);
			System.out.println("Confirmation pop up appeared");
			driver.findElement(By.xpath("//*[contains(text(),'Create New Note')]")).click();
		} catch (Exception e) {
			System.out.println("Confirmation pop up Not appeared");
		}
		try {
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 280);
		} catch (Exception e) {
		}
		System.out.println("Initial-Visit Note crated Successfully");
		test.log(LogStatus.INFO, "Initial-Visit Note crated Successfully");
		String PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Got Patient ID from App :==>" + PatientID);
		try {
			// if(TestSetUp.CurrentBrowserName=="chrome"){
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(Commons.readPropertyValue("URL"));
			ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("userPT"));
			ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
			Commons.Explicitwait();
			LoginPage.signin(driver).click();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='leftMenuList']"), 90);
			System.out.println("Login successfully in second browser");
			test.log(LogStatus.INFO, "Login successfully in second browser");
			Commons.screenshot();
			System.out.println("Entering search data" + PatientID);
			if (PatientID.isEmpty()) {
				Assert.assertFalse(true, "Did not capture Patient ID from App");
			}
			int i = 0;
			do {
				AddPatientPage.patientQuicksearchbox(driver).click();
				AddPatientPage.patientQuicksearchbox(driver).clear();
				ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), PatientID);
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
						10);
				i++;
			} while (!Commons.existsElement(driver, By.xpath("//li[@class='ng-scope selected']")) && i < 6);
			try {
				System.out.println("quick Search result found");
				Commons.waitforElement(driver, By.xpath("//li[@class='ng-scope selected']"), 20);
				driver.findElement(By.xpath("//li[@class='ng-scope selected']")).click();
				Commons.Explicitwait();
			} catch (Exception e) {
				System.out.println("Quicksearch not done");
				Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
			}
			System.out.println("Quicksearch done");
			test.log(LogStatus.INFO, "Quicksearch done in second browser");
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath("//div[@class='row btnsection']//a[contains(text(),'Medical Record')]"), 100);
			Commons.waitForElementToBeClickable(driver, CreateNotePage.MedicalRecordlink(driver), 20);
			// Thread.sleep(5000);
			CreateNotePage.MedicalRecordlink(driver).click();
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			System.out.println("Scrolling to Medical record + sign in page..");
			Commons.scrollElementinotView(CreateNotePage.MedicalRecordExpandlink(driver), driver);
			CreateNotePage.MedicalRecordExpandlink(driver).click();
			Commons.waitForLoad(driver);
			Commons.scrollElementinotView(
					driver.findElement(
							By.xpath("//div[@k-options='vmPatient.clinicalNoteInfoGridOptions']/table/tbody/tr[2][1]")),
					driver);
			Commons.Explicitwait();
			// ActionUtils.doubleClick(driver,
			// driver.findElement(By.xpath("//div[@k-options='vmPatient.clinicalNoteInfoGridOptions']/table/tbody/tr[2][1]")));
			new Actions(driver)
					.doubleClick(driver.findElement(
							By.xpath("//div[@k-options='vmPatient.clinicalNoteInfoGridOptions']/table/tbody/tr[2][1]")))
					.perform();
			System.out.println("Double Clicked on WebElement");
			test.log(LogStatus.INFO, "Previous note Opened in second browser");
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Someone else already has the data locked')]"),
					40);
			Commons.Explicitwait();
			Commons.strText_Actual = ActionUtils.getText(
					driver.findElement(By.xpath("//*[contains(text(),'Someone else already has the data locked')]")));
			driver.close();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Unable to get lock window pop for Craete note" + Throwables.getStackTraceAsString(e));
		}
		try {
			Assert.assertTrue(Commons.strText_Actual.contains("Someone else already has the data locked."));
			System.out.println("Confirm pop up appeared");
			test.log(LogStatus.INFO, "Assertion Pass!!!" + Commons.strText_Actual);
		} catch (Exception e) {
			System.out.println("Confirm pop up not appeared");
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate Note Unlock Completed as Fail");
			Assert.assertFalse(true,
					"Test:::: Validate Note Unlock Completed as Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}