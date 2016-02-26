package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_AEINSPECTION table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_AEINSPECTION"
 */

public abstract class BaseBsAeinspection  implements Serializable {

	public static String REF = "BsAeinspection";
	public static String PROP_AEPEOPLES = "Aepeoples";
	public static String PROP_AEBASIS = "Aebasis";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_AERECORDNO = "Aerecordno";
	public static String PROP_ACTUALRCDNO = "Actualrcdno";
	public static String PROP_AEORGNM = "Aeorgnm";
	public static String PROP_AEEDORGZZNM = "Aeedorgzznm";
	public static String PROP_AEEDORGXZHNO = "Aeedorgxzhno";
	public static String PROP_STAT = "Stat";
	public static String PROP_OUTRCD = "Outrcd";
	public static String PROP_AEORGNO = "Aeorgno";
	public static String PROP_CREATOR = "Creator";
	public static String PROP_CANBEUPDATE = "Canbeupdate";
	public static String PROP_INO = "Ino";
	public static String PROP_AEEDORGZZNO = "Aeedorgzzno";
	public static String PROP_INNERNO = "Innerno";
	public static String PROP_FLAG = "Flag";
	public static String PROP_AEPLANTMDATE = "Aeplantmdate";
	public static String PROP_ACTUALRCD = "Actualrcd";
	public static String PROP_INRCD = "Inrcd";
	public static String PROP_INRCDNO = "Inrcdno";
	public static String PROP_AENO = "Aeno";
	public static String PROP_AEEDORGNO = "Aeedorgno";
	public static String PROP_CRTLOGIN = "Crtlogin";
	public static String PROP_CREATORORGNO = "Creatororgno";
	public static String PROP_ISFINISHED_REFTI = "IsfinishedRefti";
	public static String PROP_AEINDATE = "Aeindate";
	public static String PROP_CREATORORG = "Creatororg";
	public static String PROP_AEREVIEWER = "Aereviewer";
	public static String PROP_OUTRCDNO = "Outrcdno";
	public static String PROP_PROBSUMR = "Probsumr";
	public static String PROP_AEPRJNM = "Aeprjnm";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_ISFINISHED = "Isfinished";
	public static String PROP_AEOUTDATE = "Aeoutdate";
	public static String PROP_AEHEADMAN = "Aeheadman";
	public static String PROP_AEEDORGNM = "Aeedorgnm";
	public static String PROP_ENQUIRERCD = "Enquirercd";
	public static String PROP_AEEDORGXZHNM = "Aeedorgxzhnm";
	public static String PROP_AEOTHER = "Aeother";
	public static String PROP_AEMASTER = "Aemaster";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_ID = "Id";
	public static String PROP_AEPLANSTDATE = "Aeplanstdate";


