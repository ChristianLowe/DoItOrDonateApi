package com.kalieki.exceptions.user;

import com.kalieki.exceptions.KaliekiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kalieki on 10/28/16.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Login Credentials")
public class InvalidCredentialsException extends KaliekiException {

}
