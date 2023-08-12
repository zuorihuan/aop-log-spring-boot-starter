package ecnu.edu.yjsy.conf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @date 2023/8/12  23:47
 */
@Aspect
@Component
public class AopLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(AopLogAspect.class);
    @Pointcut("@annotation(ecnu.edu.yjsy.annotations.AopLog)")
    public void aoplog() {}

    // 异常通知
    @AfterThrowing(pointcut = "aoplog()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        // todo 记录报错详细信息
    }

    // 环绕通知
    @Around("aoplog()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
            // todo 记录方法耗时，如果耗时过长，记录一条错误日志
            joinPoint.proceed(); // 执行目标方法
    }
}
