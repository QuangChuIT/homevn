package com.home.app.controller;

import com.home.app.service.category.CategoryLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private CategoryLocalService localService;

    @GetMapping
    public String index(){
        System.out.println("test");
        return "homePage";
    }
}
