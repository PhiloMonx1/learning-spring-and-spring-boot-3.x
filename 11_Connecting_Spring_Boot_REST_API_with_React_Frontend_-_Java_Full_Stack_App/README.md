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

---

## 1단계 - React 풀 스택 애플리케이션을 위해 Todo REST API 프로젝트 설정하기

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

3. 프로젝트 실행
모듈 불러오기가 끝난 후 'RestfulWebServicesApplication' 애플리케이션을 실행하고, ['/hello-world'](http://localhost:8080/hello-world) GET API를 확인한다.

---

## 2단계 - React Hello World 컴포넌트에서 Spring Boot Hello World REST API 호출하기

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