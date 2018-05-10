package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Utils.Commons;
import com.google.common.base.Throwables;

public class SPage {
	private static WebElement webelement = null;

	public static WebElement PatientReportTextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='patientReportComment']"), 90);
			webelement = driver.findElement(By.xpath("//*[@id='patientReportComment']"));
			System.out.println("S page Patient Report Text Box");
		} catch (NoSuchElementException e) {
			System.out.println("S page Patient Report Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientReportLoadText(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PatientReport']//input[@ng-model='libraryTitle']"), 90);
			webelement = driver.findElement(By.xpath("//div[@id='PatientReport']//input[@ng-model='libraryTitle']"));
			System.out.println("S page Patient Load Report Text Box found");
		} catch (NoSuchElementException e) {
			System.out.println("S page Patient Report load Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientReportLoadtextDropDown(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@comment-type='vmNote.patientReport.comment']//select[@ng-model='libraryValue']"),
					90);
			webelement = driver.findElement(By
					.xpath("//table[@comment-type='vmNote.patientReport.comment']//select[@ng-model='libraryValue']"));
			System.out.println("S page Patient Report Load drop down found");
		} catch (NoSuchElementException e) {
			System.out.println("S page Patient Report Load drop down not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientReportLoadSaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//table[@comment-type='vmNote.patientReport.comment']//button"),
					90);
			webelement = driver.findElement(By.xpath("//table[@comment-type='vmNote.patientReport.comment']//button"));
			System.out.println("S page Patient Report Load save button found");
		} catch (NoSuchElementException e) {
			System.out.println("S page Patient Report Load save button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientReportRedDot(WebDriver driver) {
		webelement = null;
		try {
			webelement = driver.findElement(By.xpath("//a[@href='#PatientReport']/span/span"));
			System.out.println("S page Patient Report red dot ");
		} catch (NoSuchElementException e) {
			System.out.println("S page Patient Report red dot not found");
		}
		return webelement;
	}

	public static WebElement CurrentProblemsRedDot(WebDriver driver) {
		webelement = null;
		try {
			// Commons.waitforElement(driver,
			// By.xpath("//a[@href='#CurrentProblems']/span/span"),90);
			webelement = driver.findElement(By.xpath("//a[@href='#CurrentProblems']/span/span"));
			System.out.println("S page current problem red dot ");
		} catch (NoSuchElementException e) {
			System.out.println("S page current problem red dot not found");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		return webelement;
	}

	public static WebElement SafetyConsiderationsRedDot(WebDriver driver) {
		webelement = null;
		try {
			// Commons.waitforElement(driver,
			// By.xpath("//a[@href='#SafetyConsiderations']/span/span"),90);
			webelement = driver.findElement(By.xpath("//a[@href='#SafetyConsiderations']/span/span"));
			System.out.println("S page Safety Considerations red dot ");
		} catch (NoSuchElementException e) {
			System.out.println("S page Safety Considerations red dot not found");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		return webelement;
	}

	public static WebElement MedicationsRedDot(WebDriver driver) {
		webelement = null;
		try {
			// Commons.waitforElement(driver,
			// By.xpath("//a[@href='#Medications']/span/span"),90);
			webelement = driver.findElement(By.xpath("//a[@href='#Medications']/span/span"));
			System.out.println("S page Medications red dot ");
		} catch (NoSuchElementException e) {
			System.out.println("S page Medications red dot not found");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
		}
		return webelement;
	}

	public static WebElement HistoryRedDot(WebDriver driver) {
		webelement = null;
		try {
			webelement = driver.findElement(By.xpath("//a[@href='#History']/span/span"));
			System.out.println("S page History red dot ");
		} catch (NoSuchElementException e) {
			System.out.println("S page History red dot not found");
		}
		return webelement;
	}

	public static WebElement ProblemTextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//div[@value='vmNote.currentProblem.value']//textarea"), 90);
			webelement = driver.findElement(By.xpath("//div[@value='vmNote.currentProblem.value']//textarea"));
			System.out.println("Problem text box found ");
		} catch (NoSuchElementException e) {
			System.out.println("Problem text box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ProblemLibTextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmNote.libraryGroup.currentProblems']//input"), 90);
			webelement = driver
					.findElement(By.xpath("//table[@library-group-name='vmNote.libraryGroup.currentProblems']//input"));
			System.out.println("Problem lib text box found ");
		} catch (NoSuchElementException e) {
			System.out.println("Problem lib text box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement OnSetDate(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//input[contains(@ng-model,'reportedOnsetDate')]"), 90);
			webelement = driver.findElement(By.xpath("//input[contains(@ng-model,'reportedOnsetDate')]"));
			System.out.println("On set date text box found ");
		} catch (NoSuchElementException e) {
			System.out.println("OnSet  text box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement OnSetMec(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//input[contains(@ng-model,'onsetMechanism')]"), 90);
			webelement = driver.findElement(By.xpath("//input[contains(@ng-model,'onsetMechanism')]"));
			System.out.println("Onset mechenism text box found ");
		} catch (NoSuchElementException e) {
			System.out.println("Onset mechenism text box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ProblemLoadFromLib(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmNote.libraryGroup.currentProblems']//select"), 90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmNote.libraryGroup.currentProblems']//select"));
			System.out.println("Onset mechenism text box found ");
		} catch (NoSuchElementException e) {
			System.out.println("Onset mechenism text box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CurrentProblemSave(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmNote.libraryGroup.currentProblems']//button"), 90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmNote.libraryGroup.currentProblems']//button"));
			System.out.println("Save button found ");
		} catch (NoSuchElementException e) {
			System.out.println("save button not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AddNewProblemButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Add New Problem List')]"), 90);
			webelement = driver.findElement(By.xpath("//button[contains(text(),'Add New Problem List')]"));
			System.out.println("Add problem button found ");
		} catch (NoSuchElementException e) {
			System.out.println("Add problem button not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AddNewProblemListButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//i[@title='Add Problem List']"), 90);
			webelement = driver.findElement(By.xpath("//i[@title='Add Problem List']"));
			System.out.println("Add problem list '+' button found ");
		} catch (NoSuchElementException e) {
			System.out.println("Add problem list '+' button not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CurrentProblemLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#CurrentProblems']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#CurrentProblems']"));
			System.out.println("Current Problem Link found ");
		} catch (NoSuchElementException e) {
			System.out.println("Current Problem Link not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement HistoryLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#History']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#History']"));
			System.out.println("History Link found ");
		} catch (NoSuchElementException e) {
			System.out.println("History Link not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CurrentProblemTrashIcon(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//i[@title='Delete Problem List']"), 90);
			webelement = driver.findElement(By.xpath("//i[@title='Delete Problem List']"));
			System.out.println("Current Problem trash icon found ");
		} catch (NoSuchElementException e) {
			System.out.println("Current Problem Trash icon not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement OnSetMecCkeckBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//div[@class='checkbox']/label/input"), 90);
			webelement = driver.findElement(By.xpath("//div[@class='checkbox']/label/input"));
			System.out.println("Onset mechenism text box found ");
		} catch (NoSuchElementException e) {
			System.out.println("Onset mechenism text box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Save(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 180);
			webelement = driver.findElement(By.xpath("//button[@value='Save Note']"));
			System.out.println("Save button found ");
		} catch (NoSuchElementException e) {
			System.out.println("Save button not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SafetyConsiderationLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@href='#SafetyConsiderations']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#SafetyConsiderations']"));
			System.out.println("Safety consideration  link found ");
		} catch (NoSuchElementException e) {
			System.out.println("Safety consideration  link not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicationLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[@href='#Medications']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#Medications']"));
			System.out.println("Medications  link found ");
		} catch (NoSuchElementException e) {
			System.out.println("Medications  link not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ReDFlagSafety(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver,
					By.xpath("//td[contains(text(),'Red Flags')]//following-sibling::td[1]//input[@value='No']"), 90);
			webelement = driver.findElement(
					By.xpath("//td[contains(text(),'Red Flags')]//following-sibling::td[1]//input[@value='No']"));
			System.out.println("Safety red flag found ");
		} catch (NoSuchElementException e) {
			System.out.println("Safety red flag not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AlergySafety(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath(
							"//td[contains(text(),'Patient has Allergies')]//following-sibling::td[1]//input[@value='No']"),
					90);
			webelement = driver.findElement(By.xpath(
					"//td[contains(text(),'Patient has Allergies')]//following-sibling::td[1]//input[@value='No']"));
			System.out.println("patient has Alergy Consideration flag found ");
		} catch (NoSuchElementException e) {
			System.out.println("patient has Alergy Consideration flag not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrecautionsFlag(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath(
							"//td[contains(text(),'Patient Precautions')]//following-sibling::td[1]//input[@value='No']"),
					90);
			webelement = driver.findElement(By.xpath(
					"//td[contains(text(),'Patient Precautions')]//following-sibling::td[1]//input[@value='No']"));
			System.out.println("Precautions flag found ");
		} catch (NoSuchElementException e) {
			System.out.println("Precautions flag not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement OtherMedicalConditions(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//td[contains(text(),'Other Medical Conditions')]//following-sibling::td//input[@value='Not Reported']"),
					90);
			webelement = driver.findElement(By.xpath(
					"//td[contains(text(),'Other Medical Conditions')]//following-sibling::td//input[@value='Not Reported']"));
			System.out.println("other medical conditions not reported flag found ");
		} catch (NoSuchElementException e) {
			System.out.println("other medical conditions not reported flag not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement OtherMedicalConditionstextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//td[contains(text(),'Other Medical Conditions')]//following-sibling::td//textarea"), 90);
			webelement = driver.findElement(
					By.xpath("//td[contains(text(),'Other Medical Conditions')]//following-sibling::td//textarea"));
			System.out.println("other medical conditions text box  found ");
		} catch (NoSuchElementException e) {
			System.out.println("other medical conditionstext box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PriorSurgery(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath(
							"//td[contains(text(),'Prior Surgeries')]//following-sibling::td//input[@value='Not Reported']"),
					90);
			webelement = driver.findElement(By.xpath(
					"//td[contains(text(),'Prior Surgeries')]//following-sibling::td//input[@value='Not Reported']"));
			System.out.println("prior surgery not reported flag found ");
		} catch (NoSuchElementException e) {
			System.out.println("prior surgery not reported flag not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PriorSurgerytextArea(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver,
					By.xpath("//td[contains(text(),'Prior Surgeries')]//following-sibling::td//textarea"), 90);
			webelement = driver
					.findElement(By.xpath("//td[contains(text(),'Prior Surgeries')]//following-sibling::td//textarea"));
			System.out.println("prior surgery text box found ");
		} catch (NoSuchElementException e) {
			System.out.println("prior surgery text box not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SignAndFinalize(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//button[@data-ng-click='vmSignNote.signFinalNoAttach()']"), 90);
			webelement = driver.findElement(By.xpath("//button[@data-ng-click='vmSignNote.signFinalNoAttach()']"));
			System.out.println("sign and finalize button found ");
		} catch (NoSuchElementException e) {
			System.out.println("sign and finalize button not found ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AddPhraseLibraries(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),' My Phrase Libraries')]"), 60);
			webelement = driver.findElement(By.xpath("//span[contains(text(),' My Phrase Libraries')]"));
			System.out.println("Got  My Phrase Libraries on Menu webelement");
		} catch (Exception e) {
			System.out.println("Did not get  Phrase Libraries on Menu webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
}
