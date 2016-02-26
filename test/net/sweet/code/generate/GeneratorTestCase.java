package net.sweet.code.generate;

import junit.framework.TestCase;
import net.sweet.code.generate.dao.DAOGenerator;
import net.sweet.code.generate.jbpmhandler.JbpmHandlerGenerator;

/**
 * 
 * @author Sweet
 * 
 */
public class GeneratorTestCase extends TestCase {
    public void testGenerateJbpmHandler() {
        CodeGenerator generator = new JbpmHandlerGenerator();
        generator.generate();
    }

    public void testGenerateDao() {
        CodeGenerator generator = new DAOGenerator();
        generator.generate();
    }
}
