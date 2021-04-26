package com.lx.myself.mapper.sys;

import com.lx.myself.pojo.sys.SysLoginRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysLoginRecordMapper {
    /**
     * @author sioned
     * @date 2021/04/25 18:59
     * @Description insert login record with sign in is success
     */
    void insertSysLoginRecord(@Param("sysLoginRecord") SysLoginRecord sysLoginRecord);
}
