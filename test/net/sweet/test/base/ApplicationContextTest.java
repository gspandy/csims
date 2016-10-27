package net.sweet.test.base;

import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.test.annotation.AbstractAnnotationAwareTransactionalTests;

/**
 * Spring容器对象测试基类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class ApplicationContextTest extends AbstractAnnotationAwareTransactionalTests {
	protected static final String WORKPATH;
	protected static final String TEST_TEMPOTORY_PATH;
	static {
		Properties props = System.getProperties(); // 获得系统属性集
		String osName = props.getProperty("os.name"); // 操作系统名称
		// String osArch = props.getProperty("os.arch"); // 操作系统构架
		// String osVersion = props.getProperty("os.version"); // 操作系统版本
		// System.out.println("================" + osName + "-" + osArch + "-"
		// + osVersion);
		if (osName.indexOf("Windows") != -1) {
			// WORKPATH = "file:E:/csims/WebRoot/WEB-INF/conf/spring/";
			// TEST_TEMPOTORY_PATH = "E:/csims/testtemp/";
			WORKPATH = "file:C:\\Users\\dmall\\git\\csims\\WebContent\\WEB-INF\\conf/spring/";
			TEST_TEMPOTORY_PATH = "E:\\TESTCASE_TEMP_DIR/";
		} else {
			WORKPATH = "file:/Users/yangyongzhi/Workspaces/csims/WebRoot/WEB-INF/conf/spring/";
			TEST_TEMPOTORY_PATH = "/Users/yangyongzhi/Workspaces/TESTCASE_TEMP_DIR/";
		}
	}
	protected ResourceBundle rb;

	@Override
	protected String[] getConfigLocations() {
		super.setDefaultRollback(false);
		super.setAutowireMode(AUTOWIRE_BY_NAME);
		return new String[] {
		        WORKPATH + "applicationContext-datasource-test.xml",
		        WORKPATH + "applicationContext-properties.xml",
		        WORKPATH + "applicationContext-persistence.xml",
		        WORKPATH + "service/applicationContext-service-dataimport.xml",
		        // WORKPATH + "service/applicationContext-service-exam.xml",
		        WORKPATH + "service/applicationContext-service-question.xml",
		        WORKPATH + "service/applicationContext-service-statisticcs.xml",
		        WORKPATH + "service/applicationContext-service-enforce.xml",
		        WORKPATH + "service/applicationContext-service-log.xml",
		        WORKPATH + "applicationContext-serial-number.xml", WORKPATH + "applicationContext-schedule.xml"
		// WORKPATH + "service/applicationContext-service-statisticcs.xml"
		};
	}

	@Override
	public void setDefaultRollback(boolean defaultRollback) {
		super.setDefaultRollback(false);
	}

}
