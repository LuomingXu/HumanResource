package com.qst.human_resources.handles;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置额外的资源文件配置与springBoot配置
 */

@Configuration
public class myWebMvcConfigurerAdapter implements WebMvcConfigurer {

    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/");
        //访问外部资源
//        registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/");
    }

    /**
     * 配置通过url跳转到页面,不通过controller跳转
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login");
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器,排除LoginTo/toLogin
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/toCreateUser","/createUser");
    }


}