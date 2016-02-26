package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_WORKCHECK table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_WORKCHECK"
 */

public abstract class BaseBsOffsitecheck  implements Serializable {

	public static String REF = "BsOffsitecheck";
	public static String PROP_PEPS = "Peps";
	public static String PROP_ORGS = "Orgs";
	public static String PROP_UPDATEDATE = "Updatedate";
	public static String PROP_CREATORORG = "Creatororg";
	public static String PROP_STAT = "Stat";
	public static String PROP_CHKEDORGNM = "Chkedorgnm";
	public static String PROP_CHKORGNO = "Chkorgno";
	public static String PROP_CHKORGTELE = "Chkorgtele";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_CREATOR = "Creator";
	public static String PROP_WORKCHKNO = "Workchkno";
	public static String PROP_CHKCONT = "Chkcont";
	public static String PROP_FLAG = "Flag";
	public static String PROP_CHKSTARTDT = "Chkstartdt";
	public static String PROP_CHKENDDT = "Chkenddt";
	public static String PROP_CHKORGNM = "Chkorgnm";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_CHKEDORGNO = "Chkedorgno";
	public static String PROP_CREATORORGNO = "Creatororgno";
	public static String PROP_ID = "Id";
	public static String PROP_CHKEDUSER = "Chkeduser";


