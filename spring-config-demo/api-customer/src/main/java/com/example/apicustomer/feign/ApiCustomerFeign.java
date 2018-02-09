package com.example.apicustomer.feign;

import api.Service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 *Create by yangwenfu on 2018/2/5
 * Spring cloud Feign已经集成Ribbon，所以注解@FeignClient的类，默认实现了ribbon的功能。默认是轮询 如果想要改变规则
 * 需要重新配置 参照RibbonClientConfiguration  也不需要引入ribbon的jar包
 *
 * 如果用RestTemplet 调用的话  要在创建RestTemplet加上@LoadBalanced就可以
 */
@FeignClient(value = "api-server")
public interface ApiCustomerFeign extends Service{
}
