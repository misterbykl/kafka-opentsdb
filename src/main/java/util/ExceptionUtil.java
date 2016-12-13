package util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * misterbaykal
 * <p>
 * 12/12/16 23:27
 */
public class ExceptionUtil {

    /**
     * Gets stack trace string.
     *
     * @param argE          the arg e
     * @param argMethodName the arg method name
     *                      <p>
     *                      <p>
     *                      misterbaykal
     *                      <p>
     *                      12/12/16 23:29
     */
    public static void getStackTraceString(Exception argE, String argMethodName) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            argE.printStackTrace(pw);
            System.out.println(StringUtil.append("Exception while in method ", argMethodName, ". ", sw));
        }
    }
}
