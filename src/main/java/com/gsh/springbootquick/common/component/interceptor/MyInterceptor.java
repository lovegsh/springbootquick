package com.gsh.springbootquick.common.component.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GSH
 * @create 2022/5/6 9:45
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle----->"+threadLocal.get());
//        threadLocal.set(Thread.currentThread().getName());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle----->"+threadLocal.get());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        threadLocal.remove();
//        System.out.println("afterCompletion----->"+threadLocal.get());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
