package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 获取聊天页面基础信息
     * @param principal
     * @return
     */
    @GetMapping("/chatInfo")
    public ModelMap getChatInfo(Principal principal){
        return userService.userChatInfo(principal.getName());
    }

    /**
     * 获取群组内成员
     * @param groupId
     * @return
     */
    @GetMapping("/groupMembers/{groupId}")
    public ModelMap getGroupMembers(@PathVariable("groupId")Long groupId){
        return userService.getGroupMembers(groupId);
    }
}
