package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableFeignClients("com.microservice")
@EnableDiscoveryClient
public class CurrencyCoversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyCoversionServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler()
	{
		return Sampler.ALWAYS_SAMPLE;
	}
}
