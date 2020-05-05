package com.ccg.kafka;

import com.ccg.kafka.bean.Messages;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;

/**
 *@className KafkaProducer
 *@Description
 *@Author chungaochen
 *Date 2020/4/30 18:33
 *Version 1.0
 **/
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.topic-name}")
    private String topic;

    private Gson gson = new GsonBuilder().create();

    public ListenableFuture<SendResult<String, String>> send(Messages message) {
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, gson.toJson(message));
        return  result;
    }

}