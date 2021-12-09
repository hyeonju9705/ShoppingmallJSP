<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.sql.*, java.util.ArrayList, com.conan.vo.Products, javax.sql.DataSource, javax.naming.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- <%
request.setCharacterEncoding("UTF-8");
String proId=request.getParameter("proId");
String proName=request.getParameter("proName");
int unitPrice=Integer.parseInt(request.getParameter("unitPrice"));
String description=request.getParameter("description");
String manufacturer=request.getParameter("manufacturer");
String category=request.getParameter("category");
String noOfStock=request.getParameter("noOfStock");
String fileName=request.getParameter("fileName");
//1. jndi 서버 객체 생성 jsp8번pdf jstl 9/11 잘 보기/...
InitialContext ic = new InitialContext();
//2. lookup()
DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
//3. getConnection()
Connection conn = ds.getConnection();
PreparedStatement pstmt = conn.prepareStatement("insert into PRODUCTS values(?,?,?,?,?,?,?,?)");
pstmt.setString(1,proId);
pstmt.setString(2,proName);
pstmt.setInt(3,unitPrice);
pstmt.setString(4,description);
pstmt.setString(5,manufacturer);
pstmt.setString(6,category);
pstmt.setString(7,noOfStock);
pstmt.setString(8,fileName);
pstmt.executeUpdate(); //rs를 쓸 필요가 없음 insert,delete,이런거는 쓸필요가 없어서 이렇게 하면 진짜 insert 됨

pstmt.close();
conn.close();
%> --%>
<body>
	<jsp:forward page="productList.jsp" />
</body>
</html>