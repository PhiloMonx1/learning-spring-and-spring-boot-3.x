export default function ResetButton({resetCountMethod}){
  return (
      <button className="ResetButton" onClick={resetCountMethod}>초기화</button>
  )
}