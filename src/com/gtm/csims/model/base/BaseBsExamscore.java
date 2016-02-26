package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_EXAMSCORE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_EXAMSCORE"
 */

public abstract class BaseBsExamscore  implements Serializable {

	public static String REF = "BsExamscore";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_EXTMDT = "Extmdt";
	public static String PROP_PASS = "Pass";
	public static String PROP_PEOTYPE = "Peotype";
	public static String PROP_IMPORTNO = "Importno";
	public static String PROP_STAT = "Stat";
	public static String PROP_PCBNO = "Pcbno";
	public static String PROP_SCORE = "Score";
	public static String PROP_CITY = "City";
	public static String PROP_FULLMARK = "Fullmark";
	public static String PROP_PEONM = "Peonm";
	public static String PROP_CRTDT = "Crtdt";
	public static String PROP_FLAG = "Flag";
	public static String PROP_ORGNO = "Orgno";
	public static String PROP_EXSTDT = "Exstdt";
	public static String PROP_PCBNM = "Pcbnm";
	public static String PROP_PEOID = "Peoid";
	public static String PROP_CERTTYPE = "Certtype";
	public static String PROP_CRTOR = "Crtor";
	public static String PROP_EXTYPE = "Extype";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ORGNAME = "Orgname";
	public static String PROP_ORGTPNM = "Orgtpnm";
	public static String PROP_COUNTRY = "Country";
	public static String PROP_TELE = "Tele";
	public static String PROP_ORGTPNO = "Orgtpno";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsExamscore () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsExamscore (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBsExamscore (
		java.lang.String id,
		java.lang.String importno,
		java.lang.String peoid,
		java.lang.String peotype,
		java.lang.String orgno,
		java.math.BigDecimal score) {

		this.setId(id);
		this.setImportno(importno);
		this.setPeoid(peoid);
		this.setPeotype(peotype);
		this.setOrgno(orgno);
		this.setScore(score);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String importno;
	private java.lang.String peoid;
	private java.lang.String peonm;
	private java.lang.String certtype;
	private java.lang.String peotype;
	private java.lang.String orgno;
	private java.lang.String orgname;
	private java.lang.String orgtpno;
	private java.lang.String orgtpnm;
	private java.lang.String pcbno;
	private java.lang.String pcbnm;
	private java.lang.String city;
	private java.lang.String country;
	private java.lang.String tele;
	private java.util.Date exstdt;
	private java.util.Date extmdt;
	private java.lang.String extype;
	private java.math.BigDecimal fullmark;
	private java.math.BigDecimal pass;
	private java.math.BigDecimal score;
	private java.util.Date crtdt;
	private java.lang.String crtor;
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
	 * Return the value associated with the column: IMPORTNO
	 */
	public java.lang.String getImportno () {
		return importno;
	}

	/**
	 * Set the value related to the column: IMPORTNO
	 * @param importno the IMPORTNO value
	 */
	public void setImportno (java.lang.String importno) {
		this.importno = importno;
	}



	/**
	 * Return the value associated with the column: PEOID
	 */
	public java.lang.String getPeoid () {
		return peoid;
	}

	/**
	 * Set the value related to the column: PEOID
	 * @param peoid the PEOID value
	 */
	public void setPeoid (java.lang.String peoid) {
		this.peoid = peoid;
	}



	/**
	 * Return the value associated with the column: PEONM
	 */
	public java.lang.String getPeonm () {
		return peonm;
	}

	/**
	 * Set the value related to the column: PEONM
	 * @param peonm the PEONM value
	 */
	public void setPeonm (java.lang.String peonm) {
		this.peonm = peonm;
	}



	/**
	 * Return the value associated with the column: CERTTYPE
	 */
	public java.lang.String getCerttype () {
		return certtype;
	}

	/**
	 * Set the value related to the column: CERTTYPE
	 * @param certtype the CERTTYPE value
	 */
	public void setCerttype (java.lang.String certtype) {
		this.certtype = certtype;
	}



	/**
	 * Return the value associated with the column: PEOTYPE
	 */
	public java.lang.String getPeotype () {
		return peotype;
	}

	/**
	 * Set the value related to the column: PEOTYPE
	 * @param peotype the PEOTYPE value
	 */
	public void setPeotype (java.lang.String peotype) {
		this.peotype = peotype;
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
	 * Return the value associated with the column: ORGNAME
	 */
	public java.lang.String getOrgname () {
		return orgname;
	}

	/**
	 * Set the value related to the column: ORGNAME
	 * @param orgname the ORGNAME value
	 */
	public void setOrgname (java.lang.String orgname) {
		this.orgname = orgname;
	}



	/**
	 * Return the value associated with the column: ORGTPNO
	 */
	public java.lang.String getOrgtpno () {
		return orgtpno;
	}

	/**
	 * Set the value related to the column: ORGTPNO
	 * @param orgtpno the ORGTPNO value
	 */
	public void setOrgtpno (java.lang.String orgtpno) {
		this.orgtpno = orgtpno;
	}



	/**
	 * Return the value associated with the column: ORGTPNM
	 */
	public java.lang.String getOrgtpnm () {
		return orgtpnm;
	}

	/**
	 * Set the value related to the column: ORGTPNM
	 * @param orgtpnm the ORGTPNM value
	 */
	public void setOrgtpnm (java.lang.String orgtpnm) {
		this.orgtpnm = orgtpnm;
	}



	/**
	 * Return the value associated with the column: PCBNO
	 */
	public java.lang.String getPcbno () {
		return pcbno;
	}

	/**
	 * Set the value related to the column: PCBNO
	 * @param pcbno the PCBNO value
	 */
	public void setPcbno (java.lang.String pcbno) {
		this.pcbno = pcbno;
	}



	/**
	 * Return the value associated with the column: PCBNM
	 */
	public java.lang.String getPcbnm () {
		return pcbnm;
	}

	/**
	 * Set the value related to the column: PCBNM
	 * @param pcbnm the PCBNM value
	 */
	public void setPcbnm (java.lang.String pcbnm) {
		this.pcbnm = pcbnm;
	}



	/**
	 * Return the value associated with the column: CITY
	 */
	public java.lang.String getCity () {
		return city;
	}

	/**
	 * Set the value related to the column: CITY
	 * @param city the CITY value
	 */
	public void setCity (java.lang.String city) {
		this.city = city;
	}



	/**
	 * Return the value associated with the column: COUNTRY
	 */
	public java.lang.String getCountry () {
		return country;
	}

	/**
	 * Set the value related to the column: COUNTRY
	 * @param country the COUNTRY value
	 */
	public void setCountry (java.lang.String country) {
		this.country = country;
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
	 * Return the value associated with the column: EXSTDT
	 */
	public java.util.Date getExstdt () {
		return exstdt;
	}

	/**
	 * Set the value related to the column: EXSTDT
	 * @param exstdt the EXSTDT value
	 */
	public void setExstdt (java.util.Date exstdt) {
		this.exstdt = exstdt;
	}



	/**
	 * Return the value associated with the column: EXTMDT
	 */
	public java.util.Date getExtmdt () {
		return extmdt;
	}

	/**
	 * Set the value related to the column: EXTMDT
	 * @param extmdt the EXTMDT value
	 */
	public void setExtmdt (java.util.Date extmdt) {
		this.extmdt = extmdt;
	}



	/**
	 * Return the value associated with the column: EXTYPE
	 */
	public java.lang.String getExtype () {
		return extype;
	}

	/**
	 * Set the value related to the column: EXTYPE
	 * @param extype the EXTYPE value
	 */
	public void setExtype (java.lang.String extype) {
		this.extype = extype;
	}



	/**
	 * Return the value associated with the column: FULLMARK
	 */
	public java.math.BigDecimal getFullmark () {
		return fullmark;
	}

	/**
	 * Set the value related to the column: FULLMARK
	 * @param fullmark the FULLMARK value
	 */
	public void setFullmark (java.math.BigDecimal fullmark) {
		this.fullmark = fullmark;
	}



	/**
	 * Return the value associated with the column: PASS
	 */
	public java.math.BigDecimal getPass () {
		return pass;
	}

	/**
	 * Set the value related to the column: PASS
	 * @param pass the PASS value
	 */
	public void setPass (java.math.BigDecimal pass) {
		this.pass = pass;
	}



	/**
	 * Return the value associated with the column: SCORE
	 */
	public java.math.BigDecimal getScore () {
		return score;
	}

	/**
	 * Set the value related to the column: SCORE
	 * @param score the SCORE value
	 */
	public void setScore (java.math.BigDecimal score) {
		this.score = score;
	}



	/**
	 * Return the value associated with the column: CRTDT
	 */
	public java.util.Date getCrtdt () {
		return crtdt;
	}

	/**
	 * Set the value related to the column: CRTDT
	 * @param crtdt the CRTDT value
	 */
	public void setCrtdt (java.util.Date crtdt) {
		this.crtdt = crtdt;
	}



	/**
	 * Return the value associated with the column: CRTOR
	 */
	public java.lang.String getCrtor () {
		return crtor;
	}

	/**
	 * Set the value related to the column: CRTOR
	 * @param crtor the CRTOR value
	 */
	public void setCrtor (java.lang.String crtor) {
		this.crtor = crtor;
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
		if (!(obj instanceof com.gtm.csims.model.BsExamscore)) return false;
		else {
			com.gtm.csims.model.BsExamscore bsExamscore = (com.gtm.csims.model.BsExamscore) obj;
			if (null == this.getId() || null == bsExamscore.getId()) return false;
			else return (this.getId().equals(bsExamscore.getId()));
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