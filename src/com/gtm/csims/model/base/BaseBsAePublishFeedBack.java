package com.gtm.csims.model.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the BS_AECONCLUSION table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 *
 * @hibernate.class table="BS_AECONCLUSION"
 */

public abstract class BaseBsAePublishFeedBack implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6564691171759353140L;

    // constructors
    public BaseBsAePublishFeedBack() {
    }

    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private java.lang.String id;

    // fields
    private java.lang.String aeno;
    private java.lang.String aeorgnm;
    private java.lang.String aeorgno;
    private java.lang.String aeedorgnm;
    private java.lang.String aeedorgno;

    private java.lang.String topLevelBankNm;
    private java.lang.String topLevelBankNo;
    private java.lang.String directParentlBankNm;
    private java.lang.String directParentlBankNo;
    private java.lang.String feedbackOpnion;
    private java.util.Date feedbackDate;

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
    private java.lang.String aeedOrgBankType;

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getAeno() {
        return aeno;
    }

    public void setAeno(java.lang.String aeno) {
        this.aeno = aeno;
    }

    public java.lang.String getAeorgnm() {
        return aeorgnm;
    }

    public void setAeorgnm(java.lang.String aeorgnm) {
        this.aeorgnm = aeorgnm;
    }

    public java.lang.String getAeorgno() {
        return aeorgno;
    }

    public void setAeorgno(java.lang.String aeorgno) {
        this.aeorgno = aeorgno;
    }

    public java.lang.String getAeedorgnm() {
        return aeedorgnm;
    }

    public void setAeedorgnm(java.lang.String aeedorgnm) {
        this.aeedorgnm = aeedorgnm;
    }

    public java.lang.String getAeedorgno() {
        return aeedorgno;
    }

    public void setAeedorgno(java.lang.String aeedorgno) {
        this.aeedorgno = aeedorgno;
    }

    public java.lang.String getTopLevelBankNm() {
        return topLevelBankNm;
    }

    public void setTopLevelBankNm(java.lang.String topLevelBankNm) {
        this.topLevelBankNm = topLevelBankNm;
    }

    public java.lang.String getTopLevelBankNo() {
        return topLevelBankNo;
    }

    public void setTopLevelBankNo(java.lang.String topLevelBankNo) {
        this.topLevelBankNo = topLevelBankNo;
    }

    public java.lang.String getDirectParentlBankNm() {
        return directParentlBankNm;
    }

    public void setDirectParentlBankNm(java.lang.String directParentlBankNm) {
        this.directParentlBankNm = directParentlBankNm;
    }

    public java.lang.String getDirectParentlBankNo() {
        return directParentlBankNo;
    }

    public void setDirectParentlBankNo(java.lang.String directParentlBankNo) {
        this.directParentlBankNo = directParentlBankNo;
    }

    public java.lang.String getFeedbackOpnion() {
        return feedbackOpnion;
    }

    public void setFeedbackOpnion(java.lang.String feedbackOpnion) {
        this.feedbackOpnion = feedbackOpnion;
    }

    public java.util.Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(java.util.Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public java.lang.String getCreator() {
        return creator;
    }

    public void setCreator(java.lang.String creator) {
        this.creator = creator;
    }

    public java.lang.String getCreatororg() {
        return creatororg;
    }

    public void setCreatororg(java.lang.String creatororg) {
        this.creatororg = creatororg;
    }

    public java.lang.String getCreatororgno() {
        return creatororgno;
    }

    public void setCreatororgno(java.lang.String creatororgno) {
        this.creatororgno = creatororgno;
    }

    public java.util.Date getCrtdate() {
        return crtdate;
    }

    public void setCrtdate(java.util.Date crtdate) {
        this.crtdate = crtdate;
    }

    public java.lang.String getStat() {
        return stat;
    }

    public void setStat(java.lang.String stat) {
        this.stat = stat;
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

    public java.util.Date getUpdateate() {
        return updateate;
    }

    public void setUpdateate(java.util.Date updateate) {
        this.updateate = updateate;
    }

    public java.lang.String getCrtlogin() {
        return crtlogin;
    }

    public void setCrtlogin(java.lang.String crtlogin) {
        this.crtlogin = crtlogin;
    }

    public java.lang.String getAeedorgzzno() {
        return aeedorgzzno;
    }

    public void setAeedorgzzno(java.lang.String aeedorgzzno) {
        this.aeedorgzzno = aeedorgzzno;
    }

    public java.lang.String getAeedorgxzhno() {
        return aeedorgxzhno;
    }

    public void setAeedorgxzhno(java.lang.String aeedorgxzhno) {
        this.aeedorgxzhno = aeedorgxzhno;
    }

    public java.lang.String getAeedorgzznm() {
        return aeedorgzznm;
    }

    public void setAeedorgzznm(java.lang.String aeedorgzznm) {
        this.aeedorgzznm = aeedorgzznm;
    }

    public java.lang.String getAeedorgxzhnm() {
        return aeedorgxzhnm;
    }

    public void setAeedorgxzhnm(java.lang.String aeedorgxzhnm) {
        this.aeedorgxzhnm = aeedorgxzhnm;
    }

    public boolean isIsfinished() {
        return isfinished;
    }

    public void setIsfinished(boolean isfinished) {
        this.isfinished = isfinished;
    }

    public boolean isCanbeupdate() {
        return canbeupdate;
    }

    public void setCanbeupdate(boolean canbeupdate) {
        this.canbeupdate = canbeupdate;
    }

    public java.lang.String getAeedOrgBankType() {
        return aeedOrgBankType;
    }

    public void setAeedOrgBankType(java.lang.String aeedOrgBankType) {
        this.aeedOrgBankType = aeedOrgBankType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}