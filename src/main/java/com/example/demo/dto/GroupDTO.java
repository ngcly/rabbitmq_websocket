package com.example.demo.dto;

import java.io.Serializable;

public class GroupDTO implements Serializable {
    private String id;
    private String groupname;
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public GroupDTO() {
    }

    public GroupDTO(Long id, String groupname, String avatar) {
        this.id = id.toString();
        this.groupname = groupname;
        this.avatar = avatar;
    }
}
