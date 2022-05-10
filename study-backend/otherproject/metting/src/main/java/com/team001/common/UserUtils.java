package com.team001.common;

import com.team001.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

    public static List<String> rep(List<User> users){
        List<String> arr = users.stream().map(x-> x.getUsername()).collect(Collectors.toList());
        return arr;
    }

    public static List<User> rep(List<User> users,List<String> list){
        List<User> arr = users.stream().filter(x->{
            if(list.contains(x.getUsername())){
                return false;
            } else {
                return true;
            }
        }).collect(Collectors.toList());
        return arr;
    }
}
