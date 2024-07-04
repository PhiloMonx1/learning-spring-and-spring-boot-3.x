import './TodoApp.css';
import {useState} from 'react';
export default function TodoApp() {
  return (
    <div className="TodoApp">
      Todo 관리 애플리케이션
      <LoginComponent />
      {/*<WelcomeComponent />*/}
    </div>
  );
}

function LoginComponent() {
  const [username, setUsername] = useState('eh13');
  const [password, setPassword] = useState('');
  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  return (
      <div className="LoginComponent">
        <div>
          <label>사용자명</label>
          <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
        </div>
        <div>
          <label>비밀번호</label>
          <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
        </div>
        <div>
          <button type="button" name="login">로그인</button>
        </div>
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