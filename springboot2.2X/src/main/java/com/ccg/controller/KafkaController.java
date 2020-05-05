package com.ccg.controller;

import com.ccg.kafka.KafkaProducer;
import com.ccg.kafka.bean.Messages;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 *@className KafkaController
 *@Description
 *@Author chungaochen
 *Date 2020/4/30 18:32
 *Version 1.0
 **/
@RestController
@RequestMapping("kafka/")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @ApiOperation(value = "发送消息")
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public ListenableFuture<SendResult<String, String>> send(@RequestBody Messages msg) {
        return kafkaProducer.send(msg);
    }

//    @ApiOperation(value = "发送消息")
//    @RequestMapping("/getMsg")
//    public ListenableFuture<SendResult<String, String>> send(@RequestBody Messages msg) {
//        return kafkaProducer.send(msg);
//    }


}