import {createContext, useContext, useState} from "react";

const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}) {
  const [isAuthenticated, setAuthenticated] = useState(false)

  function login(username, password) {
    const isLoginSuccess = username === 'eh13' && password === '950127'
    setAuthenticated(isLoginSuccess);
    return isLoginSuccess;
  }

  function logout() {
    setAuthenticated(false);
  }

  return (
      <AuthContext.Provider value={{isAuthenticated, login, logout}}>
        {children}
      </AuthContext.Provider>
  )
}