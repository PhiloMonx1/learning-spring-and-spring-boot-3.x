export default function ResetButton({resetCountMethod}){
  function resetCount(){
    resetCountMethod();
  }

  return (
      <button className="ResetButton" onClick={resetCount}>초기화</button>
  )
}