package com.jsong.wiki.backend.controller;

import com.jsong.wiki.backend.DataResult;
import com.jsong.wiki.backend.annotation.LogAnnotation;
import com.jsong.wiki.backend.entity.ArticleEntity;
import com.jsong.wiki.backend.entity.FolderEntity;
import com.jsong.wiki.backend.exception.BusinessException;
import com.jsong.wiki.backend.exception.code.BaseResponseCode;
import com.jsong.wiki.backend.exception.code.ResponseCodeInterface;
import com.jsong.wiki.backend.service.ArticleService;
import com.jsong.wiki.backend.service.FolderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Log4j2
public class BlogController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private FolderService folderService;

    //    @CrossOrigin
    @PostMapping("/save")
    public String save(@RequestBody ArticleEntity articleEntity) {
        log.info("save");
        articleService.saveArticle(articleEntity);
        return "111";
    }

    @PostMapping("/newFolder")
    @LogAnnotation(action = "新增文件夹")
    public DataResult addFolder(@RequestBody FolderEntity folderEntity) {
        folderService.addFolder(folderEntity);
        return DataResult.success();
    }

    //    @RestControllerAdvice 测试
    @PostMapping("/test")
    public void test(ModelMap modelMap, @ModelAttribute("author") String author) {
        modelMap.get("author");
        throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
    }
}
