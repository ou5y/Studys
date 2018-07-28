package com.sc.feignconsumer.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 关闭Hystrix容错的配置类
 * 当某个服务需要关闭的时候可以eg：
 * @FeignClient(name = "hello-service", configuration = DisableHystrixConfiguration.class)
 * Created by baipan
 * Date: 2018-07-15
 */
//巨坑：关于在Spring Cloud Feign工程中使用Hystrix配置不生效的问题
//这个注解不去掉影响全局
//https://blog.csdn.net/lvyuan1234/article/details/77155919
//@Configuration
public class DisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }

}
