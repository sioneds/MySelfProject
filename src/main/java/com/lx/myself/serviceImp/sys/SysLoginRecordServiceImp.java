package com.lx.myself.serviceImp.sys;

import com.lx.myself.mapper.sys.SysLoginRecordMapper;
import com.lx.myself.pojo.sys.SysLoginRecord;
import com.lx.myself.service.sys.SysLoginRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLoginRecordServiceImp implements SysLoginRecordService {
    @Resource
    SysLoginRecordMapper sysLoginRecordMapper;
    /**
     * @author sioned
     * @date 2021/03/25 13:52
     * @Description insert sign in record
     */
    @Override
    public void insertSysLoginRecord(SysLoginRecord sysLoginRecord) {
        sysLoginRecordMapper.insertSysLoginRecord(sysLoginRecord);
    }
}
