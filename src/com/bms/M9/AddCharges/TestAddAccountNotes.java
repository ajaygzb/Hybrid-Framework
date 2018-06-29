package com.bms.M9.AddCharges;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.AddChargeUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.SearchPatientUtils;

public class TestAddAccountNotes extends TestSetUp {
	@Test(enabled = false, priority = 2)
	public void AddAccountNotes() throws InterruptedException {
		try {
			test = extent.startTest("To Verify User can Add Account Notes from Audit Charge Page",
					"To Verify User can Add Account Notes from Audit Charge Page" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddChargeUtils.NavigateToChargeAuditPage(driver);
			test.log(LogStatus.INFO, "go to Transactions > Audit Charges");
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='auditChargeSummaryGrid']//tr[1]//*[@id='chargeAnchor']")));
			test.log(LogStatus.INFO, "Select a hyperlink  numeric value under the Charge for Audit column.");
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='btnCreateReport']"), 30);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Click Create Report");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnCreateReport']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='mainContent']//table//th[1]"), 30);
			Commons.screenshot();
			Random r = new Random();
			int randomValue = r.nextInt(driver.findElements(By.xpath("//*[@id='mainContent']//table//th[contains(text(),'Patient')]")).size()); // Getting a random
			// value that is
			// between 0 and
			// (list's size)-1
			String str = ActionUtils.getText(driver.findElements(By.xpath("//*[@id='mainContent']//table//th[contains(text(),'Patient')]")).get(randomValue)); // Clicking on the


			System.out.println("Patient Selected "+str);

