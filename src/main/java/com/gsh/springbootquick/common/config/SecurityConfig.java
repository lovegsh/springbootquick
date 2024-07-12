package com.gsh.springbootquick.common.config;

import com.gsh.springbootquick.common.component.filter.ExceptionHandlerFilter;
import com.gsh.springbootquick.common.component.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author GSH
 * @create 2023/3/6 20:13
 */
@Configuration
public class SecurityConfig {
//    @Autowired JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired ExceptionHandlerFilter exceptionHandlerFilter;

    //权限限制
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .authorizeRequests(
//                        (auth) -> auth.anyRequest().permitAll()
//                ).httpBasic(Customizer.withDefaults());
                .authorizeHttpRequests(
                        authz -> authz.anyRequest().permitAll()
//                        authz -> authz.antMatchers("/user/login","/imserver/**","/websocket","/ws").permitAll()
//                                .anyRequest().authenticated()

                ).httpBasic(withDefaults());
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(exceptionHandlerFilter, LogoutFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
