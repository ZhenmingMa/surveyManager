package com.cby.service;

import com.cby.entity.Address;
import com.cby.entity.User;
import com.cby.repository.AddressRepository;
import com.cby.repository.UserRepository;
import com.cby.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Ma on 2017/5/31.
 */
@Service
public class UserService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager manager;

    /**
     * 存放“用户名：token”键值对
     */
    public static Map<String, String> tokenMap = new HashMap<String, String>();

    /**
     * 存放“token:User”键值对
     */
    public static Map<String, User> loginUserMap = new HashMap<String, User>();

    /**
     * 注册方法
     * @param user
     */
    public void regisit(User user) {
        userRepository.save(user);
    }

    /**
     * 登录带token返回
     * @return
     */
    public User login(User user) {

        String token = tokenMap.get(user.getUserName());

        if (token == null) {

            System.out.println("新用户登录");

        } else {

            user = loginUserMap.get(token);
            loginUserMap.remove(token);
            System.out.println("更新用户登录token");

        }

        token = UUIDUtils.id(10);
        user.setToken(token);
        loginUserMap.put(token, user);
        tokenMap.put(user.getUserName(), token);
        System.out.println("目前有" + tokenMap.size() + "个用户");

        return user;

    }

    /**
     * 添加地址
     * @param token
     * @param address
     * @return
     */
    public User addAddress(String token,Address address){
        User user = loginUserMap.get(token);
        List<Address> list = user.getAddress();
        if (list.size() == 0)
            address.setCurrent(true);
        else
            address.setCurrent(false);
        list.add(addressRepository.save(address));
        user.setAddress(list);
        return userRepository.save(user);
    }

    /**
     * 更新地址
     * @param token
     * @param address
     * @return
     */
    public User updateAddress(String token,Address address){
        User user = loginUserMap.get(token);
        addressRepository.save(address);
        return userRepository.findByUId(user.getuId());
    }
}
