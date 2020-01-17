package com.gsh.springbootquick.config;

import com.gsh.springbootquick.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Create By GSH on .
 * @Configuration: 指明当前类是一个配置类；代替Spring配置文件
 * xml中用<bean></bean>标签添加组件
 */
@Configuration
public class MyConfig {

    //将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService(){
        System.out.println("配置类@bean给容器中添加组件成功");
        return new HelloService();
    }

    //自定义视图解析器
    @Bean
    public ViewResolver MyViewResolver(){
        return new MyViewResolver();
    }

    public static class MyViewResolver implements ViewResolver {

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}
