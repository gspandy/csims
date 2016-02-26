package net.sweet.test.dao;

import java.util.List;

import net.sweet.test.base.ApplicationContextTest;

import org.springframework.mock.web.MockHttpServletRequest;

import com.gtm.csims.dao.BsAdmenforceDAO;
import com.gtm.csims.dao.BsNogenerateDAO;
import com.gtm.csims.model.BsAdmenforce;
import com.gtm.csims.model.BsNogenerate;
import com.gtm.csims.utils.RequestUtil;

public class DaoTestCase extends ApplicationContextTest {
    private BsAdmenforceDAO bsAdmenforceDao;

    private BsNogenerateDAO bsNogenerateDao;

    public void setBsNogenerateDao(BsNogenerateDAO bsNogenerateDao) {
        this.bsNogenerateDao = bsNogenerateDao;
    }

    public void setBsAdmenforceDao(BsAdmenforceDAO bsAdmenforceDao) {
        this.bsAdmenforceDao = bsAdmenforceDao;
    }

    public void test_updateIncoming() {
        List a = bsNogenerateDao.getAll();
        for (int i = 0; i < a.size(); i++) {
            BsNogenerate n = (BsNogenerate) a.get(i);
            String b = n.getComeinnotext();
            n.setComeinnotext(b.replace("离场", "进场"));
            bsNogenerateDao.save(n);
        }
    }

    public void _test_save() {
        BsAdmenforce ae = new BsAdmenforce();
        ae.setAebasis("aaaaaa");
        ae.setAecontent("fdsf");
        bsAdmenforceDao.save(ae);
    }

    public void _test_get() {
        // BsAdmenforce ae =
        // bsAdmenforceDao.get("402881e7475947850147594fac870005");
        BsAdmenforce ae = bsAdmenforceDao
                .get("402881eb475bf78201475bfa452d0001");
        System.out.println("+++++++++++++++++" + ae.getAeno());
    }

    public void _test_saveFormParam() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setParameter("aeno", "1");
        mockRequest.setParameter("aeorgnm", "2");
        mockRequest.setParameter("aeorgno", "3");
        mockRequest.setParameter("aeedorgno", "4");
        mockRequest.setParameter("aeplanstdate", "5");
        mockRequest.setParameter("aeplantmdate", "6");
        mockRequest.setParameter("prjnm", "7");
        mockRequest.setParameter("prjbasis", "8");
        mockRequest.setParameter("aebasis", "9");
        mockRequest.setParameter("aecontent", "10");

        BsAdmenforce ae = RequestUtil.getBeanFromParams(mockRequest,
                BsAdmenforce.class);
        bsAdmenforceDao.save(ae);

    }

}
