package com.lx.myself.controller.sys;

import com.lx.myself.tools.Encode64;
import com.lx.myself.pojo.sys.SysUser;
import com.lx.myself.service.sys.SysUserService;
import com.lx.myself.tools.http.ResultCode;
import com.lx.myself.tools.http.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("")
@Controller
public class SysUserController {
    @Resource
    public SysUserService SysUserServiceImp;
    /**
     * @author sioned
     * @date 2021/03/25 13:54
     * @Description user sgin in
     */
    @ResponseBody
    @RequestMapping("/sys/userLogin")
    public Object userLogin(SysUser user, String cip, Model model,HttpServletRequest request,@RequestParam(name = "l",required = false)String l){
        user.setPassword(Encode64.getEncode64(user.getPassword()));
        //cip user IP and address
        if (cip.isEmpty()){
            cip="数据异常，暂未获取到";
        }
        ResultCode resultCode=SysUserServiceImp.userLogin(user.getUserName(),user.getPassword(),cip);
        return ResponseData.custom(resultCode,null);
    }
}
