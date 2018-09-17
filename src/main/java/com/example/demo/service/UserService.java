package com.example.demo.service;

import com.example.demo.dao.entity.ChatMsg;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.ChatMsgRepository;
import com.example.demo.dao.repository.FriendRepository;
import com.example.demo.dao.repository.GroupRepository;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.dto.*;
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
    @Autowired
    ChatMsgRepository chatMsgRepository;

    public Optional<User> findUserByName(String username){
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    /**
     * 获取用户、好友、群组信息
     * @return
     */
    public ModelMap userChatInfo(String username){
        User user = userRepository.findByUsername(username);
        UserDTO userInfo = UserDTO.builder()
                .id(user.getUsername())
                .username(user.getNickName())
                .avatar(user.getAvatar())
                .sign(user.getSignature())
                .status(user.getLineState())
                .build();
        List<Object[]> friends = friendRepository.getMyFriends(username);
        List<GroupDTO> groups = groupRepository.getUserGroup(username);
        Map<String, List<Object[]>> collect = friends.stream().collect(Collectors.groupingBy(c -> c[0].toString()));
        List<UserDTO> list;
        List<FriendDTO> friendList = new ArrayList<>();
        for (Map.Entry<String, List<Object[]>> entry : collect.entrySet()) {
            list = new ArrayList<>();
            for(Object[] obj:entry.getValue()){
                UserDTO friend = UserDTO.builder()
                        .username(obj[2].toString())
                        .id(obj[3].toString())
                        .avatar(String.valueOf(obj[4]))
                        .sign(String.valueOf(obj[5]))
                        .status(String.valueOf(obj[6]))
                        .build();
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

    /**
     * 更新用户在线状态
     */
    public ModelMap updateStatus(String username,String state){
        User user = userRepository.findByUsername(username);
        user.setLineState(state);
        userRepository.save(user);
        return RestUtil.Success();
    }

    /**
     * 更新签名
     */
    public ModelMap updateSign(String username,String sign){
        User user = userRepository.findByUsername(username);
        user.setSignature(sign);
        userRepository.save(user);
        return RestUtil.Success();
    }

    /**
     * 获取聊天记录信息
     */
    public ModelMap getChatMsg(String sender,String receiver,String msgType){
        List<ChatDTO> chatMsgList = new ArrayList<>();
        List<Object[]> objList;
        if("friend".equals(msgType)){
            objList = chatMsgRepository.getFriendChatList(sender,receiver);
        }else{
            objList = chatMsgRepository.getGroupChatList(receiver);
        }
        ChatDTO chatDTO;
        for(Object[] obj:objList){
            chatDTO = ChatDTO.builder()
                    .id(obj[0].toString())
                    .username(obj[1].toString())
                    .avatar(String.valueOf(obj[2]))
                    .timestamp(((Date)obj[3]).getTime())
                    .content(String.valueOf(obj[4]))
                    .build();
            chatMsgList.add(chatDTO);
        }
        return RestUtil.Success(chatMsgList);
    }

    /**
     * 推荐好友
     */
    public ModelMap findFriend(String username){
        List<UserDTO> userDTOS = friendRepository.findFriendList(username);
        return RestUtil.Success(userDTOS);
    }

    /**
     * 查找朋友
     */
    public ModelMap searchFriend(String username,String type,String name){
        if("friend".equals(type)){
            List<UserDTO> userDTOS = friendRepository.searchFriends(username,"%"+name+"%");
            return RestUtil.Success(userDTOS);
        }else{
            List<GroupInfoDTO> groupDTOS = groupRepository.searchGroup(username,"%"+name+"%");
            return RestUtil.Success(groupDTOS);
        }
    }
}
