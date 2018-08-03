package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.Session;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;


@Configuration
// 此注解表示使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
@EnableWebSocketMessageBroker
//此处没有 implements WebSocketMessageBrokerConfigurer 而是继承下面这个类 目的是将websocket session交给spring session统一管理
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端给服务端发消息的地址的前缀
        registry.setApplicationDestinationPrefixes("/app");
        /**
         * 配置消息代理
         * 使用RabbitMQ做为消息代理，替换默认的Simple Broker
         */
        registry
                // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
                .enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue");
        /**
         * RabbitMQ 四种目的地用法
         * 【1】 /queue/queuename
         * 使用默认交换机订阅/发布消息，默认由stomp自动创建一个持久化队列，参数说明
         * a. /queue：固定值
         * b. queuename：自动创建一个持久化队列
         * 对于接收者端，订阅队列queuename的消息
         * 对于发送者端，向queuename发送消息
         * [对于 SEND frame，destination 只会在第一次发送消息的时候会定义的共享 queue]
         *
         * 【2】 /amq/queue/queuename
         * 和上文的”/queue/queuename”相似，区别在于队列不由stomp自动进行创建，队列不存在失败
         * 这种情况下无论是发送者还是接收者都不会产生队列。 但如果该队列不存在，接收者会报错。
         *
         * 以上两种为队列用法 若打开两个接收页面 则发送的消息会被两个页面轮流接收
         *
         * 【3】 /exchange/exchangename/[routing_key]
         * 通过交换机订阅/发布消息，交换机需要手动创建，参数说明
         * a. /exchange：固定值
         * b. exchangename：交换机名称
         * c. [routing_key]：路由键，可选
         * 对于接收者端，该 destination 会创建一个唯一的、自动删除的随机queue， 并根据 routing_key将该 queue 绑定到所给的 exchangename，实现对该队列的消息订阅。
         * 对于发送者端，消息就会被发送到定义的 exchangename中，并且指定了 routing_key。
         *
         * 【4】 /topic/routing_key
         * 通过amq.topic交换机订阅/发布消息，订阅时默认创建一个临时队列，通过routing_key与topic进行绑定
         * a. /topic：固定前缀
         * b. routing_key：路由键
         * 对于接收者端，会创建出自动删除的、非持久的队列并根据 routing_key路由键绑定到 amq.topic 交换机 上，同时实现对该队列的订阅。
         * 对于发送者端，消息会被发送到 amq.topic 交换机中。
         */
//                .setRelayHost("localhost")
//                .setClientLogin("guest")
//                .setClientPasscode("guest")
//                .setSystemLogin("guest")
//                .setSystemPasscode("guest")
//                .setSystemHeartbeatSendInterval(5000)
//                .setSystemHeartbeatReceiveInterval(4000);
    }


    @Override
    public void configureStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 注册 Stomp的端点
         *
         * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
         * withSockJS：指定端点使用SockJS协议
         */
        registry.addEndpoint("/websocket-rabbitmq").withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(512 * 1024)
        //限制消息的大小 避免超大信息
       .setMessageSizeLimit(128 * 1024);
    }

}