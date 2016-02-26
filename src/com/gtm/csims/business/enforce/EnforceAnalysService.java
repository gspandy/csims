package com.gtm.csims.business.enforce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sweet.dao.generic.support.Page;

import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsAdmenfcanlyDAO;
import com.gtm.csims.model.BsAdmenfcanly;
import com.gtm.csims.utils.RequestUtil;
import com.gtm.csims.utils.StringUtil;

/**
 * 
 * @author Sweet
 * 
 */
public class EnforceAnalysService {
    private BsAdmenfcanlyDAO bsAdmenfcanlyDao;

    public void setBsAdmenfcanlyDao(BsAdmenfcanlyDAO bsAdmenfcanlyDao) {
        this.bsAdmenfcanlyDao = bsAdmenfcanlyDao;
    }

    /***************************************************************************
     * 行政执法统计信息
     **************************************************************************/
    /**
     * 保存行政执法统计信息
     */
    @Transactional(readOnly = false)
    public void saveAdmenfcanly(HttpServletRequest request) {
        BsAdmenfcanly ae = RequestUtil.getBeanFromParams(request, BsAdmenfcanly.class);
        bsAdmenfcanlyDao.save(ae);
    }

    /**
     * 行政执法统计信息查询
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
    @Transactional(readOnly = true)
    public Page queryAdmenfcanly(String aeNo, String aeorgName, String aeedorgName,
            String aeplanstDate, String aeplantmDate, Integer pageNo, Integer pageSize) {
        StringBuffer sb = new StringBuffer("FROM BsAdmenfcanly where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (aeNo != null && !aeNo.trim().equals("")) {
            sb.append(" and aeno like  ? ");
            param.add("%" + aeNo.trim() + "%");
        }
        if (aeplanstDate != null && !aeplanstDate.trim().equals("")) {
            sb.append(" and aeplanstdate > ? ");
            // begin = begin + " 0:00:00";
            // param.add(Timestamp.valueOf(begin));
            param.add(StringUtil.convert(aeplanstDate));
        }
        if (aeplantmDate != null && !aeplantmDate.trim().equals("")) {
            sb.append(" and aeplantmdate < ? ");
            // end = end + " 0:00:00";
            // param.add(Timestamp.valueOf(end));
            param.add(StringUtil.convert(aeplantmDate));
        }
        if (aeorgName != null && !aeorgName.trim().equals("")) {
            sb.append(" and aeorgnm like ? ");
            param.add("%" + aeorgName.trim() + "%");
        }
        if (aeedorgName != null && !aeedorgName.trim().equals("")) {
            sb.append(" and aeedorgnm like ? ");
            param.add("%" + aeedorgName.trim() + "%");
        }
        sb.append(" order by createdate DESC");
        Page page = bsAdmenfcanlyDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
        return page;

    }

    /**
     * 判断保存行政执法统计编号是否为系统唯一
     * 
     * @param admenForceNo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Boolean isUniqueAdmenfcanlyNo(String admenForceNo) {
        StringBuffer sb = new StringBuffer("FROM BsAdmenfcanly where aeno = ? ");
        List mesageList = bsAdmenfcanlyDao.find(sb.toString(), new Object[] { admenForceNo });
        if (mesageList != null) {
            if (mesageList.size() > 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据ID删除保存行政执法统计
     * 
     * @param messageId
     */
    @Transactional
    public void deleteAdmenfcanlyById(String id) {
        if (id != null && !id.trim().equals("")) {
            bsAdmenfcanlyDao.removeById(id);
        }
    }

    /**
     * 查询保存行政执法统计详情
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public BsAdmenfcanly getAdmenfcanly(String id) {
        return bsAdmenfcanlyDao.get(id);
    }
}
