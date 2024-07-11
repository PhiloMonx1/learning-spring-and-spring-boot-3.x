# 📒 [학습 노트] 챕터 15 : Spring Security로 Spring Boot 앱 보호하기

## 목록
0. [Spring Security 시작하기](#0단계---spring-security-시작하기)
1. [보안의 기초 이해하기](#1단계---보안의-기초-이해하기)
2. [보안의 원칙 이해하기](#2단계---보안의-원칙-이해하기)
3. [Spring Security 시작하기](#3단계---spring-security-시작하기)
4. [Spring Security 기본 설정 살펴보기](#4단계---spring-security-기본-설정-살펴보기)
5. [Spring Security용 Spring Boot 프로젝트 생성하기](#5단계---spring-security용-spring-boot-프로젝트-생성하기)
6. [Spring Security 살펴보기 - 폼 인증](#6단계---spring-security-살펴보기---폼-인증)
7. [Spring Security 살펴보기 - 기본 인증](#7단계---spring-security-살펴보기---기본-인증)
8. [Spring Security 살펴보기 - 크로스 사이트 요청 위조](#8단계---spring-security-살펴보기---크로스-사이트-요청-위조)
9. [Spring Security 살펴보기 - REST API에서의 CSRF](#9단계---spring-security-살펴보기---rest-api에서의-csrf)

---

## 0단계 - Spring Security 시작하기

#### 챕터 학습 목표
1. 6가지 보안 원칙 : 애플리케이션을 빌드할 때 고려해야 할 중요 보안 사항
2. 인증과 권한 부여의 차이
3. Spring Security의 기초 살펴보기
   - Spring Security Filter Chain이 무엇인지
   - Spring Security가 애플리케이션에 들어오는 요청을 모두 인터셉트하는 이유는 무엇인지
4. 다양한 종류의 인증 방식 (비교 및 차이점)
   - 폼 인증
   - 기본 인증
   - JWT 인증
5. 모범 사례
   - CSRF 
   - CORS
   - ...
6. OAuth

---

## 1단계 - 보안의 기초 이해하기

#### 인증
- 사용자가 기억할 수 있는 정보 제공
  - ID/PW
- 사용자가 가진 소유물이나 생체 정보를 기반으로 인증
  - 홍채, 지문 인식, 모바일 앱 등
- 다단계 인증 : 다양한 요소 결합
  - SNS 인증 등
    - Google SNS 인증의 경우 Google 계정 정보를 사용자가 기억해야 하고, 계정 권한을 소유한 모바일 기기 등으로 다른 애플리케이션의 인증을 시도할 수 있다.

#### 권한 부여
인증된 사용자에게 액세스 권한을 부여해, 사용자가 인증된 후 적절한 액세스 권한을 가지고 있는지 판단하여 특정 작업에 대한 접근 여부를 설정하는 것
ex) '사용자 A, B, X' 는 '데이터를 읽는 것'만 가능하고, '사용자 C, F, T'는 '데이터를 읽고, 수정하는 것'이 가능하다.

#### 인증과 권한의 차이 비유로 알아보기
- 케이스 1 : 공항
  - 인증 : 여권, 신분증
  - 권한 : 항공권, 탑승권 
- 케이스 2 : 놀이공원 테마파크
  - 인증 : 신분증, 예약 or 결제 정보 확인
  - 권한 : 입장권, 프리패스, VIP 티켓

---

## 2단계 - 보안의 원칙 이해하기

#### 보안 원칙 6계명
1. Trust Nothing : 무엇도 신뢰하지 말라
    - 모든 입력과 시스템 구성 요소(요청)를 의심하고 검증해야 한다.
2. Least Privileges : 최소 권한만 할당하라
    - 사용자나 프로세스에게 필요한 최소한의 권한만을 부여해야 한다.
    - 각 사용자에게 필요한 사용자 역할과 액세스 권한을 명확히 정해야 한다.
3. Complete Mediation : 완전 매개를 구축하라
    - 요청이 들어올 때마다 액세스 권한을 확인해야 한다.
    - 중세 시대의 수성에 비유해보자. 모든 사람이 하나의 정문을 통해 요새에 출입할 수 있다.
        - 이와 유사하게 애플리케이션이나 시스템에도 효과적으로 구현된 보안 필터가 필요하다.
4. Defense In Depth : 심층적인 방어를 구축하라
    - 전송 레이어 -> 네트워크 레이어 -> 인프라 등으로 층을 나누어 모든 곳에 보안을 적용해야 한다.
    - ex) 사용자 개인정보를 DB에 저장할 때 암호화를 적용해서 만약에 DB 데이터가 탈취 당해도 개인정보가 탈취 당하지 않도록 한다.
5. Economy Of Mechanism : 보안 아키텍처를 가능한 한 간단하게 유지하라
    - 간단한 시스템을 보호하기가 더 쉽다. 복잡할 수록 취약점 노출도가 증가한다.
    - 매커니즘의 효율성을 추구하라.
6. Openness Of Design : 설계의 개방성을 보장하라.
    - 보안 시스템의 설계와 구현을 비밀로 유지하는 것이 더 나은 보안을 제공한다는 잘못된 믿음을 버려야 한다.
        - 보안 메커니즘의 설계는 공개되어 검토될 수 있어야 한다.
        - 여러 보안 전문가들이 협업하여 검토하고 보완할 수 있도록 하는 것이 유리하다.
    - ex) JWT : [jwt.io](https://jwt.io/) 에서 토큰의 페이로드를 보는 것이 가능하다.

---

## 3단계 - Spring Security 시작하기

#### Spring MVC 작동 방식
![Request -> Dispatcher Servlet -> Controller(s)](image/MVC_Works.png)
- Spring 웹 애플리케이션으로 오는 모든 요청은 '디스패처 서블릿'에서 처리한다.

#### Spring Security 작동 방식
![Request -> Spring Security -> Dispatcher Servlet -> Controller(s)](image/Security_Works.png) 
- 중간 레이어(Spring Security )가 추가 된다.
  - 디스패처 서블릿 전에 Spring Security가 요청을 인터셉트한다.
- Spring Security에 설정된 필터 체인이 요청을 처리한다.
  - 이 과정에서 인증과 권한 부여가 동작한다.
  - 인증, 권한 확인이 완료되면 디스패처 서블릿으로 요청을 전송한다.

---

## 4단계 - Spring Security 기본 설정 살펴보기

#### Spring Security Filter Chain
요청이 들어왔을 때 Spring Security가 실행 시키는 일련의 필터 처리 과정
- 인증 : 요청자가 적절한 사용자인지 확인한다. ex) BasicAuthenticationFilter
- 권한 확인 : 요청자가 요청에 필요한 적절한 권한을 가지고 있는 확인한다. ex) AuthorizationFilter
  - URL 패턴을 통해 판단하기 때문에 해당 API의 서비스 로직을 알지 못해도 처리가 가능하다.
- 보안 관련 베스트 프랙티스
  - CORS(Cross-Origin Resource Sharing) 설정 ex) CorsFilter
  - CSRF(Cross-Site Request Forgery) 설정 ex) CsrfFilter
  - Login Page, Logout Page 설정 (기본 제공되며 커스텀 가능)
  - Http 응답에 대한 다양한 예외 처리 ex) ExceptionTranslationFilter

필터는 특정한 순서대로 실행하며 심층적으로 동작한다.
- ex) 기본 필터(CORS, CSRF) -> 인증 -> 권한 확인 -> 디스패처 서블릿

필터체인에서 애플리케이션 전역의 보안 설정을 담당하여 서비스 로직에서는 보안 설정 코드를 신경 쓸 필요 없이 관심사를 분리할 수 있다. (AOP)

---

## 5단계 - Spring Security용 Spring Boot 프로젝트 생성하기

#### 프로젝트 생성
![Spring initializer 세팅](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 빌드 도구를 'Gradle - Groovy'로 설정한다.
- 라이브러리 목록
  - Spring Web
  - Spring Security

---

## 6단계 - Spring Security 살펴보기 - 폼 인증

#### Spring Security 기본 로그인
```java
@RestController
public class HelloWorldResource {

	@GetMapping("/login")
	public String hello() {
		return "Hello World";
	}
}
```
'/login' 엔드포인트로 접근하면 "Hello world"를 출력하는 간단한 GET API이다.

![Spring Security 기본 로그인](image/SpringSecurity_BasicLogin.png)
- api로 접근 시 선언된 메서드가 아닌 Spring Security의 기본 로그인 Form이 노출된다.
- 심지어 존재하지 않는 URL 엔드포인트를 입력해도 해당 로그인 페이지로 리다이렉트 되는 것을 볼 수 있다.
  - API 사용자는 인증이 되기 전에는 엔드포인트가 유효한지 조차 볼 수 없다.
  - 실제 서비스 로직에 도달하기 전에 Spring Security 필터 체인이 인증을 확인을 수행하기 때문이다.
  - 'Complete Mediation - 완전 매개' 보안 윈칙을 지키는 것이다.

#### Form 기반 인증
로그인 `<form>`에 자격 증명을 입력한 후 제출하면 자격증명을 확인하고 인증하는 방식
- Spring Security 의 디폴트 인증 방식
- 작동 원리
  - Spring Security 기본적으로 '/login', '/logout' 페이지를 제공한다.
  - 로그인 시 해당 사용자에 대해 쿠키가 생성된다. ex) JSESSIONID : BCC81C00DD10079468F3BF57594595B6
    - Spring Security의 AuthenticationFilter(ex : UsernamePasswordAuthenticationFilter)가 이 요청을 가로챈다.
    - 입력된 자격 증명을 바탕으로 Authentication 객체를 생성하고, 이 때 AuthenticationManager(AuthenticationProvider) 등이 일한다.
    - 인증이 성공되면 해당 Authentication 객체는 SecurityContext에 저장되고, SecurityContextPersistenceFilter를 사용해서 HTTP 세션에 저장한다.
  - 해당 세션 쿠키는 요청과 함께 전송된다.
  - '/logout' 을 통해 로그아웃을 진행하면 서버 측에서 현재 사용자의 세션을 무효화한다. (세션 정보 삭제를 의미함)
    - Spring Security의 SecurityContext(ex : SecurityContextHolder)에서 현재 인증 정보가 제거된다.

---

## 7단계 - Spring Security 살펴보기 - 기본 인증

#### Basic 인증 기법
- 사용자가 입력한 자격 증명 정보를 Base 64 인코딩해서 전달한다.
  - 헤더 'Authorization' Key, 'Basic {Base 64 인코딩 자격증명}' Value 형태로 요청과 함께 전송된다.

#### Basic 인증의 문제점
![password](image/password.png)
![base64 디코드](image/base64decode.png)
- Base 64 문자열은 디코드가 쉽다.
- 만료기간이 없다.
- 사용자 액세스 권한이나 역할에 관한 정보가 없다.

---

## 8단계 - Spring Security 살펴보기 - 크로스 사이트 요청 위조

#### CSRF 공격
브라우저 쿠키 등에 인증 정보가 저장된 상태로 악성 웹사이트에 접근했을 때, 해당 웹사이트가 사용자 의도와 상관 없이 쿠키에 액세스 후 위조된 요청을 보내는 공격.
- 일반적으로 브라우저 쿠키의 자동 전송 시스템을 악용해서 공격하는 방식이다.
  - 브라우저는 요청을 처리할 때 해당 도메인에 대한 쿠기를 자동으로 전송한다. (HTTP 프로토콜의 기본 동작 방식)
- 공격 시나리오 예시
  - 은행에 로그인 한다. (쿠키에 인증 정보가 저장된다.)
  - 로그아웃을 하지 않은 상태로 웹 서핑을 하다가 악성 웹사이트에 접속한다.
  - 악성 웹사이트는 사용자의 브라우저를 통해 은행 서버로 위조된 요청을 보내도록 유도한다.
    - ex) '쿠폰 받기' 등의 위장된 버튼을 클릭하게 만든다. -> 버튼은 사실 은행 사이트의 계좌 이체 버튼과 동일한 로직을 수행한다. (쿠키에 저장된 인증 정보를 헤더에 담아 계좌 이체 API를 요청하는 로직)
  - 브라우저는 은행 도메인에 대한 저장된 쿠키를 자동으로 요청에 포함시켜 보낸다.
  - 은행 서버는 이 요청을 정상적인 사용자의 요청으로 인식하고 처리한다.
- 주요 포인트
  - 쿠키에 저장된 인증 정보를 따로 식별하거나 탈취하지 않으며 그대로 사용하는 것이다.
    - 쿠키에 저장된 인증 정보의 암호화에 영향을 받지 않는다. (인증 정보를 위조하는 개념이 아니기 때문)

#### CSRF 공격 방지
1. CSRF 토큰 : 동기화 토큰 패턴
   - 쿠키가 아닌 다른 방식으로 저장되는 토큰을 추가 발급한다.
     - HTML 폼의 hidden 필드, JavaScript 변수 등
     - 브라우저의 자동 쿠키 전송 메커니즘을 우회한다.
   - 클라이언트는 요청 시 쿠키에 저장되는 인증 객체와 함께 CSRF 방지 토큰을 함께 전송해야 한다.
   - CSRF 토큰을 매 요청마다 갱신하도록 하면 보안이 상승한다.
   - POST, PUT, DELETE 등의 요청에서만 CSRF 토큰을 확인하도록 할 수 있다.
2. 다른 출처(Cross-Site | Cross-Origin)의 요청 제한
   - 요청에는 출처(HTTP 프로토콜, IP-도메인, 포트번호) 등이 포함된다.
   - 보호받아야 할 요청의 경우 약속된 클라이언트의 출처만 허용하고 나머지는 제한하는 방식으로 방지할 수 있다.
3. SameSite 쿠키 속성 사용
   - 쿠키에 SameSite 속성을 설정하여 크로스 사이트 요청에서 쿠키 전송을 제한할 수 있다.
   - 브라우저의 쿠키 전송 정책에 의존하지 않고, Strict, Lax, None 등의 옵션을 통해 쿠키 전송 정책을 세밀하게 제어하는 방식이다.
4. Double Submit Cookie & 사용자 상호작용 요구
   - 중요 동작의 경우 CSRF 토큰을 쿠키와 요청 파라미터 모두에 포함시켜서 쿠키 만으로 중요한 요청을 처리할 수 없도록 제한하는 방식이다.
   - 중요 동작의 경우 사용자에게 비밀번호를 다시 묻는 방식으로도 일부 방지가 가능하다. ex) 사용자 정보 변경 시 패스워드를 한 번 더 입력 등

