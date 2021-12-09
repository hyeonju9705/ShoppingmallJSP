<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.net.URLDecoder,com.conan.vo.Products,java.util.ArrayList" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<jsp:include page="${context }/menu.jsp"/>
</head>
<body>
<%
String cartId= session.getId(); //세션 아이디임
String shippingCartId = "";
String shippingName="";
String shippingDate="";
String shippingNation="";
String shippingNumber="";
String shippingAddress="";
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(int i=0; i<cookies.length; i++){
		Cookie thisCookie = cookies[i];
		String name= thisCookie.getName();//cookie의 이름을 가져온다
		//cookie의 이름이 뭐냐면 cookie를 생성할 때 Cookie ~ = new Cookie("이름",값)에서 
		//" 이름"임 그걸 가져와서 다시 String name에다가 넣어준것
		if(name.equals("shippingCartId"))
			shippingCartId=URLDecoder.decode(thisCookie.getValue(), "utf-8");
		else if(name.equals("shippingName"))
			shippingName=URLDecoder.decode(thisCookie.getValue(), "utf-8");
		else if(name.equals("shippingDate"))
			shippingDate=URLDecoder.decode(thisCookie.getValue(), "utf-8");
		else if(name.equals("shippingNation"))
			shippingNation=URLDecoder.decode(thisCookie.getValue(), "utf-8");
		else if(name.equals("shippingNumber"))
			shippingNumber=URLDecoder.decode(thisCookie.getValue(), "utf-8");
		else if(name.equals("shippingAddress"))
			shippingAddress=URLDecoder.decode(thisCookie.getValue(), "utf-8");
		
		
		System.out.println("shippingCartId : "+ shippingCartId);
	}
}
request.setCharacterEncoding("UTF-8");
%>
<div class="container col-8 alert alert-info">
<div class="text-center"><h1>영수증</h1></div>
<div class="row justify-content-between">
<div class="col-4" align="left">
<strong>배송주소</strong>
<br>이름 : <%=shippingName %>
<br>우편번호 : <%=shippingNumber %>
<br>주소 : <%=shippingAddress %>(<%=shippingNation %>)<br>
</div>
<div class="col-4" align="right">
<p><em>배송일 : <%=shippingDate %></em>
</div>
<!-- 다음페이지 내용
테이블 -->
<div>
<table class="table table-hover">
<tr><th class="text-center">상품</th>
<th class="text-center">수량</th>
<th class="text-center">가격</th><th class="text-center">소계</th></tr>

<!--화면에 필요한 데이터 처리  -->
<%
//카트 리스트에 저장된 제품들의 가격, 수량,소계 등 출력
ArrayList<Products> products = (ArrayList<Products>)session.getAttribute("cartList"); //session 아이디인 cartList가 갖고있는 값들을 products에 담음
request.setAttribute("products", products); //${products}로 사용하기 위해 setting
%>
<c:set var="sum" value="0"/>
<c:forEach var="product" items="${products}">
<!-- setting 해 준 products를 product라는 이름으로 forEach문 돌리겠다
forEach문에서는 el태그로 돌려야 오류가없음 꺽새퍼센트 ㄴㄴ -->
<tr>
<td class="text-center"><em>${product.getProName()}</em></td>
<td class="text-center">${product.getQuantity() }</td>
<td class="text-center">${product.getUnitPrice() }</td>
<td class="text-center"><fmt:formatNumber value="${product.quantity*product.unitPrice}" pattern="#,###"/>원</td>
</tr>
<c:set var="sum" value="${sum+product.quantity*product.unitPrice }"/>
<!-- c:forEach돌리는 거인듯 -->
</c:forEach>
<tr>
<td></td><td></td>
<td class="text-right"><strong>총액 : </strong></td>
<td class="text-center text-danger"><strong>${sum }원</strong></td>
</tr>
</table>
<!-- 다음 페이지 내용 -->
<a href="thankCustomer.jsp" class="btn btn-success" role="button">주문 완료</a>
<a href="checkoutCanceled.jsp" class="btn btn-secondary" role="button">취소</a>
</div>

</div>
</div>
</body>
<footer>
<jsp:include page="${context }/footer.jsp"/>
</footer>
</html>