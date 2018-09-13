package com.example.demo.dao.repository;

import com.example.demo.dao.entity.ChatMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMsgRepository extends JpaRepository<ChatMsg,Long> {

    /**
     * 获取朋友聊天记录
     */
    @Query("from ChatMsg where (sender=:sender or sender=:receiver) and (receiver=:sender or receiver=:receiver) and msgType='friend'")
    List<ChatMsg> getFriendChatList(@Param("sender")String sender,@Param("receiver")String receiver);

    /**
     * 获取群组聊天记录
     */
    @Query("from ChatMsg where msgType='group' and receiver=:groupId")
    List<ChatMsg> getGroupChatList(@Param("groupId")String groupId);
}
