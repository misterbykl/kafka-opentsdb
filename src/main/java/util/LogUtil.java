package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:02
 */
public class LogUtil {
    private static final String EXCEPTION_LOGGER_NAME = "exceptionLogger";
    private static final String ROOT_LOGGER_NAME = "rootLogger";
    public static final String CONTEXT_SELECTOR_KEY = "Log4jContextSelector";
    public static final String CONTEXT_SELECTOR_VALUE = "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector";
    public static final String LOG4J_CONFIGURATION_FILE = "log4j.configurationFile";

    /**
     * Instantiates a new Log util.
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 22:04
     */
    public LogUtil() {
    }

    /**
     * Gets logger.
     *
     * @return the logger
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 22:04
     */
    private static Logger getLogger(String argLoggerName) {
        return LogManager.getLogger(argLoggerName);
    }

    /**
     * Gets root logger.
     *
     * @return the root logger
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 22:04
     */
    public static Logger getRootLogger() {
        return LogUtil.getLogger(LogUtil.ROOT_LOGGER_NAME);
    }

    /**
     * Gets exception logger.
     *
     * @return the exception logger
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 22:04
     */
    public static Logger getExceptionLogger() {
        return LogUtil.getLogger(LogUtil.EXCEPTION_LOGGER_NAME);
    }
}
