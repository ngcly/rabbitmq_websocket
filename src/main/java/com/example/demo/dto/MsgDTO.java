package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MsgDTO implements java.io.Serializable {
    private String sender;
    private String receiver;
    private String content;
    private String type;

}
