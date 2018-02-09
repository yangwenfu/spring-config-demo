package com.example.apicustomer;

import com.example.ribbon.MyRibbonClientConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RibbonClient(name = "api-server",configuration = MyRibbonClientConfiguration.class)//指定自己的ribbon路由规则
public class ApiCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCustomerApplication.class, args);
	}
}