	// constructors
	public BaseBsOffsitecheck () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsOffsitecheck (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String workchkno;
	private java.lang.String peps;
	private java.lang.String orgs;
	private java.lang.String chkorgtele;
	private java.lang.String chkedorgnm;
	private java.lang.String chkedorgno;
	private java.lang.String chkcont;
	private java.lang.String chkorgnm;
	private java.lang.String chkorgno;
	private java.util.Date chkstartdt;
	private java.util.Date chkenddt;
	private java.lang.String creator;
	private java.lang.String creatororg;
	private java.lang.String creatororgno;
	private java.util.Date crtdate;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updatedate;
	private java.lang.String chkeduser;



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

	/**
	 * Return the value associated with the column: WORKCHKNO
	 */
	public java.lang.String getWorkchkno () {
		return workchkno;
	}

	/**
	 * Set the value related to the column: WORKCHKNO
	 * @param workchkno the WORKCHKNO value
	 */
	public void setWorkchkno (java.lang.String workchkno) {
		this.workchkno = workchkno;
	}

	/**
	 * Return the value associated with the column: PEPS
	 */
	public java.lang.String getPeps () {
		return peps;
	}

	/**
	 * Set the value related to the column: PEPS
	 * @param peps the PEPS value
	 */
	public void setPeps (java.lang.String peps) {
		this.peps = peps;
	}

	public java.lang.String getChkeduser() {
		return chkeduser;
	}

	public void setChkeduser(java.lang.String chkeduser) {
		this.chkeduser = chkeduser;
	}

	/**
	 * Return the value associated with the column: ORGS
	 */
	public java.lang.String getOrgs () {
		return orgs;
	}

	/**
	 * Set the value related to the column: ORGS
	 * @param orgs the ORGS value
	 */
	public void setOrgs (java.lang.String orgs) {
		this.orgs = orgs;
	}



	/**
	 * Return the value associated with the column: CHKORGTELE
	 */
	public java.lang.String getChkorgtele () {
		return chkorgtele;
	}

	/**
	 * Set the value related to the column: CHKORGTELE
	 * @param chkorgtele the CHKORGTELE value
	 */
	public void setChkorgtele (java.lang.String chkorgtele) {
		this.chkorgtele = chkorgtele;
	}

	/**
	 * Return the value associated with the column: CHKEDORGNM
	 */
	public java.lang.String getChkedorgnm () {
		return chkedorgnm;
	}

	/**
	 * Set the value related to the column: CHKEDORGNM
	 * @param chkedorgnm the CHKEDORGNM value
	 */
	public void setChkedorgnm (java.lang.String chkedorgnm) {
		this.chkedorgnm = chkedorgnm;
	}

	public java.util.Date getChkstartdt() {
		return chkstartdt;
	}

	public void setChkstartdt(java.util.Date chkstartdt) {
		this.chkstartdt = chkstartdt;
	}

	public java.util.Date getChkenddt() {
		return chkenddt;
	}

	public void setChkenddt(java.util.Date chkenddt) {
		this.chkenddt = chkenddt;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate = updatedate;
	}

	/**
	 * Return the value associated with the column: CHKEDORGNO
	 */
	public java.lang.String getChkedorgno () {
		return chkedorgno;
	}

	/**
	 * Set the value related to the column: CHKEDORGNO
	 * @param chkedorgno the CHKEDORGNO value
	 */
	public void setChkedorgno (java.lang.String chkedorgno) {
		this.chkedorgno = chkedorgno;
	}


	/**
	 * Return the value associated with the column: CHKCONT
	 */
	public java.lang.String getChkcont () {
		return chkcont;
	}

	/**
	 * Set the value related to the column: CHKCONT
	 * @param chkcont the CHKCONT value
	 */
	public void setChkcont (java.lang.String chkcont) {
		this.chkcont = chkcont;
	}



	/**
	 * Return the value associated with the column: CHKORGNM
	 */
	public java.lang.String getChkorgnm () {
		return chkorgnm;
	}

	/**
	 * Set the value related to the column: CHKORGNM
	 * @param chkorgnm the CHKORGNM value
	 */
	public void setChkorgnm (java.lang.String chkorgnm) {
		this.chkorgnm = chkorgnm;
	}



	/**
	 * Return the value associated with the column: CHKORGNO
	 */
	public java.lang.String getChkorgno () {
		return chkorgno;
	}

	/**
	 * Set the value related to the column: CHKORGNO
	 * @param chkorgno the CHKORGNO value
	 */
	public void setChkorgno (java.lang.String chkorgno) {
		this.chkorgno = chkorgno;
	}

	/**
	 * Return the value associated with the column: CREATOR
	 */
	public java.lang.String getCreator () {
		return creator;
	}

	/**
	 * Set the value related to the column: CREATOR
	 * @param creator the CREATOR value
	 */
	public void setCreator (java.lang.String creator) {
		this.creator = creator;
	}



	/**
	 * Return the value associated with the column: CREATORORG
	 */
	public java.lang.String getCreatororg () {
		return creatororg;
	}

	/**
	 * Set the value related to the column: CREATORORG
	 * @param creatororg the CREATORORG value
	 */
	public void setCreatororg (java.lang.String creatororg) {
		this.creatororg = creatororg;
	}



	/**
	 * Return the value associated with the column: CREATORORGNO
	 */
	public java.lang.String getCreatororgno () {
		return creatororgno;
	}

	/**
	 * Set the value related to the column: CREATORORGNO
	 * @param creatororgno the CREATORORGNO value
	 */
	public void setCreatororgno (java.lang.String creatororgno) {
		this.creatororgno = creatororgno;
	}



	/**
	 * Return the value associated with the column: CRTDATE
	 */
	public java.util.Date getCrtdate () {
		return crtdate;
	}

	/**
	 * Set the value related to the column: CRTDATE
	 * @param crtdate the CRTDATE value
	 */
	public void setCrtdate (java.util.Date crtdate) {
		this.crtdate = crtdate;
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



	/**
	 * Return the value associated with the column: CREATEDATE
	 */
	public java.util.Date getCreatedate () {
		return createdate;
	}

	/**
	 * Set the value related to the column: CREATEDATE
	 * @param createdate the CREATEDATE value
	 */
	public void setCreatedate (java.util.Date createdate) {
		this.createdate = createdate;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsOffsitecheck)) return false;
		else {
			com.gtm.csims.model.BsOffsitecheck bs = (com.gtm.csims.model.BsOffsitecheck) obj;
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