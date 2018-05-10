package com.bms.M8.FLR;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;

public class TestHardStopMessagevalidation3 extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void TestHardStopWith92521() throws InterruptedException {
		test = extent.startTest(
				"To verify Hard Stop message while create and finalize initial visit WITHOUT FLR and WITH 92521",
				"Test Hard Stop message With 92521" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Navigated to A PAge");
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		APageUtils.addProcedureCode(driver, "92521");
		APageUtils.click(driver, "clickAPAge");
		APageUtils.FinalizeNote(driver);
		System.out.println("Triggred Hard Stop message");
		System.out.println(driver.findElement(By.xpath("//*[@id='errorGridId']//tbody/tr[1]")).getText());
		String Actual1 = ActionUtils.getText(driver.findElement(By.xpath("//*[@id='errorGridId']//tbody/tr[1]")));
		System.out.println(driver.findElement(By.xpath("//*[@id='errorGridId']//tbody/tr[2]")).getText());
		String Actual2 = ActionUtils.getText(driver.findElement(By.xpath("//*[@id='errorGridId']//tbody/tr[2]")));
		try {
			test.log(LogStatus.INFO, "Verifying Hard stop messages");
			Assert.assertEquals(Actual1, "Functional Reporting Codes - You must enter functional reporting codes.");
			test.log(LogStatus.INFO, Actual1);
			Assert.assertEquals(Actual2, "Severity Modifier Rationale - You must enter a severity modifier rationale.");
			test.log(LogStatus.INFO, Actual2);
			Commons.screenshot();
			driver.findElement(By.xpath("//button[contains(@ng-click,'closeErrorPopup')]")).click();
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Could not Validate Hard Stop Messages on Sign and Finalize page");
			Assert.assertFalse(true, "Could not Validate Hard Stop Messages on Sign and Finalize page"
					+ Throwables.getStackTraceAsString(e));
		}
	}
}