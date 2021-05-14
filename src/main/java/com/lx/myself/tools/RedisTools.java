package com.lx.myself.tools;



import com.alibaba.fastjson.JSONObject;
import com.lx.myself.pojo.sys.SysUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类*/
@Component
public  class RedisTools {

    static RedisTemplate<String, Object>  redisTemplate;
    @Resource(name = "myRedisTemplate")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisTools.redisTemplate = redisTemplate;
    }

    /**
     * @author sioned
     * @date 2021/04/30 22:30
     * @Description save Hash
     */
    public static void saveHash(String key,String HashKey,SysUser sysUser){
        sysUser.setPassword("");
        JSONObject jsonObject= (JSONObject) JSONObject.toJSON(sysUser);
        redisTemplate.opsForHash().put(key,HashKey,jsonObject.toJSONString());
        setTempTime(key);
    }

    /**
     * @author sioned
     * @date 2021/05/01 13:59
     * @Description get Hash
     */
    public static Object getHash(String key, String HashKey){
        setTempTime(key);
        return redisTemplate.opsForHash().get(key,HashKey);
    }

    /**
     * @author sioned
     * @date 2021/05/01 20:12
     * @Description set tempTime by key
     */
    public static void setTempTime(String key){
        redisTemplate.expire(key,5, TimeUnit.MINUTES);
    }

    /**
     * @author sioned
     * @date 2021/05/06 14:07
     * @Description INCR  value
     */
    public static Integer incrValue(String path){
        Integer increment = Math.toIntExact(redisTemplate.opsForValue().increment(path));
        return increment;
    }

    /**
     * @author 刘旋
     * @date 2021/05/13 17:20
     * @Description set value
     */
    public static void setValue(String key,Object object){
        redisTemplate.opsForValue().set(key,object);
    }

    /**
     * @author 刘旋
     * @date 2021/05/13 17:23
     * @Description get value
     */
    public static Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
