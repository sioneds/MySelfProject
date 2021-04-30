package com.lx.myself.tools;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类*/
@Component
public  class RedisTools {

    static RedisTemplate redisTemplate;
    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisTools.redisTemplate = redisTemplate;
    }

    /**
     * @author sioned
     * @date 2021/04/30 22:30
     * @Description save Hash
     */
    public static void saveHash(String key,String HashKey,Object object){
        redisTemplate.opsForHash().put(key,HashKey,object);
    }
}
