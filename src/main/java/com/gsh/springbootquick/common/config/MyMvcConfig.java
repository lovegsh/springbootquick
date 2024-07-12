package com.gsh.springbootquick.common.config;

import com.gsh.springbootquick.common.component.MyLocaleResolver;
import com.gsh.springbootquick.common.component.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Create By GSH on .
 */
//@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Autowired
    public MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //视图解析，发送/succeed请求来到success
        registry.addViewController("/succeed").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
        registry.addViewController("/testModel").setViewName("testModelAttribute");
        registry.addViewController("/404").setViewName("404");
    }

    //添加静态资源文件，外部可以直接访问地址  springboot2.0静态资源需要处理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //如下配置则能可以访问src/main/resources/static下面的文件,
        //如访问static文件夹下的XX.css，则输入：localhost:8080/static/asserts/css/bootstrap.min.css 即可访问
        //注意   registry.addResourceHandler("/static/**")配置得是静态资源访问路径，访问时必须将该路径添加进去
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        //        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


    //springboot1.0 embeddedServerContainCustomizer 在springboot2.0中被 WebServerFactoryCustomizer 代替
    //配置嵌入式Servlet容器
//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                factory.setPort(8083);
//            }
//        };
//    }

    //注册登录拦截器，springboot1.0静态资源不用处理
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html", "/", "/user/login", "/static/**", "/druid");
//    }


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



