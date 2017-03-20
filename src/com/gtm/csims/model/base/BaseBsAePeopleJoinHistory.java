package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_AEPEOPLE table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="BS_AEPEOPLE"
 */

public abstract class BaseBsAePeopleJoinHistory implements Serializable {

	/**
	 * 
	 */
    private static final long serialVersionUID = 8123892415814948628L;

	// constructors
	public BaseBsAePeopleJoinHistory() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAePeopleJoinHistory(java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String pepname;
	private java.lang.String certtp;
	private java.lang.String certno;
	private java.lang.String cardid;
	private java.lang.String orgno;
	private java.lang.String orgnm;
	private java.lang.String dptnm;

	private java.lang.String aeno;
	private java.lang.String aeorgnm;
	private java.lang.String aeorgno;
	private java.lang.String aeedorgnm;
	private java.lang.String aeedorgno;

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

	public java.lang.String getPepname() {
		return pepname;
	}

	public void setPepname(java.lang.String pepname) {
		this.pepname = pepname;
	}

	public java.lang.String getCerttp() {
		return certtp;
	}

	public void setCerttp(java.lang.String certtp) {
		this.certtp = certtp;
	}

	public java.lang.String getCertno() {
		return certno;
	}

	public void setCertno(java.lang.String certno) {
		this.certno = certno;
	}

	public java.lang.String getCardid() {
		return cardid;
	}

	public void setCardid(java.lang.String cardid) {
		this.cardid = cardid;
	}

	public java.lang.String getOrgno() {
		return orgno;
	}

	public void setOrgno(java.lang.String orgno) {
		this.orgno = orgno;
	}

	public java.lang.String getOrgnm() {
		return orgnm;
	}

	public void setOrgnm(java.lang.String orgnm) {
		this.orgnm = orgnm;
	}

	public java.lang.String getDptnm() {
		return dptnm;
	}

	public void setDptnm(java.lang.String dptnm) {
		this.dptnm = dptnm;
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