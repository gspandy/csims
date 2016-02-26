package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_NOGENERATE table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="BS_NOGENERATE"
 */

public abstract class BaseBsNogenerate implements Serializable {

	public static String REF = "BsNogenerate";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_ORGNM = "Orgnm";
	public static String PROP_EVDCNOYEAR = "Evdcnoyear";
	public static String PROP_PBNSHNOTEXT = "Pbnshnotext";
	public static String PROP_STAT = "Stat";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_EVDCNOINDEX = "Evdcnoindex";
	public static String PROP_PBNSHNOINDEX = "Pbnshnoindex";
	public static String PROP_FACTNOYEAR = "Factnoyear";
	public static String PROP_PBNSHNOYEAR = "Pbnshnoyear";
	public static String PROP_EVDCNOTEXT = "Evdcnotext";
	public static String PROP_AWAYNOTEXT = "Awaynotext";
	public static String PROP_FLAG = "Flag";
	public static String PROP_AENOTEXT = "Aenotext";
	public static String PROP_ORGNO = "Orgno";
	public static String PROP_AENOYEAR = "Aenoyear";
	public static String PROP_AREA = "Area";
	public static String PROP_AWAYNOINDEX = "Awaynoindex";
	public static String PROP_FACTNOTEXT = "Factnotext";
	public static String PROP_ID = "Id";
	public static String PROP_FACTNOINDEX = "Factnoindex";
	public static String PROP_AWAYNOYEAR = "Awaynoyear";
	public static String PROP_AENOINDEX = "Aenoindex";

	// constructors
	public BaseBsNogenerate() {
		initialize();
	}

	protected void initialize() {
	}

	// fields
	private java.lang.String id;
	private java.lang.String orgno;
	private java.lang.String orgnm;
	private java.lang.String area;
	private java.lang.String aenotext;
	private java.lang.String aenoyear;
	private java.lang.Long aenoindex;
	private java.lang.String evdcnotext;
	private java.lang.String evdcnoyear;
	private java.lang.Long evdcnoindex;
	private java.lang.String factnotext;
	private java.lang.String factnoyear;
	private java.lang.Long factnoindex;
	private java.lang.String comeinnotext;
	private java.lang.String comeinnoyear;
	private java.lang.Long comeinindex;
	private java.lang.String awaynotext;
	private java.lang.String awaynoyear;
	private java.lang.Long awaynoindex;
	private java.lang.String pbnshnotext;
	private java.lang.String pbnshnoyear;
	private java.lang.Long pbnshnoindex;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updateate;

	/**
	 * Return the value associated with the column: ID
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Set the value related to the column: ID
	 * 
	 * @param id
	 *            the ID value
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * Return the value associated with the column: ORGNO
	 */
	public java.lang.String getOrgno() {
		return orgno;
	}

	/**
	 * Set the value related to the column: ORGNO
	 * 
	 * @param orgno
	 *            the ORGNO value
	 */
	public void setOrgno(java.lang.String orgno) {
		this.orgno = orgno;
	}

	/**
	 * Return the value associated with the column: ORGNM
	 */
	public java.lang.String getOrgnm() {
		return orgnm;
	}

	/**
	 * Set the value related to the column: ORGNM
	 * 
	 * @param orgnm
	 *            the ORGNM value
	 */
	public void setOrgnm(java.lang.String orgnm) {
		this.orgnm = orgnm;
	}

	/**
	 * Return the value associated with the column: AREA
	 */
	public java.lang.String getArea() {
		return area;
	}

	/**
	 * Set the value related to the column: AREA
	 * 
	 * @param area
	 *            the AREA value
	 */
	public void setArea(java.lang.String area) {
		this.area = area;
	}

	/**
	 * Return the value associated with the column: AENOTEXT
	 */
	public java.lang.String getAenotext() {
		return aenotext;
	}

	/**
	 * Set the value related to the column: AENOTEXT
	 * 
	 * @param aenotext
	 *            the AENOTEXT value
	 */
	public void setAenotext(java.lang.String aenotext) {
		this.aenotext = aenotext;
	}

