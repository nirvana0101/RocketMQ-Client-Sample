package com.momo.rocketmq.producer;

import com.momo.rocketmq.config.JmsConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

@Component
public class SimpleProducer {
    private String GROUP_NAME="SIMPLE-PRODUCER";
    private DefaultMQProducer mqProducer;
    public SimpleProducer() throws MQClientException {
        mqProducer=new DefaultMQProducer(GROUP_NAME);
        mqProducer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        mqProducer.start();
    }
    public DefaultMQProducer getMqProducer() {
        return mqProducer;
    }
}
