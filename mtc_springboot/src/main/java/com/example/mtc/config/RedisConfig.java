package com.example.mtc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

import java.io.Serializable;
import java.util.List;

@Configuration
public class RedisConfig {
    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public RedisTemplate<String, Object> serializableRedisTemplate() {
        RedisTemplate<String, Object> serializableRedisTemplate = new RedisTemplate<>();
        serializableRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        serializableRedisTemplate.setKeySerializer(new StringRedisSerializer());
        serializableRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        serializableRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        serializableRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return serializableRedisTemplate;
    }

//    @Bean(name = "lockScript")
//    public RedisScript<Long> lockScript() {
//        DefaultRedisScript<Long> lockScript = new DefaultRedisScript<>();
//        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/lock.lua")));
//        lockScript.setResultType(Long.class);
//        return lockScript;
//    }
//
//    @Bean(name = "unlockScript")
//    public RedisScript<Long> unlockScript() {
//        DefaultRedisScript<Long> unlockScript = new DefaultRedisScript<>();
//        unlockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/unlock.lua")));
//        unlockScript.setResultType(Long.class);
//        return unlockScript;
//    }
//
//    @Bean(name = "DECRScript")
//    public RedisScript<Boolean> DECRScript() {
//        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
//        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/decrement.lua")));
//        script.setResultType(Boolean.class);
//        return script;
//    }
//
//    @Bean(name = "FLOWCONTROL")
//    public RedisScript<List> flowControl() {
//        DefaultRedisScript<List> script = new DefaultRedisScript<>();
//        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/flowControl.lua")));
//        script.setResultType(List.class);
//        return script;
//    }
}
