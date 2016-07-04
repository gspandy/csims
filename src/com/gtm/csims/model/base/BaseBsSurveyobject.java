package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_SURVEYOBJECT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_SURVEYOBJECT"
 */

public abstract class BaseBsSurveyobject  implements Serializable {

	public static String REF = "BsSurveyobject";
	public static String PROP_QORG = "Qorg";
	public static String PROP_QORGNO = "Qorgno";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_ISFINISHED = "Isfinished";
	public static String PROP_QUESTIONAIREID = "Questionaireid";
	public static String PROP_FLAG = "Flag";
	public static String PROP_STAT = "Stat";
	public static String PROP_QORGTYPENO = "Qorgtypeno";
	public static String PROP_QORGTYPE = "Qorgtype";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsSurveyobject () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsSurveyobject (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String questionaireid;
	private java.lang.String qorgno;
	private java.lang.String qorg;
	private java.lang.String qorgtypeno;
	private java.lang.String qorgtype;
	private java.lang.String isfinished;
	private java.lang.String stat;
	private java.lang.String flag;
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
	 * Return the value associated with the column: QUESTIONAIREID
	 */
	public java.lang.String getQuestionaireid () {
		return questionaireid;
	}

	/**
	 * Set the value related to the column: QUESTIONAIREID
	 * @param questionaireid the QUESTIONAIREID value
	 */
	public void setQuestionaireid (java.lang.String questionaireid) {
		this.questionaireid = questionaireid;
	}



	/**
	 * Return the value associated with the column: QORGNO
	 */
	public java.lang.String getQorgno () {
		return qorgno;
	}

	/**
	 * Set the value related to the column: QORGNO
	 * @param qorgno the QORGNO value
	 */
	public void setQorgno (java.lang.String qorgno) {
		this.qorgno = qorgno;
	}



	/**
	 * Return the value associated with the column: QORG
	 */
	public java.lang.String getQorg () {
		return qorg;
	}

	/**
	 * Set the value related to the column: QORG
	 * @param qorg the QORG value
	 */
	public void setQorg (java.lang.String qorg) {
		this.qorg = qorg;
	}



	/**
	 * Return the value associated with the column: QORGTYPENO
	 */
	public java.lang.String getQorgtypeno () {
		return qorgtypeno;
	}

	/**
	 * Set the value related to the column: QORGTYPENO
	 * @param qorgtypeno the QORGTYPENO value
	 */
	public void setQorgtypeno (java.lang.String qorgtypeno) {
		this.qorgtypeno = qorgtypeno;
	}



	/**
	 * Return the value associated with the column: QORGTYPE
	 */
	public java.lang.String getQorgtype () {
		return qorgtype;
	}

	/**
	 * Set the value related to the column: QORGTYPE
	 * @param qorgtype the QORGTYPE value
	 */
	public void setQorgtype (java.lang.String qorgtype) {
		this.qorgtype = qorgtype;
	}



	/**
	 * Return the value associated with the column: ISFINISHED
	 */
	public java.lang.String getIsfinished () {
		return isfinished;
	}

	/**
	 * Set the value related to the column: ISFINISHED
	 * @param isfinished the ISFINISHED value
	 */
	public void setIsfinished (java.lang.String isfinished) {
		this.isfinished = isfinished;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsSurveyobject)) return false;
		else {
			com.gtm.csims.model.BsSurveyobject bsSurveyobject = (com.gtm.csims.model.BsSurveyobject) obj;
			if (null == this.getId() || null == bsSurveyobject.getId()) return false;
			else return (this.getId().equals(bsSurveyobject.getId()));
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