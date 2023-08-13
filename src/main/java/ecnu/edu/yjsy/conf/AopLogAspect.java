package ecnu.edu.yjsy.conf;

import ecnu.edu.yjsy.annotations.AopLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @date 2023/8/12  23:47
 */
@Aspect
@Component
public class AopLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(AopLogAspect.class);
    private final static Logger slowLogger = LoggerFactory.getLogger("slow_logger");

    private static final long slow_log_threshold = 10*1000;
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
        long beginTime = System.currentTimeMillis();
        String methodDetail = joinPoint.getSignature().toLongString();
        joinPoint.proceed(); // 执行目标方法
        MethodSignature methodSignature = getMethodSignature(joinPoint);
        // get annotation on method
        AopLog annotation = methodSignature.getMethod().getAnnotation(AopLog.class);
        boolean enableTimeRecord = annotation.enableTimeRecord();
        if (enableTimeRecord){
            long endTime = System.currentTimeMillis();
            if(endTime - beginTime > slow_log_threshold){
                // a slow method execution
                double slow_seconds = (endTime-beginTime)/1000.0;
                slowLogger.error("A slow Execution, execution time [{} second], method detail:[{}]", slow_seconds, methodDetail);
            }
        }
    }

    public MethodSignature getMethodSignature(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        if(signature instanceof  MethodSignature){
            return (MethodSignature)signature;
        }
        return null;
    }
}
