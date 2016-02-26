package com.gtm.csims.webs.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.action.DynaActionForm;

/**
 * 统一分页标签
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class PageTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private String formName;
    private String hrefClass;
    private String formIndex;

    public PageTag() {
    }

    /**
     * 显示标签具体内容
     */
    @Override
    public int doStartTag() throws JspException {
        /*
         * 支持正则表达式计算标签参数 partsStr = EvalHelper.evalString("partsStr", partsStr,
         * this, pageContext);
         */

        // 判断参数为空的情况
        if (hrefClass == null) {
            hrefClass = "";
        }
        if (formIndex == null || formIndex.trim().equals("")) {
            formIndex = "0";
        }
        if (formName == null || formName.trim().equals("")) {
            return EVAL_BODY_INCLUDE;
        }

        StringBuffer htmlStr = new StringBuffer();
        DynaActionForm dynaForm = (DynaActionForm) pageContext.getRequest()
                .getAttribute(formName);

        // 共有页数
        htmlStr.append("第");
        htmlStr.append(dynaForm.get("pageNo"));
        htmlStr.append(" 页 ");
        htmlStr.append("共");
        htmlStr.append(dynaForm.get("pageCount"));
        htmlStr.append("页 ");
        // 首页链接
        htmlStr.append("<a href=\"javascript:SeparatePage('first'," + formIndex
                + ")\" class='" + hrefClass + "'> 首页</a>&nbsp;");
        // 上一页链接
        if (dynaForm.get("previous").equals("1")) {
            htmlStr.append("<a href=\"javascript:SeparatePage('previous',"
                    + formIndex + ")\" class='" + hrefClass + "'>上一页 </a>");
        }
        // 下一页链接
        if (dynaForm.get("next").equals("1")) {
            htmlStr.append("<a href=\"javascript:SeparatePage('next',"
                    + formIndex + ")\" class='" + hrefClass + "'>下一页</a>");
        }
        // 尾页链接
        htmlStr.append("&nbsp;<a href=\"javascript:SeparatePage('last',"
                + formIndex + ")\" class='" + hrefClass + "'>尾页</a>");

        // 跳转到多少页

        htmlStr.append(" 跳转到 ");

        htmlStr
                .append("&nbsp;&nbsp;&nbsp;<input type='text' name='pageNo' size='1' value='");
        htmlStr.append(dynaForm.get("pageNo"));
        htmlStr.append("' onkeydown=\"if(event.keyCode==13)");
        htmlStr.append("{SeparatePage('jumpTo',0);}\"/>");
        htmlStr.append("页");

        try {
            pageContext.getOut().write(htmlStr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }

    public String getHrefClass() {
        return hrefClass;
    }

    public void setHrefClass(String hrefClass) {
        this.hrefClass = hrefClass;
    }

    public String getFormIndex() {
        return formIndex;
    }

    public void setFormIndex(String formIndex) {
        this.formIndex = formIndex;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
