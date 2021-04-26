package com.lx.myself.pojo.sys;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * sys_role_middle
 *
 * @author Administrator
 * @date 2021/04/25 22:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sys_role_middle")
@Component
public class SysRoleMiddle {
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, isKey = true)
    private String id;
    @Column(name = "user_id", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String userId;
    @Column(name = "role_id", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String roleId;
}
