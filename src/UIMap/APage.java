package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Utils.Commons;
import com.google.common.base.Throwables;

public class APage {
	private static WebElement webelement = null;

	public static WebElement APageLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitForLoad(driver);
			Commons.ExistandDisplayElement(driver, By.xpath("//a[@data-ng-click='redirectToAssesment()']"), 60);
			Commons.waitforElement(driver, By.xpath("//a[@data-ng-click='redirectToAssesment()']"), 90);
			webelement = driver.findElement(By.xpath("//a[@data-ng-click='redirectToAssesment()']"));
			System.out.println("A Page Link button  found");
		} catch (NoSuchElementException e) {
			System.out.println("A Page Link  button  not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AssesmentAndPrognosislink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#Assessment']"));
			System.out.println("Assesment And Prognosis link button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Assesment And Prognosislink button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Diagnoselink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#Diagnosis']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#Diagnosis']"));
			System.out.println("Diagnose link button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Diagnose link button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GoalsRehabPotentialLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#Goals-Rehab-Potential']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#Goals-Rehab-Potential']"));
			System.out.println("Diagnose link button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Diagnose link button not  found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicareFLRandTherapyCapLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"), 100);
			webelement = driver.findElement(By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']"));
			System.out.println("Medicare-FLR link found");
		} catch (NoSuchElementException e) {
			System.out.println(" Did not get Medicare-FLR link");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement OpageLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(@data-ng-click,'redirectToObjective()')]"), 100);
			webelement = driver.findElement(By.xpath("//a[contains(@data-ng-click,'redirectToObjective()')]"));
			System.out.println("O page link found");
		} catch (NoSuchElementException e) {
			System.out.println(" Did not get O page link");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SpageLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(@data-ng-click,'redirectToSubjective()')]"), 100);
			webelement = driver.findElement(By.xpath("//a[contains(@data-ng-click,'redirectToSubjective()')]"));
			System.out.println("S page link found");
		} catch (NoSuchElementException e) {
			System.out.println(" Did not get S page link");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PpageLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(@data-ng-click,'redirectToPlan()')]"), 100);
			webelement = driver.findElement(By.xpath("//a[contains(@data-ng-click,'redirectToPlan()')]"));
			System.out.println("P page link found");
		} catch (NoSuchElementException e) {
			System.out.println(" Did not get P page link");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PlannedInterventionLink(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@id='Ppage_Planned-Interventions']"), 100);
			webelement = driver.findElement(By.xpath("//a[@id='Ppage_Planned-Interventions']"));
			System.out.println("Planned Interventions page link found");
		} catch (NoSuchElementException e) {
			System.out.println(" Did not get Planned Interventions link");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CandidateTherapyCheckbox(WebDriver driver, String Option) {
		webelement = null;
		switch (Option) {
		case "yes":
			try {
				Commons.existsElement(driver, By.xpath("//input[@name='CandidateTherapy']"));
				// Commons.waitforElement(driver,
				// By.xpath("//input[@name='CandidateTherapy'])"),90);
				webelement = driver.findElement(By.xpath("//input[@name='CandidateTherapy'][1]"));
				System.out.println("Candidate Therapy yes button  found");
			} catch (NoSuchElementException e) {
				System.out.println("Candidate Therapy yes button  found");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "no":
			try {
				Commons.existsElement(driver, By.xpath("//input[@name='CandidateTherapy']"));
				// Commons.waitforElement(driver,
				// By.xpath("//input[@name='CandidateTherapy'][2])"),90);
				webelement = driver.findElement(By.xpath("//input[@name='CandidateTherapy'][2]"));
				System.out.println("Candidate Therapy no button  found");
			} catch (NoSuchElementException e) {
				System.out.println("Candidate Therapy no button not found");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		}
		return webelement;
	}

	public static WebElement AssessmentTextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//textarea[@name='assessmentStatement']"), 90);
			webelement = driver.findElement(By.xpath("//textarea[@name='assessmentStatement']"));
			System.out.println("Assessment Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("Assessment Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrognosisTextBox1(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//textarea[@name='positivePrognosisStatement']"), 90);
			webelement = driver.findElement(By.xpath("//textarea[@name='positivePrognosisStatement']"));
			System.out.println("Prognosis Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("Prognosis Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrognosisTextBox2(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//textarea[@name='negativePrognosisStatement']"), 90);
			webelement = driver.findElement(By.xpath("//textarea[@name='negativePrognosisStatement']"));
			System.out.println("negative Prognosis Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("negative Prognosis Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement diagnosisobservationTextBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//textarea[@name='diagnosisComment']"), 90);
			webelement = driver.findElement(By.xpath("//textarea[@name='diagnosisComment']"));
			System.out.println("diagnosis Comment Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("diagnosis Comment Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement goalTextBox(WebDriver driver, String row) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='noteGoal_" + row + "']"), 90);
			webelement = driver.findElement(By.xpath("//*[@id='noteGoal_" + row + "']"));
			System.out.println("goal Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("goal Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement diagnosisCommentTitleBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.diagnosis']//input"), 90);
			webelement = driver
					.findElement(By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.diagnosis']//input"));
			System.out.println("diagnosis Comment Title Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("diagnosis Comment Title Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrognosisTitleBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisIncreases']//input"), 90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisIncreases']//input"));
			System.out.println("Prognosis Title Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("Prognosis Title Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AssessmentTitleBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.assessment']//input"), 90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.assessment']//input"));
			System.out.println("Assessment Title Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("Assessment Title Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement negativePrognosisTitleBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisDecreases']//input"), 90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisDecreases']//input"));
			System.out.println("negative Prognosis Title Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("negative Prognosis Title Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement goalTitleBox(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.goal']//input"), 90);
			webelement = driver
					.findElement(By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.goal']//input"));
			System.out.println("Goal Title Text Box  found");
		} catch (NoSuchElementException e) {
			System.out.println("Goal Title Text Box not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AssessmentSaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.assessment']//button"), 90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.assessment']//button"));
			System.out.println("Assessment Save Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Assessment Save Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrognosisSaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisIncreases']//button"),
					90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisIncreases']//button"));
			System.out.println("Prognosis Save Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Prognosis Save Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement negativePrognosisSaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisDecreases']//button"),
					90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.prognosisDecreases']//button"));
			System.out.println("negative Prognosis Save Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("negative Prognosis Save Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement diagnosisCommentSaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.diagnosis']//button"), 90);
			webelement = driver.findElement(
					By.xpath("//table[@library-group-name='vmAssessment.libraryGroup.diagnosis']//button"));
			System.out.println("diagnosis Comment Save Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("diagnosis Comment Save Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GoalSaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver,
					By.xpath("//h2[contains(.,'Goal (at least one goal is required)')]//following::td[4]//button"), 90);
			webelement = driver.findElement(
					By.xpath("//h2[contains(.,'Goal (at least one goal is required)')]//following::td[4]//button"));
			System.out.println("Goal Save Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Goal Save Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GoalAddButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.scrollElementinotView(
					driver.findElement(
							By.xpath("//*[@id='Goals-Rehab-Potential']//following::div//button//span[text()='Add']")),
					driver);
			Commons.waitforElement(driver,
					By.xpath("//*[@id='Goals-Rehab-Potential']//following::div//button//span[text()='Add']"), 90);
			webelement = driver.findElement(
					By.xpath("//*[@id='Goals-Rehab-Potential']//following::div//button//span[text()='Add']"));
			System.out.println("Goal Add Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Goal Add Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GoalDeleteButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'removeGoals()')]"), 90);
			webelement = driver.findElement(By.xpath("//button[contains(@ng-click,'removeGoals()')]"));
			System.out.println("Goal Delete Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Goal Delete Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DiagnoseRedDot(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#Diagnosis']/span/span"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#Diagnosis']/span/span"));
			System.out.println("Diagnose Red Dot Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Diagnose Red Dot Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SaveButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 90);
			webelement = driver.findElement(By.xpath("//button[@value='Save Note']"));
			System.out.println("Save Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Save Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicalRecordsOnPatient(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#MedicalRecord']"), 90);
			webelement = driver.findElement(By.xpath("//a[@href='#MedicalRecord']"));
			System.out.println("Medical record Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Medical record Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DiagnoseDeleteButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='removeDxcode()']"), 90);
			webelement = driver.findElement(By.xpath("//button[@ng-click='removeDxcode()']"));
			System.out.println("Diagnose Delete Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Diagnose Delete Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement LockGoal(WebDriver driver) {
		webelement = null;
		try {
			Commons.scrollElementinotView(driver.findElement(By.xpath("//*[@id='goalsListTableBody']//td//i")), driver);
			Commons.waitforElement(driver, By.xpath("//*[@id='goalsListTableBody']//td//i"), 90);
			webelement = driver.findElement(By.xpath("//*[@id='goalsListTableBody']//td//i"));
			System.out.println(" Lock goal Button  found");
		} catch (NoSuchElementException e) {
			System.out.println("Lock goal Button not found");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
}
