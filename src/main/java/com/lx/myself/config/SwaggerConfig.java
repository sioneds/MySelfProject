package com.lx.myself.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * swagger config
 *
 * @author Administrator
 * @date 2021/03/29 15:30
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    default path : http://localhost:8092/swagger-ui.html
    @Bean
    public Docket docket(Environment environment){
        //configure the environment--环境 to use
//        Profiles profile=Profiles.of("dev","test");
//        //judge--判断 profile is use?
//        boolean flag=environment.acceptsProfiles(profile);
        boolean flag=true;
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                //RequestHandlerSelectors //Configure how to scan
                //basepackage //Specifies--指定 the package to scan
                //any() scan all
                //none() nothing
                //withClassAnnotation scan class with annotation
                //withMethodAnnotation scan method with annotation
                .apis(RequestHandlerSelectors.basePackage("com.lx.myself.controller"))
                //filter
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfo(
                "mySelf Api Document",
                "Swagger Api Document",
                "1.0",
                "http://localhost:8091",
                new Contact("sioned", "http://localhost:8091", "852271455@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
