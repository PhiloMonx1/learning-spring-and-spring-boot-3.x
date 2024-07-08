import {useParams} from "react-router-dom";
import {retrieveTodoApi} from "../api/TodoApiService";
import {useAuth} from "../security/AuthContext";
import {useEffect, useState} from "react";

export default function TodoDetail() {
  const authContext = useAuth();
  const username = authContext.username;
  const {id} = useParams();

  const [description, setDescription] = useState('');

  function retrieveTodo() {
    retrieveTodoApi(username, id)
    .then((response) => {
      setDescription(response.data.description)
    })
    .catch((error) => console.log(error))
  }

  useEffect(
      () => retrieveTodo(),[id]
  )

  return (
      <div className="container">
        <h1>TODO 상세</h1>
        <div>
          목표 : {description}
        </div>
      </div>
  )
}