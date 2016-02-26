package com.gtm.csims.utils;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * Json对象转化时对Date类型的处理方式
 * 
 * @author Sweet
 * 
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
    private static final String FORMAT = "yyyy-MM-dd";

    public Object processArrayValue(Object value, JsonConfig config) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }

    private Object process(Object value) {

        if (value instanceof Date) {
            return DateFormatUtils.format((Date) value, FORMAT);
        }
        return value == null ? StringUtils.EMPTY : value.toString();
    }
}
