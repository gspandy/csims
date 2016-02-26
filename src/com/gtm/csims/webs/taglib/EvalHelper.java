package com.gtm.csims.webs.taglib;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * This class is used in the <code>evaluateExpressions</code> method of each
 * Tag class. It is used to process the original attribute value through the
 * JSTL EL engine to produce an evaluated value. It provides functions to
 * evaluate the expression assuming it is an Object, String, Integer, or Boolean
 * result.
 */
public final class EvalHelper {
    private EvalHelper() {
    }

    /**
     * Evaluates the attribute value in the JSTL EL engine, returning the raw
     * Object value of the evaluated expression. If the original expression is
     * null, or the resulting value is null, it will return null.
     */
    public static Object eval(String attrName, String attrValue, Tag tagObject,
            PageContext pageContext) throws JspException {
        Object result = null;

        if (attrValue != null) {
            result = ExpressionEvaluatorManager.evaluate(attrName, attrValue,
                    Object.class, tagObject, pageContext);
        }

        return result;
    }

    /**
     * Evaluates the attribute value in the JSTL EL engine, assuming the
     * resulting value is a String object. If the original expression is null,
     * or the resulting value is null, it will return null.
     */
    public static String evalString(String attrName, String attrValue,
            Tag tagObject, PageContext pageContext) throws JspException {
        Object result = null;

        if (attrValue != null) {
            result = ExpressionEvaluatorManager.evaluate(attrName, attrValue,
                    String.class, tagObject, pageContext);
        }

        return (String) result;
    }

    /**
     * Evaluates the attribute value in the JSTL EL engine, assuming the
     * resulting value is an Integer object. If the original expression is null,
     * or the resulting value is null, it will return null.
     */
    public static Integer evalInteger(String attrName, String attrValue,
            Tag tagObject, PageContext pageContext) throws JspException {
        Object result = null;

        if (attrValue != null) {
            result = ExpressionEvaluatorManager.evaluate(attrName, attrValue,
                    Integer.class, tagObject, pageContext);
        }

        return (Integer) result;
    }

    /**
     * Evaluates the attribute value in the JSTL EL engine, assuming the
     * resulting value is an Boolean object. If the original expression is null,
     * or the resulting value is null, it will return null.
     */
    public static Boolean evalBoolean(String attrName, String attrValue,
            Tag tagObject, PageContext pageContext) throws JspException {
        Object result = null;

        if (attrValue != null) {
            result = ExpressionEvaluatorManager.evaluate(attrName, attrValue,
                    Boolean.class, tagObject, pageContext);
        }

        return (Boolean) result;
    }
}
