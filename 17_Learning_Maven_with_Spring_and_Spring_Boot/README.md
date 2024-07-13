# 📒 [학습 노트] 챕터 17 :Spring과 Spring Boot로 Maven 배우기

## 목록
1. [Maven 소개](#1단계---maven-소개)
2. [Maven으로 Spring Boot 프로젝트 생성하기](#2단계---maven으로-spring-boot-프로젝트-생성하기)

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