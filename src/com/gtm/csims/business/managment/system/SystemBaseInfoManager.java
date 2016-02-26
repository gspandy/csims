package com.gtm.csims.business.managment.system;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import com.gtm.csims.model.BsDept;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsOrgComInfo;
import com.gtm.csims.model.BsArea;

/**
 * 系统基础信息维护类
 * 
 * 机构
 * 
 * 
 */
@SuppressWarnings("unchecked")
public interface SystemBaseInfoManager {
	
	public BsOrg getOrgByNo(Serializable no);
	
	public BsDept getDeptByNo(Serializable no);
	
	public List<BsOrg> orgListByParentNo(String parentNo);
	
	public List<BsOrg> pcbOrgList(String parentNo);
	
	public List getDepByOrgNoForSelect(String orgNo);
	
	public String getDepByOrgNoForJson(String orgNo);

	public void savaBsOrg(BsOrg bs);
	
	public void savaBsOrgComInfo(BsOrgComInfo bs);
	
	public void savaBsDept(BsDept bs);
	
	public Page getDeptList(String deptName, String orgNo, int pageNo, int pageSize);
	
	public Page getOrgList(String orgNo, String orgName, String org, int pageNo, int pageSize);
	
	public String getOrgComInfoTR(String orgNo);
	
	public void savaOrgComInfo(InputStream is,String endRow);
	
	public List<BsArea> areaList(String userAreaId, boolean isFirst);
	
	/**
	 * 遍历取得指定机构的所有子机构
	 * 
	 * @param orgId
	 * @return
	 */
	public String getAllChildOfOrg(String orgId);
	
	
	/**
	 * 取得指定地区下的所有地区
	 * 
	 * @param areaId
	 * @return
	 */
	public String getAllOrgByAreaId(String areaId);

}
