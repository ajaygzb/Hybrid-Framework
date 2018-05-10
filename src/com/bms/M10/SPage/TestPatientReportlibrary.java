package com.bms.M10.SPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.SPage;
import Utils.Commons;
import Utils.SPageUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestPatientReportlibrary extends TestSetUp {
	public String strActualtext;

	@Test(enabled = true, priority = 2)
	public void Patient_Report_library_load() throws InterruptedException {
		test = extent.startTest("To Validate Patient Report library_load on S page",
				"Test Patient_Report_library_load for S page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = SPageUtils.NavigateToSpage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To S Page");
		try {
			System.out.println("checking visibility of Patient library drop down");
			test.log(LogStatus.INFO, "checking visibility of Patient library drop down");
			Assert.assertTrue(SPage.PatientReportLoadtextDropDown(driver).isDisplayed());
			test.log(LogStatus.INFO, "Patient library drop down is available");
			System.out.println("verifing loading of patient Report from already entred libraries");
			SPageUtils.click(SPage.PatientReportLoadtextDropDown(driver));
			Select lib = new Select(SPage.PatientReportLoadtextDropDown(driver));
			WebElement option = lib.getFirstSelectedOption();
			String file = option.getText();
			System.out.println("Added library name is:" + file);
			String value = SPage.PatientReportTextBox(driver).getText();
			System.out.println("Added library text is:" + value);
			SPageUtils.click(SPage.Save(driver));
			String actualfile = lib.getFirstSelectedOption().getText();
			String actualvalue = SPage.PatientReportTextBox(driver).getText();
			Assert.assertEquals(actualfile, file);
			// Assert.assertEquals(actualvalue, value);
			test.log(LogStatus.INFO, "Loading from Library is successful");
			System.out.println("Validation of  Patient Report library load on S page is successful");
			test.log(LogStatus.INFO, "Validation of Patient Report library load on S page is successful");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 1)
	public void Patient_Report_new_library_item() throws InterruptedException {
		test = extent.startTest("To Validate Patient Report new library item on S page",
				"Test Patient_Report_new_library_item for S Page" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = SPageUtils.NavigateToSpage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To S Page");
		try {
			System.out.println("checking visibility of Patient library drop down");
			test.log(LogStatus.INFO, "checking visibility of Patient library drop down");
			Assert.assertTrue(SPage.PatientReportLoadtextDropDown(driver).isDisplayed());
			test.log(LogStatus.INFO, "Patient library drop down is available");
			System.out.println("adding new patient report library");
			String value = "This is test script to add patient report";
			String file = "fileAutomation";
			file = SPageUtils.AddPatientReport(driver, file, value);
			SPageUtils.click(SPage.PatientReportLoadtextDropDown(driver));
			Select lib = new Select(SPage.PatientReportLoadtextDropDown(driver));
			WebElement option = lib.getFirstSelectedOption();
			String actualfile = option.getText();
			System.out.println("Added library name is:" + actualfile);
			String actualvalue = SPage.PatientReportTextBox(driver).getText();
			System.out.println("Added library text is:" + actualvalue);
			Assert.assertEquals(actualfile, file);
			// Assert.assertEquals(actualvalue, value);
			System.out.println("New Patient Report library is added successfully");
			test.log(LogStatus.INFO, "New Patient Report library is added successfully");
			SPageUtils.navigateToIntervensionLib(driver, "Patient Report Library");
			String s = "//div[@id='libItemGrid']//span[contains(text(),'" + file + "')]";
			Assert.assertTrue(Commons.existsElement(driver, By.xpath(s)));
			System.out.println("Validation of adding new report library  on S page is successful");
			test.log(LogStatus.INFO, "Validation of adding new report library on S page is successful");
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
