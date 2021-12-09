<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="jumbotron">
<div class="container">
<div class="text">
<h1>회원 정보</h1>
</div>
</div>
</div>

 <div class="container">
<div class="text" style="background: pink">
<p style="padding: 10px; text-align: center;">
<%=(String)session.getAttribute("userId") %>님 환영합니다</p>
</div>
</div>
</body>
<footer>
<jsp:include page="footer.jsp"/>
</footer>
</html>