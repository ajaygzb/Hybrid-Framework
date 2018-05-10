package com.bms.M8.FLR;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;

public class TestFLR10THVisitPromtWithAddendum extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void FLR10thVisitWithAddendum() throws InterruptedException {
		test = extent.startTest("To verify FLR PROMPT ON 10TH VISIT - where addendums created on the case",
				"Test FLR 10th Visit Promt With Addendum " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Added Initial Visit Note");
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation");
		test.log(LogStatus.INFO, "Added FLR Data to Initial Note");
		test.log(LogStatus.INFO, "Finalized Initial Note");
		APageUtils.FinalizeNote(driver); // Initial Visit note finalized
		System.out.println("Initial Visit note finalized");
		test.log(LogStatus.INFO, "Initial Visit note finalized");
		test.log(LogStatus.INFO, "Add Addendum to Initial Note");
		APageUtils.AddAddendum(driver);
		test.log(LogStatus.INFO, "Finalize Addendum");
		APageUtils.FinalizeNote(driver);
		for (int i = 1; i < 9; i++) {
			APageUtils.createfollowUpNewNote(driver);
			APageUtils.click(driver, "clickAPAge");
			test.log(LogStatus.INFO, "Navigated to A PAge");
			APageUtils.FinalizeNote(driver);
			System.out.println(i + "__Follow Up Visit Completed");
			test.log(LogStatus.INFO, i + "__Follow Up Visit Completed");
		}
		System.out.println("FLR 10th Visit");
		test.log(LogStatus.INFO, "FLR 10th Visit");
		APageUtils.createfollowUpNewNote(driver);
		APageUtils.click(driver, "clickAPAge");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		APageUtils.FinalizeNote(driver);
		ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
		String strText_Actual = null;
		strText_Actual = Commons.capturemessage(driver, 60);
		String strExpectedText = ("It has been 10 visits since the Functional Reporting codes have been updated. You must update the Functional Reporting codes."); // 2108954
		try {
			test.log(LogStatus.INFO, "Actual Message" + "___ " + strText_Actual);
			test.log(LogStatus.INFO, "Expected Message" + "___" + strExpectedText);
			Assert.assertEquals(strExpectedText, strText_Actual);
			test.log(LogStatus.INFO, "Test:::: testFLR 10 visit promt Completed as Pass");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: FLR 10th Visit Prompt Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
