package com.bms.M11.EMR;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.TestSetUp;
import Utils.Commons;
import Utils.EMRUtils;
import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class TestSignNoteWithDiffrentUsers extends TestSetUp {
	public static String strText_Actual = null;
	public static String Text_Expected = null;

	@Test(enabled = true, priority = 1)
	public void SignNotewithCustomerUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with Customer", "Test Sign and Finalize Note with Customer"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Customer");
		test.log(LogStatus.INFO, "Login as user Customer");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userCustomer"),
				Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "Customer", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with Customer  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with Customer  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 2)
	public void SignNotewithPTUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with PT User", "Test Sign and Finalize Note with PT User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as PT User");
		test.log(LogStatus.INFO, "Login as PT User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPT"), Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "PTUser", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with PT User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with PT User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 3)
	public void SignNotewithOTUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with OT User", "Test Sign and Finalize Note with OT User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as OT User");
		test.log(LogStatus.INFO, "Login as OT User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userOT"), Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "OTUser", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with OT User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with OT User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 4)
	public void SignNotewithSLPUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with SLP User", "Test Sign and Finalize Note with SLP User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as SLP User");
		test.log(LogStatus.INFO, "Login as SLP User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userSLP"), Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "SLPUser", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with SLP User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with SLP User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 5)
	public void SignNotewithRTUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with RT User", "Test Sign and Finalize Note with RT User"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as RT User");
		test.log(LogStatus.INFO, "Login as RT User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userRT"), Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "RTUser", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with RT User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with RT User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 6)
	public void SignNotewithFrontOfficeStaffUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with FrontOfficeStaff User",
				"Test Sign and Finalize Note with FrontOfficeStaff User" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as FrontOfficeStaff User");
		test.log(LogStatus.INFO, "Login as FrontOfficeStaff User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userFrontofficestaff"),
				Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "FrontOfficeStaff", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with FrontOfficeStaff User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with FrontOfficeStaff User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = false, priority = 7)
	public void SignNotewithPracticeAdminstratorUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with PracticeAdminstrator User",
				"Test Sign and Finalize Note with PracticeAdminstrator User" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as PracticeAdminstrator User");
		test.log(LogStatus.INFO, "Login as PracticeAdminstrator User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPracticeAdminstrator"),
				Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "PracticeAdminstrator", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with PracticeAdminstrator User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with PracticeAdminstrator User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 8)
	public void SignNotewithStudentproviderUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with Studentprovider User",
				"Test Sign and Finalize Note with Studentprovider User" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as Studentprovider User");
		test.log(LogStatus.INFO, "Login as Studentprovider User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userstudentprovider"),
				Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "Studentprovider", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with Studentprovider User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with Studentprovider User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 9)
	public void SignNotewithTreatingproviderUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with Treating provider User",
				"Test Sign and Finalize Note with Treating provider User" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as Treating provider User");
		test.log(LogStatus.INFO, "Login as Treating provider User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("usertreatingprovider"),
				Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "Treating provider", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with Treating provider User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with Treating provider User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 10)
	public void SignNotewithNonclinicalstaffUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with Nonclinicalstaff User",
				"Test Sign and Finalize Note with Nonclinicalstaff User" + "*****Current Browser******"
						+ CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as NonclinicalstaffUser");
		test.log(LogStatus.INFO, "Login as Nonclinicalstaff User");
		Commons.launchRevFlow(driver);
		Commons.logintoRevflow(driver, Commons.readPropertyValue("usernonclinicalstaff"),
				Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "Nonclinicalstaff", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with Nonclinicalstaff User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with Nonclinicalstaff User  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}

	@Test(enabled = true, priority = 11)
	public void SignNotewithPartnerUser() throws InterruptedException {
		test = extent.startTest("Test Sign and Finalize Note with Partner", "Test Sign and Finalize Note with Partner"
				+ "*****Current Browser******" + CurrentBrowserName + "*****Browser Version******" + caps.getVersion());
		test.assignCategory("Regression", "On" + "  " + CurrentBrowserName,
				this.getClass().getPackage().getName().toString());
		test.log(LogStatus.INFO, "Browser started");
		Commons.logout(driver);
		System.out.println("Logged out app");
		System.out.println("Login as user Partner");
		test.log(LogStatus.INFO, "Login as user Partner");
		Commons.logintoRevflow(driver, Commons.readPropertyValue("userPartner"), Commons.readPropertyValue("password"));
		try {
			EMRUtils SF = new EMRUtils();
			SF.SignandFinalize(driver, "Partner", "97140");
		} catch (AssertionError a) {
			test.log(LogStatus.INFO, "Assertion Error...");
			test.log(LogStatus.INFO, "Finalize Note with Partner  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(a));
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Exception...");
			test.log(LogStatus.INFO, "Finalize Note with Partner  Completed as Fail");
			Assert.assertFalse(true, Throwables.getStackTraceAsString(e));
		}
	}
}