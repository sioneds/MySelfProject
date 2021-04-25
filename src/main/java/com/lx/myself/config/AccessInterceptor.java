package com.lx.myself.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决跨域问题
 *
 * @author lx
 * @date 2021/04/07 17:38
 **/
@Configuration
public class AccessInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        // 设置：Access-Control-Allow-Origin头，处理Session问题
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("P3P", "CP=CAO PSA OUR");
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
            response.addHeader("Access-Control-Max-Age", "120");
        }
        //实现免登录
        HttpServletRequest req = (HttpServletRequest) request;
        //获取所的有cookie
        Cookie[] cs = req.getCookies();
        if(cs!=null){
            for(Cookie c:cs){
                if(c.getName().equals("autoLogin")){//如果存在自动登录的cookie
                    String value = c.getValue();//用户名称
                    //登录成功是指
                    req.getSession().setAttribute("name", value);
                    response.sendRedirect("http://localhost:8081/static/master/index.html");
                }
            }
        }

        return true;
    }

}
