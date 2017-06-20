package com.cby.repository;

import com.cby.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ma on 2017/5/31.
 */
public interface UserRepository extends JpaRepository<User,String> {

    //通过用户名来查询
    public List<User> findByPhone(Long phone);
    //通过用户id来查询
    public User findById(String id);
    //检查用户名和密码
    public List<User> findByPhoneAndPassword(Long phone,String password);

}
