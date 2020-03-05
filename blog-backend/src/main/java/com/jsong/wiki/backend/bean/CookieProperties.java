package com.jsong.wiki.backend.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Jsong
 * @Date: 2020/3/3 14:40
 * @Description:
 */

@Component
@Data
@ConfigurationProperties(prefix = "cookie")
public class CookieProperties {
    private String domain;
    private String path;
    private Boolean httpOnly;
}
