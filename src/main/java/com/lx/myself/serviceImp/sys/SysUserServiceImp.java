package com.lx.myself.serviceImp.sys;

import com.lx.myself.mapper.sys.SysLoginRecordMapper;
import com.lx.myself.mapper.sys.SysPermissionsMapper;
import com.lx.myself.mapper.sys.SysRoleMapper;
import com.lx.myself.mapper.sys.SysUserMapper;
import com.lx.myself.pojo.sys.*;
import com.lx.myself.service.sys.SysLoginRecordService;
import com.lx.myself.service.sys.SysUserService;
import com.lx.myself.tools.RedisTools;
import com.lx.myself.tools.StringTools;
import com.lx.myself.tools.http.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Service
public class SysUserServiceImp implements SysUserService {

    @Resource
    public SysUserMapper sysUserMapper;

    @Resource
    public SysLoginRecordService sysLoginRecordServiceImp;

    @Resource
    public SysRoleMapper sysRoleMapper;

    @Resource
    public SysPermissionsMapper sysPermissionsMapper;

    @Resource
    public RedisTools redisTools;

    HashMap<String,Object> map =new HashMap<String,Object>();

    /**
     * @author sioned
     * @date 2021/03/25 13:56
     * @Description user sgin in
     */
    @Override
    public ResultCode userLogin(HttpServletRequest request, HttpServletResponse response, String rememberMe, String name, String password, String cip) {
        map.clear();
        List<SysUser>  sysUserList= sysUserMapper.userLogin(name,password);
        if (sysUserList==null||sysUserList.size()==0){
            //username is defind
            return ResultCode.NOTFIND_USER;
        }
        if (!sysUserList.get(0).getPassword().equals(password)){
            //password is error
            return ResultCode.ERROR_PASSWORD;
        }
        if (sysUserList.get(0).getState()!=1){
            //state is abnormal
            return ResultCode.STATE_ABNORMAL;
        }
        Date date= new Date();
        SysUser sysUser=sysUserList.get(0);
        sysUserMapper.updateLastTimeAndIp(cip, date,sysUser.getId());
        String uuid=StringTools.getUUID32();
        sysLoginRecordServiceImp.insertSysLoginRecord(new SysLoginRecord(
                uuid,
                sysUser.getId(),
                sysUser.getUserName(),
                sysUser.getName(),
                new Date(),
                cip));
        request.getSession().setAttribute("loginUser",sysUser);
        //success
        return ResultCode.SUCCESS;
    }




    /**
     * @author sioned
     * @date 2021/04/25 18:56
     * @Description get user by userMap
     */
    @Override
    public SysUser getUserByName(String userName){
        //get all permissions
        Set<SysPermissions> permissionsAll = sysPermissionsMapper.getPermissionsAll();
        //get all permissionsMiddle
        Set<SysPermissionsMiddle> permissionsMiddleAll = sysPermissionsMapper.getPermissionsMiddleAll();
        //get user by name
        SysUser sysUser = sysUserMapper.getUserByName(userName);
        //get roles by userid
        Set<SysRole> roles = sysRoleMapper.getRolesByUserId(sysUser.getId());
        //by means of for() gain roles
        for (SysRole sysRole : roles){
            Set<SysPermissions> sysPermissions=new HashSet<>();
            String id = sysRole.getId();
            for (SysPermissionsMiddle sysPermissionsMiddle:permissionsMiddleAll){
                String permissionsId = sysPermissionsMiddle.getPermissionsId();
                String roleId = sysPermissionsMiddle.getRoleId();
                if (roleId.equals(id)){
                    for (SysPermissions sysPermission : permissionsAll){
                            if (permissionsId.equals(sysPermission.getId())){
                                sysPermissions.add(sysPermission);
                            }
                    }
                }
            }
        }
        sysUser.setRoles(roles);
        return sysUser;
    }
}
