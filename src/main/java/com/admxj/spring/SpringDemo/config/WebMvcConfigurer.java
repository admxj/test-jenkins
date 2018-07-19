package com.admxj.spring.SpringDemo.config;

import com.admxj.spring.SpringDemo.interceptor.OneInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private OneInterceptor oneInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(oneInterceptor).addPathPatterns("/one/**");
        super.addInterceptors(registry);
    }
}
