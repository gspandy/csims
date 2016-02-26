package com.gtm.csims.business.word;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

/**
 * 
 * @author Sweet
 * 
 */
public class WordGenerator {
    /**
     * 读取word模板并替换变量
     * 
     * @param srcPath
     * @param map
     * @return
     */
    public void replaceDoc(InputStream is, Map<String, String> map, OutputStream os) {
        if (is == null || os == null) {
            return;
        }
        try {
            // 读取word模板 
            HWPFDocument doc = new HWPFDocument(is);
            // 读取word文本内容
            Range docStr = doc.getRange();
            // 替换文本内容
            // 表格内的param1使用“\r”没有换行，而表格内的参数使用“(char)11”换行；
            // 表格外的参数param3使用“\r”换行，而表格外的参数param4使用“(char)11”换行
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (entry.getValue() == null) {
                        docStr.replaceText("#" + entry.getKey() + "#", " ");
                    } else {
                        docStr.replaceText("#" + entry.getKey() + "#", entry.getValue());
                    }
                }
            }
            doc.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
