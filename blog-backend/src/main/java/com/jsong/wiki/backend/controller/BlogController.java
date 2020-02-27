package com.jsong.wiki.backend.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@Log4j2
public class BlogController {
//    @CrossOrigin
    @RequestMapping("/save")
    public void save() {
        log.info("save");
        System.out.println(111);
    }
}
