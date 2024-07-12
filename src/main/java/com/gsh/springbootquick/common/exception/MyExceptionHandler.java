package com.gsh.springbootquick.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义错误输出的json数据
 * Create By GSH on .
 */
@ControllerAdvice
public class MyExceptionHandler {

    //浏览器和客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(Exception e){
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("msg", e.getMessage());
//        return map;
//    }

    //自适应  转发     ！！！！！没成功！！！！！！！！
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //传入自己的错误状态码 4xx 5xx
        request.setAttribute("javax.servlet.error.status_code", 400);
        map.put("code", "user.notexist");
        map.put("msg", e.getMessage());
        request.setAttribute("ext", map);
        return "forward:/error";
    }
}
