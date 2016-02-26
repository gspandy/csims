package net.sweet.database.pcbindex;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gtm.csims.utils.Constants;

import net.sweet.test.base.ApplicationContextTest;

public class PCBIndexGenerateTestCase extends ApplicationContextTest {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void test_generate() {
        assertNotNull(jdbcTemplate);
        String updateChengDuFenHangSql = "UPDATE BS_PCB SET orgIndex = '1000' WHERE no = '"
                + Constants.PCB_SC_ORG_NO + "'";
        jdbcTemplate.execute(updateChengDuFenHangSql);
        System.out.println(updateChengDuFenHangSql);

        StringBuffer getZhongZhiSql = new StringBuffer(
                "SELECT * FROM BS_PCB where parentno = '"
                        + Constants.PCB_SC_ORG_NO + "' ORDER BY no");

        List allZhongZhi = jdbcTemplate.queryForList(getZhongZhiSql.toString());
        int i = 1;
        for (Iterator iterator = allZhongZhi.iterator(); iterator.hasNext();) {
            ListOrderedMap listOrderMap = (ListOrderedMap) iterator.next();
            String ZhongZhiNo = listOrderMap.get("no").toString();
            StringBuffer updateZhongZhiIndexSql = new StringBuffer(
                    "UPDATE BS_PCB SET orgIndex = '")
                    .append(String.valueOf(i * 10000)).append("' WHERE no = '")
                    .append(ZhongZhiNo).append("'");
            System.out.println(updateZhongZhiIndexSql.toString());
            jdbcTemplate.execute(updateZhongZhiIndexSql.toString());
            StringBuffer getXianZhiHangSql = new StringBuffer(
                    "SELECT * FROM BS_PCB where parentno = '").append(
                    ZhongZhiNo).append("' ORDER BY no");
            List allXianZhiHang = jdbcTemplate.queryForList(getXianZhiHangSql
                    .toString());
            int j = 1;
            for (Iterator iterator2 = allXianZhiHang.iterator(); iterator2
                    .hasNext();) {
                ListOrderedMap listOrderMap2 = (ListOrderedMap) iterator2
                        .next();
                String XianZhiHangNo = listOrderMap2.get("no").toString();
                StringBuffer updateXianZhiHangIndexSql = new StringBuffer(
                        "UPDATE BS_PCB SET orgIndex = '")
                        .append(String.valueOf(i * 10000 + j))
                        .append("' WHERE no = '").append(XianZhiHangNo)
                        .append("'");
                System.out.println(updateXianZhiHangIndexSql.toString());
                jdbcTemplate.execute(updateXianZhiHangIndexSql.toString());
                j++;
            }
            i++;
        }
    }
}
