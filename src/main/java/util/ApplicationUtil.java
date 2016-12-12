package util;

import java.util.Properties;

/**
 * misterbaykal
 * <p>
 * 12/12/16 21:55
 */
public class ApplicationUtil {
    private static final String APP_HOME = "KAFKA_OPENTSDB_HOME";
    private static final String LOG4J_PROPERTIES = "file:./conf/log4j2.xml";
    public static final String APPLICATION_PROPERTIES = "file:./conf/application.properties";

    /**
     * Sets system properties.
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 21:55
     */
    public static void setSystemProperties() {
        Properties properties = System.getProperties();
        properties.setProperty(LogUtil.CONTEXT_SELECTOR_KEY, LogUtil.CONTEXT_SELECTOR_VALUE);
        properties.setProperty(LogUtil.LOG4J_CONFIGURATION_FILE, ApplicationUtil.LOG4J_PROPERTIES);
    }
}
