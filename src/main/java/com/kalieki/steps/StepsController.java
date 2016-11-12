package com.kalieki.steps;

import com.kalieki.BaseController;
import com.kalieki.response.Response;
import com.kalieki.response.ResponseBuilder;
import com.kalieki.user.User;
import com.kalieki.user.authentication.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kalieki on 11/12/16.
 */

@RestController
@RequestMapping(value = "/steps")
@CrossOrigin(origins = "*")
public class StepsController extends BaseController {

    @Autowired
    private Authenticator authenticator;

    @RequestMapping(method = RequestMethod.PUT)
    public Response addNewSteps(Steps steps, @RequestHeader(value="Authorization")String token){

        User curUser = authenticator.getUser(token);

        return new ResponseBuilder().build();
    }
}
