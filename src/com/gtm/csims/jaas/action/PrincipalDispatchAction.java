package com.gtm.csims.jaas.action;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jguard.core.CoreConstants;
import net.sf.jguard.core.authorization.permissions.Domain;
import net.sf.jguard.core.authorization.permissions.JGPermissionCollection;
import net.sf.jguard.core.principals.RolePrincipal;
import net.sf.jguard.ext.SecurityConstants;
import net.sf.jguard.ext.authentication.AuthenticationException;
import net.sf.jguard.ext.authentication.manager.AuthenticationManager;
import net.sf.jguard.ext.authorization.AuthorizationException;
import net.sf.jguard.ext.authorization.manager.AuthorizationManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.log.level.Logger;
import com.gtm.csims.utils.DateUtil;

/**
 * 角色处理Action
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public class PrincipalDispatchAction extends BaseAction {

    private static final Integer PAGE_CONTAIN_NUMBER = 15;

    /**
     * list principals (Principals).
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */

    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
                .equals("")) ? "1" : (String) dyna.get("pageNo");
        Set roles = null;

        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        roles = am.listPrincipals();
        String rolename = request.getParameter("rolename");
        if (rolename != null && !rolename.trim().equals("")) {
            roles = this.filteRole(roles, rolename);
        }
        Iterator itPrincipals = roles.iterator();
        List principalsList = new ArrayList();
        while (itPrincipals.hasNext()) {
            RolePrincipal role = (RolePrincipal) itPrincipals.next();
            String roleNm = role.getLocalName();
            if (!"admin".equals(roleNm) && !"guest".equals(roleNm)
                    && !"systemadmin".equals(roleNm)) {
                principalsList.add(role);
            }
        }
        // principalsList.r

        // 统计总共的页数
        int pageCountTemp = principalsList.size();
        if (pageCountTemp == 0) {
            pageCountTemp = 1;
        }
        if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0) {
            pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
        } else {
            pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
        }

        /*
         * 判断是否显示上一页或下一页
         */
        if (request.getParameter("btnType") != null
                && request.getParameter("btnType").equals("1")) {
            pageNo = "1";
        }
        if (Integer.valueOf(pageNo) != 1) {
            dyna.set("previous", "1");
        } else {
            dyna.set("previous", "0");
        }
        if (Integer.valueOf(pageNo) != pageCountTemp) {
            dyna.set("next", "1");
        } else {
            dyna.set("next", "0");
        }

        dyna.set("pageNo", pageNo);
        dyna.set("pageCount", pageCountTemp);
        List returnList = new ArrayList();
        int startIndex = PAGE_CONTAIN_NUMBER * (Integer.parseInt(pageNo) - 1);
        int endIndex = PAGE_CONTAIN_NUMBER * Integer.parseInt(pageNo);
        for (int i = startIndex; i < endIndex; i++) {
            if (i < principalsList.size()) {
                returnList.add(principalsList.get(i));
            }
        }
        PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
        dyna.set("principals", returnList);
        request.setAttribute("principalss", principalsList);
        return mapping.findForward("listPrincipalsOK");

    }

    private Set filteRole(Set roles, String searchRoleName) {
        Set newSet = new HashSet();
        Iterator itPrincipals = roles.iterator();
        while (itPrincipals.hasNext()) {
            RolePrincipal role = (RolePrincipal) itPrincipals.next();
            if (role.getName().indexOf(searchRoleName.trim()) != -1) {
                newSet.add(role);
            }
        }
        return newSet;
    }

    /**
     * Delete inheritance between two existing principals.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteInheritance(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);

        try {
            am.deleteInheritance((String) dyna.get("principalAsc"),
                    (String) dyna.get("principalDesc"));

        } catch (AuthorizationException e) {
            e.printStackTrace();
        }

        return mapping.findForward("addInheritanceOK");
    }

    /**
     * Add inheritance beteween two existings principals.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addInheritance(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);

        try {
            am.addInheritance((String) dyna.get("principalAsc"), (String) dyna
                    .get("principalDesc"));
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }

        return mapping.findForward("addInheritanceOK");
    }

    /**
     * create a new role(Principal).
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        DynaActionForm dyna = (DynaActionForm) form;
        AuthorizationManager authorizationManager = (AuthorizationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        AuthenticationManager authenticationManager = (AuthenticationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHENTICATION_MANAGER);

        try {
            RolePrincipal principal = new RolePrincipal((String) dyna
                    .get("principalName"), request.getSession()
                    .getServletContext().getServletContextName());
            authorizationManager.createPrincipal(principal);
            authenticationManager.createPrincipal(principal);

            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "创建角色【"
                    + principal.getName() + "】成功！");

        } catch (AuthorizationException e) {
            Logger.error(e.getMessage());
        } catch (AuthenticationException e) {
            Logger.error(e.getMessage());
        }

        return mapping.findForward("createPrincipalOK");

    }

    /**
     * create a new role(Principal).
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
        AuthorizationManager authorizationManager = (AuthorizationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        AuthenticationManager authenticationManager = (AuthenticationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHENTICATION_MANAGER);
        RolePrincipal principal = new RolePrincipal((String) dyna
                .get("principalName"), (String) request.getSession()
                .getServletContext().getAttribute(
                        CoreConstants.APPLICATION_NAME));
        String domainNames = (String) dyna.get("domainNames");
        String permissionNames = (String) dyna.get("permissionNames");
        String[] domNames = domainNames.split("#");
        String[] permNames = permissionNames.split("#");
        if (!domainNames.equals("")) {
            Set doms = authorizationManager.getDomains(Arrays.asList(domNames));
            principal.setDomains(doms);
        }
        if (!permissionNames.equals("")) {
            Set perms = authorizationManager.getPermissions(Arrays
                    .asList(permNames));
            principal.setPermissions(perms);
        }

        try {
            authorizationManager.updatePrincipal((String) dyna
                    .get("oldPrincipalName"), principal);
            authenticationManager.updatePrincipal((String) dyna
                    .get("oldPrincipalName"), principal);

            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "修改角色【"
                    + (String) dyna.get("oldPrincipalName") + "】成功！");
        } catch (AuthorizationException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return mapping.findForward("updatePrincipalOK");

    }

    public ActionForward updateCheckBox(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm dyna = (DynaActionForm) form;
        AuthorizationManager authorizationManager = (AuthorizationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        AuthenticationManager authenticationManager = (AuthenticationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHENTICATION_MANAGER);
        RolePrincipal principal = new RolePrincipal((String) dyna
                .get("principalName"), (String) request.getSession()
                .getServletContext().getAttribute(
                        CoreConstants.APPLICATION_NAME));
        String[] domNames = request.getParameterValues("domains");
        String[] permNames = request.getParameterValues("permissions");
        Logger.debug("domains is :" + this.arrayToStr(domNames));
        Logger.debug("permissions is :" + this.arrayToStr(permNames));
        if (!this.arrayToStr(domNames).equals("")) {
            Set doms = authorizationManager.getDomains(Arrays.asList(domNames));
            principal.setDomains(doms);
        }
        if (!this.arrayToStr(permNames).equals("")) {
            Set perms = authorizationManager.getPermissions(Arrays
                    .asList(permNames));
            principal.setPermissions(perms);
        }

        try {
            authorizationManager.updatePrincipal((String) dyna
                    .get("oldPrincipalName"), principal);
            authenticationManager.updatePrincipal((String) dyna
                    .get("oldPrincipalName"), principal);

            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "修改角色【"
                    + (String) dyna.get("oldPrincipalName") + "】成功！");

        } catch (AuthorizationException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return mapping.findForward("updatePrincipalOK");
    }

    private String arrayToStr(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append("#");
            }
        }
        return sb.toString();
    }

    /**
     * create a new role(Principal).
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
        AuthorizationManager authorizationManager = (AuthorizationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        AuthenticationManager authenticationManager = (AuthenticationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHENTICATION_MANAGER);
        try {
            authorizationManager.deletePrincipal(new RolePrincipal(
                    (String) dyna.get("principalName")));
            authenticationManager.deletePrincipal(new RolePrincipal(
                    (String) dyna.get("principalName")));

            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "删除角色【"
                    + (String) dyna.get("principalName") + "】成功！");
        } catch (AuthorizationException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return mapping.findForward("deletePrincipalOK");

    }

    /**
     * read a role(Principal).
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward read(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm dyna = (DynaActionForm) form;
        AuthorizationManager authorizationManager = (AuthorizationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        RolePrincipal principal = null;
        Set domains = null;
        Set domainsNotBound = null;
        try {
            principal = (RolePrincipal) authorizationManager
                    .readPrincipal((String) dyna.get("principalName"));
            domains = authorizationManager.listDomains();
            domainsNotBound = new HashSet(domains);
        } catch (AuthorizationException e) {
            Logger.error("principal " + (String) dyna.get("principalName")
                    + " cannot be read");
        }
        domainsNotBound.removeAll(principal.getDomains());
        List domainsNBList = new ArrayList(domainsNotBound);
        dyna.set("DomainsNotBound", domainsNBList);

        dyna.set("principal", principal);
        List domainsList = new ArrayList(domains);
        dyna.set("Domains", domainsList);

        // permissions not owned by role directly
        // and not owned by a domain owned by a role
        Set permissionsNotBound = new HashSet();
        Iterator itDomainsNB = domainsNotBound.iterator();
        while (itDomainsNB.hasNext()) {
            Domain domainTmp = (Domain) itDomainsNB.next();
            try {
                permissionsNotBound.add(domainTmp.clone());
            } catch (CloneNotSupportedException e1) {
                Logger.error(" domain " + domainTmp + " cannot be cloned");
            }
        }
        Iterator itPermNB = permissionsNotBound.iterator();
        while (itPermNB.hasNext()) {
            JGPermissionCollection domain = (JGPermissionCollection) itPermNB
                    .next();
            domain.getPermissions().removeAll(
                    principal.getOrphanedPermissions());
        }
        List permissionsNB = new ArrayList(permissionsNotBound);

        dyna.set("permissionsNotBound", permissionsNB);
        return mapping.findForward("readPrincipalOK");

    }

    public ActionForward readAsCheckBox(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        AuthorizationManager authorizationManager = (AuthorizationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        RolePrincipal principal = null;
        Set domains = null;
        Map<String, Set<?>> domainPermissionsMap = new HashMap<String, Set<?>>();
        try {
            principal = (RolePrincipal) authorizationManager
                    .readPrincipal((String) dyna.get("principalName"));
            request.setAttribute("principalName", principal.getLocalName());
            domains = authorizationManager.listDomains();
            for (Iterator iterator = domains.iterator(); iterator.hasNext();) {
                Domain domain = (Domain) iterator.next();
                domainPermissionsMap.put(domain.getName(), domain
                        .getPermissions());
            }
            // 取得系统所有权限域
            request.setAttribute("allDomains", domains);
            // 取得所有权限域所对应的所有权限
            request.setAttribute("allDomainPermissionsMap",
                    domainPermissionsMap);
        } catch (AuthorizationException e) {
            Logger.error("principal " + (String) dyna.get("principalName")
                    + " cannot be read");
        }
        // 取得当前角色所拥有的权限域
        request.setAttribute("hasDomains",
                new ArrayList(principal.getDomains()));
        // 取得当前角色所拥有的孤立权限（非权限域）
        request.setAttribute("hasOrphanedPermissions", principal
                .getOrphanedPermissions());
        return mapping.findForward("readPrincipalCheckBoxOK");

    }

    /**
     * clone a role(Principal).
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward clone(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm dyna = (DynaActionForm) form;
        String principalToClone = (String) dyna.get("principalName");
        AuthorizationManager authorizationManager = (AuthorizationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        AuthenticationManager authenticationManager = (AuthenticationManager) request
                .getSession().getServletContext().getAttribute(
                        SecurityConstants.AUTHENTICATION_MANAGER);
        try {
            Principal clonedPpal = authorizationManager
                    .clonePrincipal(principalToClone);
            authenticationManager.createPrincipal(clonedPpal);
        } catch (AuthorizationException e) {
            Logger.error(e.getMessage());
        } catch (AuthenticationException e) {
            Logger.error(e.getMessage());
        }
        return mapping.findForward("clonePrincipalOK");
    }

}
