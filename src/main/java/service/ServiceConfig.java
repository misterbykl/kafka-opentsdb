package service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:37
 */
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
    public Service service() {
        return new Service();
    }
}
