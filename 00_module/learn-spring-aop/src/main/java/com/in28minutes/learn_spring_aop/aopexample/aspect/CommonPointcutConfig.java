package com.in28minutes.learn_spring_aop.aopexample.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class CommonPointcutConfig {

	@Pointcut("execution(* com.in28minutes.learn_spring_aop.business.*.*(..))")
	void businessPackageConfig() {}

	@Pointcut("execution(* com.in28minutes.learn_spring_aop.data.*.*(..))")
	void dataPackageConfig() {}

}
