<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.net.URLEncoder" %>
    <!-- 밑에서 URLEncoder 사용하려면 반드시 import해야함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% request.setCharacterEncoding("UTF-8"); %>
<title>Insert title here</title>
</head>
<%
//urlEncoder 어케쓰는거야ㅠㅠㅠ
Cookie cartId=new Cookie("shippingCartId", URLEncoder.encode(request.getParameter("cartId"),"UTF-8"));
Cookie name= new Cookie("shippingName", URLEncoder.encode(request.getParameter("shippingName"),"UTF-8"));
Cookie date= new Cookie("shippingDate",URLEncoder.encode(request.getParameter("shippingDate"),"UTF-8"));
Cookie nation= new Cookie("shippingNation",URLEncoder.encode(request.getParameter("shippingNation"),"UTF-8"));
Cookie number= new Cookie("shippingNumber",URLEncoder.encode(request.getParameter("shippingNumber"),"UTF-8"));
Cookie address= new Cookie("shippingAddress",URLEncoder.encode(request.getParameter("shippingAddress"),"UTF-8"));
//---
cartId.setMaxAge(24*60*60);
name.setMaxAge(24*60*60);
date.setMaxAge(24*60*60);
nation.setMaxAge(24*60*60);
number.setMaxAge(24*60*60);
address.setMaxAge(24*60*60);
//---
response.addCookie(cartId);
response.addCookie(name);
response.addCookie(date);
response.addCookie(nation);
response.addCookie(number);
response.addCookie(address);
//---
response.sendRedirect("orderConfirmation.jsp");
%>
<body>

</body>
</html>