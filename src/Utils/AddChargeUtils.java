package Utils;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddChargesPage;
import UIMap.AddPatientPage;
import UIMap.CasePage;

public class AddChargeUtils extends TestSetUp {
	public static String strText_Actual;
	
	
	

	public static void NavigateToAddChargePage(WebDriver driver) throws InterruptedException {
		Commons.waitForLoad(driver);
		ActionUtils.click(AddChargesPage.TransactionOnMenu(driver));
		ActionUtils.click(AddChargesPage.AddChargesButton(driver));
		Commons.waitForLoad(driver);
		Commons.screenshot();
	}

	public static void NavigateToViewChargePage(WebDriver driver) throws InterruptedException {
		Commons.waitForLoad(driver);
		ActionUtils.click(AddChargesPage.TransactionOnMenu(driver));
		ActionUtils.click(AddChargesPage.ViewChargesButton(driver));
		Commons.waitForLoad(driver);
	}

	public static void NavigateToPaymentPage(WebDriver driver) throws InterruptedException {
		Commons.waitForLoad(driver);
		ActionUtils.click(AddChargesPage.TransactionOnMenu(driver));
		ActionUtils.click(AddChargesPage.PaymentButton(driver));
		Commons.waitForLoad(driver);
		// Assert.assertTrue(driver.getCurrentUrl().contains("payment"));
	}

