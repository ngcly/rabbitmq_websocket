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
    @Query(value = "select t2.id,t2.groupName,t3.nickName,t1.username,t1.avatar,t1.signature,t1.lineState " +
            "from User t1,Subgroup t2,Friend t3 where t2.username=:username and t2.id = t3.subgroupId " +
            "and t3.username = t1.username order by t2.orderNum")
    List<Object[]> getMyFriends(@Param("username")String username);
}
