package com.cby.repository;

import com.cby.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ma on 2017/8/4.
 */
public interface ContactRepository extends JpaRepository<Contact,Integer> {

}
