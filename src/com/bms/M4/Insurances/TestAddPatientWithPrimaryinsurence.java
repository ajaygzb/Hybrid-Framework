package com.bms.M4.Insurances;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import UIMap.AddPatientPage;
import UIMap.CasePage;
import Utils.ActionUtils;
import Utils.AddCaseUtils;
import Utils.AddPatientUtils;
import Utils.Commons;
import com.relevantcodes.extentreports.LogStatus;

public class TestAddPatientWithPrimaryinsurence extends TestSetUp {
	// 5,11,17,23,29 inscode
	// 1,7,13,19,25 lastname
	@Test(enabled = true, priority = 1)
	public void TestDataScriptInsurances() throws InterruptedException {
		AddPatientUtils.data.clear();
		System.out.println(AddPatientUtils.ReadExceldata(driver));
		for (int i = 1; i < AddPatientUtils.data.size();) {
			System.out.println(AddPatientUtils.data.get(i));
			System.out.println(AddPatientUtils.data.get(i + 4));
			test = extent.startTest("Add Primary Insurance with code" + "  " + AddPatientUtils.data.get(i + 4),
					"Add Primary Insurance from excel data with code" + AddPatientUtils.data.get(i + 4)
							+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******"
							+ caps.getVersion());
			test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
					this.getClass().getPackage().getName().toString());
			test.log(LogStatus.INFO, "Browser started");
			AddPatientUtils.QuickAddpatientwithInsurence(driver, AddPatientUtils.data.get(i));
			AddCaseUtils.GoToCaseList(driver);
			Commons.Explicitwait();
			AddCaseUtils.OpenShellCase(driver);
			AddCaseUtils.AddCaseWithAllRequiredFields(driver);
			AddCaseUtils.AddDXCode(driver);
			AddCaseUtils.AddPrimaryInsuranceByCode(driver, AddPatientUtils.data.get(i + 4));
			String toastMessage = null;
			toastMessage = AddCaseUtils.ClickCaseSave(driver);
			if (toastMessage == null && Commons.existsElement(driver,
					By.xpath("//button[contains(@ng-click,'vmCase.saveClick(caseForm)')]"))) {
				toastMessage = AddCaseUtils.ClickCaseSave(driver);
			}
			if (toastMessage != null && toastMessage.contains("The policy number should be 9 or 11 numbers")) {
				ActionUtils.click(CasePage.primaryInsurancePolicyNameORSSN(driver));
				ActionUtils.clear(CasePage.primaryInsurancePolicyNameORSSN(driver));
				ActionUtils.sendKeys(CasePage.primaryInsurancePolicyNameORSSN(driver), "123456789");
				String toast = AddCaseUtils.ClickCaseSave(driver);
				toastMessage = toast;
			}
			try {
				Assert.assertEquals(toastMessage, "Case updated successfully.");
			} catch (AssertionError e) {
				test.log(LogStatus.INFO, "***************Could not capture Toast message*************");
				s_assert.assertTrue(Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]")),
						"Could not save case..");
				if (!Commons.existsElement(driver, By.xpath("//button[contains(.,'Edit Case')]"))) {
					test.log(LogStatus.ERROR, "Could not Add InsuranceCode" + AddPatientUtils.data.get(i + 4));
				}
			}
			String strActual_text_id = null;
			strActual_text_id = ActionUtils.getText(AddPatientPage.patientID(driver));
			if (strActual_text_id.isEmpty()) {
				s_assert.assertFalse(true, "testFailed");
			}
			System.out.println("Patient ID" + "  " + strActual_text_id);
			test.log(LogStatus.INFO, "Added Patient" + strActual_text_id);
			test.log(LogStatus.INFO, "Last Name" + AddPatientUtils.data.get(i));
			test.log(LogStatus.INFO, "Added InsuranceCode" + AddPatientUtils.data.get(i + 4));
			i = i + 6;
		    Commons.screenshot();
			extent.flush();
		}
	}
}