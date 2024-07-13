package com.in28minutes.learn_spring_aop.aopexample.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class PerformanceTrackingAspect {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Around("execution(* com.in28minutes.learn_spring_aop.*.*.*(..))")
	public Object findExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long end = System.currentTimeMillis();

		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		logger.info("실행 메서드 : {}.{}(), 메서드 실행 시간 : {} ms" , className, methodName, (end - start));

		return result;
	}
}
