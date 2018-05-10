package UIMap;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;

public class AddPatientPage {
	private static WebElement webelement = null;

	public static WebElement AddPatientMenu(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'Add Patient')]"), 90);
			webelement = driver.findElement(By.xpath("//span[contains(.,'Add Patient')]"));
			System.out.println("Add Patient menu button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Add Patient menu button  not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ViewPatient(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//span[text()='View Patient']/.."), 30);
			webelement = driver.findElement(By.xpath("//span[text()='View Patient']/.."));
			System.out.println("View Patient menu button found");
		} catch (NoSuchElementException e) {
			System.out.println("View Patient menu button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientMenu(WebDriver driver) {
		webelement = null;
		try {
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("tabletchrome")) {
				Commons.waitforElement(driver, By.xpath("//*[@id='menu-toggle']/i"), 90);
				driver.findElement(By.xpath("//*[@id='menu-toggle']/i")).click();
				Commons.Explicitwait();
				Commons.Explicitwait();
			}
			{
				Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient']"), 150);
				webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient']"));
				System.out.println("Patient side menu found");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Patient Side menu NOT found");
			// Commons.ScreenPrint(driver, "AddPatient", "patient_menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static WebElement patientQuicksearchbox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//input[@aria-label='Patient Search']"), 300);
			webelement = driver.findElement(By.xpath("//input[@aria-label='Patient Search']"));
			System.out.println("Quick Search button found");
		} catch (NoSuchElementException e) {
			System.out.println("Quick Search button found");
			// Commons.ScreenPrint(driver, "AddPatient",
			// "patientQuicksearchbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patientSearchSubmenu(WebDriver driver) /// WHY
																	/// ?????????????????????
																	/// and what
																	/// is this
	{
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(@href,'patient-search')]"), 300);
			webelement = driver.findElement(By.xpath("//a[contains(@href,'patient-search')]"));
			System.out.println("Patient search sub menu found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient search sub menu NOT found");
			// Commons.ScreenPrint(driver, "AddPatient", "patient_search_btn");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patient_search__green_btn(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='page-content-wrapper']/div/div[1]/div/div[2]/button[1]"),
					300);
			webelement = driver
					.findElement(By.xpath("//*[@id='page-content-wrapper']/div/div[1]/div/div[2]/button[1]"));
			System.out.println("Search button found");
		} catch (NoSuchElementException e) {
			System.out.println("Search button NOT found");
			// Commons.ScreenPrint(driver, "AddPatient",
			// "patient_search__green_btn");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	// ****************************************************************************--------*********************************************
	public static WebElement patient_search_box(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//input[@id='input-0']"), 300);
			webelement = driver.findElement(By.id("//input[@id='input-0']"));
			System.out.println("Patient search box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient search box Not found");
			// Commons.ScreenPrint(driver, "AddPatient", "patient_search_box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patient_search_firstname(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@ng-model='vmPatientSearch.FirstName']"), 300);
			webelement = driver.findElement(By.xpath("//*[@ng-model='vmPatientSearch.FirstName']"));
			System.out.println("Patient search first name box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient search first name box Not found");
			// Commons.ScreenPrint(driver, "AddPatient",
			// "patient_search_firstname");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patient_search_lastname(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@ng-model='vmPatientSearch.LastName']"), 300);
			webelement = driver.findElement(By.xpath("//*[@ng-model='vmPatientSearch.LastName']"));
			System.out.println("Patient search last name box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient search last name box Not found");
			// Commons.ScreenPrint(driver, "AddPatient",
			// "patient_search_lastname");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patient_search_ID(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@ng-model='vmPatientSearch.patientViewId']"), 300);
			webelement = driver.findElement(By.xpath("//*[@ng-model='vmPatientSearch.patientViewId']"));
			System.out.println("Patient ID search box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient ID search box Not found");
			// Commons.ScreenPrint(driver, "AddPatient", "patient_search_ID");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patient_search_SSN(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.id("patientSearchssn"), 300);
			webelement = driver.findElement(By.id("patientSearchssn"));
			System.out.println("Patient SSN search box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient SSN search box Not found");
			// Commons.ScreenPrint(driver, "AddPatient", "patient_search_SSN");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patient_search_advance(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(.,'Advanced Search')]"), 60);
			webelement = driver.findElement(By.xpath("//a[contains(.,'Advanced Search')]"));
			System.out.println("Patient advance search button found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient advance search button not found");
			// Commons.ScreenPrint(driver, "AddPatient",
			// "patient_search_advance");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement firstname(WebDriver driver) throws InterruptedException {
		webelement = null;
		ActionUtils.click(AddPatientPage.gender(driver));
		try {
			Commons.waitForLoad(driver);
			Commons.Explicitwait();
			Commons.scrollElementinotView(driver.findElement(By.id("patientRegistrationFirstName")), driver);
			Commons.waitforElement(driver, By.id("patientRegistrationFirstName"), 30);
			webelement = driver.findElement(By.id("patientRegistrationFirstName"));
			System.out.println("Patient first name text box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient first name not found");
			// Commons.ScreenPrint(driver, "AddPatient", "firstname");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement emr_firstname(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("emergencyFirstname"), 300);
			webelement = driver.findElement(By.name("emergencyFirstname"));
			System.out.println("Patient emergency first name text box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient emergency first name not found");
			// Commons.ScreenPrint(driver, "AddPatient", "emr_firstname");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement emr_lastname(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("emergencyLastname"), 300);
			webelement = driver.findElement(By.name("emergencyLastname"));
			System.out.println("Patient emergency first name text box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient emergency first name not found");
			// Commons.ScreenPrint(driver, "AddPatient", "emr_lastname");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement emr_middlename(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("emergencyMiddleName"), 300);
			webelement = driver.findElement(By.name("emergencyMiddleName"));
			System.out.println("Patient emergency middle name text box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient emergency middle name not found");
			// Commons.ScreenPrint(driver, "AddPatient", "emr_middlename");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement emr_cell(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("emergencyCell"), 300);
			webelement = driver.findElement(By.name("emergencyCell"));
			System.out.println("Patient emergency cell text box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient emergency cell not found");
			// Commons.ScreenPrint(driver, "AddPatient", "emr_cell");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicareCapAddRow(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='MedicareCap']//button[@class='btn btn-primary btnGreen']"), 300);
			webelement = driver
					.findElement(By.xpath("//div[@id='MedicareCap']//button[@class='btn btn-primary btnGreen']"));
			System.out.println("Medicare Cap Add row button found");
		} catch (NoSuchElementException e) {
			System.out.println("Medicare Cap Add row button not found");
			// Commons.ScreenPrint(driver, "AddPatient", "add_row");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicareYearTextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.scrollElementinotView(driver.findElement(By.xpath("//input[@id='medYear0']")), driver);
			Commons.waitforElement(driver, By.xpath("//input[@id='medYear0']"), 60);
			webelement = driver.findElement(By.xpath("//input[@id='medYear0']"));
			System.out.println("Medicare year field found");
		} catch (NoSuchElementException e) {
			System.out.println("Medicare year field not found");
			// Commons.ScreenPrint(driver, "AddPatient", "med_year");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicareAmountTextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.id("medAmount0"), 300);
			webelement = driver.findElement(By.id("medAmount0"));
			System.out.println("Medicare amount field found");
		} catch (NoSuchElementException e) {
			System.out.println("Medicare amount field not found");
			// Commons.ScreenPrint(driver, "AddPatient", "med_amount");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicareSpecialityPTSLP(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//select[@id='medSpType0']/option[1]"), 300);
			webelement = driver.findElement(By.xpath("//select[@id='medSpType0']/option[1]"));
			System.out.println("Medicare type field found");
		} catch (NoSuchElementException e) {
			System.out.println("Medicare type field not found");
			// Commons.ScreenPrint(driver, "AddPatient", "med_type");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement lastname(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.id("patientLastName"), 300);
			webelement = driver.findElement(By.id("patientLastName"));
			System.out.println("Patient last name text box found");
		} catch (NoSuchElementException e) {
			System.out.println("Patient Last name text box NOT found");
			// Commons.ScreenPrint(driver, "AddPatient", "lastname");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement phone_home(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.id("homeNumber"), 300);
			webelement = driver.findElement(By.id("homeNumber"));
			System.out.println("Entered Phone number(Home)");
		} catch (NoSuchElementException e) {
			System.out.println("Home number NOT found");
			// Commons.ScreenPrint(driver, "AddPatient", "phone_home");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement gender(WebDriver driver) throws InterruptedException {
		webelement = null;
		try {
			int i = 0;
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//select[@id='patientGender']"), 250);
			Commons.scrollElementinotView(driver.findElement(By.xpath("//select[@id='patientGender']")), driver);
			System.out.println("Checking dropdowns are loading in app ?");
			Commons.ExistandDisplayElement(driver, By.xpath("//select[@id='patientGender']/option[1]"), 20);
			while (!Commons.existsElement(driver, By.xpath("//select[@id='patientGender']/option[1]")) && i < 4) {
				System.out.println(" Gender dropdown data not loaded on Add Patient Page");
				if (Commons.existsElement(driver, By.xpath("//select[@id='patientGender']/option[1]"))) {
				} else {
					// Smoetimes server gives intenal error 500 and drop downs
					// stop display to fix that issue
					driver.getCurrentUrl();
					driver.get(driver.getCurrentUrl());
					Commons.waitForLoad(driver);
					System.out.println(
							"***********************Refreshing Page as Dropdowns not loading*****************");
					ActionUtils.click(AddPatientPage.PatientMenu(driver));
					ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
					Commons.waitForElementToBeVisible(driver,
							driver.findElement(By.xpath("//select[@id='patientGender']")), 80);
					Commons.ExistandDisplayElement(driver, By.xpath("//select[@id='patientGender']/option[1]"), 20);
				}
				i++;
			}
			System.out.println("Found Gender Dropdown");
			Commons.waitforElement(driver, By.xpath("//select[@id='patientGender']/option[1]"), 80);
			webelement = driver.findElement(By.xpath("//select[@id='patientGender']/option[1]"));
			System.out.println("Entered Patient gender");
		} catch (Exception e) {
			System.out.println("Gender field NOT found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//button[contains(@class,'btn btn-primary btnGreen tab')][@type='button']"), 30);
			webelement = driver
					.findElement(By.xpath("//button[contains(@class,'btn btn-primary btnGreen tab')][@type='button']"));
			System.out.println("Clicked on Save");
		} catch (NoSuchElementException e) {
			System.out.println("Save button not found");
			// Commons.ScreenPrint(driver, "AddPatient", "save_btn");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement dup_save_btn(WebDriver driver) {
		webelement = null;
		try {
			webelement = driver.findElement(
					By.xpath("//div[@id='duplicatePatientPopup']//button[@class='btn btn-primary btnGreen']"));
			System.out.println("Duplicate patient Save button found");
		} catch (NoSuchElementException e) {
			System.out.println("Duplicate patient Save button not found");
			// Commons.ScreenPrint(driver, "AddPatient", "dup_save_btn");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement duplicateSelectFirstRecord(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='duplicatePatientPopup']//table//tbody//tr/td[1]"), 300);
			webelement = driver.findElement(By.xpath("//*[@id='duplicatePatientPopup']//table//tbody//tr/td[1]"));
			System.out.println("Clicked on first row in the duplicate list");
		} catch (NoSuchElementException e) {
			System.out.println("Duplicate list not found");
			// Commons.ScreenPrint(driver, "AddPatient",
			// "duplicateSelectFirstRecord");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DOB(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.id("patientDob"), 300);
			webelement = driver.findElement(By.id("patientDob"));
			System.out.println("DOB field found");
		} catch (NoSuchElementException e) {
			System.out.println("DOB field NOT found");
			// Commons.ScreenPrint(driver, "AddPatient", "DOB");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Toast_msg_add_patient(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.id("toast-container"), 300);
			webelement = driver.findElement(By.id("toast-container"));
			System.out.println("Toast message appeared for add patient");
		} catch (NoSuchElementException e) {
			System.out.println("Toast message not found");
			// Commons.ScreenPrint(driver, "AddPatient",
			// "Toast_msg_add_patient");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patient_id(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//span[contains(@class,'id')]"), 300);
			webelement = driver.findElement(By.xpath("//span[contains(@class,'id')]"));
			System.out.println("Found patient id");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient id");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	// Objects for add patient
	public static WebElement mi(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("patientMiddleName"), 300);
			webelement = driver.findElement(By.name("patientMiddleName"));
			System.out.println("Found patient middle name");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture middle name");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement suffix(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("suffix"), 300);
			webelement = driver.findElement(By.name("suffix"));
			System.out.println("Found patient suffix");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient suffix");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement address1(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("patientAddress"), 300);
			webelement = driver.findElement(By.name("patientAddress"));
			System.out.println("Found patient address1");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture address1");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement address2(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("address2"), 300);
			webelement = driver.findElement(By.name("address2"));
			System.out.println("Found patient address2");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient address2");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement country(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("country"), 300);
			webelement = driver.findElement(By.name("country"));
			System.out.println("found country field");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture country field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement city(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("patientCity"), 300);
			webelement = driver.findElement(By.name("patientCity"));
			System.out.println("Found city");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture city");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement state(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//select[@name='state']/option[1]"), 60);
			webelement = driver.findElement(By.xpath("//select[@name='state']/option[1]"));
			System.out.println("Found patient state");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture state");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement zipcode(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.id("zipId"), 300);
			webelement = driver.findElement(By.id("zipId"));
			System.out.println("Found zipcode");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture zipcode");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement email(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//input[contains(@ng-model,'patient.email')]"), 300);
			webelement = driver.findElement(By.xpath("//input[contains(@ng-model,'patient.email')]"));
			System.out.println("Found patient email");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient email");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement reminder(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("reminderType"), 300);
			webelement = driver.findElement(By.name("reminderType"));
			System.out.println("Found patient reminder type");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture remindertype");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ssn(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("patientSsn"), 300);
			webelement = driver.findElement(By.name("patientSsn"));
			System.out.println("Found patient SSN");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient SSN");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement maritial_status(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("maritalStatus"), 300);
			webelement = driver.findElement(By.name("maritalStatus"));
			System.out.println("Found patient maritial status");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture maritial status");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement spokenlanguage(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("spokenLanguage"), 300);
			webelement = driver.findElement(By.name("spokenLanguage"));
			System.out.println("Found patient spokenLanguage ");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient spokenLanguage ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement comment(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("comment"), 300);
			webelement = driver.findElement(By.name("comment"));
			System.out.println("Found patient comment");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient comment");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement alternatepatient_id(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("altPatientId"), 60);
			webelement = driver.findElement(By.name("altPatientId"));
			System.out.println("Found patient alternate id");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient alternate id");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement driver_license(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("driverLicense"), 300);
			webelement = driver.findElement(By.name("driverLicense"));
			System.out.println("Found patient drivingLicense");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture patient  drivingLicense");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement responsibleparty(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='ResponsibleParty']/div[1]/div/div/label/input"), 300);
			webelement = driver.findElement(By.xpath("//*[@id='ResponsibleParty']/div[1]/div/div/label/input"));
			System.out.println("Found checkbox responsible party");
		} catch (NoSuchElementException e) {
			System.out.println("unable to capture checkbox responsible party");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement patientID(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='patientID']"), 280);
			webelement = driver.findElement(By.xpath("//*[@id='patientID']"));
			System.out.println("Fetching Patient ID of current Patient");
		} catch (NoSuchElementException e) {
			System.out.println("Patient ID of current patient NOT found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static List<WebElement> patientPagefieldsWithError(WebDriver driver) {		
		List<WebElement> webelement = driver.findElements(By.xpath("//table[@id='medicareCapTbl']//div[contains(@class,'has-error')]"));
		
		return webelement;
	}
	

	public static WebElement EditButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Edit Patient')]"), 200);
			webelement = driver.findElement(By.xpath("//button[contains(text(),'Edit Patient')]"));
			System.out.println("Found Patient Edit Button of current Patient");
		} catch (NoSuchElementException e) {
			System.out.println("Patient Edit Button of current patient NOT found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientActiveCheckbox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.name("isPatientActive"), 300);
			webelement = driver.findElement(By.name("isPatientActive"));
			System.out.println("Found Patient Active Check Box of current Patient");
		} catch (NoSuchElementException e) {
			System.out.println("Patient Active Check Box of current patient NOT found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientNameInHeader(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//span[@id='patientFirstName']"), 100);
			webelement = driver.findElement(By.xpath("//span[@id='patientFirstName']"));
			System.out.println("Found Patient Name in  header");
		} catch (Exception e) {
			System.out.println("Could not find Patient Name in  header ");
			// Commons.ScreenPrint(driver, "AddPatientPage", "PatientName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Financial(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#Financial']"), 100);
			webelement = driver.findElement(By.xpath("//a[@href='#Financial']"));
			System.out.println("Found Financial in  header");
		} catch (Exception e) {
			System.out.println("Could not find Financial in  header ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
}
