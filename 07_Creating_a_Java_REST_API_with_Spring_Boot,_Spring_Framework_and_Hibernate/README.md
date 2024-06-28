# 📒 [학습 노트] 챕터 7 : Spring Boot와 Spring Framework, Hibernate로 Java REST API 생성하기

## 목록
0. [Spring Boot로 REST API 생성하기 - 개요](#0단계---spring-boot로-rest-api-생성하기---개요)
1. [Spring Boot로 REST API 프로젝트 초기화하기](#1단계---spring-boot로-rest-api-프로젝트-초기화하기)
2. [Spring Boot로 Hello World REST API 생성하기](#2단계---spring-boot로-hello-world-rest-api-생성하기)
3. [Hello World REST API를 업그레이드하여 Bean 반환하기](#3단계---hello-world-rest-api를-업그레이드하여-bean-반환하기)

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