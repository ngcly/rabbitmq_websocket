package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Group;
import com.example.demo.dto.GroupDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    /**
     * 获取当前用户的群组
     */
    @Query(value = "select t1.id,t1.groupName as groupname,t1.avatar from userGroup t1,Group t2 " +
            "where t1.userId=:userId and t1.groupId=t2.id ")
    Collection<GroupDTO> getUserGroup(@Param("userId")Long userId);

    /**
     * 获取当前群内成员
     * @param groupId
     */
    @Query("select t2.id,t2.username,t2.avatar,t2.sign,t2.lineState from UserGroup t1,User t2 where t1.groupId=:groupId and t1.userId=t2.id")
    Collection<UserDTO> getGroupUsers(@Param("groupId")Long groupId);
}
