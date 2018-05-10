package com.bms.M7.APage;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import UIMap.LoginPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;

public class TestAPageLockFunctionality extends TestSetUp {
	@Test(enabled = true, priority = 1)
	public void Note_UnlockApage() throws InterruptedException, AWTException {
		test = extent.startTest(
				"To Validate Note is Unlock - subsequent note on case - where previous note created in R5",
				"Note is Unlock" + "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
						+ caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.NavigateToApage(driver);
		String PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Got Patient ID from App :==>" + PatientID);
		Commons.Explicitwait();
		try {
			// if(TestSetUp.CurrentBrowserName=="chrome"){
			test.log(LogStatus.INFO, "Second Browser started and login with diffrent provider");
			System.out.println("Starting New Browser in Firefox...");
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(Commons.readPropertyValue("URL"));
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("userPT"));
			ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
			Commons.Explicitwait();
			LoginPage.signin(driver).click();
			Commons.waitForLoad(driver);
			System.out.println("Entering search data" + PatientID);
			if (PatientID.isEmpty()) {
				Assert.assertFalse(true, "Did not capture Patient ID from App");
			}
			int i = 0;
			do {
				AddPatientPage.patientQuicksearchbox(driver).click();
				AddPatientPage.patientQuicksearchbox(driver).clear();
				ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), PatientID);
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
						10);
				i++;
			} while (!Commons.existsElement(driver, By.xpath("//li[@class='ng-scope selected']")) && i < 6);
			try {
				System.out.println("quick Search result found");
				Commons.waitforElement(driver, By.xpath("//li[@class='ng-scope selected']"), 20);
				driver.findElement(By.xpath("//li[@class='ng-scope selected']")).click();
				Commons.Explicitwait();
			} catch (Exception e) {
				System.out.println("Quicksearch not done");
				Assert.assertTrue(false, Throwables.getStackTraceAsString(e));
			}
			System.out.println("Quicksearch done");
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath("//div[@class='row btnsection']//a[contains(text(),'Medical Record')]"), 100);
			Commons.waitForElementToBeClickable(driver, CreateNotePage.MedicalRecordlink(driver), 20);
			// Thread.sleep(5000);
			CreateNotePage.MedicalRecordlink(driver).click();
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			System.out.println("Scrolling to Medical record + sign in page..");
			Commons.scrollElementinotView(CreateNotePage.MedicalRecordExpandlink(driver), driver);
			CreateNotePage.MedicalRecordExpandlink(driver).click();
			Commons.waitForLoad(driver);
			Commons.scrollElementinotView(
					driver.findElement(
							By.xpath("//div[@k-options='vmPatient.clinicalNoteInfoGridOptions']/table/tbody/tr[2][1]")),
					driver);
			Commons.Explicitwait();
			// ActionUtils.doubleClick(driver,
			// driver.findElement(By.xpath("//div[@k-options='vmPatient.clinicalNoteInfoGridOptions']/table/tbody/tr[2][1]")));
			new Actions(driver)
					.doubleClick(driver.findElement(
							By.xpath("//div[@k-options='vmPatient.clinicalNoteInfoGridOptions']/table/tbody/tr[2][1]")))
					.perform();
			System.out.println("Double Clicked on WebElement");
			Commons.waitforElement(driver, By.xpath("//*[contains(text(),'Someone else already has the data locked')]"),
					40);
			Commons.Explicitwait();
			Commons.strText_Actual = ActionUtils.getText(
					driver.findElement(By.xpath("//*[contains(text(),'Someone else already has the data locked')]")));
			driver.close();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Unable to get lock window pop for Craete note" + Throwables.getStackTraceAsString(e));
		}
		try {
			Assert.assertTrue(Commons.strText_Actual.contains("Someone else already has the data locked"));
			System.out.println("Confirm pop up appeared");
			test.log(LogStatus.INFO, "Assertion Pass!!!");
			test.log(LogStatus.INFO, Commons.strText_Actual);
		} catch (Exception e) {
			System.out.println("Confirm pop up not appeared");
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate Note Unlock Completed as Fail");
			Assert.assertFalse(true,
					"Test:::: Validate Note Unlock Completed as Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
