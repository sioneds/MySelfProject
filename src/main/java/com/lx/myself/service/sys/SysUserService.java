package com.lx.myself.service.sys;


import com.lx.myself.pojo.sys.SysUser;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface SysUserService {
    /**
     * @author sioned
     * @date 2021/03/25 13:52
     * @Description  user sign in
     */
    public boolean userLogin(String name, String password,String cip);
}
