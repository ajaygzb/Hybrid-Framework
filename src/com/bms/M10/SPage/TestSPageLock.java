package com.bms.M10.SPage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CreateNotePage;
import UIMap.LoginPage;
import UIMap.SPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;
import Utils.SPageUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestSPageLock extends TestSetUp {
	public String strActualtext;

	@Test(enabled = true, priority = 1)
	public void Lock_functionality_Ok() throws InterruptedException {
		test = extent.startTest("To Validate Lock functionality with Okay selection on S page",
				"Test  Lock functionality with Okay selection on S page" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = SPageUtils.NavigateToSpage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To S Page");
		try {
			test.log(LogStatus.INFO, "Second Browser started and login with diffrent provider");
			System.out.println("Starting New Browser in Firefox...");
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("--start-maximized"); WebDriver driver = new
			 * ChromeDriver(options);
			 */
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(Commons.readPropertyValue("URL"));
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("userPT"));
			ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
			Commons.Explicitwait();
			LoginPage.signin(driver).click();
			Commons.waitForLoad(driver);
			System.out.println("Entering search data" + strActualtext);
			if (strActualtext.isEmpty()) {
				Assert.assertFalse(true, "Did not capture Patient ID from App");
			}
			int i = 0;
			do {
				AddPatientPage.patientQuicksearchbox(driver).click();
				AddPatientPage.patientQuicksearchbox(driver).clear();
				ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), strActualtext);
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
			System.out.println("clicking on okay button of lock");
			Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='lockMessagePopUp']//input[@value='OK']"), 15);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='lockMessagePopUp']//input[@value='OK']")));
			System.out.println("Clicked on OK button to acquire lock");
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@id='lockMessagePopUp']"), 60);
			// driver.get(driver.getCurrentUrl());
			Thread.sleep(10000);
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToSubjective()']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//a[@data-ng-click='redirectToSubjective()']")));
			System.out.println("Clicked on S page link");
			Commons.waitForLoad(driver);
			SPage.PatientReportLoadText(driver).sendKeys("checking editing mode after taking lock");
			SPage.Save(driver).click();
			Commons.waitForLoad(driver);
			driver.quit();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Unable to get lock window pop for Craete note" + Throwables.getStackTraceAsString(e));
		}
		try {
			try {
				SPage.Save(driver).click();
			} catch (Exception e) {
			}
			Commons.waitforElement(driver,
					By.xpath("//*[contains(text(),'You cannot edit while someone else has data locked.')]"), 90);
			Commons.Explicitwait();
			String strText_Actual = driver
					.findElement(
							By.xpath("//*[contains(text(),'You cannot edit while someone else has data locked.')]"))
					.getText();
			System.out.println(strText_Actual);
			Assert.assertTrue(strText_Actual.contains("You cannot edit while someone else has data locked."));
			ActionUtils.click(driver.findElement(By.xpath("//input[@data-ng-click='cancelLockAlert()']")));
			Assert.assertFalse(SPage.PatientReportLoadText(driver).isEnabled());
		} catch (Exception e) {
			System.out.println("You cannot edit while someone else has data locked. does not appear");
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate Note Unlock Completed as Fail");
			Assert.assertFalse(true,
					"Test:::: Validate Note Unlock Completed as Fail" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void Lock_functionality_cancel() throws InterruptedException {
		test = extent.startTest("To Validate Lock functionality with Cancel selection on S page",
				"Test  Lock functionality with Okay selection on S page" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		strActualtext = SPageUtils.NavigateToSpage(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Navigated To S Page");
		try {
			test.log(LogStatus.INFO, "Second Browser started and login with diffrent provider");
			System.out.println("Starting New Browser in Firefox...");
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("--start-maximized"); WebDriver driver = new
			 * ChromeDriver(options);
			 */
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(Commons.readPropertyValue("URL"));
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			ActionUtils.sendKeys(LoginPage.username(driver), Commons.readPropertyValue("userPT"));
			ActionUtils.sendKeys(LoginPage.password(driver), Commons.readPropertyValue("password"));
			Commons.Explicitwait();
			LoginPage.signin(driver).click();
			Commons.waitForLoad(driver);
			System.out.println("Entering search data" + strActualtext);
			if (strActualtext.isEmpty()) {
				Assert.assertFalse(true, "Did not capture Patient ID from App");
			}
			int i = 0;
			do {
				AddPatientPage.patientQuicksearchbox(driver).click();
				AddPatientPage.patientQuicksearchbox(driver).clear();
				ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), strActualtext);
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
			System.out.println("clicking on okay button of lock");
			driver.findElement(By.xpath("//input[@data-ng-click='vmNote.cancelLockConfirm()']")).click();
			Commons.waitforElement(driver,
					By.xpath("//*[contains(text(),'You cannot edit while someone else has data locked.')]"), 90);
			Commons.Explicitwait();
			String strText_Actual = driver
					.findElement(
							By.xpath("//*[contains(text(),'You cannot edit while someone else has data locked.')]"))
					.getText();
			System.out.println(strText_Actual);
			Assert.assertTrue(strText_Actual.contains("You cannot edit while someone else has data locked."));
			/*
			 * driver.findElement(By.xpath(
			 * "//*[contains(text(),'You cannot edit while someone else has data locked.')]"
			 * )).click(); driver.findElement(By.xpath(
			 * "//input[@data-ng-click='cancelLockAlert()']")).click();
			 * APageUtils.click(driver.findElement(By.xpath(
			 * "//a[@data-ng-click='redirectToSubjective()']")));
			 * Assert.assertFalse(SPage.PatientReportLoadText(driver).isEnabled(
			 * ));
			 */
			driver.quit();
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Unable to get lock window pop for Craete note" + Throwables.getStackTraceAsString(e));
		}
		try {
			SPage.PatientReportLoadText(driver).sendKeys("checking editing mode ");
			SPage.Save(driver).click();
			Commons.waitForLoad(driver);
		} catch (Exception e) {
			System.out.println("You cannot edit while someone else has data locked. does not appear");
			test.log(LogStatus.INFO, "Assertion Error!!!" + e);
			test.log(LogStatus.INFO, "Test:::: Validate Note Unlock Completed as Fail");
			Assert.assertFalse(true,
					"Test:::: Validate Note Unlock Completed as Fail" + Throwables.getStackTraceAsString(e));
		}
	}
}
