<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<jsp:include page="../menu.jsp"/>
</head>
<body>
<div class="jumbotron">
<div class="container">
<h1 class="display-3">주문 완료</h1>
</div>
</div>
<div class="container">
<h2 class="alert alert-danger">주문이 취소되었습니다</h2>
<div class="col-sm-offset-2 col-sm-10">
<a href="${contextPath }/product/productList" class="btn btn-secondary"
role="button">상품 목록</a>
</div>
</div>
</body>
<footer>
<%@include file="../footer.jsp" %>
</footer>
</html>