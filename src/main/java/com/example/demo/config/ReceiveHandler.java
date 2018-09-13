package com.example.demo.config;

import com.example.demo.dto.MsgDTO;
import com.example.demo.dto.NewsDTO;
import com.example.demo.service.MessageService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * RabbitMQ 消费者
 */
@Component
public class ReceiveHandler {
    @Autowired
    MessageService messageService;

    private static final Logger log = LoggerFactory.getLogger(ReceiveHandler.class);

    /**
     * <p>该方案是 spring-boot-data-amqp 默认的方式,不太推荐。具体推荐使用  listenerManualAck()</p>
     * 默认情况下,如果没有配置手动ACK, 那么Spring Data AMQP 会在消息消费完毕后自动帮我们去ACK
     * 存在问题：如果报错了,消息不会丢失,但是会无限循环消费,一直报错,如果开启了错误日志很容易就吧磁盘空间耗完
     * 解决方案：手动ACK,或者try-catch 然后在 catch 里面讲错误的消息转移到其它的系列中去
     * spring.rabbitmq.listener.simple.acknowledge-mode=manual
     * <p>
     * @param newsDTO 监听的内容
     */
    @RabbitListener(queues = {RabbitConfig.DEFAULT_QUEUE})
    public void listenerAutoAck(NewsDTO newsDTO, Message message, Channel channel) {
        //如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("[listenerAutoAck 监听的消息] - [{}]", newsDTO.toString());
            //通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                //处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = {RabbitConfig.MANUAL_QUEUE})
    public void listenerManualAck(NewsDTO newsDTO, Message message, Channel channel) {
        log.info("[listenerManualAck 监听的消息] - [{}]", newsDTO.toString());
        try {
            //通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }

    /**
     * 延迟队列消费
     * @param newsDTO
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {RabbitConfig.DELAY_QUEUE})
    public void listenerDelayQueue(NewsDTO newsDTO, Message message, Channel channel) {
        log.info("[listenerDelayQueue 监听的消息] - [消费时间] - [{}] - [{}]", LocalDateTime.now(), newsDTO.toString());
        try {
            //通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }


    @RabbitListener(queues = {RabbitConfig.CHAT_QUEUE})
    public void listenerManualAck(MsgDTO msg, Message message, Channel channel) {
        try {
            messageService.saveChatMsg(msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
