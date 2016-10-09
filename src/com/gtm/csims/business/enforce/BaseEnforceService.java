package com.gtm.csims.business.enforce;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsAepeopleDAO;
import com.gtm.csims.dao.BsDictionaryDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.dao.BsOrgRelevantInfoDAO;
import com.gtm.csims.dao.BsUserInfoOfJgDAO;
import com.gtm.csims.dao.BsUserRelevantInfoDAO;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.model.BsDictionary;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsOrgRelevantInfo;
import com.gtm.csims.model.BsUserInfoOfJg;
import com.gtm.csims.model.BsUserRelevantInfo;
import com.gtm.csims.utils.Constants;
import com.gtm.csims.utils.StringUtil;

/**
 * 行政执法业务基类
 * 
 * @author Sweet 
 * @version 1.5
 * @see java.lang.Class
 * @since JDK1.0
 */
@SuppressWarnings("unchecked")
public class BaseEnforceService {
	/**
	 * IN语句条件分隔符.
	 */
	private static final String SEPARATOR = ",";

	/**
	 * IN语句选择值最大数量.
	 */
	private static final int MAX_IN_CLUASE_NUMBER = 200;

	protected BsUserInfoOfJgDAO bsUserInfoOfJgDao;
	protected BsOrgDAO bsOrgDao;
	protected BsAepeopleDAO bsAepeopleDao;
	protected JdbcTemplate jdbcTemplate;
	protected BsDictionaryDAO bsDictionaryDao;
	protected BsUserRelevantInfoDAO bsUserRelevantInfoDao;
	protected BsOrgRelevantInfoDAO bsOrgRelevantInfoDao;

	/**
	 * 根据机构编号获取用户信息
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsUserInfoOfJg> getUsersByOrgNo(String orgNo) {
		List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find("from BsUserInfoOfJg where OrgCodeOfJr = ?", orgNo);
		return list;
	}

	/**
	 * 根据多个机构编号获取用户信息
	 * 
	 * @param orgNos
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsUserInfoOfJg> getUsersByOrgNos(String orgNos) {
		List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find("from BsUserInfoOfJg where OrgCodeOfJr in (" + orgNos + ")",
		        new Object[] {});
		return list;
	}

	/**
	 * 根据用户登录名获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public BsUserInfoOfJg getUsersByUserId(String userId) {
		List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find("from BsUserInfoOfJg where loginid = '" + userId + "'",
		        new Object[] {});
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据机构编号获取该机构子机构
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsOrg> getOrgsByParentOrgNo(String parentNo) {
		List<BsOrg> list = bsOrgDao.find("from BsOrg where Parentno = ?", new Object[] { parentNo });
		return list;
	}

	/**
	 * 根据机构编号获取该机构子机构
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsOrg> getOrgsByParentOrgNoContainSelf(String parentNo) {
		List<BsOrg> list = bsOrgDao.find("from BsOrg where Parentno = ? or no = ? order by Parentno", new Object[] {
		        parentNo, parentNo });
		return list;
	}

	/**
	 * 根据机构编号获取该机构子机构.
	 * 
	 * @param parentNo
	 *            金融机构父节点
	 * @param pbcNo
	 *            当前用户所在人行机构
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsOrg> getOrgsByParentOrgNoForZHOrXZH(String parentNo, String pbcNo) {
		if (StringUtils.isBlank(pbcNo)) {
			return null;
		}
		String child = this.getChildOrgByParentNoStr(pbcNo);
		if (StringUtils.isBlank(pbcNo)) {
			child = "''";
		}
		List<BsOrg> list = bsOrgDao.find(
		        "from BsOrg where Parentno = ? and " + this.getOrInClause("Pcbno in (%s)", child), parentNo);
		return list;
	}

	/**
	 * 构造数量大于db2上限的In查询语句.
	 * 
	 * @param cluase
	 *            in语句：A IN (%s)
	 * @param items
	 *            IN语句中选项值：'a','b'...
	 * @return
	 */
	public String getOrInClause(String cluase, String items) {
		if (StringUtils.isBlank(cluase) || StringUtils.isBlank(items)) {
			return StringUtils.EMPTY;
		}
		String[] itemArr = items.split(SEPARATOR);
		if (ArrayUtils.isEmpty(itemArr)) {
			return StringUtils.EMPTY;
		}
		int length = itemArr.length;
		if (length > MAX_IN_CLUASE_NUMBER) {
			int n = length / MAX_IN_CLUASE_NUMBER;
			int mod = length % MAX_IN_CLUASE_NUMBER;
			if (mod > 0) {
				n = n + 1;
			}
			StringBuffer sb = new StringBuffer("(");
			for (int i = 0; i < n; i++) {
				sb.append(String.format(cluase, StringUtil.join(
				        ArrayUtils.subarray(itemArr, i * MAX_IN_CLUASE_NUMBER, (i + 1) * MAX_IN_CLUASE_NUMBER),
				        StringUtils.EMPTY, StringUtils.EMPTY, SEPARATOR)));
				if (i < n - 1) {
					sb.append(" OR ");
				}
			}
			sb.append(")");
			return sb.toString();
		} else {
			return String.format(cluase, items);
		}
	}

