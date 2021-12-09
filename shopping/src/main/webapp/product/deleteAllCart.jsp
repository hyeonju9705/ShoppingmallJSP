<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page
	import="java.sql.*, java.util.ArrayList, com.conan.vo.Products, javax.sql.DataSource, javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String proId=request.getParameter("proId");

ArrayList<Products> products = (ArrayList<Products>)session.getAttribute("cartList");

products.clear(); //arrayList 다 지워주는 함수
//cartList 중 해당 제품만 제거한 다음 cart.jsp로 리다이렉트
RequestDispatcher rd= request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);
%>
</body>
</html>