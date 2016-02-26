package com.gtm.csims.job.backup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成sql临时数据文件线程.
 * 
 * @author yangyongzhi
 *
 */
public class GenerateSqlTempFileThread implements Runnable {

	/**
	 * 调试日志.
	 */
	protected static Logger LOGGER = LoggerFactory
			.getLogger(GenerateSqlTempFileThread.class);

	private BackupDbService backupDbService;

	private FileService backupFileService;

	private int type;

	public void setBackupDbService(BackupDbService backupDbService) {
		this.backupDbService = backupDbService;
	}

	public void setBackupFileService(FileService backupFileService) {
		this.backupFileService = backupFileService;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void run() {
		switch (type) {
		case 1:
			try {
				backupDbService.generateResetSquence();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			break;
		case 2:
			// try {
			// backupDbService.generateResetAutoIncreaseId();
			// } catch (Exception e) {
			// LOGGER.error(e.getMessage(), e);
			// }
			break;
		case 3:
			// try {
			// backupDbService.copy(backupFileService.getWebRealPath());
			// } catch (Exception e) {
			// LOGGER.error(e.getMessage(), e);
			// }
			break;
		default:
			break;
		}
	}

}
