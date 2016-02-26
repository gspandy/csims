package net.sweet.code.generate;

/**
 * 类名大小写处理类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class WordCaseConvertor {
    public static String getFirstLetterLowCase(String s) {
        s = Character.toLowerCase(s.charAt(0)) + s.substring(1);
        return s;
    }

    public static String getFirstLetterUpperCase(String s) {
        s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
        return s;
    }
}
