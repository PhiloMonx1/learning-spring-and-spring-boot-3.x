# 📒 [학습 노트] 챕터 12: JPA 및 Hibernate를 사용한 Java 풀스택 애플리케이션 연결하기(Spring Boot & React)

## 목록
1. [풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot](#1단계---풀-스택-react와-jpa-및-hibernate를-사용한-spring-boot)

---

## 1단계 - 풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot

#### 라이브러리 추가
- jpa
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```
- h2 데이터베이스
    ```xml
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>
    ```

#### h2 데이터베이스 기본 url 설정
```properties
spring.datasource.url=jdbc:h2:mem:testdb
```

#### h2 콘솔
['/h2-console/'](http://localhost:8080/h2-console/) 접근
- JwtSecurityConfig의 필터체인에서 '/h2-console/*' 엔드포인트의 권한을 열어주었기에 인증 없이 접근이 가능하다.

---