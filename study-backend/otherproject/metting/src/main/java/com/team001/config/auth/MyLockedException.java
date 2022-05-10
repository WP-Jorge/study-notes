package com.team001.config.auth;

import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Component;

public class MyLockedException extends LockedException {
    public MyLockedException(String msg) {
        super(msg);
        System.out.println("##########$$$$$$$$$$$$$$$!111111111111111111111111");
    }

    public MyLockedException(String msg, Throwable t) {
        super(msg, t);
    }
}
