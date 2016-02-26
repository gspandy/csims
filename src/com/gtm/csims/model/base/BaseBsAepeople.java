package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_AEPEOPLE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_AEPEOPLE"
 */

public abstract class BaseBsAepeople  implements Serializable {

	public static String REF = "BsAepeople";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_ORGNM = "Orgnm";
	public static String PROP_STAT = "Stat";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_CERTNO = "Certno";
	public static String PROP_BANKNO = "Bankno";
	public static String PROP_FLAG = "Flag";
	public static String PROP_CREDITNO = "Creditno";
	public static String PROP_ORGNO = "Orgno";
	public static String PROP_PEPNAME = "Pepname";
	public static String PROP_TELE = "Tele";
	public static String PROP_DPTNM = "Dptnm";
	public static String PROP_DPTNO = "Dptno";
	public static String PROP_PRCIPAL = "Prcipal";
	public static String PROP_ID = "Id";
	public static String PROP_CERTTP = "Certtp";
	public static String PROP_CARDID = "Cardid";
	public static String PROP_SEX = "Sex";
	public static String PROP_EDUCATION = "Education";
	// constructors
	public BaseBsAepeople () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAepeople (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String pepname;
	private java.lang.String certtp;
	private java.lang.String certno;
	private java.lang.String cardid;
	private java.lang.String bankno;
	private java.lang.String creditno;
	private java.lang.String orgno;
	private java.lang.String orgnm;
	private java.lang.String dptnm;
	private java.lang.String dptno;
	private java.lang.String prcipal;
	private java.lang.String tele;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.lang.String updateate;
	private java.lang.String sex;
	private java.lang.String education;


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

	public java.lang.String getCardid() {
		return cardid;
	}

	public void setCardid(java.lang.String cardid) {
		this.cardid = cardid;
	}

	/**
	 * Return the value associated with the column: PEPNAME
	 */
	public java.lang.String getPepname () {
		return pepname;
	}

	/**
	 * Set the value related to the column: PEPNAME
	 * @param pepname the PEPNAME value
	 */
	public void setPepname (java.lang.String pepname) {
		this.pepname = pepname;
	}

	public java.lang.String getSex() {
		return sex;
	}

	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	public java.lang.String getEducation() {
		return education;
	}

	public void setEducation(java.lang.String education) {
		this.education = education;
	}

	public java.lang.String getDptno() {
		return dptno;
	}

	public void setDptno(java.lang.String dptno) {
		this.dptno = dptno;
	}

	/**
	 * Return the value associated with the column: CERTTP
	 */
	public java.lang.String getCerttp () {
		return certtp;
	}

	/**
	 * Set the value related to the column: CERTTP
	 * @param certtp the CERTTP value
	 */
	public void setCerttp (java.lang.String certtp) {
		this.certtp = certtp;
	}

	/**
	 * Return the value associated with the column: CERTNO
	 */
	public java.lang.String getCertno () {
		return certno;
	}

	/**
	 * Set the value related to the column: CERTNO
	 * @param certno the CERTNO value
	 */
	public void setCertno (java.lang.String certno) {
		this.certno = certno;
	}

	/**
	 * Return the value associated with the column: BANKNO
	 */
	public java.lang.String getBankno () {
		return bankno;
	}

	/**
	 * Set the value related to the column: BANKNO
	 * @param bankno the BANKNO value
	 */
	public void setBankno (java.lang.String bankno) {
		this.bankno = bankno;
	}



	/**
	 * Return the value associated with the column: CREDITNO
	 */
	public java.lang.String getCreditno () {
		return creditno;
	}

	/**
	 * Set the value related to the column: CREDITNO
	 * @param creditno the CREDITNO value
	 */
	public void setCreditno (java.lang.String creditno) {
		this.creditno = creditno;
	}



	/**
	 * Return the value associated with the column: ORGNO
	 */
	public java.lang.String getOrgno () {
		return orgno;
	}

	/**
	 * Set the value related to the column: ORGNO
	 * @param orgno the ORGNO value
	 */
	public void setOrgno (java.lang.String orgno) {
		this.orgno = orgno;
	}



	/**
	 * Return the value associated with the column: ORGNM
	 */
	public java.lang.String getOrgnm () {
		return orgnm;
	}

	/**
	 * Set the value related to the column: ORGNM
	 * @param orgnm the ORGNM value
	 */
	public void setOrgnm (java.lang.String orgnm) {
		this.orgnm = orgnm;
	}



	/**
	 * Return the value associated with the column: DPTNM
	 */
	public java.lang.String getDptnm () {
		return dptnm;
	}

	/**
	 * Set the value related to the column: DPTNM
	 * @param dptnm the DPTNM value
	 */
	public void setDptnm (java.lang.String dptnm) {
		this.dptnm = dptnm;
	}



	/**
	 * Return the value associated with the column: PRCIPAL
	 */
	public java.lang.String getPrcipal () {
		return prcipal;
	}

	/**
	 * Set the value related to the column: PRCIPAL
	 * @param prcipal the PRCIPAL value
	 */
	public void setPrcipal (java.lang.String prcipal) {
		this.prcipal = prcipal;
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
	public java.lang.String getUpdateate () {
		return updateate;
	}

	/**
	 * Set the value related to the column: UPDATEATE
	 * @param updateate the UPDATEATE value
	 */
	public void setUpdateate (java.lang.String updateate) {
		this.updateate = updateate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsAepeople)) return false;
		else {
			com.gtm.csims.model.BsAepeople bsAepeople = (com.gtm.csims.model.BsAepeople) obj;
			if (null == this.getId() || null == bsAepeople.getId()) return false;
			else return (this.getId().equals(bsAepeople.getId()));
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