package com.epam.hackathongood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caojiantao
 * @mail: jtcao2@iflytek.com
 * @description: swagger配置类
 * @date 2018/10/30 17:18
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket swaggerPlugin() {
        List<Parameter> parameters = new ArrayList<>();
        Parameter token = new ParameterBuilder()
                .name("X-Token")
                .description("登陆令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .build();
        parameters.add(token);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.epam.hackathongood.controller"))
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger入门示例")
                .description("一些借口描述")
                .version("1.0")
                .build();
    }
}
