package com.gtm.csims.jaas.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gtm.csims.dao.BsOrgDAO;

import com.gtm.csims.model.BsOrg;
import org.springframework.transaction.annotation.Transactional;

/**
 * jguard权限相关功能实现类
 * 
 * @author Sweet
 * 
 */
public class JGuardDataAuthorizationService {
    private BsOrgDAO bsOrgDao;

    public void setBsOrgDao(BsOrgDAO bsOrgDao) {
        this.bsOrgDao = bsOrgDao;
    }

    @SuppressWarnings("unchecked")
    public String listChildrenOrganizationsStr(String orgNo,
            Boolean containSelf, Boolean hasQuote) {
        List resultList = new ArrayList();
        List nextChildOrgList = null;
        BsOrg bs = new BsOrg();
        bs = bsOrgDao.get(orgNo);
        resultList.add(bs);
        nextChildOrgList = getNextChildOrgList("'" + bs.getNo() + "'");
        if (nextChildOrgList.size() != 0) {
            iteratorAddChildOrg(resultList, nextChildOrgList);
        }
        String resultOrgSting = "";
        for (int i = 0; i < resultList.size(); i++) {
            BsOrg orgObject = (BsOrg) resultList.get(i);
            if (!"A1000111000031".equals(orgObject.getNo())) {
                resultOrgSting += "'" + orgObject.getNo() + "'";
                if (i != resultList.size() - 1) {
                    resultOrgSting += ",";
                }
            }
        }
        return resultOrgSting.length() == 0 ? "''" : resultOrgSting;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    private List getNextChildOrgList(String queryOrgIdString) {
        StringBuffer hql = new StringBuffer();
        hql.append("From BsOrg ba Where ba.Parentno in (" + queryOrgIdString
                + ") ");
        return bsOrgDao.find(hql.toString());
    }

    @SuppressWarnings("unchecked")
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

}
