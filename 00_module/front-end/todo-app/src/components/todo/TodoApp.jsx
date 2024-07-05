import './TodoApp.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import HeaderComponent from "./common/Header";
import FooterComponent from "./common/Footer";
import WelcomeComponent from "./pages/Welcome";
import ListTodosComponent from "./todos/ListTodos";
import NotFoundErrorComponent from "./pages/NotFoundPage";
import LogoutComponent from "./auth/Logout";
import LoginComponent from "./auth/Login";
import AuthProvider from "./security/AuthContext";

export default function TodoApp() {
  return (
    <div className="TodoApp">

      <AuthProvider>
        <BrowserRouter>
          <HeaderComponent />
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/welcome/:username" element={<WelcomeComponent />} />
            <Route path="/todos" element={<ListTodosComponent />} />
            <Route path="/logout" element={<LogoutComponent />} />

            <Route path="*" element={<NotFoundErrorComponent />} />
          </Routes>
          <FooterComponent />
        </BrowserRouter>
      </AuthProvider>

    </div>
  );
}