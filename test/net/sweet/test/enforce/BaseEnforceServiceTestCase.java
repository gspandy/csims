package net.sweet.test.enforce;

import junit.framework.TestCase;

import com.gtm.csims.business.enforce.BaseEnforceService;

public class BaseEnforceServiceTestCase extends TestCase {
    public void testa() {
        BaseEnforceService service = new BaseEnforceService();
        String a = service
                .getOrInClause(
                        "A IN (%s)",
                        "'aaaa','bbbbb','ccccc','dddd','eeee','ffff','gggggg'"
                                + ",'aaaa','bbbbb','ccccc','dddd','eeee','ffff','gggggg'"
                                + ",'aaaa','bbbbb','ccccc','dddd','eeee','ffff','gggggg'"
                                + ",'aaaa','bbbbb','ccccc','dddd','eeee','ffff','gggggg'"
                                + ",'aaaa','bbbbb','ccccc','dddd','eeee','ffff','gggggg'"
                                + ",'aaaa','bbbbb','ccccc','dddd','eeee','ffff','gggggg'");
        System.out.println(a);
    }

}
