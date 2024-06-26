<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <title>Todo 추가 페이지</title>
    </head>
    <body>
        <div class="container">
            <h1>Todo 추가</h1>
            <form:form method="post" modelAttribute="todo">
                <form:input type="hidden" path="id" required="required" />
                <form:input type="hidden" path="done" required="required" />
                목표: <form:input type="text" path="description" required="required" />
                <input type="submit" class="btn btn-success" />
            </form:form>
        </div>
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    </body>
</html>