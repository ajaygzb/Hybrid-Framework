package com.bms.M7.APage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;

public class TestAPageDischargeFLR extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void DischargeFLR() throws InterruptedException {
		test = extent.startTest("To verify create note for case with discharged FLR codes",
				"Test Discharge FLR (future test case when full note functionality)" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Navigated to A PAge");
		APageUtils.AddFLRDATA(driver, "G8993", "CH", "CH", "Test Automation");
		APageUtils.FinalizeNote(driver);
		APageUtils.createfollowUpNewNote(driver);
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to Goal Tab");
		ActionUtils.click(driver.findElement(By.xpath("//option[@label='Discharge Goal']")));
		APageUtils.FinalizeNote(driver);
		APageUtils.createfollowUpNewNote(driver);
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to Goal Tab");
		ActionUtils
				.click(driver.findElement(By.xpath("//*[@id='flrCurrentStatus']//option[contains(@label,'G8993')]")));
		Commons.strText_Actual = null;
		Commons.strText_Actual = Commons.capturemessage(driver, 30);
		try {
			Assert.assertEquals(Commons.strText_Actual, "This FLR Code has already been used.");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Could not Capture Message for G code");
			Assert.assertFalse(true, "Could not Capture Message for G code" + Throwables.getStackTraceAsString(e));
		}
	}
}
