package com.lx.myself.pojo.sys;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
/**
 * role
 *
 * @author Administrator
 * @date 2021/04/25 18:31
 **/


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sys_role")
@Component
public class SysRole {
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, isKey = true)
    private String id;
    @Column(name = "role_name", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<SysPermissions> permissions;
}