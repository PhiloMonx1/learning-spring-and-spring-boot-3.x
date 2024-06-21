# 📒 [학습 노트] 챕터 6 : Spring과 Spring Boot로 JPA와 Hibernate 시작하기


## 1단계 - JPA와 Hibernate 시작하기 - 목표

#### 학습 목표
1. JPA 이전의 세계 이해하기
2. JDBC & Spring JDBC
3. JPA 와 Hibernate의 차이
4. Spring Boot Data JPA 사용해보기

#### 학습 접근 방식
1. H2(인메모리 DB)로 Spring Boot 프로젝트 생성
2. H2 Database에 `COURSE` 테이블 생성
3. JDBC를 사용해서 `COURSE` 테이블의 데이터 활용
4. JPA와 Hibernate 사용해서 `COURSE` 테이블의 데이터 활용
5. JPA와 Hibernate 차이점
6. Spring Data JPA를 사용
---

## 2단계 - JPA와 Hibernate에 맞는 새 Spring Boot 프로젝트 설정하기

#### 프로젝트 생성
![Spring-initializer.png](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 라이브러리 목록
  - Spring Web
  - Spring Data JDBC
  - Spring Data JPA
  - H2 Database

#### 프로젝트 실행
![run-spring-boot.png](image/run-spring-boot.png)
- JDBC와 JPA가 실행된 것을 볼 수 있다.
---