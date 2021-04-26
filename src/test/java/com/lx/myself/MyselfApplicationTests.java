package com.lx.myself;

import com.lx.myself.pojo.sys.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


@SpringBootTest
public class MyselfApplicationTests {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Test
    public void test1(){
        redisTemplate.opsForList().set("1",0,"111");
    }



}
