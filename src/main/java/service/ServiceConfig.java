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
    public Service service(@Value("${opentsdb.config.path}") String tsdbConfigPath, @Value("${opentsdb.word.splitter}") String wordSplitter) {
        return new Service(tsdbConfigPath, wordSplitter);
    }
}
