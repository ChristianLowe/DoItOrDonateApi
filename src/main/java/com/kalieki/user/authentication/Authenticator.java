package com.kalieki.user.authentication;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.kalieki.exceptions.user.InvalidTokenException;
import com.kalieki.generator.RandomString;
import com.kalieki.user.User;
import com.kalieki.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by kalieki on 9/27/16.
 */
@Component
public class Authenticator {

    private LoadingCache<String, String> cache;
    private UserDao userDao;

    @Autowired
    public Authenticator(UserDao userdao) {
        this.userDao = userdao;
        initCache();

    }

    private void initCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                throw new InvalidTokenException();
                            }
                        });
    }

    private final String AUTHORIZATION = "Authorization";

    public User getUser(String token) {
        try {
            String username = cache.get(token);
            return userDao.findOneByUsername(username);
        }catch(ExecutionException e){
            throw new RuntimeException(e);
        }
    }

    public String newToken(User u) throws ExecutionException {
        String newToken = RandomString.length(512);
        try {
            cache.get(newToken);
        }catch(UncheckedExecutionException uce){
            if(uce.getCause() != null && uce.getCause().getClass().equals(InvalidTokenException.class)){
                cache.put(newToken, u.getUsername().toLowerCase());
                return newToken;
            }else{
                throw uce;
            }
        }

        return newToken(u);
    }

    public void destroy(HttpHeaders headers) {
        String tokenStr = headers.getFirst(AUTHORIZATION);
        cache.invalidate(tokenStr);
    }
}
