# 📒 [학습 노트] 챕터 2 : Spring Framework를 사용하여 Java 객체를 생성하고 관리하기

## 목차
1. [Spring Framework Beans의 지연 초기화와 즉시 초기화 알아보기](#1단계---spring-framework-beans의-지연-초기화와-즉시-초기화-알아보기)
2. [지연 초기화와 즉시 초기화 비교하기](#2단계---지연-초기화와-즉시-초기화-비교하기)
3. [Java Spring Framework Bean 스코프 - 프로토타입 및 싱글톤](#3단계---java-spring-framework-bean-스코프---프로토타입-및-싱글톤)
4. [프로토타입과 싱글톤 비교하기 - Spring Framework Bean 스코프](#4단계---프로토타입과-싱글톤-비교하기---spring-framework-bean-스코프)
5. [Spring Bean 알아보기 - PostConstruct 및 PreDestroy](#5단계---spring-bean-알아보기---postconstruct-및-predestroy)
6. [Jakarta EE의 발전 - J2EE 및 Java EE와 비교](#6단계---jakarta-ee의-발전---j2ee-및-java-ee와-비교)
7. [Spring Framework 및 Java를 통해 Jakarta CDI 알아보기](#7단계---spring-framework-및-java를-통해-jakarta-cdi-알아보기)
8. [Java Spring XML 설정 알아보기](#8단계---java-spring-xml-설정-알아보기)
9. [Java 어노테이션과 XML 설정 알아보기 - Java Spring Framework](#9단계---java-어노테이션과-xml-설정-알아보기---java-spring-framework)
10. [Spring Framework 스테레오타입 어노테이션 - Component 등](#10단계---spring-framework-스테레오타입-어노테이션---component-등)
11. [간단한 복습 - 중요한 Spring Framework 어노테이션](#11단계---간단한-복습---중요한-spring-framework-어노테이션)
12. [간단한 복습 - 중요한 Spring Framework 개념](#12단계---간단한-복습---중요한-spring-framework-개념)
13. [Spring 전체 구조 알아보기 - Framework, 모듈, 프로젝트](#13단계---spring-전체-구조-알아보기---framework-모듈-프로젝트)


## 1단계 - Spring Framework Beans의 지연 초기화와 즉시 초기화 알아보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/53b457cffcdc4ebf54ec0ab4d0b0c95853351807)

#### 즉시 초기화 (Eager Initialization)
- Spring Bean의 기본 초기화 방식
```java
@Component
class ClassA { }
@Component
class ClassB {
	private ClassA classA;
	public ClassB(ClassA classA) {
		System.out.println("초기화를 진행합니다.");
		this.classA = classA;
	}
}
@Configuration
@ComponentScan
public class LazyInitializationContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(
				LazyInitializationContextLauncherApplication.class)) {
		}
	}
}
```
![Eager-Initialization-test.png](image/Eager-Initialization-test.png)
- `LazyInitializationContextLauncherApplication` 에서는 `context`를 선언할 뿐 `ClassB`에 대한 호출이 없다.
- Spring 컨텍스트를 실행하면 기본적으로 초기화가 적용된다. (객체의 Bean이 생성될 때 자동 초기화)

#### 지연 초기화 (Lazy Initialization)
```java
@Component
@Lazy
class ClassB {
	private ClassA classA;
	public ClassB(ClassA classA) {
		System.out.println("초기화를 진행합니다.");
		this.classA = classA;
	}
}
```
- `@Lazy` 어노테이션을 부여해서 초기화를 지연시킬 수 있다.
- `getBean()` 메서드를 통해 `ClassB` 클래스의 Bean을 호출할 때 초기화가 진행된다.
- `@Component`를 부여한 클래스나 `@Bean`을 부여한 메서드에 사용할 수 있다.
  - `@Configuration` 클래스에도 사용 가능

#### 지연 초기화 특징
- 기본적으로 제공되는 즉시 초기화를 사용하는 것이 권장된다.
  - Spring 구성에 오류가 있을 경우 애플리케이션 실행 단계에서 오류를 조기 발견할 수 있기 때문.
- 실제 의존성 대신 '해결 프록시(Lazy-resolution proxy)'가 주입된다.
  - 실제 의존성 객체와 동일한 인터페이스를 구현하고 있다.

## 2단계 - 지연 초기화와 즉시 초기화 비교하기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/372420e9e812cd8e2763e276b0e635aac08940e0)

#### 지연 초기화 (Lazy Initialization)
- 초기화 시점 : Bean이 애플리케이션에서 처음 호출될 때
- 기본 값 아님
- 명시적 사용 방법 : @Lazy | @Lazy(value=true)
- 예외 처리 방식 : 런타임 단계
- 사용빈도 : 드물게 사용됨
- 메모리 : Bean이 실제로 호출 될 때 등록 (절약)
- 시나리오 : 애플리케이션에 사용 빈도가 적은 Bean의 경우

#### 즉시 초기화 (Eager Initialization)
- 초기화 시점 : 애플리케이션의 시작 (Spring 컨텍스트 시작)
- 기본 값
- 명시적 사용 방법 : @Lazy(value=false)
- 예외 처리 방식 : 컴파일 단계
- 사용빈도 : 기본 사용됨
- 메모리 : 애플리케이션 실행 단계에서 모든 Bean을 미리 등록
- 시나리오 : 일반적인 Bean의 경우

## 3단계 - Java Spring Framework Bean 스코프 - 프로토타입 및 싱글톤
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/29ab4cf794ada89f92a56f148d6882c7326485a6)

#### 프로토타입 스코프 (Prototype)
```java
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass { }
```
![Get-Prototype-Bean.png](image/Get-Prototype-Bean.png)
- `ConfigurableBeanFactory.SCOPE_PROTOTYPE`는 "prototype" 문자열을 리턴한다.
- 프로토타입 Bean은 호출 할 때마다 다른 해시 값을 가진다. (새로운 인스턴스를 생성)

#### 싱글톤 스코프 (Singleton)

```java
@Component
class NormalClass { }
```
![Get-Singleton-Bean.png](image/Get-Singleton-Bean.png)
- Spring의 기본 값이다.
- 호출할 때마다 새로운 인스턴스를 생성하는 것이 아닌 고유한 인스턴스를 참조한다.
- Spring IoC 컨테이너 당 Bean 객체의 인스턴스가 단 하나.

#### 웹 애플리케이션에서 사용되는 특수한 스코프
- 리퀘스트 (Request) : 'HTTP 요청' 당 하나의 인스턴스가 사용됨
- 세션 (Session) : '사용자 HTTP 세션' 당 하나의 인스턴스가 사용됨
- 애플리케이션 (Application) : '웹 애플리케이션 전체'에 하나의 인스턴스가 사용됨.
- 웹소켓 (Websocket) : '웹소켓 인스턴스' 당 하나의 인스턴스가 사용됨.

#### Java Singleton (GOF) vs Spring Singleton
- 자바 싱글톤은 '디자인 패턴' 이다.
- Java 싱글톤과 Spring 싱글톤의 차이
  - 자바 싱글톤 : JVM 당 객체 인스턴스가 하나.
  - 스프링 싱글톤 : Spring IoC 컨테이너 하나에 객체 인스턴스가 하나.
    - JVM에 Spring IoC 컨테이너를 하나만 실한다면 Java 싱글톤과 같은 의미가 될 수 있다. 
    - 일반적으로 JVM에 여러 개의 Spring IoC 컨테이너를 사용하지는 않기 때문에 99.99%의 경우 Java 싱글톤과 같다.

## 4단계 - 프로토타입과 싱글톤 비교하기 - Spring Framework Bean 스코프
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/0f1f79be93b7358091ecf5f9636a1df9f9b100f0)

#### 프로토타입 (Prototype)
- 인스턴스 갯수 : Spring IoC 컨테이너 당 여러 개
- Bean : 호출 할 때마다 새로운 인스턴스
- 기본 값 아님
- 사용 방법 : @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
- 사용 시나리오 : Stateful beans (상태 정보를 가지고 있는 Bean)
  - ex) 사용자 정보의 경우 사용자마다 별도의 Bean을 생성해야 함
  - 주의점
    - 요청 간 상태 정보를 유지해야 하므로, 스레드 안전성(Thread-safe)을 고려
    - 상태 정보를 관리하는 메커니즘이 필요 (HTTP 세션, 데이터베이스 등)

#### 싱글톤 (Singleton)
- 인스턴스 갯수 : Spring IoC 컨테이너 당 하나
- Bean : 하나의 인스턴스를 다시 사용
- Spring 기본 값
- 사용 방법 : @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) | 디폴트
- 사용 시나리오 : Stateless beans (상태 정보를 가지고 있지 않은 Bean)

## 5단계 - Spring Bean 알아보기 - PostConstruct 및 PreDestroy
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/ade0506bd3f2a7259a7b30492328726fbccbb711)

#### PostConstruct : 빈 생성 후 작업
```java
import jakarta.annotation.PostConstruct;

@Component
class SomeClass {
	private SomeDependency someDependency;
	public SomeClass(SomeDependency someDependency) {
		this.someDependency = someDependency;
		System.out.println("모든 의존성이 준비되었습니다.");
	}

	@PostConstruct
	public void initialize() {
		someDependency.getReady();
	}
}

@Component
class SomeDependency {
	public void getReady() {
		System.out.println("SomeDependency : 로직 실행");
	}
}
```
![PostConstruct.png](image/PostConstruct.png)
- 특정 메서드에 `@PostConstruct` 어노테이션을 부여하면 의존성이 준비된 후 자동으로 메서드가 실행된다.
- 초기화가 필요한 경우, 예를들어 데이터베이스 등에서 데이터를 가져와서 Bean을 초기화 하는 경``우
  - `User`라는 Bean이 있을 경우 데이터베이스에서 User의 정보를 가져와 필드를 초기화



#### PreDestroy : 빈 소멸 전 작업
```java
@Component
class SomeClass {
	@PreDestroy
	public void cleanup() {
		System.out.println("정리");
	}
}
```
![PreDestroy.png](image/PreDestroy.png)
- Bean이 삭제될 때 해당 어노테이션이 부여된 메서드가 실행된다.
- 가령, 데이터베이스의 연결을 끊는 경우나 데이터 저장 등

## 6단계 - Jakarta EE의 발전 - J2EE 및 Java EE와 비교
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/d43fc558452d368c05b0cd63be9eb24ea5a9baee)

#### EE(Enterprise Edition)의 역사
![Evolution-of-EE.png](image/Evolution-of-EE.png)

- 초기 Java 버전에서 엔터프라이즈 기능 대부분은 JDK에 자바 언어로 직접 구축되어 있었다.
- 시간이 지나면서 기능들이 분리되게 된다.
  - J2EE : Java 2 플랫폼 엔터프라이즈 에디션
    - Sun Microsystems(현 Oracle)에 의해 개발
    - 주요 API와 스펙이 Java 2 Platform, Standard Edition (J2SE) 2.x 버전을 기반
  - Java EE : Java 플랫폼 엔터프라이즈 에디션
    - J2EE의 후속 버전
    - 기존 J2EE의 API와 스펙을 개선, 확장
    - Java SE 5.0 이상 버전을 기반
  - Jakarta EE : 2018년부터 변경된 Java EE의 신규 브랜드 명칭
    - Java EE 8의 기술 스펙과 API를 계승하면서, Eclipse 재단에서 관리
    - Java SE 8 이상 버전을 기반
    - Spring 6 & Spring Boot 3 부터 Jakarta EE 스펙을 지원

#### Jakarta EE에 속한 기술
- JSP ( Jakarta Server Pages | Java Server Pages )
  - 동적 웹 페이지 생성
- JSTL ( Jakarta Standard Tag Library | JavaServer Pages Standard Tag Library )
  - JSP 페이지에서 사용할 수 있는 표준 태그 라이브러리
- EJB ( Jakarta Enterprise Bean | Enterprise JavaBeans )
  - 기업용 Java 애플리케이션 개발을 위한 컴포넌트 모델을 제공
- JAX-RS ( Jakarta RESTful Web Services | Java API for RESTful Web Services )
  - RESTful 웹 서비스 개발을 위한 Java API 표준
- Jakarta Bean Validation
  - 애플리케이션에서 데이터 유효성 검사를 위한 표준 API
- CID ( Jakarta Contexts and Dependency Injection )
  - 애플리케이션 구성 요소 간의 의존성 관리를 지원
- JPA ( Jakarta Persistence | Java Persistence API )
  - 관계형 데이터베이스와 상호 작용 ORM

## 7단계 - Spring Framework 및 Java를 통해 Jakarta CDI 알아보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/d5869bb0b84c2e4b4706d7992fec5d444f2adf70)

CID ( Jakarta Contexts and Dependency Injection )

#### Spring 프레임워크에서 지원
- Spring 프레임워크 V1 은 2004년에 공개됨
- CDI 규격은 2009년 12월에 Java EE 6 플랫폼에 도입됨

#### 규격이자 인터페이스 (구현이 없다)
- Spring 프레임워크에서 구현

#### API 어노테이션 (중요한 것 일부만 나열)
- Inject ( Spring의 Autowired와 비슷함 )
- Named ( Spring의 Component와 비슷함 )
- Qualifier ( Spring의 동일 이름 어노테이션과 비슷함 )
- Scope ( Spring의 동일 이름 어노테이션과 비슷함 )
- Singleton ( Spring의 동일 이름 어노테이션과 비슷함 )

#### CDI 실습
1. 라이브러리 추가
```xml
<dependency>
    <groupId>jakarta.inject</groupId>
    <artifactId>jakarta.inject-api</artifactId>
    <version>2.0.1</version>
</dependency>
```
2. Spring 프레임워크가 제공하는 어노테이션 대신 Jakarta 어노테이션 사용해보기
```java
//@Component
@Named
class BusinessService {
	private DataService dataService;
	public DataService getDataService() {
		return dataService;
	}
	//@Autowired
    @Inject
	public void setDataService(DataService dataService) {
		System.out.println("Setter 주입");
		this.dataService = dataService;
	}
}
```
- `@Component` 대신 `@Named` 를 사용할 수 있다.
- `@Autowired` 대신 `@Inject` 을 사용할 수 있다.

## 8단계 - Java Spring XML 설정 알아보기
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/ff44f2a35a3babd912afe2b601b8b020962d7c92)

[HelloWorldConfiguration.java](..%2F00_module%2Flearn-spring-framework-01%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Flearn_spring_framework%2Fhelloworld%2FHelloWorldConfiguration.java)
`HelloWorldConfiguration`에선 Java 문법으로 설정을 하고, Bean을 정의한다. 

과거에는 Java 설정이 없었기에 XML으로 설정을 해야 했었다.

#### XML 설정 파일 초안 작성
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> <!-- bean definitions here -->
</beans>
```
- 프로젝트 경로 '/src/main/resources'에 작성한다.
- Spring 팀에서 [예시](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/xsd-configuration.html)를 제공한다.
  - '40.2.8 the context schema' 대목을 참고.

#### XML 설정 파일 실행
```java
public class XmlConfigurationContextLauncherApplication {
	public static void main(String[] args) {
		try (var context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
			Arrays.stream(context.getBeanDefinitionNames())
					.forEach(System.out::println);
		}
	}
}
```
- `ClassPathXmlApplicationContext` 클래스에 Xml 파일 이름을 줄 수 있다. ('/src/main/resources' 경로에 있어야 함)

#### XML에서 Bean 정의하기 
```xml
<bean id="name" class="java.lang.String">
  <constructor-arg value="EH13" />
</bean>
```
- 기존 Xml 파일 `beans` 태그 내부에 Bean을 정의할 수 있다.

```xml
  <context:component-scan base-package="com.in28minutes.learn_spring_framework.game" />
```
- 이와 같은 방식으로 컴포넌트 스캔을 정의하는 것도 가능하다.

```xml
  <bean id="game" class="com.in28minutes.learn_spring_framework.game.PacmanGame" />
  <bean id="gameRunner" class="com.in28minutes.learn_spring_framework.game.GameRunner">
    <constructor-arg ref="game" />
  </bean>
```
- 커스텀 클래스에 Bean 등록 및 의존성 주입도 가능하다.

## 9단계 - Java 어노테이션과 XML 설정 알아보기 - Java Spring Framework
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/f6627ee786c2136efaf2570857bb7ba696ffc248)

#### Java 어노테이션
- 사용 편이성 : 편리하고 쉬움
- 구문 : 짧고, 간결함
- POJO : 불가능
- 관리 편이성 : 쉬움
- 디버그 난이도 : 어려움
  - Spring 프레임워크를 아주 잘 이해하고 있어야 디버그가 가능함

#### XML 설정
- 사용 편이성 : 번거로움 
  - 인스턴스를 만들기 위해서 패키지 전체 이름이 필요
- 구문 : 복잡함
- POJO : 가능
- 관리 편이성 : 어려움 
  - 패키지 변경, 클래스명을 바꾸는 경우, 설정파일 내에서도 함께 바꿔주어야 함
- 디버그 난이도 : 비교적 쉬움

#### 권장 사항
- 둘 중 어느 것을 사용해도 괜찮지만 섞어서 사용하는 것은 지양한다.

## 10단계 - Spring Framework 스테레오타입 어노테이션 - Component 등
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/3dbddfd58f426a298bd0cc523b1c4981d9f05ccb)

#### 스프링 스테레오타입 어노테이션 (Spring Stereotype Annotations)
특정한 목적을 가진 컴포넌트를 쉽게 식별할 수 있도록 해주는 어노테이션

- @Component : 제너릭(Generic)한 어노테이션으로 모든 클래스에 적용 가능
  - 모든 Spring 스테레오타입 어노테이션의 베이스
- @Service : 비즈니스 로직이 포함된 클래스에 적용
- @Controller : 컨트롤러 클래스에 적용
  - ex) 웹 컨트롤러
  - 웹 애플리케이션과 REST API 컨트롤러 정의
- @Repository : 데이터베이스와 통신하는 클래스에 적용

#### 적용 실습
`com.in28minutes.learn_spring_framework.examples.c1` 패키지의 클래스를 특화된 구현체 어노테이션으로 명시할 수 있다.
- 변경
  - `BusinessCalculationService` : 비즈니스 로직이 정의되어 있으므로 `@Service` 어노테이션 부여가 적절하다.
  - `MongoDbDataService`, `MySqlDataService` : 데이터 베이스 통신 로직이 정의되어 있으므로 `@Repository`가 적절하다.

#### 무엇을 사용해야 할까?
- 최대한 구체적인 어노테이션을 사용하는 것이 권장된다.
  - 프레임워크에 개발자의 의도를 더 자세하게 전달할 수 있다.
  - AOP(관점 지향 프로그래밍)을 통해 어노테이션을 감지하고 부가 동작을 추가하는 것이 가능하다.
    - ex) `@Repository` 어노테이션이 부여되어 있으면 Spring이 자동으로 JDBC 예외 변화 기능에 연결을 진행한다.

## 11단계 - 간단한 복습 - 중요한 Spring Framework 어노테이션
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/2d9b6fcb19370b40f84d71899ac10e7be4e4078c)

#### Spring 프레임워크의 중요한 어노테이션 
- @Configuration 
  - Java 설정 파일을 생성한다는 의미
  - 클래스가 @Bean 메서드를 하나 이상 선언함을 나타냄
  - Spring 컨테이너에서 처리해서 자동으로 Bean 정의를 생성
  - ex) [GamingConfiguration.java](..%2F00_module%2Flearn-spring-framework-01%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Flearn_spring_framework%2FGamingConfiguration.java)
- @ComponentScan
  - 컴포넌트 스캔을 실행할 특정 패키지를 정의
  - 파라미터로 별도 패키지를 지정하지 않을 시 해당 어노테이션을 부여한 클래스의 패키지(하위 패키지 포함)에서 스캔 실행
- @Bean
  - 메서드에 부여하는 어노테이션
  - Spring이 Bean을 생성함
- @Component
  - 부여한 클래스가 컴포넌트임을 나타냄
  - 컴포넌트 스캔 대상에 속한다면 Spring이 Bean을 생성함
- @Service
  - @Component 어노테이션의 구체적인 구현체 어노테이션
  - 부여한 클래스에 비즈니스 로직이 있음을 나타냄
- @Controller
  - @Component 어노테이션의 구체적인 구현체 어노테이션
  - 부여한 클래스가 컨트롤러임을 나타냄 ex) 웹 컨트롤러
  - 일반적으로 웹 애플리케이션과 REST API 에서 컨트롤러를 정의하는데 사용
- @Repository
  - @Component 어노테이션의 구체적인 구현체 어노테이션
  - 부여된 클래스가 데이터베이스에서 데이터를 검색하거나 조작하는데 사용됨을 나타냄

#### Spring 프레임워크의 중요한 어노테이션 2
- @Primary
  - 여러 Bean이 단일 값 의존성에 연결될 후보일 경우 우선 순위를 부여함
  - ex) [MarioGame.java](..%2F00_module%2Flearn-spring-framework-02%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Flearn_spring_framework%2Fgame%2FMarioGame.java)
- @Qualifier
  - 의존성 자동 연결 시 Bean 후보의 고유한 한정자를 부여함
  - 필드나 매개변수에서 사용됨
  - @Primary 보다 우선도가 높음
  - ex 선언) [SuperContraGame.java](..%2F00_module%2Flearn-spring-framework-02%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Flearn_spring_framework%2Fgame%2FSuperContraGame.java)
  - ex 사용) [GameRunner.java](..%2F00_module%2Flearn-spring-framework-02%2Fsrc%2Fmain%2Fjava%2Fcom%2Fin28minutes%2Flearn_spring_framework%2Fgame%2FGameRunner.java)
- @Lazy
  - Bean의 초기화 시점을 Spring 컨텍스트 실행 시점이 아닌 Bean 호출 시점으로 변경
- @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  - 부여된 클래스를 프로토타입 형식의 Bean으로 정의
    - 프로토타입 : Bean을 참조할 때마다 신규 인스턴스를 생성
  - @Scope의 기본 값은 '싱글톤'
    - 싱글톤 : 하나의 Bean 인스턴스를 재활용
    - 별도 지정하지 않아도 기본 값으로 설정됨
    - 명시적 지정은 `@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)`

#### Spring 프레임워크의 중요한 어노테이션 3
- @PostConstruct
  - 의존성 주입이 수행된 이후 초기화를 위해 실행될 메서드를 의미
  - 부여된 메서드는 Bean이 생성된 이후 자동으로 호출됨
- @PreDestroy
  - 부여된 메서드는 Bean이 삭제되기 전 자동으로 호출됨
- @Named
  - jakarta CDI에서 제공하는 메서드
  - Spring의 @Component를 대체 가능함
- @Inject
  - jakarta CDI에서 제공하는 메서드
  - Spring의 @Autowired를 대체 가능함

## 12단계 - 간단한 복습 - 중요한 Spring Framework 개념
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/557727818139ba7f0f1a75a70d4a317b9d93a724)

#### 의존성 주입
- Spring 프레임워크가 Bean을 확인하고, 의존성을 확인하고, 의존성을 Bean에 연결하는 과정
- IoC (제어의 역전)이라고 부르기도 함
  - 객체와 객체를 연결하는 코드를 프로그래머가 아닌 Srping 프레임워크가 담당하기 때문
- Spring 의존성 주입의 방식
  - 생성자 주입 : 클래스의 생성자를 통해 주입
  - Setter 주입 : 클래스 Setter 메서드를 통해 주입
  - 필드 주입 : 생성자나 Setter 메서드가 없을 경우 Spring이 리플렉션을 사용해서 주입

#### IoC 컨테이너
- Spring Bean과 Bean의 수명을 책임지는 Spring의 IoC 컨텍스트
- Bean의 생성, 전체 수명, 종료를 책임짐
- IoC 컨테이너의 유형
  - Bean Factory
  - Application Context

#### Spring Bean
- Spring에서 관리하는 인스턴스 객체
- Spring에서 관리하는 객체는 모두 Spring Bean이라고 부른다

#### 자동 연결 (Auto-wiring)
- 특정 Spring Bean이 의존성이 필요하다면 Spring이 올바른 의존성을 찾아서 Bean에 연결하는 과정

## 13단계 - Spring 전체 구조 알아보기 - Framework, 모듈, 프로젝트
[커밋 내역](https://github.com/PhiloMonx1/learning-spring-and-spring-boot-3.x/commit/12f176a157949632e07553273ff153c5da92874f)

![Spring-Big-Feature.png](image/Spring-Big-Feature.png)

#### 스프링 코어 (Spring Core)
- IoC 컨테이너, 의존성 주입, 자동 연결 등 지금까지 배운 내용을 스프링 코어라고 한다.
- Spring의 기본적인 구성

#### Big Feature
- Spring Framework
- Spring 모듈
- Spring 프로젝트

#### Spring Modules Big Feature
![Spring-Modules-Big-Feature.png](image/Spring-Modules-Big-Feature.png)
Spring 프레임워크에는 Spring 모듈이 여러 개 포함되어 있다.

- Core 모듈 (Fundamental Features)
  - IoC 컨테이너
  - 의존성 주입
  - 자동 연결
- Spring MVC
  - 웹 애플리케이션
  - REST API
- Spring WebFlux
  - 리액티브 웹 애플리케이션 (비동기 웹 애플리케이션)
- Spring JDBC, JPA
  - 데이터 베이스 엑세스
- Spring JMS
  - 다른 애플리케이션과 통합
- Mock Objects, Spring MVC Test
  - 단위 테스트 작성

스프링 프레임워크가 모듈로 나누어져 있기 때문에 각각의 애플리케이션 요구사항에 맞춰 유연한 선택이 가능하다.


#### Spring Projects Big Feature
![Spring-Projects-Big-Feature.png](image/Spring-Projects-Big-Feature.png)

- 애플리케이션 아키텍쳐는 계속해서 발전한다.
  - Web > REST API > MSA > Cloud > ...
- Spring은 아직까지 자주 쓰이는 프레임워크이다. Spring이 계속해서 발전하기 때문이다.
  - Spring Framework : 스프링의 첫 번째 프로젝트
  - Spring Security : 웹 애플리케이션이나 REST API에 보안을 추가 or 마이크로서비스에 인증. 권한을 부여할 때 사용
  - Spring Data : 데이터베이스와 통합할 때 사용 (NoSQL, SQL DB에 연결하는 모든 경우에 사용됨)
  - Spring Integration : 다른 애플리케이션과 통합애 사용
  - Spring Boot : 마이크로서비스를 빌드할 때 사용
    - 마이크로서비스 : 하나의 큰 애플리케이션을 작고 독립적인 서비스 단위로 쪼개어 개발하고 배포하는 아키텍처 스타일
  - Spring Cloud : 네이티브 애플리케이션을 빌드할 때 사용
    - 네이티브 애플리케이션 : 특정 운영 체제(OS)에 최적화된 애플리케이션

#### Spring Big Feature - Framework, Modules and Projects
![Spring-Big-Feature.png](image/Spring-Big-Feature.png)
계증 : Spring 프로젝트 > Spring 프레임워크 > Spring 모듈

#### 스프링 생태계는 왜 인기가 많을까?
1. Spring에서는 느슨한 결합이 가능하다.
   - Spring은 Bean의 생성, 의존성을 관리한다.
   - 장기적인 유지보수에 유리한 애플리케이션 개발이 가능하다.
   - 단위 테스트 작성이 수월하다.
2. 보일러플레이트 코드(Boilerplate Code)를 줄여준다.
   - 보일러플레이트 코드 : 프로그래밍에서 반복적으로 사용되는 표준 코드 패턴
   - 메서드마다 예외 처리를 작성팔 필요가 없어서 비즈니스 로직에 집중이 가능하다.
3. 아키텍처 유연성
   - 다양한 모듈 제공
4. 시간에 따라 발전함
   - 애플리케이션 요구가 늘어남에 따라 다양한 프로젝트를 도입