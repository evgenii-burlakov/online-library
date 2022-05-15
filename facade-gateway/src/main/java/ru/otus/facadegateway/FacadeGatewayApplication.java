package ru.otus.facadegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FacadeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacadeGatewayApplication.class, args);
    }

}
