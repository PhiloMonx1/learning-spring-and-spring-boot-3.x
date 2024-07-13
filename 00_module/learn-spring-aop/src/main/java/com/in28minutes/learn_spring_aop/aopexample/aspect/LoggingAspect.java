package com.in28minutes.learn_spring_aop.aopexample.aspect;

import org.aspectj.lang.JoinPoint;
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
	public void LogMethodCall(JoinPoint joinPoint) {
		logger.info("Before 메소드 실행 : {}", joinPoint);
	}

}
