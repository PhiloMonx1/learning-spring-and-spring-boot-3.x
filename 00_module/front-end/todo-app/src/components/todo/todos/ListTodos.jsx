import {useEffect, useState} from "react";
import {retrieveAllTodosForUsernameApi, deleteTodoApi} from "../api/TodoApiService";

export default function ListTodosComponent() {
  const [todos, setTodos] = useState([]);
  const [message, setMessage] = useState("");

  function refreshTodos() {
    retrieveAllTodosForUsernameApi('eh13')
    .then((response) => setTodos(response.data))
    .catch((error) => console.log(error))
  }

  function deleteTodo(id) {
    deleteTodoApi('eh13', id)
    .then(
        () => {
          refreshTodos();
          setMessage(`삭제가 완료되었습니다.`)
        }
    )
  }

  useEffect(
      () => refreshTodos(), []
  )

  return (
      <div className="container">
        <h1>나의 TODO 리스트</h1>
        {message && <div className="alert alert-success">{message}</div>}
        <div>
          <table className="table">
            <thead>
            <tr>
              <th>할 일</th>
              <th>완료 여부</th>
              <th>목표 일자</th>
              <th>삭제</th>
            </tr>
            </thead>
            <tbody>
            {
              todos.map((todo) => (
                  <tr key={todo.id}>
                    <td>{todo.description}</td>
                    <td>{todo.done.toString()}</td>
                    <td>{todo.targetDate.toString()}</td>
                    <td><button className="btn btn-warning" onClick={() => deleteTodo(todo.id)}>삭제</button>
                    </td>
                  </tr>
              ))
            }
            </tbody>
          </table>
        </div>
      </div>
  );
}
