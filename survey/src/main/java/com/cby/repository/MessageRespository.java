package com.cby.repository;

import com.cby.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ma on 2017/8/2.
 */
public interface MessageRespository extends JpaRepository<Message,Integer> {
    List<Message> getMessagesByUserIdOrderByDate(String userId);
}
