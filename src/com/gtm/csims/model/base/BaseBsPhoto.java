package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_TARGET table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="BS_ATTCACHMENTS"
 */

public abstract class BaseBsPhoto implements Serializable {

	public static String REF = "BsPhoto";
	public static String PROP_ID = "Id";
	public static String PROP_ATTCHPATH = "Photopath";
	// constructors
	public BaseBsPhoto() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsPhoto(java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String photopath;
	
	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="org.hibernate.id.UUIDHexGenerator"
	 *               column="ID"
	 */

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(java.lang.String photopath) {
		this.photopath = photopath;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.gtm.csims.model.BsPhoto))
			return false;
		else {
			com.gtm.csims.model.BsPhoto bs = (com.gtm.csims.model.BsPhoto) obj;
			if (null == this.getId() || null == bs.getId())
				return false;
			else
				return (this.getId().equals(bs.getId()));
		}
	}

	@Override
	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}