import './TodoApp.css';
import {useState} from 'react';
import {BrowserRouter, Routes, Route, useNavigate} from "react-router-dom";

export default function TodoApp() {
  return (
    <div className="TodoApp">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginComponent />} />
          <Route path="/login" element={<LoginComponent />} />
          <Route path="/welcome" element={<WelcomeComponent />} />
          <Route path="*" element={<ErrorComponent />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

function LoginComponent() {
  const navigate = useNavigate();
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
      navigate('/welcome');
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
        <h1>로그인</h1>
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
        <h1>환영합니다</h1>
        <div>
          만나서 반갑습니다!
        </div>
      </div>
  );
}

function ErrorComponent() {
  return (
      <div className="ErrorComponent">
        <h1>NOT FOUND</h1>
        <div>
          404! 페이지를 찾을 수 없습니다.
        </div>
      </div>
  );
}