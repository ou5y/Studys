package com.bp.ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient	//注册为Eureka客户端应用，获取服务发现的能力
//@EnableCircuitBreaker   	//开启断路器功能
@SpringCloudApplication		//这个注解包含上面3个注解
@EnableHystrixDashboard
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	@LoadBalanced // 开启客户端负载均衡
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}


}
