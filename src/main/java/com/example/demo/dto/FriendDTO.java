package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FriendDTO {
    private String groupname;
    private String id;
    private List<UserDTO> list;
}
