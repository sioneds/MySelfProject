package com.lx.myself.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * DruidConfig
 *
 * @author Administrator
 * @date 2021/03/26 14:24
 **/
@Configuration
@PropertySource(value = {"classpath:config/druid.yml"})
public class DruidConfig {

    @ConfigurationProperties(prefix = "druid")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        //get druid background monitor
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        // These parameters can be found in com.alibaba.druid.support.http.StatViewServlet
        // parent com.alibaba.druid.support.http.ResourceServlet
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "boss"); //background monitor username
        initParams.put("loginPassword", "boss"); //background monitor password


        //initParams.put("allow", "localhost")：only localhost can access
        //initParams.put("allow", "")：if value is null ,everyone can access
        initParams.put("allow", "");
        //can not access key:name ，value：ip
        //initParams.put("hehe", "192.168.1.20");192.168.1.20 can not access

        // init parameters
        bean.setInitParameters(initParams);
        return bean;
    }

    //配置 Druid 监控 之  web 监控的 filter
    //WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //exclusions：Those requests not need filter
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,*.woff,*.woff2,*.jpg,*.png,*.ico,*.svg,/druid/*,/jdbc/*");
        bean.setInitParameters(initParams);

        //"/*" filter all
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
