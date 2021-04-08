package com.lx.myself.service.sys;

import com.lx.myself.pojo.sys.SysLoginRecord;

import javax.annotation.Resource;

@Resource
public interface SysLoginRecordService {
    /**
     * @author sioned
     * @date 2021/03/25 13:52
     * @Description insert sign in record
     */
    void insertSysLoginRecord(SysLoginRecord sysLoginRecord);
}
