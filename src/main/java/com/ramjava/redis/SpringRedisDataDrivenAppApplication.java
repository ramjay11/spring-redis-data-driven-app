package com.ramjava.redis;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

import reactor.core.publisher.Mono;

// Reactive means it is non-blocking/asynchronous
@SpringBootApplication
public class SpringRedisDataDrivenAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisDataDrivenAppApplication.class, args);
	}
	
	@Bean
	ApplicationRunner geography (ReactiveRedisTemplate<String, String>template) {
		
	}

	@Bean
	ApplicationRunner List(ReactiveRedisTemplate<String, String> template) {
		return args -> {
			
			var listTemplate = template.opsForList();
			var listName = "spring-team";
			var push = listTemplate.leftPushAll(listName, "Huskey", "Saint", "Siber", "Doray");
			push.thenMany(listTemplate.leftPop(listName))
				.doOnNext(System.out::println)
				.thenMany(listTemplate.leftPop(listName))
				.doOnNext(System.out::println)
				.subscribe();	
		};
		
	}
}
