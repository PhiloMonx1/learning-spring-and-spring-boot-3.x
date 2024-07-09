import {apiClient} from "../api/ApiClient";

export const retrieveHelloWorldPathVariable = (username, token) => apiClient.get(`/hello-world/path-variable/${username}`)

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`,{
      headers: {
        Authorization: token
      }
    })
