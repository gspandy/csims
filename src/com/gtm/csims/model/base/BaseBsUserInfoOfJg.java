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

public abstract class BaseBsUserInfoOfJg  implements Serializable {

	public static String REF = "BsUserInfoOfJg";
	public static String PROP_ID = "Id";
	public static String PROP_APPLYER = "Applyer";
	public static String PROP_LOGINID = "LoginId";
	public static String PROP_ISPCBUSER = "IsPcbUser";
	public static String PROP_USERTYPE = "UserType";
	public static String PROP_NAME = "Name";
	public static String PROP_PASSWORD = "Password";
	public static String PROP_CARDTYPE = "CardType";
	public static String PROP_JGPRINCIPAL = "JgPrincipal";
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
	public static String PROP_USERCARD = "UserCard";
	public static String PROP_USERSTATUS = "UserStatus";
	public static String PROP_LOGINDATE = "LoginDate";
	public static String PROP_CREATERCODE = "CreaterCode";
	public static String PROP_CREATERNAME = "CreaterName";
	public static String PROP_CREATEDATE = "CreateDate";
	public static String PROP_MANAGERCODE = "ManagerCode";
	public static String PROP_STOPERCODE = "StoperCode";
	public static String PROP_STOPERNAME = "StoperName";
	public static String PROP_STOPDATE = "StopDate";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";
	public static String PROP_NOWUSER = "NowUser";

	// constructors
	public BaseBsUserInfoOfJg () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserInfoOfJg (java.lang.String id) {
		this.setId(id);
		initialize();
	}
	protected void initialize () {}
	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String loginId;
	private java.lang.String applyer;
	private java.lang.String userType;
	private java.lang.String isPcbUser;
	private java.lang.String name;
	private java.lang.String password;
	private java.lang.String cardType;
	private java.lang.String jgPrincipal;
	private java.lang.String education;
	private java.lang.String post;
	private java.lang.String userCard;
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
	private java.lang.String managerCode;
	private java.lang.String stoperCode;
	private java.lang.String stoperName;
	private java.lang.String status;
	private java.lang.String flag;
	private java.lang.String nowUser;
	private java.util.Date  loginDate;
	private java.util.Date  createDate;
	private java.util.Date  stopDate;


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

	public java.lang.String getApplyer() {
		return applyer;
	}

	public void setApplyer(java.lang.String applyer) {
		this.applyer = applyer;
	}

	public java.lang.String getLoginId() {
		return loginId;
	}

	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
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

	public java.lang.String getCardType() {
		return cardType;
	}

	public void setCardType(java.lang.String cardType) {
		this.cardType = cardType;
	}

	public java.lang.String getJgPrincipal() {
		return jgPrincipal;
	}

	public void setJgPrincipal(java.lang.String jgPrincipal) {
		this.jgPrincipal = jgPrincipal;
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

	public java.lang.String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(java.lang.String managerCode) {
		this.managerCode = managerCode;
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

	public java.lang.String getNowUser() {
		return nowUser;
	}

	public void setNowUser(java.lang.String nowUser) {
		this.nowUser = nowUser;
	}

	public java.lang.String getUserCard() {
		return userCard;
	}

	public void setUserCard(java.lang.String userCard) {
		this.userCard = userCard;
	}

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserInfoOfJg)) return false;
		else {
			com.gtm.csims.model.BsUserInfoOfJg bs = (com.gtm.csims.model.BsUserInfoOfJg) obj;
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