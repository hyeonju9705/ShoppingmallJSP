<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../menu.jsp" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- session아이디랑 userId랑 일치하면? 수정 가능, 아니면 보기만 가능 -->
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">게시글 수정하기</h1>
		</div>
	</div>
	<div class="container">
		<form name="newWrite"
			action="<%=request.getContextPath()%>/board/updateProc.do?boardNo=${bd.boardNo}"
			class="form-horizontal" method="post" onsubmit="return checkForm()">
			<div class="form-group row">
				<label class="col-sm-2 control-label">아이디</label>
				<div class="col-sm-5">
					<input name="userId" type="text" class="form-control"
						value="${bd.userId}" readonly>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">제목</label>
				<div class="col-sm-5">
					<input name="title" type="text" class="form-control"
						value="${bd.title}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">내용</label>
				<div class="col-sm-5">
					<textarea name="content" class="form-control">${bd.content }</textarea>
				</div>
			</div>

			<div class="form group row">
				<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-primary" value="저장">
				<a href="<%=request.getContextPath()%>/board/listProc.do"><input class="btn btn-success" value="목록"></a>
				</div>
			</div>
		</form>
	</div>
</body>
<footer>
	<jsp:include page="../footer.jsp" />
</footer>
</html>