package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_BUSIEVAL table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_BUSIEVAL"
 */

public abstract class BaseBsBusieval  implements Serializable {

	public static String REF = "BsBusieval";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_BUSIEVALNO = "Busievalno";
	public static String PROP_STAT = "Stat";
	public static String PROP_EVALORGNO = "Evalorgno";
	public static String PROP_EVALEDORGNO = "Evaledorgno";
	public static String PROP_CREATOR = "Creator";
	public static String PROP_FLAG = "Flag";
	public static String PROP_CREATORORGNO = "Creatororgno";
	public static String PROP_CREATORORG = "Creatororg";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_EVALORGNM = "Evalorgnm";
	public static String PROP_EVALEDORGNM = "Evaledorgnm";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_ID = "Id";
	public static String PROP_EVALDURING = "EvalDuring";
	public static String PROP_EVALYEAR = "EvalYear";


	// constructors
	public BaseBsBusieval () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsBusieval (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String busievalno;
	private java.lang.String evalorgnm;
	private java.lang.String evalorgno;
	private java.lang.String evaledorgnm;
	private java.lang.String evaledorgno;
	private java.lang.String evalCondition;
	private java.lang.String evalInfo;
	private java.lang.String evalContent;
	private java.lang.String deScore;
	private java.util.Date evaldt;
	private java.lang.String histrec;
	private java.lang.String creator;
	private java.lang.String creatororg;
	private java.lang.String creatororgno;
	private java.util.Date crtdate;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.lang.String baseScore;
	private java.lang.String evalDuring;
	private java.lang.String evalYear;
	private java.util.Date createdate;
	private java.util.Date updateate;



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
	 * Return the value associated with the column: BUSIEVALNO
	 */
	public java.lang.String getBusievalno () {
		return busievalno;
	}

	/**
	 * Set the value related to the column: BUSIEVALNO
	 * @param busievalno the BUSIEVALNO value
	 */
	public void setBusievalno (java.lang.String busievalno) {
		this.busievalno = busievalno;
	}

	public java.lang.String getEvaledorgnm() {
		return evaledorgnm;
	}

	public void setEvaledorgnm(java.lang.String evaledorgnm) {
		this.evaledorgnm = evaledorgnm;
	}

	public java.lang.String getEvaledorgno() {
		return evaledorgno;
	}

	public void setEvaledorgno(java.lang.String evaledorgno) {
		this.evaledorgno = evaledorgno;
	}

	/**
	 * Return the value associated with the column: EVALORGNM
	 */
	public java.lang.String getEvalorgnm () {
		return evalorgnm;
	}

	/**
	 * Set the value related to the column: EVALORGNM
	 * @param evalorgnm the EVALORGNM value
	 */
	public void setEvalorgnm (java.lang.String evalorgnm) {
		this.evalorgnm = evalorgnm;
	}

	/**
	 * Return the value associated with the column: EVALORGNO
	 */
	public java.lang.String getEvalorgno () {
		return evalorgno;
	}

	/**
	 * Set the value related to the column: EVALORGNO
	 * @param evalorgno the EVALORGNO value
	 */
	public void setEvalorgno (java.lang.String evalorgno) {
		this.evalorgno = evalorgno;
	}

	/**
	 * Return the value associated with the column: HISTREC
	 */
	public java.lang.String getHistrec () {
		return histrec;
	}

	/**
	 * Set the value related to the column: HISTREC
	 * @param histrec the HISTREC value
	 */
	public void setHistrec (java.lang.String histrec) {
		this.histrec = histrec;
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

	/**
	 * Return the value associated with the column: UPDATEATE
	 */
	public java.util.Date getUpdateate () {
		return updateate;
	}

	/**
	 * Set the value related to the column: UPDATEATE
	 * @param updateate the UPDATEATE value
	 */
	public void setUpdateate (java.util.Date updateate) {
		this.updateate = updateate;
	}

	public java.lang.String getEvalDuring() {
		return evalDuring;
	}

	public void setEvalDuring(java.lang.String evalDuring) {
		this.evalDuring = evalDuring;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public java.lang.String getEvalCondition() {
		return evalCondition;
	}

	public void setEvalCondition(java.lang.String evalCondition) {
		this.evalCondition = evalCondition;
	}

	public java.lang.String getEvalInfo() {
		return evalInfo;
	}

	public void setEvalInfo(java.lang.String evalInfo) {
		this.evalInfo = evalInfo;
	}

	public java.lang.String getEvalContent() {
		return evalContent;
	}

	public void setEvalContent(java.lang.String evalContent) {
		this.evalContent = evalContent;
	}

	public java.lang.String getDeScore() {
		return deScore;
	}

	public void setDeScore(java.lang.String deScore) {
		this.deScore = deScore;
	}

	public java.lang.String getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(java.lang.String baseScore) {
		this.baseScore = baseScore;
	}

	public java.util.Date getEvaldt() {
		return evaldt;
	}

	public void setEvaldt(java.util.Date evaldt) {
		this.evaldt = evaldt;
	}

	public java.lang.String getEvalYear() {
		return evalYear;
	}

	public void setEvalYear(java.lang.String evalYear) {
		this.evalYear = evalYear;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsBusieval)) return false;
		else {
			com.gtm.csims.model.BsBusieval bsBusieval = (com.gtm.csims.model.BsBusieval) obj;
			if (null == this.getId() || null == bsBusieval.getId()) return false;
			else return (this.getId().equals(bsBusieval.getId()));
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