package com.bms.M9.AddCharges;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import Utils.AddChargeUtils;
import Utils.Commons;

public class TestRolePermissionsPaymentsPage extends TestSetUp {
	public String strActualtext;

	// PAY.PER.001,002,003,004
	// All Test of this class have merged to TestRolePermissionsAddcharges page
	// @Test(enabled = true,priority =1)
	public void PermissionByRoleFrontOfficeStaff() throws InterruptedException {
		try {
			test = extent.startTest("Payments page availability as Customer with role: Front Office Staff",
					"Payments page availability as Customer with role: Front Office Staff"
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
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='scheduler']//b[contains(text(),'Appointment')]"),
					80);
			test.log(LogStatus.INFO, "RevFlow home page displays");
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'No Patient Selected')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(text(),'No Patient Selected')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Patient Selected");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true);
			}
			// Use the patient search to access a patient account without
			// complete case(s)
			test.log(LogStatus.INFO, "Use the patient search to access a patient account without complete case(s)");
			AddChargeUtils.QuickSearchPatient(driver, "Automation");
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page without complete case(s)");
			// Verify the defaults for the Payment Code when Transaction Type is
			// set to COPAY
			try {
				String expectedMessage = driver.findElement(By.xpath("//h1[contains(text(),'Payments')]")).getText();
				Assert.assertTrue(expectedMessage.contains("Payments"));
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true);
			}
		} catch (Exception e) {
			test.log(LogStatus.INFO,
					"***************Test Role Permissions front office staff Payments Page Fail*************");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO,
				"***************Test Role Permissions front office staff Payments Page Pass*************");
	}

	// @Test(enabled = true,priority =2)
	public void PermissionByRolePracticeAdmin() throws InterruptedException {
		try {
			test = extent.startTest("Payments page availability as Customer with role: Practice Admin.",
					"Payments page availability as Customer with role: Practice Admin." + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
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
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'No Patient Selected')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(text(),'No Patient Selected')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Patient Selected");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true);
			}
			// Use the patient search to access a patient account without
			// complete case(s)
			test.log(LogStatus.INFO, "Use the patient search to access a patient account without complete case(s)");
			AddChargeUtils.QuickSearchPatient(driver, "Automation");
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page without complete case(s)");
			try {
				String expectedMessage = driver.findElement(By.xpath("//h1[contains(text(),'Payments')]")).getText();
				Assert.assertTrue(expectedMessage.contains("Payments"));
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true);
			}
		} catch (Exception e) {
			test.log(LogStatus.INFO,
					"***************Test Role Permissions Practice Admin. Payments Page Fail*************");
			Assert.assertFalse(true);
		}
		test.log(LogStatus.INFO,
				"***************Test Role Permissions Practice Admin. Payments Page Pass*************");
	}

	// @Test(enabled = true,priority =3)
	public void PermissionByRoleCustomerProvider() throws InterruptedException {
		try {
			test = extent.startTest("Payments page availability as Customer with role: Provider",
					"Payments page availability as Customer with role: Provider" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			Commons.logout(driver);
			test.log(LogStatus.INFO, "Login with a user account that is setup as a 'Customer' and role = Provider ");
			Commons.logintoRevflow(driver, Commons.readPropertyValue("userCustomer"),
					Commons.readPropertyValue("password"));
			Commons.waitForLoad(driver);
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page");
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'No Patient Selected')]"), 60);
			try {
				String expectedMessage = driver.findElement(By.xpath("//span[contains(text(),'No Patient Selected')]"))
						.getText();
				Assert.assertEquals(expectedMessage, "No Patient Selected");
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true);
			}
			// Use the patient search to access a patient account without
			// complete case(s)
			test.log(LogStatus.INFO, "Use the patient search to access a patient account without complete case(s)");
			AddChargeUtils.QuickSearchPatient(driver, "Automation");
			AddChargeUtils.NavigateToPaymentPage(driver);
			test.log(LogStatus.INFO, "Navigate to Payments page without complete case(s)");
			try {
				String expectedMessage = driver.findElement(By.xpath("//h1[contains(text(),'Payments')]")).getText();
				Assert.assertTrue(expectedMessage.contains("Payments"));
				test.log(LogStatus.INFO, "***************Assertion Pass **********************" + expectedMessage);
				Commons.screenshot();
			} catch (AssertionError e) {
				test.log(LogStatus.INFO,
						"***************Assertion Fail*************" + Throwables.getStackTraceAsString(e));
				Assert.assertFalse(true);
			}
		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************Test Role Permissions Provider Payments Page Fail*************");
			Assert.assertFalse(true);
		}
		test.log(LogStatus.INFO, "***************Test Role Permissions Provider Payments Page Pass*************");
	}
}
