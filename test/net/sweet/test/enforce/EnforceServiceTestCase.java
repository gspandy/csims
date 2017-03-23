package net.sweet.test.enforce;

import java.util.List;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.enforce.EnforceService;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.model.BsOrg;

public class EnforceServiceTestCase extends ApplicationContextTest {

	private EnforceService enforceService;

	public void setEnforceService(EnforceService enforceService) {
		this.enforceService = enforceService;
	}

	public void _testGetRecentScore() {
		System.out.println(enforceService.getChildOrgByParentNoStr("A1000151000016"));
		System.out.println(enforceService.getChildOrgByParentNoStr("A1000151000093"));
		System.out.println(enforceService.getChildOrgByParentNoStr("A1000151000028"));
	}

	public void _testGet2() {
		System.out.println(enforceService.queryAdmenforce(null, null, null, null, null, 1, 50, "A1000151000016"));
	}

	public void testsiftAepeople() {
		List<BsAepeople> peoples = enforceService.siftAepeople(true, "B", 0, 100, 3);
		System.out.println(peoples.size());
	}

	public void testsiftAeedOrg() {
		List<BsOrg> peoples = enforceService.siftAeedOrg(true, "", 2, 100, "", 2);
		System.out.println(peoples.size());
	}
}
