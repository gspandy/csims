package com.gtm.csims.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.gtm.csims.base.BaseAction;

/**
 * @ClassName: ${FileHandlerManagerAction}
 * @Description: ${附件下载Action}
 * @author qhy
 * @date ${date} ${time}
 * 
 */
@SuppressWarnings("unchecked")
public class FileHandlerManagerAction extends BaseAction {
	private FileHandler fileHandler;
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}


	/**
	 * @Description:下载检查方案
	 * @author qhy
	 */
	public ActionForward downloadAtt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String attId =request.getParameter("attId").toString();
		try {
			fileHandler.download(attId, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			this.printMessage(request, response, e.getMessage(), "error");
		}
		return null;
	}
}
