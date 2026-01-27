package com.shema.Hospital_managment_system_Spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("execution(* com.shema.Hospital_managment_system_Spring.service..*(..))")
    public void serviceMethod() {}


    @Before("serviceMethod()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("➡️ Entering method: {}", joinPoint.getSignature());
    }

    @Around("serviceMethod()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("🟢 Method START: {}", joinPoint.getSignature());

        try {
            Object result = joinPoint.proceed();
            log.info("✅ Method SUCCESS: {}", joinPoint.getSignature());
            return result;
        } catch (Exception ex) {
            log.error("❌ Method FAILED: {}", joinPoint.getSignature(), ex);
            throw ex;
        } finally {
            log.info("🔚 Method END: {}", joinPoint.getSignature());
        }
    }

    @AfterThrowing(pointcut = "serviceMethod()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("🔥 Exception in {}: {}", joinPoint.getSignature(), ex.getMessage());
    }
}
