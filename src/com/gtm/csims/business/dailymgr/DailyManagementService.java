package com.gtm.csims.business.dailymgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.struts.util.LabelValueBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsBusievalDAO;
import com.gtm.csims.dao.BsBusievalInfoDAO;
import com.gtm.csims.dao.BsOffsitecheckDAO;
import com.gtm.csims.dao.BsTrainingDAO;
import com.gtm.csims.dao.BsWorkcheckDAO;
import com.gtm.csims.model.BsBusieval;
import com.gtm.csims.model.BsBusievalInfo;
import com.gtm.csims.model.BsDictionary;
import com.gtm.csims.model.BsOffsitecheck;
import com.gtm.csims.model.BsTraining;
import com.gtm.csims.model.BsWorkcheck;
import com.gtm.csims.utils.RequestUtil;
import com.gtm.csims.utils.StringUtil;

public class DailyManagementService {
	private BsBusievalDAO bsBusievalDao;
	private BsBusievalInfoDAO bsBusievalInfoDao;
	private BsTrainingDAO bsTrainingDao;
	private BsWorkcheckDAO bsWorkcheckDao;
	private BsOffsitecheckDAO bsOffsitecheckDao;
	private JdbcTemplate jdbcTemplate;


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setBsBusievalDao(BsBusievalDAO bsBusievalDao) {
		this.bsBusievalDao = bsBusievalDao;
	}

	public void setBsTrainingDao(BsTrainingDAO bsTrainingDao) {
		this.bsTrainingDao = bsTrainingDao;
	}

	public void setBsWorkcheckDao(BsWorkcheckDAO bsWorkcheckDao) {
		this.bsWorkcheckDao = bsWorkcheckDao;
	}
	
	public void setBsOffsitecheckDao(BsOffsitecheckDAO bsOffsitecheckDao) {
		this.bsOffsitecheckDao = bsOffsitecheckDao;
	}

	public void setBsBusievalInfoDao(BsBusievalInfoDAO bsBusievalInfoDao) {
		this.bsBusievalInfoDao = bsBusievalInfoDao;
	}

	/***************************************************************************
	 * 培训信息
	 **************************************************************************/
	/**
	 * 保存培训信息
	 */
	@Transactional(readOnly = false)
	public void saveTraning(BsTraining bs) {
		bsTrainingDao.save(bs);
	}

	/**
	 * 培训信息查询
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
	public Page queryTraning(String trainingNo,String tranOrgNo, String aeplanstDate, String aeplantmDate,
			Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsTraining where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (trainingNo != null && !trainingNo.trim().equals("")) {
			sb.append(" and trainingno like  ? ");
			param.add("%" + trainingNo.trim() + "%");
		}
		if (aeplanstDate != null && !aeplanstDate.trim().equals("")) {
			sb.append(" and trandt > ? ");
			param.add(StringUtil.convert(aeplanstDate));
		}
		if (aeplantmDate != null && !aeplantmDate.trim().equals("")) {
			sb.append(" and trandt < ? ");
			param.add(StringUtil.convert(aeplantmDate));
		}
		if (tranOrgNo != null && !tranOrgNo.trim().equals("")) {
			sb.append(" and tranorgno = ? ");
			param.add(tranOrgNo.trim());
		}
		sb.append(" order by createdate DESC");
		Page page = bsTrainingDao.pagedQuery(sb.toString(), pageNo, pageSize,
				param.toArray());
		return page;

	}

	/**
	 * 判断培训信息编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	public Boolean isUniqueTraningNo(String admenForceNo) {
		StringBuffer sb = new StringBuffer(
				"FROM BsTraining where trainingno = ? ");
		List mesageList = bsTrainingDao.find(sb.toString(),
				new Object[] { admenForceNo });
		if (mesageList != null) {
			if (mesageList.size() > 0)
				return false;
			else
				return true;
		}
		return false;
	}

	/**
	 * 根据ID删除培训信息
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteTraningById(String id) {
		if (id != null && !id.trim().equals(""))
			bsTrainingDao.removeById(id);
	}

	/**
	 * 查询培训信息详情
	 * 
	 * @param id
	 * @return
	 */
	public BsTraining getTraning(String id) {
		StringBuffer sb = new StringBuffer("FROM BsTraining where id = ? ");
		List<BsTraining> mesageList = bsTrainingDao.find(sb.toString(),
				new Object[] { id });
		if (mesageList != null) {
			if (mesageList.size() > 0){
				BsTraining bs = mesageList.get(0);
				return (BsTraining) mesageList.get(0);
			}else
				return null;
		}
		return null;
	}

