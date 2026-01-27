package com.shema.Hospital_managment_system_Spring.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class loggingAspect {

    // Reusable pointcut for all services
    @Pointcut("execution(* com.shema.Hospital_managment_system_Spring.service.*.*(..))")
    public void serviceMethod(){}

    // Before advice
    @Before("serviceMethod()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("➡️ [Before] " + joinPoint.getSignature());
    }

    @Around("serviceMethods()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("Transaction START for " + joinPoint.getSignature());

        Object result;
        try{
        result =joinPoint.proceed();
            System.out.println("Transaction COMMIT for " + joinPoint.getSignature());
        }catch (Exception e){
            System.out.println("Transaction ROLLBACK for " + joinPoint.getSignature());
            throw e;
        }
        System.out.println("Transaction END for " + joinPoint.getSignature());
        return result;
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public  void logException(JoinPoint joinPoint, Exception ex){
        System.out.println("❌ Exception in " + joinPoint.getSignature() + ": " + ex.getMessage());
    }

}
