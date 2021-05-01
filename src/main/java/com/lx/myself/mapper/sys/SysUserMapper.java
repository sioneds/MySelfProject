package com.lx.myself.mapper.sys;


import com.lx.myself.pojo.sys.SysRole;
import com.lx.myself.pojo.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Mapper
public interface SysUserMapper {
    /**
     * @author sioned
     * @date 2021/04/25 19:00
     * @Description user sign in
     */
    List<SysUser> userLogin(String name, String password);
    /**
     * @author sioned
     * @date 2021/04/25 19:01
     * @Description update last time and ip with sign in is success
     */
    int updateLastTimeAndIp(String cip, Date date,String id);
    /**
     * @author sioned
     * @date 2021/04/25 19:03
     * @Description get user by name
     */
    SysUser getUserByName(String userName);
    /**
     * @author sioned
     * @date 2021/04/25 19:03
     * @Description get user by lastIp
     */
    SysUser getUserByCip(String lastIp);
}
