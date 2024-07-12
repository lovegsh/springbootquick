//package com.gsh.springbootquick.config;
//
//import com.gsh.springbootquick.servlet.MyServlet;
//import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
//import org.springframework.boot.web.server.ConfigurableWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.print.event.PrintJobListener;
//import javax.servlet.Servlet;
//
///**
// * Create By GSH on .
// */
//@Configuration
//public class MyServerConfig {
//
//    //注册三大组件
//    @Bean
//    public ServletRegistrationBean myServlet(){
//        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
//        return registrationBean;
//    }
//
//    //配置嵌入式Servlet容器
//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                factory.setPort(8083);
//            }
//        };
//    }
//}
