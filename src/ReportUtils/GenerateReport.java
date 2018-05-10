
  package ReportUtils; 
  import org.automationtesting.excelreport.Xl;
  public class GenerateReport { 
  public static final String EXcelfilePath = System.getProperty("user.dir")+"/Report/";
  public static void createExcelreport() throws Exception { 
	  
// Xl.generateReport("excel-report.xlsx");
  Xl.generateReport(EXcelfilePath,"excel-report.xlsx"); } }
 