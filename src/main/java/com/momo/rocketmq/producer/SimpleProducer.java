package com.momo.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

@Component
public class SimpleProducer {
    private String NAME_SERVER="10.187.23.102:9876";
    private String GROUP_NAME="SIMPLE-PRODUCER";
    private DefaultMQProducer mqProducer;
    public SimpleProducer() throws MQClientException {
        mqProducer=new DefaultMQProducer(GROUP_NAME);
        mqProducer.setNamesrvAddr(NAME_SERVER);
        mqProducer.start();
    }
    public DefaultMQProducer getMqProducer() {
        return mqProducer;
    }
}
