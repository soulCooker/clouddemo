package com.jayll.springbootclient;


import com.jayll.springbootclient.controller.CounterController;
import com.jayll.springbootclient.controller.GreetingController;
import com.jayll.springbootclient.dao.RedisClient;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EurekaClientApplication implements GreetingController, CounterController {
    @Autowired
    private EurekaClient eurekaClient;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private RedisClient redisClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Override
    public String greeting() {
        System.out.println("appName get:" + appName);
        return String.format("hello from %s", eurekaClient.getApplication(appName).getName());
    }

    @Override
    public Long get(String key) {
        RedisTemplate<String, String> redisTemplate = redisClient.getTemplate();
        String value = redisTemplate.opsForValue().get(key);
        return value == null ? null : Long.parseLong(value);
    }

    @Override
    public void incr(String key) {
        RedisTemplate<String, String> redisTemplate = redisClient.getTemplate();
        redisTemplate.opsForValue().increment(key);
    }
}
