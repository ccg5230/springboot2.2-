package com.ccg.kafka.client;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@className Kafka_Producer
 *@Description
 *@Author chungaochen
 *Date 2020/4/30 22:55
 *Version 1.0
 **/
@Component
public class Kafka_Producer  {

    public String topic = Kafka_Config.topic;

    @Autowired
    Producer producer;

    public void producer() throws Exception {

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello, Kafka!");
        try {
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println(metadata.partition() + ":" + metadata.offset());
                    }
                }
            });
        } catch (Exception e) {
        }
    }
}
