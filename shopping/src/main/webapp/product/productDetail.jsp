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
<jsp:include page="../menu.jsp"></jsp:include>
<script type="text/javascript">
function addToCart(){
	if(confirm("상품을 장바구니에 추가하시겠습니까?")){
		document.addForm.submit();
	}else{ document.addForm.reset(); }
}
</script>
</head>
<body>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
	<%-- <jsp:useBean id="product" class="com.conan.vo.Products" /> --%>
	<!-- product라는 이름으로 빈즈를 사용하겟따. 이러면 이제 product.~~로 사용가능 -->
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 상세정보</h1>
		</div>
	</div>
	<div class="col-md-6">
		<h3>${product.proName }</h3>
		<p>${product.description }</p>
		<p>
			<b>상품 코드 : </b><span class="badge badge-danger">${product.proId }</span>
		</p>
		<p>
			<b>제조사 : </b>${product.manufacturer }</p>
		<p>
			<b>재고 수 : </b>${product.noOfStock }</p>
		<h3>${product.unitPrice }원</h3>
		<form name="addForm" action="${contextPath}/product/addCart?proId=${product.proId }" method="post">
		<p>
			<a href="#" class="btn btn-info" onclick="addToCart()"> 상품 주문 &raquo;</a> 
			<a href="cart.jsp" class="btn btn-warning"> 장바구니 &raquo;</a>
			 <a
				href="<%=request.getContextPath() %>/product/productList" class="btn btn-secondary" role="button">
				상품 목록 &raquo;</a>
		</p>
		</form>
	</div>
</body>
<footer>
<jsp:include page="../footer.jsp"/>
</footer>
</html>