package com.example.demo.dao.entity;

import javax.persistence.*;

/**
 * 群表
 */
@Entity
@Table(name = "group_crowd")
public class Group extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;   //群名称

    private String avatar;      //群头像

    private String groupDsc;    //群描述

    private Integer number;     //群人数

    private Boolean approval;  //进群是否需要验证

    @Column(columnDefinition="enum('1','2')")
    private Byte status;       //群状态 1-正常 2-全体禁言

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGroupDsc() {
        return groupDsc;
    }

    public void setGroupDsc(String groupDsc) {
        this.groupDsc = groupDsc;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getApproval() {
        return approval;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
