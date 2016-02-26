package com.gtm.csims.jaas.service;

import java.util.List;

import javax.security.auth.Subject;

import net.sf.jguard.ext.authorization.manager.AuthorizationManager;

/**
 * 系统所有业务数据权限控制类，根据用户返回各类业务数据
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public interface DataAuthorizationService {

	/**
	 * 查询指定地区的所有子地区信息
	 * 
	 * @param areaNo
	 * @param containSelf
	 *            是否返回本身
	 * @return
	 */
	public String listChildrenAreasStr(String areaNo, Boolean containSelf,
			Boolean hasQuote);

	/**
	 * 查询指定机构的所有子机构信息
	 * 
	 * @param organizationNo
	 * @param containSelf
	 *            是否返回本身
	 * @return
	 */
	public String listChildrenOrganizationsStr(String organizationNo,
			Boolean containSelf, Boolean hasQuote);

//	public List<BsOrganization> listChildOrg(String orgNo);
	
	/**
	 * 根据当前登录用户获取能够操作的指标栏目集合
	 * 
	 * @param authorizationManager
	 * @param subject
	 * @param operator
	 * @return
	 */
//	public List authorizeTargetType(AuthorizationManager authorizationManager,
//			Subject subject, String requestAction, String method);

	/**
	 * 根据当前登录用户获取能够操作的指标栏目集合
	 * 
	 * @param authorizationManager
	 * @param subject
	 * @param requestAction
	 * @param method
	 * @param idName
	 * @return
	 */
//	public List authorizeTargetType(AuthorizationManager authorizationManager,
//			Subject subject, String requestAction, String method, String idName);

	/**
	 * 根据当前登录用户获取能够操作的栏目集合
	 * 
	 * @param authorizationManager
	 * @param subject
	 * @param operator
	 * @return
	 */
//	public List authorizeFiledType(AuthorizationManager authorizationManager,
//			Subject subject, String requestAction, String method);

	/**
	 * 根据当前登录用户获取能够操作的栏目集合
	 * 
	 * @param authorizationManager
	 * @param subject
	 * @param requestAction
	 * @param method
	 * @param idName
	 * @return
	 */
//	public List authorizeFiledType(AuthorizationManager authorizationManager,
//			Subject subject, String requestAction, String method, String idName);

	/**
	 * 根据当前登录用户获取能够操作的一级栏目集合
	 * 
	 * @param authorizationManager
	 * @param subject
	 * @param requestAction
	 * @param method
	 * @param idName
	 * @return
	 */
	// public List authorizeFirstLevelFiledType(
	// AuthorizationManager authorizationManager, Subject subject,
	// String requestAction, String method, String idName);

	/**
	 * 判断当前子机构是否是父机构的下属机构
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	public Boolean isChildOrganization(String parent, String child);

	/**
	 * 判断当前子地区是否是父地区的下属地区
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	public Boolean isChildArea(String parent, String child);

	/**
	 * 获取系统中二级指标类型（即A,B节点下一级的指标类型）
	 * 
	 * @param authorizationManager
	 * @param subject
	 * @param requestAction
	 * @param method
	 * @param idName
	 * @return
	 */
	public List authorizeSecondLevelTargetType(
			AuthorizationManager authorizationManager, Subject subject,
			String requestAction, String method, String idName);
	
	/**
	 * 查询机构
	 * @param value
	 * @return
	 */
//	public BsOrganization getBsOrganization(String Id);

	/**
	 * 查询地区
	 * @param value
	 * @return
	 */
	
	
}
