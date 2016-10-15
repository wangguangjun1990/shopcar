package com.wang.service.impl;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.PageRequest;
import com.wang.entity.Book;
import com.wang.mapper.BookMapper;
import com.wang.service.BookService;
import com.wang.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/15.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Page<Book> findall(int s,int q) {
        PageRequest pg=new PageRequest(s,q);
        Page<Book> list=bookMapper.searchBookByParams(null,pg);
          return list;

    }

    @Override
    public Book findbyid(int id) {
          Map map=new HashMap();
          map.put("bookId",id);
            List<Book> list=bookMapper.searchBookByParams(map);
               if(list.size()==1){
                   return list.get(0);
               }
        else
                   return null;
    }
}
