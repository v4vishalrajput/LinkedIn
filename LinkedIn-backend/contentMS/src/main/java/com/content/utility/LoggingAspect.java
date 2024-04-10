package com.content.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.content.exception.UserException;



@Aspect
@Component
public class LoggingAspect {
    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.content.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(UserException exception) {
        LOGGER.error("in aspect");
        LOGGER.error(exception.getMessage(), exception);
    }
    }

