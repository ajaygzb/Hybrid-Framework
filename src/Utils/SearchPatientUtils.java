package Utils;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import UIMap.AddPatientPage;

public class SearchPatientUtils {
	public static String QuickpatientSearch(WebDriver driver, String strSearchdata){
		try {
			if(!Strings.isNullOrEmpty(strSearchdata)){
            ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
			System.out.println("Clicked on patient search button");
			System.out.println("Entering search data" + strSearchdata);
			int i = 1;
			do {
				ActionUtils.JavaScriptclick(AddPatientPage.patientQuicksearchbox(driver));
				AddPatientPage.patientQuicksearchbox(driver).clear();
				ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), strSearchdata);
				System.out.println("Waiting for results to load....");
				Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
						10);
				i++;
			} while (!Commons.existsElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"))
					&& i < 10);
			try {
				System.out.println("Got quick search result");
				ActionUtils.click(driver.findElement(By.xpath("//span[contains(@md-highlight-text,'searchText')]")));
				System.out.println("Clicked on Search result");
				Commons.Explicitwait();
			} catch (Exception e) {
				System.out.println("Quicksearch not done");
			}
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			Commons.strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Quick search performed successfully" + "Actual ID From App" + Commons.strText_Actual);
			}
		} catch (NoSuchElementException e) {
			System.out.println("Unable to perform quick search");
			e.printStackTrace();
			Assert.assertFalse(true, "Unable to perform quick search" + Throwables.getStackTraceAsString(e));
		}
		
		return Commons.strText_Actual;
		
	}

	public static String patientSearchByFirstname(WebDriver driver, String firstname) {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
			System.out.println("Clicked on patient search button");
			ActionUtils.sendKeys(AddPatientPage.patient_search_firstname(driver), firstname);
			ActionUtils.click(AddPatientPage.patient_search__green_btn(driver));
			Commons.waitforElement(driver, By.xpath("//b[contains(.,'Search Results')]"), 30);
			Commons.strText_Actual = driver
					.findElements(By
							.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//span[@ng-bind='dataItem.name.firstName']"))
					.get(0).getText().toLowerCase();
			System.out.println("Firstname in Application " + "   " + Commons.strText_Actual);
			System.out.println("Search by First name performed successfully");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to perform search by First Name");
			e.printStackTrace();
		}
		return Commons.strText_Actual;
	}

	public static String SearchRandomPatient(WebDriver driver, String strSearchdata) {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
			System.out.println("Clicked on patient search button");
			ActionUtils.sendKeys(AddPatientPage.patient_search_firstname(driver), strSearchdata);
			ActionUtils.click(AddPatientPage.patient_search__green_btn(driver));
			Commons.waitforElement(driver, By.xpath("//b[contains(.,'Search Results')]"), 80);
			System.out.println("Loaded search results");
			int result = driver
					.findElements(By
							.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//span[@ng-bind='dataItem.name.firstName']"))
					.size();
			ActionUtils.doubleClick(driver,
					driver.findElements(By
							.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//span[@ng-bind='dataItem.name.firstName']"))
							.get(new Random().nextInt(result - 1) + 1));
			Commons.waitForLoad(driver);
			Commons.strText_Actual = ActionUtils.getText(AddPatientPage.patientID(driver));
			System.out.println("Searched Random patient successfully with ID:=>" + "   "
					+ ActionUtils.getText(AddPatientPage.patientID(driver)));
		} catch (NoSuchElementException e) {
			System.out.println("Unable to perform search by First Name");
			e.printStackTrace();
		}
		return Commons.strText_Actual;
	}

	public static String patientSearch_byLastname(WebDriver driver) {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
			System.out.println("Clicked on patient search button");
			AddPatientPage.patient_search_firstname(driver).clear();
			ActionUtils.sendKeys(AddPatientPage.patient_search_lastname(driver), AddPatientUtils.strLastNametemp);
			ActionUtils.click(AddPatientPage.patient_search__green_btn(driver));
			Commons.waitforElement(driver, By.xpath("//b[contains(.,'Search Results')]"), 30);
			Commons.strText_Actual = driver
					.findElement(By
							.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//span[@ng-bind='dataItem.name.lastName']"))
					.getText();
			System.out.println("lastname in Application " + "   " + Commons.strText_Actual);
			System.out.println("Search by Last name performed successfully");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to perform search by Last name");
			e.printStackTrace();
		}
		return Commons.strText_Actual;
	}

	public static String patientSearch_byPatientID(WebDriver driver, String ID) {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
			System.out.println("Clicked on patient search button");
			AddPatientPage.patient_search_lastname(driver).clear();
			ActionUtils.sendKeys(AddPatientPage.patient_search_ID(driver), ID); /// ???????????????????
																				/// what
																				/// will
																				/// be
																				/// Patient
																				/// ID
			ActionUtils.click(AddPatientPage.patient_search__green_btn(driver));
			Commons.waitforElement(driver, By.xpath("//b[contains(.,'Search Results')]"), 30);
			Commons.strText_Actual = driver
					.findElement(By
							.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//span[@ng-bind='dataItem.id']"))
					.getText();
			System.out.println("ID in Application " + "   " + Commons.strText_Actual);
			System.out.println("Search by ID performed successfully");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to perform search by ID");
			e.printStackTrace();
		}
		return Commons.strText_Actual;
	}

	public static String patientSearch_bySSN(WebDriver driver) {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.patientSearchSubmenu(driver));
			System.out.println("Clicked on patient search button");
			ActionUtils.click(AddPatientPage.patient_search_advance(driver));
			System.out.println("Clicked on patient advance search button");
			AddPatientPage.patient_search_ID(driver).clear();
			ActionUtils.sendKeys(AddPatientPage.patient_search_SSN(driver), (AddPatientUtils.strSSN));
			ActionUtils.click(AddPatientPage.patient_search__green_btn(driver));
			Commons.waitforElement(driver, By.xpath("//b[contains(.,'Search Results')]"), 30);
			Commons.strText_Actual = driver.findElement(By.xpath("//*[@id='gridSearch']//tbody//tr[1]//td[7]"))
					.getText();
			System.out.println("SSN in Application " + "   " + Commons.strText_Actual);
			System.out.println("Search by SSN performed successfully");
		} catch (Exception e) {
			System.out.println("Unable to perform search by SSN");
			Assert.assertFalse(true, "Unable to perform search by SSN" + Throwables.getStackTraceAsString(e));
		}
		return Commons.strText_Actual;
	}
	
	public static Boolean patientSearch_byPhone(WebDriver driver) {
		Boolean status = null;
		try {
			
			driver.findElement(By.xpath("//input[@id='patientSearchPhone']")).sendKeys("(716)845-7459");
			ActionUtils.click(AddPatientPage.patient_search__green_btn(driver));
			Commons.waitforElement(driver, By.xpath("//b[contains(.,'Search Results')]"), 30);
			status = driver.findElement(By.xpath("//*[@id='gridSearch']//tbody")).isDisplayed();
			System.out.println("Search by Phone Number performed successfully");
		} catch (Exception e) {
			System.out.println("Unable to perform search by Phone Number");
			Assert.assertFalse(true, "Unable to perform search by Phone Number" + Throwables.getStackTraceAsString(e));
		}
		return status;
	}
	
	
	
}
