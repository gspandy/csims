package com.gtm.csims.jaas.action;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jguard.core.authorization.permissions.URLPermission;
import net.sf.jguard.ext.SecurityConstants;
import net.sf.jguard.ext.authorization.AuthorizationException;
import net.sf.jguard.ext.authorization.manager.AuthorizationManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gtm.csims.base.BaseAction;

/**
 * 权限域处理Action
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class DomainDispatchAction extends BaseAction {

    private static Logger logger = LoggerFactory
            .getLogger(DomainDispatchAction.class);

    /**
     * create an URLDomain.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm dyna = (DynaActionForm) form;
        String domainName = (String) dyna.get("domainName");
        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        try {
            am.createDomain(domainName);
        } catch (AuthorizationException e) {
            logger.error("domain " + domainName + " cannot be created", e);
        }
        return mapping.findForward("createDomainOK");

    }

    /**
     * delete an URLDomain.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm dyna = (DynaActionForm) form;
        String domainName = (String) dyna.get("domainName");
        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        try {
            am.deleteDomain(domainName);
        } catch (AuthorizationException e) {
            logger.error("domain " + domainName + " cannot be deleted ", e);
        }
        return mapping.findForward("deleteDomainOK");

    }

    /**
     * list URLDomains and permissions.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        Set domains = null;

        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        try {
            domains = am.listDomains();
        } catch (AuthorizationException e) {
            logger.error("domains  cannot be list ", e);
        }

        List domainsList = new ArrayList();
        if (domains != null) {
            Iterator itDomains = domains.iterator();
            while (itDomains.hasNext()) {
                domainsList.add(itDomains.next());
            }
        }

        dyna.set("Domains", domainsList);
        if (System.getSecurityManager() != null) {
            try {
                AccessController.checkPermission(new URLPermission("toto",
                        "/jGuardExample/AccessDenied.do,http,blabla"));
            } catch (SecurityException sex) {
                logger.error(" access is denied ", sex);
            } catch (IllegalArgumentException e) {
                logger.error(" bad uri synthax ", e);
            }
        }
        return mapping.findForward("listDomainsOK");

    }

    /**
     * update an URLDomain name.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        try {
            am.updateDomain((String) dyna.get("domainName"), (String) dyna
                    .get("oldDomainName"));

        } catch (AuthorizationException e) {
            logger.error("domain " + (String) dyna.get("domainName")
                    + " cannot be updated", e);
        }

        return mapping.findForward("updateDomainOK");

    }
}
