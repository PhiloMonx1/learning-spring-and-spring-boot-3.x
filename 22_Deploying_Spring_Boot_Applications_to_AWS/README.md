# 📒 [학습 노트] 챕터 22: AWS에 Spring Boot 애플리케이션 배포하기

## 목록
1. [Hello World Spring Boot 앱 AWS에 배포하기](#1단계---hello-world-spring-boot-앱-aws에-배포하기)
2. [AWS Elastic Beanstalk 살펴보기 - AWS에 배포한 첫 번째 Spring Boot 앱](#2단계---aws-elastic-beanstalk-살펴보기---aws에-배포한-첫-번째-spring-boot-앱)
3. [MySQL 데이터베이스를 통해 Docker 컨테이너로 Spring Boot REST API 실행하기](#3단계---mysql-데이터베이스를-통해-docker-컨테이너로-spring-boot-rest-api-실행하기)
4. [MySQL을 이용하여 AWS Elastic Beanstalk에 Spring Boot REST API 배포하기](#4단계---mysql을-이용하여-aws-elastic-beanstalk에-spring-boot-rest-api-배포하기)
5. [AWS Elastic Beanstalk 및 Amazon RDS 살펴보기 - Spring Boot REST API](#5단계---aws-elastic-beanstalk-및-amazon-rds-살펴보기---spring-boot-rest-api)
6. [Spring Boot 및 React 풀스택 앱 살펴보기](#6단계---spring-boot-및-react-풀스택-앱-살펴보기)
7. [AWS Elastic Beanstalk에 풀스택 Spring Boot REST API 배포하기](#7단계---aws-elastic-beanstalk에-풀스택-spring-boot-rest-api-배포하기)
8. [Amazon S3에 풀스택 React App 배포하기](#8단계---amazon-s3에-풀스택-react-app-배포하기)

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

## 3단계 - MySQL 데이터베이스를 통해 Docker 컨테이너로 Spring Boot REST API 실행하기

#### 애플리케이션 변경점
[rest-api-mysql](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/91-aws/02-rest-api-mysql) : mySQL과 연결된 Rest API 애플리케이션
1. 시큐리티 필터체인
  ```java
  .requestMatchers("/").permitAll()
  ```
  Spring Security 필터체인에 애플리케이션 루트 엔드포인트 요청을 허용으로 바꿨다.
  - 상태 확인
    - 애플리케이션이 요청에 응답할 준비가 되어 있는지 (정상적으로 실행되고 있는지) 확인하는 과정
    - '/' 기본 루트로 AWS가 요청을 보낸 후 200이 돌아오면 정상 상태로 인식한다.
2. application.properties
  ```properties
  spring.datasource.url=jdbc:mysql://${RDS_HOSTNAME:localhost}:${RDS_PORT:3306}/${RDS_DB_NAME:social-media-database}
  spring.datasource.username=${RDS_USERNAME:social-media-user}
  spring.datasource.password=${RDS_PASSWORD:dummypassword}
  ```
  - 환경 변수를 사용해서 설정하도록 변경했다. (초기 값 설정도 진행함)
    - AWS Beanstalk 애플리케이션 관리 웹 페이지에서 환경 변수를 설정하고 애플리케이션에 전달하는 것이 가능하다.

#### 애플리케이션 실행
배포 전 애플리케이션을 테스트하기 위해 실행해보자.
1. Docker MySQL 컨테이너 실행
2. 애플리케이션 실행
3. 애플리케이션 API 테스트

---

## 4단계 - MySQL을 이용하여 AWS Elastic Beanstalk에 Spring Boot REST API 배포하기

#### Beanstalk 배포
2단계와 동일하지만 MySQL 데이터베이스를 Beanstalk에 연결하는 부분이 추가된다.
- RDS(Relational Database Service) : AWS에서 제공하는 관리형 SQL 데이터베이스 서비스

1. 배포 페이지에서 추가 설정에 진입
2. 데이터베이스 섹션 설정
   - 데이터 베이스 모델 및 사양 설정
   - 데이터 베이스 아이디, 비밀번호 설정
   - 데이터 베이스 삭제 정책 설정 : 환경이 종료될 때 데이터 베이스를 어떻게 할지에 대한 설정이다.
     - Create snapshot : 스냅샷(복제본)을 생성 후 데이터 베이스를 삭제한다.
     - Retain : 환경이 종료돼도 데이터 베이스는 삭제되지 않는다. (데이터 베이스가 Elastic Beanstalk 외부에서 분리되어 동작한다.)
     - Delete : 환경이 종료될 대 함께 종료한다.

---

## 5단계 - AWS Elastic Beanstalk 및 Amazon RDS 살펴보기 - Spring Boot REST API

#### 환경 변수
```properties
spring.datasource.url=jdbc:mysql://${RDS_HOSTNAME:localhost}:${RDS_PORT:3306}/${RDS_DB_NAME:social-media-database}
spring.datasource.username=${RDS_USERNAME:social-media-user}
spring.datasource.password=${RDS_PASSWORD:dummypassword}
```
- RDS_HOSTNAME, RDS_PORT, RDS_DB_NAME, RDS_USERNAME, RDS_PASSWORD 라는 이름은 AWS Elastic Beanstalk 환경에서 자동으로 인식하는 환경변수 이다.

#### 보안 그룹
RDS에 할당된 보안그룹을 확인할 수 있다.
- RDS 인바운드 규칙은 EC2와 연결되어 있으며 EC2에서 발생하는 트래픽만을 허용한다.
  - local에서 RDS에 바로 연결하고자 해도 연결할 수 없다.

환경 변수와 보안 그룹 등의 설정을 사용자가 직접하지 않아도 Elastic Beanstalk가 자동으로 진행한다.

---

## 6단계 - Spring Boot 및 React 풀스택 앱 살펴보기

#### 백엔드 애플리케이션
[rest-api-full-stack-h2](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/91-aws/03-rest-api-full-stack-h2) : 풀스택용 백엔드 애플리케이션
- 변경점
1. JwtSecurityConfig : 필터체인 루트 경로 요청 허용 추가
2. HelloWorldController : 신규 API 추가 (상태 확인 용 루트 엔드포인트 GET API)
3. RestfulWebServicesApplication : cors 허용 설정 추가
4. application.properties : 서버 포트 5000으로 변경

#### 프론트엔드 애플리케이션
[frontend-react](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/91-aws/04-frontend-react) : 풀스택용 프론트엔드 애플리케이션
- npm 라이브러리 설치 : 테스트가 필요하다면 `npm install`을 입력해서 프로젝트 npm 라이브러리를 설치한다. (npm 라이브러리는 GitHub에 공유하지 않는 것이 일반적이다.)
- apiClient 확인 : '/todo-app/src/components/todo/api/ApiClient.js' 경로에서 apiClient의 baseURL을 확인한다. 
  - 서버의 포트가 바뀌었기에 바꿔줘야 한다. & 서버가 AWS에 배포되면 바꿔줘야 한다.

---

## 7단계 - AWS Elastic Beanstalk에 풀스택 Spring Boot REST API 배포하기

#### 백엔드 애플리케이션 배포
1. 벡엔드 애플리케이션 빌드
2. 백엔드 Beanstalk 환경 구성 및 배포 (h2를 사용하기에 RDS 연결은 진행하지 않는다.)
3. 배포 완료 후 URL 링크 복사

#### 프론트엔드 애플리케이션 배포
백엔드 배포 완료 후 웹 애플리케이션 URL을 프론트엔드 애플리케이션의 baseURL로 사용해야 한다.
```js
// '/todo-app/src/components/todo/api/ApiClient.js'
import axios from 'axios'

export const apiClient = axios.create(
    {
        // baseURL: 'http://localhost:5000' //#CHANGE
        baseURL: 'http://YOUR_BACKEND_API_URL.elasticbeanstalk.com/' 
    }
);
```
- baseURL 변경 후 API 요청을 테스트 해본다.

---

## 8단계 - Amazon S3에 풀스택 React App 배포하기

#### 애플리케이션 빌드
```
npm run build
```
- 배포 가능한 프로덕션 패키지를 빌드한다.
  - 소스 코드를 최적화하고 압축
  - build 폴더에 프로덕션 패키지가 생성된다.

#### Amazon S3(Amazon Simple Storage Service)
AWS에서 제공하는 객체 스토리지 서비스, 파일을 객체로 저장한다.
- 클라우드 드라이브와 유사하게 파일을 저장할 수 있다.
  - 키-값 구조로 데이터를 저장한다.

빌드된 프론트엔드 애플리케이션을 S3에 업로드해서 배포할 것이다.

#### S3 버킷
S3 버킷 : 데이터를 저장하는 기본 컨테이너
- 한 계정이 여러 개의 독립적인 버킷을 생성할 수 있다.
- 버킷마다 다른 보안 정책, 관리 정책을 설정할 수 있다.
- GitHub의 레포지토리와 비슷하다.

#### S3 버킷 생성
1. 버킷 이름 설정 : 버킷 이름은 전 세계에서 유일해야 한다.
2. 버킷 리전 설정 : 버킷은 특정 AWS 리전에 생성된다. (리전을 변경할 시 기존 리전의 버킷이 안 보일 수 있다.)
3. 공개 액세스 허용 : 외부에서 프론트엔드 애플리케이션에 접근할 수 있도록 `Block all public access`를 비활성화 한다.
   - S3는 기본적으로 저장된 파일에 대한 공개 액세스가 차단된다.

#### 프로젝트 배포
1. 버킷에 프로덕션 빌드 패키지를 업로드한다.
2. 'Properties' 탭에서 'Static website hosting'에 접근한다.
   - index 설정 : 인덱스 html 설정 (index.html)
3. S3가 제공하는 엔드포인트 URL이 생성된다. (애플리케이션 접근 URL)
    
#### 버킷 정책 (Bucket policy) 설정
S3 버킷에 대한 액세스를 제어하는 리소스 기반 보안 정책
```json
{
    "Version": "2012-10-17",
    "Statement": [
          {
            "Sid": "Statement1",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::your-bucket-name/*"
          }
    ]
}
```
- Sid : 정책 문의 식별자
- Effect : 정책이 가진 효과 ("Allow" 또는 "Deny")
  - 특정 액션을 명시적으로 허용하거나 차단하는 방식으로 설정한다.
- Principal : 정책이 적용될 엔티티(사용자, 역할, 서비스 등)
- Action : 'Effect'를 통해 허용되거나 차단될 작업
  - "s3:GetObject" : S3에 저장된 객체를 읽는 작업
- Resource : 정책을 적용할 버킷내의 리소스 `your-bucket-name` 부분을 실제 버킷 이름으로 변경해야 한다.

버킷 정책까지 설정한 후 S3가 제공하는 엔드포인트 URL을 통해 애플리케이션에 접근할 수 있게 된다.

---