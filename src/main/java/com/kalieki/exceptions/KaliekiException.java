package com.kalieki.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kalieki on 10/28/16.
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = KaliekiException.IT_BROKED)
public class KaliekiException extends RuntimeException{

    public static final String IT_BROKED = "It broked";
}
