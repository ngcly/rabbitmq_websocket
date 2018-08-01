package com.example.demo.controller;

import com.example.demo.config.RabbitConfig;
import com.example.demo.dto.NewsDTO;
import com.example.demo.util.RestCode;
import com.example.demo.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/send")
    public ModelMap defaultMessage() {
        NewsDTO message = new NewsDTO();
        message.setId("1");
        message.setName("消息队列信息内容");
        //第一个参数是队列名称 第二个参数是 发送的具体内容
        rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_QUEUE, message);
        rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_QUEUE, message);
        return RestUtil.Success("发送时间为："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @GetMapping("/later/{time}")
    public ModelMap laterSend(@PathVariable("time")int time){
        NewsDTO news = new NewsDTO();
        news.setId("2");
        news.setName("延迟队列信息");
        // 添加延时队列
        rabbitTemplate.convertAndSend(RabbitConfig.DLX_EXCHANGE, RabbitConfig.DLX_ROUTING_KEY, news, message -> {
            //设置头部 可要可不要,根据自己需要自行处理
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, NewsDTO.class.getName());
            //如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送时自己控制时间
            message.getMessageProperties().setExpiration(time * 1000 + "");
            return message;
        });
        return RestUtil.Success("发送时间为："+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @GetMapping("/sendEmail/{message}")
    public ModelMap sendEmail(@PathVariable("message")String message){
        //用于封装邮件信息的实例
        SimpleMailMessage smm=new SimpleMailMessage();
        //邮件发送者
        smm.setFrom(from);
        //邮件主题
        smm.setSubject("Hello");
        //邮件内容
        smm.setText(message);
        //接受者
        smm.setTo("965315004@qq.com");
        try {
            jms.send(smm);
            return RestUtil.Success("发送成功","");
        } catch (Exception e) {
            return RestUtil.Error(RestCode.UNAUTHZ);
        }
    }

}
