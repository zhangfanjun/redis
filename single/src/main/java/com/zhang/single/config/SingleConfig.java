package com.zhang.single.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: 单机配置
 * @author: zhangFanJun
 * @create: 2021-08-30 23:58
 **/
@Slf4j
@Configuration
@PropertySource("classpath:application.properties")
public class SingleConfig {

    @Value("${redis.single.host}")
    private String host;
    @Value("${redis.single.port}")
    private Integer port;

    @Bean
    public JedisPool setSingleRedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(1500L);
        // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(false);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, 3000);
        log.info("host:{}",host);
        return jedisPool;
    }
}
