package com.example.demo.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by prakashdas on 16/08/18.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
