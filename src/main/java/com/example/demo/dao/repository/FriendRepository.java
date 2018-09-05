package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    /**
     * 获取好友列表
     */
    @Query(value = "select t1.shipId,t1.shipName,t2.id,t2.username,t2.avatar,t2.sign,t2.lineState " +
            "from Friend t1,User t2 where t1.userId=:userId and t1.friendId=t2.id ")
    List<Object[]> getFriends(@Param("userId")Long userId);

}
