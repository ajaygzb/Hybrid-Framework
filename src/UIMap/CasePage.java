package UIMap;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;

public class CasePage {
	public static WebElement webelement;
	public static Random rand;

	public static WebElement PatientQuickSearch(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@placeholder='Patient Search']"), 20);
			webelement = driver.findElement(By.xpath("//input[@placeholder='Patient Search']"));
			System.out.println("Got PatientQuickSearch webelement");
		} catch (Exception e) {
			System.out.println("Did not get PatientQuickSearch webelement");
			// Commons.ScreenPrint(driver, "CasePage", "PatientQuickSearch");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientOnMenu(WebDriver driver) {
		try {
			Commons.waitForLoad(driver);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("tabletchrome") && !driver
					.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient']")).isDisplayed()) {
				Commons.waitforElement(driver, By.xpath("//*[@id='menu-toggle']/i"), 90);
				driver.findElement(By.xpath("//*[@id='menu-toggle']/i")).click();
				Commons.Explicitwait();
				Commons.Explicitwait();
			}
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient']"));
			Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient']"),
					10);
			Commons.waitForElementToBeClickable(driver, webelement, 10);
			System.out.println("Got PatientOnMenu webelement");
		} catch (Exception e) {
			System.out.println("Did not get PatientOnMenu webelement");
			Assert.assertFalse(true,
					"Did not get PatientOnMenu webelement" + "      " + Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MDCodeReferingphysician(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='ReferringPhysician']//input[@id='RP_MDCode']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='ReferringPhysician']//input[@id='RP_MDCode']"));
			System.out.println("Found MD Code field for  refering physician");
		} catch (Exception e) {
			System.out.println("****MD Code field for refering physician not found*****");
			// Commons.ScreenPrint(driver, "CasePage", "Referingphysician");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientSearchOnMenu(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient Search']"), 20);
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient Search']"), 30);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Patient Search']"));
			System.out.println("Got PatientOnMenu webelement");
		} catch (Exception e) {
			System.out.println("Did not get PatientSearchOnMenu webelement");
			// Commons.ScreenPrint(driver, "CasePage", "PatientSearchOnMenu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SearchButtonOnAdvanceSearchPage(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmPatientSearch.searchPatients(0);']"), 40);
			webelement = driver.findElement(By.xpath("//button[@ng-click='vmPatientSearch.searchPatients(0);']"));
			System.out.println("Got SearchButtonOnAdvanceSearchPage webelement");
		} catch (Exception e) {
			System.out.println("Did not get SearchButtonOnAdvanceSearchPage webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SearchButtonOnAdvanceSearchPage");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GetRandomPatientFromAdvanceSearchPage(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td"),50);
			int size = driver.findElements(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]")).size()- 1;
			System.out.println("Toat result Found" + size);
		    int randomnum=rand.nextInt(12 - 2 + 1)+ 2;
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver.findElements(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td")).get(randomnum);
			} else {
				webelement = driver.findElements(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td")).get(randomnum);
				
			}
			
			System.out.println("Got GetPatientListFromAdvanceSearchPage webelement");
		} catch (Exception e) {
			System.out.println("Did not get GetPatientListFromAdvanceSearchPage webelement");
			
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GetFirstCaseFromCaseList(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]"));
			System.out.println("Got GetFirstCaseFromCaseList webelement");
		} catch (Exception e) {
			System.out.println("Did not get GetFirstCaseFromCaseList webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "GetFirstCaseFromCaseList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GetFirstCaseNameFromCaseList(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']"),
					20);
			webelement = driver
					.findElements(By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']")).get(2);
			System.out.println("Got GetFirstCaseNameFromCaseList webelement");
		} catch (Exception e) {
			System.out.println("Did not get GetFirstCaseNameFromCaseList webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "GetFirstCaseNameFromCaseList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GetFirstCasePrimaryInsuranceName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']"),
					80);
			webelement = driver
					.findElements(By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']")).get(3);
			System.out.println("Got GetFirstCasePrimaryInsuranceName webelement");
		} catch (Exception e) {
			System.out.println("Did not get GetFirstCasePrimaryInsuranceName webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "GetFirstCasePrimaryInsuranceName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GetFirstCaseSecondaryInsuranceName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']"),
					80);
			webelement = driver
					.findElements(By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']")).get(4);
			System.out.println("Got GetFirstCaseSecondaryInsuranceName webelement");
		} catch (Exception e) {
			System.out.println("Did not get GetFirstCaseSecondaryInsuranceName webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "GetFirstCaseSecondaryInsuranceName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement GetFirstCaseTertiaryInsuranceName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']"),
					80);
			webelement = driver
					.findElements(By.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']")).get(5);
			System.out.println("Got GetFirstCaseTertiaryInsuranceName webelement");
		} catch (Exception e) {
			System.out.println("Did not get GetFirstCaseTertiaryInsuranceName webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "GetFirstCaseTertiaryInsuranceName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static int GetFirstCaseStatusFromCaseList(WebDriver driver) {
		int status = 0;
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']//i[@class='fa fa-check']"),
					80);
			status = driver
					.findElements(By
							.xpath("//div[@id='gridCaseDetails']//tbody//tr[1]//td[@role='gridcell']//i[@class='fa fa-check']"))
					.size();
			System.out.println("Got GetFirstCaseStatusFromCaseList webelement");
		} catch (Exception e) {
			System.out.println("Did not get GetFirstCaseStatusFromCaseList webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "GetFirstCaseStatusFromCaseList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return status;
	}

	/* CASE INFO SECTION */
	/* CREATED BY VIVEK BADOLA */
	public static WebElement CaseOnMenu(WebDriver driver) {
		try {
			/*
			 * Commons.waitforElement(driver,
			 * By.xpath("//ul[@id='sidemenu']//span[text()='Cases']"), 20);
			 * webelement=driver.findElement(By.xpath(
			 * "//ul[@id='sidemenu']//span[text()='Cases']"));
			 */
			webelement = null;
			Commons.waitForLoad(driver);
			Commons.existsElement(driver, By.xpath("//a[@href='#Cases']"));
			Commons.waitforElement(driver, By.xpath("//a[@href='#Cases']"), 90); // RB
			webelement = driver.findElement(By.xpath("//a[@href='#Cases']"));
			Commons.waitForElementToBeVisible(driver, webelement, 10);
			System.out.println("Got cases hyperlink webelement");
		} catch (Exception e) {
			System.out.println("Did not get cases hyperlink webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseOnMenu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement NewCaseButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[text()='New Case']"), 30);
			webelement = driver.findElement(By.xpath("//button[text()='New Case']"));
			System.out.println("Got New Case Button webelement");
		} catch (Exception e) {
			System.out.println("Did not get New Case Button webelement");
			// Commons.ScreenPrint(driver, "CasePage", "NewCaseButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@ng-model='vmCase.case.description']"), 50);
			webelement = driver.findElement(By.xpath("//input[@ng-model='vmCase.case.description']"));
			System.out.println("Got Case Name  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Case Name  webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	

	public static WebElement InjuryTypeAsNone(WebDriver driver) {
		try {
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//select[@name='injuryType']//option[@label='None']"), 40);
			webelement = driver.findElement(By.xpath("//select[@name='injuryType']//option[@label='None']"));
			System.out.println("Got InjuryTypeAsNone webelement");
		} catch (Exception e) {
			System.out.println("Did not get InjuryTypeAsNone webelement");
			// Commons.ScreenPrint(driver, "CasePage", "InjuryTypeAsNone");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement InjuryTypeAsOther(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//select[@name='injuryType']//option[@label='Other']"), 20);
			webelement = driver.findElement(By.xpath("//select[@name='injuryType']//option[@label='Other']"));
			System.out.println("Got InjuryTypeAsNone webelement");
		} catch (Exception e) {
			System.out.println("Did not get InjuryTypeAsNone webelement");
			// Commons.ScreenPrint(driver, "CasePage", "InjuryTypeAsNone");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement InjuryTypeAsEmploymentRelated(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//select[@name='injuryType']//option[@label='Employment Related']"), 20);
			webelement = driver
					.findElement(By.xpath("//select[@name='injuryType']//option[@label='Employment Related']"));
			System.out.println("Got InjuryTypeAsEmploymentRelated webelement");
		} catch (Exception e) {
			System.out.println("Did not get InjuryTypeAsEmploymentRelated webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "InjuryTypeAsEmploymentRelated");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement InjuryTypeAsAutoAccidentRelated(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//select[@name='injuryType']//option[@label='Auto Accident Related']"), 20);
			webelement = driver
					.findElement(By.xpath("//select[@name='injuryType']//option[@label='Auto Accident Related']"));
			System.out.println("Got InjuryTypeAsAutoAccidentRelated webelement");
		} catch (Exception e) {
			System.out.println("Did not get InjuryTypeAsAutoAccidentRelated webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "InjuryTypeAsAutoAccidentRelated");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement OnsetDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='Injury/Onset Date']/span/button"), 20);
			webelement = driver.findElement(By.xpath("//*[@id='Injury/Onset Date']/span/button"));
			System.out.println("Got Onset Date webelement");
		} catch (Exception e) {
			System.out.println("Did not get Onset Date webelement");
			// Commons.ScreenPrint(driver, "CasePage", "OnsetDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement StartofCareDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='startofcaredate']/span/button"), 50);
			webelement = driver.findElement(By.xpath("//*[@id='startofcaredate']/span/button"));
			System.out.println("Got Start of Care Date webelement");
		} catch (Exception e) {
			System.out.println("Did not get Start of Care Date webelement");
			// Commons.ScreenPrint(driver, "CasePage", "StartofCareDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DischargeDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='dischargedate']/span/button"), 20);
			webelement = driver.findElement(By.xpath("//*[@id='dischargedate']/span/button"));
			System.out.println("Got Discharge Date webelement");
		} catch (Exception e) {
			System.out.println("Did not get Discharge Date webelement");
			// Commons.ScreenPrint(driver, "CasePage", "DischargeDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AssignmentDate(WebDriver driver) {
		try {
			Commons.scrollElementinotView(driver.findElement(By.xpath(".//*[@id='assignmentdate']/span/button")),
					driver);
			Commons.waitforElement(driver, By.xpath(".//*[@id='assignmentdate']/span/button"), 30);
			webelement = driver.findElement(By.xpath(".//*[@id='assignmentdate']/span/button"));
			System.out.println("Got Assignment Date webelement");
		} catch (Exception e) {
			System.out.println("Did not get Assignment Date webelement");
			// Commons.ScreenPrint(driver, "CasePage", "AssignmentDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseEffectiveDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='effectivedate']/span/button"), 20);
			webelement = driver.findElement(By.xpath(".//*[@id='effectivedate']/span/button"));
			System.out.println("Got Case Effective Date webelement");
		} catch (Exception e) {
			System.out.println("Did not get Case Effective Date webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseEffectiveDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ReferringPhysician(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//md-autocomplete[@id='autosearchPrimaryPhysician']"), 20);
			webelement = driver.findElement(By.xpath("//md-autocomplete[@id='autosearchPrimaryPhysician']"));
			System.out.println("Got Referring Physician  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Referring Physician  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "ReferringPhysician ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ReferringPhysicianMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@data-target='#ReferringPhysician']"), 20);
			webelement = driver.findElement(By.xpath("//button[@data-target='#ReferringPhysician']"));
			System.out.println("Got  Referring Physician Magnifier  webelement");
		} catch (Exception e) {
			System.out.println("Did not get  Referring Physician Magnifier  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "ReferringPhysicianMagnifier ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ReferringPhysicianSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='ReferringPhysician']//input[@value='Search']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='ReferringPhysician']//input[@value='Search']"));
			System.out.println("Got Search Referring Physician Button  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Search Referring Physician Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SearchReferringPhysicianButton ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ReferringPhysicianList(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='ReferringPhysicianId']//tr[contains(@class,'ng-scope')]//td[5]"), 40);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver
						.findElements(
								By.xpath("//div[@id='ReferringPhysicianId']//tr[contains(@class,'ng-scope')]//td"))
						.get(new Random().nextInt(6));
			} else {
				webelement = driver
						.findElements(
								By.xpath("//div[@id='ReferringPhysicianId']//tr[contains(@class,'ng-scope')]//td"))
						.get(new Random().nextInt(6));
			}
			System.out.println("Got Referring Physician List webelement");
		} catch (Exception e) {
			System.out.println("Did not get Referring Physician List  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "ReferringPhysicianList
			// ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondReferringPhysician(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//md-autocomplete[@id='autosearchSecondaryPhysician']"), 20);
			webelement = driver.findElement(By.xpath("//md-autocomplete[@id='autosearchSecondaryPhysician']"));
			System.out.println("Got Second Referring Physician webelement");
		} catch (Exception e) {
			System.out.println("Did not get Second Referring Physician webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondReferringPhysician");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondReferringPhysicianSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='SecondReferringPhysician']//input[@value='Search']"),
					40);
			webelement = driver.findElement(By.xpath("//div[@id='SecondReferringPhysician']//input[@value='Search']"));
			System.out.println("Got Search Second Referring Physician Button webelement");
		} catch (Exception e) {
			System.out.println("Did not get Search Second Referring Physician Button webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SearchSecondReferringPhysicianButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondReferringPhysicianMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@data-target='#SecondReferringPhysician']"), 20);
			webelement = driver.findElement(By.xpath("//button[@data-target='#SecondReferringPhysician']"));
			System.out.println("Got Second Referring Physician Magnifier webelement");
		} catch (Exception e) {
			System.out.println("Did not get  Second Referring Physician Magnifier webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondReferringPhysicianMagnifier");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondReferringPhysicianList(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='SecondReferringPhysicianId']//tr[contains(@class,'ng-scope')]"), 30);
			webelement = driver
					.findElements(
							By.xpath("//div[@id='SecondReferringPhysicianId']//tr[contains(@class,'ng-scope')]//td"))
					.get(5);
			System.out.println("Got Second Referring Physician List webelement");
		} catch (Exception e) {
			System.out.println("Did not get Second Referring Physician List webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondReferringPhysicianList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Location(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//md-autocomplete[@id='autosearchPOS']"), 20);
			webelement = driver.findElement(By.xpath("//md-autocomplete[@id='autosearchPOS']"));
			System.out.println("Got Location  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Location  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "Location");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement LocationMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='autosearchPOS']//preceding::button[1]"), 60);
			webelement = driver.findElement(By.xpath("//*[@id='autosearchPOS']//preceding::button[1]"));
			System.out.println("Got Location Magnifier  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Location Magnifier  webelement");
			
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	public static WebElement LocationInputbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='Location_Name']"), 60);
			webelement = driver.findElement(By.xpath("//*[@id='Location_Name']"));
			System.out.println("Got Location Name  Input");
		} catch (Exception e) {
			System.out.println("Did not get Location Name  Input");
			
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement LocationSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Location']//input[@value='Search']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Location']//input[@value='Search']"));
			System.out.println("Got Location Search Button  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Location Search Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "LocationSearchButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement LocationList(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='LocationId']//tr[contains(@class,'ng-scope')]"),
					90);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver
						.findElements(By.xpath("//div[@id='LocationId']//tr[contains(@class,'ng-scope')]//td"))
						.get(new Random().nextInt(5) + 1);
			} else {
				webelement = driver
						.findElements(By.xpath("//div[@id='LocationId']//tbody//tr[contains(@class,'ng-scope')]"))
						.get(new Random().nextInt(5) + 1);
			}
			System.out.println("Got Location List  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Location List webelement");
			
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	public static WebElement LocationList(WebDriver driver,String Location) {
		try {
			Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='LocationId']//tr[contains(@class,'ng-scope')]//span[text()='"+Location+"']"),
					90);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver
						.findElement(By.xpath("//div[@id='LocationId']//tr[contains(@class,'ng-scope')]//span[text()='"+Location+"']"));
						
			} else {
				webelement = driver
						.findElement(By.xpath("//div[@id='LocationId']//tr[contains(@class,'ng-scope')]//span[text()='"+Location+"']"));
						
			}
			System.out.println("Got Location:    "+Location);
		} catch (Exception e) {
			System.out.println("Did not get Location:    "+Location);
			
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement BillingProvider(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//md-autocomplete[@id='autosearchBP']"), 20);
			webelement = driver.findElement(By.xpath("//md-autocomplete[@id='autosearchBP']"));
			System.out.println("Got Billing Provider  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "BillingProvider");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement BillingProviderMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='btnBillingProviderSearch']"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='btnBillingProviderSearch']"));
			System.out.println("Got Billing Provider Magnifier  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider Magnifier webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "BillingProviderMagnifier");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	public static WebElement BillingProviderInputBox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='Provider_Name']"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='Provider_Name']"));
			System.out.println("Got Billing Provider Input box");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider Input box");
			// Commons.ScreenPrint(driver, "CasePage",
			// "BillingProviderMagnifier");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	

	public static WebElement BillingProviderSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='BillingProvider']//input[@value='Search']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='BillingProvider']//input[@value='Search']"));
			System.out.println("Got Billing Provider Search Button  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider Search button  webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement BillingProviderList(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='BillingId']//tr[contains(@class,'ng-scope')]//td[2]"), 50);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver
						.findElements(By.xpath("//div[@id='BillingId']//tr[contains(@class,'ng-scope')]//td[2]"))
						.get(2);
			} else {
				webelement = driver
						.findElements(By.xpath("//div[@id='BillingId']//tr[contains(@class,'ng-scope')]//td")).get(2);
			}
			System.out.println("Got Billing Provider List  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider List  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "BillingProviderList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	public static WebElement BillingProviderList(WebDriver driver,String ProviderName) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='BillingId']//tr[contains(@class,'ng-scope')]//td[1]//span[text()='"+ProviderName+"']"), 50);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver
						.findElement(By.xpath("//div[@id='BillingId']//tr[contains(@class,'ng-scope')]//td[1]//span[text()='"+ProviderName+"']"));
						
			} else {
				webelement = driver
						.findElement(By.xpath("//div[@id='BillingId']//tr[contains(@class,'ng-scope')]//td[1]//span[text()='"+ProviderName+"']"));
			}
			System.out.println("Got Billing Provider Name");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider Name");
			// Commons.ScreenPrint(driver, "CasePage", "BillingProviderList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Discipline(WebDriver driver) {
		try {
			Commons.waitforElement(driver,By.xpath("//select[contains(@ng-model,'discipline')]//option[text()='OTHER']"), 20);
			webelement = driver.findElement(By.xpath("//select[contains(@ng-model,'discipline')]//option[text()='OTHER']"));
			System.out.println("Got Discipline  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Discipline  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "Discipline");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AdditionalReportText(WebDriver driver) {
		try {
			// Commons.waitforElement(driver,
			// By.xpath("//*[@id='ProviderandLocation']/div/div[4]/div/div/input"),
			// 20);
			// webelement=driver.findElement(By.xpath("//*[@id='ProviderandLocation']/div/div[4]/div/div/input"));
			Commons.waitforElement(driver, By.xpath("//input[@ng-model='vmCase.case.internalInformation']"), 20);
			webelement = driver.findElement(By.xpath("//input[@ng-model='vmCase.case.internalInformation']"));
			System.out.println("Got Additional Report Text  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Additional Report Text  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "AdditionalReportText");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	// **************************************************
	public static WebElement AlternateReferralSourceMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//*[@id='SearchReferralSourceBtn']"),
					40);
			webelement = driver.findElement(
					By.xpath("//*[@id='SearchReferralSourceBtn']"));
			System.out.println("Got Alternate Referral Source Magnifier webelement");
		} catch (Exception e) {
			System.out.println("Did not get Alternate Referral Source Magnifier webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AlternateReferralSourceSearchbutton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='SearchReferralSource']//input[@id='Search']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='SearchReferralSource']//input[@id='Search']"));
			System.out.println("Got Alternate Referral Source search button");
		} catch (Exception e) {
			System.out.println("Did not get Alternate Referral Source search button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AlternateReferralSourceList(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='GetReferralSourceId']//td[contains(@role,'gridcell')]"), 90);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver
						.findElements(By.xpath("//div[@id='GetReferralSourceId']//td[contains(@role,'gridcell')]"))
						.get(new Random().nextInt(6));
			} else {
				webelement = driver
						.findElements(By.xpath("//div[@id='GetReferralSourceId']//td[contains(@role,'gridcell')]"))
						.get(new Random().nextInt(6));
			}
			System.out.println("Got Alternate Referral Source List  webelement");
		} catch (Exception e) {
			System.out.println("Did not get  Alternate Referral Source List  webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	// *************************************************
	public static WebElement UseSpecialBilling(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='ProviderandLocation']//input[@type='checkbox']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='ProviderandLocation']//input[@type='checkbox']"));
			System.out.println("Got Use Special Billing  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Use Special Billing  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "UseSpecialBilling");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseSaveButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@id='saveCaseDetailsButton']"), 20);
			webelement = driver.findElement(By.xpath("//button[@id='saveCaseDetailsButton']"));
			System.out.println("Got Case Save Button  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Case Save Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseSaveButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseCancelButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmCase.cancel()'][text()='Cancel']"), 20);
			webelement = driver.findElement(By.xpath("//button[@ng-click='vmCase.cancel()'][text()='Cancel']"));
			System.out.println("Got Case Cancel Button  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Case Cancel Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseCancelButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/* DIAGNOSIS SECTION */
	/* CREATED BY VIVEK BADOLA */
	public static WebElement AddICD10CodeButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='btnICDCode_']"), 20);
			webelement = driver.findElement(By.xpath("//*[@id='btnICDCode_']"));
			System.out.println("Got Add ICD10 Code  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Add ICD10 Code  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "AddICD10Code");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DiagnosisDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='removeDxcode()']"), 20);
			webelement = driver.findElement(By.xpath("//button[@ng-click='removeDxcode()']"));
			System.out.println("Got Diagnosis Delete Button  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Diagnosis Delete Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "DiagnosisDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DiagnosisCodeSearchButton(WebDriver driver) {
		try {
			if (Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='ICDCode']//button[text()='Search']"), 20)) {
				Commons.waitforElement(driver, By.xpath("//div[@id='ICDCode']//button[text()='Search']"), 20);
				webelement = driver.findElement(By.xpath("//div[@id='ICDCode']//button[text()='Search']"));
				System.out.println("Got Search Diagnosis Code Button  webelement");
			} else {
				ActionUtils.click(CasePage.AddICD10CodeButton(driver));
				Commons.waitforElement(driver, By.xpath("//div[@id='ICDCode']//button[text()='Search']"), 20);
				webelement = driver.findElement(By.xpath("//div[@id='ICDCode']//button[text()='Search']"));
			}
		} catch (Exception e) {
			System.out.println("Did not get Search Diagnosis Code Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SearchDiagnosisCodeButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DiagnosisCancelButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='case']//input[@value='Cancel']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='case']//input[@value='Cancel']"));
			System.out.println("Got Cancel Diagnosis Button  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Cancel Diagnosis  Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CancelDiagnosisButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DxCodeList(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td"),
					60);
			webelement = driver.findElements(By.xpath("//div[@id='gridSearch']//tr[contains(@class,'ng-scope')]//td"))
					.get(new Random().nextInt(5));
			System.out.println("Got DxCodeList  webelement");
		} catch (Exception e) {
			System.out.println("Did not get DxCode List  webelement");
			// Commons.ScreenPrint(driver, "DxCode List", "DxCodeList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SelectAllDx(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@ng-model='selectedAllDx']"), 20);
			webelement = driver.findElement(By.xpath("//input[@ng-model='selectedAllDx']"));
			System.out.println("Got Select All Dx  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Select All Dx  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "Select All Dx");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * PRIMARY INSURANCE CREATED BY VIVEK BADOLA
	 */
	public static WebElement PrimaryInsuranceTab(WebDriver driver) {
		try {
			Commons.scrollElementinotView(driver.findElement(By.xpath("//a[@href='#PrimaryInsurance']")), driver);
			Commons.waitforElement(driver, By.xpath("//a[@href='#PrimaryInsurance']"), 20);
			webelement = driver.findElement(By.xpath("//a[@href='#PrimaryInsurance']"));
			System.out.println("Got PrimaryInsuranceTab  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceTab  webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryInsuranceMagnifier(WebDriver driver) {
		try {
			Commons.scrollElementinotView(
					driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//button[@data-target='#Primary']")),
					driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//button[@data-target='#Primary']"),
					40);
			webelement = driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//button[@data-target='#Primary']"));
			System.out.println("Got PrimaryInsuranceMagnifier  webelement");
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceMagnifier  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryInsuranceMagnifier");
			Assert.assertFalse(true, "Did not get PrimaryInsuranceMagnifier  webelement");
		}
		return webelement;
	}

	public static WebElement PrimaryInsuranceName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Primary']//input[@id='Insurance_Name']"), 50);
			webelement = driver.findElement(By.xpath("//div[@id='Primary']//input[@id='Insurance_Name']"));
			System.out.println("Got PrimaryInsuranceName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceName  webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryInsurancecode(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Primary']//input[@id='Insurance_Code']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Primary']//input[@id='Insurance_Code']"));
			System.out.println("Got PrimaryInsuranceCode  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceCode  webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryInsuranceSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Primary']//input[@value='Search']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Primary']//input[@value='Search']"));
			System.out.println("Got PrimaryInsuranceSearchButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceSearchButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryInsuranceSearchButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryInsuranceCancelButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Primary']//input[@value='Cancel']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Primary']//input[@value='Cancel']"));
			System.out.println("Got PrimaryInsuranceCancelButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceCancelButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryInsuranceCancelButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryInsuranceClearButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Primary']//input[@value='Clear']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Primary']//input[@value='Clear']"));
			System.out.println("Got PrimaryInsuranceClearButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceClearButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryInsuranceClearButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryInsuranceList(WebDriver driver) {
		try {
			// Commons.waitforElement(driver,
			// By.xpath("//div[@id='InsuranceCodePrimaryId']//tr[contains(@class,'ng-scope')]//td[2]"),90);
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='InsuranceCodePrimaryId']//tr[contains(@class,'ng-scope')]//td[2]"), 90);
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("Safari")) {
				System.out.println("$$In safari block$$");
				webelement = driver
						.findElements(
								By.xpath("//div[@id='InsuranceCodePrimaryId']//tr[contains(@class,'ng-scope')]//td[2]"))
						.get(0);
			} else {
				webelement = driver
						.findElements(
								By.xpath("//div[@id='InsuranceCodePrimaryId']//tr[contains(@class,'ng-scope')]//td[2]"))
						.get(0);
			}
			System.out.println("Got PrimaryInsuranceList  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryInsuranceList  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "PrimaryInsuranceList");
			Assert.assertFalse(true,
					"Did not get PrimaryInsuranceList  webelement" + Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement primaryInsuranceGroupNumberORClaimNumber(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.groupNumber']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.groupNumber']"));
			System.out.println("Got primaryInsuranceGroupNumberORClaimNumber  webelement");
		} catch (Exception e) {
			System.out.println("Did not get primaryInsuranceGroupNumberORClaimNumber  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "primaryInsuranceGroupNumberORClaimNumber");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement primaryInsurancePolicyNameORSSN(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.policyNumber']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.policyNumber']"));
			System.out.println("Got primaryInsurancePolicyNameORSSN  webelement");
		} catch (Exception e) {
			System.out.println("Did not get primaryInsurancePolicyNameORSSN  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "primaryInsurancePolicyNameORSSN");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimarySameAsPatient(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//input[contains(@class,'sameAsPatient')]"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//input[contains(@class,'sameAsPatient')]"));
			System.out.println("Got PrimarySameAsPatient  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimarySameAsPatient  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "PrimarySameAsPatient");
			Assert.assertFalse(true, "Did not get PrimarySameAsPatient  webelement");
		}
		return webelement;
	}

	public static WebElement RelationshipAsSelf(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//option[@label='Self']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//option[@label='Self']"));
			System.out.println("Got RelationshipAsSelf  webelement");
		} catch (Exception e) {
			System.out.println("Did not get RelationshipAsSelf  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "RelationshipAsSelf");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement RelationshipAsEmployee(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//option[@label='Employee']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//option[@label='Other']"));
			System.out.println("Got RelationshipAsEmployee  webelement");
		} catch (Exception e) {
			System.out.println("Did not get RelationshipAsEmployee  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "RelationshipAsEmployee");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement RelationshipAsOther(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//option[@label='Other']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//option[@label='Other']"));
			System.out.println("Got RelationshipAsOther  webelement");
		} catch (Exception e) {
			System.out.println("Did not get RelationshipAsOther  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "RelationshipAsOther");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimarySubscriberOrEmployerName(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.employeeName']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.employeeName']"));
			System.out.println("Got PrimarySubscriberOrEmployerName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimarySubscriberOrEmployerName  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimarySubscriberOrEmployerName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimarySubscriberIsActive(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.isActive']"),
					20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//input[@ng-model='model.isActive']"));
			System.out.println("Got PrimarySubscriberIsActive  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimarySubscriberIsActive  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimarySubscriberIsActive");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * PRIMARY INSURANCE ADD PRE-AUTHORIZATION CREATED BY VIVEK BADOLA
	 */
	public static WebElement PrimaryAddPreauthorizationButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//button[text()='Add Preauthorization']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//button[text()='Add Preauthorization']"));
			System.out.println("Got PrimaryAddPreauthorizationButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryAddPreauthorizationButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryAddPreauthorizationButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryPreauthorizationDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(preAuthModel,')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(preAuthModel,')]"));
			System.out.println("Got PrimaryPreauthorizationDeleteButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryPreauthorizationDeleteButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryPreauthorizationDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryPreAuthorizationSelectCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[@name='isDelete']"), 20);
			webelement = driver.findElement(
					By.xpath("//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[@name='isDelete']"));
			System.out.println("Got PrimaryPreAuthorizationSelectCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryPreAuthorizationSelectCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryPreAuthorizationSelectCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryPreAuthorizationNumber(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[@name='preAuthNumber']"),
					20);
			webelement = driver.findElement(By
					.xpath("//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[@name='preAuthNumber']"));
			System.out.println("Got PrimaryPreAuthorizationNumber  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryPreAuthorizationNumber  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryPreAuthorizationNumber");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryPreAuthorizationFromDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("(//div[@id='PrimaryInsurance']//td)[3]//button"), 20);
			webelement = driver.findElement(By.xpath("(//div[@id='PrimaryInsurance']//td)[3]//button"));
			System.out.println("Got PrimaryPreAuthorizationFromDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryPreAuthorizationFromDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryPreAuthorizationFromDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryPreAuthorizationThroughDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("(//div[@id='PrimaryInsurance']//td)[4]//button"), 20);
			webelement = driver.findElement(By.xpath("(//div[@id='PrimaryInsurance']//td)[4]//button"));
			System.out.println("Got PrimaryPreAuthorizationThroughDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryPreAuthorizationThroughDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryPreAuthorizationThroughDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryPreAuthorizationVisits(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[contains(@name,'preAuthVisitUsed')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[contains(@name,'preAuthVisitUsed')]"));
			System.out.println("Got PrimaryPreAuthorizationVisits  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryPreAuthorizationVisits  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryPreAuthorizationVisits");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryPreAuthorizationIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isActive']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isActive']"));
			System.out.println("Got PrimaryPreAuthorizationIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryPreAuthorizationIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryPreAuthorizationIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * PRIMARY INSURANCE ADD ELIGIBILITIES CREATED BY VIVEK BADOLA
	 */
	public static WebElement PrimaryAddEligibilityButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//button[text()='Add Eligibility']"),
					20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//button[text()='Add Eligibility']"));
			System.out.println("Got PrimaryAddEligibilityButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryAddEligibilityButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryAddEligibilityButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//button[contains(@ng-click, 'item.isDelete=true;parentModel.removeEligibility($index,insuranceName)')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//button[contains(@ng-click, 'item.isDelete=true;parentModel.removeEligibility($index,insuranceName)')]"));
			System.out.println("Got PrimaryEligibilityDeleteButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityDeleteButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityContactName(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.contactName']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.contactName']"));
			System.out.println("Got PrimaryEligibilityContactName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityContactName  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityContactName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityEffectiveDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"(//h2[contains(.,'Primary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"),
					20);
			webelement = driver.findElement(By.xpath(
					"(//h2[contains(.,'Primary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"));
			System.out.println("Got PrimaryEligibilityEffectiveDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityEffectiveDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityEffectiveDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityTerminationDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"(//h2[contains(.,'Primary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[2]"),
					20);
			webelement = driver.findElement(By.xpath(
					"(//h2[contains(.,'Primary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[2]"));
			System.out.println("Got PrimaryEligibilityTerminationDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityTerminationDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityTerminationDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityInNetworkCopay(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkCopay']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkCopay']"));
			System.out.println("Got PrimaryEligibilityInNetworkCopay  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityInNetworkCopay  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityInNetworkCopay");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityOutOfNetworkCopay(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkCoPay']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkCoPay']"));
			System.out.println("Got PrimaryEligibilityOutOfNetworkCopay  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityOutOfNetworkCopay  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityOutOfNetworkCopay");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityInNetworkDeductible(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkDeduct']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkDeduct']"));
			System.out.println("Got PrimaryEligibilityInNetworkDeductible  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityInNetworkDeductible  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityInNetworkDeductible");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityOutofNetworkDeductible(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkDeduct']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkDeduct']"));
			System.out.println("Got PrimaryEligibilityOutofNetworkDeductible  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityOutofNetworkDeductible  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityOutofNetworkDeductible");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryEligibilityIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.isActive']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.isActive']"));
			System.out.println("Got PrimaryEligibilityIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryEligibilityIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryEligibilityIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * ADD DOCOTOR'S ORDER CREATED BY VIVEK BADOLA
	 */
	public static WebElement PrimaryDocotorsOrderAddButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//button[@ng-click='doctorOrder();']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='PrimaryInsurance']//button[@ng-click='doctorOrder();']"));
			System.out.println("Got PrimaryDocotorsOrderAddButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderAddButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderAddButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryDocotorsOrderDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(doctorOrderModel,')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(doctorOrderModel,')]"));
			System.out.println("Got PrimaryDocotorsOrderDeleteButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderDeleteButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryDocotorsOrderSelectCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[@name='isDelete']"),
					20);
			webelement = driver.findElement(
					By.xpath("//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[@name='isDelete']"));
			System.out.println("Got PrimaryDocotorsOrderSelectCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderSelectCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderSelectCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryDocotorsOrderFromDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("(//h2[contains(.,'Orders')]/following::button[contains(@class,'calenderbutton')])[1]"),
					20);
			webelement = driver.findElement(
					By.xpath("(//h2[contains(.,'Orders')]/following::button[contains(@class,'calenderbutton')])[1]"));
			System.out.println("Got PrimaryDocotorsOrderFromDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderFromDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderFromDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryDocotorsOrderThroughDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("(//h2[contains(.,'Orders')]/following::button[contains(@class,'calenderbutton')])[2]"),
					20);
			webelement = driver.findElement(
					By.xpath("(//h2[contains(.,'Orders')]/following::button[contains(@class,'calenderbutton')])[2]"));
			System.out.println("Got PrimaryDocotorsOrderThroughDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderThroughDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderThroughDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryDocotorsOrderPrescribedVisits(WebDriver driver) {
		try { // input[contains(@name,'preAuthVisitUsed')]
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[contains(@name,'docPrescribedVisits')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[contains(@name,'docPrescribedVisits')]"));
			System.out.println("Got PrimaryDocotorsOrderPrescribedVisits  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderPrescribedVisits  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderPrescribedVisits");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryDocotorsOrderComment(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[@ name='docComment']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[@ name='docComment']"));
			System.out.println("Got PrimaryDocotorsOrderComment  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderComment  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderComment");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PrimaryDocotorsOrderIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[@ ng-model='model[$index].isActive']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='doctorOrderModel']//input[@ ng-model='model[$index].isActive']"));
			System.out.println("Got PrimaryDocotorsOrderIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get PrimaryDocotorsOrderIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "PrimaryDocotorsOrderIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * SECONDARY INSURANCE CREATED BY VIVEK BADOLA
	 */
	public static WebElement SecondaryInsuranceTab(WebDriver driver) {
		try {		
			Commons.ExistandDisplayElement(driver, By.xpath("//div[@id='SecondaryInsurance']//a[@data-toggle='collapse']"), 80);
			webelement = driver.findElement(By.xpath("//div[@id='SecondaryInsurance']//a[@data-toggle='collapse']"));
			System.out.println("Got SecondaryInsuranceTab  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceTab  webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='SecondaryInsurance']//button[@data-target='#Secondary']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='SecondaryInsurance']//button[@data-target='#Secondary']"));
			System.out.println("Got SecondaryInsuranceMagnifier  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceMagnifier  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceMagnifier");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Secondary']//input[@id='Secondary_Name']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Secondary']//input[@id='Secondary_Name']"));
			System.out.println("Got SecondaryInsuranceName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceName  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Secondary']//input[@value='Search']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Secondary']//input[@value='Search']"));
			System.out.println("Got SecondaryInsuranceSearchButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceSearchButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceSearchButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceCancelButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Secondary']//input[@value='Cancel']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Secondary']//input[@value='Cancel']"));
			System.out.println("Got SecondaryInsuranceCancelButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceCancelButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceCancelButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceClearButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Secondary']//input[@value='Clear']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Secondary']//input[@value='Clear']"));
			System.out.println("Got SecondaryInsuranceClearButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceClearButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceClearButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceList(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='InsuranceCodeSecondaryId']//tr[contains(@class,'ng-scope')]//td[2]"), 60);
			System.out
					.println("Number of Insurance Available=" + driver
							.findElements(By
									.xpath("//div[@id='InsuranceCodeSecondaryId']//tr[contains(@class,'ng-scope')]//td[2]"))
							.get(0).getSize());
			webelement = driver
					.findElements(
							By.xpath("//div[@id='InsuranceCodeSecondaryId']//tr[contains(@class,'ng-scope')]//td[2]"))
					.get(0);
			System.out.println("Got SecondaryInsuranceList  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceList  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceGroupNumberOrClaimNumber(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='SecondaryInsurance']//input[@name='secondaryInsuranceFormgroupNumber']"), 20);
			webelement = driver.findElement(
					By.xpath("//div[@id='SecondaryInsurance']//input[@name='secondaryInsuranceFormgroupNumber']"));
			System.out.println("Got SecondaryInsuranceGroupNumberOrClaimNumber  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceGroupNumberOrClaimNumber  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceGroupNumberOrClaimNumber");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePolicyNumberOrSSN(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='SecondaryInsurance']//input[@name='secondaryInsuranceFormpolicyName']"), 20);
			webelement = driver.findElement(
					By.xpath("//div[@id='SecondaryInsurance']//input[@name='secondaryInsuranceFormpolicyName']"));
			System.out.println("Got SecondaryInsuranceGroupNumberOrSSN  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceGroupNumberOrSSN  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceGroupNumberOrSSN");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceMSPtypeLabel(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='SecondaryInsurance']//label[contains(text(),'MSP Type')]"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='SecondaryInsurance']//label[contains(text(),'MSP Type')]"));
			System.out.println("Got SecondaryInsuranceMSPtypeLabel  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceMSPtypeLabel  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceMSPtypeLabel");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceSameAsPatientCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='SecondaryInsurance']//input[contains(@class,'sameAsPatient')]"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='SecondaryInsurance']//input[contains(@class,'sameAsPatient')]"));
			System.out.println("Got SecondaryInsuranceSameAsPatientCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceSameAsPatientCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceSameAsPatientCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceSubscriberName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='SecondaryInsurance']//input[@name='subscriberName']"),
					20);
			webelement = driver.findElement(By.xpath("//div[@id='SecondaryInsurance']//input[@name='subscriberName']"));
			System.out.println("Got SecondaryInsuranceSubscriberName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceSubscriberName  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceSubscriberName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceRelationshipAsSelf(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//option[@label='Self']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//option[@label='Self']"));
			System.out.println("Got SecondaryInsuranceRelationshipAsSelf  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceRelationshipAsSelf  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceRelationshipAsSelf");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceRelationshipAsEmployee(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//option[@label='Employee']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//option[@label='Employee']"));
			System.out.println("Got SecondaryInsuranceRelationshipAsEmployee  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceRelationshipAsEmployee  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceRelationshipAsEmployee");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceRelationshipAsOther(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='PrimaryInsurance']//option[@label='Other']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='PrimaryInsurance']//option[@label='Other']"));
			System.out.println("Got SecondaryInsuranceRelationshipAsOther  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceRelationshipAsOther  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceRelationshipAsOther");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceSubscriberActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='SecondaryInsurance']//input[@ng-model='model.isActive']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='SecondaryInsurance']//input[@ng-model='model.isActive']"));
			System.out.println("Got SecondaryInsuranceSubscriberActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceSubscriberActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceSubscriberActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * SECONDARY INSURANCE ADD-PREAUTHORIZATION CREATED BY VIVEK BADOLA
	 */
	public static WebElement SecondaryInsurancePreauthorizationAddButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='SecondaryInsurance']//button[@ng-click='preAuth();']"),
					20);
			webelement = driver
					.findElement(By.xpath("//div[@id='SecondaryInsurance']//button[@ng-click='preAuth();']"));
			System.out.println("Got SecondaryInsurancePreauthorizationAddButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationAddButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationAddButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePreauthorizationDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(preAuthModel,')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(preAuthModel,')]"));
			System.out.println("Got SecondaryInsurancePreauthorizationDeleteButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationDeleteButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePreauthorizationSelectCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isDelete']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isDelete']"));
			System.out.println("Got SecondaryInsurancePreauthorizationSelectCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationSelectCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationSelectCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePreauthorizationNumber(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].preAuthNumber']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].preAuthNumber']"));
			System.out.println("Got SecondaryInsurancePreauthorizationNumber  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationNumber  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationNumber");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePreauthorizationFromDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("(//div[@id='SecondaryInsurance']//td)[3]//button"), 20);
			webelement = driver.findElement(By.xpath("(//div[@id='SecondaryInsurance']//td)[3]//button"));
			System.out.println("Got SecondaryInsurancePreauthorizationFromDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationFromDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationFromDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePreauthorizationThroughDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("(//div[@id='SecondaryInsurance']//td)[4]//button"), 20);
			webelement = driver.findElement(By.xpath("(//div[@id='SecondaryInsurance']//td)[4]//button"));
			System.out.println("Got SecondaryInsurancePreauthorizationThroughDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationThroughDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationThroughDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePreauthorizationVisits(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].preAuthVisits']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].preAuthVisits']"));
			System.out.println("Got SecondaryInsurancePreauthorizationVisits  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationVisits  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationVisits");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsurancePreauthorizationIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isActive']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isActive']"));
			System.out.println("Got SecondaryInsurancePreauthorizationIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsurancePreauthorizationIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsurancePreauthorizationIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * SECONDARY INSURANCE ADD-ELIGIBILITIES CREATED BY VIVEK BADOLA
	 */
	public static WebElement SecondaryInsuranceEligibilityAddButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='SecondaryInsurance']//button[@ng-click='eligibility();']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='SecondaryInsurance']//button[@ng-click='eligibility();']"));
			System.out.println("Got SecondaryInsuranceEligibilityAddButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityAddButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityAddButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//button[@ng-click='item.isDelete=true;parentModel.removeEligibility($index,insuranceName);']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//button[@ng-click='item.isDelete=true;parentModel.removeEligibility($index,insuranceName);']"));
			System.out.println("Got SecondaryInsuranceEligibilityDeleteButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityDeleteButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityContactName(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.contactName']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.contactName']"));
			System.out.println("Got SecondaryInsuranceEligibilityContactName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityContactName  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityContactName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityEffectiveDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"(//h2[contains(.,'Secondary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"),
					20);
			webelement = driver.findElement(By.xpath(
					"(//h2[contains(.,'Secondary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"));
			System.out.println("Got SecondaryInsuranceEligibilityEffectiveDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityEffectiveDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityEffectiveDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityTerminationDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"(//h2[contains(.,'Secondary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"),
					20);
			webelement = driver.findElement(By.xpath(
					"(//h2[contains(.,'Secondary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"));
			System.out.println("Got SecondaryInsuranceEligibilityTerminationDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityTerminationDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityTerminationDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityInNetworkCopay(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkCopay']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkCopay']"));
			System.out.println("Got SecondaryInsuranceEligibilityInNetworkCopay  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityInNetworkCopay  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityInNetworkCopay");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityOutNetworkCopay(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkCoPay']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkCoPay']"));
			System.out.println("Got SecondaryInsuranceEligibilityOutNetworkCopay  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityOutNetworkCopay  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityOutNetworkCopay");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityInNetworkDeductible(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkDeduct']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkDeduct']"));
			System.out.println("Got SecondaryInsuranceEligibilityInNetworkDeductible  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityInNetworkDeductible  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityInNetworkDeductible");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityOutNetworkDeductible(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkDeduct']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkDeduct']"));
			System.out.println("Got SecondaryInsuranceEligibilityOutNetworkDeductible  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityOutNetworkDeductible  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityOutNetworkDeductible");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SecondaryInsuranceEligibilityIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.isActive']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='SecondaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.isActive']"));
			System.out.println("Got SecondaryInsuranceEligibilityIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get SecondaryInsuranceEligibilityIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "SecondaryInsuranceEligibilityIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * TERTIARY INSURANCE CREATED BY VIVEK BADOLA
	 */
	public static WebElement TertiaryInsuranceTab(WebDriver driver) {
		try {
			Commons.scrollElementinotView(webelement, driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//a[@data-toggle='collapse']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='TertiaryInsurance']//a[@data-toggle='collapse']"));
			System.out.println("Got TertiaryInsuranceTab  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceTab  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "TertiaryInsuranceTab");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//button[@data-target='#Tertiary']"),
					20);
			webelement = driver
					.findElement(By.xpath("//div[@id='TertiaryInsurance']//button[@data-target='#Tertiary']"));
			System.out.println("Got TertiaryInsuranceMagnifier  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceMagnifier  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceMagnifier");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceName(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Tertiary']//input[@id='Tertiary_Name']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Tertiary']//input[@id='Tertiary_Name']"));
			System.out.println("Got TertiaryInsuranceName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceName  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "TertiaryInsuranceName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Tertiary']//input[@value='Search']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Tertiary']//input[@value='Search']"));
			System.out.println("Got TertiaryInsuranceSearchButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceSearchButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceSearchButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceCancelButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='Tertiary']//input[@value='Cancel']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='Tertiary']//input[@value='Cancel']"));
			System.out.println("Got TertiaryInsuranceCancelButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceCancelButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceCancelButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceList(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='InsuranceCodeTertiaryId']//tr[contains(@class,'ng-scope')]//td[2]"), 20);
			webelement = driver
					.findElements(
							By.xpath("//div[@id='InsuranceCodeTertiaryId']//tr[contains(@class,'ng-scope')]//td[2]"))
					.get(0);
			System.out.println("Got TertiaryInsuranceList  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceList  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "TertiaryInsuranceList");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceGroupNumberOrClaimNumber(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='TertiaryInsurance']//input[@name='tertiaryInsuranceFormgroupNumber']"), 20);
			webelement = driver.findElement(
					By.xpath("//div[@id='TertiaryInsurance']//input[@name='tertiaryInsuranceFormgroupNumber']"));
			System.out.println("Got TertiaryInsuranceGroupNumberOrClaimNumber  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceGroupNumberOrClaimNumber  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceGroupNumberOrClaimNumber");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePolicyNumberOrSSN(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='TertiaryInsurance']//input[@name='tertiaryInsuranceFormpolicyName']"), 20);
			webelement = driver.findElement(
					By.xpath("//div[@id='TertiaryInsurance']//input[@name='tertiaryInsuranceFormpolicyName']"));
			System.out.println("Got TertiaryInsurancePolicyNumberOrSSN  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePolicyNumberOrSSN  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePolicyNumberOrSSN");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceMSPtypeLabel(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='TertiaryInsurance']//label[contains(text(),'MSP Type')]"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='TertiaryInsurance']//label[contains(text(),'MSP Type')]"));
			System.out.println("Got TertiaryInsuranceMSPtypeLabel  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceMSPtypeLabel  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceMSPtypeLabel");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceSameAsPatientCheckbox(WebDriver driver) {
		webelement = null;
		try {
			/*
			 * Commons.waitforElement(driver, By.xpath(
			 * "//div[@id='TertiaryInsurance']//input[@ng-click='parentModel.copyPatientDetails(model);']"
			 * ), 20); webelement=driver.findElement(By.xpath(
			 * "//div[@id='TertiaryInsurance']//input[@ng-click='parentModel.copyPatientDetails(model);']"
			 * ));
			 */
			Commons.waitforElement(driver,
					By.xpath("//div[@id='TertiaryInsurance']//input[contains(@class,'sameAsPatient')]"), 40);
			webelement = driver
					.findElement(By.xpath("//div[@id='TertiaryInsurance']//input[contains(@class,'sameAsPatient')]"));
			System.out.println("Got TertiaryInsuranceSameAsPatientCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceSameAsPatientCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceSameAsPatientCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceSubscriberName(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//input[@name='subscriberName']"),
					20);
			webelement = driver.findElement(By.xpath("//div[@id='TertiaryInsurance']//input[@name='subscriberName']"));
			System.out.println("Got TertiaryInsuranceSubscriberName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceSubscriberName  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceSubscriberName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceSubscriberIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='TertiaryInsurance']//input[@ng-model='model.isActive']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='TertiaryInsurance']//input[@ng-model='model.isActive']"));
			System.out.println("Got TertiaryInsuranceSubscriberIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceSubscriberIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceSubscriberIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceRelationshipAsSelf(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//Option[@label='Self']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='TertiaryInsurance']//Option[@label='Self']"));
			System.out.println("Got TertiaryInsuranceRelationshipAsSelf  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceRelationshipAsSelf  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceRelationshipAsSelf");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceRelationshipAsEmployee(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//Option[@label='Employee']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='TertiaryInsurance']//Option[@label='Employee']"));
			System.out.println("Got TertiaryInsuranceRelationshipAsEmployee  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceRelationshipAsEmployee  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceRelationshipAsEmployee");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceRelationshipAsOther(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//Option[@label='Other']"), 20);
			webelement = driver.findElement(By.xpath("//div[@id='TertiaryInsurance']//Option[@label='Other']"));
			System.out.println("Got TertiaryInsuranceRelationshipAsOther  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceRelationshipAsOther  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceRelationshipAsOther");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * TERTIARY INSURANCE ADD-PREAUTHORIZATION CREATED BY VIVEK BADOLA
	 */
	public static WebElement TertiaryInsurancePreauthorizationAddButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='TertiaryInsurance']//button[@ng-click='preAuth();']"),
					20);
			webelement = driver.findElement(By.xpath("//div[@id='TertiaryInsurance']//button[@ng-click='preAuth();']"));
			System.out.println("Got TertiaryInsurancePreauthorizationAddButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationAddButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationAddButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePreauthorizationDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(preAuthModel,')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//button[contains(@ng-click,'parentModel.removeDynamicHtmlRows(preAuthModel,')]"));
			System.out.println("Got TertiaryInsurancePreauthorizationDeleteButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationDeleteButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePreauthorizationSelectCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='TertiaryInsurance']//div[@model='preAuthModel']//input[@name='isDelete']"),
					20);
			webelement = driver.findElement(
					By.xpath("//div[@id='TertiaryInsurance']//div[@model='preAuthModel']//input[@name='isDelete']"));
			System.out.println("Got TertiaryInsurancePreauthorizationSelectCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationSelectCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationSelectCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePreauthorizationNumber(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].preAuthNumber']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].preAuthNumber']"));
			System.out.println("Got TertiaryInsurancePreauthorizationNumber  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationNumber  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationNumber");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePreauthorizationFromDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("(//div[@id='TertiaryInsurance']//td)[3]//button"), 20);
			webelement = driver.findElement(By.xpath("(//div[@id='TertiaryInsurance']//td)[3]//button"));
			System.out.println("Got TertiaryInsurancePreauthorizationFromDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationFromDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationFromDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePreauthorizationThroughDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("(//div[@id='TertiaryInsurance']//td)[4]//button"), 20);
			webelement = driver.findElement(By.xpath("(//div[@id='TertiaryInsurance']//td)[4]//button"));
			System.out.println("Got TertiaryInsurancePreauthorizationThroughDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationThroughDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationThroughDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePreauthorizationVisits(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[contains(@name,'preAuthVisitUsed')]"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='PrimaryInsurance']//div[@model='preAuthModel']//input[contains(@name,'preAuthVisitUsed')]"));
			System.out.println("Got TertiaryInsurancePreauthorizationVisits  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationVisits  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationVisits");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsurancePreauthorizationIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isActive']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='preAuthModel']//input[@ng-model='model[$index].isActive']"));
			System.out.println("Got TertiaryInsurancePreauthorizationIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsurancePreauthorizationIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsurancePreauthorizationIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * TERTIARY INSURANCE ADD-ELIGIBILITY CREATED BY VIVEK BADOLA
	 */
	public static WebElement TertiaryInsuranceEligibiltyAddButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='TertiaryInsurance']//button[@ng-click='eligibility();']"), 20);
			webelement = driver
					.findElement(By.xpath("//div[@id='TertiaryInsurance']//button[@ng-click='eligibility();']"));
			System.out.println("Got TertiaryInsuranceEligibiltyAddButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyAddButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyAddButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyDeleteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//button[@ng-click='item.isDelete=true;parentModel.removeEligibility($index,insuranceName);']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//button[@ng-click='item.isDelete=true;parentModel.removeEligibility($index,insuranceName);']"));
			System.out.println("Got TertiaryInsuranceEligibiltyDeleteButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyDeleteButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyDeleteButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyContactName(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.contactName']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.contactName']"));
			System.out.println("Got TertiaryInsuranceEligibiltyContactName  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyContactName  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyContactName");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyEffectiveDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"(//h2[contains(.,'Tertiary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"),
					20);
			webelement = driver.findElement(By.xpath(
					"(//h2[contains(.,'Tertiary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[1]"));
			System.out.println("Got TertiaryInsuranceEligibiltyEffectiveDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyEffectiveDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyEffectiveDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyTerminationDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"(//h2[contains(.,'Tertiary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[2]"),
					20);
			webelement = driver.findElement(By.xpath(
					"(//h2[contains(.,'Tertiary Insurance Eligibility')]/following::button[contains(@class,'calenderbutton')])[2]"));
			System.out.println("Got TertiaryInsuranceEligibiltyTerminationDate  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyTerminationDate  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyTerminationDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyInNetworkCopay(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkCopay']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkCopay']"));
			System.out.println("Got TertiaryInsuranceEligibiltyInNetworkCopay  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyInNetworkCopay  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyInNetworkCopay");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyOutNetworkCopay(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkCoPay']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkCoPay']"));
			System.out.println("Got TertiaryInsuranceEligibiltyOutNetworkCopay  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyOutNetworkCopay  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyOutNetworkCopay");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyInNetworkDeductible(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkDeduct']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.inNetworkDeduct']"));
			System.out.println("Got TertiaryInsuranceEligibiltyInNetworkDeductible  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyInNetworkDeductible  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyInNetworkDeductible");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyOutNetworkDeductible(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkDeduct']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.outNetworkDeduct']"));
			System.out.println("Got TertiaryInsuranceEligibiltyOutNetworkDeductible  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyOutNetworkDeductible  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyOutNetworkDeductible");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TertiaryInsuranceEligibiltyIsActiveCheckbbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.isActive']"),
					20);
			webelement = driver.findElement(By.xpath(
					"//div[@id='TertiaryInsurance']//div[@model='eligibilityModel']//input[@ng-model='item.isActive']"));
			System.out.println("Got TertiaryInsuranceEligibiltyIsActiveCheckbbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get TertiaryInsuranceEligibiltyIsActiveCheckbbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "TertiaryInsuranceEligibiltyIsActiveCheckbbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseIsActiveCheckbox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@ng-model='vmCase.case.isActive']"), 20);
			webelement = driver.findElement(By.xpath("//input[@ng-model='vmCase.case.isActive']"));
			System.out.println("Got CaseIsActiveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseIsActiveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseIsActiveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseEditButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Edit Case')]"), 40);
			webelement = driver.findElement(By.xpath("//button[contains(.,'Edit Case')]"));
			System.out.println("Got CaseEditButton  webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseEditButton  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseEditButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement FOTOLink(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//a[@href='#FOTO']"), 40);
			webelement = driver.findElement(By.xpath("//a[@href='#FOTO']"));
			System.out.println("Got FOTO  webelement");
		} catch (Exception e) {
			System.out.println("Did not get FOTO  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseEditButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement RetrieveEpisode(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Retrieve Episode')]"), 40);
			webelement = driver.findElement(By.xpath("//button[contains(.,'Retrieve Episode')]"));
			System.out.println("Got Retrieve Episode  webelement");
		} catch (Exception e) {
			System.out.println("Did not get Retrieve Episode  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseEditButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement RetrieveFOTOReport(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Retrieve FOTO Report')]"), 40);
			webelement = driver.findElement(By.xpath("//button[contains(.,'Retrieve FOTO Report')]"));
			System.out.println("Got Retrieve FOTO Report webelement");
		} catch (Exception e) {
			System.out.println("Did not get Retrieve FOTO Report  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseEditButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * REQUIRED FIELD REMOVE IN CASE CREATED BY MAHESH YADAV
	 */
	public static WebElement CaseAssignmentDateRemove(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='assignmentdate']//input"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='assignmentdate']//input"));
			System.out.println("Got CaseAssignmentDateRemove webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseAssignmentDateRemove  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseAssignmentDateRemove
			// ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseEffectiveDateRemove(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='effectivedate']//input"), 20);
			webelement = driver.findElement(By.xpath("//*[@id='effectivedate']//input"));
			System.out.println("Got CaseEffectiveDateRemove webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseEffectiveDateRemove  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CaseEffectiveDateRemove
			// ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseReferringPhysicianRemoveButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//button[contains(@data-ng-mousedown,'vmCase.physicianCancel(true)')]"), 20);
			webelement = driver
					.findElement(By.xpath("//button[contains(@data-ng-mousedown,'vmCase.physicianCancel(true)')]"));
			System.out.println("Got CaseReferringPhysicianRemoveButton webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseReferringPhysicianRemoveButton webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "CaseReferringPhysicianRemoveButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseLocationRemoveButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(@data-ng-click,'vmCase.clearPlaceOfService')]"),
					20);
			webelement = driver
					.findElement(By.xpath("//button[contains(@data-ng-click,'vmCase.clearPlaceOfService')]"));
			System.out.println("Got CaseLocationRemoveButton webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseLocationRemoveButton webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "CaseLocationRemoveButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseBillingProviderRemoveButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(@data-ng-click,'vmCase.clearBilingProvider')]"),
					20);
			webelement = driver
					.findElement(By.xpath("//button[contains(@data-ng-click,'vmCase.clearBilingProvider')]"));
			System.out.println("Got CaseBillingProviderRemoveButton webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseBillingProviderRemoveButton webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "CaseBillingProviderRemoveButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CaseICD10CodeRemoveCheckbox(WebDriver driver) {
		try {
			Commons.scrollElementinotView(driver.findElement(By.xpath("//input[@ng-model='id']")), driver);
			Commons.waitforElement(driver, By.xpath("//input[@ng-model='id']"), 20);
			webelement = driver.findElement(By.xpath("//input[@ng-model='id']"));
			System.out.println("Got CaseICD10CodeRemoveCheckbox  webelement");
		} catch (Exception e) {
			System.out.println("Did not get CaseICD10CodeRemoveCheckbox  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "CaseICD10CodeRemoveCheckbox");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static WebElement ReferringPhysicianList_MDcode(WebDriver driver) { // selecting
																				// the
																				// first
																				// referring
																				// physician
																				// from
																				// list
																				// on
																				// the
																				// basis
																				// of
																				// MD
																				// code
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='ReferringPhysicianId']//tr[contains(@class,'ng-scope')]"), 20);
			webelement = driver
					.findElements(By.xpath("//div[@id='ReferringPhysicianId']//tr[contains(@class,'ng-scope')]//td"))
					.get(0);
			System.out.println("Got Referring Physician List_MDcode webelement");
		} catch (Exception e) {
			System.out.println("Did not get Referring Physician List_MDcode List  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "ReferringPhysicianList_MDcode ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CloneCaseRadioButtonFirstCase(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//input[@type='radio' and @name='cloneCase']"), 20);
			webelement = driver.findElement(By.xpath("//input[@type='radio' and @name='cloneCase']"));
			System.out.println("Got Clone Case Radio Button for Most Recent Case webelement");
		} catch (Exception e) {
			System.out.println("Did not get Case Radio Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage",
			// "CloneCaseRadioButtonFirstCase");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MDCode_SecondryReferingphysician(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='SecondReferringPhysician']//input[@id='SRP_MDCode']"),
					20);
			webelement = driver.findElement(By.xpath("//div[@id='SecondReferringPhysician']//input[@id='SRP_MDCode']"));
			System.out.println("Found MD Code field for  refering physician");
		} catch (Exception e) {
			System.out.println("****MD Code field for Secondry refering physician not found*****");
			// Commons.ScreenPrint(driver, "CasePage", " Secondry
			// Referingphysician");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static WebElement CloneCaseGreenButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.scrollElementinotView(driver.findElement(By.xpath("//button[@ng-click='vmPatient.checkClone();']")),
					driver);
			Commons.waitforElement(driver, By.xpath("//button[@ng-click='vmPatient.checkClone();']"), 20);
			webelement = driver.findElement(By.xpath("//button[@ng-click='vmPatient.checkClone();']"));
			System.out.println("Got Clone Case Green Button webelement");
		} catch (Exception e) {
			System.out.println("Did not get Clone Case Green Button  webelement");
			// Commons.ScreenPrint(driver, "CasePage", "CloneCaseGreenButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	//// input[@value='Yes']
	public static WebElement CloneCasePopUpYesButton(WebDriver driver) {
		webelement = null;
		try {
			Commons.waitforElement(driver, By.xpath("//input[contains(@data-toggle,'modal')][@value='Yes']"), 20);
			webelement = driver.findElement(By.xpath("//input[contains(@data-toggle,'modal')][@value='Yes']"));
			System.out.println("Got Yes Button webelement on Clone Case pop up window");
		} catch (Exception e) {
			System.out.println("Did not get Yes Button webelement on Clone Case PopUp ");
			// Commons.ScreenPrint(driver, "CasePage", "CloneCaseGreenButton");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	
}
