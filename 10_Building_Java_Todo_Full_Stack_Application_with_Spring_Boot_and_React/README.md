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