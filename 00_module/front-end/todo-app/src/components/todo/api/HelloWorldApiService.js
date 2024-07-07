import axios from "axios";

const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});

export const retrieveHelloWorldPathVariable = (username) => apiClient.get(`/hello-world/path-variable/${username}`)