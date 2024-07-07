import {Link, useParams} from "react-router-dom";
import axios from "axios";
import {useState} from "react";

export default function WelcomeComponent() {
  const params = useParams()
  const [message, setMessage] = useState(null)

  function callHelloWorldRestApi() {
    console.log("callHelloWorldRestApi")
    axios.get('http://localhost:8080/hello-world-bean')
    .then ((response) => successfulResponse(response))
    .catch((error) => failedResponse(error))
    .finally(() => console.log("finally"))
  }

  function successfulResponse(response) {
    console.log(response)
    setMessage(response.data.message)
  }

  function failedResponse(error) {
    console.log(error)
  }


  return (
      <div className="WelcomeComponent">
        <h1>환영합니다</h1>
        <div>
          {params.username}님! 만나서 반갑습니다.
        </div>
        <div>
          <Link to="/todos">Todo리스트</Link>
        </div>
        <div>
          <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>Hello World GET 요청</button>
        </div>
        <div className="text-info">{message }</div>
      </div>
  );
}