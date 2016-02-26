package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_ADMPUNISH table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_ADMPUNISH"
 */

public abstract class BaseBsAdmpunish  implements Serializable {

	public static String REF = "BsAdmpunish";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_ILLEGALUNIT = "Illegalunit";
	public static String PROP_AEORGNM = "Aeorgnm";
	public static String PROP_AEEDORGZZNM = "Aeedorgzznm";
	public static String PROP_AEEDORGXZHNO = "Aeedorgxzhno";
	public static String PROP_STAT = "Stat";
	public static String PROP_SUMRYATTA = "Sumryatta";
	public static String PROP_ILLEGALPEOPLE = "Illegalpeople";
	public static String PROP_PEOPLERDATE = "Peoplerdate";
	public static String PROP_PEOPLER = "Peopler";
	public static String PROP_AEORGNO = "Aeorgno";
	public static String PROP_CREATOR = "Creator";
	public static String PROP_CANBEUPDATE = "Canbeupdate";
	public static String PROP_AEEDORGZZNO = "Aeedorgzzno";
	public static String PROP_FLAG = "Flag";
	public static String PROP_AEPLANTMDATE = "Aeplantmdate";
	public static String PROP_PEOPLEOPNION = "Peopleopnion";
	public static String PROP_AENO = "Aeno";
	public static String PROP_AEEDORGNO = "Aeedorgno";
	public static String PROP_CRTLOGIN = "Crtlogin";
	public static String PROP_CREATORORGNO = "Creatororgno";
	public static String PROP_CREATORORG = "Creatororg";
	public static String PROP_DPTER = "Dpter";
	public static String PROP_SUMMARYS = "Summarys";
	public static String PROP_DPTERDATE = "Dpterdate";
	public static String PROP_AEOPNIONNO = "Aeopnionno";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_PUNISHNO = "Punishno";
	public static String PROP_ISFINISHED = "Isfinished";
	public static String PROP_AEEDORGNM = "Aeedorgnm";
	public static String PROP_CHAIROPNION = "Chairopnion";
	public static String PROP_AEEDORGXZHNM = "Aeedorgxzhnm";
	public static String PROP_CHAIRERDATE = "Chairerdate";
	public static String PROP_CRTDATE = "Crtdate";
	public static String PROP_CHAIRER = "Chairer";
	public static String PROP_ID = "Id";
	public static String PROP_REASON = "Reason";
	public static String PROP_AEPLANSTDATE = "Aeplanstdate";
	public static String PROP_DPTOPNION = "Dptopnion";


