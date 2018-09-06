package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class UserDTO implements Serializable {
    private String id;       //由于layIm 的固定json格式 此处用唯一的用户名代替 对应数据库的用户名
    private String username; //此处用昵称代替 对应数据库的昵称名字
    private String avatar;   //头像
    private String sign;     //签名
    private String status;   //状态

    public UserDTO() {
    }

    public UserDTO(String id, String username, String avatar, String sign, String status) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.sign = sign;
        this.status = status;
    }
}
