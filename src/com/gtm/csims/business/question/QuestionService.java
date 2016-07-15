package com.gtm.csims.business.question;

import java.util.ArrayList;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsAnswerresultDAO;
import com.gtm.csims.dao.BsQuestionDAO;
import com.gtm.csims.dao.BsQuestionaireDAO;
import com.gtm.csims.dao.BsSurveyobjectDAO;
import com.gtm.csims.model.BsAnswerresult;
import com.gtm.csims.model.BsQuestion;
import com.gtm.csims.model.BsQuestionaire;
import com.gtm.csims.model.BsSurveyobject;

/**
 * 问卷调查.
 * 
 * @author sweet
 * 
 */
public class QuestionService {

	private JdbcTemplate jdbcTemplate;
	private BsQuestionaireDAO bsQuestionaireDao;
	private BsQuestionDAO bsQuestionDao;
	private BsSurveyobjectDAO bsSurveyobjectDao;
	private BsAnswerresultDAO bsAnswerresultDao;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setBsQuestionaireDao(BsQuestionaireDAO bsQuestionaireDao) {
		this.bsQuestionaireDao = bsQuestionaireDao;
	}

	public void setBsQuestionDao(BsQuestionDAO bsQuestionDao) {
		this.bsQuestionDao = bsQuestionDao;
	}

	public void setBsSurveyobjectDao(BsSurveyobjectDAO bsSurveyobjectDao) {
		this.bsSurveyobjectDao = bsSurveyobjectDao;
	}

	public void setBsAnswerresultDao(BsAnswerresultDAO bsAnswerresultDao) {
		this.bsAnswerresultDao = bsAnswerresultDao;
	}

	/**
	 * 
	 * @param qtitle
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page queryQuestionairs(String qtitle, Integer pageNo,
			Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsQuestionaire  ");
		List<Object> param = new ArrayList<Object>();
		if (!"".equals(qtitle)) {
			sb.append(" where Qtitle like  ? ");
			param.add("%" + qtitle.trim() + "%");
		}
		sb.append(" order by Createdate DESC");
		Page page = bsQuestionaireDao.pagedQuery(sb.toString(), pageNo,
				pageSize, param.toArray());
		return page;
	}

	/**
	 * 
	 * @param qtitle
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page queryAnswerQuestionairs(String qtitle, String loginOrgNo,
			Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer(
				"FROM BsSurveyobject where BsQuestionaire.Status <> '0' ");
		List<Object> param = new ArrayList<Object>();
		sb.append(" and Soqorgno =  ? ");
		param.add(loginOrgNo.trim());
		if (!"".equals(qtitle)) {
			sb.append(" and BsQuestionaire.Qtitle like ? ");
			param.add("%" + qtitle.trim() + "%");
		}
		sb.append(" order by Createdate DESC");
		Page page = bsSurveyobjectDao.pagedQuery(sb.toString(), pageNo,
				pageSize, param.toArray());
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page queryBsSurveyobject(String qid, String orgNo,
			Integer pageNo, Integer pageSize) {
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"FROM BsSurveyobject where ");
		sb.append(" BsQuestionaire.Id =  ? ");
		param.add(qid.trim());
		if (!"".equals(orgNo)) {
			sb.append(" and Soqorgno =  ? ");
			param.add(orgNo.trim());
		}
		sb.append(" order by BsQuestionaire.Createdate DESC");
		Page page = bsSurveyobjectDao.pagedQuery(sb.toString(), pageNo,
				pageSize, param.toArray());
		return page;
	}

	@Transactional(readOnly = true)
	public BsSurveyobject getBsSurveyobject(String id) {
		return bsSurveyobjectDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public BsQuestionaire getBsQuestionaireById(String id) {
		return bsQuestionaireDao.get(id);
	}

	@Transactional(readOnly = true)
	public BsQuestion getBsQuestionById(String id) {
		return bsQuestionDao.get(id);
	}

	@Transactional(readOnly = true)
	public BsSurveyobject getBsSurveyobjectById(String id) {
		return bsSurveyobjectDao.get(id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<BsQuestion> getBsQuestionByQuestionaireId(String qid) {
		StringBuffer sb = new StringBuffer("FROM BsQuestion ");
		List<Object> param = new ArrayList<Object>();
		sb.append(" where BsQuestionaire.Id =  ? ");
		param.add(qid.trim());
		sb.append(" order by Createdate ASC");
		return bsQuestionDao.find(sb.toString(), param.toArray());
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<BsSurveyobject> getBsSurveyobjectByQuestionaireId(String pid) {
		StringBuffer sb = new StringBuffer("FROM BsSurveyobject ");
		List<Object> param = new ArrayList<Object>();
		sb.append(" where BsQuestionaire.Id =  ? ");
		param.add(pid.trim());
		return bsSurveyobjectDao.find(sb.toString(), param.toArray());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<BsAnswerresult> getBsAnswerresultsByOrgnoAndQqid(String orgNo,String qqid,String result) {
		StringBuffer sb = new StringBuffer("FROM BsAnswerresult where ");
		List<Object> param = new ArrayList<Object>();
		sb.append("  BsQuestion.Id =  ? ");
		param.add(qqid.trim());
		sb.append(" and Arorgno =  ? ");
		param.add(orgNo.trim());
		sb.append(" and Answerresult =  ? ");
		param.add(result.trim());
		return bsAnswerresultDao.find(sb.toString(), param.toArray());
	}

	/**
	 * 新增问卷调查.
	 * 
	 */
	@Transactional(readOnly = false)
	public void saveBsQuestionaire(BsQuestionaire bs) {
		bsQuestionaireDao.save(bs);
	}

