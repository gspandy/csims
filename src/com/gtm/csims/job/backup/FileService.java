package com.gtm.csims.job.backup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 备份过程文件处理类.
 * 
 * @author yangyongzhi
 *
 */
public class FileService {

    /**
     * 日志调试.
     */
    private static Logger LOGGER = Logger.getLogger(FileService.class);

    /**
     * 压缩完成文件最终存放路径.
     */
    public static final String BACKUP_ZIP_FILE_FOLDER = "Dowload" + File.separator;

    /**
     * Web服务器绝对路径配置.
     */
    private String webRealPath;

    /**
     * 生成备份压缩文件.
     */
    public String doZip() throws Exception {
        if (StringUtils.isBlank(webRealPath)) {
            throw new IllegalArgumentException("Web服务器绝对路径未配置");
        }
        LOGGER.debug("当前Web服务器绝对路径为" + webRealPath);
        File dir = new File(webRealPath + BACKUP_ZIP_FILE_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
            LOGGER.debug("创建Web服务器绝对路径下备份文件存放文件夹为" + webRealPath + BACKUP_ZIP_FILE_FOLDER);
        }
        //zipDirectoryPath:需要压缩的文件夹名 
        File tmpFiles = new File(BackupDbService.BACKUP_TEMP_PATH);
        //压缩后生成的zip文件名
        String zipFileName = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + ".zip";
        String zipFilePath = webRealPath + BACKUP_ZIP_FILE_FOLDER + zipFileName;
        ZipOutputStream zipOut = null;
        try {
            zipOut = new ZipOutputStream(
                    new BufferedOutputStream(new FileOutputStream(zipFilePath)));
            zipOut.setEncoding(CharEncoding.UTF_8);
            this.zip(zipOut, tmpFiles, StringUtils.EMPTY);
            LOGGER.debug("压缩文件生成成功，名称为" + zipFileName);
            return zipFileName;
        } catch (Exception e) {
            LOGGER.error("压缩文件失败:", e);
            throw e;
        } finally {
            IOUtils.closeQuietly(zipOut);
        }
    }

    /**
     * 将文件夹下所有文件写入到zip压缩文件.
     * 
     * @param out
     * @param f
     * @param base
     * @throws Exception
     */
    private void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + File.separator));
            base = base.length() == 0 ? StringUtils.EMPTY : base + File.separator;
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            LOGGER.debug("正在添加文件" + base + "到压缩文件");
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 清空备份临时文件.
     */
    public void clean() {
        try {
            FileUtils.deleteDirectory(new File(BackupDbService.BACKUP_TEMP_PATH));
            LOGGER.debug("删除备份临时文件夹" + BackupDbService.BACKUP_TEMP_PATH);
        } catch (IOException e) {
            LOGGER.error("删除备份临时文件夹失败", e);
        }
        File dir = new File(BackupDbService.BACKUP_TEMP_PATH);
        if (!dir.exists()) {
            LOGGER.debug("重新创建备份临时文件夹" + BackupDbService.BACKUP_TEMP_PATH);
            dir.mkdirs();
        }
        /*  try {
              FileUtils.forceMkdir(new File(BackupDbService.BACKUP_TEMP_PATH));
          } catch (IOException e) {
              LOGGER.error("重新创建备份临时文件夹失败", e);
          }*/
    }

    public void setWebRealPath(String webRealPath) {
        this.webRealPath = webRealPath;
    }

}
