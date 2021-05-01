package com.lx.myself.tools;



import com.alibaba.fastjson.JSONObject;
import com.lx.myself.pojo.sys.SysUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    }

    /**
     * @author sioned
     * @date 2021/05/01 13:59
     * @Description get Hash
     */
    public static Object getHash(String key, String HashKey){
        return redisTemplate.opsForHash().get(key,HashKey);
    }
}
