# 📒 [학습 노트] 챕터 9 : 카운터 예제를 통해 React 컴포넌트 살펴보기

## 목록
1. [카운터 예제를 통해 React 컴포넌트 살펴보기](#1단계---카운터-예제를-통해-react-컴포넌트-살펴보기)
2. [React 애플리케이션 시작하기 - Counte](#2단계---react-애플리케이션-시작하기---counter)
3. [React 애플리케이션 시작하기 - Counter-2](#3단계---react-애플리케이션-시작하기---counter-2)
4. [useState 훅을 사용해 React State 알아보기 - Counter에 상태 추가](#4단계---usestate-훅을-사용해-react-state-알아보기---counter에-상태-추가)
5. [React State 알아보기 - 백그라운드에서는 무슨 일이 일어날까?](#5단계---react-state-알아보기---백그라운드에서는-무슨-일이-일어날까)
6. [React Props 알아보기 - 카운터 증분 값 설정하기](#6단계---react-props-알아보기---카운터-증분-값-설정하기)
7. [여러 개의 카운터 버튼 추가하기](#7단계---여러-개의-카운터-버튼-추가하기)
8. [React State 끌어올리기 - CounterButton 및 CounterButton 구성](#8단계---react-state-끌어올리기---counterbutton-및-counterbutton-구성)

---

## 1단계 - 카운터 예제를 통해 React 컴포넌트 살펴보기

#### 컴포넌트는 여러 개의 파트로 구성되어 있다.
- 뷰 (View) - JSX로 작성하며 빌드 후 JS로 포팅된다.
- 로직 (Logic) - JS로 작성한다.
- 스타일 (Styling) - CSS로 작성한다.
- 상태 (State) : 특정 컴포넌트 내부에 저장되는 데이터.
- 프로퍼티(Props) : 다른 컴포넌트로 데이터를 전달할 때 사용

#### 카운터 애플리케이션
- 카운트 버튼(+1 | -1)을 클릭하면 숫자를 증가시키거나 감소시킨다.
- 초기화 버튼을 클릭하면 숫자를 0으로 초기화 한다.

---

## 2단계 - React 애플리케이션 시작하기 - Counter

#### 컴포넌트 버튼에 JS 함수 연결 실습 (onClick)
```js
export default function Counter() {

  function incrementCounterFunction() {
    console.log("증가 버튼 클릭 됨");
  }

  return (
      <div className="Counter">
        <span className="counter">0</span>
        <div>
          <button className="counterButton" onClick={incrementCounterFunction}>+1</button>
        </div>
      </div>
  )
}
```
- JS는 함수 안에 함수를 선언하는 것이 가능하다.
- 'onClick' 속성으로 함수를 버튼과 연결시킬 수 있다.
- 연결하는 함수는 중괄호로 묶어야 하고 이름 뒤에'()'를 붙이면 안된다.
  - 이름 뒤에'()'를 붙일 경우 페이지가 랜딩될 때 함수가 실행되며, 이 때 버튼 클릭으로 인한 함수 호출은 동작하지 않는다.

---

## 3단계 - React 애플리케이션 시작하기 - Counter-2

#### 리액트 컴포넌트 스타일 적용
1. Style 속성 사용
2. className 사용

#### Style 속성 사용 실습
```js
export default function Counter() {

  function incrementCounterFunction() {
    console.log("증가 버튼 클릭 됨");
  }

  return (
      <div className="Counter">
        <span className="counter">0</span>
        <div>
          <button className="counterButton"
              onClick={incrementCounterFunction}
              style={{
                fontSize: "30px",
                backgroundColor: "#00a5ab"
              }}
          >+1
          </button>
        </div>
      </div>
  )
}
```
- style 속성을 부여하고 중괄호를 두 번 묶어야 한다.

#### Style 속성 사용 개선 : 객체로 분리해서 사용하기
```js
export default function Counter() {

  const buttonStyle = {
    fontSize: "30px",
    backgroundColor: "#00a5ab"
  }

  function incrementCounterFunction() {
    console.log("증가 버튼 클릭 됨");
  }

  return (
      <div className="Counter">
        <span className="counter">0</span>
        <div>
          <button className="counterButton"
                  onClick={incrementCounterFunction}
                  style={buttonStyle}
          >+1</button>
        </div>
      </div>
  )
}
```
- 'buttonStyle' 객체를 선언하여 사용할 수 있다.

#### CSS 파일을 생성해서 개선하기
1. CSS 파일 작성
```css
/* /src/components/counter/Counter.css */

.counterButton {
  font-size: 30px;
  background-color: #00a5ab;
}
```
- '.counterButton' : css 선택자를 사용해서 버튼 컴포넌트를 선택한다.
- css 문법과 JSX 스타일 문법은 작성 방법이 다르다.

```js
/* /src/components/counter/Counter.js */

import './Counter.css';
export default function Counter() {

  function incrementCounterFunction() {
    console.log("증가 버튼 클릭 됨");
  }

  return (
      <div className="Counter">
        <span className="counter">0</span>
        <div>
          <button className="counterButton"
                  onClick={incrementCounterFunction}
          >+1</button>
        </div>
      </div>
  )
}
```
- 임포트만 해주면 CSS가 적용된다.

---

## 4단계 - useState 훅을 사용해 React State 알아보기 - Counter에 상태 추가

#### State
리액트의 내장 객체로 컴포넌트의 데이터나 정보를 저장하는 데 사용한다.
- 생성된 모든 객체는 모두 State를 가질 수 있다.
  - 같은 컴포넌트라도 인스턴스를 5개 만들면 5개 모두 각각 다른 state를 가진다.
- ‘useState’ 훅(Hooks)를 사용해 함수형 컴포넌트에 State를 구현할 수 있다.
  - ‘useState’가 반환하는 값
    - 현재 state 값
    - state를 업데이트하는 함수

#### 컴포넌트에 상태 추가 실습
```js
export default function Counter() {

  const state = useState(0);

  function incrementCounterFunction() {
    console.log(state);
    console.log("증가 버튼 클릭 됨");
  }
  
  //...(생략)
}
```
- 콘솔에 노출된 state는 두 개의 값을 리턴한다.
  - 첫 번째 인덱스 : '상태의 초기값'
  - 두 번째 인덱스 : 'dispatchSetState()' - 상태 업데이트를 처리하는 함수

#### `dispatchSetState()` 사용해서 컴포넌트의 상태 변경 실습
```js
export default function Counter() {

  const state = useState(0);
  function incrementCounterFunction() {
    state[1](state[0] + 1);
  }

  return (
          <div className="Counter">
            <span className="counter">{state[0]}</span>
            <div>
              <button className="counterButton"
                      onClick={incrementCounterFunction}
              >+1</button>
            </div>
          </div>
  )
}
```
- `state[1]()` : `dispatchSetState()` 함수와 동일하다.

#### 구조 분해를 활용해서 리팩토링
```js
import {useState} from 'react';
import './Counter.css';
export default function Counter() {

  const [count, setCount] = useState(0);
  function incrementCounterFunction() {
    setCount(count + 1);
  }

  return (
      <div className="Counter">
        <span className="counter">{count}</span>
        <div>
          <button className="counterButton"
                  onClick={incrementCounterFunction}
          >+1</button>
        </div>
      </div>
  )
}
```
- `const [count, setCount] = useState(0)`로 `useState(0)`의 각 인덱스를 '`useState[0]`=`count`', '`useState[1]`=`setCount`' 로 매핑시킬 수 있다.

---

## 5단계 - React State 알아보기 - 백그라운드에서는 무슨 일이 일어날까?

#### DOM(Document Object Model)
- HTML 페이지는 일반적으로 DOM 요소로 표현된다.
- HTML 페이지의 각 요소는 DOM 노드에 해당한다.
- 요소를 업데이트하려면 DOM을 업데이트해야 한다.

#### 순수 JS로 DOM 조작 예시
```js
// 상태를 관리할 변수
let count = 0;

// DOM 요소 선택
const counterElement = document.getElementById('counter');
const incrementButton = document.getElementById('incrementButton');
const decrementButton = document.getElementById('decrementButton');

// 카운터 값을 업데이트하고 화면에 표시하는 함수
function updateCounter() {
    counterElement.textContent = count;
}

// 증가 함수
function incrementCounter() {
    count++;
    updateCounter();
}

// 감소 함수
function decrementCounter() {
    count--;
    updateCounter();
}

// 이벤트 리스너 추가
incrementButton.addEventListener('click', incrementCounter);
decrementButton.addEventListener('click', decrementCounter);

// 초기 카운터 값 표시
updateCounter();
```

#### 리액트의 동작 방식
- 리액트는 ‘가상 DOM(Virtual DOM)’을 사용한다.
  - 가상 DOM : HTML DOM을 가상으로 만들어 메모리에 보관하는 가상 UI 표현
- JSX 코드는 가상 DOM를 업데이트 한다.
- 가상 DOM이 업데이트되면 리액트가 변경 사항을 파악해 HTML 페이지에 동기화 시킨다.
  - 페이지가 로딩 시 리액트는 페이지의 첫 번째 가상 DOM을 생성 (DOM v1)
  - 상태 업데이트 로직 발생 시 해당 컴포넌트를 다시 랜더링 하고 두 번째 가상 DOM 생성 (DOM v2)
  - DOM v1 과 DOM v2의 차이점을 비교해서 달라진 부분을 HTML 페이지에 반영

#### 추가 학습 '가상 DOM'이라는 용어에 대해...
최근 React 팀에서는 '가상 DOM' 이라는 용어의 사용을 줄이고 있다. 대신 'UI 트리(UI tree)' 또는 '리액트 엘리먼트 트리(React element tree)'라는 용어를 더 선호하는 추세이다.

- 이는 가상 DOM이 실제로는 React 요소 트리라는 점을 강조하기 위함이다.
- JSX 코드는 실제로 가상 DOM을 직접 업데이트하지 않는다.
  - JSX는 React.createElement() 호출로 변환되어 React 요소 트리를 생성하며, 이 트리가 가상 DOM의 역할을 수행한다.

---

## 6단계 - React Props 알아보기 - 카운터 증분 값 설정하기

#### 프로퍼티(Props)
컴포넌트 간 데이터를 전달하는 방식
- 하나의 컴포넌트는 여러 개의 프로퍼티를 가질 수 있다.
- 컴포넌트끼리 프로퍼티를 넘겨줄 수 있다.

#### 프로퍼티(Props) 사용 실습
```js
function App() {
  return (
    <div className="App">
      <PlayWithProps property1="value1" property2="value2"/>
    </div>
  );
}

function PlayWithProps(properties) {
  console.log(properties)
  return (
    <div>Props</div>
  );
}
```
- 콘솔 로그에서 `{property1: 'value1', property2: 'value2'}`를 확인할 수 있다.
  - 각각 property1, property2라는 프로퍼티를 가진 객체 형태로 전달된 것이다.
  - `properties.property1` 으로 각 객체에 접근이 가능하다. (값은 'value1'이다.)

#### 모던 JS의 프로퍼티 사용
```js
function PlayWithProps({property1, property2}) {
  console.log(property1)
  return (
    <div>Props</div>
  );
}
```
- 이와 같이 객체 구조 분해를 활용해서 리팩토링 할 수 있다.

#### 프로퍼티 활용해서 다양한 카운터 생산 실습
```js

// /src/App.js
function App() {
  return (
          <div className="App">
            <Counter by={1}/>
            <Counter by={2}/>
            <Counter by={5}/>
          </div>
  );
}

// /src/components/counter/Counter.jsx
import {useState} from 'react';
import './Counter.css';
export default function Counter({by}) {

  const [count, setCount] = useState(0);
  function incrementCounterFunction() {
    setCount(count + by);
  }

  function decrementCounterFunction() {
    setCount(count - by);
  }

  return (
      <div className="Counter">
        <span className="counter">{count}</span>
        <div>
          <button className="counterButton" onClick={incrementCounterFunction}>+{by}</button>
          <button className="counterButton" onClick={decrementCounterFunction}>-{by}</button>
        </div>
      </div>
  )
}
```

---

## 7단계 - 여러 개의 카운터 버튼 추가하기

#### 프로퍼티를 사용할 때의 유의점
1. 프로퍼티를 전달하지 않아도 컴파일 오류가 발생하지 않는다.
   - `<Counter />` 이렇게 프로퍼티를 전달하지 않아도 컴파일 단계에서 오류가 발생하지 않는다.
   - 그러나 의도하지 않은 동작으로 이어질 수 있어 유의해야 한다.
2. 프로퍼티의 타입을 잘 확인해야 한다.
    ```js
    // by값을 전달하는 부분
    function App() {
      return (
              <div className="App">
                <Counter by="1"/>
                <Counter by="2"/>
                <Counter by="5"/>
              </div>
      );
    }
    
    // count에 by 값을 더하는 함수
    function incrementCounterFunction() {
      setCount(count + by);
    }
    ```
    - `incrementCounterFunction()` 에서 처리하는 `count + by` 로직은 'count'나 'by' 중 하나가 문자열일 경우 문자열을 연결시키는 방식으로 동작한다.
      - ex) '1 + "5" = 15' 가 되는 식이다.
      - 해당 문제는 런타임시 발생하며 컴파일 단계에서 해당 문제를 검증하려면 TS(TypeScript)를 사용하거나 별도의 검증 로직을 작성해주어야 한다.

#### propTypes 제안하기
```js
import {PropTypes} from "prop-types";
//...(생략)
export default function Counter({by}) {
//...(생략)
  Counter.propTypes = {
    by: PropTypes.number
  }
}
```
- 이와 같은 방식으로 'Counter'의 프로퍼티를 지정해서 기본 타입을 제안할 수 있다.
- 이 경우 자바처럼 컴파일 단계에서 검증을 하지는 않지만, 지정하지 않은 타입이 전달되었을 때 브라우저 콘솔창에 경고 문구가 노출된다.
  ```
  Warning: Failed prop type: Invalid prop `by` of type `string` supplied to `Counter`, expected `number`.
      at Counter (http://localhost:3000/static/js/bundle.js:118:3)
      at App
  ```

#### 프로퍼티의 기본 값 지정

```js
Counter.defaultProps = {
  by: 1
}
```
- 이와 같은 방식으로 프로퍼티의 기본 값을 줄 수도 있다.

---

## 8단계 - React State 끌어올리기 - CounterButton 및 CounterButton 구성

#### 상태 끌어올리기(lifting state up)
여러 컴포넌트에서 공유해야 하는 state를 그들의 가장 가까운 공통 조상 컴포넌트로 이동시키는 기법
- 동일한 변경 사항을 여러 컴포넌트에 반영해야 할 때 사용
- '단방향 데이터 흐름 원칙'에 따라 상위 컴포넌트에서 하위 컴포넌트로의 전달만 허용한다. (props를 통해 데이터를 전달함)

#### 상태 끌어올리기(lifting state up) 패턴 준비 작업 실습
```js
export default function Counter() {
  const [count, setCount] = useState(0);

  function incrementCounterParentFunction(by) {
    setCount(count + by);
  }

  return (
      <>
        <span className="totalCounter">{count}</span>
        <CounterButton by={1}/>
        <CounterButton by={2}/>
        <CounterButton by={5}/>
      </>
  )
}

export function CounterButton({ by = 1 }) {
  // 구현 로직
}
```
- 'CounterButton' 는 기존의 'Counter'이며 기존 'App.js'에 있던 코드를 새로운 'Counter'에서 처리하고 있다.
- 이와 같이 자식 컴포넌트를 묶는 부모 컴포넌트를 생성하여 자식 컴포넌트로 props를 전달하는 "상태 끌어올리기(lifting state up)" 패턴을 구현할 수 있다.

#### 추가 학습 : `defaultProps` 에 대하여...
```js
CounterButton.defaultProps = {
  by: 1
}
```
- 해당 방법으로 프로퍼티의 기본 값을 설정하면 브라우저 콘솔에서 경고를 노출한다,
  - "Warning: Counter: Support for defaultProps will be removed from function components in a future major release. Use JavaScript default parameters instead."
  - 리액트 팀은 추후 `defaultProps`의 지원을 제거할 계획이라고 안내하고 있다.

#### 프로퍼티 기본 값 지정의 권장 방법
```js
export function CounterButton({ by = 1 }) {
  // 구현 로직
}
```
- JS 기본 문법으로도 파라미터의 기본 값을 지정할 수 있다.

---