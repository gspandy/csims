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

public abstract class BaseBsUserInfoOfZx  implements Serializable {

	public static String REF = "BsUserInfoOfZx";
	public static String PROP_ID = "Id";
	public static String PROP_USERID = "UserId";
	public static String PROP_LOGINID = "LoginId";
	public static String PROP_ZXUAERTYPE = "ZxUserType";
	public static String PROP_ZXUSERNAME = "ZxUserName";
	public static String PROP_ORGCODE = "OrgCode";
	public static String PROP_ORGNAME = "OrgName";
	public static String PROP_USERSTATUS = "UserStatus";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";
	public static String PROP_NOWUSER = "NowUser";
	public static String PROP_CREATEDATE = "CreateDate";
	public static String PROP_STOPDATE = "StopDate";
	


	// constructors
	public BaseBsUserInfoOfZx () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserInfoOfZx (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String userId;
	private java.lang.String loginId;
	private java.lang.String zxUserName;
	private java.lang.String zxUserType;
	private java.lang.String orgCode;
	private java.lang.String orgName;
	private java.lang.String userStatus;
	private java.lang.String status;
	private java.lang.String flag;
	private java.lang.String nowUser;
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

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getLoginId() {
		return loginId;
	}

	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}

	public java.lang.String getOrgName() {
		return orgName;
	}

	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	public java.lang.String getZxUserName() {
		return zxUserName;
	}

	public void setZxUserName(java.lang.String zxUserName) {
		this.zxUserName = zxUserName;
	}

	public java.lang.String getZxUserType() {
		return zxUserType;
	}

	public void setZxUserType(java.lang.String zxUserType) {
		this.zxUserType = zxUserType;
	}

	public java.lang.String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	public java.lang.String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(java.lang.String userStatus) {
		this.userStatus = userStatus;
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

	public java.lang.String getNowUser() {
		return nowUser;
	}

	public void setNowUser(java.lang.String nowUser) {
		this.nowUser = nowUser;
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

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserInfoOfZx)) return false;
		else {
			com.gtm.csims.model.BsUserInfoOfZx bs = (com.gtm.csims.model.BsUserInfoOfZx) obj;
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