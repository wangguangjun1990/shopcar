package com.wang.service.impl;

import com.wang.entity.User;
import com.wang.mapper.UserMapper;
import com.wang.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@Service
public class UserserviceImpl implements Userservice {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User check(String username, String pwd) {
        Map map=new HashMap();
        map.put("userName",username);
        map.put("passWord",pwd);
       List<User> list=userMapper.searchUserByParams(map);
        if(list.size()==1){
            return list.get(0);
        }else
            return null;
       }

    @Override
    public User check1(String username) {
        Map map=new HashMap();
        map.put("username",username);
        List<User> list=userMapper.searchUserByParams(map);
        if(list.size()==1){
            return list.get(0);
        }else
            return null;

    }
}