	/***************************************************************************
	 * 业务评价信息
	 **************************************************************************/
	/**
	 * 保存业务评价信息
	 */
	@Transactional(readOnly = false)
	public void saveBsBusiEval(BsBusieval bs1,BsBusievalInfo bs2) {
		bsBusievalDao.save(bs1);
		bsBusievalInfoDao.save(bs2);
	}
	
	@Transactional(readOnly = false)
	public void saveBsBusiEvalInfo(BsBusievalInfo bs2) {
		bsBusievalInfoDao.save(bs2);
	}

	/**
	 * 业务评价信息查询
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
	public Page queryBusiEval(String busievalno, String evalorgno,
			String evaledorgno, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsBusieval where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (busievalno != null && !busievalno.trim().equals("")) {
			sb.append(" and busievalno =  ? ");
			param.add(busievalno.trim());
		}
		if (evalorgno != null && !evalorgno.trim().equals("")) {
			sb.append(" and evalorgno = ? ");
			param.add(evalorgno.trim());
		}
		if (evaledorgno != null && !evaledorgno.trim().equals("")) {
			sb.append(" and evaledorgno = ? ");
			param.add(evaledorgno.trim());
		}
//		sb.append(" group by evaledorgno");
		Page page = bsBusievalDao.pagedQuery(sb.toString(), pageNo, pageSize,
				param.toArray());
		return page;

	}

	/**
	 * 判断业务评价信息编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	public Boolean isUniqueBusiEvalNo(String admenForceNo) {
		StringBuffer sb = new StringBuffer(
				"FROM BsBusieval where busievalno = ? ");
		List mesageList = bsBusievalDao.find(sb.toString(),
				new Object[] { admenForceNo });
		if (mesageList != null) {
			if (mesageList.size() > 0)
				return false;
			else
				return true;
		}
		return false;
	}

	/**
	 * 根据ID删除业务评价信息
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteBusiEvalById(String id) {
		if (id != null && !id.trim().equals(""))
			bsBusievalDao.removeById(id);
		jdbcTemplate.execute("delete from Bs_BUSIEVALINFO where BSBUSIEVAL='"+id+"'");
	}

	/**
	 * 查询业务评价信息详情
	 * 
	 * @param id
	 * @return
	 */
	public BsBusieval getBusiEval(String id) {
		StringBuffer sb = new StringBuffer("FROM BsBusieval where id = ? ");
		List<BsBusieval> mesageList = bsBusievalDao.find(sb.toString(),
				new Object[] { id });
		if (mesageList != null) {
			if (mesageList.size() > 0){
				BsBusieval bs = mesageList.get(0);
				return (BsBusieval) mesageList.get(0);
			}else
				return null;
		}
		return null;
	}

	/***************************************************************************
	 * 工作检查信息
	 **************************************************************************/
	/**
	 * 保存工作检查信息
	 */
	@Transactional(readOnly = false)
	public void saveWorkCheck(BsWorkcheck bs) {
		bsWorkcheckDao.save(bs);
	}

