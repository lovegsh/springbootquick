package com.gsh.springbootquick.common.component.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {
	@Before("execution(public int com.bagu.service.impl.timuServiceImpl.*(..))")
	public void before(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println(name+"方法执行了"+"传入的参数是"+ Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(value = "execution(public int com.bagu.service.impl.timuServiceImpl.*(..))",returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("方法的结果是" + result);
	}
}
