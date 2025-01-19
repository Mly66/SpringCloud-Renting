package com.nbmly.renting.config;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class RedissonConfig {

    @Value("${redisson.host}")
    private String host;
    @Value("${redisson.port}")
    private Integer port;
    @Value("${redisson.database}")
    private int database;
    @Value("${redisson.timeout}")
    private int timeout;
    @Value("${redisson.maxIdle}")
    private int maxIdle;
    @Value("${redisson.minIdle}")
    private int minIdle;

    @Bean("redissonClient")
    public RedissonClient getRedisClient() {
        Config config = getRedissonConfig(host, port, null, database, timeout, maxIdle, minIdle);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    private Config getRedissonConfig(String host, Integer port, String password, int database,
            int timeout, int maxIdle, int minIdle) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(String.format("redis://%s:%s", host, port)).setConnectTimeout(timeout)
                .setDatabase(database)
                .setConnectionPoolSize(maxIdle)
                .setConnectionMinimumIdleSize(minIdle)
                .setIdleConnectionTimeout(timeout)
                .setTimeout(timeout);

        if (StrUtil.isNotEmpty(password)) {
            serverConfig.setPassword(password);
        }

        return config;
    }
}
