package com.gtm.csims.jaas.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.jguard.core.principals.RolePrincipal;
import net.sweet.dao.generic.support.Page;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.util.LabelValueBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.dao.BsUserApplyInfoDAO;
import com.gtm.csims.dao.BsUserComInfoDAO;
import com.gtm.csims.dao.BsUserInfoOfJgDAO;
import com.gtm.csims.dao.BsUserInfoOfZxDAO;
import com.gtm.csims.dao.BsUserReportInfoDAO;
import com.gtm.csims.file.FileHandler;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.jaas.action.PrincipalComparator;
import com.gtm.csims.jaas.service.AuthenticationException;
import com.gtm.csims.jaas.service.UserService;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsPhoto;
import com.gtm.csims.model.BsUserApplyInfo;
import com.gtm.csims.model.BsUserComInfo;
import com.gtm.csims.model.BsUserInfoOfJg;
import com.gtm.csims.model.BsUserInfoOfZx;
import com.gtm.csims.model.BsUserReportInfo;
import com.gtm.csims.utils.ExcelUtil;
import com.gtm.csims.utils.MD5;
import com.gtm.csims.utils.StringUtil;

public class UserServiceImpl implements UserService {
    public static int[] TABLEDATA_BEGIN_X = { 0 };
    public static int[] TABLEDATA_BEGIN_Y = { 2 };
    private static int SPLIT_LENGTH = 800;
    private String authenImpl;

    private Boolean isPasswordMD5;

    private JdbcTemplate jdbcTemplate;

    private BsUserInfoOfJgDAO bsUserInfoOfJgDao;

    private BsUserReportInfoDAO bsUserReportInfoDao;

    private BsUserInfoOfZxDAO bsUserInfoOfZxDao;

    private BsUserApplyInfoDAO bsUserApplyInfoDao;

    private FileHandler fileHandler;

    private SystemBaseInfoManager systemBaseInfoManager;
    
    private BsUserComInfoDAO bsUserComInfoDao;

