package net.sweet.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.gtm.csims.business.word.WordGenerator;

public class WordGeneratorTestCase extends TestCase {

    public void test() {
        Map map = new HashMap();
        map.put("FILED8", "aaaa");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D://factbook.doc");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            // 输出word文件
            OutputStream outs = new FileOutputStream("D:\\b.doc");
            new WordGenerator().replaceDoc(fis, map, outs);
            outs.write(ostream.toByteArray());
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
