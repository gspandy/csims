package com.gtm.csims.jaas.service;

import com.gtm.csims.model.BsOrg;

@SuppressWarnings("unchecked")
public interface CredentialService {

	public BsOrg getOrganization(String no);
}
