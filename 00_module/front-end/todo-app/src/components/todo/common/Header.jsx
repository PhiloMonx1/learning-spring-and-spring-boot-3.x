import {Link} from "react-router-dom";

export default function HeaderComponent() {
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