package UIMap;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;

public class CreateNotePage {
	public static WebElement webelement;
	public static List<WebElement> webelementList;

	public static WebElement EMROnMenu(WebDriver driver) {
		try {
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("tabletchrome")) {
				Commons.waitforElement(driver, By.xpath("//*[@id='menu-toggle']/i"), 90);
				driver.findElement(By.xpath("//*[@id='menu-toggle']/i")).click();
				Commons.Explicitwait();
				Commons.Explicitwait();
			}
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='EMR']/.."), 40);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='EMR']/.."));
			System.out.println("Got EMR on Menu webelement");
		} catch (Exception e) {
			System.out.println("Did not get EMR on Menu webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SignButton(WebDriver driver) {
		try {
			if (!Commons.ExistandDisplayElement(driver, By.xpath("//button[contains(.,'Sign')]"), 190)) {
				System.out.println("****************************Refreshing Browser*******************");
				driver.navigate().refresh();
			}
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Sign')]"), 190);
			webelement = driver.findElement(By.xpath("//button[contains(.,'Sign')]"));
			System.out.println("Got Sign Button");
		} catch (Exception e) {
			System.out.println("Did not Get Sign Button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AddClinicalNote(WebDriver driver) {
		try {
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Add Clinical Note')]"), 60);
			webelement = driver.findElement(By.xpath("//span[contains(text(),'Add Clinical Note')]"));
			System.out.println("Got AddClinicalNote on Menu webelement");
		} catch (Exception e) {
			
			System.out.println("Did not get AddClinicalNote on Menu webelement Retrying...");
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Add Clinical Note')]"), 60);
			webelement = driver.findElement(By.xpath("//span[contains(text(),'Add Clinical Note')]"));
			System.out.println("Got AddClinicalNote on Menu webelement");
			//Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	// select[@id='ddlCases']/option[2]
	public static WebElement CaseNameddl(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlCases']/option[2]"), 60);
			webelement = driver.findElement(By.xpath("//select[@id='ddlCases']/option[2]"));
			System.out.println("Got Case Name dropdown list");
		} catch (TimeoutException e) {
			ActionUtils.click(CreateNotePage.EMROnMenu(driver));
			ActionUtils.click(CreateNotePage.AddClinicalNote(driver));
			Commons.waitforElement(driver, By.xpath("//select[@id='ddlCases']/option[2]"), 90);
			System.out.println("Opened Create Note Screen");
			webelement = driver.findElement(By.xpath("//select[@id='ddlCases']/option[2]"));
			System.out.println("Got Case Name dropdown list");
		} catch (Exception e) {
			System.out.println("Did not get Case Name dropdown list");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement VisitTypeddl(WebDriver driver, String i) {
		try {
			Commons.SelectElement(driver, By.xpath("//select[@name='ddlVisitType']"), i);
			Commons.waitforElement(driver, By.xpath("//option[@label='" + i + "']"), 60);
			webelement = driver.findElement(By.xpath("//option[@label='" + i + "']"));
			System.out.println("Got dropdown list Visit Type " + i);
		} catch (Exception e) {
			System.out.println("Did not get dropdown list Visit Type " + i);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DateofService(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//*[@id='DateOfService']//span//button[contains(@ng-click,'DateOfService')]"), 30);
			webelement = driver.findElement(
					By.xpath("//*[@id='DateOfService']//span//button[contains(@ng-click,'DateOfService')]"));
			System.out.println("Got Date Of Service");
		} catch (Exception e) {
			System.out.println("Did not get Got Date Of Service");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement DateofDischarge(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='dvDischargeDate']//button"), 30);
			webelement = driver.findElement(By.xpath("//div[@id='dvDischargeDate']//button"));
			System.out.println("Got Date Of Discharge");
		} catch (Exception e) {
			System.out.println("Did not get Date Of Discharge");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CreateNoteButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='btnCreateNote']"), 30);
			webelement = driver.findElement(By.xpath("//*[@id='btnCreateNote']"));
			System.out.println("Got Create Note Button");
		} catch (Exception e) {
			System.out.println("Did not get Create Note Button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement NonVisitStatement(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//*[@id='dvNonVisitStatement']//following::textarea[@name='nonVisitStatement']"), 30);
			webelement = driver.findElement(
					By.xpath("//*[@id='dvNonVisitStatement']//following::textarea[@name='nonVisitStatement']"));
			System.out.println("Got Non-Visit statement Text area");
		} catch (Exception e) {
			System.out.println("Did not get Non-Visit statement Text area");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicalRecordlink(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@class='row btnsection']//a[contains(text(),'Medical Record')]"), 100);
			webelement = driver
					.findElement(By.xpath("//div[@class='row btnsection']//a[contains(text(),'Medical Record')]"));
			System.out.println("Got Medical record hyper link");
		} catch (Exception e) {
			System.out.println("Did not get medical record hyper link");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MedicalRecordExpandlink(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//*[@id='gridMedicalRecordsDetails']/table/tbody/tr[1]/td[1]/a[contains(@class,'expand')]"),
					20);
			Commons.scrollElementinotView(
					driver.findElement(By
							.xpath("//*[@id='gridMedicalRecordsDetails']/table/tbody/tr[1]/td[1]/a[contains(@class,'expand')]")),
					driver);
			Commons.waitforElement(driver,
					By.xpath(
							"//*[@id='gridMedicalRecordsDetails']/table/tbody/tr[1]/td[1]/a[contains(@class,'expand')]"),
					80);
			webelement = driver.findElement(By.xpath(
					"//*[@id='gridMedicalRecordsDetails']/table/tbody/tr[1]/td[1]/a[contains(@class,'expand')]"));
			System.out.println("Got Medical record Expand link");
		} catch (Exception e) {
			System.out.println("Did not get medical record Expand link");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	// *[contains(text(),'Sign and Finalize')]
	public static WebElement SignandFinalizeButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(.,'Sign Note')]"), 190);
			ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Sign and Finalize')]"), 190);
			webelement = driver.findElement(By.xpath("//button[contains(.,'Sign and Finalize')]"));
			System.out.println("Got Sign and Finalize Button");
		} catch (WebDriverException e) {
			// System.out.println("Did not Get Sign and Finalize Button");
			// Assert.assertFalse(true,Throwables.getStackTraceAsString (e));
			try {
				ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Sign Note')]")));
				Commons.waitforElement(driver, By.xpath("//button[contains(.,'Sign and Finalize')]"), 190);
			} catch (Exception e1) {
				System.out.println("Did not Get Sign and Finalize Button");
				Assert.assertFalse(true, "Did not Get Sign and Finalize Button" + Throwables.getStackTraceAsString(e1));
			}
		}
		return webelement;
	}

	// *[@id='exam-templates']/div[1]//input[@value='Add All']
	// *******************************//******************************************//*************************************
	public static List<WebElement> AvailableBodyParts(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//span"), 15);
			webelementList = driver
					.findElements(By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//span"));
			System.out.println("Got list of body parts");
		} catch (Exception e) {
			System.out.println("Did not get list of body parts");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelementList;
	}

	public static List<WebElement> AddedBodyParts(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[2]//span"), 5);
			webelementList = driver
					.findElements(By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[2]//span"));
			System.out.println("Got added list of body parts");
		} catch (Exception e) {
			System.out.println("Did not get added list of body parts");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelementList;
	}

	public static List<WebElement> AvailablePublicTemplates(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[1]//span"), 5);
			webelementList = driver
					.findElements(By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[1]//span"));
			System.out.println("Got list Available Custom  Templates");
		} catch (Exception e) {
			System.out.println("Did not get list Available Custom  Templates");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelementList;
	}

	public static List<WebElement> AddedPublicTemplates(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[2]//span"), 5);
			webelementList = driver
					.findElements(By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[2]//span"));
			System.out.println("Got list of Added Custom Templates");
		} catch (Exception e) {
			System.out.println("Did not get list of Added Custom Templates");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelementList;
	}

	public static List<WebElement> AvailablePersonalTemplates(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//span"), 5);
			webelementList = driver
					.findElements(By.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//span"));
			System.out.println("Got list Available Custom  Templates");
		} catch (Exception e) {
			System.out.println("Did not get list Available Custom  Templates");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelementList;
	}

	public static List<WebElement> AddedPersonalTemplates(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@title-details='vmNote.customTitle']/div/div[2]//span"), 5);
			webelementList = driver
					.findElements(By.xpath("//div[@title-details='vmNote.customTitle']/div/div[2]//span"));
			System.out.println("Got list of Added Custom Templates");
		} catch (Exception e) {
			System.out.println("Did not get list of Added Custom Templates");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelementList;
	}

	public static WebElement AddSelect(WebDriver driver, String Addtype) {
		switch (Addtype) {
		case "bodyparts":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Add Selected']"),
						20);
				webelement = driver.findElement(By
						.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Add Selected']"));
				System.out.println("Got AddSelect button");
			} catch (Exception e) {
				System.out.println("Did not get AddSelect button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PersonalTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Add Selected']"),
						20);
				webelement = driver.findElement(By
						.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Add Selected']"));
				System.out.println("Got AddSelect button");
			} catch (Exception e) {
				System.out.println("Did not get AddSelect button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PublicTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Add Selected']"),
						20);
				webelement = driver.findElement(By
						.xpath("//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Add Selected']"));
				System.out.println("Got AddSelect button");
			} catch (Exception e) {
				System.out.println("Did not get AddSelect button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		}
		return webelement;
	}

	public static WebElement AddAll(WebDriver driver, String Addtype) {
		switch (Addtype) {
		case "bodyparts":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Add All']"), 20);
				webelement = driver.findElement(
						By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Add All']"));
				System.out.println("Got Add all Select button");
			} catch (Exception e) {
				System.out.println("Did not get Add all Select button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PersonalTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Add All']"), 20);
				webelement = driver.findElement(
						By.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Add All']"));
				System.out.println("Got Add all Select button");
			} catch (Exception e) {
				System.out.println("Did not get Add all Select button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PublicTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Add All']"),
						20);
				webelement = driver.findElement(
						By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Add All']"));
				System.out.println("Got Add all Select button");
			} catch (Exception e) {
				System.out.println("Did not get Add all Select button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		}
		return webelement;
	}

	public static WebElement RemoveSelected(WebDriver driver, String Addtype) {
		switch (Addtype) {
		case "bodyparts":
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Remove Selected']"),
						20);
				webelement = driver.findElement(By.xpath(
						"//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Remove Selected']"));
				System.out.println("Got RemoveSelected button");
			} catch (Exception e) {
				System.out.println("Did not get RemoveSelected button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PersonalTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Remove Selected']"),
						20);
				webelement = driver.findElement(By.xpath(
						"//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Remove Selected']"));
				System.out.println("Got RemoveSelected button");
			} catch (Exception e) {
				System.out.println("Did not get RemoveSelected button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PublicTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath(
								"//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Remove Selected']"),
						20);
				webelement = driver.findElement(By.xpath(
						"//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Remove Selected']"));
				System.out.println("Got RemoveSelected button");
			} catch (Exception e) {
				System.out.println("Did not get RemoveSelected button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		}
		return webelement;
	}

	public static WebElement RemoveAll(WebDriver driver, String Addtype) {
		switch (Addtype) {
		case "bodyparts":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Remove All']"),
						20);
				webelement = driver.findElement(
						By.xpath("//div[@title-details='vmNote.globalTitle']/div/div[1]//input[@value='Remove All']"));
				System.out.println("Got RemoveAll button");
			} catch (Exception e) {
				System.out.println("Did not get RemoveAll button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PersonalTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Remove All']"),
						20);
				webelement = driver.findElement(
						By.xpath("//div[@title-details='vmNote.customTitle']/div/div[1]//input[@value='Remove All']"));
				System.out.println("Got RemoveAll button");
			} catch (Exception e) {
				System.out.println("Did not get RemoveAll button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		case "PublicTemplates":
			try {
				Commons.waitforElement(driver,
						By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Remove All']"),
						20);
				webelement = driver.findElement(
						By.xpath("//div[@title-details='vmNote.companyTitle']/div/div[1]//input[@value='Remove All']"));
				System.out.println("Got RemoveAll button");
			} catch (Exception e) {
				System.out.println("Did not get RemoveAll button");
				Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
			}
			break;
		}
		return webelement;
	}

	public static WebElement StartofCareDate(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//*[@id='StartofCare']/span//button[contains(@ng-click,'StartofCareDate')]"), 50);
			webelement = driver.findElement(
					By.xpath("//*[@id='StartofCare']/span//button[contains(@ng-click,'StartofCareDate')]"));
			System.out.println("Got Start of Care Date webelement");
		} catch (Exception e) {
			System.out.println("Did not get Start of Care Date webelement");
			// Commons.ScreenPrint(driver, "CasePage", "StartofCareDate");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SearchCompanybutton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@*='Search']"), 50);
			webelement = driver.findElement(By.xpath("//input[@*='Search']"));
			System.out.println("Got Search Button For Company");
		} catch (Exception e) {
			System.out.println("Did not get Search Button For Company");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement RedButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//span[@class='clinicalNoteStatus note-red']"), 60);
			webelement = driver.findElement(By.xpath("//span[@class='clinicalNoteStatus note-red']"));
			System.out.println("Got Red button ");
		} catch (Exception e) {
			System.out.println("Did not get Red button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement HomeButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'Home')]"), 60);
			webelement = driver.findElement(By.xpath("//span[contains(.,'Home')]"));
			System.out.println("Got Home button ");
		} catch (Exception e) {
			System.out.println("Did not get Home button ");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement SignandAttachScannedDocButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(.,'Sign and Attach Scanned Documents')]"), 90);
			webelement = driver.findElement(By.xpath("//button[contains(.,'Sign and Attach Scanned Documents')]"));
			System.out.println("Got Sign and Attach Scanned Doc Button");
		} catch (Exception e) {
			System.out.println("Did not Get Attach Scanned Doc Button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	public static WebElement BillingProviderMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(@ng-click,'openBillingProviderSearch')]"), 40);
			webelement = driver.findElement(By.xpath("//button[contains(@ng-click,'openBillingProviderSearch')]"));
			System.out.println("Got Billing Provider Magnifier On Sign Note page");
		} catch (Exception e) {
			System.out.println("Did not get Billing Provider Magnifier On Sign Note Page");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	public static WebElement SelectProcCodeMagnifier(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='chargeCaptureVisible']//button[contains(@ng-click,'openProcedureCode')]"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='chargeCaptureVisible']//button[contains(@ng-click,'openProcedureCode')]"));
			System.out.println("Got Select Proc Code Magnifier On Charge Capture page");
		} catch (Exception e) {
			System.out.println("Did not get Select Proc Code Magnifier On Charge Capture page");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	public static WebElement ProcCodeInputBox(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='GetProcedureCodeChargeCapture']//input[@id='ProcCode']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='GetProcedureCodeChargeCapture']//input[@id='ProcCode']"));
			System.out.println("Got Proc Code Input box On Procedure Code Search pop up");
		} catch (Exception e) {
			System.out.println("Did not get Proc Code Input box On Procedure Code Search pop up");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	public static WebElement ProcCodeSearchButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='GetProcedureCodeChargeCapture']//input[@id='Search']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='GetProcedureCodeChargeCapture']//input[@id='Search']"));
			System.out.println("Got Proc Code search button On Procedure Code Search pop up");
		} catch (Exception e) {
			System.out.println("Did not get Proc Code search button On Procedure Code Search pop up");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}