			// random item
			// in the list.
			// String
			//	str=driver.findElements(By.xpath("//*[@id='mainContent']//table//th[contains(text(),'Patient')]")).get(0).getText();
			String pid = str.replaceAll("\\D+", "");
			System.out.println(str.trim());
			test.log(LogStatus.INFO,
					"Choose a patient for testing and under their charge details select Add Account Note" + str);
			test.log(LogStatus.INFO, "Click Create Report");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnNewNote" + randomValue + "']")));
			Commons.waitforElement(driver, By.xpath("//*[@id='txtNote']"), 20);
			String Randomstr = RandomStringUtils.randomAlphabetic(10);
			System.out.println(Randomstr);
			driver.findElement(By.xpath("//*[@id='txtNote']")).sendKeys("Automation Test"+Randomstr);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlNoteCode']//option[contains(text(),'INFO')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlSticky']//option[contains(text(),'Charge')]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnSaveNote']")));
			Commons.capturemessage(driver, 30);
			SearchPatientUtils.QuickpatientSearch(driver, pid);
			Commons.waitforElement(driver, By.xpath("//*[@id='stickyNoteIcon']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//div[@id='displayStickyNotePopup']//button[contains(text(),'Print')]"), 30);
			boolean Text_Expected = driver.getPageSource().contains(Randomstr);
			Assert.assertTrue(Text_Expected);
			Commons.screenshot();
			ActionUtils.click(driver
					.findElement(By.xpath("//div[@id='displayStickyNotePopup']//button[contains(text(),'Close')]")));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************Test Validate Mandatory Fields Add Charges Fail*************");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "***************Test Validate Mandatory Fields Add Charges Pass*************");
	}


	@Test(enabled = true, priority = 1)
	public void AddAccountNotesPatientHeader() throws InterruptedException {
		try {
		
			test = extent.startTest("To Verify User can Add Account Notes from Patient Header also validate functionality of print selected note button",
					"To Verify User can Add Account Notes from Patient Header also validate functionality of print selected note button" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
             AddPatientUtils.QuickAddpatient(driver);
			test.log(LogStatus.INFO, "Click on Sticky on Patient Header");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
			Commons.waitforElement(driver, By.xpath("//h4[contains(.,'Account Notes for')]"),20);
			Commons.waitforElement(driver, By.xpath("//*[@id='gridAccountNoteIcon']//p[contains(.,'No records found')]"),20);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Click on Add New Note Button");
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='displayStickyNotePopup']//button[contains(.,'Add New Note')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='txtNote']"), 20);
			String Randomstr = RandomStringUtils.randomAlphabetic(10);
			System.out.println(Randomstr);
			driver.findElement(By.xpath("//*[@id='txtNote']")).sendKeys("Automation Test"+Randomstr);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlNoteCode']//option[contains(text(),'INFO')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlStickyPatientHeader']//option[contains(text(),'Charge')]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnAddNote']")));
			Commons.capturemessage(driver, 30);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='displayStickyNotePopup']//button[contains(text(),'Close')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='stickyNoteIcon']"), 30);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,By.xpath("//div[@id='displayStickyNotePopup']//button[contains(text(),'Print')]"), 30);
			boolean Text_Expected = driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']")).getText().contains(Randomstr);
			Assert.assertTrue(Text_Expected);
			Commons.screenshot();
			test.log(LogStatus.INFO, "Validate Print Selected Notes button");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnPrintNote']")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Please select a record"));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelectAllHeader']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnPrintNote']")));
			Commons.waitforElement(driver, By.xpath("//div[@id='printAllNotesHeader']//h4[text()='Account Notes']"), 30);
			Commons.screenshot();
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='printAllNotesHeader']//*[@id='printContents']/tbody"))
					.getText().contains(Randomstr));
			
			test = extent.startTest("To Verify User can view a 'account note' From the patient header sticky option",
					"To Verify User can view a 'account note' From the patient header sticky option" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			String arr[]={"Note For:","Company:","Printed Date:","Account Number:","Printed Time:","Note Info:","Note Text:","Note Type:","Created Date:","Created By:"};
			test.log(LogStatus.INFO, "validate the following fields are displayed on the Confirm Print dialogue box: ");
			Commons.AssertTextArray(driver, By.xpath("//div[@id='printAllNotesHeader']//*[@id='printContents']/tbody//b"), arr);
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteHeaderCloseBtn']")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='displayStickyNotePopup']//button[contains(text(),'Close')]")));
			






			test = extent.startTest("To Verify User can view a 'account note' From the patient_view screen",
					"To Verify User can view a 'account note' From the patient_view screen" + "*****Current Browser******"
							+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");

			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#AccountNotes']")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='collapseThree']//button[contains(.,'Add New Note')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='txtNotePatient']"), 20);
			String Randomstr1 = RandomStringUtils.randomAlphanumeric(10);
			System.out.println(Randomstr1);
			driver.findElement(By.xpath("//*[@id='txtNotePatient']")).sendKeys("Automation Test"+Randomstr1);
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlNoteCode']//option[contains(text(),'INFO')]")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlStickyAddNote']//option[contains(text(),'Charge')]")));
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='btnAddNote']")));
			Commons.capturemessage(driver, 30);
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='gridAccountNote']"))
					.getText().contains(Randomstr1));


			Commons.screenshot();
			test.log(LogStatus.INFO, "Validate Print Selected Notes button on Patient View screen");
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='collapseThree']//button[contains(.,'Print Selected Notes')]")));
			Assert.assertTrue(Commons.capturemessage(driver, 30).contains("Please select a record"));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkSelectAll']")));
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='collapseThree']//button[contains(.,'Print Selected Notes')]")));
			Commons.waitforElement(driver, By.xpath("//*[@id='printAllNotes']//h4[text()='Account Notes']"), 30);
			Commons.screenshot();
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='printAllNotes']//*[@id='printContents']/tbody")).getText().contains(Randomstr));

			test.log(LogStatus.INFO, "validate the following fields are displayed on the Confirm Print dialogue box: ");
			Commons.screenshot();
			ActionUtils.click(driver.findElement(By.xpath("//*[@id='printAllNotes']//button[contains(.,'Close')]")));

		} catch (Exception e) {
			test.log(LogStatus.INFO, "***************To Verify User can Add Account Notes from Patient Header also validate functionality of print selected note button PASS*************");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		test.log(LogStatus.INFO, "***************To Verify User can Add Account Notes from Patient Header also validate functionality of print selected note button PASS*************");
	}





























}
