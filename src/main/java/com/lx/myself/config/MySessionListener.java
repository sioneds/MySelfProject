package com.lx.myself.config;

import com.lx.myself.tools.MySessionContext;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * MySessionListener
 *
 * @author Administrator
 * @date 2021/04/11 17:25
 **/
@WebListener
public class MySessionListener implements HttpSessionListener {
    public void sessionCreate(HttpSessionEvent sessionEvent) {
        System.out.println("create session‘s id is"+sessionEvent.getSession().getId());
        MySessionContext.addSession(sessionEvent.getSession());
    }

    public void sessionDestroy(HttpSessionEvent sessionEvent) {
        System.out.println("destroy session‘s id is"+sessionEvent.getSession().getId());
        MySessionContext.removeSession(sessionEvent.getSession());
    }
}
