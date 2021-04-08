package com.lx.myself.serviceImp.sys;

import com.lx.myself.mapper.sys.SysLoginRecordMapper;
import com.lx.myself.mapper.sys.SysUserMapper;
import com.lx.myself.pojo.sys.SysLoginRecord;
import com.lx.myself.pojo.sys.SysUser;
import com.lx.myself.service.sys.SysLoginRecordService;
import com.lx.myself.service.sys.SysUserService;
import com.lx.myself.tools.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class SysUserServiceImp implements SysUserService {
    @Resource
    public SysUserMapper sysUserMapper;
    @Resource
    public SysLoginRecordService sysLoginRecordServiceImp;

    /**
     * @author sioned
     * @date 2021/03/25 13:56
     * @Description user sgin in
     */
    @Override
    public boolean userLogin(String name, String password,String cip) {
        List<SysUser>  sysUserList= sysUserMapper.userLogin(name,password);
        boolean flag=false;
        if (sysUserList!=null&&sysUserList.size()>0){
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
            flag=true;

        }
        return flag;
    }
}
