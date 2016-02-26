package com.gtm.csims.business.exam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.enforce.BaseEnforceService;
import com.gtm.csims.dao.BsExamscoreDAO;
import com.gtm.csims.model.BsExamscore;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.utils.Constants;
import com.gtm.csims.utils.StringUtil;

/**
 * 考试功能相关业务实现类
 * 
 * @author yangyz 
 * @version 1.5
 * @see java.lang.Class
 * @since JDK1.0
 */
public class ExamService extends BaseEnforceService {
    private BsExamscoreDAO bsExamscoreDao;

    /**
     * 保存考试成绩
     * 
     * @param es
     */
    @Transactional(readOnly = false)
    public void saveExamscore(BsExamscore es) {
        if (es == null) {
            return;
        }
        if (StringUtils.isBlank(es.getId())) {
            es.setCreatedate(new Date());
        } else {
            es.setUpdateate(new Date());
        }
        bsExamscoreDao.save(es);
    }

    /**
     * 人民银行查询用户考试成绩信息
     * 
     * @param peoId
     * @param peonm
     * @param peotype
     * @param exStDt
     * @param exTmDt
     * @param pageNo
     * @param pageSize
     * @param orgNo
     * @return
     */
    @Transactional(readOnly = true)
    public Page queryExamScoreByPcb(String peoId, String peonm, String peotype, String exStDt,
            String exTmDt, Integer pageNo, Integer pageSize, String orgNo) {
        StringBuffer sb = new StringBuffer("FROM BsExamscore where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (StringUtils.isNotBlank(peoId)) {
            sb.append(" and peoid like  ? ");
            param.add(peoId.trim() + "%");
        }
        if (StringUtils.isNotBlank(peonm)) {
            sb.append(" and peonm like  ? ");
            param.add(peonm.trim() + "%");
        }
        if (StringUtils.isNotBlank(peotype)) {
            sb.append(" and extype = ? ");
            param.add(peotype.trim());
        }
        if (StringUtils.isNotBlank(exStDt)) {
            sb.append(" and exstdt >= ? ");
            param.add(StringUtil.convert(exStDt, false));
        }
        if (StringUtils.isNotBlank(exTmDt)) {
            sb.append(" and extmdt <= ? ");
            param.add(StringUtil.convert(exTmDt, true));
        }
        if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
            String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
            if (StringUtils.isNotBlank(childrenNoStr)) {
                sb.append(" and  ").append(this.getOrInClause("pcbno in (%s)", childrenNoStr));

            }
        }
        sb.append(" order by createdate DESC");
        Page page = bsExamscoreDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
        return page;
    }

    /**
     * 金融机构管理员用户查询用户考试成绩信息
     * 
     * @param peoId
     * @param peonm
     * @param peotype
     * @param exStDt
     * @param exTmDt
     * @param pageNo
     * @param pageSize
     * @param orgNo
     * @return
     */
    @Transactional(readOnly = true)
    public Page queryExamScoreByBank(String peoId, String peonm, String peotype, String exStDt,
            String exTmDt, Integer pageNo, Integer pageSize, String orgNo) {
        StringBuffer sb = new StringBuffer("FROM BsExamscore where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (StringUtils.isNotBlank(peoId)) {
            sb.append(" and peoid like  ? ");
            param.add("%" + peoId.trim() + "%");
        }
        if (StringUtils.isNotBlank(peonm)) {
            sb.append(" and peonm like  ? ");
            param.add("%" + peonm.trim() + "%");
        }
        if (StringUtils.isNotBlank(peotype)) {
            sb.append(" and peotype like  ? ");
            param.add("%" + peotype.trim() + "%");
        }
        if (StringUtils.isNotBlank(exStDt)) {
            sb.append(" and exstdt >= ? ");
            param.add(StringUtil.convert(exStDt, false));
        }
        if (StringUtils.isNotBlank(exTmDt)) {
            sb.append(" and extmdt <= ? ");
            param.add(StringUtil.convert(exTmDt, true));
        }
        if (StringUtils.isNotBlank(orgNo)) {
            BsOrg org = bsOrgDao.get(orgNo.trim());
            if (org != null && StringUtils.isNotBlank(org.getH())) {
                sb.append(" and orgtpno = ? ");
                if (StringUtils.equals(org.getF(), "3")) {
                    //登录机构为农村信用合作社（含联社）
                    param.add("3");
                } else {
                    param.add(org.getH().trim());
                }
            }
        }
        sb.append(" order by createdate DESC");
        Page page = bsExamscoreDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
        return page;
    }

    /**
     * 查询考试成绩详情
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BsExamscore getExamScoreById(String id) {
        return bsExamscoreDao.get(id);
    }

    /**
     * 根据条件计算范围内考试通过率
     * 
     * @param peotype
     *            用户类型
     * @param exStDt
     *            考试开始时间
     * @param exTmDt
     *            考试结束时间
     * @param isPcb
     *            当前统计用户是否数据人行
     * @param orgNo
     *            当前用户所属机构
     * @return 及格、不及格、满分三种情况所占比例
     */
    @Transactional(readOnly = true)
    public Map<String, String> calculateExamPassRate(String peotype, String exStDt, String exTmDt,
            Boolean isPcb, String orgNo) {
        Map<String, String> passRateMap = new HashMap<String, String>();
        StringBuilder sqlCondition = new StringBuilder();
        List<Object> param = new ArrayList<Object>();
        if (StringUtils.isNotBlank(peotype)) {
            sqlCondition.append(" AND peotype LIKE ? ");
            param.add("%" + peotype.trim() + "%");
        }
        if (StringUtils.isNotBlank(exStDt)) {
            sqlCondition.append(" AND exstdt >= ? ");
            param.add(StringUtil.convert(exStDt, false));
        }
        if (StringUtils.isNotBlank(exTmDt)) {
            sqlCondition.append(" AND extmdt <= ? ");
            param.add(StringUtil.convert(exTmDt, true));
        }
        if (BooleanUtils.isTrue(isPcb)) {
            if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
                String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
                if (StringUtils.isNotBlank(childrenNoStr)) {
                    sqlCondition.append(" AND ").append(
                            this.getOrInClause("pcbno in (%s)", childrenNoStr));
                }
            }
        } else {
            if (StringUtils.isNotBlank(orgNo)) {
                BsOrg org = bsOrgDao.get(orgNo.trim());
                if (org != null && StringUtils.isNotBlank(org.getH())) {
                    sqlCondition.append(" AND orgtpno = ? ");
                    param.add(org.getH().trim());
                }
            }
        }
        StringBuilder sqlSb = new StringBuilder(
                "SELECT COUNT(id) FROM BS_EXAMSCORE WHERE score >= pass AND score <> fullmark ");
        int rate = 0;
        try {
            rate = jdbcTemplate.queryForInt(sqlSb.append(sqlCondition).toString(), param.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        passRateMap.put("及格", String.valueOf(rate));
        System.out.println(sqlSb.toString());
        sqlSb.setLength(0);
        sqlSb.append("SELECT COUNT(id) FROM BS_EXAMSCORE WHERE score < pass ");
        rate = 0;
        try {
            rate = jdbcTemplate.queryForInt(sqlSb.append(sqlCondition).toString(), param.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        passRateMap.put("不及格", String.valueOf(rate));
        sqlSb.setLength(0);
        sqlSb.append("SELECT COUNT(id) FROM BS_EXAMSCORE WHERE score = fullmark ");
        rate = 0;
        try {
            rate = jdbcTemplate.queryForInt(sqlSb.append(sqlCondition).toString(), param.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        passRateMap.put("满分", String.valueOf(rate));

        return passRateMap;
    }

    /**
     * 返回用户最近考试成绩
     * 
     * @param loginId
     *            用户ID
     * @param peotype
     *            用户类型
     * 
     *            1.企业征信查询用户 2.个人征信查询用户
     * @return 该用户作为当前用户类型参加的最近一次考试成绩
     * 
     *         出现异常或者错误统一返回0
     */
    @Transactional(readOnly = true)
    public BigDecimal getRecentScore(String loginId, String peotype) {
        Page page = bsExamscoreDao.pagedQuery(
                "from BsExamscore where peoid = ? and peotype = ? order by extmdt desc", 1, 1,
                loginId, peotype);
        if (page != null) {
            try {
                List resultLt = (List) page.getResult();
                if (resultLt != null && resultLt.size() > 0) {
                    BsExamscore score = (BsExamscore) resultLt.get(0);
                    return score.getScore();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BigDecimal.ZERO;
    }

    public void setBsExamscoreDao(BsExamscoreDAO bsExamscoreDao) {
        this.bsExamscoreDao = bsExamscoreDao;
    }
}
