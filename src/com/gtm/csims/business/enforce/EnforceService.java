package com.gtm.csims.business.enforce;

import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.csvreader.CsvWriter;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.business.serialnumber.NoGenerator;
import com.gtm.csims.dao.BsAdmenforceDAO;
import com.gtm.csims.dao.BsAdmpunishDAO;
import com.gtm.csims.dao.BsAdmpunishconsDAO;
import com.gtm.csims.dao.BsAeFeedBackDAO;
import com.gtm.csims.dao.BsAePeopleJoinHistoryDAO;
import com.gtm.csims.dao.BsAePublishFeedBackDAO;
import com.gtm.csims.dao.BsAeconclusionDAO;
import com.gtm.csims.dao.BsAeedOrgJoinHistoryDAO;
import com.gtm.csims.dao.BsAeinspectionAnlDAO;
import com.gtm.csims.dao.BsAeinspectionDAO;
import com.gtm.csims.dao.BsAerectificationDAO;
import com.gtm.csims.dao.BsFactbookDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.dao.BsWorkbasisDAO;
import com.gtm.csims.dao.BsWorkcomingDAO;
import com.gtm.csims.dao.BsWorkgoawayDAO;
import com.gtm.csims.dao.BsWorktalksummaryDAO;
import com.gtm.csims.model.BsAdmenforce;
import com.gtm.csims.model.BsAdmpunish;
import com.gtm.csims.model.BsAdmpunishcons;
import com.gtm.csims.model.BsAeFeedBack;
import com.gtm.csims.model.BsAePublishFeedBack;
import com.gtm.csims.model.BsAeconclusion;
import com.gtm.csims.model.BsAeinspection;
import com.gtm.csims.model.BsAeinspectionAnl;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.model.BsAerectification;
import com.gtm.csims.model.BsDictionary;
import com.gtm.csims.model.BsFactbook;
import com.gtm.csims.model.BsNogenerate;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsWorkbasis;
import com.gtm.csims.model.BsWorkcoming;
import com.gtm.csims.model.BsWorkgoaway;
import com.gtm.csims.model.BsWorktalksummary;
import com.gtm.csims.utils.Constants;
import com.gtm.csims.utils.StringUtil;

/**
 * 行政执法业务实现类
 * 
 * @author yangyz 
 * @version 1.5
 * @see java.lang.Class
 * @since JDK1.0
 */
@SuppressWarnings("unchecked")
public class EnforceService extends BaseEnforceService {
	private BsAdmenforceDAO bsAdmenforceDao;
	private BsAeconclusionDAO bsAeconclusionDao;
	private BsAeinspectionDAO bsAeinspectionDao;
	private BsAerectificationDAO bsAerectificationDao;
	private BsWorkbasisDAO bsWorkbasisDao;
	private BsWorkcomingDAO bsWorkcomingDao;
	private BsWorkgoawayDAO bsWorkgoawayDao;
	private BsWorktalksummaryDAO bsWorktalksummaryDao;
	private BsAeinspectionAnlDAO bsAeinspectionAnlDao;
	private BsFactbookDAO bsFactbookDao;
	private NoGenerator noGenerator;
	private RemindService remindService;
	private BsAdmpunishDAO bsAdmpunishDao;
	private BsAdmpunishconsDAO bsAdmpunishconsDao;
	private BsAeFeedBackDAO bsAeFeedBackDao;
	private BsAePublishFeedBackDAO bsAePublishFeedBackDao;

	private BsAePeopleJoinHistoryDAO bsAePeopleJoinHistoryDao;
	private BsAeedOrgJoinHistoryDAO BsAeedOrgJoinHistoryDao;

