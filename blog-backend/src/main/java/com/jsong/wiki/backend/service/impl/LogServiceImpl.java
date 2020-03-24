package com.jsong.wiki.backend.service.impl;

import com.jsong.wiki.backend.entity.LogEntity;
import com.jsong.wiki.backend.repository.LogDao;
import com.jsong.wiki.backend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Jsong
 * @Date: 2020/3/23 21:24
 * @Description:
 */

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    public void saveLog(LogEntity logEntity){
        logDao.save(logEntity);
    }
}
