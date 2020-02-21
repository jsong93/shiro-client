package com.jsong.wiki.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @RequestMapping("/save")
    public void save(){
        System.out.println(111);
    }
}
