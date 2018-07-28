package com.sc.feignconsumer;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@EnableFeignClients//开启feign客户端注解
@EnableDiscoveryClient//开始eureka客户端注解
@EnableCircuitBreaker    //开启断路器功能
@EnableHystrixDashboard
@SpringBootApplication
public class FeignConsumerApplication {


	/**
	 * 定义日志级别
	 */
	@Bean
	public Logger.Level feignLoggerLevel(){
		//Logger.Level level = Logger.Level.NONE;//默认，不记录任何信息
		//Logger.Level level = Logger.Level.BASIC;//仅仅记录请求方法、URL以及响应状态码和执行时间
		//Logger.Level level = Logger.Level.HEADERS;//除了BASIC的日志之外，还会记录请求和响应的头信息
		Logger.Level level = Logger.Level.FULL;//记录所有的请求和响应信息的明细，包括头信息、请求体、元数据等
		return level;
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplication.class, args);
	}
}
