<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Todo 목록 페이지</title>
    </head>
    <body>
        <div>환영합니다. ${name}님</div>
        <hr>
        <h1>Todo List</h1>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>설명</th>
                    <th>목표 일시</th>
                    <th>완료 여부</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>