package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String username;
    private String avatar;
    private String sign;
    private String status;
}
