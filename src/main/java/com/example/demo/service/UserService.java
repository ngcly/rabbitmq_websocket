package com.example.demo.service;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.FriendRepository;
import com.example.demo.dao.repository.GroupRepository;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.dto.FriendDTO;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    GroupRepository groupRepository;

    public Optional<User> findUserByName(String username){
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    /**
     * 获取用户、好友、群组信息
     * @return
     */
    public ModelMap userChatInfo(String username){
        User user = userRepository.findByUsername(username);
        UserDTO userInfo = UserDTO.builder().id(user.getId().toString()).username(user.getUsername()).avatar(user.getAvatar()).sign(user.getSign()).status(user.getLineState()).build();
        List<Object[]> friends = friendRepository.getFriends(user.getId());
        Collection<GroupDTO> groups = groupRepository.getUserGroup(user.getId());
        Map<String, List<Object[]>> collect = friends.stream().collect(Collectors.groupingBy(c -> c[0].toString()));
        List<UserDTO> list;
        List<FriendDTO> friendList = new ArrayList<>();
        for (Map.Entry<String, List<Object[]>> entry : collect.entrySet()) {
            list = new ArrayList<>();
            for(Object[] obj:entry.getValue()){
                UserDTO friend = UserDTO.builder().id(obj[2].toString()).username(obj[3].toString()).avatar(obj[4].toString()).sign(obj[5].toString()).status(obj[6].toString()).build();
                list.add(friend);
            }
            FriendDTO friendDTO = FriendDTO.builder().id(entry.getKey()).groupname(entry.getValue().get(0)[1].toString()).list(list).build();
            friendList.add(friendDTO);
        }
        Map<String,Object> info = new HashMap<>();
        info.put("mine",userInfo);
        info.put("friend",friendList);
        info.put("group",groups);
        return RestUtil.Success(info);
    }

    /**
     * 获取群内成员
     * @return
     */
    public ModelMap getGroupMembers(Long groupId){
        Collection<UserDTO> userDTOS = groupRepository.getGroupUsers(groupId);
        Map<String,Object> map = new HashMap<>();
        map.put("list",userDTOS);
        return RestUtil.Success(map);
    }
}
