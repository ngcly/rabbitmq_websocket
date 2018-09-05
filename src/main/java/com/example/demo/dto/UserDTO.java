package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {
    private String id;
    private String username;
    private String avatar;
    private String sign;
    private String status;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String avatar, String sign, String status) {
        this.id = id.toString();
        this.username = username;
        this.avatar = avatar;
        this.sign = sign;
        this.status = status;
    }
}
