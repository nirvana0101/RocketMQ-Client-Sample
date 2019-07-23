package com.momo.rocketmq.consumer;

import com.momo.rocketmq.config.JmsConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SimpleConsumer {
    private final String SIMPLE_CONSUMER_GROUP="SIMPLE_CONSUMER_GROUP";
    private DefaultMQPushConsumer mqConsumer;
    public SimpleConsumer() throws MQClientException {
        mqConsumer=new DefaultMQPushConsumer(SIMPLE_CONSUMER_GROUP);
        mqConsumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        mqConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        mqConsumer.subscribe(JmsConfig.SIMPLE_TOPIC,"*");
        mqConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                   ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                try{
                    Message msg=list.get(0);
                    System.out.println(msg.toString());
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }catch (Exception e){
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        });
        mqConsumer.start();
    }
}
