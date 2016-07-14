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
	public static String PROP_STAT = "Stat";
	public static String PROP_QCREATORORG = "Qcreatororg";
	public static String PROP_QTITLE = "Qtitle";
	public static String PROP_QSUMRY = "Qsumry";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_QCRTDATE = "Qcrtdate";
	public static String PROP_QENDDATETIME = "Qenddatetime";
	public static String PROP_FLAG = "Flag";
	public static String PROP_QISFINISHED = "Qisfinished";
	public static String PROP_QSTAT = "Qstat";
	public static String PROP_QCREATORORGNO = "Qcreatororgno";
	public static String PROP_QCREATOR = "Qcreator";
	public static String PROP_ID = "Id";
	public static String PROP_QISENABLE = "Qisenable";


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
	private java.lang.String qcreatororg;
	private java.lang.String qcreatororgno;
	private java.util.Date qcrtdate;
	private java.lang.String qisenable;
	private java.lang.String qisfinished;
	private java.lang.Short qstat;
	private java.util.Date qenddatetime;
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



	/**
	 * Return the value associated with the column: QCREATORORG
	 */
	public java.lang.String getQcreatororg () {
		return qcreatororg;
	}

	/**
	 * Set the value related to the column: QCREATORORG
	 * @param qcreatororg the QCREATORORG value
	 */
	public void setQcreatororg (java.lang.String qcreatororg) {
		this.qcreatororg = qcreatororg;
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



	/**
	 * Return the value associated with the column: QISENABLE
	 */
	public java.lang.String getQisenable () {
		return qisenable;
	}

	/**
	 * Set the value related to the column: QISENABLE
	 * @param qisenable the QISENABLE value
	 */
	public void setQisenable (java.lang.String qisenable) {
		this.qisenable = qisenable;
	}



	/**
	 * Return the value associated with the column: QISFINISHED
	 */
	public java.lang.String getQisfinished () {
		return qisfinished;
	}

	/**
	 * Set the value related to the column: QISFINISHED
	 * @param qisfinished the QISFINISHED value
	 */
	public void setQisfinished (java.lang.String qisfinished) {
		this.qisfinished = qisfinished;
	}



	/**
	 * Return the value associated with the column: QSTAT
	 */
	public java.lang.Short getQstat () {
		return qstat;
	}

	/**
	 * Set the value related to the column: QSTAT
	 * @param qstat the QSTAT value
	 */
	public void setQstat (java.lang.Short qstat) {
		this.qstat = qstat;
	}



	/**
	 * Return the value associated with the column: QENDDATETIME
	 */
	public java.util.Date getQenddatetime () {
		return qenddatetime;
	}

	/**
	 * Set the value related to the column: QENDDATETIME
	 * @param qenddatetime the QENDDATETIME value
	 */
	public void setQenddatetime (java.util.Date qenddatetime) {
		this.qenddatetime = qenddatetime;
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