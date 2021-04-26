package com.lx.myself.pojo.sys;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Permissions
 *
 * @author Administrator
 * @date 2021/04/25 18:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sys_permissions")
@Component
public class SysPermissions {
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, isKey = true)
    private String id;
    @Column(name = "permissions_name", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String permissionsName;
}