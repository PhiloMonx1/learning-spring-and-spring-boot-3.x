import {useEffect, useState} from "react";
import {retrieveAllTodosForUsername} from "../api/TodoApiService";

export default function ListTodosComponent() {
  const [todos, setTodos] = useState([]);

  function refreshTodos() {
    retrieveAllTodosForUsername('eh13')
    .then((response) => setTodos(response.data))
    .catch((error) => console.log(error))
    .finally(() => console.log("finally"))
  }

  useEffect(
      () => refreshTodos(), []
  )

  return (
      <div className="container">
        <h1>나의 TODO 리스트</h1>
        <div>
          <table className="table">
            <thead>
            <tr>
              <th>id</th>
              <th>할 일</th>
              <th>완료 여부</th>
              <th>목표 일자</th>
            </tr>
            </thead>
            <tbody>
            {
              todos.map((todo) => (
                  <tr key={todo.id}>
                    <td>{todo.id}</td>
                    <td>{todo.description}</td>
                    <td>{todo.done.toString()}</td>
                    <td>{todo.targetDate.toString()}</td>
                  </tr>
              ))
            }
            </tbody>
          </table>
        </div>
      </div>
  );
}
