//package com.gsh.springbootquick.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
///**
// * swagger2
// * @author GSH
// * @create 2022/1/7 14:52
// */
//@Configuration
//@EnableOpenApi
//public class SwaggerConfig {
//
//    public static final String VERSION = "1.0.0";
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("swagger2") //设置文档的标题
//                .description("API接口文档") // 设置文档的描述
//                .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
//                .contact(new Contact("gg", "http://gg.com", "gg@qq.com"))
//                .termsOfServiceUrl("http://www.apache.org/licenses/LICENSE-2.0") // 设置文档的License信息->1.3 License information
//                .build();
//    }
//}
