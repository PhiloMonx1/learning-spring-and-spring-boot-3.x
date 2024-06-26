<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
        <title>Todo 추가 페이지</title>
    </head>
    <body>
        <div class="container">
            <h1>Todo 추가</h1>
            <form:form method="post" modelAttribute="todo">
                <form:input type="hidden" path="id" required="required" />
                <form:input type="hidden" path="done" required="required" />

                <fieldset class="m-3">
                    <form:label path="description">해야 할 일</form:label>
                    <form:input type="text" path="description" required="required" />
                    <form:errors path="description" cssClass="text-warning"/>
                </fieldset>

                <fieldset class="m-3">
                    <form:label path="targetDate">목표 일자</form:label>
                    <form:input type="text" path="targetDate" required="required" />
                    <form:errors path="targetDate" cssClass="text-warning"/>
                </fieldset>

                <input type="submit" class="btn btn-success" />
            </form:form>
        </div>
        <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
        <script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
        <script src="webjars/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.ko.min.js"></script>
        <script type="text/javascript">
          $('#targetDate').datepicker({
            format: 'yyyy-mm-dd',
            language: 'ko',
            weekStart: 0,
            autoclose: true,
            todayHighlight: true,
          });
        </script>
    </body>
</html>