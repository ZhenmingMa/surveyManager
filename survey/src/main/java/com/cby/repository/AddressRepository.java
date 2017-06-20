package com.cby.repository;

import com.cby.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ma on 2017/6/6.
 */
public interface AddressRepository extends JpaRepository<Address,Integer> {
    public List<Address> findByUserId(String userId);
}
