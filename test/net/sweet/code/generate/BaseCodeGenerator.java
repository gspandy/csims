package net.sweet.code.generate;

import org.webmacro.InitException;
import org.webmacro.WM;
import org.webmacro.WebMacro;

/**
 * 
 * @author Sweet
 * 
 */
public abstract class BaseCodeGenerator implements CodeGenerator {
    protected static final String DIST_PATH = "F:\\MyWorkSapces\\testtemp\\";

    protected WebMacro wm;

    public BaseCodeGenerator() {
        super();
        try {
            wm = new WM();
        } catch (InitException e) {
            e.printStackTrace();
        }
    }

    public abstract void generate();
}
