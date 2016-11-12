package com.kalieki.donation;

import com.kalieki.response.Response;
import com.kalieki.response.ResponseBuilder;
import com.kalieki.user.User;
import com.kalieki.user.UserDao;
import com.kalieki.user.authentication.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kalieki on 11/12/16.
 */

@RestController
@RequestMapping(value = "/donate")
@CrossOrigin(origins = "*")
public class DonationCriteriaController {

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private UserDao userDao;


    @RequestMapping(method = RequestMethod.POST)
    public Response updateDonationCriteria(@RequestBody DonationCriteria donationCriteria, @RequestHeader String authorization){

        User curUser = authenticator.getUser(authorization);

        curUser.setDonationCriteria(donationCriteria);
        userDao.save(curUser);

        return new ResponseBuilder().withEntity(curUser).build();
    }

}
