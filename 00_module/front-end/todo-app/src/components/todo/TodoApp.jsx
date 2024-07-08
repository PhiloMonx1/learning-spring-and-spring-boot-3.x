import './TodoApp.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import HeaderComponent from "./common/Header";
import FooterComponent from "./common/Footer";
import WelcomeComponent from "./pages/Welcome";
import ListTodosComponent from "./todos/ListTodos";
import TodoComponent from "./todos/TodoDetail";
import NotFoundErrorComponent from "./pages/NotFoundPage";
import LogoutComponent from "./auth/Logout";
import LoginComponent from "./auth/Login";
import AuthProvider from "./security/AuthContext";
import AuthenticatedRoute from "./security/AuthenticatedRoute";

export default function TodoApp() {
  return (
    <div className="TodoApp">

      <AuthProvider>
        <BrowserRouter>
          <HeaderComponent />
          <Routes>
            <Route path="/" element={<LoginComponent />} />
            <Route path="/login" element={<LoginComponent />} />
            <Route path="/welcome/:username" element={
              <AuthenticatedRoute>
                <WelcomeComponent />
              </AuthenticatedRoute>
            } />
            <Route path="/todos" element={
              <AuthenticatedRoute>
                <ListTodosComponent />
              </AuthenticatedRoute>
            } />
            <Route path="/todo/:id" element={
              <AuthenticatedRoute>
                <TodoComponent />
              </AuthenticatedRoute>
            } />
            <Route path="/logout" element={
              <AuthenticatedRoute>
                <LogoutComponent />
              </AuthenticatedRoute>
            } />

            <Route path="*" element={<NotFoundErrorComponent />} />
          </Routes>
          <FooterComponent />
        </BrowserRouter>
      </AuthProvider>

    </div>
  );
}