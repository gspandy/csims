package com.gtm.csims.business.managment.system;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gtm.csims.dao.BsDeptDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.jaas.service.UserService;
import com.gtm.csims.model.BsDept;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsUserInfoOfJg;

/**
 * @ClassName: ${SelectSystemDataBaseByDwr}
 * @Description: ${Dwr处理异步请求}
 * @author qhy
 * @date ${date} ${time}
 * 
 */
@SuppressWarnings("unchecked")
public class SelectSystemDataBaseByDwr {
	private BsOrgDAO bsOrgDao;
	private JdbcTemplate jdbcTemplate;
	private BsDeptDAO bsDeptDao;
	private UserService userService;

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	public void setBsDeptDao(BsDeptDAO bsDeptDao) {
		this.bsDeptDao = bsDeptDao;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String[] getOrgByNo(String no) {
		String[] result = new String[17];
		BsOrg bs = bsOrgDao.get(no);
		result[0] = bs.getName();
		result[1] = bs.getNo();
		try{
			if(bs.getOrgCodeOfZz() == null){
				result[2] = "";
			}else{
				result[2] = bs.getOrgCodeOfZz();
			}
			if(bs.getOrgCodeOfXy() == null){
				result[3]="";
			}else{
				result[3] = bs.getOrgCodeOfXy();
			}
			if(bs.getE() == null){
				result[4] = "";
			}else{
				result[4] = bs.getE();
			}
			if(bs.getG() == null){
				result[5] = "";
			}else{
				result[5] = bs.getG();
			}
			if(bs.getJ() == null){
				result[6] = "";
			}else{
				result[6] = bs.getJ();
			}
			if(bs.getK() == null){
				result[7] = "";
			}else{
				result[7] = bs.getK();
			}
			if(bs.getL() == null){
				result[8] = "";
			}else{
				result[8] = bs.getL();
			}
			if(bs.getM() == null){
				result[9] = "";
			}else{
				result[9] = bs.getM();
			}
			if(bs.getO() == null){
				result[10] = "";
			}else{
				result[10] = bs.getO();
			}
			if(bs.getQ() == null){
				result[11] = "";
			}else{
				result[11] = bs.getQ();
			}
			if(bs.getS() == null){
				result[12] = "";
			}else{
				result[12] = bs.getS();
			}
			if(bs.getT() == null){
				result[13] = "";
			}else{
				result[13] = bs.getT();
			}
			if(bs.getZ() == null){
				result[14] = "";
			}else{
				result[14] = bs.getZ();
			}
			if(bs.getAa() == null){
				result[15] = "";
			}else{
				result[15] = bs.getAa();
			}
			if(bs.getIspcb() == null){
				result[16] = "";
			}else{
				result[16] = bs.getIspcb();
			}
		}catch (Exception e){
			result[2]="";
			result[3]="";
			result[4]="";
		}
		
		return result;
	}
	
	public String[] getOrgInfo(String orgNo){
		String[] result = new String[3];
		BsOrg bs = bsOrgDao.get(orgNo);
		result[0] = orgNo;
		try{
			if(bs.getOrgCodeOfZz() == null){
				result[1] = "";
			}else{
				result[1] = bs.getOrgCodeOfZz();
			}
			if(bs.getOrgCodeOfXy() == null){
				result[2]="";
			}else{
				result[2] = bs.getOrgCodeOfXy();
			}
		}catch (Exception e){
			result[1]="";
			result[2]="";
		}
		return result;
	}
	
	public String[] getPcbDept(String orgno) {
		List<BsDept> list = bsDeptDao.find(
				"from BsDept b where b.orgno ='" + orgno + "'");
		String[] result = new String[list.size()];
		for(int i=0;i<list.size();i++){
			BsDept bs = list.get(i);
			result[i] = bs.getId() +"-"+bs.getName();
		}
		return result;
	}
	
	public String[] getDept(String orgno) {
		
		List<BsDept> list = bsDeptDao.find(
				"from BsDept where Orgno ='" + orgno + "'");
		String[] result = new String[list.size()];
		for(int i=0;i<list.size();i++){
			BsDept bs = list.get(i);
			result[i] = bs.getId() +"-"+bs.getName();
		}
		return result;
	}
	
	public String[] getAepeople(String orgno,String deptno) {
		
//		List<BsDept> list = bsDeptDao.find(
//				"from BsDept b where b.orgno ='" + orgno + "'");
//		String[] result = new String[list.size()];
//		for(int i=0;i<list.size();i++){
//			BsDept bs = list.get(i);
//			result[i] = bs.getDeptNo() +"-"+bs.getDeptName();
//		}
		String[] result = new String[3];
		result[0] = "51082419851031575X" + "-" + "邱洋";
		result[1] = "N02" + "-" + "李四";
		result[2] = "N03" + "-" + "王五";
		return result;
	}
	
//	public String[] getAepeopleInfo(String loginId) {
//		BsUserInfo bs = userService.getBsUserInfoByLoginId(loginId);
//		String[] result = new String[4];
//		try{
//			if(bs.getPost() == null){
//				result[1] = "";
//			}else{
//				result[1] = bs.getPost();
//			}
//			if(bs.getPhone() == null){
//				result[2]="";
//			}else{
//				result[2] = bs.getPhone();
//			}
//			if(bs.getName() == null){
//				result[3]="";
//			}else{
//				result[3] = bs.getName();
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//			result[1]="";
//			result[2]="";
//		}
//		result[0] = loginId;
//		return result;
//	}
	
	public String getUserCheckboxByOrgNo(String orgNo) {
		StringBuffer sb = new StringBuffer("<td align='left' width='10%' height='25'>");
		List<BsUserInfoOfJg> list = userService.getUserByOrgNo(orgNo);
		for(int i=0;i<list.size();i++){
			BsUserInfoOfJg bs = list.get(i);
			sb.append("<input type='checkbox' name='userCheckbox' value='").append(bs.getName()).append("' />").append(bs.getName()).append("<br>");
		}
		sb.append("</td>");
		return sb.toString();
	}
	
}
