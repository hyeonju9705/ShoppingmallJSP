<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% request.setCharacterEncoding("UTF-8"); %>
<% String shippingCartId= session.getId();%>
<!-- session아이디가 필요할 때에 이렇게 가져와서 input type="hidden"으로 실어서 보내준다. -->
<title>Insert title here</title>
</head>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<jsp:include page="${context }/menu.jsp"/>
<body>
<div class="jumbotron">
<div class="container">
<h1 class="display-3">주문하기</h1>
</div>
</div>
<div class="container">
<form action="shippingInfoProc.jsp" class="form-horizontal" method="post">
<input type="hidden" name="cartId" value="<%=shippingCartId%>"/>

<div class="form-group row">
<label class="col-sm-2">이름</label>
<div class="col-sm-3">
<input name="shippingName" type="text" class="form-control"/>
</div>
</div>

<div class="form-group row">
<label class="col-sm-2">배송일</label>
<div class="col-sm-3">
<input name="shippingDate" type="text" class="form-control"/>(yyyy/mm/dd)
</div>
</div>
<div class="form-group row">
<label class="col-sm-2">국가</label>
<div class="col-sm-3">
<input name="shippingNation" type="text" class="form-control"/>
</div>
</div>
<div class="form-group row">
<label class="col-sm-2">우편번호</label>
<div class="col-sm-3">
<input name="shippingNumber" type="text" class="form-control"/>
</div>
</div>
<div class="form-group row">
<label class="col-sm-2">주소</label>
<div class="col-sm-3">
<input name="shippingAddress" type="text" class="form-control"/>
</div>
</div>

<div class="form-group row">
<div class="col-sm-offset-2 col-sm-10">
<a href="cart.jsp?cartId=<%=request.getParameter("cartId") %>" class="btn btn-secondary"
role="button">이전</a>
<input type="submit" class="btn btn-primary" value="등록"/>
<a href="checkoutCanceled.jsp" class="btn btn-secondary" role="button">취소</a>
</div>
</div>
</form>
</div>
</body>
<footer>
<jsp:include page="${context }/footer.jsp"/>
</footer>
</html>