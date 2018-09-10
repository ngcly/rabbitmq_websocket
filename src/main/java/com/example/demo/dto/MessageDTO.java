package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class MessageDTO {
    private MessageType emit;
    private Map<String,String> data;


    public enum MessageType {
        chatMessage,
        addList,
    }
}
