package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import service.Service;
import util.LogUtil;

import java.util.Arrays;
import java.util.List;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:17
 */
public class Consumer {
    private final Logger logger = LogUtil.getRootLogger();

    private List<String> topicList;

    @Autowired
    private KafkaConsumer<String, String> consumer;
    @Autowired
    private Service service;


    /**
     * Instantiates a new Consumer.
     *
     * @param topics   the topics
     * @param consumer the consumer
     *                 <p>
     *                 <p>
     *                 misterbaykal
     *                 <p>
     *                 12/12/16 22:26
     */
    public Consumer(String topics, KafkaConsumer<String, String> consumer) {
        this.topicList = Arrays.asList(topics.split(","));
        this.consumer = consumer;
        this.consumer.subscribe(this.topicList);
        String server = System.getProperties().getProperty(ConsumerConfig.CONSUMER_BOOTSTRAP_SERVERS);
        String group = System.getProperties().getProperty(ConsumerConfig.GROUP_ID);
        logger.info("Kafka consumer is ready. - Server: " + server + ", Group: " + group + ". Topics: " + topics);
    }

    /**
     * Consume.
     * <p>
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 22:26
     */
    @Scheduled(fixedDelayString = "10")
    public void consume() {
        ConsumerRecords<String, String> records = consumer.poll(0);
        if (!records.isEmpty()) {
            for (ConsumerRecord<String, String> record : records) {
                if (this.getTopicList().get(0).equals(record.topic())) {
                    this.service.shreadAndSaveMessage(record.value());
                    logger.info("Message is processed");
                }
            }
        }
    }

    /**
     * Get Topics
     * <p>
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 22:26
     */
    private List<String> getTopicList() {
        return this.topicList;
    }
}
