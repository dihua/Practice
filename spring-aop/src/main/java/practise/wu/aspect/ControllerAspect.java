package practise.wu.aspect;

import practise.wu.utils.CommUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
public class ControllerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(* practise.wu.controller.*.*(..))")
    private void controllerPointCut () {

    }

    @Before(value = "controllerPointCut()")
    public void before(JoinPoint joinPoint) {
        LOGGER.info("==" + CommUtils.getTimeStr() + " 开始请求" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName() + "==");
        LOGGER.info("before 方法前置处理");
    }


    @After(value = "controllerPointCut()")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("==" + CommUtils.getTimeStr() + " 请求" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName() + "结束==");
        LOGGER.info("after 方法后置处理");
    }
}
