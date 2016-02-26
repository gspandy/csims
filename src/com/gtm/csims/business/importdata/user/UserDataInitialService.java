package com.gtm.csims.business.importdata.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsDeptDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.model.BsDept;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsUserInfo;
import com.gtm.csims.utils.Constants;
import com.gtm.csims.utils.StringUtil;

/**
 * 用户数据初始化实现
 * 
 * @author Sweet
 * 
 */
public class UserDataInitialService {

    private JdbcTemplate jdbcTemplate;
    private BsDeptDAO bsDeptDao;
    private BsOrgDAO bsOrgDao;

    public void setBsOrgDao(BsOrgDAO bsOrgDao) {
        this.bsOrgDao = bsOrgDao;
    }

    public void setBsDeptDao(BsDeptDAO bsDeptDao) {
        this.bsDeptDao = bsDeptDao;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 从临时表导入用户信息
     */
    @Transactional(readOnly = false)
    @SuppressWarnings("unchecked")
    public void importUserDataFromTempTable() {
        String selectAllSql = "SELECT ID,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T FROM importusertemp"
        // + " WHERE id ='1000000001'"
        ;
        // 查出临时表所有用户数据
        List<Map<String, Object>> allUserTemp = jdbcTemplate.queryForList(selectAllSql);
        // System.out.println(allUserTemp.size());
        for (Iterator<Map<String, Object>> iterator = allUserTemp.iterator(); iterator.hasNext();) {
            Map<String, Object> map = (ListOrderedMap) iterator.next();
            // System.out.println(map.get("ID"));
            try {
                // 插入BS_USERINFOOFJG表
                this.insertToBS_USERINFOOFJGFromTemp(map);
            } catch (Exception e) {
                try {
                    // 插入BS_USERINFOOFZX征信用户表
                    this.insertToBsUserInfoZXFromTemp(map);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    this.saveUserinfoToErrorTable(map, "jg_message");
                }
                continue;
            }
            try {
                // 插入jguard表
                this.insertToJGUserInfoFromTemp(map);
            } catch (Exception e) {
                this.saveUserinfoToErrorTable(map, "jg_message");
                continue;
            }
        }
    }

    /**
     * 根据IMPORTUSERTEMP表中的初始数据进行业务逻辑处理.
     * 
     * 结果插入BS_USERINFOOFJG和BS_USERINFOOFZX表 <br>
     * A:金融机构编码<br>
     * B:机构名称（用户工作单位）<br>
     * C:所在部门<br>
     * D:姓名<br>
     * E:性别<br>
     * F:证件号码<br>
     * G:成绩<br>
     * H:个人系统-管理员用户名<br>
     * I:个人系统-数据上报员用户名<br>
     * J:个人系统-查询员用户名<br>
     * K:个人系统-异议处理员用户名<br>
     * L:企业系统-管理员用户名 <br>
     * M:企业系统-数据上报员用户名<br>
     * N:企业系统-查询员用户名<br>
     * O:企业系统-异议处理员用户名<br>
     * P:学历<br>
     * Q:联系电话<br>
     * R:用户名所属金融机构编码<br>
     * S:用户名所属机构名称<br>
     * T:<br>
     * 
     * @param map
     */
    @Transactional(readOnly = false)
    @SuppressWarnings("unchecked")
    private void insertToBS_USERINFOOFJGFromTemp(Map<String, Object> map) {
        String userid = UUID.randomUUID().toString();
        BsUserInfo bsUserInfo = new BsUserInfo();
        bsUserInfo.setId(map.get("F").toString().trim());
        bsUserInfo.setName(map.get("D").toString().trim());
        bsUserInfo.setZxName1(map.get("H").toString().trim());
        bsUserInfo.setCardType("身份证");
        bsUserInfo.setCardId(map.get("F").toString().trim());
        bsUserInfo.setCreaterCode("");// ???????????????
        bsUserInfo.setCreaterName("");
        // 如果当前用户就是管理员
        if ((map.get("H") != null && !map.get("H").toString().trim().equals(""))
                || (map.get("L") != null && !map.get("L").toString().trim().equals(""))) {
            // ID以A开头的表示属于人民银行用户
            if (map.get("A").toString().indexOf("A") != -1) {
                try {
                    int orgLevel = this.getOrgLevel(map.get("A").toString().trim());
                    if (orgLevel == 1) {
                        bsUserInfo.setJgPrincipal("csims#一般管理员用户（人行成都分行）");// 一般管理员用户（人行成都分行）
                    } else if (orgLevel == 2) {
                        bsUserInfo.setJgPrincipal("csims#一般管理员用户（人行市中支）");// 一般管理员用户（人行市中支）
                    } else if (orgLevel == 3) {
                        bsUserInfo.setJgPrincipal("csims#一般管理员用户（人行县支行）");// 一般管理员用户（人行县支行）
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    bsUserInfo.setJgPrincipal("csims#一般管理员用户（人行成都分行）");// 一般管理员用户（人行成都分行）
                }
            } else {
                bsUserInfo.setJgPrincipal("csims#一般管理员用户（非人行）");
            }
            // 如果当前用户属于人民银行成都分行用户
            if (map.get("A") != null
                    && map.get("A").toString().trim().equals(Constants.PCB_SC_ORG_NO)) {
                bsUserInfo.setUSERTYPE("高级管理员");
            } else {
                bsUserInfo.setUSERTYPE("一般管理员");
                // bsUserInfo.setCreaterCode("admin");// ???????????????
                // bsUserInfo.setCreaterName("admin");
            }
        } else {// 如果当前用户不是管理员
            if (map.get("A").toString().indexOf("A") != -1) {
                bsUserInfo.setJgPrincipal("csims#普通用户（人行）");
                try {
                    int orgLevel = this.getOrgLevel(map.get("A").toString().trim());
                    if (orgLevel == 1) {
                        bsUserInfo.setJgPrincipal("csims#普通用户（人行成都分行）");// 普通用户（人行成都分行）
                    } else if (orgLevel == 2) {
                        bsUserInfo.setJgPrincipal("csims#普通用户（人行市中支）");// 普通用户（人行市中支）
                    } else if (orgLevel == 3) {
                        bsUserInfo.setJgPrincipal("csims#普通用户（人行县支行）");// 普通用户（人行县支行）
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    bsUserInfo.setJgPrincipal("csims#普通用户（人行成都分行）");// 普通用户（人行成都分行）
                }
            } else {
                bsUserInfo.setJgPrincipal("csims#普通用户（金融机构）");
            }
            bsUserInfo.setUSERTYPE("普通用户");
        }
        StringBuffer zxPrincipalSb = new StringBuffer();
        String zxid = null;
        List<String> insertBSUSERINFOOFZXSqls = new ArrayList<String>();
        String creator = "";
        // 向BS_USERINFOOFZX表插入-个人征信管理员
        if (map.get("H") != null && !map.get("H").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findTopAdminCreator(map);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "个人征信管理员用户"
                            + "','"
                            + map.get("H").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("I") != null && !map.get("I").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, true);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "个人征信数据报送用户"
                            + "','"
                            + map.get("I").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("J") != null && !map.get("J").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, true);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + (this.isPBCUser(map.get("A").toString().trim()) ? "个人征信窗口查询用户"
                                    : "个人征信业务查询用户")
                            + "','"
                            + map.get("J").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("K") != null && !map.get("K").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, true);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "个人征信异议处理用户"
                            + "','"
                            + map.get("K").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("L") != null && !map.get("L").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findTopAdminCreator(map);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "企业征信管理员用户"
                            + "','"
                            + map.get("L").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("M") != null && !map.get("M").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, false);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "企业征信数据报送用户"
                            + "','"
                            + map.get("M").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("N") != null && !map.get("N").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, false);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + (this.isPBCUser(map.get("A").toString().trim()) ? "企业征信窗口查询用户"
                                    : "企业征信业务查询用户")
                            + "','"
                            + map.get("N").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("O") != null && !map.get("O").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, false);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "企业征信异议处理用户"
                            + "','"
                            + map.get("O").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        String zxPrincipalStr = zxPrincipalSb.toString();
        // if (zxPrincipalStr.length() > 0)
        // zxPrincipalStr = zxPrincipalStr.substring(0, zxPrincipalStr
        // .length() - 1);
        bsUserInfo.setZxPrincipal(zxPrincipalStr);
        bsUserInfo.setUserCode(map.get("F").toString().trim());
        bsUserInfo.setEducation(map.get("P").toString().trim());
        bsUserInfo.setPost("");
        bsUserInfo.setOrgCodeOfJr(map.get("A").toString().trim());
        bsUserInfo.setOrgCodeOfXy("");
        bsUserInfo.setOrgCodeOfZz("");
        bsUserInfo.setOrgName(map.get("B").toString().trim());
        // 查询当前用户的部门信息在部门表是否存在记录
        if (map.get("C") != null && !map.get("C").toString().trim().equals("")) {
            String queryDeptSql = "from BsDept where orgno = '" + map.get("A").toString().trim()
                    + "' and name='" + map.get("C").toString().trim() + "'";
            List<BsDept> list = bsDeptDao.find(queryDeptSql, new Object[] {});
            if (list != null && list.size() > 0) {
                BsDept dept = list.get(0);
                bsUserInfo.setDeptNo(dept.getId());
                bsUserInfo.setDeptName(dept.getName());
            } else {
                bsUserInfo.setDeptNo("error");
                bsUserInfo.setDeptName("无部门");
            }
        } else {
            bsUserInfo.setDeptNo("0");
            bsUserInfo.setDeptName("无部门");
        }
        bsUserInfo.setPhone(map.get("Q").toString().trim());
        bsUserInfo.setStoperCode("");
        bsUserInfo.setUserStatus("启用");
        bsUserInfo.setCreateDate(new Date());
        bsUserInfo.setNOWUSER("1");
        if (map.get("A").toString().indexOf("A") != -1) {
            bsUserInfo.setISPCBUSER("YES");
        } else {
            bsUserInfo.setISPCBUSER("NO");
        }

        bsUserInfo.setMANAGERCODE(bsUserInfo.getCreaterCode().trim());
        bsUserInfo.setLOGINID(map.get("F").toString().trim());

        // nowuser '1',,,
        // usertype yuan jg-principal
        // ispcb yes no
        // zxorgcode1

        StringBuffer insertSql = new StringBuffer("insert into BS_USERINFOOFJG (id,name")
                .append(",CardType,JgPrincipal,CreaterCode,CreaterName")
                .append(",Education,Post,OrgCodeOfJr,OrgCodeOfXy")
                .append(",OrgCodeOfZz,OrgName,DeptNo,DeptName,Phone,StoperCode,UserStatus")
                .append(",NOWUSER,USERTYPE,ISPCBUSER,MANAGERCODE,LOGINID").append(") values ('")
                .append(userid.trim()).append("','").append(bsUserInfo.getName().trim())
                .append("','").append(bsUserInfo.getCardType().trim()).append("','")
                .append(bsUserInfo.getJgPrincipal().trim()).append("','")
                .append(bsUserInfo.getCreaterCode().trim()).append("','")
                .append(bsUserInfo.getCreaterName().trim()).append("','")
                .append(bsUserInfo.getEducation().trim()).append("','")
                .append(bsUserInfo.getPost().trim()).append("','")
                .append(bsUserInfo.getOrgCodeOfJr().trim()).append("','")
                .append(bsUserInfo.getOrgCodeOfXy().trim()).append("','")
                .append(bsUserInfo.getOrgCodeOfZz().trim()).append("','")
                .append(bsUserInfo.getOrgName().trim()).append("','")
                .append(bsUserInfo.getDeptNo().trim()).append("','")
                .append(bsUserInfo.getDeptName().trim()).append("','")
                .append(bsUserInfo.getPhone().trim()).append("','")
                .append(bsUserInfo.getStoperCode().trim()).append("','")
                .append(bsUserInfo.getUserStatus().trim()).append("','")
                .append(bsUserInfo.getNOWUSER().trim()).append("','")
                .append(bsUserInfo.getUSERTYPE().trim()).append("','")
                .append(bsUserInfo.getISPCBUSER().trim()).append("','")
                .append(bsUserInfo.getMANAGERCODE().trim()).append("','")
                .append(bsUserInfo.getLOGINID().trim()).append("')");
        System.out.println(insertSql.toString());
        jdbcTemplate.update(insertSql.toString());
        String[] insertBSUSERINFOOFZXSqlsArr = new String[insertBSUSERINFOOFZXSqls.size()];
        insertBSUSERINFOOFZXSqlsArr = insertBSUSERINFOOFZXSqls.toArray(insertBSUSERINFOOFZXSqlsArr);
        for (int i = 0; i < insertBSUSERINFOOFZXSqlsArr.length; i++) {
            System.out.println("-----" + insertBSUSERINFOOFZXSqlsArr[i]);
        }
        jdbcTemplate.batchUpdate(insertBSUSERINFOOFZXSqlsArr);
        // try {
        // bsUserInfoDao.save(bsUserInfo);
        // } catch (Exception e) {
        // System.out.println(e.getMessage() + " " + bsUserInfo.getId());
        // }
    }

    /**
     * 查找当前用户创建者
     * 
     * @param map
     * @param isPerson
     * @return
     */
    @SuppressWarnings("unchecked")
    private String findCreator(Map<String, Object> map, boolean isPerson) {
        try {
            String queryAdminSql = "SELECT D,F FROM importusertemp WHERE A='"
                    + map.get("A").toString().trim() + "' AND ( " + (isPerson ? "H" : "L")
                    + "  <> '')";
            // 查询本机构是否有管理员
            List<Map<String, Object>> templist = jdbcTemplate.queryForList(queryAdminSql);
            if (templist != null && templist.size() > 0) {// 本机构有管理员
                ListOrderedMap loMap = (ListOrderedMap) templist.get(0);
                return loMap.get("D").toString();
                // bsUserInfo.setCreaterName(loMap.get("D").toString());
            } else {// 如果本机构没有管理员，则查询上级机构
                // 查询上级机构
                List<Map<String, Object>> upOrg = jdbcTemplate
                        .queryForList("SELECT ParentNo FROM bs_org WHERE No= '"
                                + map.get("A").toString().trim() + "'");
                if (upOrg != null && upOrg.size() > 0) {// 如果有上级机构
                    ListOrderedMap a = (ListOrderedMap) upOrg.get(0);
                    queryAdminSql = "SELECT D,F FROM importusertemp WHERE A='"
                            + a.get("ParentNo").toString() + "' AND (" + (isPerson ? "H" : "L")
                            + " <> '')";
                    // 查询上级机构是否有管理员
                    templist = jdbcTemplate.queryForList(queryAdminSql);
                    if (templist != null && templist.size() > 0) {// 上级机构如果有管理员
                        ListOrderedMap loMap = (ListOrderedMap) templist.get(0);
                        return loMap.get("D").toString();
                        // bsUserInfo.setCreaterName(loMap.get("D").toString());
                    } else {
                        // 如果上级机构没有管理员，则到IMPORTTEMP_JGUARD表中找对应的非人行高级管理员
                        String[] allParentArr = this
                                .getAllParentNos(map.get("A").toString().trim());
                        List<Map<String, Object>> allParentlt = jdbcTemplate
                                .queryForList("SELECT NAME FROM IMPORTTEMP_JGUARD WHERE ORGNO IN ("
                                        + StringUtil.join(allParentArr, "'", "'", ",") + ")");
                        if (allParentlt != null && allParentlt.size() > 0) {
                            ListOrderedMap b = (ListOrderedMap) allParentlt.get(0);
                            return b.get("NAME").toString();
                            // bsUserInfo.setCreaterName(b.get("NAME").toString());
                        } else {
                            return "admin";
                            // bsUserInfo.setCreaterName("");
                        }
                    }
                } else {
                    // 如果找不到上级机构，则到IMPORTTEMP_JGUARD表中找对应的非人行高级管理员
                    String[] allParentArr = this.getAllParentNos(map.get("A").toString().trim());
                    List<Map<String, Object>> allParentlt = jdbcTemplate
                            .queryForList("SELECT NAME FROM IMPORTTEMP_JGUARD WHERE ORGNO IN ("
                                    + StringUtil.join(allParentArr, "'", "'", ",") + ")");
                    if (allParentlt != null && allParentlt.size() > 0) {
                        ListOrderedMap b = (ListOrderedMap) allParentlt.get(0);
                        return b.get("NAME").toString();
                        // bsUserInfo.setCreaterName(b.get("NAME").toString());
                    } else {
                        return "admin";
                        // bsUserInfo.setCreaterName("");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "admin";
        }
    }

    /**
     * 从IMPORTTEMP_JGUARD表中查找当前用户机构的高级管理员
     * 
     * 用于为个人管理员或企业管理员
     * 
     * @param map
     * @param isPerson
     * @return
     */
    @SuppressWarnings("unchecked")
    private String findTopAdminCreator(Map<String, Object> map) {
        try {
            // 到IMPORTTEMP_JGUARD表中找对应的非人行高级管理员
            String[] allParentArr = this.getAllParentNos(map.get("A").toString().trim());
            List<Map<String, Object>> allParentlt = jdbcTemplate
                    .queryForList("SELECT NAME FROM IMPORTTEMP_JGUARD WHERE ORGNO IN ("
                            + StringUtil.join(allParentArr, "'", "'", ",") + ")");
            if (allParentlt != null && allParentlt.size() > 0) {
                ListOrderedMap b = (ListOrderedMap) allParentlt.get(0);
                return b.get("NAME").toString();
                // bsUserInfo.setCreaterName(b.get("NAME").toString());
            } else {
                return "admin";
                // bsUserInfo.setCreaterName("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "admin";
        }
    }

    /**
     * 根据IMPORTUSERTEMP表中的初始数据进行业务逻辑处理
     * 
     * 如果判断BS_USERINFOOFJG表中已经存在记录则只将结果插入BS_USERINFOOFZX表
     * 
     * @param map
     */
    @Transactional(readOnly = false)
    @SuppressWarnings("unchecked")
    private void insertToBsUserInfoZXFromTemp(Map<String, Object> map) {
        String selectExsitUserJG = "SELECT ID FROM BS_USERINFOOFJG WHERE loginid='"
                + map.get("F").toString().trim() + "'";
        String userid = "";
        List<Map<String, Object>> exsitUserJG = jdbcTemplate.queryForList(selectExsitUserJG);
        if (exsitUserJG != null && exsitUserJG.size() > 0) {
            userid = ((ListOrderedMap) exsitUserJG.get(0)).get("ID").toString();
        } else {
            return;
        }
        String zxid = null;
        List<String> insertBSUSERINFOOFZXSqls = new ArrayList<String>();
        String creator = "";
        if (map.get("H") != null && !map.get("H").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findTopAdminCreator(map);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "个人征信管理员用户"
                            + "','"
                            + map.get("H").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("I") != null && !map.get("I").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, true);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "个人征信数据报送用户"
                            + "','"
                            + map.get("I").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("J") != null && !map.get("J").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, true);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + (this.isPBCUser(map.get("A").toString().trim()) ? "个人征信窗口查询用户"
                                    : "个人征信业务查询用户")
                            + "','"
                            + map.get("J").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("K") != null && !map.get("K").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, true);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "个人征信异议处理用户"
                            + "','"
                            + map.get("K").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("L") != null && !map.get("L").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findTopAdminCreator(map);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "企业征信管理员用户"
                            + "','"
                            + map.get("L").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("M") != null && !map.get("M").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, false);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "企业征信数据报送用户"
                            + "','"
                            + map.get("M").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("N") != null && !map.get("N").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, false);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + (this.isPBCUser(map.get("A").toString().trim()) ? "企业征信窗口查询用户"
                                    : "企业征信业务查询用户")
                            + "','"
                            + map.get("N").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        if (map.get("O") != null && !map.get("O").toString().trim().equals("")) {
            zxid = UUID.randomUUID().toString();
            creator = this.findCreator(map, false);
            insertBSUSERINFOOFZXSqls
                    .add("INSERT INTO BS_USERINFOOFZX (ID,USERID,LOGINID,ZXUSERTYPE"
                            + ",ZXUSERNAME,ORGCODE,ORGNAME,NOWUSER,USERSTATUS,CREATOR,BSUSERINFOOFJG) VALUES ('"
                            + zxid
                            + "','"
                            + userid
                            + "','"
                            + map.get("F").toString().trim()
                            + "','"
                            + "企业征信异议处理用户"
                            + "','"
                            + map.get("O").toString().trim()
                            + "','"
                            + ((map.get("R") == null || map.get("R").toString().trim().equals("")) ? map
                                    .get("A").toString().trim()
                                    : map.get("R").toString().trim())
                            + "','"
                            + ((map.get("S") == null || map.get("S").toString().trim().equals("")) ? map
                                    .get("B").toString().trim()
                                    : map.get("S").toString().trim()) + "','1','启用','" + creator
                            + "','" + userid + "')");
        }
        String[] insertBSUSERINFOOFZXSqlsArr = new String[insertBSUSERINFOOFZXSqls.size()];
        insertBSUSERINFOOFZXSqlsArr = insertBSUSERINFOOFZXSqls.toArray(insertBSUSERINFOOFZXSqlsArr);
        for (int i = 0; i < insertBSUSERINFOOFZXSqlsArr.length; i++) {
            System.out.println("-----" + insertBSUSERINFOOFZXSqlsArr[i]);
        }
        jdbcTemplate.batchUpdate(insertBSUSERINFOOFZXSqlsArr);
        // try {
        // bsUserInfoDao.save(bsUserInfo);
        // } catch (Exception e) {
        // System.out.println(e.getMessage() + " " + bsUserInfo.getId());
        // }
    }

    /**
     * 根据IMPORTUSERTEMP表中的初始数据进行业务逻辑处理
     * 
     * 插入jguard表
     * 
     * @param map
     */
    // ALTER SEQUENCE jg_credential_seq RESTART WITH 50000;
    // ALTER SEQUENCE jg_user_seq RESTART WITH 12500;
    @Transactional(readOnly = false)
    private void insertToJGUserInfoFromTemp(Map<String, Object> map) {
        // 获取用户id
        String sql1 = "SELECT nextval for jg_user_seq from SYSIBM.SYSDUMMY1";
        int jgUserId = jdbcTemplate.queryForInt(sql1);
        System.out.println(jgUserId);
        // 插入jg_user表
        String sql2 = "INSERT INTO jg_user (id) values(" + jgUserId + ")";
        jdbcTemplate.update(sql2);
        // 获取credential id
        String sql3 = "SELECT nextval for jg_credential_seq from SYSIBM.SYSDUMMY1";
        int jgCredId = jdbcTemplate.queryForInt(sql3);
        // 插入jg_credential表
        String sqlC1 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId
                + ", "
                + jgUserId
                + ", 1, 'organization', '"
                + map.get("A").toString().trim() + "')";
        jdbcTemplate.update(sqlC1);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC2 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId
                + ", "
                + jgUserId
                + ", 1, 'nickname', '"
                + map.get("D").toString().trim() + "')";
        jdbcTemplate.update(sqlC2);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC3 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId
                + ", "
                + jgUserId
                + ", 0, 'login', '"
                + map.get("F").toString().trim()
                + "')";
        jdbcTemplate.update(sqlC3);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC4 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId
                + ", "
                + jgUserId
                + ", 0, 'password', '96e79218965eb72c92a549dd5a330112')";
        jdbcTemplate.update(sqlC4);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC5 = null;
        if (map.get("A").toString().indexOf("A") != -1) {
            sqlC5 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                    + jgCredId + ", " + jgUserId + ", 1, 'ispcbuser', 'YES')";
        } else {
            sqlC5 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                    + jgCredId + ", " + jgUserId + ", 1, 'ispcbuser', 'NO')";
        }
        jdbcTemplate.update(sqlC5);
        // 分配角色
        if ((map.get("H") != null && !map.get("H").toString().trim().equals(""))
                || (map.get("L") != null && !map.get("L").toString().trim().equals(""))) {
            if (map.get("A").toString().indexOf("A") != -1) {
                try {
                    int orgLevel = this.getOrgLevel(map.get("A").toString().trim());
                    if (orgLevel == 1) {
                        jdbcTemplate
                                .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                        + jgUserId + ",14,'true',1)");// 一般管理员用户（人行成都分行）
                    } else if (orgLevel == 2) {
                        jdbcTemplate
                                .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                        + jgUserId + ",6,'true',1)");// 一般管理员用户（人行市中支）
                    } else if (orgLevel == 3) {
                        jdbcTemplate
                                .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                        + jgUserId + ",15,'true',1)");// 一般管理员用户（人行县支行）
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 如果当前用户属于执法人员
                if (this.isAePeople(map.get("F").toString().trim())) {
                    jdbcTemplate
                            .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                    + jgUserId + ",12,'true',1)");// 执法人员
                }
            } else {
                jdbcTemplate
                        .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                + jgUserId + ",7,'true',1)");// 一般管理员（非人行）
            }
        } else {
            if (map.get("A").toString().indexOf("A") != -1) {
                try {
                    int orgLevel = this.getOrgLevel(map.get("A").toString().trim());
                    if (orgLevel == 1) {
                        jdbcTemplate
                                .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                        + jgUserId + ",19,'true',1)");// 普通用户（人行成都分行）
                    } else if (orgLevel == 2) {
                        jdbcTemplate
                                .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                        + jgUserId + ",10,'true',1)");// 普通用户（人行市中支）
                    } else if (orgLevel == 3) {
                        jdbcTemplate
                                .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                        + jgUserId + ",18,'true',1)");// 普通用户（人行县支行）
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 如果当前用户属于执法人员
                if (this.isAePeople(map.get("F").toString().trim())) {
                    jdbcTemplate
                            .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                    + jgUserId + ",12,'true',1)");// 执法人员
                }
            } else {
                jdbcTemplate
                        .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                                + jgUserId + ",11,'true',1)");// 普通用户(金融机构)
            }
        }
    }

    /**
     * 单个用户插入jguard表
     * 
     * @param login
     * @param orgNo
     * @param nickName
     * @param ispcbuser
     * @param principals
     */
    // ALTER SEQUENCE jg_credential_seq RESTART WITH 50000;
    // ALTER SEQUENCE jg_user_seq RESTART WITH 12500;
    @Transactional(readOnly = false)
    private void insertToJGUserInfo(String login, String orgNo, String nickName, String ispcbuser,
            String[] principals) {
        String sql1 = "SELECT nextval for jg_user_seq from SYSIBM.SYSDUMMY1";
        int jgUserId = jdbcTemplate.queryForInt(sql1);
        System.out.println(jgUserId);
        String sql2 = "INSERT INTO jg_user (id) values(" + jgUserId + ")";
        jdbcTemplate.update(sql2);

        String sql3 = "SELECT nextval for jg_credential_seq from SYSIBM.SYSDUMMY1";
        int jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC1 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId + ", " + jgUserId + ", 1, 'organization', '" + orgNo + "')";
        jdbcTemplate.update(sqlC1);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC2 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId + ", " + jgUserId + ", 1, 'nickname', '" + nickName + "')";
        jdbcTemplate.update(sqlC2);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC3 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId + ", " + jgUserId + ", 0, 'login', '" + login + "')";
        jdbcTemplate.update(sqlC3);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC4 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId
                + ", "
                + jgUserId
                + ", 0, 'password', '96e79218965eb72c92a549dd5a330112')";
        jdbcTemplate.update(sqlC4);
        jgCredId = jdbcTemplate.queryForInt(sql3);
        String sqlC5 = null;
        sqlC5 = "INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) values ("
                + jgCredId + ", " + jgUserId + ", 1, 'ispcbuser', '" + ispcbuser + "')";
        jdbcTemplate.update(sqlC5);

        for (int i = 0; i < principals.length; i++) {
            jdbcTemplate
                    .update("INSERT INTO jg_user_principal(user_id,principal_id,definition,active) values("
                            + jgUserId + "," + principals[i] + ",'true',1)");
        }
    }

    /**
     * 从临时表IMPORTTEMP_JGUARD获取数据向jguard表导入数据
     */
    @SuppressWarnings("unchecked")
    public void importUserIntoJguard() {
        String selectAllSql = "SELECT ID, LOGIN, NAME, PWD, ISPCBUSER, ORGNO FROM IMPORTTEMP_JGUARD";
        // 查出临时表所有用户数据
        List<Map<String, Object>> allUserTemp = jdbcTemplate.queryForList(selectAllSql);
        String[] principals = new String[] { "5" };
        String[] principals2 = new String[] { "4" };
        for (Iterator<Map<String, Object>> iterator = allUserTemp.iterator(); iterator.hasNext();) {
            ListOrderedMap map = (ListOrderedMap) iterator.next();
            try {
                // 插入jguard表
                if (map.get("ISPCBUSER") != null && map.get("ISPCBUSER").toString().equals("YES")) {
                    this.insertToJGUserInfo(map.get("LOGIN").toString().trim(), map.get("ORGNO")
                            .toString().trim(), map.get("NAME").toString().trim(),
                            map.get("ISPCBUSER").toString().trim(), principals2);
                } else {
                    this.insertToJGUserInfo(map.get("LOGIN").toString().trim(), map.get("ORGNO")
                            .toString().trim(), map.get("NAME").toString().trim(),
                            map.get("ISPCBUSER").toString().trim(), principals);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 从执法人员表中获取数据向jguard表导入数据
     */
    @SuppressWarnings("unchecked")
    public void importAePeopleIntoJguard() {
        String selectAllSql = "SELECT ID, CARDID, PEPNAME, BANKNO FROM BS_AEPEOPLE";
        // 查出临时表所有用户数据
        List<Map<String, Object>> allUserTemp = jdbcTemplate.queryForList(selectAllSql);
        String[] principals = new String[] { "12" };
        for (Iterator<Map<String, Object>> iterator = allUserTemp.iterator(); iterator.hasNext();) {
            ListOrderedMap map = (ListOrderedMap) iterator.next();
            try {
                if (!this.isThisAepeopleExsitInJgguard(map.get("CARDID").toString().trim())) {
                    // 插入jguard表
                    this.insertToJGUserInfo(map.get("CARDID").toString().trim(), map.get("BANKNO")
                            .toString().trim(), map.get("PEPNAME").toString().trim(), "YES",
                            principals);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 判断当前执法人员是否已经在jgaurd表中存在
     * 
     * @param login
     * @return
     */
    @SuppressWarnings("unchecked")
    private Boolean isThisAepeopleExsitInJgguard(String login) {
        String selectAllSql = "SELECT ID FROM JG_CREDENTIAL WHERE cred_value = '" + login.trim()
                + "'";
        // 查出临时表所有用户数据
        List<Map<String, Object>> allUserTemp = jdbcTemplate.queryForList(selectAllSql);
        if (allUserTemp != null && allUserTemp.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 从临时表IMPORTTEMP_JGUARD获取数据向BS_USERINFOOFJG表导入数据
     */
    @SuppressWarnings("unchecked")
    public void importUserIntoBSUSERINFOOFJG() {
        String selectAllSql = "SELECT ID, LOGIN, NAME, PWD, ISPCBUSER, ORGNO FROM IMPORTTEMP_JGUARD";
        // 查出临时表所有用户数据
        List<Map<String, Object>> allUserTemp = jdbcTemplate.queryForList(selectAllSql);
        // String[] principals = new String[] { "5" };
        for (Iterator<Map<String, Object>> iterator = allUserTemp.iterator(); iterator.hasNext();) {
            ListOrderedMap map = (ListOrderedMap) iterator.next();
            // System.out.println(map.get("ID"));
            String orgName = "";
            if (map.get("ORGNO") != null && !map.get("ORGNO").toString().trim().equals("")) {
                BsOrg org = bsOrgDao.get(map.get("ORGNO").toString().trim());
                if (org != null) {
                    orgName = org.getName();
                } else {
                    orgName = "";
                }
            }
            try {
                // 插入BsUser表
                StringBuffer insertSql = new StringBuffer(
                        "INSERT INTO BS_USERINFOOFJG (ID, LOGINID, NAME, USERTYPE, CARDTYPE, JGPRINCIPAL, EDUCATION, POST, ORGCODEOFJR")
                        .append(" , ORGCODEOFXY, ORGCODEOFZZ, ORGNAME, DEPTNO, DEPTNAME, PHONE, PHOTO, USERSTATUS, ISPCBUSER, NOWUSER) ")
                        .append(" VALUES ('")
                        .append(UUID.randomUUID().toString())
                        .append("', '")
                        .append(map.get("LOGIN").toString().trim() + "', '")
                        .append(map.get("NAME").toString().trim())
                        .append("', '高级管理员', '', '', '', '', '")
                        .append(map.get("ORGNO").toString().trim() + "', '', '','")
                        .append(orgName)
                        .append("', '', '', '', '', '启用', '"
                                + map.get("ISPCBUSER").toString().trim() + "', '1')");
                // System.out.println(insertSql.toString());
                jdbcTemplate.update(insertSql.toString());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    private void saveUserinfoToErrorTable(Map<String, Object> map, String message) {
        StringBuffer errorSqlSb = new StringBuffer(
                "INSERT INTO importusertemp_error (ID,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,Message) VALUES('")
                .append(map.get("ID").toString()).append("','").append(map.get("A").toString())
                .append("','").append(map.get("B").toString()).append("','")
                .append(map.get("C").toString()).append("','").append(map.get("D").toString())
                .append("','").append(map.get("E").toString()).append("','")
                .append(map.get("F").toString()).append("','").append(map.get("G").toString())
                .append("','").append(map.get("H").toString()).append("','")
                .append(map.get("I").toString()).append("','").append(map.get("J").toString())
                .append("','").append(map.get("K").toString()).append("','")
                .append(map.get("L").toString()).append("','").append(map.get("M").toString())
                .append("','").append(map.get("N").toString()).append("','")
                .append(map.get("O").toString()).append("','").append(map.get("P").toString())
                .append("','").append(map.get("Q").toString()).append("','")
                .append(map.get("R").toString()).append("','").append(map.get("S").toString())
                .append("','").append(message).append("')");
        // System.out.println(errorSqlSb.toString());
        jdbcTemplate.execute(errorSqlSb.toString());
    }

    /**
     * 获取当前机构的所有父机构集合
     * 
     * @param orgNo
     * @return
     */
    @SuppressWarnings("unchecked")
    public String[] getAllParentNos(String orgNo) {
        List<Map<String, Object>> upOrg = null;
        String parentNo = orgNo;
        List<String> resultlt = new ArrayList<String>();
        resultlt.add(orgNo);
        boolean isContinue = true;
        while (isContinue) {
            upOrg = jdbcTemplate.queryForList("SELECT ParentNo FROM bs_org WHERE No= '" + parentNo
                    + "'");
            if (upOrg != null && upOrg.size() > 0) {// 如果有上级机构
                ListOrderedMap a = (ListOrderedMap) upOrg.get(0);
                if (!a.get("ParentNo").toString().trim().equals("0")) {
                    resultlt.add(a.get("ParentNo").toString().trim());
                    parentNo = a.get("ParentNo").toString().trim();
                } else {
                    isContinue = false;
                }
            } else {
                isContinue = false;
            }
        }
        String[] resultArr = new String[resultlt.size()];
        return resultlt.toArray(resultArr);
    }

    /**
     * 判断当前用户是否为执法人员
     * 
     * @param idCardNo
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean isAePeople(String idCardNo) {
        List<Map<String, Object>> aePeopleLt = jdbcTemplate
                .queryForList("SELECT ID FROM BS_AEPEOPLE WHERE Cardid = '" + idCardNo + "'");
        if (aePeopleLt != null && aePeopleLt.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前机构层级
     * 
     * @param orgNo
     * @return 1- 人行成都分行 2-人行市中支 3-人行县支行
     */
    private int getOrgLevel(String orgNo) throws Exception {
        if (orgNo.trim().equals(Constants.PCB_SC_ORG_NO)) {
            return 1;
        }
        BsOrg org = bsOrgDao.get(orgNo);
        if (org != null) {
            if (org.getParentno().trim().equals(Constants.PCB_SC_ORG_NO)) {
                return 2;
            } else {
                return 3;
            }
        } else {
            throw new Exception("不能找到机构：" + orgNo);
        }
    }

    /**
     * 根据当前用户所属机构判断是否为人民银行用户
     * 
     * @param a
     * @return
     */
    private Boolean isPBCUser(String a) {
        if (StringUtils.isNotBlank(a) && a.trim().startsWith("A"))
            return true;
        return false;
    }

}
