package com.nbmly.renting.service.imp;

import com.nbmly.renting.common.AccountModRedisKey;
import com.nbmly.renting.service.AccountModService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class AccountModServiceImpl implements AccountModService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveAccountCollection(String key, String value) {
        long time = new Date().getTime();
        redisTemplate.opsForZSet().add(AccountModRedisKey.MOD_KEY + key, value, time);
    }

    @Override
    public void delAccountCollection(String key, String value) {
        redisTemplate.opsForZSet().remove(AccountModRedisKey.MOD_KEY + key, value);
    }

    @Override
    public Set<String> getAccountCollection(String key) {
        Set<String> range = redisTemplate.opsForZSet().range(AccountModRedisKey.MOD_KEY + key, 0, -1);
        return range;
    }

    @Override
    public boolean isAccountCollectionValue(String key, String value) {
        Long score = redisTemplate.opsForZSet().rank(AccountModRedisKey.MOD_KEY + key, value);
        if (score == null) {
            return false;
        }
        return true;
    }
}
