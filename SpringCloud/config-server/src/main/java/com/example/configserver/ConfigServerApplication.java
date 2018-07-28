package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;



@EnableConfigServer	//开启Spring Cloud Config的服务功能
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ConfigServerApplication.class, args);
		new SpringApplicationBuilder(ConfigServerApplication.class)
				.web(true)
				.run(args);
	}
}
