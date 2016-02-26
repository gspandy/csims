package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_DURING table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BsUserInfo"
 */

public abstract class BaseBsUserApplyInfo  implements Serializable {

	public static String REF = "BsUserApplyInfo";
	public static String PROP_ID = "Id";
	public static String PROP_NAME = "Name";
	public static String PROP_USERTYPE = "UserType";
	public static String PROP_ISPCBUSER = "IsPcbUser";
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
	public static String PROP_ZXORGCODE1 = "ZxOrgCode1";
	public static String PROP_ZXORGCODE2 = "ZxOrgCode2";
	public static String PROP_ZXORGCODE3 = "ZxOrgCode3";
	public static String PROP_ZXORGCODE4 = "ZxOrgCode4";
	public static String PROP_ZXORGCODE5 = "ZxOrgCode5";
	public static String PROP_ZXORGCODE6 = "ZxOrgCode6";
	public static String PROP_ZXORGCODE7 = "ZxOrgCode7";
	public static String PROP_ZXORGCODE8 = "ZxOrgCode8";
	public static String PROP_ZXORGCODE9 = "ZxOrgCode9";
	public static String PROP_ZXORGNAME1 = "ZxOrgName1";
	public static String PROP_ZXORGNAME2 = "ZxOrgName2";
	public static String PROP_ZXORGNAME3 = "ZxOrgName3";
	public static String PROP_ZXORGNAME4 = "ZxOrgName4";
	public static String PROP_ZXORGNAME5 = "ZxOrgName5";
	public static String PROP_ZXORGNAME6 = "ZxOrgName6";
	public static String PROP_ZXORGNAME7 = "ZxOrgName7";
	public static String PROP_ZXORGNAME8 = "ZxOrgName8";
	public static String PROP_ZXORGNAME9 = "ZxOrgName9";
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
	public static String PROP_PHONE = "Phone";
	public static String PROP_PHOTO = "Photo";
	public static String PROP_APPLYCODE = "ApplyCode";
	public static String PROP_APPLYNAME = "ApplyName";
	public static String PROP_CREATERCODE = "CreaterCode";
	public static String PROP_CREATEDATE = "CreateDate";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";
	public static String PROP_DEPTNAME = "DeptName";
	public static String PROP_DEPTNO = "DeptNo";
	public static String PROP_USERCARD = "UserCard";

