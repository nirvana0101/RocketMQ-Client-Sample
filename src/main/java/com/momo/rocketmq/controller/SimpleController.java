package com.momo.rocketmq.controller;

import com.momo.rocketmq.producer.SimpleProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Autowired
    private SimpleProducer simpleProducer;
    private final String TOPIC="SIMPLE-TOPIC";
    @RequestMapping("/callback")
    private Object callback(String text) throws Exception {
        Message msg=new Message(TOPIC,"tag1",text,("Message="+text).getBytes());
        SendResult sendResult=simpleProducer.getMqProducer().send(msg);
        System.out.println(sendResult.toString());
        return sendResult.toString();
    }
}
