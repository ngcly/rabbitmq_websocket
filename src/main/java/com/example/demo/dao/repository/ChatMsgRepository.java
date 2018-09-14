package com.example.demo.dao.repository;

import com.example.demo.dao.entity.ChatMsg;
import com.example.demo.dto.ChatDTO;
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
    @Query(value = "SELECT * FROM (SELECT t2.username id,t2.nick_name,t2.avatar,t1.created_at,t1.content FROM " +
            "chat_msg t1,USER t2 WHERE t1.msg_type='friend' AND t1.sender=:sender AND t1.receiver=:receiver AND " +
            "t1.sender=t2.username UNION ALL SELECT t2.username id,t4.nick_name,t2.avatar,t1.created_at,t1.content " +
            "FROM chat_msg t1,USER t2,subgroup t3,friend t4 WHERE t1.sender=:receiver AND t1.msg_type='friend' AND " +
            "t1.receiver=:sender AND t1.sender=t2.username AND t1.receiver=t3.username AND t3.id=t4.subgroup_id AND " +
            "t4.username=t1.sender) r ORDER BY r.created_at",nativeQuery = true)
    List<Object[]> getFriendChatList(@Param("sender")String sender,@Param("receiver")String receiver);

    /**
     * 获取群组聊天记录
     */
    @Query("SELECT t2.username,t3.nickName,t2.avatar,t1.createdAt,t1.content FROM ChatMsg t1,User t2,UserGroup t3 " +
            "WHERE t1.msgType='group' AND t1.sender=t2.username AND t1.receiver=t3.groupId and t1.receiver=:groupId " +
            "AND t1.sender=t3.username ORDER BY t1.createdAt")
    List<Object[]> getGroupChatList(@Param("groupId")String groupId);
}
