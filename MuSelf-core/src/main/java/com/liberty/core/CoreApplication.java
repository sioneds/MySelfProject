package com.liberty.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.Resource;
import javax.sql.DataSource;
@Slf4j
@SpringBootApplication
public class CoreApplication extends SpringBootServletInitializer {

    private static DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
        try {
            log.info("**************************************************************************************");
            log.info("*********项目启动成功({})", dataSource.getConnection().getMetaData().getURL()+"*********");
            log.info("**************************************************************************************");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
    public static DataSource getDataSource() {
        return dataSource;
    }
    @Resource
    public void setDataSource(DataSource dataSource) {
        CoreApplication.dataSource = dataSource;
    }
}
