# 📒 [학습 노트] 챕터 7 : Spring Boot와 Spring Framework, Hibernate로 Java REST API 생성하기

## 목록
0. [Spring Boot로 REST API 생성하기 - 개요](#0단계---spring-boot로-rest-api-생성하기---개요)
1. [Spring Boot로 REST API 프로젝트 초기화하기](#1단계---spring-boot로-rest-api-프로젝트-초기화하기)
2. [Spring Boot로 Hello World REST API 생성하기](#2단계---spring-boot로-hello-world-rest-api-생성하기)
3. [Hello World REST API를 업그레이드하여 Bean 반환하기](#3단계---hello-world-rest-api를-업그레이드하여-bean-반환하기)
4. [백엔드에서는 어떤 일이 벌어지고 있을까? Spring Boot 스타터와 자동 설정](#4단계---백엔드에서는-어떤-일이-벌어지고-있을까-spring-boot-스타터와-자동-설정)
5. [패스 변수로 Hello World REST API 업그레이드하기](#5단계---패스-변수로-hello-world-rest-api-업그레이드하기)

---

## 0단계 - Spring Boot로 REST API 생성하기 - 개요

#### 챕터 목표
1. REST API를 빌드하는 데 Spring Boot가 최적의 프레임워크 중 하나인 이유
2. 훌륭한 REST API 빌드하는 법
   - REST API 포함해야 돼는 리소스는?
   - 리소스에서 수행할 수 있는 작업을 식별하는 방법?
   - 요청과 응답 구조 정의 방식?
3. REST API 의 우수 사례 (늘 소비자 관점에서 생각하는 것이 가장 중요)
   - 검증
   - 국제화
   - 예외 처리
   - HATEOAS
   - 버전 관리
   - 문서화
   - 콘텐츠 협상
   - ...

#### 기초 학습 접근법
1. ‘Hello World’ REST API를 빌드
2. Spring Boot를 통한 REST API 빌드의 기본 알아보기
3. 신규 어노테이션 학습
   - @RestController
   - @RequestMapping
   - @GetMapping
   - @PutMapping
   - @PathVariable
4. JSON 변환 이해하기

#### SNS 애플리케이션용 REST API 빌드 (RESTful API)
1. 올바른 요청 및 응답 구조 학습
2. REST API에 보안 구현
3. 검증 및 예외처리 구현
4. 고급 REST API 기능 추가
   - 국제화
   - HATEOAS
   - 버전 관리
   - 문서화
   - 콘텐츠 협상
   - ...
5. JPA와 Hibernate를 사용해서 REST API를 데이터베이스에 연결
   - H2 데이터베이스를 연결해서 JPA와 Hibernate의 원리를 이해
   - MySQL로 교체

챕터의 목표는 'Spring Boot로 훌륭한 REST API를 빌드'하고 해당 문서에 작성된 '중요한 용어' 들을 이해하는 것이다.

---

## 1단계 - Spring Boot로 REST API 프로젝트 초기화하기

#### 프로젝트 생성
![Spring-initializer.png](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 라이브러리 목록
   - Spring Web
   - Spring Boot DevTools

---

## 2단계 - Spring Boot로 Hello World REST API 생성하기

#### Hello Wolrd GET API 작성
```java
@RestController
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
}
```
- @RestController : `@Controller` + `@ResponseBody `
  - `@Controller` 는 보통 View를 리턴하는 데 사용한다. (데이터를 리턴하기 위해서는 `@ResponseBody`와 함께 사용해야 한다.)
  - `@RestController` 는 데이터를 리턴하는 데 사용한다. (내부에 `@Controller`와 `@ResponseBody`를 함께 가지고 있다.)
- @GetMapping : `@RequestMapping(method = RequestMethod.GET, path = "/hello-world")` 와 동일한 기능이다.

---

## 3단계 - Hello World REST API를 업그레이드하여 Bean 반환하기

```java
@RestController
public class HelloWorldController {
	//...(생략)
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
}

public class HelloWorldBean {
   private String message;
   
   //Getter, Setter 생략
}
```
- 엔드포인트로 들어가면 아래의 데이터를 받을 수 있다.
   ```json
   {
     "message": "Hello World"
   }
   ```

---

## 4단계 - 백엔드에서는 어떤 일이 벌어지고 있을까? Spring Boot 스타터와 자동 설정

#### 디스패처 서블릿 (Dispatcher Servlet)
- 애플리케이션의 모든 요청을 중앙 집권 관리하는 주체 '검문소'에 비유할 수 있다.
- 모든 요청을 가장 먼저 받아서 해당 요청을 처리할 수 있는 핸들러(컨트롤러)에 연결하는 역할을 가진다.
- 디스패처 서블릿의 루트 매핑은 '/' 이기 때문에 모든 요청을 처리할 수 있는 것이다.
- ps : 스프링 시큐리티의 요청 처리보단 뒤에서 일어난다.
  - 스프링 시큐리티는 '서블릿 필터'를 사용해서 동작하며, '서블릿 필터'는 검문소에 도착하기 전 거쳐야 하는 '검문 초소'에 비유할 수 있음.

#### 디스패처 서블릿 설정
- Spring Boot의 자동 설정(Auto-configuration)에 의해 설정된다.
  - [DispatcherServletAutoConfiguration 클래스](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/autoconfigure/web/servlet/DispatcherServletAutoConfiguration.html) 참고.
  - 애플리케이션 실행 debug 로그에서 'DispatcherServletAutoConfiguration matched:'를 검색해서 현재 애플리케이션의 설정을 확인해볼 수 있다.

#### JSON 변환
`/hello-world-bean` API는 Bean 객체를 자동으로 JSON 변환하여 응답하고 있다.
- `@ResponseBody` : 응답 리턴을 HTTP 응답 본문으로 직접 전송
  - `@RestController` 어노테이션 내부에서 같이 사용하고 있다. 
- `JacksonHttpMessageConvertersConfiguration` : JSON 처리를 위한 HttpMessageConverter 자동 구성 (Jackson 라이브러리)
  - `org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration` 클래스
  - 역시 애플리케이션 실행 debug 로그에서 'JacksonHttpMessageConvertersConfiguration'를 검색해서 현재 애플리케이션의 설정을 확인해 볼 수 있다.

#### `/hello-world-bean` GET 요청 시 발생하는 로그
```
DEBUG 13972 --- [restful-web-services] [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : GET "/hello-world-bean", parameters={}
DEBUG 13972 --- [restful-web-services] [nio-8080-exec-2] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.in28minutes.rest.webservices.restful_web_services.helloworld.HelloWorldController#helloWorldBean()
DEBUG 13972 --- [restful-web-services] [nio-8080-exec-2] o.j.s.OpenEntityManagerInViewInterceptor : Opening JPA EntityManager in OpenEntityManagerInViewInterceptor
DEBUG 13972 --- [restful-web-services] [nio-8080-exec-2] m.m.a.RequestResponseBodyMethodProcessor : Using 'application/json;q=0.8', given [text/html, application/xhtml+xml, image/avif, image/webp, image/apng, application/xml;q=0.9, */*;q=0.8, application/signed-exchange;v=b3;q=0.7] and supported [application/json, application/*+json]
DEBUG 13972 --- [restful-web-services] [nio-8080-exec-2] m.m.a.RequestResponseBodyMethodProcessor : Writing [HelloWorldBean{message='Hello World'}]
DEBUG 13972 --- [restful-web-services] [nio-8080-exec-2] o.j.s.OpenEntityManagerInViewInterceptor : Closing JPA EntityManager in OpenEntityManagerInViewInterceptor
DEBUG 13972 --- [restful-web-services] [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : Completed 200 OK
```
- 첫 번째 줄 : 디스패처 서블릿이 GET 요청을 받음
- 두 번째 줄 : 디스패처 서블릿이 요청을 처리할 적절한 핸들러(컨트롤러 메서드)를 찾음
- 세 번째 줄 : JPA EntityManager를 열어서 데이터베이스 작업을 준비함 (트랜잭션 관리)
   - DB를 연결하지 않았기에 실제 DB 작업을 진행하지는 않는다
- 네 번째 줄 : 응답의 Content-Type을 'application/json'으로 지정함
- 다섯 번째 줄 : 컨트롤러에서 반환된 객체를 JSON 형식으로 변환하여 응답 본문에 작성
- 여섯 번째 줄 : 요청이 끝났으므로 EntityManager를 닫음
- 일곱 번째 줄 : 요청 처리를 완료하고 200 응답을 보냄

#### 오류 매핑 
브라우저에 존재하지 않는 URL 엔드포인트를 입력할 시 404 'Whitelabel Error Page' 를 볼 수 있다.
- [ErrorMvcAutoConfiguration 클래스](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/autoconfigure/web/servlet/error/ErrorMvcAutoConfiguration.html)에 의해 설정된다.
- 애플리케이션 실행 debug 로그에서 'ErrorMvcAutoConfiguration matched:'를 검색해서 현재 애플리케이션의 설정을 확인해볼 수 있다.

#### 스타터 프로젝트 (Spring Boot Starter)
애플리케이션 실행 debug 로그를 보면 'Spring MVC', 'Jackson', 'Tomcat' 등 키워드가 노출되는 것을 볼 수 있다. 그런데 해당 애플리케이션에는 'Jackson', 'Tomcat' 둥의 라이브러리를 추가하지 않았다.
- `spring-boot-starter-web`
  - pom.xml 에서 확인 할 수 있는 라이브러리이다.
  - 해당 라이브리 내부에서 상기한 'Spring MVC', 'Jackson', 'Tomcat' 등의 라이브러리가 포함되어 있다.
- Spring Boot Starter 프로젝트는 개발에 필수적인 라이브러리를 함께 제공하는 '모음집'으로 비유할 수 있다.

---

## 5단계 - 패스 변수로 Hello World REST API 업그레이드하기

#### @PathVariable
```java
@RestController
public class HelloWorldController {
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
}
```
- url 엔드포인트에서 파라미터가 아닌 패스 변수를 받는 것이 가능하다.
- 엔드포인트에서는 `{}` 중괄호에 변수명을 작성한다.
- 메서드 파라미터에 `@PathVariable`와 함께 변수명을 매핑시켜서 변수를 받을 수 있다.
- 브라우저에서 '/hello-world/path-variable/패스변수'로 접근시 '패스변수' 문자열이 `name` 변수로 매핑된다.

---