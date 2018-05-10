package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.google.common.base.Throwables;
import UIMap.AddPatientPage;

// **********************This function is to add patient with mandatory
// fields only*************************************//
public class AddPatientUtils {
	public static int x = new Random().nextInt(1000000);
	public static String strFirstNametemp = "AjayQA" + x;
	public static String strLastNametemp = "Automate" + new Random().nextInt(1000);
	public static String strPhonetemp = "5326848252";
	public static String strGendertemp = "Male";
	public static String strDOBtemp = "11112000";
	public static String strSSN = "123456789";
	public static String patientIdfromApp = null;
	public static String patientID;
	public static String str;
	public static   List<String> data = new ArrayList<String>();
	public static String strFirstNameCancel = "CancelQA" + x;

	// **********************This function is to add patient with mandatory fields only*************************************//
	public static String QuickAddpatient(WebDriver driver) throws InterruptedException {    
		String Addpatienttoaster ="";
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
			System.out.println("Opened add patient page");
		} catch (Exception e) {
			System.out.println("Unable to open add patient page");
			Assert.assertFalse(true,"Unable to open add patient page"+Throwables.getStackTraceAsString(e));
		}

		try{
			List<String> exceldata = Commons.ReadExceldata(0);
			Commons.waitforElement(driver, By.id("patientRegistrationFirstName"), 30);
			ActionUtils.click(driver.findElement(By.id("patientRegistrationFirstName")));
			ActionUtils.sendKeys(AddPatientPage.firstname(driver),exceldata.get(0));
			ActionUtils.sendKeys(AddPatientPage.lastname(driver), (exceldata.get(1) + new Random().nextInt(10000000)));
			ActionUtils.sendKeys(AddPatientPage.phone_home(driver),exceldata.get(2));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@class='form-error ng-scope']"), 90);
			String DOB = exceldata.get(3).toString();
			HandlingCalendars.datepick(driver,
			By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"), DOB);
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			Addpatienttoaster = Commons.capturemessage(driver,20);
			if (Addpatienttoaster==null &&
		    driver.findElement(By.xpath("//div[@id='duplicatePatientPopup']//button[@class='btn btn-primary btnGreen']")).isDisplayed()){
			System.out.println("Duplicate Patient Pop up displayed");
			Commons.screenshot();
			AddPatientPage.dup_save_btn(driver).click();
			Addpatienttoaster = Commons.capturemessage(driver, 30);
			}
			return Addpatienttoaster;
		}catch(Exception e){

			System.out.println("Unable to get Add patient toaster  "+Throwables.getStackTraceAsString(e));
			Commons.screenshot();
			Assert.assertFalse(ActionUtils.getText(AddPatientPage.patientID(driver)).isEmpty(),"Unable to get Add patient toaster  "+Throwables.getStackTraceAsString(e));
		}
			System.out.println("Patient Add steps completed");
			return Addpatienttoaster;		
	}		
		
		
	
	//************************New ***************************
	// This function is to Add patient with all fields including responsible
		// party details and emergency contact.
	    public static String addpatient_withallfields(WebDriver driver, String PatientName) throws InterruptedException {
		List<String> Exceldata=Commons.ReadExceldata(1);
		if (PatientName.isEmpty()) {
			Commons.strfirstname = Exceldata.get(0);
		} else {
			Commons.strfirstname = PatientName;
		}

		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
			System.out.println("Opened add patient page");
		} catch (Exception e) {
			System.out.println("Unable to open add patient page");
			Assert.assertFalse(true,"Unable to open add patient page"+Throwables.getStackTraceAsString(e));
		}
				
	          String Addpatienttoaster = "";
	
			try {
				ActionUtils.sendKeys(AddPatientPage.firstname(driver),Commons.strfirstname);
				ActionUtils.sendKeys(AddPatientPage.lastname(driver),
				Exceldata.get(1) + new Random().nextInt(1000000000));
				ActionUtils.sendKeys(AddPatientPage.phone_home(driver),Exceldata.get(2));
				// ActionUtils.click(AddPatientPage.gender(driver));
				ActionUtils.sendKeys(AddPatientPage.zipcode(driver),Exceldata.get(12));
				Commons.scrollElementinotView(AddPatientPage.comment(driver), driver);
				Commons.ExistandDisplayElement(driver,
				By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"), 40);
				HandlingCalendars.datepick(driver,
				By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"),
				Exceldata.get(3));
				ActionUtils.sendKeys(AddPatientPage.mi(driver),Exceldata.get(5));
				ActionUtils.sendKeys(AddPatientPage.suffix(driver),Exceldata.get(6));
				ActionUtils.sendKeys(AddPatientPage.address1(driver),Exceldata.get(7) + new Random().nextInt(9999999) +"Automation Data");
				ActionUtils.sendKeys(AddPatientPage.address2(driver),
		        Exceldata.get(8) + new Random().nextInt(9999999) +"Automation Data2");
				ActionUtils.sendKeys(AddPatientPage.country(driver),Exceldata.get(9));
				ActionUtils.sendKeys(AddPatientPage.city(driver),Exceldata.get(10));
				ActionUtils.click(AddPatientPage.state(driver));
				ActionUtils.sendKeys(AddPatientPage.email(driver),Exceldata.get(13));
				ActionUtils.sendKeys(AddPatientPage.reminder(driver),Exceldata.get(14));
				ActionUtils.sendKeys(AddPatientPage.ssn(driver),Exceldata.get(15));
				ActionUtils.sendKeys(AddPatientPage.maritial_status(driver),Exceldata.get(16));
				ActionUtils.sendKeys(AddPatientPage.driver_license(driver),Exceldata.get(17));
				ActionUtils.sendKeys(AddPatientPage.spokenlanguage(driver),Exceldata.get(18));
				ActionUtils.sendKeys(AddPatientPage.comment(driver),Exceldata.get(19));
				ActionUtils.click(AddPatientPage.responsibleparty(driver));
				// Emergency contact fields
				ActionUtils.sendKeys(AddPatientPage.emr_firstname(driver), Commons.strfirstname);
				ActionUtils.sendKeys(AddPatientPage.emr_lastname(driver),Exceldata.get(2));
				ActionUtils.sendKeys(AddPatientPage.emr_middlename(driver),Exceldata.get(5));
				ActionUtils.sendKeys(AddPatientPage.emr_cell(driver),Exceldata.get(2));
				
				// HandlingCalendars.datepick(driver, By.xpath(
				 //"//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"),Exceldata.get(3));
				 
				System.out.println("clicked on same as patient");
				ActionUtils.sendKeys(AddPatientPage.alternatepatient_id(driver),"123456789");
				AddPatientPage.MedicareYearTextBox(driver).click();
				ActionUtils.sendKeys(AddPatientPage.MedicareYearTextBox(driver), "2017");
				ActionUtils.sendKeys(AddPatientPage.MedicareAmountTextBox(driver), "1980");
				ActionUtils.click(AddPatientPage.MedicareSpecialityPTSLP(driver));
				Commons.waitForLoad(driver);
				Commons.waitForElementToBeClickable(driver, AddPatientPage.SaveButton(driver), 10);
				ActionUtils.click(AddPatientPage.SaveButton(driver));
				Addpatienttoaster = Commons.capturemessage(driver,20);
				if (Addpatienttoaster==null &&
			    driver.findElement(By.xpath("//div[@id='duplicatePatientPopup']//button[@class='btn btn-primary btnGreen']")).isDisplayed()){
				System.out.println("Duplicate Patient Pop up displayed");
				Commons.screenshot();
				AddPatientPage.dup_save_btn(driver).click();
				Addpatienttoaster = Commons.capturemessage(driver, 30);
				}
				return Addpatienttoaster;
			}catch(Exception e){

				System.out.println("Unable to get Add patient toaster  "+Throwables.getStackTraceAsString(e));
				Commons.screenshot();
				Assert.assertFalse(ActionUtils.getText(AddPatientPage.patientID(driver)).isEmpty(),"Unable to get Add patient toaster  "+Throwables.getStackTraceAsString(e));
			}
				System.out.println("Patient Add steps completed");
				return Addpatienttoaster;		
		}		
		//***********************NEW*****************
	

	// This function is to verify User can add Duplicate patient.
	public static String AddDuplicatePatient(WebDriver driver) throws InterruptedException {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
			System.out.println("Opened add patient page");
			ActionUtils.sendKeys(AddPatientPage.firstname(driver), AddPatientUtils.strFirstNametemp);
			ActionUtils.sendKeys(AddPatientPage.lastname(driver), AddPatientUtils.strLastNametemp);
			ActionUtils.sendKeys(AddPatientPage.phone_home(driver), AddPatientUtils.strPhonetemp);
			HandlingCalendars.datepick(driver,
			By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"),
			"12/05/1993");
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			if (!Commons.ExistandDisplayElement(driver, By.id("duplicatePatientPopup"), 40)) {
				System.out.println(
						"Patient Added with ID" + "  " + ActionUtils.getText(AddPatientPage.patientID(driver)));
				ActionUtils.click(AddPatientPage.PatientMenu(driver));
				ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
				System.out.println("Opened add patient page");
				ActionUtils.sendKeys(AddPatientPage.firstname(driver), AddPatientUtils.strFirstNametemp);
				ActionUtils.sendKeys(AddPatientPage.lastname(driver), AddPatientUtils.strLastNametemp);
				ActionUtils.sendKeys(AddPatientPage.phone_home(driver), AddPatientUtils.strPhonetemp);
				HandlingCalendars.datepick(driver,
						By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"),
						"12/05/1993");
				ActionUtils.click(AddPatientPage.SaveButton(driver));
			}
			Commons.waitforElement(driver, By.id("duplicatePatientPopup"), 60);
			System.out.println("Found duplicate patient pop up window");
			Commons.strText_Actual = ActionUtils.getText(driver
			.findElement(By.xpath("//div[contains(text(), 'Multiple patient were found with this name')]")));
			ActionUtils.click(AddPatientPage.dup_save_btn(driver));
			System.out.println("Verified duplicate patient add messge==>" + Commons.strText_Actual);
			// //Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			Assert.fail("Add duplicate patient failed");
			e.printStackTrace();
		}
		System.out.println("Duplicate Patient Add  validation is completed");
		return Commons.strText_Actual;
	}

	// This function is to verify all Mandatory fields on Add patient page
	public static List<String> verifyAddPatientFields(WebDriver driver) {
		List<String> strText_Actual= new ArrayList<String>();
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			Commons.strText_Actual = Commons.capturemessage(driver, 30);
			strText_Actual.add(0, Commons.strText_Actual);
			System.out.println("Opened add patient page");
			Commons.waitforElement(driver,
					By.xpath("//div[@class='has-error']//input[@id='patientRegistrationFirstName']"), 10);
			Assert.assertTrue(
			driver.findElement(By.xpath("//div[@class='has-error']//input[@id='patientRegistrationFirstName']"))
			.isEnabled());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='has-error']//input[@id='patientLastName']"))
			.isEnabled());
			Assert.assertTrue(
			driver.findElement(By.xpath("//div[@class='has-error']/input[@id='homeNumber']")).isEnabled());
			Assert.assertTrue(
			driver.findElement(By.xpath("//div[@class='has-error']//input[@id='patientDob']")).isEnabled());
			
			// Verify Labels on Add patient page
			List<String>actuallabels= new ArrayList<String>();
			List<WebElement>labels=driver.findElements(By.xpath("//*[@id='mainContent']//label"));
			for(WebElement w:labels){				
				if(w.isDisplayed()){
					System.out.println(w.getText());
					actuallabels.add(w.getText());
					
				}}
			System.out.println("Total No. Of labels are   "+actuallabels.size());
			Assert.assertEquals(52,actuallabels.size());
			String arr[]={"First Name (required)",
					"MI",
					"Last Name (required)",
					"Suffix",
					"Preferred Name",
					"Address 1 (required for billing)",
					"Address 2",
					"City (required for billing)",
					"Country (required for billing)",
					"State (required for billing)",
					"ZIP Code (required for billing)",
					"Home",
					"Cell",
					"Work",
					"Primary",
					"Email",
					"Reminder Type",
					"Date of Birth (required)",
					"Gender (required)",
					"SSN (required for billing)",
					"Marital Status",
					"Driver's License",
					"Spoken Language",
					"Comment",
					"Alternate Patient ID",
					"First Name",
					"MI",
					"Last Name",
					"Home",
					"Cell",
					"Work",
					"Same as Patient",
					};

			for(int i=0;i<arr.length;i++){
				Assert.assertTrue(arr[i].contains(actuallabels.get(i)));
				
			}
			// verify error messages for indivisuals fields Phone no.,ZIP,SSN,Email
			ActionUtils.sendKeys(AddPatientPage.phone_home(driver),"123");
			Commons.waitforElement(driver, By.xpath("//input[@id='homeNumber']//following-sibling::div[contains(.,'Please enter 10 digits')]"), 5);
			ActionUtils.sendKeys(AddPatientPage.email(driver),"test123");
			Commons.waitforElement(driver, By.xpath("//input[contains(@ng-model,'patient.email')]//following-sibling::div//span[contains(.,'Please enter valid email address')]"), 5);
			ActionUtils.sendKeys(AddPatientPage.ssn(driver),"222");
			Commons.waitforElement(driver, By.xpath("//input[contains(@name,'patientSsn')]//following-sibling::div[contains(.,'You must enter 9 digits')]"), 5);
			ActionUtils.sendKeys(AddPatientPage.zipcode(driver),"1234");
			Commons.waitforElement(driver, By.xpath("//input[@name='patientZipCode']//following-sibling::div//span[contains(.,'Zip can be 5 or 9 digits')]"), 5);
			Commons.screenshot();
			driver.findElement(By.id("patientRegistrationFirstName")).sendKeys("@@___!!");
			ActionUtils.sendKeys(AddPatientPage.lastname(driver), AddPatientUtils.strLastNametemp);
			ActionUtils.sendKeys(AddPatientPage.phone_home(driver), AddPatientUtils.strPhonetemp);
			Commons.SelectElement(driver, By.xpath("//*[@id='patientGender']"),"Male");
			HandlingCalendars.datepick(driver,
					By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"),
					"12/05/1993");
			ActionUtils.sendKeys(AddPatientPage.ssn(driver), String.valueOf(AddPatientUtils.strSSN));
			ActionUtils.sendKeys(AddPatientPage.zipcode(driver),"12345");
			ActionUtils.sendKeys(AddPatientPage.email(driver), "TestAutomation@Test.com");
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			Commons.strText_Actual = Commons.capturemessage(driver, 30);
			strText_Actual.add(1, Commons.strText_Actual);
			
			System.out.println(strText_Actual);

		} catch (NoSuchElementException e) {
			System.out.println("Mandatory fields validation failed");
			Assert.fail("Mandatory fields validation failed");
			e.printStackTrace();
		}
		System.out.println("Patient Add mandatory field validation is completed");
		return strText_Actual;
	}

	// This function is used to add a temporary patient while performing quick
	// search by Patient ID
	public static String QuickAddpatientTemp(WebDriver driver) throws InterruptedException {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
			System.out.println("Opened add patient page");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to open add patient page");
			e.printStackTrace();
		}
		try {
			ActionUtils.sendKeys(AddPatientPage.firstname(driver), AddPatientUtils.strFirstNametemp);
			ActionUtils.sendKeys(AddPatientPage.lastname(driver), AddPatientUtils.strLastNametemp);
			ActionUtils.sendKeys(AddPatientPage.phone_home(driver), AddPatientUtils.strPhonetemp);
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@class='form-error ng-scope']"), 60);
			HandlingCalendars.datepick(driver,
					By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"),
					"12/05/1993");
			ActionUtils.sendKeys(AddPatientPage.ssn(driver), String.valueOf(AddPatientUtils.strSSN));
			ActionUtils.sendKeys(AddPatientPage.alternatepatient_id(driver),"123456789");
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			Commons.Explicitwait();
			String toast = null;
			toast = Commons.capturemessage(driver, 15);
			if (toast == null) {
				System.out.println("Checking for duplicate Patient Pop up...");
				boolean x = Commons.ExistandDisplayElement(driver,
						By.xpath("//div[@id='duplicatePatientPopup']//button[@class='btn btn-primary btnGreen']"), 10);
				if (x == true) {
					System.out.println("Found duplicate Patient Pop up...");
					ActionUtils.click(AddPatientPage.duplicateSelectFirstRecord(driver));
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to Add temporary patient");
			Assert.assertFalse(true, "Unable to Add temp patient" + Throwables.getStackTraceAsString(e));
		}
		Commons.Explicitwait(); // To wait due to sync issue
		patientIdfromApp = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Patient Add steps completed" + "******" + " Got  Patient ID from App" + patientIdfromApp);
		return patientIdfromApp; // Finding Patient ID of registered Patient
	}

	public static String QuickAddpatientWithBlankMedicare(WebDriver driver) throws InterruptedException {
		List<String> exceldata = Commons.ReadExceldata(0);
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
			System.out.println("Opened add patient page");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to open add patient page");
			e.printStackTrace();
		}
		try {
			ActionUtils.sendKeys(AddPatientPage.firstname(driver),exceldata.get(0));
			ActionUtils.sendKeys(AddPatientPage.lastname(driver), (exceldata.get(1) + new Random().nextInt(1000)));
			ActionUtils.sendKeys(AddPatientPage.phone_home(driver), exceldata.get(2));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@class='form-error ng-scope']"), 90);
			// ActionUtils.click(AddPatientPage.gender(driver));
			ActionUtils.click(AddPatientPage.DOB(driver));
			String DOB = exceldata.get(3).toString();
			HandlingCalendars.datepick(driver,
			By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"), DOB);
			System.out.println("Clicked on Add row for medicare cap");
			ActionUtils.sendKeys(AddPatientPage.MedicareAmountTextBox(driver),"100000");
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			Commons.strText_Actual = Commons.capturemessage(driver, 10);
		} catch (NoSuchElementException e) {
			System.out.println("Did not get Required field toast message");
			e.printStackTrace();
		}
		System.out.println("Got required field toast message");
		return Commons.strText_Actual;
	}

	public static int getfieldswithErrorCount(WebDriver driver) {
		return AddPatientPage.patientPagefieldsWithError(driver).size();
	}

	// *******************************this is quick add for customize insurence
	// values *******************************
	public static String QuickAddpatientwithInsurence(WebDriver driver, String lastname) throws InterruptedException {
		try {
			ActionUtils.click(AddPatientPage.PatientMenu(driver));
			ActionUtils.click(AddPatientPage.AddPatientMenu(driver));
			System.out.println("Opened add patient page");
		} catch (NoSuchElementException e) {
			System.out.println("Unable to open add patient page");
			e.printStackTrace();
		}
		try {
			/*
			 * System.out.println(data.indexOf("1001"));
			 * System.out.println(data.indexOf("10013"));
			 * System.out.println(data.indexOf("1026"));
			 * System.out.println(data.indexOf("1141"));
			 * System.out.println(data.indexOf("1043"));
			 */
			// System.out.println(data.get(data.indexOf("1001")));
			ActionUtils.sendKeys(AddPatientPage.firstname(driver), data.get(0));
			ActionUtils.sendKeys(AddPatientPage.lastname(driver), lastname);
			ActionUtils.sendKeys(AddPatientPage.phone_home(driver), data.get(2));
			Commons.waitForElementToBeNotVisible(driver, By.xpath("//div[@class='form-error ng-scope']"), 90);
			// ActionUtils.click(AddPatientPage.gender(driver));
			ActionUtils.click(AddPatientPage.DOB(driver));
			String DOB = data.get(3).toString();
			HandlingCalendars.datepick(driver,
					By.xpath("//*[@id='patientDob']//preceding::button[1][contains(@class,'calenderbutton')]"), DOB);
			ActionUtils.sendKeys(AddPatientPage.city(driver), "Washington");
			ActionUtils.click(AddPatientPage.state(driver));
			ActionUtils.sendKeys(AddPatientPage.zipcode(driver), "123456789");
			ActionUtils.sendKeys(AddPatientPage.address1(driver), "123 Park avenue second California");
			ActionUtils.sendKeys(AddPatientPage.mi(driver), "M");
			ActionUtils.sendKeys(AddPatientPage.suffix(driver), "T");
			ActionUtils.sendKeys(AddPatientPage.address2(driver), "456 Park avenue second California");
			ActionUtils.sendKeys(AddPatientPage.email(driver), "TestAutomation@Test.com");
			ActionUtils.sendKeys(AddPatientPage.ssn(driver), "123456789");
			ActionUtils.sendKeys(AddPatientPage.maritial_status(driver), "Single");
			ActionUtils.sendKeys(AddPatientPage.driver_license(driver), "AutomationTest-458548");
			ActionUtils.click(AddPatientPage.responsibleparty(driver));
			// Emergency contact fields
			ActionUtils.sendKeys(AddPatientPage.emr_firstname(driver), data.get(0));
			ActionUtils.sendKeys(AddPatientPage.emr_lastname(driver), lastname);
			ActionUtils.sendKeys(AddPatientPage.emr_middlename(driver), "M");
			ActionUtils.sendKeys(AddPatientPage.emr_cell(driver), data.get(2));
			Commons.waitForLoad(driver);
			Commons.existsElement(driver,
					By.xpath("//button[contains(@class,'btn btn-primary btnGreen tab')][@type='submit']"));
			// Thread.sleep(2000);
			ActionUtils.click(AddPatientPage.SaveButton(driver));
			try {
				Commons.waitforElement(driver, By.xpath("//div[contains(@class,'toast-message')]"), 5);
				Commons.strText_Actual = driver.findElement(By.xpath("//div[contains(@class,'toast-message')]"))
						.getText();
				System.out.println("Got toast message from app");
			} catch (Exception e) {
				System.out.println(" Checking for Duplicate patient pop up appeared");
				Commons.strText_Actual = null;
			}
			// //Thread.sleep(1000);
			try {
				if (Commons.strText_Actual == null) {
					Commons.waitforElement(driver,
							By.xpath("//div[@id='duplicatePatientPopup']//button[@class='btn btn-primary btnGreen']"),
							10);
					AddPatientPage.dup_save_btn(driver).click();
					Commons.strText_Actual = Commons.capturemessage(driver, 30);
				}
			} catch (Exception e) {
				System.out.println("Duplicate patient button not found");
			}
			try {
				if (Commons.strText_Actual != null) {
					System.out.println("Got toast message for Add patient");
					System.out.println("Toast Mesage==>" + Commons.strText_Actual);
				}
			} catch (Exception e) {
				System.out.println("unable to get toast message for Add patient" + e);
			}
		} catch (NoSuchElementException e) {
			System.out.println("Unable to add patient" + Throwables.getStackTraceAsString(e));
		}
		System.out.println("Patient Add steps completed");
		ActionUtils.getText(AddPatientPage.patientID(driver));
		return Commons.strText_Actual;
	}

	public static List<String> ReadExceldata(WebDriver driver) throws InterruptedException {
		try {
			FileInputStream file = new FileInputStream(
			new File(Commons.projectPath.getAbsolutePath() + "/src/dataRepository/inputdata/BMS.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(2);
			System.out.println("rows" + sheet.getLastRowNum());
			// Find number of rows in excel file
			int rowCount = sheet.getLastRowNum();
			System.out.println("Total rows" + rowCount);
			System.out.print("Getting Patient data from Excel..");
			// Create a loop over all the rows of excel file to read it
			for (int i = 1; i <= rowCount; i++) {
				Row row = sheet.getRow(i);
				// Create a loop to print cell values in a row
				for (int j = 0; j < row.getLastCellNum(); j++) {
					// Print excel data in console
					if (row.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						str = NumberToTextConverter.toText(row.getCell(j).getNumericCellValue());
						// System.out.print(str+"|| ");
					} else {
						str = row.getCell(j).getStringCellValue();
					}
					System.out.print(str + "|| ");
					data.add(str);
				}
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return data;
	}
}
