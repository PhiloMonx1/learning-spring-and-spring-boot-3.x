import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

function App() {
  return (
    <div className="App">
      <FirstComponent></FirstComponent>
      <SecondComponent></SecondComponent>
      <ThirdComponent />
      <FourthComponent />
    </div>
  );
}

function FirstComponent() {
  return (
      <div className="FirstComponent">첫 번째 컴포넌트</div>
  );
}

function SecondComponent() {
  return (
      <div className="SecondComponent">두 번째 컴포넌트</div>
  );
}

class ThirdComponent extends Component {
  render() {
    return (
        <div className="ThirdComponent">세 번째 컴포넌트</div>
    );
  }
}

class FourthComponent extends Component {
  render() {
    return (
        <div className="FourthComponent">네 번째 컴포넌트</div>
    );
  }
}

export default App;
