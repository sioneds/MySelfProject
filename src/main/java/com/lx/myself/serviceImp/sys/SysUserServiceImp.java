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

    HashMap<String,Object> map =new HashMap<String,Object>();

    /**
     * @author sioned
     * @date 2021/03/25 13:56
     * @Description user sgin in
     */
    @Override
    public HashMap<String,Object> userLogin(HttpServletRequest request, HttpServletResponse response, String rememberMe, String name, String password, String cip) {
        map.clear();

        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                name,
                password
        );
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            map.put("ResultCode",ResultCode.NOTFIND_USER);
            return map;
        } catch (IncorrectCredentialsException e) {
            map.put("ResultCode",ResultCode.ERROR_PASSWORD);
            return map;
        } catch (Exception e) {
            map.put("ResultCode",ResultCode.STATE_ABNORMAL);
            return map;
        }
        map.put("ResultCode",ResultCode.SUCCESS);

        /*HashMap<String,Object> map =new HashMap<String,Object>();//store ResultCode and session id
        List<SysUser>  sysUserList= sysUserMapper.userLogin(name,password);
        map.put("sessionId",request.getSession().getId());
        if (sysUserList==null||sysUserList.size()==0){
            //username is defind
            map.put("ResultCode",ResultCode.NOTFIND_USER);
            return map;
        }
        if (!sysUserList.get(0).getPassword().equals(password)){
            //password is error
            map.put("ResultCode",ResultCode.ERROR_PASSWORD);
            return map;
        }
        if (sysUserList.get(0).getState()!=1){
            //state is abnormal
            map.put("ResultCode",ResultCode.STATE_ABNORMAL);
            return map;
        }
        Date date= new Date();
        SysUser sysUser=sysUserList.get(0);
        int i = sysUserMapper.updateLastTimeAndIp(cip, date,sysUser.getId());
        String uuid=StringTools.getUUID32();
        sysLoginRecordServiceImp.insertSysLoginRecord(new SysLoginRecord(
                uuid,
                sysUser.getId(),
                sysUser.getUserName(),
                sysUser.getName(),
                new Date(),
                cip));
//        if ("rememberMe".equals(rememberMe)){
//            int seconds = 60*60*24;
//            //声明cookie
//            Cookie c = new Cookie("autoLogin",sysUser.getUserName());
//            c.setMaxAge(seconds);
//            //保存cookie
//            response.addCookie(c);
//        }
        request.getSession().setAttribute("loginUser",sysUser);
        //success
        map.put("ResultCode",ResultCode.SUCCESS);*/
        return map;
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
