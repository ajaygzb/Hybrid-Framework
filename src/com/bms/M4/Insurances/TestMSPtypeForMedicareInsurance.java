package com.bms.M4.Insurances;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestMSPtypeForMedicareInsurance extends TestSetUp {
	@Test(priority = 1)
	public void MSPtypeInSecondaryInsurance() {
		String expectedMessage = null;
		try {
			test = extent.startTest("Test MSP type In Secondary Insurance",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.AddSecondaryInsuranceAsMedicare(driver);
			expectedMessage = ActionUtils.getText(CasePage.SecondaryInsuranceMSPtypeLabel(driver));
			Assert.assertTrue(expectedMessage.contains("MSP Type"));
			AddCaseUtils.AddTertiaryInsuranceAsMedicare(driver);
			;
			expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			if (expectedMessage != null) {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} else {
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			test.log(LogStatus.INFO, "Case MSP type For Medicare Insurance Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: TestMSPtypeForMedicareInsurance() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: TestMSPtypeForMedicareInsurance() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(priority = 2)
	public void MSPtypeInTertiaryInsurance() {
		String expectedMessage = null;
		try {
			test = extent.startTest(" Test MSP type In Tertiary Insurance",
					"Test Case Information" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.QuickAddpatient(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.AddSecondaryInsuranceAsMedicare(driver);
			AddCaseUtils.AddTertiaryInsuranceAsMedicare(driver);
			expectedMessage = ActionUtils.getText(CasePage.TertiaryInsuranceMSPtypeLabel(driver));
			Assert.assertTrue(expectedMessage.contains("MSP Type"));
			expectedMessage = AddCaseUtils.ClickCaseSave(driver);
			if (expectedMessage != null) {
				Assert.assertEquals(expectedMessage, "Case saved successfully.");
			} else {
				Assert.assertTrue(CasePage.CaseEditButton(driver).isDisplayed(), "Could not save case..");
			}
			test.log(LogStatus.INFO, "Case MSP type In Tertiary Insurance Saves  successfully.");
			test.log(LogStatus.INFO, "Test:::: MSPtypeInTertiaryInsurance() Completed as Pass");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Assertion Error!!!");
			test.log(LogStatus.INFO, "Test:::: MSPtypeInTertiaryInsurance() Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
