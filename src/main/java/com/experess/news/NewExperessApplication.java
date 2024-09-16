package com.experess.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.demo.repository"})
@EntityScan(basePackages = {"com.example.demo.entity"})
public class NewExperessApplication {
	public static void main(String[] args) {
		SpringApplication.run(NewExperessApplication.class, args);
	}

}
