import {useNavigate, useParams} from "react-router-dom";
import {createTodoApi, retrieveTodoApi, updateTodoApi} from "../api/TodoApiService";
import {useAuth} from "../security/AuthContext";
import {useEffect, useState} from "react";
import {Formik, Form, Field, ErrorMessage} from "formik";
import moment from "moment";

export default function TodoDetail() {
  const authContext = useAuth();
  const username = authContext.username;
  const {id} = useParams();
  const navigate = useNavigate();

  const [description, setDescription] = useState('');
  const [targetDate, setTargetDate] = useState('');

  function retrieveTodo() {
    if(id != -1){
      retrieveTodoApi(username, id)
      .then((response) => {
        setDescription(response.data.description)
        setTargetDate(response.data.targetDate)
      })
      .catch((error) => console.log(error))
    }
  }

  function onSubmit(values) {
    const todo = {
      id: id,
      username: username,
      description: values.description,
      targetDate: values.targetDate,
      done: false
    }

    if(id == -1){
      createTodoApi(username, todo)
      .then((response) => {
        navigate('/todos')
      })
      .catch((error) => console.log(error))
    }else {
      updateTodoApi(username, id, todo)
      .then((response) => {
        navigate('/todos')
      })
      .catch((error) => console.log(error))
    }
  }

  function validate(values){
    let errors = {

    };
    if(values.description.length < 5){
      errors.description = '할 일은 5글자 이상 작성해 주십시오.';
    }
    if(values.targetDate == ''){
      errors.targetDate = '목표 일자를 입력해주세요';
    }
    if(!moment(values.targetDate).isAfter()){
      errors.targetDate = '목표 일자는 현재 날짜보다 과거일 수 없습니다.'
    }
    return errors;
  }

  useEffect(
      () => retrieveTodo(),[id]
  )

  return (
      <div className="container">
        <h1>TODO 상세</h1>
        <Formik
            initialValues={{description, targetDate}}
            enableReinitialize={true}
            onSubmit={onSubmit}
            validate={validate}
            validateOnChange={false}
            validateOnBlur={false}
        >
          {
            (props) => (
              <Form>
                <ErrorMessage
                    name="description"
                    component="div"
                    className="alert alert-warning"
                />
                <ErrorMessage
                    name="targetDate"
                    component="div"
                    className="alert alert-warning"
                />

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