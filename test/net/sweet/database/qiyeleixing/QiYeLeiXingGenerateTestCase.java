package net.sweet.database.qiyeleixing;

import java.util.Iterator;
import java.util.List;

import net.sweet.test.base.ApplicationContextTest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 整理企业类型数据类
 * 
 * @author yangyongzhi
 * 
 */
public class QiYeLeiXingGenerateTestCase extends ApplicationContextTest {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 将企业类型分类初始数据重新按照规范整理
	 */
	public void test_generateQiYeLeiXing() {
		assertNotNull(jdbcTemplate);
		try {
			System.out.println(jdbcTemplate.getDataSource().getConnection());

			int count = jdbcTemplate
					.queryForInt("SELECT COUNT(*) FROM BS_QIYELEIXING");
			System.out.println(count);

			// 查询行业表所有数据
			List allData = jdbcTemplate
					.queryForList("SELECT * FROM BS_QIYELEIXING");
			for (Iterator iterator = allData.iterator(); iterator.hasNext();) {
				ListOrderedMap listOrderMap = (ListOrderedMap) iterator.next();
				// System.out.println(listOrderMap);
				// {ID=1001414, TOTALCODE=, NAME=国际组织, LEVEL=, LEVEL1=T,
				// LEVEL2=96, LEVEL3=960, LEVEL4=9600,
				// DESCRP=指联合国和其他国际组织驻我国境内机构等的活动, STATUS=null, FLAG=null,
				// CREATEDATE=null, UPDATEDATE=null}
				String totalCode = listOrderMap.get("TOTALCODE").toString();

				String id = listOrderMap.get("ID").toString();

				// System.out.println(level1 + " " + level2 + " " + level3 + " "
				// + level4 + " ");
				String level = "";

				String level1 = "";
				String level2 = "";
				String level3 = "";
				// 整理四个级别编号，不够在前面补零

				level1 = String.valueOf(totalCode.charAt(0));
				level2 = String.valueOf(totalCode.charAt(0))
						+ String.valueOf(totalCode.charAt(1));
				level3 = String.valueOf(totalCode.charAt(0))
						+ String.valueOf(totalCode.charAt(1))
						+ String.valueOf(totalCode.charAt(2));

				// 根据totalCode判断level值
				if (totalCode.charAt(2) == '0' && totalCode.charAt(1) == '0')
					level = "1";
				else {
					if (totalCode.charAt(2) == '0'
							&& totalCode.charAt(1) != '0')
						level = "2";
					else {
						level = "3";
					}
				}

				// System.out.println(level1 + " " + level2 + " " + level3 + " "
				// + level4 + " " + level + " " + totalCode);
				String updateSql = "UPDATE BS_QIYELEIXING SET LEVEL = '"
						+ level + "',LEVEL1 = '" + level1 + "',LEVEL2 = '"
						+ level2 + "',LEVEL3 = '" + level3 + "' WHERE ID ='"
						+ id + "'";
				System.out.println(updateSql + ";");

				jdbcTemplate.execute(updateSql);// 更新每个行业数据

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
