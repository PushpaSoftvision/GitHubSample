package Stepdefination;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RestService {
	String eproj, esuite, sproj, ssuite;
	String[] etestcase = new String[200];
	String[] stestcase = new String[200];
	long startTime, endTime, totalTime;
	Integer i, j, rowcount, sheetcnt, processcount = 0;
	static SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	static String dateFormat = f.format(new Date());
	static String timeForDD = dateFormat.substring(9, dateFormat.length()).replace(":", "");

	@Given("^Open Chrome  and start application$")
	public void open_Chrome_and_start_application() throws Throwable {
		String path1 = "C:\\Users\\pushpa.s\\workspace\\com.push\\ExcelFiles\\Book1.xlsx";
		Workbook wb = WorkbookFactory.create(new FileInputStream(path1));
		rowcount = wb.getSheet("Sheet1").getLastRowNum();
		System.out.println("number of rows are   " + rowcount);
		for (int k = 1; k <= rowcount; k++) {
			etestcase[k] = wb.getSheet("Sheet1").getRow(k).getCell(2).toString();
			stestcase[k] = wb.getSheet("Sheet2").getRow(k).getCell(2).toString();
			System.out.println(etestcase[k]);
			System.out.println(stestcase[k]);
		}
		eproj = wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		esuite = wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		System.out.println(eproj);
		System.out.println(esuite);
	}

	@When("^I enter valid username and valid password$")
	public void i_enter_valid_username_and_valid_password() throws Throwable {
		File f = new File("C:\\Users\\vinaya.varma\\Desktop\\results\\vtest-response-results.htm");
		if (f.exists()) {
			f.delete();
		}
		j = 0;
		for (int v = 1; v <= rowcount; v++) {
			WsdlProject project1 = new WsdlProject(eproj);
			WsdlTestSuite testSuite1 = project1.getTestSuiteByName(esuite);
			WsdlTestCase testCase1 = testSuite1.getTestCaseByName(etestcase[v]);
		}

	}

	@Then("^User should be able to login successfully$")
	public void user_should_be_able_to_login_successfully() throws Throwable {

	}

}
