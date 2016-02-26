package com.gtm.csims.file.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.transaction.file.FileResourceManager;
import org.apache.commons.transaction.file.ResourceManagerException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsAttcachmentsDAO;
import com.gtm.csims.dao.BsPhotoDAO;
import com.gtm.csims.file.FileHandleException;
import com.gtm.csims.file.FileHandler;
import com.gtm.csims.model.BsAttcachments;
import com.gtm.csims.model.BsPhoto;

/**
 * 文件处理实现类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public class FileHandlerImpl implements FileHandler {
    private static final int BUFFER = 8192;
    private static final int BUFFER2 = 4096;
    private Map<String, String> folderNames;
    private BsAttcachmentsDAO bsAttcachmentsDao;
    private BsPhotoDAO bsPhotoDao;

    public void setBsAttcachmentsDao(BsAttcachmentsDAO bsAttcachmentsDao) {
        this.bsAttcachmentsDao = bsAttcachmentsDao;
    }

    public void setBsPhotoDao(BsPhotoDAO bsPhotoDao) {
        this.bsPhotoDao = bsPhotoDao;
    }

    public void setFolderNames(Map<String, String> folderNames) {
        this.folderNames = folderNames;
    }

    // public FileHandlerImpl() {
    // folderNames = new HashMap<String, String>();
    // }

    private String getFolderName(String key) {
        String value = folderNames.get(key);
        if (value == null) {
            throw new FileHandleException("No [" + key + "] foler name definded here!");
        }
        return value;
    }

    /**
     * 处理request中文件参数，并保存文件到服务器中
     * 
     * @param form
     * @param webRealPath
     * @param params
     */
    public void save(ActionForm form, String webRealPath, Map<String, String[]> params,
            Map<String, Object> piVariables) {
        if (form.getMultipartRequestHandler() == null) {
            return;
        }
        Hashtable<String, FormFile> filetable = form.getMultipartRequestHandler().getFileElements();// 获得当前http请求中的文件表单项
        FileResourceManager frm = new FileResourceManager(webRealPath + File.separator
                + this.getFolderName(UPLOAD_FILE_FOLDER_PATH), webRealPath + File.separator
                + this.getFolderName(TEMP_FILE_FOLDER_PATH), false, null);
        // new CommonsLoggingLogger(log));// 创建文件资源管理器
        String txId = null;
        try {
            frm.start();// 开启文件资源管理器
            txId = frm.generatedUniqueTxId();
            frm.startTransaction(txId);// 开启事务
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileHandleException("Start file transaction failure!");
        }
        try {
            for (Enumeration<String> e = filetable.keys(); e.hasMoreElements();) {// 逐步处理文件表单项
                UUID uuid = UUID.randomUUID();
                String key = e.nextElement();
                FormFile file = filetable.get(key);
                if (file == null || file.getFileName().trim().equals("") || file.getFileSize() <= 0) {// 文件表单项值为空或者文件长度为0，则不处理
                    params.remove(key);
                    continue;
                }
                String randomFileName = this.getFolderName(RANDOM_UPLOAD_FILE_PREFIX) + uuid
                        + this.getFolderName(RANDOM_UPLOAD_FILE_POSTFIX) + file.getFileName();// 生成服务器端存放文件的随机名
                frm.writeResource(txId, randomFileName).write(file.getFileData());
                if (piVariables.get(key) != null && frm.resourceExists(txId, piVariables.get(key))) {// 如果当前实例变量中已经存在该文件属性键值对，则删除该原上传文件
                    try {
                        frm.deleteResource(txId, piVariables.get(key));
                    } catch (Exception deleteFileException) {
                        deleteFileException.printStackTrace();
                    }
                }
                params.remove(key);// 从当前http请求中删除文件表单项
                params.put(key, new String[] { randomFileName });// 重新放入文件表单项的值为文件名
            }
            frm.commitTransaction(txId);// 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            try {
                frm.rollbackTransaction(txId);// 事物回滚
            } catch (ResourceManagerException e1) {
                e1.printStackTrace();
            }
            throw new FileHandleException("Save file failure, transaction rollbacked!");
        }
    }

    /**
     * 保存文件到目标地址
     * 
     * @throws IOException
     */
    public void save(InputStream fis, String realPath) throws IOException {
        byte[] buffer = new byte[BUFFER2];// 缓冲区
        BufferedOutputStream output = null;
        BufferedInputStream input = null;

        try {
            output = new BufferedOutputStream(new FileOutputStream(realPath));
            input = new BufferedInputStream(fis);
            int n = -1;
            // 遍历，开始下载        
            while ((n = input.read(buffer, 0, BUFFER2)) > -1) {
                output.write(buffer, 0, n);
            }
            output.flush(); // 不可少         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流，不可少          
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * 保存文件到目标地址,返回该文件MD5值
     * 
     * @param fis
     *            文件输入流
     * @param realPath
     *            保存文件的目标绝对路径
     * @return 保存的文件MD5值
     * @throws IOException
     */
    public String saveReturnMD5(InputStream fis, String realPath) throws IOException {
        byte[] buffer = new byte[BUFFER2];

        MessageDigest md5 = null;
        Boolean isCanBeMd5 = false;
        try {
            md5 = MessageDigest.getInstance("MD5");
            isCanBeMd5 = true;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        BufferedOutputStream output = null;
        BufferedInputStream input = null;
        try {
            output = new BufferedOutputStream(new FileOutputStream(realPath));
            input = new BufferedInputStream(fis);
            int n = -1;
            while ((n = input.read(buffer, 0, BUFFER2)) > -1) {
                output.write(buffer, 0, n);
                try {
                    if (isCanBeMd5) {
                        md5.update(buffer, 0, n);
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
        if (isCanBeMd5 && md5 != null) {
            try {
                return DigestUtils.md5Hex(md5.digest());
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 保存文件到服务器指定UPLOAD文件夹
     * 
     * @param fileData
     * @param servletContextRealPath
     * @param folderName
     * @param distFileName
     */
    public void save(byte[] fileData, String servletContextRealPath, String[] folderNames,
            String diskFileName) {
        // 判断服务器绝对路径是否存在，不存在则建立该目录
        File file = new File(servletContextRealPath);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                throw new FileHandleException("can not create folder:" + servletContextRealPath);
            }
        }
        // 判断服务器绝对路径下是否存在Upload文件夹，不存在则建立该目录
        file = new File(servletContextRealPath + File.separator + UPLOAD_FILE_FOLDER_PATH);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                throw new FileHandleException("can not create folder:" + servletContextRealPath
                        + File.separator + UPLOAD_FILE_FOLDER_PATH);
            }
        }
        // 创建层级目录
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < folderNames.length; i++) {
            sb.append(File.separator + folderNames[i]);
            file = new File(servletContextRealPath + File.separator + UPLOAD_FILE_FOLDER_PATH
                    + sb.toString());
            if (!file.exists()) {
                try {
                    file.mkdirs();
                } catch (Exception e) {
                    throw new FileHandleException("can not create folder:" + servletContextRealPath
                            + File.separator + UPLOAD_FILE_FOLDER_PATH + sb.toString());
                }
            }
        }
        // 向目标输出流写数据
        if (file != null && file.exists() && file.isDirectory()) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(servletContextRealPath + File.separator
                        + UPLOAD_FILE_FOLDER_PATH + sb.toString() + File.separator + diskFileName);
                fos.write(fileData);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                throw new FileHandleException("The dist file not found!");
            } catch (IOException e1) {
                e1.printStackTrace();
                throw new FileHandleException("I/O exception in saving file");
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        throw new FileHandleException("Can not close the file output stream!");
                    }
                }
            }
        } else {
            throw new FileHandleException("no folder with path(" + servletContextRealPath
                    + File.separator + UPLOAD_FILE_FOLDER_PATH + sb.toString()
                    + "), and can't create this folder.");
        }
    }

    /**
     * 通过ActionForm中的FormFile获得文件信息并保存文件到服务器制定文件夹
     * 
     * @param formFile
     * @param servletContextRealPath
     * @param folderNames
     * @param distFileName
     */
    public void save(FormFile formFile, String servletContextRealPath, String[] folderNames,
            String distFileName) {
        try {
            this.save(formFile.getFileData(), servletContextRealPath, folderNames, distFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileHandleException("The dist file not found!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileHandleException("I/O exception in saving file");
        }
    }

    /**
     * 通过ActionForm中的FormFile数组获得文件信息并保存文件到服务器制定文件夹
     * 
     * @param formFile
     * @param servletContextRealPath
     * @param folderNames
     * @param distFileName
     */
    public void save(FormFile[] formFiles, String servletContextRealPath, String[] folderNames,
            String[] distFileName) {
        try {
            for (int i = 0; i < formFiles.length; i++) {
                FormFile formFile = formFiles[i];
                this.save(formFile.getFileData(), servletContextRealPath, folderNames,
                        distFileName[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileHandleException("The dist file not found!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileHandleException("I/O exception in saving file");
        }
    }

    /**
     * 删除文件
     * 
     * @param realPath
     *            绝对路径
     */
    public void delete(String realPath) {
        File distFile = new File(realPath);
        distFile.deleteOnExit();
    }

    /**
     * 获得随机文件名中的真实文件名部分
     * 
     * @param fileName
     *            随机文件名 eg:
     *            (7435720d-1169-48be-8e72-4fec30dd48b8)2aef67a140ed189345106461
     *            .jpg
     * @return 真实文件名 eg:2aef67a140ed189345106461.jpg
     */
    public String getDisplayFileName(String fileName) {
        Pattern pattern = Pattern.compile("\\" + this.getFolderName(RANDOM_UPLOAD_FILE_PREFIX)
                + "[^" + this.getFolderName(RANDOM_UPLOAD_FILE_POSTFIX) + "]*\\"
                + this.getFolderName(RANDOM_UPLOAD_FILE_POSTFIX));
        Matcher matcher = pattern.matcher(fileName);
        StringBuffer sbuf = new StringBuffer();
        while (matcher.find()) {
            // String str = matcher.group();
            matcher.appendReplacement(sbuf, Matcher.quoteReplacement(String.valueOf("")));
            break;// 只去掉字符串中的第一对括号
        }
        matcher.appendTail(sbuf);
        return sbuf.toString();
    }

    /**
     * 根据上传文件的名称生成格式统一的随机上传名
     * 
     * @param fileName
     * @return
     */
    public String getRandomFileName(String fileName) {
        UUID uuid = UUID.randomUUID();
        StringBuffer randomName = new StringBuffer();
        randomName.append(this.getFolderName(RANDOM_UPLOAD_FILE_PREFIX));
        randomName.append(uuid.toString());
        randomName.append(this.getFolderName(RANDOM_UPLOAD_FILE_POSTFIX));
        randomName.append(fileName);
        return randomName.toString();
    }

    /**
     * 获得绝对路径中的项目名称
     * 
     * @param realPath
     *            项目绝对路径 eg:D:\Tomcat_6\webapps\sweet_pro
     * @return 项目Context名 eg:sweet_pro
     */
    public String getContextPathFormServletContext(String realPath) {
        Pattern pattern = Pattern.compile("[^\\" + File.separator + "]*\\" + File.separator);
        Matcher matcher = pattern.matcher(realPath);
        StringBuffer sbuf = new StringBuffer();
        while (matcher.find()) {
            // String str = matcher.group();
            matcher.appendReplacement(sbuf, Matcher.quoteReplacement(String.valueOf("")));
        }
        matcher.appendTail(sbuf);
        return sbuf.toString();
    }

    /**
     * 获得绝对路径中的项目名称
     * 
     * @param srcFolderPath
     *            源文件夹路径
     * @param destFilePath
     *            目标文件路径（ZIP文件）
     * @return
     */
    public void compress(String srcFolderPath, String destFilePath) {
        File srcdir = new File(srcFolderPath);
        if (!srcdir.exists()) {
            throw new RuntimeException(srcFolderPath + "不存在");
        }

        File destFile = new File(destFilePath);
        if (destFile != null && destFile.exists()) {
            destFile.deleteOnExit();
        }

        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(destFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        // fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
        // eg:zip.setIncludes("*.java");
        // fileSet.setExcludes(...); 排除哪些文件或文件夹
        zip.addFileset(fileSet);

        zip.execute();
    }

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
    public void compress(String webRealPath, String[] srcFilePaths, String destFilePath,
            Map<String, String> scanMap) {
        File destFile = new File(destFilePath);
        if (destFile != null && destFile.exists()) {
            destFile.deleteOnExit();
        }
        List<File> fileList = new ArrayList<File>();
        File file = null;
        for (int i = 0; i < srcFilePaths.length; i++) {
            file = new File(webRealPath + File.separator
                    // + "UPLOAD"
                    + this.getFolderName(UPLOAD_FILE_FOLDER_PATH) + File.separator
                    + srcFilePaths[i]);
            if (file.exists()) {
                fileList.add(file);
            }
        }
        File[] files = new File[fileList.size()];
        files = fileList.toArray(files);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(destFilePath);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            for (int i = 0; i < files.length; i++) {
                /* 递归 */
                compressFile(scanMap, files[i], out, "Scan/");
            }
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 压缩一个文件 */
    private void compressFile(Map<String, String> scanMap, File file, ZipOutputStream out,
            String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            String scan = file.getName().toString().replace(".", "-");
            String[] scans = scan.split("-");
            String scanName = scans[0];
            String scanRealName = scanMap.get(scanName);
            ZipEntry entry = new ZipEntry(basedir + scanRealName);
            out.putNextEntry(entry);
            int count;
            byte[] data = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
    public String saveBsAttachmentWithFileType(String username, FormFile file, String realPath) {
        InputStream input = null;
        try {
            input = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            BsAttcachments bsAttcachments = new BsAttcachments();
            bsAttcachments.setId(UUID.randomUUID().toString().replace("-", ""));
            String fileName = file.getFileName(); // 获取文件名
            String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
            String attchpath = File.separator + FileHandler.UPLOAD_FILE_FOLDER_PATH
                    + File.separator + bsAttcachments.getId() + fileType;
            bsAttcachments.setAttchname(fileName);
            bsAttcachments.setAttchuploader(username);
            bsAttcachments.setAttchpath(attchpath);
            Date today = new Date();
            bsAttcachments.setCreatedate(today);
            bsAttcachments.setUpdatedate(today);
            bsAttcachmentsDao.save(bsAttcachments);
            String path = realPath + bsAttcachments.getAttchpath();
            this.save(input, path);
            return bsAttcachments.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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
    @Transactional(readOnly = false)
    public String saveBsAttachment(String username, FormFile file, String realPath) {
        InputStream input = null;
        try {
            input = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            BsAttcachments bsAttcachments = new BsAttcachments();
            bsAttcachments.setId(UUID.randomUUID().toString().replace("-", ""));
            String fileName = file.getFileName(); // 获取文件名
            String attchpath = File.separator + FileHandler.UPLOAD_FILE_FOLDER_PATH
                    + File.separator + bsAttcachments.getId();
            bsAttcachments.setAttchname(fileName);
            bsAttcachments.setAttchuploader(username);
            bsAttcachments.setAttchpath(attchpath);
            Date today = new Date();
            bsAttcachments.setCreatedate(today);
            bsAttcachments.setUpdatedate(today);
            String path = realPath + bsAttcachments.getAttchpath();
            String fileMd5 = this.saveReturnMD5(input, path);
            bsAttcachments.setFlag(fileMd5);
            bsAttcachmentsDao.save(bsAttcachments);
            return bsAttcachments.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                //TODO
            }
            try {
                if (file != null) {
                    file.destroy();
                }
            } catch (Exception e) {
                //TODO
            }
        }
    }

    /**
     * 下载附件
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void download(String attachId, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BsAttcachments bs = null;
        if (attachId == null || attachId.trim().length() <= 0) {
            throw new Exception("附件ID不能为空");
        } else {
            try {
                bs = bsAttcachmentsDao.get(attachId);
                if (bs == null) {
                    throw new Exception("不能在附件信息表中查询到ID为" + attachId + "的附件");
                }
            } catch (Exception e) {
                throw new Exception("从数据库获取附件ID为 " + attachId + "的信息发生错误，" + e.getMessage());
            }
        }
        response.setContentType("application/x-download");
        String attchPath = bs.getAttchpath();
        String attchName = bs.getAttchname();
        attchName = URLEncoder.encode(attchName, "UTF-8");

        try {
            File attchFile = new File(request.getSession().getServletContext().getRealPath("")
                    + attchPath);
            if (!attchFile.exists()) {
                throw new Exception("服务器上没有找到相应附件文件，有可能被人为删除，请联系管理员");
            }
        } catch (Exception e) {
            throw new Exception("服务器上没有找到相应附件文件，有可能被人为删除，请联系管理员");
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + attchName);
        RequestDispatcher dis = request.getRequestDispatcher(attchPath);
        if (dis != null) {
            dis.forward(request, response);
        }
        response.flushBuffer();
    }

    @Transactional(readOnly = false)
    public String saveBsPhoto(FormFile file, String realPath) {
        InputStream input = null;
        try {
            input = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        try {
            BsPhoto bs = new BsPhoto();
            bs.setId(UUID.randomUUID().toString().replace("-", ""));
            String fileName = file.getFileName(); // 获取文件名
            String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
            String photopath = File.separator + FileHandler.PHOTO_FILE_FOLDER_PATH + File.separator
                    + bs.getId() + fileType;
            bs.setPhotopath(photopath);
            bsPhotoDao.save(bs);
            String path = realPath + photopath;
            this.save(input, path);
            return bs.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Transactional(readOnly = false)
    public String updateBsPhoto(FormFile file, String realPath, String photoId) {
        InputStream input = null;
        try {
            input = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return photoId;
        }
        try {
            BsPhoto bs = this.getBsPhoto(photoId);
            String fileName = file.getFileName(); // 获取文件名
            String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
            String photopath = File.separator + FileHandler.PHOTO_FILE_FOLDER_PATH + File.separator
                    + bs.getId() + fileType;
            bs.setPhotopath(photopath);
            bsPhotoDao.save(bs);
            String path = realPath + photopath;
            this.save(input, path);
            return bs.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return photoId;
        }
    }

    public BsPhoto getBsPhoto(String id) {
        return bsPhotoDao.get(id);
    }
}
