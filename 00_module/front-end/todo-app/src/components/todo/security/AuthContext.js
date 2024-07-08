import {createContext, useContext, useState} from "react";
import {executeBasicAuthenticationService} from "../api/HelloWorldApiService";

const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}) {
  const [isAuthenticated, setAuthenticated] = useState(false)
  const [username, setUsername] = useState(null)

  function login(username, password) {
    const basicToken = 'Basic ' + window.btoa(username + ":" + password)

    executeBasicAuthenticationService(basicToken)
    .then((response) => console.log(response))
    .catch((error) => console.log(error))

    setAuthenticated(false)
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