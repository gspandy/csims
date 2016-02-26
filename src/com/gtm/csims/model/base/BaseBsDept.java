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

public abstract class BaseBsDept  implements Serializable {

	public static String REF = "BaseBsDept";
	public static String PROP_ID = "Id";
	public static String PROP_NAME = "Name";
	public static String PROP_ORGNO = "Orgno";
	public static String PROP_ORGNAME = "Orgname";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";


	// constructors
	public BaseBsDept () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsDept (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String orgno;
	private java.lang.String orgname;
	private java.lang.String status;
	private java.lang.String flag;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
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

	public java.lang.String getOrgno() {
		return orgno;
	}

	public void setOrgno(java.lang.String orgno) {
		this.orgno = orgno;
	}

	public java.lang.String getOrgname() {
		return orgname;
	}

	public void setOrgname(java.lang.String orgname) {
		this.orgname = orgname;
	}

	@Override
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsDept)) return false;
		else {
			com.gtm.csims.model.BsDept bs = (com.gtm.csims.model.BsDept) obj;
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