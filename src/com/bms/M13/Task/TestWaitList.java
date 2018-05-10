package com.bms.M13.Task;

import java.rmi.AccessException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.LoginPage;
import UIMap.WaitList;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.waiListUtils;

public class TestWaitList extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void TestSavingWithOutEnteringData() {
		test = extent.startTest("To Verify mandatory error message come when no data is entered in wait list ",
				"Test saving with out Entering data" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		waiListUtils.NavigateToWaitlist(driver);
		WaitList.waitSaveButton(driver).click();
		try {
			String toastermessage = Commons.capturemessage(driver, 100);
			Assert.assertEquals(toastermessage, "You must specify a Patient Name.");
			System.out.println("toaster message is : " + toastermessage);
		} catch (AssertionError e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			System.out.println("did not get toaster message , hence test case fails ");
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
		}
		WaitList.closeButton(driver).click();
	}

	@Test(enabled = true, priority = 2)
	public void TestAddingPatientToWaitList() throws InterruptedException {
		test = extent.startTest("To Verify patient is added to wait list ", "Test patient is added to wait list "
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.QuickAddpatient(driver);
		String patientheader = ActionUtils.getText(AddPatientPage.PatientNameInHeader(driver));
		String patient[] = patientheader.split(" ");
		// waiListUtils.DeleteWholeWaitList(driver);
		test.log(LogStatus.INFO, "Adding patient to wait list ");
		waiListUtils.NavigateToWaitlist(driver);
		try {
			WaitList.WaitListSearchButton(driver).click();
			WaitList.patientnameSearchBox(driver).sendKeys(patient[0]);
			WaitList.patientLastnameSearchBox(driver).sendKeys(patient[1]);
			WaitList.PatientSearchButton(driver).click();
			Commons.waitforElement(driver, By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr"),
					90);
			String patientName = driver
					.findElement(By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr/td[2]/span"))
					.getText();
			String patientLast = driver
					.findElement(By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr/td[3]/span"))
					.getText();
			String fullname = patientName + " " + patientLast;
			String newname = fullname.trim();
			System.out.println("patient added to wait list is :" + fullname);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr")));
			WaitList.waitSaveButton(driver).click();
			waiListUtils.NavigateToViewWaitlist(driver);
			driver.findElement(By.xpath("//div[@id='viewWaitList']//thead//tr//th[2]")).click();
			Commons.waitForLoad(driver);
			Assert.assertNotNull(driver.findElement(
					By.xpath("//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'" + ")]")));
			Commons.fnHighlightMe(driver, driver.findElement(
					By.xpath("//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'" + ")]")));
			driver.findElement(
					By.xpath("//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'" + ")]"))
					.click();
			test.log(LogStatus.INFO, "test case is passed patient is added To waitList successfully ");
		} catch (AssertionError e) {
			System.out.println("un able to add patient to wait list ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			System.out.println("un able to add patient to wait list ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void TestDeletePatientformWaitList() throws InterruptedException {
		test = extent.startTest("To Verify patient is removed from wait list ",
				"Test patient is removed from wait list " + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.QuickAddpatient(driver);
		String patientheader = ActionUtils.getText(AddPatientPage.PatientNameInHeader(driver));
		String patient[] = patientheader.split(" ");
		// waiListUtils.DeleteWholeWaitList(driver);
		test.log(LogStatus.INFO, "Adding patient to wait list");
		waiListUtils.NavigateToWaitlist(driver);
		try {
			WaitList.WaitListSearchButton(driver).click();
			WaitList.patientnameSearchBox(driver).sendKeys(patient[0]);
			WaitList.patientLastnameSearchBox(driver).sendKeys(patient[1]);
			WaitList.PatientSearchButton(driver).click();
			Commons.waitforElement(driver, By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr"),
					90);
			String patientName = driver
					.findElement(By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr/td[2]/span"))
					.getText();
			String patientLast = driver
					.findElement(By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr/td[3]/span"))
					.getText();
			String fullname = patientName + " " + patientLast;
			String newname = fullname.trim();
			System.out.println("patient added to wait list is :" + fullname);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@double-click='vmWaitList.patientGridClick()']//tbody/tr")));
			WaitList.waitSaveButton(driver).click();
			waiListUtils.NavigateToViewWaitlist(driver);
			driver.findElement(By.xpath("//div[@id='viewWaitList']//thead//tr//th[2]")).click();
			Commons.waitForLoad(driver);
			Assert.assertNotNull(driver.findElement(
					By.xpath("//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'" + ")]")));
			Commons.fnHighlightMe(driver, driver.findElement(
					By.xpath("//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'" + ")]")));
			driver.findElement(
					By.xpath("//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'" + ")]"))
					.click();
			test.log(LogStatus.INFO, "Deleting Patient from wait list ");
			driver.findElement(By.xpath("//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'"
					+ ")]//ancestor::tr//td[1]//input")).click();
			WaitList.DeleteButton(driver).click();
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//*[@id='confirmdelete']//li[contains(.,'Are you sure you want to remove the checked patient(s) from the waitlist?')]"),
						90);
				String actualMessage = driver
						.findElement(By
								.xpath("//*[@id='confirmdelete']//li[contains(.,'Are you sure you want to remove the checked patient(s) from the waitlist?')]"))
						.getText();
				Assert.assertEquals(actualMessage,
						"Are you sure you want to remove the checked patient(s) from the waitlist?");
				System.out.println("got confirmation message :" + actualMessage);
				ActionUtils.click(driver.findElement(By.xpath("//button[@ng-click='vmWaitList.removeWaitlist()']")));
			} catch (AssertionError e) {
				System.out.println("not getting right confirmation pop up message ");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			} catch (Exception e) {
				System.out.println("not getting confirmation pop up ");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
				test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
			}
			Commons.capturemessage(driver, 60);
			Commons.waitForLoad(driver);
			WebElement element = null;
			try {
				element = driver.findElement(By.xpath(
						"//div[@id='viewWaitList']//tr//td//span[contains(text()," + "'" + newname + "'" + ")]"));
			} catch (Exception e) {
				element = null;
			}
			Assert.assertNull(element);
			test.log(LogStatus.INFO, "test case is passed patient is deleted from waitList successfully ");
		} catch (AssertionError e) {
			System.out.println("un able to add patient to wait list ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
		} catch (Exception e) {
			System.out.println("un able to add patient to wait list ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			test.log(LogStatus.INFO, Throwables.getStackTraceAsString(e));
		}
	}
}
