package com.example.demo.dao.repository;

import com.example.demo.dao.entity.NoticeMsg;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface NoticeMsgRepository extends JpaRepository<NoticeMsg,Long> {

    Long countNoticeMsgByReceiverAndState(String receiver,Byte state);

//    Page<NoticeMsg> findAllByReceiver(Pageable pageable,String receiver);
}
