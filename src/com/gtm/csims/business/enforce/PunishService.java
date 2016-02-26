package com.gtm.csims.business.enforce;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.serialnumber.NoGenerator;
import com.gtm.csims.dao.BsAdmpunishDAO;
import com.gtm.csims.dao.BsAdmpunishconsDAO;
import com.gtm.csims.dao.BsAeconclusionDAO;
import com.gtm.csims.model.BsAdmpunish;
import com.gtm.csims.model.BsAdmpunishcons;
import com.gtm.csims.model.BsAeconclusion;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.utils.Constants;
import com.gtm.csims.utils.StringUtil;

/**
 * 行政处罚业务实现类
 * 
 * @author Sweet
 * 
 */
@SuppressWarnings("unchecked")
public class PunishService extends BaseEnforceService {
    private BsAdmpunishDAO bsAdmpunishDao;
    private BsAdmpunishconsDAO bsAdmpunishconsDao;
    private BsAeconclusionDAO bsAeconclusionDao;
    private NoGenerator noGenerator;

    public void setNoGenerator(NoGenerator noGenerator) {
        this.noGenerator = noGenerator;
    }

    public void setBsAdmpunishDao(BsAdmpunishDAO bsAdmpunishDao) {
        this.bsAdmpunishDao = bsAdmpunishDao;
    }

    public void setBsAdmpunishconsDao(BsAdmpunishconsDAO bsAdmpunishconsDao) {
        this.bsAdmpunishconsDao = bsAdmpunishconsDao;
    }

    public void setBsAeconclusionDao(BsAeconclusionDAO bsAeconclusionDao) {
        this.bsAeconclusionDao = bsAeconclusionDao;
    }

    /**
     * 保存行政处罚.
     * 
     * @param ae
     */
    @Transactional(readOnly = false)
    public void saveAdmPunish(BsAdmpunish ae) {
        this.validateAdmPunish(ae);
        if (StringUtils.isBlank(ae.getId())) {
            ae.setCreatedate(new Date());
            ae.setUpdateate(new Date());
        } else {
            ae.setUpdateate(new Date());
        }
        bsAdmpunishDao.save(ae);
    }

    /**
     * 保存行政处罚，并更新处罚立项编号.
     * 
     * @param ae
     * @param orgNo
     * @param year
     */
    @Transactional(readOnly = false)
    public void saveAdmPunish(BsAdmpunish ae, String orgNo, String year) {
        this.validateAdmPunish(ae);
        if (StringUtils.isBlank(ae.getId())) {
            ae.setCreatedate(new Date());
            ae.setUpdateate(new Date());
        } else {
            ae.setUpdateate(new Date());
        }
        if (StringUtils.isNotBlank(ae.getAeedorgno())
                && ae.getAeedorgno().trim().equals(Constants.PCB_SC_ORG_NO)) {
            // 被检查机构为分行
            ae.setAeedorgzzno(Constants.PCB_SC_ORG_NO);
            ae.setAeedorgzznm("中国人民银行成都分行");
        } else {
            BsOrg aeedOrg = bsOrgDao.get(ae.getAeedorgno());
            if (aeedOrg != null) {
                if (aeedOrg.getIspcb().equalsIgnoreCase("YES")) {
                    if (aeedOrg.getParentno().trim().equals(Constants.PCB_SC_ORG_NO)) {
                        // 被检查机构为中支
                        ae.setAeedorgzzno(aeedOrg.getNo());
                        ae.setAeedorgzznm(aeedOrg.getName());
                    } else {
                        // 被检查机构为县支行
                        ae.setAeedorgzzno(aeedOrg.getParentno());
                        ae.setAeedorgzznm(aeedOrg.getParentname());
                        ae.setAeedorgxzhno(aeedOrg.getNo());
                        ae.setAeedorgxzhnm(aeedOrg.getName());
                    }
                } else {
                    // 被检查机构为商业银行
                    BsOrg zz = super.getPbcZZNo(ae.getAeedorgno());
                    if (zz != null) {
                        ae.setAeedorgzzno(zz.getNo());
                        ae.setAeedorgzznm(zz.getName());
                    } else {
                        BsOrg xzh = super.getPbcXZHNo(ae.getAeedorgno());
                        if (xzh != null) {
                            ae.setAeedorgzzno(xzh.getParentno());
                            ae.setAeedorgzznm(xzh.getParentname());
                            ae.setAeedorgxzhno(xzh.getNo());
                            ae.setAeedorgxzhnm(xzh.getName());
                        }
                    }
                }
            }
        }
        bsAdmpunishDao.save(ae);
        noGenerator.updateNo(NoGenerator.PUNISH_NO_TYPE, orgNo, year);
    }

