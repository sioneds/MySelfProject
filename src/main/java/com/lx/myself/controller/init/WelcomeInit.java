package com.lx.myself.controller.init;

import com.lx.myself.tools.RedisTools;
import com.lx.myself.tools.http.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/05/06 14:02
 **/
@RequestMapping("/init")
@RestController
public class WelcomeInit {
    @Resource
    RedisTools redisTools;
    Map<String,Object> resultMap=new HashMap<>();
    /**
     * @author sioned
     * @date 2021/05/06 14:04
     * @Description get browse num
     */
    @PostMapping("/browseNum")
    public ResponseData browseNum(String path){
        resultMap.put("browseNum",redisTools.incrValue(path));
        return ResponseData.success(resultMap);
    }
}
