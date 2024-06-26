<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>로그인</h1>
    <form method="post">
        이름: <input type="text" name="name">
        비밀번호: <input type="password" name="password">
        <input type="submit">
    </form>
    <pre>${errorMessage}</pre>
</div>

<%@ include file="common/footer.jspf" %>