	/**
	 * 工作检查信息查询
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
	public Page queryWorkCheck(String workChkNo, String chkOrgNo,
			String chkedOrgNo, String aeplanstDate, String aeplantmDate,
			Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsWorkcheck where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (workChkNo != null && !workChkNo.trim().equals("")) {
			sb.append(" and workchkno like  ? ");
			param.add("%" + workChkNo.trim() + "%");
		}
		if (aeplanstDate != null && !aeplanstDate.trim().equals("")) {
			sb.append(" and chkdt > ? ");
			param.add(StringUtil.convert(aeplanstDate));
		}
		if (aeplantmDate != null && !aeplantmDate.trim().equals("")) {
			sb.append(" and chkdt < ? ");
			param.add(StringUtil.convert(aeplantmDate));
		}
		if (chkOrgNo != null && !chkOrgNo.trim().equals("")) {
			sb.append(" and chkorgno = ? ");
			param.add(chkOrgNo.trim());
		}
		if (chkedOrgNo != null && !chkedOrgNo.trim().equals("")) {
			sb.append(" and chkedorgno = ? ");
			param.add(chkedOrgNo.trim());
		}
		sb.append(" order by createdate DESC");
		Page page = bsWorkcheckDao.pagedQuery(sb.toString(), pageNo, pageSize,
				param.toArray());
		return page;
	}
	
	/**
	 * 非现场检查信息查询
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
	public Page queryOffsiteCheck(String workChkNo, String chkOrgNo,
			String chkedOrgNo, String aeplanstDate, String aeplantmDate,
			Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsOffsitecheck where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (workChkNo != null && !workChkNo.trim().equals("")) {
			sb.append(" and workchkno like  ? ");
			param.add("%" + workChkNo.trim() + "%");
		}
		if (aeplanstDate != null && !aeplanstDate.trim().equals("")) {
			sb.append(" and chkdt > ? ");
			param.add(StringUtil.convert(aeplanstDate));
		}
		if (aeplantmDate != null && !aeplantmDate.trim().equals("")) {
			sb.append(" and chkdt < ? ");
			param.add(StringUtil.convert(aeplantmDate));
		}
		if (chkOrgNo != null && !chkOrgNo.trim().equals("")) {
			sb.append(" and chkorgno = ? ");
			param.add("%" + chkOrgNo.trim() + "%");
		}
		if (chkedOrgNo != null && !chkedOrgNo.trim().equals("")) {
			sb.append(" and chkedorgno = ? ");
			param.add("%" + chkedOrgNo.trim() + "%");
		}
		sb.append(" order by createdate DESC");
		Page page = bsOffsitecheckDao.pagedQuery(sb.toString(), pageNo, pageSize,
				param.toArray());
		return page;

	}

	/**
	 * 判断工作检查信息编号是否为系统唯一
	 * 
	 * @param admenForceNo
	 * @return
	 */
	public Boolean isUniqueWorkCheckNo(String admenForceNo) {
		StringBuffer sb = new StringBuffer(
				"FROM BsWorkcheck where workchkno = ? ");
		List mesageList = bsWorkcheckDao.find(sb.toString(),
				new Object[] { admenForceNo });
		if (mesageList != null) {
			if (mesageList.size() > 0)
				return false;
			else
				return true;
		}
		return false;
	}

	/**
	 * 根据ID删除工作检查信息
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteOffsiteCheckById(String id) {
		if (id != null && !id.trim().equals(""))
			bsOffsitecheckDao.removeById(id);
	}
	/**
	 * 根据ID删除工作检查信息
	 * 
	 * @param messageId
	 */
	@Transactional(readOnly = false)
	public void deleteWorkCheckById(String id) {
		if (id != null && !id.trim().equals(""))
			bsWorkcheckDao.removeById(id);
	}

	/**
	 * 查询工作检查信息详情
	 * 
	 * @param id
	 * @return
	 */
	public BsOffsitecheck getOffsiteCheck(String id) {
		StringBuffer sb = new StringBuffer("FROM BsOffsitecheck where id = ? ");
		List<BsOffsitecheck> mesageList = bsOffsitecheckDao.find(sb.toString(),
				new Object[] { id });
		if (mesageList != null) {
			if (mesageList.size() > 0){
				BsOffsitecheck bs = mesageList.get(0);
				return (BsOffsitecheck) mesageList.get(0);
			}else
				return null;
		}
		return null;
	}
	
	/**
	 * 查询工作检查信息详情
	 * 
	 * @param id
	 * @return
	 */
	public BsWorkcheck getWorkCheck(String id) {
		StringBuffer sb = new StringBuffer("FROM BsWorkcheck where id = ? ");
		List<BsWorkcheck> mesageList = bsWorkcheckDao.find(sb.toString(),
				new Object[] { id });
		if (mesageList != null) {
			if (mesageList.size() > 0){
				BsWorkcheck bs = mesageList.get(0);
				return (BsWorkcheck) mesageList.get(0);
			}else
				return null;
		}
		return null;
	}
	
