package com.bms.M6.Scheduler;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.CreateNotePage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddScheduleUtils;
import Utils.Commons;
import com.google.common.base.CharMatcher;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestProviderHomePageLayout extends TestSetUp {
	AddScheduleUtils callto = new AddScheduleUtils();
	public int ScheduledAppointmentonProviderHome = 0;
	public List<WebElement> actual = null;

	@Test(enabled = true, priority = 1)
	public void TestProviderHomePageLayoutValidation() throws InterruptedException {
		test = extent.startTest("Test Validate Provider Home Page Layout",
				"Test Test Validate Provider Home Page Layout" + "*****Current Browser******" + CurrentBrowserName
						+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		try {
			Commons.logout(driver);
			Commons.logintoRevflow(driver, "AutomationBMSProvider", Commons.readPropertyValue("password"));
			Commons.ExistandDisplayElement(driver, By.xpath("//b[contains(.,'Appointment')]"), 50);
			if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 5)) {
				ScheduledAppointmentonProviderHome = driver
						.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
				Commons.screenshot();
			} else {
				ScheduledAppointmentonProviderHome = 0;
			}
			int i = 0;
			while (ScheduledAppointmentonProviderHome < 3 && i < 5) {
				callto.ScheduleRecurringAppointment(driver, "Initial Evaluation", "Scheduled");
				ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 90);
				ScheduledAppointmentonProviderHome = driver
						.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
				i++;
			}
			System.out.println(ScheduledAppointmentonProviderHome = driver
					.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size());
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 90);
			Commons.screenshot();
			extent.endTest(test);
			try {
				// Case-1 Validate Drop down when appointment type Selected
				test = extent.startTest("Case-1 Validate Drop down when Appointment Type Selected ",
						"Case-1 Validate Drop down when Appointment Type Selected" + "*****Current Browser******"
								+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				Commons.waitforElement(driver, By.xpath("//*[@id='ddlList-provider']//option"), 30);
				actual = driver.findElements(By.xpath("//*[@id='ddlList-provider']//option"));
				actual = actual.subList(0, 3);
				System.out.println("No Of Dropdown option Values in Appointment Type are:" + actual.size());
				test.log(LogStatus.INFO, "No Of Dropdown option Values in Appointment Type are:" + actual.size());
				test.log(LogStatus.INFO, "Validating Values in Appointment Type type dropdown list");
				for (WebElement we : actual) {
					System.out.println(we.getText());
					test.log(LogStatus.INFO, we.getText());
				}
				// make sure you found the right number of elements
				String[] expected1 = { "Appointment Type", "Appointment Status Category", "Note Status", };
				APageUtils.DropDownValuesAssertion(driver, actual, expected1);
				Commons.waitforElement(driver, By.xpath("//*[@id='ddlList-value']//option"), 30);
				actual = driver.findElements(By.xpath("//*[@id='ddlList-value']//option"));
				actual = actual.subList(0, 4);
				System.out.println("No Of Dropdown option Values in Appointment Type are:" + actual.size());
				test.log(LogStatus.INFO, "No Of Dropdown option Values in Appointment Type are:" + actual.size());
				test.log(LogStatus.INFO, "Validating Values in Appointment Type type dropdown list");
				for (WebElement we : actual) {
					System.out.println(we.getText());
					test.log(LogStatus.INFO, we.getText());
				}
				// make sure you found the right number of elements
				String[] expected2 = { "All Appointment Types", "Initial Evaluation", "Follow-up Visit",
						"Re-evaluation" };
				APageUtils.DropDownValuesAssertion(driver, actual, expected2);
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-1 Failed " + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			try {
				// Case-2 Validate Drop down when Appointment Status Category
				// Selected
				test = extent.startTest("Case-2 Validate Drop down when Appointment Status Category Selected ",
						"Case-2 Validate Drop down when Appointment Status Category Selected "
								+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
								+ caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				Commons.waitforElement(driver, By.xpath("//*[@id='ddlList-provider']"), 30);
				// ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlList-provider']//option[contains(.,'Appointment
				// Status Category')]")));
				Commons.SelectElement(driver, By.xpath("//*[@id='ddlList-provider']"), "Appointment Status Category");
				Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='ddlList-value']//option"), 30);
				actual = driver.findElements(By.xpath("//*[@id='ddlList-value']//option"));
				System.out.println("No Of Dropdown option Values in Appointment Status Category are:" + actual.size());
				test.log(LogStatus.INFO,
						"No Of Dropdown option Values in Appointment Status Category are:" + actual.size());
				test.log(LogStatus.INFO, "Validating Values in Appointment Status Category dropdown list");
				for (WebElement we : actual) {
					System.out.println(we.getText());
					test.log(LogStatus.INFO, we.getText());
				}
				// make sure you found the right number of elements
				String[] expected3 = { "All Categories", "Rescheduled", "No Show", "Canceled", "Arrived", "Scheduled",
						"Other" };
				APageUtils.DropDownValuesAssertion(driver, actual, expected3);
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-2 Failed " + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			try {
				// Case-3 Validate Drop down when Note Status Selected
				test = extent.startTest("Case-3 Validate Drop down when Note Status Selected ",
						"Case-3 Validate Drop down when Note Status Selected " + "*****Current Browser******"
								+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				Commons.waitforElement(driver, By.xpath("//*[@id='ddlList-provider']"), 30);
				// ActionUtils.click(driver.findElement(By.xpath("//*[@id='ddlList-provider']//option[contains(.,'Note
				// Status')]")));
				Commons.SelectElement(driver, By.xpath("//*[@id='ddlList-provider']"), "Note Status");
				Commons.ExistandDisplayElement(driver, By.xpath("//*[@id='ddlList-value']//option"), 30);
				actual = driver.findElements(By.xpath("//*[@id='ddlList-value']//option"));
				System.out.println("No Of Dropdown option Values in Note Status are:" + actual.size());
				test.log(LogStatus.INFO, "No Of Dropdown option Values in Note Status are:" + actual.size());
				test.log(LogStatus.INFO, "Validating Values in Note Status dropdown list");
				for (WebElement we : actual) {
					System.out.println(we.getText());
					test.log(LogStatus.INFO, we.getText());
				}
				// make sure you found the right number of elements
				String[] expected4 = { "All Note Status", "All Notes Not Finalized", "Completed Notes",
						"Finalized Notes", "Pending Notes", "Case Incomplete", "Signed Notes" };
				APageUtils.DropDownValuesAssertion(driver, actual, expected4);
				System.out.println("******************Verified Drop downs on Provider home screen*****************");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-3 Failed " + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-4
			// Verify the functionality of check box on Provider Home screen
			try {
				test = extent.startTest("Case-4 Verify the functionality of check box on Provider Home screen ",
						"Case-4 Verify the functionality of check box on Provider Home screen "
								+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
								+ caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				ScheduledAppointmentonProviderHome = driver
						.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='typeCheck']")));
				Thread.sleep(2000);
				Commons.waitForLoad(driver);
				int Countafter = driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
				if (Countafter > ScheduledAppointmentonProviderHome) {
					System.out.println("Verified Functionality for type Checkbox");
				} else {
					System.out.println("No show and Cancleld appoitnment not available");
				}
				ActionUtils.click(driver.findElement(By.xpath("//*[@id='typeCheck']")));
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-4 Failed " + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-6
			// Verify user can navigate to Appointment window by clicking on
			// appointment time on Home screen
			try {
				test = extent.startTest(
						"Case-6 Verify user can navigate to Appointment window by clicking on appointment time on Home screen ",
						"Case-6 Verify user can navigate to Appointment window by clicking on appointment time on Home screen"
								+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
								+ caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//*[@id='scheduler']//span[contains(@class,'agendaTime')]"),
						90);
				ActionUtils.click(driver
						.findElements(By.xpath("//*[@id='scheduler']//span[contains(@class,'agendaTime')]")).get(0));
				Commons.waitForLoad(driver);
				if(Commons.ExistandDisplayElement(driver,By.xpath("//span[contains(@class,'window-title')][contains(.,'Appointment ')]"), 30)){
					Commons.screenshot();
					ActionUtils.click(driver.findElement(By.xpath("//a[contains(@class,'scheduler-cancel')]")));
				}else{
					
					Commons.waitforElement(driver, By.xpath("//span[contains(.,'Edit Recurring Appointment')]"),20);
					Commons.screenshot();
	
				}
				
				String instruction = ActionUtils.getText(driver.findElement(
						By.xpath("//span[contains(text(),'Select the circle icon to the left of the patient')]")));
				Assert.assertTrue(instruction.contains("Select the circle icon to the left of the patient"));
				Assert.assertTrue(driver.getPageSource().contains("Next Doctor Visit"));
				System.out.println("*******Case-6 Assertion Pass*******");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-6 Failed " + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-7
			// Verify user can navigate to Create Note Screen from Provider Home
			// page
			try {
				test = extent.startTest(
						"Case-7 Verify user can navigate to Create Note Screen from Provider Home page ",
						"Case-7 Verify user can navigate to Create Note Screen from Provider Home page"
								+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
								+ caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 30);
				if(Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus note-red')]"),9)){
					
					ActionUtils
					.click(driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus note-red')]")).get(0));
					Commons.waitForLoad(driver);
					Commons.waitforElement(driver, By.xpath("//h1[contains(.,'New Note')]"), 30);
					Commons.screenshot();
					
					
				}else{
				
				ActionUtils
						.click(driver.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).get(0));
				Commons.waitForLoad(driver);
				//ActionUtils.click(CreateNotePage.CaseNameddl(driver));
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//a[contains(.,'Note Details')]"), 30);
				Commons.screenshot();
				}
				ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
				Commons.waitForLoad(driver);
				Commons.screenshot();
				System.out.println("*******Case-7 Assertion Pass*******");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-7 Failed " + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-8
			// Verify user can navigate to Patient View Screen from Provider
			// Home page
			try {
				test = extent.startTest(
						"Case-8 Verify user can navigate to Patient View Screen from Provider Home page ",
						"Case-8 Verify user can navigate to Patient View Screen from Provider Home page"
								+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
								+ caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				Commons.waitforElement(driver, By.xpath("//*[@id='name']//span[@class='ListName']//u"), 30);
				driver.findElements(By.xpath("//*[@id='name']//span[@class='ListName']//u")).get(0).click();
				Commons.waitForLoad(driver);
				Commons.waitforElement(driver, By.xpath("//span[@id='patientID']"), 80);
				Commons.screenshot();
				ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
				Commons.waitForLoad(driver);
				Commons.screenshot();
				System.out.println("*******Case-8 Assertion Pass*******");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-8 Failed " + Throwables.getStackTraceAsString(e));
			}
			Commons.screenshot();
			extent.endTest(test);
			// Case-5
			// Verify the functionality of previous and next day button Provider
			// Home screen
			try {
				test = extent.startTest(
						"Case-5 Verify the functionality of previous and next day button Provider Home screen ",
						"Case-5 Verify the functionality of previous and next day button Provider Home screen"
								+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
								+ caps.getVersion());
				test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
						this.getClass().getPackage().getName().toString());
				test.log(LogStatus.INFO, "Browser started");
				ActionUtils.click(driver.findElement(By.xpath("//a[@title='Today']")));
				String CurrentDate = CharMatcher.DIGIT
						.retainFrom(ActionUtils
								.getText(driver.findElement(By.xpath("//span[contains(@data-bind,'formattedDate')]"))))
						.substring(0, 2); //
				System.out.println("Current Date" + CurrentDate);
				// Navigate to future date 2 days ahead
				ActionUtils.click(driver.findElement(By.xpath(".//*[@id='scheduler']//a[@title='Next']")));
				ActionUtils.click(driver.findElement(By.xpath(".//*[@id='scheduler']//a[@title='Next']")));
				String FutureDate = CharMatcher.DIGIT
						.retainFrom(ActionUtils
								.getText(driver.findElement(By.xpath("//span[contains(@data-bind,'formattedDate')]"))))
						.substring(0, 2); //
				System.out.println("Future Date" + FutureDate);
		/*		if (Integer.parseInt(CurrentDate) <= 29) {
					Assert.assertTrue(Integer.parseInt(CurrentDate) < Integer.parseInt(FutureDate));
				}*/
				Assert.assertTrue(!FutureDate.equals(CurrentDate));
				ActionUtils.click(driver.findElement(By.xpath("//a[@title='Today']")));
				Commons.waitForLoad(driver);
				// Navigate to Past date 2 days ahead
				ActionUtils.click(driver.findElement(By.xpath(".//*[@id='scheduler']//a[@title='Previous']")));
				ActionUtils.click(driver.findElement(By.xpath(".//*[@id='scheduler']//a[@title='Previous']")));
				String PastDate = CharMatcher.DIGIT
						.retainFrom(ActionUtils
								.getText(driver.findElement(By.xpath("//span[contains(@data-bind,'formattedDate')]"))))
						.substring(0, 2); //
				System.out.println("Past Date" + PastDate);
			/*	if (Integer.parseInt(CurrentDate) <= 29) {
					if (CurrentDate.matches("01|02|03|04")) {
						System.out.println("Current DAte:" + CurrentDate);
						Assert.assertNotEquals(CurrentDate, PastDate);
					} else {
						Assert.assertTrue(Integer.parseInt(CurrentDate) > Integer.parseInt(PastDate));
					}
				}*/
				Assert.assertTrue(!PastDate.equals(CurrentDate));
				ActionUtils.click(driver.findElement(By.xpath("//a[@title='Today']")));
				Commons.waitForLoad(driver);
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Case-5 Failed " + Throwables.getStackTraceAsString(e));
			}
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "***********Assertion Error**********");
			Assert.assertFalse(true, "Test Provider Home Page LayOut Failed" + Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3, dependsOnMethods = { "TestProviderHomePageLayoutValidation" })
	public void TestProviderHomePageFilter() throws InterruptedException {
		test = extent.startTest("Test Validate Provider Home Page Filter Functionality",
				"Test Validate Provider Home Page Filter Functionality" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userrecurring"),
				Commons.readPropertyValue("password"));
		Commons.ExistandDisplayElement(driver, By.xpath("//b[contains(.,'Appointment')]"), 50);
		if (Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 5)) {
			ScheduledAppointmentonProviderHome = driver
					.findElements(By.xpath("//table[contains(@class,'scheduler-table')]//td[contains(.,'Followup')]"))
					.size();
			Commons.screenshot();
		} else {
			ScheduledAppointmentonProviderHome = 0;
		}
		int i = 0;
		while (ScheduledAppointmentonProviderHome < 3 && i < 5) {
			callto.ScheduleRecurringAppointment(driver, "Followup", "Scheduled");
			ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 90);
			ScheduledAppointmentonProviderHome = driver
					.findElements(By.xpath("//table[contains(@class,'scheduler-table')]//td[contains(.,'Followup')]"))
					.size();
			i++;
		}
		System.out.println(ScheduledAppointmentonProviderHome = driver
				.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size());
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(.,'Home')]")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//span[contains(@class,'clinicalNoteStatus')]"), 90);
		// Case-1 To verify Filter Appointment by type
		Commons.SelectElement(driver, By.xpath("//*[@id='ddlList-provider']"), "Appointment Type");
		Commons.SelectElement(driver, By.xpath("//*[@id='ddlList-value']"), "Initial Evaluation");
		int ScheduledAppointmentonProviderHomeAfterFilter = driver
				.findElements(By.xpath("//span[contains(@class,'clinicalNoteStatus')]")).size();
		Assert.assertTrue(ScheduledAppointmentonProviderHomeAfterFilter < ScheduledAppointmentonProviderHome,
				"Filter Appointment Type is failed");
	}

	@Test(enabled = true, priority = 4)
	public void TestNonProviderHomePage() throws InterruptedException {
		test = extent.startTest("Test Non Provider Home Page View", "Test Validate Non Provider Home Page View"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		String strActualtext = null;
		Commons.logout(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userProvider101"),
				Commons.readPropertyValue("password"));
		Assert.assertTrue(Commons.ExistandDisplayElement(driver, By.xpath("//img[contains(@src,'LogoProvider')]"), 50),
				"Non-provider Logo not found");
		Assert.assertTrue(driver.getCurrentUrl().contains("non-provider"));
	}
}