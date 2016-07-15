package com.gtm.csims.business.managment.system;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.dailymgr.DailyManagementService;
import com.gtm.csims.business.question.QuestionService;
import com.gtm.csims.dao.BsBusievalDAO;
import com.gtm.csims.dao.BsDeptDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.dao.BsUserInfoOfJgDAO;
import com.gtm.csims.dao.BsUserInfoOfZxDAO;
import com.gtm.csims.jaas.service.UserService;
import com.gtm.csims.model.BsBusieval;
import com.gtm.csims.model.BsBusievalInfo;
import com.gtm.csims.model.BsDept;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsQuestion;
import com.gtm.csims.model.BsUserInfoOfJg;
import com.gtm.csims.model.BsUserInfoOfZx;

/**
 * @ClassName: ${SelectSystemDataBaseByDwr}
 * @Description: ${Dwr处理异步请求}
 * @author qhy
 * @date ${date} ${time}
 * 
 */
@SuppressWarnings("unchecked")
public class SelectSystemDataBaseByDwr {
	private BsOrgDAO bsOrgDao;
	private BsDeptDAO bsDeptDao;
	private UserService userService;
	private DailyManagementService dailyManagementService;
	private BsUserInfoOfJgDAO bsUserInfoOfJgDao;
	private BsUserInfoOfZxDAO bsUserInfoOfZxDao;
	private BsBusievalDAO bsBusievalDao;
	private SystemBaseInfoManager systemBaseInfoManager;
	private QuestionService questionService;

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	public void setBsDeptDao(BsDeptDAO bsDeptDao) {
		this.bsDeptDao = bsDeptDao;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setDailyManagementService(DailyManagementService dailyManagementService) {
		this.dailyManagementService = dailyManagementService;
	}

	public void setBsUserInfoOfJgDao(BsUserInfoOfJgDAO bsUserInfoOfJgDao) {
		this.bsUserInfoOfJgDao = bsUserInfoOfJgDao;
	}

	public void setBsUserInfoOfZxDao(BsUserInfoOfZxDAO bsUserInfoOfZxDao) {
		this.bsUserInfoOfZxDao = bsUserInfoOfZxDao;
	}

	public void setBsBusievalDao(BsBusievalDAO bsBusievalDao) {
		this.bsBusievalDao = bsBusievalDao;
	}

	public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
		this.systemBaseInfoManager = systemBaseInfoManager;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@Transactional(readOnly = true)
	public String[] getOrgByNo(String no) {
		String result[] = new String[20];
		BsOrg bs = (BsOrg) bsOrgDao.get(no);
		result[0] = bs.getName();
		result[1] = bs.getNo();
		result[18] = bs.getParentno();
		try {
			if (bs.getOrgCodeOfZz() == null)
				result[2] = "";
			else
				result[2] = bs.getOrgCodeOfZz();
			if (bs.getOrgCodeOfXy() == null)
				result[19] = "";
			else
				result[19] = bs.getOrgCodeOfXy();
			if (bs.getE() == null)
				result[4] = "";
			else
				result[4] = bs.getE();
			if (bs.getG() == null)
				result[5] = "";
			else
				result[5] = bs.getG();
			if (bs.getJ() == null)
				result[6] = "";
			else
				result[6] = bs.getJ();
			if (bs.getK() == null)
				result[7] = "";
			else
				result[7] = bs.getK();
			if (bs.getL() == null)
				result[8] = "";
			else
				result[8] = bs.getL();
			if (bs.getM() == null)
				result[9] = "";
			else
				result[9] = bs.getM();
			if (bs.getO() == null)
				result[10] = "";
			else
				result[10] = bs.getO();
			if (bs.getQ() == null)
				result[11] = "";
			else
				result[11] = bs.getQ();
			if (bs.getS() == null)
				result[12] = "";
			else
				result[12] = bs.getS();
			if (bs.getT() == null)
				result[13] = "";
			else
				result[13] = bs.getT();
			if (bs.getZ() == null)
				result[14] = "";
			else
				result[14] = bs.getZ();
			if (bs.getAa() == null)
				result[15] = "";
			else
				result[15] = bs.getAa();
			if (bs.getIspcb() == null)
				result[16] = "";
			else
				result[16] = bs.getIspcb();
		} catch (Exception e) {
			result[2] = "";
			result[3] = "";
			result[4] = "";
		}
		return result;
	}

	@Transactional(readOnly = true)
	public String[] getOrgInfo(String orgNo) {
		String result[] = new String[4];
		BsOrg bs = (BsOrg) bsOrgDao.get(orgNo);
		result[0] = orgNo;
		try {
			if (bs.getOrgCodeOfZz() == null)
				result[1] = "";
			else
				result[1] = bs.getOrgCodeOfZz();
			if (bs.getOrgCodeOfXy() == null)
				result[2] = "";
			else
				result[2] = bs.getOrgCodeOfXy();
			if (bs.getAa() == null)
				result[3] = "";
			else
				result[3] = bs.getAa();
		} catch (Exception e) {
			result[1] = "";
			result[2] = "";
			result[3] = "";
		}
		return result;
	}

	@Transactional(readOnly = true)
	public String getOrgParentNoByNo(String orgNo) {
		String result = "";
		BsOrg bs = (BsOrg) bsOrgDao.get(orgNo);
		try {
			if (bs.getParentno() == null)
				result = "";
			else
				result = bs.getParentno();
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	@Transactional(readOnly = true)
	public String[] getPcbDept(String orgno) {
		List<BsDept> list = bsDeptDao.find("from BsDept b where b.orgno ='" + orgno + "'");
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			BsDept bs = list.get(i);
			result[i] = bs.getId() + "-" + bs.getName();
		}
		return result;
	}

	@Transactional(readOnly = true)
	public String[] getDept(String orgno) {

		List<BsDept> list = bsDeptDao.find("from BsDept where Orgno ='" + orgno + "'");
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			BsDept bs = list.get(i);
			result[i] = bs.getId() + "-" + bs.getName();
		}
		return result;
	}

	public String[] getAepeople(String orgno, String deptno) {

		// List<BsDept> list = bsDeptDao.find(
		// "from BsDept b where b.orgno ='" + orgno + "'");
		// String[] result = new String[list.size()];
		// for(int i=0;i<list.size();i++){
		// BsDept bs = list.get(i);
		// result[i] = bs.getDeptNo() +"-"+bs.getDeptName();
		// }
		String[] result = new String[3];
		result[0] = "51082419851031575X" + "-" + "邱洋";
		result[1] = "N02" + "-" + "李四";
		result[2] = "N03" + "-" + "王五";
		return result;
	}

	// public String[] getAepeopleInfo(String loginId) {
	// BsUserInfo bs = userService.getBsUserInfoByLoginId(loginId);
	// String[] result = new String[4];
	// try{
	// if(bs.getPost() == null){
	// result[1] = "";
	// }else{
	// result[1] = bs.getPost();
	// }
	// if(bs.getPhone() == null){
	// result[2]="";
	// }else{
	// result[2] = bs.getPhone();
	// }
	// if(bs.getName() == null){
	// result[3]="";
	// }else{
	// result[3] = bs.getName();
	// }
	// }catch (Exception e){
	// e.printStackTrace();
	// result[1]="";
	// result[2]="";
	// }
	// result[0] = loginId;
	// return result;
	// }

	// public String getUserCheckboxByOrgNo(String orgNo) {
	// StringBuffer sb = new StringBuffer(
	// "<td align='left' width='10%' height='25'>");
	// List<BsUserInfoOfJg> list = userService.getUserByOrgNo(orgNo);
	// for (int i = 0; i < list.size(); i++) {
	// BsUserInfoOfJg bs = list.get(i);
	// sb.append("<input type='checkbox' name='userCheckbox' value='")
	// .append(bs.getName()).append("' />").append(bs.getName())
	// .append("<br>");
	// }
	// sb.append("</td>");
	// return sb.toString();
	// }

	@Transactional(readOnly = true)
	public String[] getUpdateEvalInfo(String evalInfoId) {
		return null;
	}

	@Transactional(readOnly = true)
	public String getUserCheckboxByOrgNo(String orgNo, String flag) {
		StringBuffer sb = new StringBuffer("");
		List<BsUserInfoOfJg> list = userService.getUserByOrgNo(orgNo);
		if (list.size() == 0) {
			sb.append("");
		} else {
			sb.append("<td><table width='100%' border='0' cellpadding='0' cellspacing='0' class='tableline01'>");
			for (int i = 0; i < list.size(); i++) {
				String input1 = "INPUT" + String.valueOf(i);
				String input2 = "INPUT" + String.valueOf(i) + "0";
				String td2 = "TD" + String.valueOf(i) + "0";
				BsUserInfoOfJg bs = (BsUserInfoOfJg) list.get(i);
				if ("0".equals(flag))
					sb.append("<tr><td align='left' height='30' width='10%'>&nbsp;<input id='")
					        .append(input1)
					        .append("' type='checkbox' name='userCheckbox1' value='")
					        .append(String.valueOf(i))
					        .append(",")
					        .append(bs.getLoginId())
					        .append(",")
					        .append(bs.getName())
					        .append("' />")
					        .append(bs.getName())
					        .append("</td>")
					        .append("<td><input id='")
					        .append(input2)
					        .append("' type='text' name='userCheckbox2' class='text11155123' style='display: none;' /></td></tr>");
				else
					sb.append("<tr><td align='left' height='30' width='10%'>&nbsp;<input id='")
					        .append(input1)
					        .append("' type='checkbox' name='userCheckbox1' onclick= 'return openText(this);' value='")
					        .append(String.valueOf(i))
					        .append(",")
					        .append(bs.getLoginId())
					        .append(",")
					        .append(bs.getName())
					        .append("' />")
					        .append(bs.getName())
					        .append("</td>")
					        .append("<td width='5%' id='")
					        .append(td2)
					        .append("' style='display: none;'>存在问题:</td><td><input id='")
					        .append(input2)
					        .append("' type='text' name='userCheckbox2' class='text11155123' style='display: none;' /></td></tr>");
			}
			sb.append("</table></td>");
		}
		return sb.toString();
	}

	@Transactional(readOnly = true)
	public Boolean getUserStatusInfoOfJg(String userId) {
		Boolean status = false;
		BsUserInfoOfJg bs = userService.getBsUserInfoOfJgById(userId);
		if ("停用".equals(bs.getUserStatus()))
			status = true;
		return status;
	}

	@Transactional(readOnly = true)
	public Boolean getYearAndDuring(String year, String during) {
		return null;
	}

	@Transactional(readOnly = true)
	public String getEvalBaseScore(String year, String during, String evalInfo) {
		return null;
	}

	@Transactional(readOnly = true)
	public String getZxUserNameInfo(String loginId, String zxUserTypeName) {
		return null;
	}

	@Transactional(readOnly = true)
	// 获取征信系统用户使用状态
	public String getZxUserNameInfoStatus(String loginId, String zxName) {
		String[] zxNames = zxName.split("###");
		String str = "";
		String zxUserType = "";
		List<BsUserInfoOfZx> list = null;
		for (int i = 0; i < zxNames.length; i++) {
			StringBuffer sbSql = new StringBuffer("");
			str = zxNames[i].toString().trim();
			String[] strs = str.split("===");
			sbSql.append("from BsUserInfoOfZx where LoginId<>'").append(loginId.trim()).append("' and ZxUserName ='")
			        .append(strs[1].toString().trim()).append("' and UserStatus='启用' ");
			list = bsUserInfoOfZxDao.find(sbSql.toString());
			if (list.size() > 0) {
				return "征信系统用户名【" + strs[1].toString().trim() + "】已存在,请核对!";
			}
			sbSql.delete(0, sbSql.length());
			zxUserType = BaseAction.getZXPriName(strs[0].toString().trim());
			sbSql.append("from BsUserInfoOfZx where LoginId='").append(loginId.trim()).append("' and ZxUserName ='")
			        .append(strs[1].toString().trim()).append("' and ZXUSERTYPE='").append(zxUserType)
			        .append("' and UserStatus='启用' ");
			list = bsUserInfoOfZxDao.find(sbSql.toString());
			if (list.size() > 0) {
				return "身份识别码【" + loginId + "】的【" + zxUserType + "】征信系统用户名【" + strs[1].toString().trim() + "】已存在,请核对!";
			}
			sbSql.delete(0, sbSql.length());
		}
		return "";
	}

	@Transactional(readOnly = true)
	public String[] getGlyByOrgNoAndZxUserType(String tdId, String orgNo) {
		List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find(" from BsUserInfoOfJg where OrgCodeOfJr = '" + orgNo + "' "
		        + "and UserType = '一般管理员' and UserStatus='启用' ");
		if (list.size() > 0) {
			String glyUser[] = new String[list.size() + 1];
			glyUser[0] = tdId;
			String glyName = "";
			String glyLoginId = "";
			for (int i = 0; i < list.size(); i++) {
				BsUserInfoOfJg bs = list.get(i);
				glyName = bs.getName().toString();
				glyLoginId = bs.getLoginId().toString();
				glyUser[i + 1] = glyName + "," + glyLoginId;
			}
			return glyUser;
		} else {
			String glyUser[] = new String[1];
			glyUser[0] = tdId;
			return glyUser;
		}
	}

	@Transactional(readOnly = true)
	public String[] getAepeopleByOrgAndDept(String orgNo, String dptNo) {
		List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find("from BsUserInfoOfJg where OrgCodeOfJr = '" + orgNo + "' "
		        + "and DeptNo = '" + dptNo + "' and Name <> 'admin' ");
		String glyUser[] = new String[list.size()];
		if (list.size() > 0) {
			glyUser = new String[list.size()];
			String glyName = "";
			String glyLoginId = "";
			for (int i = 0; i < list.size(); i++) {
				BsUserInfoOfJg bs = list.get(i);
				glyName = bs.getName().toString();
				glyLoginId = bs.getLoginId().toString();
				glyUser[i] = glyName + "=" + glyLoginId;
			}
		}
		return glyUser;
	}

	@Transactional(readOnly = true)
	public String[] getGlyByOrgNo(String orgNo) {
		List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find("from BsUserInfoOfJg where OrgCodeOfJr = '" + orgNo + "' "
		        + "and UserType = '一般管理员' and UserStatus='启用' ");
		if (list.size() > 0) {
			String glyUser[] = new String[list.size() + 1];
			glyUser[0] = "";
			String glyName = "";
			String glyLoginId = "";
			for (int i = 0; i < list.size(); i++) {
				BsUserInfoOfJg bs = list.get(i);
				glyName = bs.getName().toString();
				glyLoginId = bs.getLoginId().toString();
				glyUser[i + 1] = glyName + "," + glyLoginId;
			}
			return glyUser;
		} else {
			String glyUser[] = new String[1];
			glyUser[0] = "";
			return glyUser;
		}
	}

	@Transactional(readOnly = true)
	public Boolean getEvalMarkingInfo(String orgNo, String year, String during) {
		List<BsBusieval> list = bsBusievalDao.find(" from BsBusieval where EvalYear='" + year + "' and EvalDuring='"
		        + during + "'" + " and Evaledorgno='" + orgNo + "'");
		if (list.size() > 0)
			return true;
		else
			return false;
	}

	@Transactional(readOnly = true)
	public String[] getEvalInfo(String year, String during) {
		return null;
	}

	@Transactional(readOnly = false)
	public String getEvalInfoId(String year, String during) {
		return null;
	}

	@Transactional(readOnly = true)
	public String getEvalRule(String evalInfo) {
		return null;
	}

	@Transactional(readOnly = true)
	public String getEvalRuleByYearAndDuring(String year, String during) {
		return null;
	}

	@Transactional(readOnly = true)
	public String getTotalScore(String year, String during) {
		return null;
	}

	@Transactional(readOnly = false)
	public String getOrgMc(String year, String during, String orgNo) {
		return null;
	}

	@Transactional(readOnly = true)
	public String[] getQuestionById(String id) {
		BsQuestion bs = questionService.getBsQuestionById(id);
		String result[] = new String[8];
		try {
			result[0] = bs.getQqtitle();
			result[1] = bs.getAnswera();
			result[2] = bs.getAnswerb();
			result[3] = bs.getAnswerc();
			result[4] = bs.getAnswerd();
			result[5] = bs.getAnswere();
			result[6] = bs.getAnswerf();
			result[7] = bs.getAnswerg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
