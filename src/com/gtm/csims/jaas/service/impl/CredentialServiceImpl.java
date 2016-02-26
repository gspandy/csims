package com.gtm.csims.jaas.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.jaas.service.CredentialService;
import com.gtm.csims.model.BsOrg;

@SuppressWarnings("unchecked")
public class CredentialServiceImpl implements CredentialService {
	private BsOrgDAO bsOrgDao;

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	@Transactional(readOnly = true)
	public BsOrg getOrganization(String no) {
		// TODO Auto-generated method stub
		return bsOrgDao.get(no);
	}

	

}
