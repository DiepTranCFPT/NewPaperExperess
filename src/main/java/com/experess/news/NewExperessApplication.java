package com.experess.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.experess.news.repository"})
@EntityScan(basePackages = {"com.experess.news.entity"})
@EnableAsync
public class NewExperessApplication {
	public static void main(String[] args) {
		SpringApplication.run(NewExperessApplication.class, args).close();
		//SpringApplication.run(Swagger.class, args);
	}


	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(2);
		threadPoolTaskExecutor.setMaxPoolSize(2);
		threadPoolTaskExecutor.setQueueCapacity(500);
		threadPoolTaskExecutor.setThreadNamePrefix("task-executor");
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

}
