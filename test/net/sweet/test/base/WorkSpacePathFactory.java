package net.sweet.test.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取当前eclipse工作绝对路径
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class WorkSpacePathFactory {
    private static String WORKPATH;
    private static String TESTCASE_TEMP;
    static {
        InputStream is = WorkSpacePathFactory.class.getClassLoader()
                .getResourceAsStream("workspace_path.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
            WORKPATH = prop.getProperty("workpath");
            TESTCASE_TEMP = prop.getProperty("testcase.temp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getWorkSpacePath() {
        return WORKPATH;
    }

    public static String getTestCaseTemp() {
        return TESTCASE_TEMP;
    }
}
