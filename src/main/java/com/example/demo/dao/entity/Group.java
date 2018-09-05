package com.example.demo.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "group_crowd")
public class Group extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    private String avatar;

    private String groupDsc;

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
}
