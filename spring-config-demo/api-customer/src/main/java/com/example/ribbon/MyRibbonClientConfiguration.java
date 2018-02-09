package com.example.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2018/2/7.
 * 	MyRibbonClientConfiguration必须是@Configuration，但请注意，它不在主应用程序上下文的@ComponentScan中，
 * 	否则将由所有@RibbonClients共享。如果您使用@ComponentScan（或@SpringBootApplication），
 * 	则需要采取措施避免包含（例如将其放在一个单独的，不重叠的包中，或者指定要在@ComponentScan）。
 * 	最好就是和@SpringBootApplication不放在的同包和子包下 因为@SpringBootApplication默认扫描本包和子包所有的配置
 */
@Configuration
public class MyRibbonClientConfiguration {
//	IRule
//	功能：根据特定算法中从服务列表中选取一个要访问的服务
//	常用IRule实现有以下几种：
//
//	RoundRobinRule
//	轮询规则，默认规则。同时也是更高级rules的回退策略
//
//			AvailabilityFilteringRule
//	这个负载均衡器规则，会先过滤掉以下服务：
//
//	a. 由于多次访问故障而处于断路器跳闸状态
//	b. 并发的连接数量超过阈值
//			然后对剩余的服务列表按照RoundRobinRule策略进行访问
//
//	WeightedResponseTimeRule
//	根据平均响应时间计算所有服务的权重，响应时间越快，服务权重越重、被选中的概率越高。刚启动时，如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够，会切换到WeightedResponseTimeRule。
//
//	RetryRule
//	先按照RoundRobinRule的策略获取服务，如果获取服务失败，则在指定时间内会进行重试，获取可用的服务
//
//			BestAvailableRule
//	此负载均衡器会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
//
//			RandomRule
//	随机获取一个服务

	@Bean
	public IRule randomRule(){
		return new RandomRule();
	}
}
