<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<!-- ${contextPath} 를 사용하기위해 써주는것 -->
<nav class="navbar navbar-expand navbar-dark bg-dark">
<div class="container">
<div class="navbar-header">
<a class="navbar-brand" href="${contextPath }/index.jsp">HOME</a>
</div>
<div>
<ul class="navbar-nav mr-auto">
<li class="nav-item"><a class="nav-link" href="${contextPath }/board/listProc.do">게시판</a></li>
<%if(session.getAttribute("userId")!=null){%>
<li class="nav-item"><a class="nav-link" href="#">[<%=(String)session.getAttribute("userId")%>님]></a></li>
<li class="nav-item"><a class="nav-link" href="${contextPath}/logoutProc.jsp">로그아웃</a></li>
<li class="nav-item"><a class="nav-link" href="#">회원 수정</a></li>
<%}else{ %>
<li class="nav-item"><a class="nav-link" href="${contextPath}/loginFrm.jsp">로그인</a></li>
<%} %>
<li class="nav-item"><a class="nav-link" href="${contextPath }/product/productList">상품목록</a></li>
<li class="nav-item"><a class="nav-link" href="${contextPath }/product/addProduct.jsp">상품등록</a></li>
</ul>
</div>
</div>
</nav>
</body>
</html>