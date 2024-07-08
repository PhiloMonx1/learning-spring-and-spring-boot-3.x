import axios from "axios";

const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});

export const retrieveHelloWorldPathVariable
    = (username, token) => apiClient.get(`/hello-world/path-variable/${username}`,{
      headers: {
        Authorization: token
      }
    })

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`,{
      headers: {
        Authorization: token
      }
    })
