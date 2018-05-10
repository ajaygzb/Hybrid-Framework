package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.AddPatientPage;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestViewChargesPartnerUser extends TestSetUp {
	// CHG.ADD.210,211,004,004a,005,006
	@Test(enabled = true, priority = 1)
	public void ValidateViewChargeUserPartner() throws InterruptedException {
		try {
			test = extent.startTest("To Verify VIEW CHARGE with and without billing/Payments as Partner user",
					"To Verify VIEW CHARGE with and without billing/Payments as Partner user"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Login as user Partner");
			Commons.logout(driver);
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPartner"),
					Commons.readPropertyValue("password"));
			test.log(LogStatus.INFO, "Add patient with complete Case");
			AddChargeUtils.AddPatientCaseCompleted(driver, "DummyAddcharge");
			test.log(LogStatus.INFO, ActionUtils.getText(AddPatientPage.patientID(driver)));
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			// VIEW CHARGE without payments as Partner user
			// Adding multiple Charges to current Patient on different DOS
			test.log(LogStatus.INFO, "Adding multiple Charges to current Patient on different DOS");
			// charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			AddChargeUtils.AddCPTCode(driver, "97110", 2);
			AddChargeUtils.AddCPTCode(driver, "97010", 3);
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
			Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
			// Navigate to View Charge Screen
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			try {
				test.log(LogStatus.INFO,
						"Verifying Edit Charge,Edit all CHarges For Visit,Delete button appears on View charge");
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Edit Charge')]"), 30));
				Assert.assertTrue(Commons.waitforElement(driver,
						By.xpath("//button[contains(text(),'Edit all Charges for Visit')]"), 30));
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//button//span[contains(text(),'Delete')]"), 30));
				// test.log(LogStatus.INFO, "Update the date of service from the
				// current date to 7 days in the past, then click save");
				test.log(LogStatus.INFO, "To Verify Edit Charge functionality");
				test.log(LogStatus.INFO,
						"Case:1=> To verify toast messages when clicked on Edit charge and Edit all charge for Visit button without selecting any check box");
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Edit Charge')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 15), "Please select a charge.");
				test.log(LogStatus.INFO,
						"*************Got Toast Message after click on Edit charge: Assertion Pass************");
				ActionUtils
						.click(driver.findElement(By.xpath("//button[contains(text(),'Edit all Charges for Visit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 15), "Please select a charge.");
				test.log(LogStatus.INFO,
						"*************Got Toast Message after click on Edit all charges for Visit: Assertion Pass************");
				test.log(LogStatus.INFO,
						"Case:2 To Verify Edit Charge functionality after selecting charge by clicking on check box");
				test.log(LogStatus.INFO, "Click on first Checkbox and then click on Edit charge button");
				ActionUtils.click(driver.findElement(
						By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr[1]//input[contains(@type,'checkbox')]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(text(),'Edit Charge')]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//*[@id='btnSaveCharge']"), 40);
				test.log(LogStatus.INFO, "Navigated to Edit Charge Page");
				test.log(LogStatus.INFO,
						"To Verify Only Fist row will appear in Editable mode and rest 2 rows are in locked state");
				int lockedrows = driver
						.findElements(By
								.xpath("//*[@id='tbl_proc']/table/tbody//span[contains(text(),'LOCKED Single Edit Mode')]"))
						.size();
				Assert.assertEquals(lockedrows, 2);
				test.log(LogStatus.INFO, "Assertion Pass");
				Commons.screenshot();
				test.log(LogStatus.INFO,
						"Case:3 Update the date of service from the current date to 7 days in the past, then click save");
				test.log(LogStatus.INFO, "Change Date in Date Of Service Field to" + "7/06/2016");
				HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "7/06/2016");
				test.log(LogStatus.INFO, "Enter Date in Plan of care" + "7/06/2016");
				HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "7/06/2016");
				test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
				Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
				AddChargeUtils.NavigateToViewChargePage(driver);
				test.log(LogStatus.INFO, "Navigated to View charges");
				test.log(LogStatus.INFO, "To Verify Updated Date Appears in View charge Page");
				Commons.waitforElement(driver, By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]"),90);
				String date3 = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(2)
						.getText().toString().trim();
				Assert.assertEquals(date3, "06/07/2016");
				Commons.screenshot();
				test.log(LogStatus.INFO, "******************Assertion Pass****************");
				// Edit all Charges For Visit
				test.log(LogStatus.INFO, "To Verify Edit all Charges for Visit functionality");
				test.log(LogStatus.INFO,
						"Case:2 To Verify Edit all Charges for Visit functionality after selecting charge by clicking on check box");
				test.log(LogStatus.INFO, "Click on first Checkbox and then click on Edit all Charges for Visit button");
				ActionUtils.click(driver.findElement(
						By.xpath("//*[@id='viewChargeGrid']/table/tbody/tr[1]//input[contains(@type,'checkbox')]")));
				ActionUtils
						.click(driver.findElement(By.xpath("//button[contains(text(),'Edit all Charges for Visit')]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//*[@id='btnSaveCharge']"), 40);
				test.log(LogStatus.INFO, "Navigated to Edit Charge Page");
				test.log(LogStatus.INFO, "To Verify All charges (with Same DOS) will appear in Editable mode");
				int Editrows = driver
						.findElements(By.xpath("//*[@id='tbl_proc']/table/tbody//span[contains(text(),'EDIT')]"))
						.size();
				// *[@id='tbl_proc']/table/tbody//span[contains(text(),'EDIT')]
				Assert.assertEquals(Editrows, 2);
				test.log(LogStatus.INFO, "Assertion Pass");
				Commons.screenshot();
				test.log(LogStatus.INFO,
						"Case:3 Update the date of service from the current date to 7 days in the past, then click save");
				test.log(LogStatus.INFO, "Change Date in Date Of Service Field to" + "7/06/2016");
				HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "7/06/2016");
				test.log(LogStatus.INFO, "Enter Date in Plan of care" + "7/06/2016");
				HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "7/06/2016");
				// test.log(LogStatus.INFO, "Enter Date in Plan of
				// care"+"01/01/2017");
				// *[@id='planofcaredate']/span"), "01/01/2017");
				test.log(LogStatus.INFO, "Clicked on Save Button On Add charge page");
				ActionUtils.click(AddChargesPage.SaveChargeButton(driver));
				Assert.assertEquals(Commons.capturemessage(driver, 190), "Charges Saved.");
				AddChargeUtils.NavigateToViewChargePage(driver);
				test.log(LogStatus.INFO, "Navigated to View charges");
				test.log(LogStatus.INFO, "To Verify Updated Dates Appears in View charge Page");
				test.log(LogStatus.INFO, "Total 3 rows updated for charge");
				Commons.waitforElement(driver, By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]"),90);
				String dateA = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(0)
						.getText().toString().trim();
				String dateB = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(1)
						.getText().toString().trim();
				String dateC = driver.findElements(By.xpath("//*[@id='viewChargeGrid']/table/tbody//td[4]")).get(2)
						.getText().toString().trim();
				Assert.assertEquals(dateA, "06/07/2016");
				Assert.assertEquals(dateB, "06/07/2016");
				Assert.assertEquals(dateC, "06/07/2016");
				Commons.screenshot();
				test.log(LogStatus.INFO, "******************Assertion Pass****************");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "Assertion Failed" + Throwables.getStackTraceAsString(e));
				test.log(LogStatus.FAIL, "Test fail");
			}
			test.log(LogStatus.INFO,
					"To Verify Checkboxes convert into Lock symbol when user makes payment and charges are not editable after Payment is Done ");
			// Deleting all existing charges on current patient
			test.log(LogStatus.INFO, "Deleting Existing charges on Patient");
			ActionUtils.click(driver
					.findElement(By.xpath("//*[@id='viewChargeGrid']/table//th//input[contains(@type,'checkbox')]")));
			ActionUtils.click(driver.findElement(By.xpath("//button//span[contains(text(),'Delete')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='btnDeleteWarning']"), 20);
			driver.findElement(By.xpath("//*[@id='btnDeleteWarning']")).click();
			Commons.capturemessage(driver, 40);
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'No Charges Available')]"), 30);
			test.log(LogStatus.INFO, "Adding Charges to current user with Full Payment");
			AddChargeUtils.NavigateToAddChargePage(driver);
			// VIEW CHARGE with payments as Partner user
			// charge-1
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "14/06/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "14/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "14/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97140", 1);
			test.log(LogStatus.INFO, "Clicked on Save charge and payment Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeandPaymentButton(driver));
			Commons.capturemessage(driver, 90);
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlPayCode']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlPayCode']//option[4]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='ddlMoneyType']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlMoneyType']//option[contains(text(),'Cash')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSavePayment']")));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 60);
			// charge-2
			test.log(LogStatus.INFO, "Enter Date in Date Of Service Field" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath("//*[@id='dateofservice']//span"), "15/06/2016");
			test.log(LogStatus.INFO, "Enter Date in Plan of care" + "15/06/2016");
			HandlingCalendars.datepick(driver, By.xpath(".//*[@id='planofcaredate']/span"), "15/06/2016");
			AddChargeUtils.AddCPTCode(driver, "97110", 1);
			test.log(LogStatus.INFO, "Clicked on Save charge and payment Button On Add charge page");
			ActionUtils.click(AddChargesPage.SaveChargeandPaymentButton(driver));
			Commons.capturemessage(driver, 90);
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlPayCode']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlPayCode']//option[4]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='ddlMoneyType']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlMoneyType']//option[contains(text(),'Cash')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSavePayment']")));
			Commons.waitForLoad(driver);
			Commons.capturemessage(driver, 60);
			AddChargeUtils.NavigateToViewChargePage(driver);
			test.log(LogStatus.INFO, "Navigated to View charges");
			test.log(LogStatus.INFO,
					"To verify Checkboxes changed to Lock Symbol and charges cannot be changed after Payment is Done");
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath(".//*[@id='viewChargeGrid']/table/tbody//i[contains(@class,'lock')]"), 30));
			System.out.println("Assertion Pass");
		} catch (Exception e) {
			Assert.assertFalse(true, "Test View Charge BMS User Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
