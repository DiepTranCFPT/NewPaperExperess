package com.experess.news;

import io.swagger.models.Swagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.experess.news.repository"})
@EntityScan(basePackages = {"com.experess.news.entity"})
public class NewExperessApplication {
	public static void main(String[] args) {
		SpringApplication.run(NewExperessApplication.class, args);
		//SpringApplication.run(Swagger.class, args);
	}

}
