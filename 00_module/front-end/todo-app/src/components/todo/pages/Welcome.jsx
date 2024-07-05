import {Link, useParams} from "react-router-dom";

export default function WelcomeComponent() {
  const params = useParams()

  return (
      <div className="WelcomeComponent">
        <h1>환영합니다</h1>
        <div>
          {params.username}님! 만나서 반갑습니다.
        </div>
        <div>
          <Link to="/todos">Todo리스트</Link>
        </div>
      </div>
  );
}