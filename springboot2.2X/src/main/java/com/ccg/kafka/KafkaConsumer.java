package com.ccg.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Optional;

/**
 *@className KafkaConsumer
 *@Description
 *@Author chungaochen
 *Date 2020/4/30 18:34
 *Version 1.0
 **/
@Service
public class KafkaConsumer {

    @Value("${kafka.topic.topic-name}")
    private String topic;

//    @KafkaListener(topics = "springboot_topic")
//    public void listen1(ConsumerRecord<?,String> record) {
//        String value = record.value();
//        System.out.println("kafka监听的值是："+value);
//        System.out.println(record);
//    }

    @KafkaListener
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("record =" + record);
            System.out.println("message =" + message);
 
        }
    }

    //@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次
    @PostConstruct
    public void initKafkaHandler() throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String topics =topic;
        String[] topicArray = topics.split(",");
        //反射，listen是方法名，ConsumerRecord.class是参数的类，找到这个监听方法修改topics的值
        Method listen = KafkaConsumer.class.getDeclaredMethod("listen", ConsumerRecord.class);
        KafkaListener kafkaListener = listen.getAnnotation(KafkaListener.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(kafkaListener);
        Field hField = invocationHandler.getClass().getDeclaredField("memberValues");
        //默认的访问权限是不行的，要修改成true才能修改属性的值
        hField.setAccessible(true);
        Map memberValues = (Map) hField.get(invocationHandler);
        memberValues.put("topics", topicArray);
    }

    @KafkaListener(topics = "test2", groupId = "group2")
    public void listen2(ConsumerRecord<?,String> record) {
        String value = record.value();
        System.out.println("kafka监听的值是2："+value);
        System.out.println(record);
    }

}