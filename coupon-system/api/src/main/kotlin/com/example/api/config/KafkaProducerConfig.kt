package com.example.api.config

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.LongSerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.retrytopic.DestinationTopic
import java.util.*
import kotlin.collections.HashMap

@Configuration
class KafkaProducerConfig {

    @Bean
    fun producerFactory(): ProducerFactory<String, Long> {
        val configs = HashMap<String, Any>()

        configs[BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        configs[KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configs[VALUE_SERIALIZER_CLASS_CONFIG] = LongSerializer::class.java

        return DefaultKafkaProducerFactory(configs)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Long> {
        return KafkaTemplate(producerFactory())
    }
}
