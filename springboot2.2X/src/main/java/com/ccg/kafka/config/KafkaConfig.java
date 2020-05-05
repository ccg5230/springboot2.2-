package com.ccg.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@className KafkaConfig
 *@Description
 *@Author chungaochen
 *Date 2020/4/30 18:23
 *Version 1.0
 **/
@Configuration
public class KafkaConfig {

    //通过KafkaAdminClient 可以在线管理kafka，创建Topic，删除Topic，查询信息等等
    @Bean
    public NewTopic createTopic(){
        //创建topic
        return new NewTopic("springboot_topic",4, (short) 3);
    }

}