	/**
	 * 保存工作检查信息
	 */
	@Transactional(readOnly = false)
	public void saveOffsiteCheck(BsOffsitecheck bs) {
		bsOffsitecheckDao.save(bs);
	}
	
	public String getBusievalStaResult(String evaledorgno){
		List list = jdbcTemplate.queryForList("select distinct(EVALINFO) from Bs_Busieval where EVALEDORGNO='"+evaledorgno+"' ");
		String[] evalInfos = new String[list.size()];
		StringBuffer sb = new StringBuffer("");
		try {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap map = (ListOrderedMap) list.get(i);
				evalInfos[i] = String.valueOf(map.get("EVALINFO"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.append("<tr align='center'><td rowspan='2'>&nbsp;</td><td colspan='4' align='center'>好的方面</td><td colspan='4'>不足之处</td><td colspan='4'>自我评价</td></tr>");
		sb.append("<tr align='center'><td>序号</td><td>评价内容</td><td>评价时间</td><td>得分</td><td>序号</td><td>评价内容</td><td>评价时间</td><td>得分</td><td>序号</td><td>评价内容</td><td>评价时间</td><td>得分</td></tr>");
		for(int j=0;j<evalInfos.length;j++){
			StringBuffer sb1 = new StringBuffer("FROM BsBusieval where EvalInfo = '"+evalInfos[j]+"' and EvalCondition ='好的方面' ");
			StringBuffer sb2 = new StringBuffer("FROM BsBusieval where EvalInfo = '"+evalInfos[j]+"' and EvalCondition ='不足之处' ");
			StringBuffer sb3 = new StringBuffer("FROM BsBusieval where EvalInfo = '"+evalInfos[j]+"' and EvalCondition ='自我评价' ");
			List<BsBusieval> list1 = bsBusievalDao.find(sb1.toString());
			List<BsBusieval> list2 = bsBusievalDao.find(sb2.toString());
			List<BsBusieval> list3 = bsBusievalDao.find(sb3.toString());
			int x=0;
			if(x<list1.size()){
				x=list1.size();
				if(x<list2.size()){
					x=list2.size();
					if(x<list3.size()){
						x=list3.size();
					}
				}
			}
			
			if(x>0){
				for(int k=0;k<x;k++){
					if(k==0){
						sb.append("<tr align='center'><td rowspan='"+x+"'>").append(evalInfos[j]).append("</td>");
					}else{
						sb.append("<tr align='center'>");
					}
					sb.append("<td>").append(k+1).append("</td><td>").append(list1.get(k).getEvalContent()).append("</td><td>").append(list1.get(k).getEvaldt()).append("</td>");
					sb.append("<td>").append(k+1).append("</td><td>").append(list1.get(k).getEvalContent()).append("</td><td>").append(list1.get(k).getEvaldt()).append("</td>");
					sb.append("<td>").append(k+1).append("</td><td>").append(list1.get(k).getEvalContent()).append("</td><td>").append(list1.get(k).getEvaldt()).append("</td></tr>");
//					sb.append("<td>").append(k+1).append("</td><td>").append(list2.get(k).getEvalContent()).append("</td><td>").append(list2.get(k).getEvaldt()).append("</td><td>").append(list2.get(k).getScore()).append("</td><td>");
//					sb.append("<td>").append(k+1).append("</td><td>").append(list3.get(k).getEvalContent()).append("</td><td>").append(list3.get(k).getEvaldt()).append("</td><td>").append(list3.get(k).getScore()).append("</td></tr>");
				}
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String getBusievalInfoString(BsBusieval bs){
		String evalInfos[] = {"综合管理","系统建设","征信管理","征信宣传与维权","信用体系建设","加分项目"};
		StringBuffer sbTab = new StringBuffer("");
		sbTab.append("<tr align='center' class='tabletext02'><td rowspan='2'>&nbsp;</td><td colspan='3' align='center'>基本情况</td>")
			 .append("<td colspan='4' align='center'>存在问题</td><td colspan='2' align='center'>加分情况</td></tr>");
		sbTab.append("<tr align='center' class='tabletext02'><td align='center'>序号</td><td align='center'>评价内容</td>")
		     .append("<td align='center'>基本分</td><td align='center'>序号</td><td align='center'>评价细则</td>")
		     .append("<td align='center'>评价内容<td align='center'>扣分</td><td align='center'>序号</td><td align='center'>评价内容</td></tr>");
		for(int i=0;i<6;i++){
			StringBuffer sb01 = new StringBuffer("FROM BsBusievalInfo where BsBusieval.Id = '"+bs.getId()+"' ");
			StringBuffer sb02 = new StringBuffer("FROM BsBusievalInfo where BsBusieval.Id = '"+bs.getId()+"' ");
			StringBuffer sb03 = new StringBuffer("FROM BsBusievalInfo where BsBusieval.Id = '"+bs.getId()+"' ");
			String evalInfo = evalInfos[i];
			sb01.append(" and evalInfo = '"+evalInfo+"' ");
			sb02.append(" and evalInfo = '"+evalInfo+"' ");
			sb03.append(" and evalInfo = '"+evalInfo+"' ");
			List<BsBusievalInfo> list1 = bsBusievalInfoDao.find(sb01.append(" and evalCondition = '基本情况' ").toString());
			List<BsBusievalInfo> list2 = bsBusievalInfoDao.find(sb02.append(" and evalCondition = '存在问题' ").toString());
			List<BsBusievalInfo> list3 = bsBusievalInfoDao.find(sb03.append(" and evalCondition = '加分情况' ").toString());
			int rowSpan=0;
			if(rowSpan<=list1.size()){
				rowSpan=list1.size();
				if(rowSpan<=list2.size()){
					rowSpan=list2.size();
					if(rowSpan<=list3.size()){
						rowSpan=list3.size();
					}
				}
			}
			String sb1s[] = new String[list1.size()];
			String sb2s[] = new String[list2.size()];
			String sb3s[] = new String[list3.size()];
			for(int x=0;x<list1.size();x++){
				StringBuffer sb1 = new StringBuffer("");
				BsBusievalInfo bsBusievalInfo = list1.get(x);
				sb1.append("<td align='center'>").append(String.valueOf(x+1)).append("&nbsp</td><td align='center'>")
				   .append(bsBusievalInfo.getEvalContent()).append("&nbsp</td><td align='center'>")
				   .append(bsBusievalInfo.getBaseScore()).append("</td>");
				sb1s[x] = sb1.toString();
			}
			for(int y=0;y<list2.size();y++){
				StringBuffer sb2 = new StringBuffer("");
				BsBusievalInfo bsBusievalInfo = list2.get(y);
				sb2.append("<td align='center'>").append(String.valueOf(y+1)).append("&nbsp</td><td align='center'>")
				   .append(bsBusievalInfo.getEvalRule()).append("&nbsp;</td><td align='center'>")
				   .append(bsBusievalInfo.getEvalContent()).append("&nbsp</td><td align='center'>")
				   .append(bsBusievalInfo.getDeScore()).append("</td>");
				sb2s[y] = sb2.toString();
			}
			for(int z=0;z<list3.size();z++){
				StringBuffer sb3 = new StringBuffer("");
				BsBusievalInfo bsBusievalInfo = list3.get(z);
				sb3.append("<td align='center'>").append(String.valueOf(z+1)).append("&nbsp</td><td align='center'>")
				   .append(bsBusievalInfo.getEvalContent()).append("&nbsp</td>");
				sb3s[z] = sb3.toString();
			}
			if(rowSpan<=0){
				sbTab.append("<tr align='center'><td  class='tabletext02'>").append(evalInfo).append("</td><td>&nbsp;</td><td>&nbsp;</td>")
					 .append("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			}
			if(rowSpan>0){
				for(int j=0;j<rowSpan;j++){
					if(j==0){
						sbTab.append("<tr align='center'><td rowspan='"+rowSpan+"' class='tabletext02'>").append(evalInfo).append("</td>");
					}
					if(j<sb1s.length){
						sbTab.append(sb1s[j]);
					}else{
						sbTab.append("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
					}
					if(j<sb2s.length){
						sbTab.append(sb2s[j]);
					}else{
						sbTab.append("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
					}
					if(j<sb3s.length){
						sbTab.append(sb3s[j]);
					}else{
						sbTab.append("<td>&nbsp;</td><td>&nbsp;</td>");
					}
					sbTab.append("</tr>");
				}
			}
		}
		return sbTab.toString();
	}
	
//			for(int j=0;j<rowSpan;j++){
//				sbTab.append("<tr align='center'><td rowspan='"+rowSpan+"'>").append(evalInfo).append("&nbsp;</td>");
//			}
//			
//			
//			
//			if(rowSpan == 1 || rowSpan == 0){
//				sbTab.append("<tr align='center'><td rowspan='"+rowSpan+"'>").append(evalInfo).append("&nbsp;</td>");
//				for(int j=0;j<rowSpan;j++){
//					
//				}
//				try{
//					value = map.get("1-1").toString();
//					sbTab.append(value.substring(0, 1));
//				}catch (Exception e){
//					value = "";
//					sbTab.append(value);
//				}
//				sbTab.append("&nbsp;</td>").append("<td>");
//			}else{
//				
//			}
//			基本情况</td><td colspan='4'>存在问题</td><td colspan='2'>加分情况</td></tr>");
//			
//		}
//		
//		
//		int a = 0;
//		int b = 0;
//		int c = 0;
//		int d = 0;
//		int e = 0;
//		int f = 0;
//		String m = "";
//		String n = "";
//		List<BsBusievalInfo> list = bsBusievalInfoDao.find(sb.toString());
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("1", "综合管理");
//		map.put("2", "系统建设");
//		map.put("3", "征信管理");
//		map.put("4", "征信宣传与维权");
//		map.put("5", "信用体系建设");
//		map.put("6", "加分项目");
//		
//		map.put("01", "基本情况");
//		map.put("02", "存在问题");
//		map.put("03", "加分项目");
//		
//		map.put("01-1", "序号");
//		map.put("01-2", "基本分");
//		map.put("01-3", "评价内容");
//		map.put("02-1", "序号");
//		map.put("02-2", "扣分");
//		map.put("02-3", "评价细则");
//		map.put("02-4", "评价内容");
//		map.put("03-1", "序号");
//		map.put("03-2", "评价内容");
//		
//		map.put("1-1-1", "");
//		map.put("1-1-2", "");
//		map.put("1-1-3", "");
//		map.put("1-1-4", "");
//		map.put("1-1-5", "");
//		map.put("1-1-6", "");
//		map.put("1-1-7", "");
//		map.put("1-1-8", "");
//		map.put("1-1-9", "");
//		map.put("1-1-10", "");
//		map.put("1-1-11", "");
//		for(int i=0;i<list.size();i++){
//			BsBusievalInfo obj = list.get(i);
//			String evalInfo = obj.getEvalInfo();
//			if("综合管理".equals(evalInfo)){
//				m = "1";
//				a++;
//				n = String.valueOf(a);
//			}else if("系统建设".equals(evalInfo)){
//				m = "2";
//				b++;
//				n = String.valueOf(b);
//			}else if("征信管理".equals(evalInfo)){
//				m = "3";
//				c++;
//				n = String.valueOf(c);
//			}else if("征信宣传与维权".equals(evalInfo)){
//				m = "4";
//				d++;
//				n = String.valueOf(d);
//			}else if("征信宣传与维权".equals(evalInfo)){
//				m = "5";
//				e++;
//				n = String.valueOf(e);
//			}else if("加分项目".equals(evalInfo)){
//				m = "6";
//				f++;
//				n = String.valueOf(f);
//			}
//				map.put(m+n+"-1", n);
//				map.put(m+n+"-2", obj.g);
//				map.put(m+n+"-3", n);
//				map.put(m+n+"-4", n);
//				map.put(m+n+"-5", n);
//				map.put(m+n+"-6", n);
//				map.put(m+n+"-7", n);
//				map.put(m+n+"-8", n);
//				map.put(m+n+"-9", n);
//				map.put(m+n+"-10", n);
//				map.put(m+n+"-11", n);
//			}
//		
//		}
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			BsBusievalInfo obj = (BsBusievalInfo) iterator.next();
//			String evalInfo = obj.getEvalInfo();
//			if("综合管理".equals(evalInfo)){
//				map
//			}
//			map.put(object.getDkey(), object.getDvalue());
//		}
//		
//		List list = jdbcTemplate.queryForList("select distinct(EVALINFO) from Bs_Busieval where EVALEDORGNO='"+evaledorgno+"' ");
//		String[] evalInfos = new String[list.size()];
//		StringBuffer sb = new StringBuffer("");
//		try {
//			for (int i = 0; i < list.size(); i++) {
//				ListOrderedMap map = (ListOrderedMap) list.get(i);
//				evalInfos[i] = String.valueOf(map.get("EVALINFO"));
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		sb.append("<tr align='center'><td rowspan='2'>&nbsp;</td><td colspan='4' align='center'>好的方面</td><td colspan='4'>不足之处</td><td colspan='4'>自我评价</td></tr>");
//		sb.append("<tr align='center'><td>序号</td><td>评价内容</td><td>评价时间</td><td>得分</td><td>序号</td><td>评价内容</td><td>评价时间</td><td>得分</td><td>序号</td><td>评价内容</td><td>评价时间</td><td>得分</td></tr>");
//		for(int j=0;j<evalInfos.length;j++){
//			StringBuffer sb1 = new StringBuffer("FROM BsBusieval where EvalInfo = '"+evalInfos[j]+"' and EvalCondition ='好的方面' ");
//			StringBuffer sb2 = new StringBuffer("FROM BsBusieval where EvalInfo = '"+evalInfos[j]+"' and EvalCondition ='不足之处' ");
//			StringBuffer sb3 = new StringBuffer("FROM BsBusieval where EvalInfo = '"+evalInfos[j]+"' and EvalCondition ='自我评价' ");
//			List<BsBusieval> list1 = bsBusievalDao.find(sb1.toString());
//			List<BsBusieval> list2 = bsBusievalDao.find(sb2.toString());
//			List<BsBusieval> list3 = bsBusievalDao.find(sb3.toString());
//			int x=0;
//			if(x<list1.size()){
//				x=list1.size();
//				if(x<list2.size()){
//					x=list2.size();
//					if(x<list3.size()){
//						x=list3.size();
//					}
//				}
//			}
//			
//			if(x>0){
//				for(int k=0;k<x;k++){
//					if(k==0){
//						sb.append("<tr align='center'><td rowspan='"+x+"'>").append(evalInfos[j]).append("</td>");
//					}else{
//						sb.append("<tr align='center'>");
//					}
//					sb.append("<td>").append(k+1).append("</td><td>").append(list1.get(k).getEvalContent()).append("</td><td>").append(list1.get(k).getEvaldt()).append("</td>");
//					sb.append("<td>").append(k+1).append("</td><td>").append(list1.get(k).getEvalContent()).append("</td><td>").append(list1.get(k).getEvaldt()).append("</td>");
//					sb.append("<td>").append(k+1).append("</td><td>").append(list1.get(k).getEvalContent()).append("</td><td>").append(list1.get(k).getEvaldt()).append("</td></tr>");
////					sb.append("<td>").append(k+1).append("</td><td>").append(list2.get(k).getEvalContent()).append("</td><td>").append(list2.get(k).getEvaldt()).append("</td><td>").append(list2.get(k).getScore()).append("</td><td>");
////					sb.append("<td>").append(k+1).append("</td><td>").append(list3.get(k).getEvalContent()).append("</td><td>").append(list3.get(k).getEvaldt()).append("</td><td>").append(list3.get(k).getScore()).append("</td></tr>");
//				}
//			}
//		}
//		return sb.toString();
//	}
}
