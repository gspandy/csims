package net.sweet.test.imptuser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.datacollection.submit.reader.OrgCsvReader;

public class OrgCsvReaderTestCase extends ApplicationContextTest {
    private OrgCsvReader orgCsvReader;

    public void setOrgCsvReader(OrgCsvReader orgCsvReader) {
        this.orgCsvReader = orgCsvReader;
    }

    private static final String FOLD_PATH = "E:\\MyWorkSpaces10\\";

    public void test_read() {
        InputStream is = null;
        try {
            is = new FileInputStream(FOLD_PATH + "org.csv");
            orgCsvReader.read(is, "GB2312", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
