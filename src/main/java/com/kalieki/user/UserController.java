package com.kalieki.user;

import com.kalieki.BaseController;
import com.kalieki.exceptions.user.InvalidCredentialsException;
import com.kalieki.exceptions.user.UsernameTakenException;
import com.kalieki.response.Response;
import com.kalieki.response.ResponseBuilder;
import com.kalieki.user.authentication.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

/**
 * Created by kalieki on 9/24/16.
 */
@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*")
public class UserController extends BaseController {

    public static final String KAL_TOKEN = "KalToken";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String REGISTER_SUCCESS_TEMPLATE = "Welcome {0}!";
    public static final String REGISTER_SUCCESSFUL = "Register Successful";
    public static final String LOGIN_SUCCESSFUL = "Login Successful";
    public static final String LOGIN_SUCCESS_TEMPLATE = "Welcome back {0}!";

    private UserDao userDao;
    private Authenticator authenticator;

    public UserController(@Autowired UserDao userDao, @Autowired Authenticator authenticator) {
        this.userDao = userDao;
        this.authenticator = authenticator;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response getUser(@RequestBody() User user, HttpServletResponse response) throws ExecutionException {
        User foundUser = userDao.findOneByUsername(user.getUsername());
        checkValid(foundUser, user);
        setAuthHeader(authenticator.newToken(user), response);

        return new ResponseBuilder()
                .withEntity(foundUser)
                .withSuccessToast(LOGIN_SUCCESSFUL, LOGIN_SUCCESS_TEMPLATE, foundUser.getUsername() )
                .build();
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response createUser(@RequestBody() User user, HttpServletResponse response) throws ExecutionException {
        if(userDao.usernameExists(user.getUsername())){
            throw new UsernameTakenException();
        }

        userDao.save(user);
        setAuthHeader(authenticator.newToken(user), response);

        return new ResponseBuilder()
                .withEntity(user)
                .withSuccessToast(REGISTER_SUCCESSFUL, REGISTER_SUCCESS_TEMPLATE, user.getUsername() )
                .build();
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response logout(@RequestHeader HttpHeaders headers) throws ExecutionException {
        authenticator.destroy(headers);
        return new ResponseBuilder()
                .withSuccessToast(null, "Logged Out Success!")
                .build();
    }







    private void checkValid(User foundUser, User user) {
        if(foundUser == null || !user.getPassword().equals(foundUser.getPassword())){
            throw new InvalidCredentialsException();
        }
    }

    private void setAuthHeader(String token, HttpServletResponse response) {
        response.setHeader(KAL_TOKEN,token);
        response.setHeader(ACCESS_CONTROL_EXPOSE_HEADERS,KAL_TOKEN);
    }











}
