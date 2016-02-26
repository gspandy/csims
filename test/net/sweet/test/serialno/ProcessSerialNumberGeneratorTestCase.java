package net.sweet.test.serialno;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.serialnumber.NoGenerator;
import com.gtm.csims.dao.BsNogenerateDAO;
import com.gtm.csims.model.BsNogenerate;

/**
 * 
 * @author Sweet
 * 
 */
public class ProcessSerialNumberGeneratorTestCase extends
        ApplicationContextTest {
    private BsNogenerateDAO bsNogenerateDao;
    private NoGenerator noGenerator;

    public void setBsNogenerateDao(BsNogenerateDAO bsNogenerateDao) {
        this.bsNogenerateDao = bsNogenerateDao;
    }

    public void setNoGenerator(NoGenerator noGenerator) {
        this.noGenerator = noGenerator;
    }

    public void testSave() {
        BsNogenerate no = new BsNogenerate();
        no.setOrgnm("人民银行成都分行");
        no.setOrgno("A000001");
        no.setArea("成都市");
        no.setAenoindex(Long.valueOf(1));
        no.setAenotext("检立字");
        no.setAenoyear("2013");

        no.setAwaynoindex(Long.valueOf(1));
        no.setAwaynotext("A");
        no.setAwaynoyear("2013");

        no.setEvdcnoindex(Long.valueOf(1));
        no.setEvdcnotext("B");
        no.setEvdcnoyear("2013");

        no.setFactnoindex(Long.valueOf(1));
        no.setFactnotext("C");
        no.setFactnoyear("2013");
        no.setPbnshnoindex(Long.valueOf(1));
        no.setPbnshnotext("D");
        no.setPbnshnoyear("2013");
        bsNogenerateDao.save(no);
    }

    public void testGenerate() {
        try {
            BsNogenerate no = noGenerator.getNoGenerate("A000001", "2014");
            System.out.println(no.getAenotext());
            System.out.println(no.getAenoindex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUpdate() {
        try {
            noGenerator.updateNo(1, "A000001", "2014");
            BsNogenerate no = noGenerator.getNoGenerate("A000001", "2014");
            System.out.println(no.getAenotext());
            System.out.println(no.getAenoindex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
