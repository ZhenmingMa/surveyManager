package com.cby.service;

import com.cby.entity.User;
import com.cby.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ma on 2017/5/31.
 */
@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager manager;

    /**
     * 注册方法
     * @param user
     */
    public void regisit(User user){
        userRepository.save(user);
    }

    /**
     * 登录
     */
    public String login(String username,String password){

        return null;
    }



}
