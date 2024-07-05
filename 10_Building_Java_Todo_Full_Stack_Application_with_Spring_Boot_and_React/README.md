# 📒 [학습 노트] 챕터 10: Spring Boot와 React로 Java 풀스택 Todo 애플리케이션 만들기

## 목록
1. [React로 할 일 관리 앱 구축 시작하기](#1단계---react로-할-일-관리-앱-구축-시작하기)
2. [로그인 컴포넌트 시작하기 - Todo React 앱](#2단계---로그인-컴포넌트-시작하기---todo-react-앱)
3. [로그인 컴포넌트 개선 - Todo React 앱](#3단계---로그인-컴포넌트-개선---todo-react-앱)
4. [하드 코딩으로 인증 추가 - Todo React 앱](#4단계---하드-코딩으로-인증-추가---todo-react-앱)
5. [로그인 컴포넌트에서 조건에 따른 메시지 표시 - Todo React 앱](#5단계---로그인-컴포넌트에서-조건에-따른-메시지-표시---todo-react-앱)
6. [React Router DOM으로 로그인 컴포넌트에 라우팅](#6단계---react-router-dom으로-로그인-컴포넌트에-라우팅)
7. [React 앱에 에러 컴포넌트 추가하기](#7단계---react-앱에-에러-컴포넌트-추가하기)
8. [웰컴 컴포넌트에서 하드 코딩 삭제](#8단계---웰컴-컴포넌트에서-하드-코딩-삭제)
9. [할 일 목록 컴포넌트 React로 만들기](#9단계---할-일-목록-컴포넌트-react로-만들기)
10. [할 일 목록 컴포넌트에 상세 내용 보여주기](#10단계---할-일-목록-컴포넌트에-상세-내용-보여주기)
11. [헤더, 바닥글, 로그아웃 컴포넌트 React로 만들기](#11단계---헤더-바닥글-로그아웃-컴포넌트-react로-만들기)
12. [React 프론트엔드 애플리케이션에 Bootstrap 추가](#12단계---react-프론트엔드-애플리케이션에-bootstrap-추가)
13. [Bootstrap을 사용하여 Todo React 프론트엔드 애플리케이션에 스타일 적용](#13단계---bootstrap을-사용하여-todo-react-프론트엔드-애플리케이션에-스타일-적용)
14. [React 컴포넌트를 개별 JavaScript 모듈로 리팩토링](#14단계---react-컴포넌트를-개별-javascript-모듈로-리팩토링)
15. [인증 컨텍스트로 React State를 여러 컴포넌트와 공유하기](#14단계---react-컴포넌트를-개별-javascript-모듈로-리팩토링)
16. [React State를 업데이트하고 인증 컨텍스트를 통해 확인](#16단계---react-state를-업데이트하고-인증-컨텍스트를-통해-확인)
17. [isAuthenticated를 React State에 설정 - 인증 컨텍스트](#17단계---isauthenticated를-react-state에-설정---인증-컨텍스트)

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

## 3단계 - 로그인 컴포넌트 개선 - Todo React 앱

#### 읽기 전용 컴포넌트...?
```jsx
<input type="text" name="username" value="eh13"/>
```
해당 컴포넌트를 로드하면 브라우저 콘솔에 경고 메시지가 노출된다.
```
"Warning: You provided a `value` prop to a form field without an `onChange` handler. This will render a read-only field. If the field should be mutable use `defaultValue`. Otherwise, set either `onChange` or `readOnly`."
```
`value` prop을 제공했지만 `onChange` 핸들러가 없다는 것이다.
- 단순 경고 뿐 아니라 필드의 값을 수정하는 것도 불가능하다.
- 리액트는 State의 불변성을 중요하게 여긴다. `value` 값이 정해져 있으면 기본적으로 변경할 수 없다.
- 리액트를 사용하는 것으로 `readOnly` prop 없이 필드가 강제 읽기 전용이 되었다.

#### 제어 컴포넌트(Controlled Component)
리액트가 state를 제어할 수 있는 컴포넌트
```
"Warning: You provided a `value` prop to a form field without an `onChange` handler. This will render a read-only field. If the field should be mutable use `defaultValue`. Otherwise, set either `onChange` or `readOnly`."
```
해당 문제를 좀 더 풀이를 하자면
- 현재 리액트는 컴포넌트의 state를 제어할 수 없는 상태이다.
  - `value`는 고정되어 있고, 이것을 리액트가 변경할 수 있는 방법을 알려주지 않아서 필드 값의 변경이 동작하지 않는 것.
- `onChange`를 추가하여 리액트가 해당 필드의 state를 제어할 수 있도록 할 수 있다.
  - **리액트가 state를 제어할 수 있는 컴포넌트를 '제어 컴포넌트'라고 부른다.**

#### 제어 컴포넌트 실습
1. `useState`를 선언하고 초기화 한다.
    ```jsx
    const [username, setUsername] = useState('eh13');
    ```
2. 기존 'username' 필드의 value 프로퍼티에 useState를 연결한다.
    ```jsx
    <input type="text" name="username" value={username} />
    ```
3. onChange 핸들러 함수 추가
    ```jsx
      function handleUsernameChange(event) {
        setUsername(event.target.value);
      }
    ```
   - event : 사용자의 행동(예: 클릭, 키 입력)에 의해 발생한 이벤트에 대한 정보를 담고 있는 객체 
     - onChange, onClick 등에 의해 자동으로 연결 핸들러 함수로 전달된다.
     - `handleUsernameChange()` 내에 `console.log(event)`를 추가하여 event를 확인해 볼 수 잇다.
   - event.target : 이벤트가 발생한 DOM 요소를 가리킨다.
   - `setUsername(event.target.value)` : username state를 `event.target.value` 값으로 변경한다. (Setter와 유사함)
4. 필드에 onChange 함수 연결
    ```jsx
    <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
    ```
5. 리액트는 이제 name="username" input을 제어할 수 있다. 이는 onChange에 핸들러 함수를 연결했기 때문이며, 핸들러 함수에 state 객체를 조작하는 로직이 포함되어 있기 때문이다.

#### 추가 학습 : prop 복습
- 프로퍼티(property, 속성)의 줄임말이다.
- HTML에 부여되는 대부분의 속성 역시 prop 이다.
  - ex) `<input type="text" name="username" value="eh13"/>`를 기준으로 type, name, value 등

#### 추가 학습 : useState 복습
useState는 리액트가 조작할 수 있는 데이터 객체를 생성하는 것과 같다. `useState()`를 선언할 때 리액트가 관리하는 데이터 베이스에 데이터 객체가 생성되는 것으로 이해할 수 있다.
```jsx
  const [username, setUsername] = useState('eh13');
  const [password, setPassword] = useState('');
```
이와 같은 useState가 있다고 가정하자
- 선언 코드 이해 (`const [username, setUsername] = useState('eh13');`을 기준으로)
  - `useState('eh13')` 부분은 JAVA의 생성자 호출을 통한 인스턴스 생성과 유사하다
  - `username`는 Getter 메서드를 통해 값을 가져오는 것과 유사하다
  - `setUsername` 는 Setter 메서드를 통해 값을 수정하는 것과 유사하다
  - 즉, 'eh13'이라는 값으로 `useState` 타입의 객체를 초기화 하고 Getter, Setter 메서드를 사용하는 것으로 비유할 수 있다.
- 선언 방식 이해
  - useState는 여러 개 생성할 수 있으며 지역 내에서 고유한 변수, 함수명을 사용해야 한다.
    - username(변수), setUsername(함수), password(변수), setPassword(함수) 해당 4개의 네이밍은 각각 고유해야 한다.
    - 각 useState는 서로 완전히 독립적인 상태이다. 자신만의 값과 업데이트 함수를 가진다.

---

## 4단계 - 하드 코딩으로 인증 추가 - Todo React 앱

#### 하드코딩 인증 추가 실습
```jsx
function LoginComponent() {
  const [username, setUsername] = useState('eh13');
  const [password, setPassword] = useState('');
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [showErrorMessage, setShowErrorMessage] = useState(false);
  //...(생략)
  function handleSubmit() {
    if(username === 'eh13' && password === '950127') {
      setShowSuccessMessage(true);
      setShowErrorMessage(false);
    }
    else {
      setShowSuccessMessage(false);
      setShowErrorMessage(true);
    }
  }

  function SuccessMessageComponent() {
    if(showSuccessMessage) {
      return (<div className="successMessage">인증 성공</div>)
    }
    return null
  }

  function ErrorMessageComponent() {
    if(showErrorMessage) {
      return (<div className="errorMessage">인증 실패 : 인증 정보를 확인해주세요.</div>)
    }
    return null
  }

  return (
      <div className="Login">
        <SuccessMessageComponent />
        <ErrorMessageComponent />
        {/*...(생략)*/}
      </div>
  );
}
```
- `SuccessMessageComponent()` 메서드에선 조건문 안에 컴포넌트 리턴문을 담아 특정 조건에 충족할 때만 컴포넌트를 노출시키고 있다.

---

## 5단계 - 로그인 컴포넌트에서 조건에 따른 메시지 표시 - Todo React 앱

#### JavaScript의 '단축 평가'
JavaScript의 &&(AND 연산자)는 '단축 평가'라는 특징을 가지고 있다.
```js
true && 'EH13' //결과 값 : EH13
1+1 === 2 && 'EH13' //결과 값 : EH13
1+1 === 3 && 'EH13' //결과 값 : false
```
- 왼쪽 피연산자를 평가
- 거짓이면 'false' 리턴
- 참이면 오른쪽 피연산자 리턴

#### 단축 평가 활용 실습
```jsx
  //...(생략)
  return (
      <div className="Login">
        {showSuccessMessage && <div className="successMessage">인증 성공</div>}
        {showErrorMessage && <div className="errorMessage">인증 실패 : 인증 정보를 확인해주세요.</div>}
        {/*...생략  */}
      </div>
  );
```
- 단축 평가를 활용해서 기존 `SuccessMessageComponent` 함수를 아예 삭제할 수 있다.

---

## 6단계 - React Router DOM으로 로그인 컴포넌트에 라우팅

#### 라우터(Router)
URL에 따라서 적절한 컴포넌트를 리턴하는 기술
- ex) '/home = 홈페이지', '/login = 로그인 페이지' 등
- Spring의 API(JSP 리턴) 엔드포인트와 유사하다.
- 페이지 자체가 변하는 것이 아닌, 동일 페이지에서 컴포넌트만 변경한다. (SPA)
  - 하나의 HTML 파일 내에서 URL에 따라 적절한 컴포넌트의 교체가 이루어짐.

#### React Router DOM
React 애플리케이션에서 라우팅을 구현하기 위한 라이브러리
- SPA(Single Page Application) 구현 가능

#### React Router DOM 설치
```
npm install react-router-dom
```
- npm을 통해 설치가 완료되면 `package.json` 파일 내에서 확인 할 수 있다.

#### Route 사용하기
```jsx
import {BrowserRouter, Routes, Route} from "react-router-dom";

export default function TodoApp() {
  return (
      <div className="TodoApp">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/welcome" element={<WelcomeComponent />} />
          </Routes>
        </BrowserRouter>
      </div>
  );
}
```
- path : 엔드포인트
- element : 'path' URL에서 노출할 컴포넌트

#### 다른 컴포넌트로 라우팅 실습
```jsx
import {BrowserRouter, Routes, Route, useNavigate} from "react-router-dom";
//...(생략)

function LoginComponent() {
  const navigate = useNavigate();
  //...(생략)
  function handleSubmit() {
    if(username === 'eh13' && password === '950127') {
      setShowSuccessMessage(true);
      setShowErrorMessage(false);
      navigate('/welcome');
    }
    else {
      setShowSuccessMessage(false);
      setShowErrorMessage(true);
    }
  }
  //...(생략)
}
```
- useNavigate 를 사용해서 다른 라우터를 호출할 수 있다.

---

## 7단계 - React 앱에 에러 컴포넌트 추가하기

현재 존재하지 않는 엔드포인트 URL을 입력할 경우 빈페이지가 표시되고 콘솔창에 에러가 노출된다. 에러 컴포넌트로 예외 처리를 할 것이다.
#### 컴포넌트 생성
```jsx
function ErrorComponent() {
  return (
      <div className="ErrorComponent">
        <h1>NOT FOUND</h1>
        <div>
          404! 페이지를 찾을 수 없습니다.
        </div>
      </div>
  );
}
```
- 간단한 404 컴포넌트를 추가했다.

```jsx
export default function TodoApp() {
  return (
      <div className="TodoApp">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/welcome" element={<WelcomeComponent />} />
            <Route path="*" element={<ErrorComponent />} />
          </Routes>
        </BrowserRouter>
      </div>
  );
}
```
- 'path'를 `*` 로 작성할 경우 나머지 라우터에 해당하지 않은 경우 모두 `*`로 잡힌다.

---

## 8단계 - 웰컴 컴포넌트에서 하드 코딩 삭제

#### userParams
라우팅 경로에 해당하는 현재 URL에서 동적 파라미터를 Key/Value 쌍을 가진 객체로 반환한다.

#### userParams 실습
1. 임포트
    ```jsx
    import {useParams} from "react-router-dom";
    ```
2. URL파라미터(동적 세그먼트) 전달
    ```jsx
    <Route path="/welcome/:username" element={<WelcomeComponent />} />
    ```
    - 'username' 이라는 이름으로 동적 세그먼트를 전달.
    - 스프링의 `@PathVariable`과 유사하다.
3. 네이게이터 변경
    ```jsx
      function handleSubmit() {
        if(username === 'eh13' && password === '950127') {
          setShowSuccessMessage(true);
          setShowErrorMessage(false);
          navigate(`/welcome/${username}`);
        }
        else {
          setShowSuccessMessage(false);
          setShowErrorMessage(true);
        }
      }
    ```
    - ${변수} 방식을 사용하기 위해서는 따옴표(')가 아닌 백틱(`)을 사용해야 한다.
4. 파라미터 사용
    ```jsx
    function WelcomeComponent() {
      const params = useParams()
    
      return (
          <div className="WelcomeComponent">
            <h1>환영합니다</h1>
            <div>
              {params.username}님! 만나서 반갑습니다.
            </div>
          </div>
      );
    }
    ```
    - `params.username` 대신 `const {username} = useParams()` 으로 사용할 수도 있다.

---

## 9단계 - 할 일 목록 컴포넌트 React로 만들기

#### 컴포넌트에서 리스트 렌더링(반목문) 실습
```jsx
function ListTodosComponent() {
  const todos = [
    {id: 1, description: 'AWS 배우기'},
    {id: 2, description: 'Spring Boot 배우기'},
    {id: 3, description: 'React 배우기'},
  ]


  return (
      <div className="ListTodosComponent">
        <h1>나의 TODO 리스트</h1>
        <div>
          <table>
            <thead>
            <tr>
              <th>id</th>
              <th>할 일</th>
            </tr>
            </thead>
            <tbody>
            {
              todos.map((todo) => (
                  <tr>
                    <td>{todo.id}</td>
                    <td>{todo.description}</td>
                  </tr>
              ))
            }
            </tbody>
          </table>
        </div>
      </div>
  );
}
```
- `todos.map()` 배열 내부에 map 함수를 사용해서 반복할 로직을 작성하면 된다.
  - (todo) => () : todos 배열의 각 인덱스는 화살표 다음 괄호의 로직을 반복한다.

#### List의 고유 key
위에서 제안한 방식으로 코드를 작성하면 브라우저 콘솔창에 다음과 같은 경고 메시지가 출력된다.
```
TodoApp.jsx:125 Warning: Each child in a list should have a unique "key" prop.
```
제공된 리스트의 각 인덱스에는 고유한 key가 있어야 한다고 말하고 있다. 
- 리액트는 key를 사용하여 리스트의 각 항목을 고유하게 식별한다.
- 해당 key를 통해서 리스트의 항목이 변경되었을 때 어떤 항목을 선택해야 하는지 리액트가 구분할 수 있다.
- key로 인해 리스트의 전체 항목을 조회할 필요가 없어 최적화 된 렌더링 환경을 구성할 수 있다.

#### List 고유 key 삽입
```jsx
<tbody>
{
  todos.map((todo) => (
      <tr key={todo.id}>
        <td>{todo.id}</td>
        <td>{todo.description}</td>
      </tr>
  ))
}
</tbody>
```
- 반복해서 작성되는 <tr> 태그 각각에 `todo.id`를 기반으로 고유한 key가 부여된다.
- key를 부여하는 것은 성능 최적화 및 컴포넌트의 올바른 동작을 위해 선택이 아닌 '필수' 사항으로 인지해야 한다.
  - 컴파일 에러가 아닌 런타임 에러가 콘솔창에 노출되는 리액트의 특성상 더 유의할 필요가 있음. 

---

## 10단계 - 할 일 목록 컴포넌트에 상세 내용 보여주기

#### a태그 대신 Link 사용하기
```jsx
import {Link} from "react-router-dom";

<Link to="/todos">Todo리스트</Link>
```
- 'react-router-dom'의 Link를 사용하면 a 태그를 대체할 수 있다.
  - `<a href="/todos">Todo리스트</a>` 와 동일하다.
- a태그는 이동 시 페이지 전체를 로드하지만 Link의 경우 컴포넌트만 로드한다.
  - SPA 원칙에 따라 동작한다.

---

## 11단계 - 헤더, 바닥글, 로그아웃 컴포넌트 React로 만들기

#### 헤더 푸터 적용하기
```jsx
export default function TodoApp() {
  return (
      <div className="TodoApp">
        <HeaderComponent />

        <BrowserRouter>
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/welcome/:username" element={<WelcomeComponent />} />
            <Route path="/todos" element={<ListTodosComponent />} />
            <Route path="/logout" element={<LogoutComponent />} />

            <Route path="*" element={<ErrorComponent />} />
          </Routes>
        </BrowserRouter>

        <FooterComponent />
      </div>
  );
}
```
- `TodoApp` 컴포넌트가 최상위 컴포넌트이기 때문에 해당 컴포넌트에 삽입하면 모든 라우터에서도 헤더 & 푸터가 포함된다.

---

## 12단계 - React 프론트엔드 애플리케이션에 Bootstrap 추가

#### npm 설치 (bootstrap)
```
npm install bootstrap
```

#### 부트스트랩 임포트
- 페이지 전체에 적용하기 위해 'index.js' 파일에 임포트한다.
    ```js
    import 'bootstrap/dist/css/bootstrap.min.css'
    ```
    - 'node_modules' 내부에 bootstrap 폴더를 찾을 수 있다.
    - import 할 때는 'node_modules'는 경로에서 제외해도 된다.

#### 부트스트랩 사용해서 스타일 개선
```jsx
  //...(생략)
  return (
      <div className="container">
        <h1>나의 TODO 리스트</h1>
        <div>
          <table className="table">
            <thead>
            <tr>
              <th>id</th>
              <th>할 일</th>
              <th>완료 여부</th>
              <th>목표 일자</th>
            </tr>
            </thead>
            <tbody>
            {
              todos.map((todo) => (
                  <tr key={todo.id}>
                    <td>{todo.id}</td>
                    <td>{todo.description}</td>
                    <td>{todo.done.toString()}</td>
                    <td>{todo.targetDate.toDateString()}</td>
                  </tr>
              ))
            }
            </tbody>
          </table>
        </div>
      </div>
  );
//...(생략)
```
- ListTodosComponent()에 적용했다.
- className="container", className="table" 가 부트스트랩이 적용된 클래스이다.

---

## 13단계 - Bootstrap을 사용하여 Todo React 프론트엔드 애플리케이션에 스타일 적용

#### "react-router-dom"를 사용할 때 주의점
- "react-router-dom"에서 제공하는 컴포넌트(Link 등)는 'BrowserRouter' 컴포넌트 하위 컴포넌트에서만 사용이 가능하다.

#### 헤더 구현 실습
```jsx
function HeaderComponent() {
  return (
      <header className="border-bottom border-light border-5 mb-5 p-2">
        <div className="container">
          <div className="row">
            <nav className="navbar navbar-expand-lg">
              <a className="navbar-brand ms-2 fs-2 fw-bold text-black" href="http://localhost:3000/">🫐블루베리 Todo</a>
              <div className="collapse navbar-collapse">
                <ul className="navbar-nav">
                  <li className="nav-item fs-5"><Link className="nav-link" to="/welcome/eh13">Home</Link></li>
                  <li className="nav-item fs-5"><Link className="nav-link" to="/todos">Todo 목록</Link></li>
                </ul>
              </div>
              <ul className="navbar-nav">
                <li className="nav-item fs-5"><Link className="nav-link" to="/login">로그인</Link></li>
                <li className="nav-item fs-5"><Link className="nav-link" to="/logout">로그아웃</Link></li>
              </ul>
            </nav>
          </div>
        </div>
      </header>

  );
}
```
- 부트스트랩을 활용해서 헤더 구현.

---

## 14단계 - React 컴포넌트를 개별 JavaScript 모듈로 리팩토링

#### 모듈 분리
- 같은 모듈 내에서 함수 단위로 구분되던 컴포넌트를 모듈 단위로 분리한다.
  - 컴포넌트 함수를 통째로 복사해서 새로운 모듈에 붙여넣으면 된다.
  - 'react-router-dom'을 사용하는 컴포넌트는 import 문을 신경써서 옮겨주어야 한다.
  - 모듈 파일명은 'Component'를 제외했다. (컴포넌트는 대문자로 시작한다는 규칙을 지키면 컴포넌트임을 명시하지 않아도 알아볼 수 있을 것이라 판단)
  - 기존 'ErrorComponent'는 'NotFoundErrorComponent'로 리네이밍 했다.
  - 강의에서는 폴더 구조까지 리팩토링하지 않지만 개인적으로 진행했다.

---

## 15단계 - 인증 컨텍스트로 React State를 여러 컴포넌트와 공유하기

#### 컨텍스트(Context)
컴포넌트 트리 전체에 걸쳐 데이터를 효율적으로 공유할 수 있게 해주는 내장 기능 
- 전역적으로 사용이 필요한 (사용자 정보) 등을 처리할 때 주로 사용
- props를 통해 여러 계층의 컴포넌트를 거치지 않고도 데이터를 공유
  - 깊은 계층 구조에서 여러 컴포넌트를 거쳐 props를 전달하는 문제(props drilling)를 해결
- 주의점
  - Context를 과도하게 사용하면 컴포넌트 재사용이 어려워질 수 있다.
  - Context가 변경될 때마다 하위 컴포넌트들이 리렌더링 될 수 있다.

#### 컨텍스트 사용 실습
1. 컨텍스트 생성
    ```jsx
    import {createContext} from "react";
    const AuthContext = createContext()
    ```
2. AuthProvider 컴포넌트 정의
    ```jsx
    import {createContext} from "react";
    const AuthContext = createContext()
    
    export default function AuthProvider({children}) {
      return (
          <AuthContext.Provider>
            {children}
          </AuthContext.Provider>
      )
    }
    ```
    - `{children}` : `AuthProvider` 컴포넌트의 하위 컴포넌트는 children 파라미터를 통해 전달된다.
    - 외부에서 `AuthProvider` 컴포넌트 안에 다른 컴포넌트를 하위 컴포넌트로 넣으면 결과적으로는 `<AuthContext.Provider>` 안에 하위 컴포넌트가 자리하게 된다.
3. TodoApp 에서 사용
    ```jsx
    export default function TodoApp() {
      return (
          <div className="TodoApp">
    
            <AuthProvider>
              <BrowserRouter>
                <HeaderComponent />
                <Routes>
                  <Route path="/" element={<LoginComponent />} />
                  <Route path="/login" element={<LoginComponent />} />
                  <Route path="/welcome/:username" element={<WelcomeComponent />} />
                  <Route path="/todos" element={<ListTodosComponent />} />
                  <Route path="/logout" element={<LogoutComponent />} />
    
                  <Route path="*" element={<NotFoundErrorComponent />} />
                </Routes>
                <FooterComponent />
              </BrowserRouter>
            </AuthProvider>
    
          </div>
      );
    }
    ```
    - `<AuthProvider>` 컴포넌트 안에 다른 컴포넌트를 배치한다.
    - BrowserRouter, HeaderComponent, Routes, 모든 Route 컴포넌트는 `<AuthContext.Provider>`로 묶이는 것과 같아졌다.

#### 컨텍스트에 State 추가하기
```jsx
import {createContext, useState} from "react";

export const AuthContext = createContext()

export default function AuthProvider({children}) {
  const [number, setNumber] = useState(0)

  return (
      <AuthContext.Provider value={ {number} }>
        {children}
      </AuthContext.Provider>
  )
}
```
- 'AuthContext.Provider'에 `value` 프로퍼티를 설정했다.
  - value를 통해 데이터를 전달할 수 있다.
- 'AuthContext'를 익스포트 했다.

```jsx
import {AuthContext} from "../security/AuthContext";
import {useContext} from "react";

export default function HeaderComponent() {

  const authContext = useContext(AuthContext)
  console.log(authContext.number);
  //...(생략)
}
```
- 외부에서 `useContext(AuthContext)`로  `AuthContext`의 State에 접근이 가능하다.
  - 콘솔을 확인해보면 `number`의 초기값이 0이 출력된다.
- 현재 `HeaderComponent`의 경우 모든 컴포넌트와 함께 출력되기 때문에 제외한 다른 URL 라우터에서도 해당 값은 유지된다.

---

## 16단계 - React State를 업데이트하고 인증 컨텍스트를 통해 확인

#### setInterval
일정 주기마다 지정된 함수를 반복 실행하도록 설정하는 함수.
```js
  setInterval(
      () => setNumber(number + 1),
      10000
  )
```
- 10초 (10000ms) 마다 `setNumber(number + 1)` 로직이 반복된다.

#### `const authContext = useContext(AuthContext);` 개선
```jsx
// 선언부 AuthContext.js
export const useAuth = () => useContext(AuthContext);

// 사용부 Header.jsx
const authContext = useAuth()
```
- 'AuthContext'를 기존 `const authContext = useContext(AuthContext);`로 가지고 오던 코드를 개선했다.
- 선언하는 곳에서 `useAuth` 함수를 만들어 해당 함수를 내보내면 'AuthContext'를 직접적으로 내보내지 않아도 된다.
  - 'AuthContext'의 내부 구현을 숨기고, 사용자에게 필요한 인터페이스만 노출

---

## 17단계 - isAuthenticated를 React State에 설정 - 인증 컨텍스트

#### 인증 관련 state 추가 
```jsx
  const [isAuthenticated, setAuthenticated] = useState(false)
```
- AuthContext에 인증 상태를 관리하는 state를 추가한다.

#### value 전달
```jsx
  return (
      <AuthContext.Provider value={ {number, isAuthenticated, setAuthenticated} }>
        {children}
      </AuthContext.Provider>
  )
```
- AuthContext.Provider의 value에 인증 관련 state를 추가한다.

#### value 리팩토링
```jsx
  const valueToBeShared = {number, isAuthenticated, setAuthenticated}
  return (
      <AuthContext.Provider value={ valueToBeShared }>
        {children}
      </AuthContext.Provider>
  )
```
- value에 포함될 객체를 변수로 선언해서 전달할 수도 있다.
- 일반적으로 잘 사용되지 않는 방법이다. (value 객체가 복잡할 경우 고려할 수 있다)

#### 로그인 컴포넌트에서 setAuthenticated 사용
```jsx
  function handleSubmit() {
    if(username === 'eh13' && password === '950127') {
      authContext.setAuthenticated(true);
      
      setShowSuccessMessage(true);
      setShowErrorMessage(false);
      navigate(`/welcome/${username}`);
    }
    else {
      setShowSuccessMessage(false);
      setShowErrorMessage(true);
    }
  }
```
- 로그인이 성공했을 때 `authContext.setAuthenticated(true)`로 'isAuthenticated' 값을 true로 바꿔준다.

#### isAuthenticated 값 사용
```jsx
export default function HeaderComponent() {

  const authContext = useAuth()
  const isAuthenticated = authContext.isAuthenticated

  return (
      <header className="border-bottom border-light border-5 mb-5 p-2">
        {/*...(생략)*/}
                  <li className="nav-item fs-5">{isAuthenticated && <Link className="nav-link" to="/welcome/eh13">Home</Link>}</li>
                  <li className="nav-item fs-5">{isAuthenticated && <Link className="nav-link" to="/todos">Todo 목록</Link>}</li>
        {/*...(생략)*/}
      </header>
  );
}
```
- isAuthenticated 값이 true 일 경우에만 'Home', 'Todo 목록' 링크를 노출하도록 설정한다.
```jsx
<li className="nav-item fs-5">{!isAuthenticated && <Link className="nav-link" to="/login">로그인</Link>}</li>
<li className="nav-item fs-5">{isAuthenticated && <Link className="nav-link" to="/logout">로그아웃</Link>}</li>
```
- 로그인의 경우 `!isAuthenticated` 조건으로 isAuthenticated 값이 'false' 일 때만 메뉴 노출이 되도록 설정할 수 있다.

#### 로그아웃 구현
```jsx
  function logout() {
    authContext.setAuthenticated(false)
  }
  <li className="nav-item fs-5">{isAuthenticated && <Link className="nav-link" to="/logout" onClick={logout}>로그아웃</Link>}</li>
```
- logout() 함수를 로그아웃 링크 onClick 으로 연결시킨다.

---