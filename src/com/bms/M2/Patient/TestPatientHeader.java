package com.bms.M2.Patient;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import UIMap.SchedulerPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import Utils.SearchPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestPatientHeader extends TestSetUp {
	public String PID = null;
	public List<WebElement> actual = null;

	@Test(enabled = true, priority = 1)
	public void TestValidatePatientHeader1() throws InterruptedException {
		TestAddFavorites();
		Commons.screenshot();
		extent.endTest(test);
		TestUpdateFavorites();
		Commons.screenshot();
		extent.endTest(test);
		TestRemoveFavorites();
		Commons.screenshot();
		extent.endTest(test);
		test = extent.startTest(" Test-11:To Validate Values in Patient Header with Patient Incomplete Case Incomplete",
				"To Validate Values in Patient Header with Patient Incomplete Case Incomplete"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text_Toast = null;
		strActual_text_Toast = AddPatientUtils.QuickAddpatient(driver);
		System.out.println("Toast text in app" + "  " + strActual_text_Toast);
		test.log(LogStatus.INFO, "Added Patient");
		String Text_Expected = "Patient added successfully.";
		try {
			if (strActual_text_Toast == null) {
				Assert.assertFalse(ActionUtils.getText(AddPatientPage.patientID(driver)).isEmpty());
				System.out.println("Patient Added but could not capture toast message....");
				System.out.println(
						"Patient Added with ID" + "  " + ActionUtils.getText(AddPatientPage.patientID(driver)));
			} else {
				Assert.assertTrue(strActual_text_Toast.contains(Text_Expected), "Toast messages not matched");
			}
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testQuickAddPatient() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		try {
			// Status
			Assert.assertTrue(ActionUtils
					.getText(
							driver.findElement(By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]")))
					.contains("Status: Active - Case Incomplete"), "Could not Validate Status");
			// Provider Name
			Assert.assertTrue(ActionUtils
					.getText(driver
							.findElement(By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Provider:')]")))
					.contains("Provider: Not Specified"), "Could not Validate Provider Name");
			// Insurance Class
			Assert.assertTrue(ActionUtils
					.getText(driver.findElement(
							By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Class:')]")))
					.contains("Insurance Class: Not Specified"), "Could not Validate Insurance Class");
			// Insurance Name
			Assert.assertTrue(ActionUtils
					.getText(driver.findElement(
							By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Name:')]")))
					.contains("Insurance Name: Not Specified"), "Could not Validate Insurance Name");
		} catch (AssertionError e) {
			Assert.assertFalse(true, "Error in Validationg Header Values" + Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.endTest(test);
		TestContactDetailsCase();
		Commons.screenshot();
		extent.endTest(test);
		TestPatientReceipt();
		Commons.screenshot();
		extent.endTest(test);
		TestPatientReceiptwithpayment();
		Commons.screenshot();
		extent.endTest(test);
		TestViewPatientAppointment();
		Commons.screenshot();
		// extent.endTest(test);
	}

	@Test(enabled = true, priority = 2)
	public void TestValidatePatientHeader2() throws InterruptedException {
		test = extent.startTest("Test-14:To Validate Values in Patient Header with Patient complete Case Incomplete",
				"To Validate Values in Patient Header with Patient complete Case Incomplete"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text_Toast = null;
		strActual_text_Toast = AddPatientUtils.addpatient_withallfields(driver, "Test Header");
		System.out.println("Toast text in app" + "  " + strActual_text_Toast);
		test.log(LogStatus.INFO, "Added Patient");
		String Text_Expected = "Patient added successfully.";
		try {
			if (strActual_text_Toast == null) {
				Assert.assertFalse(ActionUtils.getText(AddPatientPage.patientID(driver)).isEmpty());
				System.out.println("Patient Added but could not capture toast message....");
				System.out.println(
						"Patient Added with ID" + "  " + ActionUtils.getText(AddPatientPage.patientID(driver)));
			} else {
				Assert.assertTrue(strActual_text_Toast.contains(Text_Expected), "Toast messages not matched");
			}
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testQuickAddPatient() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		// Status
		Assert.assertTrue(ActionUtils
				.getText(driver.findElement(By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]")))
				.contains("Status: Active - Case Incomplete"), "Could not Validate Status");
		// Provider Name
		Assert.assertTrue(ActionUtils
				.getText(driver.findElement(By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Provider:')]")))
				.contains("Provider: Not Specified"), "Could not Validate Provider Name");
		// Insurance Class
		Assert.assertTrue(ActionUtils
				.getText(driver
						.findElement(By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Class:')]")))
				.contains("Insurance Class: Not Specified"), "Could not Validate Insurance Class");
		// Insurance Name
		Assert.assertTrue(ActionUtils
				.getText(driver
						.findElement(By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Name:')]")))
				.contains("Insurance Name: Not Specified"), "Could not Validate Insurance Class");
		// Patient Comment
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='patientComment']")))
				.contains("This is a test String"), "Could not Validate Patient Comment");
	}

	@Test(enabled = true, priority = 3)
	public void TestValidatePatientHeader3() throws InterruptedException {
		test = extent.startTest("Test-12:To Validate Values in Patient Header with Patient complete Case complete",
				"To Validate Values in Patient Header with Patient complete Case complete"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.addpatient_withallfields(driver, "HeaderCaseCompleted");// Add
																				// Patient
																				// all
																				// Details
		// Patient Comment
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='patientComment']")))
				.contains("This is a test String"), "Could not Validate Patient Comment");
		PID = ActionUtils.getText(AddPatientPage.patientID(driver));
		AddCaseUtils.GoToAddNewCase(driver);
		AddCaseUtils.AddCaseWithAllFields(driver);
		test.log(LogStatus.INFO, "Case Added with All required field");
		AddCaseUtils.AddDXCode(driver);
		test.log(LogStatus.INFO, "Added DX Code");
		AddCaseUtils.AddPrimaryInsuranceAsBlueShield(driver);
		AddCaseUtils.ClickCaseSave(driver);
		AddCaseUtils.GoToCaseList(driver);
		Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
		String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
		Assert.assertTrue(expectedMessage.contains("AutoPri Blue Shield"));
		test.log(LogStatus.INFO, "Verified Primary Insurance as AutoPri Blue Shield");
		test.log(LogStatus.INFO, "Case Completed successfully.");
		test.log(LogStatus.INFO, "Test:::: CaseAsCompleted() Completed as Pass");
		// Validate Values In Patient Header
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='collapsebtn']")));// *[@id='collapsebtn']
		try {
			// Status
			Assert.assertTrue(ActionUtils
					.getText(
							driver.findElement(By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]")))
					.contains("Status: Active"), "Could not Validate Status");
			// Provider Name
			Assert.assertFalse(ActionUtils
					.getText(driver
							.findElement(By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Provider:')]")))
					.contains("Provider: Not Specified"), "Could not Validate Provider Name");
			// Insurance Class
			Assert.assertFalse(ActionUtils
					.getText(driver.findElement(
							By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Class:')]")))
					.contains("Insurance Class: Not Specified"), "Could not Validate Insurance Class");
			// Insurance Name
			Assert.assertFalse(ActionUtils
					.getText(driver.findElement(
							By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Name:')]")))
					.contains("Insurance Name: Not Specified"), "Could not Validate Insurance Name");
			/*
			 * if(!Commons.ExistandDisplayElement(driver,
			 * By.xpath("//*[@id='patientComment']"),5)){
			 * ActionUtils.click(driver.findElement(By.xpath(
			 * "//*[@id='collapsebtn']")));//*[@id='collapsebtn']
			 * Commons.waitforElement(driver,
			 * By.xpath("//*[@id='patientComment']"),5); } // Patient Comment
			 * Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath
			 * ("//*[@id='patientComment']")) ).contains("This is a test String"
			 * ),"Could not Validate Patient Comment"); PID =
			 * ActionUtils.getText(AddPatientPage.patientID(driver));
			 */
		} catch (AssertionError e) {
			Assert.assertFalse(true, "Error in Validationg Header Values" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, dependsOnMethods = { "TestValidatePatientHeader3" })
	public void TestValidatePatientHeader4() throws InterruptedException {
		test = extent.startTest("Test-13:To Validate Values in Patient Header with Patient Incomplete Case complete",
				"To Validate Values in Patient Header with Patient Incomplete Case complete"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		SearchPatientUtils.QuickpatientSearch(driver, PID);
		// Remove Required field City
		Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Patient')]"), 60);
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Edit Patient')]")));
		ActionUtils.click(driver.findElement(By.xpath("//input[@name='patientCity']")));
		driver.findElement(By.xpath("//input[@name='patientCity']")).clear();
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//button[@id='savePatientDetailsButton']")));
		Commons.capturemessage(driver, 90);
		// Validate Values In Patient Header
		try {
			// Status
			Assert.assertTrue(ActionUtils
					.getText(
							driver.findElement(By.xpath("//div[contains(.,'Status:')][contains(@class,'ng-binding')]")))
					.contains("Status: Active - Patient Incomplete"), "Could not Validate Status");
			// Provider Name
			Assert.assertFalse(ActionUtils
					.getText(driver
							.findElement(By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Provider:')]")))
					.contains("Provider: Not Specified"), "Could not Validate Provider Name");
			// Insurance Class
			Assert.assertFalse(ActionUtils
					.getText(driver.findElement(
							By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Class:')]")))
					.contains("Insurance Class: Not Specified"), "Could not Validate Insurance Class");
			// Insurance Name
			Assert.assertFalse(ActionUtils
					.getText(driver.findElement(
							By.xpath("//div[contains(@class,'ng-binding')][contains(.,'Insurance Name:')]")))
					.contains("Insurance Name: Not Specified"), "Could not Validate Insurance Name");
		} catch (AssertionError e) {
			Assert.assertFalse(true, "Error in Validationg Header Values" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true)
	public void TestAddDuplicateInterventionLibrary() throws InterruptedException {
		test = extent.startTest("To Validate user cannot add duplicate intervention library",
				"To Validate user cannot add duplicate intervention library" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// Navigate to Intervention library page
		ActionUtils.click(CreateNotePage.EMROnMenu(driver));
		ActionUtils.click(
				driver.findElement(By.xpath("//*[@id='sidemenu']//a/span[contains(.,' My Interventions Libraries')]")));
		Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Interventions Libraries')]"), 70);
		String libname = ActionUtils
				.getText(driver.findElement(By.xpath("//*[@id='interventionLibraryGrid']//tbody//tr[2]//td[6]")));
		System.out.println(libname);
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'New')]")));
		Commons.waitforElement(driver, By.xpath("//*[@id='interventionNewLibraryNameTxt']"), 60);
		driver.findElement(By.xpath(".//*[@id='interventionNewLibraryNameTxt']")).sendKeys(libname);
		Commons.SelectElement(driver, By.xpath("//*[@id='interventionType']"), "Modalities");
		ActionUtils.click(driver.findElement(By.xpath(
				"//*[@id='interventionTypeGrid']/table/tbody/tr[1]/td[1]//input[@id='interventionsBlockChck']")));
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@ng-click,'addAvailableInterventions')]")));
		ActionUtils.click(driver.findElement(By.xpath("//button[contains(@class,'saveInterventionLibraryItem')]")));
		System.out.println(Commons.capturemessage(driver, 30).contains("There is already an item with same name"));
	}

	// ***********************************Additional functions clubbed with
	// tests***********************************
	public void TestPatientReceipt() {
		// Patient Receipt with Payment
		test = extent.startTest("Test-15:To Validate Print Ptient Recipt (Header) functionality WithOut Payment Added",
				"To Validate Print Ptient Recipt (Header) functionality WithOut Payment Added"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerReceiptIcon']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='Receipts']//h4[contains(.,'Patient Receipt')]"), 60);
			Commons.screenshot();
			Assert.assertTrue(ActionUtils
					.getText(driver.findElement(
							By.xpath("//*[@id='Receipts']/div[contains(.,'Patient has no credits on file.')]")))
					.contains("Patient has no credits on file."), "Unable to Validate Text on Patient Receipt");
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='closeReceiptsPopUp']")));
			Commons.screenshot();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Test Print Ptient Recipt Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			test.log(LogStatus.FAIL, "Test Print Ptient Recipt Failed" + Throwables.getStackTraceAsString(a));
		}
		// extent.endTest(test);
	}

	public void TestPatientReceiptwithpayment() throws InterruptedException {
		// Patient Receipt without Payment
		test = extent.startTest("Test-16:To Validate Print Ptient Recipt (Header) functionality With Payment Added",
				"To Validate Print Ptient Recipt (Header) functionality With Payment Added"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
			HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"), "01/01/2017");
			ActionUtils.click(driver.findElement(By.xpath("//option[@label='PATIENT PD IN OFFICE']")));
			ActionUtils.click(driver.findElement(By.xpath("//option[@label='Cash']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "15");
			test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
			ActionUtils.click(CasePage.LocationMagnifier(driver));
			ActionUtils.click(CasePage.LocationSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
			Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
			Commons.waitforElement(driver, By.xpath("//div[@id='patientCredits']//table/tbody/tr[1]"), 50);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerReceiptIcon']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='Receipts']//h4[contains(.,'Patient Receipt')]"), 60);
			Commons.screenshot();
			Assert.assertTrue(ActionUtils
					.getText(driver
							.findElement(By.xpath("//*[@id='Receipts']//option[@label='01/01/2017 - Cash - $15.00']")))
					.contains("01/01/2017 - Cash - $15.00"), "Unable to Validate Amount in Patient Receipt");
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='closeReceiptsPopUp']")));
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"Test Print Ptient Recipt with Payment Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			test.log(LogStatus.FAIL,
					"Test Print Ptient Recipt with Payment Failed" + Throwables.getStackTraceAsString(a));
		}
		// Commons.screenshot();
		// extent.endTest(test);
	}

	public void TestViewPatientAppointment() {
		// Patient Receipt with Payment
		test = extent.startTest("Test-17:To Validate View all patient appointments functionality on Header",
				"To Validate View all patient appointments functionality on Header" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerAppointmentIcon']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='headerAppointment']//h4[contains(.,'Appointments for')]"),
					60);
			Commons.screenshot();
			Assert.assertTrue(
					ActionUtils
							.getText(driver.findElement(By
									.xpath(".//*[@id='HeaderAppopintmentId']//span[contains(.,'No Appointment Found')]")))
							.contains("No Appointment Found"),
					"Could not validate text on Header appointment pop up");
			System.out.println("*************Assertion-1 Pass**********");
			Assert.assertTrue(
					ActionUtils
							.getText(driver.findElement(By
									.xpath("//*[@id='headerAppointment']//div[contains(.,'Total number of search results: 0 ')][contains(@class,'text-center')]")))
							.contains("Total number of search results: 0"),
					"Could not validate text on Header appointment pop up");
			System.out.println("*************Assertion-2 Pass**********");
			// close pop up
			ActionUtils
					.click(driver.findElement(By.xpath(".//*[@id='headerAppointment']//button[contains(.,'Close')]")));
			// schedule appointment
			ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
			ActionUtils.click(SchedulerPage.ViewScheduleButton(driver));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//span[contains(text(),'Loading')]"), 60);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='mySpinner']"), 200);
			System.out.println("Opened View Schedule page");
			AddScheduleUtils.SelectViewBy(driver, "Provider View", "Automation BMS Provider");
			AddScheduleUtils.GotoAppointmentWindow(driver);
			// QuickAddPatient(driver);
			AddScheduleUtils.setAppointmenttime(driver, String.valueOf(new Random().nextInt(10)));
			ActionUtils.JavaScriptclick(SchedulerPage.Typefield(driver));
			ActionUtils.JavaScriptclick(SchedulerPage.Statusfield(driver));
			ActionUtils.sendKeys(SchedulerPage.AppointmentNote(driver), "This Is a Test String For Scheduler Notes");
			AddScheduleUtils.ClickSaveAppointmentwindow(driver, false);
			Commons.strText_Actual = ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientFirstName']")))
					.replaceAll("\\s", "");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerAppointmentIcon']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='headerAppointment']//h4[contains(.,'Appointments for')]"),
					60);
			Commons.screenshot();
			Assert.assertTrue(ActionUtils
					.getText(driver.findElement(By.xpath("//*[@id='HeaderAppopintmentId']//table//tbody//td[5]")))
					.contains("Automation BMS"), "Could not validate text in appointment window on header");
			System.out.println("*************Assertion-3 Pass**********");
			Commons.screenshot();
			Assert.assertTrue(
					ActionUtils.getText(driver.findElement(By
									.xpath("//*[@id='headerAppointment']//div[contains(.,'Total number of search results:')][contains(@class,'text-center')]")))
							.contains("Total number of search results:"),
					"Could not validate text on Header appointment pop up");
			System.out.println("*************Assertion-4 Pass**********");
			Commons.screenshot();
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='headerAppointment']//button[contains(.,'Close')]")));
			Commons.screenshot();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Test View all patient appointments Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			test.log(LogStatus.FAIL, "Test View all patient appointments Failed" + Throwables.getStackTraceAsString(a));
		}
		// extent.endTest(test);
	}

	public void TestAddFavorites() {
		// Add to Favorites
		test = extent.startTest("Test-18:To Verify Add to Favorite functionality",
				"To Verify Add to Favorite functionality" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//span[contains(.,'Home')]")));
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(@class,'fvt')]")));
			if (!Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Add Favorite  ')]"),
					5)) {
				Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//i[contains(@ng-click,'DeleteFavorite')]"),
						5);
				ActionUtils.click(
						driver.findElement(By.xpath("//*[@id='navbar']//i[contains(@ng-click,'DeleteFavorite')]")));
				Commons.waitforElement(driver,
						By.xpath("//*[@id='favoriteDeleteConfirmation']//button[contains(.,'Delete')]"), 5);
				ActionUtils.click(driver
						.findElement(By.xpath("//*[@id='favoriteDeleteConfirmation']//button[contains(.,'Delete')]")));
				Commons.capturemessage(driver, 30);
				Commons.waitForLoad(driver);
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(@class,'fvt')]")));
			}
			Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Add Favorite  ')]"), 60);
			Commons.screenshot();
			Assert.assertTrue(ActionUtils
					.getText(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(.,'Add Favorite  ')]")))
					.contains("Add Favorite"), "Unable to Validate Text Add favorite");
			System.out.println("*************Ässertion-1 Pass***********");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(.,'Add Favorite  ')]")));
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//*[@id='Favpopup']//h4[contains(.,'Add Favorite')]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='Favpopup']//button[contains(.,'Add')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Favorite added successfully."),
					"Could not validate Toaster for Add favorite");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Test Add favorite Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			test.log(LogStatus.FAIL, "Test Add favorite Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	public void TestUpdateFavorites() {
		// Update Favorites
		test = extent.startTest("Test-19:To Verify Update Favorite functionality",
				"To Verify Update Favorite functionality" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//span[contains(.,'Home')]")));
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(@class,'fvt')]")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='navbar']//a[contains(.,'Update Favorite')]"),
					5)) {
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(.,'Update Favorite')]")));
				Commons.screenshot();
				Commons.waitforElement(driver, By.xpath("//button[contains(.,'Update')]"), 5);
				Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Manage Favorite')]"), 10);
				driver.findElement(By.xpath("//*[@id='favouriteName']")).clear();
				ActionUtils.sendKeys(driver.findElement(By.xpath("//*[@id='favouriteName']")), "Home123");
				Commons.screenshot();
				ActionUtils.click(
						driver.findElement(By.xpath("//button[contains(.,'Update')]")));
				/*
				 * Commons.waitforElement(driver, By.xpath(
				 * "//*[@id='duplicateFavoritePopup']//h4[contains(.,'Alert')]")
				 * ,5); Commons.screenshot();
				 * Assert.assertTrue(ActionUtils.getText
				 * (driver.findElement(By.xpath(
				 * "//*[@id='duplicateFavoritePopup']//div[3][contains(.,'This will override the existing favorite')]"
				 * ))) .contains("This will override the existing favorite"),
				 * "Unable to validate Message on Update Favorite pop up window"
				 * ); ActionUtils.click(driver.findElement(By.xpath(
				 * "//*[@id='duplicateFavoritePopup']//button[contains(.,'Yes')]"
				 * )));
				 */
				Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Favorite updated successfully."),
						"Unable to capture update favorite toast message");
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Test Update favorite Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			test.log(LogStatus.FAIL, "Test Update favorite Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	public void TestRemoveFavorites() {
		// Remove Favorites
		test = extent.startTest("Test-20:To Verify Remove Favorite functionality",
				"To Verify Remove Favorite functionality" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='leftMenuList']//span[contains(.,'Home')]")));
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(@class,'fvt')]")));
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='navbar']//i[contains(@ng-click,'DeleteFavorite')]"), 5)) {
				Commons.waitforElement(driver, By.xpath("//*[@id='navbar']//i[contains(@ng-click,'DeleteFavorite')]"),
						5);
				ActionUtils.click(
						driver.findElement(By.xpath("//*[@id='navbar']//i[contains(@ng-click,'DeleteFavorite')]")));
				Commons.waitforElement(driver,
						By.xpath("//*[@id='favoriteDeleteConfirmation']//button[contains(.,'Delete')]"), 5);
				Assert.assertTrue(
						ActionUtils
								.getText(driver.findElement(By
										.xpath("//*[@id='favoriteDeleteConfirmation']//div[3][contains(.,'This will permanently remove the selected favorite.')]")))
								.contains("This will permanently remove the selected favorite."),
						"Unable to validate Message on Delete Favorite pop up window");
				System.out.println("*************Ässertion-1 Pass***********");
				ActionUtils.click(driver
						.findElement(By.xpath("//*[@id='favoriteDeleteConfirmation']//button[contains(.,'Delete')]")));
				Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Favorite updated successfully."),
						"Could not validate Toaster on delete Favorite");
				System.out.println("*************Ässertion-2 Pass***********");
				Commons.waitForLoad(driver);
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='navbar']//a[contains(@class,'fvt')]")));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Test Delete favorite Failed" + Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			test.log(LogStatus.FAIL, "Test Delete favorite Failed" + Throwables.getStackTraceAsString(a));
		}
	}

	public void TestContactDetailsCase() {
		// Verify Contact details in Account Notes
		test = extent.startTest("Test-23:To Verify Patient Contact Details in Case will appear in Account Notes",
				"To Verify Patient Contact Details in Case will appear in Account Notes" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			AddCaseUtils.GoToCaseList(driver);
			// Open Existing Case
			Commons.waitforElement(driver, By.xpath("//*[@id='gridCaseDetails']//tbody//tr[1]//td[4]//span"), 30);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//*[@id='gridCaseDetails']//tbody//tr[1]//td[4]//span")));
			Commons.waitForLoad(driver);
			ActionUtils.click(CasePage.CaseEditButton(driver));
			Commons.waitforElement(driver, By.xpath("//select[contains(@ng-change,'PatientContact')]"), 30);
			actual = driver.findElements(By.xpath("//select[contains(@ng-change,'PatientContact')]//option[@label]"));
			actual = actual.subList(0, 6);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = { "--Select--", "LEFTMSG", "1STCALL", "2NDCALL", "3RDCALL", "SCHAPPT" };
			APageUtils.DropDownValuesAssertion(driver, actual, expected1);
			Commons.SelectElement(driver, By.xpath("//select[contains(@ng-change,'PatientContact')]"), "LEFTMSG");
			ActionUtils.sendKeys(
					driver.findElement(By.xpath("//textarea[contains(@ng-model,'patientContactComment')]")),
					"BMSAutomationtest");
			Commons.screenshot();
			AddCaseUtils.ClickCaseSave(driver);
			ActionUtils.click(driver.findElement(By.xpath("//span[@id='patientFirstName']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@href='#AccountNotes']"), 70);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#AccountNotes']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='gridAccountNote']//table//tbody//tr[1]"), 80);
			Assert.assertTrue(
					ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNote']//table//tbody//tr[1]")))
							.contains("BMSAutomationtest"),
					"Assertionerror1 Case Contact");
			Assert.assertTrue(
					ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNote']//table//tbody//tr[1]")))
							.contains("LEFTMSG"),
					"Assertionerror2 Case Contact");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Test Validate Patient Contact in Case appears in Account notes Failed"
					+ Throwables.getStackTraceAsString(e));
		} catch (AssertionError a) {
			test.log(LogStatus.FAIL, "Test Validate Patient Contact in Case appears in Account notes Failed"
					+ Throwables.getStackTraceAsString(a));
		}
	}
}