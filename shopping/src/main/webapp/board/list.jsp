<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.conan.dao.BoardDAO, com.conan.vo.BoardVO, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../menu.jsp" />
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">게시글</h1>
		</div>
	</div>
	<div class="container">
		<form action="<c:url value="/board/writeFrm.jsp" />" method="post">
			<div>
				<div class="text-right">
					<%-- <span class="badge badge-warning">총 게시글 수 : ${cnt}</span> --%>
				</div>
			</div>
			
			list :<c:out value="${voList }"/>
			<div style="padding-top: 50px">
				<table class="table table-hover">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>내용</td>
						<td>작성일</td>
						<td>조회수</td>
						<td>글쓴이</td>
					</tr>
					<!-- 게시글 출력 부분 생략 -->
					<!-- forEach문 인듯 -->
					<%-- <c:set var="voList" value=${voList }/> --%>
					
					
					<c:forEach var="vo" items="${voList}">
						<tr>
							<td>${vo.boardNo}</td>
							<td><a href="<%=request.getContextPath()%>/board/viewProc.do?boardNo=${vo.boardNo}">${vo.title}</a></td>
							<td>${vo.content}</td>
							<td>${vo.regDate}</td>
							<td>${vo.hit}</td>
							<td>${vo.userId}</td>
						</tr>
					</c:forEach>
				</table>
				<table>
				
				<tr>
					<td width="100%" align="right"><a
						href="<%=request.getContextPath()%>/board/writeFrm.jsp"
						onclick="checkForm(); return false;" class="btn btn-info">
							&laquo;글쓰기</a></td>
				</tr>
				
				</table>

			</div>
		</form>
	</div>
	<script type="text/javascript">
function checkForm(){
	// 로그인 하지 않은 경우, alert 팝업
	var userId=<%=session.getAttribute("userId")%>
		;
			if (userId == null) {
				alert("로그인 해주세요");
			} else {
				location.href = "
	<%=request.getContextPath()%>/board/writeFrm.jsp"; 
	//로그인 한 경우, 글쓰기 페이지로 이동
	}
	}
</script>
</body>
<footer>
	<jsp:include page="../footer.jsp" />
</footer>
</html>