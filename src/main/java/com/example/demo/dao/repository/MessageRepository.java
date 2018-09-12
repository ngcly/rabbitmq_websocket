package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    /**
     * 获取朋友聊天记录
     */
    @Query("from Message where (sender=:sender or sender=:accepter) and (accepter=:sender or accepter=:accepter) and msgType=:msgType and state=:state")
    List<Message> getFriendChatList(@Param("sender")String sender,@Param("accepter")String accepter,@Param("msgType")String msgType,@Param("state")String state);

    /**
     * 获取群组聊天记录
     */
    @Query("from Message where msgType=:msgType and acceptType='2' and receiver=:groupId and state=:state")
    List<Message> getGroupChatList(@Param("msgType")String msgType,@Param("groupId")String groupId,@Param("state")String state);
}
