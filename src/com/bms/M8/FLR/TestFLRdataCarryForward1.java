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

public class TestFLRdataCarryForward1 extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void FLRDataSubsequentNote() throws InterruptedException {
		test = extent.startTest(
				"To verify Procedure Code Entry carry forward, where data on previous note is NOT BLANK when finalized and subsequent note is created",
				"Test FLR data Carry Forward " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Navigated to A PAge");
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "Test Automation FLRDataSubsequentNote");
		APageUtils.FinalizeNote(driver);
		APageUtils.createfollowUpNewNote(driver);
		test.log(LogStatus.INFO, "Created FollowupNote");
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to FLR DATA Tab");
		try {
			test.log(LogStatus.INFO, "Verifying data in FLR Tab is disabled and modified");
			System.out.println(driver
					.findElements(By.xpath("//table[@id='flrTable']//select[contains(@disabled,disabled)]")).size());
			Assert.assertEquals(driver
					.findElements(By.xpath("//table[@id='flrTable']//select[contains(@disabled,disabled)]")).size(), 4,
					"FLR Tabel data is not Disabled");
			APageUtils.AssertFLRTABDATA(driver, "G8993", "CN", "G8994", "CH");
			Commons.Explicitwait();
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Could not Validate Data In FLR tab");
			Assert.assertFalse(true,
					"Could not Could not Validate Data In FLR tab" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void AddendumDataOnFLRDataSubsequentNoteDisplay() throws InterruptedException {
		test = extent.startTest(
				"To verify  Procedure Code Entry carry forward on ADDENDUMS, "
						+ "where data on previous ADDENDUM note is NOT BLANK when finalized and subsequent note is created",
				"Test FLR data Carry Forward from Addendum " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Navigated to A PAge");
		APageUtils.AddFLRDATA(driver, "G8978", "CM", "CI", "Test Automation AddendumFLRDataSubsequentNote");
		APageUtils.FinalizeNote(driver);
		// Add Addendum
		APageUtils.AddAddendum(driver);
		test.log(LogStatus.INFO, "Added Addendum");
		APageUtils.AddFLRDATA(driver, "G8990", "CL", "CK", "Test Automation AddendumFLRDataSubsequentNote");
		test.log(LogStatus.INFO, "Added Addendum with new FLR DATA");
		APageUtils.FinalizeNote(driver);
		APageUtils.createfollowUpNewNote(driver);
		test.log(LogStatus.INFO, "Created FollowupNote");
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to FLR DATA Tab");
		try {
			test.log(LogStatus.INFO, "Verifying data in FLR Tab is modified/Copied from Addendum");
			APageUtils.AssertFLRTABDATA(driver, "G8990", "CL", "G8991", "CK");
			Commons.Explicitwait();
			test.log(LogStatus.INFO, "*************Assertion Pass*************");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Could not Validate Data In FLR tab");
			Assert.assertFalse(true,
					"Could not Could not Validate Data In FLR tab" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void AddendumDataOnFLRDataSubsequentNoteNotDisplay() throws InterruptedException {
		test = extent.startTest("To verify FLR carry forward where data on current note is BLANK",
				"Test Addendum Data On FLR Data Subsequent Note Not Display " + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Created Note-1 with Blank FLR data");
		APageUtils.createfollowUpNewNote(driver);
		test.log(LogStatus.INFO, "Created Note-2 with Blank FLR data");
		APageUtils.GotoNoteList(driver, 1);
		test.log(LogStatus.INFO, "Validate that Note-2 has Blank FLR data");
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to FLR DATA Tab");
		Assert.assertEquals(driver
				.findElements(By.xpath("//table[@id='flrTable']//select[contains(@class,'ng-empty ng-not-modified')]"))
				.size(), 4);
		test.log(LogStatus.INFO, "Verified FLR Tab is blanked for Note-2");
		APageUtils.GotoNoteList(driver, 2);
		test.log(LogStatus.INFO, "Exit note (2), without finalizing, and edit note (1)");
		test.log(LogStatus.INFO, "Adding FLR data in Note-1");
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to FLR DATA Tab Note-1");
		APageUtils.AddFLRDATA(driver, "G8993", "CN", "CH", "");
		APageUtils.GotoNoteList(driver, 1);
		test.log(LogStatus.INFO, "Exit note (1), without finalizing, and edit note (2)");
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		// 2108832
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to FLR DATA Tab Note-2");
		APageUtils.AssertFLRTABDATA(driver, "G8993", "CN", "G8994", "CH");
		Commons.waitForLoad(driver);
		APageUtils.GotoNoteList(driver, 2);
		test.log(LogStatus.INFO, "Exit note (2), without finalizing, and edit note (1)");
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		ActionUtils.sendKeys(driver.findElement(By.xpath("//textarea[contains(@name,'severityModifierRationale')]")),
				"SeverityModifierRationale");
		APageUtils.FinalizeNote(driver);
		// *********************************************************finilazie
		// Note//Add rationale
		APageUtils.GotoNoteList(driver, 2);
		test.log(LogStatus.INFO, "Adding Addendum to Note-1");
		APageUtils.AddAddendum(driver);
		APageUtils.AddFLRDATA(driver, "G8987", "CK", "CJ", "Test AddendumDataOnFLRDataSubsequentNoteNotDisplay");
		APageUtils.FinalizeNote(driver);
		test.log(LogStatus.INFO, "Finalized Addendum for Note-1");
		test.log(LogStatus.INFO, "Exit note (1) with Addendum and edit note (2)");
		APageUtils.GotoNoteList(driver, 3);
		APageUtils.click(driver, "clickAPAge");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to FLR DATA Tab for Note-2");
		try {
			test.log(LogStatus.INFO, "Verifying data in FLR Tab for Note-2 is not modified/Copied from Addendum");
			APageUtils.AssertFLRTABDATA(driver, "G8993", "CN", "G8994", "CH");
			Commons.Explicitwait();
			test.log(LogStatus.INFO, "*************Assertion Pass*************");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Could not Validate Data In FLR tab");
			Assert.assertFalse(true,
					"Could not Could not Validate Data In FLR tab" + Throwables.getStackTraceAsString(e));
		}
	}
}
