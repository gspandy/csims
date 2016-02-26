package net.sweet.code.generate.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sweet.code.generate.BaseCodeGenerator;
import net.sweet.code.generate.WordCaseConvertor;

import org.webmacro.Context;
import org.webmacro.Template;

/**
 * DAO生成工具
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public class DAOGenerator extends BaseCodeGenerator {
    private static final String VIEW_TEMPLATE_PATH;
    private static final String DAO_TEMPLATE;
    private static final String DAO_TEMPlATE_IMPLEMENT;
    private static final String SPRING_XML_TEMPLATE;
    private static final String DAO_FILE_FOLDER;
    private static final String DAO_IMPLEMENT_FILE_FOLDER;
    static {
        VIEW_TEMPLATE_PATH = "F:\\MyWorkSapces\\csims\\WebRoot\\WEB-INF\\classes\\net\\sweet\\code\\generate\\dao\\view\\";
        DAO_TEMPLATE = "DAOTemplate.view";
        DAO_TEMPlATE_IMPLEMENT = "DAOImplTemplate.view";
        SPRING_XML_TEMPLATE = "SpringDaoXml.view";
        DAO_FILE_FOLDER = "DAOs";
        DAO_IMPLEMENT_FILE_FOLDER = "DAOImplements";
        File file = new File(DIST_PATH + DAO_FILE_FOLDER);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        file = new File(DIST_PATH + DAO_IMPLEMENT_FILE_FOLDER);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List loadModulePackage() {
        /*
         * File file = new File(DAOGenerator.class.getClassLoader().getResource(
         * this.modulePath).getFile());
         */
        File file = new File(
                "F:\\MyWorkSapces\\csims\\WebRoot\\WEB-INF\\classes\\com\\gtm\\csims\\model\\");
        File[] javaFiles = file.listFiles();
        List moduleNames = new ArrayList();
        for (int i = 0; i < javaFiles.length; i++) {
            if (javaFiles[i].isFile()) {
                moduleNames.add(javaFiles[i].getName().replace(".class", ""));
            }
        }
        return moduleNames;
    }

    public void generateSpringXml(List modules) {
        try {
            List lowCaseModeules = new ArrayList();
            // wm.getBroker();
            for (Iterator iterator = modules.iterator(); iterator.hasNext();) {
                lowCaseModeules.add(WordCaseConvertor
                        .getFirstLetterLowCase((String) iterator.next()));
            }
            OutputStream os = new FileOutputStream(DIST_PATH + "springDao.xml");
            Context c = wm.getContext();
            c.put("modules", lowCaseModeules);
            Template t = wm.getTemplate(VIEW_TEMPLATE_PATH
                    + SPRING_XML_TEMPLATE);
            t.write(os, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateDAO(String moduleName) {
        try {
            // wm.getBroker();
            OutputStream os = new FileOutputStream(DIST_PATH + DAO_FILE_FOLDER
                    + "\\" + moduleName + "DAO.java");
            Context c = wm.getContext();
            c.put("moduleName", moduleName);
            Template t = wm.getTemplate(VIEW_TEMPLATE_PATH + DAO_TEMPLATE);
            t.write(os, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateDAOImplement(String moduleName) {
        try {
            // wm.getBroker();
            OutputStream os = new FileOutputStream(DIST_PATH
                    + DAO_IMPLEMENT_FILE_FOLDER + "\\" + moduleName
                    + "DAOImpl.java");
            Context c = wm.getContext();
            c.put("moduleName", moduleName);
            Template t = wm.getTemplate(VIEW_TEMPLATE_PATH
                    + DAO_TEMPlATE_IMPLEMENT);
            t.write(os, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generate() {
        List modules = loadModulePackage();
        generateSpringXml(modules);
        for (Iterator iterator = modules.iterator(); iterator.hasNext();) {
            String module = (String) iterator.next();
            generateDAO(module);
            generateDAOImplement(module);
        }
    }

}
