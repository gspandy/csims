package com.gtm.csims.jaas.action;

import java.io.Serializable;
import java.util.Comparator;

import net.sf.jguard.core.principals.RolePrincipal;

/**
 * jguard角色比较规则，用于排序
 * 
 * @author Sweet
 * 
 */
@SuppressWarnings( { "serial", "unchecked" })
public class PrincipalComparator implements Comparator, Serializable {
    public int compare(Object arg0, Object arg1) {
        if (arg0 instanceof RolePrincipal && arg1 instanceof RolePrincipal) {
            RolePrincipal role0 = (RolePrincipal) arg0;
            RolePrincipal role1 = (RolePrincipal) arg1;
            return role0.getName().compareTo(role1.getName());
        } else {
            return 0;
        }
    }
}
