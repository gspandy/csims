package net.sweet.code.generate.jbpmhandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sweet.code.generate.BaseCodeGenerator;
import net.sweet.code.generate.WordCaseConvertor;
import net.sweet.test.base.WorkSpacePathFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.webmacro.Context;
import org.webmacro.Template;

/**
 * jBPM Handler类生成工具
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public class JbpmHandlerGenerator extends BaseCodeGenerator {
    private static final String  PROCESS_DEFINITION_NAME;
    private static final String  PROCESS_DEFINITION_XML_PATH;
    private static final String  VIEW_TEMPLATE_PATH;
    private static final String  ACTION_TEMPLATE;
    private static final String  ASSIGNMENT_TEMPLATE;
    private static final String  SPRING_XML_TEMPLATE;
    private static final String  ACTIONS_FILE_FOLDER;
    private static final String  ASSIGNMENT_FILE_FOLDER;
    static {
        PROCESS_DEFINITION_NAME = "pre_loan";
        PROCESS_DEFINITION_XML_PATH = WorkSpacePathFactory.getWorkSpacePath()
                + "WebRoot\\WEB-INF\\classes\\net\\sweet\\core\\process\\definition\\preloan\\"
                + PROCESS_DEFINITION_NAME + "\\processdefinition.xml";
        VIEW_TEMPLATE_PATH = WorkSpacePathFactory.getWorkSpacePath()
                + "WebRoot\\WEB-INF\\classes\\net\\sweet\\code\\generate\\jbpmhandler\\view\\";
        ACTION_TEMPLATE = "ActionHandlerTemplate.view";
        ASSIGNMENT_TEMPLATE = "AssignmentHandlerTemplate.view";
        SPRING_XML_TEMPLATE = "SpringHandlerXml.view";
        ACTIONS_FILE_FOLDER = "Actions";
        ASSIGNMENT_FILE_FOLDER = "Assignments";
        File file = new File(DIST_PATH + ACTIONS_FILE_FOLDER);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        file = new File(DIST_PATH + ASSIGNMENT_FILE_FOLDER);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Document getXMLResource() {
        try {
            File xmlFile = new File(PROCESS_DEFINITION_XML_PATH);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(xmlFile);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPdName(Document doc) {
        Element root = doc.getRootElement();
        return root.attributeValue("name");
    }

    public List getActionNames(Document doc) {
        Element root = doc.getRootElement();
        Element taskNode;
        List result = new ArrayList();
        for (Iterator i = root.elementIterator("task-node"); i.hasNext();) {
            taskNode = (Element) i.next();
            Element event;
            for (Iterator i2 = taskNode.elementIterator("event"); i2.hasNext();) {
                event = (Element) i2.next();
                Element action;
                for (Iterator i3 = event.elementIterator("action"); i3
                        .hasNext();) {
                    action = (Element) i3.next();

                    result.add(WordCaseConvertor.getFirstLetterUpperCase(action
                            .elementText("targetBean")));
                }

            }

        }
        return result;
    }

    public List getAssignmentNames(Document doc) {
        Element root = doc.getRootElement();
        Element taskNode;
        List result = new ArrayList();
        for (Iterator i = root.elementIterator("task-node"); i.hasNext();) {
            taskNode = (Element) i.next();
            Element event;
            for (Iterator i2 = taskNode.elementIterator("task"); i2.hasNext();) {
                event = (Element) i2.next();
                Element action;
                for (Iterator i3 = event.elementIterator("assignment"); i3
                        .hasNext();) {
                    action = (Element) i3.next();
                    if (action.elementText("targetBean") != null) {

                        result.add(WordCaseConvertor
                                .getFirstLetterUpperCase(action
                                        .elementText("targetBean")));
                    }
                }
            }

        }
        return result;
    }

    private void saveDist(String name, String templatePath, String type) {
        try {
            OutputStream os = new FileOutputStream(DIST_PATH + type + "//"
                    + name + ".java");
            Context c = wm.getContext();
            c.put("pdName", PROCESS_DEFINITION_NAME);
            c.put("className", name);
            Template t = wm.getTemplate(templatePath);
            t.write(os, c);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateActions() {
        List names = getActionNames(getXMLResource());
        for (Iterator iterator = names.iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            this.saveDist(name, VIEW_TEMPLATE_PATH + ACTION_TEMPLATE,
                    ACTIONS_FILE_FOLDER);
        }

    }

    public void generateAssignments() {
        List names = getAssignmentNames(getXMLResource());
        for (Iterator iterator = names.iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            this.saveDist(name, VIEW_TEMPLATE_PATH + ASSIGNMENT_TEMPLATE,
                    ASSIGNMENT_FILE_FOLDER);
        }
    }

    public void generateSpringXml() {
        try {
            OutputStream os = new FileOutputStream(DIST_PATH
                    + "applicationContext-pd-" + PROCESS_DEFINITION_NAME
                    + ".xml");
            Context c = wm.getContext();
            c.put("pdName", PROCESS_DEFINITION_NAME);
            c.put("actionNames", getActionNames(getXMLResource()));
            c.put("assignmentNames", getAssignmentNames(getXMLResource()));
            Template t = wm.getTemplate(VIEW_TEMPLATE_PATH
                    + SPRING_XML_TEMPLATE);
            t.write(os, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generate() {
        generateActions();
        generateAssignments();
        generateSpringXml();
    }
}