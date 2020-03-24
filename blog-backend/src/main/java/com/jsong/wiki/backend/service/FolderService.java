package com.jsong.wiki.backend.service;

import com.jsong.wiki.backend.annotation.LogAnnotation;
import com.jsong.wiki.backend.entity.FolderEntity;

/**
 * @Author: Jsong
 * @Date: 2020/3/17 21:41
 * @Description:
 */
public interface FolderService {

    void addFolder(FolderEntity folderEntity);
}
