package io.github.ricky.common.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/9/10
 * @className KafkaProducerConfiguration
 * @desc kafka生产者配置类 TODO
 */
@Configuration
public class KafkaProducerConfiguration {

    @Value("${kafka.bootstrap_servers_config}")
    private String bootstrap_servers_config;

    @Value("${kafka.retries_config}")
    private String retries_config;

    @Value("${kafka.batch_size_config}")
    private String batch_size_config;

    @Value("${kafka.linger_ms_config}")
    private String linger_ms_config;

    @Value("${kafka.buffer_memory_config}")
    private String buffer_memory_config;

    @Value("${kafka.topic}")
    private String topic;

    @Bean
    public KafkaTemplate<?, ?> kafkaTemplate() {
        HashMap<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers_config);
        configs.put(ProducerConfig.RETRIES_CONFIG, retries_config);
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, batch_size_config);
        configs.put(ProducerConfig.LINGER_MS_CONFIG, linger_ms_config);
        configs.put(ProducerConfig.BUFFER_MEMORY_CONFIG, buffer_memory_config);
        configs.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class);
        // 设置序列化
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 设置自定义分区
        DefaultKafkaProducerFactory<?, ?> producerFactory = new DefaultKafkaProducerFactory<>(configs);
        return new KafkaTemplate<>(producerFactory);
    }

}
