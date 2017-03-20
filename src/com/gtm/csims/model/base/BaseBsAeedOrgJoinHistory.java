package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_AEPEOPLE table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="BS_AEPEOPLE"
 */

public abstract class BaseBsAeedOrgJoinHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8123892415814948628L;

	// constructors
	public BaseBsAeedOrgJoinHistory() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAeedOrgJoinHistory(java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	// primary key
	private java.lang.String id;

	private java.lang.String aeno;
	private java.lang.String aeorgnm;
	private java.lang.String aeorgno;
	private java.lang.String aeedorgnm;
	private java.lang.String aeedorgno;
	private java.lang.String aeedorgtypeno;

	private java.lang.Integer pricipal;

	private java.util.Date aeplanstdate;
	private java.util.Date aeplantmdate;
	private java.util.Date aeEnforceCreateDate;

	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updatedate;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getAeno() {
		return aeno;
	}

	public void setAeno(java.lang.String aeno) {
		this.aeno = aeno;
	}

	public java.lang.String getAeorgnm() {
		return aeorgnm;
	}

	public void setAeorgnm(java.lang.String aeorgnm) {
		this.aeorgnm = aeorgnm;
	}

	public java.lang.String getAeorgno() {
		return aeorgno;
	}

	public void setAeorgno(java.lang.String aeorgno) {
		this.aeorgno = aeorgno;
	}

	public java.lang.String getAeedorgnm() {
		return aeedorgnm;
	}

	public void setAeedorgnm(java.lang.String aeedorgnm) {
		this.aeedorgnm = aeedorgnm;
	}

	public java.lang.String getAeedorgno() {
		return aeedorgno;
	}

	public void setAeedorgno(java.lang.String aeedorgno) {
		this.aeedorgno = aeedorgno;
	}

	public java.lang.String getAeedorgtypeno() {
		return aeedorgtypeno;
	}

	public void setAeedorgtypeno(java.lang.String aeedorgtypeno) {
		this.aeedorgtypeno = aeedorgtypeno;
	}

	public java.lang.Integer getPricipal() {
		return pricipal;
	}

	public void setPricipal(java.lang.Integer pricipal) {
		this.pricipal = pricipal;
	}

	public java.util.Date getAeplanstdate() {
		return aeplanstdate;
	}

	public void setAeplanstdate(java.util.Date aeplanstdate) {
		this.aeplanstdate = aeplanstdate;
	}

	public java.util.Date getAeplantmdate() {
		return aeplantmdate;
	}

	public void setAeplantmdate(java.util.Date aeplantmdate) {
		this.aeplantmdate = aeplantmdate;
	}

	public java.util.Date getAeEnforceCreateDate() {
		return aeEnforceCreateDate;
	}

	public void setAeEnforceCreateDate(java.util.Date aeEnforceCreateDate) {
		this.aeEnforceCreateDate = aeEnforceCreateDate;
	}

	public java.lang.String getStat() {
		return stat;
	}

	public void setStat(java.lang.String stat) {
		this.stat = stat;
	}

	public java.lang.String getFlag() {
		return flag;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate = updatedate;
	}


}