<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page
	import="java.sql.*, java.util.ArrayList, com.conan.vo.Products, javax.sql.DataSource, javax.naming.*"%>
    <%@ page import="java.net.URLDecoder,com.conan.vo.Products,java.util.ArrayList" %>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<jsp:include page="${context }/menu.jsp"/>
<body>
<%
request.setCharacterEncoding("UTF-8");
String shippingCartId="";
String shippingName="";
String shippingDate="";
String shippingNation="";
String shippingNumber="";
String shippingAddress="";

Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		String name=cookie.getName();
		if(name.equals("shippingCartId"))
			shippingCartId=URLDecoder.decode(cookie.getValue(),"UTF-8");
		
		
		else if(name.equals("shippingDate"))
			shippingDate=URLDecoder.decode(cookie.getValue(),"UTF-8");
		else if(name.equals("shippingName"))
			shippingName=URLDecoder.decode(cookie.getValue(), "utf-8");
		else if(name.equals("shippingNation"))
			shippingNation=URLDecoder.decode(cookie.getValue(), "utf-8");
		else if(name.equals("shippingNumber"))
			shippingNumber=URLDecoder.decode(cookie.getValue(), "utf-8");
		else if(name.equals("shippingAddress"))
			shippingAddress=URLDecoder.decode(cookie.getValue(), "utf-8");
	}
	
	System.out.println("shippingCartId thank  : "+ shippingCartId);
}
%>
<div class="jumbotron">
<div class="container">
<h1 class="display-3">주문 완료</h1>
</div>
</div>
<div class="container">
<h2 class="alert alert-danger">주문해주셔서 감사합니다</h2>
<p>주문은 <%=shippingDate %>에 배송될 예정입니다.
<p> 주문번호 : <%=shippingCartId %>
<p>이름 : <%=shippingName %>
</div>
<div class="container">
<p><a href="productList.jsp" class="btn btn-secondary">&laquo; 상품 목록</a>
</div>
<%
// 세션에서 카트 제거
ArrayList<Products> products = (ArrayList<Products>)session.getAttribute("cartList");
products.clear(); //arrayList 다 지워주는 함수
//쿠키들 제거
/* Cookie[] cookies = request.getCookies(); */
for(int i=0; i<cookies.length; i++){
	cookies[i].setMaxAge(0);
	response.addCookie(cookies[i]);
}
%>
</body>
<footer>
<jsp:include page="${context }/footer.jsp"/>
</footer>
</html>