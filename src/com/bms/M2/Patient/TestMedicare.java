package com.bms.M2.Patient;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestMedicare extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void testQuickAddPatientwithBlankMedicareErrorMessage() throws InterruptedException {
		test = extent.startTest("To Verify Error Message on Medicare",
				"Test Error message validation for Medicare Fields" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActual_text_Toast = null;
		strActual_text_Toast = AddPatientUtils.QuickAddpatientWithBlankMedicare(driver);
		System.out.println("Toast text in app" + "  " + strActual_text_Toast);
		String Text_Expected = "You must complete all required fields.";
		try {
			Assert.assertTrue(strActual_text_Toast.contains(Text_Expected), "Toast messages not matched");
			test.log(LogStatus.INFO, "Test:::: testQuickAddPatientwithBlankMedicareErrorMessage() Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error !!!");
			test.log(LogStatus.INFO, "Test:::: testQuickAddPatientwithBlankMedicareErrorMessage() Completed as Fail");
			// Commons.ScreenPrint(driver, "TestMedicare",
			// "testQuickAddPatientwithBlankMedicareErrorMessage");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		extent.endTest(test);
		testQuickAddPatientwithBlankMedicareHighlightFields();
	}

	// @Test(enabled = true,priority =1)
	public void testQuickAddPatientwithBlankMedicareHighlightFields() throws InterruptedException {
		test = extent.startTest("To Verify Error Hghlights Fields on Medicare",
				"Test Error fields validation for Medicare Fields" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// AddPatientUtils.QuickAddpatientWithBlankMedicare(driver);
		try {
			Assert.assertEquals(AddPatientUtils.getfieldswithErrorCount(driver), 2); // 2
																						// fields
																						// should
																						// be
																						// highlighted
																						// with
																						// red
																						// border
			test.log(LogStatus.INFO, "=================!!!!!!!!!ASSERTION Pass !!!!!!!!!!!!===================");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO,
					"=================!!!!!!!!!ASSERTION ERROR !!!!!!!!!!!!=============================================================================== ");
			// Commons.ScreenPrint(driver, "TestMedicare",
			// "testQuickAddPatientwithBlankMedicareHighlightFields");
			test.log(LogStatus.INFO, "Test:::: testQuickAddPatientwithBlankMedicareHighlightFields() Completed.");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}