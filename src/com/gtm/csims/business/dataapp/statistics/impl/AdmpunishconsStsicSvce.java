package com.gtm.csims.business.dataapp.statistics.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.dataapp.statistics.BaseStatisticsService;
import com.gtm.csims.business.dataapp.statistics.StatisticsService;
import com.gtm.csims.business.enforce.EnforceService;
import com.gtm.csims.model.BsAdmpunishcons;

/**
 * 
 * @author Sweet
 * 
 */
public class AdmpunishconsStsicSvce extends BaseStatisticsService implements StatisticsService {

    private EnforceService enforceService;

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    /**
     * 当前报表需要重新复制行
     * 
     * @return
     */
    public Boolean isOnlyFill() {
        return false;
    }

    /**
     * 返回报表的横向单元格数量,从第A列（0）开始-AA列
     */
    public Integer getxCount() {
        return 9;
    }

    /**
     * 统计流程
     */
    @Transactional(readOnly = false)
    public Map<String, Object> doStatistics(Map<String, String> paramsMap) {
        String uuid = this.insertTempTable(paramsMap);
        this.calculateTempData(uuid);
        return this.getResultData(uuid, paramsMap);
    }

    /**
     * 根据统计参数插入临时表数据
     * 
     * @param paramsMap
     */
    private String insertTempTable(Map<String, String> paramsMap) {
        // 针对每一次统计生成唯一ID，以便后期统计最后结果
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    /**
     * 根据临时表中的初始数据进行合计等计算
     * 
     * @param uuid
     */
    private void calculateTempData(String uuid) {
    }

    /**
     * 查询统计临时表,将返回数据组装为显示表格格式,作为报表结果数据
     * 
     * @param uuid
     * @return
     */
    private Map<String, Object> getResultData(String uuid, Map<String, String> paramsMap) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<?> result = this.enforceService.queryAllBsAdmpunishcons(paramsMap.get("ORGNO").toString());
        BsAdmpunishcons row = null;
        for (int j = 1; j <= result.size(); j++) {// 返回数据条数
            // 循环每条数据
            try {
                row = (BsAdmpunishcons) result.get(j - 1);
            } catch (Exception e) {
                // 如果查询出错
                for (int i = 1; i <= this.getxCount(); i++) {// 15列(A1-O1)
                    resultMap.put(j + "-" + i, StringUtils.EMPTY);
                }
                continue;
            }
            resultMap.put(j + "-" + 1, row.getAeno());
            resultMap.put(j + "-" + 2, row.getAeopnionno());
            resultMap.put(j + "-" + 3, row.getPunishno());
            resultMap.put(j + "-" + 4, row.getAeorgnm());
            resultMap.put(j + "-" + 5, row.getAeedorgnm());
            resultMap.put(j + "-" + 6, row.getPunishbookno());
            resultMap.put(j + "-" + 7, row.getPunishcount());
            resultMap.put(j + "-" + 8, row.getCrtlogin());
            resultMap.put(j + "-" + 9, DateFormatUtils.format(row.getCreatedate(), DATE_TIME_FORMAT));
        }
        return resultMap;
    }

}
