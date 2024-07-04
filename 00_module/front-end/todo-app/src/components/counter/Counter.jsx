import {useState} from 'react';
import './Counter.css';
import CounterButton from "./CounterButton";
import ResetButton from "./ResetButton";

export default function Counter() {
  const [count, setCount] = useState(0);

  function incrementCounterParentFunction(by) {
    setCount(count + by);
  }

  function decrementCounterParentFunction(by) {
    setCount(count - by);
  }

  function resetCountFunction(){
    setCount(0)
  }

  return (
      <>
        <span className="totalCounter">{count}</span>
        <CounterButton by={1}
                       incrementMethod={incrementCounterParentFunction}
                       decrementMethod={decrementCounterParentFunction}/>
        <CounterButton by={2}
                       incrementMethod={incrementCounterParentFunction}
                       decrementMethod={decrementCounterParentFunction}/>
        <CounterButton by={5}
                       incrementMethod={incrementCounterParentFunction}
                       decrementMethod={decrementCounterParentFunction}/>
        <ResetButton resetCountMethod={resetCountFunction} />
      </>
  )
}