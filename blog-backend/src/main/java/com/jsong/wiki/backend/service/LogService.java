package com.jsong.wiki.backend.service;

import com.jsong.wiki.backend.entity.LogEntity;

/**
 * @Author: Jsong
 * @Date: 2020/3/23 21:23
 * @Description:
 */


public interface LogService {

    void saveLog(LogEntity logEntity);
}
