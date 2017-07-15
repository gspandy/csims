package com.gtm.csims.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.model.BsPhoto;

/**
 * 系统统一文件处理者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public interface FileHandler {
	public static String UPLOAD_FILE_FOLDER_PATH = "UPLOAD";
	public static String PHOTO_FILE_FOLDER_PATH = "PHOTO";
	public static String WORD_TEMPLATE_FILE_PATH = "WORD_TEMPLATES";
	public static String TEMP_FILE_FOLDER_PATH = "TEMPORARY";
	public static String EXCEL_FILE_FOLDER_PATH = "EXCELUPLOAD";
	public static String INFORMATION_ATTATCHMENT_FOLDER_PATH = "INFORMATION";
	public static String RANDOM_UPLOAD_FILE_PREFIX = "FILE_PREFIX";
	public static String RANDOM_UPLOAD_FILE_POSTFIX = "FILE_POSTFIX";

	/**
	 * 处理request中文件参数，并保存文件到服务器中
	 * 
	 * @param form
	 * @param webRealPath
	 * @param params
	 * @param piVariables
	 */
	public void save(ActionForm form, String webRealPath, Map<String, String[]> params, Map<String, Object> piVariables);

	/**
	 * 保存文件到目标地址
	 * 
	 * @param fileData
	 * @param realPath
	 */
	public void save(InputStream fis, String realPath) throws IOException;

	/**
	 * 保存文件到服务器制定文件夹
	 * 
	 * @param fileData
	 * @param servletContextRealPath
	 * @param folderName
	 * @param distFileName
	 */
	public void save(byte[] fileData, String servletContextRealPath, String[] folderNames, String diskFileName);

	/**
	 * 通过ActionForm中的FormFile获得文件信息并保存文件到服务器制定文件夹
	 * 
	 * @param formFile
	 * @param servletContextRealPath
	 * @param folderNames
	 * @param distFileName
	 */
	public void save(FormFile formFile, String servletContextRealPath, String[] folderNames, String distFileName);

	/**
	 * 通过ActionForm中的FormFile数组获得文件信息并保存文件到服务器制定文件夹
	 * 
	 * @param formFile
	 * @param servletContextRealPath
	 * @param folderNames
	 * @param distFileName
	 */
	public void save(FormFile[] formFiles, String servletContextRealPath, String[] folderNames, String[] distFileName);

	/**
	 * 删除文件
	 * 
	 * @param realPath
	 *            绝对路径
	 */
	public void delete(String realPath);

	/**
	 * 获得随机文件名中的真实文件名部分
	 * 
	 * @param fileName
	 *            随机文件名 eg:
	 *            (7435720d-1169-48be-8e72-4fec30dd48b8)2aef67a140ed189345106461
	 *            .jpg
	 * @return 真实文件名 eg:2aef67a140ed189345106461.jpg
	 */
	public String getDisplayFileName(String fileName);

	/**
	 * 根据上传文件的名称生成格式统一的随机上传名
	 * 
	 * @param fileName
	 * @return
	 */
	public String getRandomFileName(String fileName);

	/**
	 * 获得绝对路径中的项目名称
	 * 
	 * @param realPath
	 *            项目绝对路径 eg:D:\Tomcat_6\webapps\sweet_pro
	 * @return 项目Context名 eg:sweet_pro
	 */
	public String getContextPathFormServletContext(String realPath);

	/**
	 * 获得绝对路径中的项目名称
	 * 
	 * @param srcFolderPath
	 *            源文件夹路径
	 * @param destFilePath
	 *            目标文件路径（ZIP文件）
	 * @return
	 */
	public void compress(String srcFolderPath, String destFilePath);

	/**
	 * 根据文件名数组生成压缩ZIP文件
	 * 
	 * @param webRealPath
	 *            项目部署的绝对路径
	 * @param srcFilePaths
	 *            文件名数组
	 * @param destFilePath
	 *            目标zip文件路径
	 */
	public void compress(String webRealPath, String[] srcFilePaths, String destFilePath, Map<String, String> scanMap);

	/**
	 * 页面上传附件保存方法
	 * 
	 * 以UUID作为文件名，不带文件后缀，保存在web服务器
	 * 
	 * 数据库记录文件ID、路径及名称
	 * 
	 * @param username
	 * @param file
	 * @param realPath
	 * @return
	 */
	public String saveBsAttachment(String username, FormFile file, String realPath);

	/**
	 * 页面上传附件保存方法
	 * 
	 * 以UUID作为文件名，不带文件后缀，保存在web服务器
	 * 
	 * 数据库记录文件ID、路径及名称
	 * 
	 * @param username
	 * @param file
	 * @param realPath
	 * @return
	 */
	public String saveBsAttachment(String username, byte[] file, String realPath);

	/**
	 * 页面上传附件保存方法
	 * 
	 * 以UUID作为文件名，同时文件名保留文件后缀，保存在web服务器
	 * 
	 * 数据库记录文件ID、路径及名称
	 * 
	 * @param username
	 * @param file
	 * @param realPath
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveBsAttachmentWithFileType(String username, FormFile file, String realPath);

	/**
	 * 下载附件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void download(String attachId, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String saveBsPhoto(FormFile file, String realPath);

	public String updateBsPhoto(FormFile file, String realPath, String photoId);

	public BsPhoto getBsPhoto(String id);
}
