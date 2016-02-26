package com.gtm.csims.jaas.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gtm.csims.model.BsUserApplyInfo;
import com.gtm.csims.model.BsUserInfoOfJg;
import com.gtm.csims.model.BsUserInfoOfZx;
import com.gtm.csims.model.BsUserReportInfo;

import net.sweet.dao.generic.support.Page;

/**
 * 用户信息业务提供者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public interface UserService {
	
	public List<BsUserInfoOfJg> getUserByOrgNo(String orgNo);
	
	public String getAepeople(String orgNo,String deptNo);
	
	@SuppressWarnings("unchecked")
	public List getPrincipalList();
	
	@SuppressWarnings("unchecked")
	public List getPepByOrgNoandDeptNoForSelect(String orgNo,String deptNo);
	
	/**
	 * 更新用户密码
	 * 
	 * @param loginName
	 * @param credencialName
	 * @param password
	 * @param userId
	 */
	public void updateUserPassword(String loginName, String credencialName,
			String password, String userId);

	/**
	 * 获得用户密码
	 * 
	 * @param loginName
	 * @param credencialName
	 * @param userId
	 * @return
	 */
	public String getPassword(String loginName, String credencialName,
			String userId);

	/**
	 * 认证该用户信息是否正确
	 * 
	 * @param login
	 * @param password
	 * @param isMd5
	 */
	public void authenticateUser(String login, String password, Boolean isMd5)
			throws AuthenticationException;

	/**
	 * 返回系统密码加密设置方式
	 * 
	 * @return
	 */
	public Boolean getIsPasswordMD5();

	/**
	 * 返回用户列表
	 * 
	 * @return
	 */
	public Page getUserList(String loginName,String loginNickName,String loginOrg,String loginUserType,String userName,
			String loginId,String organization,String userRole,String userType,int pageNo, int pageSize);
	
	/**
	 * 返回查询用户列表
	 * 
	 * @return
	 */
	public Page getUserInfoListPage(String areaNo,String orgNo,String zxUserType,int pageNo, int pageSize);
	
	/**
	 * 返回查询用户列表
	 * 
	 * @return
	 */
	public Page getUserInfoListPageOfJg(String jgUserRole,int pageNo, int pageSize);
	
	/**
	 * 返回查询用户总数
	 * 
	 * @return
	 */
	public List<BsUserInfoOfZx> getUserInfoList(String areaNo,String orgNo,String zxUserType);
	
	/**
	 * 返回查询用户总数
	 * 
	 * @return
	 */
	public List<BsUserInfoOfJg> getUserInfoListOfJg(String jgUserRole);

	/**
	 * 返回用户备案列表个人
	 * 
	 * @return
	 */
	public Page getUserReportInfoList(String loginName,String loginNickName,String loginOrg,String loginUserType,String userName,
			String loginId,String organization,String reportType,int pageNo, int pageSize);
	
	/**
	 * 返回用户备案列表汇总
	 * 
	 * @return
	 */
	public List<BsUserReportInfo> getBsUserReportInfoList(String loginName,String loginNickName,String loginOrg,
			String loginUserType,String begin,String end,String organization,String reportType);

	/**
	 * 返回用户列表
	 * 
	 * @return
	 */
	public Page getPluralUserList(String loginName, int pageNo, int pageSize);

	/**
	 * 返回待审核用户列表
	 * 
	 * @return
	 */
	public Page getAuditUserList(String nowLoginUserName,String userName,
			String loginId,String organization, int pageNo,int pageSize);

	/**
	 * 返回申请用户列表
	 * 
	 * @return
	 */
	public Page getApplyUserList(String nowLoginUserName,String userName,
			String loginId,String organization, int pageNo,
			int pageSize);

	/**
	 * 返回用户ID
	 * 
	 * @return
	 */
	public String getUserIdByLoginName(String loginName);

	public BsUserInfoOfJg getBsUserInfoOfJgByLoginId(String loginId);
	
	public void savaBsUserInfoOfJg(BsUserInfoOfJg bsUserInfoOfJg);
	
	public void savaBsUserInfoOfZx(BsUserInfoOfZx bsUserInfoOfZx);
	
	public BsUserInfoOfJg getBsUserInfoOfJgById(String id);
	
	public BsUserInfoOfZx getBsUserInfoOfZxById(String id);
	
	public BsUserApplyInfo getBsUserApplyInfo(String loginId);

	public void savaBsUserApplyInfo(BsUserApplyInfo bsUserApplyInfo);

	public void delBsUserApplyInfo(BsUserApplyInfo bsUserApplyInfo);

	public void delBsUserPluralInfoById(String id);
	
	public List<BsUserInfoOfZx> getUserZxInfo(String userId);
	
	public String getUserZxInfoTr(String id,String flag);
	
	public List getUserZxInfoTrOfApply(String id,String flag);
	
	public String getUserInfoTr(BsUserInfoOfJg bsUserInfoOfJg,List principalsList,Collection tempCol);
	
	public void updateUserManager(String loginId,String oldLoginId);
	
	public void cancelBsZxUserById(String id);
	
	public void disableZxUserByUserId(String userId);
	
	public void enableZxUserByUserId(String userId);
	
	public void deleteUser(BsUserInfoOfJg bsUserInfoOfJg);
	
	public String getUserZxInfoTrOfReport(String id);
	
	public BsUserReportInfo getBsUserReportInfoById(String id);
	
	public void savaBsUserReportInfo(BsUserReportInfo bsUserReportInfo);
	
	public HSSFWorkbook generateExcel(Map<String, String> keyValueMap);
	
	public void savaUserStopInfo(HttpServletRequest request,String loginId,String nowLoginUserName,InputStream is,String endRow);
	
	public StringBuffer statisticsByArea();
	
	public StringBuffer statisticsByAreaNo(String areaNo);
	
	public StringBuffer statisticsByPrincipal();
	
	public StringBuffer statisticsByOrg();
	
	public StringBuffer statisticsByParentOrgNo(String orgNo);
	
	public HSSFWorkbook generateExcelOfUserInfoQueryResult(Map<String, String> keyValueMap,String flag);

	public void savaUserComInfo(InputStream is,String endRow);
}
