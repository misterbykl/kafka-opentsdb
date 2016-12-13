package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:37
 */
@PropertySource(value = "file:./conf/application.properties")
@Configuration
public class ServiceConfig {

    /**
     * Service service.
     *
     * @return the service
     * <p>
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 22:37
     */
    @Bean
    public Service service(@Value("${hbase.server}") String server, @Value("${hbase.table.name}") String tableName) {
        return new Service(server, tableName);
    }
}
