package com.job.service.security;

import org.apache.catalina.User;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();
    public static void set(UserContext user){
        CONTEXT.set(user);
    }
     public static UserContext get(){
        return CONTEXT.get();
     }
     public static void clear(){
        CONTEXT.remove();
     }
}
