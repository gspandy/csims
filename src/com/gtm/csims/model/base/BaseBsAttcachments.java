package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_TARGET table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="BS_ATTCACHMENTS"
 */

public abstract class BaseBsAttcachments implements Serializable {

	public static String REF = "BsAttcachments";
	public static String PROP_ID = "Id";
	public static String PROP_WFID = "Wfid";
	public static String PROP_ATTCHNAME = "Attchname";
	public static String PROP_ATTCHUPLOADER = "Attchuploader";
	public static String PROP_ATTCHPATH = "Attchpath";
	public static String PROP_ATTCHOFFLOW = "Attchofflow";
	public static String PROP_STATUS = "Status";
	public static String PROP_FLAG = "Flag";
	public static String PROP_CREATEDATE = "Createdate";
	public static String PROP_UPDATEDATE = "Updatedate";
	// constructors
	public BaseBsAttcachments() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBsAttcachments(java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String wfid;
	private java.lang.String attchname;
	private java.lang.String attchuploader;
	private java.lang.String attchpath;
	private java.lang.String attchofflow;
	private java.lang.String status;
	private java.lang.String flag;
	private java.util.Date createdate;
	private java.util.Date updatedate;
	
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

	public java.lang.String getWfid() {
		return wfid;
	}

	public void setWfid(java.lang.String wfid) {
		this.wfid = wfid;
	}

	public java.lang.String getAttchname() {
		return attchname;
	}

	public void setAttchname(java.lang.String attchname) {
		this.attchname = attchname;
	}

	public java.lang.String getAttchuploader() {
		return attchuploader;
	}

	public void setAttchuploader(java.lang.String attchuploader) {
		this.attchuploader = attchuploader;
	}

	public java.lang.String getAttchpath() {
		return attchpath;
	}

	public void setAttchpath(java.lang.String attchpath) {
		this.attchpath = attchpath;
	}

	public java.lang.String getAttchofflow() {
		return attchofflow;
	}

	public void setAttchofflow(java.lang.String attchofflow) {
		this.attchofflow = attchofflow;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getFlag() {
		return flag;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}
	
	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.gtm.csims.model.BsAttcachments))
			return false;
		else {
			com.gtm.csims.model.BsAttcachments bsAttcachments = (com.gtm.csims.model.BsAttcachments) obj;
			if (null == this.getId() || null == bsAttcachments.getId())
				return false;
			else
				return (this.getId().equals(bsAttcachments.getId()));
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