package com.network.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    @AfterThrowing(pointcut = "execution(* com.network.service.*Impl.*(..))", throwing = "exception")
    public void logExceptionFromService(Throwable exception) throws Throwable {
        LOGGER.error(exception.getMessage(), exception);
        throw exception;
    }
}