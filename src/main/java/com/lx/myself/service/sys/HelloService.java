package com.lx.myself.service.sys;

import com.lx.myself.pojo.sys.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @date 2021/03/31 13:38
 **/
public class HelloService {




    @Resource
    @Qualifier("redisTemplate")
    public static RedisTemplate<String,Object> redisTemplate;

    @Test
    public void contextLoads() {
        SysUser sysUser=new SysUser();

        sysUser.setId("sioned");
        sysUser.setName("刘旋");
        sysUser.setUserName("boss");
        redisTemplate.opsForValue().set("user",sysUser);
    }


}
