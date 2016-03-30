package FFQ_Utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FFQ_ExcelReadingScript {

	public String fileName;
	public String sheetName;
	public String strSheetsToExecute = "";
	public String strTestCaseNameToExecute = "";
	public String sCurrentEnvironmentCondition ="";
	//public String sEnvName = "";

	public int iNOfRows = 0;
	public static int iReadCounter = 0;
	
	static List<Integer> lRowIndex = new ArrayList<Integer>();
	

	// public static int counter;
	private FFQ_ReportingScript fFQ_ReportingScript;

	public FFQ_ExcelReadingScript(FFQ_ReportingScript fFQ_ReportingScript) {
		this.fFQ_ReportingScript = fFQ_ReportingScript;
	}

	public FFQ_ExcelReadingScript() {

	}

	public int getiNOfRows() throws Exception {
		File inputWorkbook = new File(fileName);
		fFQ_ReportingScript.counter = 0; // changing int counter to feild
		Workbook w;

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(sheetName);
			iNOfRows = sheet.getRows();

			for (int k = 1; k < iNOfRows; k++) {
				if ((sheet.getCell(0, k).getContents().trim()).equals("Yes")) {

					fFQ_ReportingScript.counter = fFQ_ReportingScript.counter + 1;
					lRowIndex.add(k);
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
		/*
		 * System.clearProperty("NoOfRows"); System.setProperty("NoOfRows",
		 * String.valueOf(counter));
		 */
		return fFQ_ReportingScript.counter;
	}

	public void setiNOfRows(int iNOfRows) {
		this.iNOfRows = iNOfRows;
	}

	public String getStrSheetsToExecute() {
		return strSheetsToExecute;
	}

	public void setStrSheetsToExecute(String strSheetsToExecute) {
		this.strSheetsToExecute = strSheetsToExecute;
	}

	Map<String, String> DataMap = new HashMap();


	public Map<String, String> getDataMap() {
		return DataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		DataMap = dataMap;
	}

	public void setInputFile(String inputFile) {
		this.fileName = inputFile;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public void readByIndex(int i) throws IOException {
		File inputWorkbook = new File(fileName);
		Workbook w;
		// int noOfRows = Integer.parseInt(System.getProperty("NoOfRows"));
		int noOfRows = fFQ_ReportingScript.counter;

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(sheetName);
			System.out.println("Sheet Name:- " + sheet.getName());

			for (int j = 0; j < sheet.getColumns(); j++) {
				/*
				 * if ((sheet.getCell(0,
				 * l.get(counter)).getContents().trim()).equals("Yes")) {
				 * System.
				 * out.println("Cell :"+DataMap.containsKey((sheet.getCell(j,
				 * 0).getContents().trim())));
				 */
				if (DataMap.containsKey((sheet.getCell(j, 0).getContents()
						.trim()))) {
					DataMap.remove((sheet.getCell(j, 0).getContents().trim()));
				}
				DataMap.put(sheet.getCell(j, 0).getContents().trim(), sheet
						.getCell(j, lRowIndex.get(iReadCounter)).getContents()
						.trim());
			}
			iReadCounter = iReadCounter + 1;

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public void read() throws IOException {
		File inputWorkbook = new File(fileName);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(sheetName);
			System.out.println("Sheet Name:- " + sheet.getName());
			for (int j = 0; j < sheet.getColumns(); j++) {
				DataMap.put(sheet.getCell(j, 0).getContents().trim(), sheet
						.getCell(j, 1).getContents().trim());
			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public String ReadMasterSheet() throws IOException {
		File inputWorkbook = new File(fileName);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
			for (int i = 1; i < sMasterSheet.getRows(); i++) {
				// Get execute Condition.
				String sCurrentExecutionCondition = sMasterSheet.getCell(4, i)
						.getContents();
				if (sCurrentExecutionCondition.equals("Yes")) {
					strSheetsToExecute = strSheetsToExecute + ","
							+ sMasterSheet.getCell(2, i).getContents();

				}
			}
		} catch (BiffException e) {
			e.printStackTrace();

		}
		return strSheetsToExecute;

	}

	
	
	public String ReadMasterSheetTestCaseName() throws IOException {
		File inputWorkbook = new File(fileName);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
			for (int i = 1; i < sMasterSheet.getRows(); i++) {
				// Get execute Condition.
				String sCurrentExecutionCondition = sMasterSheet.getCell(4, i)
						.getContents();
				if (sCurrentExecutionCondition.equals("Yes")) {
					strTestCaseNameToExecute = strTestCaseNameToExecute + ","
							+ sMasterSheet.getCell(1, i).getContents();

				}
			}
		} catch (BiffException e) {
			e.printStackTrace();

		}
		return strTestCaseNameToExecute;

	}

	/*
	 * to read browser name from excel sheet
	 * 
	 * @CSC
	 */

	public String ReadBroserName() throws IOException {
		File inputWorkbook = new File(fileName);
		Workbook w;
		String browserName = "";
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
			for (int i = 1; i < sMasterSheet.getRows(); i++) {
				// Get execute Condition.
				String sCurrentExecutionCondition = sMasterSheet.getCell(4, i)
						.getContents();
				if (sCurrentExecutionCondition.equals("Yes")) {
					browserName = browserName + ","
							+ sMasterSheet.getCell(3, i).getContents();

				}
			}
		} catch (BiffException e) {
			e.printStackTrace();

		}
		return browserName;

	}
	
	public String ReadEnvName() throws IOException {
		File inputWorkbook = new File(fileName);
		Workbook w;
		String sEnvName = "";

		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
			for (int i = 1; i < sMasterSheet.getRows(); i++) {
				String sCurrentExecutionCondition = sMasterSheet.getCell(4, i)
						.getContents();
				if (sCurrentExecutionCondition.equals("Yes")) {
					sEnvName =   sEnvName +"," 
							+ sMasterSheet.getCell(17, i).getContents();
					

				}
			}
		} catch (BiffException e) {
			e.printStackTrace();

		}
		return sEnvName;

	}
	public String ReadQCUpdateFlag() throws IOException {
		File inputWorkbook = new File(fileName);
		Workbook w;
		int vcolNum = -1;
		String QCUpdate = "";
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
			
			for (int i = 1; i < sMasterSheet.getColumns(); i++) {
				// Get execute Condition.
				try{
					String sColHeader = sMasterSheet.getCell(i,0)
							.getContents();
					if (sColHeader.equals("QC_Update")) {
						vcolNum = i;
						break;
					}
				}catch(Exception e){
					vcolNum = -1;
					break;
				}
			}
			if(vcolNum == -1){
				for (int i = 1; i < sMasterSheet.getRows(); i++) {					
					QCUpdate = QCUpdate + "," + "No";
				}				
			}
			else{
				for (int i = 1; i < sMasterSheet.getRows(); i++) {
					// Get execute Condition.
					String sCurrentExecutionCondition = sMasterSheet.getCell(4, i)
							.getContents();
					if (sCurrentExecutionCondition.equals("Yes")) {
						QCUpdate = QCUpdate + ","
								+ sMasterSheet.getCell(vcolNum, i).getContents();
	
					}
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();

		}
		return QCUpdate;

	}
	public int ReadObjectiveCount(String sTestcaseName) {
		File inputWorkbook = new File(fileName);
		Workbook w;
		int objCount = 0;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sMasterSheet = w.getSheet("ConfigurationSheet");
			for (int i = 1; i < sMasterSheet.getRows(); i++) {
				// Get execute Condition.
				String sCurrentExecutionCondition = sMasterSheet.getCell(1, i)
						.getContents();
				if (sCurrentExecutionCondition.equals(sTestcaseName)) {
					objCount =  Integer.parseInt(sMasterSheet.getCell(8, i).getContents());

				}
			}
		} catch (BiffException | IOException e) {
			System.out.println("Objective count is not mention in input sheet");
			e.printStackTrace();
			return 0;
		}catch (Exception e) {
			System.out.println("Objective count is not mention in input sheet");
			e.printStackTrace();
			return 0;
		}
		return objCount;

	}

	public void getFile() {

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.showOpenDialog(null);
		String path = chooser.getSelectedFile().getAbsolutePath();
		System.out.println(path);
	}

	}
