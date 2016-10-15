package com.wang.controller;

import com.wang.entity.Book;
import com.wang.service.BookService;
import com.wang.vo.BookVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/10/15.
 */
@Controller
public class CarController extends BaseController {

    @Autowired
    private BookService bookService;
     @RequestMapping("/controller/findbook")
    public String findbook(@RequestParam("bookId") String[]bookId,HttpSession session){
          Map car= (Map) session.getAttribute("car");
         if(car==null){
             car=new HashMap();
         }
         for(int i=0;bookId!=null&&i< bookId.length;i++){
             BookVo bookVo=new BookVo();
             int s1=Integer.parseInt(bookId[i]);
             Book book = bookService.findbyid(s1);
             bookVo.setBookPic(book.getBookPic());
             bookVo.setBookName(book.getBookName());
             bookVo.setBookId(book.getBookId());
             bookVo.setBookPrice(book.getBookPrice());
             bookVo.setBookStor(book.getBookStor());

             BookVo s =(BookVo)car.get(book.getBookId());
             if(s!=null){
                 bookVo.setCount(s.getCount()+1);
             }
             else
                 bookVo.setCount(1);
             car.put(book.getBookId(),bookVo);

         }
           session.setAttribute("car", car);
            return "shop";

     }
    //修改商品信息
    @RequestMapping("/controller/update")
    @ResponseBody
    public int update(@RequestParam("count") Integer count,@RequestParam("bookId") Integer bookId,HttpSession session){
        Map car= (Map) session.getAttribute("car");
                   BookVo bookVo= (BookVo)car.get(bookId);
                   bookVo.setCount(count);
               int sum=0;
        Collection<BookVo> s=car.values();
        for(BookVo b:s){
            //循环计算总计
            sum+=b.getCount()*b.getBookPrice();
        }
        //将修改好的购物车放入session
        session.setAttribute("car", car);
                return  sum;
    }

     //删除商品
    @RequestMapping("/controller/removecar")
     public String remove(@RequestParam("bookId") Integer bookId,HttpSession session){
        Map car= (Map) session.getAttribute("car");
        car.remove(bookId);
        session.setAttribute("car",car);
        return "shop";
    }







}

