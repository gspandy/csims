package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_ADMENFORCE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_ADMENFORCE"
 */

public abstract class BaseBsAdmenforce  implements Serializable {

	public static String REF = "BsAdmenforce";
	public static String PROP_AEBASIS = "Aebasis";
	public static String PROP_CHAIRPEOPLEORG = "Chairpeopleorg";
	public static String PROP_AESTAT = "Aestat";
	public static String PROP_AEORGNM = "Aeorgnm";
	public static String PROP_STAT = "Stat";
	public static String PROP_AEORGNO = "Aeorgno";
	public static String PROP_REGORGNO = "Regorgno";
	public static String PROP_PRJNM = "Prjnm";
	public static String PROP_CHAIRPEOPLE = "Chairpeople";
	public static String PROP_AEWAY = "Aeway";
	public static String PROP_CANBEUPDATE = "Canbeupdate";
	public static String PROP_INNERNO = "Innerno";
	public static String PROP_FLAG = "Flag";
	public static String PROP_AEPLANTMDATE = "Aeplantmdate";
	public static String PROP_AESUMMARY = "Aesummary";
	public static String PROP_AECONTENT = "Aecontent";
	public static String PROP_MAXINO = "Maxino";
	public static String PROP_AENO = "Aeno";
	public static String PROP_AEEDORGNO = "Aeedorgno";
	public static String PROP_AEPLAN = "Aeplan";
	public static String PROP_CRTLOGIN = "Crtlogin";
	public static String PROP_REGDT = "Regdt";
	public static String PROP_DPTPEOPLE = "Dptpeople";
	public static String PROP_REGORGNM = "Regorgnm";
	public static String PROP_DPTPEOPLEORG = "Dptpeopleorg";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_AEPEOPLE = "Aepeople";
	public static String PROP_DEPTAUDITDATE = "Deptauditdate";
	public static String PROP_AELIMT = "Aelimt";
	public static String PROP_STEP = "Step";
	public static String PROP_ISFINISHED = "Isfinished";
	public static String PROP_DEPTMAN = "Deptman";
	public static String PROP_DPTPEOPLEORGNO = "Dptpeopleorgno";
	public static String PROP_CHAIRPEOPLEORGNO = "Chairpeopleorgno";
	public static String PROP_AEHEADMAN = "Aeheadman";
	public static String PROP_AEEDORGNM = "Aeedorgnm";
	public static String PROP_CHAIROPNION = "Chairopnion";
	public static String PROP_REGPEP = "Regpep";
	public static String PROP_AEOTHER = "Aeother";
	public static String PROP_AEMASTER = "Aemaster";
	public static String PROP_UPDATEDATE = "Updatedate";
	public static String PROP_ID = "Id";
	public static String PROP_CHAIRAUDITDATE = "Chairauditdate";
	public static String PROP_AEPLANSTDATE = "Aeplanstdate";
	public static String PROP_CHAIRMAN = "Chairman";
	public static String PROP_PRJBASIS = "Prjbasis";
	public static String PROP_DPTOPNION = "Dptopnion";


