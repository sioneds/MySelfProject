package com.lx.myself.mapper.sys;

import com.lx.myself.pojo.sys.SysPermissions;
import com.lx.myself.pojo.sys.SysPermissionsMiddle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;
@Mapper
public interface SysPermissionsMapper {
    /**
     * @author sioned
     * @date 2021/04/25 22:33
     * @Description get Permissions by role_id
     */
    public Set<SysPermissions> getPermissionsByRoleId(String id);
    /**
     * @author sioned
     * @date 2021/04/26 01:06
     * @Description get PermissionsMiddle all
     */
    public Set<SysPermissionsMiddle> getPermissionsMiddleAll();
    /**
     * @author sioned
     * @date 2021/04/26 01:06
     * @Description get SysPermissions all
     */
    public Set<SysPermissions> getPermissionsAll();
}
