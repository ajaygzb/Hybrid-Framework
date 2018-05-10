package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import UIMap.ChangeCompany;

public class ChangeCompanyUtils {
	public static void changeCompany(WebDriver driver, String companyID) throws InterruptedException {
		System.out.println("log in company is " + ChangeCompany.companyName(driver).getText());
		System.out.println("chnaging company to :" + companyID);
		ChangeCompany.companyName(driver).click();
		ChangeCompany.changeComapny(driver).click();
		Thread.sleep(2000);
		ChangeCompany.CompanycodeTextBox(driver).sendKeys(companyID);
		ChangeCompany.search(driver).click();
		Thread.sleep(2000);
		Commons.waitforElement(driver, By.xpath("//div[@id='ChangeCompanyGridId']//td"), 80);
		ActionUtils.doubleClick(driver, driver.findElement(By.xpath("//div[@id='ChangeCompanyGridId']//td")));
		String comapnyname = ChangeCompany.companyName(driver).getText();
		System.out.println("you are logged in to company " + comapnyname);
		Assert.assertTrue(comapnyname.contains(companyID.toUpperCase()));
	}
}