	// constructors
	public BaseBsAdmenforce () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAdmenforce (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBsAdmenforce (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno) {

		this.setId(id);
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
	private java.lang.String aeno;
	private java.lang.String aeorgnm;
	private java.lang.String aeorgno;
	private java.lang.String aeedorgnm;
	private java.lang.String aeedorgno;
	private java.util.Date aeplanstdate;
	private java.util.Date aeplantmdate;
	private java.lang.String prjnm;
	private java.lang.String prjbasis;
	private java.lang.String aebasis;
	private java.lang.String aecontent;
	private java.lang.String aelimt;
	private java.lang.String aeway;
	private java.lang.String aepeople;
	private java.lang.String aeplan;
	private java.lang.String dptopnion;
	private java.lang.String dptpeople;
	private java.lang.String dptpeopleorg;
	private java.lang.String dptpeopleorgno;
	private java.lang.String chairopnion;
	private java.lang.String chairpeople;
	private java.lang.String chairpeopleorg;
	private java.lang.String chairpeopleorgno;
	private java.lang.String aestat;
	private java.lang.String regpep;
	private java.lang.String regorgnm;
	private java.lang.String regorgno;
	private java.util.Date regdt;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updatedate;
	private java.lang.Integer maxino;
	private java.lang.String aesummary;
	private java.lang.String step;
	private java.lang.String aeheadman;
	private java.lang.String aeother;
	private java.lang.String aemaster;
	private java.lang.String chairman;
	private java.lang.String deptman;
	private java.util.Date deptauditdate;
	private java.util.Date chairauditdate;
	private java.lang.String crtlogin;
	private java.lang.String innerno;
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
	 * Return the value associated with the column: PRJNM
	 */
	public java.lang.String getPrjnm () {
		return prjnm;
	}

	/**
	 * Set the value related to the column: PRJNM
	 * @param prjnm the PRJNM value
	 */
	public void setPrjnm (java.lang.String prjnm) {
		this.prjnm = prjnm;
	}



	/**
	 * Return the value associated with the column: PRJBASIS
	 */
	public java.lang.String getPrjbasis () {
		return prjbasis;
	}

	/**
	 * Set the value related to the column: PRJBASIS
	 * @param prjbasis the PRJBASIS value
	 */
	public void setPrjbasis (java.lang.String prjbasis) {
		this.prjbasis = prjbasis;
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
	 * Return the value associated with the column: AECONTENT
	 */
	public java.lang.String getAecontent () {
		return aecontent;
	}

	/**
	 * Set the value related to the column: AECONTENT
	 * @param aecontent the AECONTENT value
	 */
	public void setAecontent (java.lang.String aecontent) {
		this.aecontent = aecontent;
	}



	/**
	 * Return the value associated with the column: AELIMT
	 */
	public java.lang.String getAelimt () {
		return aelimt;
	}

	/**
	 * Set the value related to the column: AELIMT
	 * @param aelimt the AELIMT value
	 */
	public void setAelimt (java.lang.String aelimt) {
		this.aelimt = aelimt;
	}



	/**
	 * Return the value associated with the column: AEWAY
	 */
	public java.lang.String getAeway () {
		return aeway;
	}

	/**
	 * Set the value related to the column: AEWAY
	 * @param aeway the AEWAY value
	 */
	public void setAeway (java.lang.String aeway) {
		this.aeway = aeway;
	}



	/**
	 * Return the value associated with the column: AEPEOPLE
	 */
	public java.lang.String getAepeople () {
		return aepeople;
	}

	/**
	 * Set the value related to the column: AEPEOPLE
	 * @param aepeople the AEPEOPLE value
	 */
	public void setAepeople (java.lang.String aepeople) {
		this.aepeople = aepeople;
	}



	/**
	 * Return the value associated with the column: AEPLAN
	 */
	public java.lang.String getAeplan () {
		return aeplan;
	}

	/**
	 * Set the value related to the column: AEPLAN
	 * @param aeplan the AEPLAN value
	 */
	public void setAeplan (java.lang.String aeplan) {
		this.aeplan = aeplan;
	}



	/**
	 * Return the value associated with the column: DPTOPNION
	 */
	public java.lang.String getDptopnion () {
		return dptopnion;
	}

	/**
	 * Set the value related to the column: DPTOPNION
	 * @param dptopnion the DPTOPNION value
	 */
	public void setDptopnion (java.lang.String dptopnion) {
		this.dptopnion = dptopnion;
	}



	/**
	 * Return the value associated with the column: DPTPEOPLE
	 */
	public java.lang.String getDptpeople () {
		return dptpeople;
	}

	/**
	 * Set the value related to the column: DPTPEOPLE
	 * @param dptpeople the DPTPEOPLE value
	 */
	public void setDptpeople (java.lang.String dptpeople) {
		this.dptpeople = dptpeople;
	}



	/**
	 * Return the value associated with the column: DPTPEOPLEORG
	 */
	public java.lang.String getDptpeopleorg () {
		return dptpeopleorg;
	}

	/**
	 * Set the value related to the column: DPTPEOPLEORG
	 * @param dptpeopleorg the DPTPEOPLEORG value
	 */
	public void setDptpeopleorg (java.lang.String dptpeopleorg) {
		this.dptpeopleorg = dptpeopleorg;
	}



	/**
	 * Return the value associated with the column: DPTPEOPLEORGNO
	 */
	public java.lang.String getDptpeopleorgno () {
		return dptpeopleorgno;
	}

	/**
	 * Set the value related to the column: DPTPEOPLEORGNO
	 * @param dptpeopleorgno the DPTPEOPLEORGNO value
	 */
	public void setDptpeopleorgno (java.lang.String dptpeopleorgno) {
		this.dptpeopleorgno = dptpeopleorgno;
	}



	/**
	 * Return the value associated with the column: CHAIROPNION
	 */
	public java.lang.String getChairopnion () {
		return chairopnion;
	}

	/**
	 * Set the value related to the column: CHAIROPNION
	 * @param chairopnion the CHAIROPNION value
	 */
	public void setChairopnion (java.lang.String chairopnion) {
		this.chairopnion = chairopnion;
	}



	/**
	 * Return the value associated with the column: CHAIRPEOPLE
	 */
	public java.lang.String getChairpeople () {
		return chairpeople;
	}

	/**
	 * Set the value related to the column: CHAIRPEOPLE
	 * @param chairpeople the CHAIRPEOPLE value
	 */
	public void setChairpeople (java.lang.String chairpeople) {
		this.chairpeople = chairpeople;
	}



	/**
	 * Return the value associated with the column: CHAIRPEOPLEORG
	 */
	public java.lang.String getChairpeopleorg () {
		return chairpeopleorg;
	}

	/**
	 * Set the value related to the column: CHAIRPEOPLEORG
	 * @param chairpeopleorg the CHAIRPEOPLEORG value
	 */
	public void setChairpeopleorg (java.lang.String chairpeopleorg) {
		this.chairpeopleorg = chairpeopleorg;
	}



	/**
	 * Return the value associated with the column: CHAIRPEOPLEORGNO
	 */
	public java.lang.String getChairpeopleorgno () {
		return chairpeopleorgno;
	}

	/**
	 * Set the value related to the column: CHAIRPEOPLEORGNO
	 * @param chairpeopleorgno the CHAIRPEOPLEORGNO value
	 */
	public void setChairpeopleorgno (java.lang.String chairpeopleorgno) {
		this.chairpeopleorgno = chairpeopleorgno;
	}



	/**
	 * Return the value associated with the column: AESTAT
	 */
	public java.lang.String getAestat () {
		return aestat;
	}

	/**
	 * Set the value related to the column: AESTAT
	 * @param aestat the AESTAT value
	 */
	public void setAestat (java.lang.String aestat) {
		this.aestat = aestat;
	}



	/**
	 * Return the value associated with the column: REGPEP
	 */
	public java.lang.String getRegpep () {
		return regpep;
	}

	/**
	 * Set the value related to the column: REGPEP
	 * @param regpep the REGPEP value
	 */
	public void setRegpep (java.lang.String regpep) {
		this.regpep = regpep;
	}



	/**
	 * Return the value associated with the column: REGORGNM
	 */
	public java.lang.String getRegorgnm () {
		return regorgnm;
	}

	/**
	 * Set the value related to the column: REGORGNM
	 * @param regorgnm the REGORGNM value
	 */
	public void setRegorgnm (java.lang.String regorgnm) {
		this.regorgnm = regorgnm;
	}



	/**
	 * Return the value associated with the column: REGORGNO
	 */
	public java.lang.String getRegorgno () {
		return regorgno;
	}

	/**
	 * Set the value related to the column: REGORGNO
	 * @param regorgno the REGORGNO value
	 */
	public void setRegorgno (java.lang.String regorgno) {
		this.regorgno = regorgno;
	}



	/**
	 * Return the value associated with the column: REGDT
	 */
	public java.util.Date getRegdt () {
		return regdt;
	}

	/**
	 * Set the value related to the column: REGDT
	 * @param regdt the REGDT value
	 */
	public void setRegdt (java.util.Date regdt) {
		this.regdt = regdt;
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
	 * Return the value associated with the column: UPDATEDATE
	 */
	public java.util.Date getUpdatedate () {
		return updatedate;
	}

	/**
	 * Set the value related to the column: UPDATEDATE
	 * @param updatedate the UPDATEDATE value
	 */
	public void setUpdatedate (java.util.Date updatedate) {
		this.updatedate = updatedate;
	}



	/**
	 * Return the value associated with the column: MAXINO
	 */
	public java.lang.Integer getMaxino () {
		return maxino;
	}

	/**
	 * Set the value related to the column: MAXINO
	 * @param maxino the MAXINO value
	 */
	public void setMaxino (java.lang.Integer maxino) {
		this.maxino = maxino;
	}



	/**
	 * Return the value associated with the column: AESUMMARY
	 */
	public java.lang.String getAesummary () {
		return aesummary;
	}

	/**
	 * Set the value related to the column: AESUMMARY
	 * @param aesummary the AESUMMARY value
	 */
	public void setAesummary (java.lang.String aesummary) {
		this.aesummary = aesummary;
	}



	/**
	 * Return the value associated with the column: STEP
	 */
	public java.lang.String getStep () {
		return step;
	}

	/**
	 * Set the value related to the column: STEP
	 * @param step the STEP value
	 */
	public void setStep (java.lang.String step) {
		this.step = step;
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
	 * Return the value associated with the column: CHAIRMAN
	 */
	public java.lang.String getChairman () {
		return chairman;
	}

	/**
	 * Set the value related to the column: CHAIRMAN
	 * @param chairman the CHAIRMAN value
	 */
	public void setChairman (java.lang.String chairman) {
		this.chairman = chairman;
	}



	/**
	 * Return the value associated with the column: DEPTMAN
	 */
	public java.lang.String getDeptman () {
		return deptman;
	}

	/**
	 * Set the value related to the column: DEPTMAN
	 * @param deptman the DEPTMAN value
	 */
	public void setDeptman (java.lang.String deptman) {
		this.deptman = deptman;
	}



	/**
	 * Return the value associated with the column: DEPTAUDITDATE
	 */
	public java.util.Date getDeptauditdate () {
		return deptauditdate;
	}

	/**
	 * Set the value related to the column: DEPTAUDITDATE
	 * @param deptauditdate the DEPTAUDITDATE value
	 */
	public void setDeptauditdate (java.util.Date deptauditdate) {
		this.deptauditdate = deptauditdate;
	}



	/**
	 * Return the value associated with the column: CHAIRAUDITDATE
	 */
	public java.util.Date getChairauditdate () {
		return chairauditdate;
	}

	/**
	 * Set the value related to the column: CHAIRAUDITDATE
	 * @param chairauditdate the CHAIRAUDITDATE value
	 */
	public void setChairauditdate (java.util.Date chairauditdate) {
		this.chairauditdate = chairauditdate;
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
		if (!(obj instanceof com.gtm.csims.model.BsAdmenforce)) return false;
		else {
			com.gtm.csims.model.BsAdmenforce bsAdmenforce = (com.gtm.csims.model.BsAdmenforce) obj;
			if (null == this.getId() || null == bsAdmenforce.getId()) return false;
			else return (this.getId().equals(bsAdmenforce.getId()));
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