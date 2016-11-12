package com.kalieki.response;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by kalieki on 10/28/16.
 */
public class DefaultErrorResponse extends HashMap<String, Object> {

    public static final String IT_BROKED = "Server Broked";
    //{"timestamp":1477711117993,"status":500,"error":"Internal Server Error","exception":"org.springframework.dao.InvalidDataAccessResourceUsageException","message":"could not extract ResultSet; SQL [n/a]; nested exception is org.hibernate.exception.SQLGrammarException: could not extract ResultSet","path":"/user/register"}

    public DefaultErrorResponse() {
        this(IT_BROKED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public DefaultErrorResponse(String msg, HttpStatus status) {
        super();
        this.put("timestamp", new Date().getTime());
        this.put("status",status.value());
        this.put("error",status.getReasonPhrase());
        this.put("exception", "RuntimeException");
        this.put("message", msg);
    }
}
