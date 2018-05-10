package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;
import UIMap.ChangeCompany;
import UIMap.ManageMyAccount;
import UIMap.SchedulerPage;
import UIMap.WaitList;

public class ManageMyAccountUtils {
	public static void NavigateToManageMyAccount(WebDriver driver) {
		ActionUtils.JavaScriptclick(ChangeCompany.companyName(driver));
		ActionUtils.JavaScriptclick(ManageMyAccount.ManageMyAccountLink(driver));
		Commons.waitforElement(driver, By.xpath("//h1[contains(text(),'Manage My Account ')]"), 90);
		Assert.assertNotNull(driver.findElement(By.xpath("//h1[contains(text(),'Manage My Account ')]")));
	}

	public static void changedefaultViewProvider(WebDriver driver, String providerdesc) {
		ManageMyAccountUtils.NavigateToManageMyAccount(driver);
		System.out.println("clicking on edit button");
		ManageMyAccount.EditButton(driver).click();
		Commons.waitForLoad(driver);
		try {
			System.out.println("selecting default view to provider's view : " + providerdesc);
			Select dropdown = new Select(
					driver.findElement(By.xpath("//select[@ng-change='vmManageMyAccount.defaultViewchange()']")));
			dropdown.selectByVisibleText("Provider");
			Commons.waitforElement(driver, By.id("schedulerProviderBtn"), 90);
			driver.findElement(By.id("schedulerProviderBtn")).click();
			Commons.waitforElement(driver, By.id("SearchProvider_ProviderDescription"), 100);
			driver.findElement(By.id("SearchProvider_ProviderDescription")).sendKeys(providerdesc);
			driver.findElement(
					By.xpath("//div[@config-id='vmManageMyAccount.searchProviderKey']//input[@value='Search']"))
					.click();
			Commons.waitforElement(driver,
					By.xpath("//div[@config-id='vmManageMyAccount.searchProviderKey']//tbody//td"), 100);
			ActionUtils.doubleClick(driver,
					driver.findElement(By.xpath("//div[@config-id='vmManageMyAccount.searchProviderKey']//tbody//td")));
			ManageMyAccount.SaveButton(driver).click();
		} catch (Exception e) {
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}
