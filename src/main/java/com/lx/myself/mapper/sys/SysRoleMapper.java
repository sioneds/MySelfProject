package com.lx.myself.mapper.sys;

import com.lx.myself.pojo.sys.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;


@Mapper
public interface SysRoleMapper {
    /**
     * @author sioned
     * @date 2021/04/25 22:23
     * @Description get roles with userid
     */
    Set<SysRole> getRolesByUserId(String id);
}
