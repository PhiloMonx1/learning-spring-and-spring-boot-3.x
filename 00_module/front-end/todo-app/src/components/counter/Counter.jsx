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