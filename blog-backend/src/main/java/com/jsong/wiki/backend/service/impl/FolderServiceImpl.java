package com.jsong.wiki.backend.service.impl;

import com.jsong.wiki.backend.entity.FolderEntity;
import com.jsong.wiki.backend.repository.FolderDao;
import com.jsong.wiki.backend.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Jsong
 * @Date: 2020/3/17 21:41
 * @Description:
 */

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderDao folderDao;

    @Override
    public void addFolder(FolderEntity folderEntity) {
        folderDao.save(folderEntity);
    }
}