	// constructors
	public BaseBsAeinspection () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAeinspection (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBsAeinspection (
		java.lang.String id,
		java.lang.String ino,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno) {

		this.setId(id);
		this.setIno(ino);
		this.setAeno(aeno);
		this.setAeorgnm(aeorgnm);
		this.setAeorgno(aeorgno);
		this.setAeedorgnm(aeedorgnm);
		this.setAeedorgno(aeedorgno);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String ino;
	private java.lang.String aeno;
	private java.lang.String aeorgnm;
	private java.lang.String aeorgno;
	private java.lang.String aeedorgnm;
	private java.lang.String aeedorgno;
	private java.lang.String aerecordno;
	private java.util.Date aeplanstdate;
	private java.util.Date aeplantmdate;
	private java.lang.String aeprjnm;
	private java.util.Date aeindate;
	private java.util.Date aeoutdate;
	private java.lang.String aepeoples;
	private java.lang.String aebasis;
	private java.lang.String enquirercd;
	private java.lang.String actualrcdno;
	private java.lang.String actualrcd;
	private java.lang.String inrcd;
	private java.lang.String inrcdno;
	private java.lang.String outrcd;
	private java.lang.String outrcdno;
	private java.lang.String creator;
	private java.lang.String creatororg;
	private java.lang.String creatororgno;
	private java.util.Date crtdate;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updateate;
	private java.lang.String probsumr;
	private java.lang.String aeheadman;
	private java.lang.String aemaster;
	private java.lang.String aereviewer;
	private java.lang.String aeother;
	private java.lang.String crtlogin;
	private java.lang.String innerno;
	private java.lang.String aeedorgzzno;
	private java.lang.String aeedorgxzhno;
	private java.lang.String aeedorgzznm;
	private java.lang.String aeedorgxzhnm;
	private boolean isfinished;
	private boolean isfinishedRefti;
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
	 * Return the value associated with the column: INO
	 */
	public java.lang.String getIno () {
		return ino;
	}

	/**
	 * Set the value related to the column: INO
	 * @param ino the INO value
	 */
	public void setIno (java.lang.String ino) {
		this.ino = ino;
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
	 * Return the value associated with the column: AERECORDNO
	 */
	public java.lang.String getAerecordno () {
		return aerecordno;
	}

	/**
	 * Set the value related to the column: AERECORDNO
	 * @param aerecordno the AERECORDNO value
	 */
	public void setAerecordno (java.lang.String aerecordno) {
		this.aerecordno = aerecordno;
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
	 * Return the value associated with the column: AEPRJNM
	 */
	public java.lang.String getAeprjnm () {
		return aeprjnm;
	}

	/**
	 * Set the value related to the column: AEPRJNM
	 * @param aeprjnm the AEPRJNM value
	 */
	public void setAeprjnm (java.lang.String aeprjnm) {
		this.aeprjnm = aeprjnm;
	}



	/**
	 * Return the value associated with the column: AEINDATE
	 */
	public java.util.Date getAeindate () {
		return aeindate;
	}

	/**
	 * Set the value related to the column: AEINDATE
	 * @param aeindate the AEINDATE value
	 */
	public void setAeindate (java.util.Date aeindate) {
		this.aeindate = aeindate;
	}



	/**
	 * Return the value associated with the column: AEOUTDATE
	 */
	public java.util.Date getAeoutdate () {
		return aeoutdate;
	}

	/**
	 * Set the value related to the column: AEOUTDATE
	 * @param aeoutdate the AEOUTDATE value
	 */
	public void setAeoutdate (java.util.Date aeoutdate) {
		this.aeoutdate = aeoutdate;
	}



	/**
	 * Return the value associated with the column: AEPEOPLES
	 */
	public java.lang.String getAepeoples () {
		return aepeoples;
	}

	/**
	 * Set the value related to the column: AEPEOPLES
	 * @param aepeoples the AEPEOPLES value
	 */
	public void setAepeoples (java.lang.String aepeoples) {
		this.aepeoples = aepeoples;
	}



	/**
	 * Return the value associated with the column: AEBASIS
	 */
	public java.lang.String getAebasis () {
		return aebasis;
	}

	/**
	 * Set the value related to the column: AEBASIS
	 * @param aebasis the AEBASIS value
	 */
	public void setAebasis (java.lang.String aebasis) {
		this.aebasis = aebasis;
	}



	/**
	 * Return the value associated with the column: ENQUIRERCD
	 */
	public java.lang.String getEnquirercd () {
		return enquirercd;
	}

	/**
	 * Set the value related to the column: ENQUIRERCD
	 * @param enquirercd the ENQUIRERCD value
	 */
	public void setEnquirercd (java.lang.String enquirercd) {
		this.enquirercd = enquirercd;
	}



	/**
	 * Return the value associated with the column: ACTUALRCDNO
	 */
	public java.lang.String getActualrcdno () {
		return actualrcdno;
	}

	/**
	 * Set the value related to the column: ACTUALRCDNO
	 * @param actualrcdno the ACTUALRCDNO value
	 */
	public void setActualrcdno (java.lang.String actualrcdno) {
		this.actualrcdno = actualrcdno;
	}



	/**
	 * Return the value associated with the column: ACTUALRCD
	 */
	public java.lang.String getActualrcd () {
		return actualrcd;
	}

	/**
	 * Set the value related to the column: ACTUALRCD
	 * @param actualrcd the ACTUALRCD value
	 */
	public void setActualrcd (java.lang.String actualrcd) {
		this.actualrcd = actualrcd;
	}



	/**
	 * Return the value associated with the column: INRCD
	 */
	public java.lang.String getInrcd () {
		return inrcd;
	}

	/**
	 * Set the value related to the column: INRCD
	 * @param inrcd the INRCD value
	 */
	public void setInrcd (java.lang.String inrcd) {
		this.inrcd = inrcd;
	}



	/**
	 * Return the value associated with the column: INRCDNO
	 */
	public java.lang.String getInrcdno () {
		return inrcdno;
	}

	/**
	 * Set the value related to the column: INRCDNO
	 * @param inrcdno the INRCDNO value
	 */
	public void setInrcdno (java.lang.String inrcdno) {
		this.inrcdno = inrcdno;
	}



	/**
	 * Return the value associated with the column: OUTRCD
	 */
	public java.lang.String getOutrcd () {
		return outrcd;
	}

	/**
	 * Set the value related to the column: OUTRCD
	 * @param outrcd the OUTRCD value
	 */
	public void setOutrcd (java.lang.String outrcd) {
		this.outrcd = outrcd;
	}



	/**
	 * Return the value associated with the column: OUTRCDNO
	 */
	public java.lang.String getOutrcdno () {
		return outrcdno;
	}

	/**
	 * Set the value related to the column: OUTRCDNO
	 * @param outrcdno the OUTRCDNO value
	 */
	public void setOutrcdno (java.lang.String outrcdno) {
		this.outrcdno = outrcdno;
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



	/**
	 * Return the value associated with the column: PROBSUMR
	 */
	public java.lang.String getProbsumr () {
		return probsumr;
	}

	/**
	 * Set the value related to the column: PROBSUMR
	 * @param probsumr the PROBSUMR value
	 */
	public void setProbsumr (java.lang.String probsumr) {
		this.probsumr = probsumr;
	}



	/**
	 * Return the value associated with the column: AEHEADMAN
	 */
	public java.lang.String getAeheadman () {
		return aeheadman;
	}

	/**
	 * Set the value related to the column: AEHEADMAN
	 * @param aeheadman the AEHEADMAN value
	 */
	public void setAeheadman (java.lang.String aeheadman) {
		this.aeheadman = aeheadman;
	}



	/**
	 * Return the value associated with the column: AEMASTER
	 */
	public java.lang.String getAemaster () {
		return aemaster;
	}

	/**
	 * Set the value related to the column: AEMASTER
	 * @param aemaster the AEMASTER value
	 */
	public void setAemaster (java.lang.String aemaster) {
		this.aemaster = aemaster;
	}



	/**
	 * Return the value associated with the column: AEREVIEWER
	 */
	public java.lang.String getAereviewer () {
		return aereviewer;
	}

	/**
	 * Set the value related to the column: AEREVIEWER
	 * @param aereviewer the AEREVIEWER value
	 */
	public void setAereviewer (java.lang.String aereviewer) {
		this.aereviewer = aereviewer;
	}



	/**
	 * Return the value associated with the column: AEOTHER
	 */
	public java.lang.String getAeother () {
		return aeother;
	}

	/**
	 * Set the value related to the column: AEOTHER
	 * @param aeother the AEOTHER value
	 */
	public void setAeother (java.lang.String aeother) {
		this.aeother = aeother;
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
	 * Return the value associated with the column: INNERNO
	 */
	public java.lang.String getInnerno () {
		return innerno;
	}

	/**
	 * Set the value related to the column: INNERNO
	 * @param innerno the INNERNO value
	 */
	public void setInnerno (java.lang.String innerno) {
		this.innerno = innerno;
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
	 * Return the value associated with the column: ISFINISHREFTI
	 */
	public boolean isIsfinishedRefti () {
		return isfinishedRefti;
	}

	/**
	 * Set the value related to the column: ISFINISHREFTI
	 * @param isfinishedRefti the ISFINISHREFTI value
	 */
	public void setIsfinishedRefti (boolean isfinishedRefti) {
		this.isfinishedRefti = isfinishedRefti;
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
		if (!(obj instanceof com.gtm.csims.model.BsAeinspection)) return false;
		else {
			com.gtm.csims.model.BsAeinspection bsAeinspection = (com.gtm.csims.model.BsAeinspection) obj;
			if (null == this.getId() || null == bsAeinspection.getId()) return false;
			else return (this.getId().equals(bsAeinspection.getId()));
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