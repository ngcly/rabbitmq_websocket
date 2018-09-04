package com.example.demo.dao.repository;

import com.example.demo.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<User, Long> {

}
