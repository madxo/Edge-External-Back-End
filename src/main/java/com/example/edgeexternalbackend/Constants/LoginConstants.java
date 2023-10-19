package com.example.edgeexternalbackend.Constants;

import com.example.edgeexternalbackend.Modal.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LoginConstants {

    public static final Map<String, User> USER_MAP;

    static {
        Map<String, User> map = new HashMap<>();
        map.put("mohit.sharma@octanner.com", new User("mohit.sharma@octanner.com",
                "Mohit",
                "K@srp2017",
                true));
        map.put("saurabh.mishra@octanner.com", new User("saurabh.mishra@octanner.com",
                "Saurabh",
                "K@srp2018",
                false));
        USER_MAP = Collections.unmodifiableMap(map);
    }


}