	/**
	 * Return the value associated with the column: AENOYEAR
	 */
	public java.lang.String getAenoyear() {
		return aenoyear;
	}

	/**
	 * Set the value related to the column: AENOYEAR
	 * 
	 * @param aenoyear
	 *            the AENOYEAR value
	 */
	public void setAenoyear(java.lang.String aenoyear) {
		this.aenoyear = aenoyear;
	}

	/**
	 * Return the value associated with the column: AENOINDEX
	 */
	public java.lang.Long getAenoindex() {
		return aenoindex;
	}

	/**
	 * Set the value related to the column: AENOINDEX
	 * 
	 * @param aenoindex
	 *            the AENOINDEX value
	 */
	public void setAenoindex(java.lang.Long aenoindex) {
		this.aenoindex = aenoindex;
	}

	/**
	 * Return the value associated with the column: EVDCNOTEXT
	 */
	public java.lang.String getEvdcnotext() {
		return evdcnotext;
	}

	/**
	 * Set the value related to the column: EVDCNOTEXT
	 * 
	 * @param evdcnotext
	 *            the EVDCNOTEXT value
	 */
	public void setEvdcnotext(java.lang.String evdcnotext) {
		this.evdcnotext = evdcnotext;
	}

	/**
	 * Return the value associated with the column: EVDCNOYEAR
	 */
	public java.lang.String getEvdcnoyear() {
		return evdcnoyear;
	}

	/**
	 * Set the value related to the column: EVDCNOYEAR
	 * 
	 * @param evdcnoyear
	 *            the EVDCNOYEAR value
	 */
	public void setEvdcnoyear(java.lang.String evdcnoyear) {
		this.evdcnoyear = evdcnoyear;
	}

	/**
	 * Return the value associated with the column: EVDCNOINDEX
	 */
	public java.lang.Long getEvdcnoindex() {
		return evdcnoindex;
	}

	/**
	 * Set the value related to the column: EVDCNOINDEX
	 * 
	 * @param evdcnoindex
	 *            the EVDCNOINDEX value
	 */
	public void setEvdcnoindex(java.lang.Long evdcnoindex) {
		this.evdcnoindex = evdcnoindex;
	}

	/**
	 * Return the value associated with the column: FACTNOTEXT
	 */
	public java.lang.String getFactnotext() {
		return factnotext;
	}

	/**
	 * Set the value related to the column: FACTNOTEXT
	 * 
	 * @param factnotext
	 *            the FACTNOTEXT value
	 */
	public void setFactnotext(java.lang.String factnotext) {
		this.factnotext = factnotext;
	}

	/**
	 * Return the value associated with the column: FACTNOYEAR
	 */
	public java.lang.String getFactnoyear() {
		return factnoyear;
	}

	/**
	 * Set the value related to the column: FACTNOYEAR
	 * 
	 * @param factnoyear
	 *            the FACTNOYEAR value
	 */
	public void setFactnoyear(java.lang.String factnoyear) {
		this.factnoyear = factnoyear;
	}

	/**
	 * Return the value associated with the column: FACTNOINDEX
	 */
	public java.lang.Long getFactnoindex() {
		return factnoindex;
	}

	/**
	 * Set the value related to the column: FACTNOINDEX
	 * 
	 * @param factnoindex
	 *            the FACTNOINDEX value
	 */
	public void setFactnoindex(java.lang.Long factnoindex) {
		this.factnoindex = factnoindex;
	}

	/**
	 * Return the value associated with the column: AWAYNOTEXT
	 */
	public java.lang.String getAwaynotext() {
		return awaynotext;
	}

	/**
	 * Set the value related to the column: AWAYNOTEXT
	 * 
	 * @param awaynotext
	 *            the AWAYNOTEXT value
	 */
	public void setAwaynotext(java.lang.String awaynotext) {
		this.awaynotext = awaynotext;
	}

	/**
	 * Return the value associated with the column: AWAYNOYEAR
	 */
	public java.lang.String getAwaynoyear() {
		return awaynoyear;
	}

