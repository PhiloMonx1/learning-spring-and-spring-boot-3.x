<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <title>Todo 목록 페이지</title>
    </head>
    <body>
        <div class="container">
            <h1>Todo List</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>설명</th>
                        <th>목표 일시</th>
                        <th>완료 여부</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.id}</td>
                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.done}</td>
                            <td><a href="update-todo?id=${todo.id}" class="btn btn-info">수정</a></td>
                            <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">삭제</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="add-todo" class="btn btn-success">Todo 추가</a>
        </div>
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    </body>
</html>