package com.kalieki.exceptions.user;

import com.kalieki.exceptions.KaliekiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kalieki on 9/27/16.
 */

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid Token")
public class InvalidTokenException extends KaliekiException {

}