이러한 방법들을 구현할 때 성능과 사용자 경험에 미치는 영향도 고려해야 한다. 모든 방식을 사용하면 보안이 높아지지만, 그만큼 시스템 부하 및 사용자의 불편함도 증가할 수 있다.

---

## 9단계 - Spring Security 살펴보기 - REST API에서의 CSRF

#### Spring Security의 기본 CSRF 방어 방식
Spring Security는 CSRF 토큰을 사용한 방어 방식을 기본 값으로 수행한다.
![Spring Security 로그아웃 페이지 HTML](image/logout.png)
- Spring의 기본 로그아웃이 from으로 구현되어 있다. 
- 로그아웃 form 내부에 hidden input 필드로 CSRF 토큰이 삽입되어 있다.
  - Spring에서 CSRF 토큰을 자동으로 생성해 추가한 것이다.
  - GET 메서드 API에서는 CSRF 토큰을 확인하지 않으나 POST, PUT의 경우 자동으로 확인한다.
    - 직접 작성한 API 역시 POST, PUT 메서드를 사용한다면 CSRF 토큰 검증을 수행하므로, 토큰 없이 요청 시 401에러를 노출하게 된다.

#### CSRF 토큰 생성 실습
```java
@RestController
public class SpringSecurityPlayResource {

	@GetMapping("/csrf-token")
	public CsrfToken retrieveCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
}
```
- HttpServletRequest : 현재 요청의 정보를 받을 수 있다.
- getAttribute("_csrf") : '_csrf'라는 이름의 요청 속성을 지정한다.
  - 서버 측에서 요청 객체에 추가하는 데이터이며, 클라이언트가 직접 설정하거나 접근할 수는 없다.
- 주의 : API로 CSRF를 직접 노출시키는 것은 지양해야 한다.
  - 해당 코드는 Spring Security의 모든 요청에는 자동으로 CSRF 토큰이 포함되는 것을 보여주기 위함이다.
    - Attribute에 토큰이 포함되는 것은 서버 측에서 처리하는 것으로 실제 토큰을 사용해서 요청을 성공시키기 위해선 헤더에 담아야 한다.
  - Spring Security는 자동으로 폼 기반 제출에 CSRF 토큰을 포함시킨다.
    - RESTful API를 사용하는 경우, 일반적으로 다른 방식(예: 세션 기반 인증 대신 토큰 기반 인증 사용)으로 CSRF 공격을 방지한다.

![csrf 토큰 노출](image/csrf-token.png)
- 'X-CSRF-TOKEN'를 Key로, 노출된 토큰을 Value로 헤더에 추가해서 요청을 보내면 요청이 통과된다.

---