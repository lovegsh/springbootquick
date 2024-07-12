package com.gsh.springbootquick.common.component.filter;

import cn.hutool.jwt.JWTUtil;
import com.gsh.springbootquick.common.exception.DefaultException;
import com.gsh.springbootquick.common.util.JwtUtil;
import org.jvnet.mimepull.DecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GSH
 * @create 2023/3/8 20:11
 */
//@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    public static final String AUTHENTICATION = "Authorization";

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHENTICATION);
        String requestURI = request.getRequestURI();
        if ("/user/login".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (token == null) {
            throw new DefaultException("===== token is null! =====");
        }
        if (!JwtUtil.verify(token)) {
            throw new DefaultException("===== verify failure! =====");
        }
        if (JwtUtil.isExpired(token)) {
            throw new DefaultException("===== token is expired! =====");
        }
        String username = JwtUtil.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
