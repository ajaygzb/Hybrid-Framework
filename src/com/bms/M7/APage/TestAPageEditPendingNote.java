package com.bms.M7.APage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.APage;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

public class TestAPageEditPendingNote extends TestSetUp {
	public String strText_Actual;

	@Test(enabled = true, priority = 1)
	public void EditPendingNote() throws InterruptedException {
		try {
			test = extent.startTest("To Verify R5 pending note, edit in R5",
					"Test Edit Pending Notes in R5" + "*****Current Browser******" + CurrentBrowserName
							+ "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			APageUtils.NavigateToApage(driver);
			test.log(LogStatus.INFO, "Navigate to A PAge");
			test.log(LogStatus.INFO, "Entering Data In Assesment Field");
			ActionUtils.sendKeys(APage.AssessmentTextBox(driver),
					"Automation Test Data For Assesment Field" + "\n" + "Automation Test Data For Assesment Field"
							+ "\n" + "Automation Test Data For Assesment Field" + "\n");
			System.out.println("Entered Assesment Data");
			Commons.screenshot();
			APage.SaveButton(driver).click();
			String PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Got Patient ID from App :==>" + PatientID);
			Commons.Explicitwait();
			APageUtils.click(CreateNotePage.HomeButton(driver));
			Commons.waitForLoad(driver);
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
			SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			System.out.println("Got Patient searched with diffrent provider");
			APageUtils.GotoNoteList(driver, 1);
			test.log(LogStatus.INFO, "Successfully redirected to APage");
			System.out.println("Adding DX code M545");
			test.log(LogStatus.INFO, "Adding DX code M545");
			APageUtils.AddSpecificDxCode(driver, "M545");
			Commons.waitForLoad(driver);
			APageUtils.click(APage.SaveButton(driver));
			APageUtils.click(CreateNotePage.HomeButton(driver));
			Commons.waitForLoad(driver);
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("username"),
					Commons.readPropertyValue("password"));
			SearchPatientUtils.QuickpatientSearch(driver, PatientID);
			System.out.println("Got Patient searched with diffrent provider");
			APageUtils.GotoNoteList(driver, 1);
			test.log(LogStatus.INFO, "Successfully redirected to APage");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
		} catch (Exception e) {
			Assert.assertFalse(true, "Test Failed" + Throwables.getStackTraceAsString(e));
		}
		try {
			test.log(LogStatus.INFO, "Asserting Data on A Page");
			Assert.assertTrue(driver.getPageSource().contains("Low back pain"));
			Assert.assertTrue(driver.getPageSource().contains("M545"));
			test.log(LogStatus.INFO, "Asserting DX Data***** Pass");
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//textarea[contains(@class,'ng-not-empty')]")));
			test.log(LogStatus.INFO, "Asserting Assesment field Data ***** PASS");
		} catch (AssertionError e) {
			test.log(LogStatus.INFO, "Test Failed" + Throwables.getStackTraceAsString(e));
			System.out.println("Test Failed" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true);
		}
	}
}