    /**
     * 验证行政处罚信息正确性.
     * 
     * @param ae
     */
    private void validateAdmPunish(BsAdmpunish ae) {
        if (ae == null) {
            throw new IllegalArgumentException("行政处罚信息对象为空");
        }
        if (ae.getIllegalpeople() != null && ae.getIllegalpeople().length() > 250) {
            throw new IllegalArgumentException("违法人员超过允许长度250");
        }
        if (ae.getPeopler() != null && ae.getPeopler().length() > 25) {
            throw new IllegalArgumentException("承办人超过允许长度25");
        }
        if (ae.getDpter() != null && ae.getDpter().length() > 25) {
            throw new IllegalArgumentException("部门负责人超过允许长度25");
        }
        if (ae.getChairer() != null && ae.getChairer().length() > 25) {
            throw new IllegalArgumentException("审批人超过允许长度25");
        }
    }

    /**
     * 更新行政处罚立项对应的工作检查结论中存在记录.
     * 
     * @param aeno
     */
    @Transactional(readOnly = false)
    public void updateAeCon(String aeno) {
        if (StringUtils.isBlank(aeno)) {
            return;
        }
        List<BsAeconclusion> list = bsAeconclusionDao.find("from BsAeconclusion where aeno = ? ",
                aeno);
        if (CollectionUtils.isNotEmpty(list)) {
            BsAeconclusion bs = list.get(0);
            bs.setIsfinished(true);
            bsAeconclusionDao.save(bs);
        }
    }

