package com.kalieki.exceptions.user;

import com.kalieki.exceptions.KaliekiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kalieki on 10/28/16.
 */

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = UsernameTakenException.USERNAME_ALREADY_TAKEN)
public class UsernameTakenException extends KaliekiException {

    public static final String USERNAME_ALREADY_TAKEN = "Username Already Taken";
}
