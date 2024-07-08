import {useParams} from "react-router-dom";
import {retrieveTodoApi} from "../api/TodoApiService";
import {useAuth} from "../security/AuthContext";
import {useEffect, useState} from "react";
import {Formik, Form, Field} from "formik";

export default function TodoDetail() {
  const authContext = useAuth();
  const username = authContext.username;
  const {id} = useParams();

  const [description, setDescription] = useState('');
  const [targetDate, setTargetDate] = useState('');

  function retrieveTodo() {
    retrieveTodoApi(username, id)
    .then((response) => {
      setDescription(response.data.description)
      setTargetDate(response.data.targetDate)
    })
    .catch((error) => console.log(error))
  }

  function onSubmit(values) {
    console.log(values)
  }

  useEffect(
      () => retrieveTodo(),[id]
  )

  return (
      <div className="container">
        <h1>TODO 상세</h1>
        <Formik initialValues={{description, targetDate}} enableReinitialize={true} onSubmit={onSubmit}>
          {
            (props) => (
              <Form>
                <fieldset className="form-group">
                  <label>할 일</label>
                  <Field className="form-control" type="text" name="description"/>
                </fieldset>
                <fieldset className="form-group">
                  <label>목표 일자</label>
                  <Field className="form-control" type="date" name="targetDate"/>
                </fieldset>
                <div>
                  <button className="btn btn-success m-5" type="submit">저장</button>
                </div>
              </Form>
            )
          }
        </Formik>
      </div>
  )
}