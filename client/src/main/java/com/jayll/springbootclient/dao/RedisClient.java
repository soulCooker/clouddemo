package com.jayll.springbootclient.dao;

import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisClient implements InitializingBean {
    RedisTemplate<String, String> template;
    @Value("${REDIS_HOST}")
    private String host;
    @Value("${REDIS_PASSWORD}")
    private String password;
    @Value("${REDIS_PORT}")
    private int port;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("-------------RedisClient REDIS_HOST:%s, REDIS_PASSWORD:%s, REDIS_PORT:%d\n", host, password, port);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(LettuceConnectionFactory.createRedisConfiguration(RedisURI.builder().withHost(host).withPassword((CharSequence) password).withPort(port).build()));
        connectionFactory.afterPropertiesSet();
        template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(StringRedisSerializer.UTF_8);
        template.afterPropertiesSet();
    }

    public RedisTemplate<String, String> getTemplate() {
        return template;
    }
}
