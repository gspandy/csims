package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_DURING table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="BsUserInfo"
 */

public abstract class BaseBsUserInfo implements Serializable {

	public static String REF = "BsUserInfo";
	public static String PROP_ID = "Id";
	public static String PROP_NAME = "Name";
	public static String PROP_PASSWORD = "Password";
	public static String PROP_ZXNAME1 = "ZxName1";
	public static String PROP_ZXNAME2 = "ZxName2";
	public static String PROP_ZXNAME3 = "ZxName3";
	public static String PROP_ZXNAME4 = "ZxName4";
	public static String PROP_ZXNAME5 = "ZxName5";
	public static String PROP_ZXNAME6 = "ZxName6";
	public static String PROP_ZXNAME7 = "ZxName7";
	public static String PROP_ZXNAME8 = "ZxName8";
	public static String PROP_ZXNAME9 = "ZxName9";
	public static String PROP_CARDTYPE = "CardType";
	public static String PROP_CARDID = "CardId";
	public static String PROP_JGPRINCIPAL = "JgPrincipal";
	public static String PROP_ZXPRINCIPAL = "ZxPrincipal";
	public static String PROP_USERCODE = "UserCode";
	public static String PROP_EDUCATION = "Education";
	public static String PROP_POST = "Post";
	public static String PROP_ORGCODEOFJR = "OrgCodeOfJr";
	public static String PROP_ORGCODEOFZZ = "OrgCodeOfZz";
	public static String PROP_ORGCODEOFXY = "OrgCodeOfXy";
	public static String PROP_ORGNAME = "OrgName";
	public static String PROP_DEPTNAME = "DeptName";
	public static String PROP_DEPTNO = "DeptNo";
	public static String PROP_PHONE = "Phone";
	public static String PROP_PHOTO = "Photo";
	public static String PROP_USERSTATUS = "UserStatus";
	public static String PROP_LOGINDATE = "LoginDate";
	public static String PROP_CREATERCODE = "CreaterCode";
	public static String PROP_CREATERNAME = "CreaterName";
	public static String PROP_CREATEDATE = "CreateDate";
	public static String PROP_STOPERCODE = "StoperCode";
	public static String PROP_STOPERNAME = "StoperName";
	public static String PROP_STOPDATE = "StopDate";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";

