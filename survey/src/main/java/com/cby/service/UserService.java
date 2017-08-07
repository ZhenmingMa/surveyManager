package com.cby.service;

import com.cby.annotation.UserAccess;
import com.cby.entity.*;
import com.cby.enums.ResultEnum;
import com.cby.repository.*;
import com.cby.utils.ResultUtils;
import com.cby.utils.SystemManger;
import com.cby.utils.TimeUtils;
import com.cby.utils.UUIDUtils;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ma on 2017/5/31.
 */
@Service
public class UserService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyPointRepository myPointRepository;
    @Autowired
    private MyBonusRepository myBonusRepository;
    @Autowired
    private MessageRespository messageRespository;

    @PersistenceContext
    private EntityManager manager;

    /**
     * 存放“用户名：token”键值对
     */
    public static Map<Long, String> tokenMap = new HashMap<Long, String>();

    /**
     * 存放“token:User”键值对
     */
    public static Map<String, User> loginUserMap = new HashMap<String, User>();

    /**
     * 注册方法
     *
     * @param user
     */
    public Result regisit(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultEnum.UNKONW_ERROR);
        }
        List<User> listByPhone = userRepository.findByPhone(user.getPhone());
        if (listByPhone.size() != 0) {
            return ResultUtils.error(ResultEnum.ACCOUNT_HAS_EXIST);
        }
        user.setTime(new Date());
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 登录带token返回
     *
     * @return
     */
    public Result login(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(101, bindingResult.getFieldError().getDefaultMessage());
        }
        List<User> listByPhone = userRepository.findByPhone(user.getPhone());
        if (listByPhone.size() == 0)
            return ResultUtils.error(ResultEnum.ACCOUNT_NO_EXIST);
        List<User> list = userRepository.findByPhoneAndPassword(user.getPhone(), user.getPassword());
        if (list.size() == 0) {
            return ResultUtils.error(ResultEnum.PASSWORD_ERROR);
        }
        User u = list.get(0);
        String token = tokenMap.get(u.getPhone());
        if (token == null) {
            System.out.println("新用户登录");
        } else {
            u = loginUserMap.get(token);
            loginUserMap.remove(token);
            System.out.println("更新用户登录token");
        }
        token = UUIDUtils.id(10);
        u.setToken(token);
        loginUserMap.put(token, u);
        tokenMap.put(u.getPhone(), token);
        System.out.println("目前有" + tokenMap.size() + "个用户");
        return ResultUtils.success(userRepository.save(u));
    }

    public Result loginByPhone(String phone) {
        List<User> listByPhone = userRepository.findByPhone(Long.parseLong(phone));
        if (listByPhone.size() == 0)
            return ResultUtils.error(ResultEnum.ACCOUNT_NO_EXIST);

        else {
            User u = listByPhone.get(0);
            String token = tokenMap.get(u.getPhone());
            if (token == null) {
                System.out.println("新用户登录");
            } else {
                u = loginUserMap.get(token);
                loginUserMap.remove(token);
                System.out.println("更新用户登录token");
            }
            token = UUIDUtils.id(10);
            u.setToken(token);
            loginUserMap.put(token, u);
            tokenMap.put(u.getPhone(), token);
            System.out.println("目前有" + tokenMap.size() + "个用户");
            return ResultUtils.success(userRepository.save(u));
        }
    }

    /**
     * 更新用户信息
     *
     * @param
     * @return
     */
