package com.jayll.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp
{
    public static void main( String[] args )
    {
        System.out.println("hello world");
        SpringApplication.run(EurekaApp.class, args);
    }
}
