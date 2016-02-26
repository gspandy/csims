package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_WORKTALKSUMMARY table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_WORKTALKSUMMARY"
 */

public abstract class BaseBsWorktalksummary  implements Serializable {

	public static String REF = "BsWorktalksummary";
	public static String PROP_FILED7 = "Filed7";
	public static String PROP_UPDATEATE = "Updateate";
	public static String PROP_FILED6 = "Filed6";
	public static String PROP_AEORGNM = "Aeorgnm";
	public static String PROP_STAT = "Stat";
	public static String PROP_FILED10 = "Filed10";
	public static String PROP_AEORGNO = "Aeorgno";
	public static String PROP_FILED9 = "Filed9";
	public static String PROP_FILED13 = "Filed13";
	public static String PROP_FILED5 = "Filed5";
	public static String PROP_FILED16 = "Filed16";
	public static String PROP_FILED4 = "Filed4";
	public static String PROP_FLAG = "Flag";
	public static String PROP_FILED1 = "Filed1";
	public static String PROP_AEEDORGNO = "Aeedorgno";
	public static String PROP_FILED15 = "Filed15";
	public static String PROP_FILED14 = "Filed14";
	public static String PROP_FILED3 = "Filed3";
	public static String PROP_FILED17 = "Filed17";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_FILED2 = "Filed2";
	public static String PROP_FILED12 = "Filed12";
	public static String PROP_AEEDORGNM = "Aeedorgnm";
	public static String PROP_FILED11 = "Filed11";
	public static String PROP_FILED8 = "Filed8";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBsWorktalksummary () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsWorktalksummary (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String stat;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updateate;
	private java.lang.String filed1;
	private java.lang.String filed2;
	private java.lang.String filed3;
	private java.lang.String filed4;
	private java.lang.String filed5;
	private java.lang.String filed6;
	private java.lang.String filed7;
	private java.lang.String filed8;
	private java.lang.String filed9;
	private java.lang.String filed10;
	private java.lang.String filed11;
	private java.lang.String filed12;
	private java.lang.String filed13;
	private java.lang.String filed14;
	private java.lang.String filed15;
	private java.lang.String filed16;
	private java.lang.String filed17;
	private java.lang.String aeorgno;
	private java.lang.String aeorgnm;
	private java.lang.String aeedorgno;
	private java.lang.String aeedorgnm;



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
	 * Return the value associated with the column: FILED1
	 */
	public java.lang.String getFiled1 () {
		return filed1;
	}

	/**
	 * Set the value related to the column: FILED1
	 * @param filed1 the FILED1 value
	 */
	public void setFiled1 (java.lang.String filed1) {
		this.filed1 = filed1;
	}



	/**
	 * Return the value associated with the column: FILED2
	 */
	public java.lang.String getFiled2 () {
		return filed2;
	}

	/**
	 * Set the value related to the column: FILED2
	 * @param filed2 the FILED2 value
	 */
	public void setFiled2 (java.lang.String filed2) {
		this.filed2 = filed2;
	}



	/**
	 * Return the value associated with the column: FILED3
	 */
	public java.lang.String getFiled3 () {
		return filed3;
	}

	/**
	 * Set the value related to the column: FILED3
	 * @param filed3 the FILED3 value
	 */
	public void setFiled3 (java.lang.String filed3) {
		this.filed3 = filed3;
	}



	/**
	 * Return the value associated with the column: FILED4
	 */
	public java.lang.String getFiled4 () {
		return filed4;
	}

	/**
	 * Set the value related to the column: FILED4
	 * @param filed4 the FILED4 value
	 */
	public void setFiled4 (java.lang.String filed4) {
		this.filed4 = filed4;
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
	 * Return the value associated with the column: FILED13
	 */
	public java.lang.String getFiled13 () {
		return filed13;
	}

	/**
	 * Set the value related to the column: FILED13
	 * @param filed13 the FILED13 value
	 */
	public void setFiled13 (java.lang.String filed13) {
		this.filed13 = filed13;
	}



	/**
	 * Return the value associated with the column: FILED14
	 */
	public java.lang.String getFiled14 () {
		return filed14;
	}

	/**
	 * Set the value related to the column: FILED14
	 * @param filed14 the FILED14 value
	 */
	public void setFiled14 (java.lang.String filed14) {
		this.filed14 = filed14;
	}



	/**
	 * Return the value associated with the column: FILED15
	 */
	public java.lang.String getFiled15 () {
		return filed15;
	}

	/**
	 * Set the value related to the column: FILED15
	 * @param filed15 the FILED15 value
	 */
	public void setFiled15 (java.lang.String filed15) {
		this.filed15 = filed15;
	}



	/**
	 * Return the value associated with the column: FILED16
	 */
	public java.lang.String getFiled16 () {
		return filed16;
	}

	/**
	 * Set the value related to the column: FILED16
	 * @param filed16 the FILED16 value
	 */
	public void setFiled16 (java.lang.String filed16) {
		this.filed16 = filed16;
	}



	/**
	 * Return the value associated with the column: FILED17
	 */
	public java.lang.String getFiled17 () {
		return filed17;
	}

	/**
	 * Set the value related to the column: FILED17
	 * @param filed17 the FILED17 value
	 */
	public void setFiled17 (java.lang.String filed17) {
		this.filed17 = filed17;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsWorktalksummary)) return false;
		else {
			com.gtm.csims.model.BsWorktalksummary bsWorktalksummary = (com.gtm.csims.model.BsWorktalksummary) obj;
			if (null == this.getId() || null == bsWorktalksummary.getId()) return false;
			else return (this.getId().equals(bsWorktalksummary.getId()));
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