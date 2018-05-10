package com.bms.M2.Patient;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.HandlingCalendars;
import Utils.SearchPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestSearchPatient extends TestSetUp {
	// Quick Search by Patient ID
	@Test(enabled = true, priority = 5) // Quick search by Patient ID
	public void testQuickSearchPatient() throws InterruptedException {
		test = extent.startTest("To Search patient with quick search box", "Test Quick search"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualID = null;
		String strExpectedID = AddPatientUtils.QuickAddpatientTemp(driver);
		strActualID = SearchPatientUtils.QuickpatientSearch(driver, strExpectedID);
		System.out.println("Searched Patient ID in application " + "  " + strActualID);
		test.log(LogStatus.INFO, "Searched Patient ID in application " + "  " + strActualID);
		try {
			Assert.assertEquals(strActualID, strExpectedID);
			test.log(LogStatus.INFO, "Actual ID" + "  " + strActualID + "  ", "Expected ID" + "  " + strExpectedID);
			test.log(LogStatus.INFO, "Test:::: testQuickSearchPatient() Completed as Pass");
			test.log(LogStatus.INFO,
					"Test-2 To verify user can navigate to Advance search Page by clicking on Magnifier on quick search box");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(@href,'patient-search')]")));
			Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Patient Search')]"), 50);
			Commons.screenshot();
			test.log(LogStatus.INFO, "**************Assertion test-2 Pass*****************");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: testQuickSearchPatient() Completed as Fail");
			// Commons.ScreenPrint(driver,
			// "TestSearchPatient","testSearchPatient");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		Commons.screenshot();
		extent.endTest(test);
		testSearchByFirstname();
		Commons.screenshot();
		extent.endTest(test);
		testSearchByLastName();
		Commons.screenshot();
		extent.endTest(test);
		testSearchByPatientID();
		Commons.screenshot();
		extent.endTest(test);
		testSearchBySSN();
		Commons.screenshot();
		extent.endTest(test);
		testSearchByPhoneNumber();
		Commons.screenshot();
		extent.endTest(test);
		AddPatientSearchPage();
	}

	// @Test(enabled = true, priority = 6) // Advanced Search by First Name
	public void testSearchByFirstname() {
		test = extent.startTest("To Search patient by First Name", "Test search by First Name"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_firstname_search = null;
		strActual_firstname_search = SearchPatientUtils.patientSearchByFirstname(driver,
				AddPatientUtils.strFirstNametemp);
		System.out.println("Advanced Search result by First Name text in app" + "  " + strActual_firstname_search);
		String Firstname_Text_Expected = AddPatientUtils.strFirstNametemp.toLowerCase();
		try {
			Assert.assertTrue(strActual_firstname_search.contains(Firstname_Text_Expected),
					"Search Results not matched");
			Reporter.log("Test:::: testSearchByFirstname() Completed.");
		} catch (AssertionError e) {
			Reporter.log("Assertion Error!!!");
			// Commons.ScreenPrint(driver,
			// "TestSearchPatient","testSearchByFirstname");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	// @Test(enabled = true, priority = 7)
	public void testSearchByLastName() {
		test = extent.startTest("To Search patient by Last Name", "Test search by Last Name"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_lastname_search = null;
		strActual_lastname_search = SearchPatientUtils.patientSearch_byLastname(driver);
		System.out.println("search result by first name text in app" + "  " + strActual_lastname_search);
		String lastname_Text_Expected = AddPatientUtils.strLastNametemp;
		try {
			Assert.assertTrue(strActual_lastname_search.contains(lastname_Text_Expected), "search results not matched");
		} catch (AssertionError e) {
			System.out.println(
					"=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=============================================================================== ");
			// Commons.ScreenPrint(driver, "TestSearchPatient",
			// "testSearchByLastName");
			System.out.println(
					"######################################################################################################################################## ");
			System.out.println("Test:::: testSearchByLastName() Completed.");
			System.out.println(
					"######################################################################################################################################## ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	// @Test(enabled = true, priority = 8)
	public void testSearchByPatientID() {
		test = extent.startTest("To Search patient by patient ID", "Test search by Patient ID"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_ID_search = null;
		strActual_ID_search = SearchPatientUtils.patientSearch_byPatientID(driver, AddPatientUtils.patientIdfromApp);
		System.out.println("search result by ID in app" + "  " + strActual_ID_search);
		String ID_Text_Expected = AddPatientUtils.patientIdfromApp;
		try {
			Assert.assertTrue(strActual_ID_search.contains(ID_Text_Expected), "search results not matched");
		} catch (AssertionError e) {
			System.out.println(
					"=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=============================================================================== ");
			// Commons.ScreenPrint(driver, "TestSearchPatient",
			// "testSearchByPatientID");
			System.out.println(
					"######################################################################################################################################## ");
			System.out.println("Test:::: testSearchByPatientID() Completed.");
			System.out.println(
					"######################################################################################################################################## ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	// @Test(enabled = true, priority = 9)
	public void testSearchBySSN() {
		test = extent.startTest("To Search patient by SSN", "Test search by SSN" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_SSN_search = null;
		strActual_SSN_search = SearchPatientUtils.patientSearch_bySSN(driver);
		test.log(LogStatus.INFO, "search result by SSN in app" + "  " + strActual_SSN_search);
		String SSN_Text_Expected = "123-45-6789";
		try {
			Assert.assertTrue(strActual_SSN_search.contains(SSN_Text_Expected), "Search Results not matched");
		} catch (AssertionError e) {
			// Commons.ScreenPrint(driver, "TestSearchPatient",
			// "testSearchBySSN");
			test.log(LogStatus.INFO, "Test:::: testSearchBySSN() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	//	test.log(LogStatus.INFO, "Test-3 To validate Menu Toggle button functionality");
	//	ActionUtils.click(driver.findElement(By.xpath("//*[@id='menu-toggle']/i")));
		Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Patient Search')]"), 50);
		Commons.screenshot();
		test.log(LogStatus.INFO, "**************Assertion test-2 Pass*****************");
	}

	public void testSearchByPhoneNumber() {
		test = extent.startTest("To Search patient by Phone Number", "Test search by Phone Number" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		
		try {
		Assert.assertTrue(SearchPatientUtils.patientSearch_byPhone(driver));
		} catch (AssertionError e) {
			
			test.log(LogStatus.INFO, "Test:::: testSearchBy Phone number Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			
			
			
		}
	
	}
	
	public void AddPatientSearchPage() {
		test = extent.startTest("To Add New patient From Patient Search Screen", "To Add New patient From Patient Search Screen" + "*****Current Browser******"
				+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		
		
		  String Addpatienttoaster ="";
		  Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='newPatientBtn']")));
			try{
				List<String> exceldata = Commons.ReadExceldata(0);
				Commons.waitforElement(driver, By.id("patientRegistrationFirstName"), 30);
				ActionUtils.click(driver.findElement(By.id("patientRegistrationFirstName")));
				ActionUtils.sendKeys(AddPatientPage.firstname(driver),exceldata.get(0));
				ActionUtils.sendKeys(AddPatientPage.lastname(driver), (exceldata.get(1) + new Random().nextInt(10000000)));
				ActionUtils.sendKeys(AddPatientPage.phone_home(driver),exceldata.get(2));
				Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@class='form-error ng-scope']"), 90);
				String DOB = exceldata.get(3).toString();
				HandlingCalendars.datepick(driver,
				By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"), DOB);
				ActionUtils.click(AddPatientPage.SaveButton(driver));
				Addpatienttoaster = Commons.capturemessage(driver,20);
				if (Addpatienttoaster==null &&
			    driver.findElement(By.xpath("//div[@id='duplicatePatientPopup']//button[@class='btn btn-primary btnGreen']")).isDisplayed()){
				System.out.println("Duplicate Patient Pop up displayed");
				Commons.screenshot();
				AddPatientPage.dup_save_btn(driver).click();
				Addpatienttoaster = Commons.capturemessage(driver, 30);
				}
				
			}catch(Exception e){

				System.out.println("Unable to get Add patient toaster  "+Throwables.getStackTraceAsString(e));
				Commons.screenshot();
				Assert.assertFalse(ActionUtils.getText(AddPatientPage.patientID(driver)).isEmpty(),"Unable to get Add patient toaster  "+Throwables.getStackTraceAsString(e));
			}
				System.out.println("Patient Add steps completed");
		
	
	}

















}