    private String orgSql(String orgString) {
        String[] orgs = orgString.split(",");
        StringBuffer sb = new StringBuffer();
        if (orgs.length < SPLIT_LENGTH) {
            sb.append(" and orgCode ");
            sb.append(" in (" + orgString + ") ");
        } else {
            int countInCluase = 0;
            sb.append(" and ");
            sb.append(" (orgCode ");
            String[] _temp = new String[countInCluase + SPLIT_LENGTH];
            int x = orgs.length / SPLIT_LENGTH;
            int y = x * SPLIT_LENGTH;
            for (int i = 0; i < x; i++) {
                System.arraycopy(orgs, countInCluase, _temp, 0, SPLIT_LENGTH);
                System.out.println(_temp.length);
                sb.append(" in (").append(StringUtil.join(_temp, "", "", ","))
                        .append(")");
                countInCluase += SPLIT_LENGTH;
                if (i != (x - 1))
                    sb.append(" OR orgCode ");
            }
            String[] _temp2 = new String[orgs.length - y];
            if (y < orgs.length) {
                sb.append(" OR orgCode ");
                System.arraycopy(orgs, countInCluase, _temp2, 0, orgs.length
                        - y);
                System.out.println(_temp2.length);
                sb.append(" in (").append(StringUtil.join(_temp2, "", "", ","))
                        .append(")");
            }
            sb.append(")");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private String getSplitInDept(String[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            // sb.append("'");
            sb.append(array[i]);
            // sb.append("'");
            if (i != array.length - 1)
                sb.append(",");
        }
        return sb.toString();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setBsUserInfoOfJgDao(BsUserInfoOfJgDAO bsUserInfoOfJgDao) {
        this.bsUserInfoOfJgDao = bsUserInfoOfJgDao;
    }

    public void setBsUserInfoOfZxDao(BsUserInfoOfZxDAO bsUserInfoOfZxDao) {
        this.bsUserInfoOfZxDao = bsUserInfoOfZxDao;
    }

    public void setBsUserApplyInfoDao(BsUserApplyInfoDAO bsUserApplyInfoDao) {
        this.bsUserApplyInfoDao = bsUserApplyInfoDao;
    }

    public void setBsUserReportInfoDao(BsUserReportInfoDAO bsUserReportInfoDao) {
        this.bsUserReportInfoDao = bsUserReportInfoDao;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setSystemBaseInfoManager(
            SystemBaseInfoManager systemBaseInfoManager) {
        this.systemBaseInfoManager = systemBaseInfoManager;
    }

    public void setBsUserComInfoDao(BsUserComInfoDAO bsUserComInfoDao) {
		this.bsUserComInfoDao = bsUserComInfoDao;
	}

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<BsUserInfoOfJg> getUserByOrgNo(String orgNo){
    	return bsUserInfoOfJgDao.find("FROM BsUserInfoOfJg WHERE orgCodeOfJr = '" + orgNo + "' and name <> 'admin' ");
    }
    
	@SuppressWarnings("unchecked")
    public List getPrincipalList() {
        String principalName = "";
        List result = new ArrayList();
        result.add(new LabelValueBean("-----请选择-----", ""));
        List list = jdbcTemplate
                .queryForList("select NAME from JG_PRINCIPAL where NAME <> 'admin' and NAME <> 'guest' and NAME <> 'systemadmin' ");
        try {
            for (int i = 0; i < list.size(); i++) {
                ListOrderedMap map = (ListOrderedMap) list.get(i);
                principalName = String.valueOf(map.get("NAME"));
                result.add(new LabelValueBean(principalName, principalName));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    // @SuppressWarnings("unchecked")
    // public String getAepeople(String orgNo, String deptNo) {
    // List exsitList = bsUserInfoDao
    // .find("FROM BsUserInfo WHERE orgCodeOfJr = '" + orgNo
    // + "' and deptNo='" + deptNo + "' and name <> 'admin' ");
    // JSONArray jsonArray = new JSONArray();
    // // 把取出的部门转换成json格式返回
    // for (Iterator iterator = exsitList.iterator(); iterator.hasNext();) {
    // BsUserInfo bs = (BsUserInfo) iterator.next();
    // JSONObject json = new JSONObject();
    // json.accumulate("id", bs.getId());
    // json.accumulate("name", bs.getName());
    // jsonArray.add(json);
    // }
    // return jsonArray.toString();
    // }

//     @SuppressWarnings("unchecked")
//     @Transactional(readOnly = true)
//     public List getPepByOrgNoandDeptNoForSelect(String orgNo, String deptNo)
//     {
//     List result = new ArrayList();
//     result.add(new LabelValueBean("-----请选择-----", ""));
//     List exsitList = bsUserInfoDao
//     .find("FROM BsUserInfo WHERE orgCodeOfJr = '" + orgNo
//     + "' and deptNo='" + deptNo + "' and name <> 'admin'");
//     // 把取出的部门转换成下拉列表格式返回
//     for (Iterator iterator = exsitList.iterator(); iterator.hasNext();) {
//     BsUserInfo bs = (BsUserInfo) iterator.next();
//     result.add(new LabelValueBean(bs.getName(), bs.getId().toString()));
//     }
//     return result;
//     }

    @Transactional
    public void updateUserPassword(String loginName, String credencialName,
            String password, String userId) {
        Long userid = jdbcTemplate
                .queryForLong(
                        "SELECT user_id FROM jg_credential WHERE cred_value=? AND cred_name=?",
                        new Object[] { userId, loginName });
        jdbcTemplate
                .update(
                        "UPDATE jg_credential SET cred_value=? WHERE user_id=? AND cred_name=?",
                        new Object[] { password, userid,
                                UserCredentialName.password.name() });
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public String getPassword(String loginName, String credencialName,
            String userId) {
        String password = "";

        List list = jdbcTemplate
                .queryForList(
                        "SELECT  cred_value FROM jg_credential WHERE user_id IN  "
                                + "(SELECT user_id FROM jg_credential WHERE cred_value=? AND cred_name=? ) AND cred_name=? ",
                        new Object[] { userId, loginName, credencialName },
                        java.lang.String.class);
        for (int i = 0; i < list.size(); i++) {
            password = (String) list.get(i);
        }
        return password;

    }

    @Transactional(readOnly = true)
    public void authenticateUser(String login, String password, Boolean isMd5)
            throws AuthenticationException {
        if (authenImpl == null || authenImpl.trim().equals("")) {
            this.autenticateWithJGuard(login, password, isMd5);
            return;
        }
        /*
         * if (authenImpl.equalsIgnoreCase("Axis1")) {
         * this.authenticateWithAxis1(login, password, isMd5); return; } if
         * (authenImpl.equalsIgnoreCase("Axis2")) {
         * this.authenticateWithAxis2(login, password, isMd5); return; }
         */
        this.autenticateWithJGuard(login, password, isMd5);
    }

    /**
     * 根据jGuard用户信息表验证用户合法性
     * 
     * @param login
     * @param password
     * @param isMd5
     * @throws AuthenticationException
     */
    private void autenticateWithJGuard(String login, String password,
            Boolean isMd5) throws AuthenticationException {
        if (isMd5) {
            MD5 md5 = new MD5();
            password = (md5.getMD5ofStr(password)).toLowerCase();
        }
        String correctPassword = null;
        try {
            correctPassword = this.getPassword(UserCredentialName.login.name(),
                    UserCredentialName.password.name(), login);
            if (correctPassword == null)
                throw new AuthenticationException("Can't get User[" + login
                        + "]'s password sucessfully!");
        } catch (Exception e) {
            throw new AuthenticationException("Can't get User[" + login
                    + "]'s password sucessfully!");
        }
        if (password.equals(correctPassword))
            return;
        else
            throw new AuthenticationException("User[" + login
                    + "] is authenticate failure!");
    }

    /**
     * 调用WebService Axis1验证用户合法性
     * 
     * @param login
     * @param password
     * @param isMd5
     * @throws AuthenticationException
     */
    /*
     * @SuppressWarnings("unused") private void authenticateWithAxis1(String
     * login, String password, Boolean isMd5) throws AuthenticationException {
     * Service service = new Service(); Call call; try { call = (Call)
     * service.createCall(); call.setTargetEndpointAddress(epr_axis1);
     * call.setOperationName(new QName(qname1, qname2)); String result; try {
     * result = (String) call.invoke(new Object[] { login, password }); if
     * (result.startsWith("1;")) return; } catch (RemoteException e) {
     * e.printStackTrace(); throw new AuthenticationException( "the webservice
     * EPR can't connected."); } } catch (ServiceException e) {
     * e.printStackTrace(); throw new AuthenticationException("invocate remote
     * method failure."); } throw new AuthenticationException("User[" + login + "]
     * is authenticate failure!"); }
     */
    /**
     * 调用WebService Axis2验证用户合法性
     * 
     * @param login
     * @param password
     * @param isMd5
     * @throws AuthenticationException
     */
    /*
     * @SuppressWarnings("unused") private void authenticateWithAxis2(String
     * login, String password, Boolean isMd5) throws AuthenticationException {
     * VerifyPassStub stub; try { stub = new VerifyPassStub(epr_axis2); } catch
     * (AxisFault e) { e.printStackTrace(); throw new AuthenticationException(
     * "the webservice EPR can't connected."); } VerifyPassStub.VerifyPass req =
     * new VerifyPassStub.VerifyPass(); req.setUserID(login);
     * req.setPassword(password); VerifyPassStub.VerifyPassResponse res; try {
     * res = stub.verifyPass(req); } catch (RemoteException e) {
     * e.printStackTrace(); throw new AuthenticationException("invocate remote
     * method failure."); } if (res.get_return().startsWith("1;")) return; else
     * throw new AuthenticationException("User[" + login + "] is authenticate
     * failure!"); }
     */

    public void setAuthenImpl(String authenImpl) {
        this.authenImpl = authenImpl;
    }

    public Boolean getIsPasswordMD5() {
        return isPasswordMD5;
    }

    public void setIsPasswordMD5(Boolean isPasswordMD5) {
        this.isPasswordMD5 = isPasswordMD5;
    }

    /**
     * 返回用户列表
     * 
     * @return
     */
    public Page getUserList(String loginName, String loginNickName,
            String loginOrg, String loginUserType, String userName,
            String loginId, String organization, String userRole,
            String userType, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfJg where  name <> 'admin' and nowUser = '1' ");
        List<Object> param = new ArrayList<Object>();
        if (!"".equals(organization) && organization != null) {
            sb.append(" and orgCodeOfJr = ? ");
            param.add(organization);
        }
        if (!"admin".equals(loginNickName)) {
            if (!"内控监督员".equals(loginUserType)) {
                sb.append(" and createrCode = ? ");
                param.add(loginName);
            }
            sb.append(" and orgCodeOfJr = ? ");
            param.add(loginOrg);
        }
        if (!"".equals(userName) && userName != null) {
            sb.append(" and name = ? ");
            param.add(userName);
        }
        if (!"".equals(loginId) && loginId != null) {
            sb.append(" and loginId = ? ");
            param.add(loginId);
        }
        if (!"".equals(userRole) && userRole != null) {
            sb.append(" and jgPrincipal like ? ");
            param.add("%" + userRole.trim() + "%");
        }
        if (!"".equals(userType) && userType != null) {
            sb.append(" and userType = ? ");
            param.add(userType);
        }
        sb.append(" order by createDate asc");
        Page page = bsUserInfoOfJgDao.pagedQuery(sb.toString(), pageNo,
                pageSize, param.toArray());
        return page;
    }

    /**
     * 返回查询用户列表
     * 
     * @return
     */
    public Page getUserInfoListPage(String areaId, String orgNo,
            String zxUserType, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfZx where nowUser = '1' ");
        List<Object> param = new ArrayList<Object>();
        if (!"".equals(areaId) && areaId != null) {
            String orgString = systemBaseInfoManager.getAllOrgByAreaId(areaId);
            if (orgString != null) {
                sb.append(this.orgSql(orgString));
            }
        } else if (!"".equals(orgNo) && orgNo != null) {
            String orgString = systemBaseInfoManager.getAllChildOfOrg(orgNo);
            sb.append(this.orgSql(orgString));
        } else {
            sb.append(" and ZxUserType = '" + zxUserType + "' ");
        }
        sb.append(" order by createDate asc");
        Page page = bsUserInfoOfZxDao.pagedQuery(sb.toString(), pageNo,
                pageSize, param.toArray());
        return page;
    }

    public Page getUserInfoListPageOfJg(String jgUserRole, int pageNo,
            int pageSize) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfJg where nowUser = '1' and JgPrincipal like '%"
                        + jgUserRole + "%' ");
        sb.append(" order by createDate asc");
        Page page = bsUserInfoOfZxDao.pagedQuery(sb.toString(), pageNo,
                pageSize);
        return page;
    }

    /**
     * 返回查询用户总数
     * 
     * @return
     */
    public List<BsUserInfoOfZx> getUserInfoList(String areaId, String orgNo,
            String zxUserType) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfZx where nowUser = '1' ");
        List<Object> param = new ArrayList<Object>();
        if (!"".equals(areaId) && areaId != null) {
            String orgString = systemBaseInfoManager.getAllOrgByAreaId(areaId);
            sb.append(this.orgSql(orgString));
        } else if (!"".equals(orgNo) && orgNo != null) {
            String orgString = systemBaseInfoManager.getAllChildOfOrg(orgNo);
            sb.append(this.orgSql(orgString));
        } else {
            sb.append(" and ZxUserType = '" + zxUserType + "' ");
        }
        sb.append(" order by createDate asc");
        List<BsUserInfoOfZx> list = bsUserInfoOfZxDao.find(sb.toString());
        return list;
    }

    public List<BsUserInfoOfJg> getUserInfoListOfJg(String jgUserRole) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfJg where nowUser = '1' and JgPrincipal like '%"
                        + jgUserRole + "%' ");
        sb.append(" order by createDate asc");
        List<BsUserInfoOfJg> list = bsUserInfoOfZxDao.find(sb.toString());
        return list;
    }

    /**
     * 返回用户备案列表
     * 
     * @return
     */
    public Page getUserReportInfoList(String loginName, String loginNickName,
            String loginOrg, String loginUserType, String userName,
            String loginId, String organization, String reportType, int pageNo,
            int pageSize) {
        StringBuffer sb = new StringBuffer(" from BsUserReportInfo where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (!"admin".equals(loginNickName)) {
            sb.append(" and orgCodeOfJr = ? ");
            if ("".equals(organization)) {
                param.add(loginOrg);
            } else {
                param.add(organization);
            }
        } else {
            if (!"".equals(organization) && organization != null) {
                sb.append(" and orgCodeOfJr = ? ");
                param.add(organization);
            }
        }
        if (!"".equals(userName) && userName != null) {
            sb.append(" and name = ? ");
            param.add(userName);
        }
        if (!"".equals(loginId) && loginId != null) {
            sb.append(" and loginId = ? ");
            param.add(loginId);
        }
        if (!"".equals(reportType) && reportType != null) {
            sb.append(" and reportType = ? ");
            param.add(reportType);
        }
        sb.append(" order by reportDate asc");
        Page page = bsUserReportInfoDao.pagedQuery(sb.toString(), pageNo,
                pageSize, param.toArray());
        return page;
    }

    /**
     * 返回用户备案列表汇总
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<BsUserReportInfo> getBsUserReportInfoList(String loginName,
            String loginNickName, String loginOrg, String loginUserType,
            String begin, String end, String organization, String reportType) {
        List<BsUserReportInfo> list = null;
        List<Object> param = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer(" from BsUserReportInfo where 1=1 ");
        if (!"admin".equals(loginNickName)) {
            if ("".equals(organization)) {
                sb.append(" and orgCodeOfJr = ? ");
                param.add(loginOrg);
            } else {
                sb.append(" and orgCodeOfJr = ? ");
                param.add(organization);
            }
        } else {
            if (!"".equals(organization) && organization != null) {
                sb.append(" and orgCodeOfJr = ? ");
                param.add(organization);
            }
        }
        if (!"".equals(begin) && begin != null) {
            sb.append(" and reportDate > ? ");
            param.add(StringUtil.convert(begin));
        }
        if (!"".equals(end) && end != null) {
            sb.append(" and reportDate < ? ");
            param.add(StringUtil.convert(end));
        }
        if (!"".equals(reportType) && reportType != null) {
            sb.append(" and reportType = ? ");
            param.add(reportType);
        }
        sb.append(" order by reportDate asc");
        list = bsUserReportInfoDao.find(sb.toString(), param.toArray());
        return list;
    }

    // /**
    // * 返回启用用户列表,进行兼任信息添加
    // *
    // * @return
    // */
    // public Page getPluralUserList(String loginName, int pageNo, int pageSize)
    // {
    // StringBuffer sb = new StringBuffer(
    // " from BsUserInfo where CreaterCode = '" + loginName
    // + "' and UserStatus='启用' and nowUser='1'");
    // List<Object> param = new ArrayList<Object>();
    // sb.append(" order by CreateDate asc");
    // Page page = bsUserInfoDao.pagedQuery(sb.toString(), pageNo, pageSize,
    // param.toArray());
    // return page;
    // }

    /**
     * 返回待审核用户列表
     * 
     * @return
     */
    public Page getAuditUserList(String nowLoginUserName, String userName,
            String loginId, String organization, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(
                " from BsUserApplyInfo where flag='1'");
        List<Object> param = new ArrayList<Object>();
        sb.append(" and createrCode = ? ");
        param.add(nowLoginUserName);
        if (!"".equals(userName) && userName != null) {
            sb.append(" and name = ? ");
            param.add(userName);
        }
        if (!"".equals(loginId) && loginId != null) {
            sb.append(" and loginId = ? ");
            param.add(loginId);
        }
        if (!"".equals(organization) && organization != null) {
            sb.append(" and orgCodeOfJr = ? ");
            param.add(organization);
        }
        sb.append(" order by createDate asc");
        Page page = bsUserApplyInfoDao.pagedQuery(sb.toString(), pageNo,
                pageSize, param.toArray());
        return page;
    }

    /**
     * 返回申请用户列表
     * 
     * @return
     */
    public Page getApplyUserList(String nowLoginUserName, String userName,
            String loginId, String organization, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer(
                " from BsUserApplyInfo where flag='0'");
        List<Object> param = new ArrayList<Object>();
        sb.append(" and applyCode = ? ");
        param.add(nowLoginUserName);
        if (!"".equals(userName) && userName != null) {
            sb.append(" and name = ? ");
            param.add(userName);
        }
        if (!"".equals(loginId) && loginId != null) {
            sb.append(" and loginId = ? ");
            param.add(loginId);
        }
        if (!"".equals(organization) && organization != null) {
            sb.append(" and orgCodeOfJr = ? ");
            param.add(organization);
        }
        sb.append(" order by createDate asc");
        Page page = bsUserApplyInfoDao.pagedQuery(sb.toString(), pageNo,
                pageSize, param.toArray());
        return page;
    }

    /**
     * 返回用户ID
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getUserIdByLoginName(String loginName) {
        List list = null;
        list = jdbcTemplate
                .queryForList("select a.USER_ID as ID from JG_CREDENTIAL as a where a.CRED_NAME = 'login' and a.CRED_VALUE = '"
                        + loginName + "' ");
        try {
            ListOrderedMap map = (ListOrderedMap) list.get(0);
            String userId = String.valueOf(map.get("ID"));
            return userId;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    @Transactional(readOnly = false)
    public void deleteUser(BsUserInfoOfJg bsUserInfoOfJg) {
        jdbcTemplate.execute(" delete from BS_USERINFOOFJG where LOGINID='"
                + bsUserInfoOfJg.getLoginId() + "'");
        jdbcTemplate.execute(" delete from BS_USERINFOOFZX where LOGINID='"
                + bsUserInfoOfJg.getLoginId() + "'");
    }

    @Transactional(readOnly = false)
    public void cancelBsZxUserById(String id) {
        bsUserInfoOfZxDao.removeById(id);
    }

    public BsUserInfoOfJg getBsUserInfoOfJgById(String id) {
        return bsUserInfoOfJgDao.get(id);
    }

    // public BsUserPluralInfo getBsUserPluralInfoById(String id){
    // return bsUserPluralInfoDao.get(id);
    // }

    public BsUserInfoOfZx getBsUserInfoOfZxById(String id) {
        return bsUserInfoOfZxDao.get(id);
    }

    // @SuppressWarnings("unchecked")
    // public BsUserInfo getBsUserInfoByLoginId(String loginId) {
    // StringBuffer sb = new StringBuffer(
    // " from BsUserInfo where LoginId = '" + loginId
    // + "' and nowUser = '1' ");
    // List<BsUserInfo> list = bsUserInfoDao.find(sb.toString());
    // if(list.size()>0){
    // return list.get(0);
    // }else{
    // return null;
    // }
    // }

    @SuppressWarnings("unchecked")
    public BsUserInfoOfZx getBsUserInfoOfZxByLoginIdAndZXname(String loginId,
            String zxName) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfZx where LoginId = '" + loginId
                        + "' and ZxUserName = '" + zxName
                        + "' and nowUser = '1' ");
        List<BsUserInfoOfZx> list = bsUserInfoOfZxDao.find(sb.toString());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public BsUserInfoOfJg getBsUserInfoOfJgByLoginId(String loginId) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfJg where LoginId = '" + loginId
                        + "' and nowUser = '1' ");
        List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find(sb.toString());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public BsUserApplyInfo getBsUserApplyInfo(String loginId) {
        return bsUserApplyInfoDao.get(loginId);
    }

    public BsUserReportInfo getBsUserReportInfoById(String id) {
        return bsUserReportInfoDao.get(id);
    }

    @Transactional(readOnly = false)
    public void savaBsUserInfoOfJg(BsUserInfoOfJg bsUserInfoOfJg) {
        bsUserInfoOfJgDao.save(bsUserInfoOfJg);
    }

    @Transactional(readOnly = false)
    public void savaBsUserInfoOfZx(BsUserInfoOfZx bsUserInfoOfZx) {
        bsUserInfoOfZxDao.save(bsUserInfoOfZx);
    }

    @Transactional(readOnly = false)
    public void savaBsUserReportInfo(BsUserReportInfo bsUserReportInfo) {
        bsUserReportInfoDao.save(bsUserReportInfo);
    }

    @Transactional(readOnly = false)
    public void savaBsUserApplyInfo(BsUserApplyInfo bsUserApplyInfo) {
        bsUserApplyInfoDao.save(bsUserApplyInfo);
    }

    @Transactional(readOnly = false)
    public void delBsUserApplyInfo(BsUserApplyInfo bsUserApplyInfo) {
        bsUserApplyInfoDao.remove(bsUserApplyInfo);
    }

    @Transactional(readOnly = false)
    public void disableZxUserByUserId(String id) {
        jdbcTemplate
                .execute(" update BS_USERINFOOFZX set USERSTATUS='禁用' where ID='"
                        + id + "' and NOWUSER='1' ");
        Date date = new Date();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = df1.format(date);
        Timestamp CreateDate = Timestamp.valueOf(time);
        jdbcTemplate.execute(" update BS_USERINFOOFZX set STOPDATE='"
                + CreateDate + "' where ID='" + id
                + "'  and NOWUSER='1' and STOPDATE is null");
    }

    @Transactional(readOnly = false)
    public void enableZxUserByUserId(String userId) {
        jdbcTemplate
                .execute(" update BS_USERINFOOFZX set USERSTATUS='启用' where USERID='"
                        + userId + "' and NOWUSER='1'");
    }

    @SuppressWarnings("unchecked")
    public List<BsUserInfoOfZx> getUserZxInfo(String userId) {
        List<BsUserInfoOfZx> list = bsUserInfoOfZxDao
                .find(" from BsUserInfoOfZx where userId='" + userId
                        + "'  and NOWUSER='1'");
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<BsUserInfoOfJg> getBeforeUserInfoListByLoginId(String loginId) {
        StringBuffer sb = new StringBuffer(
                " from BsUserInfoOfJg where loginId = '" + loginId
                        + "' and nowUser='0'");
        List<BsUserInfoOfJg> list = bsUserInfoOfJgDao.find(sb.toString());
        return list;
    }

    @Transactional(readOnly = false)
    public void updateUserManager(String loginId, String oldLoginId) {
        jdbcTemplate.execute(" update BS_USERINFO set MANAGERCODE = '"
                + loginId + "' where MANAGERCODE = '" + oldLoginId + "'");
        jdbcTemplate.execute(" update BS_USERAPPLYINFO set CREATERCODE = '"
                + loginId + "' where CREATERCODE = '" + oldLoginId + "'");
        jdbcTemplate.execute(" update BS_USERPLURALINFO set LOGINID = '"
                + loginId + "' where LOGINID = '" + oldLoginId + "'");
    }

    public List getUserZxInfoTrOfApply(String id,String flag) {
    	List<String[]> list = new ArrayList();
    	String sbs[] = new String[9];
    	String flags[] = new String[9];
    	BsUserApplyInfo bs = bsUserApplyInfoDao.get(id);
        StringBuffer sb1 = new StringBuffer("");
        StringBuffer sb2 = new StringBuffer("");
        StringBuffer sb3 = new StringBuffer("");
        StringBuffer sb4 = new StringBuffer("");
        StringBuffer sb5 = new StringBuffer("");
        StringBuffer sb6 = new StringBuffer("");
        StringBuffer sb7 = new StringBuffer("");
        StringBuffer sb8 = new StringBuffer("");
        StringBuffer sb9 = new StringBuffer("");
        if (bs != null) {
            sb1.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName1' ");
            if(bs.getZxPrincipal().contains("企业征信管理员用户")){
            	sb1.append(" checked ");
            	flags[0] = "1";
            }else{
            	flags[0] = "0";
            }
            if("1".equals(flag)){
            	sb1.append(" disabled ");
            }
            sb1.append(" />企业征信管理员用户</td>");
            sbs[0] = sb1.toString();
            
            sb2.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName2' ");
            if(bs.getZxPrincipal().contains("企业征信查询用户")){
            	sb2.append(" checked ");
            	flags[1] = "1";
            }else{
            	flags[1] = "0";
            }
            if("1".equals(flag)){
            	sb2.append(" disabled ");
            }
            sb2.append(" />企业征信查询用户</td>");
            sbs[1] = sb2.toString();
            
            sb3.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName3' ");
            if(bs.getZxPrincipal().contains("企业征信数据报送用户")){
            	sb3.append(" checked ");
            	flags[2] = "1";
            }else{
            	flags[2] = "0";
            }
            if("1".equals(flag)){
            	sb3.append(" disabled ");
            }
            sb3.append(" />企业征信数据报送用户</td>");
            sbs[2] = sb3.toString();
            
            sb4.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName4' ");
            if(bs.getZxPrincipal().contains("企业征信异议处理用户")){
            	sb4.append(" checked ");
            	flags[3] = "1";
            }else{
            	flags[3] = "0";
            }
            if("1".equals(flag)){
            	sb4.append(" disabled ");
            }
            sb4.append(" />企业征信异议处理用户</td>");
            sbs[3] = sb4.toString();
            
            sb5.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName5' ");
            if(bs.getZxPrincipal().contains("个人征信管理员用户")){
            	sb5.append(" checked ");
            	flags[4] = "1";
            }else{
            	flags[4] = "0";
            }
            if("1".equals(flag)){
            	sb5.append(" disabled ");
            }
            sb5.append(" />个人征信管理员用户</td>");
            sbs[4] = sb5.toString();
            
            sb6.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName6' ");
            if(bs.getZxPrincipal().contains("个人征信查询用户")){
            	sb6.append(" checked ");
            	flags[5] = "1";
            }else{
            	flags[5] = "0";
            }
            if("1".equals(flag)){
            	sb6.append(" disabled ");
            }
            sb6.append(" />个人征信查询用户</td>");
            sbs[5] = sb6.toString();
            
            sb7.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName7' ");
            if(bs.getZxPrincipal().contains("个人征信数据报送用户")){
            	sb7.append(" checked ");
            	flags[6] = "1";
            }else{
            	flags[6] = "0";
            }
            if("1".equals(flag)){
            	sb7.append(" disabled ");
            }
            sb7.append(" />个人征信数据报送用户</td>");
            sbs[6] = sb7.toString();
            
            sb8.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName8' ");
            if(bs.getZxPrincipal().contains("个人征信异议处理用户")){
            	sb8.append(" checked ");
            	flags[7] = "1";
            }else{
            	flags[7] = "0";
            }
            if("1".equals(flag)){
            	sb8.append(" disabled ");
            }
            sb8.append(" />个人征信异议处理用户</td>");
            sbs[7] = sb8.toString();
            
            sb9.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName9' ");
            if(bs.getZxPrincipal().contains("其他")){
            	sb9.append(" checked ");
            	flags[8] = "1";
            }else{
            	flags[8] = "0";
            }
            if("1".equals(flag)){
            	sb9.append(" disabled ");
            }
            sb9.append(" />其他</td>");
            sbs[8] = sb9.toString();
            list.add(sbs);
            list.add(flags);
        }
        return list;
    }
//        BsUserApplyInfo bs = bsUserApplyInfoDao.get(id);
//        StringBuffer sb1 = new StringBuffer("");
//        StringBuffer sb2 = new StringBuffer("");
//        StringBuffer sb3 = new StringBuffer("");
//        StringBuffer sb4 = new StringBuffer("");
//        StringBuffer sb5 = new StringBuffer("");
//        StringBuffer sb6 = new StringBuffer("");
//        StringBuffer sb7 = new StringBuffer("");
//        StringBuffer sb8 = new StringBuffer("");
//        StringBuffer sb9 = new StringBuffer("");
//        if (bs != null) {
//            sb1.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName1' ");
//            if(bs.getZxPrincipal().contains("企业征信管理员用户")){
//            	sb1.append(" checked ");
//            }
//            sb1.append(" disabled />企业征信管理员用户</td>");
//            
//            sb2.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName2' ");
//            if(bs.getZxPrincipal().contains("企业征信查询用户")){
//            	sb2.append(" checked ");
//            }
//            sb2.append(" disabled />企业征信查询用户</td>");
//            
//            sb3.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName3' ");
//            if(bs.getZxPrincipal().contains("企业征信数据报送用户")){
//            	sb3.append(" checked ");
//            }
//            sb3.append(" disabled />企业征信数据报送用户</td>");
//            
//            sb4.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName4' ");
//            if(bs.getZxPrincipal().contains("企业征信异议处理用户")){
//            	sb4.append(" checked ");
//            }
//            sb4.append(" disabled />企业征信异议处理用户</td>");
//            
//            sb5.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName5' ");
//            if(bs.getZxPrincipal().contains("个人征信管理员用户")){
//            	sb5.append(" checked ");
//            }
//            sb5.append(" disabled />个人征信管理员用户</td>");
//            
//            sb6.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName6' ");
//            if(bs.getZxPrincipal().contains("个人征信查询用户")){
//            	sb6.append(" checked ");
//            }
//            sb6.append(" disabled />个人征信查询用户</td>");
//            
//            sb7.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName7' ");
//            if(bs.getZxPrincipal().contains("个人征信数据报送用户")){
//            	sb7.append(" checked ");
//            }
//            sb7.append(" disabled />个人征信数据报送用户</td>");
//            
//            sb8.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName8' ");
//            if(bs.getZxPrincipal().contains("个人征信异议处理用户")){
//            	sb8.append(" checked ");
//            }
//            sb8.append(" disabled />个人征信异议处理用户</td>");
//            
//            sb9.append("<td align='left' width='10%' height='25'><input type='checkbox' name='zxPrincipal' value='zxName9' ");
//            if(bs.getZxPrincipal().contains("其他")){
//            	sb9.append(" checked ");
//            }
//            sb9.append(" disabled />其他</td>");
//            return sb.toString();
//        }
            
//            <td class="tdstylebotton"><td align='center' width='30%'>征信系统用户名</td>")
//              .append("<td align='center' width='55%'>征信系统用户所属机构</td></tr>");
//            if (!"".equals(bs.getZxName1())) {
//                sb.append("<tr><td align='center'>企业征信管理员用户</td><td align='center'>")
//                  .append(bs.getZxName1()).append("</td><td align='center'>")
//                  .append(bs.getZxOrgName1()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName2())) {
//                sb
//                        .append(
//                                "<tr><td align='center'>企业征信查询用户</td><td align='center'>")
//                        .append(bs.getZxName2()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName2()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName3())) {
//                sb
//                        .append(
//                                "<tr><td align='center'>企业征信数据报送用户</td><td align='center'>")
//                        .append(bs.getZxName3()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName3()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName4())) {
//                sb
//                        .append(
//                                "<tr><td align='center'>企业征信异议处理用户</td><td align='center'>")
//                        .append(bs.getZxName4()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName4()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName5())) {
//                sb
//                        .append(
//                                "<tr><td align='center'>个人征信管理员用户</td><td align='center'>")
//                        .append(bs.getZxName5()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName5()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName6())) {
//                sb
//                        .append(
//                                "<tr><td align='center'>个人征信查询用户</td><td align='center'>")
//                        .append(bs.getZxName6()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName6()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName7())) {
//                sb
//                        .append(
//                                "<tr><td align='center'>个人征信数据报送用户</td><td align='center'>")
//                        .append(bs.getZxName7()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName7()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName8())) {
//                sb
//                        .append(
//                                "<tr><td align='center'>个人征信异议处理用户</td><td align='center'>")
//                        .append(bs.getZxName8()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName8()).append("</td></tr>");
//            }
//            if (!"".equals(bs.getZxName9())) {
//                sb.append("<tr><td align='center'>其他</td><td align='center'>")
//                        .append(bs.getZxName9()).append(
//                                "</td><td align='center'>").append(
//                                bs.getZxOrgName9()).append("</td></tr>");
//            }
//        }
//        return sb.toString();
//    }

    public String getUserZxInfoTr(String id, String flag) {
        StringBuffer sb = new StringBuffer();
        List<BsUserInfoOfZx> list = getUserZxInfo(id);
        BsUserInfoOfZx bs = new BsUserInfoOfZx();
        if (list.size() > 0) {
            sb
                    .append(
                            "<tr class='tabletext02'><td align='center'>征信系统用户类型</td><td align='center' width='20%'>征信系统用户名</td>")
                    .append(
                            "<td align='center' width='40%'>征信系统用户所属机构</td><td align='center' width='15%'>征信系统用户状态</td>");
            if ("0".equals(flag)) {
                sb.append("<td align='center' width='10%'>操作</td></tr>");
            } else {
                sb.append("</tr>");
            }
        }
        for (int j = 0; j < list.size(); j++) {
            bs = list.get(j);
            sb.append("<tr><td align='center'>").append(bs.getZxUserType())
                    .append("</td>");
            sb.append("<td align='center'>").append(bs.getZxUserName()).append(
                    "</td>");
            sb.append("<td align='center'>").append(bs.getOrgName()).append(
                    "</td>");
            sb.append("<td align='center'>").append(bs.getUserStatus()).append(
                    "</td>");
            if ("0".equals(flag)) {
                if ("启用".equals(bs.getUserStatus())) {
                    sb
                            .append("<td align='center'><input type='button' value='禁 用' class='botton01' onclick=\"return disabledZxUser('");
                } else {
                    sb
                            .append("<td align='center'><input type='button' value='启 用' class='botton01' onclick=\"return enabledZxUser('");
                }
                sb.append(bs.getId()).append("');\" /></td></tr>");

                // sb.append("<td align='center'><input type='button' value='删
                // 除' class='botton01' onclick=\"return cancelZxUser('")
                // .append(bs.getId()).append("');\" /></td></tr>");
            }
        }
        return sb.toString();
    }

    public String getUserZxInfoTrOfReport(String id) {
        // BsUserReportInfo bsUserReportInfo = this.getBsUserReportInfoById(id);
        // String userId = bsUserReportInfo.getUserId();
        StringBuffer sb = new StringBuffer();
        // List<BsUserInfoOfZx> list = getUserZxInfo(userId);
        // BsUserInfoOfZx bs = new BsUserInfoOfZx();
        // if(list.size()>0){
        // sb.append("<tr class='tabletext02'><td
        // align='center'>征信系统用户类型</td><td align='center'
        // width='25%'>征信系统用户名</td>")
        // .append("<td align='center' width='50%'>征信系统用户所属机构</td></tr>");
        // }
        // for(int j=0;j<list.size();j++){
        // bs = list.get(j);
        // sb.append("<tr><td
        // align='center'>").append(bs.getZxUserType()).append("</td>");
        // sb.append("<td
        // align='center'>").append(bs.getZxUserName()).append("</td>");
        // sb.append("<td
        // align='center'>").append(bs.getOrgName()).append("</td></tr>");
        // }
        return sb.toString();
    }

    public String getUserInfoTr(BsUserInfoOfJg bsUserInfoOfJg,
            List principalsList, Collection tempCol) {
        String loginId = bsUserInfoOfJg.getLoginId();
        StringBuffer sb = new StringBuffer();
        sb
                .append(
                        "<tr><td align='right' class='tabletext02' width='15%'>用户姓名</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getName())).append(
                        "&nbsp</td></tr>");
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>监管系统用户类型</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getUserType())).append(
                        "&nbsp</td></tr>");
        if ("内控监督员".equals(bsUserInfoOfJg.getUserType())) {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>员工号</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getLoginId()))
                    .append("&nbsp</td></tr>");
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>职务</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getPost())).append(
                            "&nbsp</td></tr>");
        } else {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>证件类型</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getCardType()))
                    .append("&nbsp</td></tr>");
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>证件号码</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getLoginId()))
                    .append("&nbsp</td></tr>");
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>最高学历</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getEducation()))
                    .append("&nbsp</td></tr>");
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>联系电话</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getPhone())).append(
                            "&nbsp</td></tr>");
        }
        sb
        		.append(
                		"<tr><td align='right' class='tabletext02'>用户使用者身份</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getUserCard())).append(
                		"&nbsp</td></tr>");
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>用户状态</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getUserStatus())).append(
                        "&nbsp</td></tr>");
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>监管系统用户所在机构名称</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getOrgName())).append(
                        "&nbsp</td></tr>");
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>监管系统用户所在机构编码</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getOrgCodeOfJr()))
                .append("&nbsp</td></tr>");
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>机构信用编码</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getOrgCodeOfXy()))
                .append("&nbsp</td></tr>");
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>组织机构编码</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getOrgCodeOfZz()))
                .append("&nbsp</td></tr>");
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>监管系统用户所在部门</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getDeptName())).append(
                        "&nbsp</td></tr>");
        sb.append("<tr><td align='right' class='tabletext02'>用户照片</td>");
        if (!"".equals(bsUserInfoOfJg.getPhoto())
                && bsUserInfoOfJg.getPhoto() != null) {
            BsPhoto bsPhoto = new BsPhoto();
            bsPhoto = fileHandler.getBsPhoto(bsUserInfoOfJg.getPhoto());
            sb.append("<td colspan='3'><img src='\\csims\\").append(
                    bsPhoto.getPhotopath()).append(
                    "'  width='160px' height='120px' /></td></tr>");
        } else {
            sb.append("<td colspan='3'>未上传照片</td></tr>");
        }
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>创建者姓名</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getCreaterName()))
                .append("&nbsp</td></tr>");
        if (bsUserInfoOfJg.getCreateDate() == null
                || "".equals(bsUserInfoOfJg.getCreateDate())) {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>创建日期</td><td colspan='3'>")
                    .append("&nbsp</td></tr>");
        } else {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>创建日期</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getCreateDate()))
                    .append("&nbsp</td></tr>");
        }
        sb
                .append(
                        "<tr><td align='right' class='tabletext02'>停用者姓名</td><td colspan='3'>")
                .append(String.valueOf(bsUserInfoOfJg.getStoperName())).append(
                        "&nbsp</td></tr>");
        if (bsUserInfoOfJg.getStopDate() == null
                || "".equals(bsUserInfoOfJg.getStopDate())) {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>停用日期</td><td colspan='3'>")
                    .append("&nbsp</td></tr>");
        } else {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>停用日期</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getStopDate()))
                    .append("&nbsp</td></tr>");
        }
        if (bsUserInfoOfJg.getLoginDate() == null) {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>最近登录日期</td><td colspan='3'>")
                    .append("&nbsp</td></tr>");
        } else {
            sb
                    .append(
                            "<tr><td align='right' class='tabletext02'>最近登录日期</td><td colspan='3'>")
                    .append(String.valueOf(bsUserInfoOfJg.getLoginDate()))
                    .append("&nbsp</td></tr>");
        }
        sb
                .append("<tr><td align='right' class='tabletext02'>监管系统用户角色</td><td colspan='3'><table>");
		List returnList = new ArrayList();
		for (Iterator<?> it = tempCol.iterator(); it.hasNext();) {
			RolePrincipal principal = (RolePrincipal) it.next();
			returnList.add(principal);
		}
		PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
        List<?> principals = (List<?>) principalsList;
        for (Iterator<?> it = returnList.iterator(); it.hasNext();) {
            RolePrincipal principal = (RolePrincipal) it.next();
            if (principal.getLocalName().trim().equalsIgnoreCase("admin")
                    || principal.getLocalName().trim().equalsIgnoreCase(
                            "systemadmin")
                    || principal.getLocalName().trim()
                            .equalsIgnoreCase("guest")) {
                continue;
            }
            sb.append("<tr>");
            sb.append("<td align='left' width='3%'>");
            sb
                    .append("<input id='selectPrincipal' name='selectPrincipal' type='checkbox' value='");
            sb.append(principal.getName());
            sb.append("' ");
            if (principals != null && principals.size() > 0) {
                for (int i = 0; i < principals.size(); i++) {
                    if (principals.get(i).equals(principal.getName())) {
                        sb.append(" checked='checked' disabled");
                        break;
                    } else {
                        sb.append(" disabled");
                        break;
                    }
                }
            }
            sb.append("/></td>");
            sb.append("<td align='left' width='80%'>");
            sb.append(principal.getLocalName());
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table></td></tr>");
        List<BsUserInfoOfZx> list = getUserZxInfo(bsUserInfoOfJg.getId());
        BsUserInfoOfZx bs = new BsUserInfoOfZx();
        if (list.size() > 0) {
            sb
                    .append(
                            "<tr class='tabletext02'><td align='center'>征信系统用户类型</td><td align='center' width='20%'>征信系统用户名</td>")
                    .append(
                            "<td align='center' width='50%'>征信系统用户所属机构</td><td align='center' width='15%'>征信系统用户状态</td></tr>");
        }
        for (int j = 0; j < list.size(); j++) {
            bs = list.get(j);
            sb.append("<tr><td align='center'>").append(bs.getZxUserType())
                    .append("</td>");
            sb.append("<td align='center'>").append(bs.getZxUserName()).append(
                    "</td>");
            sb.append("<td align='center'>").append(bs.getOrgName()).append(
                    "</td>");
            sb.append("<td align='center'>").append(bs.getUserStatus()).append(
                    "</td></tr>");
        }

        List<BsUserInfoOfJg> userList = getBeforeUserInfoListByLoginId(loginId);
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                BsUserInfoOfJg bsBeforeUserInfo = userList.get(i);
                sb
                        .append(
                                "<tr class='tabletext02'><td align='center' colspan='4'>工作履历-")
                        .append(i + 1).append("</td></tr>");
                sb
                        .append(
                                "<tr><td align='right' class='tabletext02'>用户姓名</td><td colspan='3'>")
                        .append(String.valueOf(bsBeforeUserInfo.getName()))
                        .append("&nbsp</td></tr>");
                sb
                        .append(
                                "<tr><td align='right' class='tabletext02'>监管系统用户类型</td><td colspan='3'>")
                        .append(String.valueOf(bsBeforeUserInfo.getUserType()))
                        .append("&nbsp</td></tr>");
                if ("内控监督员".equals(bsBeforeUserInfo.getUserType())) {
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>员工号</td><td>")
                            .append(
                                    String.valueOf(bsBeforeUserInfo
                                            .getLoginId())).append(
                                    "&nbsp</td></tr>");
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>职务</td><td>")
                            .append(String.valueOf(bsBeforeUserInfo.getPost()))
                            .append("&nbsp</td></tr>");
                } else {
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>证件类型</td><td colspan='3'>")
                            .append(
                                    String.valueOf(bsBeforeUserInfo
                                            .getCardType())).append(
                                    "&nbsp</td></tr>");
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>证件号码</td><td colspan='3'>")
                            .append(
                                    String.valueOf(bsBeforeUserInfo
                                            .getLoginId())).append(
                                    "&nbsp</td></tr>");
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>最高学历</td><td colspan='3'>")
                            .append(
                                    String.valueOf(bsBeforeUserInfo
                                            .getEducation())).append(
                                    "&nbsp</td></tr>");
                }
                sb
                        .append(
                                "<tr><td align='right' class='tabletext02'>所在机构名称</td><td colspan='3'>")
                        .append(String.valueOf(bsBeforeUserInfo.getOrgName()))
                        .append("&nbsp</td></tr>");
                sb
                        .append(
                                "<tr><td align='right' class='tabletext02'>所在部门</td><td colspan='3'>")
                        .append(String.valueOf(bsBeforeUserInfo.getDeptName()))
                        .append("&nbsp</td></tr>");
                if (bsBeforeUserInfo.getCreateDate() == null
                        || "".equals(bsBeforeUserInfo.getCreateDate())) {
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>创建日期</td><td colspan='3'>")
                            .append("&nbsp").append("&nbsp</td></tr>");
                } else {
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>创建日期</td><td colspan='3'>")
                            .append(
                                    String.valueOf(bsBeforeUserInfo
                                            .getCreateDate())).append(
                                    "&nbsp</td></tr>");
                }
                sb
                        .append(
                                "<tr><td align='right' class='tabletext02'>创建人</td><td colspan='3'>")
                        .append(
                                String.valueOf(bsBeforeUserInfo
                                        .getCreaterName())).append(
                                "&nbsp</td></tr>");
                if (bsBeforeUserInfo.getStopDate() == null
                        || "".equals(bsBeforeUserInfo.getStopDate())) {
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>停用日期</td><td colspan='3'>")
                            .append("&nbsp").append("&nbsp</td></tr>");
                } else {
                    sb
                            .append(
                                    "<tr><td align='right' class='tabletext02'>停用日期</td><td colspan='3'>")
                            .append(
                                    String.valueOf(bsBeforeUserInfo
                                            .getStopDate())).append(
                                    "&nbsp</td></tr>");
                }
                sb
                        .append(
                                "<tr><td align='right' class='tabletext02'>停用人</td><td colspan='3'>")
                        .append(
                                String
                                        .valueOf(bsBeforeUserInfo
                                                .getStoperName())).append(
                                "&nbsp</td></tr>");
                List<BsUserInfoOfZx> beforeList = getUserZxInfo(bsBeforeUserInfo
                        .getId());
                if (beforeList.size() > 0) {
                    sb
                            .append(
                                    "<tr class='tabletext02'><td align='center'>征信系统用户类型</td><td align='center' width='20%'>征信系统用户名</td>")
                            .append(
                                    "<td align='center' width='50%'>征信系统用户所属机构</td><td align='center' width='15%'>征信系统用户状态</td></tr>");
                }
                BsUserInfoOfZx beforeBs = new BsUserInfoOfZx();
                for (int k = 0; k < beforeList.size(); k++) {
                    bs = list.get(k);
                    sb.append("<tr><td align='center'>").append(
                            beforeBs.getZxUserType()).append("</td>");
                    sb.append("<td align='center'>").append(
                            beforeBs.getZxUserName()).append("</td>");
                    sb.append("<td align='center'>").append(
                            beforeBs.getOrgName()).append("</td>");
                    sb.append("<td align='center'>").append(
                            beforeBs.getUserStatus()).append("</td></tr>");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据组装后的结果集生成Excel POI 对象
     * 
     * @param map
     * @param keyValueMap
     * @return
     */
    public HSSFWorkbook generateExcel(Map<String, String> keyValueMap) {
        int x = 17;
        int y = keyValueMap.size() / x;
        // 根据报表序号获取带有表头的Excel POI 对象
        HSSFWorkbook wb = this.fillExcelTitle();
        HSSFSheet sheet0 = wb.getSheetAt(0);
        // 如果行数超过1行，需要复制空白行供数据填充
        if (y > 1) {
            HSSFRow row = sheet0.getRow(TABLEDATA_BEGIN_Y[0]);
            for (int count = 0; count < y - 1; count++) {
                HSSFRow rowNew = sheet0.createRow(TABLEDATA_BEGIN_Y[0] + 1
                        + count);
                if (row != null) {
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        HSSFCell cellNew = rowNew.createCell(i);
                        HSSFCell cell = row.getCell(i);
                        if (cell != null) {
                            cellNew.setCellType(cell.getCellType());
                            // cellNew.setCellValue(cell.get);
                            cellNew.setCellStyle(cell.getCellStyle());
                        }
                    }
                }
            }

        }
        // 填充单元格数据
        String cellValue = "";
        HSSFCellStyle titleStyle = sheet0.getRow(0).getCell(0).getCellStyle();
        HSSFCellStyle sumStyle = wb.createCellStyle();
        sumStyle.cloneStyleFrom(titleStyle);
        sumStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        sumStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        sumStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        sumStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        for (int i = 1; i <= y; i++) {
            for (int j = 1; j <= x; j++) {
                cellValue = keyValueMap.get(i + "-" + j);
                sheet0.getRow(TABLEDATA_BEGIN_Y[0] + (i - 1)).getCell(
                        TABLEDATA_BEGIN_X[0] + (j - 1)).setCellValue(
                        new HSSFRichTextString(cellValue));
            }
        }
        return wb;
    }

    /**
     * 根据组装后的结果集生成Excel POI 对象
     * 
     * @param map
     * @param keyValueMap
     * @return
     */
    public HSSFWorkbook generateExcelOfUserInfoQueryResult(
            Map<String, String> keyValueMap, String flag) {
        int x = 5;
        int y = keyValueMap.size() / 5;
        if ("1".equals(flag)) {
            x = 4;
            y = keyValueMap.size() / 4;
        }
        // 根据报表序号获取带有表头的Excel POI 对象
        HSSFWorkbook wb = this.fillExcelTitleOfUserInfoQueryResult(flag);
        HSSFSheet sheet0 = wb.getSheetAt(0);
        // 如果行数超过1行，需要复制空白行供数据填充
        if (y > 1) {
            HSSFRow row = sheet0.getRow(TABLEDATA_BEGIN_Y[0]);
            for (int count = 0; count < y - 1; count++) {
                HSSFRow rowNew = sheet0.createRow(TABLEDATA_BEGIN_Y[0] + 1
                        + count);
                if (row != null) {
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        HSSFCell cellNew = rowNew.createCell(i);
                        HSSFCell cell = row.getCell(i);
                        if (cell != null) {
                            cellNew.setCellType(cell.getCellType());
                            // cellNew.setCellValue(cell.get);
                            cellNew.setCellStyle(cell.getCellStyle());
                        }
                    }
                }
            }

        }
        // 填充单元格数据
        String cellValue = "";
        HSSFCellStyle titleStyle = sheet0.getRow(0).getCell(0).getCellStyle();
        HSSFCellStyle sumStyle = wb.createCellStyle();
        sumStyle.cloneStyleFrom(titleStyle);
        sumStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        sumStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        sumStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        sumStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        for (int i = 1; i <= y; i++) {
            for (int j = 1; j <= x; j++) {
                cellValue = keyValueMap.get(i + "-" + j);
                sheet0.getRow(TABLEDATA_BEGIN_Y[0] + (i - 1)).getCell(
                        TABLEDATA_BEGIN_X[0] + (j - 1)).setCellValue(
                        new HSSFRichTextString(cellValue));
            }
        }
        return wb;
    }

    public HSSFWorkbook fillExcelTitleOfUserInfoQueryResult(String flag) {
        String modelName = "excelModelDownloadOfUserInfoQueryResult.xls";
        if ("1".equals(flag)) {
            modelName = "excelModelDownloadOfUserInfoQueryResultOfJg.xls";
        }
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(
                    "com/gtm/csims/jaas/service/impl/excelModel/" + modelName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public HSSFWorkbook fillExcelTitle() {
        String modelName = "excelModelDownload.xls";
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(
                    "com/gtm/csims/jaas/service/impl/excelModel/" + modelName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public void delBsUserPluralInfoById(String id) {
        // TODO Auto-generated method stub

    }

    public String getAepeople(String orgNo, String deptNo) {
        // TODO Auto-generated method stub
        return null;
    }

    public List getPepByOrgNoandDeptNoForSelect(String orgNo, String deptNo) {
        // TODO Auto-generated method stub
        return null;
    }

    public Page getPluralUserList(String loginName, int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    // public String getUserInfoTrForPlural(BsUserInfo bsUserInfo,List
    // principalsList,Collection tempCol){
    // StringBuffer sb = new StringBuffer();
    // sb.append("<tr><td align='right' class='tabletext02'
    // width='20%'>用户姓名</td><td>")
    // .append(String.valueOf(bsUserInfo.getName()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>证件类型</td><td>")
    // .append(String.valueOf(bsUserInfo.getCardType()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>证件号码</td><td>")
    // .append(String.valueOf(bsUserInfo.getCardId()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>监管系统用户类型</td><td>")
    // .append(String.valueOf(bsUserInfo.getUserType()))
    // .append("&nbsp</td></tr>");
    // if (bsUserInfo.getUserType().equals("内控监督员")) {
    // sb.append("<tr><td align='right' class='tabletext02'>员工号</td><td>")
    // .append(String.valueOf(bsUserInfo.getUserCode()))
    // .append("&nbsp</td></tr>");
    // }
    // sb.append("<tr><td align='right'
    // class='tabletext02'>征信系统用户类型</td><td><table>");
    // String zxName = "";
    // zxName = bsUserInfo.getZxName1();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<td colspan='2' align='left' width='3%'>><input
    // type='checkbox' name='zxPrincipalnow' disabled />企业征信管理员用户</td>");
    // } else {
    // sb.append("<td width='70%'><input type='checkbox' name='zxPrincipalnow'
    // checked disabled />企业征信管理员用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName2();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />企业征信查询用户</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />企业征信查询用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName3();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />企业征信数据报送用户</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />企业征信数据报送用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName4();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />企业征信异议处理用户</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />企业征信异议处理用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName5();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />个人征信管理员用户</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />个人征信管理员用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName6();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />个人征信查询用户</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />个人征信查询用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName7();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />个人征信数据报送用户</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />个人征信数据报送用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName8();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />个人征信异议处理用户</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />个人征信异议处理用户</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // zxName = bsUserInfo.getZxName9();
    // sb.append("<tr>");
    // if ("".equals(zxName) || zxName==null) {
    // sb.append("<tr><td colspan='2'><input type='checkbox'
    // name='zxPrincipalnow' disabled />其他</td>");
    // } else {
    // sb.append("<tr><td><input type='checkbox' name='zxPrincipalnow' checked
    // disabled />其他</td>")
    // .append("<td>征信系统用户名:")
    // .append(zxName)
    // .append("</td>");
    // }
    // sb.append("</tr>");
    // sb.append("</table></td></tr>");
    //		
    // sb.append("<tr><td align='right'
    // class='tabletext02'>监管系统用户角色</td><td><table>");
    // Set<?> allPrincipals = (Set<?>) tempCol;
    // List<?> principals = (List<?>) principalsList;
    // for (Iterator<?> it = allPrincipals.iterator(); it.hasNext();) {
    // RolePrincipal principal = (RolePrincipal) it.next();
    // if (principal.getLocalName().trim().equalsIgnoreCase(
    // "admin")
    // || principal.getLocalName().trim()
    // .equalsIgnoreCase("systemadmin")
    // || principal.getLocalName().trim()
    // .equalsIgnoreCase("guest")) {
    // continue;
    // }
    // sb.append("<tr>");
    // sb.append("<td align='left' width='3%'>");
    // sb
    // .append("<input id='selectPrincipal' name='selectPrincipal'
    // type='checkbox' value='");
    // sb.append(principal.getName());
    // sb.append("' ");
    // if (principals != null && principals.size() > 0) {
    // for (int i = 0; i < principals.size(); i++) {
    // if (principals.get(i).equals(
    // principal.getName())) {
    // sb.append(" checked='checked' disabled");
    // break;
    // }else{
    // sb.append(" disabled");
    // break;
    // }
    // }
    // }
    // sb.append("/></td>");
    // sb.append("<td align='left' width='80%'>");
    // sb.append(principal.getLocalName());
    // sb.append("</td>");
    // sb.append("</tr>");
    // }
    // sb.append("</table></td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>最高学历</td><td>")
    // .append(String.valueOf(bsUserInfo.getEducation()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>职务</td><td>")
    // .append(String.valueOf(bsUserInfo.getPost()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>联系电话</td><td>")
    // .append(String.valueOf(bsUserInfo.getPhone()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>所在机构名称</td><td>")
    // .append(String.valueOf(bsUserInfo.getOrgName()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>所在机构编码</td><td>")
    // .append(String.valueOf(bsUserInfo.getOrgCodeOfJr()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>所在机构信用编码</td><td>")
    // .append(String.valueOf(bsUserInfo.getOrgCodeOfXy()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>所在组织机构编码</td><td>")
    // .append(String.valueOf(bsUserInfo.getOrgCodeOfZz()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>所在部门名称</td><td>")
    // .append(String.valueOf(bsUserInfo.getDeptName()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>用户照片</td>");
    // if(!"".equals(bsUserInfo.getPhoto()) && bsUserInfo.getPhoto()!=null){
    // BsPhoto bsPhoto = new BsPhoto();
    // bsPhoto = fileHandler.getBsPhoto(bsUserInfo.getPhoto());
    // sb.append("<td colspan='4'><img src='\\csims\\")
    // .append(bsPhoto.getPhotopath())
    // .append("' width='160px' height='120px' /></td></tr>");
    // }else{
    // sb.append("<td>未上传照片</td></tr>");
    // }
    // sb.append("<tr><td align='right' class='tabletext02'>用户状态</td><td>")
    // .append(String.valueOf(bsUserInfo.getUserStatus()))
    // .append("&nbsp</td></tr>");
    // sb.append("<tr><td align='right' class='tabletext02'>创建者姓名</td><td>")
    // .append(String.valueOf(bsUserInfo.getCreaterName()))
    // .append("&nbsp</td></tr>");
    // if (bsUserInfo.getCreateDate() == null) {
    // sb.append("<tr><td align='right' class='tabletext02'>创建日期</td><td>")
    // .append("&nbsp</td></tr>");
    // }else{
    // sb.append("<tr><td align='right' class='tabletext02'>创建日期</td><td>")
    // .append(String.valueOf(bsUserInfo.getCreateDate()))
    // .append("&nbsp</td></tr>");
    // }
    // if (bsUserInfo.getLoginDate() == null) {
    // sb.append("<tr><td align='right' class='tabletext02'>最近登录日期</td><td>")
    // .append("&nbsp</td></tr>");
    // } else {
    // sb.append("<tr><td align='right' class='tabletext02'>最近登录日期</td><td>")
    // .append(String.valueOf(bsUserInfo.getLoginDate()))
    // .append("&nbsp</td></tr>");
    // }
    // List<BsUserPluralInfo> list =
    // getBsUserPluralInfoListByUserIdOfNow(bsUserInfo.getId());
    // if (list.size() > 0) {
    // sb.append("<tr><td colspan='2'><table width='100%' border='0'
    // cellpadding='0' cellspacing='0' class='tableline01'>")
    // .append("<tr class='tabletext02'><td align='center'>征信系统用户名</td>")
    // .append("<td align='center'>征信系统用户类型</td><td align='center'>兼任机构名称</td>")
    // .append("<td align='center'>兼任部门名称</td><td align='center'>兼任状态</td>")
    // .append("<td align='center'>创建时间</td><td align='center'>停用时间</td><td
    // align='center'>操作</td></tr>");
    // for (int i = 0; i < list.size(); i++) {
    // BsUserPluralInfo bs = list.get(i);
    // sb.append("<tr><td align='center'>")
    // .append(bs.getZxName())
    // .append("</td><td align='center'>")
    // .append(bs.getZxPrincipal())
    // .append("</td><td align='center'>")
    // .append(bs.getOrgName())
    // .append("</td><td align='center'>")
    // .append(bs.getDeptName())
    // .append("</td><td align='center'>")
    // .append(bs.getUserStatus())
    // .append("</td><td align='center'>")
    // .append(bs.getCreateDate())
    // .append("</td><td align='center'>");
    // if(bs.getStopDate()==null){
    // sb.append("");
    // }else{
    // sb.append(bs.getStopDate());
    // }
    // if(bs.getUserStatus().equals("启用")){
    // sb.append("</td><td align='center'><input type='button' value='禁 用'
    // class='botton01' onclick=\"return disabledUserPlural('");
    // }else{
    // sb.append("</td><td align='center'><input type='button' value='启 用'
    // class='botton01' onclick=\"return enabledUserPlural('");
    // }
    // sb.append(bs.getId()).append("');\" /></td></tr>");
    // }
    // sb.append("</table></td></tr>");
    // }
    // return sb.toString();
    // }

    @Transactional(readOnly = false)
    public void savaUserStopInfo(HttpServletRequest request, String loginId,
            String nowLoginUserName, InputStream is, String endRow) {
        HSSFWorkbook wb = null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int i = 0; i < Integer.parseInt(endRow); i++) {
                ExcelRow _excelRow = new ExcelRow();
                _excelRow.setRowA(ExcelUtil
                        .getValue(sheet.getRow(i).getCell(0)));
                _excelRow.setRowB(ExcelUtil
                        .getValue(sheet.getRow(i).getCell(1)));
                String stopedLoginId = _excelRow.getRowA();
                String stopedZXname = _excelRow.getRowB();
                BsUserInfoOfZx bsUserInfoOfZx = this
                        .getBsUserInfoOfZxByLoginIdAndZXname(stopedLoginId,
                                stopedZXname);
                BsUserInfoOfJg bsUserInfoOfJg = this
                        .getBsUserInfoOfJgByLoginId(stopedLoginId);
                String id = bsUserInfoOfZx.getId();
                Date date = new Date();
                this.disableZxUserByUserId(id);
                BsOrg bsOrg = systemBaseInfoManager.getOrgByNo(bsUserInfoOfZx
                        .getOrgCode());
                List<BsUserInfoOfZx> list = this.getUserZxInfo(id);
                for (int j = 0; j < list.size(); j++) {
                    BsUserReportInfo bsUserReportInfo = new BsUserReportInfo();
                    BsUserInfoOfZx bs = list.get(j);
                    String zxXtName = "";
                    String zxXtUserType = "";
                    String zxUserType = bs.getZxUserType();
                    if (zxUserType.indexOf("企业") >= 0) {
                        zxXtName = "企业征信";
                    } else {
                        zxXtName = "个人征信";
                    }
                    if (zxUserType.indexOf("管理") >= 0) {
                        zxXtUserType = "管理员用户";
                    }
                    if (zxUserType.indexOf("数据报送用户") >= 0) {
                        zxXtUserType = "数据报送用户";
                    }
                    if (zxUserType.indexOf("查询") >= 0) {
                        zxXtUserType = "查询用户";
                    }
                    if (zxUserType.indexOf("异议处理用户") >= 0) {
                        zxXtUserType = "异议处理用户";
                    }
                    bsUserReportInfo.setUserZxName(bs.getZxUserName());
                    bsUserReportInfo.setUserZxOrgName(bs.getOrgName());
                    bsUserReportInfo.setZxXtName(zxXtName);
                    bsUserReportInfo.setZxXtUserType(zxXtUserType);
                    bsUserReportInfo.setLoginId(bsUserInfoOfZx.getLoginId());
                    bsUserReportInfo.setName(bsUserInfoOfJg.getName());
                    bsUserReportInfo.setOrgCodeOfJr(bsUserInfoOfJg
                            .getOrgCodeOfJr());
                    bsUserReportInfo.setOrgCodeOfXy(bsUserInfoOfJg
                            .getOrgCodeOfXy());
                    bsUserReportInfo.setOrgCodeOfZz(bsUserInfoOfJg
                            .getOrgCodeOfZz());
                    bsUserReportInfo.setOrgName(bsUserInfoOfJg.getOrgName());
                    bsUserReportInfo.setDeptNo(bsUserInfoOfJg.getDeptNo());
                    bsUserReportInfo.setDeptName(bsUserInfoOfJg.getDeptName());
                    bsUserReportInfo.setPhone(bsUserInfoOfJg.getPhone());
                    bsUserReportInfo.setReportDate(new Date());
                    bsUserReportInfo.setReportType("禁用用户");
                    bsUserReportInfo.setCreaterName(nowLoginUserName);
                    bsUserReportInfo.setApplyName(bsUserInfoOfJg.getApplyer());
                    bsUserReportInfo
                            .setEducation(bsUserInfoOfJg.getEducation());
                    bsUserReportInfo.setC(bsOrg.getQ());
                    bsUserReportInfo.setP(bsOrg.getO());
                    this.savaBsUserReportInfo(bsUserReportInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("读取Excel失败");
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    class ExcelRow {
        private String rowA;
        private String rowB;

        public String getRowA() {
            return rowA;
        }

        public void setRowA(String rowA) {
            this.rowA = rowA;
        }

        private String getRowB() {
            return rowB;
        }

        private void setRowB(String rowB) {
            this.rowB = rowB;
        }
    }

    public StringBuffer statisticsByArea() {
        StringBuffer sb = new StringBuffer();
        sb
                .append("<tr><td align='center' width='50%'>地区</td><td align='center'>总数</td><tr>");
        String areaName = "";
        int total;
        List list = (List) jdbcTemplate.queryForList(
                "select  distinct(P) FROM BS_ORG where Q is not null",
                new Object[] {}, java.lang.String.class);
        for (int i = 0; i < list.size(); i++) {
            String areaNo = (String) list.get(i);
            List list1 = (List) jdbcTemplate.queryForList(
                    "select  Q FROM BS_ORG where P='" + areaNo + "'",
                    new Object[] {}, java.lang.String.class);
            for (int j = 0; j < list1.size(); j++) {
                areaName = (String) list1.get(j);
            }
            total = jdbcTemplate
                    .queryForInt(" select count(*) from BS_USERINFOOFJG where ORGCODEOFJR in (select NO from BS_ORG where P='"
                            + areaNo + "')");
            sb.append("<tr><td align='center'>");
            sb.append("<a href='./User.do?method=userStatisticsByArea&flag=")
                    .append(areaNo).append("'>").append(areaName)
                    .append("</a>").append("</td>");
            sb.append("<td align='center'>").append(total).append("</td></tr>");
        }
        return sb;
    }

    public StringBuffer statisticsByAreaNo(String areaNo) {
        StringBuffer sb = new StringBuffer();
        sb
                .append("<tr><td align='center' width='50%'>地区</td><td align='center'>总数</td><tr>");
        String areaName = "";
        int total;
        List list = (List) jdbcTemplate.queryForList(
                "select  distinct(R) FROM BS_ORG where P='" + areaNo + "'",
                new Object[] {}, java.lang.String.class);
        for (int i = 0; i < list.size(); i++) {
            String areaNo1 = (String) list.get(i);
            List list1 = (List) jdbcTemplate.queryForList(
                    "select  S FROM BS_ORG where R='" + areaNo1 + "'",
                    new Object[] {}, java.lang.String.class);
            for (int j = 0; j < list1.size(); j++) {
                areaName = (String) list1.get(j);
            }
            total = jdbcTemplate
                    .queryForInt(" select count(*) from BS_USERINFOOFJG where ORGCODEOFJR in (select NO from BS_ORG where R='"
                            + areaNo1 + "')");
            sb.append("<tr><td align='center'>");
            sb.append(areaName).append("</td>");
            sb.append("<td align='center'>").append(total).append("</td></tr>");
        }
        return sb;
    }

    public StringBuffer statisticsByPrincipal() {
        StringBuffer sb = new StringBuffer();
        sb
                .append("<tr><td align='center' width='50%'>用户类型</td><td align='center'>总数</td><tr>");
        int total;
        List list = (List) jdbcTemplate
                .queryForList(
                        "select  distinct(ZXUSERTYPE) FROM BS_USERINFOOFZX where ZXUSERTYPE is not null",
                        new Object[] {}, java.lang.String.class);
        for (int i = 0; i < list.size(); i++) {
            String userPrincipal = (String) list.get(i);
            total = jdbcTemplate
                    .queryForInt(" select count(*) from BS_USERINFOOFZX where ZXUSERTYPE ='"
                            + userPrincipal + "' ");
            sb.append("<tr><td align='center'>");
            sb.append(userPrincipal).append("</td>");
            sb.append("<td align='center'>").append(total).append("</td></tr>");
        }
        return sb;
    }

    public StringBuffer statisticsByOrg() {
        StringBuffer sb = new StringBuffer();
        sb
                .append("<tr><td align='center' width='50%'>机构名称</td><td align='center'>总数</td><tr>");
        String name = "";
        int total;
        List list = (List) jdbcTemplate
                .queryForList(
                        "select  distinct(NO) FROM BS_ORG where NO is not null and parentno='0'",
                        new Object[] {}, java.lang.String.class);
        for (int i = 0; i < list.size(); i++) {
            String no = (String) list.get(i);
            List list1 = (List) jdbcTemplate.queryForList(
                    "select  NAME FROM BS_ORG where NO='" + no + "'",
                    new Object[] {}, java.lang.String.class);
            for (int j = 0; j < list1.size(); j++) {
                name = (String) list1.get(j);
            }
            String orgNos = systemBaseInfoManager.getAllChildOfOrg(no);
            total = jdbcTemplate
                    .queryForInt(" select count(*) from BS_USERINFOOFJG where ORGCODEOFJR in ("
                            + orgNos + ")");
            sb.append("<tr><td align='center'>");
            sb.append("<a href='./User.do?method=userStatisticsByOrg&flag=")
                    .append(no).append("'>").append(name).append("</a>")
                    .append("</td>");
            sb.append("<td align='center'>").append(total).append("</td></tr>");
        }
        return sb;
    }

    public StringBuffer statisticsByParentOrgNo(String orgNo) {
        StringBuffer sb = new StringBuffer();
        sb
                .append("<tr><td align='center' width='50%'>机构名称</td><td align='center'>总数</td><tr>");
        String name = "";
        int total;
        List list = (List) jdbcTemplate.queryForList(
                "select  distinct(NO) FROM BS_ORG where NO is not null and parentno='"
                        + orgNo + "'", new Object[] {}, java.lang.String.class);
        for (int i = 0; i < list.size(); i++) {
            String no = (String) list.get(i);
            List list1 = (List) jdbcTemplate.queryForList(
                    "select  NAME FROM BS_ORG where NO='" + no + "'",
                    new Object[] {}, java.lang.String.class);
            for (int j = 0; j < list1.size(); j++) {
                name = (String) list1.get(j);
            }
            String orgNos = systemBaseInfoManager.getAllChildOfOrg(no);
            total = jdbcTemplate
                    .queryForInt(" select count(*) from BS_USERINFOOFJG where ORGCODEOFJR in ("
                            + orgNos + ")");
            sb.append("<tr><td align='center'>");
            sb.append("<a href='./User.do?method=statisticsByOrg&flag=")
                    .append(no).append("'>").append(name).append("</a>")
                    .append("</td>");
            sb.append("<td align='center'>").append(total).append("</td></tr>");
        }
        return sb;
    }

    @Transactional(readOnly = false)
    public void savaUserComInfo(InputStream is, String endRow) {
        HSSFWorkbook wb = null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int i = 1; i < Integer.parseInt(endRow); i++) {
                // 你把你的方法写道这
                String loginId = ExcelUtil.getValue(sheet.getRow(i).getCell(
                        0));
                String comOrgNo = ExcelUtil
                        .getValue(sheet.getRow(i).getCell(1));
                BsOrg bsComOrg = systemBaseInfoManager.getOrgByNo(comOrgNo);
                String comOrgName = bsComOrg.getName();
                String comContent = ExcelUtil.getValue(sheet.getRow(i).getCell(
                        2));
                String comDate = ExcelUtil.getValue(sheet.getRow(i).getCell(3));
                BsUserComInfo bsUserComInfo = new BsUserComInfo();
                bsUserComInfo.setLoginId(loginId);
                bsUserComInfo.setComOrgNo(comOrgNo);
                bsUserComInfo.setComOrgName(comOrgName);
                bsUserComInfo.setComContent(comContent);
                bsUserComInfo.setComDate(comDate);
                bsUserComInfoDao.save(bsUserComInfo);
                // this.savaBsOrgComInfo(bsOrgComInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("读取Excel失败");
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
