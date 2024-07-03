# 📒 [학습 노트] 챕터 8 : Spring Boot와 Spring Framework, Hibernate로 Java REST API 생성하기

## 목록
1. [시작하기 - 풀 스택 Spring Boot와 React 애플리케이션](#1단계---시작하기---풀-스택-spring-boot와-react-애플리케이션)
2. [풀 스택 아키텍처는 무엇이며 왜 필요한가](#2단계---풀-스택-아키텍처는-무엇이며-왜-필요한가)
3. [JavaScript와 ECMA Script의 역사 이해하기](#3단계---javascript와-ecma-script의-역사-이해하기)
4. [Visual Studio Code 설치](#4단계---visual-studio-code-설치)
5. [Node.js와 npm 설치](#5단계---nodejs와-npm-설치)

---

## 1단계 - 시작하기 - 풀 스택 Spring Boot와 React 애플리케이션

#### 중요 키워드
1. Modern JavaScript (ECMA 스크립트 등장 이후의 JS)
2. 리액트 (React Fundamentals) 
3. 리액트 컴포넌트 (Component)
4. State
5. 라우팅 (Routing)
6. REST API 호출
7. 풀스택 애플리케이션 인증 구현

#### 실습 예제
1. 카운터 애플리케이션
   - 리액트의 기초 이해 
     - 컴포넌트가 무엇인가
     - 컴포넌트가 왜 필요한가
     - 컴포넌트는 어떻게 구축하는가
     - State는 무엇인가
     - 속성을 뜻하는 Props는 무엇인가
2. Todo 관리 애플리케이션 (풀 스택 애플리케이션)
   - Todo 애플리케이션의 기능 (백엔드 실습 때 만들어 보았던 기능)
   - 로그인, 로그아웃
     - JWT
     - JSON 웹 토큰

---

## 2단계 - 풀 스택 아키텍처는 무엇이며 왜 필요한가

#### 풀 스택 애플리케이션
![full-stack-architecture.png](image/full-stack-architecture.png)
- 일반적인 풀 스택 아키텍쳐는 3가지 컴포넌트로 구성된다.
  - 프론트엔드 : 주로 브라우저에서 실행된다. (해당 과정에서는 리액트로 구성)
  - 백엔드 : 서버에서 실행된다. (해당 과정에서는 Spring Boot로 구성)
  - 데이터베이스 : 서버와 연결된 데이터 저장소 (해당 과정에서는 H2, MySQL로 구성)
- 애플리케이션에 인증은 필수적이다.
  - REST API를 보호하기 위함 (해당 과정에서는 Spring Security로 구성)
    - Basic Security로 시작해서 JWT를 구현할 것이다.

#### 왜 풀 스택 아키텍처인가?
풀 스택 아키텍처는 구현 난이도가 복잡하다. 여러 언어를 이해해야 하고 다양한 프레임워크를 알아야 한다. 프론트엔드와 백엔드는 빌드 도구 등도 차이가 있다. 그럼에도 왜 풀 스택 아키텍처를 사용할까?

- 풀 스택 아키텍처는 유연하고, REST API 사용이 가능하다.
- REST API가 있으면 다른 애플리케이션을 만들어서 API와 소통하도록 할 수 있다. ex) 모바일 앱, IoT 앱 등

---

## 3단계 - JavaScript와 ECMA Script의 역사 이해하기

#### JS(Java Script) 역사
- 지속적으로 진화해왔다.
  - ES5, ES6, ES7, ES13, ES14 등
- 초기 버전은 'DOM'을 다루는 데 사용되었고, 작성이 어려웠다.
  - DOM(Document Object Model) : HTML의 id, class 등을 통해 접근, 조작 가능한 객체

#### ES(ECMA Script)
- ECMA-262 기술 규격에 따라 정의한 표준화된 스크립트 프로그래밍 언어.
  - 지속적인 버전 업데이트를 거쳐왔다.
  - 자바스크립트의 표준화된 버전이다.

---

## 4단계 - Visual Studio Code 설치

#### VS 코드 설치
[공식페이지 - 다운로드](https://code.visualstudio.com/download)

#### 부록 : 인텔리제이에서 React 프로젝트 생성

![IntelliJ-tool-react.png](image/IntelliJ-tool-react.png)
- 인텔리제이 프로젝트 생성 tool에서도 React를 지원한다.
- 리액트 프로젝트를 시작하는 명령어가 포함된다.
- 주의 : Node JS가 설치되어 있어야 한다.

![IntelliJ-project-react.png](image/IntelliJ-project-react.png)
- 프로젝트 파일 생성이 완료된 후 리액트 프로젝트 생성 명령어에 대한 응답을 요청한다.
  - y를 눌러 진핼할 수 있다.

![IntelliJ-run-react.png](image/IntelliJ-run-react.png)
- 리액트 초기화가 완료되면, 인텔리제이에서 'run' 버튼으로 `npm start` 명령어를 실행할 수 있다.

---

## 5단계 - Node.js와 npm 설치

#### Node js 설치
[공식페이지 - 다운로드](https://nodejs.org/en/download/)
```
node --version
```
- 명령어로 node 설치가 정상적으로 되었는지 확인할 수 있다.

#### NPM(Node.js Package Manager)
- 패키지 관리자 (Spring의 Maven | gradle 과 유사하다)
- node를 설치하면 자동으로 node 버전에 호환되는 npm이 함께 설치된다.
  ```
  npm -version 
  ```
  - 명령어로 npm 버전을 확인해서 설치가 정상적으로 되었는지 확인할 수 있다.

#### Node 프로젝트 생성
```
npm init
```
- 현재 경로의 폴더에 node 프로젝트를 생성한다. (package name만 작성해주고 나머지는 모두 엔터를 눌러 기본값으로 설정할 수 있다.)
  - package name: 프로젝트 이름
  - version: 프로젝트 버전
  - description: 프로젝트 설명
  - entry point: 프로젝트의 메인 파일 (기본값 index.js)
  - test command: 테스트 명령어
  - git repository: Git 저장소 URL
  - keywords: 프로젝트 키워드
  - author: 작성자 정보
  - license: 라이선스 정보 (기본값 ISC)

#### package.json
프로젝트의 메타데이터, 의존성, 스크립트 등 중요한 정보를 포함하고 있는 '매니페스트' 파일

프로젝트 설정을 마치고 생성이 끝나면 폴더 경로에 'package.json' 파일이 생성된다.
```json
{
  "name": "first-npm-projet",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "",
  "license": "ISC"
}
```
- 프로젝트를 생성할 때 설정한 내용이 명시되어 있다.

#### 프로젝트에 라이브러리 추가 (jquery)
```
npm install jquery
```
- 프로젝트 경로에 해당 명령어를 입력한다.
- 패키지 경로에 'node_modules' 폴더가 생기고 내부에 'jquery' 폴더가 생긴 것을 볼 수 있다.

```json
{
  "name": "first-npm-projet",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "",
  "license": "ISC",
  "dependencies": {
    "jquery": "^3.7.1"
  }
}
```
- 'package.json' 파일을 보면 'dependencies' 항목에 "jquery"가 추가 된 것을 볼 수 있다.

---