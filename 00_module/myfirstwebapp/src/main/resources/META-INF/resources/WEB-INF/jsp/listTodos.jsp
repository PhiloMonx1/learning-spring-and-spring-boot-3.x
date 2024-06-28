<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>Todo List</h1>
    <table class="table">
        <thead>
            <tr>
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

<%@ include file="common/footer.jspf" %>