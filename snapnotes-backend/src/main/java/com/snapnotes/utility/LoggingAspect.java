package com.snapnotes.utility;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.snapnotes.service.NoteServiceImpl.*(..))", throwing = "exception")
    public void logExceptionFromService(Exception exception){
        LOGGER.error(exception.getMessage(),exception);
    }
}
