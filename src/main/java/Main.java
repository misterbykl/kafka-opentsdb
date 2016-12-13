import config.MainConfig;
import kafka.ConsumerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ServiceConfig;
import util.ApplicationUtil;
import util.ExceptionUtil;

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
        System.out.println("kafka-opentsdb is starting...");
        AnnotationConfigApplicationContext applicationContext = null;
        try {
            applicationContext = new AnnotationConfigApplicationContext();
            applicationContext.register(MainConfig.class, ConsumerConfig.class, ServiceConfig.class);
            applicationContext.refresh();
            applicationContext.registerShutdownHook();
            System.out.println("kafka-opentsdb has started");

        } catch (Exception e) {
            System.out.println("Exception while starting app");
            ExceptionUtil.getStackTraceString(e, "shredAndSaveMassage");
        }
    }
}
