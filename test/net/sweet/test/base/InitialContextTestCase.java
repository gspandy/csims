package net.sweet.test.base;

/**
 * 
 * @author Sweet
 * 
 */
public class InitialContextTestCase extends ApplicationContextTest {

    public void test() {
        System.out.println("Spring Application Name is :"
                + applicationContext.getDisplayName());
        System.out.println("Bean Count is :"
                + applicationContext.getBeanDefinitionCount());
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (int i = 0; i < beans.length; i++) {
            System.out.println("Bean[" + i + "] Name is :" + beans[i]);
        }
    }
}