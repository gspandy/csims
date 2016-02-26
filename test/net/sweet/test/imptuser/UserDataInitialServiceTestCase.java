package net.sweet.test.imptuser;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.importdata.user.UserDataInitialService;

/**
 * 
 * @author Sweet
 * 
 */
public class UserDataInitialServiceTestCase extends ApplicationContextTest {
    private UserDataInitialService userDataInitialService;

    public void setUserDataInitialService(
            UserDataInitialService userDataInitialService) {
        this.userDataInitialService = userDataInitialService;
    }

    public void _testGetAllParentNos() {
        String[] allParentNoArr = userDataInitialService
                .getAllParentNos("A1000151000156");
        for (int i = 0; i < allParentNoArr.length; i++) {
            System.out.println(allParentNoArr[i]);
        }
    }

    public void _testIsAePeople() {
        System.out.println(userDataInitialService
                .isAePeople("5125011979031dd40010"));
    }

    public void testIsThisAepeopleExsitInJgguard() {
        System.out.println(userDataInitialService
                .isAePeople("510727197106050058"));
    }

}
