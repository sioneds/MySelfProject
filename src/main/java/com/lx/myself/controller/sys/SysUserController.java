package com.lx.myself.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.lx.myself.mapper.sys.SysUserMapper;
import com.lx.myself.tools.Encode64;
import com.lx.myself.pojo.sys.SysUser;
import com.lx.myself.service.sys.SysUserService;
import com.lx.myself.tools.MySessionContext;
import com.lx.myself.tools.http.ResultCode;
import com.lx.myself.tools.http.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("/sys")
@RestController
public class SysUserController {
    @Resource
    public SysUserService sysUserServiceImp;

    @Resource
    public SysUserMapper sysUserMapper;

    public Map<String,Object> resultMap=new LinkedHashMap();
    /**
     * @author sioned
     * @date 2021/03/25 13:54
     * @Description user sgin in
     */
    @PostMapping("/userLogin")
    public ResponseData userLogin(SysUser user, String rememberMe, String cip, Model model, HttpServletResponse response, HttpServletRequest request, @RequestParam(name = "l",required = false)String l){
        user.setPassword(Encode64.getEncode64(user.getPassword()));
        //cip user IP and address
        if (cip.isEmpty()){
            cip="数据异常，暂未获取到";
            return ResponseData.custom(ResultCode.NETWORK_ANOMALY);
        }
        ResultCode resultCode=sysUserServiceImp.userLogin(request,response,rememberMe,user.getUserName(),user.getPassword(),cip);
        String id = request.getSession().getId();
        MySessionContext.addSession(request.getSession());
        resultMap.put("sessionId",id);
        return ResponseData.custom(resultCode,resultMap);
    }

    /**
     * @author sioned
     * @date 2021/05/01 13:04
     * @Description get user
     */
    @PostMapping("/getUser")
    public SysUser getUser(HttpServletRequest request,HttpServletResponse response,String cip){
        String userByRedis = (String) sysUserServiceImp.getUserByRedis(cip);
        JSONObject jsonobject = JSONObject.parseObject(userByRedis);
        SysUser sysUser=jsonobject.toJavaObject(SysUser.class);
        return sysUser;
    }
    /**
     * @author sioned
     * @date 2021/05/06 15:14
     * @Description get user count
     */
    @PostMapping("/getUserCount")
    public ResponseData getUserCount(){
        resultMap.put("getUserCount",sysUserMapper.getUserCount());
        return ResponseData.success(resultMap);
    }
}
