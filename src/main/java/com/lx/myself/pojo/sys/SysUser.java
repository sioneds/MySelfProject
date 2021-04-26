package com.lx.myself.pojo.sys;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author sioned
 * @date 2021/03/25 13:52
 * @Description user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sys_user")
@Component
public class SysUser implements Serializable {
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, isKey = true)
    private String id;
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String name;
    @Column(name = "user_name", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false, isUnique = true)
    private String userName;
    @Column(name = "password", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String password;
    @Column(name = "email", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String email;
    @Column(name = "sex", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String sex;//1 is boy 2 is girl
    @Column(name = "is_admin", type = MySqlTypeConstant.INT, isNull = false)
    private Integer isAdmin;//1 is true 2 is false
    @Column(name = "login_time", type = MySqlTypeConstant.DATETIME)
    private Date loginTime;
    @Column(name = "sign_in_time", type = MySqlTypeConstant.DATETIME)
    private Date signInTime;
    @Column(name = "last_ip", type = MySqlTypeConstant.VARCHAR, length = 64)
    private String lastIp;
    @Column(name = "state", type = MySqlTypeConstant.INT, isNull = false)
    private Integer state;//1 is normal 2 is freeze 3 is ban
    @Column(name = "profile_photo", type = MySqlTypeConstant.VARCHAR, length = 255)
    private String profilePhoto;
    private Set<SysRole> roles;
}
