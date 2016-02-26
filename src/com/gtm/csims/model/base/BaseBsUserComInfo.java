package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_AREA table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_BANK"
 */

public abstract class BaseBsUserComInfo  implements Serializable {

	public static String REF = "BsUserComInfo";
	public static String PROP_ID = "Id";
	public static String PROP_LOGINID = "LoginId";
	public static String PROP_COMDATE = "ComDate";
	public static String PROP_COMCONTENT = "ComContent";
	public static String PROP_COMORGNO = "ComOrgNo";
	public static String PROP_COMORGNAME = "ComOrgName";

	// constructors
	public BaseBsUserComInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserComInfo (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String loginId;
	private java.lang.String comDate;
	private java.lang.String comContent;
	private java.lang.String comOrgNo;
	private java.lang.String comOrgName;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.String getLoginId() {
		return loginId;
	}

	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}

	public java.lang.String getComDate() {
		return comDate;
	}

	public void setComDate(java.lang.String comDate) {
		this.comDate = comDate;
	}

	public java.lang.String getComContent() {
		return comContent;
	}

	public void setComContent(java.lang.String comContent) {
		this.comContent = comContent;
	}

	public java.lang.String getComOrgNo() {
		return comOrgNo;
	}

	public void setComOrgNo(java.lang.String comOrgNo) {
		this.comOrgNo = comOrgNo;
	}

	public java.lang.String getComOrgName() {
		return comOrgName;
	}

	public void setComOrgName(java.lang.String comOrgName) {
		this.comOrgName = comOrgName;
	}

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserComInfo)) return false;
		else {
			com.gtm.csims.model.BsUserComInfo bs = (com.gtm.csims.model.BsUserComInfo) obj;
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