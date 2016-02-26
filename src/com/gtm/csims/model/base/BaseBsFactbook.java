package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_FACTBOOK table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_FACTBOOK"
 */

public abstract class BaseBsFactbook  implements Serializable {

	public static String REF = "BsFactbook";
	public static String PROP_FILED7 = "Filed7";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_FILED6 = "Filed6";
	public static String PROP_AEORGNM = "Aeorgnm";
	public static String PROP_STAT = "Stat";
	public static String PROP_FILED10 = "Filed10";
	public static String PROP_PCBNO = "Pcbno";
	public static String PROP_AEORGNO = "Aeorgno";
	public static String PROP_FILED9 = "Filed9";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_FILED5 = "Filed5";
	public static String PROP_SELECTEDWTGK = "Selectedwtgk";
	public static String PROP_FILED12 = "Filed12";
	public static String PROP_FLAG = "Flag";
	public static String PROP_AEEDORGNM = "Aeedorgnm";
	public static String PROP_FILED11 = "Filed11";
	public static String PROP_ORGTPNM = "Orgtpnm";
	public static String PROP_PCBNM = "Pcbnm";
	public static String PROP_AENO = "Aeno";
	public static String PROP_AEEDORGNO = "Aeedorgno";
	public static String PROP_ORGTPNO = "Orgtpno";
	public static String PROP_FILED8 = "Filed8";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsFactbook () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsFactbook (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String aeedorgnm;
	private java.lang.String aeedorgno;
	private java.lang.String aeno;
	private java.lang.String aeorgnm;
	private java.lang.String aeorgno;
	private java.util.Date createdate;
	private java.lang.String filed10;
	private java.lang.String filed11;
	private java.lang.String filed12;
	private java.lang.String filed5;
	private java.lang.String filed6;
	private java.lang.String filed7;
	private java.lang.String filed8;
	private java.lang.String filed9;
	private java.lang.String flag;
	private java.lang.String orgtpnm;
	private java.lang.String orgtpno;
	private java.lang.String pcbnm;
	private java.lang.String pcbno;
	private java.lang.String selectedwtgk;
	private java.lang.String stat;
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
	 * Return the value associated with the column: aeedorgnm
	 */
	public java.lang.String getAeedorgnm () {
		return aeedorgnm;
	}

	/**
	 * Set the value related to the column: aeedorgnm
	 * @param aeedorgnm the aeedorgnm value
	 */
	public void setAeedorgnm (java.lang.String aeedorgnm) {
		this.aeedorgnm = aeedorgnm;
	}



	/**
	 * Return the value associated with the column: aeedorgno
	 */
	public java.lang.String getAeedorgno () {
		return aeedorgno;
	}

	/**
	 * Set the value related to the column: aeedorgno
	 * @param aeedorgno the aeedorgno value
	 */
	public void setAeedorgno (java.lang.String aeedorgno) {
		this.aeedorgno = aeedorgno;
	}



	/**
	 * Return the value associated with the column: aeno
	 */
	public java.lang.String getAeno () {
		return aeno;
	}

	/**
	 * Set the value related to the column: aeno
	 * @param aeno the aeno value
	 */
	public void setAeno (java.lang.String aeno) {
		this.aeno = aeno;
	}



	/**
	 * Return the value associated with the column: aeorgnm
	 */
	public java.lang.String getAeorgnm () {
		return aeorgnm;
	}

	/**
	 * Set the value related to the column: aeorgnm
	 * @param aeorgnm the aeorgnm value
	 */
	public void setAeorgnm (java.lang.String aeorgnm) {
		this.aeorgnm = aeorgnm;
	}



	/**
	 * Return the value associated with the column: aeorgno
	 */
	public java.lang.String getAeorgno () {
		return aeorgno;
	}

	/**
	 * Set the value related to the column: aeorgno
	 * @param aeorgno the aeorgno value
	 */
	public void setAeorgno (java.lang.String aeorgno) {
		this.aeorgno = aeorgno;
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
	 * Return the value associated with the column: FILED10
	 */
	public java.lang.String getFiled10 () {
		return filed10;
	}

	/**
	 * Set the value related to the column: FILED10
	 * @param filed10 the FILED10 value
	 */
	public void setFiled10 (java.lang.String filed10) {
		this.filed10 = filed10;
	}



	/**
	 * Return the value associated with the column: FILED11
	 */
	public java.lang.String getFiled11 () {
		return filed11;
	}

	/**
	 * Set the value related to the column: FILED11
	 * @param filed11 the FILED11 value
	 */
	public void setFiled11 (java.lang.String filed11) {
		this.filed11 = filed11;
	}



	/**
	 * Return the value associated with the column: FILED12
	 */
	public java.lang.String getFiled12 () {
		return filed12;
	}

	/**
	 * Set the value related to the column: FILED12
	 * @param filed12 the FILED12 value
	 */
	public void setFiled12 (java.lang.String filed12) {
		this.filed12 = filed12;
	}



	/**
	 * Return the value associated with the column: FILED5
	 */
	public java.lang.String getFiled5 () {
		return filed5;
	}

	/**
	 * Set the value related to the column: FILED5
	 * @param filed5 the FILED5 value
	 */
	public void setFiled5 (java.lang.String filed5) {
		this.filed5 = filed5;
	}



	/**
	 * Return the value associated with the column: FILED6
	 */
	public java.lang.String getFiled6 () {
		return filed6;
	}

	/**
	 * Set the value related to the column: FILED6
	 * @param filed6 the FILED6 value
	 */
	public void setFiled6 (java.lang.String filed6) {
		this.filed6 = filed6;
	}



	/**
	 * Return the value associated with the column: FILED7
	 */
	public java.lang.String getFiled7 () {
		return filed7;
	}

	/**
	 * Set the value related to the column: FILED7
	 * @param filed7 the FILED7 value
	 */
	public void setFiled7 (java.lang.String filed7) {
		this.filed7 = filed7;
	}



	/**
	 * Return the value associated with the column: FILED8
	 */
	public java.lang.String getFiled8 () {
		return filed8;
	}

	/**
	 * Set the value related to the column: FILED8
	 * @param filed8 the FILED8 value
	 */
	public void setFiled8 (java.lang.String filed8) {
		this.filed8 = filed8;
	}



	/**
	 * Return the value associated with the column: FILED9
	 */
	public java.lang.String getFiled9 () {
		return filed9;
	}

	/**
	 * Set the value related to the column: FILED9
	 * @param filed9 the FILED9 value
	 */
	public void setFiled9 (java.lang.String filed9) {
		this.filed9 = filed9;
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
	 * Return the value associated with the column: orgtpnm
	 */
	public java.lang.String getOrgtpnm () {
		return orgtpnm;
	}

	/**
	 * Set the value related to the column: orgtpnm
	 * @param orgtpnm the orgtpnm value
	 */
	public void setOrgtpnm (java.lang.String orgtpnm) {
		this.orgtpnm = orgtpnm;
	}



	/**
	 * Return the value associated with the column: orgtpno
	 */
	public java.lang.String getOrgtpno () {
		return orgtpno;
	}

	/**
	 * Set the value related to the column: orgtpno
	 * @param orgtpno the orgtpno value
	 */
	public void setOrgtpno (java.lang.String orgtpno) {
		this.orgtpno = orgtpno;
	}



	/**
	 * Return the value associated with the column: pcbnm
	 */
	public java.lang.String getPcbnm () {
		return pcbnm;
	}

	/**
	 * Set the value related to the column: pcbnm
	 * @param pcbnm the pcbnm value
	 */
	public void setPcbnm (java.lang.String pcbnm) {
		this.pcbnm = pcbnm;
	}



	/**
	 * Return the value associated with the column: pcbno
	 */
	public java.lang.String getPcbno () {
		return pcbno;
	}

	/**
	 * Set the value related to the column: pcbno
	 * @param pcbno the pcbno value
	 */
	public void setPcbno (java.lang.String pcbno) {
		this.pcbno = pcbno;
	}



	/**
	 * Return the value associated with the column: selectedwtgk
	 */
	public java.lang.String getSelectedwtgk () {
		return selectedwtgk;
	}

	/**
	 * Set the value related to the column: selectedwtgk
	 * @param selectedwtgk the selectedwtgk value
	 */
	public void setSelectedwtgk (java.lang.String selectedwtgk) {
		this.selectedwtgk = selectedwtgk;
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
		if (!(obj instanceof com.gtm.csims.model.BsFactbook)) return false;
		else {
			com.gtm.csims.model.BsFactbook bsFactbook = (com.gtm.csims.model.BsFactbook) obj;
			if (null == this.getId() || null == bsFactbook.getId()) return false;
			else return (this.getId().equals(bsFactbook.getId()));
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