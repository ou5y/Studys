package com.bp.ribbonconsumer.controller;

import com.bp.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;


    /**
     * Ribbon默认采用轮询的方式进行调用
     * 从而实现客户端的负载均衡
     * Created by baipan
     * Date: 2017/10/27
     */
    @RequestMapping(value = "/hello-ribbon-consumer/{n}", method = RequestMethod.POST)
    public String helloConsumer(@PathVariable(value = "n", required = false) String n){
//        ResponseEntity<String> body = restTemplate.getForEntity("http://hello-service/hello/{1}", String.class, "狗娃子");
//        String str = body.getBody();
//        return str;
        return helloService.helloService(n);
    }


}
