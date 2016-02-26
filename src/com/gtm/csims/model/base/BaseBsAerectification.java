package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_AERECTIFICATION table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_AERECTIFICATION"
 */

public abstract class BaseBsAerectification  implements Serializable {

	public static String REF = "BsAerectification";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_AEORGNM = "Aeorgnm";
	public static String PROP_STAT = "Stat";
	public static String PROP_AEEDORGXZHNO = "Aeedorgxzhno";
	public static String PROP_AEEDORGZZNM = "Aeedorgzznm";
	public static String PROP_AEORGNO = "Aeorgno";
	public static String PROP_TRACKCONTEND = "Trackcontend";
	public static String PROP_CANBEUPDATE = "Canbeupdate";
	public static String PROP_AEEDORGZZNO = "Aeedorgzzno";
	public static String PROP_FLAG = "Flag";
	public static String PROP_AEPLANTMDATE = "Aeplantmdate";
	public static String PROP_AENO = "Aeno";
	public static String PROP_TRACKATTA = "Trackatta";
	public static String PROP_AEEDORGNO = "Aeedorgno";
	public static String PROP_RECTIFICATIONTMDATE = "Rectificationtmdate";
	public static String PROP_CRTLOGIN = "Crtlogin";
	public static String PROP_TRACKNO = "Trackno";
	public static String PROP_AEOPNIONNO = "Aeopnionno";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ISFINISHED = "Isfinished";
	public static String PROP_AEEDORGNM = "Aeedorgnm";
	public static String PROP_AEEDORGXZHNM = "Aeedorgxzhnm";
	public static String PROP_AEPLANSTDATE = "Aeplanstdate";
	public static String PROP_ID = "Id";
	public static String PROP_RECTIFICATIONSTDATE = "Rectificationstdate";


