package com.example.demo.service.impl;

import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by prakashdas on 19/08/18.
 */
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
}
