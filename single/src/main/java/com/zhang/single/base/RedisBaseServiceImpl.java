package com.zhang.single.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Objects;

/**
 * @description: redis基础功能
 * @author: zhangFanJun
 * @create: 2021-09-01 23:00
 **/
@Slf4j
@Component
public class RedisBaseServiceImpl {

    @Autowired
    private JedisPool jedisPool;

    /**
     * key-value保存一个字符串
     * */
    public boolean setString(String key,String value){
        String result = jedisPool.getResource().set(key, value);
        if ("OK".equalsIgnoreCase(result)){
            return true;
        }
        return false;
    }

    /**
     * nx key不存在的时候才能成功，可以用于分布式锁
     * xx key存在才能成功
     * */
    public boolean setnx(String key ,String value){
        Long result = jedisPool.getResource().setnx(key, value);
        if(Objects.nonNull(result)&&result>0){
            return true;
        }
        return false;
    }

    public boolean setStringExpire(String key,String value,long seconds){
        String result = jedisPool.getResource().setex(key, seconds, value);
        if ("OK".equalsIgnoreCase(result)){
            return true;
        }
        return false;
    }

    public boolean msetString(String...keyValues){
        String result = jedisPool.getResource().mset(keyValues);
        if ("OK".equalsIgnoreCase(result)){
            return true;
        }
        return false;
    }

    public String getString(String key){
        String result = jedisPool.getResource().get(key);
        return result;
    }

    /**
     * 批量操作
     * */
    public List<String> mgetString(String...keys){
        List<String> results = jedisPool.getResource().mget(keys);
        return results;
    }

    /**
     * 如果key不存在就按照0计算，返回1
     * 如果value是数字，就增加1
     * 如果value不是数字，报错
     * */
    public Long incr(String key){
        try {
            Long result = jedisPool.getResource().incr(key);
            return result;
        } catch (Exception e){
            log.error("incr:{},",key,e);
            return null;
        }
    }

    /**
     * 根据入参加1
     * */
    public Long incrBy(String key,Long num){
        Long result = jedisPool.getResource().incrBy(key, num);
        return result;
    }

    /**
     * 如果key不存在就按照0计算，返回1
     * 如果value是数字，就增加1
     * 如果value不是数字，报错
     * */
    public Long decr(String key){
        try {
            Long result = jedisPool.getResource().decr(key);
            return result;
        } catch (Exception e){
            log.error("incr:{},",key,e);
            return null;
        }
    }

    public Long decrBy(String key,Long num){
        Long result = jedisPool.getResource().decrBy(key, num);
        return result;
    }

    /**
     * 在原来的基础上拼接字符串
     * */
    public Long append(String key,String append){
        Long result = jedisPool.getResource().append(key, append);
        return result;
    }

    /**
     * 返回字符串的长度
     * */
    public Long strlen(String key){
        Long result = jedisPool.getResource().strlen(key);
        return result;
    }

    /**
     * 在set的基础上，将原来的值返回
     * */
    public String getset(String key,String value){
        String old = jedisPool.getResource().getSet(key, value);
        return old;
    }



}
