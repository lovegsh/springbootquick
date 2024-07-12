package com.gsh.springbootquick.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author GSH
 * @create 2023/1/19 16:13
 */
//@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    private final String adminContextPath;

//    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
//        this.adminContextPath = adminServerProperties.getContextPath();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll()  // 虽都可以访问
                .antMatchers("/users/**").hasRole("USER")   // 需要相应的角色才能访问
                .antMatchers("/admins/**").hasRole("ADMIN")   // 需要相应的角色才能访问
                .and()
            .formLogin()   //基于 Form 表单登录验证
                .disable();
//                .loginPage("/login")
//                .loginProcessingUrl("/user/login")
//                .failureUrl("/404");  // 自定义登录界面
    }

    /**
     * 用户信息服务
     */
//    @Bean
//    public UserDetailsService userDetailsServiceBean() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); // 在内存中存放用户信息
//        manager.createUser(User.withUsername("waylau").password("123456").role("USER").build());
//        manager.createUser(User.withUsername("admin").password("123456").role("ADMIN").role("USER").build());
//        return manager;
//    }
    /**
     * 认证信息管理
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter( "redirectTo" );
//        http.authorizeRequests()
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated().and()
//                .formLogin()          //自定义登录页面
//                .loginPage("/login" )
//                //            	.loginProcessingUrl("/user/login")  //登录访问路径
//                //            	.defaultSuccessUrl("/index").permitAll()   //登录成功后跳转路径
//                .successHandler( successHandler ).and()
//                .logout()
//                .logoutUrl("/logout" ).and()
//                .httpBasic().and()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        http.exceptionHandling().accessDeniedPage("/unauth.html");//自定义403页面
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.ldapAuthentication()
//                .userDetailsContextMapper(new PersonContextMapper())
//                .userDnPatterns("uid={0},ou=people") //LDAP
//                .contextSource()
//                .port(0);
//    }
}
