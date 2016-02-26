package com.gtm.csims.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.action.DynaActionForm;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * 对HttpRequest中的参数进行bean封装
 * 
 * @author Sweet
 * 
 */
public class RequestUtil {
    public static <T> T getBeanFromParams(HttpServletRequest request, Class<T> modelclass) {
        Field[] fields = modelclass.getSuperclass().getDeclaredFields();
        List<Field> lst = new ArrayList<Field>();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                // System.out.println(field.getName());
                lst.add(field);
            }
        }
        T o = null;
        try {
            o = modelclass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        PropertyDescriptor pd = null;
        for (Field f : lst) {
            if (f.getName().equals("hashCode")) {
                continue;
            }
            try {
                pd = new PropertyDescriptor(f.getName(), modelclass);
                Method w = pd.getWriteMethod();
                String value = request.getParameter(pd.getName());
                // System.out.println(value);
                if (value != null && !value.trim().equals("")) {
                    if (f.getType() == Integer.class) {
                        w.invoke(o, Integer.valueOf(value));
                    } else if (f.getType() == Long.class) {
                        w.invoke(o, Long.valueOf(value));
                    } else if (f.getType() == Boolean.class) {
                        w.invoke(o, Boolean.valueOf(value));
                    } else if (f.getType() == String.class) {
                        w.invoke(o, value);
                    } else if (f.getType() == Date.class) {
                        w.invoke(o, DateUtils.parseDate(value, DateUtil.DATE_FORMAT_ARRAY));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return o;
    }

    public static void setFormFromBean(DynaActionForm form, Object object) {
        Field[] fields = object.getClass().getSuperclass().getDeclaredFields();
        List<Field> lst = new ArrayList<Field>();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                // System.out.println(field.getName());
                lst.add(field);
            }
        }
        PropertyDescriptor pd = null;
        for (Field f : lst) {
            if (f.getName().equals("hashCode")) {
                continue;
            }
            try {
                pd = new PropertyDescriptor(f.getName(), object.getClass());
                Method w = pd.getReadMethod();
                if (f.getType() == Integer.class) {
                    form.set(f.getName(),
                            w.invoke(object) == null ? StringUtils.EMPTY : w.invoke(object)
                                    .toString());
                } else if (f.getType() == Long.class) {
                    form.set(f.getName(),
                            w.invoke(object) == null ? StringUtils.EMPTY : w.invoke(object)
                                    .toString());
                } else if (f.getType() == Boolean.class) {
                    form.set(f.getName(),
                            w.invoke(object) == null ? StringUtils.EMPTY : w.invoke(object)
                                    .toString());
                } else if (f.getType() == String.class) {
                    form.set(f.getName(),
                            w.invoke(object) == null ? StringUtils.EMPTY : w.invoke(object)
                                    .toString());
                } else if (f.getType() == Date.class) {
                    form.set(
                            f.getName(),
                            w.invoke(object) == null ? StringUtils.EMPTY : DateFormatUtils.format(
                                    (Date) w.invoke(object), DateUtil.DATE_FORMAT_YYYY_MM_DD));
                } else if (f.getType() == BigDecimal.class) {
                    form.set(f.getName(),
                            w.invoke(object) == null ? StringUtils.EMPTY : w.invoke(object)
                                    .toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public static void main(String[] a) {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setParameter("aeno", "1");
        mockRequest.setParameter("aeorgnm", "2");
        mockRequest.setParameter("aeorgno", "3");
        mockRequest.setParameter("aeedorgno", "4");
        mockRequest.setParameter("aeplanstdate", "5");
        mockRequest.setParameter("aeplantmdate", "6");
        mockRequest.setParameter("prjnm", "7");
        mockRequest.setParameter("prjbasis", "8");
        mockRequest.setParameter("aebasis", "9");
        mockRequest.setParameter("aecontent", "10");

        // BsAdmenforce ae = RequestUtil.getBeanFromParams(mockRequest,
        // BsAdmenforce.class);
        // System.out.println(ae.getAeno() + " " + ae.getClass().getName());
    }
}
