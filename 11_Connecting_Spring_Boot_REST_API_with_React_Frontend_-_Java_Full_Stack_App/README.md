# 📒 [학습 노트] 챕터 11: Spring Boot REST API와 React 프론트엔드 연결하기 - Java 풀스택 앱

## 목록
1. [React 풀 스택 애플리케이션을 위해 Todo REST API 프로젝트 설정하기](#1단계---react-풀-스택-애플리케이션을-위해-todo-rest-api-프로젝트-설정하기)
2. [React Hello World 컴포넌트에서 Spring Boot Hello World REST API 호출하기](#2단계---react-hello-world-컴포넌트에서-spring-boot-hello-world-rest-api-호출하기)
3. [Spring Boot REST API에 대해 CORS 요청 활성화하기](#3단계---spring-boot-rest-api에-대해-cors-요청-활성화하기)
4. [React에서 Spring Boot Hello World Bean과 패스 변수 REST API 호출하기](#4단계---react에서-spring-boot-hello-world-bean과-패스-변수-rest-api-호출하기)
5. [Spring Boot REST API 호출 코드를 별도의 모듈에 리팩터링하기](#5단계---spring-boot-rest-api-호출-코드를-별도의-모듈에-리팩터링하기)
6. [Spring Boot REST API에서 Axios를 사용하는 최적의 방식](#6단계---spring-boot-rest-api에서-axios를-사용하는-최적의-방식)
7. [Retrieve Todos Spring Boot REST API GET 메서드 만들기](#7단계---retrieve-todos-spring-boot-rest-api-get-메서드-만들기)
8. [React 앱에서 Spring Boot REST API로부터 Todo 표시하기](#8단계---react-앱에서-spring-boot-rest-api로부터-todo-표시하기)
9. [Todo를 받고 삭제하는 Spring Boot REST API 메서드 만들기](#9단계---todo를-받고-삭제하는-spring-boot-rest-api-메서드-만들기)
10. [React 프론트엔드에 삭제 기능 추가하기](#10단계---react-프론트엔드에-삭제-기능-추가하기)
11. [username을 React 인증 컨텍스트에 설정하기](#11단계---username을-react-인증-컨텍스트에-설정하기)
12. [Todo 페이지를 표시하기 위한 Todo React 컴포넌트 만들기](#12단계---todo-페이지를-표시하기-위한-todo-react-컴포넌트-만들기)
13. [Todo React 컴포넌트를 표시하기 위해 Formik 및 Moment 라이브러리 추가하기](#13단계---todo-react-컴포넌트를-표시하기-위해-formik-및-moment-라이브러리-추가하기)
14. [Formik을 이용하여 Todo React 컴포넌트에 검증 추가하기](#14단계---formik을-이용하여-todo-react-컴포넌트에-검증-추가하기)
15. [Spring Boot 백엔드 API에 Todo 업데이트 및 생성 REST API 추가하기](#15단계---spring-boot-백엔드-api에-todo-업데이트-및-생성-rest-api-추가하기)
16. [React 프론트엔드에 업데이트 기능 추가하기](#16단계---react-프론트엔드에-업데이트-기능-추가하기)
17. [React 프론트엔드에 새로운 Todo 생성 기능 추가하기](#17단계---react-프론트엔드에-새로운-todo-생성-기능-추가하기)
18. [Spring Security로 Spring Boot REST API 보호하기](#18단계---spring-security로-spring-boot-rest-api-보호하기)
19. [Spring Boot REST API 호출을 위해 React에 인증 헤더 추가하기](#19단계---spring-boot-rest-api-호출을-위해-react에-인증-헤더-추가하기)
20. [모든 OPTIONS 요청을 허용하도록 Spring Security 설정하기](#20단계---모든-options-요청을-허용하도록-spring-security-설정하기)
21. [React 앱에 로그인할 때 기본 인증 서비스 호출하기](#21단계---react-앱에-로그인할-때-기본-인증-서비스-호출하기)
22. [async와 await를 사용하여 기본 인증 API 호출하기](#22단계---async와-await를-사용하여-기본-인증-api-호출하기)
23. [AuthContext에 기본 인증 토큰 설정하기](#23단계---authcontext에-기본-인증-토큰-설정하기)
24. [인증 헤더를 추가하기 위해 Axios 인터셉터 설정하기](#24단계---인증-헤더를-추가하기-위해-axios-인터셉터-설정하기)
25. [JWT 및 Spring Security 시작하기](#25단계---jwt-및-spring-security-시작하기)
26. [Spring Security JWT REST API와 React 프론트엔드 통합하기](#26단계---spring-security-jwt-rest-api와-react-프론트엔드-통합하기)
27. [JWT 인증 및 Spring Boot 문제 해결하기](#27단계---jwt-인증-및-spring-boot-문제-해결하기)

---

## 1단계 - React 풀 스택 애플리케이션을 위해 Todo REST API 프로젝트 설정하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/9ec8fb3ee5f72eff52515380ef34027c32475b06)

챕터 목표 : 기존 Spring Boot로 제작한 백엔드 애플리케이션을 재활용해서 풀 스택 애플리케이션을 만들 것이다.

원활한 진행을 위해 정돈된 백엔드 [애플리케이션 프로젝트](https://github.com/in28minutes/master-spring-and-spring-boot/blob/main/13-full-stack/99-reuse/01-rest-api-starting-code.zip)를 제공한다.

#### REST API 목록
1. Hello World REST API
- GET '/hello-world'
- GET '/hello-world-bean'
- GET '/hello-world/path-variable/{name}'
2. Todo REST API
- GET '/user/{username}/todos'
- GET '/user/{username}/todos/{id}'
- POST '/user/{username}/todos/{id}'
- PUT '/user/{username}/todos/{id}'
- DELETE '/user/{username}/todos/{id}'

#### 프로젝트 설치 & 모듈 등록 (IntelliJ IDE)
[링크](https://github.com/in28minutes/master-spring-and-spring-boot/blob/main/13-full-stack/99-reuse/01-rest-api-starting-code.zip)에서 제공

1. 필요 없는 파일 삭제. (이클립스 설정 파일 삭제)
- '.settings' 디렉토리 및 내부 파일
- '.classpath' 파일
- '.project' 파일

2. 모듈 등록
- 프로젝트 구조 (Ctrl + Alt + Shift + S)설정에 들어간다.
- 추가(Alt + insert) 버튼을 클릭 후 '모듈 가져오기'를 선택한다.
  - ![img.png](image/IntelliJ-module-setting-1.png)
- 모듈로 가져올 프로젝트를 선택하고 '확인' 버튼을 클릭한다.
- '외부 모델에서 모듈 가져오기'에서 'Maven'을 선택 후 '생성' 버튼을 클릭한다.
  - ![img_1.png](image/IntelliJ-module-setting-2.png)
- '적용' 혹은 '확인'을 누른 후 모듈을 불러올 때까지 기다린다.

3. **중요!** Spring Boot 버전 변경
    ```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.0.0-M4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    ```
    - pom.xml을 보면 Spring Boot 버전이 '3.0.0-M4' 인것을 확인할 수 있다.
    - 해당 버전은 정식 릴리즈 버전이 아닌 마일스톤 버전으로 나중에 JWT를 구현하는 단계에서 이슈가 발생한다.
    - 최신 버전을 변경 후 Maven을 다시 불러오는 것을 추천한다. (필자는 3.3.1 버전 사용)
    
4. 프로젝트 실행
모듈 불러오기가 끝난 후 'RestfulWebServicesApplication' 애플리케이션을 실행하고, ['/hello-world'](http://localhost:8080/hello-world) GET API를 확인한다.

---

## 2단계 - React Hello World 컴포넌트에서 Spring Boot Hello World REST API 호출하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/e31844cd6bfa23830037e5259236e627c238bc80)

#### Axios
브라우저와 Node.js에서 사용할 수 있는 Promise 기반의 HTTP 클라이언트 라이브러리
- Promise : 비동기 작업을 처리하기 위한 객체
  - 비동기 작업 : 특정 코드의 실행이 완료될 때까지 기다리지 않고 다음 코드를 먼저 실행하는 방식의 작업
    - ex) 서버에 요청을 보낸 후 응답을 기다리는 동안 다른 작업을 진행할 수 있다. 

#### Axios 설치
```
npm install axios
```

#### Axios 사용
```jsx
import axios from "axios";

function callHelloWorldRestApi() {
    console.log("callHelloWorldRestApi")
    axios.get('http://localhost:8080/hello-world')
    .then ((response) => successfulResponse(response))
    .catch((error) => failedResponse(error))
    .finally(() => console.log("finally"))
}

function successfulResponse(response) {
  console.log(response)
}

function failedResponse(error) {
  console.log(error)
}
```
- then : 요청 성공
- catch : 요청 실패
- finally : 성공과 실패 상관하지 않음
- Promise 체이닝(Promise chaining) : 여러 개의 비동기 작업을 순차적으로 처리할 때 사용되는 기법
  - then이 정상적으로 완료되면 catch는 실행되지 않는다.
  - then은 여러 번 사용할 수 있다.
  - catch와 finally는 일반적으로 체인의 끝에 한 번씩 사용된다.

---

## 3단계 - Spring Boot REST API에 대해 CORS 요청 활성화하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/705f51ba5e583e7ccf6dd1416036f46332cb3af4)

2단계를 진행 후 실제 브라우저에서 API 요청을 보내면 
```
"Access to XMLHttpRequest at 'http://localhost:8080/hello-world' from origin 'http://localhost:3000' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource."
```
다음과 같은 에러 메시지가 콘솔에 출력된다. 해석하자면 API 요청이 "CORS 정책에 의해 차단되었다"는 것이다.

#### cross-origin request
API가 차단된 이유는 3000번 포트(클라이언트)에서 8080번 포트(서버)로 보내는 요청이 'cross-origin request'이기 때문이다.
- 다른 출처(origin)로 보내는 요청을 의미함. 아래 세 가지 중 하나라도 다르다면 다른 출처로 인식한다.
  - 프로토콜 (예: http, https)
  - 호스트 (도메인 또는 IP 주소)
  - 포트 번호
- 기본적으로 브라우저는 Same-Origin Policy를 따라 Cross-Origin 요청을 제한
  - 대표적인 공격으로 CSRF 공격이 있다.

#### CSRF(Cross-Site Request Forgery) 공격
악의적인 웹사이트가 사용자의 브라우저를 통해 다른 신뢰할 수 있는 사이트에 요청을 보내 민감한 정보를 탈취하거나 원치 않는 작업을 수행하는 것
1. 은행, SNS 등의 서비스를 이용하면서 로그인이 된 상태의 브라우저 환경에서
2. 악의적인 웹 사이트에 사용자가 접속한다.
3. 브라우저에 저장된 정보 ex) 은행 인증 권한, SNS 인증 권한 등을 악의적 웹 사이트가 탈취한다.
4. 악의적 웹 사이트에서 사용자의 인증 권한으로 은행이나 SNS에 요청을 보낸다.
5. 의도하지 않은 계좌 이체, 게시물 작성 등의 피해가 발생한다.

이와 같은 시나리오를 CSRF 공격이라고 한다.

#### CORS (Cross-Origin Resource Sharing)
Cross-Origin 요청에 대해 리소스 공유를 허용하는 옵션.
- 브라우저에 의해 구현되는 보안 메커니즘
- 서버가 특정 출처로부터의 요청을 허용한다고 명시적으로 알리면 브라우저는 해당 출처로부터의 Cross-Origin 요청을 서버에 전송하는 것을 허용한다.
- 서버에서 클라이언트 출처(http://localhost:3000/)를 명시하여 클라이언트에서의 Cross-Origin 요청을 허용할 수 있다.

#### CORS 설정하기 (WebMvcConfigurer)
대부분의 보안 설정은 Spring Security에서 지원한다. CORS 역시 Spring Security에서 지원하는 설정이다.

그런테 CORS의 경우 브라우저에서 동작하는 보안 정책이다. 즉, 서버가 어떠한 보안 정책 및 보안 설정을 가지고 있는지 브라우저는 알지 못한 상태로 CORS를 강제한다. Spring Security를 사용하지 않는 서버의 경우도 CORS 보안 정책을 피해갈 수 없다.
서버 애플리케이션이 Spring Security 라이브러리를 사용하지 않는 경우를 대비해서 스프링 프레임워크에선 기본적으로 CORS 설정을 할 수 있는 방법을 제공한다.

- 애플리케이션 메인 파일에 선언.
```java
@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:3000/");
			}
		};
	}

}
```
- [WebMvcConfigurer](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html) : Spring MVC 구성을 사용자 정의하는 데 사용되는 인터페이스
  - addCorsMappings : CORS 설정을 위한 메서드 (오버라이드 함)
    - addMapping() : 허용 API 엔드포인트, "/**"를 통해 모든 API 허용 
      - 특정 API만 허용 가능 : ex) "/api/**": /api로 시작하는 모든 경로에 적용
        - addMapping()의 경우 한 번에 하나의 경로만 지정할 수 있다.
          - addMapping()를 추가 선언해서 다른 경로에 대한 설정을 추가할 수 있다.
            ```java
            @Bean
            public WebMvcConfigurer corsConfigurer() {
              return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
                  registry.addMapping("/api/**")
                          .allowedMethods("*")
                          .allowedOrigins("http://localhost:3000");
                  registry.addMapping("/user")
                          .allowedMethods("*")
                          .allowedOrigins("http://localhost:3000");
                }
              };
            }
            ```
    - allowedMethods() : 허용 HTTP 메서드, ("*")를 통해 모든 HTTP 메서드 허용 
      - 특정 메서드만 허용 가능 : ex) allowedMethods("GET", "POST", "PUT", "DELETE")
    - allowedOrigins() : 허용 출처, 클라이언트 도메인:포트 명시적 허용
      - 여러 출처 허용 가능: ex) allowedOrigins("http://localhost:3000", "https://example.com")

---

## 4단계 - React에서 Spring Boot Hello World Bean과 패스 변수 REST API 호출하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/aa3b627b41c5f774e25ac96387d1c09224157cde)

####
```jsx
export default function WelcomeComponent() {
  const [message, setMessage] = useState(null)

  function callHelloWorldRestApi() {
    console.log("callHelloWorldRestApi")
    axios.get('http://localhost:8080/hello-world-bean')
    .then ((response) => successfulResponse(response))
    .catch((error) => failedResponse(error))
    .finally(() => console.log("finally"))
  }

  function successfulResponse(response) {
    console.log(response)
    setMessage(response.data.message)
  }
  
  //...(생략)
  return (
        // ...(생략)
        <div className="text-info">{message}</div>
  );
}
```
- useState에 API response을 담을 수 있다.
- response.data : API의 응답 데이터
  ```json
  {
    "message" : "Hello World"
  }
  ```
  - 값이 message key에 담겨서 오기 때문에 "Hello World"를 노출하기 위해서는 `response.data.message`로 접근해야 한다.

---

## 5단계 - Spring Boot REST API 호출 코드를 별도의 모듈에 리팩터링하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/c55c60d7e7505ee5206ab9e026a8ce58906fc4ac)

#### HelloWolrdBean API 리팩토링
```js
// HelloWorldApiService.js 
export function retrieveHelloWorldBean() {
  return axios.get('http://localhost:8080/hello-world-bean')
}
```
- 이와 같이 axios.get 부분만 분리해서 컴포넌트 모듈에서 axios를 직접 임포트하지 않고 `retrieveHelloWorldBean()` 함수를 사용해서 api 요청을 하도록 할 수 있다.

```js
export const retrieveHelloWorldBean = () => axios.get('http://localhost:8080/hello-world-bean')
```
- 이와 같이 변수화 시켜서 사용하는 것도 가능하다.

```jsx
  function callHelloWorldRestApi() {
  retrieveHelloWorldBean()
  .then ((response) => successfulResponse(response))
  .catch((error) => failedResponse(error))
  .finally(() => console.log("finally"))
}
```
- 사용할 때는 `axios.get('http://localhost:8080/hello-world-bean')` 대신 `retrieveHelloWorldBean()`를 사용하기만 하면 된다.

---

## 6단계 - Spring Boot REST API에서 Axios를 사용하는 최적의 방식
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/38a6c45c695e2469deafd77e72aa8236c1ae6c05)

#### Axios에서 API 패스변수 처리하기
```
export const retrieveHelloWorldPathVariable = (username) => axios.get(`http://localhost:8080/hello-world/path-variable/${username}`)
```
- 이와 같은 방식으로 패스변수를 전달할 수 있다.

#### Axios API 베이스 URL 설정하기
```js
import axios from "axios";

const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});

export const retrieveHelloWorldBean = () => apiClient.get('/hello-world-bean')
export const retrieveHelloWorldPathVariable = (username) => apiClient.get(`/hello-world/path-variable/${username}`)
```
- 중복해서 발생하는 서버도메인을 해당 방식으로 개선할 수 있다.
  - 베이스 URL 설정과 함께 선언한 `apiClient`를 통해 api요청을 한다.

---

## 7단계 - Retrieve Todos Spring Boot REST API GET 메서드 만들기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/dc98a079d7d5e2c73ea9e8b9151a5bfc55bc8b8c)

#### TodoResource 구현
```java
@RestController
public class TodoResource {
	private TodoService todoService;

	public TodoResource(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoService.findByUsername(username);
	}
}
```
- TodoService를 포함하는 생성자로 TodoService에 접근할 수 있게된다. (스프링 컨텍스트가 Bean의 의존성 주입을 책임짐)
- '@GetMapping("/users/{username}/todos")' API 명세로 작성.

#### 추가 학습 : 'Controller' vs 'Resource'
- 왜 Controller가 아닌 Resource 일까?
  - Controller : 일반적인 MVC (Model-View-Controller) 패턴에서 주로 사용
  - Resource : RESTful API 설계에서 더 자주 사용
    - "리소스"라는 개념은 REST 아키텍처의 핵심 요소 중 하나이기 때문
      - 리소스 : 네트워크 상에서 고유하게 식별 가능한 모든 종류의 객체, 문서, 또는 서비스를 나타내는 개념적 언어.
    - Resource라는 용어는 REST의 핵심 개념을 더 잘 반영하며 클래스가 리소스에 대한 CRUD 작업을 처리한다는 것을 명확히 나타낼 수 있다.
    - 결과적으로는 두 용어 모두 잘 사용하며 팀 컨벤션에 따라 선택된다. `@RestController` 어노테이션을 통해 REST API의 명확성을 가져갈 수 있다.


#### 추가 학습 : 리소스 지향 아키텍처 & Uniform Interface 원칙
```java
@RestController
public class ShoppingController {

  @PostMapping("/addItemToCart")
  public void addItemToCart(@RequestParam Long userId, @RequestParam Long itemId, @RequestParam int quantity) {
    // 장바구니에 상품 추가 로직
  }

  @PostMapping("/removeItemFromCart")
  public void removeItemFromCart(@RequestParam Long userId, @RequestParam Long itemId) {
    // 장바구니에서 상품 제거 로직
  }

  @GetMapping("/getCartItems")
  public List<CartItem> getCartItems(@RequestParam Long userId) {
    // 사용자의 장바구니 아이템 목록 조회 로직
  }

  @PostMapping("/placeOrder")
  public OrderConfirmation placeOrder(@RequestParam Long userId) {
    // 주문 처리 로직
  }

  @GetMapping("/getOrderHistory")
  public List<Order> getOrderHistory(@RequestParam Long userId) {
    // 사용자의 주문 내역 조회 로직
  }

  @PostMapping("/cancelOrder")
  public void cancelOrder(@RequestParam Long orderId) {
    // 주문 취소 로직
  }
}
```
해당 코드는 리소스 지향 아키텍처 및 Uniform Interface 원칙을 따르지 않은 코드이다. 문제점을 살펴보자.
1. 엔드포인트가 마치 메서드 이름 처럼 동작을 나타내는 동사형 문법으로 서술되어 있다.
  - ROA를 어긴 것
2. HTTP 메서드가 제한 사용되어 있다.
  - ex) `@PostMapping("/removeItemFromCart")` : DELETE 메서드 대신 POST 메서드를 사용하고 엔드포인트로 remove 동작을 함을 알리고 있다.
  - Uniform Interface 원칙을 어긴 것
- 결과적으로 '리소스 지향 아키텍처' & 'Uniform Interface 원칙'을 어긴 API이다.

#### 추가 학습 : 리소스 지향 아키텍처(ROA, Resource-Oriented Architecture)
```java
// 기존
@PostMapping("/addItemToCart")

// 리소스 지향 아키텍처
@PostMapping("/carts/{userId}/items")
```
- 리소스를 중심으로 엔드포인트를 작성하면 추가적인 해석 없이 일관된 형태의 엔드포인트를 제공할 수 있다.

#### 추가 학습 : Uniform Interface 원칙
일관된 인터페이스를 사용해서 API의 사용성, 확장성, 플랫폼 독립성을 보장하는 설계 원칙
- HTTP 메소드를 통한 자원 조작 : GET, POST, PUT, DELETE 등의 HTTP 메소드를 사용하여 리소스에 대한 CRUD 작업을 수행해야 함
- 자기 서술적 메시지 : 요청과 응답은 자신을 설명할 수 있는 정보를 포함해야
  - ex) HTTP 메소드, HTTP 상태 코드, 헤더 정보, URL, 응답 본문, 하이퍼미디어 링크, 에러 메시지, 버전 정보
- HATEOAS : 응답에 관련 리소스의 링크를 포함해야 함
- 클라이언트-서버 분리 : 클라이언트와 서버는 독립적으로 발전할 수 있어야 함
- 상태 없음 (Stateless) : 각 요청은 독립적이며, 서버는 클라이언트의 상태를 저장하지 않아야 함 (서버와 클라이언트의 결합도를 낮추어 서버의 확장성이 향상된다.)
  - 각 API 요청은 필요한 모든 정보를 포함해야 한다.
  - 서버는 이전 요청의 컨텍스트를 저장하거나 사용하지 않는다.
  - 클라이언트는 매 요청마다 인증 정보를 포함해야 한다.
  - 서버는 클라이언트의 세션 상태를 저장하지 않는다.
  - JWT 등의 토큰 기반 인증을 사용하는 것으로 구현할 수 있다.
    - 완전한 무상태성은 실제 애플리케이션에서 달성하기 어려울 수 있으며, 인증, 권한 부여 등에서는 일정 수준의 상태 유지가 필요할 수 있다.

---

## 8단계 - React 앱에서 Spring Boot REST API로부터 Todo 표시하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/16d227c0e2cfa607b18d85f17b934a927db4d3bf)

#### TodoApiService 추가 및 API 호출
```js
//TodoApiService.js
import axios from "axios";

const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});

export const retrieveAllTodosForUsername = (username) => apiClient.get(`/users/${username}/todos`)
```

#### ListTodos 컴포넌트에서 Todos 노출하기
```jsx
const [todos, setTodos] = useState([]);

function refreshTodos() {
  retrieveAllTodosForUsername('eh13')
  .then ((response) => setTodos(response.data))
  .catch((error) => console.log(error))
  .finally(() => console.log("finally"))
}

useEffect(
        () => refreshTodos(), []
)
```
- `then ((response) => setTodos(response.data))` 으로 인해 useState에 API 응답 값이 저장된다.
- 컴포넌트 내에서 사용시 기존 `{todo.targetDate.toDateString()}`를 `{todo.targetDate.toString()}`으로 변경해야 한다.

#### useEffect
React의 함수형 컴포넌트에서 부수 효과(side effects)를 다루기 위한 Hook
```jsx
useEffect(
        () => refreshTodos(), []
)
```
- 컴포넌트가 렌더링 된 후에 useEffect() 내의 로직이 실행된다.
  - 첫 번째 렌더링을 포함하여 매 렌더링 후에 기본적으로 실행됨
- API 호출, 이벤트 리스너 등록/해제, DOM 조작 등 부수 효과를 처리한다.
- 인자
  - 첫 번째 인자 : 실행할 부수 효과 로직
  - 두 번째 인자 : 지정된 값들이 변경될 때만 useEffect가 실행 (배열로 전달)
    - 빈 배열([])을 전달하면 컴포넌트가 마운트될 때만 실행
    - 배열을 생략하면 매 렌더링마다 실행

#### 부록(1) : apiClient 분리하기
```js
const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});
```
해당 코드가 클라이언트의 'ApiService.js' 파일마다 중복으로 발생한다. 'UserApiService.js'가 추가되면 역시 중복으로 발생할 것이다.

```js
const todoApiClient = axios.create({
  baseURL: 'http://localhost:8080/todos'
});

const userApiClient = axios.create({
  baseURL: 'http://localhost:8080/users'
});
```
이와 같은 방법으로 작성하는 것은 어떨까?
- 모듈화되어 있어 리소스별로 다른 설정이 필요한 경우 유연하게 대처할 수 있다.
- 보다 명확하게 정의되어 있어 코드의 가독성이 높아진다.

이 경우 `/users/${username}/todos` API는 'userApiClient'에 속해야 한다.

#### 부록(2) : 'TodoResource'가 아닌 'UserResource'
```java
@RestController
public class TodoResource {
	private TodoService todoService;

	public TodoResource(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoService.findByUsername(username);
	}
}
``` 
- '/users/{username}/todos' API는 `TodoResource` 가 아닌 `UserResource`의 책임으로 변경하는 것이 고려될 수 있다. (이 경우 메서드 명도 보다 구체적으로 변경되어야 한다.)
  - /users 로 시작하는 엔드포인트 특성상 User 리소스가 중심이 되는 것으로 여겨지기 때문
  - Axios의 BaseURL 처럼 스프링 부트에도 각 컨트롤러의 Base 엔드포인트를 설정하는 기능이 있는데 이 경우 TodoResource는 "/v1/todos" 등으로 설정이 될 것이기 때문

#### 부록(3) : 무엇이 RESTful한 API 인가?
그런데 문제가 있다. 클라이언트 모듈상으로 Todo에 대한 리소스는 Todo 컴포넌트에 속한다. 그러나 API 호출 클라이언트는 User 클라이언트이다.

그럼 '/users/{username}/todos' 대신 '/todos/users/{username}'로 엔드포인트를 변경하는 것은 어떨까? 컨트롤러를 변경할 필요도 없고, 프론트엔드에서도 컴포넌트와 알맞은 것 같다.

하지만 아래 서술할 이유로 인해 권장되지 않는 방법이다.
- 리소스 계층 구조 및 관계의 명확성 : User:Todo 는 1:N 관계로 Todo가 User에 속하는 계층 구조를 나타내는 것이 좋다.
  - '/todos/users/{username}' : Todo에 속한 User 라는 해석이 가능할 수 있다.
- 일관성 : 다른 사용자 관련 엔드포인트와 일관된 구조를 유지할 필요가 있다.
  - '/todos/users/{username}' : 
    - 도메인이 더 생겨남에 따라 일관된 엔드포인트 작성이 힘들어 질 수 있다.
    - 일반적으로 RESTful API에서는 리소스를 먼저 명시하고 그 뒤에 식별자나 하위 리소스를 배치하는데, 해당 엔드포인트는 이러한 관행을 무시하는 것으로 보일 수 있다.
- 확장성 : 필요에 따라 /users/{id}/todos/{todo_id}와 같이 특정 할 일 항목에 접근하는 엔드포인트로 자연스럽게 확장 가능해야 한다.

#### 부록(4) : 중첩 리소스 모델(Nested Resource Model) - 리소스 간의 계층 구조 명시하기
만약 Todo 말고, Comment, Follower 등의 User와 연결되는 신규 도메인이 더 추가될 경우엔 어떻게 해야 할까?
```
'/users/{username}/todos'
'/users/{username}/comment'
'/users/{username}/follower'
```
이와 같은 방식을 '중첩 리소스 모델'이라고 할 수 있다. 
- 리소스간의 계층 구조를 명확하게 표현
- 소유 관계 직관적 표현 
- 직관적인 URL 구조 (읽기 쉬운 URL)

그러나 이 경우 `UserResource`가 방대해지며. 클라이언트 역시 동일한 문제를 겪을 것이다. 
추가로
- URI 복잡성 증가
- 확장성 제한
- 성능 문제

등의 문제들도 발생할 수 있다. 

#### 부록(5) : 평면 리소스 모델(Flat Resource Model) - 리소스 중심 설계 강화하기 
```
'/todos?username={username}'
'/comment?username={username}'
'/follower?username={username}'
```
이와 같은 방식으로 파람을 사용해서 `UserResource`의 책임을 각 리소스별로 분담하는 것이 가능하다.
- 각 리소스를 독립적인 엔티티로 취급한다.
- 더 유연하고 확장 가능한 API 설계가 가능하다.
- 복잡한 애플리케이션의 경우 더 적절하다.

하지만 평면 리소스 모델의 단점도 명확히 존재한다.
- 복잡한 관계 표현의 어려움 : 깊은 중첩 관계를 가진 데이터 구조를 표현하기 어려움
- 쿼리 파라미터가 복잡해질 경우 나타나는 문제
  - URL 가독성 저하
  - 캐싱 전략 난이도 상승
  - 서버 측 복잡성 증가
  - 보안 이슈
  - 성능 이슈

#### 부록(6) : 중첩 리소스 모델 vs 평면 리소스 모델
어떤 방식을 선택하는 것이 좋을까? "둘 다 많이 사용되기는 하지만 일반적으로 '중첩 리소스 모델'이 좀 더 RESTful 하다"는 의견이 많았다.
1. 명확한 리소스 계층 구조 표현
   - URL을 통해 리소스 계층 및 API의 응답을 예측하는 것이 가능하다.
2. 리소스 중심 URI
   - 쿼리 파라미터를 사용하는 설계는 일반적으로 리소스 중심 URI로 간주되지 않는다. (리소스 경로를 통한 URL이 더 권장된다.)
   - 쿼리 파라미터의 경우 주로 '필터링'에 사용된다는 것을 인지할 필요가 있다.

- 리소스 간의 관계가 중요하고, 계층 구조를 명확히 표현하고자 한다면 '중첩 리소스 모델'이 적합하다.
- 리소스에 대한 다양한 필터링이 필요하고, 유연성을 높이고자 한다면 '평면 리소스 모델'이 적합할 수 있다.

두 패턴 모두 각각의 장단점을 가지고 있다.

경우에 따라 '평면 리소스 모델'이 리소스 중심적인 RESTful의 원칙에 더 부합하다는 설명을 듣기도 했다.

#### 부록(7) : 중요한 것은 '클라이언트'와의 소통이다.
UX(User Experience)라는 용어가 있다. '사용자 경험'을 의미하는 것으로 유저 편의성 등을 나타내고, 고객과 직접적으로 맞닿아 있는 프론트엔드 개발자에게는 필수 덕목으로 여겨진다. (백엔드 개발자 역시 UX를 중요하게 고려해야 한다.) 
백엔드 개발자에게 있어서 가장 앞선 단계의 클라이언트(고객)는 프론트엔드 개발자라는 점을 항상 기억해야 한다. 
즉, 백엔드 설계에 있어서 '프론트엔드 개발자의 편의성' 역시 중요한 고려 사항이라는 것이다. 
평면 리소스 모델의 경우 리액트를 사용하는 프론트엔드 개발자가 보다 리소스 모델 별로 컴포넌트를 설계하기에 더 원활할 수 있다. 그러니 경우에 따라서는 평면 리소스 모델을 선택하는 것을 고려할 수 있는 것이다. 
패턴의 정답은 없기 때문에 설계를 할 때 협업하는 팀과의 충분한 소통을 통해 내부의 명확한 컨벤션과 규칙을 만들고, 이를 일관되게 지키는 것이 중요하다.

API 설계는 상황과 요구사항에 따라 유연하게 접근해야 하며, 절대적인 RESTful API를 개념적으로만 추구하기 보다는 서비스 관점에서 다양한 의견을 조합하여 설계하는 것이 중요하다는 결론을 내린다.

---

## 9단계 - Todo를 받고 삭제하는 Spring Boot REST API 메서드 만들기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/d2924d297ced38f6e7b64fac71848fc0c17fbb07)

#### 구현 실습
```java
@RestController
public class TodoResource {
    //...(생략)
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
		return todoService.findById(id);
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
		todoService.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
```
두 API 모두 '/users/{username}' 부분이 필요 없으며 현재 사용되지도 않는다. 강의 코드를 유지하기 위해 리팩토링은 하지 않았다.

---

## 10단계 - React 프론트엔드에 삭제 기능 추가하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/79983a03b58ee93038e62fd1d87a7f25dda99f95)

#### Todo 삭제 구현
```jsx
//TodoApiService.js
export const deleteTodoApi = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)

//ListTodos.jsx
//...(생략)
function deleteTodo(id) {
  deleteTodoApi('eh13', id)
}
//...(생략)
<tbody>
{
  todos.map((todo) => (
          <tr key={todo.id}>
            //...(생략)
            <td><button className="btn btn-warning" onClick={() => deleteTodo(todo.id)}>삭제</button>
            </td>
          </tr>
  ))
}
</tbody>
//...(생략)
```

#### Todo 삭제 후 완료 메시지 리턴 & 리스트 업데이트
```jsx
//...(생략)
const [message, setMessage] = useState("");

//...(생략)
function deleteTodo(id) {
  deleteTodoApi('eh13', id)
  .then(
          () => {
            refreshTodos();
            setMessage(`삭제가 완료되었습니다.`)
          }
  )
}

//...(생략)
{message && <div className="alert alert-success">{message}</div>}
```
- `{message && <div className="alert alert-success">{message}</div>}` : 메시지가 존재할 경우 메시지 div를 노출

---

## 11단계 - username을 React 인증 컨텍스트에 설정하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/fcb259c5826693e22feb4573348a6000f28fae59)

#### 구현 실습
```js
//AuthContext.js
export default function AuthProvider({children}) {
  const [isAuthenticated, setAuthenticated] = useState(false)
  const [username, setUsername] = useState(null)

  function login(username, password) {
    const isLoginSuccess = username === 'eh13' && password === '950127'
    setUsername(username);
    setAuthenticated(isLoginSuccess);
    return isLoginSuccess;
  }

  function logout() {
    setAuthenticated(false);
    setUsername(null);
  }

  return (
          <AuthContext.Provider value={{isAuthenticated, login, logout, username}}>
            {children}
          </AuthContext.Provider>
  )
}
```

```jsx
//ListTodos.jsx

const authContext = useAuth();
const username = authContext.username;

function refreshTodos() {
  retrieveAllTodosForUsernameApi(username)
  .then((response) => setTodos(response.data))
  .catch((error) => console.log(error))
}

function deleteTodo(id) {
  deleteTodoApi(username, id)
  .then(
          () => {
            refreshTodos();
            setMessage(`삭제가 완료되었습니다.`)
          }
  )
}
```

---

## 12단계 - Todo 페이지를 표시하기 위한 Todo React 컴포넌트 만들기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/de013e953050cefd7f1cb3c72bc207c39d2baa2e)

#### 구현 실습
1. api 호출
    ```js
    //TodoApiService.js 
    
    export const retrieveTodoApi = (username, id) => apiClient.get(`/users/${username}/todos/${id}`)
    ```
2. 'TodoDetail' 컴포넌트 추가
    ```jsx
    export default function TodoDetail() {
      const authContext = useAuth();
      const username = authContext.username;
      const {id} = useParams();
    
      const [description, setDescription] = useState('');
    
      function retrieveTodo() {
        retrieveTodoApi(username, id)
        .then((response) => {
          setDescription(response.data.description)
        })
        .catch((error) => console.log(error))
      }
    
      useEffect(
              () => retrieveTodo(),[id]
      )
    
      return (
              <div className="container">
                <h1>TODO 상세</h1>
                <div>
                  목표 : {description}
                </div>
              </div>
      )
    }
    ```
    - `() => retrieveTodo(),[id]` : id가 변경될 때마다 `retrieveTodo()` 실행
3. Route 등록
    ```jsx
    //TodoApp.jsx
   
    import TodoComponent from "./todos/TodoDetail";
    //...(생략)
    <Route path="/todo/:id" element={
      <AuthenticatedRoute>
        <TodoComponent />
      </AuthenticatedRoute>
    } />
    ```
4. 네비게이트 연결
    ```jsx
    //ListTodos.jsx
    
    function updateTodo(id){
      navigate(`/todo/${id}`);
    }
    ```
    - `updateTodo()`를 버튼의 onClick 함수로 지정하여 사용할 수 있다.
---

## 13단계 - Todo React 컴포넌트를 표시하기 위해 Formik 및 Moment 라이브러리 추가하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/1d1a2bfe513dd4eb66dacf8ab4240538b52a1943)

#### npm 라이브러리 설치
- [formik](https://www.npmjs.com/package/formik) : 폼(form) 구현을 간소화, 관리 및 유효성 검사 지원 라이브러리
- [moment](https://www.npmjs.com/package/moment) : 날짜 관련 라이브러리 (주의 : 현재 레거시 프로젝트이며, 유지 관리 모드에 있음)
  - 강의에서는 'moment'를 사용했지만 더 이상 개발이 되지 않는 라이브러리이다.
  - Luxon, Day.js, date-fns, js-Joda 등의 라이브러리로 대체하는 것이 좋다.

#### formik 사용해서 form 작성
```jsx
import {Formik, Form, Field} from "formik";

export default function TodoDetail() {
  //...(생략)
  function onSubmit(values) {
    console.log(values)
  }
  
  //...(생략)
  return (
      <div className="container">
        <h1>TODO 상세</h1>
        <Formik initialValues={{description, targetDate}} enableReinitialize={true} onSubmit={onSubmit}>
          {
            (props) => (
              <Form>
                <fieldset className="form-group">
                  <label>할 일</label>
                  <Field className="form-control" type="text" name="description"/>
                </fieldset>
                <fieldset className="form-group">
                  <label>목표 일자</label>
                  <Field className="form-control" type="date" name="targetDate"/>
                </fieldset>
                <div>
                  <button className="btn btn-success m-5" type="submit">저장</button>
                </div>
              </Form>
            )
          }
        </Formik>
      </div>
  )
}
```
- initialValues : 초기값 설정
- enableReinitialize : initialValues가 변경될 때마다 폼의 상태를 재설정
- onSubmit : form이 제출되었을 때 실행될 함수 지정

---

## 14단계 - Formik을 이용하여 Todo React 컴포넌트에 검증 추가하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/41a8e5b86d76263278a432a7cc4db11d370d2c85)

#### 밸리데이션 실습
```jsx
function validate(values){
  let errors = {

  };
  if(values.description.length < 5){
    errors.description = '할 일은 5글자 이상 작성해 주십시오.';
  }
  if(values.targetDate == ''){
    errors.targetDate = '목표 일자를 입력해주세요';
  }
  return errors;
}

//...(생략)
  return (
        <div className="container">
          <h1>TODO 상세</h1>
          <Formik
                  initialValues={{description, targetDate}}
                  enableReinitialize={true}
                  onSubmit={onSubmit}
                  validate={validate}
                  validateOnChange={false}
                  validateOnBlur={false}
          >
            {
              (props) => (
                      <Form>
                        <ErrorMessage
                                name="description"
                                component="div"
                                className="alert alert-warning"
                        />
                        <ErrorMessage
                                name="targetDate"
                                component="div"
                                className="alert alert-warning"
                        />
                      {/* ...(생략) */}
                      </Form>
              )
            }
          </Formik>
        </div>
)
```
- 'onSubmit'을 사용하던 방식과 비슷하다. (함수 연결)
- 'ErrorMessage' 컴포넌트를 사용해서 에러 메시지를 노출할 수 있다,
- validateOnChange : 필드 값이 변경될 때마다 유효성 검사 수행 여부
- validateOnBlur : 필드에서 포커스가 벗어날 때(blur 이벤트 발생 시) 유효성 검사 수행 여부

---

## 15단계 - Spring Boot 백엔드 API에 Todo 업데이트 및 생성 REST API 추가하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/eedd0e9c1d6a268fb9d33454a135417190ccf78b)

#### 실습
```java
@RestController
public class TodoResource {
    //...(생략)
  
    @PostMapping("/users/{username}/todos")
	public Todo addTodo(@PathVariable String username, @RequestBody Todo todo) {
		return todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
		todo.setId(id);
		todo.setUsername(username);
		todo.setDone(false);
		todoService.updateTodo(todo);
		return ResponseEntity.noContent().build();
	}
	
    //...(생략)
}
```

---

## 16단계 - React 프론트엔드에 업데이트 기능 추가하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/09e30718af62653a2a031a2f74256b2931f1d11f)

#### API 호출 등록
```js
export const updateTodoApi = (username, id, todo) => apiClient.put(`/users/${username}/todos/${id}`, todo)
```
- todo 파라미터 포함.

#### TodoDetail form의 onSubmit에서 API 사용하기
```jsx
  function onSubmit(values) {
  const todo = {
    id: id,
    username: username,
    description: values.description,
    targetDate: values.targetDate,
    done: false
  }
  updateTodoApi(username, id, todo)
}
```
- 백엔드에서 이미 id, username, done에 대한 처리를 해놓았기에 'values'만 넣어줘도 괜찮지만 일반적인 구현법으로 작성하였다.

#### 수정 완료 후 Todo 리스트로 리다이렉트 시키기
```jsx
function onSubmit(values) {
  const todo = {
    id: id,
    username: username,
    description: values.description,
    targetDate: values.targetDate,
    done: false
  }
  updateTodoApi(username, id, todo)
  .then((response) => {
    navigate('/todos')
  })
  .catch((error) => console.log(error))
}
```
- 'then' 체이닝을 통해 성공했을 경우에만 이동하도록 설정할 수 있다.

---

## 17단계 - React 프론트엔드에 새로운 Todo 생성 기능 추가하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/3af1106fa0de6ba150c37823f2be303c283cc84b)

#### 구현 실습
```jsx
//ListTodos.jsx
function addNewTodo(){
  navigate(`/todo/-1`);
}

//TodoDetail.jsx
function retrieveTodo() {
  if(id != -1){
    retrieveTodoApi(username, id)
    .then((response) => {
      setDescription(response.data.description)
      setTargetDate(response.data.targetDate)
    })
    .catch((error) => console.log(error))
  }
}

function onSubmit(values) {
  const todo = {
    id: id,
    username: username,
    description: values.description,
    targetDate: values.targetDate,
    done: false
  }

  if(id == -1){
    createTodoApi(username, todo)
    .then((response) => {
      navigate('/todos')
    })
    .catch((error) => console.log(error))
  }else {
    updateTodoApi(username, id, todo)
    .then((response) => {
      navigate('/todos')
    })
    .catch((error) => console.log(error))
  }
}
```
- addNewTodo() 함수는 /todo/-1 로 리다이렉트 한다. (버튼에 연결됨)
- 만약 todo의 id가 -1 일 경우 수정 대신 신규 생성 로직을 수행한다.

#### 날짜 밸리데이션
```jsx
import moment from "moment";

function validate(values){
  let errors = {

  };
  if(values.description.length < 5){
    errors.description = '할 일은 5글자 이상 작성해 주십시오.';
  }
  if(values.targetDate == ''){
    errors.targetDate = '목표 일자를 입력해주세요';
  }
  if(!moment(values.targetDate).isAfter()){
    errors.targetDate = '목표 일자는 현재 날짜보다 과거일 수 없습니다.'
  }
  return errors;
}
```

---

## 18단계 - Spring Security로 Spring Boot REST API 보호하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/3e11cbfd08f9a59c5ef5f854ebbc6964094fa4a5)

#### Spring Security 추가
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- 백엔드 프로젝트 'pom.xml'에 Spring Security 라이브러리 추가

#### Spring Security 기본 계정 정보 변경하기
```properties
spring.security.user.name=eh13
spring.security.user.password=950127
```

#### Security 필터 체인 설정
```java
@Configuration
public class BasicAuthenticationSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.build();
	}
}

```
- authorizeHttpRequests : HTTP 요청에 대한 권한 부여 규칙 설정 (모든 요청 인증 필요)
- httpBasic : HTTP 기본 인증 활성화 (기본 설정 사용 : 브라우저 팝업창으로 인증 요구함)
- sessionManagement : 세션 관리 정책 설정 (STATELESS : 무상태 - 서버에서 세션 생성하지 않음)
- csrf : CSRF(Cross-Site Request Forgery) 보호를 설정 (비활성화)

---

## 19단계 - Spring Boot REST API 호출을 위해 React에 인증 헤더 추가하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/424d6c6804b31c1a557c706366f81689153e87ef)

#### API 요청에 인증 헤더 추가하기
```js
export const retrieveHelloWorldPathVariable
        = (username) => apiClient.get(`/hello-world/path-variable/${username}`,{
  headers: {
    Authorization: 'Basic ZWgxMzo5NTAxMjc='
  }
})
```
- 이렇게 헤더를 추가해도 인증을 통과하지 못한다.

#### 인증 에러
```
Access to XMLHttpRequest at 'http://localhost:8080/hello-world/path-variable/eh13' from origin 'http://localhost:3000' has been blocked by CORS policy: Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.
```
- "Response to preflight request doesn't pass access control check"
  - Preflight request 실패 : 서버에 preflight request를 보냈지만, 서버의 응답이 액세스 제어 검사를 통과하지 못함
- "No 'Access-Control-Allow-Origin' header is present on the requested resource"
  -  실제 요청이 CORS 정책 위반으로 인해 차단됨

총 두 개의 에러가 발생한다.

#### Preflight request
CORS(Cross-Origin Resource Sharing) 정책에 따라 브라우저가 자동으로 생성하여 보내는 추가적인 요청 (실제 요청이 안전한지 서버에 확인하는 역할)
-  실제 요청을 보내기 전에 먼저 OPTIONS 메서드로 Preflight request를 보낸다.
  -  Preflight request에는 실제 요청에 대한 정보(HTTP 메서드, 헤더 등)가 포함된다.
- 서버는 이 Preflight request에 대해 다음과 같은 응답을 반환해야 한다.
  - Access-Control-Allow-Origin: 허용되는 origin 정보 
  - Access-Control-Allow-Methods: 허용되는 HTTP 메서드 정보 
  - Access-Control-Allow-Headers: 허용되는 요청 헤더 정보

---

## 20단계 - 모든 OPTIONS 요청을 허용하도록 Spring Security 설정하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/6500c42f8c1f2d4080ca7039cf0e8e6ea8d42efe)

#### OPTIONS HTTP 메서드
주어진 URL 또는 서버에 대해 허용된 통신 옵션을 요청하는 HTTP 메서드 대표적으로 'Preflight request'가 있다.

#### Spring Security에서 OPTIONS 요청을 허용 설정
```java
@Configuration
public class BasicAuthenticationSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(
						auth -> auth
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.build();
	}
}
```
- `.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()` 코드가 설정 부분이다.
  - .requestMatchers : 특정 패턴의 요청에 대한 접근 권한을 설정
    - 첫 번째 파라미터 : HTTP 메서드가 OPTIONS 인 경우
    - 두 번째 파라미터 : 모든 엔드포인트
  - .permitAll() : 접근을 허용함
- Spring Boot 버전이 '3.0.0-M4' 라면 `requestMatchers` 대신 `antMatchers`을 사용해야 한다.

---

## 21단계 - React 앱에 로그인할 때 기본 인증 서비스 호출하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/4eef4ae75c8d27e8fb65f754e0ad6faece34126c)

#### AuthContext::login() 개선
1. 백엔드 API 추가
    ```java
    @GetMapping(path = "/basicauth")
    public String basicAuthCheck() {
        return "Success";
    }
    ```
    - 인증 성공 시 리턴하기 위한 API (추후 토큰 사용 방식과 비교를 위한 API)
2. 프론트엔드 API 호출 추가
    ```js
    export const executeBasicAuthenticationService
        = (token) => apiClient.get(`/basicauth`,{
          headers: {
            Authorization: token
          }
        })
    
    ```
3. AuthContext 에서 basic 토큰 넘겨주기 실습
    ```js
    function login(username, password) {
      const basicToken = 'Basic ' + window.btoa(username + ":" + password)
    
      executeBasicAuthenticationService(basicToken)
      .then((response) => console.log(response))
      .catch((error) => console.log(error))
    
      setAuthenticated(false)
    }
    ```
    - window.btoa : base64 인코딩 메서드

---

## 22단계 - async와 await를 사용하여 기본 인증 API 호출하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/d3eb973f6939d41f33f4e721f4cf50b8f6734ba7)

```js
async function login(username, password) {
  const basicToken = 'Basic ' + window.btoa(username + ":" + password)

  try {
    const response = await executeBasicAuthenticationService(basicToken)

    if(response.status === 200) {
      setAuthenticated(true)
      setUsername(username)
      return true
    }
    else {
      setAuthenticated(false)
      setUsername(null)
      return false
    }
  } catch (error) {
    setAuthenticated(false)
    setUsername(null)
    return false
  }

}
```
`executeBasicAuthenticationService()`를 통한 인증 응답이 완료될 때까지 기다리기 위해 async와 await를 사용했다.
- async : 함수를 비동기적으로 실행한다.
- await : 해당 작업이 완료될 때까지 코드가 일시 중지된다.
  - async 안에서만 사용이 가능하다.
- `login()` 함수를 사용하는 외부에서도 async, await를 함께 사용해주어야 한다.
  ```jsx
  async function handleSubmit() {
    if(await authContext.login(username, password)) {
      navigate(`/welcome/${username}`);
    }
    else {
      setShowErrorMessage(true);
    }
  }
  ```
  
#### useState에 토큰 담기 실습
```js
const [token, setToken] = useState(null)
```
useState() 선언하고 API 요청이 성공적으로 완료 시 setToken()를 사용해서 token을 담을 수 있다.

---

## 23단계 - AuthContext에 기본 인증 토큰 설정하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/a33be9a904617b051287315db60a1661a101c631)

#### 실습
```js
//HelloWorldApiService.js
export const retrieveHelloWorldPathVariable
    = (username, token) => apiClient.get(`/hello-world/path-variable/${username}`,{
      headers: {
        Authorization: token
      }
    })

//Welcome.jsx
const authContext = useAuth();
function callHelloWorldPathVariable() {
  retrieveHelloWorldPathVariable(params.username, authContext.token)
  .then ((response) => successfulResponse(response))
  .catch((error) => failedResponse(error))
  .finally(() => console.log("finally"))
}
```

---

## 24단계 - 인증 헤더를 추가하기 위해 Axios 인터셉터 설정하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/7b54de82b766a4b60ef33dc223fec0d8a1c990ca)

#### 공통 ApiClient 생성
```js
import axios from "axios";

export const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});
```
- 다른 ApiService에서는 `import {apiClient} from "../api/ApiClient";`를 통해 공통 ApiClient를 사용할 수 있다.

#### 공통 ApiClient 헤더에 토큰 담기 실습
```jsx
apiClient.interceptors.request.use(
(config) => {
  config.headers.Authorization = basicToken
  return config
})
```
- `AuthContext::login()` 함수의 성공 부분에 해당 코드를 추가한다.
- interceptors : 요청과 응답을 가로채서 처리하기 위한 기능
  - 요청이 전송되기 전이나 응답이 받아지기 전에 요청/응답을 수정하거나 추가적인 로직을 실행할 수 있다.
  - 헤더 추가, 요청 데이터 변환, 로깅 등의 작업을 처리할 수 있게 해준다.

---

## 25단계 - JWT 및 Spring Security 시작하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/b63905775bd714264c4af6e88413ab6197efa58a)

#### Basic 토큰의 단점
- 만료 기한이 없다.
- 사용자 세부정보를 토큰에 담을 수 없다.
- Base64 인코딩 방식으로 쉽게 디코딩도 가능하다.

결론적으로 Basic 토큰은 프로덕션 환경에 적합하지 않다.

#### JWT(Json Web Token)
토큰을 만드는 표준 시스템 정의
- 사용자 세부정보 및 인증을 담는 것이 가능
- 해싱 알고리즘 사용
- 만료 시간 설정 가능

#### 라이브러리 추가 (JWT 관련)
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```
- JWT 토큰을 검증하고 인증된 사용자에게 리소스에 대한 액세스 권한을 부여하기 위해 사용

#### **중요!** 강의 코드 트러블슈팅
해당 단계는
[강의 코드](https://github.com/in28minutes/master-spring-and-spring-boot/blob/main/13-full-stack/99-reuse/02-spring-security-jwt.md)를 제공한다.

그러나 강의 코드를 그대로 적용하면 `JwtSecurityConfig` 파일에서 이슈가 발생한다.

- 프로젝트 seed 파일의 Spring Boot 버전이 '3.0.0-M4'이다.
- 제공된 코드의 `JwtSecurityConfig`에는 `requestMatchers`가 사용되는데 '3.0.0-M4' 버전에서는 `requestMatchers` 대신 `antMatchers`을 사용해야 한다.
  - 그러나 `antMatchers`로 바꿔도 JWT 인증이 정상적으로 동작하지 않는다.

해결 방법
1. Spirng Boot 버전을 최신 버전으로 업그레이드 한다. (필자는 3.3.1 버전 사용)
2. `JwtSecurityConfig::securityFilterChain()`을 아래 코드로 변경한다.
    ```java
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/authenticate")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/*")).permitAll() // h2-console is a servlet and NOT recommended for a production
                        .requestMatchers(new AntPathRequestMatcher("/**", "OPTIONS")).permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .httpBasic(Customizer.withDefaults())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .build();
    }
    ```
3. 강의 실습을 진행한다.

---

## 26단계 - Spring Security JWT REST API와 React 프론트엔드 통합하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/01bafb74918bb9ccebfc0d5cf4f74a4c2312e47b)

#### jwt 토큰 생성 API 호출부 등록
```js
export const executeJwtAuthenticationService = (username, password) => apiClient.post(`/authenticate`, {username, password})
```
- 주의할 점은 get이 아닌 post로 요청해야 한다는 것이다.

#### AuthContext.js 인증 객체 jwt로 변경
```jsx
  async function login(username, password) {
  try {
    const response = await executeJwtAuthenticationService(username, password)

    if(response.status === 200) {
      const jwtToken = "Bearer " + response.data.token
      setAuthenticated(true)
      setUsername(username)
      setToken(jwtToken)

      apiClient.interceptors.request.use(
          (config) => {
            config.headers.Authorization = jwtToken
            return config
          })

      return true
    }
    else {
      logout()
      return false
    }
  } catch (error) {
    logout()
    return false
  }

}
```

---

## 27단계 - JWT 인증 및 Spring Boot 문제 해결하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/3201c2c81973dd4044a29d1b2261f9f56a42a2fe)

강의 내용은 JWT 실습에 있어 문제를 겪는 학습자를 위한 간단한 가이드가 포함되어 있으며 이전 단계를 충실하게 수행했다면 필요하지 않은 내용이다.

나는 JWT 관련 로직에 대한 설명으로 해당 단계 학습 노트를 대체하고자 한다.

#### 파일구성
컨트롤러(JwtAuthenticationController), 서비스(JwtTokenService), 설정(JwtSecurityConfig), DTO(JwtTokenRequest, JwtTokenResponse) 으로 구성되어 있다.

- JwtTokenRequest, JwtTokenResponse
  - DTO의 역할을 수행하며, [Java record](../01_Getting_Started_with_Java_Spring_Framework/README.md#레코드-record)로 구현되어 있다.
  - JwtTokenRequest : 토큰 구성에 필요한 username, password를 필드로 가진다.
  - JwtTokenResponse : 토큰을 필드로 가진다.

- JwtAuthenticationController
  - JWT 발급(POST '/authenticate') REST API를 담당하는 컨트롤러  
    - JwtTokenRequest(username, password)를 받아서, JwtTokenResponse(token)을 반환하는 API
  - AuthenticationManager : Spring Security에서 제공하는 사용자 인증 처리를 위한 인터페이스
    - 다양한 인증 제공자(AuthenticationProvider)를 사용하여 인증을 수행함.

- JwtTokenService
  - JwtEncoder : Spring Security OAuth2 라이브러리에서 제공하는 JWT 생성을 위한 인터페이스
  - generateToken() : Authentication 인증 객체를 받아서 토큰을 생성하는 메서드

- JwtSecurityConfig
  - JWT 토큰 로직에 필요한 각종 설정을 담당
    - securityFilterChain 설정, 토큰 서명 및 검증 등의 설정이 포함되어 있음

#### 컨트롤러 : JwtAuthenticationController 내부 코드 살펴보기
```java
Authentication authenticationToken =
        new UsernamePasswordAuthenticationToken(
                jwtTokenRequest.username(),
                jwtTokenRequest.password());
```
- jwtTokenRequest의 username과 password를 기반으로 `Authentication` 객체를 만든다. 
  - UsernamePasswordAuthenticationToken 객체는 Authentication를 상속하고 있다.
  - 여기서는 username, password를 검증하지 않는다. 그냥 주어진 정보로 Authentication 객체를 만들 뿐이다.
    - 해당 상태의 Authentication 객체는 단순히 사용자 입력 정보를 캡슐화 할 뿐, 인증이 된 상태가 아니다. (isAuthenticated() 메서드 호출 시 false 반환.)

```java
Authentication authentication =
        authenticationManager.authenticate(authenticationToken);
```
- 앞서 생성한 Authentication 객체를 AuthenticationManager의 authenticate() 메서드로 인증하는 과정을 수행한다.
- AuthenticationManager는 원래 인터페이스 이지만, 의존성이 주입될 때 JwtSecurityConfig에서 Bean으로 등록한 AuthenticationManager의 구현체(ProviderManager)가 주입된다.
  ```java
  //JwtSecurityConfig
  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService);
      return new ProviderManager(authenticationProvider);
  }
  ```
   - 결과적으로 주입되는 것은 `ProviderManager` 이다. 즉, ProviderManager의 authenticate() 메서드를 사용하게 된다.

```java
String token = tokenService.generateToken(authentication);
```
- TokenService의 generateToken() 메서드로 인증 객체를 기반으로 토큰을 생성한다. 

#### 서비스 : JwtTokenService 내부 코드 살펴보기
```java
String scope = authentication
        .getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));
```
- 컨트롤러를 통해 제공된 인증 객체(Authentication)에서 권한 목록을 가져와 스트림을 통해 모든 권한을 문자열로 추출한다.
  - sout을 통해 콘솔에 scope를 출력하면 'ROLE_USER'가 노출된다. 

```java
JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(Instant.now())
        .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();
```
- JWT의 클레임을 설정하는 로직
  - 클레임(claims) : 토큰에 포함될 정보를 의미한다.
  - issuer : 발행자
  - issuedAt : 발행 시간
  - expiresAt : 만료 시간
  - subject : 식별자 
    - 고유한 값으로 설정해야 하며, 일반적으로 User의 id 등으로 설정한다.
    - 해당 식별자를 통해 토큰 소유자를 판단할 수 있어야 한다.
  - claim : 커스텀 키-밸류 쌍을 넣을 수 있다.

```java
return this.jwtEncoder
        .encode(JwtEncoderParameters.from(claims))
        .getTokenValue();
```
- 앞서 작성한 claims으로 토큰을 생성하고, 토큰을 문자열로 리턴.
- jwtEncoder : 컨트롤러의 `AuthenticationManager`와 마찬가지로 JwtSecurityConfig 에서 등록한 Bean이 의존성 주입에 사용된다.
  ```java
  //JwtSecurityConfig
  @Bean
  JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
      return new NimbusJwtEncoder(jwkSource);
  }
  ```
  - 결과적으로는 `NimbusJwtEncoder` 구현체의 `encode()` 메서드가 실제로 동작한다.
    - NimbusJwtEncoder : Spring Security OAuth2에서 제공하는 jwtEncoder의 구현체 (일반적으로 권장됨)
    - JWKSource<SecurityContext> : JWT 서명에 사용되는 암호화 키를 제공하는 소스
      - JWKSource 또한 JwtSecurityConfig에서 구현체를 Bean으로 등록하고 있다.
        ```java
        @Bean
        public JWKSource<SecurityContext> jwkSource() {
            JWKSet jwkSet = new JWKSet(rsaKey());
            return (((jwkSelector, securityContext)
                    -> jwkSelector.select(jwkSet)));
        }
        ```
        - NimbusJwtEncoder의 encode()를 사용할 때 JWKSet를 통해 토큰에 '서명'을 하게 된다.
        - 토큰 서명 : 토큰의 내용이 변경되지 않았음을 보장하며 내용을 암호화 하지는 않는다.
        - 서명 암호화 키 : JWKSet는 서명을 위한 암호화 키를 제공하는데, 이는 가짜 서명을 방지하기 위함이다.

#### 설정 파일 : JwtSecurityConfig
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
```
- 클래스에 부여된 어노테이션이다.
  - @Configuration : 해당 클래스가 Spring 설정 클래스임을 명시하는 어노테이션 (Bean 등록 역시 Spring 설정 중 한 부분이다.)
    - 하나 이상의 @Bean 메서드를 포함할 수 있으며, Spring IoC 컨테이너에 의해 Bean 정의의 소스로 처리된다.
  - @EnableWebSecurity : Spring Security를 활성화한다.
    - Spring Security의 기본 설정을 오버라이드 해서 커스텀 설정으로 보안 정책을 정의할 것임을 Spring에게 알리는 목적이다.
  - @EnableMethodSecurity : 메서드 수준의 보안을 활성화 한다.
    - Java 메서드를 의미하며, 특정 메서드의 보안(특정 권한만 메서드 실행 가능)을 설정할 수 있다.
    - API 역시 컨트롤러의 메서드이기 때문에 JWT 사용자 권한에 따라 API 접근을 커스텀할 수 있다. 

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
  return httpSecurity
          .authorizeHttpRequests(auth -> auth
                  .requestMatchers(new AntPathRequestMatcher("/authenticate")).permitAll()
                  .requestMatchers(new AntPathRequestMatcher("/h2-console/*"))
                  .permitAll() // h2-console is a servlet and NOT recommended for a production
                  .requestMatchers(new AntPathRequestMatcher("/**", "OPTIONS")).permitAll()
                  .anyRequest()
                  .authenticated())
          .csrf(AbstractHttpConfigurer::disable)
          .sessionManagement(session -> session.
                  sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
          .httpBasic(Customizer.withDefaults())
          .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
          .build();
}
```
SecurityFilterChain을 커스터마이즈하여 Spring Security의 기본 설정을 오버라이드
- authorizeHttpRequests : 요청에 대한 접근 가능 여부 설정
  - '/authenticate' 엔드포인트 '/h2-console/'로 시작하는 모든 엔드포인트, 모든 OPTIONS HTTP 메서드 요청은 인증이 필요 없으며, 나머지 요청은 전부 인증이 필요하다.
  - 서비스 API 중에서는 JWT 발급을 담당하는 '/authenticate' API 만 오픈해서 인증된 사용자만 서비스 API에 접근할 수 있도록 하고 있다.
- csrf : csrf 보안 설정을 비활성화 했다.
  - JWT 인증 방식으로 대체 가능하며, 세션을 저장하지 않기 때문에 비활성화 하는 것이 적절하다.
  - 특정 클라이언트에게만 열어주는 방식으로 사용하는 것이 더 안전하다.
- sessionManagement : 세션을 저장하지 않는 것으로 설정했다.
  - JWT를 매 요청마다 전송하기 때문에 필요한 정보가 있다면 세션에 저장하는 대신 JWT에 포함하는 것이 더 적절하다.
- oauth2ResourceServer : JWT를 사용하는 OAuth2 리소스 서버를 구성한다. (Spring Security OAuth2에서 제공)
  - 리소스 서버 : 보호된 리소스(예: 사용자 데이터, API 엔드포인트)를 호스팅하는 서버
  - OAuth2 리소스 서버는 '서블릿 필터' 처럼 요청이 API에 전달되기 전에 요청에 포함된 토큰을 검증하는 로직을 수행한다.
- httpBasic : Basic 인증을 활성화한다.
- headers : 헤더 설정, X-Frame-Options 헤더에 대해 같은 출처만 허용한다. (동일한 ip와 포트에서만 허용 h2-콘솔 대응)

```java
@Bean
public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    return new ProviderManager(authenticationProvider);
}
```
- 컨트롤러에서 사용하는 `AuthenticationManager` 구현체 Bean 등록 메서드
- UserDetailsService : 사용자 정보 (데이터 베이스 등에서 사용자 정보를 가져오는 역할을 수행한다.)
- DaoAuthenticationProvider : 인증 처리를 위한 프로바이더 구현체, 사용자 이름과 비밀번호를 기반으로 동작한다. (AbstractUserDetailsAuthenticationProvider 인터페이스를 상속함) 
  - setUserDetailsService() : 제공된 사용자 정보를 바탕으로 인증 정책을 설정한다.
- `return new ProviderManager(authenticationProvider)` : 사용자 정보를 바탕으로 생성된 인증 정책을 가지고 실제 검증을 진행하는 클래스.

결과적으로 해당 Bean은 실제 사용자 정보를 기반으로 인증 정책을 생성한다. <br>
해당 인증 정책은 '사용자 이름'과 '비밀번호'를 기반으로 인증을 진행하며, 실제 사용자 정보는 파라미터로 들어오는 userDetailsService에 담겨 있다. <br>
이후 컨트롤러에서는 해당 Bean이 주입되면서 ProviderManager의 authenticate() 메서드를 사용해서 <br>
요청 데이터의 username과 password와 ProviderManager 인스턴스의 userDetailsService의 사용자 정보가 일치하는지 검증한다. <br>

```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("eh13")
            .password("{noop}950127")
            .authorities("read")
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(user);
}
```
- 인증 로직이 발생할 때 AuthenticationManager Bean에 인자로 넘겨지는 사용자 정보
  - DB 연결을 하지 않고 하드코딩으로 사용자 정보를 설정했다.
- 빌더를 통해 사용자 정보를 설정한다.
  - withUsername : 해당 사용자의 이름 (비밀번호와 함께 인증에 사용됨)
  - password : 해당 사용자의 비밀번호 (이름과 함께 인증에 사용됨)
    - {noop} : 비밀번호 인코딩(암호화)를 하지 않음을 의미한다.
  - authorities : 해당 사용자의 권한 (여러 개 설정 가능)
    - 직접적으로 특정 작업이나 리소스에 대한 접근 권한을 정의한다.
    - 정의한 권한은 사용자가 관련 로직을 작성하지 않는 이상 임의로 사용자의 행동을 제한하는 것은 아니다.
  - roles : 해당 사용자의 역할 (여러 개 설정 가능)
    - "ROLE_" 접두사가 자동으로 추가된다. ex) ROLE_USER
    - 내부적으로 `.authorities("ROLE_USER")`로 변환된다.
    - !주의! authorities, roles는 코드와 같이 설정한 후 별도의 권한 처리 로직을 작성하지 않으면 실제 보안 효과가 없으며 그냥 이름 붙이기에 불과하다. 
      - Spring Security에서 권한에 기반한 별도 동작을 사전 정의하지 않으며 개발자의 몫이다.
- InMemoryUserDetailsManager : 인메모리용 사용자 정보 관리 클래스이다.
  - `UserDetailsManager`를 상속하며, `UserDetailsManager`는 `UserDetailsService`를 상속한다.

```java
@Bean
public JWKSource<SecurityContext> jwkSource() {
    JWKSet jwkSet = new JWKSet(rsaKey());
    return (((jwkSelector, securityContext)
            -> jwkSelector.select(jwkSet)));
}
```
- JWT 서명에 필요한 암호키(JSON Web Key)를 설정하는 Bean 등록 메서드
  - JWKSource(JSON Web Key Source) : JWK를 제공하는 소스로, 보안 컨텍스트(SecurityContext)와 함께 사용됨
  - JWKSet : 여러 JWK를 포함하는 객체
    - `rsaKey()` 메서드(RSA 키 쌍을 생성)를 사용해서 JWKSet의 인스턴스를 생성 
      - rsaKey 메서드도 같은 파일 내에 정의되어 있으며 후술할 것이다.
  - return문 : JWKSource가 인터페이스이므로 익명 구현체를 람다식으로 리턴한다.
    ```java
    JWKSource<SecurityContext> jwkSource = (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    ```
    - 해당 코드와 동일하다.
    ```java
    JWKSource<SecurityContext> jwkSource = new JWKSource<SecurityContext>() {
        @Override
        public List<JWK> get(JWKSelector jwkSelector, SecurityContext securityContext) throws KeySourceException {
            return jwkSelector.select(jwkSet);
        }
    };
    ```
    - 코드를 더 풀면 이렇게 작성할 수 있다.

```java
@Bean
public RSAKey rsaKey() {

    KeyPair keyPair = keyPair();

    return new RSAKey
            .Builder((RSAPublicKey) keyPair.getPublic())
            .privateKey((RSAPrivateKey) keyPair.getPrivate())
            .keyID(UUID.randomUUID().toString())
            .build();
}

@Bean
public KeyPair keyPair() {
    try {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    } catch (Exception e) {
        throw new IllegalStateException(
                "Unable to generate an RSA Key Pair", e);
    }
}
```
- RSAKey : RSA알고리즘을 사용하는 키 객체 (개인키, 공개키 둘 다 존재함)
  - 내부에서 keyPair를 사용하고 있으며 공개키와 개인키, keyID를 객체에 담아서 빌드하고 있다.
  - 공개키는 JWT 서명 검증에, 개인키는 서명 생성에 사용된다.
    - 개인키는 서명을 작성, 수정할 수 있는 권한을 가지며, 공개키는 검증할 권한만을 가진다. 서명의 불변성과 신뢰성을 위해 키를 분리한다.
  - 고유한 키를 식별하기 위해서 UUID를 포함한다.
- KeyPair : 공개키와 개인키 쌍을 생성하는 Bean 등록 메서드
  - KeyPairGenerator를 설정해서 리턴한다.
    - getInstance("RSA") : 생성할 키를 RSA 키로 정한다.
    - initialize(2048) : 생성할 키의 크기를 2048bit로 정한다. (보안 강도 설정)
    - generateKeyPair() : 설정된 값으로 실제 키를 생성한다. (생성된 키는 RSAKey의 개인키, 공개키로 사용됨)

결론적으로 rsaKey() 메서드는 keyPair() 메서드에서 생성된 키 쌍(공개키-개인키 쌍)을 사용하여 RSAKey 객체를 생성하는 로직이다.

```java
@Bean
JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
    return new NimbusJwtEncoder(jwkSource);
}

@Bean
JwtDecoder jwtDecoder() throws JOSEException {
    return NimbusJwtDecoder
            .withPublicKey(rsaKey().toRSAPublicKey())
            .build();
}
```
- JWT 인코딩, 디코딩 담당 Bean 등록 메서드
- 각각 일반적으로 사용되는 구현체를 사용해서 로직을 수행하고 있다.
- JwtEncoder의 경우 상기한 JWKSource를 파라미터로 받아서 사용하고 있다. (토큰 인코딩 중 서명을 포함할 때 JWKSource에 포함된 `rsaKey()`의 개인키를 사용해서 서명을 작성)
- JwtDecoder에서는 withPublicKey() 메서드를 통해 토큰의 서명을 검증한다. JWKSource에 담긴 것과 동일한 `rsaKey()`의 공개 키가 검증 과정에서 쓰인다.
  - JwtDecoder는 다른 코드에서 사용되는 것을 볼 수 없는데 필터체인에 의해 등록된 OAuth2 리소스 서버 동작에 자동 의존성 주입이 되어서 토큰 검증에 기여한다.

#### JWT 노트 회고
- JWT 구현에 있어 jjwt 와 같은 라이브러리를 사용하는 것도 고려할 수 있다.
- 강의 코드는 실제 프로덕트에서 사용할 수 없는 부분이 있으니 주의가 필요하다. ex) UserDetailsService 하드 코딩
- JWT는 만료시간을 짧게 설정하고, 리프레시 토큰을 추가하는 운영법도 고려할 수 있다.
- JWT는 내부 클레임이 노출되기 쉽기 때문에 클레임에 예민한 정보를 담아서는 안된다.

---