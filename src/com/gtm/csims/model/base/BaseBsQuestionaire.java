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
	public static String PROP_DSUMRY = "Dsumry";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_CREATORORG = "Creatororg";
	public static String PROP_STAT = "Stat";
	public static String PROP_QTITLE = "Qtitle";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_CREATOR = "Creator";
	public static String PROP_ISENABLE = "Isenable";
	public static String PROP_ISFINISHED = "Isfinished";
	public static String PROP_FLAG = "Flag";
	public static String PROP_ENDDATETIME = "Enddatetime";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_CREATORORGNO = "Creatororgno";
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
	private java.lang.String dsumry;
	private java.lang.String creator;
	private java.lang.String creatororg;
	private java.lang.String creatororgno;
	private java.util.Date crtdate;
	private java.lang.String isenable;
	private java.lang.String isfinished;
	private java.util.Date enddatetime;
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
	 * Return the value associated with the column: DSUMRY
	 */
	public java.lang.String getDsumry () {
		return dsumry;
	}

	/**
	 * Set the value related to the column: DSUMRY
	 * @param dsumry the DSUMRY value
	 */
	public void setDsumry (java.lang.String dsumry) {
		this.dsumry = dsumry;
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
	 * Return the value associated with the column: ISENABLE
	 */
	public java.lang.String getIsenable () {
		return isenable;
	}

	/**
	 * Set the value related to the column: ISENABLE
	 * @param isenable the ISENABLE value
	 */
	public void setIsenable (java.lang.String isenable) {
		this.isenable = isenable;
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
	 * Return the value associated with the column: ENDDATETIME
	 */
	public java.util.Date getEnddatetime () {
		return enddatetime;
	}

	/**
	 * Set the value related to the column: ENDDATETIME
	 * @param enddatetime the ENDDATETIME value
	 */
	public void setEnddatetime (java.util.Date enddatetime) {
		this.enddatetime = enddatetime;
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