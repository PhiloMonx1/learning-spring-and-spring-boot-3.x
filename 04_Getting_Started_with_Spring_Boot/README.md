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

## 3단계 - Spring Initializer로 새 Spring Boot Project 설정하기

#### Spring Boot 프로젝트 만들기
[spring initializer](https://start.spring.io/)
![spring-boot-crate.png](image/spring-boot-crate.png)
- 가장 최신 버전의 Release를 사용하는 것을 권장.
- Java 17 이상부터 Spring Boot 3을 사용할 수 있다.
- Spring Web 라이브러리를 추가한다.
  - Spring MVC로 웹 애플리케이션과 REST API를 빌드할 때 사용하는 라이브러리
  - Apache Tomcat을 임베디드 컨테이너로 사용

#### 인텔리제이에서 모듈 추가
[챕터1 ReadME 3단계 참고](..%2F01_Getting_Started_with_Java_Spring_Framework%2FREADME.md)

#### 애플리케이션 실행
-`LearnSpringBootApplication` 애플리케이션을 실행한다.

![run-spring-web.png](image/run-spring-web.png)
- 8080 포트의 Tomcat 서버 실행을 성공했다고 나타난다.

![localhost-8080.png](image/localhost-8080.png)
- http://localhost:8080/ 주소로 접근시 'Whitelabel Error Page'가 나타나면 성공이다.
  - 포트 번호가 다르다면 해당 포트 번호 주소로 접근해야 한다.

## 4단계 - Spring Boot를 사용하여 Hello World API 빌드하기

#### 만들고자 하는 API
![example-api.png](image/example-api.png)

1. `Course` 클래스 생성
[Course.java](..%2F00_module%2Flearn-spring-boot%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Fspringboot%2Flearn_spring_boot%2FCourse.java)
2. 컨트롤러 작성
```java
@RestController
public class CourseController { }
```
컨트롤러 클래스에 `@RestController` 어노테이션을 부여한다.
3. 메서드에 리퀘스트 매핑
```java
@RestController
public class CourseController {

	@RequestMapping("/courses")
	public List<Course> retrieveAllCourses() {
		return Arrays.asList(
				new Course(1, "Learn AWS", "in28minutes"),
				new Course(2, "Learn DevOps", "in28minutes")
		);
	}
}
```
실행할 메서드에 `@RequestMapping` 어노테이션을 부여해서 매핑한다.
4. API 확인
![courses-api-check.png](image/courses-api-check.png)
`RequestMapping`에 매핑된 '/courses' 경로에서 API를 확인할 수 있다.