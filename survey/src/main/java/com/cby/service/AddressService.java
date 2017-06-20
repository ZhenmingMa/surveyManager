package com.cby.service;

import com.cby.entity.Address;
import com.cby.entity.Result;
import com.cby.entity.User;
import com.cby.enums.ResultEnum;
import com.cby.repository.AddressRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Ma on 2017/6/19.
 */
@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    /**
     * 添加地址
     * @param token
     * @param address
     * @return
     */

    public Result addAddress(String token, Address address){
        User user = UserService.loginUserMap.get(token);
        address.setUserId(user.getId());
        List<Address> list = addressRepository.findByUserId(user.getId());
        if (list.size() == 0){
            address.setCurrent(true);
        }else {
            address.setCurrent(false);
        }
        return ResultUtils.success(addressRepository.save(address));
    }

    /**
     * 更新地址
     * @param token
     * @param address
     * @return
     */
    public Result updateAddress(String token,Address address){
        if (address.getId() == null)
            return ResultUtils.error(ResultEnum.ADDRESS_NO_ID);
        User user = UserService.loginUserMap.get(token);
        address.setUserId(user.getId());
        return ResultUtils.success(addressRepository.save(address));
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    public Result deleteAddress(Integer id){
        if (id==null)
            return ResultUtils.error(ResultEnum.ADDRESS_NO_ID);
        addressRepository.delete(id);
        return ResultUtils.success();
    }

    /**
     * 获取所有地址
     * @param token
     * @return
     */
    public Result getAllAddress(String token){
        User user = UserService.loginUserMap.get(token);
        List<Address> list = addressRepository.findByUserId(user.getId());
         return ResultUtils.success(list);
    }

    /**
     * 设置默认收货地址
     * @param token
     * @param id
     * @return
     */
    public Result setDefaultAddress(String token, Integer id) {
        User user = UserService.loginUserMap.get(token);
        List<Address> list = addressRepository.findByUserId(user.getId());
        for (Address address : list){
            if (address.getId() == id)
                address.setCurrent(true);
            else
                address.setCurrent(false);
            addressRepository.save(address);
        }
        return ResultUtils.success();
    }
}
