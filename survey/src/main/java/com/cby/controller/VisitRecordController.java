package com.cby.controller;

import com.cby.entity.Result;
import com.cby.repository.VisitRecordRepo;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ma on 2017/6/1.
 */
@RestController
public class VisitRecordController {
    @Autowired
    private VisitRecordRepo visitRecordRepo;


    @GetMapping(value = "/getAllVisitRecord")
    public Result getAllVisitRecord(){
       return ResultUtils.success(visitRecordRepo.findAll());
    }
    @GetMapping(value = "/getTodayVisitRecord")
    public Result getTodayVisitRecord(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        System.out.println(time);
        return ResultUtils.success(visitRecordRepo.findByTimeLike(time+"%"));
    }


}
