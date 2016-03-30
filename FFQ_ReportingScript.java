package FFQ_Utilities;
import FFQ_DriverScript.FFQ_GlobalVariables;


import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.hamcrest.core.IsNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import FFQ_DriverScript.FFQ_DriverCreation;
import FFQ_DriverScript.FFQ_ExecutionofScripts;
import FFQ_MainExecution.FFQ_Starting_of_Execution;

import com.thoughtworks.selenium.Selenium;

/**
 * FFQ_ReportingScript --- Class for generation detail and high level reports
 * 
 * @author csc
 */

public class FFQ_ReportingScript extends FFQ_DriverCreation {
	private String strDetails;
	
	public FFQ_ReportingScript(String testcasename) {
		strDetails = testcasename;
		

	}

	
	public FFQ_ReportingScript (){
		
	}

	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd-hh.mm.ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	

	private String strAbsolutepath = new File("").getAbsolutePath();
	private String strScreenshot;
	static String strAbsolutePath = new File("").getAbsolutePath();
	public String Randomtime;
	
	


	List<Integer> listPassDetails = new ArrayList<Integer>(0);
	List<Integer> listFailDetails = new ArrayList<Integer>(0);
	List<Integer> listTotalStepsDetails = new ArrayList<Integer>(0);
	List<String> listReportFileDetails = new ArrayList<String>(0);

	String strScenarioDetails = null;
	String strPassDetails = null;
	String strFailDetails = null;
	String strTotalStepsDetails = null;
	String strReportFileDetails = null;
	int iPassCount = 0;
	int iFailCount = 0;


	public static int month;
	public static int day;
	public static int year;

	public String strFinalStartTime;
	public String strFinalStopTime;

	public String strStartTime;
	public String strStopTime;

	public float timeElapsed;
	public long startTime;
	public long stopTime;

	public Calendar calendar = new GregorianCalendar();

	public FFQ_Starting_of_Execution exe = new FFQ_Starting_of_Execution();

	private static int hour;
	private static int min;
	private static int sec;
	private static String am_pm;
	String sPathTillMonth;
	String sPathTillDate;
	String sPathTillUserName;
	String sPathTillBrowserName;
	String strBrowser;
	String sEnv;

	

	private static boolean running = false;
	public String[] monthName = { "FFQ_JANAURY", "FFQ_FEBRUARY", "FFQ_MARCH", "FFA_APRIL",
			"FFQ_MAY", "FFQ_JUNE", "FFQ_JULY", "FFQ_AUGUST", "FFQ_SEPTEMBER", "FFQ_OCTOBER",
			"FFQ_NOVEMBER", "FFQ_DECEMBER" };

	// For screen Shot

	// public FFQ_ExecutionofScripts scriptExe = new FFQ_ExecutionofScripts();
	private String strReportFile, strBatchReportFile, strScreenshotPath, Reporttime;
	
	private int iNoOfRows;
	public int iCurrentIndex;
	public int counter;
	

	public void setStrBrowser(String strBrowser) {
		this.strBrowser = strBrowser;
	}
	public void setEnv(String sEnv) {
		this.sEnv = sEnv;
	}

