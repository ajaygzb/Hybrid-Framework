package com.bms.M2.Patient;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestInactivatePatient extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void testInactivatePatinetwithNoActiveCharges() throws InterruptedException {
		test = extent.startTest("InActive Patient With No Active Charges",
				"Test Inactivate Patinet with No Active Charges" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_firstname_search = "testuserComplete";
		strActual_firstname_search = SearchPatientUtils.SearchRandomPatient(driver, strActual_firstname_search);
		System.out.println("Quick Search result by First Name text in app" + "  " + strActual_firstname_search);
		test.log(LogStatus.INFO, "Quick Search result by First Name text in app" + "  " + strActual_firstname_search);
		try {
			ActionUtils.click((AddPatientPage.EditButton(driver)));// click on
																	// Edit
																	// button
			test.log(LogStatus.INFO, "Clicked on Edit Button");
			Commons.scrollElementinotView(AddPatientPage.PatientActiveCheckbox(driver), driver);
			AddPatientPage.PatientActiveCheckbox(driver).sendKeys(Keys.PAGE_DOWN);
			Assert.assertTrue(AddPatientPage.PatientActiveCheckbox(driver).isSelected());
			Commons.waitforElement(driver, By.name("isPatientActive"), 30);
			ActionUtils.click(AddPatientPage.PatientActiveCheckbox(driver)); // click
																				// on
																				// Patient
																				// Active
																				// Check
																				// box
			test.log(LogStatus.INFO, "clicked on Patient Active Check box");
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			test.log(LogStatus.INFO, "clicked on Save");
			Commons.capturemessage(driver, 100);
			ActionUtils.click((AddPatientPage.EditButton(driver)));// click on
																	// Edit
																	// button
			Assert.assertFalse(AddPatientPage.PatientActiveCheckbox(driver).isSelected());
			// reverting Patient Back to Active
			test.log(LogStatus.INFO, "reverting Patient Back to Active");
			Commons.scrollElementinotView(AddPatientPage.PatientActiveCheckbox(driver), driver);
			AddPatientPage.PatientActiveCheckbox(driver).sendKeys(Keys.PAGE_DOWN);
			Commons.scrollElementinotView(AddPatientPage.PatientActiveCheckbox(driver), driver);
			ActionUtils.click(AddPatientPage.PatientActiveCheckbox(driver)); // click
																				// on
																				// Patient
																				// Active
																				// Check
																				// box
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			test.log(LogStatus.PASS, "Test:::: testInactivatePatinetwithNoActiveCharges() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: testInactivatePatinetwithNoActiveCharges() Completed as Fail");
			// Commons.ScreenPrint(driver,
			// "TestInactivatePatient","testInactivatePatinetwithNoActiveCharges");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
