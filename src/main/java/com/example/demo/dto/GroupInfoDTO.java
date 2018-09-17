package com.example.demo.dto;

import java.io.Serializable;

public class GroupInfoDTO implements Serializable {
    private String id;
    private String groupName;
    private String avatar;
    private String des;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public GroupInfoDTO() {
    }

    public GroupInfoDTO(Long id, String groupName, String avatar, String des) {
        this.id = id.toString();
        this.groupName = groupName;
        this.avatar = avatar;
        this.des = des;
    }
}