//    public Result userUpdate(String token, User user,
//                             BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResultUtils.error(101, bindingResult.getFieldError().getDefaultMessage());
//        } else {
//            User user_map = loginUserMap.get(token);
//            User user_db = userRepository.findById(user_map.getId());
//            System.out.printf(user_db.toString());
//            if (!"null".equals(user.getBirthday())) {
//                user_db.setBirthday(user.getBirthday());
//            }
//            if (!"null".equals(user.getLocation()))
//                user_db.setLocation(user.getLocation());
//            if (!"null".equals(user.getOccupation()))
//                user_db.setOccupation(user.getOccupation());
//            if (!"null".equals(user.getIncome()))
//                user_db.setIncome(user.getIncome());
//            if (!"null".equals(user.getHobby() != null))
//                user_db.setHobby(user.getHobby());
//            return ResultUtils.success(userRepository.save(user_db));
//        }
//    }
    public Result forgetPass(String phone, String password) {
        List<User> list = userRepository.findByPhone(Long.parseLong(phone));
        if (list == null || list.size() == 0) {
            return ResultUtils.error(ResultEnum.ACCOUNT_NO_EXIST);
        } else {
            User user = list.get(0);
            user.setPassword(password);
            return ResultUtils.success(userRepository.save(user));
        }
    }

    /**
     * 获取刷新每日登录积分
     *
     * @param
     * @return
     */
    public Result updateLoginMyPoint(String token) {
        User user = loginUserMap.get(token);
        MyPoint myPoint = myPointRepository.findByUserId(user.getId());
        System.out.println(myPoint == null);
        if (myPoint == null) {
            myPoint = new MyPoint();
            myPoint.setUserId(user.getId());
            myPoint.setTime(new Date());
            myPoint.setLogin(SystemManger.LoginPoint);
            myPoint.setCount(myPoint.getLogin() + myPoint.getShare());
            myPointRepository.save(myPoint);
        }
        Date date = myPoint.getTime();
        if (date.before(TimeUtils.getStartTime())) {
            myPoint.setLogin(myPoint.getLogin() + SystemManger.LoginPoint);
            myPoint.setTime(new Date());
            myPoint.setCount(myPoint.getLogin() + myPoint.getShare());
        }
        System.out.println(myPoint.toString());
        return ResultUtils.success(myPointRepository.save(myPoint));
    }

    /**
     * 更新我的分享积分
     *
     * @param token
     * @return
     */
    public Result updateShareMyPoint(String token) {
        User user = loginUserMap.get(token);
        MyPoint myPoint = myPointRepository.findByUserId(user.getId());
        if (myPoint == null) {
            myPoint = new MyPoint();
            myPoint.setUserId(user.getId());
            myPoint.setTime(new Date());
            myPoint.setLogin(SystemManger.LoginPoint);
            myPoint.setCount(myPoint.getLogin() + myPoint.getShare());
            return ResultUtils.success(myPointRepository.save(myPoint));
        }
        myPoint.setShare(myPoint.getShare() + SystemManger.SharePoint);
        myPoint.setTime(new Date());
        myPoint.setCount(myPoint.getLogin() + myPoint.getShare());
        return ResultUtils.success(myPointRepository.save(myPoint));
    }

    /**
     * 获取我的积分
     *
     * @param token
     * @return
     */
    public Result getMyPoint(String token) {
        User user = loginUserMap.get(token);
        MyPoint myPoint = myPointRepository.findByUserId(user.getId());
        if (myPoint == null) {
            myPoint = new MyPoint();
            myPoint.setUserId(user.getId());
            myPoint.setTime(new Date());
            return ResultUtils.success(myPointRepository.save(myPoint));
        }
        return ResultUtils.success(myPoint);
    }

    /**
     * 更新问卷回答奖金
     *
     * @param token
     * @return
     */
    public Result updateAnswerMyBonus(String token, double answer) {
        User user = loginUserMap.get(token);
        MyBonus myBonus = myBonusRepository.findByUserId(user.getId());
        if (myBonus == null) {
            myBonus = new MyBonus();
            myBonus.setUserId(user.getId());
            myBonus.setTime(new Date());
            myBonus.setAnswer(answer);
            myBonus.setCount(myBonus.getAnswer() + myBonus.getInvite());
            return ResultUtils.success(myBonusRepository.save(myBonus));
        }
        myBonus.setAnswer(myBonus.getAnswer() + answer);
        myBonus.setCount(myBonus.getAnswer() + myBonus.getInvite());
        return ResultUtils.success(myBonusRepository.save(myBonus));

    }

    /**
     * 更新邀请奖金
     *
     * @param token
     * @return
     */
    public Result updateInviteMyBonus(String token) {
        User user = loginUserMap.get(token);
        MyBonus myBonus = myBonusRepository.findByUserId(user.getId());
        if (myBonus == null) {
            myBonus = new MyBonus();
            myBonus.setUserId(user.getId());
            myBonus.setTime(new Date());
            myBonus.setInvite(SystemManger.InviteBonus);
            myBonus.setCount(myBonus.getAnswer() + myBonus.getInvite());
            return ResultUtils.success(myBonusRepository.save(myBonus));
        }
        myBonus.setInvite(myBonus.getInvite() + SystemManger.InviteBonus);
        myBonus.setCount(myBonus.getAnswer() + myBonus.getInvite());
        return ResultUtils.success(myBonusRepository.save(myBonus));
    }

    /**
     * 获取我的积分
     *
     * @param token
     * @return
     */
    public Result getMybonus(String token) {
        User user = loginUserMap.get(token);
        MyBonus myBonus = myBonusRepository.findByUserId(user.getId());
        if (myBonus == null) {
            myBonus = new MyBonus();
            myBonus.setUserId(user.getId());
            myBonus.setTime(new Date());
            return ResultUtils.success(myBonusRepository.save(myBonus));
        }
        return ResultUtils.success(myBonus);
    }

    public Result getMessage(String token) {
        User user = loginUserMap.get(token);
        List<Message> list = messageRespository.getMessagesByUserIdOrderByDate(user.getId());
        return ResultUtils.success(list);
    }
    public Result updateMessage(String token) {
        User user = loginUserMap.get(token);
        List<Message> list = messageRespository.getMessagesByUserIdOrderByDate(user.getId());
        for (Message message:list){
            message.setState("1");
            messageRespository.save(message);
        }
        return ResultUtils.success();
    }

    /**
     * 添加地址
     *
     * @param token
     * @param address
     * @return
     */
