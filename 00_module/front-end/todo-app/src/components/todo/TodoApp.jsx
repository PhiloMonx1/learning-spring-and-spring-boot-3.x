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
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [showErrorMessage, setShowErrorMessage] = useState(false);
  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit() {
    if(username === 'eh13' && password === '950127') {
      setShowSuccessMessage(true);
      setShowErrorMessage(false);
    }
    else {
      setShowSuccessMessage(false);
      setShowErrorMessage(true);
    }
  }

  function SuccessMessageComponent() {
    if(showSuccessMessage) {
      return (<div className="successMessage">인증 성공</div>)
    }
    return null
  }

  function ErrorMessageComponent() {
    if(showErrorMessage) {
      return (<div className="errorMessage">인증 실패 : 인증 정보를 확인해주세요.</div>)
    }
    return null
  }

  return (
      <div className="Login">
        {showSuccessMessage && <div className="successMessage">인증 성공</div>}
        {showErrorMessage && <div className="errorMessage">인증 실패 : 인증 정보를 확인해주세요.</div>}
        <div className="LoginForm">
          <div>
            <label>사용자명</label>
            <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
          </div>
          <div>
            <label>비밀번호</label>
            <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
          </div>
          <div>
            <button type="button" name="login" onClick={handleSubmit}>로그인</button>
          </div>
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