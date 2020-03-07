package com.jsong.wiki.backend;

import org.junit.Test;

import java.util.UUID;

/**
 * @Author: Jsong
 * @Date: 2020/3/1 15:01
 * @Description:
 */
public class BackendTestApplication {
    public void redirect() {

    }

    @Test
    public void getUuid() {
        System.out.println(UUID.randomUUID().toString());
    }

}
