package net.sweet.test.stastic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sweet.test.base.ApplicationContextTest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gtm.csims.business.dataapp.statistics.StatisticsService;

/**
 * 
 * @author Sweet
 * 
 */
public class QuestionByTestCase extends ApplicationContextTest {

	private StatisticsService questionStsicSvce;

	public void setQuestionStsicSvce(StatisticsService questionStsicSvce) {
		this.questionStsicSvce = questionStsicSvce;
	}

	public void testGenerateHTML() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("qid", "82cc50c68c5c4bfab519d3d84f9dfe30");
		Map<String, String> keyValue = new HashMap<String, String>();
		keyValue.put("TITLE", "2014");

		Map<String, Object> resultData = questionStsicSvce.doStatistics(params);
		File file = new File(TEST_TEMPOTORY_PATH + "test_report14.html");
		if (file.exists()) {
			file.delete();
		}
		try {
			// file = new File("F:/MyWorkSapces/testtemp/test_report1.html");
			BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
			out.write("<html><head></head><body>");
			out.write(questionStsicSvce.getHTMLString("14", resultData, keyValue));
			out.write("</body></html>");
			// out.newLine();
			out.close();
			out = null;
			file = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void testGenerateExcel() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("qid", "82cc50c68c5c4bfab519d3d84f9dfe30");
		Map<String, String> keyValue = new HashMap<String, String>();
		keyValue.put("TITLE", "2014");

		Map<String, Object> resultData = questionStsicSvce.doStatistics(params);
		HSSFWorkbook wb = questionStsicSvce.generateExcel("11", resultData, keyValue);
		try {
			FileOutputStream fos = new FileOutputStream(TEST_TEMPOTORY_PATH + "test_report11.xls");
			try {
				wb.write(fos);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
