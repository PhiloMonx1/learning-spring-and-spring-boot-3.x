import {useState} from 'react';
import './Counter.css';
import {PropTypes} from "prop-types";

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

CounterButton.propTypes = {
  by: PropTypes.number
}