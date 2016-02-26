package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_WORKCHECK table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_USERRELEVANTINFO"
 */

public abstract class BaseBsUserRelevantInfo  implements Serializable {

	public static String REF = "BsUserRelevantInfo";
	public static String PROP_ID = "Id";
	public static String PROP_USERLIGONID = "UserLoginId";
	public static String PROP_USERNAME = "UserName";
	public static String PROP_INFOTYPE = "InfoType";
	public static String PROP_RELEVANTID = "RelevantId";
	public static String PROP_RELEVANTINFO = "RelevantInfo";
	public static String PROP_RELEVANTDATE = "RelevantDate";
	public static String PROP_STAT = "Stat";
	public static String PROP_FLAG = "Flag";


	// constructors
	public BaseBsUserRelevantInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsUserRelevantInfo (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String userLoginId;
	private java.lang.String userName;
	private java.lang.String relevantId;
	private java.lang.String infoType;
	private java.lang.String relevantInfo;
	private java.lang.String relevantDate;
	private java.lang.String stat;
	private java.lang.String flag;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
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

	public java.lang.String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(java.lang.String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getRelevantId() {
		return relevantId;
	}

	public void setRelevantId(java.lang.String relevantId) {
		this.relevantId = relevantId;
	}

	public java.lang.String getInfoType() {
		return infoType;
	}

	public void setInfoType(java.lang.String infoType) {
		this.infoType = infoType;
	}

	public java.lang.String getRelevantInfo() {
		return relevantInfo;
	}

	public void setRelevantInfo(java.lang.String relevantInfo) {
		this.relevantInfo = relevantInfo;
	}

	public java.lang.String getRelevantDate() {
		return relevantDate;
	}

	public void setRelevantDate(java.lang.String relevantDate) {
		this.relevantDate = relevantDate;
	}

	/**
	 * Return the value associated with the column: STAT
	 */
	public java.lang.String getStat () {
		return stat;
	}

	/**
	 * Set the value related to the column: STAT
	 * @param stat the STAT value
	 */
	public void setStat (java.lang.String stat) {
		this.stat = stat;
	}



	/**
	 * Return the value associated with the column: FLAG
	 */
	public java.lang.String getFlag () {
		return flag;
	}

	/**
	 * Set the value related to the column: FLAG
	 * @param flag the FLAG value
	 */
	public void setFlag (java.lang.String flag) {
		this.flag = flag;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsUserRelevantInfo)) return false;
		else {
			com.gtm.csims.model.BsUserRelevantInfo bs = (com.gtm.csims.model.BsUserRelevantInfo) obj;
			if (null == this.getId() || null == bs.getId()) return false;
			else return (this.getId().equals(bs.getId()));
		}
	}

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


	public String toString () {
		return super.toString();
	}


}