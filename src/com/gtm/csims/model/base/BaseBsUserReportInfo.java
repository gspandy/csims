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

public abstract class BaseBsUserReportInfo  implements Serializable {

	public static String REF = "BsUserReportInfo";
	public static String PROP_ID = "Id";
	public static String PROP_USERZXORGNAME = "UserZxOrgName";
	public static String PROP_USERZXNAME = "UserZxName";
	public static String PROP_ZXXTNAME = "ZxXtName";
	public static String PROP_ZXXTUSERTYPE = "ZxXtUserType";
	public static String PROP_LOGINID = "LoginId";
	public static String PROP_USERTYPE = "UserType";
	public static String PROP_NAME = "Name";
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
	public static String PROP_CREATERNAME = "CreaterName";
	public static String PROP_REPORTDATE = "ReportDate";
	public static String PROP_REPORTTYPE = "ReportType";
	public static String PROP_APPLYNAME = "ApplyName";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";
	public static String PROP_P = "P";
	public static String PROP_C = "C";
	public static String PROP_SIX = "Six";

	// constructors
	public BaseBsUserReportInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserReportInfo (java.lang.String id) {
		this.setId(id);
		initialize();
	}
	protected void initialize () {}
	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String loginId;
	private java.lang.String userZxOrgName;
	private java.lang.String userZxName;
	private java.lang.String zxXtName;
	private java.lang.String zxXtUserType;
	private java.lang.String userType;
	private java.lang.String name;
	private java.lang.String cardType;
	private java.lang.String jgPrincipal;
	private java.lang.String education;
	private java.lang.String post;
	private java.lang.String orgCodeOfJr;
	private java.lang.String orgCodeOfZz;
	private java.lang.String orgCodeOfXy;
	private java.lang.String orgName;
	private java.lang.String deptName;
	private java.lang.String deptNo;
	private java.lang.String phone;
	private java.lang.String createrName;
	private java.lang.String applyName;
	private java.lang.String status;
	private java.lang.String flag;
	private java.lang.String p;
	private java.lang.String c;
	private java.lang.String reportType;
	private java.lang.String six;
	private java.util.Date  reportDate;


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

	public java.lang.String getUserZxOrgName() {
		return userZxOrgName;
	}

	public void setUserZxOrgName(java.lang.String userZxOrgName) {
		this.userZxOrgName = userZxOrgName;
	}

	public java.lang.String getSix() {
		return six;
	}

	public void setSix(java.lang.String six) {
		this.six = six;
	}

	public java.lang.String getUserZxName() {
		return userZxName;
	}

	public void setUserZxName(java.lang.String userZxName) {
		this.userZxName = userZxName;
	}

	public java.lang.String getZxXtName() {
		return zxXtName;
	}

	public void setZxXtName(java.lang.String zxXtName) {
		this.zxXtName = zxXtName;
	}

	public java.lang.String getZxXtUserType() {
		return zxXtUserType;
	}

	public void setZxXtUserType(java.lang.String zxXtUserType) {
		this.zxXtUserType = zxXtUserType;
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

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
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

	public java.lang.String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(java.lang.String createrName) {
		this.createrName = createrName;
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

	public java.lang.String getApplyName() {
		return applyName;
	}

	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	public java.lang.String getP() {
		return p;
	}

	public void setP(java.lang.String p) {
		this.p = p;
	}

	public java.lang.String getC() {
		return c;
	}

	public void setC(java.lang.String c) {
		this.c = c;
	}

	public java.lang.String getReportType() {
		return reportType;
	}

	public void setReportType(java.lang.String reportType) {
		this.reportType = reportType;
	}

	public java.util.Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(java.util.Date reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserReportInfo)) return false;
		else {
			com.gtm.csims.model.BsUserReportInfo bs = (com.gtm.csims.model.BsUserReportInfo) obj;
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