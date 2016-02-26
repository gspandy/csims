package com.gtm.csims.business.datacollection.submit.reader;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.business.datacollection.submit.base.CSVFileReader;

/**
 * 机构全量CSV读取到临时全量机构表.<br>
 * 用于将人行提供的机构全量原始数据读取到全量机构临时表
 * 
 * @author Sweet
 * 
 */
public class AllOrgInitCsvReader extends CSVFileReader {
    private static final Log LOG = LogFactory.getLog(OrgCsvReader.class);
    @SuppressWarnings("unused")
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private JdbcTemplate jdbcTemplate;

    @Override
    protected void read(ArrayList<String[]> csvList, Map<String, String> reportParam)
            throws ReportSubmitException {
        if (CollectionUtils.isEmpty(csvList)) {
            return;
        }
        int successCount = 0;
        int failureCount = 0;
        int lastRowIndex = csvList.size();
        LOG.info("total count:" + lastRowIndex);
        StringBuffer insertSql = new StringBuffer();
        for (int row = 0; row < lastRowIndex; row++) {
            insertSql.setLength(0);
            if (ArrayUtils.isEmpty(csvList.get(row))) {
                failureCount++;
                continue;
            }
            try {
                insertSql
                        .append("INSERT INTO TEMP_INIT_ALL_ORG (A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z")
                        .append(",AA,AB,AC,AD,AE,AF,AG,AH,AI,AJ,AK,AL,AM) VALUES(").append("'");
                for (int i = 0; i < 39; i++) {
                    insertSql.append(StringUtils.trimToEmpty(csvList.get(row)[i].trim())
                            .replace("'", "“").replace(";", ","));
                    if (i < 39 - 1) {
                        insertSql.append("','");
                    } else {
                        insertSql.append("')");
                    }
                }
                jdbcTemplate.execute(insertSql.toString());
                successCount++;

            } catch (Exception e) {
                e.printStackTrace();
                failureCount++;
                continue;
            }
        }
        LOG.debug("success count is :" + successCount + ", failure count is :" + failureCount);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
