# 📒 [학습 노트] 챕터 12: JPA 및 Hibernate를 사용한 Java 풀스택 애플리케이션 연결하기(Spring Boot & React)

## 목록
1. [풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot](#1단계---풀-스택-react와-jpa-및-hibernate를-사용한-spring-boot)
2. [풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - 테이블 준비하기](#2단계---풀-스택-react와-jpa-및-hibernate를-사용한-spring-boot---테이블-준비하기)

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

## 2단계 - 풀 스택 React와 JPA 및 Hibernate를 사용한 Spring Boot - 테이블 준비하기

#### Todo 엔티티 생성 
1. 클래스에 @Entity 어노테이션 부여
2. id 필드에 @Id, @GeneratedValue 어노테이션 부여
3. 기본 생성자 생성

이 세 가지는 필수이다.

#### 시작 데이터 삽입
```properties
spring.jpa.defer-datasource-initialization=true
```
- properties에 해당 설정을 해주어야 한다.
```sql
insert into todo(id, description, done, target_date, username)
values (10001, 'JPA 배우기', false, CURRENT_DATE(), 'eh13');

insert into todo(id, description, done, target_date, username)
values (10002, 'SQL 배우기', false, CURRENT_DATE(), 'eh13');

insert into todo(id, description, done, target_date, username)
values (10003, 'Spring 배우기', false, CURRENT_DATE(), 'eh13');
```
- src/resources/data.sql

---