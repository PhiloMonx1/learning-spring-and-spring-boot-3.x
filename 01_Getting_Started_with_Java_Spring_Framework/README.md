# 📒학습 노트

## 1단계 - Java Spring Framework가 필요한 이유 이해하기

#### 애플케이션 아키텍처의 발전 
Wep App -> REST API -> Full Stack -> Microservices

#### 애플리케이션 구축을 위한 프레임워크
1. Spring MVC
2. Hibernate
3. Spring Security
4. Spring Data
5. Spring Cloud

#### Spring 프레임 워크
의존성 주입, 자동 연결 지원 <br>
훨씬 적은 코드로 더 많은 일을 할 수 있도록 한다.

#### Spring 프레임 워크와 밀접하게 연관된 용어
1. 강한 결합 (Tight Coupling)
2. 느슨한 결합 (Loose Coupling)
3. 의존성 주입 (Dependency Injection)
4. IOC 컨테이너 (IOC Container)
5. 애플리케이션 컨텍스트 (Application Context)
6. Spring Bean
7. 자동 연결 (Auto Wiring)
8. 컴포넌트 스캔 (Component Scan)

## 2단계 - Java Spring Framework 시작하기

#### Java Spring Framework 를 사용하여 구축 가능한 애플리케이션
1. Web
2. REST API
3. Full Stack
4. Microservices

Java Spring Framework 를 사용하면 Spring Boot 를 빠르게 이해할 수 있으며, 빠른 디버깅이 가능하다.

#### 섹션의 목표
1. Spring 프레임워크의 핵심 기능 이해
2. 실습 접근 방식 사용
3. 최신 Spring 접근 방식을 사용하여 '느슨하게 결합'된 Hello world 게임 앱 구축
4. Spring 프레임 워크에 연관된 다양한 용어 학습
   - 강한 결합과 느슨한 결합 (Tight Coupling and Loose Coupling)
   - IOC 컨테이너 (IOC Container)
   - 애플리케이션 컨텍스트 (Application Context)
   - 컴포넌트 스캔 (Component Scan)
   - 의존성 주입 (Dependency Injection)
   - Spring Bean
   - 자동 연결 (Auto Wiring)
5. '반복 접근 방식' 을 통해 마리오, 팩맨 등의 게임을 실행하는 `GameRunner` 클래스 디자인
   - 반복 1 : 강한 결합의 JAVA 코드 작성 (Tightly Coupled Java Code)
     - `GameRunner` 클래스
     - `Game` 클래스들 : Mario, Pacman 등
   - 반복 2 : 자바 인터페이스(Interfaces)를 사용한 느슨한 결합 (Loose Coupling) 
     - `GameRunner` 클래스
     - `GamingConsole` 인터페이스
       - `Game` 클래스들 : Mario, Pacman 등
   - 반복 3 : Spring 프레임워크 도입하여 느슨한 결합 1단계 구현
     - Spring Beans 생성
     - 스프링 프레임워크가 생성된 객체와 연결 관계를 관리하도록 구현
   - 반복 4 : Spring 프레임워크로 느슨한 결합 2단계 구현
     - 어노테이션 (Annotations) 사용
     - 스프링이 객체를 직접 생성, 관리, 자동 연결하도록 구현