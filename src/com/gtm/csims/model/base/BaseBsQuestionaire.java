package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_QUESTIONAIRE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_QUESTIONAIRE"
 */

public abstract class BaseBsQuestionaire  implements Serializable {

	public static String REF = "BsQuestionaire";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_STATUS = "Status";
	public static String PROP_QCREATORORGNAME = "Qcreatororgname";
	public static String PROP_QTITLE = "Qtitle";
	public static String PROP_QSUMRY = "Qsumry";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_QENDDATETIME = "Qenddatetime";
	public static String PROP_FLAG = "Flag";
	public static String PROP_QCREATORORGNO = "Qcreatororgno";
	public static String PROP_QCREATOR = "Qcreator";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsQuestionaire () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsQuestionaire (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String qtitle;
	private java.lang.String qsumry;
	private java.lang.String qcreator;
	private java.lang.String qcreatororgname;
	private java.lang.String qcreatororgno;
	private java.util.Date qcrtdate;
	private java.lang.String qenddatetime;
	private java.lang.String status;
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
	 * Return the value associated with the column: QSUMRY
	 */
	public java.lang.String getQsumry () {
		return qsumry;
	}

	/**
	 * Set the value related to the column: QSUMRY
	 * @param qsumry the QSUMRY value
	 */
	public void setQsumry (java.lang.String qsumry) {
		this.qsumry = qsumry;
	}



	/**
	 * Return the value associated with the column: QCREATOR
	 */
	public java.lang.String getQcreator () {
		return qcreator;
	}

	/**
	 * Set the value related to the column: QCREATOR
	 * @param qcreator the QCREATOR value
	 */
	public void setQcreator (java.lang.String qcreator) {
		this.qcreator = qcreator;
	}

	public java.lang.String getQcreatororgname() {
		return qcreatororgname;
	}

	public void setQcreatororgname(java.lang.String qcreatororgname) {
		this.qcreatororgname = qcreatororgname;
	}

	/**
	 * Return the value associated with the column: QCREATORORGNO
	 */
	public java.lang.String getQcreatororgno () {
		return qcreatororgno;
	}

	/**
	 * Set the value related to the column: QCREATORORGNO
	 * @param qcreatororgno the QCREATORORGNO value
	 */
	public void setQcreatororgno (java.lang.String qcreatororgno) {
		this.qcreatororgno = qcreatororgno;
	}



	/**
	 * Return the value associated with the column: QCRTDATE
	 */
	public java.util.Date getQcrtdate () {
		return qcrtdate;
	}

	/**
	 * Set the value related to the column: QCRTDATE
	 * @param qcrtdate the QCRTDATE value
	 */
	public void setQcrtdate (java.util.Date qcrtdate) {
		this.qcrtdate = qcrtdate;
	}


	public java.lang.String getQenddatetime() {
		return qenddatetime;
	}

	public void setQenddatetime(java.lang.String qenddatetime) {
		this.qenddatetime = qenddatetime;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
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
		if (!(obj instanceof com.gtm.csims.model.BsQuestionaire)) return false;
		else {
			com.gtm.csims.model.BsQuestionaire bsQuestionaire = (com.gtm.csims.model.BsQuestionaire) obj;
			if (null == this.getId() || null == bsQuestionaire.getId()) return false;
			else return (this.getId().equals(bsQuestionaire.getId()));
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