package com.wang.controller;

import com.fc.platform.commons.page.Page;
import com.wang.entity.Book;
import com.wang.entity.User;
import com.wang.service.BookService;
import com.wang.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/10/14.
 */
@Controller
public class UserController {
    @Autowired
    private Userservice userservice;

    @Autowired
    private BookService bookService;
    //根据用户名和密码查客户，判断是否匹配
    @RequestMapping("/check")
       @ResponseBody
       public int  check(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord,HttpSession session){
        User user=userservice.check(userName,passWord);
        if(user!=null){
            session.setAttribute("user",user);
            return 1;
        }
        else
            return -1;
    }

//根据用户查询是否有这个客户
    @RequestMapping("/check1")
    @ResponseBody
    public int  check1(@RequestParam("userName") String userName){
        User user=userservice.check1(userName);
        if(user!=null){

            return 1;
        }
        else
            return -1;
    }

    @RequestMapping("/toindex")
    public String toindex(@RequestParam(name="page", defaultValue = "0") Integer page,ModelMap modelMap){
        Page<Book> list1=bookService.findall(page,5);
        modelMap.put("list1",list1);
        int shang;
        int xia;
        if(page==0){
            shang=0;
            xia=page+1;

        }
        else if(page==list1.getTotalPages()-1){
            shang=page-1;
            xia=list1.getTotalPages()-1;
        }

        else{
            shang=page-1;
            xia=page+1;
        }
        modelMap.put("shang",shang);
        modelMap.put("xia",xia);
        return "index";
    }
}
