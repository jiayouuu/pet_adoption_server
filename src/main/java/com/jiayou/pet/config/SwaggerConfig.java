package com.jiayou.pet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI配置
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI createOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("接口文档")
                        .description("Restful 接口")
                        .version("1.0")
                        .contact(new Contact()
                                .name("xxx")
                                .url("#")
                                .email("xxx@qq.com")));
    }
}
