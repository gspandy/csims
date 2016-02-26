package com.gtm.csims.webs.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 工作流自定义表单标签
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("serial")
public class TaskFormTag extends TagSupport {

    private String processDefinitionId;
    private String taskId;
    private String processInstanceId;

    public TaskFormTag() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int doStartTag() throws JspException {
        /*
         * ApplicationContext ac = WebApplicationContextUtils
         * .getWebApplicationContext(this.pageContext.getServletContext()); //
         * 从WebApplicationContext中获取fwFormService和jbpmWorkflowDriver
         * FWFormService fwFormService = (FWFormService) ac
         * .getBean("fwFormService"); WorkflowDriver wfDriver = (WorkflowDriver)
         * ac .getBean("jbpmWorkflowDriver"); // 获得当前流程定义的所有表单项 List formItems =
         * fwFormService .getItems(new
         * Long(EvalHelper.evalString("processDefinitionId",
         * processDefinitionId, this, pageContext))); // 获得当前流程实例的所有变量 Map<String,
         * String> variables = wfDriver .getProcessInstanceVariables(new
         * Long(EvalHelper.evalString( "processInstanceId", processInstanceId,
         * this, pageContext))); String taskName =
         * EvalHelper.evalString("taskId", taskId, this, pageContext); // 生成标签内容
         * StringBuffer outSb = new StringBuffer(); Item item = null; for
         * (Iterator iterator = formItems.iterator(); iterator.hasNext();) {
         * FwFormitem formItem = (FwFormitem) iterator.next();
         * Logger.debug("taskId is :" + taskName); if
         * (formItem.getVisibleNodes().indexOf(taskName.trim()) != -1) { item =
         * (Item) ac.getBean(formItem.getItemType().getName() + "Item");
         * outSb.append("<li><label>"); outSb.append(formItem.getName());
         * outSb.append("："); outSb.append("</label>"); if
         * (formItem.getEditableNodes().indexOf(taskName.trim()) != -1)
         * outSb.append(item.displayHTML(formItem, variables)); else
         * outSb.append(item.displayReadOnlyHTML(formItem, variables)); if
         * (formItem.isNotEmpty()) outSb .append("<font
         * style='color:#DDDDDD;'>&nbsp;*&nbsp;</font>"); outSb.append("</li>"); } }
         * try { this.pageContext.getOut().write(outSb.toString()); } catch
         * (IOException e) { e.printStackTrace(); }
         */

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
