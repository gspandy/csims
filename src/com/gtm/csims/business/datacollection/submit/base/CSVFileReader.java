package com.gtm.csims.business.datacollection.submit.base;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.CharEncoding;
import org.apache.log4j.Logger;

import com.csvreader.CsvReader;
import com.gtm.csims.business.datacollection.submit.ReportSubmitException;

/**
 * CSV文件操作类.
 * 
 * @author Sweet
 * 
 */
public abstract class CSVFileReader {

    /**
     * 日志调试.
     */
    private static final Logger LOGGER = Logger.getLogger(CSVFileReader.class);

    /**
     * 
     */
    protected ExcelProgressListener listener;

    /**
     * 设置集合数据处理监听器.
     * 
     * @param listener
     */
    public void setListener(ExcelProgressListener listener) {
        this.listener = listener;
    }

    /**
     * 读取文件流信息，并调用子类的具体业务方法对获取csv文件数据进行操作.
     * 
     * @param is
     * @param reportParam
     * @throws ReportSubmitException
     */
    public void read(InputStream is, Map<String, String> reportParam) throws ReportSubmitException {
        this.read(is, CharEncoding.UTF_8, reportParam);
    }

    /**
     * 读取文件流信息，并调用子类的具体业务方法对获取csv文件数据进行操作.
     * 
     * @param is
     * @param reportParam
     * @throws ReportSubmitException
     */
    public void read(InputStream is, String charEncode, Map<String, String> reportParam)
            throws ReportSubmitException {
        try {
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            CsvReader reader = new CsvReader(is, ',', Charset.forName(charEncode));
            reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
            while (reader.readRecord()) { // 逐行读入除表头的数据
                csvList.add(reader.getValues());
            }
            reader.close();
            this.read(csvList, reportParam);
        } catch (Exception ex) {
            LOGGER.error("读取CSV文件发生错误", ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error("读取CSV文件后关闭文件流发生错误", e);
            }
        }
    }

    /**
     * 分析CSV文件后获取的字符串数组List集合，并结合参数设置进行具体业务操作
     * 
     * @param csvList
     *            读取CSV文件后获取的字符串数组List集合
     * @param reportParam
     *            参数设置
     * @throws ReportSubmitException
     */
    protected abstract void read(ArrayList<String[]> csvList, Map<String, String> reportParam)
            throws ReportSubmitException;

    /**
     * 写入CSV文件
     */
    // public void writeCsv() {
    // try {
    // String csvFilePath = "c:/test.csv";
    // CsvWriter wr = new CsvWriter(csvFilePath, ',',
    // Charset.forName("GB2312"));
    // String[] contents = { "aaaaa", "bbbbb", "cccccc", "ddddddddd" };
    // wr.writeRecord(contents);
    // wr.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
}
