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

public abstract class BaseBsUserPluralInfo  implements Serializable {

	public static String REF = "BsUserPluralInfo";
	public static String PROP_ID = "Id";
	public static String PROP_LOGINID = "LoginId";
	public static String PROP_ZXNAME1 = "ZxName1";
	public static String PROP_ZXNAME2 = "ZxName2";
	public static String PROP_ZXNAME3 = "ZxName3";
	public static String PROP_ZXNAME4 = "ZxName4";
	public static String PROP_ZXNAME5 = "ZxName5";
	public static String PROP_ZXNAME6 = "ZxName6";
	public static String PROP_ZXNAME7 = "ZxName7";
	public static String PROP_ZXNAME8 = "ZxName8";
	public static String PROP_ZXNAME9 = "ZxName9";
	public static String PROP_JGPRINCIPAL = "JgPrincipal";
	public static String PROP_ZXPRINCIPAL = "ZxPrincipal";
	public static String PROP_ORGCODEOFJR = "OrgCodeOfJr";
	public static String PROP_ORGCODEOFZZ = "OrgCodeOfZz";
	public static String PROP_ORGCODEOFXY = "OrgCodeOfXy";
	public static String PROP_ORGNAME = "OrgName";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";
	public static String PROP_DEPTNAME = "DeptName";
	public static String PROP_DEPTNO = "DeptNo";


	// constructors
	public BaseBsUserPluralInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserPluralInfo (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String loginId;
	private java.lang.String zxName1;
	private java.lang.String zxName2;
	private java.lang.String zxName3;
	private java.lang.String zxName4;
	private java.lang.String zxName5;
	private java.lang.String zxName6;
	private java.lang.String zxName7;
	private java.lang.String zxName8;
	private java.lang.String zxName9;
	private java.lang.String jgPrincipal;
	private java.lang.String zxPrincipal;
	private java.lang.String orgCodeOfJr;
	private java.lang.String orgCodeOfZz;
	private java.lang.String orgCodeOfXy;
	private java.lang.String orgName;
	private java.lang.String status;
	private java.lang.String flag;
	private java.lang.String deptName;
	private java.lang.String deptNo;


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

	public java.lang.String getLoginId() {
		return loginId;
	}

	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
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

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserPluralInfo)) return false;
		else {
			com.gtm.csims.model.BsUserPluralInfo bs = (com.gtm.csims.model.BsUserPluralInfo) obj;
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