    /**
     * 行政处罚查询
     * 
     * @param aeNo
     *            检查工作记录编号
     * @param aeorgName
     *            检查单位
     * @param aeedorgName
     *            被检查单位
     * @param aeplanstDate
     *            开始日期
     * @param aeplantmDate
     *            结束日期
     * @param pageNo
     * @param pageSize
     * @param orgNo
     *            登录机构编号
     * @return
     */
    @Transactional(readOnly = true)
    public Page queryAdmPunish(String punishNo, String illegalUnit, String aeOrgNm,
            String aeplanstDate, String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
        StringBuffer sb = new StringBuffer("from BsAdmpunish where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (StringUtils.isNotBlank(punishNo)) {
            sb.append(" and punishno like  ? ");
            param.add("%" + punishNo.trim() + "%");
        }
        if (StringUtils.isNotBlank(aeplanstDate)) {
            sb.append(" and crtdate > ? ");
            param.add(StringUtil.convert(aeplanstDate));
        }
        if (StringUtils.isNotBlank(aeplantmDate)) {
            sb.append(" and crtdate < ? ");
            param.add(StringUtil.convert(aeplantmDate));
        }
        if (StringUtils.isNotBlank(illegalUnit)) {
            sb.append(" and illegalunit like ? ");
            param.add("%" + illegalUnit.trim() + "%");
        }
        if (StringUtils.isNotBlank(aeOrgNm)) {
            sb.append(" and aeorgnm like ? ");
            param.add("%" + aeOrgNm.trim() + "%");
        }
        if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
            String childrenNoStr = this.getChildOrgByParentNoStr(orgNo);
            if (StringUtils.isNotBlank(childrenNoStr)) {
                sb.append(" and (").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
                if (super.isPbcZZ(orgNo)) {
                    sb.append(" or aeedorgzzno = ? ");
                    param.add(orgNo.trim());
                } else if (super.isPbcXzh(orgNo)) {
                    sb.append(" or aeedorgxzhno = ? ");
                    param.add(orgNo.trim());
                }
                sb.append(")");
            }
        }
        sb.append(" order by createdate desc");
        Page page = bsAdmpunishDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
        return page;

    }

    /**
     * 判断行政处罚编号是否为系统唯一
     * 
     * @param no
     * @return
     */
    @Transactional(readOnly = true)
    public Boolean isUniqueAdmPunishNo(String no) {
        List<BsAdmpunish> list = bsAdmpunishDao.find("FROM BsAdmpunish where punishno = ? ", no);
        if (CollectionUtils.isNotEmpty(list)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据ID删除行政处罚.
     * 
     * @param id
     */
    @Transactional(readOnly = false)
    public void deleteAdmPunishById(String id) {
        if (StringUtils.isNotBlank(id)) {
            bsAdmpunishDao.removeById(id);
        }
    }

    /**
     * 查询行政处罚详情.
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BsAdmpunish getAdmPunish(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        List<BsAdmpunish> list = bsAdmpunishDao.find("FROM BsAdmpunish where id = ? ", id);
        if (CollectionUtils.isNotEmpty(list)) {
            return (BsAdmpunish) list.get(0);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public BsAdmpunish getAdmPunishById(String id) {
        return bsAdmpunishDao.get(id);
    }

    /**
     * 查询行政处罚详情.
     * 
     * @param aeNo
     * @return
     */
    @Transactional(readOnly = true)
    public List<BsAdmpunish> getAdmPunishListByAeNo(String aeNo) {
        if (StringUtils.isBlank(aeNo)) {
            return null;
        }
        List<BsAdmpunish> list = bsAdmpunishDao.find("from BsAdmpunish where aeno = ? ", aeNo);
        return list;
    }

    /**
     * 查询行政处罚详情
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BsAdmpunish getAdmPunishByAeNo(String aeNo) {
        List<BsAdmpunish> list = bsAdmpunishDao.find("from BsAdmpunish where aeno = ? ", aeNo);
        if (CollectionUtils.isNotEmpty(list)) {
            return (BsAdmpunish) list.get(0);
        }
        return null;
    }

    /**
     * 保存行政处罚结论.
     * 
     * @param ae
     */
    @Transactional(readOnly = false)
    public void saveAdmPunishCon(BsAdmpunishcons ae) {
        if (StringUtils.isBlank(ae.getId())) {
            ae.setCreatedate(new Date());
        } else {
            ae.setUpdateate(new Date());
        }
        if (StringUtils.isNotBlank(ae.getAeedorgno())
                && ae.getAeedorgno().trim().equals(Constants.PCB_SC_ORG_NO)) {
            // 被检查机构为分行
            ae.setAeedorgzzno(Constants.PCB_SC_ORG_NO);
            ae.setAeedorgzznm("中国人民银行成都分行");
        } else {
            BsOrg aeedOrg = bsOrgDao.get(ae.getAeedorgno());
            if (aeedOrg != null) {
                if (aeedOrg.getIspcb().equalsIgnoreCase("YES")) {
                    if (aeedOrg.getParentno().trim().equals(Constants.PCB_SC_ORG_NO)) {
                        // 被检查机构为中支
                        ae.setAeedorgzzno(aeedOrg.getNo());
                        ae.setAeedorgzznm(aeedOrg.getName());
                    } else {
                        // 被检查机构为县支行
                        ae.setAeedorgzzno(aeedOrg.getParentno());
                        ae.setAeedorgzznm(aeedOrg.getParentname());
                        ae.setAeedorgxzhno(aeedOrg.getNo());
                        ae.setAeedorgxzhnm(aeedOrg.getName());
                    }
                } else {
                    // 被检查机构为商业银行
                    BsOrg zz = super.getPbcZZNo(ae.getAeedorgno());
                    if (zz != null) {
                        ae.setAeedorgzzno(zz.getNo());
                        ae.setAeedorgzznm(zz.getName());
                    } else {
                        BsOrg xzh = super.getPbcXZHNo(ae.getAeedorgno());
                        if (xzh != null) {
                            ae.setAeedorgzzno(xzh.getParentno());
                            ae.setAeedorgzznm(xzh.getParentname());
                            ae.setAeedorgxzhno(xzh.getNo());
                            ae.setAeedorgxzhnm(xzh.getName());
                        }
                    }
                }
            }
        }
        bsAdmpunishconsDao.save(ae);
    }

    /**
     * 设置能否修改标识.<br>
     * 
     * 当完成处罚结论新增后，对该处罚结论对应的处罚立项、处罚结论均设置为不能修改
     * 
     * @param aeinspNo
     *            工作检查记录编号
     */
    @Transactional(readOnly = false)
    public void refreshUpdateFlag(final String aeinspNo) {
        if (StringUtils.isBlank(aeinspNo)) {
            return;
        }
        BsAdmpunish admpunsh = this.getAdmPunishByAeNo(aeinspNo);
        if (admpunsh != null) {
            admpunsh.setCanbeupdate(false);
            this.bsAdmpunishDao.save(admpunsh);
        }

        BsAdmpunishcons admpunshcon = this.getAdmPunishConByIno(aeinspNo);
        if (admpunshcon != null) {
            admpunshcon.setCanbeupdate(false);
            this.bsAdmpunishconsDao.save(admpunshcon);
        }
    }

    /**
     * 行政处罚结论查询
     * 
     * @param aeNo
     *            检查工作记录编号
     * @param aeorgName
     *            检查单位
     * @param aeedorgName
     *            被检查单位
     * @param aeplanstDate
     *            开始日期
     * @param aeplantmDate
     *            结束日期
     * @param pageNo
     * @param pageSize
     * @param orgNo
     *            登录机构编号
     * @return
     */
    @Transactional(readOnly = true)
    public Page queryAdmPunishCon(String punishno, String punishUnit, String punishUnitNo,
            String enforceUnit, String aeplanstDate, String aeplantmDate, Integer pageNo,
            Integer pageSize, String orgNo) {
        StringBuffer sb = new StringBuffer("from BsAdmpunishcons where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (StringUtils.isNotBlank(punishno)) {
            sb.append(" and punishno like  ? ");
            param.add("%" + punishno.trim() + "%");
        }
        if (StringUtils.isNotBlank(aeplanstDate)) {
            sb.append(" and crtdate > ? ");
            param.add(StringUtil.convert(aeplanstDate));
        }
        if (StringUtils.isNotBlank(aeplantmDate)) {
            sb.append(" and crtdate < ? ");
            param.add(StringUtil.convert(aeplantmDate));
        }
        if (StringUtils.isNotBlank(punishUnit)) {
            sb.append(" and aeedorgnm like ? ");
            param.add("%" + punishUnit.trim() + "%");
        }
        // if (punishUnitNo != null && !punishUnitNo.trim().equals("")) {
        // sb.append(" and punishunitno like ? ");
        // param.add("%" + punishUnitNo.trim() + "%");
        // }
        if (StringUtils.isNotBlank(enforceUnit)) {
            sb.append(" and aeorgnm like ? ");
            param.add("%" + enforceUnit.trim() + "%");
        }
        if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
            String childrenNoStr = this.getChildOrgByParentNoStr(orgNo);
            if (StringUtils.isNotBlank(childrenNoStr)) {
                sb.append(" and (").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
                if (super.isPbcZZ(orgNo)) {
                    sb.append(" or aeedorgzzno = ? ");
                    param.add(orgNo.trim());
                } else if (super.isPbcXzh(orgNo)) {
                    sb.append(" or aeedorgxzhno = ? ");
                    param.add(orgNo.trim());
                }
                sb.append(")");
            }
        }
        sb.append(" order by createdate desc");
        Page page = bsAdmpunishconsDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
        return page;

    }

    /**
     * 判断行政处罚结论编号是否为系统唯一
     * 
     * @param admenForceNo
     * @return
     */
    @Transactional(readOnly = true)
    public Boolean isUniqueAdmPunishConNo(String no) {
        StringBuffer sb = new StringBuffer("from BsAdmpunishcons where punishbookno = ? ");
        List<BsAdmpunishcons> list = bsAdmpunishconsDao.find(sb.toString(), no);
        if (CollectionUtils.isNotEmpty(list)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据ID删除行政处罚结论
     * 
     * @param messageId
     */
    @Transactional(readOnly = false)
    public void deleteAdmPunishConById(String id) {
        if (StringUtils.isNotBlank(id)) {
            bsAdmpunishconsDao.removeById(id);
        }
    }

    /**
     * 查询行政处罚结论详情
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BsAdmpunishcons getAdmPunishCon(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        List<BsAdmpunishcons> list = bsAdmpunishconsDao.find("from BsAdmpunishcons where id = ? ",
                id);
        if (CollectionUtils.isNotEmpty(list)) {
            return (BsAdmpunishcons) list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据行政执法工作记录编号查询行政处罚结论详情.
     * 
     * @param ino
     * @return
     */
    @Transactional(readOnly = true)
    public BsAdmpunishcons getAdmPunishConByIno(String ino) {
        if (StringUtils.isBlank(ino)) {
            return null;
        }
        List<BsAdmpunishcons> list = bsAdmpunishconsDao.find(
                "from BsAdmpunishcons where aeno = ? ", ino);
        if (CollectionUtils.isNotEmpty(list)) {
            return (BsAdmpunishcons) list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据Id查询行政处罚结论详情.
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BsAdmpunishcons getAdmPunishConById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return bsAdmpunishconsDao.get(id);
    }

    /**
     * 查询行政执法登记详情
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BsAdmpunish getAdmPunishByNo(String no) {
        if (StringUtils.isBlank(no)) {
            return null;
        }
        List<BsAdmpunish> list = bsAdmpunishDao.find("from BsAdmpunish where punishno = ? ", no);
        if (CollectionUtils.isNotEmpty(list)) {
            return (BsAdmpunish) list.get(0);
        } else {
            return null;
        }
    }
}
