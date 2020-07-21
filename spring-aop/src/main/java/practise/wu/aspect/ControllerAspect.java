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
 *
 */
@Component
@Aspect
public class ControllerAspect {

    /**
     * Aspect(切面):通常是一个类，里面可以定义切入点和通知
     * JoinPoint(连接点):程序执行过程中明确的点，一般是方法的调用
     * Advice(通知):AOP在特定的切入点上执行的增强处理，before,after,afterReturning,afterThrowing,around
     * Pointcut(切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
     * AOP代理：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以是JDK动态代理(基于接口)，或CGLIB代理(基于子类)
     *
     *
     * 通知方法:
     *
     * 前置通知:在我们执行目标方法之前运行(@Before)
     * 后置通知:在我们目标方法运行结束之后 ,不管有没有异常(@After)
     * 返回通知:在我们的目标方法正常返回值后运行(@AfterReturning)
     * 异常通知:在我们的目标方法出现异常后运行(@AfterThrowing)
     * 环绕通知:动态代理, 需要手动执行joinPoint.procced()(其实就是执行我们的目标方法执行之前相当于前置通知, 执行之后就相当于我们后置通知(@Around)
     */




    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 可以统一定义切点
     * 第1个 * 表示要拦截的目标方法返回值可以是任意的【也可以明确指明返回类型】
     * 第2个 * 表示controller包下的任意类
     * 第3个 * 表示类中的任意方法
     * 最后 .. 表示方法参数任意，个数任意，类型任意
     */
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
