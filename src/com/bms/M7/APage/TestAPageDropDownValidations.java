package com.bms.M7.APage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import Utils.FinancialUtils;
import Utils.SearchPatientUtils;

public class TestAPageDropDownValidations extends TestSetUp {
	public String strActualtext;

	@Test(enabled = true, priority = 1)
	public void kx_modifier() throws InterruptedException {
		test = extent.startTest("To Validate Required Field Indicator for Diagnosis on A page",
				"Test required_field_indicators for Diagnosis tab" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		AddPatientUtils.QuickAddpatient(driver);
		strActualtext = ActionUtils.getText(AddPatientPage.patientID(driver));
		System.out.println("Quick Add a Patient with ID==>" + strActualtext);
		APageUtils.createNewNote(driver);
		test.log(LogStatus.INFO, "Added a Patient with ID" + strActualtext);
		test.log(LogStatus.INFO, "Created new note for InitialVisit");
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		test.log(LogStatus.INFO, "Adding procedure Codes 97140,97113");
		APageUtils.addProcedureCode(driver, "97140");
		APageUtils.addProcedureCode(driver, "97113");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitforElement(driver, By.xpath("//a[@href='#Assessment']"), 80);
		test.log(LogStatus.INFO, "Navigated to Apage");
		Commons.scrollElementinotView(driver.findElement(By.xpath("//input[contains(@ng-model,'therapyCap')]")),
				driver);
		Commons.waitforElement(driver, By.xpath("//input[contains(@ng-model,'therapyCap')]"), 30);
		ActionUtils.click(driver.findElement(By.xpath("//input[contains(@ng-model,'therapyCap')]")));
		test.log(LogStatus.INFO, "Checked checkbox for Threapy Cap");
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		test.log(LogStatus.INFO, "Clicked on charge capture link");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//select[contains(@ng-model,'procMod1')]/option[@label]"), 30);
		test.log(LogStatus.INFO, "Validate Modifier list dropdown options");
		List<WebElement> options = driver
				.findElements(By.xpath("//select[contains(@ng-model,'procMod1')]/option[@label]"));
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains("KX REQ DOC IS ON FILE")) {
				System.out.println("Validate Modifier list dropdown options=>" + options.get(i).getText());
				test.log(LogStatus.INFO, "Validate Modifier list dropdown options=>" + options.get(i).getText());
				try {
					Assert.assertEquals(options.get(i).getText(), "KX REQ DOC IS ON FILE");
				} catch (AssertionError e) {
					Assert.assertFalse(true, "Test Failed Could not Validate Modifier List Values");
				}
			}
		} // ending for loop
		Commons.waitForLoad(driver);
		APageUtils.createfollowUpNewNote(driver);
		test.log(LogStatus.INFO, "Created new note for FollowUP Visit");
		driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")).click();
		Commons.waitForLoad(driver);
		APageUtils.click(driver, "clickAPAge");
		test.log(LogStatus.INFO, "Navigated to Apage");
		ActionUtils.click(driver.findElement(By.xpath("//a[@href='#Goals-Rehab-Potential']")));
		Commons.waitforElement(driver, By.xpath("//input[contains(@ng-model,'therapyCap')]"), 30);
		test.log(LogStatus.INFO, "Validating That Tharapy Cap Checkbox is Checked");
		Commons.scrollElementinotView(driver.findElement(By.xpath("//input[contains(@ng-model,'therapyCap')]")),
				driver);
		Assert.assertTrue(
				Commons.existsElement(driver,
						By.xpath("//input[contains(@ng-model,'therapyCap')][contains(@class,'empty')]")),
				"Checkbox is not checked");
		test.log(LogStatus.INFO, "Paased Checked checkbox for Threapy Cap");
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Charge Capture')]")));
		test.log(LogStatus.INFO, "Clicked on charge capture link");
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//select[contains(@ng-model,'procMod1')]/option[@label]"), 30);
		test.log(LogStatus.INFO, "Validate Modifier list dropdown options For Subsequent Note");
		Commons.waitForLoad(driver);
		List<WebElement> options1 = driver
				.findElements(By.xpath("//select[contains(@ng-model,'procMod1')]/option[@label]"));
		for (int i = 0; i < options1.size(); i++) {
		
			if (options1.get(i).getText().contains("KX REQ DOC IS ON FILE")) {
				System.out.println(
						"Validate Modifier list dropdown options for Subsequent Note=>" + options1.get(i).getText());
				test.log(LogStatus.INFO,
						"Validate Modifier list dropdown options for Subsequent Note=>" + options1.get(i).getText());
				try {
					Assert.assertEquals(options1.get(i).getText(), "KX REQ DOC IS ON FILE");
				} catch (AssertionError e) {
					Assert.assertFalse(true, "Test Failed Could not Validate Modifier List Values for Subsequent Note");
				}
			}
		}

