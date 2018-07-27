package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ 配置
 */
@Configuration
public class RabbitConfig {
    public static final String DEFAULT_QUEUE = "default";
    public static final String MANUAL_QUEUE = "manual";

    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);


    @Bean
    public Queue defaultBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_QUEUE, true);
    }

    @Bean
    public Queue manualBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(MANUAL_QUEUE, true);
    }

    /**
     * 延迟队列 流程：生产者发送信息（带有过期时间和转发路由）->死信交换机->死信路由—>死信队列（无消费者）->转发交换机->转发路由->消费队列
     * 订单超时取消 将订单号丢入延迟队列中，当延迟订单队列消费时 根据订单号比对 业务订单是否已支付 若未支付 则取消订单 否则直接完成。
     */

    /**
     * 配置摸板（非必要 测试延迟队列方便直观）
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    /**
     * DLX，dead letter发送到的 exchange
     * 此处的 exchange 很重要,具体消息就是发送到该交换机的
     */
    public static final String DLX_EXCHANGE = "delay";

    /**
     * routing key 名称
     * 此处的 routingKey 很重要要,具体消息发送在该 routingKey
     */
    public static final String DLX_ROUTING_KEY = "";

    /**
     * 死信队列名称
     */
    public static final String DLX_QUEUE = "delay.queue";

    /**
     * 转发交换机名称
     */
    public static final String DELAY_EXCHANGE = "dev";

    /**
     * 转发 路由名称
     */
    public static final String DELAY_ROUTING_KEY = "all";

    /**
     * 消费队列名称
     */
    public static final String DELAY_QUEUE = "dev.queue";

    /**
     * 死信队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5 * 1000);
     * 第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     * 第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     **/
    @Bean
    public Queue dlxQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", DELAY_EXCHANGE);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", DELAY_ROUTING_KEY);
        return new Queue(DLX_QUEUE, true, false, false, params);
    }

    /**
     * DLX 交换机
     */
    @Bean
    public DirectExchange dlxDirectExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    /**
     * 需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
     * 这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “dog”，则只有被标记为“dog”的消息才被转发，不会转发dog.puppy，也不会转发dog.guard，只会转发dog
     * 它不像 TopicExchange 那样可以使用通配符适配多个
     */
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxDirectExchange()).with(DLX_ROUTING_KEY);
    }

    /**
     * 消费队列
     * @return
     */
    @Bean
    public Queue consumeQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    /**
     * Topic模式交换机
     */
    @Bean
    public TopicExchange delayTopicExchange() {
        return new TopicExchange(DELAY_EXCHANGE);
    }

    /**
     * 将消费队列与交换机进行路由绑定
     * 将路由键和某模式进行匹配。此时队列需要绑定在一个模式上。
     * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(consumeQueue()).to(delayTopicExchange()).with(DELAY_ROUTING_KEY);
    }

}
