package com.gsh.springbootquick.config;

import com.gsh.springbootquick.component.LoginHandleInterceptor;
import com.gsh.springbootquick.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Create By GSH on .
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //视图解析，发送/succeed请求来到success
        registry.addViewController("/succeed").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //注册拦截器，静态资源不用处理
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
    //所有的WebMvcConfigurer组件都会一起起作用
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("login");
//                registry.addViewController("/index.html").setViewName("login");
//            }
//        };
//        return webMvcConfigurer;
//    }


}


//弃用了
//public class MyMvcConfig extends WebMvcConfigurerAdapter {
//}


