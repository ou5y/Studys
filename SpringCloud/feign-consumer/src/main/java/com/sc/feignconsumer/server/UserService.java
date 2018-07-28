package com.sc.feignconsumer.server;

import com.sc.feignconsumer.config.DisableHystrixConfiguration;
import com.sc.feignconsumer.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by baipan
 * Date: 2017-11-19
 */
//关闭hello-service服务的Hystrix容错的配置类
//@FeignClient(name = "hello-service", configuration = DisableHystrixConfiguration.class)
@FeignClient(name = "hello-service", fallback = UserServiceFallback.class)//服务名称是不区分大小写的HELLO-SERVICE也可以
public interface UserService {

    //@RequestParam、@RequestHeader的value不能少
    //SpringMVC会根据参数名称默认指定value值
    //Feign不会，必须手动指定，不然IllegalStateException
    @RequestMapping(value = "/get-user-name", method = RequestMethod.POST)
    String postUserByName(@RequestParam(value = "name") String name);


    //header中文会乱码
    @RequestMapping(value = "/get-user-name-age", method = RequestMethod.POST)
    String postUserByNameAge(@RequestHeader(value = "name") String name, @RequestHeader(value = "age") Integer age);


    @RequestMapping(value = "/get-user-user", method = RequestMethod.POST)
    String postUserByUser(@RequestBody User user);
}
