package com.jsong.wiki.backend.config;

import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSConfigurationException;
import com.thetransactioncompany.cors.CORSFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Configuration
//@ComponentScan(value = "com.jsong.wiki.cas.filter")
public class CorsFilterConfig {




    @Bean
    public CORSFilter corsFilter() {
        System.out.println("-----------------corsFilter start-------------------");
        CORSFilter corsFilter = new CORSFilter();
        try {
            // 读取cors配置
            Properties properties= PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
            CORSConfiguration corsConfiguration = new CORSConfiguration(properties);
            corsFilter.setConfiguration(corsConfiguration);
            System.out.println("-----------------corsFilter-------------------");
            return corsFilter;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CORSConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