//    public User addAddress(String token, Address address) {
//        User user = loginUserMap.get(token);
//        List<Address> list = user.getAddress();
//        if (list.size() == 0)
//            address.setCurrent(true);
//        else
//            address.setCurrent(false);
//        list.add(addressRepository.save(address));
//        user.setAddress(list);
//        return userRepository.save(user);
//    }

    /**
     * 更新地址
     *
     * @param token
     * @param address
     * @return
     */
//    public User updateAddress(String token, Address address) {
//        User user = loginUserMap.get(token);
//        addressRepository.save(address);
//        return userRepository.findByUId(user.getuId());
//    }

    /**
     * 设置默认地址
     *
     * @param token
     * @param id
     * @return
     */
//    public User setDefaultAddress(String token, Integer id) {
//        User user = loginUserMap.get(token);
//        List<Address> list = user.getAddress();
//        for (Address address : list) {
//            if (address.getId() == id)
//                address.setCurrent(true);
//            else
//                address.setCurrent(false);
//            addressRepository.save(address);
//        }
//        user.setAddress(list);
//        return userRepository.save(user);
//    }

    /**
     * 删除收货地址
     * @param token
     * @param id
     * @return
     */
//    public User deleteAddress(String token, Integer id) {
//        System.out.println(token+" -- "+id);
//        User user = loginUserMap.get(token);
//        System.out.println(user.toString());
//        List<Address> list = user.getAddress();
//        List<Address> list1 = new ArrayList<>();
//        for (Address address :
//                list) {
//            if (address.getId()!=id){
//                list1.add(address);
//                System.out.println(address.getId());
//            }
//        }
//        for (Address address:list1){
//            System.out.print(address.getId()+" ");
//        }
//
//        user.setAddress(list1);
//        System.out.println(user.toString());
//        User user1 = userRepository.save(user);
//
//        return user1;
//    }

//    public  List<Address> getAllAddress(String token){
//        User user = loginUserMap.get(token);
//        User user1 =  userRepository.findOne(user.getId());
//        List<Address> list = user1.getAddress();
//        return list;
//    }

    /**
     * 检查登录状态
     *
     * @param token
     * @return
     */
    public Result checkStatus(String token) {
        User user = loginUserMap.get(token);
        if (user != null)
            return ResultUtils.success(userRepository.findOne(user.getId()));
        else
            return ResultUtils.error(ResultEnum.NOPERMISSION);
    }
}
