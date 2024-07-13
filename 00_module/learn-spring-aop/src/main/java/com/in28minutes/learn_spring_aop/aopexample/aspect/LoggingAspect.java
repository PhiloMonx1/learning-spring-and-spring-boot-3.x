package com.in28minutes.learn_spring_aop.aopexample.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Before("execution(* com.in28minutes.learn_spring_aop.business.*.*(..))")
	public void LogMethodCallBefore(JoinPoint joinPoint) {
		logger.info("Before 메소드 실행 : {}", joinPoint);
	}

	@After("execution(* com.in28minutes.learn_spring_aop.business.*.*(..))")
	public void LogMethodCallAfter(JoinPoint joinPoint) {
		logger.info("After 메소드 실행 : {}", joinPoint);
	}

	@AfterThrowing(pointcut = "execution(* com.in28minutes.learn_spring_aop.business.*.*(..))", throwing = "exception")
	public void LogMethodCallAfterThrowing(JoinPoint joinPoint, Exception exception) {
		logger.info("AfterThrowing 메소드 예외 발생 : {}", joinPoint, exception);
	}

	@AfterReturning(pointcut = "execution(* com.in28minutes.learn_spring_aop.business.*.*(..))", returning = "result")
	public void LogMethodCallAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("AfterReturning 메소드 실행 성공 : {}", joinPoint, result);
	}
}
