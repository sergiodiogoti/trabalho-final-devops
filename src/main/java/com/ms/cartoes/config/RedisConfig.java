package com.ms.cartoes.config;

import com.ms.cartoes.entities.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Cliente> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Cliente> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Cliente.class));
        return template;
    }
}

