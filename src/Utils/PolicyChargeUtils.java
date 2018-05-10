package Utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import UIMap.SchedulerPage;
import com.relevantcodes.extentreports.LogStatus;

public class PolicyChargeUtils {
	public static void ApproveCharges(WebDriver driver, String newName) throws InterruptedException {
		System.out.println("navigating to ploicy charge queue");
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
		Commons.waitForLoad(driver);
		Thread.sleep(3000);
		System.out.println("looking for patient" + newName);
		String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
				+ "')]";
		Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
		System.out.println("found");
		Thread.sleep(3000);
		xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
				+ "')]/ancestor::tr/td/input";
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		Thread.sleep(4000);
		ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
		System.out.println("selecting the charge");
		System.out.println("selecting the charge");
		ActionUtils.click(driver.findElement(By.id("policyApproveBtn")));
		Commons.capturemessage(driver, 40);
		System.out.println("Charge is approved");
		Commons.waitForLoad(driver);
		xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
		Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
		System.out.println("Charge is removed from the queue");
	}

	public static void DenyCharges(WebDriver driver, String newName) throws InterruptedException {
		System.out.println("navigating to ploicy charge queue");
		ActionUtils.click(SchedulerPage.ScheduleOnMenu(driver));
		ActionUtils.click(SchedulerPage.PolicyChargeQueue(driver));
		Commons.waitForLoad(driver);
		Thread.sleep(3000);
		System.out.println("looking for patient" + newName);
		String xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
				+ "')]";
		Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
		System.out.println("found");
		Thread.sleep(3000);
		xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName
				+ "')]/ancestor::tr/td/input";
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		Thread.sleep(4000);
		ActionUtils.JavaScriptclick(driver.findElement(By.xpath(xpath)));
		System.out.println("selecting the charge");
		ActionUtils.click(driver.findElement(By.id("policydenyBtn")));
		Commons.waitForLoad(driver);
		xpath = "//div[@id='viewPolicyChargeQueue']//table//tbody//tr//td/span[contains(text(),'" + newName + "')]";
		Assert.assertFalse(Commons.existsElement(driver, By.xpath(xpath)));
		System.out.println("Charge is removed from the queue");
	}

	public static void ViewcancellationCharges(WebDriver driver, String patientid) throws InterruptedException {
		System.out.println("searching patient id :" + patientid);
		SearchPatientUtils.QuickpatientSearch(driver, patientid);
		Commons.waitForLoad(driver);
		AddCaseUtils.GoToCaseList(driver);
		Commons.waitForLoad(driver);
		String xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
		Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
		System.out.println("self pay case is added");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		Assert.assertEquals(elements.size(), 1);
		System.out.println("there is only one self pay case");
		xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
		Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
		Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("0"));
		System.out.println("number of charges on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
		System.out.println("navigating to view charges");
		AddChargeUtils.NavigateToViewChargePage(driver);
		Commons.waitForLoad(driver);
		xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
		Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		System.out.println("self pay case is visible in view charges");
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
		xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
		Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().contains("40"));
		System.out.println("charge added on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
	}

	public static void ViewNoShowCharges(WebDriver driver, String patientid) throws InterruptedException {
		System.out.println("searching patient id :" + patientid);
		SearchPatientUtils.QuickpatientSearch(driver, patientid);
		Commons.waitForLoad(driver);
		AddCaseUtils.GoToCaseList(driver);
		Commons.waitForLoad(driver);
		String xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]";
		Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
		System.out.println("self pay case is added");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		Assert.assertEquals(elements.size(), 1);
		System.out.println("there is only one self pay case");
		xpath = "//*[@id='gridCaseDetails']/table/tbody//tr/td/span[contains(text(),'Self Pay')]/ancestor::tr/td[12]/span";
		// Assert.assertTrue(Commons.existsElement(driver, By.xpath(xpath)));
		// Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().equals("1"));
		System.out.println("number of charges on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
		System.out.println("navigating to view charges");
		AddChargeUtils.NavigateToViewChargePage(driver);
		Commons.waitForLoad(driver);
		xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]";
		Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		System.out.println("self pay case is visible in view charges");
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(By.xpath(xpath)));
		xpath = "//div[@id='viewChargeGrid']//table//tbody/tr/td[contains(text(),'Self Pay')]/ancestor::tr/td[21]";
		Commons.scrollElementinotView(driver.findElement(By.xpath(xpath)), driver);
		Assert.assertTrue(Commons.waitForElementToBeVisible(driver, driver.findElement(By.xpath(xpath)), 120));
		Assert.assertTrue(driver.findElement(By.xpath(xpath)).getText().contains("27"));
		System.out.println("charge added on self pay case is :" + driver.findElement(By.xpath(xpath)).getText());
	}
}
