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

public abstract class BaseBsBusievalInfo  implements Serializable {

	public static String REF = "BsBusievalInfo";
	public static String PROP_ID = "Id";
	public static String PROP_EVALCONDITION = "EvalCondition";
	public static String PROP_STAT = "Stat";
	public static String PROP_EVALINFO = "EvalInfo";
	public static String PROP_FLAG = "Flag";
	public static String PROP_EVALCONTENT = "EvalContent";
	public static String PROP_DESCORE = "DeScore";
	public static String PROP_BASESCORE = "BaseScore";
	public static String PROP_EVALDT = "Evaldt";
	public static String PROP_HISTREC = "Histrec";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_UPDATEDATE = "Updatedate";
	public static String PROP_BSBUSIEVAL = "BsBusieval";
	public static String PROP_EVALRULE = "EvalRule";


	// constructors
	public BaseBsBusievalInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsBusievalInfo (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String evalCondition;
	private java.lang.String evalInfo;
	private java.lang.String evalContent;
	private java.lang.String evalRule;
	private java.lang.String deScore;
	private java.util.Date evaldt;
	private java.lang.String histrec;
	private java.lang.String creator;
	private java.util.Date crtdate;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.lang.String baseScore;
	private java.util.Date createdate;
	private java.util.Date updatedate;

	// many to one
	private com.gtm.csims.model.BsBusieval bsBusieval;

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

	public int getHashCode() {
		return hashCode;
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

	public java.lang.String getEvalRule() {
		return evalRule;
	}

	public void setEvalRule(java.lang.String evalRule) {
		this.evalRule = evalRule;
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

	public com.gtm.csims.model.BsBusieval getBsBusieval() {
		return bsBusieval;
	}

	public void setBsBusieval(com.gtm.csims.model.BsBusieval bsBusieval) {
		this.bsBusieval = bsBusieval;
	}

	public java.lang.String getHistrec() {
		return histrec;
	}

	public void setHistrec(java.lang.String histrec) {
		this.histrec = histrec;
	}

	public java.lang.String getCreator() {
		return creator;
	}

	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}

	public java.util.Date getCrtdate() {
		return crtdate;
	}

	public void setCrtdate(java.util.Date crtdate) {
		this.crtdate = crtdate;
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

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsBusievalInfo)) return false;
		else {
			com.gtm.csims.model.BsBusievalInfo bs = (com.gtm.csims.model.BsBusievalInfo) obj;
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