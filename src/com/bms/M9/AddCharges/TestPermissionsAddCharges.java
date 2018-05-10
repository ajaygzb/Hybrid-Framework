package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import ReportUtils.ExtentManager;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.Commons;

public class TestPermissionsAddCharges extends TestSetUp {
	TestRolePermissionsPaymentsPage Payment = new TestRolePermissionsPaymentsPage();
	public String strActualtext;

	// CHG.ADD.001,002,003
	@Test(enabled = true, priority = 1)
	public void PermissionByRoleFrontOfficeStaff() throws InterruptedException {
		try {
			test = extent.startTest(
					" CHG.ADD.001: Add Charge page availability as Customer with role: Front Office Staff",
					"Add Charge page availability as Customer with role: Front Office Staff"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			test.log(LogStatus.INFO,
					"Login with a user account that is setup as a 'Customer' and role = front office staff ");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userFrontofficestaff"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			Assert.assertTrue(Commons.waitforElement(driver,
					By.xpath("//*[@id='scheduler']//b[contains(text(),'Appointment')]"), 80));
			test.log(LogStatus.INFO, "RevFlow home page displays");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'No Patient Selected')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(.,'No Patient Selected')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Patient Selected");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Search patient with Incomplete Case");
			AddChargeUtils.QuickSearchPatient(driver, "Automation");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'No Cases Available')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(.,'No Cases Available')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Cases Available");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Search patient with complete Case");
			AddChargeUtils.QuickSearchPatient(driver, "EMRmedicare");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']//option[2]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			try {
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']//option[2]"), 60));
				test.log(LogStatus.INFO, "***************Assertion Pass **********************"
						+ "Charges page displays case drop down.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
			}
		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************Test Permission By Role front office staff Fail*************");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "***************Test Permission By Role front office staff Pass*************");
		Payment.PermissionByRoleFrontOfficeStaff();
	}

	@Test(enabled = true, priority = 2)
	public void PermissionByRolePracticeAdmin() throws InterruptedException {
		try {
			test = extent.startTest(
					" CHG.ADD.002: Add Charge page availability as Customer with role: Practice Administrator",
					"Add Charge page availability as Customer with role: Practice Administrator"
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			test.log(LogStatus.INFO,
					"Login with a user account that is setup as a 'Customer' and role = Practice Administrator ");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userPracticeAdminstrator"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			Assert.assertTrue(
					Commons.waitforElement(driver, By.xpath("//img[@src='/content/images/LogoProvider.png']"), 60));
			test.log(LogStatus.INFO, "RevFlow home page displays");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'No Patient Selected')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(.,'No Patient Selected')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Patient Selected");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Search patient with Incomplete Case");
			AddChargeUtils.QuickSearchPatient(driver, "Automation");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'No Cases Available')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(.,'No Cases Available')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Cases Available");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
			}
			test.log(LogStatus.INFO, "Search patient with complete Case");
			AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
			AddChargeUtils.NavigateToAddChargePage(driver);
			test.log(LogStatus.INFO, "Navigate to Add charges page");
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']//option[2]"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//select[@id='ddlChargeCaseList']//option[2]")));
			Commons.waitForLoad(driver);
			try {
				Assert.assertTrue(
						Commons.waitforElement(driver, By.xpath("//select[@id='ddlChargeCaseList']//option[2]"), 60));
				test.log(LogStatus.INFO, "***************Assertion Pass **********************"
						+ "Charges page displays case drop down.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
			}
		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************Test Permission By Role Practice Administrator Fail*************");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "***************Test Permission By Role Practice Administrator Pass*************");
		Payment.PermissionByRolePracticeAdmin();
	}

	@Test(enabled = true, priority = 3)
	public void PermissionByRoleCustomerProvider() throws InterruptedException {
		try {
			test = extent.startTest("CHG.ADD.003: Add Charge page availability as Customer with role: Provider",
					"Add Charge page availability as Customer with role: Provider" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "Login with a user account that is setup as a 'Customer' and role = Provider ");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userCustomer"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			try {
				test.log(LogStatus.INFO, "Open Transaction sub menu");
				Commons.waitforElement(driver, By.xpath("//span[contains(.,'Transactions')]"), 80);
				ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Transactions')]")));
				test.log(LogStatus.INFO, "Verify View Charges Option not available in sub menu");
				Assert.assertFalse(
						Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'View Charges')]"), 2));
				test.log(LogStatus.INFO, "Verify Charge Audit Option not available in sub menu");
				Assert.assertFalse(
						Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(.,'Charge Audit')]"), 2));
				test.log(LogStatus.INFO, "***************Assertion Pass **********************");
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.FAIL, "***************Assertion Fail*************");
				// Assert.assertFalse(true,Throwables.getStackTraceAsString(e));
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "***************Test Permission By Role Provider  Fail*************");
			// Assert.assertFalse(true);
		}
		test.log(LogStatus.INFO, "***************Test Permission By Role Provider Pass*************");
		Commons.screenshot();
		extent.endTest(test);
		Payment.PermissionByRoleCustomerProvider();
	}
}
