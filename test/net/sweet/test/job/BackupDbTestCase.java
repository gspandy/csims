package net.sweet.test.job;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.job.backup.BackupDbJob;

public class BackupDbTestCase extends ApplicationContextTest {

    private BackupDbJob backupDbJob;

    public void setBackupDbJob(BackupDbJob backupDbJob) {
        this.backupDbJob = backupDbJob;
    }

    public void testQueryDbAndWriteTmpFile() throws Exception {
        backupDbJob.doQueryDbAndWriteTmpFile();
        Thread.sleep(60 * 60 * 1000);
    }

}
