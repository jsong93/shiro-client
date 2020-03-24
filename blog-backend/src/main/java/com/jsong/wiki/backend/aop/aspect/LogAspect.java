package com.jsong.wiki.backend.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.jsong.wiki.backend.annotation.LogAnnotation;
import com.jsong.wiki.backend.entity.LogEntity;
import com.jsong.wiki.backend.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: Jsong
 * @Date: 2020/3/22 20:48
 * @Description:
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    /***
     * 切点
     * @date 2020/3/22 21:07
     * @author Jsong
     * @param
     * @return void
     */
    @Pointcut("@annotation(com.jsong.wiki.backend.annotation.LogAnnotation)")
    public void logpointCut() {

    }

//    @Before("createTiemPointCut()")
//    public void before(ProceedingJoinPoint proceedingJoinPoint){
//        Object[] args = proceedingJoinPoint.getArgs();
//        for (Object arg : args) {
//
//        }
//    }

    /***
     * 环绕 执行方法前方法后
     * @date 2020/3/22 21:17
     * @author Jsong
     * @param proceedingJoinPoint
     * @return void
     */
    @Around("logpointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogEntity logEntity = new LogEntity();
        // 执行开始时间
        Long beginTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        // 执行时长
        long time = System.currentTimeMillis() - beginTime;
        logEntity.setDuration((int) time);

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        // 注解上的名字
        String action = logAnnotation.action();
        logEntity.setOperate(action);

        // 类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        logEntity.setMethod(className + "." + methodName);

        Object[] args = proceedingJoinPoint.getArgs();
        String params = null;
        if (args.length > 0) {
            // date对象 变json字符串
//            params = JSON.toJSONString(args);
            params = JSON.toJSONStringWithDateFormat(args, "yyyy-MM-dd HH:mm:ss");
        }
        logEntity.setParams(params);
        logService.saveLog(logEntity);
    }

}
