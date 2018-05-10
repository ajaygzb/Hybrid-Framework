package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.Commons;
import Utils.HandlingCalendars;

public class TestInsOverpayment extends TestSetUp {
	public String actual = null;

	@Test(enabled = true, priority = 1)
	public void AddPaymentsTransactionType() throws InterruptedException {
		try {
			// TC-1
			test = extent.startTest("Add Payments to Patient with Transaction Type Insurance Overpayment",
					"Add Payments to Patient with Transaction Type Insurance Overpayment" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			test.log(LogStatus.INFO, "Search patient with complete Case");
			AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
			// check if there are existing payments on user
			if (Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='patientCredits']//tbody//tr"), 5)) {
				// Delete Existing Payment
				ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
				Commons.waitforElement(driver,
						By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
				Commons.screenshot();
				ActionUtils.click(driver
						.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
				Commons.capturemessage(driver, 60);
			}
			try {
				// Case-1
				// Add Payments to Patient with Insurance Overpayment
				HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"),
						"01/01/2017");
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "100");
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
				test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
				ActionUtils.click(CasePage.LocationMagnifier(driver));
				ActionUtils.click(CasePage.LocationSearchButton(driver));
				ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
				ActionUtils.click(driver.findElement(
						By.xpath("//select[@id='sel_TransType']//option[contains(text(),'INS OVERPAYMENT')]")));
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30),
						"Please fill all the mandatory fields or check highlighted field values.");
				ActionUtils.click(driver.findElement(By.xpath("//select[@id='sel_Insurance']//option[2]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
				Commons.waitforElement(driver,
						By.xpath("//div[@id='summary']//tbody/tr[3]/td[3]/span[contains(text(),'$100.00')]"), 50);
				Assert.assertEquals(driver
						.findElement(
								By.xpath("//div[@id='summary']//tbody/tr[3]/td[3]/span[contains(text(),'$100.00')]"))
						.getText(), "$100.00");
				System.out.println("Added credits to Patient");
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='patientCredits']//tbody/tr")).getText()
						.contains("INS OVERPAYMENT"), "Credit row not added");
				Commons.screenshot();
				// Delete Existing Payment
				ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
				Commons.waitforElement(driver,
						By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
				Commons.screenshot();
				ActionUtils.click(driver
						.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
				actual = Commons.capturemessage(driver, 60);
				if (actual != null) {
					Assert.assertEquals(actual, "Credit Deleted.");
				}
			} catch (AssertionError e) {
				test.log(LogStatus.FAIL, "Add Payments to Patient with Transaction Type Insurance Overpayment"
						+ Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-2
			// Add Payments to Patient with with Transaction Type INTEREST
			test = extent.startTest("Add Payments to Patient with Transaction Type INTEREST",
					"Add Payments to Patient with Transaction Type INTEREST" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "On Existing Patient in previous case");
			try {
				Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"),50);
				HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"),
						"01/01/2017");
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "100");
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
				test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
				ActionUtils.click(CasePage.LocationMagnifier(driver));
				ActionUtils.click(CasePage.LocationSearchButton(driver));
				ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
				ActionUtils.click(driver
						.findElement(By.xpath("//select[@id='sel_TransType']//option[contains(text(),'INTEREST')]")));
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30),
						"Please fill all the mandatory fields or check highlighted field values.");
				ActionUtils.click(driver.findElement(By.xpath("//select[@id='sel_Insurance']//option[2]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
				Commons.waitforElement(driver,
						By.xpath("//div[@id='summary']//tbody/tr[4]/td[3]/span[contains(text(),'$100.00')]"), 50);
				Assert.assertEquals(driver
						.findElement(
								By.xpath("//div[@id='summary']//tbody/tr[4]/td[3]/span[contains(text(),'$100.00')]"))
						.getText(), "$100.00");
				System.out.println("Added credits to Patient");
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='patientCredits']//tbody/tr")).getText()
						.contains("INTEREST"), "Credit row not added");
				Commons.screenshot();
				// Delete Existing Payment
				ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
				Commons.waitforElement(driver,
						By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
				Commons.screenshot();
				ActionUtils.click(driver
						.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
				actual = Commons.capturemessage(driver, 60);
				if (actual != null) {
					Assert.assertEquals(actual, "Credit Deleted.");
				}
			} catch (AssertionError e) {
				test.log(LogStatus.FAIL,
						"Add Payments to Patient with Transaction Type INTEREST" + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-3
			// Add Payments to Patient with Transaction Type MEDICAL RECORD
			test = extent.startTest("Add Payments to Patient with Transaction Type MEDICAL RECORD",
					"Add Payments to Patient with Transaction Type MEDICAL RECORD" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "On Existing Patient in previous case");
			try {
				HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"),
						"01/01/2017");
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "100");
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
				test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
				ActionUtils.click(CasePage.LocationMagnifier(driver));
				ActionUtils.click(CasePage.LocationSearchButton(driver));
				ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
				ActionUtils.click(driver.findElement(
						By.xpath("//select[@id='sel_TransType']//option[contains(text(),'MEDICAL RECORD')]")));
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30),
						"Please fill all the mandatory fields or check highlighted field values.");
				ActionUtils.click(driver.findElement(By.xpath("//select[@id='sel_Insurance']//option[2]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
				Commons.waitforElement(driver,
						By.xpath("//div[@id='summary']//tr[1]/td[2]/span[contains(text(),'$100.00')]"), 50);
				actual = driver
						.findElement(By.xpath("//div[@id='summary']//tr[1]/td[2]/span[contains(text(),'$100.00')]"))
						.getText().trim();
				System.out.println(actual);
				Assert.assertTrue(actual.contains("$100.00"));
				System.out.println("Added credits to Patient");
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='patientCredits']//tbody/tr")).getText()
						.contains("MEDICAL RECORD"), "Credit row not added");
				Commons.screenshot();
				// Delete Existing Payment
				ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
				Commons.waitforElement(driver,
						By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
				Commons.screenshot();
				ActionUtils.click(driver
						.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
				actual = Commons.capturemessage(driver, 60);
				if (actual != null) {
					Assert.assertEquals(actual, "Credit Deleted.");
				}
			} catch (AssertionError e) {
				test.log(LogStatus.FAIL, "Add Payments to Patient with Transaction Type MEDICAL RECORD"
						+ Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-4
			// Add Payments to Patient with Transaction Type SUPPLY RECORD
			test = extent.startTest("Add Payments to Patient with Transaction Type SUPPLY RECORD",
					"Add Payments to Patient with Transaction Type SUPPLY RECORD" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "On Existing Patient in previous case");
			try {
				HandlingCalendars.datepick(driver, By.xpath("//button[contains(@ng-click,'DepositDate')]"),
						"01/01/2017");
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_Amount']")), "100");
				ActionUtils.sendKeys(driver.findElement(By.xpath("//input[@id='txt_CCnum']")), "1234");
				test.log(LogStatus.INFO, "Add Location And Billing Provider fields");
				ActionUtils.click(CasePage.LocationMagnifier(driver));
				ActionUtils.click(CasePage.LocationSearchButton(driver));
				ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
				ActionUtils.click(driver.findElement(
						By.xpath("//select[@id='sel_TransType']//option[contains(text(),'SUPPLY RECORD')]")));
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='sel_PayCode']//option[5]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30),
						"Please fill all the mandatory fields or check highlighted field values.");
				ActionUtils.click(driver.findElement(By.xpath("//select[@id='sel_Insurance']//option[2]")));
				ActionUtils.click(driver.findElement(By.xpath("//button[contains(@id,'AddCredit')]")));
				Assert.assertEquals(Commons.capturemessage(driver, 30), "Credit Added.");
				System.out.println("Added credits to Patient");
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='patientCredits']//tbody/tr")).getText()
						.contains("SUPPLY RECORD"), "Credit row not added");
				Commons.screenshot();
				// Delete Existing Payment
				ActionUtils.click(driver.findElement(By.xpath("//input[@id='selectAll']")));
				ActionUtils.click(driver.findElement(By.xpath("//button[@id='btn_DeleteCredit']")));
				Commons.waitforElement(driver,
						By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]"), 15);
				Commons.screenshot();
				ActionUtils.click(driver
						.findElement(By.xpath("//div[@id='deleteWarningPopup']//button//span[contains(text(),'OK')]")));
				actual = Commons.capturemessage(driver, 60);
				if (actual != null) {
					Assert.assertEquals(actual, "Credit Deleted.");
				}
			} catch (AssertionError e) {
				test.log(LogStatus.FAIL, "Add Payments to Patient with Transaction Type SUPPLY RECORD"
						+ Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true, "Add Payments to Patient with Transaction Type SUPPLY RECORD"
						+ Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
		} catch (Exception e) {
			Assert.assertFalse(true, "Payments Add Failed" + Throwables.getStackTraceAsString(e));
		}
	}
}
