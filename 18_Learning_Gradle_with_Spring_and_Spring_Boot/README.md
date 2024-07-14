# 📒 [학습 노트] 챕터 18 :Spring과 Spring Boot로 Gradle 학습하기

## 목록
1. [Gradle 시작하기](#1단계---gradle-시작하기)
2. [Gradle로 Spring Boot 프로젝트 생성하기](#2단계---gradle로-spring-boot-프로젝트-생성하기)

---

## 1단계 - Gradle 시작하기

오픈 소스 빌드 자동화 도구, Maven을 대체할 수 있다.

#### Maven과의 차이
- 유연성 & 확장성: DSL을 사용하여 빌드 스크립트를 작성하며, 이는 POM 보다 더 유연하고 표현력이 풍부하다.
- 성능 : 증분 빌드, 빌드 캐시, 병렬 실행 등의 최적화 기능으로 Maven 보다 빠른 빌드 성능을 제공한다.
  - 증분 빌드 : 변경 파일만 빌드해서 빌드에 필요한 자원을 아끼는 전략

Gradle은 Maven의 대체제로 사용할 수 있으며, 특히 Maven과 동일한 폴더 구조를 채택해서 호환성 또한 좋다.
- Gradle를 사용해서 생성한 라이브러리를 Maven에서 사용할 수 있으며 그 반대도 가능함.

---

## 2단계 - Gradle로 Spring Boot 프로젝트 생성하기

#### 프로젝트 생성
![Spring initializer 세팅](image/Spring-initializer.png)
- [Spring initializer](https://start.spring.io/) 를 통해 프로젝트를 생성한다.
- 빌드 도구를 'Gradle - Groovy"로 설정한다.
- 라이브러리는 추가하지 않았다.

#### build.gradle
Maven의 pom.xml 에 대응하는 파일
- DSL을 사용해서 작성한다.

#### settings.gradle
프로젝트명이 포함되어 있는 파일
- 주로 멀티 모듈 프로젝트에서 사용된다.
  - 멀티 모듈 프로젝트 : 하나의 프로젝트에 여러 모듈로 구성된 프로젝트 
    - 하나의 애플리케이션 내에서 코드를 논리적으로 분리한 것으로 아키텍처 자체가 독립적인 MSA 와는 다르다.
  - 프로젝트 구조를 정의하고, 하위 모듈을 포함시키는 데 사용

---