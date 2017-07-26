package com.cby.service;

import com.cby.entity.Application;
import com.cby.entity.Result;
import com.cby.repository.ApplicationRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ma on 2017/7/26.
 */
@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Result apply(String token,Application application){

        return ResultUtils.success(applicationRepository.save(application));
    }
}
