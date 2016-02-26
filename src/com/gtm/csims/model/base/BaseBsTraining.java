package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_TRAINING table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_TRAINING"
 */

public abstract class BaseBsTraining  implements Serializable {

	public static String REF = "BsTraining";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_CREATORORG = "Creatororg";
	public static String PROP_TRANORGNM = "Tranorgnm";
	public static String PROP_STAT = "Stat";
	public static String PROP_TRANORGNO = "Tranorgno";
	public static String PROP_TRANEDORGNM = "Tranedorgnm";
	public static String PROP_TRANEDORGNO = "Tranedorgno";
	public static String PROP_TRANTERM = "Tranterm";
	public static String PROP_LASTDT = "Lastdt";
	public static String PROP_TRAININGNO = "Trainingno";
	public static String PROP_OPERDPT = "Operdpt";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_TRANSTARTDT = "Transtartdt";
	public static String PROP_TRANENDDT = "Tranenddt";
	public static String PROP_TRANCONT = "Trancont";
	public static String PROP_CREATOR = "Creator";
	public static String PROP_FLAG = "Flag";
	public static String PROP_TRANTM = "Trantm";
	public static String PROP_TELE = "Tele";
	public static String PROP_TRANPEPNUM = "Tranpepnum";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_CREATORORGNO = "Creatororgno";
	public static String PROP_ID = "Id";
	public static String PROP_TRANUSER = "Tranuser";


	// constructors
	public BaseBsTraining () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsTraining (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String trainingno;
	private java.lang.String tranorgnm;
	private java.lang.String tranorgno;
	private java.lang.String tranedorgnm;
	private java.lang.String tranedorgno;
	private java.lang.String operdpt;
	private java.lang.String tranuser;
	private java.lang.String tele;
	private java.util.Date transtartdt;
	private java.util.Date tranenddt;
	private java.lang.String tranterm;
	private java.lang.String trancont;
	private java.lang.String lastdt;
	private java.lang.String trantm;
	private java.lang.String tranpepnum;
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


	public java.lang.String getTranuser() {
		return tranuser;
	}

	public void setTranuser(java.lang.String tranuser) {
		this.tranuser = tranuser;
	}

	/**
	 * Return the value associated with the column: TRAININGNO
	 */
	public java.lang.String getTrainingno () {
		return trainingno;
	}

	/**
	 * Set the value related to the column: TRAININGNO
	 * @param trainingno the TRAININGNO value
	 */
	public void setTrainingno (java.lang.String trainingno) {
		this.trainingno = trainingno;
	}

	public java.lang.String getTranedorgnm() {
		return tranedorgnm;
	}

	public void setTranedorgnm(java.lang.String tranedorgnm) {
		this.tranedorgnm = tranedorgnm;
	}

	public java.lang.String getTranedorgno() {
		return tranedorgno;
	}

	public void setTranedorgno(java.lang.String tranedorgno) {
		this.tranedorgno = tranedorgno;
	}

	/**
	 * Return the value associated with the column: TRANORGNM
	 */
	public java.lang.String getTranorgnm () {
		return tranorgnm;
	}

	/**
	 * Set the value related to the column: TRANORGNM
	 * @param tranorgnm the TRANORGNM value
	 */
	public void setTranorgnm (java.lang.String tranorgnm) {
		this.tranorgnm = tranorgnm;
	}



	/**
	 * Return the value associated with the column: TRANORGNO
	 */
	public java.lang.String getTranorgno () {
		return tranorgno;
	}

	/**
	 * Set the value related to the column: TRANORGNO
	 * @param tranorgno the TRANORGNO value
	 */
	public void setTranorgno (java.lang.String tranorgno) {
		this.tranorgno = tranorgno;
	}



	/**
	 * Return the value associated with the column: OPERDPT
	 */
	public java.lang.String getOperdpt () {
		return operdpt;
	}

	/**
	 * Set the value related to the column: OPERDPT
	 * @param operdpt the OPERDPT value
	 */
	public void setOperdpt (java.lang.String operdpt) {
		this.operdpt = operdpt;
	}



	/**
	 * Return the value associated with the column: TELE
	 */
	public java.lang.String getTele () {
		return tele;
	}

	/**
	 * Set the value related to the column: TELE
	 * @param tele the TELE value
	 */
	public void setTele (java.lang.String tele) {
		this.tele = tele;
	}

	public java.util.Date getTranstartdt() {
		return transtartdt;
	}

	public void setTranstartdt(java.util.Date transtartdt) {
		this.transtartdt = transtartdt;
	}

	public java.util.Date getTranenddt() {
		return tranenddt;
	}

	public void setTranenddt(java.util.Date tranenddt) {
		this.tranenddt = tranenddt;
	}

	/**
	 * Return the value associated with the column: TRANTERM
	 */
	public java.lang.String getTranterm () {
		return tranterm;
	}

	/**
	 * Set the value related to the column: TRANTERM
	 * @param tranterm the TRANTERM value
	 */
	public void setTranterm (java.lang.String tranterm) {
		this.tranterm = tranterm;
	}



	/**
	 * Return the value associated with the column: TRANCONT
	 */
	public java.lang.String getTrancont () {
		return trancont;
	}

	/**
	 * Set the value related to the column: TRANCONT
	 * @param trancont the TRANCONT value
	 */
	public void setTrancont (java.lang.String trancont) {
		this.trancont = trancont;
	}



	/**
	 * Return the value associated with the column: LASTDT
	 */
	public java.lang.String getLastdt () {
		return lastdt;
	}

	/**
	 * Set the value related to the column: LASTDT
	 * @param lastdt the LASTDT value
	 */
	public void setLastdt (java.lang.String lastdt) {
		this.lastdt = lastdt;
	}



	/**
	 * Return the value associated with the column: TRANTM
	 */
	public java.lang.String getTrantm () {
		return trantm;
	}

	/**
	 * Set the value related to the column: TRANTM
	 * @param trantm the TRANTM value
	 */
	public void setTrantm (java.lang.String trantm) {
		this.trantm = trantm;
	}



	/**
	 * Return the value associated with the column: TRANPEPNUM
	 */
	public java.lang.String getTranpepnum () {
		return tranpepnum;
	}

	/**
	 * Set the value related to the column: TRANPEPNUM
	 * @param tranpepnum the TRANPEPNUM value
	 */
	public void setTranpepnum (java.lang.String tranpepnum) {
		this.tranpepnum = tranpepnum;
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

	public java.util.Date getUpdateate() {
		return updateate;
	}

	public void setUpdateate(java.util.Date updateate) {
		this.updateate = updateate;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsTraining)) return false;
		else {
			com.gtm.csims.model.BsTraining bsTraining = (com.gtm.csims.model.BsTraining) obj;
			if (null == this.getId() || null == bsTraining.getId()) return false;
			else return (this.getId().equals(bsTraining.getId()));
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