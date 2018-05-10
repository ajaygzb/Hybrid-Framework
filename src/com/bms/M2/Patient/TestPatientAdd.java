package com.bms.M2.Patient;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestPatientAdd extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void testQuickAddPatient() throws InterruptedException {
		test = extent.startTest("To Verify User can add patient By Entering All Mandatory fields Value",
				"Test Quick Add Patient" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
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
			// Commons.ScreenPrint(driver, "TestPatientAdd",
			// "testQuickAddPatient");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void testAddPatientAllfields() throws InterruptedException {
		test = extent.startTest("To Verify Patient Add with all fields", "Test Complete Add Patient"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
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

	@Test(enabled = true, priority = 4)
	public void testaddpatientMandatoryfields() {
		test = extent.startTest("To Validate mandatory Fields ON Add patient Page",
				"Test Validate Mandatory Fields on Add patient Page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		
		List<String>str_act=AddPatientUtils.verifyAddPatientFields(driver);
		
		// Assertion-1
		String strActual_text_Toast = null;
		strActual_text_Toast = str_act.get(0).toString().trim();
		System.out.println("Toast text in app" + "  " + strActual_text_Toast);
		String Text_Expected = "You must complete all required fields.";
		test.log(LogStatus.INFO, "Expected:==>>" + Text_Expected.trim());
		test.log(LogStatus.INFO, "Actual:==>>" + strActual_text_Toast.trim());
		
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='unSaved']//input[@value='No']")));
		
		try {
			Assert.assertEquals(Text_Expected.trim(), strActual_text_Toast.trim(), "Toast messages not matched");
			Assert.assertTrue((str_act.get(1).toString().trim().startsWith("Special characters")));
			
		} catch (AssertionError e) {
			test.log(LogStatus.INFO,
					"=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=============================================================================== ");
			// Commons.ScreenPrint(driver, "TestPatientAdd",
			// "addpatientMandatoryfields");
			System.out.println(
					"######################################################################################################################################## ");
			test.log(LogStatus.INFO, "Test:::: addpatientMandatoryfields() Completed.");
			System.out.println(
					"######################################################################################################################################## ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void testAddDuplicatePatient() throws InterruptedException {
		test = extent.startTest("To Validate Add duplicate patient Pop up", "Test Add Duplicate Patient"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text = null;
		strActual_text = AddPatientUtils.AddDuplicatePatient(driver);
		System.out.println("Toast text in app" + "  " + strActual_text);
		String Text_Expected = "Multiple patient were found with this name and date of birth";
		try {
			Assert.assertTrue(strActual_text.contains(Text_Expected), "Toast messages not matched");
			test.log(LogStatus.INFO, "Toast Message Appeared");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO,
					"=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=============================================================================== ");
			// Commons.ScreenPrint(driver, "TestPatientAdd",
			// "addDuplicatePatient");
			System.out.println(
					"######################################################################################################################################## ");
			test.log(LogStatus.INFO, "Test:::: addDuplicatePatient() Completed.");
			System.out.println(
					"######################################################################################################################################## ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}