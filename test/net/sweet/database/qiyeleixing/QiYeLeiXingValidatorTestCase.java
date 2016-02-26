package net.sweet.database.qiyeleixing;

import java.util.Iterator;
import java.util.List;

import net.sweet.test.base.ApplicationContextTest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 验证企业类型数据类
 * 
 * @author yangyongzhi
 * 
 */
public class QiYeLeiXingValidatorTestCase extends ApplicationContextTest {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 验证整理后的数据是否正确
	 */
	public void test_validateData() {
		// 查询行业表所有数据
		List allData = jdbcTemplate.queryForList("SELECT * FROM BS_HANGYE");
		for (Iterator iterator = allData.iterator(); iterator.hasNext();) {
			ListOrderedMap listOrderMap = (ListOrderedMap) iterator.next();
			// System.out.println(listOrderMap);
			// {ID=1001414, TOTALCODE=, NAME=国际组织, LEVEL=, LEVEL1=T,
			// LEVEL2=96, LEVEL3=960, LEVEL4=9600,
			// DESCRP=指联合国和其他国际组织驻我国境内机构等的活动, STATUS=null, FLAG=null,
			// CREATEDATE=null, UPDATEDATE=null}
			String level1 = listOrderMap.get("LEVEL1").toString();
			String level2 = listOrderMap.get("LEVEL2").toString();
			String level3 = listOrderMap.get("LEVEL3").toString();
			String level4 = listOrderMap.get("LEVEL4").toString();
			String level = listOrderMap.get("LEVEL").toString();
			String totalCode = listOrderMap.get("TOTALCODE").toString();

			if (level2 == null || level2.trim().equals("")) {
				assertEquals(level, "1");
				assertEquals(totalCode.length(), 1);
				assertEquals(level2.length(), 0);
				assertEquals(level3.length(), 0);
				assertEquals(level4.length(), 0);
			} else {
				if (level3 == null || level3.trim().equals("")) {
					assertEquals(level, "2");
					assertEquals(totalCode.length(), 3);
					assertEquals(level2.length(), 2);
					assertEquals(level3.length(), 0);
					assertEquals(level4.length(), 0);
				} else {
					if (level4 == null || level4.trim().equals("")) {
						assertEquals(level, "3");
						assertEquals(totalCode.length(), 4);
						assertEquals(level2.length(), 2);
						assertEquals(level3.length(), 3);
						assertEquals(level4.length(), 0);
					} else {
						assertEquals(level, "4");
						assertEquals(totalCode.length(), 5);
						assertEquals(level2.length(), 2);
						assertEquals(level3.length(), 3);
						assertEquals(level4.length(), 4);

					}
				}
			}

		}
	}
}
