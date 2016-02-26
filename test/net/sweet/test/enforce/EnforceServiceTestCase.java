package net.sweet.test.enforce;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.enforce.EnforceService;

public class EnforceServiceTestCase extends ApplicationContextTest {

    private EnforceService enforceService;

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public void testGetRecentScore() {
        System.out.println(enforceService
                .getChildOrgByParentNoStr("A1000151000016"));
        System.out.println(enforceService
                .getChildOrgByParentNoStr("A1000151000093"));
        System.out.println(enforceService
                .getChildOrgByParentNoStr("A1000151000028"));
    }

    public void _testGet2() {
        System.out.println(enforceService.queryAdmenforce(null, null, null,
                null, null, 1, 50, "A1000151000016"));
    }
}
