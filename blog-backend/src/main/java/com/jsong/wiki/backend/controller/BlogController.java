package com.jsong.wiki.backend.controller;

import com.jsong.wiki.backend.entity.ArticleEntity;
import com.jsong.wiki.backend.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/blog")
@Log4j2
public class BlogController {
    @Autowired
    private ArticleService articleService;

    //    @CrossOrigin
    @RequestMapping("/save")
    public String save(@RequestBody ArticleEntity articleEntity) {
        log.info("save");
        articleService.saveArticle(articleEntity);
        return "111";
    }
}
