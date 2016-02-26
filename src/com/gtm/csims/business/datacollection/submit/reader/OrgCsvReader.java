package com.gtm.csims.business.datacollection.submit.reader;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.business.datacollection.submit.base.CSVFileReader;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.model.BsOrg;

/**
 * 机构全量CSV格式读取实现类.<br>
 * 用于初始化机构数据或者增加新机构
 * 
 * @author Sweet
 * 
 */
public class OrgCsvReader extends CSVFileReader {
    private static final Log LOG = LogFactory.getLog(OrgCsvReader.class);
    @SuppressWarnings("unused")
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private BsOrgDAO bsOrgDao;

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
        BsOrg org = null;
        for (int row = 0; row < lastRowIndex; row++) {
            if (ArrayUtils.isEmpty(csvList.get(row))) {
                failureCount++;
                continue;
            }
            try {
                org = new BsOrg();
                org.setNo(StringUtils.trimToEmpty(csvList.get(row)[0].trim()));
                bsOrgDao.save(org);
                successCount++;
            } catch (Exception e) {
                e.printStackTrace();
                failureCount++;
                continue;
            }
        }
        LOG.debug("success count is :" + successCount + ", failure count is :" + failureCount);
    }

    public void setBsOrgDao(BsOrgDAO bsOrgDao) {
        this.bsOrgDao = bsOrgDao;
    }
}
