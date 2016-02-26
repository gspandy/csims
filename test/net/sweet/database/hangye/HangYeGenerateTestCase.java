package net.sweet.database.hangye;

import java.util.Iterator;
import java.util.List;

import net.sweet.test.base.ApplicationContextTest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gtm.csims.utils.StringUtil;

/**
 * 整理行业基础数据类
 * 
 * @author yangyongzhi
 * 
 */
public class HangYeGenerateTestCase extends ApplicationContextTest {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 将行业分类初始数据重新按照规范整理
	 */
	public void test_generateHangYe() {
		assertNotNull(jdbcTemplate);
		try {
			System.out.println(jdbcTemplate.getDataSource().getConnection());

			int count = jdbcTemplate
					.queryForInt("SELECT COUNT(*) FROM BS_HANGYE");
			System.out.println(count);

			// List allData = jdbcTemplate
			// .queryForList("SELECT * FROM BS_HANGYE fetch first 5 rows only");

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

				String fiveCode = "";

				String id = listOrderMap.get("ID").toString();

				// System.out.println(level1 + " " + level2 + " " + level3 + " "
				// + level4 + " ");

				// 整理四个级别编号，不够在前面补零
				if (level1 != null && !level1.trim().equals(""))
					level1 = StringUtil.addZero(level1, 1);
				if (level2 != null && !level2.trim().equals(""))
					level2 = StringUtil.addZero(level2, 2);
				if (level3 != null && !level3.trim().equals(""))
					level3 = StringUtil.addZero(level3, 3);
				if (level4 != null && !level4.trim().equals(""))
					level4 = StringUtil.addZero(level4, 4);

				// 计算当前行业的级别数
				String level = "4";
				if (level2 == null || level2.trim().equals(""))
					level = "1";
				else {
					if (level3 == null || level3.trim().equals(""))
						level = "2";
					else {
						if (level4 == null || level4.trim().equals(""))
							level = "3";
					}
				}

				// 判断并得出当前行业的完整行业编号
				StringBuffer totalCode = new StringBuffer("");

				if (level2 == null || level2.trim().equals("")) {
					totalCode.append(level1);
				} else {
					if (level3 == null || level3.trim().equals("")) {
						totalCode.append(level1).append(level2);
					} else {
						if (level4 == null || level4.trim().equals("")) {
							totalCode.append(level1).append(level3);
						} else {
							totalCode.append(level1).append(level4);
						}
					}
				}
				if (totalCode.length() != 1)
					fiveCode = StringUtil.addZeroLast(totalCode.toString(), 5);
				else
					fiveCode = totalCode.toString();
				// System.out.println(level1 + " " + level2 + " " + level3 + " "
				// + level4 + " " + level + " " + totalCode);
				String updateSql = "UPDATE BS_HANGYE SET TOTALCODE = '"
						+ totalCode + "',LEVEL = '" + level + "',LEVEL1 = '"
						+ level1 + "',LEVEL2 = '" + level2 + "',LEVEL3 = '"
						+ level3 + "',LEVEL4 = '" + level4 + "',FIVECODE = '"
						+ fiveCode + "' WHERE ID ='" + id + "'";
				System.out.println(updateSql + ";");

				jdbcTemplate.execute(updateSql);// 更新每个行业数据

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
