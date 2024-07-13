# 📒 [학습 노트] 챕터 16 : Spring Boot과 함께 하는 Spring AOP 배우기

## 목록
1. [Spring AOP 시작하기 – 개요](#1단계---spring-aop-시작하기--개요)
2. [관점 지향 프로그래밍이란](#2단계---관점-지향-프로그래밍이란)
3. [Spring AOP를 이용한 Spring Boot 프로젝트 생성하기](#3단계---spring-aop를-이용한-spring-boot-프로젝트-생성하기)
4. [Spring AOP에 필요한 Spring 컴포넌트 만들기](#4단계---spring-aop에-필요한-spring-컴포넌트-만들기)
5. [AOP 로깅 애스펙트와 포인트컷 만들기](#5단계---aop-로깅-애스펙트와-포인트컷-만들기)
6. [AOP 용어 훑어보기](#6단계---aop-용어-훑어보기)
7. [AOP 어노테이션 @After, @AfterReturning, @AfterThrowing](#7단계---aop-어노테이션-after-afterreturning-afterthrowing)
8. [Timer 클래스와 함께 Around AOP 어노테이션 배우기](#8단계---timer-클래스와-함께-around-aop-어노테이션-배우기)
9. [베스트 프랙티스 - 공용 포인트컷 정의하기](#9단계---베스트-프랙티스---공용-포인트컷-정의하기)
10. [TrackTime 어노테이션 만들어 보기](#10단계---tracktime-어노테이션-만들어-보기)
11. [Spring AOP 시작하기 – 마무리](#11단계---spring-aop-시작하기--마무리)

---

## 1단계 - Spring AOP 시작하기 – 개요
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/29086ff55748e8578ee2983cf22f2c5623b64228)

#### 학습 키워드
1. AOP(관점 지향 프로그래밍, Aspect Oriented Programming)
2. AOP의 개념 (AOP Concepts) 
3. 포인트컷 (Pointcut)
4. 애스팩트 (Aspect)
5. 어드바이스 (Advice)
6. Annotations
   - @Before : 코드를 실행하기 전 수행해야 하는 작업들 처리
   - @After : 코드가 실행되고 난 후 수행해양 하는 작업들 처리
   - @Around : 코드가 실행되기 전과 실행된 후 해야 하는 작업들 처리
7. 모범 사례 (Beat Practices)

---

## 2단계 - 관점 지향 프로그래밍이란
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/de0265b28440d0df04e8e90c6e0c8a55a3ecd584)

#### 계층적 접근(Layered Architecture)
소프트웨어 애플리케이션을 여러 개의 논리적 계층으로 나누어 구성하는 아키텍처 패턴, 애플리케이션에 따라 다양한 패턴의 계층 구조가 있을 수 있다.
- ex) 일반적인 웹 애플리케이션의 계층적 접근 패턴
  - 웹 레이어 (프레젠테이션 레이어): 사용자 인터페이스 View, 컨트롤러
  - 비즈니스 레이어 (서비스 레이어): 핵심 비즈니스 로직
  - 데이터 레이어 (영속성 레이어): 데이터베이스 상호작용
- 각각의 층은 역할 및 책임이 분리되어 있기에 다루는 일이 다르다.

#### 모든 레이어의 공통 부문
1. 보안 : 층과 상관 없이 모든 레이어에 필요하다. (보안 원칙 중 '심층적 방어를 구축하라'가 있다.)
2. 성능 측정
3. 로깅 

이와 같이 모든 계층에 동일하게 적용해야 하는 공통 부문을 '공통 관심사'라고 표현한다.

#### AOP : 효율적인 공통 관심사 구현
모든 층 마다 동일한 공통 관심사를 따로 구현하는 것은 중복 작업이 발생할 수 있다. 이러한 문제를 해결할 때 AOP를 주로 사용한다.

AOP가 하는 작업
- 공통 관심사를 '애스팩트'로 만든다.
   - 보안 애스팩트, 로깅 애스팩트 등...
- 애스팩트를 어디에 적용할 것인지를 명시하는 로직을 정의한다. (이것을 포인트컷이라고 부름)

#### Java 진영의 AOP
- Spring AOP (Spring Aspect Oriented Programming) : Spring Bean을 이용해 사용
- AspectJ : Spring Bean이 아니어도 사용할 수 있기 때문에 스프링을 사용하지 않을 경우 대안이 될 수 있다.

---

## 3단계 - Spring AOP를 이용한 Spring Boot 프로젝트 생성하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/fb6a7bada01411b169c8bcd9ccea5c51fb4d9163)

#### 프로젝트 생성
![Spring initializer 세팅](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 빌드 도구를 'Gradle - Groovy'로 설정한다.
- 라이브러리는 추가하지 않았다.

---

## 4단계 - Spring AOP에 필요한 Spring 컴포넌트 만들기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/7ff762de1a49f4408fe8b14009b511edaaea8bd4)

#### 데이터 레이어
```java
package com.in28minutes.learn_spring_aop.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService {

	public int[] retrieveData() {
		return new int[] { 11, 22, 33, 44, 55 };
	}

}
```

#### 비즈니스 레이어
```java
package com.in28minutes.learn_spring_aop.business;

import com.in28minutes.learn_spring_aop.data.DataService;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class BusinessService1 {
	private final DataService dataService;

	public BusinessService1(DataService dataService) {
		this.dataService = dataService;
	}

	public int calculateMax() {
		int[] data = dataService.retrieveData();
		return Arrays.stream(data).max().orElse(0);
	}
}
```

#### 코드 사용부 `CommandLineRunner` 사용
```java
package com.in28minutes.learn_spring_aop;

import com.in28minutes.learn_spring_aop.business.BusinessService1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringAopApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private final BusinessService1 businessService1;

	public LearnSpringAopApplication(BusinessService1 businessService1) {
		this.businessService1 = businessService1;
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info( "가장 큰 값은 {}", businessService1.calculateMax() );
	}
}
```
- CommandLineRunner : 스프링 부트 애플리케이션의 구동 시점에 특정 코드를 실행하기 위해 사용되는 인터페이스
  - run() 메서드를 구현해야 하며 해당 메서드 내의 로직을 자동 실행한다.

---

## 5단계 - AOP 로깅 애스펙트와 포인트컷 만들기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/5d4c94a9ad008aef7a020cd81150e7a52f13933e)

#### 라이브러리 추가
```
implementation 'org.springframework.boot:spring-boot-starter-aop'
```

#### 로깅 애스팩트 작성
```java
@Configuration
@Aspect
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Before("execution(* com.in28minutes.learn_spring_aop.business.*.*(..))")
	public void LogMethodCall(JoinPoint joinPoint) {
		logger.info("Before 메소드 실행 : {}", joinPoint);
	}

}
```
- @Aspect : 해당 클래스가 애스팩트임을 명시
- @Before : JoinPoint가 실행되기 전에 LogMethodCall()의 로직이 먼저 실행됨
  - 선언 방식 : execution() 안에 패키지 경로를 포함한 매칭 조인포인트를 입력 (패키지 기반이 아닌 어노테이션 기반 등 다양한 방식이 존재한다.)
    - 첫 번째 * : 반환 타입 () 
    - 두 번째 * : 클래스
    - 세 번째 * : 메서드
    - (..) : 파라미터
    - ex) "execution(User com.example.service.UserService.getUser(String, int))"
      - `User`를 반환하고, `com.example.service` 패키지에 속한 `UserService`의 `getUser()` 메서드 중 `String, int`를 파라미터로 받는 메서드를 대상으로 애스팩트 적용.
- JoinPoint : 애스팩트 메서드를 실행할 타겟
- 포인트컷 적용 Tip
  - 애스팩트 메서드에 여러 개의 포인트컷을 적용하는 것이 가능하다.
  - &&, ||, ! 연산자를 사용하여 여러 포인트컷을 결합하는 것이 가능하다.
  - @Pointcut 어노테이션을 사용하여 포인트컷을 정의하고 재사용하는 것이 가능하다.
    ```java
    @Aspect
    @Component
    public class ReusablePointcutAspect {
        @Pointcut("execution(* com.example.service.*.*(..))")
        public void serviceLayer() {}
    
        @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
        public void transactionalMethod() {}
    
        @Before("serviceLayer() && transactionalMethod()")
        public void beforeTransactionalServiceMethod() {
            // 서비스 레이어의 트랜잭션 메서드에 대한 로직
        }
    }
    ```
    -  com.example.service 패키지 내의 클래스 전체 중 Transactional 어노테이션이 붙은 전체 메서드를 대상으로 애스팩트 메서드를 적용.

---

## 6단계 - AOP 용어 훑어보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/92dd145ad3baeafcf55cefa75146ae25ea446681)

#### 컴파일 타임 관련 용어
1. 어드바이스(Advice) : 실행할 코드 ex) 로그 출력, 인증 등의 실제 로직이 담긴 코드
2. 포인트컷(Pointcut) : 인터셉터하려는 메서드 호출 (특정 메서드를 명시해서 인터셉트 할 수 있다.)
3. 애스팩트(Aspect) : 어드바이스와 포인트컷의 조합 언제(포인트컷) 무엇(어드바이스)을 할 것인지를 담은 것.
4. 위버(Weaver) : AOP를 구현한 프레임워크 ex) AspectJ, Spring AOP 등
   - 어드바이스랑 포인트컷을 정의하고 난 다음 어드바이스가 적시에 구현되도록 일하는 주체이다. (이러한 AOP 작업을 '위빙'이라고 부른다.)
   - Spring AOP는 프록시 기반으로 동작하며, 런타임에 위빙이 일어난다.

#### 런타임 관련 용어
1. 조인포인트(Join Point) : 런타임 환경에서 포인트컷 조건이 참일 때 실행되는 어드바이스 실행 인스턴스
   - 포인트컷 조건에 부합하는 메서드가 100개라면, 어드바이스도 100번 실행되고, 각각의 어드바이스에 조인포인트 인스턴스가 존재한다.
   - 인자, 클래스명, 메서드명 등의 포인트컷 조건 메서드에 관련된 정보를 확인할 수 있다.

---

## 7단계 - AOP 어노테이션 @After, @AfterReturning, @AfterThrowing
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/6d6306cc93ee29735484f0e6c7aa10b96c14dc43)

####  @After, @AfterReturning, @AfterThrowing
- @After : 메서드가 실행된 후 결과에 상관 없이 무조건 수행할 작업을 지정한다.
  - 메서드의 결과가 성공인지 예외를 던지는지 상관없이 실행.
- @AfterReturning : 메서드가 성공적으로 실행된 경우에 수행할 작업을 지정한다.
- @AfterThrowing : 메서드가 실행 중 예외가 발생한 경우에 수행할 작업을 지정한다.

#### 실습
```java
public class BusinessService1 {
    //...(생략)
	public int calculateMax() {
		int[] data = dataService.retrieveData();
		if (data.length == 0) {
			throw new IllegalArgumentException("데이터가 비어 있습니다.");
		}
		return Arrays.stream(data).max().getAsInt();
	}
}
```
- data가 빈 배열일 경우 `IllegalArgumentException` 예외를 발생시키도록 로직을 변경했다.

- @After
    ```java
    @After("execution(* com.in28minutes.learn_spring_aop.business.*.*(..))")
    public void LogMethodCallAfter(JoinPoint joinPoint) {
        logger.info("After 메소드 실행 : {}", joinPoint);
    }
    ```
    - 메서드의 실행 성공 여부와 상관 없이 로그를 받을 수 있다.

- @AfterReturning
    ```java
    @AfterThrowing(pointcut = "execution(* com.in28minutes.learn_spring_aop.business.*.*(..))", throwing = "exception")
    public void LogMethodCallAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.info("AfterThrowing 메소드 예외 발생 : {}", joinPoint, exception);
    }
    ```
    - 예외가 발생할 경우 해당 로그가 출력된다.
    - `throwing = "exception"`를 추가하고, 해당 이름으로 Exception을 받는다.

- @AfterThrowing
    ```java
    @AfterReturning(pointcut = "execution(* com.in28minutes.learn_spring_aop.business.*.*(..))", returning = "result")
    public void LogMethodCallAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("AfterReturning 메소드 실행 성공 : {}", joinPoint, result);
    }
    ```
    - 예외 발생 없이 메서드가 성공할 경우 해당 로그가 출력된다.
    - `returning = "result"`을 추가하고 해당 이름으로 리턴 객체를 받는다.

---

## 8단계 - Timer 클래스와 함께 Around AOP 어노테이션 배우기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/a722cae4e4e07b692d889843d2ebf4c4beeaac25)

#### @Around
포인트컷 메서드의 실행 전과 후 특정한 작업을 실행.
- 전, 후로 각각 다른 작업을 지정해서 실행하도록 할 수도 있다.

#### 메서드 실행 시간 로깅 실습
```java
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
```
- joinPoint.proceed() : 포인트컷 메서드를 실행한다.
- joinPoint.proceed() 메서드의 앞 뒤로 현재 시각을 밀리세컨드 단위로 뽑아서 끝나는 시간에서 시작 시간을 빼면 메서드의 실행 시간을 도출할 수 있다.

#### 로깅 결과
```
2024-07-13T23:01:18.727+09:00  INFO 26952 --- [learn-spring-aop] [           main] c.i.l.LearnSpringAopApplication          : Started LearnSpringAopApplication in 1.187 seconds (process running for 1.712)
2024-07-13T23:01:18.732+09:00  INFO 26952 --- [learn-spring-aop] [           main] c.i.l.a.a.LoggingAspect$$SpringCGLIB$$0  : Before 메소드 실행 : execution(int com.in28minutes.learn_spring_aop.business.BusinessService1.calculateMax())
2024-07-13T23:01:18.734+09:00  INFO 26952 --- [learn-spring-aop] [           main] erformanceTrackingAspect$$SpringCGLIB$$0 : 실행 메서드 : DataService.retrieveData(), 메서드 실행 시간 : 0 ms
2024-07-13T23:01:18.735+09:00  INFO 26952 --- [learn-spring-aop] [           main] erformanceTrackingAspect$$SpringCGLIB$$0 : 실행 메서드 : BusinessService1.calculateMax(), 메서드 실행 시간 : 1 ms
2024-07-13T23:01:18.736+09:00  INFO 26952 --- [learn-spring-aop] [           main] c.i.l.a.a.LoggingAspect$$SpringCGLIB$$0  : AfterReturning 메소드 실행 성공 : execution(int com.in28minutes.learn_spring_aop.business.BusinessService1.calculateMax())
2024-07-13T23:01:18.736+09:00  INFO 26952 --- [learn-spring-aop] [           main] c.i.l.a.a.LoggingAspect$$SpringCGLIB$$0  : After 메소드 실행 : execution(int com.in28minutes.learn_spring_aop.business.BusinessService1.calculateMax())
2024-07-13T23:01:18.736+09:00  INFO 26952 --- [learn-spring-aop] [           main] earnSpringAopApplication$$SpringCGLIB$$0 : 가장 큰 값은 55
2024-07-13T23:01:18.736+09:00  INFO 26952 --- [learn-spring-aop] [           main] c.i.l.a.a.LoggingAspect$$SpringCGLIB$$0  : Before 메소드 실행 : execution(int com.in28minutes.learn_spring_aop.business.BusinessService2.calculateMin())
2024-07-13T23:01:18.737+09:00  INFO 26952 --- [learn-spring-aop] [           main] erformanceTrackingAspect$$SpringCGLIB$$0 : 실행 메서드 : DataService.retrieveData(), 메서드 실행 시간 : 0 ms
2024-07-13T23:01:18.738+09:00  INFO 26952 --- [learn-spring-aop] [           main] erformanceTrackingAspect$$SpringCGLIB$$0 : 실행 메서드 : BusinessService2.calculateMin(), 메서드 실행 시간 : 0 ms
2024-07-13T23:01:18.738+09:00  INFO 26952 --- [learn-spring-aop] [           main] c.i.l.a.a.LoggingAspect$$SpringCGLIB$$0  : AfterReturning 메소드 실행 성공 : execution(int com.in28minutes.learn_spring_aop.business.BusinessService2.calculateMin())
2024-07-13T23:01:18.738+09:00  INFO 26952 --- [learn-spring-aop] [           main] c.i.l.a.a.LoggingAspect$$SpringCGLIB$$0  : After 메소드 실행 : execution(int com.in28minutes.learn_spring_aop.business.BusinessService2.calculateMin())
2024-07-13T23:01:18.738+09:00  INFO 26952 --- [learn-spring-aop] [           main] earnSpringAopApplication$$SpringCGLIB$$0 : 가장 작은 값은 11
```

---

## 9단계 - 베스트 프랙티스 - 공용 포인트컷 정의하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/d556867981a0a65b0a7509d2de8661c687d72f87)

Spring AOP 사용실습을 하면서 포인트컷을 정의해보았다. 그런데 만약 포인트컷 매칭 정보가 변경되면 어떻게 해야 할까? 예를 들어 패키지명이 변경된 경우 모든 포인트컷의 패키지명을 변경해야 할 수 있다. 이 문제를 해결하기 위한 AOP 모법 사례를 알아보자.

#### 포인트컷 선언부 개선
```java
@Configuration
@Aspect
public class CommonPointcutConfig {

	@Pointcut("execution(* com.in28minutes.learn_spring_aop.business.*.*(..))")
	void businessPackageConfig() {}

	@Pointcut("execution(* com.in28minutes.learn_spring_aop.data.*.*(..))")
	void dataPackageConfig() {}

}

//사용
@Configuration
@Aspect
public class PerformanceTrackingAspect {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Around("com.in28minutes.learn_spring_aop.aopexample.aspect.CommonPointcutConfig.businessPackageConfig()"
			+ " || com.in28minutes.learn_spring_aop.aopexample.aspect.CommonPointcutConfig.dataPackageConfig()")
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
```
- 같은 패키지 내에서 사용하는 경우 패키지 전체 경로가 아닌 `CommonPointcutConfig.businessPackageConfig()`로 호출할 수 있다.
- 다른 패키지의 경우 static import 등의 방법을 사용하는 것도 방법이다.
- execution 지시자 대신 어노테이션 지시자나 bean 지시자 등을 사용하는 것도 방법이다.
  - 어노테이션 지시자 예시 : `@annotation(org.springframework.stereotype.Service)`
    - Service 어노테이션 대상 클래스의 메서드를 포인트컷으로 정의
  - bean 지시자 예시 : `bean(*Service*)`
    - 이름에 'Service'가 포함되어 있는 Bene에 포함된 메서드를 포인트컷으로 정의

---

## 10단계 - TrackTime 어노테이션 만들어 보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/4647aa6c93e7faa7d76afbb040a6c174e25ada17)

#### 커스텀 어노테이션 'TrackTime' 추가
```java
@Target({ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface TrackTime { }
```
- @Target({ElementType.METHOD}) : 어노테이션을 부여할 수 있는 타겟 정의 (메서드로 지정)
- @Retention : 어노테이션이 일하는 시점 정의 (런타임 지정)
- 별도 로직은 정의하지 않고 라벨용 어노테이션을 만들었다.

#### TrackTime 용 포인트컷 작성
```java
@Configuration
@Aspect
public class CommonPointcutConfig {
	//...(기존 포인트컷)
  
	@Pointcut("@annotation(com.in28minutes.learn_spring_aop.aopexample.annotations.TrackTime)")
	void trackTimeAnnotation() {}
}

@Configuration
@Aspect
public class PerformanceTrackingAspect {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Around("CommonPointcutConfig.trackTimeAnnotation()")
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
```
- findExecutionTime() 메서드의 포인트컷을 변경한다.

```java
@Service
public class BusinessService1 {
	private final DataService dataService;

	public BusinessService1(DataService dataService) {
		this.dataService = dataService;
	}

	@TrackTime
	public int calculateMax() {
		int[] data = dataService.retrieveData();
		if (data.length == 0) {
			throw new IllegalArgumentException("데이터가 비어 있습니다.");
		}
		return Arrays.stream(data).max().getAsInt();
	}
}
```
- 특정 메서드에 `@TrackTime` 어노테이션을 부여해서 해당 메서드의 성능만 체크하는 것이 가능해졌다.

---

## 11단계 - Spring AOP 시작하기 – 마무리
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/6dfa01a06cf4c78f47791a862cfd2210cad6d82c)

#### 챕터 복습
1. AOP : 애플리케이션의 여러 계층에 중복해서 적용해야 하는 인증, 로깅, 성능체크 등의 로직을 따로 관리해서 중복 코드 없이 동시 적용할 수 있도록 하는 프로그래밍 기법
2. 주요 키워드
   - 애스팩트 : AOP를 통해 실행하고자 하는 작업
   - 포인트컷 : AOP 작업을 적용할 대상
   - 조인포인트 : AOP 로직이 적용될 때 포인트컷 메서드에 대응하여 생성되는 객체, 대상의 정보(클래스명, 메서드명, 반환 값 등)를 담고 있음.
3. 주요 어노테이션
   - @Pointcut : 포인트컷 매칭을 지정할 수 있는 어노테이션
   - @Before : 메서드 실행 전에 실행되는 작업 지정 어노테이션
   - @After : 메서드 실행 후 실행되는 작업 지정 어노테이션
     - @AfterThrowing : 메서드 실행 중 예외가 발생한 경우에만 
     - @AfterReturning : 메서드 실행 중 예외가 발생하지 않은 경우에만
   - @Around : 메서드 실행 전과 후 실행되는 작업 지정 어노테이션
4. 모범사례
   - @Pointcut 어노테이션으로 공용 포인트컷을 만든 후 다른 애스팩트에서 해당 공용 포인트컷을 사용하면 패키지명 변경 등의 수정 사항을 유연하게 처리할 수 있다.
   - Bean 지정자, 어노테이션 지정자 등을 적절히 사용해서 유연성 있는 AOP를 적용하는 것이 권장된다.
     - 라벨용 커스텀 어노테이션을 만들어서 사용하는 것도 가능

---