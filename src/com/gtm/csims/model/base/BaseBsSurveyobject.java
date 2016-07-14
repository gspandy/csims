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
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_SOISFINISHED = "Soisfinished";
	public static String PROP_SOQORGTYPE = "Soqorgtype";
	public static String PROP_FLAG = "Flag";
	public static String PROP_STAT = "Stat";
	public static String PROP_SOQORGTYPENO = "Soqorgtypeno";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_SOQORG = "Soqorg";
	public static String PROP_SOQUESTIONAIREID = "Soquestionaireid";
	public static String PROP_ID = "Id";
	public static String PROP_SOQORGNO = "Soqorgno";


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
	private java.lang.String soquestionaireid;
	private java.lang.String soqorgno;
	private java.lang.String soqorg;
	private java.lang.String soqorgtypeno;
	private java.lang.String soqorgtype;
	private java.lang.String soisfinished;
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
	 * Return the value associated with the column: SOQUESTIONAIREID
	 */
	public java.lang.String getSoquestionaireid () {
		return soquestionaireid;
	}

	/**
	 * Set the value related to the column: SOQUESTIONAIREID
	 * @param soquestionaireid the SOQUESTIONAIREID value
	 */
	public void setSoquestionaireid (java.lang.String soquestionaireid) {
		this.soquestionaireid = soquestionaireid;
	}



	/**
	 * Return the value associated with the column: SOQORGNO
	 */
	public java.lang.String getSoqorgno () {
		return soqorgno;
	}

	/**
	 * Set the value related to the column: SOQORGNO
	 * @param soqorgno the SOQORGNO value
	 */
	public void setSoqorgno (java.lang.String soqorgno) {
		this.soqorgno = soqorgno;
	}



	/**
	 * Return the value associated with the column: SOQORG
	 */
	public java.lang.String getSoqorg () {
		return soqorg;
	}

	/**
	 * Set the value related to the column: SOQORG
	 * @param soqorg the SOQORG value
	 */
	public void setSoqorg (java.lang.String soqorg) {
		this.soqorg = soqorg;
	}



	/**
	 * Return the value associated with the column: SOQORGTYPENO
	 */
	public java.lang.String getSoqorgtypeno () {
		return soqorgtypeno;
	}

	/**
	 * Set the value related to the column: SOQORGTYPENO
	 * @param soqorgtypeno the SOQORGTYPENO value
	 */
	public void setSoqorgtypeno (java.lang.String soqorgtypeno) {
		this.soqorgtypeno = soqorgtypeno;
	}



	/**
	 * Return the value associated with the column: SOQORGTYPE
	 */
	public java.lang.String getSoqorgtype () {
		return soqorgtype;
	}

	/**
	 * Set the value related to the column: SOQORGTYPE
	 * @param soqorgtype the SOQORGTYPE value
	 */
	public void setSoqorgtype (java.lang.String soqorgtype) {
		this.soqorgtype = soqorgtype;
	}



	/**
	 * Return the value associated with the column: SOISFINISHED
	 */
	public java.lang.String getSoisfinished () {
		return soisfinished;
	}

	/**
	 * Set the value related to the column: SOISFINISHED
	 * @param soisfinished the SOISFINISHED value
	 */
	public void setSoisfinished (java.lang.String soisfinished) {
		this.soisfinished = soisfinished;
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