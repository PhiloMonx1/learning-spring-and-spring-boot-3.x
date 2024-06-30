# 📒 [학습 노트] 챕터 7 : Spring Boot와 Spring Framework, Hibernate로 Java REST API 생성하기

## 목록
0. [Spring Boot로 REST API 생성하기 - 개요](#0단계---spring-boot로-rest-api-생성하기---개요)
1. [Spring Boot로 REST API 프로젝트 초기화하기](#1단계---spring-boot로-rest-api-프로젝트-초기화하기)
2. [Spring Boot로 Hello World REST API 생성하기](#2단계---spring-boot로-hello-world-rest-api-생성하기)
3. [Hello World REST API를 업그레이드하여 Bean 반환하기](#3단계---hello-world-rest-api를-업그레이드하여-bean-반환하기)
4. [백엔드에서는 어떤 일이 벌어지고 있을까? Spring Boot 스타터와 자동 설정](#4단계---백엔드에서는-어떤-일이-벌어지고-있을까-spring-boot-스타터와-자동-설정)
5. [패스 변수로 Hello World REST API 업그레이드하기](#5단계---패스-변수로-hello-world-rest-api-업그레이드하기)
6. [SNS 애플리케이션용 REST API 설계하기](#6단계---sns-애플리케이션용-rest-api-설계하기)
7. [사용자 Bean과 UserDaoService 생성하기](#7단계---사용자-bean과-userdaoservice-생성하기)
8. [User Resource에서 GET 메서드 구현하기](#8단계---user-resource에서-get-메서드-구현하기)
9. [User Resource에서 POST 메서드 구현하기](#9단계---user-resource에서-post-메서드-구현하기)
10. [POST 메소드를 개선해 올바른 HTTP 상태 코드와 locat](#10단계---post-메소드를-개선해-올바른-http-상태-코드와-location)
11. [예외 처리 구현하기 - 404 Resource Not found](#11단계---예외-처리-구현하기---404-resource-not-found)
12. [모든 리소스를 대상으로 예외 처리 구현하기](#12-단계---모든-리소스를-대상으로-예외-처리-구현하기)
13. [DELETE 메소드로 사용자 리소스 삭제하기](#13단계---delete-메소드로-사용자-리소스-삭제하기)

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

## 6단계 - SNS 애플리케이션용 REST API 설계하기

#### 주요 리소스 (Model)
- Users : 사용자
   - id
   - name : 이름
   - birthDay : 생일
- Posts : 게시물
   - id
   - description : 내용

#### 주요 API 메서드
- GET : 특정 리소스의 상세 정보 검색
- POST : 새 리소르 생성
- PUT : 기존 리소스의 상세 정보 수정
  - 기존 리소스를 완전히 대체한다. 
  - 기존과 동일한 값이 있더라도 같은 값으로 덮어 씌움
- PATCH : 기존 리소스의 일부 상세 정보 업데이트
  - 변경된 부분만 업데이트 한다.
  - 기존과 동일한 값의 경우 업데이트를 무시하고 변경점만 반영
- DELETE : 기존 리소스 삭제

#### 주요 엔드포인트 예시 (일반적으로 엔드포인트의 복수형 영단어를 사용한다.)
- 전체 사용자 조회
  - GET '/users'
- 신규 사용자 생성
  - POST '/users'
- 특정 사용자 조회
  - GET '/users/{id}'
  - ex) '/users/1' : id 값이 '1' 인 사용자 조회
- 특정 사용자 삭제
  - DELETE '/users/{id}'
- 특정 사용자의 모든 게시물 조회
  - GET '/users/{id}/posts'
- 특정 사용자의 특정 게시물 조회
  - GET '/users/{id}/posts/{id}'
  - ex) '/users/30/posts/24' : id 값이 30인 사용자의 id 값이 24인 게시물 검색
  - ps : 개인적으로 '/posts/{id}'로 충분하다고 생각한다. 게시물의 id가 중복되지는 않기 때문이다. 그러나 카테고리 등의 중복될 수 있는 패스변수의 경우 '특정 사용자의 특정 카테고리의 게시물'은 합리적인 설계이다.

---

## 7단계 - 사용자 Bean과 UserDaoService 생성하기

#### User 클래스 선언
[User.java](..%2F00_module%2Frestful-web-services%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Frest%2Fwebservices%2Frestful_web_services%2Fuser%2FUser.java)

#### UserDaoService 선언
JPA와 Hibernate를 사용하기 전 정적 ArrayList를 사용하여 간단한 인-메모리 데이터 저장소를 구현하고자 한다.
```java
@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(3, "Jim", LocalDate.now().minusYears(20)));
	}

}
```

#### DAO(Data Access Object)
- 데이터베이스에 직접 접근하여 데이터를 조작하는 객체 
- 레포지토리와 유사한 목적을 가지고 있다.
- 레포지토리 보다 더 낮은 추상화 수준에서 작동하는 패턴이다. (데이터베이스와 더 직접적으로 상호작용)
  - 특정 데이터베이스에 특화된 기술을 사용하기 유용하다. ex) MySQL 만의 기술 등
  - 레포지토리 패턴에 비해 비즈니스 로직과 데이터 접근 로직을 명확히 분리되지 않는다.

---

## 8단계 - User Resource에서 GET 메서드 구현하기

#### 모든 Users 검색
```java
@RestController
public class UserResource {

	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
}

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(3, "Jim", LocalDate.now().minusYears(20)));
	}

	public List findAll() {
		return users;
	}
}
```

#### 단일 User 검색
```java
@RestController
public class UserResource {
	//...(생략)
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return service.findOne(id);
	}

}

@Component
public class UserDaoService {
    //...(생략)
	public User findOne(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst().get();
	}
}
```

---

##  9단계 - User Resource에서 POST 메서드 구현하기

#### User POST API 추가
```java
@RestController
public class UserResource {
	//...(생략)
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		service.save(user);
	}
}

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;

	static {
		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
	}
    //...(생략)
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
}
```

#### [Talend API Tester](https://chromewebstore.google.com/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm) 사용해서 API 테스트
GET 메서드와 달리 웹 브라우저에서 바로 POST 요청을 보낼 수 있는 방법은 없다. 때문에 'REST API 클라이언트' 라는 것을 사용해야 한다. 'Postman', '인텔리제이의 `.http` 확장자' 등 여러 가지가 있으나 강의에서는 구글 크롬 확장 프로그램인 'Talend API Tester'를 사용했다.

![Talend-API-Tester.png](image/Talend-API-Tester.png)
1. API 메서드 
2. API URL 
3. 요청 헤더
    - Content-Type : 요청 데이터의 유형
4. 요청 바디
5. 결과

현 시점에서 알아야 할 사용법은 이미지로 대체한다.

---

## 10단계 - POST 메소드를 개선해 올바른 HTTP 상태 코드와 Location

#### REST API의 다양한 응답 형태
REST API를 구현할 때는, 정확한 응답 상태를 반환하는 것이 중요하다.
- 중요한 응답 코드
- 200(OK) : 성공
- 201(Created) : 성공, POST 요청으로 새 리소스를 생성한 경우
- 204(No Content) : 성공, 응답으로 반환할 본문이 없음
  - PUT이나 DELETE 후 정상적으로 데이터베이스 반영이 되었음을 알리는 용도로 사용한다.
- 400(Bad Request) : 요청 시 전달 받은 정보의 검증이 실패한 경우
- 401(Unauthorized) : 인증, 인가 실패 경우
- 403(Forbidden) : 인증은 성공했으나 권한이 없는 경우 
  - USER 권한을 가진 사용자가 ADMIN 권한이 필요한 요청을 했을 경우 사용한다.
- 404(Not Found) : 요청한 리소스를 찾지 못했을 경우
- 405(Method Not Allowed) : 허용되지 않은 HTTP 메서드로 요청했을 경우
  - ex) 현재 '/users/{id}' 엔드포인트에는 GET으로 작성된 API만 있고, PUT이나 DELETE로 작성된 API가 없다. PUT이나 DELETE로 '/users/{id}' 엔드포인트에 요청을 보낼 시 발생할 수 있다.
- 409(Conflict): 리소스의 현재 상태와 요청이 충돌한 경우 (리소스의 무결성 조건 위반의 경우)
  - 이미 존재하는 리소스를 생성하려는 경우
  - 여러 클라이언트가 하나의 리소스를 동시에 수정하려는 경우
- 429(Too Many Requests): 사용자가 일정 시간 동안 너무 많은 요청을 보냈을 경우
- 500(Internal Server Error) : 서버에서 예외가 발생한 경우
  - 이 경우에는 클라이언트가 대응하지 않고 서버가 대응해야 해야 한다.

#### User POST API에 201 HTTP 코드 반환 실습
```java
@RestController
public class UserResource {
	//...(생략)
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		service.save(user);
		return ResponseEntity.created(null).build();
	}
}
```
- `ResponseEntity.created()`의 `created()` 메서드는 201(Created)를 의미한다.
  - 때문에 `ResponseEntity.noContent()` 등도 있다.

#### Location
```java
@RestController
public class UserResource {
	//...(생략)
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").
				buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
```
- 기존 null로 입력했던 `created()` 메서드의 파라미터를 `location` 으로 채워넣었다.
- location 코드 설명
  - URI(Uniform Resource Identifier) : URL의 상위 개념으로 본래 의미는 "리소스를 식별하는 문자열 형식의 식별자"이나, 여기서는 URL과 동일하다고 이해해도 된다.
  - `ServletUriComponentsBuilder.fromCurrentRequest()` : HTTP 요청 정보를 기반으로 URI 빌더를 생성
  - `path("/{id}")` : 생성된 리소스의 URI 경로에 {id} 부분을 추가
- 응답 헤더의 location에 API 요청으로 인해 생성된 `User`의 id가 포함된 url이 리턴된다.
  - ex) 'http://localhost:8080/users/4' 
  - 해당 로케이션 url을 GET 메서드로 요청해 생성된 `User`을 확인할 수 있다.

---

## 11단계 - 예외 처리 구현하기 - 404 Resource Not found

GET 'users/{id}' 엔드포인트에 존재하지 않는 id를 입력시 500(서버)에러가 발생한다. 존재하지 않은 User을 조회하려는 시도이기 때문에 404(Not Found)로 변경이 필요하다.

#### 500에러가 발생하는 이유
```
This application has no explicit mapping for /error, so you are seeing this as a fallback.

There was an unexpected error (type=Internal Server Error, status=500).
No value present`
java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:143)
    at com.in28minutes.rest.webservices.restful_web_services.user.UserDaoService.findOne(UserDaoService.java:25)
...(생략)
```
- `spring-boot-devtools` 라이브러리가 있을 경우 에러가 발생했을 때 자바 디버그 에러를 확인할 수 있다. (응답 Body의 'trace'로 리턴 됨)
- 에러를 해석하면 'UserDaoService.findOne(UserDaoService.java:25)' 에러가 발생한 메서드를 알려주고 있다.

#### `UserDaoService::findOne()` 메서드 수정
```java
@RestController
public class UserResource {
    //...(생략)
	public User findOne(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}
	//...(생략)
}
```
- 반환 값을 `get()`에서 `orElse(null)`로 변경해 id가 일치하는 User 객체를 찾지 못했을 경우 null을 리턴하도록 변경한다.
- 해당 사항을 적용한 후 존재하지 않는 User를 조회하면 에러페이지가 나타나지 않고 응답도 '200(OK)'로 반환된다.

#### 예외처리 ([UserNotFoundException.java](..%2F00_module%2Frestful-web-services%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Frest%2Fwebservices%2Frestful_web_services%2Fuser%2FUserNotFoundException.java))
```java
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}
}
```
- 커스텀 예외 클래스를 생성한다.
- `@ResponseStatus` 어노테이션을 통해 HTTP 코드를 정할 수 있다.
- 생성자를 통해 예외 메시지를 외부에서 주입할 수 있다. 
  - 입력한 메시지는 응답 바디의 "message"로 리턴된다. (`RuntimeException`를 상속했기에 가능함)

#### `UserResource::retrieveUser()` '/users/{id}' API 예외처리
```java
@RestController
public class UserResource {
    //...(생략)
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id:" + id);
		}
		return user;
	}
	//...(생략)
}
```
- `service::findOne()`의 결과가 null로 리턴되었을 때 예외처리

#### spring-boot-devtools 의 예외 처리
```
This application has no explicit mapping for /error, so you are seeing this as a fallback.

There was an unexpected error (type=Internal Server Error, status=500).
No value present`
java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:143)
    at com.in28minutes.rest.webservices.restful_web_services.user.UserDaoService.findOne(UserDaoService.java:25)
...(생략)
```
에러가 발생한 페이지에는 이와 같은 구체적인 JAVA 디버그 에러를 확인 할 수 있다. 응답 바디에서는 "trace"로 리턴된다. `spring-boot-devtools` 라이브러리의 기본 예외처리 방식에 의한 것이다. 개발 환경에서는 유용하지만 프로덕션 환경에서는 경우에 따라 예민한 정보가 노출될 위험이 있다.

- 해결법 : `application.properties` 설정으로 비활성화 할 수 있다.
    ```properties
    server.error.include-stacktrace=never
    ```
- 프로덕션 환경 : 빌드된 jar 파일로 애플리케이션을 실행할 때 `spring-boot-devtools` 라이브러리는 자동으로 비활성화 된다.

---

## 12 단계 - 모든 리소스를 대상으로 예외 처리 구현하기

#### [ErrorDetails.java](..%2F00_module%2Frestful-web-services%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Frest%2Fwebservices%2Frestful_web_services%2Fexception%2FErrorDetails.java) 커스텀 예외 구조 생성
```java
public class ErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	//생성자 및 Getter, Setter
}
```
- timestamp : 오류 발생 시점
- message : 오류 메시지
- details : 오류에 대한 추가 상세 정보

#### ResponseEntityExceptionHandler
```json
{
"timestamp": "2024-06-30T10:39:01.153+00:00",
"status": 404,
"error": "Not Found",
"message": "id:4",
"path": "/users/4"
}
```
API 요청 중 예외가 발생할 경우 기본 예외 형식이다.
- [ResponseEntityExceptionHandler](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html)::handleException() 메서드에서 정의하고 있다.
  - `ResponseEntityExceptionHandler` 클래스를 상속 받는 클래스를 선언하여 형식을 커스텀할 수 잇다.

#### `ResponseEntityExceptionHandler` 를 상속하는 커스텀 예외 핸들러 생성
```java
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails>handleAllException(Exception ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```
![custom-all-exception.png](image/custom-all-exception.png)

#### 특정 예외 클래스에 대한 커스텀 예외 처리
현재 예외 처리는 모든 예외에 대한 전역 처리를 하고 있어 일치하는 유저를 찾지 못한 상황에서 404가 아닌 500 에러코드를 노출한다. 특정 예외 클래스에 대한 예외처리를 별도 지정하는 것으로 해결할 수 있다.
```java
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	//handleAllException() 메서드 생략
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails>handleUserNotFoundException(Exception ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
```
![custom-user-not-found-exception.png](image/custom-user-not-found-exception.png)

#### 어떻게 가능한 걸까?
- @ControllerAdvice : 애플리케이션 전체에서 발생하는 예외를 처리하는 클래스에게 부여하는 어노테이션.
  - 일반적으로 ResponseEntityExceptionHandler 클래스를 확장하여 사용하나 필수는 아니다.
  - 여러 클래스에 적용할 수 있으나 `@Order`를 통해 순서를 정해주지 않으면 예상치 못한 에러가 발생할 수 있다. 
- @ExceptionHandler : 특정 예외를 처리하는 메서드를 지정하는 어노테이션
  - 어노테이션의 파라미터로 처리할 예외 클래스를 지정한다. ex) `@ExceptionHandler(UserNotFoundException.class)`
  - 지정된 클래스 및 지정된 클래스를 상속 받는 클래스 예외가 발생했을 때 부여된 메서드가 실행된다.

즉, `@ControllerAdvice`와 `@ExceptionHandler`의 조합으로 인해 전역 예외 처리가 가능하며 `ResponseEntityExceptionHandler` 클래스를 굳이 상속할 필요는 없다.

---

## 13단계 - DELETE 메소드로 사용자 리소스 삭제하기

#### User 삭제 메서드 추가
```java
@Component
public class UserDaoService {
    //...(생략)
	public void deleteById(int id) {
		users.removeIf(user -> user.getId() == id);
	}
}
```

#### User 삭제 API 추가
```java
@RestController
public class UserResource {
    //...(생략)
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}
}
```

#### API 리팩토링
강의에서는 위의 과정까지 진행하지만 개인적으로 리팩토링을 하고 싶으면 아래 코드를 참고할 수 있다.
```java
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id:" + id);
		}
		service.deleteById(id);

		return ResponseEntity.noContent().build();
	}
```
- 삭제하려는 User가 존재하지 않을 경우 예외처리 추가
- 요청이 성공했을 경우 200이 아닌 204 코드 반환

강의 진행을 위해 코드는 롤백시킨 후 커밋함.

---