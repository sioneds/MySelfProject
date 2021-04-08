package com.lx.myself.mapper.sys;

import com.lx.myself.pojo.sys.SysLoginRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysLoginRecordMapper {

    void insertSysLoginRecord(@Param("sysLoginRecord") SysLoginRecord sysLoginRecord);
}