	/**
	 * Set the value related to the column: AWAYNOYEAR
	 * 
	 * @param awaynoyear
	 *            the AWAYNOYEAR value
	 */
	public void setAwaynoyear(java.lang.String awaynoyear) {
		this.awaynoyear = awaynoyear;
	}

	/**
	 * Return the value associated with the column: AWAYNOINDEX
	 */
	public java.lang.Long getAwaynoindex() {
		return awaynoindex;
	}

	/**
	 * Set the value related to the column: AWAYNOINDEX
	 * 
	 * @param awaynoindex
	 *            the AWAYNOINDEX value
	 */
	public void setAwaynoindex(java.lang.Long awaynoindex) {
		this.awaynoindex = awaynoindex;
	}

	/**
	 * Return the value associated with the column: PBNSHNOTEXT
	 */
	public java.lang.String getPbnshnotext() {
		return pbnshnotext;
	}

	/**
	 * Set the value related to the column: PBNSHNOTEXT
	 * 
	 * @param pbnshnotext
	 *            the PBNSHNOTEXT value
	 */
	public void setPbnshnotext(java.lang.String pbnshnotext) {
		this.pbnshnotext = pbnshnotext;
	}

	/**
	 * Return the value associated with the column: PBNSHNOYEAR
	 */
	public java.lang.String getPbnshnoyear() {
		return pbnshnoyear;
	}

	/**
	 * Set the value related to the column: PBNSHNOYEAR
	 * 
	 * @param pbnshnoyear
	 *            the PBNSHNOYEAR value
	 */
	public void setPbnshnoyear(java.lang.String pbnshnoyear) {
		this.pbnshnoyear = pbnshnoyear;
	}

	/**
	 * Return the value associated with the column: PBNSHNOINDEX
	 */
	public java.lang.Long getPbnshnoindex() {
		return pbnshnoindex;
	}

	/**
	 * Set the value related to the column: PBNSHNOINDEX
	 * 
	 * @param pbnshnoindex
	 *            the PBNSHNOINDEX value
	 */
	public void setPbnshnoindex(java.lang.Long pbnshnoindex) {
		this.pbnshnoindex = pbnshnoindex;
	}

	/**
	 * Return the value associated with the column: STAT
	 */
	public java.lang.String getStat() {
		return stat;
	}

	/**
	 * Set the value related to the column: STAT
	 * 
	 * @param stat
	 *            the STAT value
	 */
	public void setStat(java.lang.String stat) {
		this.stat = stat;
	}

	/**
	 * Return the value associated with the column: FLAG
	 */
	public java.lang.String getFlag() {
		return flag;
	}

	/**
	 * Set the value related to the column: FLAG
	 * 
	 * @param flag
	 *            the FLAG value
	 */
	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	/**
	 * Return the value associated with the column: CREATEDATE
	 */
	public java.util.Date getCreatedate() {
		return createdate;
	}

	/**
	 * Set the value related to the column: CREATEDATE
	 * 
	 * @param createdate
	 *            the CREATEDATE value
	 */
	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * Return the value associated with the column: UPDATEATE
	 */
	public java.util.Date getUpdateate() {
		return updateate;
	}

	/**
	 * Set the value related to the column: UPDATEATE
	 * 
	 * @param updateate
	 *            the UPDATEATE value
	 */
	public void setUpdateate(java.util.Date updateate) {
		this.updateate = updateate;
	}

	public String toString() {
		return super.toString();
	}

	public java.lang.String getComeinnotext() {
		return comeinnotext;
	}

	public void setComeinnotext(java.lang.String comeinnotext) {
		this.comeinnotext = comeinnotext;
	}

	public java.lang.String getComeinnoyear() {
		return comeinnoyear;
	}

	public void setComeinnoyear(java.lang.String comeinnoyear) {
		this.comeinnoyear = comeinnoyear;
	}

	public java.lang.Long getComeinindex() {
		return comeinindex;
	}

	public void setComeinindex(java.lang.Long comeinindex) {
		this.comeinindex = comeinindex;
	}

}