	// constructors
	public BaseBsAdmpunish () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAdmpunish (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBsAdmpunish (
		java.lang.String id,
		java.lang.String aeno,
		java.lang.String aeorgnm,
		java.lang.String aeorgno,
		java.lang.String aeedorgnm,
		java.lang.String aeedorgno,
		java.lang.String punishno) {

		this.setId(id);
		this.setAeno(aeno);
		this.setAeorgnm(aeorgnm);
		this.setAeorgno(aeorgno);
		this.setAeedorgnm(aeedorgnm);
		this.setAeedorgno(aeedorgno);
		this.setPunishno(punishno);
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
	private java.lang.String punishno;
	private java.lang.String illegalunit;
	private java.lang.String illegalpeople;
	private java.lang.String reason;
	private java.lang.String summarys;
	private java.lang.String sumryatta;
	private java.lang.String dptopnion;
	private java.lang.String peopleopnion;
	private java.lang.String chairopnion;
	private java.lang.String dpter;
	private java.lang.String chairer;
	private java.lang.String peopler;
	private java.util.Date dpterdate;
	private java.util.Date chairerdate;
	private java.util.Date peoplerdate;
	private java.lang.String creator;
	private java.lang.String creatororg;
	private java.lang.String creatororgno;
	private java.util.Date crtdate;
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updateate;
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
	 * Return the value associated with the column: PUNISHNO
	 */
	public java.lang.String getPunishno () {
		return punishno;
	}

	/**
	 * Set the value related to the column: PUNISHNO
	 * @param punishno the PUNISHNO value
	 */
	public void setPunishno (java.lang.String punishno) {
		this.punishno = punishno;
	}



	/**
	 * Return the value associated with the column: ILLEGALUNIT
	 */
	public java.lang.String getIllegalunit () {
		return illegalunit;
	}

	/**
	 * Set the value related to the column: ILLEGALUNIT
	 * @param illegalunit the ILLEGALUNIT value
	 */
	public void setIllegalunit (java.lang.String illegalunit) {
		this.illegalunit = illegalunit;
	}



	/**
	 * Return the value associated with the column: ILLEGALPEOPLE
	 */
	public java.lang.String getIllegalpeople () {
		return illegalpeople;
	}

	/**
	 * Set the value related to the column: ILLEGALPEOPLE
	 * @param illegalpeople the ILLEGALPEOPLE value
	 */
	public void setIllegalpeople (java.lang.String illegalpeople) {
		this.illegalpeople = illegalpeople;
	}



	/**
	 * Return the value associated with the column: REASON
	 */
	public java.lang.String getReason () {
		return reason;
	}

	/**
	 * Set the value related to the column: REASON
	 * @param reason the REASON value
	 */
	public void setReason (java.lang.String reason) {
		this.reason = reason;
	}



	/**
	 * Return the value associated with the column: SUMMARYS
	 */
	public java.lang.String getSummarys () {
		return summarys;
	}

	/**
	 * Set the value related to the column: SUMMARYS
	 * @param summarys the SUMMARYS value
	 */
	public void setSummarys (java.lang.String summarys) {
		this.summarys = summarys;
	}



	/**
	 * Return the value associated with the column: SUMRYATTA
	 */
	public java.lang.String getSumryatta () {
		return sumryatta;
	}

	/**
	 * Set the value related to the column: SUMRYATTA
	 * @param sumryatta the SUMRYATTA value
	 */
	public void setSumryatta (java.lang.String sumryatta) {
		this.sumryatta = sumryatta;
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
	 * Return the value associated with the column: PEOPLEOPNION
	 */
	public java.lang.String getPeopleopnion () {
		return peopleopnion;
	}

	/**
	 * Set the value related to the column: PEOPLEOPNION
	 * @param peopleopnion the PEOPLEOPNION value
	 */
	public void setPeopleopnion (java.lang.String peopleopnion) {
		this.peopleopnion = peopleopnion;
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
	 * Return the value associated with the column: DPTER
	 */
	public java.lang.String getDpter () {
		return dpter;
	}

	/**
	 * Set the value related to the column: DPTER
	 * @param dpter the DPTER value
	 */
	public void setDpter (java.lang.String dpter) {
		this.dpter = dpter;
	}



	/**
	 * Return the value associated with the column: CHAIRER
	 */
	public java.lang.String getChairer () {
		return chairer;
	}

	/**
	 * Set the value related to the column: CHAIRER
	 * @param chairer the CHAIRER value
	 */
	public void setChairer (java.lang.String chairer) {
		this.chairer = chairer;
	}



	/**
	 * Return the value associated with the column: PEOPLER
	 */
	public java.lang.String getPeopler () {
		return peopler;
	}

	/**
	 * Set the value related to the column: PEOPLER
	 * @param peopler the PEOPLER value
	 */
	public void setPeopler (java.lang.String peopler) {
		this.peopler = peopler;
	}



	/**
	 * Return the value associated with the column: DPTERDATE
	 */
	public java.util.Date getDpterdate () {
		return dpterdate;
	}

	/**
	 * Set the value related to the column: DPTERDATE
	 * @param dpterdate the DPTERDATE value
	 */
	public void setDpterdate (java.util.Date dpterdate) {
		this.dpterdate = dpterdate;
	}



	/**
	 * Return the value associated with the column: CHAIRERDATE
	 */
	public java.util.Date getChairerdate () {
		return chairerdate;
	}

	/**
	 * Set the value related to the column: CHAIRERDATE
	 * @param chairerdate the CHAIRERDATE value
	 */
	public void setChairerdate (java.util.Date chairerdate) {
		this.chairerdate = chairerdate;
	}



	/**
	 * Return the value associated with the column: PEOPLERDATE
	 */
	public java.util.Date getPeoplerdate () {
		return peoplerdate;
	}

	/**
	 * Set the value related to the column: PEOPLERDATE
	 * @param peoplerdate the PEOPLERDATE value
	 */
	public void setPeoplerdate (java.util.Date peoplerdate) {
		this.peoplerdate = peoplerdate;
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
		if (!(obj instanceof com.gtm.csims.model.BsAdmpunish)) return false;
		else {
			com.gtm.csims.model.BsAdmpunish bsAdmpunish = (com.gtm.csims.model.BsAdmpunish) obj;
			if (null == this.getId() || null == bsAdmpunish.getId()) return false;
			else return (this.getId().equals(bsAdmpunish.getId()));
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