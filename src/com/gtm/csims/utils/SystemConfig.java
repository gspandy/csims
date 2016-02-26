package com.gtm.csims.utils;

import java.util.ResourceBundle;

/**
 * 获取系统配置信息
 * 
 * @author Sweet
 * 
 */
public class SystemConfig {
    public static final ResourceBundle PROJECT_CONFIG = ResourceBundle
            .getBundle("../ec_config");

    public static String getValueFromKey(String key) {
        String value = PROJECT_CONFIG.getString(key);
        return value;
    }
}
