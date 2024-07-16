# 📒 [학습 노트] 챕터 22: AWS에 Spring Boot 애플리케이션 배포하기

## 목록
1. [Hello World Spring Boot 앱 AWS에 배포하기](#1단계---hello-world-spring-boot-앱-aws에-배포하기)
2. [AWS Elastic Beanstalk 살펴보기 - AWS에 배포한 첫 번째 Spring Boot 앱](#2단계---aws-elastic-beanstalk-살펴보기---aws에-배포한-첫-번째-spring-boot-앱)

---

## 1단계 - Hello World Spring Boot 앱 AWS에 배포하기

#### 배포할 애플리케이션 프로젝트
[링크](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/91-aws)를 통해 배포할 애플리케이션 프로젝트를 다운받을 수 있다.
- hello-world-java : 간단한 HelloWorld API 애플리케이션
- [rest-api-mysql](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/91-aws/02-rest-api-mysql) : mySQL과 연결된 Rest API 애플리케이션
- [rest-api-full-stack-h2](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/91-aws/03-rest-api-full-stack-h2) : 풀스택용 백엔드 애플리케이션
- [frontend-react](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/91-aws/04-frontend-react) : 풀스택용 프론트엔드 애플리케이션

#### 포트 변경
```properties
server.port=5000
```
- Elastic Beanstalk에 애플리케이션을 배포할 때 권장되는 포트는 5000번이다.

#### 애플리케이션 빌드
```
mvn clean package
```
- 이전 빌드에서 생성된 모든 빌드 파일을 삭제하고 jar로 패키징 하는 빌드 명령어.

#### Elastic Beanstalk에 배포
1. 애플리케이션 이름을 설정한다. (AWS Beanstalk에서 식별하는 이름. 고유해야 함)
2. 플랫폼에서 Java를 선택한다. (버전은 애플리케이션 빌드 버전보다 높은 버전이어야 함.)
3. 빌드된 jar 파일을 업로드한다.
4. 단일 인스턴스를 선택한 후 별도 설정 없이 완료하면 배포가 진행된다.

---

## 2단계 - AWS Elastic Beanstalk 살펴보기 - AWS에 배포한 첫 번째 Spring Boot 앱

#### Beanstalk 애플리케이션 관리 기능
- 상태 모니터링
  - 현재 애플리케이션 상태 확인
  - 배포된 버전 정보 확인
  - 발생한 모든 이벤트 조회
- 애플리케이션 접근
  - AWS에서 발행한 URL을 통해 웹 애플리케이션 접근
- 인프라 관리
  - 연결된 EC2 인스턴스 확인
  - 인스턴스 상태 및 정보 조회
- 버전 관리
  - 새로운 애플리케이션 버전 업로드 및 배포
  - 기존 버전 관리 및 롤백
- 환경 관리
  - 개발, QA, 스테이징, 프로덕션 등 다중 환경 생성 및 관리
  - 환경별 설정 변경
- 모니터링 및 로그
  - CPU 사용률 등 주요 메트릭 확인
  - 애플리케이션 로그 조회
  - 로그 파일 다운로드 
    - 최근 100줄 
    - 전체 로그
- 배포 관리
  - 새 버전 업로드 및 배포
  - 배포 히스토리 확인
- 리소스 관리
  - 애플리케이션 및 환경 삭제
  - 리소스 사용 최적화

---