# 📒 [학습 노트] 챕터 10: Spring Boot와 React로 Java 풀스택 Todo 애플리케이션 만들기

## 목록
1. [React로 할 일 관리 앱 구축 시작하기](#1단계---react로-할-일-관리-앱-구축-시작하기)
2. [로그인 컴포넌트 시작하기 - Todo React 앱](#2단계---로그인-컴포넌트-시작하기---todo-react-앱)
3. [로그인 컴포넌트 개선 - Todo React 앱](#3단계---로그인-컴포넌트-개선---todo-react-앱)

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