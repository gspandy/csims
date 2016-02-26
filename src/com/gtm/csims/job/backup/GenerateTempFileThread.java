package com.gtm.csims.job.backup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成临时数据文件线程.
 * 
 * @author yangyongzhi
 *
 */
public class GenerateTempFileThread implements Runnable {

    /**
     * 调试日志.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(GenerateTempFileThread.class);

    private BackupDbService backupDbService;

    private String tableName;

    public void setBackupDbService(BackupDbService backupDbService) {
        this.backupDbService = backupDbService;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void run() {
        try {
            backupDbService.generateSingleTableData(tableName);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
