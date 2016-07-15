package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_ANSWERRESULT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_ANSWERRESULT"
 */

public abstract class BaseBsAnswerresult  implements Serializable {

	public static String REF = "BsAnswerresult";
	public static String PROP_ID = "Id";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_ANSWERRESULT = "Answerresult";
	public static String PROP_STATUS = "Status";
	public static String PROP_ARORGNAME = "Arorgname";
	public static String PROP_ARORGNO = "Arorgno";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ARAREANO = "Arareano";
	public static String PROP_ARORGTYPENO = "Arorgtypeno";
	public static String PROP_ARORGTYPENAME = "Arorgtypename";
	public static String PROP_FLAG = "Flag";
	public static String PROP_ARAREA = "Ararea";
	public static String PROP_BSQUESTIONAIRE = "BsQuestionaire";
	public static String PROP_BSQUESTION = "BsQuestion";



	// constructors
	public BaseBsAnswerresult () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAnswerresult (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String ararea;
	private java.lang.String arareano;
	private java.lang.String arorgname;
	private java.lang.String arorgno;
	private java.lang.String arorgtypename;
	private java.lang.String arorgtypeno;
	private java.util.Date createdate;
	private java.lang.String flag;
	private java.lang.String status;
	private java.util.Date updateate;
	private java.lang.String answerresult;
	
	private com.gtm.csims.model.BsQuestionaire bsQuestionaire;
	private com.gtm.csims.model.BsQuestion bsQuestion;



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

	public java.lang.String getArarea() {
		return ararea;
	}

	public void setArarea(java.lang.String ararea) {
		this.ararea = ararea;
	}

	public java.lang.String getArareano() {
		return arareano;
	}

	public void setArareano(java.lang.String arareano) {
		this.arareano = arareano;
	}

	public java.lang.String getArorgname() {
		return arorgname;
	}

	public void setArorgname(java.lang.String arorgname) {
		this.arorgname = arorgname;
	}

	public java.lang.String getArorgno() {
		return arorgno;
	}

	public void setArorgno(java.lang.String arorgno) {
		this.arorgno = arorgno;
	}

	public java.lang.String getArorgtypename() {
		return arorgtypename;
	}

	public void setArorgtypename(java.lang.String arorgtypename) {
		this.arorgtypename = arorgtypename;
	}

	public java.lang.String getArorgtypeno() {
		return arorgtypeno;
	}

	public void setArorgtypeno(java.lang.String arorgtypeno) {
		this.arorgtypeno = arorgtypeno;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public java.lang.String getFlag() {
		return flag;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.util.Date getUpdateate() {
		return updateate;
	}

	public void setUpdateate(java.util.Date updateate) {
		this.updateate = updateate;
	}

	public java.lang.String getAnswerresult() {
		return answerresult;
	}

	public void setAnswerresult(java.lang.String answerresult) {
		this.answerresult = answerresult;
	}

	public com.gtm.csims.model.BsQuestionaire getBsQuestionaire() {
		return bsQuestionaire;
	}

	public void setBsQuestionaire(com.gtm.csims.model.BsQuestionaire bsQuestionaire) {
		this.bsQuestionaire = bsQuestionaire;
	}

	public com.gtm.csims.model.BsQuestion getBsQuestion() {
		return bsQuestion;
	}

	public void setBsQuestion(com.gtm.csims.model.BsQuestion bsQuestion) {
		this.bsQuestion = bsQuestion;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsAnswerresult)) return false;
		else {
			com.gtm.csims.model.BsAnswerresult bsAnswerresult = (com.gtm.csims.model.BsAnswerresult) obj;
			if (null == this.getId() || null == bsAnswerresult.getId()) return false;
			else return (this.getId().equals(bsAnswerresult.getId()));
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