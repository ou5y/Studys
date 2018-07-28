package com.sc.feignconsumer.controller;

import com.sc.feignconsumer.entity.User;
import com.sc.feignconsumer.server.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by baipan
 * Date: 2017-11-19
 */
@RestController
@Api(value = "获取用户", description = "用户")
public class ConsumerController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取用户信息get-user-name", notes = "根据@RequestParam传参")
    @RequestMapping(value = "/get-user-name", method = RequestMethod.POST)
    public Object getByName(@RequestParam String name){
        return userService.postUserByName(name);
    }


    @ApiOperation(value = "获取用户信息get-user-name-age", notes = "根据@RequestParam、@RequestHeader传参")
    @RequestMapping(value = "/get-user-name-age", method = RequestMethod.POST)
    public Object getByNameAndAge(@RequestParam String name, @RequestParam Integer age){
        return userService.postUserByNameAge(name, age);
    }

    @ApiOperation(value = "获取用户信息get-user-user", notes = "根据@RequestBody传参")
    @RequestMapping(value = "/get-user-user", method = RequestMethod.POST)
    public Object getByUser(@RequestParam String name, @RequestParam Integer age, @RequestParam String sex){
        return userService.postUserByUser(new User(null, name, age, sex));
    }

}
