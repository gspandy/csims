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
public class WtgkStatisticServiceTestCase extends ApplicationContextTest {

    private StatisticsService wtgkStsicSvce;

    public void setWtgkStsicSvce(StatisticsService wtgkStsicSvce) {
        this.wtgkStsicSvce = wtgkStsicSvce;
    }

    public void testGenerateHTML() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_DATE", "2014-11-18 00:00:00");
        params.put("TO_DATE", "2014-11-18 23:59:59");
        params.put("LOGIN_ORGNO", "A1000151000028");
        params.put("LOGIN_IS_PCB", "YES");
        Map<String, String> keyValue = new HashMap<String, String>();
        keyValue.put("DURING", "2014");
        keyValue.put("ANL_ORG", "中国人民银行成都分行");

        Map<String, Object> resultData = wtgkStsicSvce.doStatistics(params);
        File file = new File(TEST_TEMPOTORY_PATH + "test_report3.html");
        if (file.exists()) {
            file.delete();
        }
        try {
            // file = new File("F:/MyWorkSapces/testtemp/test_report1.html");
            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.write("<html><head></head><body>");
            out.write(wtgkStsicSvce.getHTMLString("3", resultData, keyValue));
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
        params.put("FROM_DATE", "2014-11-18 00:00:00");
        params.put("TO_DATE", "2014-11-18 23:59:59");
        params.put("LOGIN_ORGNO", "A1000151000028");
        params.put("LOGIN_IS_PCB", "YES");
        Map<String, String> keyValue = new HashMap<String, String>();
        keyValue.put("DURING", "2014");
        keyValue.put("ANL_ORG", "中国人民银行成都分行");

        Map<String, Object> resultData = wtgkStsicSvce.doStatistics(params);
        HSSFWorkbook wb = wtgkStsicSvce
                .generateExcel("3", resultData, keyValue);
        try {
            FileOutputStream fos = new FileOutputStream(TEST_TEMPOTORY_PATH
                    + "test_report3.xls");
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
