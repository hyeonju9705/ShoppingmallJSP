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
</head>
<body>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
		</div>
	</div>
	<div class="container">
		<div class="row" align="center">
			<%-- <c:set var="list" value="<%=pl%>" /> --%>
			<!-- pl의 값을 list라는 변수명으로 setting하겟다 : 이건 servlet에서 함-->
			<c:forEach var="item" items="${list }">
			<!-- setting 한 list의 값을 item에다가 담아가지구 for Each문 돌려
			이렇게 하면 item.~~~로 할 수 있음. 그리고 item은 list의 모든값을 가지고잇어서
			그냥 ${item} 으로 사용하면 모든 값을 출력해줌. -->
				<div class="col-md-4">
				<p><img src="../upload/${item.fileName}" width="100" height="100"></p>
				<!-- product 폴더 밖의 upload폴더라서 상대경로로 설정 -->
					<h3>${item.proName }</h3>
					<p>${item.description }</p>
					<p>${item.unitPrice }원</p>
					<p><a href="<%=request.getContextPath()%>/product/productDetail?proId=${item.proId }" class="btn btn-secondary" role="button">상세정보 &raquo;</a>
					<!-- proId를 getParameter로 넘겨줌 > Productdetail page로 가보자 -->
				</div>
			</c:forEach>
		</div>
	</div>
</body>
<footer>
<jsp:include page="../footer.jsp"/>
</footer>
</html>