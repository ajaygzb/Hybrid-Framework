package com.bms.M9.AddCharges;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import Utils.APageUtils;
import Utils.AddChargeUtils;
import Utils.Commons;

public class TestPaymentDropDowns extends TestSetUp {
	public String strActualtext;
	public List<WebElement> actual = null;

	// PAY.DEF.004
	@Test(enabled = true, priority = 1)
	public void DefaultPaymentCodeWithAmountTocollect_BMSUSER() throws InterruptedException {
		// TC-1
		test = extent.startTest("Default Payment Code by Transaction Type - BMS USER",
				"Default Payment Code by Transaction Type - BMS USER" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login as user BMS");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userBMS"), Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Search patient with complete Case");
		AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
		AddChargeUtils.NavigateToPaymentPage(driver);
		test.log(LogStatus.INFO, "Navigate to Payments page");
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
		try {
			// Case-1 Transaction Type Copay
			test.log(LogStatus.INFO, "*************Case-1 When Transaction Type Copay *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as Copay");
			AddChargeUtils.PaymentTransactiontype(driver, "Copay");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 6);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = { "PATIENT PAY CREDIT CARD", "PATIENT PERSONAL CK", "PATIENT PD IN OFFICE",
					"PATIENT PAY CASH", "CASH BASED PGRM PAT PYMT", "MEDICAL RECORD PAYMENT", "Cash DOS", };
			APageUtils.DropDownValuesAssertion(driver, actual, expected1);
			// Case-2 Transaction Type INS OVERPAYMENT
			test.log(LogStatus.INFO,
					"*************Case-2 When Transaction Type as INS OVERPAYMENT *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as INS OVERPAYMENT");
			AddChargeUtils.PaymentTransactiontype(driver, "INS OVERPAYMENT");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected2 = { "BMS PAY CREDIT TO CLINIC", "PATIENT PAY CREDIT CARD", "PATIENT PERSONAL CK",
					"PATIENT PD IN OFFICE", "MISCELLANEOUS PAYMENT", "PATIENT PAY CASH", "APPLIED TO DEDUCTIBLE",
					"INSURANCE PAYMENT CONTRAC", "INS PAY NON CONTRACT", "MEDICAID PAYMENT", "MEDICARE PAYMENT",
					"WORKER COMP PAYMENT", "HMO PAYMENT", "ATTORNEY PAYMENT", "CASH BASED PGRM PAT PYMT",
					"REFUND INSURANCE", "REFUND PATIENT", "bms test payment code", "PAYMENT CORRECTION",
					"MEDICAL RECORD PAYMENT", "No charge visit", "No Charge Visit", "Cash DOS" };
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-3 Transaction Type INTEREST
			test.log(LogStatus.INFO,
					"*************Case-3 When Transaction Type as INTEREST *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as INTEREST");
			AddChargeUtils.PaymentTransactiontype(driver, "INTEREST");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-4 Transaction Type MEDICAL RECORD
			test.log(LogStatus.INFO,
					"*************Case-4 When Transaction Type as MEDICAL RECORD *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as MEDICAL RECORD");
			AddChargeUtils.PaymentTransactiontype(driver, "MEDICAL RECORD");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-5 Transaction Type PATIENT REFUND
			test.log(LogStatus.INFO,
					"*************Case-5 When Transaction Type as PATIENT REFUND *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as PATIENT REFUND");
			AddChargeUtils.PaymentTransactiontype(driver, "PATIENT REFUND");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-6 Transaction Type SUPPLY RECORD
			test.log(LogStatus.INFO,
					"*************Case-6 When Transaction Type as SUPPLY RECORD*******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as SUPPLY RECORD");
			AddChargeUtils.PaymentTransactiontype(driver, "SUPPLY RECORD");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
		} catch (Exception e) {
			Assert.assertFalse(true, "Could not match Drop down values Payment Code on Payment Page"
					+ Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void DefaultPaymentCodeWithAmountTocollect_PartnerUser() throws InterruptedException {
		// TC-1
		test = extent.startTest("Default Payment Code by Transaction Type - Customer (partner)",
				"Default Payment Code by Transaction Type - Customer (partner)" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login as user Partner");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPartner"), Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Search patient with complete Case");
		AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
		AddChargeUtils.NavigateToPaymentPage(driver);
		test.log(LogStatus.INFO, "Navigate to Payments page");
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
		try {
			// Case-1 Transaction Type Copay
			test.log(LogStatus.INFO, "*************Case-1 When Transaction Type Copay *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as Copay");
			AddChargeUtils.PaymentTransactiontype(driver, "Copay");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 6);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = { "PATIENT PAY CREDIT CARD", "PATIENT PERSONAL CK", "PATIENT PD IN OFFICE",
					"PATIENT PAY CASH", "CASH BASED PGRM PAT PYMT", "MEDICAL RECORD PAYMENT", "Cash DOS", };
			APageUtils.DropDownValuesAssertion(driver, actual, expected1);
			// Case-2 Transaction Type INS OVERPAYMENT
			test.log(LogStatus.INFO,
					"*************Case-2 When Transaction Type as INS OVERPAYMENT *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as INS OVERPAYMENT");
			AddChargeUtils.PaymentTransactiontype(driver, "INS OVERPAYMENT");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected2 = { "BMS PAY CREDIT TO CLINIC", "PATIENT PAY CREDIT CARD", "PATIENT PERSONAL CK",
					"PATIENT PD IN OFFICE", "MISCELLANEOUS PAYMENT", "PATIENT PAY CASH", "APPLIED TO DEDUCTIBLE",
					"INSURANCE PAYMENT CONTRAC", "INS PAY NON CONTRACT", "MEDICAID PAYMENT", "MEDICARE PAYMENT",
					"WORKER COMP PAYMENT", "HMO PAYMENT", "ATTORNEY PAYMENT", "CASH BASED PGRM PAT PYMT",
					"REFUND INSURANCE", "REFUND PATIENT", "bms test payment code", "PAYMENT CORRECTION",
					"MEDICAL RECORD PAYMENT", "No charge visit", "No Charge Visit", "Cash DOS" };
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-3 Transaction Type INTEREST
			test.log(LogStatus.INFO,
					"*************Case-3 When Transaction Type as INTEREST *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as INTEREST");
			AddChargeUtils.PaymentTransactiontype(driver, "INTEREST");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-4 Transaction Type MEDICAL RECORD
			test.log(LogStatus.INFO,
					"*************Case-4 When Transaction Type as MEDICAL RECORD *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as MEDICAL RECORD");
			AddChargeUtils.PaymentTransactiontype(driver, "MEDICAL RECORD");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-5 Transaction Type PATIENT REFUND
			test.log(LogStatus.INFO,
					"*************Case-5 When Transaction Type as PATIENT REFUND *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as PATIENT REFUND");
			AddChargeUtils.PaymentTransactiontype(driver, "PATIENT REFUND");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			// Case-6 Transaction Type SUPPLY RECORD
			test.log(LogStatus.INFO,
					"*************Case-6 When Transaction Type as SUPPLY RECORD*******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as SUPPLY RECORD");
			AddChargeUtils.PaymentTransactiontype(driver, "SUPPLY RECORD");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 22);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected2);
		} catch (Exception e) {
			Assert.assertFalse(true, "Could not match Drop down values Payment Code on Payment Page"
					+ Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void DefaultPaymentCodeWithAmountTocollect_FrontOfficeStaffUser() throws InterruptedException {
		// TC-1
		test = extent.startTest("Default Payment Code by Transaction Type - Customer (front office & provider)",
				"Default Payment Code by Transaction Type - Customer (front office & provider)"
						+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		test.log(LogStatus.INFO, "Login as user FrontOfficeStaff Provider");
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userFrontofficestaff"),
				Commons.readPropertyValue("password"));
		test.log(LogStatus.INFO, "Search patient with complete Case");
		AddChargeUtils.QuickSearchPatient(driver, "EMRMedicare");
		AddChargeUtils.NavigateToPaymentPage(driver);
		test.log(LogStatus.INFO, "Navigate to Payments page");
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Payments')]"), 60);
		try {
			// Case-1 Transaction Type Copay
			test.log(LogStatus.INFO, "*************Case-1 When Transaction Type Copay *******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as Copay");
			AddChargeUtils.PaymentTransactiontype(driver, "Copay");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 3);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			// make sure you found the right number of elements
			String[] expected1 = { "PATIENT PERSONAL CK", "PATIENT PD IN OFFICE", "MEDICAL RECORD PAYMENT",
					"Cash DOS", };
			APageUtils.DropDownValuesAssertion(driver, actual, expected1);
			// Case-2 Transaction Type SUPPLY RECORD
			test.log(LogStatus.INFO,
					"*************Case-2 When Transaction Type as SUPPLY RECORD*******************************");
			test.log(LogStatus.INFO, "Setting Transaction Type as SUPPLY RECORD");
			AddChargeUtils.PaymentTransactiontype(driver, "SUPPLY RECORD");
			Commons.screenshot();
			Commons.existsElement(driver, By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = driver.findElements(By.xpath("//select[@id='sel_PayCode']/option[@label]"));
			actual = actual.subList(0, 3);
			System.out.println("No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "No Of Dropdown option Values in Payment Code are:" + actual.size());
			test.log(LogStatus.INFO, "Validating Values in Transaction type dropdown list");
			for (WebElement we : actual) {
				System.out.println(we.getText());
				test.log(LogStatus.INFO, we.getText());
			}
			APageUtils.DropDownValuesAssertion(driver, actual, expected1);
		} catch (Exception e) {
			Assert.assertFalse(true, "Could not match Drop down values Payment Code on Payment Page"
					+ Throwables.getStackTraceAsString(e));
		}
	}
}