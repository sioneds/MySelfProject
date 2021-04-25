package com.lx.myself.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * my redirect
 *
 * @author Administrator
 * @date 2021/04/11 19:30
 **/
@Controller
public class MyRedirect {

    /**
     * @author sioned
     * @date 2021/04/11 19:33
     * @Description do to index
     */
    @RequestMapping("/toIndex")
    public void toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8081/static/master/index.html");
    }
}
