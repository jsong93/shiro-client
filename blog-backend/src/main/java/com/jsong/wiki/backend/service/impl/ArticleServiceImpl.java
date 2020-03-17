package com.jsong.wiki.backend.service.impl;

import com.jsong.wiki.backend.entity.ArticleEntity;
import com.jsong.wiki.backend.repository.ArticleDao;
import com.jsong.wiki.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Jsong
 * @Date: 2020/3/9 20:52
 * @Description:
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void saveArticle(ArticleEntity articleEntity) {
        articleDao.save(articleEntity);
    }
}
