package com.gtm.csims.business.enforce;

import java.util.List;

import com.gtm.csims.model.BsAdmenforce;
import com.gtm.csims.model.BsAepeople;

@SuppressWarnings("serial")
public class AeEnforceDTO extends BsAdmenforce {

	private List<BsAepeople> otheres;

	private List<BsAepeople> masters;

	private List<BsAepeople> aeheadmans;

	public List<BsAepeople> getOtheres() {
		return otheres;
	}

	public void setOtheres(List<BsAepeople> otheres) {
		this.otheres = otheres;
	}

	public List<BsAepeople> getMasters() {
		return masters;
	}

	public void setMasters(List<BsAepeople> masters) {
		this.masters = masters;
	}

	public List<BsAepeople> getAeheadmans() {
		return aeheadmans;
	}

	public void setAeheadmans(List<BsAepeople> aeheadmans) {
		this.aeheadmans = aeheadmans;
	}

}