	// constructors
	public BaseBsUserApplyInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserApplyInfo (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String isPcbUser;
	private java.lang.String userType;
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
	private java.lang.String zxOrgCode1;
	private java.lang.String zxOrgCode2;
	private java.lang.String zxOrgCode3;
	private java.lang.String zxOrgCode4;
	private java.lang.String zxOrgCode5;
	private java.lang.String zxOrgCode6;
	private java.lang.String zxOrgCode7;
	private java.lang.String zxOrgCode8;
	private java.lang.String zxOrgCode9;
	private java.lang.String zxOrgName1;
	private java.lang.String zxOrgName2;
	private java.lang.String zxOrgName3;
	private java.lang.String zxOrgName4;
	private java.lang.String zxOrgName5;
	private java.lang.String zxOrgName6;
	private java.lang.String zxOrgName7;
	private java.lang.String zxOrgName8;
	private java.lang.String zxOrgName9;
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
	private java.lang.String phone;
	private java.lang.String photo;
	private java.lang.String applyCode;
	private java.lang.String applyName;
	private java.lang.String createrCode;
	private java.lang.String status;
	private java.lang.String flag;
	private java.util.Date  createDate;
	private java.lang.String deptName;
	private java.lang.String deptNo;
	private java.lang.String userCard;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  column="ID"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
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

	public java.lang.String getIsPcbUser() {
		return isPcbUser;
	}

	public void setIsPcbUser(java.lang.String isPcbUser) {
		this.isPcbUser = isPcbUser;
	}

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
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

	public java.lang.String getUserCard() {
		return userCard;
	}

	public void setUserCard(java.lang.String userCard) {
		this.userCard = userCard;
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

	public java.lang.String getZxOrgCode1() {
		return zxOrgCode1;
	}

	public void setZxOrgCode1(java.lang.String zxOrgCode1) {
		this.zxOrgCode1 = zxOrgCode1;
	}

	public java.lang.String getZxOrgCode2() {
		return zxOrgCode2;
	}

	public void setZxOrgCode2(java.lang.String zxOrgCode2) {
		this.zxOrgCode2 = zxOrgCode2;
	}

	public java.lang.String getZxOrgCode3() {
		return zxOrgCode3;
	}

	public void setZxOrgCode3(java.lang.String zxOrgCode3) {
		this.zxOrgCode3 = zxOrgCode3;
	}

	public java.lang.String getZxOrgCode4() {
		return zxOrgCode4;
	}

	public void setZxOrgCode4(java.lang.String zxOrgCode4) {
		this.zxOrgCode4 = zxOrgCode4;
	}

	public java.lang.String getZxOrgCode5() {
		return zxOrgCode5;
	}

	public void setZxOrgCode5(java.lang.String zxOrgCode5) {
		this.zxOrgCode5 = zxOrgCode5;
	}

	public java.lang.String getZxOrgCode6() {
		return zxOrgCode6;
	}

	public void setZxOrgCode6(java.lang.String zxOrgCode6) {
		this.zxOrgCode6 = zxOrgCode6;
	}

	public java.lang.String getZxOrgCode7() {
		return zxOrgCode7;
	}

	public void setZxOrgCode7(java.lang.String zxOrgCode7) {
		this.zxOrgCode7 = zxOrgCode7;
	}

	public java.lang.String getZxOrgCode8() {
		return zxOrgCode8;
	}

	public void setZxOrgCode8(java.lang.String zxOrgCode8) {
		this.zxOrgCode8 = zxOrgCode8;
	}

	public java.lang.String getZxOrgCode9() {
		return zxOrgCode9;
	}

	public void setZxOrgCode9(java.lang.String zxOrgCode9) {
		this.zxOrgCode9 = zxOrgCode9;
	}

	public java.lang.String getZxOrgName1() {
		return zxOrgName1;
	}

	public void setZxOrgName1(java.lang.String zxOrgName1) {
		this.zxOrgName1 = zxOrgName1;
	}

	public java.lang.String getZxOrgName2() {
		return zxOrgName2;
	}

	public void setZxOrgName2(java.lang.String zxOrgName2) {
		this.zxOrgName2 = zxOrgName2;
	}

	public java.lang.String getZxOrgName3() {
		return zxOrgName3;
	}

	public void setZxOrgName3(java.lang.String zxOrgName3) {
		this.zxOrgName3 = zxOrgName3;
	}

	public java.lang.String getZxOrgName4() {
		return zxOrgName4;
	}

	public void setZxOrgName4(java.lang.String zxOrgName4) {
		this.zxOrgName4 = zxOrgName4;
	}

	public java.lang.String getZxOrgName5() {
		return zxOrgName5;
	}

	public void setZxOrgName5(java.lang.String zxOrgName5) {
		this.zxOrgName5 = zxOrgName5;
	}

	public java.lang.String getZxOrgName6() {
		return zxOrgName6;
	}

	public void setZxOrgName6(java.lang.String zxOrgName6) {
		this.zxOrgName6 = zxOrgName6;
	}

	public java.lang.String getZxOrgName7() {
		return zxOrgName7;
	}

	public void setZxOrgName7(java.lang.String zxOrgName7) {
		this.zxOrgName7 = zxOrgName7;
	}

	public java.lang.String getZxOrgName8() {
		return zxOrgName8;
	}

	public void setZxOrgName8(java.lang.String zxOrgName8) {
		this.zxOrgName8 = zxOrgName8;
	}

	public java.lang.String getZxOrgName9() {
		return zxOrgName9;
	}

	public void setZxOrgName9(java.lang.String zxOrgName9) {
		this.zxOrgName9 = zxOrgName9;
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

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(java.lang.String applyCode) {
		this.applyCode = applyCode;
	}

	public java.lang.String getApplyName() {
		return applyName;
	}

	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	public java.lang.String getFlag() {
		return flag;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.lang.String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(java.lang.String createrCode) {
		this.createrCode = createrCode;
	}

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserApplyInfo)) return false;
		else {
			com.gtm.csims.model.BsUserApplyInfo bs = (com.gtm.csims.model.BsUserApplyInfo) obj;
			if (null == this.getId() || null == bs.getId()) return false;
			else return (this.getId().equals(bs.getId()));
		}
	}

	@Override
	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	@Override
	public String toString () {
		return super.toString();
	}


}