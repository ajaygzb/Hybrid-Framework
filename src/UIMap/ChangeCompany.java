package UIMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Utils.Commons;
import com.google.common.base.Throwables;

public class ChangeCompany {
	static WebElement webelement;

	public static WebElement companyName(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.id("companyName"), 40);
			webelement = driver.findElement(By.id("companyName"));
			System.out.println("Got company name");
		} catch (Exception e) {
			System.out.println("Did not get Company name");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement changeComapny(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//a[contains(text(),'Change Company')]"), 40);
			webelement = driver.findElement(By.xpath("//a[contains(text(),'Change Company')]"));
			System.out.println("Got chane company");
		} catch (Exception e) {
			System.out.println("Did not get Change company");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement CompanycodeTextBox(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@id='ChangeCompany_CompanyCode']"), 40);
			webelement = driver.findElement(By.xpath("//input[@id='ChangeCompany_CompanyCode']"));
			System.out.println("Got change company code text box");
		} catch (Exception e) {
			System.out.println("Did not get Change company code text box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement search(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//div[@id='changeCompany']//input[@value='Search']"), 40);
			webelement = driver.findElement(By.xpath("//div[@id='changeCompany']//input[@value='Search']"));
			System.out.println("Got search button");
		} catch (Exception e) {
			System.out.println("Did not get search button");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}

	public static WebElement NoShowCheckBox(WebDriver driver) {
		try {
			Commons.Explicitwait();
			Commons.waitForLoad(driver);
			Commons.waitforElement(driver, By.xpath("//input[@id='typeCheck']"), 40);
			webelement = driver.findElement(By.xpath("//input[@id='typeCheck']"));
			System.out.println("Got No show check box button");
		} catch (Exception e) {
			System.out.println("Did not get No show check box");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
		return webelement;
	}
}
