package net.sweet.test.imptuser;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.importdata.user.UserDataInitialService;

/**
 * 
 * @author Sweet
 * 
 */
public class ImportJguardDataTestCase extends ApplicationContextTest {
    private UserDataInitialService userDataInitialService;

    public void setUserDataInitialService(UserDataInitialService userDataInitialService) {
        this.userDataInitialService = userDataInitialService;
    }

    /**
     * 导入IMPORTTEMP_JGUARD表中管理员数据到jguard中
     */
    public void _testIntoJguard() {
        userDataInitialService.importUserIntoJguard();
    }

    /**
     * 从临时表IMPORTTEMP_JGUARD获取数据向BS_USERINFOOFJG表导入数据
     */
    public void _testImportUserIntoBSUSERINFOOFJG() {
        userDataInitialService.importUserIntoBSUSERINFOOFJG();
    }

    /**
     * 从执法人员表中获取数据向jguard表导入数据
     */
    public void testImportAePeopleIntoJguard() {
        userDataInitialService.importAePeopleIntoJguard();
    }
}
