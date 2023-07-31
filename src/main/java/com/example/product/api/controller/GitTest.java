package com.example.product.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GitTest {

    @GetMapping("/test/bread")
    public String TestBread(){
        return "TestBread";
    }
}
