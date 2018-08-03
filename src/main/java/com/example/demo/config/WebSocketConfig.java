package com.example.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.socket.config.annotation.*;
import java.util.Map;


@Configuration
// 此注解表示使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${app.jwtSecret}")
    private String tokenSecretKey;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    //注册STOMP协议节点并映射url
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加连接节点
        registry.addEndpoint("/websocket-rabbitmq")
                .setAllowedOrigins("*") //允许跨域
                .withSockJS();  //指定使用SockJS协议
    }

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
        /** RabbitMQ 四种目的地用法
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
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String jwtToken = accessor.getFirstNativeHeader("Auth-Token");
                    if (StringUtils.isNotEmpty(jwtToken)) {
                        Map sessionAttributes = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
                        sessionAttributes.put(CsrfToken.class.getName(), new DefaultCsrfToken("Auth-Token", "Auth-Token", jwtToken));
                        UserDetails userDetails = customUserDetailsService.loadUserById(jwtTokenProvider.getUserIdFromJWT(jwtToken));
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        accessor.setUser(authentication);
                    }
                }
                return message;
            }
        });
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(512 * 1024)
        //限制消息的大小 避免超大信息
       .setMessageSizeLimit(128 * 1024);
    }

}