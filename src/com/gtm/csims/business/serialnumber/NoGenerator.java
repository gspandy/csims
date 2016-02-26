package com.gtm.csims.business.serialnumber;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsNogenerateDAO;
import com.gtm.csims.model.BsNogenerate;

/**
 * 工作检查记录相关信息编号生成器
 * 
 * @author Sweet
 * 
 */
public class NoGenerator {

    /**
     * 编号类型.
     */
    public static final int AENFORCE_NO_TYPE = 1;
    public static final int EVIDENCE_NO_TYPE = 2;
    public static final int FACTBOOK_NO_TYPE = 3;
    public static final int GOAWAY_NO_TYPE = 4;
    public static final int PUNISH_NO_TYPE = 5;
    public static final int INCOMING_NO_TYPE = 6;

    private BsNogenerateDAO bsNogenerateDao;

    /**
     * 获取当前机构的编号对象.
     * 
     * @param orgNo
     *            机构编号
     * @param year
     *            年份
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public BsNogenerate getNoGenerate(String orgNo, String year) {
        if (StringUtils.isNotBlank(orgNo) && StringUtils.isNotBlank(year)) {
            String hql = "from BsNogenerate where orgno = ? and aenoyear = ?";
            List<BsNogenerate> noGrtList = bsNogenerateDao.find(hql, orgNo, year);
            if (CollectionUtils.isNotEmpty(noGrtList)) {
                BsNogenerate noGrt = noGrtList.get(0);
                if (noGrt == null) {
                    return null;
                }
                // noGrt = bsNogenerateDao.get(noGrt.getId());
                return noGrt;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 更新编号.
     * 
     * @param type
     *            编号种类
     * @param orgNo
     *            机构代码
     * @param year
     *            年份
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public void updateNo(int type, String orgNo, String year) {
        if (StringUtils.isNotBlank(orgNo) && StringUtils.isNotBlank(year)) {
            String hql = "from BsNogenerate where orgno = ? and aenoyear = ?";
            List<BsNogenerate> noGrtList = bsNogenerateDao.find(hql, orgNo, year);
            if (CollectionUtils.isNotEmpty(noGrtList)) {
                BsNogenerate noGrt = noGrtList.get(0);
                if (noGrt != null) {
                    switch (type) {
                    case AENFORCE_NO_TYPE:// 行政执法立项编号
                        noGrt.setAenoindex(noGrt.getAenoindex() + 1);
                        break;
                    case EVIDENCE_NO_TYPE:// 取证编号
                        noGrt.setEvdcnoindex(noGrt.getEvdcnoindex() + 1);
                        break;
                    case FACTBOOK_NO_TYPE:// 事实认定书编号
                        noGrt.setFactnoindex(noGrt.getFactnoindex() + 1);
                        break;
                    case GOAWAY_NO_TYPE:// 离场编号
                        noGrt.setAwaynoindex(noGrt.getAwaynoindex() + 1);
                        break;
                    case PUNISH_NO_TYPE:// 处罚编号
                        noGrt.setPbnshnoindex(noGrt.getPbnshnoindex() + 1);
                        break;
                    case INCOMING_NO_TYPE:// 进场编号
                        noGrt.setComeinindex(noGrt.getComeinindex() + 1);
                        break;
                    default:
                        return;
                    }
                    bsNogenerateDao.save(noGrt);
                }
            }
        }
    }

    /**
     * 生成编号字符串.
     * 
     * @param noStr
     * @param year
     * @param no
     * @return
     */
    public String generateNoStr(String noStr, String year, String no) {
        return String.format("%s【%s】第【%s】号", noStr, year, no);
    }

    /**
     * 获取当前年.
     * 
     * @return
     */
    public String getYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return String.valueOf(year);
    }

    public void setBsNogenerateDao(BsNogenerateDAO bsNogenerateDao) {
        this.bsNogenerateDao = bsNogenerateDao;
    }
}
