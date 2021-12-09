<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page
	import="java.sql.*, java.util.ArrayList, com.conan.vo.Products, javax.sql.DataSource, javax.naming.*"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../menu.jsp"></jsp:include>
</head>
<body>

<div class="jumbotron">
<div class="container">
<h1 class="display-3">장바구니</h1>
</div>
<div class="container">
<div class="row">
<table width="100%">
<tr>
<td align="left"><a href="deleteAllCart.jsp?cartId=${cartId}" class="btn btn-danger">전체삭제</a></td>
<td align="right"><a href="shippingInfo.jsp?cartId=${cartId}" class="btn btn-success">주문하기</a></td>
</tr>
</table></div>
<div style="padding-top: 50px">
<table class="table table-hover">
<tr><th>상품</th><th>가격</th><th>수량</th><th>소계</th><th>비고</th></tr>
<%
//카트 리스트에 저장된 제품들의 가격, 수량,소계 등 출력
ArrayList<Products> products = (ArrayList<Products>)session.getAttribute("cartList"); //session 아이디인 cartList가 갖고있는 값들을 products에 담음
//cartList session은 addCart.java에서 세션에 카트리스트가 없을 때에 session을 cartList 라는 이름으로 생성해줬음
request.setAttribute("products", products); //${products}로 사용하기 위해 setting

/* request.setAttribute("sum", sum); */
%>

<c:set var="sum" value="0"/>
<!-- sum=0; 이라는 것과 똑같음 -->
<c:forEach var="product" items="${products}">
<!-- setting 해 준 products를 product라는 이름으로 forEach문 돌리겠다 이제 product.~~로 사용가능-->
<tr>
<td>${ product.proId }-${ product.proName }</td>
<td>${ product.unitPrice }</td>
<td>${product.quantity }</td>
<td><fmt:formatNumber value="${product.quantity*product.unitPrice}" pattern="#,###"/>원</td>
<td><a href="removeCart.jsp?proId=${ product.proId }" class="badge badge-danger">삭제</a></td></tr>
<c:set var="sum" value="${sum+product.quantity*product.unitPrice }"/>
<!-- sum=sum+product.quantity*unitprice 라는 뜻 -->
</c:forEach>

<tr><th></th><th></th><th>총액</th><th><fmt:formatNumber value="${sum }" pattern="#,###"/>원</th><th></th></tr>
</table>
<a href="<%=request.getContextPath()%>/product/productList" class="btn btn-secondary">&laquo; 쇼핑 계속하기</a>
</div>
</div>
</body>
<footer><jsp:include page="../footer.jsp"/></footer>
</html>