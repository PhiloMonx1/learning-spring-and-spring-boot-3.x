# 📒 [학습 노트] 챕터 11: Spring Boot REST API와 React 프론트엔드 연결하기 - Java 풀스택 앱

## 목록
1. [React 풀 스택 애플리케이션을 위해 Todo REST API 프로젝트 설정하기](#1단계---react-풀-스택-애플리케이션을-위해-todo-rest-api-프로젝트-설정하기)
2. [React Hello World 컴포넌트에서 Spring Boot Hello World REST API 호출하기](#2단계---react-hello-world-컴포넌트에서-spring-boot-hello-world-rest-api-호출하기)

---

## 1단계 - React 풀 스택 애플리케이션을 위해 Todo REST API 프로젝트 설정하기

챕터 목표 : 기존 Spring Boot로 제작한 백엔드 애플리케이션을 재활용해서 풀 스택 애플리케이션을 만들 것이다.

원활한 진행을 위해 정돈된 백엔드 [애플리케이션 프로젝트](https://github.com/in28minutes/master-spring-and-spring-boot/blob/main/13-full-stack/99-reuse/01-rest-api-starting-code.zip)를 제공한다.

#### REST API 목록
1. Hello World REST API
- GET '/hello-world'
- GET '/hello-world-bean'
- GET '/hello-world/path-variable/{name}'
2. Todo REST API
- GET '/user/{username}/todos'
- GET '/user/{username}/todos/{id}'
- POST '/user/{username}/todos/{id}'
- PUT '/user/{username}/todos/{id}'
- DELETE '/user/{username}/todos/{id}'

#### 프로젝트 설치 & 모듈 등록 (IntelliJ IDE)
[링크](https://github.com/in28minutes/master-spring-and-spring-boot/blob/main/13-full-stack/99-reuse/01-rest-api-starting-code.zip)에서 제공

1. 필요 없는 파일 삭제. (이클립스 설정 파일 삭제)
- '.settings' 디렉토리 및 내부 파일
- '.classpath' 파일
- '.project' 파일

2. 모듈 등록
- 프로젝트 구조 (Ctrl + Alt + Shift + S)설정에 들어간다.
- 추가(Alt + insert) 버튼을 클릭 후 '모듈 가져오기'를 선택한다.
  - ![img.png](image/IntelliJ-module-setting-1.png)
- 모듈로 가져올 프로젝트를 선택하고 '확인' 버튼을 클릭한다.
- '외부 모델에서 모듈 가져오기'에서 'Maven'을 선택 후 '생성' 버튼을 클릭한다.
  - ![img_1.png](image/IntelliJ-module-setting-2.png)
- '적용' 혹은 '확인'을 누른 후 모듈을 불러올 때까지 기다린다.

3. 프로젝트 실행
모듈 불러오기가 끝난 후 'RestfulWebServicesApplication' 애플리케이션을 실행하고, ['/hello-world'](http://localhost:8080/hello-world) GET API를 확인한다.

---

## 2단계 - React Hello World 컴포넌트에서 Spring Boot Hello World REST API 호출하기

#### Axios
브라우저와 Node.js에서 사용할 수 있는 Promise 기반의 HTTP 클라이언트 라이브러리
- Promise : 비동기 작업을 처리하기 위한 객체
  - 비동기 작업 : 특정 코드의 실행이 완료될 때까지 기다리지 않고 다음 코드를 먼저 실행하는 방식의 작업
    - ex) 서버에 요청을 보낸 후 응답을 기다리는 동안 다른 작업을 진행할 수 있다. 

#### Axios 설치
```
npm install axios
```

#### Axios 사용
```jsx
import axios from "axios";

function callHelloWorldRestApi() {
    console.log("callHelloWorldRestApi")
    axios.get('http://localhost:8080/hello-world')
    .then ((response) => successfulResponse(response))
    .catch((error) => failedResponse(error))
    .finally(() => console.log("finally"))
}

function successfulResponse(response) {
  console.log(response)
}

function failedResponse(error) {
  console.log(error)
}
```
- then : 요청 성공
- catch : 요청 실패
- finally : 성공과 실패 상관하지 않음
- Promise 체이닝(Promise chaining) : 여러 개의 비동기 작업을 순차적으로 처리할 때 사용되는 기법
  - then이 정상적으로 완료되면 catch는 실행되지 않는다.
  - then은 여러 번 사용할 수 있다.
  - catch와 finally는 일반적으로 체인의 끝에 한 번씩 사용된다.

---