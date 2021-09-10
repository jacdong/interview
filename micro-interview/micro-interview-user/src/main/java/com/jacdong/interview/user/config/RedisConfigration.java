package com.jacdong.interview.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.jacdong.interview.user.common.RedisObjectSerializer;

/**
 * 
 * @ClassName: RedisRepositoryConfig
 * @Description: 配置redis
 * @author DongBin
 * @date 2021-09-08 06:08:32
 */
@Configuration
@EnableRedisRepositories
public class RedisConfigration {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
	    RedisTemplate<String,Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(redisConnectionFactory);
	    template.setKeySerializer(new StringRedisSerializer());
	    template.setValueSerializer(new RedisObjectSerializer());
	    return template;
	}

}