	@Transactional(readOnly = false)
	public void saveBsQuestion(BsQuestion bs) {
		bsQuestionDao.save(bs);
	}
	
	@Transactional(readOnly = false)
	public void savaBsAnserResult(BsAnswerresult bs) {
		bsAnswerresultDao.save(bs);
	}

	@Transactional(readOnly = false)
	public void delBsQuestion(BsQuestion bs) {
		bsQuestionDao.remove(bs);
	}

	@Transactional(readOnly = false)
	public void delBsSurveyobject(BsSurveyobject bs) {
		bsSurveyobjectDao.remove(bs);
	}

	@Transactional(readOnly = false)
	public void delBsQuestionaire(String qid) {
		jdbcTemplate.execute(" delete from BS_QUESTION where BSQUESTIONAIRE='"
				+ qid + "' ");
		jdbcTemplate
				.execute(" delete from BS_SURVEYOBJECT where BSQUESTIONAIRE='"
						+ qid + "' ");
		jdbcTemplate.execute(" delete from BS_QUESTIONAIRE where ID='" + qid
				+ "' ");
	}
	
	@Transactional(readOnly = false)
	public void delAnserResult(String qid,String orgno) {
		jdbcTemplate.execute(" delete from BS_ANSWERRESULT where BSQUESTIONAIRE='" + qid
				+ "' and ARORGNO='"+orgno+"' ");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getBsQuestionStrByQuestionaireId(String qid,String qtitle,String orgNo) {
		List<BsQuestion> list = this.getBsQuestionByQuestionaireId(qid);
		StringBuffer sb = new StringBuffer("");
		sb.append("<tr><td class='text' align='center'>").append(qtitle).append("<br>").append("问卷调查</td></tr>");
		BsQuestion bs = new BsQuestion();
		List<BsAnswerresult> bsAnswerresultList = new ArrayList();
		for(int i=0;i<list.size();i++){
			bs = list.get(i);
			sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;").append(i+1).append("、").append(bs.getQqtitle().trim()).append("</td></tr>");
			if(!"".equals(bs.getAnswera().trim())){
				sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				  .append("<input type='checkbox' name='checkbox'"); 
				bsAnswerresultList = this.getBsAnswerresultsByOrgnoAndQqid(orgNo,bs.getId(),"A");
				if(bsAnswerresultList.size()>0){
					sb.append(" checked ");
				}
				sb.append("value='").append(bs.getId()).append("-A'>A:").append(bs.getAnswera()).append("</td></tr>");
			}
			if(!"".equals(bs.getAnswerb().trim())){
				sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				  .append("<input type='checkbox' name='checkbox'");
				bsAnswerresultList = this.getBsAnswerresultsByOrgnoAndQqid(orgNo,bs.getId(),"B");
				if(bsAnswerresultList.size()>0){
					sb.append(" checked ");
				}
				sb.append("value='").append(bs.getId()).append("-B'>B:").append(bs.getAnswerb()).append("</td></tr>");
			}
			if(!"".equals(bs.getAnswerc().trim())){
				sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				  .append("<input type='checkbox' name='checkbox'");
				bsAnswerresultList = this.getBsAnswerresultsByOrgnoAndQqid(orgNo,bs.getId(),"C");
				if(bsAnswerresultList.size()>0){
					sb.append(" checked ");
				}
				sb.append("value='").append(bs.getId()).append("-C'>C:").append(bs.getAnswerc()).append("</td></tr>");
			}
			if(!"".equals(bs.getAnswerd().trim())){
				sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				  .append("<input type='checkbox' name='checkbox'");
				bsAnswerresultList = this.getBsAnswerresultsByOrgnoAndQqid(orgNo,bs.getId(),"D");
				if(bsAnswerresultList.size()>0){
					sb.append(" checked ");
				}
				sb.append("value='").append(bs.getId()).append("-D'>D:").append(bs.getAnswerd()).append("</td></tr>");
			}
			if(!"".equals(bs.getAnswere().trim())){
				sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				  .append("<input type='checkbox' name='checkbox'");
				bsAnswerresultList = this.getBsAnswerresultsByOrgnoAndQqid(orgNo,bs.getId(),"E");
				if(bsAnswerresultList.size()>0){
					sb.append(" checked ");
				}
				sb.append("value='").append(bs.getId()).append("-E'>E:").append(bs.getAnswere()).append("</td></tr>");
			}
			if(!"".equals(bs.getAnswerf().trim())){
				sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				  .append("<input type='checkbox' name='checkbox'");
				bsAnswerresultList = this.getBsAnswerresultsByOrgnoAndQqid(orgNo,bs.getId(),"F");
				if(bsAnswerresultList.size()>0){
					sb.append(" checked ");
				}
				sb.append("value='").append(bs.getId()).append("-F'>F:").append(bs.getAnswerf()).append("</td></tr>");
			}
			if(!"".equals(bs.getAnswerg().trim())){
				sb.append("<tr><td class='text' align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
				  .append("<input type='checkbox' name='checkbox'");
				bsAnswerresultList = this.getBsAnswerresultsByOrgnoAndQqid(orgNo,bs.getId(),"G");
				if(bsAnswerresultList.size()>0){
					sb.append(" checked ");
				}
				sb.append("value='").append(bs.getId()).append("-G'>G:").append(bs.getAnswerg()).append("</td></tr>");
			}
		}
		return sb.toString();
	}
}
