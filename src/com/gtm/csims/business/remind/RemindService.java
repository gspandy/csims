package com.gtm.csims.business.remind;

/**
 * 写日志、发消息接口
 * 
 * @author Sweet
 * 
 */
public interface RemindService {

    void writeLog(String content, String priority, String category,
            String thread, String processer);

    void sendMessage(String title, String content, String receiver,
            String sender, String category);
}
