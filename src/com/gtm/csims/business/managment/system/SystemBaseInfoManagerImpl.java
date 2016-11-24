package com.gtm.csims.business.managment.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sweet.dao.generic.support.Page;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.util.LabelValueBean;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsDeptDAO;
import com.gtm.csims.dao.BsOrgComInfoDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.model.BsDept;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsOrgComInfo;
import com.gtm.csims.utils.ExcelUtil;
import com.gtm.csims.dao.BsAreaDAO;
import com.gtm.csims.model.BsArea;

@SuppressWarnings("unchecked")
public class SystemBaseInfoManagerImpl implements SystemBaseInfoManager {
	private BsOrgDAO bsOrgDao;
	private BsOrgComInfoDAO bsOrgComInfoDao;
	private BsDeptDAO bsDeptDao;
	private BsAreaDAO bsAreaDao;

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	public void setBsDeptDao(BsDeptDAO bsDeptDao) {
		this.bsDeptDao = bsDeptDao;
	}

	@Transactional(readOnly = true)
	public BsOrg getOrgByNo(Serializable no) {
		return bsOrgDao.get(no);
	}

	@Transactional(readOnly = true)
	public BsOrg getOrgByName(Serializable name) {
		List<BsOrg> orgs = bsOrgDao.find("from BsOrg where name = ?", name);
		if (orgs != null && orgs.size() > 0) {
			return orgs.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public void setBsOrgComInfoDao(BsOrgComInfoDAO bsOrgComInfoDao) {
		this.bsOrgComInfoDao = bsOrgComInfoDao;
	}

	@Transactional(readOnly = true)
	public BsDept getDeptByNo(Serializable no) {
		return bsDeptDao.get(no);
	}

	public BsAreaDAO getBsAreaDao() {
		return bsAreaDao;
	}

	public void setBsAreaDao(BsAreaDAO bsAreaDao) {
		this.bsAreaDao = bsAreaDao;
	}

	// 读取地区树
	public List<BsArea> areaList(String userAreaId, boolean isFirst) {
		try {
			StringBuffer areaHql = new StringBuffer();
			areaHql.append("from BsArea as ba");
			if (isFirst == true) {
				areaHql.append(" where  ba.Id = '" + userAreaId + "'  and ba.Status = '1' ");
			} else {
				areaHql.append(" where  ba.Parent.Id = '" + userAreaId + "'  and ba.Status = '1' ");
			}
			List<BsArea> baList = bsAreaDao.find(areaHql.toString());
			return baList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<BsOrg> orgListByParentNo(String parentNo) {
		try {
			StringBuffer areaHql = new StringBuffer();
			areaHql.append(" from BsOrg where parentno = '" + parentNo + "' order by no asc ");
			List<BsOrg> list = bsOrgDao.find(areaHql.toString());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<BsOrg> pcbOrgList(String parentNo) {
		try {
			StringBuffer areaHql = new StringBuffer();
			areaHql.append(" from BsOrg where parentno = '" + parentNo + "' order by no asc ");
			List<BsOrg> bankList = bsOrgDao.find(areaHql.toString());
			return bankList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional(readOnly = true)
	public List getDepByOrgNoForSelect(String orgNo) {
		List result = new ArrayList();
		result.add(new LabelValueBean("-----请选择-----", ""));
		List exsitList = bsDeptDao.find("FROM BsDept WHERE Orgno = ?", orgNo);
		// 把取出的部门转换成下拉列表格式返回
		for (Iterator iterator = exsitList.iterator(); iterator.hasNext();) {
			BsDept bs = (BsDept) iterator.next();
			result.add(new LabelValueBean(bs.getName(), bs.getId().toString()));
		}
		result.add(new LabelValueBean("无部门", "0"));
		return result;
	}

	@Transactional(readOnly = true)
	public String getDepByOrgNoForJson(String orgNo) {
		List exsitList = bsDeptDao.find("FROM BsDept WHERE Orgno = ?", orgNo);
		JSONArray jsonArray = new JSONArray();
		// 把取出的部门转换成json格式返回
		for (Iterator iterator = exsitList.iterator(); iterator.hasNext();) {
			BsDept bs = (BsDept) iterator.next();
			JSONObject json = new JSONObject();
			json.accumulate("id", bs.getId());
			json.accumulate("name", bs.getName());
			jsonArray.add(json);
		}
		return jsonArray.toString();
	}

	@SuppressWarnings("deprecation")
	@Transactional(readOnly = false)
	public void savaBsOrg(BsOrg bs) {
		bsOrgDao.save(bs);
	}

	@SuppressWarnings("deprecation")
	@Transactional(readOnly = false)
	public void savaBsOrgComInfo(BsOrgComInfo bs) {
		bsOrgComInfoDao.save(bs);
	}

	@SuppressWarnings("deprecation")
	@Transactional(readOnly = false)
	public void savaBsDept(BsDept bs) {
		bsDeptDao.save(bs);
	}

	public Page getDeptList(String deptName, String orgNo, int pageNo, int pageSize) {
		StringBuffer sb = new StringBuffer(" from BsDept where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (!"".equals(orgNo) && orgNo != null) {
			sb.append(" and orgno = ? ");
			param.add(orgNo);
		}
		if (!"".equals(deptName) && deptName != null) {
			sb.append(" and name like ? ");
			param.add("%" + deptName + "%");
		}
		Page page = bsDeptDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	public Page getOrgList(String orgNo, String orgName, String org, int pageNo, int pageSize) {
		StringBuffer sb = new StringBuffer(" from BsOrg where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (!"".equals(orgNo) && orgNo != null) {
			sb.append(" and no = ? ");
			param.add(orgNo);
		}
		if (!"".equals(org) && org != null) {
			sb.append(" and no = ? ");
			param.add(org);
		} else {
			if (!"".equals(orgName) && orgName != null) {
				sb.append(" and name like ? ");
				param.add("%" + orgName + "%");
			}
		}
		Page page = bsOrgDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	public String getOrgComInfoTR(String orgNo) {
		List orgComInfoList = bsOrgComInfoDao.find("FROM BsOrgComInfo WHERE no = ?", orgNo);
		StringBuffer sb = new StringBuffer("");
		if (orgComInfoList.size() > 0) {
			sb.append("<tr class='tabletext02'><td align='center'>序号</td><td align='center'>表彰时间</td>").append(
			        "<td align='center'>表彰机构</td><td align='center'>表彰内容</td></tr>");
			for (int i = 0; i < orgComInfoList.size(); i++) {
				BsOrgComInfo bs = (BsOrgComInfo) orgComInfoList.get(i);
				sb.append("<tr><td align='center'>").append(i + 1).append("</td>");
				sb.append("<td align='center'>").append(bs.getComDate()).append("</td>");
				sb.append("<td align='center'>").append(bs.getComOrgName()).append("</td>");
				sb.append("<td align='center'>").append(bs.getComContent()).append("</td></tr>");
			}
		}
		return sb.toString();
	}

	@Transactional(readOnly = false)
	public void savaOrgComInfo(InputStream is, String endRow) {
		HSSFWorkbook wb = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			for (int i = 1; i < Integer.parseInt(endRow); i++) {
				// 你把你的方法写道这
				String comedOrgNo = ExcelUtil.getValue(sheet.getRow(i).getCell(0));
				String comOrgNo = ExcelUtil.getValue(sheet.getRow(i).getCell(1));
				BsOrg bsComOrg = this.getOrgByNo(comOrgNo);
				String comOrgName = bsComOrg.getName();
				String comContent = ExcelUtil.getValue(sheet.getRow(i).getCell(2));
				String comDate = ExcelUtil.getValue(sheet.getRow(i).getCell(3));
				BsOrgComInfo bsOrgComInfo = new BsOrgComInfo();
				bsOrgComInfo.setNo(comedOrgNo);
				bsOrgComInfo.setComOrgNo(comOrgNo);
				bsOrgComInfo.setComOrgName(comOrgName);
				bsOrgComInfo.setComContent(comContent);
				bsOrgComInfo.setComDate(comDate);
				bsOrgComInfoDao.save(bsOrgComInfo);
				// this.savaBsOrgComInfo(bsOrgComInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取Excel失败");
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Transactional(readOnly = true)
	public String getAllChildOfOrg(String orgId) {
		List resultList = new ArrayList();
		List nextChildOrgList = new ArrayList();
		BsOrg bsOrg = new BsOrg();
		bsOrg = bsOrgDao.get(orgId);
		resultList.add(bsOrg);
		nextChildOrgList = getNextChildOrgList("'" + bsOrg.getNo() + "'");
		if (nextChildOrgList.size() != 0) {
			iteratorAddChildOrg(resultList, nextChildOrgList);
		}
		String resultOrgSting = "";
		for (int i = 0; i < resultList.size(); i++) {
			BsOrg orgObject = (BsOrg) resultList.get(i);
			resultOrgSting += "'" + orgObject.getNo() + "'";
			if (i != resultList.size() - 1) {
				resultOrgSting += ",";
			}
		}
		return resultOrgSting.length() == 0 ? "''" : resultOrgSting;
	}

	@Transactional(readOnly = true)
	private List getNextChildOrgList(String queryOrgIdString) {
		StringBuffer hql = new StringBuffer();
		hql.append("From BsOrg ba Where ba.Parentno in (" + queryOrgIdString + ") AND ba.Parentno <> ba.No");
		return bsOrgDao.find(hql.toString());
	}

	@Transactional(readOnly = true)
	private void iteratorAddChildOrg(List resultList, List childOrgList) {
		String queryOrgIdString = "";
		for (int i = 0; i < childOrgList.size(); i++) {
			BsOrg childOrgObject = (BsOrg) childOrgList.get(i);
			resultList.add(childOrgObject);
			queryOrgIdString += "'" + childOrgObject.getNo() + "'";
			if (i != childOrgList.size() - 1) {
				queryOrgIdString += ",";
			}
		}
		List nextChildOrgList = getNextChildOrgList(queryOrgIdString);
		if (nextChildOrgList.size() != 0) {
			iteratorAddChildOrg(resultList, nextChildOrgList);
		}
	}

	/**
	 * 取得指定地区下的所有机构
	 * 
	 * @param orgId
	 * @return
	 */
	public String getAllOrgByAreaId(String areaId) {
		BsArea bsArea = new BsArea();
		bsArea = bsAreaDao.get(areaId);
		String resultOrgSting = "";
		List<BsOrg> resultList = null;
		if (bsArea.getLevel().equals("PROVINCE")) {
			// resultList = bsOrgDao.find(" From BsOrg ");
			return null;
		}
		if (bsArea.getLevel().equals("CITY")) {
			resultList = bsOrgDao.find(" From BsOrg Where P='" + areaId + "'");
		}
		if (bsArea.getLevel().equals("COUNTRY")) {
			resultList = bsOrgDao.find(" From BsOrg Where R='" + areaId + "'");
		}
		if (resultList != null) {
			for (int i = 0; i < resultList.size(); i++) {
				BsOrg orgObject = (BsOrg) resultList.get(i);
				resultOrgSting += "'" + orgObject.getNo() + "'";
				if (i != resultList.size() - 1) {
					resultOrgSting += ",";
				}
			}
		}
		return resultOrgSting.length() == 0 ? "''" : resultOrgSting;
	}

	@Transactional(readOnly = true)
	public List<BsOrg> getBsOrgList(String orgStr, String areaStr) {
		try {
			StringBuffer orgHql = new StringBuffer();
			orgHql.append(" from BsOrg where 1=1 ");
			if (!"".equals(orgStr)) {
				orgHql.append(" and No in (" + orgStr + ")");
			}
			List<BsOrg> list = new ArrayList();
			BsOrg bs = new BsOrg();
			if ("".equals(areaStr)) {
				list = bsOrgDao.find(orgHql.toString());
			} else {
				List<BsOrg> listp = bsOrgDao.find(orgHql.toString() + " and N in (" + areaStr
				        + ") and (P is null or P ='' ) ");
				List<BsOrg> listc = bsOrgDao.find(orgHql.toString() + " and P in (" + areaStr
				        + ") and (R is null or R ='' ) ");
				List<BsOrg> listx = bsOrgDao.find(orgHql.toString() + " and Q in (" + areaStr + ") ");
				if (listp.size() > 0) {
					for (int i = 0; i < listp.size(); i++) {
						bs = listp.get(i);
						list.add(bs);
					}
				}
				if (listc.size() > 0) {
					for (int i = 0; i < listc.size(); i++) {
						bs = listc.get(i);
						list.add(bs);
					}
				}
				if (listx.size() > 0) {
					for (int i = 0; i < listx.size(); i++) {
						bs = listx.get(i);
						list.add(bs);
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
