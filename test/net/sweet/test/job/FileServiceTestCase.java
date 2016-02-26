package net.sweet.test.job;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.job.backup.BackupDbJob;

public class FileServiceTestCase extends ApplicationContextTest {

    private BackupDbJob backupDbJob;

    public void setBackupDbJob(BackupDbJob backupDbJob) {
        this.backupDbJob = backupDbJob;
    }

    public void testZip() throws Exception {
        backupDbJob.doZipAndRecordPath();
    }
    
    public void testTempPath(){
    	System.out.println(System
			.getProperty("java.io.tmpdir"));
    }

}
