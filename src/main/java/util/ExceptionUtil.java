package util;

import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * misterbaykal
 * <p>
 * 12/12/16 23:27
 */
public class ExceptionUtil {

    private static final Logger logger = LogUtil.getRootLogger();

    /**
     * Gets custom stack trace.
     *
     * @param argThrowable the arg throwable
     * @return the custom stack trace
     * <p>
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 23:29
     */
    public static String getCustomStackTrace(Throwable argThrowable) {
        return StringUtil.append("Reason: ", argThrowable.toString(), " Line: ", argThrowable.getStackTrace()[0]);
    }

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
            logger.error(StringUtil.append("Exception while in method ", argMethodName, ". ", sw));
        }
    }
}
