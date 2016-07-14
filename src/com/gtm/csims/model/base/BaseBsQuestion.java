package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_QUESTION table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_QUESTION"
 */

public abstract class BaseBsQuestion  implements Serializable {

	public static String REF = "BsQuestion";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_ANSWERE = "Answere";
	public static String PROP_ANSWERG = "Answerg";
	public static String PROP_QQTITLE = "Qqtitle";
	public static String PROP_ANSWERA = "Answera";
	public static String PROP_STAT = "Stat";
	public static String PROP_ANSWERD = "Answerd";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_QQUESTIONAIREID = "Qquestionaireid";
	public static String PROP_ANSWERC = "Answerc";
	public static String PROP_FLAG = "Flag";
	public static String PROP_QQINDEX = "Qqindex";
	public static String PROP_ANSWERF = "Answerf";
	public static String PROP_ANSWERB = "Answerb";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsQuestion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsQuestion (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String answera;
	private java.lang.String answerb;
	private java.lang.String answerc;
	private java.lang.String answerd;
	private java.lang.String answere;
	private java.lang.String answerf;
	private java.lang.String answerg;
	private java.util.Date createdate;
	private java.lang.String flag;
	private java.lang.Short qqindex;
	private java.lang.String qqtitle;
	private java.lang.String qquestionaireid;
	private java.lang.String stat;
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
	 * Return the value associated with the column: ANSWERA
	 */
	public java.lang.String getAnswera () {
		return answera;
	}

	/**
	 * Set the value related to the column: ANSWERA
	 * @param answera the ANSWERA value
	 */
	public void setAnswera (java.lang.String answera) {
		this.answera = answera;
	}



	/**
	 * Return the value associated with the column: ANSWERB
	 */
	public java.lang.String getAnswerb () {
		return answerb;
	}

	/**
	 * Set the value related to the column: ANSWERB
	 * @param answerb the ANSWERB value
	 */
	public void setAnswerb (java.lang.String answerb) {
		this.answerb = answerb;
	}



	/**
	 * Return the value associated with the column: ANSWERC
	 */
	public java.lang.String getAnswerc () {
		return answerc;
	}

	/**
	 * Set the value related to the column: ANSWERC
	 * @param answerc the ANSWERC value
	 */
	public void setAnswerc (java.lang.String answerc) {
		this.answerc = answerc;
	}



	/**
	 * Return the value associated with the column: ANSWERD
	 */
	public java.lang.String getAnswerd () {
		return answerd;
	}

	/**
	 * Set the value related to the column: ANSWERD
	 * @param answerd the ANSWERD value
	 */
	public void setAnswerd (java.lang.String answerd) {
		this.answerd = answerd;
	}



	/**
	 * Return the value associated with the column: ANSWERE
	 */
	public java.lang.String getAnswere () {
		return answere;
	}

	/**
	 * Set the value related to the column: ANSWERE
	 * @param answere the ANSWERE value
	 */
	public void setAnswere (java.lang.String answere) {
		this.answere = answere;
	}



	/**
	 * Return the value associated with the column: ANSWERF
	 */
	public java.lang.String getAnswerf () {
		return answerf;
	}

	/**
	 * Set the value related to the column: ANSWERF
	 * @param answerf the ANSWERF value
	 */
	public void setAnswerf (java.lang.String answerf) {
		this.answerf = answerf;
	}



	/**
	 * Return the value associated with the column: ANSWERG
	 */
	public java.lang.String getAnswerg () {
		return answerg;
	}

	/**
	 * Set the value related to the column: ANSWERG
	 * @param answerg the ANSWERG value
	 */
	public void setAnswerg (java.lang.String answerg) {
		this.answerg = answerg;
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
	 * Return the value associated with the column: QQINDEX
	 */
	public java.lang.Short getQqindex () {
		return qqindex;
	}

	/**
	 * Set the value related to the column: QQINDEX
	 * @param qqindex the QQINDEX value
	 */
	public void setQqindex (java.lang.Short qqindex) {
		this.qqindex = qqindex;
	}



	/**
	 * Return the value associated with the column: QQTITLE
	 */
	public java.lang.String getQqtitle () {
		return qqtitle;
	}

	/**
	 * Set the value related to the column: QQTITLE
	 * @param qqtitle the QQTITLE value
	 */
	public void setQqtitle (java.lang.String qqtitle) {
		this.qqtitle = qqtitle;
	}



	/**
	 * Return the value associated with the column: QQUESTIONAIREID
	 */
	public java.lang.String getQquestionaireid () {
		return qquestionaireid;
	}

	/**
	 * Set the value related to the column: QQUESTIONAIREID
	 * @param qquestionaireid the QQUESTIONAIREID value
	 */
	public void setQquestionaireid (java.lang.String qquestionaireid) {
		this.qquestionaireid = qquestionaireid;
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
		if (!(obj instanceof com.gtm.csims.model.BsQuestion)) return false;
		else {
			com.gtm.csims.model.BsQuestion bsQuestion = (com.gtm.csims.model.BsQuestion) obj;
			if (null == this.getId() || null == bsQuestion.getId()) return false;
			else return (this.getId().equals(bsQuestion.getId()));
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