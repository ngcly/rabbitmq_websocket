package com.example.demo.dao.entity;

import javax.persistence.*;

/**
 * 聊天信息表
 */
@Entity
@Table(name = "chat_msg")
public class ChatMsg extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;       //信息内容
    private String sender;        //发送人
    private String receiver;      //接收者
    @Column(columnDefinition="enum('friend','group')")
    private String msgType;       // 朋友 群

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
