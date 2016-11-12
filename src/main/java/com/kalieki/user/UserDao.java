package com.kalieki.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by kalieki on 9/24/16.
 */


public interface UserDao extends CrudRepository<User, Long> {

    User findOneByUsername(String username);

    User findOneByUsernameAndPassword(String user, String password);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
    boolean usernameExists(@Param("username") String username);
}
