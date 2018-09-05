package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/groupMembers")
    public ModelMap getGroupMembers(@RequestParam("id")Long groupId){
        return userService.getGroupMembers(groupId);
    }
}
