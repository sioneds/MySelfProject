package com.lx.myself.config;

import com.lx.myself.pojo.sys.SysUser;
import com.lx.myself.service.sys.SysUserService;
import com.lx.myself.tools.MySessionContext;
import com.lx.myself.tools.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * login Interceptor
 *
 * @author Administrator
 * @date 2021/04/09 23:35
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTools redisTools;
    @Resource
    public SysUserService SysUserServiceImp;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        HashMap<String,Object> map=new HashMap<String,Object>();
        String queryStrings = request.getQueryString();
        if (queryStrings!=null&&queryStrings!=""){
            try {
                queryStrings= URLDecoder.decode(request.getQueryString(),"utf-8");//将中文转码
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String[] splits = queryStrings.split("&");
            for (String queryString :splits){
                String[] split = queryString.split("=");
                for (int i=0;i<split.length;i++){
                    if ("cip".equals(split[i])){
                        map.put("cip",split[i+1]);
                    }
                }
            }
            String cip= (String) map.get("cip");
            Object sysUser =  redisTools.getHash(cip, "cip");
            if (sysUser==null){
                response.sendRedirect("http://localhost:8081/login.html");
                return false;
            }
        }else{
                response.sendRedirect("http://localhost:8081/login.html");
                return false;
        }
        return true;
    }

}