	public static void NavigateToChargeAuditPage(WebDriver driver) throws InterruptedException {
		Commons.waitForLoad(driver);
		ActionUtils.click(AddChargesPage.TransactionOnMenu(driver));
		ActionUtils.click(AddChargesPage.ChargeAudit(driver));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='btn_ReleaseCharges']"), 50);
	}

	public static String QuickSearchPatient(WebDriver driver, String strSearchdata) throws InterruptedException {
		try {
			System.out.println("Entering search data" + strSearchdata);
			Commons.waitForLoad(driver);
			int i = 1;
			do {
				ActionUtils.JavaScriptclick(AddPatientPage.patientQuicksearchbox(driver));
				AddPatientPage.patientQuicksearchbox(driver).clear();
				ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), strSearchdata);
				Commons.ExistandDisplayElement(driver, By.xpath("//li[@class='ng-scope selected']"), 15);
			} while (!Commons.existsElement(driver, By.xpath("//li[@class='ng-scope selected']")) && i < 10);
			try {
				System.out.println("Got quick search result");
				Commons.waitforElement(driver, By.xpath("//li[@class='ng-scope selected']"), 20);
				Thread.sleep(8000);
				System.out.println("Search result list loaded.....");
				// span[contains(@md-highlight-text,'searchText')]
				// System.out.println(driver.findElements(By.xpath("//span[contains(@md-highlight-text,'searchText')]")).size());
				// int size
				// =driver.findElements(By.xpath("//span[contains(@md-highlight-text,'searchText')]")).size();
				int getsearch = (new Random().nextInt(3)) + 1;
				System.out.println(getsearch);
				WebElement patient = driver
						.findElements(By.xpath("//span[contains(@md-highlight-text,'searchText')]/..")).get(getsearch);
				Commons.scrollElementinotView(patient, driver);
				Commons.waitforElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"), 30);
				Commons.waitForLoad(driver);
				// Thread.sleep(5000);
				System.out.println("Loading Quick search list....." + patient.getText());
				Commons.waitForElementToBeClickable(driver, patient, 30);
				patient.click();
				Commons.Explicitwait();
				System.out.println("Clicked on Patient in search result..");
			} catch (Exception e) {
				System.out.println("Quicksearch not done" + Throwables.getStackTraceAsString(e));
			}
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			System.out.println("Loading Patient Page..");
			Commons.strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Quick search performed successfully" + "Actual ID From App" + Commons.strText_Actual);
		} catch (Exception e) {
			System.out.println("Unable to perform quick search");
			e.printStackTrace();
			Assert.assertFalse(true, "Unable to perform quick search" + Throwables.getStackTraceAsString(e));
		}
		Commons.Explicitwait();
		return Commons.strText_Actual;
	}

	// Method to Add a patient with all details and one complete case
	public static String AddPatientCaseCompleted(WebDriver driver, String PatientName) throws InterruptedException {
		try {
			AddPatientUtils.addpatient_withallfields(driver, PatientName);// Add
																			// Patient
																			// all
																			// Details
			strText_Actual = null;
			strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddChargeUtils.ClickCaseSave(driver);
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 300);
			Assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
					"Could not save case..");
			AddCaseUtils.GoToCaseList(driver);
			String expectedMessage = AddCaseUtils.GetFirstCasePrimaryInsuranceName(driver);
			System.out.println("Expected=>" + expectedMessage);
			Assert.assertTrue(expectedMessage.contains("AutoPri Medicare"));
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return strText_Actual;
	}

	public static void ClickCaseSave(WebDriver driver) {
		if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
			System.out.println("$$In safari block$$");
			try {
				Commons.Explicitwait();
				Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"),
						40);
				ActionUtils.click(
						driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]")));
			} catch (Exception e) {
				System.out.println("Clicked on case save");
			}
		} else {
			ActionUtils.click(CasePage.CaseSaveButton(driver));
		}
		System.out.println("Saving Case..............");
		Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Edit Case')]"), 250);
	}

	public static void PaymentTransactiontype(WebDriver driver, String type) throws InterruptedException {
		try {
			
			TestSetUp t= new TestSetUp();
			
			t.test.log(LogStatus.INFO, "Selecting Transaction Type" + "    " + type);
			Commons.waitforElement(driver, By.xpath("//select[@name='TransactionType']"), 60);
			ActionUtils.click(
					driver.findElement(By.xpath("//select[@name='TransactionType']//Option[@label='" + type + "']")));
			System.out.println("Selected Transaction Type on Payments Page" + type);
		} catch (Exception e) {
			Assert.assertFalse(true,
					"Unable to Select transaction type in Payments" + Throwables.getStackTraceAsString(e));
		}
	}

	// Function yo add CPT code on Chare Details table by giving code value and
	// row no.
	public static void AddCPTCode(WebDriver driver, String CPTCode, int row) throws InterruptedException {
		try {
			
			TestSetUp t= new TestSetUp();
			t.test.log(LogStatus.INFO, "Adding CPT Code" + "    " + CPTCode);
			Commons.waitforElement(driver, By.xpath("//div[@id='tbl_proc']//tr[" + row + "]//input[@type='search']"),
					60);
			ActionUtils.sendKeys(
					driver.findElement(By.xpath("//div[@id='tbl_proc']//tr[" + row + "]//input[@type='search']")),
					CPTCode);
			Commons.ExistandDisplayElement(driver,
					By.xpath("//li[@class='ng-scope selected']//span[contains(text(),'" + CPTCode + "')]"), 12);
			Commons.waitForElementToBeVisible(driver,
					driver.findElement(
							By.xpath("//li[@class='ng-scope selected']//span[contains(text(),'" + CPTCode + "')]")),
					20);
			ActionUtils.click(driver.findElement(
					By.xpath("//li[@class='ng-scope selected']//span[contains(text(),'" + CPTCode + "')]")));
			System.out.println("Added CPT code on Add charges Page");
			Thread.sleep(2000);
		} catch (Exception e) {
			Assert.assertFalse(true, "Unable to Add CPT code on Add charges Page");
		}
	}

	public static void PrimaryInsuranceAddPreauthorization(WebDriver driver, String fromdate, String throughdate,
			String visitcount) throws InterruptedException {
		// ActionUtils.click(CasePage.PrimaryAddPreauthorizationButton(driver));
		ActionUtils.sendKeys(CasePage.PrimaryPreAuthorizationNumber(driver), "12345");// Enter
																						// Primary
																						// Insurance
																						// PreAuth
																						// number
		HandlingCalendars.datepick(driver, CasePage.PrimaryPreAuthorizationFromDate(driver), fromdate);// Enter
																										// Primary
																										// Insurance
																										// PreAuth
																										// From
																										// Date
		// HandlingCalendars.datepick(driver,
		// CasePage.PrimaryPreAuthorizationFromDate(driver),
		// "14/06/2015");//Enter Primary Insurance PreAuth From Date
		// HandlingCalendars.datepick(driver,
		// CasePage.PrimaryPreAuthorizationThroughDate(driver),
		// "14/07/2015");//Enter Primary Insurance PreAuth Through Date
		HandlingCalendars.datepick(driver, CasePage.PrimaryPreAuthorizationThroughDate(driver), throughdate);// Enter
																												// Primary
																												// Insurance
																												// PreAuth
																												// Through
																												// Date
		ActionUtils.sendKeys(CasePage.PrimaryPreAuthorizationVisits(driver), visitcount);// Enter
																							// Primary
																							// Insurance
																							// PreAuth
																							// Visits
	}
}
