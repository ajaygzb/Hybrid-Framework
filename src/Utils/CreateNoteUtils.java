package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import UIMap.CreateNotePage;
import com.google.common.base.Throwables;

public class CreateNoteUtils {
	public String PatientID;

	public String AddPatientCompleteCase(WebDriver driver, String firstname) {
		try {
			AddPatientUtils.addpatient_withallfields(driver, "PatientEMR");// Add
																			// Patient
																			// all
																			// Details
			AddCaseUtils.GoToAddNewCase(driver);
			AddCaseUtils.AddCaseWithAllFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceAsMedicare(driver);
			AddCaseUtils.ClickCaseSave(driver);
			AddCaseUtils.GoToCaseList(driver);
			Assert.assertEquals(AddCaseUtils.GetFirstCaseStatus(driver), "Complete");
			PatientID = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Patient ID from APP==>" + PatientID);
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return PatientID;
	}

	/* This Function is use to Create Note From EMR */
	public static String SearchPatientComplete(WebDriver driver, String firstname) {
		try {
			// search a patient with no Notes created
			int i = 0;
			do {
				Commons.waitForLoad(driver);
				ActionUtils.click(AddPatientPage.PatientMenu(driver));
				Commons.waitForLoad(driver);
				ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
				System.out.println("Clicked on patient search button");
				Commons.waitForLoad(driver);
				ActionUtils.sendKeys(AddPatientPage.patient_search_firstname(driver), firstname);
				ActionUtils.click(CasePage.SearchButtonOnAdvanceSearchPage(driver));
				Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//span[@class='k-icon k-i-seek-e']"),
						30);
				ActionUtils.click(
						driver.findElement(By.xpath("//div[@id='gridSearch']//span[@class='k-icon k-i-seek-e']")));
				Commons.Explicitwait();
				if (i >= 2) {
					Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Previous')]/.."), 10);
					driver.findElement(By.xpath("//span[contains(text(),'Previous')]/..")).click();
					Commons.Explicitwait();
					System.out.println("Clicked on Previous button");
				}
				ActionUtils.doubleClick(driver, CasePage.GetRandomPatientFromAdvanceSearchPage(driver));
				Commons.Explicitwait();
				System.out.println("Getting a patient with no Notes created");
				Commons.waitforElement(driver, By.xpath("//a[@class='page-scroll'][contains(.,'Medical Record')]"), 30);
				ActionUtils
						.click(driver.findElement(By.xpath("//a[@class='page-scroll'][contains(.,'Medical Record')]")));
				Commons.Explicitwait();
				Commons.Explicitwait();
				i++;
			} while (!Commons.existsElement(driver,
					By.xpath(
							"//div[@id='MedicalRecord']//span[contains(text(),'No records found')][contains(@*,'Note')]"))
					&& i < 20);
			Commons.strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Patient ID in Application " + "   " + Commons.strText_Actual);
			System.out.println("Search by First name performed successfully");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to perform search by First Name");
			e.printStackTrace();
		}
		return Commons.strText_Actual;
	}

	public static String NavigatetoCreateNote(WebDriver driver) throws InterruptedException {
		try {
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
			System.out.println("Opened Create Note Screen");
			Commons.waitForLoad(driver);
			ActionUtils.click(CreateNotePage.CaseNameddl(driver));
			// Thread.sleep(1000);
			Commons.waitForLoad(driver);
			// ActionUtils.click(driver.findElement(By.xpath("//select[@name='ddlVisitType']")));
			if (Commons.ExistandDisplayElement(driver,
					By.xpath("//select[@name='ddlVisitType']//option[@label='Evaluation/Transfer']"), 15)) {
				CreateNotePage.VisitTypeddl(driver, "Evaluation/Transfer");
			} else {
				CreateNotePage.VisitTypeddl(driver, "Plan of Care Update");
			}
			System.out.println("Entering Location Field on create Note screen");
			// Thread.sleep(1000);
			ActionUtils.click(CasePage.LocationMagnifier(driver));
			ActionUtils.click(CasePage.LocationSearchButton(driver));
			ActionUtils.doubleClick(driver, CasePage.LocationList(driver));
		} catch (NoSuchElementException e) {
			System.out.println("Unable to Open Create Note Page");
			e.printStackTrace();
			Assert.assertFalse(true, "Create Page Note Open" + Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}

	public static void clikOnAPageOnSOAP(WebDriver driver) {
		try {
			Commons.waitForLoad(driver);
			APageUtils.click(driver, "clickAPAge");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to Click on A Page on SOAP header");
			e.printStackTrace();
			Assert.assertFalse(true, "A Page Not Open" + Throwables.getStackTraceAsString(e));
		}
	}
}