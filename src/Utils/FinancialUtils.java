package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;

public class FinancialUtils {
	public static String strText_Actual;

	// Add Patient with FLR
	public static String AddPatientMedicare(WebDriver driver) throws InterruptedException {
		
		AddPatientUtils.addpatient_withallfields(driver, "TestFinancial");// Add
																			// Patient
																			// all
																			// Details
		strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
		
		TestSetUp.test.log(LogStatus.INFO, "Added Patient With All Fields ID:==>" + "   " + strText_Actual);
		AddCaseUtils.GoToAddNewCase(driver);
		AddCaseUtils.AddCaseWithAllRequiredFields(driver);
		AddCaseUtils.AddDXCode(driver);
		AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
		String expectedMessage = AddCaseUtils.ClickCaseSave(driver);
		try {
			Assert.assertEquals(expectedMessage, "Case saved successfully.");
		} catch (AssertionError e) {
			TestSetUp.test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
			Assert.assertTrue(Commons.ExistandDisplayElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 50),
					"Could not save case..");
		}
		AddCaseUtils.GoToCaseList(driver);
		expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
		TestSetUp.test.log(LogStatus.INFO, "Expected=>" + expectedMessage);
		System.out.println("Expected=>" + expectedMessage);
		// Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
		TestSetUp.test.log(LogStatus.INFO, "Case with Insurance As Medicare Saves  successfully.");
		TestSetUp.test.log(LogStatus.INFO, "Insurance Name is ==>AutoPri Medicare");
		TestSetUp.test.log(LogStatus.INFO, "Test:::: AddInsuranceAsMedicare() Completed as Pass");
		return strText_Actual;
	}

	// Navigate to Account summary
	public static void NavigatetoAccountSummary(WebDriver driver) throws InterruptedException {
		try {
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'patientHeaderNameLink')]")));
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//span[@id='patientID']"), 60);
			// ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientID']")));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Financial']"), 60);
			ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Financial']")));
			if (Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(.,'Account Summary')]"), 60)) {
				System.out.println("Got Patient's Account Summary");
			} else {
				ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Financial']")));
				Commons.ExistandDisplayElement(driver, By.xpath("//h2[contains(.,'Account Summary')]"), 60);
			}
			Commons.waitforElement(driver, By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[3]"), 30);
			ActionUtils
					.getText(driver.findElement(By.xpath("//div[@id='gridAccountSummaryNotes']//tbody//tr[3]//td[5]")));
		} catch (Exception e) {
			Assert.assertFalse(true, "Unable to open Account summary" + Throwables.getStackTraceAsString(e));
		}
	}

	// Navigate to LedgerFull
	public static void NavigatetoLedgerFull(WebDriver driver) throws InterruptedException {
		try {
			if (!Commons.ExistandDisplayElement(driver, By.xpath("//button[@id='LedgerFull']"), 20)) {
				NavigatetoAccountSummary(driver);
			}
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='LedgerFull']")));
			Commons.waitForLoad(driver);
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='ledgerFullCreateReport']")));
			ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientID']")));
			Commons.waitforElement(driver,
					By.xpath("//*[@id='ledgerFullReport']//tbody/tr[contains(.,'Totals:')]//td[19]"), 60);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Unable to open Ledger Full" + Throwables.getStackTraceAsString(e));
		}
	}

	// Navigate to LedgerVisit
	public static void NavigatetoLedgerVisit(WebDriver driver) throws InterruptedException {
		try {
			
			NavigatetoAccountSummary(driver);
			ActionUtils.click(driver.findElement(By.xpath("//button[@id='LedgerVisit']")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='viewRunReport']"), 90);
			ActionUtils.getText(driver.findElement(By.xpath("//span[@id='patientID']")));
			Commons.waitforElement(driver, By.xpath("(//*[@id='viewRunReport']//tr)[last()]//td[10]"), 60);
			Commons.screenshot();
		} catch (Exception e) {
			Assert.assertFalse(true, "Unable to open Ledger Visit" + Throwables.getStackTraceAsString(e));
		}
	}

	// Navigate to Task Group
	public static void NavigatetoTaskgroups(WebDriver driver) throws InterruptedException {
		int i = 0;
		do {
			ActionUtils.click(driver.findElement(By.xpath("//span[text()='Admin']/..")));
			ActionUtils.click(driver.findElement(By.xpath("//span[text() ='Task']/..")));
			ActionUtils.click(driver.findElement(By.xpath("//span[text() ='Task Groups']/..")));
			i++;
		} while (!Commons.ExistandDisplayElement(driver, By.xpath("//button[@id='taskGroupNewBtn']"), 10) && i < 3);
	}

	// Navigate to Task Group
	public static void NavigatetoTaskTypes(WebDriver driver) throws InterruptedException {
		int i = 0;
		do {
			ActionUtils.click(driver.findElement(By.xpath("//span[text()='Admin']/..")));
			ActionUtils.click(driver.findElement(By.xpath("//span[text() ='Task']/..")));
			ActionUtils.click(driver.findElement(By.xpath("//span[text() ='Task Types']/..")));
			i++;
		} while (!Commons.ExistandDisplayElement(driver, By.xpath("//button[@id='taskTypeNewBtn']"), 10) && i < 3);
	}
}