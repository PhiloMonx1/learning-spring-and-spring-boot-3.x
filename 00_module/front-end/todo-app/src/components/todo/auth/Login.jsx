import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {useAuth} from "../security/AuthContext";

export default function LoginComponent() {
  const authContext = useAuth()
  const navigate = useNavigate();
  const [username, setUsername] = useState('eh13');
  const [password, setPassword] = useState('');
  const [showErrorMessage, setShowErrorMessage] = useState(false);
  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit() {
    if(authContext.login(username, password)) {
      navigate(`/welcome/${username}`);
    }
    else {
      setShowErrorMessage(true);
    }
  }

  return (
      <div className="Login">
        <h1>로그인</h1>
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