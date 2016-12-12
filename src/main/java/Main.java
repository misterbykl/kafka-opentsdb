import kafka.ConsumerConfig;
import opentsdb.OpenTsdbConfig;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ServiceConfig;
import util.ApplicationUtil;
import util.LogUtil;

/**
 * Main.java
 * <p>
 * misterbaykal
 * <p>
 * 12/12/16 21:42
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     *             <p>
     *             misterbaykal
     *             <p>
     *             12/12/16 21:42
     */
    public static void main(String[] args) {
        ApplicationUtil.setSystemProperties();
        final Logger logger = LogUtil.getRootLogger();
        logger.info("kafka-opentsdb is starting...");

        AnnotationConfigApplicationContext applicationContext = null;
        try {
            applicationContext = new AnnotationConfigApplicationContext();
            applicationContext.register(ConsumerConfig.class, OpenTsdbConfig.class, ServiceConfig.class);
            applicationContext.refresh();
            applicationContext.registerShutdownHook();
            logger.info("kafka-opentsdb has started");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (applicationContext != null) {
                logger.info("App context is closing...");
                applicationContext.close();
                logger.info("App context is closed");
            }
        }
    }
}