	public void start(Calendar calander) {

		Long actualStartTime = System.currentTimeMillis();
		hour = calander.get(Calendar.HOUR);
		min = calander.get(Calendar.MINUTE);
		sec = calander.get(Calendar.SECOND);
		if (calander.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";

		running = true;
		startTime = actualStartTime;
		strStartTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;
	}

	public String stop() {
		String strStoTime = null;
		Calendar stop = new GregorianCalendar();
		Long actualstopTime = System.currentTimeMillis();
		hour = stop.get(Calendar.HOUR);
		min = stop.get(Calendar.MINUTE);
		sec = stop.get(Calendar.SECOND);
		if (stop.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";
		// .currentTimeMillis();
		stopTime = actualstopTime;
		strStoTime = "" + hour + ":" + min + ":" + sec + " " + am_pm;

		running = false;
		return strStoTime;
	}

	// Total Execution time in milliseconds
	public float getElapsedTime() {
		float elapsedTime = 0;
		if (running) {
			elapsedTime = (System.currentTimeMillis() - startTime);
			// .currentTimeMillis() - startTime);
		} else {
			elapsedTime = (stopTime - startTime);
		}
		return elapsedTime;
	}

	public String now(){
		String strComponent = null;	
		{
		
			strComponent = "BMC REMEDY";
			File oFilePathTillUserName = new File(sPathTillUserName);
			if (!oFilePathTillUserName.exists()) {
				oFilePathTillUserName.mkdir();
			}
			File osPathTillMonth = new File(sPathTillMonth);
			if (!osPathTillMonth.exists()) {
				osPathTillMonth.mkdir();
			}
			
			if(!(sPathTillBrowserName== null)) {
			File osPathTillBrowser = new File(sPathTillBrowserName);
			if (!osPathTillBrowser.exists()) {
				osPathTillBrowser.mkdir();
			}
			}
			
			if(!(sPathTillDate== null)) {
			File osPathTilldate = new File(sPathTillDate);
			if (!osPathTilldate.exists()) {
				osPathTilldate.mkdir();
			}
			}
			File resultFolder = null;
			if(!(sPathTillDate== null)) {
			resultFolder = new File(sPathTillDate + "/"+ strDetails);
			if (!resultFolder.exists()) {
				resultFolder.mkdir();
			}
			}
			
			//String strDelete = "FFQ_ExecutionofScripts";
			if(!(sPathTillDate== null)) {
			File DeleteScriptExeFolder = new File(sPathTillDate + "/"+ "FFQ_ExecutionofScripts");
			if (DeleteScriptExeFolder.exists()) {
				DeleteScriptExeFolder.delete();
			}
			}
			
			//if(!(resultFolder== null)) {
			String strScreenshots = "screenshot";
			File screenshotFolder = new File(resultFolder + "/" +strScreenshots );
			if (!screenshotFolder.exists()) {
				screenshotFolder.mkdir();
			}		
			//}
		
		//String strScreenshotPath = strAbsolutePath + "/FFQ_Results_Files/screenshot/";
			
		if(!(sPathTillMonth== null)) {
			//String screenshotFolder = null;
			//strScreenshotPath = screenshotFolder + "/" + "screenshot";
			strScreenshotPath = screenshotFolder + "/";
			
			Calendar cal = Calendar.getInstance();
			month = cal.get(Calendar.MONTH) + 1;
			day = cal.get(Calendar.DAY_OF_MONTH);
			year = cal.get(Calendar.YEAR);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			Random rand = new Random();
			int num = rand.nextInt(100);
			Randomtime = sdf.format(cal.getTime()) + "_" + num;
			strScreenshot = (String) (strScreenshotPath + strDetails + "_"
					+ Randomtime + ".png");
			
			return Randomtime;
		}
		
		}
		return "";
	}
	

	public void ReportGenerator() throws IOException {
		FileWriter aWriter = null;
		String strComponent = null;				
		// String strBrowser = exe.getExecutionBrowser();
		
		WebDriver tmpWebDriver = FFQ_DriverCreation.returnWebDriver();
		String strBrowserVersion = "NA";
		String strBrowser = "NA";
		try {
			Capabilities cap = ((RemoteWebDriver) tmpWebDriver).getCapabilities();
			strBrowserVersion = cap.getVersion();
			strBrowser = cap.getBrowserName();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String strOSName = System.getProperty("os.name");

		Calendar cal = Calendar.getInstance();
		int iMonth = cal.get(Calendar.MONTH);
		String sMonthName = monthName[iMonth];
		//String strBrowser = FFQ_ExcelReadingScript.ReadBroserName();
		//strBrowser=strBrowser.replace(",", "");
		String userName = System.getProperty("user.name");
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sDate = sdf.format(cal.getTime());
		sPathTillUserName = strAbsolutepath + FFQ_GlobalVariables.fResultPath + userName;
		sPathTillMonth = sPathTillUserName + "/" + sMonthName;
		sPathTillBrowserName = null;
		sPathTillBrowserName = sPathTillMonth + "/" + strBrowser + "  " + strBrowserVersion;
		sPathTillDate = sPathTillBrowserName + "/" + sDate;
		
		

		

		try {
			String sInputFilePath = System.getProperty("File");
			FFQ_ExcelReadingScript read = new FFQ_ExcelReadingScript();
			read.setInputFile(sInputFilePath);
			int sObjCount = read.ReadObjectiveCount(strDetails);
			
			strComponent = "BMC REMEDY";
			String time = now();
			File oFilePathTillUserName = new File(sPathTillUserName);
			if (!oFilePathTillUserName.exists()) {
				oFilePathTillUserName.mkdir();
			}
			File osPathTillMonth = new File(sPathTillMonth);
			if (!osPathTillMonth.exists()) {
				osPathTillMonth.mkdir();
			}
			
			File osPathTillBrowser = new File(sPathTillBrowserName);
			if (!osPathTillBrowser.exists()) {
				osPathTillBrowser.mkdir();
			}
			
			File osPathTilldate = new File(sPathTillDate);
			if (!osPathTilldate.exists()) {
				osPathTilldate.mkdir();
			}
			File resultFolder = new File(sPathTillDate + "/" + strDetails);
			if (!resultFolder.exists()) {
				resultFolder.mkdir();
			}
			String strScreenshots = "FFQ_SCREENSHOTS";
			File screenshotFolder = new File(resultFolder + "/" +strScreenshots );
			if (!screenshotFolder.exists()) {
				screenshotFolder.mkdir();
			}
			
			File cssFile = new File(sPathTillDate + "/" + strDetails + "/"+FFQ_GlobalVariables.fPages);
			if (!cssFile.exists()) {
				FileUtils.copyDirectory(new File(strAbsolutepath
						+ FFQ_GlobalVariables.fResultPath+FFQ_GlobalVariables.fPages), new File(sPathTillDate + "/"
						+ strDetails + "/"+FFQ_GlobalVariables.fPages));
			}

			Reporttime = "_Report_" + time + ".html";
			strReportFile = resultFolder + "/" + strDetails + Reporttime;

			

			aWriter = new FileWriter(strReportFile, true);

			aWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
			aWriter.write("<html>");
			aWriter.write("<head>");

			aWriter.write("<link type=\"text/css\" href=\"./PAGES/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
			aWriter.write("<link type=\"text/css\" href=\"./PAGES/css/myStyle.css\" rel=\"Stylesheet\" />");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/jquery-1.6.2.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/jquery-ui-1.8.16.custom.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/my.js\"></script>");
			aWriter.write("</head>");
			aWriter.write("<script type=\"text/javascript\">");

			aWriter.write("$(document).ready(function(){");
			aWriter.write("$(\"#tabs_environment,#tabs_plan,#tabs_set,#set_content_tabs\").tabs({");
			aWriter.write("selected: 0,");
			aWriter.write("deselectable: true");
			aWriter.write("});");
			aWriter.write("$(\"button\", \".btn\" ).button();");
			aWriter.write("$(\"button\", \".plan_step_list\" ).button();");
			aWriter.write("$(\"#tabs_plan\").hide();");
			aWriter.write("$(\"#tabs_set\").hide();");
			aWriter.write("$(\"#dialog\").dialog({");
			aWriter.write("autoOpen:false,");
			aWriter.write("modal:true,");
			aWriter.write("buttons:{");
			aWriter.write(" Store:function(){");
			aWriter.write("return;");
			aWriter.write("}");
			aWriter.write("},");
			aWriter.write("dialogClass: 'f2',");
			aWriter.write("resizable: true,");
			aWriter.write("show: 'slide',");
			aWriter.write("height:120");
			aWriter.write("});");

			aWriter.write("});");
			aWriter.write("</script>");
			aWriter.write("<body>");
			aWriter.write("<div class=\"page_container\">");
			aWriter.write("<div class=\"head\">");
			aWriter.write("<img alt=\"csc\" src=\"./PAGES/images/logo160.gif\">");
			aWriter.write("</div>");
			aWriter.write("<div class=\"content\">");
			aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
			aWriter.write("<tr>");
			aWriter.write("<td valign=\"top\">");
			aWriter.write("<div class=\"right_content\">");
			aWriter.write("<div id=\"tabs_environment\">");
			aWriter.write("<ul>");
			aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
					+ "Execution Report </a></li>");
			aWriter.write("</ul>");
			aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
			aWriter.write("<div style=\"margin-top: 10px;\">");
			aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr>");
			aWriter.write("<td><b>Execution Date</b></td>");
			aWriter.write("<td><b>Execution Start Time</b></td>");
			aWriter.write("<td><b>TestCase Name</b></td>");
			aWriter.write("<td><b>Objectives Count</b></td>");
			aWriter.write("<td><b>Operating System</b></td>");
			aWriter.write("<td><b>Browser</b></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td>" + day + "-" + month + "-" + year + "</td>");
			aWriter.write("<td>" + strStartTime + "</td>");
			

			aWriter.write("<td>" + strDetails + "</td>");
			aWriter.write("<td>" + sObjCount + "</td>");
			aWriter.write("<td>" + strOSName + "</td>");
			aWriter.write("<td>" + strBrowser + " : " + strBrowserVersion + "</td>");
			aWriter.write("</tr>");
			aWriter.write(" <tr class=\"list_table_tr\">");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>Test Scenario Name</b></td>");
			aWriter.write("<td><b>Test Step Description</b></td>");
			aWriter.write("<td><b>Test Data</b></td>");
			aWriter.write("<td><b>Status</b></td>");
			aWriter.write("<td><b>Result Description</b></td>");
			aWriter.write("<td><b>ScreenShot</b></td>");
			aWriter.write("</tr>");

			aWriter.flush();
			aWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public String batchReportSummaryHeader() throws IOException, BiffException {
		FileWriter aWriter = null;
		String strComponent = null;
		
		String strOSName = System.getProperty("os.name");

		Calendar cal = Calendar.getInstance();
		int iMonth = cal.get(Calendar.MONTH);
		String sMonthName = monthName[iMonth];
		String userName = System.getProperty("user.name");
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sDate = sdf.format(cal.getTime());
		sPathTillUserName = strAbsolutepath + FFQ_GlobalVariables.fResultPath + userName;
		sPathTillMonth = sPathTillUserName + "/" + sMonthName;
		//sPathTillDate = sPathTillMonth + "/" + sDate;
		
			
		
		try{
			strComponent = "BMC REMEDY";
			String time = now();
			File oFilePathTillUserName = new File(sPathTillUserName);
			if (!oFilePathTillUserName.exists()) {
				oFilePathTillUserName.mkdir();
			}
			File osPathTillMonth = new File(sPathTillMonth);
			if (!osPathTillMonth.exists()) {
				osPathTillMonth.mkdir();
			}
			
			if(!(sPathTillBrowserName== null))	{
				File osPathTillBrowser = new File(sPathTillBrowserName);
				if (!osPathTillBrowser.exists()) {
					osPathTillBrowser.mkdir();
				}
			}
			
			if(!(sPathTillDate== null))	{
			File osPathTilldate = new File(sPathTillDate);
			if (!osPathTilldate.exists()) {
				osPathTilldate.mkdir();
			}
			}
			File resultFolder = new File(sPathTillMonth);
			if (!resultFolder.exists()) {
				resultFolder.mkdir();
			}
			File cssFile = new File(sPathTillMonth + "/"+FFQ_GlobalVariables.fPages);
			if (!cssFile.exists()) {
				FileUtils.copyDirectory(new File(strAbsolutepath
						+ FFQ_GlobalVariables.fResultPath+FFQ_GlobalVariables.fPages), new File(sPathTillMonth + "/"+FFQ_GlobalVariables.fPages));
			}
			
			
			
			strBatchReportFile = resultFolder + "/" + "FFQ_FINAL_Report_" +time+ ".html";
			
			aWriter = new FileWriter(strBatchReportFile, true);

			aWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
			aWriter.write("<html>");
			aWriter.write("<head>");

			aWriter.write("<link type=\"text/css\" href=\"./PAGES/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
			aWriter.write("<link type=\"text/css\" href=\"./PAGES/css/myStyle.css\" rel=\"Stylesheet\" />");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/jquery-1.6.2.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/jquery-ui-1.8.16.custom.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/my.js\"></script>");
			aWriter.write("</head>");
			aWriter.write("<script type=\"text/javascript\">");

			aWriter.write("$(document).ready(function(){");
			aWriter.write("$(\"#tabs_environment,#tabs_plan,#tabs_set,#set_content_tabs\").tabs({");
			aWriter.write("selected: 0,");
			aWriter.write("deselectable: true");
			aWriter.write("});");
			aWriter.write("$(\"button\", \".btn\" ).button();");
			aWriter.write("$(\"button\", \".plan_step_list\" ).button();");
			aWriter.write("$(\"#tabs_plan\").hide();");
			aWriter.write("$(\"#tabs_set\").hide();");
			aWriter.write("$(\"#dialog\").dialog({");
			aWriter.write("autoOpen:false,");
			aWriter.write("modal:true,");
			aWriter.write("buttons:{");
			aWriter.write(" Store:function(){");
			aWriter.write("return;");
			aWriter.write("}");
			aWriter.write("},");
			aWriter.write("dialogClass: 'f2',");
			aWriter.write("resizable: true,");
			aWriter.write("show: 'slide',");
			aWriter.write("height:120");
			aWriter.write("});");

			aWriter.write("});");
			aWriter.write("</script>");
			aWriter.write("<body>");
			aWriter.write("<div class=\"page_container\">");
			aWriter.write("<div class=\"head\">");
			aWriter.write("<img alt=\"csc\" src=\"./PAGES/images/logo160.gif\">");
			aWriter.write("</div>");
			aWriter.write("<div class=\"content\">");
			aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
			aWriter.write("<tr>");
			aWriter.write("<td valign=\"top\">");
			aWriter.write("<div class=\"right_content\">");
			aWriter.write("<div id=\"tabs_environment\">");
			aWriter.write("<ul>");
			aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">BATCH EXECUTION </a></li>");
			aWriter.write("</ul>");
			aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
			aWriter.write("<div style=\"margin-top: 10px;\">");			
			aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr>");
			aWriter.write("<td><b>Execution Date</b></td>");			
			aWriter.write("<td><b>Operating System</b></td>");		
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td>" + day + "-" + month + "-" + year + "</td>");			
			aWriter.write("<td>" + strOSName + "</td>");		
			aWriter.write("</tr>");
			aWriter.write(" <tr class=\"list_table_tr\">");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>TestCase Name</b></td>");
			aWriter.write("<td><b>Start Test</b></td>");
			aWriter.write("<td><b>End Test</b></td>");
			aWriter.write("<td><b>Elapsed Time</b></td>");
			aWriter.write("<td><b>Browser</b></td>");
			aWriter.write("<td><b>Steps Passed</b></td>");
			aWriter.write("<td><b>Steps Failed</b></td>");
			aWriter.write("<td><b>Status</b></td>");
			aWriter.write("<td><b>Objectives</b></td>");
			aWriter.write("</tr>");

			aWriter.flush();
			aWriter.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return strBatchReportFile;
	}
	
	public void batchReportSummaryBody(String strBatchReportFileName) throws IOException{
		FileWriter aWriter = null;
		
		listFailDetails.add(iFailCount);
		listPassDetails.add(iPassCount);
		listTotalStepsDetails.add(iPassCount + iFailCount);
		listReportFileDetails.add(strReportFile);
		
		strBatchReportFile = strBatchReportFileName;
		
		String sInputFilePath = System.getProperty("File");
		FFQ_ExcelReadingScript read = new FFQ_ExcelReadingScript();
		read.setInputFile(sInputFilePath);
		int sObjCount = read.ReadObjectiveCount(strDetails);
		
		WebDriver tmpWebDriver = FFQ_DriverCreation.returnWebDriver();
		String strBrowserVersion = "NA";
		try {
			Capabilities cap = ((RemoteWebDriver) tmpWebDriver).getCapabilities();
			strBrowserVersion = cap.getVersion();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 
		try{
			int seconds = (int) (timeElapsed / 1000) % 60;
			int minutes = (int) ((timeElapsed / (1000 * 60)) % 60);
			
			aWriter = new FileWriter(strBatchReportFile, true);
			
			
			aWriter.write("<tr class=\"list_table_tr\">");
			
			//aWriter.write("<td><a href =\"file:///"+strReportFile+"\"target=\"_blank\">"+strDetails+"</td>");
			
			Calendar cal = Calendar.getInstance();
			int iMonth = cal.get(Calendar.MONTH);
			String sMonthName = monthName[iMonth];
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String sDate = sdf.format(cal.getTime());
			
			String TestCaseFolderPath = sMonthName + "/" + strBrowser + "  " + strBrowserVersion 
					+ "/" + sDate + "/" + strDetails + "/" + strDetails + Reporttime ;
			
			aWriter.write("<td><a href =\"../" + TestCaseFolderPath +"\"target=\"_blank\">"+strDetails+"</td>");
			aWriter.write("<td>"+strStartTime+"</td>");
			aWriter.write("<td>"+strStopTime+"</td>");
			aWriter.write("<td>" + minutes + " min " + seconds + " seconds" + "</td>");
			aWriter.write("<td>"+strBrowser + " : " + strBrowserVersion +"</td>");
			aWriter.write("<td>"+iPassCount+"</td>");
			aWriter.write("<td>"+iFailCount+"</td>");
			
			if(iFailCount==0){
				aWriter.write("<td><font color=\"Green\">"+"Pass</b></td>");
			} else {
				aWriter.write("<td><font color=\"Red\">"+"Fail</b></td>");
			}
			aWriter.write("<td>"+sObjCount+"</td>");
			aWriter.write("</tr>");
			aWriter.flush();
			aWriter.close();
			
			if (strBrowser.equalsIgnoreCase("Internet Explorer")){
				FFQ_ExecutionofScripts.iIETimeElapsed = FFQ_ExecutionofScripts.iIETimeElapsed + timeElapsed;
			}
			
			if (strBrowser.equalsIgnoreCase("Firefox")){
				FFQ_ExecutionofScripts.iFFTimeElapsed = FFQ_ExecutionofScripts.iFFTimeElapsed + timeElapsed;
			}
			
			if (strBrowser.equalsIgnoreCase("Chrome")){
				FFQ_ExecutionofScripts.iCHTimeElapsed = FFQ_ExecutionofScripts.iCHTimeElapsed + timeElapsed;
			}
			
			if (strBrowser.equalsIgnoreCase("Safari")){
				FFQ_ExecutionofScripts.iASTimeElapsed = FFQ_ExecutionofScripts.iASTimeElapsed + timeElapsed;
			}
			if (strBrowser.equalsIgnoreCase("Firefox") && iFailCount == 0){
				FFQ_ExecutionofScripts.iPassFF++;
				FFQ_ExecutionofScripts.iObjCntPassFF = FFQ_ExecutionofScripts.iObjCntPassFF+ sObjCount;
			}
			if (strBrowser.equalsIgnoreCase("Firefox") && iFailCount > 0){
				FFQ_ExecutionofScripts.iFailFF++;
				FFQ_ExecutionofScripts.iObjCntFailFF = FFQ_ExecutionofScripts.iObjCntFailFF+ sObjCount;
			}
			if (strBrowser.equalsIgnoreCase("Internet Explorer") && iFailCount == 0){
				FFQ_ExecutionofScripts.iPassIE++;
				FFQ_ExecutionofScripts.iObjCntPassIE = FFQ_ExecutionofScripts.iObjCntPassIE+ sObjCount;
			}
			if (strBrowser.equalsIgnoreCase("Internet Explorer") && iFailCount > 0){
				FFQ_ExecutionofScripts.iFailIE++;
				FFQ_ExecutionofScripts.iObjCntFailIE = FFQ_ExecutionofScripts.iObjCntFailIE+ sObjCount;
			}
			if (strBrowser.equalsIgnoreCase("Chrome") && iFailCount == 0){
				FFQ_ExecutionofScripts.iPassCH++;
				FFQ_ExecutionofScripts.iObjCntPassCH = FFQ_ExecutionofScripts.iObjCntPassCH+ sObjCount;
			}
			if (strBrowser.equalsIgnoreCase("Chrome") && iFailCount > 0){
				FFQ_ExecutionofScripts.iFailCH++;
				FFQ_ExecutionofScripts.iObjCntFailCH = FFQ_ExecutionofScripts.iObjCntFailCH+ sObjCount;
			}
			if (strBrowser.equalsIgnoreCase("Safari") && iFailCount == 0){
				FFQ_ExecutionofScripts.iPassAS++;
				FFQ_ExecutionofScripts.iObjCntPassAS = FFQ_ExecutionofScripts.iObjCntPassAS+ sObjCount;
			}
			if (strBrowser.equalsIgnoreCase("Safari") && iFailCount > 0){
				FFQ_ExecutionofScripts.iFailAS++;
				FFQ_ExecutionofScripts.iObjCntFailAS = FFQ_ExecutionofScripts.iObjCntFailAS+ sObjCount;
			}
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void batchReportSummaryFooter(String strBatchReportFileName){
		FileWriter aWriter = null;
		WebDriver tmpWebDriver = FFQ_DriverCreation.returnWebDriver();
		try {
			Capabilities cap = ((RemoteWebDriver) tmpWebDriver).getCapabilities();
			String strBrowserVersion = cap.getVersion();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		strBatchReportFile = strBatchReportFileName;
		int minutes, seconds, hours;
		
		try{
			
			aWriter = new FileWriter(strBatchReportFile, true);
			
			
			aWriter.write("</table>");
			aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td valign=\"top\">");
			aWriter.write("<div class=\"right_content\">");
			aWriter.write("<div id=\"tabs_environment\">");
			aWriter.write("<ul>");
			aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">Report Summary </a></li>");
			aWriter.write("</ul>");
			aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
			aWriter.write("<div style=\"margin-top: 10px;\">");
			aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"5\" ><tr class=\"list_table_tr\">");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>Browser</b></td>");
			aWriter.write("<td><b>Test Time Elapsed</b></td>");
			aWriter.write("<td><b>Total Objectives</b></td>");
			aWriter.write("<td><b>Objective Pass</b></td>");
			aWriter.write("<td><b>Objective Fail</b></td>");			
			//aWriter.write("<td><b>Pass</b></td>");
			//aWriter.write("<td><b>Fail</b></td>");
			//aWriter.write("<td><b>Total</b></td>");
			aWriter.write("</tr>");
			
			aWriter.write("<tr class=\"list_table_tr\">");
			//aWriter.write("<td>Internet Explorer : " + strBrowserVersion +"</td>");
			aWriter.write("<td>Internet Explorer</td>");
			seconds = (int) (FFQ_ExecutionofScripts.iIETimeElapsed / 1000) % 60;
			minutes = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60)) % 60);			
			hours = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60 * 60)));
			aWriter.write("<td>" + hours + " hrs " + minutes + " min " + seconds + " seconds" + "</td>");
			aWriter.write("<td>" + (FFQ_ExecutionofScripts.iObjCntPassIE+FFQ_ExecutionofScripts.iObjCntFailIE) + "</td>");
			aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iObjCntPassIE + "</td>");
			aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iObjCntFailIE + "</td>");
			
			//aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iPassIE + "</td>");
			//aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iFailIE + "</td>");
			//aWriter.write("<td>" + (FFQ_ExecutionofScripts.iFailIE+FFQ_ExecutionofScripts.iPassIE) + "</td>");
			aWriter.write("</tr>");
			
			aWriter.write("<tr class=\"list_table_tr\">");
			//aWriter.write("<td>Mozilla Firefox : " + strBrowserVersion + "</td>");
			aWriter.write("<td>Mozilla Firefox</td>");
			seconds = (int) (FFQ_ExecutionofScripts.iIETimeElapsed / 1000) % 60;
			minutes = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60)) % 60);			
			hours = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60 * 60)));
			aWriter.write("<td>" + hours + " hrs " + minutes + " min " + seconds + " seconds" + "</td>");
			aWriter.write("<td>" + (FFQ_ExecutionofScripts.iObjCntPassFF+FFQ_ExecutionofScripts.iObjCntFailFF) + "</td>");
			aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iObjCntPassFF + "</td>");
			aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iObjCntFailFF + "</td>");
			
			//aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iPassFF + "</td>");
			//aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iFailFF + "</td>");
			//aWriter.write("<td>" + (FFQ_ExecutionofScripts.iFailFF+FFQ_ExecutionofScripts.iPassFF) + "</td>");
			aWriter.write("</tr>");
			
			aWriter.write("<tr class=\"list_table_tr\">");
			//aWriter.write("<td>Google Chrome : " + strBrowserVersion + "</td>");
			aWriter.write("<td>Google Chrome</td>");
			seconds = (int) (FFQ_ExecutionofScripts.iIETimeElapsed / 1000) % 60;
			minutes = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60)) % 60);			
			hours = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60 * 60)));
			aWriter.write("<td>" + hours + " hrs " + minutes + " min " + seconds + " seconds" + "</td>");
			aWriter.write("<td>" + (FFQ_ExecutionofScripts.iObjCntPassCH+FFQ_ExecutionofScripts.iObjCntFailCH) + "</td>");
			aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iObjCntPassCH + "</td>");
			aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iObjCntFailCH + "</td>");
			
			//aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iPassCH + "</td>");
			//aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iFailCH + "</td>");
			//aWriter.write("<td>" + (FFQ_ExecutionofScripts.iFailCH+FFQ_ExecutionofScripts.iPassCH) + "</td>");
			aWriter.write("</tr>");
			
			aWriter.write("<tr class=\"list_table_tr\">");
			//aWriter.write("<td>Apple Safari : " + strBrowserVersion + "</td>");
			aWriter.write("<td>Apple Safari</td>");
			seconds = (int) (FFQ_ExecutionofScripts.iIETimeElapsed / 1000) % 60;
			minutes = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60)) % 60);			
			hours = (int) ((FFQ_ExecutionofScripts.iIETimeElapsed / (1000 * 60 * 60)));
			aWriter.write("<td>" + hours + " hrs " + minutes + " min " + seconds + " seconds" + "</td>");

			aWriter.write("<td>" + (FFQ_ExecutionofScripts.iObjCntPassAS+FFQ_ExecutionofScripts.iObjCntFailAS) + "</td>");
			aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iObjCntPassAS + "</td>");
			aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iObjCntFailAS + "</td>");
			
			//aWriter.write("<td> <font color=\"Green\">" + FFQ_ExecutionofScripts.iPassAS + "</td>");
			//aWriter.write("<td><font color=\"Red\">" + FFQ_ExecutionofScripts.iFailAS + "</td>");
			//aWriter.write("<td>" + (FFQ_ExecutionofScripts.iFailAS+FFQ_ExecutionofScripts.iPassAS) + "</td>");
			aWriter.write("</tr>");
			
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>Total</b></td>");
			seconds = (int) ((FFQ_ExecutionofScripts.strExecutionStop - FFQ_ExecutionofScripts.strExecutionStart) / 1000) % 60;
			minutes = (int) (((FFQ_ExecutionofScripts.strExecutionStop - FFQ_ExecutionofScripts.strExecutionStart) / (1000 * 60)) % 60);
			hours	= (int) (((FFQ_ExecutionofScripts.strExecutionStop - FFQ_ExecutionofScripts.strExecutionStart) / (1000 * 60 * 60)));
			aWriter.write("<td>" + hours + " hrs " + minutes + " min " + seconds + " seconds" + "</td>");
			
			aWriter.write("<td>" + (FFQ_ExecutionofScripts.iObjCntPassIE+FFQ_ExecutionofScripts.iObjCntPassFF+FFQ_ExecutionofScripts.iObjCntPassCH+FFQ_ExecutionofScripts.iObjCntPassAS+FFQ_ExecutionofScripts.iObjCntFailIE+FFQ_ExecutionofScripts.iObjCntFailFF+FFQ_ExecutionofScripts.iObjCntFailCH+FFQ_ExecutionofScripts.iObjCntFailAS) + "</td>");
			aWriter.write("<td> <font color=\"Green\">" + (FFQ_ExecutionofScripts.iObjCntPassIE+FFQ_ExecutionofScripts.iObjCntPassFF+FFQ_ExecutionofScripts.iObjCntPassCH+FFQ_ExecutionofScripts.iObjCntPassAS) + "</td>");
			aWriter.write("<td><font color=\"Red\">" + (FFQ_ExecutionofScripts.iObjCntFailIE+FFQ_ExecutionofScripts.iObjCntFailFF+FFQ_ExecutionofScripts.iObjCntFailCH+FFQ_ExecutionofScripts.iObjCntFailAS) + "</td>");
			
			
			//aWriter.write("<td> <font color=\"Green\">" + (FFQ_ExecutionofScripts.iPassIE+FFQ_ExecutionofScripts.iPassFF+FFQ_ExecutionofScripts.iPassCH+FFQ_ExecutionofScripts.iPassAS) + "</td>");
			//aWriter.write("<td><font color=\"Red\">" + (FFQ_ExecutionofScripts.iFailIE+FFQ_ExecutionofScripts.iFailFF+FFQ_ExecutionofScripts.iFailCH+FFQ_ExecutionofScripts.iFailAS) + "</td>");
			//aWriter.write("<td>" + (FFQ_ExecutionofScripts.iPassIE+FFQ_ExecutionofScripts.iPassFF+FFQ_ExecutionofScripts.iPassCH+FFQ_ExecutionofScripts.iPassAS+FFQ_ExecutionofScripts.iFailIE+FFQ_ExecutionofScripts.iFailFF+FFQ_ExecutionofScripts.iFailCH+FFQ_ExecutionofScripts.iFailAS) + "</td>");
			aWriter.write("</tr>");
			

			
			aWriter.write("</table>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</td>");
			aWriter.write("</tr>");
			aWriter.write("</table>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</td>");
			aWriter.write("</tr>");
			aWriter.write("</table>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</body>");
			aWriter.write("</html>");
			aWriter.flush();
			aWriter.close();
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public String moduleReportGenerator() {
		FileWriter aWriter = null;
		String strBrowser = exe.getExecutionBrowser();
		String strOSName = System.getProperty("os.name");
		String sTestCaseName = System.getProperty("TestCaseName");
		String sBrowserName = System.getProperty("BrowserName");
		String strDetails = System.getProperty("sheetName");
		String sNoOfRows = System.getProperty("NoOfRows");
		String strSummarizedReportFile = "";
		iNoOfRows = counter;// Integer.parseInt(sNoOfRows);

		try {
			Calendar cal = Calendar.getInstance();
			int iMonth = cal.get(Calendar.MONTH);
			String sMonthName = monthName[iMonth];
			String userName = System.getProperty("user.name");
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String sDate = sdf.format(cal.getTime());
			sPathTillUserName = strAbsolutepath + FFQ_GlobalVariables.fResultPath + userName;
			sPathTillMonth = sPathTillUserName + "/" + sMonthName;
			sPathTillDate = sPathTillMonth + "/" + sDate;
			String time = now();
			File oFilePathTillUserName = new File(sPathTillUserName);
			if (!oFilePathTillUserName.exists()) {
				oFilePathTillUserName.mkdir();
			}
			File osPathTillMonth = new File(sPathTillMonth);
			if (!osPathTillMonth.exists()) {
				osPathTillMonth.mkdir();
			}
			
			File osPathTillBrowser = new File(sPathTillBrowserName);
			if (!osPathTillBrowser.exists()) {
				osPathTillBrowser.mkdir();
			}
			
			File osPathTilldate = new File(sPathTillDate);
			if (!osPathTilldate.exists()) {
				osPathTilldate.mkdir();
			}
			File resultFolder = new File(sPathTillDate + "/" + strDetails);
			if (!resultFolder.exists()) {
				resultFolder.mkdir();
			}
			File cssFile = new File(sPathTillDate + "/" + strDetails + "/"+FFQ_GlobalVariables.fPages);
			if (!cssFile.exists()) {
				FileUtils.copyDirectory(new File(strAbsolutepath
						+ FFQ_GlobalVariables.fResultPath+FFQ_GlobalVariables.fPages), new File(sPathTillDate + "/"
						+ strDetails + "/PAGES"));
			}

			strSummarizedReportFile = resultFolder + "/" + strDetails
					+ "_Summarized_Report_" + time + ".html";

			//aWriter = new FileWriter(strBatchReportFile, true);
			aWriter = new FileWriter(strSummarizedReportFile, true);
			aWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
			aWriter.write("<html>");
			aWriter.write("<head>");

			aWriter.write("<link type=\"text/css\" href=\"./PAGES/css/themes/ui-lightness/jquery-ui-1.8.16.custom.css\" rel=\"Stylesheet\" />");
			aWriter.write("<link type=\"text/css\" href=\"./PAGES/css/myStyle.css\" rel=\"Stylesheet\" />");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/jquery-1.6.2.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/jquery-ui-1.8.16.custom.min.js\"></script>");
			aWriter.write("<script type=\"text/javascript\" src=\"./PAGES/js/my.js\"></script>");
			aWriter.write("</head>");
			aWriter.write("<script type=\"text/javascript\">");

			aWriter.write("$(document).ready(function(){");
			aWriter.write("$(\"#tabs_environment,#tabs_plan,#tabs_set,#set_content_tabs\").tabs({");
			aWriter.write("selected: 0,");
			aWriter.write("deselectable: true");
			aWriter.write("});");
			aWriter.write("$(\"button\", \".btn\" ).button();");
			aWriter.write("$(\"button\", \".plan_step_list\" ).button();");
			aWriter.write("$(\"#tabs_plan\").hide();");
			aWriter.write("$(\"#tabs_set\").hide();");
			aWriter.write("$(\"#dialog\").dialog({");
			aWriter.write("autoOpen:false,");
			aWriter.write("modal:true,");
			aWriter.write("buttons:{");
			aWriter.write(" Store:function(){");
			aWriter.write("return;");
			aWriter.write("}");
			aWriter.write("},");
			aWriter.write("dialogClass: 'f2',");
			aWriter.write("resizable: true,");
			aWriter.write("show: 'slide',");
			aWriter.write("height:120");
			aWriter.write("});");

			aWriter.write("});");
			aWriter.write("</script>");
			aWriter.write("<body>");
			aWriter.write("<div class=\"page_container\">");
			aWriter.write("<div class=\"head\">");
			aWriter.write("<img alt=\"csc\" src=\"./PAGES/images/logo160.gif\">");
			aWriter.write("</div>");
			aWriter.write("<div class=\"content\">");
			aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
			aWriter.write("<tr>");
			aWriter.write("<td valign=\"top\">");
			aWriter.write("<div class=\"right_content\">");
			aWriter.write("<div id=\"tabs_environment\">");
			aWriter.write("<ul>");
			aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
					+ "Summarized Execution Report </a></li>");
			aWriter.write("</ul>");
			aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
			aWriter.write("<div style=\"margin-top: 10px;\">");
			aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr>");
			aWriter.write("<td><b>Execution Date</b></td>");
			aWriter.write("<td><b>Execution Start Time</b></td>");
			aWriter.write("<td><b>Execution End Time</b></td>");
			
			aWriter.write("<td><b>TestCase Name</b></td>");
			aWriter.write("<td><b>Operating System</b></td>");
			aWriter.write("<td><b>Browser</b></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td>" + day + "-" + month + "-" + year + "</td>");
			
			aWriter.write("<td>" + strStartTime + "</td>");
			aWriter.write("<td>" + strStopTime + "</td>");
			aWriter.write("<td>" + Math.round((timeElapsed / (60000)) * 60) +
			" " + "seconds" + "</td>");
			
			aWriter.write("<td>" + strDetails + "</td>");
			aWriter.write("<td>" + strOSName + "</td>");
			aWriter.write("<td>" + strBrowser + "</td>");
			aWriter.write("</tr>");
			aWriter.write(" <tr class=\"list_table_tr\">");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write(" <td></td>");
			aWriter.write("<td></td>");
			aWriter.write("</tr>");
			aWriter.write("<tr class=\"list_table_tr\">");
			aWriter.write("<td><b>Serial Number</b></td>");
			aWriter.write("<td><b>Total Steps</b></td>");
			aWriter.write("<td><b>Pass Steps</b></td>");
			aWriter.write("<td><b>Fail Steps</b></td>");
			aWriter.write("<td><b>Link to detail result</b></td>");
			aWriter.write("</tr>");

			for (int i = 0; i < iNoOfRows; i++) {
				int k = i + 1;
				aWriter.write("<tr class=\"list_table_tr\"><td >" + k + "</td>");
				aWriter.write("<td >" + listTotalStepsDetails.get(i) + "</td>");
				aWriter.write("<td ><font color=\"Green\">"
						+ listPassDetails.get(i) + "</td>");
				if ((listFailDetails.get(i)) == 0) {
					aWriter.write("<td >" + listFailDetails.get(i) + "</td>");
				} else {
					aWriter.write("<td ><font color=\"Red\">"
							+ listFailDetails.get(i) + "</td>");
				}
				aWriter.write("<td >");
				aWriter.write("<a href =\"");
				aWriter.write("file:///" + listReportFileDetails.get(i));
				aWriter.write("\" target=\"_blank\">Detail Result</td>\n");
			}

			/*
			 * listPassDetails.clear(); listFailDetails.clear();
			 * listTotalStepsDetails.clear(); listReportFileDetails.clear();
			 */

			aWriter.write("</table>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</td>");
			aWriter.write("</tr>");
			aWriter.write("</table>");
			aWriter.write("</div>");
			aWriter.write("</div>");
			aWriter.write("</body>");
			aWriter.write("</html>");
			aWriter.flush();
			aWriter.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strSummarizedReportFile;
	}

	public void CreateSummary() throws IOException, BiffException, InterruptedException {

		// String strReportFile = System.getProperty("reportFilePath");
		listFailDetails.add(iFailCount);
		listPassDetails.add(iPassCount);
		listTotalStepsDetails.add(iPassCount + iFailCount);
		// System.out.println(strDetails+"list size is:"+listTotalStepsDetails.size());
		listReportFileDetails.add(strReportFile);
		/*
		 * int iPassCount = iPassCount ; int iFailCount =
		 * Integer.parseInt(listFailDetails.get(0));
		 */
		int iPercentageOfPassFail = (iPassCount * 100 / (iPassCount + iFailCount));
		FileWriter aWriter = new FileWriter(strReportFile, true);
		aWriter.write("</table>");
		aWriter.write("<table class=\"content_table\" cellpadding=\"0\" cellspacing=\"0\">");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td valign=\"top\">");
		aWriter.write("<div class=\"right_content\">");
		aWriter.write("<div id=\"tabs_environment\">");
		aWriter.write("<ul>");
		aWriter.write("<li><a href=\"#tabs-set-2\" class=\"f2\">"
				+ "Report Summary </a></li>");
		aWriter.write("</ul>");
		aWriter.write("<div id=\"tabs-set-1\"  class=\"f2\">");
		aWriter.write("<div style=\"margin-top: 10px;\">");
		aWriter.write("<table id=\"set_table\" width=\"100%\" class=\"f2\" cellpadding=\"\" cellspacing=\"10\" ><tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Data Sheet</b></td>");
		aWriter.write("<td>" + System.getProperty("File") + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>End Test Run</b></td>");
		aWriter.write("<td>" + strStopTime + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Elapsed Test Time</b></td>");
		int seconds = (int) (timeElapsed / 1000) % 60;
		int minutes = (int) ((timeElapsed / (1000 * 60)) % 60);
		aWriter.write("<td>" + minutes + " " + "min" + " " + seconds + " "
				+ "seconds" + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Number of verifications Passed</b></td>");
		aWriter.write("<td> <font color=\"Green\">" + iPassCount + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Number of verifications Failed</b></td>");
		aWriter.write("<td><font color=\"Red\">" + iFailCount + "</td>");
		aWriter.write("</tr>");
		aWriter.write("<tr class=\"list_table_tr\">");
		aWriter.write("<td><b>Percentage of verifications Passed</b></td>");
		aWriter.write("<td>" + iPercentageOfPassFail + "</td>");
		aWriter.write("</tr>");
		aWriter.write("</table>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</td>");
		aWriter.write("</tr>");
		aWriter.write("</table>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</td>");
		aWriter.write("</tr>");
		aWriter.write("</table>");
		aWriter.write("</div>");
		aWriter.write("</div>");
		aWriter.write("</body>");
		aWriter.write("</html>");
		aWriter.flush();
		aWriter.close();
		// Update Status in Input Data File
		int iCurrentExeNumber = getCurrentExecutionRow(strDetails);
		String strInputfileName = System.getProperty("File");
		File inputWorkbook = new File(strInputfileName);
		Workbook w;
		w = Workbook.getWorkbook(inputWorkbook);
		Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
		POIFSFileSystem fs;
		fs = new POIFSFileSystem(new FileInputStream(strInputfileName));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);

		HSSFSheet dataSheet = null;
		dataSheet = workbook.getSheet("ConfigurationSheet");
		HSSFRow dataRow = null;
		Iterator rows = dataSheet.rowIterator();
		int noOfRows = 0;
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();
			noOfRows++;
		}

		HSSFFont hssFont = workbook.createFont();
		HSSFCellStyle style = workbook.createCellStyle();
		String StrStatus = null;
		dataRow = dataSheet.getRow(iCurrentExeNumber);
		// String sNoOfRows = System.getProperty("NoOfRows");//
		// System.setProperty("NoOfRows")
		iNoOfRows = counter; // Integer.parseInt(sNoOfRows);
		String sHyperLink = "";
		if (iNoOfRows == 1) {
			if (iFailCount > 0) {
				StrStatus = "Fail";
				hssFont.setColor(HSSFColor.DARK_RED.index);
				style.setFont(hssFont);

			} else {
				StrStatus = "Pass";
				hssFont.setColor(HSSFColor.DARK_GREEN.index);
				style.setFont(hssFont);

			}
			sHyperLink = "HYPERLINK(\"[" + strReportFile + "]Reporlink"
					+ "\" )";

			dataRow.createCell(5).setCellStyle(style);
			dataRow.createCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
			dataRow.createCell(5).setCellValue(StrStatus);
			dataRow.createCell(6).setCellFormula(sHyperLink);
		} else {
			/*
			 * iCurrentIndex = Integer.parseInt(System
			 * .getProperty("CurrentIndex")); //changing int iCurrentIndex to a
			 * feild
			 */if (iCurrentIndex == iNoOfRows) {
				sHyperLink = "HYPERLINK(\"[" + moduleReportGenerator()
						+ "]Reporlink" + "\" )";

				dataRow.createCell(6).setCellFormula(sHyperLink);
				dataRow.createCell(5).setCellValue("NA");
			}
		}

		FileOutputStream fileOut = new FileOutputStream(strInputfileName);
		workbook.write(fileOut);
		fileOut.close();
		
		try{
			if(StrStatus.equalsIgnoreCase("Pass") && exe.strQCUpdate.equalsIgnoreCase("Yes")){
				System.out.println("QC update Process started");
				Process p = Runtime.getRuntime().exec("wscript D:/CallExcelMacro.vbs");		
			    p.waitFor();			 
			    System.out.println("QC update Process completed");
			}
		}catch(Exception e){}

		iPassCount = 0;
		iFailCount = 0;
		// listScenarioDetails.clear();
		/*
		 * listPassDetails.clear(); listFailDetails.clear();
		 * listTotalStepsDetails.clear(); listReportFileDetails.clear();
		 */
	}
	
	//public void batchReportSummary()
	public void writeStepResult(String strScenarioName,
			String strStepDescription, String strTestData, String strStatus,
			String strRessultDescription, boolean isScreenshot,
			RemoteWebDriver webDriver) {
		FileWriter aWriter = null;
		String strComponent = null;
		// String strReportFile = System.getProperty("reportFilePath");
		try {

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			/*
			 * tempList_scenario_name1.add(strScenarioName);
			 * tempList_teststep_description.add(strStepDescription);
			 * tempList_test_data.add(strTestData);
			 * tempList_result_description.add(strRessultDescription);
			 */

			if (isScreenshot) {
				now();

				try {
					
					if(strBrowser.equalsIgnoreCase("IE") || strBrowser.equalsIgnoreCase("Internet Explorer")){
						Robot robot = new Robot();
						
						BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
						ImageIO.write(screenShot, "png", new File(strScreenshot));
						
						
					} else{
						
						try{
							File srcFile;
	
							srcFile = ((TakesScreenshot) webDriver)
									.getScreenshotAs(OutputType.FILE);
	
							FileUtils.copyFile(srcFile, new File(strScreenshot));
						}
						catch(Exception e){
							Robot robot = new Robot();
							
							BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
							ImageIO.write(screenShot, "png", new File(strScreenshot));
							
						}
					}

					

					// tempList_snapshot.add(strScreenshot);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				// tempList_snapshot.add("No screenshot available");
			}
			// tempList_status.add(strStatus);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			aWriter = new FileWriter(strReportFile, true);
			aWriter.write("<tr class=\"list_table_tr\">");

			aWriter.write("<tr class=\"list_table_tr\"><td >" + strScenarioName
					+ "</td>");
			aWriter.write("<td >" + strStepDescription + "</td>");
			aWriter.write("<td >" + strTestData + "</td>");
			if ((strStatus).equalsIgnoreCase("Pass")) {
				aWriter.write("<td><font color=\"Green\">" + strStatus
						+ "</td>\n");
			} else {
				aWriter.write("<td><font color=\"Red\">" + strStatus
						+ "</td>\n");
			}
			aWriter.write("<td >" + strRessultDescription + "</td>");
			aWriter.write("<td >");

			if (strScreenshot.contentEquals("No screenshot available")) {
				aWriter.write("No Screenshot available");

			} else {
				
				String screenshothyperlinkpath = strDetails + "/screenshot/" + strDetails + "_" + Randomtime + ".png";
				aWriter.write("<a href =\"../" + screenshothyperlinkpath );
				aWriter.write("\" target=\"_blank\">Screenshot</td>\n");
				
				/*aWriter.write("<a href =\"");
				aWriter.write("file:///" + strScreenshot);
				aWriter.write("\" target=\"_blank\">Screenshot</td>\n");*/

			}

			aWriter.flush();
			aWriter.close();

			if (strStatus.equalsIgnoreCase("Pass")) {
				iPassCount++;
			}

			if (strStatus.equalsIgnoreCase("Fail")) {
				iFailCount++;
			}

			/*
			 * tempList_scenario_name1.clear();
			 * tempList_teststep_description.clear();
			 * tempList_test_data.clear(); tempList_snapshot.clear();
			 * tempList_status.clear(); tempList_result_description.clear();
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void writeStepResult(String strScenarioName,
			String strStepDescription, String strTestData, String strStatus,
			String strRessultDescription, boolean isScreenshot,
			Selenium selenium) {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

			// tempList_scenario_name1.add(strScenarioName);
			// tempList_teststep_description.add(strStepDescription);
			// tempList_test_data.add(strTestData);
			// tempList_result_description.add(strRessultDescription);

			if (isScreenshot) {
				now();
				Random rand = new Random();
				int num = rand.nextInt(1000);

				try {
					Robot robot = new Robot();
					BufferedImage screenShot = robot
							.createScreenCapture(new Rectangle(Toolkit
									.getDefaultToolkit().getScreenSize()));
					ImageIO.write(screenShot, "png", new File(strScreenshot));
					/*
					 * selenium.captureScreenshot(strScreenshot);
					 * tempList_snapshot.add(strScreenshot);
					 */

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				// tempList_snapshot.add("No screenshot available");
			}
			// tempList_status.add(strStatus);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public int getCurrentExecutionRow(String sSheetName) throws BiffException,
			IOException {

		String strInputfileName = System.getProperty("File");
		File inputWorkbook = new File(strInputfileName);
		Workbook w;
		w = Workbook.getWorkbook(inputWorkbook);
		Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
		int sCurrentRowNumber = 0;
		for (int i = 1; i < sMasterSheet.getRows(); i++) {
			// Get execute Condition.
			String sCurrentSheetName = sMasterSheet.getCell(1, i).getContents();
			String sBrowserName = sMasterSheet.getCell(3, i).getContents();
			if (sCurrentSheetName.equals(sSheetName)
					&& sBrowserName.equalsIgnoreCase(strBrowser)) {
				sCurrentRowNumber = i;
				break;

			}
		}

		return sCurrentRowNumber;

	}
public void clearQuoteNumber() throws BiffException, IOException{
		
		int iCurrentExeNumber = getCurrentExecutionRow(strDetails);
		String strInputfileName = System.getProperty("File");
		File inputWorkbook = new File(strInputfileName);
		Workbook w;
		w = Workbook.getWorkbook(inputWorkbook);
		Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
		POIFSFileSystem fs;
		fs = new POIFSFileSystem(new FileInputStream(strInputfileName));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);

		HSSFSheet dataSheet = null;
		dataSheet = workbook.getSheet(strDetails);
		HSSFRow dataRow = null;
		HSSFRow dataRowHeader=null;
		Iterator rows = dataSheet.rowIterator();
		int noOfRows = 0;
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();
			noOfRows++;
		}

		HSSFFont hssFont = workbook.createFont();
		HSSFCellStyle style = workbook.createCellStyle();
		String StrStatus = "";
		//dataRow = dataSheet.getRow(currentRowToExe);
		dataRow = dataSheet.getRow(FFQ_ExcelReadingScript.lRowIndex.get(FFQ_ExcelReadingScript.iReadCounter-1));
		
		dataRowHeader = dataSheet.getRow(0);
		int vQuoteNoRow, vQuoteAmtRow;
		vQuoteNoRow=0;
		vQuoteAmtRow=0;
		boolean QuoteNoFlag=false,QuoteAmtFlag = false;
		
		for(int i =0 ;i<18;i++){
			String strColName = dataRowHeader.getCell(i).toString();
			if(strColName.equalsIgnoreCase("QuoteNumber")){
				vQuoteNoRow = i;
				QuoteNoFlag=true;
			}
			if(strColName.equalsIgnoreCase("PremiumAmount")){
				vQuoteAmtRow = i;
				QuoteAmtFlag=true;
			}
			if(QuoteNoFlag && QuoteAmtFlag)
				break;
		}
		
		
		
		iNoOfRows = counter; // Integer.parseInt(sNoOfRows);
				
			dataRow.createCell(vQuoteNoRow).setCellType(HSSFCell.CELL_TYPE_STRING);
			dataRow.createCell(vQuoteNoRow).setCellValue("");
			dataRow.createCell(vQuoteAmtRow).setCellType(HSSFCell.CELL_TYPE_STRING);
			dataRow.createCell(vQuoteAmtRow).setCellValue("");
	

		FileOutputStream fileOut = new FileOutputStream(strInputfileName);
		workbook.write(fileOut);
		fileOut.close();

		
	}
public void WriteQuoteNumber(String strQuoteNo, String strQuoteAmt) throws BiffException, IOException{
		
		//int iCurrentExeNumber = getCurrentExecutionRow(strDetails);
		String strInputfileName = System.getProperty("File");
		File inputWorkbook = new File(strInputfileName);
		Workbook w;
		w = Workbook.getWorkbook(inputWorkbook);
		Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
		POIFSFileSystem fs;
		fs = new POIFSFileSystem(new FileInputStream(strInputfileName));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);

		HSSFSheet dataSheet = null;
		
		dataSheet = workbook.getSheet(strDetails);
		HSSFRow dataRow = null;
		HSSFRow dataRowHeader=null;
		
		Iterator rows = dataSheet.rowIterator();
		int noOfRows = 0;
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();
			noOfRows++;
		}

		HSSFFont hssFont = workbook.createFont();
		HSSFCellStyle style = workbook.createCellStyle();
		String StrStatus = "";
		dataRow = dataSheet.getRow(FFQ_ExcelReadingScript.lRowIndex.get(FFQ_ExcelReadingScript.iReadCounter-1));
		
		dataRowHeader = dataSheet.getRow(0);
		int vQuoteNoRow, vQuoteAmtRow;
		vQuoteNoRow=0;
		vQuoteAmtRow=0;
		
		boolean QuoteNoFlag=false,QuoteAmtFlag = false;
		
		for(int i =0 ;i<18;i++){
			String strColName = dataRowHeader.getCell(i).toString();
			if(strColName.equalsIgnoreCase("QuoteNumber")){
				vQuoteNoRow = i;
				QuoteNoFlag=true;
			}
			if(strColName.equalsIgnoreCase("PremiumAmount")){
				vQuoteAmtRow = i;
				QuoteAmtFlag=true;
			}
			if(QuoteNoFlag && QuoteAmtFlag)
				break;
		}
		
		
		
		iNoOfRows = counter; // Integer.parseInt(sNoOfRows);
				
			dataRow.createCell(vQuoteNoRow).setCellType(HSSFCell.CELL_TYPE_STRING);
			dataRow.createCell(vQuoteNoRow).setCellValue(strQuoteNo);
			
			dataRow.createCell(vQuoteAmtRow).setCellType(HSSFCell.CELL_TYPE_STRING);
			dataRow.createCell(vQuoteAmtRow).setCellValue(strQuoteAmt);
			

		FileOutputStream fileOut = new FileOutputStream(strInputfileName);
		workbook.write(fileOut);
		fileOut.close();

		
	}	
}