		String ID =AddPatientPage.patientID(driver).getText();

		extent.endTest(test);
		Commons.screenshot();







		test = extent.startTest("To Validate Pop up with Cancel when change the patient on an existing note",
				"To Validate Pop up with Cancel when change the patient on an existing note" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");

		// Case-1 Change the patient on an existing note Cancel

		System.out.println("Entering search data" + "EMRMEDICARE");
		int i = 1;
		do {
			ActionUtils.JavaScriptclick(AddPatientPage.patientQuicksearchbox(driver));
			AddPatientPage.patientQuicksearchbox(driver).clear();
			ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), "EMRMEDICARE");
			System.out.println("Waiting for results to load....");
			Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
					10);
			i++;
		} while (!Commons.existsElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"))
				&& i < 10);

		System.out.println("Got quick search result");
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(@md-highlight-text,'searchText')]")));
		System.out.println("Clicked on Search result");
		Commons.waitforElement(driver, By.xpath("//*[@id='notePatientMessagepopup']//h4[contains(.,'Confirm')]"),20);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='notePatientMessagepopup']//input[@value='Cancel']")));

		extent.endTest(test);
		Commons.screenshot();



		test = extent.startTest("To Validate Pop up with OK when change the patient on an existing note",
				"To Validate Pop up with OK when change the patient on an existing note" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");

		// Case-2 Change the patient on an existing note OK

		System.out.println("Entering search data" + "EMRMEDICARE");
		//int i = 1;
		do {
			ActionUtils.JavaScriptclick(AddPatientPage.patientQuicksearchbox(driver));
			AddPatientPage.patientQuicksearchbox(driver).clear();
			ActionUtils.sendKeys(AddPatientPage.patientQuicksearchbox(driver), "EMRMEDICARE");
			System.out.println("Waiting for results to load....");
			Commons.ExistandDisplayElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"),
					10);
			i++;
		} while (!Commons.existsElement(driver, By.xpath("//span[contains(@md-highlight-text,'searchText')]"))
				&& i < 10);

		System.out.println("Got quick search result");
		ActionUtils.click(driver.findElement(By.xpath("//span[contains(@md-highlight-text,'searchText')]")));
		System.out.println("Clicked on Search result");
		Commons.waitforElement(driver, By.xpath("//*[@id='notePatientMessagepopup']//h4[contains(.,'Confirm')]"),20);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='notePatientMessagepopup']//input[@value='OK']")));


		String IDNEW =AddPatientPage.patientID(driver).getText();

		System.out.println(IDNEW+"   "+ID);
		Assert.assertFalse(IDNEW.contains(ID));
		Commons.screenshot();
		extent.endTest(test);
		Commons.screenshot();




		test = extent.startTest("To Validate Body parts removal prompt with Cancel",
				"Body parts removal prompt with Cancel" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		// search existing patient

		SearchPatientUtils.QuickpatientSearch(driver, ID);
		APageUtils.GotoNoteList(driver, 1);
		ActionUtils.click(driver.findElement(By.xpath("//a[contains(.,'Note Details')]")));



		//CASE-1 Body parts removal prompt with Cancel
		ActionUtils.click(driver.findElement(By.xpath("(//input[@value='Remove All'])[1]")));
		Commons.waitforElement(driver, By.xpath("//*[@id='MessagepopupBodyPartglobal']//form[contains(.,'You are removing all previous Body Part or Template.')]"),30);
		Commons.screenshot();
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='MessagepopupBodyPartglobal']//form[contains(.,'You are removing all previous Body Part or Template.')]")))
		.contains("You are removing all previous Body Part or Template. All Exam and Post Exam responses will be deleted. Are you sure?"));
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='MessagepopupBodyPartglobal']//input[@value='Cancel']")));
		Commons.screenshot();
		extent.endTest(test);






		test = extent.startTest("To Validate Body parts removal prompt with OK",
				"Body parts removal prompt with OK" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		//CASE-2 Body parts removal prompt with OK
		Thread.sleep(3000);
		ActionUtils.click(driver.findElement(By.xpath("(//input[@value='Remove All'])[1]")));
		Commons.waitforElement(driver, By.xpath("//*[@id='MessagepopupBodyPartglobal']//form[contains(.,'You are removing all previous Body Part or Template.')]"),30);
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='MessagepopupBodyPartglobal']//form[contains(.,'You are removing all previous Body Part or Template.')]")))
		.contains("You are removing all previous Body Part or Template. All Exam and Post Exam responses will be deleted. Are you sure?"));
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='MessagepopupBodyPartglobal']//input[@value='OK']")));
		System.out.println(Commons.capturemessage(driver, 30).contains("Please select at least one body template before removing last selected template."));
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='MessagepopupBodyPartglobal']//input[@value='Cancel']")));
		Commons.screenshot();
		extent.endTest(test);
		
		
		
		test = extent.startTest("To Verify ADD Collection flag to an account from the patient_view screen",
				"To Verify ADD Collection flag to an account from the patient_view screen" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		FinancialUtils.NavigatetoAccountSummary(driver);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkCollection']")));
		Commons.waitforElement(driver, By.xpath("//*[@id='patientInCollectionFlag']"),30);
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='patientInCollectionFlag']")));
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//div[text()='This account is in collections']"))).contains("This account is in collections"));
		Commons.screenshot();
		System.out.println("**************Assertion-1 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='accntNotesPopupHeader']//h4[contains(.,'Account Notes for QuickAdd')]"),30);
		String Actual = ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//td[contains(.,'Collection flag is updated by')]")));
		String Expected = "Collection flag is updated by".trim().toString();		
		Assert.assertTrue(Actual.contains(Expected));
	//	Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//td[contains(.,'02/15/2018')]")))
	//	.contains("02/15/2018"));
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='gridAccountNoteIcon']//tbody//tr")).size()==1);
		Commons.screenshot();
		System.out.println("**************Assertion-2 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerAccountNotesCloseBtn']")));
		Commons.screenshot();
		extent.endTest(test);
		
		
		
		
		test = extent.startTest("To Verify Remove Collection flag to an account from the patient_view screen",
				"To Verify Remove Collection flag to an account from the patient_view screen" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		FinancialUtils.NavigatetoAccountSummary(driver);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkCollection']")));
		Assert.assertTrue(Commons.waitForElementToBeNotVisible(driver, By.xpath("//*[@id='patientInCollectionFlag']"),30));
		Commons.screenshot();
		System.out.println("**************Assertion-1 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='accntNotesPopupHeader']//h4[contains(.,'Account Notes for QuickAdd')]"),30);
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//tr[2]//td[contains(.,'Collection flag is updated by')]")))
		.contains("Collection flag is updated by"));
		//Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//tr[2]//td[contains(.,'02/15/2018')]")))
	//	.contains("02/15/2018"));
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='gridAccountNoteIcon']//tbody//tr")).size()==2);
		Commons.screenshot();
		System.out.println("**************Assertion-2 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerAccountNotesCloseBtn']")));
		Commons.screenshot();
		extent.endTest(test);
		
		
		
		
		
		test = extent.startTest("To Verify flag an account for a payment plan from  the patient_view screen",
				"To Verify flag an account for a payment plan from  the patient_view screen" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		FinancialUtils.NavigatetoAccountSummary(driver);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkPaymentPlan']")));
		Commons.screenshot();
		System.out.println("**************Assertion-1 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='accntNotesPopupHeader']//h4[contains(.,'Account Notes for QuickAdd')]"),30);
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//tr[3]//td[contains(.,'Patient is on an arranged payment plan')]")))
		.contains("Patient is on an arranged payment plan"));
		//Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//tr[3]//td[contains(.,'02/15/2018')]")))
	//	.contains("02/15/2018"));
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='gridAccountNoteIcon']//tbody//tr")).size()==3);
		Commons.screenshot();
		System.out.println("**************Assertion-3 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerAccountNotesCloseBtn']")));
		Commons.screenshot();
		extent.endTest(test);
		
		
		test = extent.startTest("To Verify Remove flag an account for a payment plan from  the patient_view screen",
				"To Verify Remove flag an account for a payment plan from  the patient_view screen" + "*****Current Browser******" + CurrentBrowserName
				+ "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		FinancialUtils.NavigatetoAccountSummary(driver);
		Commons.screenshot();
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='chkPaymentPlan']")));
		Commons.screenshot();
		System.out.println("**************Assertion-1 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='stickyNoteIcon']")));
		Commons.waitForLoad(driver);
		Commons.waitforElement(driver, By.xpath("//*[@id='accntNotesPopupHeader']//h4[contains(.,'Account Notes for QuickAdd')]"),30);
		Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//tr[4]//td[contains(.,'Patient is no longer on a payment plan')]")))
		.contains("Patient is no longer on a payment plan"));
		//Assert.assertTrue(ActionUtils.getText(driver.findElement(By.xpath("//*[@id='gridAccountNoteIcon']//tr[4]//td[contains(.,'02/15/2018')]")))
		//.contains("02/15/2018"));
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='gridAccountNoteIcon']//tbody//tr")).size()==4);
		Commons.screenshot();
		System.out.println("**************Assertion-4 Pass**********");
		ActionUtils.click(driver.findElement(By.xpath("//*[@id='headerAccountNotesCloseBtn']")));
		Commons.screenshot();
		extent.endTest(test);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}