package ReportUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import TestBase.TestSetUp;
import Utils.Commons;

public class ZipUtils {
	public static String outputFileName;
	public static String newLine = System.getProperty("line.separator");// This
																		// will
																		// retrieve
																		// line
																		// separator
																		// dependent
																		// on
																		// OS.

	public static void Zipfile() throws Exception {
		String sourceFolderName = "d:/Report" + TestSetUp.date;
		outputFileName = "d:/Report" + TestSetUp.date + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFileName);
		ZipOutputStream zos = new ZipOutputStream(fos);
		// level - the compression level (0-9)
		zos.setLevel(9);
		System.out.println("Begin to compress folder : " + sourceFolderName + " to " + outputFileName);
		addFolder(zos, sourceFolderName, sourceFolderName);
		zos.close();
		System.out.println("Program ended successfully!");
	}

	private static void addFolder(ZipOutputStream zos, String folderName, String baseFolderName) throws Exception {
		File f = new File(folderName);
		if (f.exists()) {
			if (f.isDirectory()) {
				// Thank to peter
				// For pointing out missing entry for empty folder
				if (!folderName.equalsIgnoreCase(baseFolderName)) {
					String entryName = folderName.substring(baseFolderName.length() + 1, folderName.length())
							+ File.separatorChar;
					System.out.println("Adding folder entry " + entryName);
					ZipEntry ze = new ZipEntry(entryName);
					zos.putNextEntry(ze);
				}
				File f2[] = f.listFiles();
				for (int i = 0; i < f2.length; i++) {
					addFolder(zos, f2[i].getAbsolutePath(), baseFolderName);
				}
			} else {
				// add file
				// extract the relative name for entry purpose
				String entryName = folderName.substring(baseFolderName.length() + 1, folderName.length());
				System.out.print("Adding file entry " + entryName + "...");
				ZipEntry ze = new ZipEntry(entryName);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(folderName);
				int len;
				byte buffer[] = new byte[1024];
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
				zos.closeEntry();
				System.out.println("OK!");
			}
		} else {
			System.out.println("File or directory not found " + folderName);
		}
	}

	public static void SendingMail() {
		sendPDFReportByGMail("ajay.kumar4@rsystems.com", "oct@2016", "ajay.kumar4@rsystems.com",
				"BMS Automation Report" + ExtentManager.getCurrentTimeStamp(), "");
	}

	private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {
		Properties props = System.getProperties();
		// String host = "smtp.gmail.com";
		String host = "Mailrelay.india.rsystems.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		// props.put("mail.smtp.port", "587"); for gmail
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		// props.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			// Set from address
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipients(Message.RecipientType.TO, "Ajay.kumar4@rsystems.com");
			/*
			 * message.addRecipients(Message.RecipientType.TO,
			 * "Ratee.bhatt@rsystems.com");
			 * message.addRecipients(Message.RecipientType.TO,
			 * "Mahesh.yadav@rsystems.com");
			 * message.addRecipients(Message.RecipientType.TO,
			 * "Sachin.shukla@rsystems.com");
			 * message.addRecipients(Message.RecipientType.TO,
			 * "Savita.kumari@rsystems.com");
			 */
			// message.addRecipients(Message.RecipientType.TO,"BMSPractise@rsystems.com");
			// Set subject
			message.setSubject(subject);
			message.setText(body);
			BodyPart objMessageBodyPart = new MimeBodyPart();
			// objMessageBodyPart.setText("This is a Auto generated mail,Plase
			// Do Not
			// Reply!!"+newLine+"RSI_BMS_AutomationReport"+newLine+Commons.BuildVersion+".html");
			System.out.println("Composing message body.");
			System.out.println(TestSetUp.TotalPass);
			System.out.println(TestSetUp.TotalFail);
			StringBuilder htmlStreamBuilder = new StringBuilder();
			htmlStreamBuilder.append(
					"<h5 style=" + "font-family:Verdana;>" + "Hi Team,<div style=" + "color:#696969;></div></h5>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<h2 style=" + "font-family:Verdana;>"
					+ "*****Test Suite run has completed sucessfully.*****</h2>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append(
					"<h3 style=" + "font-family:Verdana;>" + "Please find attached Automation Report for:<div style="
							+ "color:#696969;>" + TestSetUp.emailtitle + "</div></h3>");
			// String url = Commons.readPropertyValue("URL");
			// htmlStreamBuilder.append("<a href="+url+">Link to Revflow</a>");
			htmlStreamBuilder.append("<center><h2 style=" + "font-family:Verdana;>" + TestSetUp.browser.toUpperCase()
					+ "  " + "Browser" + "<div style=" + "color:#696969;></div></h2></center>");
			htmlStreamBuilder.append("<h4 style=" + "font-family:Verdana;>" + "Total Pass:<div style="
					+ "color:#696969;>" + TestSetUp.TotalPass + "</div></h3>");
			htmlStreamBuilder.append("<h4 style=" + "font-family:Verdana;>" + "Total Fail:<div style="
					+ "color:#696969;>" + TestSetUp.TotalFail + "</div></h3>");
			if (TestSetUp.TotalFail == 0) {
				htmlStreamBuilder
						.append("<br><br><h2 style=" + "color:#28b20f;font-family:Verdana;>" + "100% PASS</h2>");
				for (int i = 0; i < TestSetUp.TotalPass; i++) {
					// System.out.println(TestSetUp.passedtests.get(i));
					htmlStreamBuilder.append("<h5 style=" + "color:#28b20f;font-family:Verdana;>"
							+ TestSetUp.passedtests.get(i) + "</h5>");
				}
			} else {
				htmlStreamBuilder.append("<br><br><h2 style=" + "color:#28b20f;font-family:Verdana;>" + "PASS</h2>");
				for (int i = 0; i < TestSetUp.TotalPass; i++) {
					// System.out.println(TestSetUp.passedtests.get(i));
					htmlStreamBuilder.append("<h5 style=" + "color:#28b20f;font-family:Verdana;>"
							+ TestSetUp.passedtests.get(i) + "</h5>");
				}
				htmlStreamBuilder.append("<br><br><h2 style=" + "color:#DC143C;font-family:Verdana;>" + "FAILED</h2>");
				for (int i = 0; i < TestSetUp.TotalFail; i++) {
					// System.out.println(TestSetUp.passedtests.get(i));
					htmlStreamBuilder.append("<h5 style=" + "color:#DC143C;font-family:Verdana;>"
							+ TestSetUp.failedtests.get(i) + "</h5>");
				}
			}
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br><br><h2 style=" + "color:#DC143C;font-family:Verdana;>"
					+ "**NOTE:-This is an Auto Generated System E-Mail.Please do not reply **</h2>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<br>");
			htmlStreamBuilder.append("<h5 style=" + "font-family:Verdana;>" + "Thanks & Regards,<div style="
					+ "color:#696969;></div></h5>");
			htmlStreamBuilder.append(
					"<h5 style=" + "font-family:Verdana;>" + "Ajay Kumar<div style=" + "color:#696969;></div></h5>");
			String htmlStream = htmlStreamBuilder.toString();
			Multipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlStream, "text/html; charset=utf-8");
			multipart.addBodyPart(htmlPart);
			// multipart.addBodyPart(objMessageBodyPart);
			objMessageBodyPart = new MimeBodyPart();
			// Set path to the pdf report file
			String filename = ExtentManager.filePath;
			DataSource source = new FileDataSource(filename);
			objMessageBodyPart.setDataHandler(new DataHandler(source));
			objMessageBodyPart.setFileName(TestSetUp.emailtitle + ".html");
			// messageBodyPart.setFileName(attachmentName);
			multipart.addBodyPart(objMessageBodyPart);
			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}