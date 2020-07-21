package com.wu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/21
 */
@Component
@Aspect
public class MethodAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodAspect.class);

    @Pointcut("execution(* com.wu.controller.*.*(..))")
    private void methodPointCut () {

    }

    @Around(value = "methodPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("执行" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                    + "方法," + parseParames(joinPoint.getArgs()) + ",耗时:" + (end - start) + " ms!");
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            LOGGER.error(joinPoint + ",耗时:" + (end - start) + " ms,抛出异常 :" + e.getMessage());
            throw e;
        }
    }

    private String parseParames(Object[] parames) {

        if (null == parames || parames.length <= 0) {
            return "该方法没有参数";

        }
        StringBuffer param = new StringBuffer("请求参数 # 个:[ ");
        int i = 0;
        for (Object obj : parames) {
            i++;
            if (i == 1) {
                param.append(obj.toString());
                continue;
            }
            param.append(" ,").append(obj.toString());
        }
        return param.append(" ]").toString().replace("#", String.valueOf(i));
    }

    @AfterReturning(value = "methodPointCut()", returning = "ret")
    public void after(JoinPoint joinPoint, Object ret) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        LOGGER.info(name + "方法返回：" + ret);
    }

    @AfterThrowing(value = "methodPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        LOGGER.info(name + "方法抛异常：" + e.getMessage());
    }
}
