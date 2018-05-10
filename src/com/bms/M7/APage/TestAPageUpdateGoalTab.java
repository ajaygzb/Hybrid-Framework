package com.bms.M7.APage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import TestBase.TestSetUp;
import Utils.APageUtils;
import Utils.ActionUtils;
import Utils.Commons;

public class TestAPageUpdateGoalTab extends TestSetUp {
	public String strText_Actual;

	@Test(enabled = true, priority = 1)
	public void UpdateGoal() throws InterruptedException {
		test = extent.startTest("To verify DropDown Options For Update Goal tab", "Test Update Goal tab"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		APageUtils.GotoFLR(driver);
		test.log(LogStatus.INFO, "Navigated to A PAge");
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Scrolled to Goal Tab");
		try {
			test.log(LogStatus.INFO, "Validating DropDown for Update Goal Status");
			String actual = driver
					.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]//option[@label]"))
					.getText();
			Assert.assertEquals(actual, "One Time Reporting");
			test.log(LogStatus.INFO, actual);
			int size = driver
					.findElements(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]//option[@label]")).size();
			System.out.println(size);
			Assert.assertEquals(size, 1);
			test.log(LogStatus.INFO, "Validating Discharge status tab when Goal Tab is Blank");
			Assert.assertFalse(Commons.existsElement(driver,
					By.xpath("//th[@class='text-center'][contains(.,'Discharge Status')]")));
			test.log(LogStatus.INFO, "Clicked on Update Goal Status DropDown");
			test.log(LogStatus.INFO, "Clicked on One Time Reporting");
			ActionUtils.click(
					driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]//option[@label]")));
			test.log(LogStatus.INFO, "Validating Discharge status tab after Clicked on One Time Reporting");
			Assert.assertTrue(Commons.existsElement(driver,
					By.xpath("//th[@class='text-center'][contains(.,'Discharge Status')]")));
			test.log(LogStatus.INFO, " Discharge status tab Appeared");
		} catch (AssertionError e) {
			Assert.assertFalse(true, "***********Assertion Error***************");
		}
		APageUtils.createfollowUpNewNote(driver);
		test.log(LogStatus.INFO, "Created Follow Up Note for Same Patient");
		APageUtils.click(driver, "clickAPAge");
		Commons.waitForLoad(driver);
		Commons.existsElement(driver, By.xpath("//button[@value='Save Note']"));
		Commons.waitforElement(driver, By.xpath("//button[@value='Save Note']"), 50);
		System.out.println("Successfully redirected to APage");
		Commons.Explicitwait();
		Commons.waitForLoad(driver);
		System.out.println("Validating that FLR Link Appear on A Page..");
		Assert.assertTrue(
				Commons.existsElement(driver, By.xpath("//a[@href='#Medicare-Functional-Limitation-Reporting']")),
				"FLR TAB Not Appeared");
		APageUtils.click(driver, "ClickMedicareFLRLink");
		Commons.waitForLoad(driver);
		Commons.Explicitwait();
		Commons.scrollElementinotView(driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]")),
				driver);
		test.log(LogStatus.INFO, "Navigated to A page");
		try {
			test.log(LogStatus.INFO, "Validating DropDown for Update Goal Status");
			String actual = driver
					.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]//option[@label]"))
					.getText();
			Assert.assertEquals(actual, "One Time Reporting");
			test.log(LogStatus.INFO, actual);
			int size = driver
					.findElements(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]//option[@label]")).size();
			System.out.println(size);
			Assert.assertEquals(size, 1);
			test.log(LogStatus.INFO, "Validating Discharge status tab when Goal Tab is Blank");
			Assert.assertFalse(Commons.existsElement(driver,
					By.xpath("//th[@class='text-center'][contains(.,'Discharge Status')]")));
			test.log(LogStatus.INFO, "Clicked on Update Goal Status DropDown");
			test.log(LogStatus.INFO, "Clicked on One Time Reporting");
			ActionUtils.click(
					driver.findElement(By.xpath("//select[contains(@ng-change,'GoalStatusChange')]//option[@label]")));
			test.log(LogStatus.INFO, "Validating Discharge status tab after Clicked on One Time Reporting");
			Assert.assertTrue(Commons.existsElement(driver,
					By.xpath("//th[@class='text-center'][contains(.,'Discharge Status')]")));
			test.log(LogStatus.INFO, " Discharge status tab Appeared");
		} catch (AssertionError e) {
			Assert.assertFalse(true, "***********Assertion Error***************");
		}
	}
}
