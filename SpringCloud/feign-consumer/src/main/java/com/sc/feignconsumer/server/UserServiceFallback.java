package com.sc.feignconsumer.server;

import com.sc.feignconsumer.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign的Hystrix服务降级类，对应Ribbon中@HystrixCommand注解的fallbackMethod = "userBack"
 * 在Feign中的运用：
 * @FeignClient(name = "hello-service", fallback = UserServiceFallback.class)
 * Created by baipan
 * Date: 2018-07-15
 */
//UserService是一个接口不能实例化  这是class是需要实例化的
@Component
public class UserServiceFallback implements UserService{

    public UserServiceFallback(){
        System.out.println("init UserServiceFallback");
    }

    @Override
    public String postUserByName(@RequestParam(value = "name") String name) {
        return String.format("UserServiceFallback方法中 -> error method：%s", "postUserByName");
    }

    @Override
    public String postUserByNameAge(@RequestHeader(value = "name") String name, @RequestHeader(value = "age") Integer age) {
        return String.format("UserServiceFallback方法中 -> error method：%s", "postUserByNameAge");
    }

    @Override
    public String postUserByUser(@RequestBody User user) {
        return String.format("UserServiceFallback方法中 -> error method：%s", "postUserByUser");
    }
}
