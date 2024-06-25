<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>로그인</title>
    </head>
    <body>
        로그인 페이지에 오신 것을 환영합니다.
        <form method="post">
            이름: <input type="text" name="name">
            비밀번호: <input type="password" name="password">
            <input type="submit">
        </form>
        <pre>${errorMessage}</pre>
    </body>
</html>