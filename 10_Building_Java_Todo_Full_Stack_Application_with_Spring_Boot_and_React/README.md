# 📒 [학습 노트] 챕터 10: Spring Boot와 React로 Java 풀스택 Todo 애플리케이션 만들기

## 목록
1. [React로 할 일 관리 앱 구축 시작하기](#1단계---react로-할-일-관리-앱-구축-시작하기)
2. [로그인 컴포넌트 시작하기 - Todo React 앱](#2단계---로그인-컴포넌트-시작하기---todo-react-앱)

---

## 1단계 - React로 할 일 관리 앱 구축 시작하기

#### 학습할 키워드
- 라우팅(Routing)
  - 컴포넌트에서 다른 컴포넌트로 이동하는 방법
- Forms
  - 사용자에게 입력을 받는 방법
- 밸리데이션(Validation)
  - 사용자가 입력한 값을 검증하는 방법
- RestAPI 호출
  - 백엔드에서 제공하는 API 호출 방법
- JWT
  - 토큰을 활용한 인증 방법

#### 실습 프로젝트
Todo 관리 웹 애플리케이션을 풀스택으로 만들 것이다.

---

## 2단계 - 로그인 컴포넌트 시작하기 - Todo React 앱

#### 컴포넌트 작성 Tip
컴포넌트를 구조적으로 작성하는 것은 매우 중요하지만 어려운 일이기도 하다.
```jsx
export default function TodoApp() {
  return (
    <div className="TodoApp">
      Todo 관리 애플리케이션
      <LoginComponent />
      <WelcomeComponent />
    </div>
  );
}

function LoginComponent() {
  return (
    <div className="LoginComponent">
      로그인 컴포넌트
    </div>
  );
}

function WelcomeComponent() {
  return (
      <div className="WelcomeComponent">
        환영합니다
      </div>
  );
}
```
- 처음부터 무리해서 모듈을 분리하려 하지 말고 큰 구조를 잡아가면서 분리하는 것이 좋다.
- 리팩토링 단계에서 `LoginComponent`는 별도 모듈로 분리되고, 그 안에 입력 form 컴포넌트가 생겨날 것이다.

---