	// constructors
	public BaseBsAerectification () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAerectification (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBsAerectification (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno,
		java.lang.String trackno) {

		this.setId(id);
		this.setAeno(aeno);
		this.setAeorgnm(aeorgnm);
		this.setAeorgno(aeorgno);
		this.setAeedorgnm(aeedorgnm);
		this.setAeedorgno(aeedorgno);
		this.setTrackno(trackno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String aeno;
	private java.lang.String aeorgnm;
	private java.lang.String aeorgno;
	private java.lang.String aeedorgnm;
	private java.lang.String aeedorgno;
	private java.util.Date aeplanstdate;
	private java.util.Date aeplantmdate;
	private java.lang.String aeopnionno;
	private java.lang.String trackno;
	private java.lang.String trackcontend;
	private java.lang.String trackatta;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updateate;
	private java.util.Date rectificationstdate;
	private java.util.Date rectificationtmdate;
	private java.lang.String crtlogin;
	private java.lang.String aeedorgzzno;
	private java.lang.String aeedorgxzhno;
	private java.lang.String aeedorgzznm;
	private java.lang.String aeedorgxzhnm;
	private boolean isfinished;
	private boolean canbeupdate;



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
	 * Return the value associated with the column: AENO
	 */
	public java.lang.String getAeno () {
		return aeno;
	}

	/**
	 * Set the value related to the column: AENO
	 * @param aeno the AENO value
	 */
	public void setAeno (java.lang.String aeno) {
		this.aeno = aeno;
	}



	/**
	 * Return the value associated with the column: AEORGNM
	 */
	public java.lang.String getAeorgnm () {
		return aeorgnm;
	}

	/**
	 * Set the value related to the column: AEORGNM
	 * @param aeorgnm the AEORGNM value
	 */
	public void setAeorgnm (java.lang.String aeorgnm) {
		this.aeorgnm = aeorgnm;
	}



	/**
	 * Return the value associated with the column: AEORGNO
	 */
	public java.lang.String getAeorgno () {
		return aeorgno;
	}

	/**
	 * Set the value related to the column: AEORGNO
	 * @param aeorgno the AEORGNO value
	 */
	public void setAeorgno (java.lang.String aeorgno) {
		this.aeorgno = aeorgno;
	}



	/**
	 * Return the value associated with the column: AEEDORGNM
	 */
	public java.lang.String getAeedorgnm () {
		return aeedorgnm;
	}

	/**
	 * Set the value related to the column: AEEDORGNM
	 * @param aeedorgnm the AEEDORGNM value
	 */
	public void setAeedorgnm (java.lang.String aeedorgnm) {
		this.aeedorgnm = aeedorgnm;
	}



	/**
	 * Return the value associated with the column: AEEDORGNO
	 */
	public java.lang.String getAeedorgno () {
		return aeedorgno;
	}

	/**
	 * Set the value related to the column: AEEDORGNO
	 * @param aeedorgno the AEEDORGNO value
	 */
	public void setAeedorgno (java.lang.String aeedorgno) {
		this.aeedorgno = aeedorgno;
	}



	/**
	 * Return the value associated with the column: AEPLANSTDATE
	 */
	public java.util.Date getAeplanstdate () {
		return aeplanstdate;
	}

	/**
	 * Set the value related to the column: AEPLANSTDATE
	 * @param aeplanstdate the AEPLANSTDATE value
	 */
	public void setAeplanstdate (java.util.Date aeplanstdate) {
		this.aeplanstdate = aeplanstdate;
	}



	/**
	 * Return the value associated with the column: AEPLANTMDATE
	 */
	public java.util.Date getAeplantmdate () {
		return aeplantmdate;
	}

	/**
	 * Set the value related to the column: AEPLANTMDATE
	 * @param aeplantmdate the AEPLANTMDATE value
	 */
	public void setAeplantmdate (java.util.Date aeplantmdate) {
		this.aeplantmdate = aeplantmdate;
	}



	/**
	 * Return the value associated with the column: AEOPNIONNO
	 */
	public java.lang.String getAeopnionno () {
		return aeopnionno;
	}

	/**
	 * Set the value related to the column: AEOPNIONNO
	 * @param aeopnionno the AEOPNIONNO value
	 */
	public void setAeopnionno (java.lang.String aeopnionno) {
		this.aeopnionno = aeopnionno;
	}



	/**
	 * Return the value associated with the column: TRACKNO
	 */
	public java.lang.String getTrackno () {
		return trackno;
	}

	/**
	 * Set the value related to the column: TRACKNO
	 * @param trackno the TRACKNO value
	 */
	public void setTrackno (java.lang.String trackno) {
		this.trackno = trackno;
	}



	/**
	 * Return the value associated with the column: TRACKCONTEND
	 */
	public java.lang.String getTrackcontend () {
		return trackcontend;
	}

	/**
	 * Set the value related to the column: TRACKCONTEND
	 * @param trackcontend the TRACKCONTEND value
	 */
	public void setTrackcontend (java.lang.String trackcontend) {
		this.trackcontend = trackcontend;
	}



	/**
	 * Return the value associated with the column: TRACKATTA
	 */
	public java.lang.String getTrackatta () {
		return trackatta;
	}

	/**
	 * Set the value related to the column: TRACKATTA
	 * @param trackatta the TRACKATTA value
	 */
	public void setTrackatta (java.lang.String trackatta) {
		this.trackatta = trackatta;
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



	/**
	 * Return the value associated with the column: RECTIFICATIONSTDATE
	 */
	public java.util.Date getRectificationstdate () {
		return rectificationstdate;
	}

	/**
	 * Set the value related to the column: RECTIFICATIONSTDATE
	 * @param rectificationstdate the RECTIFICATIONSTDATE value
	 */
	public void setRectificationstdate (java.util.Date rectificationstdate) {
		this.rectificationstdate = rectificationstdate;
	}



	/**
	 * Return the value associated with the column: RECTIFICATIONTMDATE
	 */
	public java.util.Date getRectificationtmdate () {
		return rectificationtmdate;
	}

	/**
	 * Set the value related to the column: RECTIFICATIONTMDATE
	 * @param rectificationtmdate the RECTIFICATIONTMDATE value
	 */
	public void setRectificationtmdate (java.util.Date rectificationtmdate) {
		this.rectificationtmdate = rectificationtmdate;
	}



	/**
	 * Return the value associated with the column: CRTLOGIN
	 */
	public java.lang.String getCrtlogin () {
		return crtlogin;
	}

	/**
	 * Set the value related to the column: CRTLOGIN
	 * @param crtlogin the CRTLOGIN value
	 */
	public void setCrtlogin (java.lang.String crtlogin) {
		this.crtlogin = crtlogin;
	}



	/**
	 * Return the value associated with the column: AEEDORGZZNO
	 */
	public java.lang.String getAeedorgzzno () {
		return aeedorgzzno;
	}

	/**
	 * Set the value related to the column: AEEDORGZZNO
	 * @param aeedorgzzno the AEEDORGZZNO value
	 */
	public void setAeedorgzzno (java.lang.String aeedorgzzno) {
		this.aeedorgzzno = aeedorgzzno;
	}



	/**
	 * Return the value associated with the column: AEEDORGXZHNO
	 */
	public java.lang.String getAeedorgxzhno () {
		return aeedorgxzhno;
	}

	/**
	 * Set the value related to the column: AEEDORGXZHNO
	 * @param aeedorgxzhno the AEEDORGXZHNO value
	 */
	public void setAeedorgxzhno (java.lang.String aeedorgxzhno) {
		this.aeedorgxzhno = aeedorgxzhno;
	}



	/**
	 * Return the value associated with the column: AEEDORGZZNM
	 */
	public java.lang.String getAeedorgzznm () {
		return aeedorgzznm;
	}

	/**
	 * Set the value related to the column: AEEDORGZZNM
	 * @param aeedorgzznm the AEEDORGZZNM value
	 */
	public void setAeedorgzznm (java.lang.String aeedorgzznm) {
		this.aeedorgzznm = aeedorgzznm;
	}



	/**
	 * Return the value associated with the column: AEEDORGXZHNM
	 */
	public java.lang.String getAeedorgxzhnm () {
		return aeedorgxzhnm;
	}

	/**
	 * Set the value related to the column: AEEDORGXZHNM
	 * @param aeedorgxzhnm the AEEDORGXZHNM value
	 */
	public void setAeedorgxzhnm (java.lang.String aeedorgxzhnm) {
		this.aeedorgxzhnm = aeedorgxzhnm;
	}



	/**
	 * Return the value associated with the column: ISFINISH
	 */
	public boolean isIsfinished () {
		return isfinished;
	}

	/**
	 * Set the value related to the column: ISFINISH
	 * @param isfinished the ISFINISH value
	 */
	public void setIsfinished (boolean isfinished) {
		this.isfinished = isfinished;
	}



	/**
	 * Return the value associated with the column: CANBEUPDATE
	 */
	public boolean isCanbeupdate () {
		return canbeupdate;
	}

	/**
	 * Set the value related to the column: CANBEUPDATE
	 * @param canbeupdate the CANBEUPDATE value
	 */
	public void setCanbeupdate (boolean canbeupdate) {
		this.canbeupdate = canbeupdate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsAerectification)) return false;
		else {
			com.gtm.csims.model.BsAerectification bsAerectification = (com.gtm.csims.model.BsAerectification) obj;
			if (null == this.getId() || null == bsAerectification.getId()) return false;
			else return (this.getId().equals(bsAerectification.getId()));
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