package UIMap;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestBase.TestSetUp;
import Utils.ActionUtils;
import Utils.Commons;
import com.google.common.base.Throwables;

public class SchedulerPage {
	public static WebElement webelement;

	public static WebElement ScheduleOnMenu(WebDriver driver) {
		try {
			if (TestSetUp.CurrentBrowserName.equalsIgnoreCase("tabletchrome")) {
				Commons.Explicitwait();
				Commons.waitforElement(driver, By.xpath("//*[@id='menu-toggle']/i"), 90);
				if (!driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Schedule']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//*[@id='menu-toggle']/i")).click();
				}
				Commons.Explicitwait();
				Commons.Explicitwait();
			}
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='Schedule']"), 80);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Schedule']"));
			System.out.println("Got Schedule on Menu webelement");
		} catch (Exception e) {
			System.out.println("Did not get Schedule on Menu webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ViewScheduleButton(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='View Schedule']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='View Schedule']"));
			System.out.println("Got View Schedule on Sub Menu");
		} catch (WebDriverException e) {
			ActionUtils.click(ScheduleOnMenu(driver));
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='View Schedule']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='View Schedule']"));
			System.out.println("Got View Schedule on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get View Schedule on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement LoadingSpinnerViewschedule(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//span[contains(text(),'Loading')]"), 40);
			webelement = driver.findElement(By.xpath("//span[contains(text(),'Loading')]"));
			System.out.println("Loading Page..");
		} catch (Exception e) {
			System.out.println("Did not get Spinner Webelement");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ListViewButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='ListViewli']/a"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='ListViewli']/a"));
			System.out.println("Got List View Button");
		} catch (Exception e) {
			System.out.println("Did not get List View Button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CloseButtonListView(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='case']/button[contains(text(),'Close')]"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='case']/button[contains(text(),'Close')]"));
			System.out.println("Got Close Button on List View");
		} catch (Exception e) {
			System.out.println("Did not get Close Button on list View");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement TimeSlot(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//td[@aria-selected='false']"), 60);
			int size = driver.findElements(By.xpath("//td[@aria-selected='false']")).size();
			webelement = driver.findElements(By.xpath("//td[@aria-selected='false']"))
					.get(new Random().nextInt(size - 1));
			Commons.scrollElementinotView(webelement, driver);
			System.out.println("Got Time slot for Appointment");
		} catch (Exception e) {
			System.out.println("Did not get Time slot for Appointment");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * Below Elements for *******Appointment window ***** after selecting Time
	 * slot
	 */
	public static WebElement QuickAddPatientbutton(WebDriver driver) {
		try {
			Commons.existsElement(driver, By.xpath("//button[@id='quickAddPatient']"));
			Commons.waitforElement(driver, By.xpath("//button[@id='quickAddPatient']"), 40);
			webelement = driver.findElement(By.xpath("//button[@id='quickAddPatient']"));
			System.out.println("Got Quick Add patient button");
		} catch (Exception e) {
			System.out.println("Did not get Quick Add patient button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ScheduleMeetingbutton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Schedule Meeting')]"), 40);
			webelement = driver.findElement(By.xpath("//button[contains(text(),'Schedule Meeting')]"));
			System.out.println("Got Schedule Meeting button");
		} catch (Exception e) {
			System.out.println("Did not get Schedule Meeting button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ClearPatientbutton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//button[contains(text(),'Clear Patient')]"), 40);
			webelement = driver.findElement(By.xpath("//button[contains(text(),'Clear Patient')]"));
			System.out.println("Got Clear Patient button");
		} catch (Exception e) {
			System.out.println("Did not get Clear Patient button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CloneCasebutton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@id='cloneCaseWindow']"), 40);
			webelement = driver.findElement(By.xpath("//input[@id='cloneCaseWindow']"));
			System.out.println("Got Clone Case button");
		} catch (Exception e) {
			System.out.println("Did not get Clone Case button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Savebutton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Save')]"), 40);
			webelement = driver.findElement(By.xpath("//a[contains(text(),'Save')]"));
			System.out.println("Got Appointment Save button");
		} catch (Exception e) {
			System.out.println("Did not get Appointment Save button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Cancelbutton(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Cancel')]"), 40);
			webelement = driver.findElement(By.xpath("//a[contains(text(),'Cancel')]"));
			System.out.println("Got Appointment Cancel button");
		} catch (Exception e) {
			System.out.println("Did not get Appointment Cancel button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientSearchfield(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//md-autocomplete[@id='autosearchPatientScheduler']"), 40);
			webelement = driver.findElement(By.xpath("//md-autocomplete[@id='autosearchPatientScheduler']"));
			System.out.println("Got Patient Search Field");
		} catch (Exception e) {
			System.out.println("Did not get Patient Search Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Casefield(WebDriver driver) {
		try { // *[@id='caseDropDown']
			Commons.waitforElement(driver, By.xpath("//*[@id='caseDropDown']//span[@class='k-input']"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='caseDropDown']//span[@class='k-input']"));
			System.out.println("Got Case Field");
		} catch (Exception e) {
			System.out.println("Did not get Case Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
       
	public static WebElement CloseAppointmentWindow(WebDriver driver) {
		try { // *[@id='caseDropDown']
			Commons.waitforElement(driver, By.xpath("//span[contains(.,'Appointment ')][contains(@class,'k-window-title')]//following::div[1]//a[contains(@aria-label,'Close')]"), 40);
			webelement = driver.findElement(By.xpath("//span[contains(.,'Appointment ')][contains(@class,'k-window-title')]//following::div[1]//a[contains(@aria-label,'Close')]"));
			System.out.println("Got Close [x] button on Appointment window");
		} catch (Exception e) {
			System.out.println("Did not get Close [x] button on Appointment window");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	
	
	
	
	
	
	
	public static WebElement Typefield(WebDriver driver) {
		try {
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"))); // select
																													// dropdown
																													// button
																													// for
																													// type
			Thread.sleep(1000);
			System.out.println("Clicked on type dropdown field");
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='typeCode-list']//ul[@id='typeCode_listbox']//li[1]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='typeCode-list']//ul[@id='typeCode_listbox']//li[1]"));
			System.out.println("Got Type Field");
		} catch (Exception e) {
			System.out.println("Did not get Type Field" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Did not get Type Field");
		}
		return webelement;
	}

	public static WebElement GroupTypefield(WebDriver driver) {
		try {
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"))); // select
																													// dropdown
																													// button
																													// for
																													// type
			Thread.sleep(1000);
			System.out.println("Clicked on type dropdown field");
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='typeCode-list']//ul[@id='typeCode_listbox']//li[contains(text(),'Group')]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='typeCode-list']//ul[@id='typeCode_listbox']//li[contains(text(),'Group')]"));
			System.out.println("Got group Type Field");
		} catch (Exception e) {
			System.out.println("Did not get group Type Field" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Did not get group Type Field");
		}
		return webelement;
	}

	public static WebElement SelectType(WebDriver driver, String type) {
		try {
			Thread.sleep(1000);
			Commons.waitforElement(driver, By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"), 40);
			ActionUtils.click(driver.findElement(By.xpath("//div[@id='typeCodeDiv']//span[@class='k-select']"))); // select
																													// dropdown
																													// button
																													// for
																													// type
			Thread.sleep(1000);
			System.out.println("Clicked on type dropdown field");
			Commons.ExistandDisplayElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='typeCode-list']//ul[@id='typeCode_listbox']//li[contains(.,'"
									+ type + "')]"),
					40);
			webelement = driver.findElement(By
					.xpath("//div[@*='k-animation-container']//div[@id='typeCode-list']//ul[@id='typeCode_listbox']//li[contains(.,'"
							+ type + "')]"));
			System.out.println("Got Type Field" + type);
		} catch (Exception e) {
			System.out.println("Did not get Type Field" + Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true, "Did not get Type Field" + type);
		}
		return webelement;
	}

	public static WebElement SelectStatus(WebDriver driver, String status) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[text()='"
									+ status + "']"),
					40);
			webelement = driver.findElement(By
					.xpath("//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[text()='"
							+ status + "']"));
			System.out.println("Got Status field Field" + status);
		} catch (Exception e) {
			System.out.println("Did not get status Field" + status);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Providerfield(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='providerDropDownDiv']//span[contains(@class,'k-select')]"), 40);
			driver.findElement(By.xpath("//div[@id='providerDropDownDiv']//span[contains(@class,'k-select')]")).click(); // select
																															// dropdown
																															// button
																															// for
																															// type
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath("//ul[@id='providerDropDown_listbox']/li[contains(text(),'Automation BMS Provider')]"),
					40);
			Commons.scrollElementinotView(
					driver.findElement(By
							.xpath("//ul[@id='providerDropDown_listbox']/li[contains(text(),'Automation BMS Provider')]")),
					driver);
			webelement = driver.findElement(
					By.xpath("//ul[@id='providerDropDown_listbox']/li[contains(text(),'Automation BMS Provider')]"));
			System.out.println("Got Provider Field");
		} catch (Exception e) {
			System.out.println("Did not get Provider Field" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement locationfield(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@*='locationDropDownDiv']//span[contains(@class,'k-select')]"), 40);
			driver.findElement(By.xpath("//div[@*='locationDropDownDiv']//span[contains(@class,'k-select')]")).click(); // select
																														// dropdown
																														// button
																														// for
																														// Location
			Thread.sleep(2000);
			Commons.waitforElement(driver,
					By.xpath("(//ul[@id='location_listbox']//li[contains(text(),'Automation BMS')])[last()]"), 40);
			webelement = driver
					.findElement(By.xpath("(//ul[@id='location_listbox']//li[contains(text(),'Automation BMS')])[last()]"));
			System.out.println("Got Location Field");
		} catch (Exception e) {
			System.out.println("Did not get Location Field" + e);
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Statusfield(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Scheduled')]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Scheduled')]"));
			System.out.println("Got Status field Field");
		} catch (Exception e) {
			System.out.println("Did not get status Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Recurrencefield(WebDriver driver, String Duration) {
		try {
			Commons.waitforElement(driver, By.xpath("(//div[@name='recurrencePattern']//span)[1]"), 30);
			ActionUtils.click(driver.findElement(By.xpath("(//div[@name='recurrencePattern']//span)[1]")));
			Thread.sleep(2000);
			Commons.waitforElement(driver,
					By.xpath("//div[@*='k-animation-container']//li[contains(.,'" + Duration + "')]"), 40);
			webelement = driver
					.findElement(By.xpath("//div[@*='k-animation-container']//li[contains(.,'" + Duration + "')]"));
			System.out.println("Got Recurrance field Field");
		} catch (Exception e) {
			System.out.println("Did not get Recurrance Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement Checkboxreminder(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@data-container-for='24hr']//input[@type='checkbox']"), 40);
			webelement = driver.findElement(By.xpath("//div[@data-container-for='24hr']//input[@type='checkbox']"));
			System.out.println("Got Reminder Checkbox  Field");
		} catch (Exception e) {
			System.out.println("Did not get reminder Checkbox  Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ResourceField(WebDriver driver,String Resource) {
		try {
			
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='facResourceCode']//span[@class='k-select']//span"), 40);
			driver.findElement(By.xpath("//*[@data-container-for='facResourceCode']//span[@class='k-select']//span")).click();
			Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='resourceCode_listbox']//li[contains(.,'"+Resource+"')]"),10);
			webelement = driver.findElement(By.xpath("//*[@id='resourceCode_listbox']//li[contains(.,'"+Resource+"')]"));
			System.out.println("Got Resource Field");
		} catch (Exception e) {
			System.out.println("Did not get Resource Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement AppointmentNote(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//textarea[@name='description']"), 40);
			webelement = driver.findElements(By.xpath("//textarea[@name='description']")).get(0);
			System.out.println("Got Appointent Note Field");
		} catch (Exception e) {
			System.out.println("Did not get Appointment Note Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	/*
	 * Below Elements for *******Quick add patient under Appointment Window*****
	 * after selecting Time slot
	 */
	public static WebElement firstname(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='firstname']"), 60);
			webelement = driver.findElement(By.xpath("//input[@name='firstname']"));
			System.out.println("Got Firstname Field");
		} catch (Exception e) {
			System.out.println("Did not get Firstname Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement lastname(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='lastname']"), 40);
			webelement = driver.findElement(By.xpath("//input[@name='lastname']"));
			System.out.println("Got Lastname Field");
		} catch (Exception e) {
			System.out.println("Did not get Lastname Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement homenumber(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='homenumber']"), 40);
			webelement = driver.findElement(By.xpath("//input[@name='homenumber']"));
			System.out.println("Got Home number Field");
		} catch (Exception e) {
			System.out.println("Did not get Home number Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement gender(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//select[@name='gender']/option[2]"), 40);
			webelement = driver.findElement(By.xpath("//select[@name='gender']/option[2]"));
			System.out.println("Got Gender Field");
		} catch (Exception e) {
			System.out.println("Did not get Gender Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement casename(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//input[@name='caseName']"), 40);
			webelement = driver.findElement(By.xpath("//input[@name='caseName']"));
			System.out.println("Got Case name Field");
		} catch (Exception e) {
			System.out.println("Did not get Case name Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement newcasename(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//*[@id='schedulerCase']/input"), 40);
			webelement = driver.findElement(By.xpath("//*[@id='schedulerCase']/input"));
			System.out.println("Got New Case name Field");
		} catch (Exception e) {
			System.out.println("Did not get New Case name Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PatientSave(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='quickAddPatientTemplate']//button[contains(text(),'Save')]"), 40);
			webelement = driver
					.findElement(By.xpath("//div[@id='quickAddPatientTemplate']//button[contains(text(),'Save')]"));
			System.out.println("Got Case name Field");
		} catch (Exception e) {
			System.out.println("Did not get Case name Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement FutureDayNavigatetionArrow(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//li[contains(@class,'k-nav-next')]"), 40);
			webelement = driver.findElement(By.xpath("//li[contains(@class,'k-nav-next')]"));
			System.out.println("Got Arrow  button for Future Day Navigation");
		} catch (Exception e) {
			System.out.println("Did not get Arrow  button for Day Navigation");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PastDayNavigatetionArrow(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//li[contains(@class,'k-nav-prev')]"), 40);
			webelement = driver.findElement(By.xpath("//li[contains(@class,'k-nav-prev')]"));
			System.out.println("Got Arrow  button for Past Day Navigation");
		} catch (Exception e) {
			System.out.println("Did not get Arrow  button for Day Navigation");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement MeetingAppintmentNote(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@class='scheduler-form scheduler-appointment']//textarea[@name='description']"),
					40);
			webelement = driver.findElement(
					By.xpath("//div[@class='scheduler-form scheduler-appointment']//textarea[@name='description']"));
			System.out.println("Got Appointment Notes Field");
		} catch (Exception e) {
			System.out.println("Did not get Appointment Notes Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement eventTemplate(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"), 10);
			Commons.waitforElement(driver, By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"),
					40);
			// int size =
			// driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(),
			// 'Automate')]")).size();
			List<WebElement> alltemplate=driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]"));
			Random rand = new Random();
			webelement = driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'Auto')]")).get(rand.nextInt(alltemplate.size()));
			Commons.scrollElementinotView(webelement, driver);
			System.out.println("Got Existing Appointment Field");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
		} catch (Exception e) {
			System.out.println("Did not get Existing Appointment");
			Assert.assertFalse(true, "Did not get Existing Appointment" + Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement eventTemplateCancel(WebDriver driver) {
		try {
			Commons.ExistandDisplayElement(driver,
					By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'CancelQA')]"), 10);
			Commons.waitforElement(driver, By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'CancelQA')]"),
					40);
			// int size =
			// driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(),
			// 'Automate')]")).size();
			webelement = driver.findElements(By.xpath("//div[@id='eventTemplate']//div[contains(text(), 'CancelQA')]"))
					.get(0);
			Commons.scrollElementinotView(webelement, driver);
			System.out.println("Got Existing Appointment Field");
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
		} catch (Exception e) {
			System.out.println("Did not get Existing Appointment");
			Assert.assertFalse(true, "Did not get Existing Appointment" + Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement availableHoursStart(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.id("availableHoursStart"), 90);
			webelement = driver.findElement(By.id("availableHoursStart"));
		} catch (Exception e) {
			System.out.println("Did not get availableHoursStart");
		}
		return webelement;
	}

	public static WebElement availableHoursEnd(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.id("availableHoursEnd"), 90);
			webelement = driver.findElement(By.id("availableHoursEnd"));
		} catch (Exception e) {
			System.out.println("Did not get availableHoursEnd");
		}
		return webelement;
	}

	public static WebElement startTimeHours(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//input[@ng-model=' vmProviderAvailability.providerAvailabiltyDataScheduler.startTimeHours']"),
					90);
			webelement = driver.findElement(By.xpath(
					"//input[@ng-model=' vmProviderAvailability.providerAvailabiltyDataScheduler.startTimeHours']"));
		} catch (Exception e) {
			System.out.println("Did not get startTimeHours");
		}
		return webelement;
	}

	public static WebElement endTimeHours(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath(
							"//input[@ng-model=' vmProviderAvailability.providerAvailabiltyDataScheduler.endTimeHours']"),
					90);
			webelement = driver.findElement(By.xpath(
					"//input[@ng-model=' vmProviderAvailability.providerAvailabiltyDataScheduler.endTimeHours']"));
		} catch (Exception e) {
			System.out.println("Did not get endTimeHours");
		}
		return webelement;
	}

	public static WebElement homeOnMenu(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//a[@ng-click='setproviderHomeDate()']//span"), 40);
			webelement = driver.findElement(By.xpath("//a[@ng-click='setproviderHomeDate()']//span"));
			System.out.println("Got Home page menu");
		} catch (Exception e) {
			System.out.println("Did not get Home page menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement StatusfieldWithNoShow(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'No Show')]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'No Show')]"));
			System.out.println("Got no show Status field Field");
		} catch (Exception e) {
			System.out.println("Did not get no show status Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement StatusfieldWithcancel(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Cancel')]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Cancel')]"));
			System.out.println("Got no show Status field Field");
		} catch (Exception e) {
			System.out.println("Did not get no show status Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement StatusfieldWithArrived(WebDriver driver) {
		try {
			Thread.sleep(2000);
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Thread.sleep(2000);
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Arrived')]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Arrived')]"));
			System.out.println("Got Arrived Status field Field");
		} catch (Exception e) {
			System.out.println("Did not get Arrived status Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ProviderSelection(WebDriver driver, String provider) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//div[@id='providerDropDownDiv']//span[@class='k-select']"), 30);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='providerDropDownDiv']//span[@class='k-select']")));
			Commons.Explicitwait();
			String xpath = "//div[@*='k-animation-container']//div[@id='providerDropDown-list']//ul[@id='providerDropDown_listbox']//li[contains(text(),'"
					+ provider + "')]";
			Commons.waitforElement(driver, By.xpath(xpath), 40);
			webelement = driver.findElement(By.xpath(xpath));
			System.out.println("Got Provider Field");
		} catch (Exception e) {
			System.out.println("Did not get Provider Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement LocationSelection(WebDriver driver, String location) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//div[@id='locationDropDownDiv']//span[@class='k-select']"), 30);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='locationDropDownDiv']//span[@class='k-select']")));
			Commons.Explicitwait();
			String xpath = "//div[@*='k-animation-container']//ul[@id='location_listbox']//li[contains(text(),'"
					+ location + "')]";
			Commons.waitforElement(driver, By.xpath(xpath), 40);
			webelement = driver.findElement(By.xpath(xpath));
			System.out.println("Got Provider Field");
		} catch (Exception e) {
			System.out.println("Did not get Provider Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement StatusfieldWithVoid(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'VOID')]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'VOID')]"));
			System.out.println("Got VOID Status field Field");
		} catch (Exception e) {
			System.out.println("Did not get VOID status Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement StatusfieldWithReschedule(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']"),
					30);
			ActionUtils.click(
					driver.findElement(By.xpath("//*[@data-container-for='statusCode']//span[@class='k-select']")));
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath(
							"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Rescheduled')]"),
					40);
			webelement = driver.findElement(By.xpath(
					"//div[@*='k-animation-container']//div[@id='statusCode-list']//ul[@id='statusCode_listbox']//li[contains(text(),'Rescheduled')]"));
			System.out.println("Got reschedule Status field Field");
		} catch (Exception e) {
			System.out.println("Did not get reschedule status Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement NoShowReasonForgot(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//div[@id='noShowReasonCodeDiv']//span[@class='k-select']"), 30);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='noShowReasonCodeDiv']//span[@class='k-select']")));
			Thread.sleep(1000);
			Commons.ExistandDisplayElement(driver, By.xpath("(//ul[@id='noShowReasonCode_listbox']//li[1])[last()]"),20);
			Thread.sleep(1000);
			webelement = driver.findElement(By.xpath("(//ul[@id='noShowReasonCode_listbox']//li[1])[last()]"));
			System.out.println("Got no show Status reason field Field");
		} catch (Exception e) {
			System.out.println("Did not get no show status reason Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement cancelReason(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver,
					By.xpath("//div[contains(@ng-show,'CANCEL')]//label[contains(text(),'Reason')]"), 10);
			Thread.sleep(1000);
			ActionUtils
					.click(driver.findElement(By.xpath("//div[@id='cancelReasonCodeDiv']//span[@class='k-select']")));
			Thread.sleep(2000);
			Commons.scrollElementinotView(driver.findElement(By.xpath("(//ul[@id='cancelReasonCode_listbox']/li[2])[last()]")), driver);
			Commons.ExistandDisplayElement(driver, By.xpath("(//ul[@id='cancelReasonCode_listbox']/li[2])[last()]"), 20);
			webelement = driver.findElement(By.xpath("(//ul[@id='cancelReasonCode_listbox']/li[2])[last()]"));
			System.out.println("Got cancel Status reason field Field");
		} catch (Exception e) {
			System.out.println("Did not get cancel status reason Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement RescheduleReason(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitforElement(driver, By.xpath("//div[@id='rescheduleReasonCodeDiv']//span[@class='k-select']"),
					10);
			Thread.sleep(1000);
			ActionUtils.click(
					driver.findElement(By.xpath("//div[@id='rescheduleReasonCodeDiv']//span[@class='k-select']")));
			Commons.waitforElement(driver, By.xpath("//ul[@id='rescheduleReasonCode_listbox']/li[1]"), 20);
			webelement = driver.findElement(By.xpath("//ul[@id='rescheduleReasonCode_listbox']/li[1]"));
			System.out.println("Got reschedule  reason field Field");
		} catch (Exception e) {
			System.out.println("Did not get reschedule reason Field");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement ProviderAvailabilityButton(WebDriver driver) {
		try {
			Commons.waitforElement(driver,
					By.xpath("//div[@id='sidebar-wrapper']//span[text()='Provider Availability']"), 40);
			webelement = driver
					.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Provider Availability']"));
			System.out.println("Got Provider Availability on Sub Menu");
		} catch (WebDriverException e) {
			System.out.println("Got Provider Availability on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get Provider Availability on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement PolicyChargeQueue(WebDriver driver) {
		try {
			Commons.waitforElement(driver, By.xpath("//div[@id='sidebar-wrapper']//span[text()='Policy Charge Queue']"),
					40);
			webelement = driver
					.findElement(By.xpath("//div[@id='sidebar-wrapper']//span[text()='Policy Charge Queue']"));
			System.out.println("Got Policy charge queue on Sub Menu");
		} catch (WebDriverException e) {
			System.out.println("Got Policy charge queue on Sub Menu");
		} catch (Exception e) {
			System.out.println("Did not get Policy charge queue on Sub Menu");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
	/*---END-- -Appointment Window--*/
}