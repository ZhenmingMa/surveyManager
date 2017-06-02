package com.cby.aspect;

import com.cby.entity.VisitRecord;
import com.cby.repository.VisitRecordRepo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ma on 2017/6/1.
 */

@Aspect
@Component
public class HttpAspect {
    @Autowired
    private VisitRecordRepo visitRecordRepo;
    @Pointcut("execution(public * com.cby.controller.UserController.*(..) )")
    public void visitRecord(){
        System.out.println("visitRecord");
    }
    @Before("visitRecord()")
    public void beforeVisitRecord(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        VisitRecord visitRecord = new VisitRecord();
        visitRecord.setIp(request.getRemoteAddr());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        visitRecord.setTime(time);
        visitRecord.setUrl(request.getRequestURI());
        visitRecordRepo.save(visitRecord);
    }
    @After("visitRecord()")
    public void afterVisitRecord(){
        System.out.println("afterVisitRecord");
    }


}
