package com.bms.M11.EMR;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CreateNotePage;
import UIMap.SPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.EMRUtils;
import Utils.SPageUtils;

public class TestSignNoteWithCompleteSOAPdataWithAddendum extends TestSetUp {
	public String strActualtext = null;
	public List<WebElement> actual = null;

	// TC-3 R5 finalize note, addendum in R5
	// TC-4 R5 finalize note, subsequent note in R5 - verify carry forward data
	@Test(enabled = true, priority = 1)
	public void InitialVisitWithAddendum() throws InterruptedException {
		test = extent.startTest(
				"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Addendum note also",
				"Sign and Fianlize Note with Complete SOAP data and validate Data in Initial Note will copy to Addendum note also"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		try {
			APageUtils.GotoFLR(driver); // Added a Patient with all fields and
										// Complete case with insurance Medicare
										// FLR
			test.log(LogStatus.INFO, "Added a Patient with all fields and Complete case with insurance Medicare FLR");
			test.log(LogStatus.INFO,
					"To Validate Alert Message when Do NOT enter any minutes and click Charge Capture");
			Commons.waitForLoad(driver);
			EMRUtils.AddDataSpage(driver);
			test.log(LogStatus.INFO, "Added Data on S Page");
			EMRUtils.AddDataApage(driver);
			test.log(LogStatus.INFO, "Added Data on A Page");
			EMRUtils.AddDataPpage(driver);
			test.log(LogStatus.INFO, "Added Data on P Page");
			EMRUtils.AddDataOpage(driver);
			test.log(LogStatus.INFO, "Added Data on O Page");
			// Charge Capture and Finalize Note with Procedure code 97140
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(text(),'Charge Capture')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input"), 60);
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).click();
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).clear();
			driver.findElement(By.xpath("//*[@id='autosearchProcedureCodesChargeCapture']//input")).sendKeys("97140");
			Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(text(),'MANUAL THERAPY')]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(text(),'MANUAL THERAPY')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@name='handOnTreatmentMinutes']"), 30);
			ActionUtils.clear(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")));
			ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@name='handOnTreatmentMinutes']")), "15");
			ActionUtils.click(driver.findElement(By.xpath("//button[contains(.,'Validate Charges')]")));
			System.out.println("Clicked on Validate Charge button");
			ActionUtils.click(CreateNotePage.SignandFinalizeButton(driver));
			Commons.waitForLoad(driver);
			test.log(LogStatus.INFO, "Charge Captured and Finalize Note with Procedure code 97140");
			Commons.screenshot();
			// To Check if Note had linked Library Items auto loaded pop up
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//*[@id='examTemplateMessagePopUp']//input[contains(@value,'OK')]"), 20)) {
				System.out.println(driver
						.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")).getText());
				ActionUtils
						.click(driver.findElement(By.xpath("//*[@id='examTemplateMessagePopUp']//input[@value='OK']")));
			}
			System.out.println("Waiting for Add addendum button...");
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='Diagnosis']/div[contains(text(),'Report List')]"),
					90);
			System.out.println("Finalizing Note...");
			test.log(LogStatus.INFO, "Finalized Note.");
			Commons.screenshot();
			Commons.waitforElement(driver, By.xpath("//input[@value='Add Addendum']"), 60);
			System.out.println("Initial Visit note finalized");
			test.log(LogStatus.INFO, "Initial Visit note finalized");
			test.log(LogStatus.INFO, "Add Addendum to Initial Note");
			APageUtils.AddAddendum(driver);
			try {
				test.log(LogStatus.INFO, "Verifying data in FLR Tab is modified/Copied from Addendum");
				APageUtils.AssertFLRTABDATA(driver, "G8993", "CN", "G8994", "CH");
				Commons.Explicitwait();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='Apage_Goals-Rehab-Potential']")));
				Assert.assertTrue(Commons.ExistandDisplayElement(driver,
						By.xpath("//select//Option[contains(@selected,'selected')][contains(text(),'Very Good')]"), 20),
						"Data for Goals and Rehab not copied to Addenddum");
				test.log(LogStatus.INFO, "*************Assertion Pass*************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Could not Validate Data In Addendum");
				Assert.assertFalse(true,
						"Could not Could not Validate Data In Addendum" + Throwables.getStackTraceAsString(e));
			}
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
