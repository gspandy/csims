package net.sweet.test.job;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.job.backup.BackupDbService;

public class BackupServiceTestCase extends ApplicationContextTest {
    private BackupDbService backupDbService;

    public void setBackupDbService(BackupDbService backupDbService) {
        this.backupDbService = backupDbService;
    }

    public void testInsertDb() {
        backupDbService.insertBackupRecord("a");
    }

}
