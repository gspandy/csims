package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_ADMENFCANLY table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_ADMENFCANLY"
 */

public abstract class BaseBsAdmenfcanly  implements Serializable {

	public static String REF = "BsAdmenfcanly";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_PUNED = "Puned";
	public static String PROP_CREATORORG = "Creatororg";
	public static String PROP_STAT = "Stat";
	public static String PROP_CHKEDORGNM = "Chkedorgnm";
	public static String PROP_CHKORGNO = "Chkorgno";
	public static String PROP_FILEENT = "Fileent";
	public static String PROP_CHKPEPNUM = "Chkpepnum";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_CHKDAYNUM = "Chkdaynum";
	public static String PROP_ENQNUM = "Enqnum";
	public static String PROP_CREATOR = "Creator";
	public static String PROP_FLAG = "Flag";
	public static String PROP_FILEPEP = "Filepep";
	public static String PROP_PUNING = "Puning";
	public static String PROP_FILENUM = "Filenum";
	public static String PROP_REGNO = "Regno";
	public static String PROP_CHKORGNM = "Chkorgnm";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_CHKEDORGNO = "Chkedorgno";
	public static String PROP_CREATORORGNO = "Creatororgno";
	public static String PROP_ID = "Id";
	public static String PROP_CHKWAY = "Chkway";


	// constructors
	public BaseBsAdmenfcanly () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAdmenfcanly (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String regno;
	private java.lang.String chkorgnm;
	private java.lang.String chkorgno;
	private java.lang.String chkedorgnm;
	private java.lang.String chkedorgno;
	private java.lang.String chkway;
	private java.lang.Long chkpepnum;
	private java.lang.Long chkdaynum;
	private java.lang.Long filepep;
	private java.lang.Long fileent;
	private java.lang.Long filenum;
	private java.lang.Long enqnum;
	private java.math.BigDecimal puned;
	private java.math.BigDecimal puning;
	private java.lang.String creator;
	private java.lang.String creatororg;
	private java.lang.String creatororgno;
	private java.util.Date crtdate;
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
	 * Return the value associated with the column: REGNO
	 */
	public java.lang.String getRegno () {
		return regno;
	}

	/**
	 * Set the value related to the column: REGNO
	 * @param regno the REGNO value
	 */
	public void setRegno (java.lang.String regno) {
		this.regno = regno;
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
	 * Return the value associated with the column: CHKWAY
	 */
	public java.lang.String getChkway () {
		return chkway;
	}

	/**
	 * Set the value related to the column: CHKWAY
	 * @param chkway the CHKWAY value
	 */
	public void setChkway (java.lang.String chkway) {
		this.chkway = chkway;
	}



	/**
	 * Return the value associated with the column: CHKPEPNUM
	 */
	public java.lang.Long getChkpepnum () {
		return chkpepnum;
	}

	/**
	 * Set the value related to the column: CHKPEPNUM
	 * @param chkpepnum the CHKPEPNUM value
	 */
	public void setChkpepnum (java.lang.Long chkpepnum) {
		this.chkpepnum = chkpepnum;
	}



	/**
	 * Return the value associated with the column: CHKDAYNUM
	 */
	public java.lang.Long getChkdaynum () {
		return chkdaynum;
	}

	/**
	 * Set the value related to the column: CHKDAYNUM
	 * @param chkdaynum the CHKDAYNUM value
	 */
	public void setChkdaynum (java.lang.Long chkdaynum) {
		this.chkdaynum = chkdaynum;
	}



	/**
	 * Return the value associated with the column: FILEPEP
	 */
	public java.lang.Long getFilepep () {
		return filepep;
	}

	/**
	 * Set the value related to the column: FILEPEP
	 * @param filepep the FILEPEP value
	 */
	public void setFilepep (java.lang.Long filepep) {
		this.filepep = filepep;
	}



	/**
	 * Return the value associated with the column: FILEENT
	 */
	public java.lang.Long getFileent () {
		return fileent;
	}

	/**
	 * Set the value related to the column: FILEENT
	 * @param fileent the FILEENT value
	 */
	public void setFileent (java.lang.Long fileent) {
		this.fileent = fileent;
	}



	/**
	 * Return the value associated with the column: FILENUM
	 */
	public java.lang.Long getFilenum () {
		return filenum;
	}

	/**
	 * Set the value related to the column: FILENUM
	 * @param filenum the FILENUM value
	 */
	public void setFilenum (java.lang.Long filenum) {
		this.filenum = filenum;
	}



	/**
	 * Return the value associated with the column: ENQNUM
	 */
	public java.lang.Long getEnqnum () {
		return enqnum;
	}

	/**
	 * Set the value related to the column: ENQNUM
	 * @param enqnum the ENQNUM value
	 */
	public void setEnqnum (java.lang.Long enqnum) {
		this.enqnum = enqnum;
	}



	/**
	 * Return the value associated with the column: PUNED
	 */
	public java.math.BigDecimal getPuned () {
		return puned;
	}

	/**
	 * Set the value related to the column: PUNED
	 * @param puned the PUNED value
	 */
	public void setPuned (java.math.BigDecimal puned) {
		this.puned = puned;
	}



	/**
	 * Return the value associated with the column: PUNING
	 */
	public java.math.BigDecimal getPuning () {
		return puning;
	}

	/**
	 * Set the value related to the column: PUNING
	 * @param puning the PUNING value
	 */
	public void setPuning (java.math.BigDecimal puning) {
		this.puning = puning;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsAdmenfcanly)) return false;
		else {
			com.gtm.csims.model.BsAdmenfcanly bsAdmenfcanly = (com.gtm.csims.model.BsAdmenfcanly) obj;
			if (null == this.getId() || null == bsAdmenfcanly.getId()) return false;
			else return (this.getId().equals(bsAdmenfcanly.getId()));
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