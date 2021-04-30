package com.lx.myself.config;

import com.lx.myself.tools.MySessionContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * login Interceptor
 *
 * @author Administrator
 * @date 2021/04/09 23:35
 **/
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        HashMap<String,Object> map=new HashMap<String,Object>();
        String queryStrings = request.getQueryString();
        if (queryStrings!=null&&queryStrings!=""){
            String[] splits = queryStrings.split("&");
            for (String queryString :splits){
                String[] split = queryString.split("=");
                for (int i=0;i<split.length;i++){
                    if ("sessionId".equals(split[i])){
                        map.put("sessionId",split[i+1]);
                    }
                }
            }
            String sessionId= (String) map.get("sessionId");
            if (sessionId!=null&&sessionId!=""){
                HttpSession session = MySessionContext.getSession(sessionId);
                request.getSession().setAttribute("loginUser",session.getAttribute("loginUser"));
                MySessionContext.removeSession(session);
            }
        }
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser==null){
            response.sendRedirect("http://localhost:8081/login.html");
            return false;
        }
        return true;
    }

}
