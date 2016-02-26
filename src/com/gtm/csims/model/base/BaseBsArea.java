package com.gtm.csims.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BS_AREA table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BS_AREA"
 */

public abstract class BaseBsArea  implements Serializable {

	public static String REF = "BsArea";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";
	public static String PROP_PARENT = "Parent";
	public static String PROP_UPDATEDATE = "Updatedate";
	public static String PROP_ISLEAF = "Isleaf";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";
	public static String PROP_LEVEL = "Level";
	public static String PROP_SHORTNAME = "Shortname";


	

	// constructors
	public BaseBsArea () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsArea (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String level;
	private java.lang.Boolean isleaf;
	private java.lang.String status;
	private java.lang.String flag;
	private java.lang.String shortname;
	private java.util.Date createdate;
	private java.util.Date updatedate;

	// many to one
	private com.gtm.csims.model.BsArea parent;

	// collections
	private java.util.Set<com.gtm.csims.model.BsArea> bsAreas;



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
	 * Return the value associated with the column: NAME
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: NAME
	 * @param name the NAME value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: LEVEL
	 */
	public java.lang.String getLevel () {
		return level;
	}

	/**
	 * Set the value related to the column: LEVEL
	 * @param level the LEVEL value
	 */
	public void setLevel (java.lang.String level) {
		this.level = level;
	}



	/**
	 * Return the value associated with the column: ISLEAF
	 */
	public java.lang.Boolean getIsleaf () {
		return isleaf;
	}

	/**
	 * Set the value related to the column: ISLEAF
	 * @param isleaf the ISLEAF value
	 */
	public void setIsleaf (java.lang.Boolean isleaf) {
		this.isleaf = isleaf;
	}



	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.String getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (java.lang.String status) {
		this.status = status;
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
	 * Return the value associated with the column: PARENT
	 */
	public com.gtm.csims.model.BsArea getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: PARENT
	 * @param parent the PARENT value
	 */
	public void setParent (com.gtm.csims.model.BsArea parent) {
		this.parent = parent;
	}



	/**
	 * Return the value associated with the column: BsAreas
	 */
	public java.util.Set<com.gtm.csims.model.BsArea> getBsAreas () {
		return bsAreas;
	}

	/**
	 * Set the value related to the column: BsAreas
	 * @param bsAreas the BsAreas value
	 */
	public void setBsAreas (java.util.Set<com.gtm.csims.model.BsArea> bsAreas) {
		this.bsAreas = bsAreas;
	}

	public void addToBsAreas (com.gtm.csims.model.BsArea bsArea) {
		if (null == getBsAreas()) setBsAreas(new java.util.TreeSet<com.gtm.csims.model.BsArea>());
		getBsAreas().add(bsArea);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.gtm.csims.model.BsArea)) return false;
		else {
			com.gtm.csims.model.BsArea bsArea = (com.gtm.csims.model.BsArea) obj;
			if (null == this.getId() || null == bsArea.getId()) return false;
			else return (this.getId().equals(bsArea.getId()));
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

	public java.lang.String getShortname() {
		return shortname;
	}

	public void setShortname(java.lang.String shortname) {
		this.shortname = shortname;
	}


}