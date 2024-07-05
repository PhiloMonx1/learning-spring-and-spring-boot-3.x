export default function ListTodosComponent() {
  const today = new Date();
  const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay());

  const todos = [
    {id: 1, description: 'AWS 배우기', done: false, targetDate: targetDate},
    {id: 2, description: 'Spring Boot 배우기', done: false, targetDate: targetDate},
    {id: 3, description: 'React 배우기', done: false, targetDate: targetDate},
  ]


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
                    <td>{todo.targetDate.toDateString()}</td>
                  </tr>
              ))
            }
            </tbody>
          </table>
        </div>
      </div>
  );
}
