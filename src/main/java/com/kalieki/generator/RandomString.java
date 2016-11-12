package com.kalieki.generator;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by kalieki on 10/8/16.
 */
public class RandomString {
    public static String length(int i){
        return new BigInteger(i, new SecureRandom()).toString(32);
    }

}
