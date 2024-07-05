import './TodoApp.css';
import {useState} from 'react';
import {BrowserRouter, Routes, Route, useNavigate, useParams, Link} from "react-router-dom";

export default function TodoApp() {
  return (
    <div className="TodoApp">

      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path="/" element={<LoginComponent />} />
          <Route path="/login" element={<LoginComponent />} />
          <Route path="/welcome/:username" element={<WelcomeComponent />} />
          <Route path="/todos" element={<ListTodosComponent />} />
          <Route path="/logout" element={<LogoutComponent />} />

          <Route path="*" element={<ErrorComponent />} />
        </Routes>
        <FooterComponent />
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
      navigate(`/welcome/${username}`);
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
  const params = useParams()

  return (
      <div className="WelcomeComponent">
        <h1>환영합니다</h1>
        <div>
          {params.username}님! 만나서 반갑습니다.
        </div>
        <div>
           <Link to="/todos">Todo리스트</Link>
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

function ListTodosComponent() {
  const today = new Date();
  const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay());

  const todos = [
    {id: 1, description: 'AWS 배우기', done: false, targetDate: targetDate},
    {id: 2, description: 'Spring Boot 배우기', done: false, targetDate: targetDate},
    {id: 3, description: 'React 배우기', done: false, targetDate: targetDate},
  ]


  return (
      <div className="container">
        <h1>나의 TODO 리스트</h1>
        <div>
          <table className="table">
            <thead>
            <tr>
              <th>id</th>
              <th>할 일</th>
              <th>완료 여부</th>
              <th>목표 일자</th>
            </tr>
            </thead>
            <tbody>
            {
              todos.map((todo) => (
                  <tr key={todo.id}>
                    <td>{todo.id}</td>
                    <td>{todo.description}</td>
                    <td>{todo.done.toString()}</td>
                    <td>{todo.targetDate.toDateString()}</td>
                  </tr>
              ))
            }
            </tbody>
          </table>
        </div>
      </div>
  );
}

function HeaderComponent() {
  return (
      <header className="border-bottom border-light border-5 mb-5 p-2">
        <div className="container">
          <div className="row">
            <nav className="navbar navbar-expand-lg">
              <a className="navbar-brand ms-2 fs-2 fw-bold text-black" href="http://localhost:3000/">🫐블루베리 Todo</a>
              <div className="collapse navbar-collapse">
                <ul className="navbar-nav">
                  <li className="nav-item fs-5"><Link className="nav-link" to="/welcome/eh13">Home</Link></li>
                  <li className="nav-item fs-5"><Link className="nav-link" to="/todos">Todo 목록</Link></li>
                </ul>
              </div>
              <ul className="navbar-nav">
                <li className="nav-item fs-5"><Link className="nav-link" to="/login">로그인</Link></li>
                <li className="nav-item fs-5"><Link className="nav-link" to="/logout">로그아웃</Link></li>
              </ul>
            </nav>
          </div>
        </div>
      </header>

  );
}

function FooterComponent() {
  return (
      <footer className="footer">
        <div className="container">
          푸터
        </div>
      </footer>
  );
}

function LogoutComponent() {
  return (
      <div className="LogoutComponent">
        <h1>로그아웃</h1>
        <div>
          안녕히 가세요
        </div>
      </div>
  );
}