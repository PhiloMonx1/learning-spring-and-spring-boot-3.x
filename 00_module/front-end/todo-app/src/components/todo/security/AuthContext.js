import {createContext, useContext, useState} from "react";

const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}) {
  const [isAuthenticated, setAuthenticated] = useState(false)
  const [username, setUsername] = useState(null)

  function login(username, password) {
    const isLoginSuccess = username === 'eh13' && password === '950127'
    setAuthenticated(isLoginSuccess);
    setUsername(username);
    return isLoginSuccess;
  }

  function logout() {
    setAuthenticated(false);
    setUsername(null);
  }

  return (
      <AuthContext.Provider value={{isAuthenticated, login, logout, username}}>
        {children}
      </AuthContext.Provider>
  )
}