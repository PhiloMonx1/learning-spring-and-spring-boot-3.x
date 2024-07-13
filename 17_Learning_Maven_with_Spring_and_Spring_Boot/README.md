# 📒 [학습 노트] 챕터 17 :Spring과 Spring Boot로 Maven 배우기

## 목록
1. [Maven 소개](#1단계---maven-소개)
2. [Maven으로 Spring Boot 프로젝트 생성하기](#2단계---maven으로-spring-boot-프로젝트-생성하기)
3. [Spring Boot 프로젝트의 Maven pom.xml 살펴보기](#3단계---spring-boot-프로젝트의-maven-pomxml-살펴보기)

---

## 1단계 - Maven 소개

#### [Apache Maven 공식 사이트](https://maven.apache.org/)
공식 홈페이지의 Maven 소개이다. "Apache Maven은 소프트웨어 프로젝트 관리 및 이해 도구입니다. 프로젝트 객체 모델(POM)의 개념을 기반으로 Maven은 중앙 정보에서 프로젝트의 빌드, 보고 및 문서를 관리할 수 있습니다."
- 소프트웨어 프로젝트 관리 및 이해 도구 : 소프트웨어 개발 프로젝트를 효율적으로 계획, 실행, 모니터링하고 관리하는 데 사용되는 도구
- 프로젝트 객체 모델(POM)의 개념을 기반 : 프로젝트의 구조, 의존성, 빌드 설정 등을 XML 파일로 정의
- 중앙 정보에서 프로젝트의 빌드, 보고 및 문서를 관리 : 필요한 라이브러리와 플러그인을 중앙 저장소(Maven 서버)에서 자동으로 다운로드

#### Spring 에서의 Maven
- 라이브러리 관리
- 프로젝트 빌드
- 단위 테스트도 실행

---

## 2단계 - Maven으로 Spring Boot 프로젝트 생성하기

#### 프로젝트 생성
![Spring initializer 세팅](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 빌드 도구를 'Maven"으로 설정한다.
- 라이브러리는 추가하지 않았다.

#### pom.xml 파일 확인
프로젝트 경로 pom.xml 파일에서 프로젝트 세팅 및 의존성을 확인할 수 있다.

---

## 3단계 - Spring Boot 프로젝트의 Maven pom.xml 살펴보기

#### Maven의 의존성(dependencies)
의존성(dependencies) : 프로젝트에 사용하는 프레임워크 및 라이브러리
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```
- 각 의존성 안에 또 다른 의존성이 있을 수 있다. (압축 개념)
  - starter 라이브러리가 대게 여러 라이브러리를 모아서 한 번에 제공하는 압축 라이브러리로 쓰인다.
  - 압축된 의존성을 '전이 의존성' 이라고 부른다.

#### Maven 의존성 추가 : 라이브러리 추가
```xml 
    <dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>


  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```
- spring-boot-starter-web 라이브러리를 xml 문법에 맞게 작성하면 Maven이 해당 라이브러리를 중앙 저장소(Maven 서버)에서 식별해 자동으로 다운로드 한다.


---