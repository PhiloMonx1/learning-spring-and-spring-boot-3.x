# 📒 [학습 노트] 챕터 9 : 카운터 예제를 통해 React 컴포넌트 살펴보기

## 목록
1. [카운터 예제를 통해 React 컴포넌트 살펴보기](#1단계---카운터-예제를-통해-react-컴포넌트-살펴보기)
2. [React 애플리케이션 시작하기 - Counte](#2단계---react-애플리케이션-시작하기---counter)

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