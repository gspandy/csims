package com.gtm.csims.business.zxmarket;

import java.util.ArrayList;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsZxOrgDocDAO;
import com.gtm.csims.dao.BsZxPersonalDocDAO;
import com.gtm.csims.model.BsZxOrgDoc;
import com.gtm.csims.model.BsZxPersonalDoc;

public class ZxMarketMgrService {
	private BsZxOrgDocDAO bsZxOrgDocDao;
	private BsZxPersonalDocDAO bsZxPersonalDocDao;

	public void setBsZxOrgDocDao(BsZxOrgDocDAO bsZxOrgDocDao) {
		this.bsZxOrgDocDao = bsZxOrgDocDao;
	}

	public void setBsZxPersonalDocDao(BsZxPersonalDocDAO bsZxPersonalDocDao) {
		this.bsZxPersonalDocDao = bsZxPersonalDocDao;
	}

	@Transactional(readOnly = false)
	public void saveBsZxOrgDoc(BsZxOrgDoc bs){
		bsZxOrgDocDao.save(bs);
	}
	
	@Transactional(readOnly = false)
	public void saveBsZxPersonalDoc(BsZxPersonalDoc bs){
		bsZxPersonalDocDao.save(bs);
	}
	
	public Page queryZxOrgIntegrityDocumentList(String orgNo,String year,Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer(" FROM BsZxOrgDoc where 1=1");
		List<Object> param = new ArrayList<Object>();
		if (orgNo != null && !orgNo.trim().equals("")) {
			sb.append(" and orgCodeOfZz = ? ");
			param.add(orgNo.trim());
		}
		if (year != null && !year.trim().equals("")) {
			sb.append(" and year = ? ");
			param.add(year.trim());
		}
		sb.append(" order by recordDate DESC");
		Page page = bsZxOrgDocDao.pagedQuery(sb.toString(), pageNo, pageSize);
		return page;

	}
	
	public Page queryZxPersonalIntegrityDocumentList(String name,String orgNo,String year,Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer(" FROM BsZxPersonalDoc ");
		List<Object> param = new ArrayList<Object>();
		if (name != null && !name.trim().equals("")) {
			sb.append(" and name like ? ");
			param.add("%" + name.trim() + "%");
		}
		if (orgNo != null && !orgNo.trim().equals("")) {
			sb.append(" and orgCodeOfZz = ? ");
			param.add(orgNo.trim());
		}
		if (year != null && !year.trim().equals("")) {
			sb.append(" and year = ? ");
			param.add(year.trim());
		}
		sb.append(" order by recordDate DESC");
		Page page = bsZxPersonalDocDao.pagedQuery(sb.toString(), pageNo, pageSize);
		return page;
	}
}