	// constructors
	public BaseBsUserInfo() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserInfo(java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String password;
	private java.lang.String zxName1;
	private java.lang.String zxName2;
	private java.lang.String zxName3;
	private java.lang.String zxName4;
	private java.lang.String zxName5;
	private java.lang.String zxName6;
	private java.lang.String zxName7;
	private java.lang.String zxName8;
	private java.lang.String zxName9;
	private java.lang.String cardType;
	private java.lang.String cardId;
	private java.lang.String jgPrincipal;
	private java.lang.String zxPrincipal;
	private java.lang.String userCode;
	private java.lang.String education;
	private java.lang.String post;
	private java.lang.String orgCodeOfJr;
	private java.lang.String orgCodeOfZz;
	private java.lang.String orgCodeOfXy;
	private java.lang.String orgName;
	private java.lang.String deptName;
	private java.lang.String deptNo;
	private java.lang.String phone;
	private java.lang.String photo;
	private java.lang.String userStatus;
	private java.lang.String createrName;
	private java.lang.String createrCode;
	private java.lang.String stoperCode;
	private java.lang.String stoperName;
	private java.lang.String status;
	private java.lang.String flag;
	private java.util.Date loginDate;
	private java.util.Date createDate;
	private java.util.Date stopDate;

	private java.lang.String NOWUSER, USERTYPE, ISPCBUSER, MANAGERCODE,
			LOGINID;

	private java.lang.String ZXORGCODE1, ZXORGCODE2, ZXORGCODE3, ZXORGCODE4,
			ZXORGCODE5, ZXORGCODE6, ZXORGCODE7, ZXORGCODE8, ZXORGCODE9;

	private java.lang.String ZXORGNAME1, ZXORGNAME2, ZXORGNAME3, ZXORGNAME4,
			ZXORGNAME5, ZXORGNAME6, ZXORGNAME7, ZXORGNAME8, ZXORGNAME9;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id column="ID"
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getZxName1() {
		return zxName1;
	}

	public void setZxName1(java.lang.String zxName1) {
		this.zxName1 = zxName1;
	}

	public java.lang.String getZxName2() {
		return zxName2;
	}

	public void setZxName2(java.lang.String zxName2) {
		this.zxName2 = zxName2;
	}

	public java.lang.String getZxName3() {
		return zxName3;
	}

	public void setZxName3(java.lang.String zxName3) {
		this.zxName3 = zxName3;
	}

	public java.lang.String getZxName4() {
		return zxName4;
	}

	public void setZxName4(java.lang.String zxName4) {
		this.zxName4 = zxName4;
	}

	public java.lang.String getZxName5() {
		return zxName5;
	}

	public void setZxName5(java.lang.String zxName5) {
		this.zxName5 = zxName5;
	}

	public java.lang.String getZxName6() {
		return zxName6;
	}

	public void setZxName6(java.lang.String zxName6) {
		this.zxName6 = zxName6;
	}

	public java.lang.String getZxName7() {
		return zxName7;
	}

	public void setZxName7(java.lang.String zxName7) {
		this.zxName7 = zxName7;
	}

	public java.lang.String getZxName8() {
		return zxName8;
	}

	public void setZxName8(java.lang.String zxName8) {
		this.zxName8 = zxName8;
	}

	public java.lang.String getZxName9() {
		return zxName9;
	}

	public void setZxName9(java.lang.String zxName9) {
		this.zxName9 = zxName9;
	}

	public java.lang.String getCardType() {
		return cardType;
	}

	public void setCardType(java.lang.String cardType) {
		this.cardType = cardType;
	}

	public java.lang.String getCardId() {
		return cardId;
	}

	public void setCardId(java.lang.String cardId) {
		this.cardId = cardId;
	}

	public java.lang.String getJgPrincipal() {
		return jgPrincipal;
	}

	public void setJgPrincipal(java.lang.String jgPrincipal) {
		this.jgPrincipal = jgPrincipal;
	}

	public java.lang.String getZxPrincipal() {
		return zxPrincipal;
	}

	public void setZxPrincipal(java.lang.String zxPrincipal) {
		this.zxPrincipal = zxPrincipal;
	}

	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	public java.lang.String getEducation() {
		return education;
	}

	public void setEducation(java.lang.String education) {
		this.education = education;
	}

	public java.lang.String getPost() {
		return post;
	}

	public void setPost(java.lang.String post) {
		this.post = post;
	}

	public java.lang.String getOrgCodeOfJr() {
		return orgCodeOfJr;
	}

	public void setOrgCodeOfJr(java.lang.String orgCodeOfJr) {
		this.orgCodeOfJr = orgCodeOfJr;
	}

	public java.lang.String getOrgCodeOfZz() {
		return orgCodeOfZz;
	}

	public void setOrgCodeOfZz(java.lang.String orgCodeOfZz) {
		this.orgCodeOfZz = orgCodeOfZz;
	}

	public java.lang.String getOrgCodeOfXy() {
		return orgCodeOfXy;
	}

	public void setOrgCodeOfXy(java.lang.String orgCodeOfXy) {
		this.orgCodeOfXy = orgCodeOfXy;
	}

	public java.lang.String getOrgName() {
		return orgName;
	}

	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.lang.String getPhoto() {
		return photo;
	}

	public void setPhoto(java.lang.String photo) {
		this.photo = photo;
	}

	public java.lang.String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(java.lang.String userStatus) {
		this.userStatus = userStatus;
	}

	public java.lang.String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(java.lang.String createrName) {
		this.createrName = createrName;
	}

	public java.lang.String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(java.lang.String createrCode) {
		this.createrCode = createrCode;
	}

	public java.lang.String getStoperCode() {
		return stoperCode;
	}

	public void setStoperCode(java.lang.String stoperCode) {
		this.stoperCode = stoperCode;
	}

	public java.lang.String getStoperName() {
		return stoperName;
	}

	public void setStoperName(java.lang.String stoperName) {
		this.stoperName = stoperName;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getFlag() {
		return flag;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	public java.util.Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(java.util.Date loginDate) {
		this.loginDate = loginDate;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(java.util.Date stopDate) {
		this.stopDate = stopDate;
	}

	public java.lang.String getDeptName() {
		return deptName;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}

	public java.lang.String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(java.lang.String deptNo) {
		this.deptNo = deptNo;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserInfo))
			return false;
		else {
			com.gtm.csims.model.BsUserInfo bsUserInfo = (com.gtm.csims.model.BsUserInfo) obj;
			if (null == this.getId() || null == bsUserInfo.getId())
				return false;
			else
				return (this.getId().equals(bsUserInfo.getId()));
		}
	}

	@Override
	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public java.lang.String getNOWUSER() {
		return NOWUSER;
	}

	public void setNOWUSER(java.lang.String nowuser) {
		NOWUSER = nowuser;
	}

	public java.lang.String getUSERTYPE() {
		return USERTYPE;
	}

	public void setUSERTYPE(java.lang.String usertype) {
		USERTYPE = usertype;
	}

	public java.lang.String getISPCBUSER() {
		return ISPCBUSER;
	}

	public void setISPCBUSER(java.lang.String ispcbuser) {
		ISPCBUSER = ispcbuser;
	}

	public java.lang.String getMANAGERCODE() {
		return MANAGERCODE;
	}

	public void setMANAGERCODE(java.lang.String managercode) {
		MANAGERCODE = managercode;
	}

	public java.lang.String getLOGINID() {
		return LOGINID;
	}

	public void setLOGINID(java.lang.String loginid) {
		LOGINID = loginid;
	}

	public java.lang.String getZXORGCODE1() {
		return ZXORGCODE1;
	}

	public void setZXORGCODE1(java.lang.String zxorgcode1) {
		ZXORGCODE1 = zxorgcode1;
	}

	public java.lang.String getZXORGCODE2() {
		return ZXORGCODE2;
	}

	public void setZXORGCODE2(java.lang.String zxorgcode2) {
		ZXORGCODE2 = zxorgcode2;
	}

	public java.lang.String getZXORGCODE3() {
		return ZXORGCODE3;
	}

	public void setZXORGCODE3(java.lang.String zxorgcode3) {
		ZXORGCODE3 = zxorgcode3;
	}

	public java.lang.String getZXORGCODE4() {
		return ZXORGCODE4;
	}

	public void setZXORGCODE4(java.lang.String zxorgcode4) {
		ZXORGCODE4 = zxorgcode4;
	}

	public java.lang.String getZXORGCODE5() {
		return ZXORGCODE5;
	}

	public void setZXORGCODE5(java.lang.String zxorgcode5) {
		ZXORGCODE5 = zxorgcode5;
	}

	public java.lang.String getZXORGCODE6() {
		return ZXORGCODE6;
	}

	public void setZXORGCODE6(java.lang.String zxorgcode6) {
		ZXORGCODE6 = zxorgcode6;
	}

	public java.lang.String getZXORGCODE7() {
		return ZXORGCODE7;
	}

	public void setZXORGCODE7(java.lang.String zxorgcode7) {
		ZXORGCODE7 = zxorgcode7;
	}

	public java.lang.String getZXORGCODE8() {
		return ZXORGCODE8;
	}

	public void setZXORGCODE8(java.lang.String zxorgcode8) {
		ZXORGCODE8 = zxorgcode8;
	}

	public java.lang.String getZXORGCODE9() {
		return ZXORGCODE9;
	}

	public void setZXORGCODE9(java.lang.String zxorgcode9) {
		ZXORGCODE9 = zxorgcode9;
	}

	public java.lang.String getZXORGNAME1() {
		return ZXORGNAME1;
	}

	public void setZXORGNAME1(java.lang.String zxorgname1) {
		ZXORGNAME1 = zxorgname1;
	}

	public java.lang.String getZXORGNAME2() {
		return ZXORGNAME2;
	}

	public void setZXORGNAME2(java.lang.String zxorgname2) {
		ZXORGNAME2 = zxorgname2;
	}

	public java.lang.String getZXORGNAME3() {
		return ZXORGNAME3;
	}

	public void setZXORGNAME3(java.lang.String zxorgname3) {
		ZXORGNAME3 = zxorgname3;
	}

	public java.lang.String getZXORGNAME4() {
		return ZXORGNAME4;
	}

	public void setZXORGNAME4(java.lang.String zxorgname4) {
		ZXORGNAME4 = zxorgname4;
	}

	public java.lang.String getZXORGNAME5() {
		return ZXORGNAME5;
	}

	public void setZXORGNAME5(java.lang.String zxorgname5) {
		ZXORGNAME5 = zxorgname5;
	}

	public java.lang.String getZXORGNAME6() {
		return ZXORGNAME6;
	}

	public void setZXORGNAME6(java.lang.String zxorgname6) {
		ZXORGNAME6 = zxorgname6;
	}

	public java.lang.String getZXORGNAME7() {
		return ZXORGNAME7;
	}

	public void setZXORGNAME7(java.lang.String zxorgname7) {
		ZXORGNAME7 = zxorgname7;
	}

	public java.lang.String getZXORGNAME8() {
		return ZXORGNAME8;
	}

	public void setZXORGNAME8(java.lang.String zxorgname8) {
		ZXORGNAME8 = zxorgname8;
	}

	public java.lang.String getZXORGNAME9() {
		return ZXORGNAME9;
	}

	public void setZXORGNAME9(java.lang.String zxorgname9) {
		ZXORGNAME9 = zxorgname9;
	}

}