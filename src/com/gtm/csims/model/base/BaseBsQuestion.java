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
	public static String PROP_QUESTIONAIREID = "Questionaireid";
	public static String PROP_FLAG = "Flag";
	public static String PROP_STAT = "Stat";
	public static String PROP_QINDEX = "Qindex";
	public static String PROP_QTITLE = "Qtitle";
	public static String PROP_CREATEDATE = "Createdate";
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
	private java.lang.String questionaireid;
	private java.lang.String qtitle;
	private java.lang.Short qindex;
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
	 * Return the value associated with the column: QTITLE
	 */
	public java.lang.String getQtitle () {
		return qtitle;
	}

	/**
	 * Set the value related to the column: QTITLE
	 * @param qtitle the QTITLE value
	 */
	public void setQtitle (java.lang.String qtitle) {
		this.qtitle = qtitle;
	}



	/**
	 * Return the value associated with the column: QINDEX
	 */
	public java.lang.Short getQindex () {
		return qindex;
	}

	/**
	 * Set the value related to the column: QINDEX
	 * @param qindex the QINDEX value
	 */
	public void setQindex (java.lang.Short qindex) {
		this.qindex = qindex;
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