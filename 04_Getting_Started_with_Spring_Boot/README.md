# 📒 [학습 노트] 챕터 4 : Spring Boot 시작하기

## 1단계 - Spring Boot 시작하기 - 목표

#### 질문
- Spring Boot 가 없어도 웹 애플리케이션과 REST API를 빌드할 수 있다. 그렇다면 왜 Spring Boot를 사용할까?
- Spring Boot의 목표는 무엇일까?
- Spring Boot는 어떻게 동작할까?
- Spring Boot vs Spring MVC vs Spring

#### 챕터 학습 과정
1. Spring Boot 없이 빌드된 애플리케이션은 어땠을지
2. Spring Boot를 활용해서 프로젝트를 만들어보기 
3. Spring Boot를 활용해서 간단한 REST API를 구현해보기
4. Spring Boot의 마법 같은 개념을 이해하기
   - 스프링 이널라이져 (Spring Initializr)
   - 스타터 프로젝트 (Starter Projects) 
   - 자동 설정 (Auto Configuration)
   - 개발 도구 (Developer Tools)
   - 액추에이터 (Actuator)
   - ...

## 2단계 - Spring Boot 이전 세계 이해 - 대략적으로 알아보기

Spring Boot 전에 Spring 프로젝트를 설정하는 작업은 쉽지 않았다.
#### 첫 번째 어려움 : 의존성
![old-dependency-managemment.png](image/old-dependency-managemment.png)
pom.xml에서 프레임워크와 버전을 관리해야 함

- REST API : Spring 프레임워크와 Spring MVC 프레임워크, JSON 바인딩 프레임워크, 로깅 등이 필요
- 단위 테스트 : Spring Test 프레임워크, Mockito, JUnit 등이 필요

#### 두 번째 어려움 : web.xml
![web-xml-file.png](image/web-xml-file.png)
웹 애플리케이션의 많은 것을 설정하기 위해 필요함
- Spring MVC를 활용하려는 경우
- DispatcherServlet을 설정하는 경우

#### 세 번째 어려움 : Spring 설정
![spring-configuration.png](image/spring-configuration.png)
여러 설정을 적절하게 지정해야 애플리케이션 사용 가능
- 컴포넌트 스캔 정의
- 뷰 리졸버 정의 (웹 애플리케이션의 경우)
- 데이터 소스 정의 (데이터베이스 관련 경우)
- ...

#### 네 번째 어려움 : 비기능 요구사항 고려 (NFRs)
![NFRs.png](image/NFRs.png)
아래의 기능을 수동 구현해야 함
- 로깅
- 에러 처리
- 모니터링

#### 그리고...
- 이 모든 작업은 새로운 프로젝트를 만들 때마다 반복해야 했었다.
- 이러한 작업을 설정하는 데에는 며칠씩 걸리는게 일반적었다.
- 유지보수에 어려움을 겪었다.