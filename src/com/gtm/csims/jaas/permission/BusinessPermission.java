package com.gtm.csims.jaas.permission;

import java.security.BasicPermission;

import com.gtm.csims.log.level.Logger;

/**
 * 业务权限类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("serial")
public class BusinessPermission extends BasicPermission {

    private String actions;

    public BusinessPermission(String name, String actions) {
        super(name);
        this.actions = actions;
    }

    @Override
    public boolean implies(java.security.Permission permission) {
        BusinessPermission busipTemp = null;
        if (!(permission instanceof BusinessPermission)) {
            Logger.debug(" permission is not an BusinessPermission. type = "
                    + permission.getClass().getName());
            return false;
        }

        busipTemp = (BusinessPermission) permission;

        if (this.equals(permission)) {
            return true;
        }

        // test actions
        String busiTempActions = busipTemp.getActions();
        if (busiTempActions == null || "".equals(busiTempActions)) {
            if (actions == null || "".equals(actions)) {
                return true;
            }
            return false;
        }
        return this.impliesResource(busiTempActions);
    }

    private boolean impliesResource(String busiTempActions) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object permission) {
        if (permission == null)
            return false;
        if (this.actions == null)
            return false;
        if (permission instanceof BusinessPermission) {
            BusinessPermission temp = (BusinessPermission) permission;
            if (temp.getActions() == null)
                return false;
            else {
                return this.actions.equals((temp.getActions()));
            }
        }
        return false;
    }
}