	/**
	 * 根据人民银行机构获取机构下执法人员
	 * 
	 * @param orgNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<BsAepeople> getAepeoplesByPcbOrgNo(String pcbNo) {
		List<BsAepeople> list = bsAepeopleDao.find("from BsAepeople where bankno = ?", new Object[] { pcbNo });
		return list;
	}

	/**
	 * 根据人民银行机构编号递归所有子机构
	 * 
	 * @param pcbParentNo
	 *            人民银行父机构编号
	 * @param isContainSelf
	 *            结果是否包含自己
	 */
	@Transactional(readOnly = true)
	public String[] getChildOrgByParentNo(String pcbParentNo, Boolean isContainSelf) {
		StringBuffer sqlSb = new StringBuffer("WITH resultOrg (NO,parentNo,name) AS ")
		        .append("(SELECT No,parentNo,name FROM BS_ORG WHERE parentNo = '").append(pcbParentNo)
		        .append("' UNION ALL SELECT child.No,child.parentNo,child.name FROM resultOrg parent, BS_ORG child ")
		        .append(" WHERE parent.No = child.parentNo )").append(" SELECT NO from resultOrg ");
		// System.out.println(sqlSb.toString());
		List<Map<String, Object>> childrenlt = jdbcTemplate.queryForList(sqlSb.toString());
		if (childrenlt != null && childrenlt.size() > 0) {
			String[] childrenNoArr = null;
			if (isContainSelf) {
				childrenNoArr = new String[childrenlt.size() + 1];
				childrenNoArr[childrenNoArr.length - 1] = pcbParentNo;
			} else {
				childrenNoArr = new String[childrenlt.size()];
			}
			for (int i = 0; i < childrenlt.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) childrenlt.get(i);
				childrenNoArr[i] = map.get("NO").toString();
			}
			return childrenNoArr;
		} else {
			if (isContainSelf) {
				return new String[] { pcbParentNo };
			} else {
				return null;
			}
		}
	}

	/**
	 * 根据人民银行机构编号递归所有子机构,结果包含自己.
	 * 
	 * @param pcbParentNo
	 *            人民银行父机构编号
	 */
	@Transactional(readOnly = true)
	public String getChildOrgByParentNoStr(String pcbParentNo) {
		StringBuffer sqlSb = new StringBuffer("WITH resultOrg (NO,parentNo,name) AS ")
		        .append("(SELECT No,parentNo,name FROM BS_ORG WHERE parentNo = '").append(pcbParentNo)
		        .append("' UNION ALL SELECT child.No,child.parentNo,child.name FROM resultOrg parent, BS_ORG child ")
		        .append(" WHERE parent.No = child.parentNo )").append(" SELECT NO from resultOrg ");
		// System.out.println(sqlSb.toString());
		StringBuffer result = new StringBuffer();
		result.append("'").append(pcbParentNo).append("'");
		List<Map<String, Object>> childrenlt = jdbcTemplate.queryForList(sqlSb.toString());
		if (childrenlt != null && childrenlt.size() > 0) {
			result.append(",");
			for (int i = 0; i < childrenlt.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) childrenlt.get(i);
				result.append("'").append(map.get("NO").toString()).append("'");
				if (i != childrenlt.size() - 1) {
					result.append(",");
				}
			}
		}
		return result.toString();
	}

	/**
	 * 获取问题概况文本内容
	 * 
	 * @param selectedwtgk
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getWTGKContent(String[] selectedwtgk) {
		try {
			StringBuffer result = new StringBuffer();
			String inConditionStr = StringUtil.join(selectedwtgk, "'", "'", ",");
			List<Map<String, Object>> parentlt = jdbcTemplate
			        .queryForList("SELECT distinct(stat) FROM BS_DICTIONARY WHERE id in (" + inConditionStr + ")");
			String[] tempParentNoArr = new String[parentlt.size()];
			for (int i = 0; i < parentlt.size(); i++) {
				Map<String, Object> row = (Map<String, Object>) parentlt.get(i);
				tempParentNoArr[i] = row.get("stat").toString();
			}
			String[] totalArr = StringUtil.combineArray(selectedwtgk, tempParentNoArr);
			String totalInConditionStr = StringUtil.join(totalArr, "'", "'", ",");
			List<BsDictionary> lt = bsDictionaryDao.find("from BsDictionary where  isenable = 'true' and id in ("
			        + totalInConditionStr + ") order by id", new Object[] {});
			for (int i = 0; i < lt.size(); i++) {
				BsDictionary dc = lt.get(i);
				if (dc != null) {
					// 如果为子条目，则前面加空格缩进
					if (dc.getFlag().trim().equalsIgnoreCase("true")) {
						result.append("    ");
					} else {
						result.append("\n");
					}
					result.append(dc.getDvalue());
					result.append("\n");
				}
			}
			return result.toString();
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * 关联个人档案信息
	 * 
	 * @param personId
	 * @param personNm
	 * @param relateContent
	 */
	@Transactional(readOnly = false)
	public void relatePersonProfile(String infoType, String personId, String personNm, String relateContent) {
		if (StringUtils.isBlank(infoType)) {
			return;
		}
		if (StringUtils.isBlank(personId)) {
			return;
		}
		if (StringUtils.isBlank(personNm)) {
			return;
		}
		if (StringUtils.isBlank(relateContent)) {
			return;
		}
		// BsUserInfoOfJg user = super.getUsersByUserId(personId);
		// if (user == null)
		// return;
		try {
			BsUserRelevantInfo uri = new BsUserRelevantInfo();
			uri.setInfoType(infoType);
			uri.setRelevantDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			uri.setRelevantId(StringUtils.EMPTY);
			uri.setRelevantInfo(relateContent.trim());
			uri.setUserLoginId(personId.trim());
			uri.setUserName(personNm.trim());
			bsUserRelevantInfoDao.save(uri);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 关联机构档案信息
	 * 
	 * @param personId
	 * @param personNm
	 * @param relateContent
	 */
	@Transactional(readOnly = false)
	public void relateOrgProfile(String orgNo, String orgNm, String relateContent) {
		if (StringUtils.isBlank(orgNo)) {
			return;
		}
		if (StringUtils.isBlank(orgNm)) {
			return;
		}
		if (StringUtils.isBlank(relateContent)) {
			return;
		}
		// BsOrg org = null;
		// try {
		// org = bsOrgDao.get(orgNo);
		// } catch (Exception e) {
		// e.printStackTrace();
		// return;
		// }
		// if (org == null)
		// return;
		try {
			BsOrgRelevantInfo ori = new BsOrgRelevantInfo();
			ori.setInfoType("执法检查结论涉及机构");
			ori.setRelevantDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			ori.setRelevantId(StringUtils.EMPTY);
			ori.setRelevantInfo(relateContent.trim());
			ori.setOrgNo(orgNo.trim());
			ori.setOrgName(orgNm.trim());
			bsOrgRelevantInfoDao.save(ori);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 获取用户属性
	 * 
	 * @param login
	 *            用户登陆名
	 * @param credential
	 *            属性名
	 * @return 属性值
	 */
	@Transactional(readOnly = true)
	public String getCredential(String login, UserCredentialName credential) {
		String password = StringUtils.EMPTY;
		try {
			List list = jdbcTemplate.queryForList("SELECT cred_value FROM jg_credential WHERE user_id IN  "
			        + "(SELECT user_id FROM jg_credential WHERE cred_value = ? AND cred_name = ? ) AND cred_name = ? ",
			        new Object[] { login, UserCredentialName.login.name(), credential.name() }, java.lang.String.class);
			for (int i = 0; i < list.size(); i++) {
				password = (String) list.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * 获取执法人员信息
	 * 
	 * @param login
	 *            用户登陆名
	 * @param credential
	 *            属性名
	 * @return 属性值
	 */
	@Transactional(readOnly = true)
	public BsAepeople getAePeopleByCardId(String cardId) {
		if (StringUtils.isBlank(cardId)) {
			return null;
		}
		List peopleLt = bsAepeopleDao.find("from BsAepeople where cardid = ?", cardId.trim());
		if (CollectionUtils.isNotEmpty(peopleLt)) {
			return (BsAepeople) peopleLt.get(0);
		}
		return null;
	}

	/**
	 * 根据商业银行编号获取所属人民银行中支
	 * 
	 * @param bankNo
	 * @return
	 * 
	 *         如果所属人行机构为县支行，则返回空
	 * 
	 *         如果所属人行机构为中支，则返回中支
	 * 
	 *         如果所属人行机构为分行，则返回空
	 */
	@Transactional(readOnly = true)
	public BsOrg getPbcZZNo(String bankNo) {
		if (StringUtils.isBlank(bankNo)) {
			return null;
		} else {
			BsOrg bank = bsOrgDao.get(bankNo);
			if (bank != null) {
				String pcbno = bank.getPcbno();
				if (StringUtils.isNotBlank(pcbno)) {
					BsOrg pbc = bsOrgDao.get(pcbno);
					if (Constants.PCB_SC_ORG_NO.equals(pbc.getParentno())) {
						return pbc;
					}
				}
			}
			return null;
		}
	}

	/**
	 * 根据商业银行编号获取所属人民银行县支行
	 * 
	 * @param bankNo
	 * @return
	 * 
	 *         如果所属人行机构为县支行，则返回县支行
	 * 
	 *         如果所属人行机构为中支，则返回空
	 * 
	 *         如果所属人行机构为分行，则返回空
	 */
	@Transactional(readOnly = true)
	public BsOrg getPbcXZHNo(String bankNo) {
		if (StringUtils.isBlank(bankNo)) {
			return null;
		} else {
			BsOrg bank = bsOrgDao.get(bankNo);
			if (bank != null) {
				String pcbno = bank.getPcbno();
				if (StringUtils.isNotBlank(pcbno)) {
					BsOrg pbc = bsOrgDao.get(pcbno);
					if (!Constants.PCB_SC_ORG_NO.equals(pbc.getParentno())
					        && !Constants.PCB_SC_ORG_NO.equals(pbc.getNo())) {
						return pbc;
					}
				}
			}
			return null;
		}
	}

	/**
	 * 判断当前人行是否为中支
	 * 
	 * @param pbcNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isPbcZZ(String pbcNo) {
		if (StringUtils.isBlank(pbcNo)) {
			return false;
		} else {
			BsOrg pbc = bsOrgDao.get(pbcNo);
			if (pbc == null) {
				return false;
			} else {
				if (Constants.PCB_SC_ORG_NO.equals(pbc.getParentno())) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 判断当前人行是否为县支行
	 * 
	 * @param pbcNo
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isPbcXzh(String pbcNo) {
		if (StringUtils.isBlank(pbcNo)) {
			return false;
		} else {
			BsOrg pbc = bsOrgDao.get(pbcNo);
			if (pbc == null) {
				return false;
			} else {
				if (!Constants.PCB_SC_ORG_NO.equals(pbc.getParentno()) && !Constants.PCB_SC_ORG_NO.equals(pbc.getNo())) {
					return true;
				}
			}
			return false;
		}

	}

	public void setBsUserInfoOfJgDao(BsUserInfoOfJgDAO bsUserInfoOfJgDao) {
		this.bsUserInfoOfJgDao = bsUserInfoOfJgDao;
	}

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setBsDictionaryDao(BsDictionaryDAO bsDictionaryDao) {
		this.bsDictionaryDao = bsDictionaryDao;
	}

	public void setBsAepeopleDao(BsAepeopleDAO bsAepeopleDao) {
		this.bsAepeopleDao = bsAepeopleDao;
	}

	public void setBsUserRelevantInfoDao(BsUserRelevantInfoDAO bsUserRelevantInfoDao) {
		this.bsUserRelevantInfoDao = bsUserRelevantInfoDao;
	}

	public void setBsOrgRelevantInfoDao(BsOrgRelevantInfoDAO bsOrgRelevantInfoDao) {
		this.bsOrgRelevantInfoDao = bsOrgRelevantInfoDao;
	}
}
