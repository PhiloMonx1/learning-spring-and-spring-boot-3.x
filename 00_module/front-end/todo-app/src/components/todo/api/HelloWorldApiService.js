import axios from "axios";

const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});

export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`,{
      headers: {
        Authorization: 'Basic ZWgxMzo5NTAxMjc='
      }
    })

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`,{
      headers: {
        Authorization: token
      }
    })
