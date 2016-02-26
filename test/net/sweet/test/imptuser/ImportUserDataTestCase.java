package net.sweet.test.imptuser;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.importdata.user.UserDataInitialService;
/**
 * 
 * @author Sweet
 *
 */
public class ImportUserDataTestCase extends ApplicationContextTest {
    private UserDataInitialService userDataInitialService;

    public void setUserDataInitialService(
            UserDataInitialService userDataInitialService) {
        this.userDataInitialService = userDataInitialService;
    }

    public void test() {
        userDataInitialService.importUserDataFromTempTable();
    }
}
