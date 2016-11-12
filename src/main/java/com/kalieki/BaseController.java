package com.kalieki;

import com.google.common.util.concurrent.UncheckedExecutionException;
import com.kalieki.exceptions.KaliekiException;
import com.kalieki.response.DefaultErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by kalieki on 10/8/16.
 */
public class BaseController {

    Logger log = LoggerFactory.getLogger(BaseController.class);

    protected static final String SUCCESS = "Success";

    @ExceptionHandler(value = Exception.class)
    public Map defaultErrorHandler(Exception e, HttpServletResponse response) throws Throwable {
        log.warn(e.getMessage());
        log.debug(e.getMessage(),e);

        if(e.getClass().equals(UncheckedExecutionException.class) && KaliekiException.class.isAssignableFrom(e.getCause().getClass())){
            throw e.getCause();
        }

        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null && KaliekiException.class.isAssignableFrom(e.getClass()))
            throw e;

        if(ServletRequestBindingException.class.isAssignableFrom(e.getClass())){
            response.setStatus(400);
            return new DefaultErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

        }else{
            response.setStatus(500);
            return new DefaultErrorResponse();
        }
    }

}
