package com.experess.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.experess.news.repository"})
@EntityScan(basePackages = {"com.experess.news.entity"})
@EnableAsync
public class NewExperessApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewExperessApplication.class, args);


        // auto mở swagger
        openSwaggerUI();
    }


    /**
     * auto mở swagger
     */
    private static void openSwaggerUI() {
        String url = "http://localhost:8080/swagger-ui/index.html";
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win"))
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();

//            else if (System.getProperty("os.name").toLowerCase().contains("mac"))
//                new ProcessBuilder("open", url).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
