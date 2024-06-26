<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

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

<%@ include file="common/footer.jspf" %>

<script type="text/javascript">
  $('#targetDate').datepicker({
    format: 'yyyy-mm-dd',
    language: 'ko',
    weekStart: 0,
    autoclose: true,
    todayHighlight: true,
  });
</script>