package com.gtm.csims.business.enforce;

import java.util.List;

import com.gtm.csims.model.BsAeinspectionAnl;
import com.gtm.csims.model.BsFactbook;
import com.gtm.csims.model.BsWorkbasis;
import com.gtm.csims.model.BsWorkcoming;
import com.gtm.csims.model.BsWorkgoaway;
import com.gtm.csims.model.BsWorktalksummary;

public class AeInspectDTO {

	private String innerNo;

	private String aeheadman;
	private String aemaster;
	private String aeother;

	private BsWorkcoming BsWorkcoming;
	private BsWorktalksummary BsWorktalksummary;
	private BsWorkgoaway BsWorkgoaway;
	private BsFactbook BsFactbook;
	private List<BsWorkbasis> BsWorkbasis;
	private BsAeinspectionAnl BsAeinspectionAnl;

	private byte[] aquirAttaByteArray;
	private byte[] factAttaByteArray;
	private byte[] basisAttaByteArray;

	public BsWorkcoming getBsWorkcoming() {
		return BsWorkcoming;
	}

	public void setBsWorkcoming(BsWorkcoming bsWorkcoming) {
		BsWorkcoming = bsWorkcoming;
	}

	public BsWorktalksummary getBsWorktalksummary() {
		return BsWorktalksummary;
	}

	public void setBsWorktalksummary(BsWorktalksummary bsWorktalksummary) {
		BsWorktalksummary = bsWorktalksummary;
	}

	public BsWorkgoaway getBsWorkgoaway() {
		return BsWorkgoaway;
	}

	public void setBsWorkgoaway(BsWorkgoaway bsWorkgoaway) {
		BsWorkgoaway = bsWorkgoaway;
	}

	public BsFactbook getBsFactbook() {
		return BsFactbook;
	}

	public void setBsFactbook(BsFactbook bsFactbook) {
		BsFactbook = bsFactbook;
	}

	public List<BsWorkbasis> getBsWorkbasis() {
		return BsWorkbasis;
	}

	public void setBsWorkbasis(List<BsWorkbasis> bsWorkbasis) {
		BsWorkbasis = bsWorkbasis;
	}

	public BsAeinspectionAnl getBsAeinspectionAnl() {
		return BsAeinspectionAnl;
	}

	public void setBsAeinspectionAnl(BsAeinspectionAnl bsAeinspectionAnl) {
		BsAeinspectionAnl = bsAeinspectionAnl;
	}

	public String getInnerNo() {
		return innerNo;
	}

	public void setInnerNo(String innerNo) {
		this.innerNo = innerNo;
	}

	public String getAeheadman() {
		return aeheadman;
	}

	public void setAeheadman(String aeheadman) {
		this.aeheadman = aeheadman;
	}

	public String getAemaster() {
		return aemaster;
	}

	public void setAemaster(String aemaster) {
		this.aemaster = aemaster;
	}

	public String getAeother() {
		return aeother;
	}

	public void setAeother(String aeother) {
		this.aeother = aeother;
	}

	public byte[] getAquirAttaByteArray() {
		return aquirAttaByteArray;
	}

	public void setAquirAttaByteArray(byte[] aquirAttaByteArray) {
		this.aquirAttaByteArray = aquirAttaByteArray;
	}

	public byte[] getFactAttaByteArray() {
		return factAttaByteArray;
	}

	public void setFactAttaByteArray(byte[] factAttaByteArray) {
		this.factAttaByteArray = factAttaByteArray;
	}

	public byte[] getBasisAttaByteArray() {
		return basisAttaByteArray;
	}

	public void setBasisAttaByteArray(byte[] basisAttaByteArray) {
		this.basisAttaByteArray = basisAttaByteArray;
	}

}
