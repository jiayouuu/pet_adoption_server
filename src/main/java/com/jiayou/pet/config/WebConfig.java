/*
 * @Author: jiayouuu 3010336955@qq.com
 * @Date: 2024-01-10 10:36:34
 * @LastEditors: jiayouuu 3010336955@qq.com
 * @LastEditTime: 2025-03-19 22:19:56
 * @FilePath: /pet-backend/src/main/java/com/jiayou/pet/config/WebConfig.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.jiayou.pet.config;

import com.jiayou.pet.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web配置
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register", "/**/export", "/**/import", "/file/**")
                .excludePathPatterns("/swagger**/**", "/webjars/**", "/v3/**", "/doc.html");
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}