<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<%
session.removeAttribute("userId"); //세션 삭제
RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
rd.forward(request, response);
%>
</body>
</html>