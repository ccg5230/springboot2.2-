package com.ccg.kafka.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 *@className Kafka_Consumer
 *@Description
 *@Author chungaochen
 *Date 2020/4/30 22:56
 *Version 1.0
 **/
@Component
public class Kafka_Consumer implements InitializingBean {
    public String topic = Kafka_Config.topic;
    @Autowired
    Kafka_Config kafka_config;

    @Override
    public void afterPropertiesSet() throws Exception {
        //每个线程一个KafkaConsumer实例，且线程数设置成分区数，最大化提高消费能力
        int consumerThreadNum = 4;//线程数设置成分区数，最大化提高消费能力
        for (int i = 0; i < consumerThreadNum; i++) {
            new KafkaConsumerThread(kafka_config.customerConfigs(), topic).start();
        }
    }

    public class KafkaConsumerThread extends Thread {
        private KafkaConsumer<String, String> kafkaConsumer;

        public KafkaConsumerThread(Properties props, String topic) {
            this.kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
            this.kafkaConsumer.subscribe(Arrays.asList(topic));//订阅主题
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records =
                            kafkaConsumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("message------------ "+record.value());
                    }
                }
            } catch (Exception e) {
            } finally {
                kafkaConsumer.close();
            }
        }
    }
}

