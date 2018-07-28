package com.example.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by baipan
 * Date: 2018-07-17
 */
@RefreshScope
@ConfigurationProperties()
@RestController
public class TestController {

    /**
     * @ConfigurationProperties
     * +
     * setter
     * 得到
     */
    private String from;


    /**
     * @Value
     * 得到
     */
    @Value("${from}")
    private String fromByValue;


    /**
     * Environment获取
     */
    @Autowired
    private Environment env;


    @RequestMapping("/from1")
    public String from1(){
        return this.from;
    }

    @RequestMapping("/from2")
    public String from2(){
        return this.fromByValue;
    }

    @RequestMapping("/from3")
    public String from3(){
        String f = env.getProperty("from", "空的");
        return f;
    }


    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
}
