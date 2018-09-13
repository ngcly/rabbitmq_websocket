package com.example.demo.dao.entity;

import javax.persistence.*;

/**
 * 通知信息表
 */

@Entity
@Table(name = "notice_msg")
public class NoticeMsg extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String msgContent;    //信息内容
    @Column(columnDefinition="enum('1','2','3','4','5')")
    private String msgType;       //信息类型  1-请求添加用户 2-系统消息（添加好友）3-请求加群 4-系统消息（添加群）5-全体会员消息
    private String sender;        //发送人
    private String receiver;      //接收人
    private String accepter;      //接收消息的管理员
    private String remark;        //附加信息
    @Column(columnDefinition="enum('1','2','3','4','5','6')")
    private Byte state;           //状态 1-未读 2-同意 3-拒绝 4-同意且返回消息已读 5-拒绝且返回消息已读 6-全体消息已读
    private Long subgroupId;      //好友分组ID
    private Long handle;          //处理该消息的管理员

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Long getSubgroupId() {
        return subgroupId;
    }

    public void setSubgroupId(Long subgroupId) {
        this.subgroupId = subgroupId;
    }

    public Long getHandle() {
        return handle;
    }

    public void setHandle(Long handle) {
        this.handle = handle;
    }
}
