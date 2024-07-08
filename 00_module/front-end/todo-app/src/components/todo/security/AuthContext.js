import {createContext, useContext, useState} from "react";
import {executeBasicAuthenticationService} from "../api/HelloWorldApiService";
import {apiClient} from "../api/ApiClient";

const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}) {
  const [isAuthenticated, setAuthenticated] = useState(false)
  const [token, setToken] = useState(null)
  const [username, setUsername] = useState(null)

  async function login(username, password) {
    const basicToken = 'Basic ' + window.btoa(username + ":" + password)

    try {
        const response = await executeBasicAuthenticationService(basicToken)

      if(response.status === 200) {
        setAuthenticated(true)
        setUsername(username)
        setToken(basicToken)

        apiClient.interceptors.request.use(
            (config) => {
              config.headers.Authorization = basicToken
              return config
        })

        return true
      }
      else {
        logout()
        return false
      }
    } catch (error) {
      logout()
      return false
    }

  }

  function logout() {
    setAuthenticated(false);
    setUsername(null);
    setToken(null)
  }

  return (
      <AuthContext.Provider value={{isAuthenticated, login, logout, username, token}}>
        {children}
      </AuthContext.Provider>
  )
}