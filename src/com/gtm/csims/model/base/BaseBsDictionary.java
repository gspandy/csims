package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_DICTIONARY table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="BS_DICTIONARY"
 */

public abstract class BaseBsDictionary implements Serializable {

    public static String REF = "BsDictionary";
    public static String PROP_TYPE = "Type";
    public static String PROP_UPDATEATE = "Updateate";
    public static String PROP_DKEY = "Dkey";
    public static String PROP_FLAG = "Flag";
    public static String PROP_D_SUMRY = "DSumry";
    public static String PROP_STAT = "Stat";
    public static String PROP_ISENABLE = "Isenable";
    public static String PROP_DVALUE = "Dvalue";
    public static String PROP_CREATEDATE = "Createdate";
    public static String PROP_ID = "Id";

    // constructors
    public BaseBsDictionary() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseBsDictionary(java.lang.String id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }

    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.String id;

    // fields
    private java.lang.String type;
    private java.lang.String dkey;
    private java.lang.String dvalue;
    private java.lang.String dSumry;
    private java.lang.String stat;
    private java.lang.String flag;
    private java.lang.String isenable;
    private java.util.Date createdate;
    private java.util.Date updateate;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="org.hibernate.id.UUIDHexGenerator"
     *               column="ID"
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param id
     *            the new ID
     */
    public void setId(java.lang.String id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }

    /**
     * Return the value associated with the column: TYPE
     */
    public java.lang.String getType() {
        return type;
    }

    /**
     * Set the value related to the column: TYPE
     * 
     * @param type
     *            the TYPE value
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    /**
     * Return the value associated with the column: DKEY
     */
    public java.lang.String getDkey() {
        return dkey;
    }

    /**
     * Set the value related to the column: DKEY
     * 
     * @param dkey
     *            the DKEY value
     */
    public void setDkey(java.lang.String dkey) {
        this.dkey = dkey;
    }

    /**
     * Return the value associated with the column: DVALUE
     */
    public java.lang.String getDvalue() {
        return dvalue;
    }

    /**
     * Set the value related to the column: DVALUE
     * 
     * @param dvalue
     *            the DVALUE value
     */
    public void setDvalue(java.lang.String dvalue) {
        this.dvalue = dvalue;
    }

    /**
     * Return the value associated with the column: DSumry
     */
    public java.lang.String getDSumry() {
        return dSumry;
    }

    /**
     * Set the value related to the column: DSumry
     * 
     * @param dSumry
     *            the DSumry value
     */
    public void setDSumry(java.lang.String dSumry) {
        this.dSumry = dSumry;
    }

    /**
     * Return the value associated with the column: STAT
     */
    public java.lang.String getStat() {
        return stat;
    }

    /**
     * Set the value related to the column: STAT
     * 
     * @param stat
     *            the STAT value
     */
    public void setStat(java.lang.String stat) {
        this.stat = stat;
    }

    /**
     * Return the value associated with the column: FLAG
     */
    public java.lang.String getFlag() {
        return flag;
    }

    /**
     * Set the value related to the column: FLAG
     * 
     * @param flag
     *            the FLAG value
     */
    public void setFlag(java.lang.String flag) {
        this.flag = flag;
    }

    /**
     * Return the value associated with the column: CREATEDATE
     */
    public java.util.Date getCreatedate() {
        return createdate;
    }

    /**
     * Set the value related to the column: CREATEDATE
     * 
     * @param createdate
     *            the CREATEDATE value
     */
    public void setCreatedate(java.util.Date createdate) {
        this.createdate = createdate;
    }

    /**
     * Return the value associated with the column: UPDATEATE
     */
    public java.util.Date getUpdateate() {
        return updateate;
    }

    /**
     * Set the value related to the column: UPDATEATE
     * 
     * @param updateate
     *            the UPDATEATE value
     */
    public void setUpdateate(java.util.Date updateate) {
        this.updateate = updateate;
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof com.gtm.csims.model.BsDictionary))
            return false;
        else {
            com.gtm.csims.model.BsDictionary bsDictionary = (com.gtm.csims.model.BsDictionary) obj;
            if (null == this.getId() || null == bsDictionary.getId())
                return false;
            else
                return (this.getId().equals(bsDictionary.getId()));
        }
    }

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

    public String toString() {
        return super.toString();
    }

    public java.lang.String getIsenable() {
        return isenable;
    }

    public void setIsenable(java.lang.String isenable) {
        this.isenable = isenable;
    }

}