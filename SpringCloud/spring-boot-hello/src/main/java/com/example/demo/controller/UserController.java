package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "用户",description = "用户接口")
public class UserController {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取用户信息POST", notes = "根据url的id来获取用户对象")
    @RequestMapping(value = "/post-user", method = RequestMethod.POST)
    public Object user(@ApiParam(value = "用户ID") @RequestParam(required = false) Integer id) {
        logger.info("post收到参数："+id);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            jsonObject.put("error", "id参数为空");
        } else {
            try {
                User user = userService.get(id);
                if (user == null) {
                    jsonObject.put("error", "未找到ID=" + id + "的User信息");
                } else {
                    logger.info("post返回信息：");
                    logger.info(JSON.toJSONString(user));
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }


    //header中文会乱码
    @ApiOperation(value = "获取用户信息get-user-name-age", notes = "获取用户对象")
    @RequestMapping(value = "/get-user-name-age", method = RequestMethod.POST)
    public Object getByNameAndAge(@RequestHeader String name, @RequestHeader Integer age){
        logger.info("get-user-name-age收到参数："+name+"\t第二个参数："+age);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(name)) {
            jsonObject.put("error", "参数为空");
        } else {
            try {
                User user = userService.getByEntity(new User(null, name, age, null));
                if (user == null) {
                    jsonObject.put("error", "未找到ANME=" + name + "\tAGE=" + age + "的User信息");
                } else {
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }

    @ApiOperation(value = "获取用户信息GET", notes = "根据url的id来获取用户对象")
    @RequestMapping(value = "/get-user/{n}", method = RequestMethod.GET)
    public Object users(@PathVariable(value = "n", required = false) Integer id) {
        logger.info("get收到参数："+id);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            jsonObject.put("error", "id参数为空");
        } else {
            try {
                User user = userService.get(id);
                if (user == null) {
                    jsonObject.put("error", "未找到ID=" + id + "的User信息");
                } else {
                    logger.info("get返回信息：");
                    logger.info(JSON.toJSONString(user));
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }

    @ApiOperation(value = "获取用户信息get-user-name", notes = "获取用户对象")
    @RequestMapping(value = "/get-user-name", method = RequestMethod.POST)
    public Object getByName(@RequestParam String name){
        logger.info("get-user-name收到参数："+name);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(name)) {
            jsonObject.put("error", "参数为空");
        } else {
            try {
                User user = userService.getByEntity(new User(null, name, null, null));
                if (user == null) {
                    jsonObject.put("error", "未找到ANME=" + name + "的User信息");
                } else {
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }

    @ApiOperation(value = "获取用户信息get-user-user", notes = "获取用户对象")
    @RequestMapping(value = "/get-user-user", method = RequestMethod.POST)
    public Object getByUser(@RequestBody User user){
        logger.info("getByUser收到参数：" + (user==null?"null了莫得了":user.toString()));
        JSONObject jsonObject = new JSONObject();
        if (user==null) {
            jsonObject.put("error", "参数为空");
        } else {
            try {
                User u = userService.getByEntity(user);
                if (u == null) {
                    jsonObject.put("error", "未找到***********的User信息");
                } else {
                    return u;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }
}
