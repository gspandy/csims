package com.gtm.csims.business.enforce;

import java.util.ArrayList;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsAepeopleDAO;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.utils.StringUtil;

public class EnforcePeopleService {
	private BsAepeopleDAO bsAepeopleDao;

	public void setBsAepeopleDao(BsAepeopleDAO bsAepeopleDao) {
		this.bsAepeopleDao = bsAepeopleDao;
	}

	/***************************************************************************
	 * 执法人员登记
	 **************************************************************************/
	/**
	 * 保存执法人员
	 */
	@Transactional(readOnly = false)
	public void saveAepeople(BsAepeople bsAepeople) {
		bsAepeopleDao.save(bsAepeople);
	}

	/**
	 * 执法人员查询
	 * 
	 * @param aeNo
	 *            执法检查立项编号
	 * @param aeorgName
	 *            检查单位
	 * @param aeedorgName
	 *            被检查单位
	 * @param aeplanstDate
	 *            开始日期
	 * @param aeplantmDate
	 *            结束日期
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryAepeople(String certNo, String pepName,
			String orgNm, String aeplanstDate, String aeplantmDate,
			Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsAepeople where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (certNo != null && !certNo.trim().equals("")) {
			sb.append(" and certno like  ? ");
			param.add("%" + certNo.trim() + "%");
		}
		if (aeplanstDate != null && !aeplanstDate.trim().equals("")) {
			sb.append(" and createdate > ? ");
			param.add(StringUtil.convert(aeplanstDate));
		}
		if (aeplantmDate != null && !aeplantmDate.trim().equals("")) {
			sb.append(" and createdate < ? ");
			param.add(StringUtil.convert(aeplantmDate));
		}
		if (pepName != null && !pepName.trim().equals("")) {
			sb.append(" and pepname like ? ");
			param.add("%" + pepName.trim() + "%");
		}
		if (orgNm != null && !orgNm.trim().equals("")) {
			sb.append(" and orgnm like ? ");
			param.add("%" + orgNm.trim() + "%");
		}
		sb.append(" order by createdate DESC");
		Page page = bsAepeopleDao.pagedQuery(sb.toString(), pageNo, pageSize,
				param.toArray());
		return page;

	}

	/**
	 * 判断执法人员编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public Boolean isUniqueAepeopleNo(String no) {
		StringBuffer sb = new StringBuffer("FROM BsAepeople where certno = ? ");
		List mesageList = bsAepeopleDao.find(sb.toString(),
				new Object[] { no });
		if (mesageList != null) {
			if (mesageList.size() > 0)
				return false;
			else
				return true;
		}
		return false;
	}

	/**
	 * 根据ID删除执法人员
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteAepeopleById(String id) {
		if (id != null && !id.trim().equals(""))
			bsAepeopleDao.removeById(id);
	}

	/**
	 * 查询执法人员详情
	 * 
	 * @param id
	 * @return
	 */
	public BsAepeople getAepeople(String id) {
		return bsAepeopleDao.get(id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<BsAepeople> getAepeopleListByOrgNo(String orgNo){
		try {
			StringBuffer areaHql = new StringBuffer();
			areaHql.append(" from BsAepeople where orgno = '" + orgNo + "' ");
			List<BsAepeople> list = bsAepeopleDao.find(areaHql.toString());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
