package org.prnhs.javaee.swim.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author mosgjig
 */
@Aspect
@Component
public class SwimLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwimLoggerAspect.class);

    @Pointcut("execution(* org.prnhs.javaee.swim.services.*.*(..))")
    public void serviceMethods(){}
    
    @Pointcut("execution(* org.prnhs.javaee.swim.web.*.*(..))")
    public void controllerMethods(){}
    
    
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.debug("I'm here in logBefore");
        LOGGER.debug("My joinPoint {}", joinPoint);
    }
    
//    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        LOGGER.debug("I'm here in logAfter");
        LOGGER.debug("My joinPoint {}", joinPoint);
    }
    
//    @AfterReturning("serviceMethods()")
    public void AfterReturning(JoinPoint joinPoint) {
        LOGGER.debug("I'm here in logAfterReturning");
        LOGGER.debug("My joinPoint {}", joinPoint);
    }
    
    @Around("serviceMethods() || controllerMethods()")
    public Object doBasicAroundLog(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.debug("I'm here in doBasicAroundLog");
        LOGGER.debug("My pjp {}", pjp);
        
        Object retVal = pjp.proceed();
        
        LOGGER.debug("Return Value is {}", retVal);
        return retVal;
    }

}
