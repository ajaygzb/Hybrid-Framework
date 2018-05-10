package com.bms.M3.Cases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

public class TestFoto extends TestSetUp {
	public static String strText_Actual = Commons.readPropertyValue("userfoto");;

	@Test(enabled = true, priority = 1)
	public void TestFOTO() throws InterruptedException {
		try {
			test = extent.startTest(
					"Test FOTO functionality for: Claimed and unclaimed episodes,Retrieve episode,Retrieve FOTO report,Episode table ",
					"Test FOTO functionality for: Claimed and unclaimed episodes,Retrieve episode,Retrieve FOTO report,Episode table "
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			if (strText_Actual == null) {
				Assert.assertFalse(true, "Test got failed unable to Find Patient for FOTO test");
			}
			SearchPatientUtils.QuickpatientSearch(driver, strText_Actual);
			test.log(LogStatus.INFO, "Quick search patient contain episode attached to its case" + strText_Actual);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='gridCaseDetails']//tbody//tr[1]//td[4]"), 80);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//*[@id='gridCaseDetails']//tbody//tr[1]//td[4]")));
			Commons.waitForLoad(driver);
			ActionUtils.click(CasePage.CaseEditButton(driver));
			ActionUtils.click(CasePage.FOTOLink(driver));
			test.log(LogStatus.INFO, "Goto FOTO");
			ActionUtils.click(CasePage.RetrieveEpisode(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("(//*[@id='collapseFour']//tbody)[1]//tr[1]"), 15);
			System.out.println("Verifying header text on FOTO Episode");
			test.log(LogStatus.INFO, "Verifying header text on FOTO Episode");
			String[] expected = { "Link to Case", "FOTO Episode ID", "Body Part", "Impairment", "Date Created",
					"Therapist" };
			List<WebElement> actual = driver.findElements(By.xpath("(//*[@id='collapseFour']//thead)[1]//th"));
			for (int i = 0; i < actual.size(); i++) {
				Assert.assertTrue(actual.get(i).getText().contains(expected[i]));
			}
			System.out.println("************Assertion Pass*********");
			test.log(LogStatus.INFO, "************Assertion-1 Pass*********");
			Commons.screenshot();
			ActionUtils.click(CasePage.RetrieveFOTOReport(driver));
			test.log(LogStatus.INFO, "verifying Functionality of reterieve FOTO report button");
			Assert.assertTrue(Commons.capturemessage(driver, 60).matches(
					"Received(.*)reports from FOTO, which have been attached as scanned documents to the patient record."));
			test.log(LogStatus.INFO, "************Assertion-2 Pass*********");
			// To check Episode is claimed or Unclaimed
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='collapseFour']//input[contains(@class,'ng-not-empty')]"), 5)) {
				Commons.screenshot();
				System.out.println("Episode attached to case is claimed");
				System.out.println("Unckech Link to case and saving..");
				ActionUtils.click(driver
						.findElement(By.xpath("//*[@id='collapseFour']//input[contains(@class,'ng-not-empty')]")));
				ActionUtils.click(CasePage.CaseSaveButton(driver));
				Commons.capturemessage(driver, 200);
				Commons.waitForLoad(driver);
				ActionUtils.click(CasePage.CaseEditButton(driver));
				ActionUtils.click(CasePage.FOTOLink(driver));
				ActionUtils.click(CasePage.RetrieveEpisode(driver));
				Commons.waitforElement(driver, By.xpath("//*[@id='collapseFour']//input[contains(@class,'ng-empty')]"),
						60);
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("(//*[@id='collapseFour']//tbody)[1]//tr[1]"), 15);
			}
			Commons.screenshot();
			System.out.println("To verify Unclaimed episodes can reterieve to new case");
			test.log(LogStatus.INFO, "To verify Unclaimed episodes can reterieve to new case");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='patientFirstName']")));
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.click(CasePage.FOTOLink(driver));
			ActionUtils.click(CasePage.RetrieveEpisode(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='collapseFour']//input[contains(@class,'ng-empty')]"), 60);
			System.out.println("***************Assertion-3 Pass**************");
			test.log(LogStatus.INFO, "************Assertion-3 Pass*********");
			// Case-2
			System.out.println("To verify claimed Episodes cannot be reterive in New case");
			test.log(LogStatus.INFO, "To verify claimed Episodes cannot be reterive in New case");
			// Claim the episode and then retrieve to new case
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='patientFirstName']")));
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToCaseList(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='gridCaseDetails']//tbody//tr[1]//td[4]"), 80);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//*[@id='gridCaseDetails']//tbody//tr[1]//td[4]")));
			Commons.waitForLoad(driver);
			ActionUtils.click(CasePage.CaseEditButton(driver));
			ActionUtils.click(CasePage.FOTOLink(driver));
			ActionUtils.click(CasePage.RetrieveEpisode(driver));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='collapseFour']//input[contains(@class,'ng-empty')]"), 60);
			System.out.println("Click on check box to claim episode");
			// claim episode in case-1
			ActionUtils
					.click(driver.findElement(By.xpath("//*[@id='collapseFour']//input[contains(@class,'ng-empty')]")));
			// Retrieve episode in new case
			ActionUtils.click(CasePage.CaseSaveButton(driver));
			Commons.capturemessage(driver, 200);
			Commons.waitForLoad(driver);
			System.out.println("To verify claimed episodes cannot reterieve to new case");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='patientFirstName']")));
			Commons.waitForLoad(driver);
			AddCaseUtils.GoToAddNewCase(driver);
			ActionUtils.click(CasePage.FOTOLink(driver));
			ActionUtils.click(CasePage.RetrieveEpisode(driver));
			Commons.waitForLoad(driver);
			Assert.assertFalse(Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='collapseFour']//input[contains(@class,'ng-not-empty')]"), 15));
			Commons.screenshot();
			System.out.println("***************Assertion-4 Pass**************");
			test.log(LogStatus.INFO, "************Assertion-4 Pass*********");
		} catch (Exception e) {
			Assert.assertFalse(true, "Test FOTO Functionality Failed" + Throwables.getStackTraceAsString(e));
		}
	}
}
