package com.cby.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.cby.entity.User;
import com.cby.enums.ResultEnum;
import com.cby.exception.SurveyException;
import com.cby.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by Ma on 2017/6/7.
 */
@Aspect
@Component
public class PermissionAspect {

    @Before("@annotation(com.cby.annotation.UserAccess)")
    public void checkPermission(JoinPoint joinPoint) throws Exception{
        System.out.println("前置通知");
        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法
//        Object[] args = joinPoint.getArgs();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token=request.getParameter("token");
        System.out.println("前置通知  token:"+token);
        User user= UserService.loginUserMap.get(token);
        if(user==null){
            System.out.println("验证不通过！");
            throw new SurveyException(ResultEnum.NOPERMISSION);
        }
    }

    /**
     * 根据类和方法名得到方法
     */
    public Method getMethodByClassAndName(Class c , String methodName){
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method ;
            }
        }
        return null;
    }

}
