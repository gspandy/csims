package com.gtm.csims.business.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsAnswerresultDAO;
import com.gtm.csims.dao.BsQuestionDAO;
import com.gtm.csims.dao.BsQuestionaireDAO;
import com.gtm.csims.dao.BsSurveyobjectDAO;
import com.gtm.csims.model.BsQuestionaire;

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

	/**
	 * 
	 * @param qtitle
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page queryQuestionairs(String qtitle, Integer pageNo, Integer pageSize) {
		StringBuffer sb = new StringBuffer("FROM BsQuestionaire  ");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.isNotBlank(qtitle)) {
			sb.append(" where qtitle like  ? ");
			param.add(qtitle.trim() + "%");
		}
		sb.append(" order by createdate DESC");
		Page page = bsQuestionaireDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
		return page;
	}

	/**
	 * 新增问卷调查.
	 * 
	 */
	@Transactional(readOnly = false)
	public void create(BsQuestionaire questionaire) {
		if (questionaire == null) {
			throw new IllegalArgumentException("对象为空");
		}
		if (StringUtils.isBlank(questionaire.getQtitle())) {
			throw new IllegalArgumentException("名称为空");
		}
		if (questionaire.getQtitle().length() > 500) {
			throw new IllegalArgumentException("名称超过允许长度500");
		}
		if (StringUtils.isBlank(questionaire.getId())) {
			questionaire.setCreatedate(new Date());
		} else {
			questionaire.setUpdateate(new Date());
		}
		bsQuestionaireDao.save(questionaire);
	}

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

}
