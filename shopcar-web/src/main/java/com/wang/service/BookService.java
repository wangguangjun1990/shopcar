package com.wang.service;

import com.fc.platform.commons.page.Page;
import com.wang.entity.Book;
import com.wang.vo.BookVo;


/**
 * Created by Administrator on 2016/10/15.
 */
public interface BookService {
    public Page<Book> findall(int s,int d);

    public Book findbyid(int id);
}
