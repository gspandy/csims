package net.sweet.test;

/**
 * 
 * @author Sweet
 * 
 */
public class ArrayCopyTestCase {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] s = new String[] { "1", "2", "3", "4", "5", "6", "7", "8",
                "9", "10" };
        String[] a = new String[3];
        System.arraycopy(s, 4, a, 0, 3);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

}
