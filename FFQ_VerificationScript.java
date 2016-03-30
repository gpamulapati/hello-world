package FFQ_Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * FFQ_VerificationScript --- Class for verification of test steps using Webdriver
 * 
 * @author csc
 */

public class FFQ_VerificationScript {
	FFQ_ReportingScript fFQ_ReportingScript;
	FFQ_UtilitiesScript utils;
	Map<String, String> DataMap;

	public FFQ_VerificationScript(FFQ_ReportingScript fFQ_ReportingScript) {
		this.fFQ_ReportingScript = fFQ_ReportingScript;

		utils = new FFQ_UtilitiesScript(fFQ_ReportingScript);
		DataMap = new HashMap();

	}

	// Verifying title of the page
	public void verifyPageTitle(RemoteWebDriver webDriver,
			String strExpectedTitle) {
		boolean isExpecetdTitle = false;
		String strActualTtle = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");

		try {
			strActualTtle = webDriver.getTitle();
			if (strActualTtle.equals(strExpectedTitle)) {
				isExpecetdTitle = true;
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());

		}
		if (isExpecetdTitle) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify title of page", strExpectedTitle, "Pass",
					"Page title matches with expected", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Enter Value in text field", "Expected: "
							+ strExpectedTitle, "Pass",
					"Page title does not match with expected (Actual: "
							+ strActualTtle + ")", true, webDriver);
		}
	}

	// Verifying whether element is present on the page
	public void verifyElementPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String scenario) {
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		boolean exists = false;
		try {
			for (int interval = 0; interval < 2; interval++) {
				if (strElementProperty.equalsIgnoreCase("name")) {
					if (webDriver.findElementsByName(strElement).size() != 0
							&& webDriver.findElementByName(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					if (webDriver.findElementsById(strElement).size() != 0
							&& webDriver.findElementById(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					if (webDriver.findElementsByXPath(strElement).size() != 0
							//&& webDriver.findElementByXPath(strElement).isDisplayed()
							) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					if (webDriver.findElementsByCssSelector(strElement).size() != 0
							&& webDriver.findElementByCssSelector(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			fFQ_ReportingScript.writeStepResult(scenario,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Pass",
					"Element is present on the page", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(scenario,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Fail",
					"Element is not present on the page", true, webDriver);
		}
	}
	public void verifyElementNotPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String scenario) {
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		boolean exists = false;
		try {
			for (int interval = 0; interval < 5; interval++) {
				if (strElementProperty.equalsIgnoreCase("name")) {
					if (webDriver.findElementsByName(strElement).size() == 0
							&& !webDriver.findElementByName(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					if (webDriver.findElementsById(strElement).size() == 0
							&& !webDriver.findElementById(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					if (webDriver.findElementsByXPath(strElement).size() == 0) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					if (webDriver.findElementsByCssSelector(strElement).size() == 0
							&& !webDriver.findElementByCssSelector(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			fFQ_ReportingScript.writeStepResult(scenario,
					"Verify element is not present on the page",
					strElement.toUpperCase(), "Pass",
					"Element is not present on the page", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(scenario,
					"Verify element is not present on the page",
					strElement.toUpperCase(), "Fail",
					"Element is present on the page", true, webDriver);
		}
	}
	// Verifying whether link is present on the page
	public void verifyLinkPresent(RemoteWebDriver webDriver, String strElement) {
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		boolean exists = false;
		try {
			for (int interval = 0; interval < 30; interval++) {
				if (webDriver.findElementsByLinkText(strElement).size() != 0
						&& webDriver.findElementByLinkText(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsByName(strElement).size() != 0
						&& webDriver.findElementByName(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsById(strElement).size() != 0
						&& webDriver.findElementById(strElement).isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsByXPath(strElement).size() != 0
						&& webDriver.findElementByXPath(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Fail",
					"Link is not present on the page", true, webDriver);
		}
		if (exists) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Pass",
					"Link is present on the page", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Fail",
					"Link is not present on the page", true, webDriver);
		}
	}

	// Verifying whether element text is present on the page
	public void verifyElementTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText,String scenario) {

		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementById(strElement)
							.getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByName(strElement)
							.getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByXPath(strElement)
							.getText();
				}

			}
			if (strElementProperty.equalsIgnoreCase("css")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByCssSelector(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByCssSelector(
							strElement).getText();
				}

			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			fFQ_ReportingScript.writeStepResult(scenario,
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text  is present", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(scenario,
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	public void verifyDefaultTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText,String scenario) {
		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("defaultTxt");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("defaultTxt");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("defaultTxt");
			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify default text is present in the element",
					"Expected: " + strExpectedText, "Pass",
					"Expected text is present", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify default text is present in the element",
					"Expected: " + strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	// Verifying whether element attribute value is present on the page
	public void verifyTextValue(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedText,String scenario) {

		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("value");
			}
			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("value");
			}
			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("value");
				if(strActualText == null){
					strActualText = webDriver.findElementByXPath(strElement).getText();							
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value attribute of element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected value is present", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value attribute of element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected value is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	// Verifying whether element text is absent on the page
	public void verifyElementTextAbsent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText,String scenario) {
		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementById(strElement).getText();
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByName(strElement)
						.getText();
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualText = webDriver.findElementByXPath(strElement)
						.getText();
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (!strActualText.equals(strExpectedText)) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is not present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text is absent", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is not present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text is present", true, webDriver);
		}
	}

	// Verifying number of elemnts on the page
	public void verifyListValue(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedValue,String scenario) {

		WebElement listbox = null;
		boolean exists = false;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.getText().equals(strExpectedValue)) {
					exists = true;
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value is present in the listbox", "Expected: "
							+ strExpectedValue, "Pass",
					"Expected value is prsenet in the listbox", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value is present in the listbox", "Expected: "
							+ strExpectedValue, "Fail",
					"Expected value is not prsenet in the listbox", true,
					webDriver);
		}
	}
	public void verifyListValueNotPresent(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedValue,String scenario) {

		WebElement listbox = null;
		boolean exists = false;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.getText().equals(strExpectedValue)) {
					exists = true;
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value is not present in the listbox", "Expected: "
							+ strExpectedValue, "Fail",
					"Expected value is prsenet in the listbox", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value is not present in the listbox", "Expected: "
							+ strExpectedValue, "Pass",
					"Expected value is not prsenet in the listbox", true,
					webDriver);
		}
	}
	public void verifyListValues(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedValues,String scenario) {
		WebElement listbox = null;
		String[] arrListValues = strExpectedValues.split(";");
		int counter = 0;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (int i = 0; i < arrListValues.length; i++) {
				for (WebElement option : options) {
					if (option.getText().equals(arrListValues[i]))
						counter++;
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (counter == arrListValues.length) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify values are present in the listbox", "Expected: "
							+ strExpectedValues, "Pass",
					"Expected values are prsenet in the listbox", true,
					webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify values are present in the listbox", "Expected: "
							+ strExpectedValues, "Fail",
					"Expected values are not prsenet in the listbox", true,
					webDriver);
		}
	}

	public void verifySelectedListValue(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedSelectedValue,String scenario) {
		WebElement listbox = null;
		boolean isSelected = false;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.isSelected())
					isSelected = true;
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (isSelected) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify selected value in the listbox", "Expected: "
							+ strExpectedSelectedValue, "Pass",
					"Expected value is selected in the listbox", true,
					webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify selected value in the listbox", "Expected: "
							+ strExpectedSelectedValue, "Fail",
					"Expected value is not selected in the listbox", true,
					webDriver);
		}
	}

	public void verifyCheckboxStatus(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedStatus,String scenario) {

		String strActualStatus = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualStatus = webDriver.findElementById(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualStatus = webDriver.findElementByName(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualStatus = webDriver.findElementByXPath(strElement)
						.getAttribute("checked");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualStatus.equalsIgnoreCase("true")
				&& strExpectedStatus.equalsIgnoreCase("checked")) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is checked", "Expected: "
							+ strExpectedStatus, "Pass", "Checkbox is checked",
					true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is checked", "Expected: "
							+ strExpectedStatus, "Fail",
					"Checkbox is not checked", true, webDriver);
		}

		if (strActualStatus.equalsIgnoreCase("false")
				&& strExpectedStatus.equalsIgnoreCase("unchecked")) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is not checked", "Expected: "
							+ strExpectedStatus, "Pass",
					"Checkbox is not checked", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is not checked", "Expected: "
							+ strExpectedStatus, "Fail", "Checkbox is checked",
					true, webDriver);
		}

	}

	public void verifyRadioButtonStatus(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedStatus,String scenario) {
		String strActualStatus = null;
		boolean isSelected = false;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualStatus = webDriver.findElementById(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualStatus = webDriver.findElementByName(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				verifyElementPresent(webDriver, strElement, strElementProperty,scenario);
				strActualStatus = webDriver.findElementByXPath(strElement)
						.getAttribute("checked");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualStatus.equalsIgnoreCase("true")
				&& strExpectedStatus.equalsIgnoreCase("selected")) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is selected", "Expected: "
							+ strExpectedStatus, "Pass",
					"Radio button is selected", true, webDriver);
		} else if (strActualStatus.equalsIgnoreCase("true")
				&& strExpectedStatus.equalsIgnoreCase("deselected")) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is checked", "Expected: "
							+ strExpectedStatus, "Fail",
					"Radio button is not selected", true, webDriver);
		}

		else if (strActualStatus.equalsIgnoreCase("false")
				&& strExpectedStatus.equalsIgnoreCase("deselected")) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is not selected", "Expected: "
							+ strExpectedStatus, "Pass",
					"Radio button is not selected", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is not selected", "Expected: "
							+ strExpectedStatus, "Fail",
					"Radio button is selected", true, webDriver);
		}
	}

	// Verifying whether second element text is present on the page as the first
	// element is hidden
	public void verify2ndElementTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {

		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		List<WebElement> l1;

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}

			}
			if (strElementProperty.equalsIgnoreCase("css")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}

			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text  is present", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}
	public void verifySelectBoxValues(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String selectBoxValues,String scenario) {
		
		WebElement element= null;
		String[] expectedValues= selectBoxValues.split(";");
		int count=0;
		boolean exists = false;
		try {
				if (strElementProperty.equalsIgnoreCase("name")) {
					element=webDriver.findElement(By.xpath(strElement));
						
					
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					element=webDriver.findElement(By.xpath(strElement));
						
					
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					element=webDriver.findElement(By.xpath(strElement));
				
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					element=webDriver.findElement(By.xpath(strElement));
						
				} 
			List<WebElement> listbox=element.findElements(By.tagName("option"));
			for (int j = 0; j < expectedValues.length; j++) {
				 for (WebElement webElement : listbox) {
					 String actValues=webElement.getText();
						System.out.println(actValues);
						if(actValues.equalsIgnoreCase(expectedValues[j])){
							count++;
							break;
						}
						
			}
			
				}
			
			if(count==expectedValues.length){
				exists=true;
			}else{
				exists=false;
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
	
		if (exists) {
			
			fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
					"Verify select box values on the page",
					selectBoxValues, "Pass",
					" select box values is equal to Expected values: "+selectBoxValues+" on the page", true, webDriver);
			
		} else {
			
			fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
					"Verify select box values on the page",
					selectBoxValues, "Fail",
					" select box values is not equal to Expected values: "+selectBoxValues+" on the page", true, webDriver);
		}
			
	}
	public void verifyElementPreFilled(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String scenario) {
		boolean exists = false;
		WebElement element=null;
		String actText=null;
		try {
			    if(strElementProperty.equalsIgnoreCase("Xpath")){
					element=webDriver.findElement(By.xpath(strElement));
				} else {
					exists = false;
				}
			    
			    actText=element.getText();
			    if(actText.length()==0){
			    	actText=element.getAttribute("value");
			    }
			    if(actText.length()!=0){
			    	exists = true;
			    }else{
			    	exists = false;
			    }
			    
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			try {				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
					"Verify element is prefilled on the page",
					actText, "Pass",
					"Element is prefilled on the page", true, webDriver);
		
		} else {
			
			fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
					"Verify element is prefilled on the page",
					actText, "Fail",
					"Element is not prefilled on the page", true, webDriver);
			
		}
	}
	public void verifyElementNotPreFilled(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String scenario) {
		boolean exists = false;
		WebElement element=null;
		String actText=null;
		try {
			    if(strElementProperty.equalsIgnoreCase("Xpath")){
					element=webDriver.findElement(By.xpath(strElement));
				} else {
					exists = false;
				}
			    
			    actText=element.getText();
			    if(actText.length()==0){
			    	actText=element.getAttribute("value");
			    }
			    if(actText.length()!=0){
			    	exists = true;
			    }else{
			    	exists = false;
			    }
			    
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			try {				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
					"Verify element is not prefilled on the page",
					actText, "Fail",
					"Element is prefilled on the page", true, webDriver);
		
		} else {
			
			fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
					"Verify element is not prefilled on the page",
					actText, "Pass",
					"Element is not prefilled on the page", true, webDriver);
			
		}
	}
	public void verifyElementType(RemoteWebDriver webDriver,String strElement,String strElementProperty,
			
			String strExpectedType, String strScenario) {
		boolean isExpecetdType = false;
		String strActualType = null;
		String strDetails = utils.getDataFileInfo();
		strDetails.split("_");
		String scenario ="Verify Element is displayed  ";
		try {
			
			if(strElementProperty.equalsIgnoreCase("Xpath")){
				verifyElementPresent(webDriver, strElement, strElementProperty, scenario);
			strActualType = webDriver.findElement(By.xpath(strElement)).getAttribute("type");
				if(strActualType==null){
					strActualType = webDriver.findElement(By.xpath(strElement)).getTagName();	
							}
			}
			
			if (strActualType.equalsIgnoreCase(strExpectedType)) {
				isExpecetdType = true;
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());

		}
		if (isExpecetdType) {
			fFQ_ReportingScript.writeStepResult(strScenario.toUpperCase(),
					"Verify Element type", strExpectedType, "Pass",
					"Element type matches with expected", true, webDriver);
		} else {
			fFQ_ReportingScript.writeStepResult(strScenario.toUpperCase(),
					"Verify Element type", "Expected: "
							+ strExpectedType, "Fail",
					"Element type does not match with expected (Actual: "
							+ strActualType + ")", true, webDriver);
		}
	}
	public void verifyElementIsEnable(RemoteWebDriver webDriver,
		String strElement, String strElementProperty,String scenario) {
	String strDetails = utils.getDataFileInfo();
	strDetails.split("_");
	boolean exists = false;
	try {
		for (int interval = 0; interval < 5; interval++) {
			if (strElementProperty.equalsIgnoreCase("name")) {
				if (webDriver.findElementsByName(strElement).size() != 0
						&& webDriver.findElementByName(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else if (strElementProperty.equalsIgnoreCase("id")) {
				if (webDriver.findElementsById(strElement).size() != 0
						&& webDriver.findElementById(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else if (strElementProperty.equalsIgnoreCase("xpath")) {
				if (webDriver.findElementsByXPath(strElement).size() != 0
						&& webDriver.findElementByXPath(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else if (strElementProperty.equalsIgnoreCase("css")) {
				if (webDriver.findElementsByCssSelector(strElement).size() != 0
						&& webDriver.findElementByCssSelector(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else {
				exists = false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} catch (Exception e1) {
		System.out.println("Exception occurred -- " + e1.getMessage());
	}
	if (exists) {		
		fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
				"Verify element is enable on the page",
				strElement.toUpperCase(), "Pass",
				"Element is enable on the page", true, webDriver);		
	} else {		
		fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
				"Verify element is enable on the page",
				strElement.toUpperCase(), "Fail",
				"Element is not enable on the page", true, webDriver);		
	}
}
	public void verifyElementEnableDisable(RemoteWebDriver webDriver,
		String strElement, String strElementProperty,String strEnableDisable,String scenario) {
	String strDetails = utils.getDataFileInfo();
	strDetails.split("_");
	boolean exists = false;
	try {
		for (int interval = 0; interval < 2; interval++) {
			if (strElementProperty.equalsIgnoreCase("name")) {
				if (webDriver.findElementsByName(strElement).size() != 0
						&& webDriver.findElementByName(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else if (strElementProperty.equalsIgnoreCase("id")) {
				if (webDriver.findElementsById(strElement).size() != 0
						&& webDriver.findElementById(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else if (strElementProperty.equalsIgnoreCase("xpath")) {
				if (webDriver.findElementsByXPath(strElement).size() != 0
						&& webDriver.findElementByXPath(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else if (strElementProperty.equalsIgnoreCase("css")) {
				if (webDriver.findElementsByCssSelector(strElement).size() != 0
						&& webDriver.findElementByCssSelector(strElement)
								.isEnabled()) {
					exists = true;
					break;
				}
			} else {
				exists = false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} catch (Exception e1) {
		System.out.println("Exception occurred -- " + e1.getMessage());
	}
	if (exists && strEnableDisable.equalsIgnoreCase("Enable")) {		
		fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
				"Verify element is enable on the page",
				strElement.toUpperCase(), "Pass",
				"Element is enable on the page", true, webDriver);		
	} else if (exists && strEnableDisable.equalsIgnoreCase("Disable")) {		
		fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
				"Verify element is disable on the page",
				strElement.toUpperCase(), "Fail",
				"Element is disable on the page", true, webDriver);		
	} else if (!exists && strEnableDisable.equalsIgnoreCase("Enable")) {
		fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
				"Verify element is enable on the page",
				strElement.toUpperCase(), "Fail",
				"Element is not enable on the page", true, webDriver);
	} else if (!exists && strEnableDisable.equalsIgnoreCase("Disable")) {
		fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
				"Verify element is disable on the page",
				strElement.toUpperCase(), "Pass",
				"Element is disable on the page", true, webDriver);
	} else{		
		fFQ_ReportingScript.writeStepResult(scenario.toUpperCase(),
				"Verify element is enable or disable on the page",
				strElement.toUpperCase(), "Fail",
				"Something went wrong, please check parameters", true, webDriver);		
	}
}
	public void verifyElementAttributeValue(RemoteWebDriver webDriver,String strElement,String strElementProperty, String strAttribute,
		String strAttributeVal, String strScenario) {
	String actAttributeVal=null;
	try {
		
		if(strElementProperty.equalsIgnoreCase("Xpath")){
			actAttributeVal = webDriver.findElement(By.xpath(strElement)).getAttribute(strAttribute).toString();
		}
		
		if (strAttributeVal.equalsIgnoreCase(actAttributeVal)) {
			fFQ_ReportingScript.writeStepResult(strScenario.toUpperCase(),
					"Verify Element attribute value", actAttributeVal, "Pass",
					"Element attribute value matches with expected", true, webDriver);
		}
		else{
			fFQ_ReportingScript.writeStepResult(strScenario.toUpperCase(),
					"Verify Element attribute value", "Expected: "
							+ strAttributeVal, "Fail",
					"Element attribute value does not match with expected (Actual: "
							+ actAttributeVal + ")", true, webDriver);
		}
			
	} catch (Exception e1) {
		System.out.println("Exception occurred -- " + e1.getMessage());

	}
	
}

}
