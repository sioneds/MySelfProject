package com.lx.myself.pojo.sys;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import netscape.security.UserTarget;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author sioned
 * @date 2021/03/25 13:52
 * @Description sign in record
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sys_login_record")
@Component
public class SysLoginRecord {
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, isKey = true)
    private String id;
    @Column(name = "user_id", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String userId;
    @Column(name = "user_name", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String userName;
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 64, isNull = false)
    private String name;
    @Column(name = "login_time", type = MySqlTypeConstant.DATETIME)
    private Date loginTime;
    @Column(name = "cip", type = MySqlTypeConstant.VARCHAR, length = 64)
    private String cip;

}
