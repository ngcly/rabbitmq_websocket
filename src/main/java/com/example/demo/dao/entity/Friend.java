package com.example.demo.dao.entity;

import javax.persistence.*;

/**
 * 好友表
 */
@Entity
@Table(name = "friend")
public class Friend extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; //好友唯一用户名

    private Long subgroupId; //好友分组ID

    private String nickName; //好友昵称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSubgroupId() {
        return subgroupId;
    }

    public void setSubgroupId(Long subgroupId) {
        this.subgroupId = subgroupId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
