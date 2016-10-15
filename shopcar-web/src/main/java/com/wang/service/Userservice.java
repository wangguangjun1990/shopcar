package com.wang.service;

import com.wang.entity.User;

/**
 * Created by Administrator on 2016/10/14.
 */
public interface Userservice {
     public User check(String username,String pwd);
     public User check1(String username);

}
