package com.kalieki.steps;

import com.kalieki.BaseController;
import com.kalieki.response.Response;
import com.kalieki.response.ResponseBuilder;
import com.kalieki.user.User;
import com.kalieki.user.UserDao;
import com.kalieki.user.authentication.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kalieki on 11/12/16.
 */

@RestController
@RequestMapping(value = "/steps")
@CrossOrigin(origins = "*")
public class StepsController extends BaseController {

    private UserDao userDao;
    private Authenticator authenticator;

    public StepsController(@Autowired UserDao userDao, @Autowired Authenticator authenticator) {
        this.userDao = userDao;
        this.authenticator = authenticator;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response addNewSteps(@RequestBody Steps steps, @RequestHeader(value="Authorization")String token){

        User curUser = authenticator.getUser(token);
        //steps.setUser(curUser);
        steps.setLogTime(new Date());
        List<Steps> stepsList = curUser.getSteps();
        if(stepsList != null) {
            stepsList.add(steps);
            curUser.setSteps(stepsList);
        } else {
            stepsList = new ArrayList<>();
            stepsList.add(steps);
            curUser.setSteps(stepsList);
        }
        userDao.save(curUser);
        return new ResponseBuilder().withEntity(curUser).build();
    }
}
