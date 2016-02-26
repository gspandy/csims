package com.gtm.csims.jaas.action;

import java.security.Permission;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jguard.core.authorization.permissions.PermissionUtils;
import net.sf.jguard.ext.SecurityConstants;
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
 * 权限处理Action
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class PermissionDispatchAction extends BaseAction {

    /**
     * create a new URLPermission.
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
        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        // net.sf.jguard.jee.authentication.http.AccessFilter a;
        Permission permission = null;
        try {
            permission = getPermission(dyna);
            am.createPermission(permission, (String) dyna.get("domainName"));

            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "创建权限【"
                    + permission.getName() + "】成功！");

        } catch (AuthorizationException e) {
            Logger.error(" permission " + permission.getName() + " in domain "
                    + (String) dyna.get("domainName") + " not created ");
        } catch (ClassNotFoundException e) {
            Logger.error(" permission " + permission.getName() + " in domain "
                    + (String) dyna.get("domainName") + " not created ");
        }

        return mapping.findForward("createPermissionOK");

    }

    /**
     * delete permission.
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
        AuthorizationManager am = (AuthorizationManager) request.getSession()
                .getServletContext().getAttribute(
                        SecurityConstants.AUTHORIZATION_MANAGER);
        try {
            am.deletePermission((String) dyna.get("permissionName"));

            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "删除权限【"
                    + (String) dyna.get("permissionName") + "】成功！");

        } catch (AuthorizationException e) {
            Logger.error(" permission " + (String) dyna.get("permissionName")
                    + " not deleted ");
        }

        return mapping.findForward("deletePermissionOK");

    }

    /**
     * update permission.
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
        Permission permission = null;

        try {
            permission = getPermission(dyna);
            am.updatePermission((String) dyna.get("oldPermissionName"),
                    permission, (String) dyna.get("domainName"));

            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "修改权限【"
                    + (String) dyna.get("oldPermissionName") + "】成功！");

        } catch (AuthorizationException e) {
            Logger.error(" permission "
                    + (String) dyna.get("oldPermissionName") + " not updated ");
        } catch (ClassNotFoundException e) {
            Logger.error(" permission "
                    + (String) dyna.get("oldPermissionName") + " not updated ");
        }

        return mapping.findForward("updatePermissionOK");

    }

    private Permission getPermission(DynaActionForm dyna)
            throws ClassNotFoundException {
        String permissionName = (String) dyna.get("permissionName");
        String permissionClassName = (String) dyna.get("permissionClass");
        String actions = (String) dyna.get("permissionActions");
        Permission permission = PermissionUtils.getPermission(
                permissionClassName, permissionName, actions);
        return permission;
    }
}
