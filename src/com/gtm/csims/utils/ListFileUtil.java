package com.gtm.csims.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报文检索
 * 
 * @author DJF
 * 
 */
public class ListFileUtil {
    /**
     * 实现了FilenameFilter接口，用于过滤文件
     */
    static class MyFilenameFilter implements FilenameFilter {
        private String suffix = "";
        public MyFilenameFilter(String suffix) {
            this.suffix = suffix;
        }

        public boolean accept(File dir, String name) {
            if (new File(dir, name).isFile()) {
                return name.endsWith(suffix);
            } else {
                return true;
            }
        }

    }

    /**
     * 列出目录中通过文件名过滤器过滤后的文件
     * 
     * @param filter
     *            文件名过滤器对象
     * @param dirName
     *            目录名
     */
    public static List<Map<String, Object>> listFilesByFilenameFilter(
            FilenameFilter filter, String dirName) {
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();

        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dirName.endsWith(File.separator)) {
            dirName = dirName + File.separator;
        }
        File dirFile = new File(dirName);
        // 如果dir对应的文件不存在，或者不是一个文件夹则退出
        if (!dirFile.exists() || (!dirFile.isDirectory())) {
            return null;
        }

        // 检查源文件夹下所有能通过过滤器的文件包括子目录
        File[] files = dirFile.listFiles(filter);
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (files[i].isFile()) {
                map.put(dirFile.getName(), files[i].getName());
                maplist.add(map);
            } else if (files[i].isDirectory()) {
                map.put(dirFile.getName(), files[i].getName());
                List<Map<String, Object>> list = ListFileUtil
                        .listFilesByFilenameFilter(filter, files[i]
                                .getAbsolutePath());
                map.put(files[i].getName(), list);
                maplist.add(map);
            }

        }

        return maplist;
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getTextFile(String filepath)
            throws UnsupportedEncodingException {
        List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
        File file = new File(filepath);
        FilenameFilter mf = new MyFilenameFilter(".txt");
        List<Map<String, Object>> maplist = listFilesByFilenameFilter(mf,
                filepath);
        if (maplist != null) {
            for (Map<String, Object> map : maplist) {
                Object obj = map.get(file.getName());
                List<Map<String, Object>> list = (List<Map<String, Object>>) map
                        .get(obj);
                if (list != null) {
                    for (Map<String, Object> map2 : list) {
                        String path = obj + "\\" + map2.get(obj);
                        Map<String, Object> maps = new HashMap<String, Object>();
                        maps.put("fileType", obj.toString());
                        maps.put("fileName", map2.get(obj));
                        maps.put("filePath", java.net.URLEncoder.encode(path,
                                "UTF-8"));
                        File files = new File(filepath + "\\" + path);
                        if (files.isFile()) {
                            reslist.add(maps);
                        }
                    }
                }
            }
        }
        return reslist;
    }

}
