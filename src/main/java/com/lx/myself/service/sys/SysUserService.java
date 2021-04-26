package com.lx.myself.service.sys;


import com.lx.myself.pojo.sys.SysUser;
import com.lx.myself.tools.http.ResultCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


public interface SysUserService {
    /**
     * @author sioned
     * @date 2021/03/25 13:52
     * @Description  user sign in
     */
    public HashMap<String,Object> userLogin(HttpServletRequest request, HttpServletResponse response, String rememberMe, String name, String password, String cip);
    /**
     * @author sioned
     * @date 2021/04/25 18:56
     * @Description get user by userMap
     */
    public SysUser getUserByName(String getMapByName);
}
