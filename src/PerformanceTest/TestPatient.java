package PerformanceTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

public class TestPatient extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void testQuickAddPatient() throws InterruptedException {
		test = extent.startTest("To Verify User can add patient By Entering All Mandatory fields Value",
				"Test Quick Add Patient" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Patient");
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
			// Commons.ScreenPrint(driver, "TestPatientAdd",
			// "testQuickAddPatient");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	// Quick Search by Patient ID
	@Test(enabled = true, priority = 3) // Quick search by Patient ID
	public void testQuickSearchPatient() throws InterruptedException {
		test = extent.startTest("To Search patient with quick search box", "Test Quick search"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Patient");
		test.log(LogStatus.INFO, "Browser started");
		String strActualID = null;
		String strExpectedID = AddPatientUtils.QuickAddpatientTemp(driver);
		strActualID = SearchPatientUtils.QuickpatientSearch(driver, strExpectedID);
		System.out.println("Searched Patient ID in application " + "  " + strActualID);
		test.log(LogStatus.INFO, "Searched Patient ID in application " + "  " + strActualID);
		try {
			// Assert.assertTrue(strActualID.contains(strExpectedID),"IDs not
			// matched");
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
	}

	// @Test(enabled = true, priority = 6) // Advanced Search by First Name
	public void testSearchByFirstname() {
		test = extent.startTest("To Search patient by First Name", "Test search by First Name"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Patient");
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
		test.assignCategory("Patient");
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
		test.assignCategory("Patient");
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
		test.assignCategory("Patient");
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
		test.log(LogStatus.INFO, "Test-3 To validate Menu Toggle button functionality");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='menu-toggle']/i")));
		Commons.waitforElement(driver, By.xpath("//h1[contains(.,'Patient Search')]"), 50);
		Commons.screenshot();
		test.log(LogStatus.INFO, "**************Assertion test-2 Pass*****************");
	}

	@Test(enabled = true, priority = 2)
	public void testAddPatientAllfields() throws InterruptedException {
		test = extent.startTest("To Verify Patient Add with all fields", "Test Complete Add Patient"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Patient");
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text_Toast = null;
		strActual_text_Toast = AddPatientUtils.addpatient_withallfields(driver, "");
		System.out.println("Toast text in app" + "  " + strActual_text_Toast);
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
			test.log(LogStatus.INFO, "Assertion Error");
			System.out.println(
					"=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=============================================================================== ");
			// Commons.ScreenPrint(driver, "TestPatientAdd",
			// "testAddPatientAllfields");
			System.out.println(
					"######################################################################################################################################## ");
			test.log(LogStatus.INFO, "Test:::: testAddPatientAllfields() Completed.");
			System.out.println(
					"######################################################################################################################################## ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