	/**
	 * 保存行政执法登记.
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveAdmenforce(BsAdmenforce ae) {
		this.validateAdmenforce(ae);
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdatedate(new Date());
		}
		bsAdmenforceDao.save(ae);
	}

	/**
	 * 保存行政执法登记，并更新执法检查编号
	 * 
	 * @param ae
	 * @param orgNo
	 * @param year
	 */
	@Transactional(readOnly = false)
	public void saveAdmenforce(BsAdmenforce ae, String orgNo, String year) {
		this.validateAdmenforce(ae);
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdatedate(new Date());
		}
		bsAdmenforceDao.save(ae);
		noGenerator.updateNo(NoGenerator.AENFORCE_NO_TYPE, orgNo, year);
	}

	/**
	 * 验证行政执法立项信息正确性.
	 * 
	 * @param ae
	 */
	private void validateAdmenforce(BsAdmenforce ae) {
		if (ae == null) {
			throw new IllegalArgumentException("立项信息对象为空");
		}
		if (ae.getAecontent() != null && ae.getAecontent().getBytes().length > 2990) {
			throw new IllegalArgumentException("检查内容超过允许长度500");
		}
		if (ae.getPrjnm() != null && ae.getPrjnm().length() > 100) {
			throw new IllegalArgumentException("项目名称超过允许长度100");
		}
		if (ae.getInnerno() != null && ae.getInnerno().length() > 25) {
			throw new IllegalArgumentException("行内编号超过允许长度25");
		}
		if (ae.getAesummary() != null && ae.getAesummary().length() > 250) {
			throw new IllegalArgumentException("备注超过允许长度250");
		}
		if (ae.getDptopnion() != null && ae.getDptopnion().length() > 250) {
			throw new IllegalArgumentException("部门负责人意见超过允许长度250");
		}
		if (ae.getDeptman() != null && ae.getDeptman().length() > 25) {
			throw new IllegalArgumentException("部门负责人超过允许长度25");
		}
		if (ae.getChairopnion() != null && ae.getChairopnion().length() > 250) {
			throw new IllegalArgumentException("行领导审批意见超过允许长度250");
		}
		if (ae.getChairman() != null && ae.getChairman().length() > 25) {
			throw new IllegalArgumentException("行领导名称超过允许长度25");
		}
	}

	/**
	 * 行政执法查询
	 * 
	 * @param aeNo
	 *            执法检查立项编号
	 * @param aeorgName
	 *            检查单位
	 * @param aeedorgName
	 *            被检查单位
	 * @param aeplanstDate
	 *            开始日期
	 * @param aeplantmDate
	 *            结束日期
	 * @param pageNo
	 *            页号
	 * @param pageSize
	 *            每页显示记录数 *
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAdmenforce(String aeNo, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAdmenforce where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" and ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate DESC");
		Page page = bsAdmenforceDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;

	}

	/**
	 * 行政执法查询.
	 * 
	 * 
	 * 用于登记工作检查记录
	 * 
	 * @param aeNo
	 *            执法检查立项编号
	 * @param aeorgName
	 *            检查单位
	 * @param aeedorgName
	 *            被检查单位
	 * @param aeplanstDate
	 *            开始日期
	 * @param aeplantmDate
	 *            结束日期
	 * @param pageNo
	 *            页号
	 * @param pageSize
	 *            每页显示记录数 *
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAdmenforceForInspc(String aeNo, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAdmenforce where isfinish = 'N' ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" and ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate DESC");
		Page page = bsAdmenforceDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;

	}

	/**
	 * 判断行政执法编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean isUniqueAdmenforceNo(String admenForceNo) {
		List<BsAdmenforce> list = bsAdmenforceDao.find("from BsAdmenforce where aeno = ? ", admenForceNo);
		if (CollectionUtils.isNotEmpty(list)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据ID删除行政执法
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteAdmenforceById(String id) {
		if (StringUtils.isNotBlank(id)) {
			bsAdmenforceDao.removeById(id);
		}
	}

	/**
	 * 查询行政执法登记详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAdmenforce getAdmenforce(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		List<BsAdmenforce> list = bsAdmenforceDao.find("from BsAdmenforce where id = ? ", id);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAdmenforce) list.get(0);
		}
		return null;
	}

	/**
	 * 查询行政执法登记详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAdmenforce getAdmenforceById(String id) {
		return bsAdmenforceDao.get(id);
	}

	/**
	 * 查询行政执法登记详情
	 * 
	 * @param no
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAdmenforce getAdmenforceByNo(String no) {
		if (StringUtils.isBlank(no)) {
			return null;
		}
		List<BsAdmenforce> list = bsAdmenforceDao.find("from BsAdmenforce where aeno = ? ", no);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAdmenforce) list.get(0);
		}
		return null;
	}

	/**
	 * 保存检查工作记录.
	 * 
	 * @param aeinsp
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveAeinspection(BsAeinspection aeinsp) {
		this.validateAeinspection(aeinsp);
		if (StringUtils.isBlank(aeinsp.getId())) {
			aeinsp.setCreatedate(new Date());
		} else {
			aeinsp.setUpdateate(new Date());
		}
		if (StringUtils.isNotBlank(aeinsp.getAeedorgno())
		        && aeinsp.getAeedorgno().trim().equals(Constants.PCB_SC_ORG_NO)) {
			// 被检查机构为分行
			aeinsp.setAeedorgzzno(Constants.PCB_SC_ORG_NO);
			aeinsp.setAeedorgzznm("中国人民银行成都分行");
		} else {
			BsOrg aeedOrg = bsOrgDao.get(aeinsp.getAeedorgno());
			if (aeedOrg != null) {
				if (aeedOrg.getIspcb().equalsIgnoreCase("YES")) {
					if (aeedOrg.getParentno().trim().equals(Constants.PCB_SC_ORG_NO)) {
						// 被检查机构为中支
						aeinsp.setAeedorgzzno(aeedOrg.getNo());
						aeinsp.setAeedorgzznm(aeedOrg.getName());
					} else {
						// 被检查机构为县支行
						aeinsp.setAeedorgzzno(aeedOrg.getParentno());
						aeinsp.setAeedorgzznm(aeedOrg.getParentname());
						aeinsp.setAeedorgxzhno(aeedOrg.getNo());
						aeinsp.setAeedorgxzhnm(aeedOrg.getName());
					}
				} else {
					// 被检查机构为商业银行
					BsOrg zz = super.getPbcZZNo(aeinsp.getAeedorgno());
					if (zz != null) {
						aeinsp.setAeedorgzzno(zz.getNo());
						aeinsp.setAeedorgzznm(zz.getName());
					} else {
						BsOrg xzh = super.getPbcXZHNo(aeinsp.getAeedorgno());
						if (xzh != null) {
							aeinsp.setAeedorgzzno(xzh.getParentno());
							aeinsp.setAeedorgzznm(xzh.getParentname());
							aeinsp.setAeedorgxzhno(xzh.getNo());
							aeinsp.setAeedorgxzhnm(xzh.getName());
						}
					}
				}
			}
		}
		bsAeinspectionDao.save(aeinsp);
	}

	/**
	 * 验证工作检查记录信息正确性.
	 * 
	 * @param ae
	 */
	private void validateAeinspection(BsAeinspection aeinsp) {
		if (aeinsp == null) {
			throw new IllegalArgumentException("工作检查记录对象为空");
		}
		if (aeinsp.getInnerno() != null && aeinsp.getInnerno().length() > 25) {
			throw new IllegalArgumentException("检查工作记录编号（行内）超过允许长度25");
		}
	}

	/**
	 * 保存工作记录后更新立项信息状态.
	 * 
	 * @param aeno
	 *            立项编号
	 */
	@Transactional(readOnly = false)
	public void updateAeStatu(String aeno) {
		BsAdmenforce ae = this.getAdmenforceByNo(aeno);
		if (ae != null) {
			ae.setStep("2");
			if (ae.getMaxino() == null) {
				ae.setMaxino(1);
			} else {
				ae.setMaxino(ae.getMaxino() + 1);
			}
			List inspcs = this.getAeinspectionsByAeNo(ae.getAeno());
			int inspcCount;
			if (CollectionUtils.isNotEmpty(inspcs)) {
				inspcCount = inspcs.size();
			} else {
				inspcCount = 0;
			}
			if (StringUtils.split(ae.getAeedorgno(), ",").length == inspcCount) {
				ae.setIsfinished(true);
			}
			ae.setCanbeupdate(false);
			this.saveAdmenforce(ae);
		}
	}

	/**
	 * 更新工作检查结论对应的工作检查记录中存在记录.
	 * 
	 * @param aeno
	 */
	@Transactional(readOnly = false)
	public void updateInspcByConlusion(String aeno) {
		BsAeinspection inspc = getAeinspectionByIno(aeno);
		if (inspc != null) {
			inspc.setIsfinished(true);
			bsAeinspectionDao.save(inspc);
		}
	}

	/**
	 * 更新整改记录对应的工作检查记录中存在记录.
	 * 
	 * @param aeno
	 */
	@Transactional(readOnly = false)
	public void updateInspcByRefti(String aeno) {
		BsAeinspection inspc = getAeinspectionByIno(aeno);
		if (inspc != null) {
			inspc.setIsfinishedRefti(true);
			bsAeinspectionDao.save(inspc);
		}
	}

	/**
	 * 判断是否存在指定被检查机构的工作检查记录.
	 * 
	 * @param aeno
	 * @param aeedorgno
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean isExsitAeInspc(String aeno, String aeedorgno) {
		List<BsAeinspection> inspcList = bsAeinspectionDao.find(
		        "from BsAeinspection where aeno = ? and aeedorgno = ? ", aeno, aeedorgno);
		if (inspcList == null || inspcList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 如果当前执法检查工作记录判断为重复，则需要删除当前工作检查记录的所有相关信息
	 * 
	 * 包括进场、离场、事实认定书、会谈纪要、统计情况
	 * 
	 * @param inspect_token
	 */
	@Transactional(readOnly = false)
	public void deleteAeinspectionInfo(String inspectToken) {
		String[] sqls = new String[6];
		sqls[0] = "DELETE FROM BS_WORKCOMING WHERE stat = '" + inspectToken + "'";
		sqls[1] = "DELETE FROM BS_WORKGOAWAY WHERE stat = '" + inspectToken + "'";
		sqls[2] = "DELETE FROM BS_WORKTALKSUMMARY WHERE stat = '" + inspectToken + "'";
		sqls[3] = "DELETE FROM BS_FACTBOOK WHERE stat = '" + inspectToken + "'";
		sqls[4] = "DELETE FROM BS_AEINSPECTION_ANL WHERE stat = '" + inspectToken + "'";
		sqls[5] = "DELETE FROM BS_WORKBASIS WHERE stat = '" + inspectToken + "'";
		jdbcTemplate.batchUpdate(sqls);
	}

	/**
	 * 检查工作记录查询
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
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAeinspection(String aeNo, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAeinspection where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			// end = end + " 0:00:00";
			// param.add(Timestamp.valueOf(end));
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
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
		sb.append(" order by createdate DESC");
		Page page = bsAeinspectionDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 检查工作记录查询（用于录入检查结论）
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
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAeinspectionForCon(String aeNo, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAeinspection where isfinish= 'N' ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			// end = end + " 0:00:00";
			// param.add(Timestamp.valueOf(end));
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" and ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate DESC");
		Page page = bsAeinspectionDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 检查工作记录查询（用于录入整改信息）
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
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAeinspectionForRefti(String aeNo, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("from BsAeinspection where isfinishRefti = 'N' ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			// end = end + " 0:00:00";
			// param.add(Timestamp.valueOf(end));
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" and ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate desc");
		Page page = bsAeinspectionDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 判断检查工作记录编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean isUniqueAeInspectionNo(String admenForceNo) {
		List<BsAeinspection> list = bsAeinspectionDao.find("FROM BsAeinspection where aerecordno = ? ", admenForceNo);
		if (CollectionUtils.isNotEmpty(list)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据ID删除检查工作记录
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteAeinspectionById(String id) {
		if (StringUtils.isNotBlank(id)) {
			bsAeinspectionDao.removeById(id);
		}
	}

	/**
	 * 查询检查工作记录详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeinspection getAeinspection(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		StringBuffer sb = new StringBuffer("from BsAeinspection where id = ? ");
		List<BsAeinspection> list = bsAeinspectionDao.find(sb.toString(), id);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAeinspection) list.get(0);
		}
		return null;
	}

	/**
	 * 根据工作检查记录编号查询检查工作记录详情
	 * 
	 * @param ino
	 *            工作检查记录编号
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeinspection getAeinspectionByIno(String ino) {
		if (StringUtils.isBlank(ino)) {
			return null;
		}
		List<BsAeinspection> list = bsAeinspectionDao.find("from BsAeinspection where ino = ? ", ino);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAeinspection) list.get(0);
		}
		return null;
	}

	/**
	 * 查询检查工作记录详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeinspection getAeinspectionById(String id) {
		return bsAeinspectionDao.get(id);
	}

	/**
	 * 根据行政执法立项编号查询对应的工作检查记录
	 * 
	 * @param aeNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List getAeinspectionsByAeNo(String aeNo) {
		if (StringUtils.isBlank(aeNo)) {
			return null;
		}
		try {
			return bsAeinspectionDao.find("from BsAeinspection where aeno = ? ", aeNo);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 保存执法检查结论
	 */
	@Transactional(readOnly = false)
	public void saveAeconclusion(BsAeconclusion ae) {
		this.validateAeconclusion(ae);
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		if (StringUtils.isNotBlank(ae.getAeedorgno()) && ae.getAeedorgno().trim().equals(Constants.PCB_SC_ORG_NO)) {
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
					if (StringUtils.equals(aeedOrg.getF(), "3")) {
						// 如果当前被检查机构为“农村信用合作社（含联社）”
						ae.setAeedOrgBankType(aeedOrg.getF());
					} else {
						ae.setAeedOrgBankType(aeedOrg.getH());
					}
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

		BsOrg aeedOrg = this.getOrgByNo(ae.getAeedorgno());
		BsOrg topLevelBank = this.getTopLevelOrgByNo(ae.getAeedorgno());
		ae.setDirectParentlBankNm(aeedOrg.getParentname());
		ae.setDirectParentlBankNo(aeedOrg.getParentno());
		ae.setTopLevelBankNm(topLevelBank.getName());
		ae.setTopLevelBankNo(topLevelBank.getNo());
		bsAeconclusionDao.save(ae);
	}

	/**
	 * 保存执法检查结论
	 */
	@Transactional(readOnly = false)
	public void saveAeconclusionSimple(BsAeconclusion ae) {
		bsAeconclusionDao.save(ae);
	}

	/**
	 * 验证执法检查结论信息正确性.
	 * 
	 * @param ae
	 */
	private void validateAeconclusion(BsAeconclusion ae) {
		if (ae == null) {
			throw new IllegalArgumentException("执法检查结论对象为空");
		}
		if (ae.getAeopnionno() != null && ae.getAeopnionno().length() > 25) {
			throw new IllegalArgumentException("执法检查意见书编号超过允许长度25");
		}
	}

	/**
	 * 执法检查结论查询
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
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAeconclusion(String aeNo, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("from BsAeconclusion where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeopnionno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			// end = end + " 0:00:00";
			// param.add(Timestamp.valueOf(end));
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
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
		Page page = bsAeconclusionDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 执法检查结论查询（用于查询待登记行政处罚列表页面）
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
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAeconclusionWithPunish(String aeNo, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer(
		        "from BsAeconclusion where isfinish = 'N' and decision like '%051%' and flag is null ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeopnionno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			// end = end + " 0:00:00";
			// param.add(Timestamp.valueOf(end));
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" and ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate desc");
		Page page = bsAeconclusionDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 金融机构查询行政执法相关信息
	 * 
	 * @param aeNo
	 *            检查工作记录编号
	 * @param aeOpnionNo
	 *            执法检查意见书编号
	 * @param aeorgName
	 *            检查单位
	 * @param aeedorgName
	 *            被检查单位
	 * @param aeplanstDate
	 *            开始日期
	 * @param aeplantmDate
	 *            结束日期
	 * @param pageNo
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAeconclusionByBank(String aeNo, String aeOpnionNo, String aeorgName, String aeedorgName,
	        String aeplanstDate, String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		if (StringUtils.isBlank(orgNo)) {
			return null;
		}
		StringBuffer sb = new StringBuffer("from BsAeconclusion where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeOpnionNo)) {
			sb.append(" and aeopnionno like  ? ");
			param.add("%" + aeOpnionNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo)) {
			// String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			// if (StringUtils.isNotBlank(childrenNoStr)) {
			// sb.append(" and ").append(this.getOrInClause("aeedorgno in (%s)",
			// childrenNoStr));
			// }
			BsOrg loginOrg = bsOrgDao.get(orgNo);
			if (loginOrg != null) {
				// 如果当前金融机构的所属人民银行为成都分行，则认为该机构属于省级机构
				if (StringUtils.equals(loginOrg.getPcbno(), Constants.PCB_SC_ORG_NO)) {
					sb.append(" and aeedOrgBankType = ? ");
					if (StringUtils.equals(loginOrg.getF(), "3")) {
						// 登录机构为农村信用合作社（含联社）
						param.add("3");
					} else {
						param.add(loginOrg.getH());
					}
				} else {
					// sb.append(" and aeedorgno = ?  ");
					// param.add(orgNo);
					String[] childrens = getChildOrgByParentNo(orgNo, true);
					sb.append(" and aeedorgno in (" + StringUtil.join(childrens, "'", "'", ",") + ") ");
				}
			}
		}
		sb.append(" order by createdate desc");
		Page page = bsAeconclusionDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 人民银行查询行政执法相关信息
	 * 
	 * @param aeNo
	 *            检查工作记录编号
	 * @param aeOpnionNo
	 *            执法检查意见书编号
	 * @param aeorgName
	 *            检查单位
	 * @param aeedorgName
	 *            被检查单位
	 * @param aeplanstDate
	 *            开始日期
	 * @param aeplantmDate
	 *            结束日期
	 * @param pageNo
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAeconclusionByPcb(String aeNo, String aeOpnionNo, String aeorgName, String aeedorgName,
	        String aeplanstDate, String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		if (orgNo == null || StringUtils.isBlank(orgNo)) {
			return null;
		}
		StringBuffer sb = new StringBuffer("from BsAeconclusion where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeNo)) {
			sb.append(" and aeno like  ? ");
			param.add("%" + aeNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeOpnionNo)) {
			sb.append(" and aeopnionno like  ? ");
			param.add("%" + aeOpnionNo.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate >= ? ");
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate <= ? ");
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" and ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate desc");
		Page page = bsAeconclusionDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 判断执法检查结论编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean isUniqueAEConclusionNo(String admenForceNo) {
		List<BsAeconclusion> list = bsAeconclusionDao.find("from BsAeconclusion where aeopnionno = ? ", admenForceNo);
		if (CollectionUtils.isNotEmpty(list)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据ID删除执法检查结论
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteAeconclusionById(String id) {
		if (StringUtils.isNotBlank(id)) {
			bsAeconclusionDao.removeById(id);
		}
	}

	/**
	 * 查询执法检查结论详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeconclusion getAeconclusion(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		List<BsAeconclusion> list = bsAeconclusionDao.find("from BsAeconclusion where id = ? ", id);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAeconclusion) list.get(0);
		}
		return null;
	}

	/**
	 * 查询执法检查结论详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeconclusion getAeconclusionById(String id) {
		return bsAeconclusionDao.get(id);
	}

	/**
	 * 查询执法检查结论反馈详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeFeedBack getFeedBack(String aeno) {
		if (StringUtils.isBlank(aeno)) {
			return null;
		}
		List<BsAeFeedBack> list = bsAeFeedBackDao.find("from BsAeFeedBack where aeno = ? ", aeno);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAeFeedBack) list.get(0);
		}
		return null;
	}

	/**
	 * 查询行政处罚反馈详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAePublishFeedBack getPublishFeedBack(String aeno) {
		if (StringUtils.isBlank(aeno)) {
			return null;
		}
		List<BsAePublishFeedBack> list = bsAePublishFeedBackDao.find("from BsAePublishFeedBack where aeno = ? ", aeno);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAePublishFeedBack) list.get(0);
		}
		return null;
	}

	/**
	 * 查询行政处罚反馈详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public void refreshAeConclusionTopLevelOrgs() {
		List<BsAeconclusion> aeCons = bsAeconclusionDao.find("from BsAeconclusion ", null);
		for (BsAeconclusion bsAeconclusion : aeCons) {
			BsOrg aeedOrg = this.getOrgByNo(bsAeconclusion.getAeedorgno());
			BsOrg topLevelBank = this.getTopLevelOrgByNo(bsAeconclusion.getAeedorgno());
			bsAeconclusion.setDirectParentlBankNm(aeedOrg.getParentname());
			bsAeconclusion.setDirectParentlBankNo(aeedOrg.getParentno());
			bsAeconclusion.setTopLevelBankNm(topLevelBank.getName());
			bsAeconclusion.setTopLevelBankNo(topLevelBank.getNo());
			bsAeconclusionDao.save(bsAeconclusion);
		}
	}

	/**
	 * 查询执法检查结论详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeconclusion getAeconclusionByAeno(String aeno) {
		if (StringUtils.isBlank(aeno)) {
			return null;
		}
		List<BsAeconclusion> list = bsAeconclusionDao.find("from BsAeconclusion where aeno = ? ", aeno);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAeconclusion) list.get(0);
		}
		return null;
	}

	/**
	 * 保存跟踪整改记录
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveAerectification(BsAerectification ae) {
		this.validateAerectification(ae);
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		if (StringUtils.isNotBlank(ae.getAeedorgno()) && ae.getAeedorgno().trim().equals(Constants.PCB_SC_ORG_NO)) {
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
		bsAerectificationDao.save(ae);
	}

	/**
	 * 验证跟踪整改记录信息正确性.
	 * 
	 * @param ae
	 */
	private void validateAerectification(BsAerectification ae) {
		if (ae == null) {
			throw new IllegalArgumentException("跟踪整改记录对象为空");
		}
		if (ae.getTrackcontend() != null && ae.getTrackcontend().length() > 2300) {
			throw new IllegalArgumentException("整改情况超过允许长度400");
		}
	}

	/**
	 * 设置能否修改标识.<br>
	 * 
	 * 当完成整改记录新增后，对该整改对应的工作检查记录、工作检查结论、整改信息均设置为不能修改
	 * 
	 * @param aeinspNo
	 *            工作检查记录编号
	 */
	@Transactional(readOnly = false)
	public void refreshUpdateFlag(final String aeinspNo) {
		if (StringUtils.isBlank(aeinspNo)) {
			return;
		}
		BsAeinspection aeinsp = this.getAeinspectionByIno(aeinspNo);
		if (aeinsp != null) {
			aeinsp.setCanbeupdate(false);
			this.bsAeinspectionDao.save(aeinsp);
		}

		BsAeconclusion aecon = this.getAeconclusionByAeno(aeinspNo);
		if (aecon != null) {
			aecon.setCanbeupdate(false);
			this.bsAeconclusionDao.save(aecon);
		}

		BsAerectification aere = this.getAerectificationByIno(aeinspNo);
		if (aere != null) {
			aere.setCanbeupdate(false);
			this.bsAerectificationDao.save(aere);
		}
	}

	/**
	 * 执法整改查询.
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
	 *            页号
	 * @param pageSize
	 *            每页显示记录数
	 * @param orgNo
	 *            登录机构编号
	 * @return 分页后集合
	 */
	@Transactional(readOnly = true)
	public Page queryAerectification(String trackno, String aeorgName, String aeedorgName, String aeplanstDate,
	        String aeplantmDate, Integer pageNo, Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("from BsAerectification where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(trackno)) {
			sb.append(" and trackno like  ? ");
			param.add("%" + trackno.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeplanstDate)) {
			sb.append(" and createdate > ? ");
			// begin = begin + " 0:00:00";
			// param.add(Timestamp.valueOf(begin));
			param.add(StringUtil.convert(aeplanstDate, false));
		}
		if (StringUtils.isNotBlank(aeplantmDate)) {
			sb.append(" and createdate < ? ");
			// end = end + " 0:00:00";
			// param.add(Timestamp.valueOf(end));
			param.add(StringUtil.convert(aeplantmDate, true));
		}
		if (StringUtils.isNotBlank(aeorgName)) {
			sb.append(" and aeorgnm like ? ");
			param.add("%" + aeorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(aeedorgName)) {
			sb.append(" and aeedorgnm like ? ");
			param.add("%" + aeedorgName.trim() + "%");
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
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
		Page page = bsAerectificationDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;

	}

	/**
	 * 判断执法整改编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean isUniqueAeRectificationNo(String admenForceNo) {
		List<BsAerectification> list = bsAerectificationDao.find("from BsAerectification where trackno = ? ",
		        admenForceNo);
		if (CollectionUtils.isNotEmpty(list)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据ID删除执法整改
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteAerectificationById(String id) {
		if (StringUtils.isNotBlank(id)) {
			bsAerectificationDao.removeById(id);
		}
	}

	/**
	 * 查询执法整改详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAerectification getAerectification(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		List<BsAerectification> list = bsAerectificationDao.find("from BsAerectification where id = ? ", id);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAerectification) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据工作检查记录编号查询执法整改详情
	 * 
	 * @param aeno
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAerectification getAerectificationByIno(String aeno) {
		if (StringUtils.isBlank(aeno)) {
			return null;
		}
		List<BsAerectification> list = bsAerectificationDao.find("from BsAerectification where aeno = ? ", aeno);
		if (CollectionUtils.isNotEmpty(list)) {
			return (BsAerectification) list.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 查询执法整改详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAerectification getAerectificationById(String id) {
		return bsAerectificationDao.get(id);
	}

	/**
	 * 保存工作底稿
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveWorkBasis(BsWorkbasis ae) {
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		bsWorkbasisDao.save(ae);
	}

	/**
	 * 工作底稿查询
	 * 
	 * @param ae
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsWorkbasis> queryWorkBasis(String aeinspectionNo) {
		StringBuffer sb = new StringBuffer("from BsWorkbasis where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed7 = ? ");
			param.add(aeinspectionNo.trim());
		}
		sb.append(" order by createdate desc");
		List<BsWorkbasis> lt = bsWorkbasisDao.find(sb.toString(), param.toArray());
		return lt;
	}

	/**
	 * 删除工作底稿
	 * 
	 * @param ae
	 * @return
	 */
	@Transactional(readOnly = false)
	public void deleteWorkBasis(String id) {
		if (StringUtils.isBlank(id)) {
			return;
		}
		bsWorkbasisDao.removeById(id);
	}

	/**
	 * 分页工作底稿查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorkBasis(String aeinspectionNo, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("from BsWorkbasis where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed7 = ? ");
			param.add(aeinspectionNo.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorkbasisDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 分页工作底稿查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorkBasis(String aeinspectionNo, Integer pageNo, Integer pageSize, String inspectToken) {
		StringBuffer sb = new StringBuffer("from BsWorkbasis where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed7 = ? ");
			param.add(aeinspectionNo.trim());
		}
		if (StringUtils.isNotBlank(inspectToken)) {
			sb.append(" and stat = ? ");
			param.add(inspectToken.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorkbasisDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 查询工作底稿是否已经录入.
	 * 
	 * @param aeinspectionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean hasWorkBasis(String aeinspectionNo, String inspectToken) {
		if (StringUtils.isBlank(aeinspectionNo) || StringUtils.isBlank(inspectToken)) {
			return false;
		}
		List<BsWorkbasis> list = bsWorkbasisDao.find("from BsWorkbasis where filed7 = ? and stat = ?", aeinspectionNo,
		        inspectToken);
		return CollectionUtils.isNotEmpty(list);
	}

	/**
	 * 判断工作底稿编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean isUniqueWorkBasisNo(String no) {
		List<BsAerectification> list = bsWorkbasisDao.find("from BsAerectification where trackno = ? ", no);
		if (CollectionUtils.isNotEmpty(list)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据ID删除工作底稿
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteWorkBasisById(String id) {
		if (StringUtils.isNotBlank(id)) {
			bsWorkbasisDao.removeById(id);
		}
	}

	/**
	 * 查询工作底稿详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorkbasis getWorkBasisById(String id) {
		return bsWorkbasisDao.get(id);
	}

	/**
	 * 保存进场记录
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveWorkcoming(BsWorkcoming ae) {
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		bsWorkcomingDao.save(ae);
	}

	/**
	 * 保存进场记录，并更新进场编号
	 * 
	 * @param ae
	 * @param isNew
	 * @param orgNo
	 * @param year
	 */
	@Transactional(readOnly = false)
	public void saveWorkcoming(BsWorkcoming ae, Boolean isNew, String orgNo, String year) {
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		bsWorkcomingDao.save(ae);
		if (isNew) {
			noGenerator.updateNo(NoGenerator.INCOMING_NO_TYPE, orgNo, year);
		}
	}

	/**
	 * 分页进场记录查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorkcoming(String aeinspectionNo, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("from BsWorkcoming where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed7 = ? ");
			param.add(aeinspectionNo.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorkcomingDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 分页进场记录查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorkcoming(String aeinspectionNo, Integer pageNo, Integer pageSize, String inspectToken) {
		StringBuffer sb = new StringBuffer("from BsWorkcoming where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed7 = ? ");
			param.add(aeinspectionNo.trim());
		}
		if (StringUtils.isNotBlank(inspectToken)) {
			sb.append(" and stat = ? ");
			param.add(inspectToken.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorkcomingDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 查询进场记录是否已经录入.
	 * 
	 * @param aeinspectionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean hasWorkcoming(String aeinspectionNo, String inspectToken) {
		if (StringUtils.isBlank(aeinspectionNo) || StringUtils.isBlank(inspectToken)) {
			return false;
		}
		List<BsWorkcoming> list = bsWorkcomingDao.find("from BsWorkcoming where filed7 = ? and stat = ?",
		        aeinspectionNo, inspectToken);
		return CollectionUtils.isNotEmpty(list);
	}

	/**
	 * 分页查询执法统计情况
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageInspectionAnl(String aeinspectionNo, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("from BsAeinspectionAnl where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and Aeinspectionno = ? ");
			param.add(aeinspectionNo.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsAeinspectionAnlDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 分页查询执法统计情况
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageInspectionAnl(String aeinspectionNo, Integer pageNo, Integer pageSize, String inspectToken) {
		StringBuffer sb = new StringBuffer("from BsAeinspectionAnl where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and Aeinspectionno = ? ");
			param.add(aeinspectionNo.trim());
		}
		if (StringUtils.isNotBlank(inspectToken)) {
			sb.append(" and stat = ? ");
			param.add(inspectToken.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsAeinspectionAnlDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 查询检查工作记录统计情况是否已经录入.
	 * 
	 * @param aeinspectionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean hasInspectionAnl(String aeinspectionNo, String inspectToken) {
		if (StringUtils.isBlank(aeinspectionNo) || StringUtils.isBlank(inspectToken)) {
			return false;
		}
		List<BsAeinspectionAnl> list = bsAeinspectionAnlDao.find(
		        "from BsAeinspectionAnl where Aeinspectionno = ? and stat = ?", aeinspectionNo, inspectToken);
		return CollectionUtils.isNotEmpty(list);
	}

	/**
	 * 查询进场记录详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorkcoming getWorkcomingById(String id) {
		return bsWorkcomingDao.get(id);
	}

	/**
	 * 保存离场记录
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveWorkgoaway(BsWorkgoaway ae) {
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		bsWorkgoawayDao.save(ae);
	}

	/**
	 * 保存离场记录，并更新离场记录编号
	 * 
	 * @param ae
	 * @param isNew
	 * @param orgNo
	 * @param year
	 */
	@Transactional(readOnly = false)
	public void saveWorkgoaway(BsWorkgoaway ae, Boolean isNew, String orgNo, String year) {
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		bsWorkgoawayDao.save(ae);
		if (isNew) {
			noGenerator.updateNo(NoGenerator.GOAWAY_NO_TYPE, orgNo, String.valueOf(year));
		}
	}

	/**
	 * 保存执法情况统计
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveInspectionAnl(BsAeinspectionAnl ae) {
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		bsWorkgoawayDao.save(ae);
	}

	/**
	 * 分页离场记录查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorkgoaway(String aeinspectionNo, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("from BsWorkgoaway where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed14 = ? ");
			param.add(aeinspectionNo.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorkgoawayDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 分页离场记录查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorkgoaway(String aeinspectionNo, Integer pageNo, Integer pageSize, String inspectToken) {
		StringBuffer sb = new StringBuffer("from BsWorkgoaway where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed14 = ? ");
			param.add(aeinspectionNo.trim());
		}
		if (StringUtils.isNotBlank(inspectToken)) {
			sb.append(" and stat = ? ");
			param.add(inspectToken.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorkgoawayDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 查询离场记录是否已经录入.
	 * 
	 * @param aeinspectionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean hasWorkgoaway(String aeinspectionNo, String inspectToken) {
		if (StringUtils.isBlank(aeinspectionNo) || StringUtils.isBlank(inspectToken)) {
			return false;
		}
		List<BsWorkgoaway> list = bsWorkgoawayDao.find("from BsWorkgoaway where filed14 = ? and stat = ?",
		        aeinspectionNo, inspectToken);
		return CollectionUtils.isNotEmpty(list);
	}

	/**
	 * 查询离场记录详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorkgoaway getWorkgoawayById(String id) {
		return bsWorkgoawayDao.get(id);
	}

	/**
	 * 保存会谈纪要
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveWorktalksummary(BsWorktalksummary ae) {
		if (StringUtils.isBlank(ae.getId())) {
			ae.setCreatedate(new Date());
		} else {
			ae.setUpdateate(new Date());
		}
		bsWorktalksummaryDao.save(ae);
	}

	/**
	 * 分页会谈纪要查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorktalksummary(String aeinspectionNo, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("from BsWorktalksummary where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed16 = ? ");
			param.add(aeinspectionNo.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorktalksummaryDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 分页会谈纪要查询
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageWorktalksummary(String aeinspectionNo, Integer pageNo, Integer pageSize, String inspectToken) {
		StringBuffer sb = new StringBuffer("from BsWorktalksummary where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed16 = ? ");
			param.add(aeinspectionNo.trim());
		}
		if (StringUtils.isNotBlank(inspectToken)) {
			sb.append(" and stat = ? ");
			param.add(inspectToken.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsWorktalksummaryDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 查询会谈纪要是否已经录入.
	 * 
	 * @param aeinspectionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean hasWorktalksummary(String aeinspectionNo, String inspectToken) {
		if (StringUtils.isBlank(aeinspectionNo) || StringUtils.isBlank(inspectToken)) {
			return false;
		}
		List<BsWorktalksummary> list = bsWorktalksummaryDao.find(
		        "from BsWorktalksummary where filed16 = ? and stat = ?", aeinspectionNo, inspectToken);
		return CollectionUtils.isNotEmpty(list);
	}

	/**
	 * 查询会谈纪要详情
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorktalksummary getWorktalksummaryById(String id) {
		return bsWorktalksummaryDao.get(id);
	}

	/**
	 * 分页查询执法检查情况统计表
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageAeInspectionAnalyse(String a, String b, String stdate, String tmdate, Integer pageNo,
	        Integer pageSize, String orgNo) {
		StringBuffer sb = new StringBuffer("from BsAeinspectionAnl where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(a)) {
			sb.append(" and A1 like ? ");
			param.add("%" + a.trim() + "%");
		}
		if (StringUtils.isNotEmpty(b)) {
			sb.append(" and B1 like ? ");
			param.add("%" + b.trim() + "%");
		}
		if (StringUtils.isNotEmpty(stdate)) {
			sb.append(" and anlDate >= ? ");
			param.add(StringUtil.convert(stdate, false));
		}
		if (StringUtils.isNotEmpty(tmdate)) {
			sb.append(" and anlDate <= ? ");
			param.add(StringUtil.convert(tmdate, false));
		}
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" and ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate desc");
		Page page = bsAeinspectionAnlDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 根据父节点获取对应子节点（问题概况）
	 * 
	 * @param parent
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsDictionary> getProblemSummary(String parent) {
		String hql = "from BsDictionary where  isenable = 'true' and type = 'WTGK' and stat = ? ";
		return bsDictionaryDao.find(hql, new Object[] { parent });
	}

	/**
	 * 根据Id获取BsDictionary
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsDictionary getBsDictionary(String id) {
		return bsDictionaryDao.get(id);
	}

	/**
	 * 保存事实认定书对象
	 * 
	 * @param aeNo
	 * @param inspectionNo
	 * @param titleOrg
	 * @param aeOrg
	 * @param aeedOrg
	 * @param factBookContent
	 * @param filed6
	 * @param filed12
	 * @param filed9
	 * @param selectedwtgk
	 * @param aeorgno
	 * @param aeorgnm
	 * @param aeedorgno
	 * @param aeedorgnm
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveFactBook(String aeNo, String inspectionNo, String titleOrg, String aeOrg, String aeedOrg,
	        String factBookContent, String filed6, String filed12, String filed9, String selectedwtgk, String aeorgno,
	        String aeorgnm, String aeedorgno, String aeedorgnm, String inspectToken) {
		// BsFactbook fb = this.getFactBook(inspectionNo, inspectToken);
		BsFactbook fb = this.getFactBook(inspectionNo);
		Date date = new Date();
		if (fb == null) {
			fb = new BsFactbook();
			fb.setCreatedate(date);
			String yearStr = noGenerator.getYear();
			BsNogenerate bs = noGenerator.getNoGenerate(aeOrg, yearStr);
			if (bs == null) {
				fb.setFiled7("未明确编号" + UUID.randomUUID().toString().replace("-", StringUtils.EMPTY));
			} else {
				String factBookNo = noGenerator.generateNoStr(bs.getFactnotext(), bs.getFactnoyear(),
				        String.valueOf(bs.getFactnoindex()));
				fb.setFiled7(factBookNo);
				noGenerator.updateNo(NoGenerator.FACTBOOK_NO_TYPE, aeOrg, yearStr);
			}
		}
		fb.setUpdateate(date);
		fb.setAeno(aeNo);
		fb.setFiled5(factBookContent);
		fb.setFiled6(filed6);
		fb.setFiled9(filed9);
		fb.setFiled8(aeedOrg);
		fb.setFiled10(titleOrg);
		fb.setFiled11(inspectionNo);
		fb.setFiled12(filed12);
		fb.setSelectedwtgk(selectedwtgk);
		if (StringUtils.isNotEmpty(aeorgno)) {
			fb.setAeorgno(aeorgno);
		}
		if (StringUtils.isNotEmpty(aeorgnm)) {
			fb.setAeorgnm(aeorgnm);
		}
		if (StringUtils.isNotEmpty(aeedorgno)) {
			fb.setAeedorgno(aeedorgno);
			/*
			 * 同时查询该用户所属机构的机构类型以及机构所属人民银行 供金融机构查询时使用
			 */
			BsOrg aeedorg = bsOrgDao.get(aeedorgno);
			if (aeedorg != null) {
				fb.setOrgtpno(aeedorg.getH());
				fb.setOrgtpnm(aeedorg.getI());
				fb.setPcbno(aeedorg.getPcbno());
				fb.setPcbnm(aeedorg.getPcbname());
			}
		}
		if (StringUtils.isNotEmpty(aeedorgnm)) {
			fb.setAeedorgnm(aeedorgnm);
		}
		if (StringUtils.isNotEmpty(inspectToken)) {
			fb.setStat(inspectToken);
		}
		this.bsFactbookDao.save(fb);
		return fb.getFiled7();
	}

	/**
	 * 分页查询事实认定书.
	 * 
	 * @param aeinspectionNo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageFactBook(String aeinspectionNo, Integer pageNo, Integer pageSize, String inspectToken) {
		StringBuffer sb = new StringBuffer("from BsFactbook where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(aeinspectionNo)) {
			sb.append(" and filed11 = ? ");
			param.add(aeinspectionNo.trim());
		}
		if (StringUtils.isNotBlank(inspectToken)) {
			sb.append(" and stat = ? ");
			param.add(inspectToken.trim());
		}
		sb.append(" order by createdate desc");
		Page page = bsFactbookDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 根据检查记录编号获取事实认定书对象
	 * 
	 * @param inspctionNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsFactbook getFactBook(String inspctionNo) {
		List<BsFactbook> fbs = this.bsFactbookDao.find("from BsFactbook where Filed11 = ? order by Updateate",
		        inspctionNo);
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取事实认定书对象
	 * 
	 * @param inspctionNo
	 * @param inspect_token
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsFactbook getFactBook(String inspctionNo, String inspectToken) {
		List<BsFactbook> fbs = null;
		if (StringUtils.isNotBlank(inspectToken)) {
			fbs = this.bsFactbookDao.find("from BsFactbook where Filed11 = ? and Stat = ? order by Updateate",
			        inspctionNo, inspectToken);
		} else {
			fbs = this.bsFactbookDao.find("from BsFactbook where Filed11 = ? order by Updateate", inspctionNo);
		}
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 查询事实认定书是否已经录入.
	 * 
	 * @param aeinspectionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean hasFactBook(String aeinspectionNo, String inspectToken) {
		if (StringUtils.isBlank(aeinspectionNo) || StringUtils.isBlank(inspectToken)) {
			return false;
		}
		List<BsFactbook> list = bsFactbookDao.find("from BsFactbook where Filed11 = ? and stat = ?", aeinspectionNo,
		        inspectToken);
		return CollectionUtils.isNotEmpty(list);
	}

	/**
	 * 根据检查记录编号获取工作底稿
	 * 
	 * @param aeNo
	 * @param inspectionNo
	 * @param titleOrg
	 * @param factBookNo
	 * @param aeedOrg
	 * @param factBookContent
	 */
	@Transactional(readOnly = true)
	public BsWorkbasis getWorkbasis(String gzdgId) {
		if (StringUtils.isNotBlank(gzdgId)) {
			BsWorkbasis fbs = this.bsWorkbasisDao.get(gzdgId);
			if (fbs != null) {
				return fbs;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取进场纪录.
	 * 
	 * @param inspctionNo
	 *            工作检查记录编号
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorkcoming getWorkcoming(String inspctionNo) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsWorkcoming> fbs = this.bsWorkcomingDao.find("from BsWorkcoming where Filed7 = ? order by Updateate",
		        inspctionNo);
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取进场纪录
	 * 
	 * @param inspctionNo
	 * @param inspect_token
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorkcoming getWorkcoming(String inspctionNo, String inspectToken) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsWorkcoming> fbs = null;
		if (StringUtils.isNotBlank(inspectToken)) {
			fbs = this.bsWorkcomingDao.find("from BsWorkcoming where Filed7 = ? and stat = ? order by Updateate",
			        inspctionNo, inspectToken);
		} else {
			fbs = this.bsWorkcomingDao.find("from BsWorkcoming where Filed7 = ? order by Updateate", inspctionNo);
		}
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取离场纪录
	 * 
	 * @param inspctionNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorkgoaway getWorkgoaway(String inspctionNo) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsWorkgoaway> fbs = this.bsWorkgoawayDao.find("from BsWorkgoaway where Filed14 = ? order by Updateate",
		        inspctionNo);
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取离场纪录
	 * 
	 * @param inspctionNo
	 * @param inspect_token
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorkgoaway getWorkgoaway(String inspctionNo, String inspectToken) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsWorkgoaway> fbs = null;
		if (StringUtils.isNotBlank(inspectToken)) {
			fbs = this.bsWorkgoawayDao.find("from BsWorkgoaway where Filed14 = ? and stat = ? order by Updateate",
			        inspctionNo, inspectToken);
		} else {
			fbs = this.bsWorkgoawayDao.find("from BsWorkgoaway where Filed14 = ? order by Updateate", inspctionNo);
		}
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取工作检查记录统计信息.
	 * 
	 * @param inspctionNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeinspectionAnl getInspectionAnl(String inspctionNo) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsAeinspectionAnl> fbs = this.bsAeinspectionAnlDao.find(
		        "from BsAeinspectionAnl where aeinspectionno = ? order by Updateate", inspctionNo);
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取工作检查记录统计信息.
	 * 
	 * @param inspctionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAeinspectionAnl getInspectionAnl(String inspctionNo, String inspectToken) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsAeinspectionAnl> fbs = null;
		if (StringUtils.isNotBlank(inspectToken)) {
			fbs = this.bsAeinspectionAnlDao.find(
			        "from BsAeinspectionAnl where aeinspectionno = ? and stat = ? order by Updateate", inspctionNo,
			        inspectToken);
		} else {
			fbs = this.bsAeinspectionAnlDao.find("from BsAeinspectionAnl where aeinspectionno = ? order by Updateate",
			        inspctionNo);
		}
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param inspctionNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorktalksummary getWorktalksummary(String inspctionNo) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsWorktalksummary> fbs = this.bsWorktalksummaryDao.find(
		        "from BsWorktalksummary where Filed16 = ? order by Updateate", inspctionNo);
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查记录编号获取会谈纪要.
	 * 
	 * @param inspctionNo
	 * @param inspectToken
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsWorktalksummary getWorktalksummary(String inspctionNo, String inspectToken) {
		if (StringUtils.isBlank(inspctionNo)) {
			return null;
		}
		List<BsWorktalksummary> fbs = null;
		if (StringUtils.isNotBlank(inspectToken)) {
			fbs = this.bsWorkcomingDao.find("from BsWorktalksummary where Filed16 = ? and stat = ? order by Updateate",
			        inspctionNo, inspectToken);
		} else {
			fbs = this.bsWorktalksummaryDao.find("from BsWorktalksummary where Filed16 = ? order by Updateate",
			        inspctionNo);
		}
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据检查立项编号获取执法检查立项信息.
	 * 
	 * @param aeNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsAdmenforce getAdminEnforce(String aeNo) {
		if (StringUtils.isBlank(aeNo)) {
			return null;
		}
		List<BsAdmenforce> fbs = this.bsAdmenforceDao
		        .find("from BsAdmenforce where Aeno = ? order by Updatedate", aeNo);
		if (CollectionUtils.isNotEmpty(fbs)) {
			return fbs.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 删除当前存在但并不与任何工作检查记录相关联的信息.<br>
	 * 
	 * 包括进场、离场、事实认定书、工作底稿、执法检查情况统计<br>
	 * 此类数据由可能由于工作检查记录编号重复或者用户放弃保存产生
	 */
	@Transactional(readOnly = false)
	public void clearUselessAeinspRelative() {
		StringBuilder sb = new StringBuilder("删除无用的工作检查记录附属信息：");
		try {
			int num1 = this.clearUselessAeinspObject("BS_WORKBASIS");
			sb.append("工作底稿-").append(num1).append("条，");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("工作底稿删除失败，");
		}
		try {
			int num2 = this.clearUselessAeinspObject("BS_WORKCOMING");
			sb.append("进场记录-").append(num2).append("条，");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("进场记录删除失败，");
		}
		try {
			int num3 = this.clearUselessAeinspObject("BS_WORKGOAWAY");
			sb.append("离场记录-").append(num3).append("条，");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("离场记录删除失败，");
		}
		try {
			int num4 = this.clearUselessAeinspObject("BS_WORKTALKSUMMARY");
			sb.append("会谈纪要-").append(num4).append("条，");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("会谈纪要删除失败，");
		}
		try {
			int num5 = this.clearUselessAeinspObject("BS_FACTBOOK");
			sb.append("事实认定书-").append(num5).append("条，");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("事实认定书删除失败，");
		}
		try {
			int num6 = this.clearUselessAeinspObject("BS_AEINSPECTION_ANL");
			sb.append("执法情况统计-").append(num6).append("条，");
		} catch (Exception e) {
			e.printStackTrace();
			sb.append("执法统计情况删除失败，");
		}
		try {
			remindService.writeLog(sb.toString(), StringUtils.EMPTY, StringUtils.EMPTY, "HTTP", "系统自动");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除当前存在但并不与任何工作检查记录相关联的具体一类信息.
	 * 
	 * @param ObjectTableName
	 *            需要删除的信息表名
	 * @return 成功删除的记录数
	 */
	@Transactional(readOnly = false)
	public int clearUselessAeinspObject(String objectTableName) {
		String selectSql = "SELECT f.id AS id FROM " + objectTableName
		        + " f LEFT JOIN BS_AEINSPECTION aein ON f.stat = aein.stat WHERE aein.id IS NULL";
		List selectIdslt = jdbcTemplate.queryForList(selectSql);
		List<String> deleteIdslt = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(selectIdslt)) {
			for (Iterator iterator = selectIdslt.iterator(); iterator.hasNext();) {
				Map object = (Map) iterator.next();
				deleteIdslt.add(object.get("id").toString());
			}
			String[] factBookDeleteIdsArr = new String[deleteIdslt.size()];
			factBookDeleteIdsArr = deleteIdslt.toArray(factBookDeleteIdsArr);
			jdbcTemplate.execute("DELETE FROM " + objectTableName + " WHERE id IN("
			        + StringUtil.join(factBookDeleteIdsArr, "'", "'", ",") + ")");
		}
		if (selectIdslt != null) {
			return selectIdslt.size();
		} else {
			return 0;
		}
	}

	/**
	 * 分页查询系统参数表
	 * 
	 * @param type
	 * @param dkey
	 * @param dvalue
	 * @param dsumry
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageDctionarys(String type, String dkey, String dvalue, String dsumry, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsDictionary where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(type)) {
			sb.append(" and type = ? ");
			param.add(type.trim());
		}
		if (StringUtils.isNotEmpty(dkey)) {
			sb.append(" and dkey = ? ");
			param.add(dkey.trim());
		}
		if (StringUtils.isNotEmpty(dvalue)) {
			sb.append(" and dvalue like ? ");
			param.add("%" + dvalue.trim() + "%");
		}
		if (StringUtils.isNotEmpty(dsumry)) {
			sb.append(" and dsumry like ? ");
			param.add("%" + dsumry.trim() + "%");
		}

		sb.append(" order by type,createdate DESC");
		Page page = bsDictionaryDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 保存系统参数
	 * 
	 * @param id
	 * @param type
	 * @param dkey
	 * @param dvalue
	 * @param dsumry
	 * @param stat
	 * @param flag
	 */
	@Transactional(readOnly = false)
	public void saveDctionary(String id, String type, String dkey, String dvalue, String dsumry, String stat,
	        String flag, String isenable) throws Exception {
		BsDictionary dc = null;
		Date date = new Date();
		if (StringUtils.isBlank(id)) {
			List<BsDictionary> dcs = bsDictionaryDao.find("from BsDictionary where dkey = ? ", new Object[] { dkey });
			if (dcs != null && dcs.size() > 0) {
				throw new Exception("系统中已经存在编号为" + dkey + "的参数，参数编号不能重复");
			}
			dc = new BsDictionary();
			dc.setCreatedate(date);
		} else {
			dc = bsDictionaryDao.get(id);
			if (dc == null) {
				dc = new BsDictionary();
				dc.setCreatedate(date);
			}
		}
		dc.setUpdateate(date);
		if (StringUtils.isNotEmpty(type)) {
			dc.setType(type);
		}
		if (StringUtils.isBlank(id)) {
			String sql1 = "SELECT nextval for bs_dictionary_seq from SYSIBM.SYSDUMMY1";
			int bsdicKey = jdbcTemplate.queryForInt(sql1);
			dc.setDkey(String.valueOf(bsdicKey));
		}
		// else {
		// if (StringUtils.isNotEmpty(dkey)) {
		// dc.setDkey(dkey);
		// }
		// }
		if (StringUtils.isNotEmpty(dvalue)) {
			dc.setDvalue(dvalue);
		}
		if (dsumry != null) {
			dc.setDSumry(dsumry);
		}
		if (stat != null) {
			dc.setStat(stat);
		}
		if (StringUtils.isNotEmpty(flag)) {
			dc.setFlag(flag);
		}
		if (StringUtils.isNotEmpty(isenable)) {
			dc.setIsenable(isenable);
		}
		this.bsDictionaryDao.save(dc);
	}

	/**
	 * 获取问题概要中可以作为父节点的数据项.
	 */
	@Transactional(readOnly = true)
	public String getParentDataStr() {
		// [['false', '否'], ['true', '是']]
		StringBuffer sb = new StringBuffer("[['', '请选择']");
		List<BsDictionary> dcs = bsDictionaryDao.find("from BsDictionary where flag = 'false' and stat='000011' ");
		if (dcs != null && dcs.size() > 0) {
			for (Iterator<BsDictionary> iterator = dcs.iterator(); iterator.hasNext();) {
				BsDictionary bsDictionary = iterator.next();
				if (bsDictionary != null) {
					sb.append(",['").append(bsDictionary.getId()).append("','").append(bsDictionary.getDvalue())
					        .append("']");
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 删除系统参数
	 * 
	 * @param id
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void deleteDctionary(String id) throws Exception {
		bsDictionaryDao.removeById(id);
	}

	@Transactional(readOnly = false)
	public void evict(Object bs) {
		bsAerectificationDao.evict(bs);
	}

	@Transactional(readOnly = true)
	public BsOrg getOrgByNo(Serializable no) {
		return bsOrgDao.get(no);
	}

	/**
	 * 根据银行类型获取该银行的最上级机构（一般为省级机构）.
	 * 
	 * @param orgType
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsOrg getTopLevelOrg(String orgType) {
		List<BsOrg> orgs = bsOrgDao.find("from BsOrg where h = ? and c = ?", orgType, Constants.ZONG_BU);
		if (CollectionUtils.isEmpty(orgs)) {
			List<BsOrg> orgs2 = bsOrgDao.find("from BsOrg where h = ? and pcbno = ?", orgType, Constants.PCB_SC_ORG_NO);
			if (CollectionUtils.isEmpty(orgs2)) {
				return null;
			} else {
				return orgs2.get(0);
			}
		} else {
			return orgs.get(0);
		}
	}

	/**
	 * 根据银行No获取该银行的最上级机构（一般为省级机构）.
	 * 
	 * @param orgType
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsOrg getTopLevelOrgByNo(String orgNo) {
		BsOrg org = bsOrgDao.get(orgNo);
		if (org != null) {
			BsOrg parent = bsOrgDao.get(org.getParentno());
			if (parent.getParentno().equals("0")) {
				return org;
			} else {
				return getTopLevelOrgByNo(parent.getNo());
			}
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<BsOrg> orgListByParentNo(String parentNo) {
		try {
			StringBuffer areaHql = new StringBuffer();
			areaHql.append(" from BsOrg where parentno = '" + parentNo + "' order by no asc ");
			List<BsOrg> list = bsOrgDao.find(areaHql.toString());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List<BsOrg> orgListByParentNoForZH(String parentNo, String pcbNo) {
		try {
			String child = super.getChildOrgByParentNoStr(pcbNo);
			if (StringUtils.isBlank(child)) {
				child = "''";
			}
			StringBuffer areaHql = new StringBuffer();
			areaHql.append(" from BsOrg where parentno = '" + parentNo + "' and "
			        + this.getOrInClause("pcbno in (%s)", child) + " order by no asc ");
			List<BsOrg> list = bsOrgDao.find(areaHql.toString());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 行政执法导出cvs查询数据.
	 * 
	 * @param objectType
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<?> getExportData(int objectType, String orgNo) {
		switch (objectType) {
		case 1:
			return this.queryAllAdmenforce(orgNo);
		case 2:
			return this.queryAllAeinspection(orgNo);
		case 3:
			return this.queryAllBsAeconclusion(orgNo);
		case 4:
			return this.queryAllBsAerectification(orgNo);
		case 5:
			return this.queryAllBsAdmpunish(orgNo);
		case 6:
			return this.queryAllBsAdmpunishcons(orgNo);
		default:
			return null;
		}

	}

	/**
	 * 行政执法立项查询.<br>
	 * 
	 * 不分页
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<?> queryAllAdmenforce(String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAdmenforce ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" where ").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
			}
		}
		sb.append(" order by createdate DESC");
		return bsAdmenforceDao.find(sb.toString(), param.toArray());
	}

	/**
	 * 检查工作记录查询.<br>
	 * 
	 * 不分页
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<?> queryAllAeinspection(String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAeinspection ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" where (").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
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
		sb.append(" order by createdate DESC");
		return bsAeinspectionDao.find(sb.toString(), param.toArray());
	}

	/**
	 * 工作检查结论查询.<br>
	 * 
	 * 不分页
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<?> queryAllBsAeconclusion(String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAeconclusion ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" where (").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
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
		return bsAeconclusionDao.find(sb.toString(), param.toArray());
	}

	/**
	 * 整改情况查询.<br>
	 * 
	 * 不分页
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<?> queryAllBsAerectification(String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAerectification ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" where (").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
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
		return bsAerectificationDao.find(sb.toString(), param.toArray());
	}

	/**
	 * 行政处罚立项查询.<br>
	 * 
	 * 不分页
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<?> queryAllBsAdmpunish(String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAdmpunish ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = this.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" where (").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
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
		return bsAdmpunishDao.find(sb.toString(), param.toArray());
	}

	/**
	 * 行政处罚结论查询.<br>
	 * 
	 * 不分页
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<?> queryAllBsAdmpunishcons(String orgNo) {
		StringBuffer sb = new StringBuffer("FROM BsAdmpunishcons ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(orgNo) && !orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = this.getChildOrgByParentNoStr(orgNo);
			if (StringUtils.isNotBlank(childrenNoStr)) {
				sb.append(" where (").append(this.getOrInClause("aeorgno in (%s)", childrenNoStr));
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
		return bsAdmpunishconsDao.find(sb.toString(), param.toArray());
	}

	/**
	 * 根据数据生成cvs文件.
	 * 
	 * @param os
	 * @param datas
	 * @throws Exception
	 */
	public void generateCvs(OutputStream os, List<?> datas) throws Exception {
		CsvWriter wr = new CsvWriter(os, ',', Charset.forName("GBK"));

		for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof BsAdmenforce) {
				BsAdmenforce object = (BsAdmenforce) obj;
				wr.writeRecord(new String[] { object.getAeno(), object.getAecontent() });
				continue;
			}
			if (obj instanceof BsAeinspection) {
				BsAeinspection object = (BsAeinspection) obj;
				wr.writeRecord(new String[] { object.getAeno(), object.getAeedorgnm() });
				continue;
			}
			if (obj instanceof BsAeconclusion) {
				BsAeconclusion object = (BsAeconclusion) obj;
				wr.writeRecord(new String[] { object.getAeno(), object.getAeedorgnm() });
				continue;
			}
			if (obj instanceof BsAerectification) {
				BsAerectification object = (BsAerectification) obj;
				wr.writeRecord(new String[] { object.getAeno(), object.getAeedorgnm() });
				continue;
			}
			if (obj instanceof BsAdmpunish) {
				BsAdmpunish object = (BsAdmpunish) obj;
				wr.writeRecord(new String[] { object.getAeno(), object.getAeedorgnm() });
				continue;
			}
			if (obj instanceof BsAdmpunishcons) {
				BsAdmpunishcons object = (BsAdmpunishcons) obj;
				wr.writeRecord(new String[] { object.getAeno(), object.getAeedorgnm() });
				continue;
			}
		}

		wr.close();
	}

	/**
	 * 保存检查结论反馈.
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void saveFeedBack(BsAeFeedBack fb) {
		if (StringUtils.isBlank(fb.getId())) {
			fb.setCreatedate(new Date());
		} else {
			fb.setUpdateate(new Date());
		}
		bsAeFeedBackDao.save(fb);
	}

	/**
	 * 保存行政处罚反馈.
	 * 
	 * @param ae
	 */
	@Transactional(readOnly = false)
	public void savePublishFeedBack(BsAePublishFeedBack pfb) {
		if (StringUtils.isBlank(pfb.getId())) {
			pfb.setCreatedate(new Date());
		} else {
			pfb.setUpdateate(new Date());
		}
		bsAePublishFeedBackDao.save(pfb);
	}

	/**
	 * 根据数据生成xls文件.
	 * 
	 * @param os
	 * @param datas
	 * @throws Exception
	 */
	public void generateXls(OutputStream os, List<?> datas) throws Exception {
		// TODO
	}

	/**
	 * 筛选检查组其他成员.
	 * 
	 * @param isJoinWithin2Years
	 * @param aeLevel
	 * @param joinTime
	 * @param peopleQuantity
	 * @return
	 */
	public List<BsAepeople> siftAepeople(boolean isJoinWithin2Years, String aeLevel, Integer minJoinTime,
	        Integer maxJoinTime, Integer peopleQuantity) {

		String selectByIsJoinWithin2Years = "SELECT DISTINCT his.certno FROM BS_AEPEOPLEJOINHISTORY AS his WHERE timestampdiff(16,CHAR(CURRENT TIMESTAMP - his.AEENFORCECREATEDATE)) <= 365 * 2";
		String selectByAeLevel = "SELECT certno FROM BS_AEPEOPLE WHERE sex = ? ";
		String selectByJoinTime = "SELECT CERTNO FROM BS_AEPEOPLEJOINHISTORY AS his GROUP BY his.CERTNO HAVING COUNT(his.ID) > ? AND COUNT(his.ID) < ?";

		List<String> retainList = new ArrayList<String>();
		List<String> resultByIsJoinWithin2Years = null, resultByAeLevel = null, resultByJoinTime = null;

		if (isJoinWithin2Years) {
			resultByIsJoinWithin2Years = jdbcTemplate.queryForList(selectByIsJoinWithin2Years, String.class);
			if (resultByIsJoinWithin2Years.size() <= 0) {
				return null;
			} else {
				if (CollectionUtils.isEmpty(retainList)) {
					retainList.addAll(resultByIsJoinWithin2Years);
				} else {
					retainList.retainAll(resultByIsJoinWithin2Years);
				}
			}
		}

		if (minJoinTime != null && maxJoinTime != null && minJoinTime.compareTo(maxJoinTime) <= 0) {
			resultByJoinTime = jdbcTemplate.queryForList(selectByJoinTime, new Object[] { minJoinTime, maxJoinTime },
			        String.class);
			if (resultByJoinTime.size() <= 0) {
				return null;
			} else {
				if (CollectionUtils.isEmpty(retainList)) {
					retainList.addAll(resultByJoinTime);
				} else {
					retainList.retainAll(resultByJoinTime);
				}
			}
		}

		if (aeLevel != null) {
			resultByAeLevel = jdbcTemplate.queryForList(selectByAeLevel, new Object[] { "男" }
			// aeLevel
			        , String.class);
			if (resultByAeLevel.size() <= 0) {
				return null;
			} else {
				if (CollectionUtils.isEmpty(retainList)) {
					retainList.addAll(resultByAeLevel);
				} else {
					retainList.retainAll(resultByAeLevel);
				}
			}
		}

		if (CollectionUtils.isEmpty(retainList)) {
			return null;
		}

		List<BsAepeople> result = bsAepeopleDao.find("from BsAepeople where certNo in ("
		        + StringUtil.join(retainList.toArray(), "'", "'", ",") + ")");

		if (CollectionUtils.isEmpty(result)) {
			return null;
		}

		if (result.size() <= peopleQuantity) {
			return result;
		}

		while (result.size() > peopleQuantity) {
			result.remove(RandomUtils.nextInt(result.size()));
		}

		return result;
	}

	private static final String[] RANDOM_ORDER_FIELDS_NAME = { "NO", "AA", "AB", "AC", "AH" };
	private static final String[] RANDOM_ORDER_ASC_NAME = { "ASC", "DESC" };

	/**
	 * 筛选被检查机构.
	 * 
	 * @param isAeedOrgWithin2Years
	 * @param adjustResult
	 * @param minAeedTime
	 * @param maxAeedTime
	 * @param orgType
	 * @param peopleQuantity
	 * @return
	 */
	public List<BsOrg> siftAeedOrg(boolean hasNotBeCheckWithin2Years, String adjustResult, Integer minAeedTime,
	        Integer maxAeedTime, String orgType, Integer peopleQuantity) {

		String selectByHasBeCheckWithin2Years = "SELECT DISTINCT his.AEORGNO FROM BS_AEEDORGJOINHISTORY AS his WHERE timestampdiff(16,CHAR(CURRENT TIMESTAMP - his.AEENFORCECREATEDATE)) <= 365 * 2";
		String selectByAeedTime = "SELECT AEEDORGNO FROM BS_AEEDORGJOINHISTORY AS his GROUP BY his.AEEDORGNO HAVING COUNT(his.ID) > ? AND COUNT(his.ID) < ?";

		List<String> resultByIsJoinWithin2Years = null, resultByAeedTime = null;

		String expectOrgs = null;
		if (hasNotBeCheckWithin2Years) {
			resultByIsJoinWithin2Years = jdbcTemplate.queryForList(selectByHasBeCheckWithin2Years, String.class);
			if (resultByIsJoinWithin2Years.size() > 0) {
				expectOrgs = StringUtil.join(resultByIsJoinWithin2Years.toArray(), "'", "'", ",");
			}
		}

		String hasBeCheckOrgWithSpecialTime = null;
		if (minAeedTime != null && maxAeedTime != null && minAeedTime.compareTo(maxAeedTime) <= 0) {
			resultByAeedTime = jdbcTemplate.queryForList(selectByAeedTime, new Object[] { minAeedTime, maxAeedTime },
			        String.class);
			if (resultByAeedTime.size() <= 0) {
				return null;
			} else {
				hasBeCheckOrgWithSpecialTime = StringUtil.join(resultByAeedTime.toArray(), "'", "'", ",");
			}
		}

		StringBuffer selectOrg = new StringBuffer("from BsOrg where ispcb = 'NO' ");
		if (StringUtils.isNotEmpty(orgType)) {
			selectOrg.append("And 1=1 ");
		}
		if (StringUtils.isNotEmpty(adjustResult)) {
			selectOrg.append("And 1=1 ");
		}

		if (StringUtils.isNotEmpty(hasBeCheckOrgWithSpecialTime)) {
			selectOrg.append("And no in (" + hasBeCheckOrgWithSpecialTime + ") ");
		}

		if (StringUtils.isNotEmpty(expectOrgs)) {
			selectOrg.append("And no Not in (" + expectOrgs + ") ");
		}

		selectOrg.append(" order by " + RANDOM_ORDER_FIELDS_NAME[RandomUtils.nextInt(RANDOM_ORDER_FIELDS_NAME.length)]
		        + " " + RANDOM_ORDER_ASC_NAME[RandomUtils.nextInt(RANDOM_ORDER_ASC_NAME.length)]);

		int size = 10;
		if (peopleQuantity != null) {
			size = peopleQuantity.intValue();
		}

		List<BsOrg> result = (List<BsOrg>) bsAepeopleDao.pagedQuery(selectOrg.toString(), 1, size).getResult();

		return result;
	}

	public void setBsAdmenforceDao(BsAdmenforceDAO bsAdmenforceDao) {
		this.bsAdmenforceDao = bsAdmenforceDao;
	}

	public void setBsAeconclusionDao(BsAeconclusionDAO bsAeconclusionDao) {
		this.bsAeconclusionDao = bsAeconclusionDao;
	}

	public void setBsAeinspectionDao(BsAeinspectionDAO bsAeinspectionDao) {
		this.bsAeinspectionDao = bsAeinspectionDao;
	}

	public void setBsAerectificationDao(BsAerectificationDAO bsAerectificationDao) {
		this.bsAerectificationDao = bsAerectificationDao;
	}

	public void setBsWorkbasisDao(BsWorkbasisDAO bsWorkbasisDao) {
		this.bsWorkbasisDao = bsWorkbasisDao;
	}

	public void setBsWorkcomingDao(BsWorkcomingDAO bsWorkcomingDao) {
		this.bsWorkcomingDao = bsWorkcomingDao;
	}

	public void setBsWorkgoawayDao(BsWorkgoawayDAO bsWorkgoawayDao) {
		this.bsWorkgoawayDao = bsWorkgoawayDao;
	}

	public void setBsWorktalksummaryDao(BsWorktalksummaryDAO bsWorktalksummaryDao) {
		this.bsWorktalksummaryDao = bsWorktalksummaryDao;
	}

	public void setBsAeinspectionAnlDao(BsAeinspectionAnlDAO bsAeinspectionAnlDao) {
		this.bsAeinspectionAnlDao = bsAeinspectionAnlDao;
	}

	public void setBsFactbookDao(BsFactbookDAO bsFactbookDao) {
		this.bsFactbookDao = bsFactbookDao;
	}

	public void setNoGenerator(NoGenerator noGenerator) {
		this.noGenerator = noGenerator;
	}

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setRemindService(RemindService remindService) {
		this.remindService = remindService;
	}

	public void setBsAdmpunishDao(BsAdmpunishDAO bsAdmpunishDao) {
		this.bsAdmpunishDao = bsAdmpunishDao;
	}

	public void setBsAdmpunishconsDao(BsAdmpunishconsDAO bsAdmpunishconsDao) {
		this.bsAdmpunishconsDao = bsAdmpunishconsDao;
	}

	public void setBsAeFeedBackDao(BsAeFeedBackDAO bsAeFeedBackDao) {
		this.bsAeFeedBackDao = bsAeFeedBackDao;
	}

	public void setBsAePublishFeedBackDao(BsAePublishFeedBackDAO bsAePublishFeedBackDao) {
		this.bsAePublishFeedBackDao = bsAePublishFeedBackDao;
	}

	public void setBsAePeopleJoinHistoryDao(BsAePeopleJoinHistoryDAO bsAePeopleJoinHistoryDao) {
		this.bsAePeopleJoinHistoryDao = bsAePeopleJoinHistoryDao;
	}

	public void setBsAeedOrgJoinHistoryDao(BsAeedOrgJoinHistoryDAO bsAeedOrgJoinHistoryDao) {
		BsAeedOrgJoinHistoryDao = bsAeedOrgJoinHistoryDao;
	}

}
